/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.calculation.antlr;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.DOTTreeGenerator;
import org.antlr.stringtemplate.StringTemplate;
import org.apache.tools.ant.filters.StringInputStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.Value;

public class FormulaParser implements Runnable, Comparable<FormulaParser>, Cloneable {

	private static MyLogger LOGGER = MyLogger.getLogger(FormulaParser.class);

	private ParameterizedBuilder parameterizedBuilder;

	private Thread holdingThread;
	private Boolean threadSuspended;
	private Boolean shutdown;

	private String operationName;
	private String formula;

	private Operation builtOperation;
	private String missingReference;

	private final Boolean isDisabled;


	public FormulaParser(ParameterizedBuilder parameterizedBuilder, String operationName, String formula, Boolean isDisabled) {
		super();
		this.parameterizedBuilder = parameterizedBuilder;
		this.formula = formula;
		this.operationName = operationName;

		this.missingReference = operationName;
		this.threadSuspended = false;
		this.shutdown = false;
		this.isDisabled = isDisabled;

	}

	@Override
	public void run() {

		try {

			builtOperation = parseFormula(operationName, formula);

		} catch (ExitParsingException e) {
			interrupt();
			LOGGER.warn(this + ". Cause: " + e.toString());
			parameterizedBuilder.moveToTrash(operationName);
		} catch (InterruptedException e) {
			interrupt();
			if (LOGGER.isDebugEnabled()) LOGGER.debug(this + " has been shutdown.");
		} catch (Exception e) {
			interrupt();
			LOGGER.warn(this + ". Cause: " + e.toString(), e);
			parameterizedBuilder.moveToTrash(operationName);
		}

	}

	private void interrupt() {
		this.holdingThread = null;
		this.shutdown = true;
	}

	private Operation parseFormula(String operationName, String formula) throws Exception {
		Operation newOperation = parse(formula);
		if (newOperation != null) newOperation.setReference(operationName);
		return newOperation;
	}

	private Operation parse(String formula) throws Exception {
		CommonTree commonTree = parameterizedBuilder.antlrParser.buildTree(new StringInputStream(formula));

		if (LOGGER.isDebugEnabled()) {
			DOTTreeGenerator gen = new DOTTreeGenerator();
			StringTemplate st = gen.toDOT(commonTree);
			if (LOGGER.isDebugEnabled()) LOGGER.debug(st);
		}

		Operation builtOperation = buildOperation(commonTree);
		if (builtOperation != null) builtOperation.setFormulae(formula);
		return builtOperation;
	}

	private Operation buildOperation(CommonTree commonTree) throws Exception {

		Operation operation = null;
		if (commonTree != null ) {

			ArrayList<Operation> operands = new ArrayList<Operation>();
			String outputSelector = null;
			for ( int i = 0; i < commonTree.getChildCount(); i++ ) {

				CommonTree child = (CommonTree) commonTree.getChild(i);
				Operation builtOperation = null;
				if ((child.getChildCount() == 1 && child.getChild(0).getChildCount() == 0)) { //Leaf

					if (child.getToken().getText().equals("OperationOutput")) {
						//Output selector
						outputSelector = child.getChild(0).getText().replaceFirst(":", "");
					} else {
						//Value
						while (true) {
							try {
								builtOperation = buildLeafOperation(child);
								break;
							} catch (Exception e) { //Handling the operation reference case as a leaf
								if (e.getCause() instanceof MissingReferenceException) {
									missingReference = ((MissingReferenceException) e.getCause()).getMissingReference();
									suspend();
								} else {
									throw e;
								}
							}
						}
					}

				} else { //Build operands first
					builtOperation = buildOperation(child);
				}

				if (builtOperation != null) {
					operands.add(builtOperation);
				} 
			}

			//Node
			operation = instanciateOperation(commonTree, operands, outputSelector);

		}

		return operation;

	}

	private void suspend() throws InterruptedException {
		threadSuspended = true;
		synchronized(this) {
			while (threadSuspended) {
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Wait on suspended " + this);
				wait();
			}
		}
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Wait on suspended released for " + this);
		
		//We give up on shutdown
		if (shutdown) {
			throw new InterruptedException();
		}
	}

	@SuppressWarnings("unchecked")
	private Operation instanciateOperation(CommonTree child, ArrayList<Operation> operands, String outputSelector) throws Exception {

		//If there is a pre parameterised operation, we use it
		Operation nativeOperation = fetchNativeOperation(child.getText());
		if (nativeOperation != null) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Cloning pre parameterised native op: " + nativeOperation.getReference());
			Operation clone = (Operation) nativeOperation.clone();
			clone.setOperands(operands);
			clone.setOutputSelector(outputSelector);
			return clone;
		}

		Operation userOperation = fetchUserOperation(child.getText());

		//No native. If there is a user operation, we use it
		if (userOperation != null) {
			if (!operands.isEmpty()) throw new IllegalArgumentException("User operations can't take operands as they are parametrised and must be referenced without any parameter.");
			LOGGER.info("Cloning user op: " + userOperation.getReference());
			return (Operation) userOperation.clone(); //XXX FIXME should I clone user operations as well? XXX
		}

		//Else we instantiate a new one (in that case all the operands must be present)
		for (String opPackage : parameterizedBuilder.operationPackages) {

			try {

				String childText = opPackage + child.getToken().getText();
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Instantiating NON pre parameterise native op: " + childText);

				Class<Operation> opClass = (Class<Operation>) Class.forName(childText);
				Constructor<Operation> constructor = opClass.getConstructor(ArrayList.class, String.class);
				Operation newInstance = constructor.newInstance(operands, outputSelector);
				
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Initialising instance of: " + newInstance.getReference());
				return newInstance;
				
			} catch (ClassNotFoundException e) {
				if (LOGGER.isDebugEnabled()) LOGGER.debug(e.getMessage() + " is not native.");
			} catch (Exception e) {
				LOGGER.error(e.getMessage() + ", exception: " + e.toString() + ", cause: " + ((e.getCause() != null)? e.getCause().toString() : "unknown") + ". " +
							 "child obj: " + child + "; root obj " + child.getAncestor(0) + "; operands: " + operands + ". Can't Instantiate: " + opPackage);
			}

		}

		//All above as failed : snd pass marker (for user defined operations)
		if (LOGGER.isDebugEnabled()) LOGGER.debug(this.operationName + " is missing reference : " + child + "; Parent object " + child.getAncestor(0) + "; operands : " + operands + ". Could not instanciate. Will be parked for now.");
		missingReference = child.getText();
		suspend();
		
		return instanciateOperation(child, operands, outputSelector);

	}

	private Operation fetchNativeOperation(String opRef) {
		return parameterizedBuilder.fetchAsyncOneNativeOperation(opRef);
	}

	private Operation fetchUserOperation(String opRef) {
		return parameterizedBuilder.fetchAsyncOneUserOperation(opRef);
	}


	@SuppressWarnings("unchecked")
	private Operation buildLeafOperation(CommonTree child) throws Exception {

		String childTxt = child.getToken().getText();
		String value = child.getChild(0).getText();
		String valueClassName = "unknown";
		String opClassName = "unknown";
		try {

			valueClassName = "com.finance.pms.events.operations.nativeops." + childTxt + "Value";
			opClassName = "com.finance.pms.events.operations.nativeops." + childTxt + "Operation";

			Class<Value<?>> valueClass = (Class<Value<?>>) Class.forName(valueClassName);
			Constructor<Value<?>> valueConstructor = valueClass.getConstructor(String.class);
			Value<?> valueInstance = valueConstructor.newInstance(value);

			Class<Operation> opClass = (Class<Operation>) Class.forName(opClassName);
			Constructor<Operation> opConstructor = opClass.getConstructor();
			Operation opInstance = opConstructor.newInstance();

			opInstance.setParameter(valueInstance);
			return opInstance;

		} catch (InvocationTargetException e) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Child obj : " + child + ", Class name :" + valueClassName + ", Const value : " + value + " unresolved yet, " + e);
			throw e;
		} catch (Exception e) {
			LOGGER.error("Child obj : " + child + ", Class name :" + valueClassName + ", Const value : " + value, e);
			throw e;
		}
	}


	public Operation getBuiltOperation() {
		return builtOperation;
	}

	public void resume() {
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Resuming Parser thread.");
		if (holdingThread == null) {//Not started yet
			Thread thread = new Thread(this);
			holdingThread = thread;
			thread.start();
		} else {//We resume
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Synchronising on " + this + " in order to change state to " + !threadSuspended);
			synchronized (this) {
				threadSuspended = !threadSuspended;
				if (!threadSuspended) {
					if (LOGGER.isDebugEnabled()) LOGGER.debug("Notifying wait of " + this);
					notify();
				}
				if (LOGGER.isDebugEnabled()) LOGGER.debug("End of notification for " + this);
			}
		}

	}

	public void shutdown() {
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Shutting down Parser thread.");
		if (holdingThread != null) {
			this.shutdown = true;
			resume();
		}
	}

	public Thread getHoldingThread() {
		return holdingThread;
	}

	public Boolean getThreadSuspended() {
		return threadSuspended;
	}

	public String getMissingReference() {
		return missingReference;
	}

	public String getOperationName() {
		return operationName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((missingReference == null) ? 0 : missingReference.hashCode());
		result = prime * result + ((operationName == null) ? 0 : operationName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormulaParser other = (FormulaParser) obj;
		if (missingReference == null) {
			if (other.missingReference != null)
				return false;
		} else if (!missingReference.equals(other.missingReference))
			return false;
		if (operationName == null) {
			if (other.operationName != null)
				return false;
		} else if (!operationName.equals(other.operationName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  operationName + " is missing " + missingReference;
	}

	@Override
	public int compareTo(FormulaParser o) {
		int compareTo = operationName.compareTo(o.operationName);
		if (compareTo == 0) return missingReference.compareTo(o.missingReference);
		return compareTo;
	}

	@Override
	protected FormulaParser clone() {

		FormulaParser clone = null;
		try {
			clone = (FormulaParser) super.clone();
		} catch (CloneNotSupportedException e) {
			LOGGER.error(e,e);
		}

		return clone;
	}

	protected boolean isThreadRunning() {
		return (this.holdingThread == null && !this.shutdown) || (this.holdingThread != null && this.holdingThread.isAlive() && !this.threadSuspended);
	}

	public Boolean isDisabled() {
		return isDisabled;
	}

}
