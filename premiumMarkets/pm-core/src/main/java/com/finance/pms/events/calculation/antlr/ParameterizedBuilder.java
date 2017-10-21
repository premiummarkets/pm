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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.tools.ant.filters.StringInputStream;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;

public abstract class ParameterizedBuilder extends Observable {
	
	public enum ObsMsgType {DELETE,CHANGE, RESET};
	 
	protected class ObsMsg {
		public ObsMsgType type;
		public Operation  operation;
		public ObsMsg(ObsMsgType type, Operation operation) {
			super();
			this.type = type;
			this.operation = operation;
		}
	}
	
	private static MyLogger LOGGER = MyLogger.getLogger(ParameterizedBuilder.class);
	
	private Queue<FormulaParser> parsingQueue;
	
	protected String[] operationPackages;
	
	protected File userOperationsDir;
	protected File disabledUserOperationsDir;
	protected File trashUserOperationsDir;
	
	protected ANTLRParserHelper antlrParser;
	
	//Pre parameterised xml native ops.
	protected Map<String, Operation> nativeOperations;
	//User defined formula.txt ops 
	protected Map<String, Operation> currentOperations;
	
	public static String readableCamelCase(String desrc) {
		
		if (desrc.length() > 1) {
			for (int i = 1; i < desrc.length(); i++) {
				if (Character.isUpperCase(desrc.charAt(i))) {
					desrc = desrc.substring(0,i) + " " + desrc.substring(i);
					i++;
				}
				else if (desrc.charAt(i) == '_' && i < desrc.length()-1) {
					desrc = desrc.substring(0,i) + " " + desrc.substring(i+1);
					i++;
				}
			}
			return (""+desrc.charAt(0)).toUpperCase() + desrc.substring(1);
		} else {
			return desrc.toUpperCase();
		}
		
	}
	
	public ParameterizedBuilder() {
		super();
		currentOperations = new HashMap<String, Operation>();
		parsingQueue = new ConcurrentLinkedQueue<FormulaParser>();
	}
	
	public Map<String, Operation> getCurrentOperations() {
		return getCurrentOperations(true);
	}
	
	public Map<String, Operation> getCurrentOperations(boolean waitForSync) {
		if (waitForSync) SpringContext.getSingleton().syncOnOptPostInit();
		return currentOperations;
	}

	protected Operation fetchNativeOperation(String opRef) {
		return getNativeOperations().get(opRef);
	}
	
	protected Operation fetchAsyncNativeOperation(String opRef) {
		return fetchNativeOperation(opRef);
	}
	
	protected Operation fetchUserOperation(String opRef) {
		return getUserCurrentOperations().get(opRef);
	}
	
	protected Operation fetchAsyncUserOperation(String opRef) {
		return getUserCurrentOperations(false).get(opRef);
	}
	
	public Map<String, Operation> getUserCurrentOperations() {
		return getUserCurrentOperations(true);
	}

	public Map<String, Operation> getUserCurrentOperations(boolean waitForSync) {
	
		Map<String, Operation> map = new HashMap<String, Operation>();
		for (Operation operation : getCurrentOperations(waitForSync).values()) {
			if (operation.getFormula() != null) map.put(operation.getReference(), operation);
		}
		return map;
	}
	
	public Map<String, Operation> getUserEnabledOperations() {
		
		Map<String, Operation> map = new HashMap<String, Operation>();
		for (Operation operation : getCurrentOperations().values()) {
			if (operation.getFormula() != null && !operation.getDisabled()) map.put(operation.getReference(), operation);
		}
		return map;
	}
	
	public void addFormula(String identifier, String formula) throws IOException {
		
		FormulaParser formulaParser = null;
		Boolean isNewOp = false;
		try {
			
		    LOGGER.info("Creating parser for formula "+identifier);
			formulaParser = new FormulaParser(this, identifier, formula);
			
			LOGGER.info("Parsing for formula "+identifier);
			runParsing(formulaParser);
			LOGGER.info("Parsing ok for formula "+identifier);
			
			Operation operation = formulaParser.getBuiltOperation();	
			
			if (operation != null) {
				
			    LOGGER.info("Saving formula operation "+identifier);
				saveUserOperation(identifier, formula);
				
				Operation alreadyExists = getCurrentOperations().get(operation.getReference());
				isNewOp = (alreadyExists == null);
				
				if (!isNewOp) {
					try {
					    LOGGER.info("Updating usage of "+identifier);
						replaceInUse(operation);
					} catch (StackOverflowError e) {
						throw new InstantiationException("Can't solve : "+formulaParser+". The operation is calling its self. Please fix.");
					}
				} 
				
				getCurrentOperations().put(operation.getReference(), operation);
				
				File disabledFormulaFile = new File(disabledUserOperationsDir.getAbsolutePath() + File.separator + identifier+ ".txt");
				disabledFormulaFile.delete();
				getCurrentOperations().get(operation.getReference()).setDisabled(false);
				
			} else {
				throw new InstantiationException("Can't solve : "+formulaParser+". Please fix.");
			}
			
			updateCaches(operation, isNewOp);
			
		} catch (Exception e) {
		    LOGGER.error(e,e);
			throw new IOException(e);
		} finally {
			if (formulaParser != null) {
			    LOGGER.info("Shutting down the parser for "+identifier);
			    formulaParser.shutdown();
			}
		}
	
	}
	
	public void removeFormula(String identifier) throws IOException {

		try {
			
			Operation operation = getCurrentOperations().get(identifier);
			
			List<Operation> checkInUse = checkInUse(operation);
			if (!checkInUse.isEmpty()) throw new RuntimeException("'"+ identifier +"' is used by "+operationListAsString(", ", checkInUse)+". Please delete these first.");
			
			//Delete pre existing trashed
			File formulaFile = new File(trashUserOperationsDir.getAbsolutePath() + File.separator + identifier+ ".txt");
			formulaFile.delete();
			//Move
			Boolean moved = move(identifier, userOperationsDir.getAbsolutePath(), trashUserOperationsDir.getAbsolutePath());
			if (!moved) move(identifier, disabledUserOperationsDir.getAbsolutePath(), trashUserOperationsDir.getAbsolutePath());
			
			getCurrentOperations().remove(identifier);
			
			
			updateCaches(operation, true); //true because the dependency has been made up front so no dependencies should exist any more (ie the deleted operation is guaranteed unused at this point. As if it was new!)

			
		} catch (Exception e) {
			throw new IOException(e);
		}
		
	}
	
	public Operation duplicateOperation(Operation operation, Map<String, Operation> duplOperands) throws IOException { 
		
		List<Operation> operands = operation.getOperands();
		
		for (Operation operand : operands) {
			if (!duplOperands.containsKey(operand.getReference())) {
				Operation duplicatedOperation = subjacentDuplicator().duplicateOperation(operand, duplOperands);
				if (duplicatedOperation != null) {
					duplOperands.put(operand.getReference(), duplicatedOperation);
				}
			}
		}
		
		if (operation.getFormula() != null) {
			String duplReference = infereNextDuplIdx(operation.getReference());
			String duplFormula = infererNewFormula(duplOperands, operation.getFormula());
			addFormula(duplReference, duplFormula);
			return getCurrentOperations().get(duplReference);
		} else {
			return null;
		}
		
	}

	protected abstract String infererNewFormula(Map<String, Operation> duplOperands, String formula);
	protected abstract ParameterizedBuilder subjacentDuplicator();

	protected String infereNextDuplIdx(String sourceReference) {
		
		int idx = 0;
		String destRef = null;
		do {	
			destRef  = sourceReference+"D"+idx;
			idx++;
		} while (getCurrentOperations().get(destRef) != null);
		
		return destRef;
		
	}

	public abstract List<Operation> checkInUse(Operation operation);

	public abstract void replaceInUse(Operation operation);

	public abstract List<Operation> notifyChanged(Operation operation);

	
	protected List<Operation> actualCheckInUse(Collection<Operation> operations, Operation operationToCheck) {
		
		List<Operation> operationUsing = new ArrayList<Operation>();
		for (Operation operation : operations) {
			try {
				actualCheckInUseRecusrsive(Arrays.asList(new Operation[]{operation}), operationToCheck);
			} catch (Exception e) {
				operationUsing.add(operation);
			}
		}
		
		return operationUsing;
	}
	
	protected void actualCheckInUseRecusrsive(Collection<Operation> operations, Operation operationToCheck) {
		
		for (Operation operation : operations) {
			
			if (operation instanceof DoubleMapOperation && operationToCheck.getReference().equals(operation.getReference())) {
				throw new RuntimeException("'"+ operationToCheck.getReference() +"' is used by some other operations. Please delete these first.");
			}
			if (operation.getOperands() != null) {
				actualCheckInUseRecusrsive(operation.getOperands(), operationToCheck);
			}
			
		}
	}
	
	protected void actualReplaceInUse(Collection<Operation> operations, Operation replacemetOp) {
		
		for (Operation operation : operations) {
			actualReplacenUseRecusrsive(operation, replacemetOp);
		}
	}
	
	
	protected void actualReplacenUseRecusrsive(Operation parent, Operation replacementOp) {
		
		List<Operation> operations = parent.getOperands();
		for (int i = 0; i < operations.size(); i++) {
			
			if (operations.get(i) instanceof DoubleMapOperation && replacementOp.getReference().equals(operations.get(i).getReference())) {
				parent.replaceOperand(i, replacementOp);
			}
			if (operations.get(i).getOperands() != null) {
				actualReplacenUseRecusrsive(operations.get(i), replacementOp);
			}
			
		}
	}
	

	public void disableFormula(String identifier) throws IOException  {

		Operation operation = getCurrentOperations().get(identifier);
		if (operation == null) return;
		
		try {
			List<Operation> checkInUse = checkInUse(operation);
			if (!checkInUse.isEmpty()) throw new RuntimeException("'"+ identifier +"' is used by "+operationListAsString(", ", checkInUse)+". Please delete these first.");

			moveToDisabled(identifier);
			operation.setDisabled(true);

		} catch (Exception e) {
			throw new IOException(e);
		}

		updateCaches(operation, false);

	}

	public void enableFormula(String identifier) throws IOException {

		Operation operation = getCurrentOperations().get(identifier);
		if (operation == null) return;

		try {

			moveToEnabled(identifier);
			operation.setDisabled(false);

		} catch (Exception e) {
			throw new IOException(e);
		}

		updateCaches(operation, false);

	}
	
	protected abstract void updateCaches(Operation operation, Boolean isNewOp);
	
	protected void moveToDisabled(String identifier) {
		File formulaFile = new File(userOperationsDir.getAbsolutePath() + File.separator + identifier+ ".txt");
		File disabledFormulaFile = new File(disabledUserOperationsDir.getAbsolutePath() + File.separator + identifier+ ".txt");
		formulaFile.renameTo(disabledFormulaFile);
	}
	
	protected Boolean move(String identifier, String from, String to) {
		File origFile = new File(from + File.separator + identifier+ ".txt");
		File destFile = new File(to + File.separator + identifier+ ".txt");
		
		return origFile.renameTo(destFile);
	}

	private void moveToEnabled(String identifier) {
		File formulaFile = new File(userOperationsDir.getAbsolutePath() + File.separator + identifier+ ".txt");
		File disabledFormulaFile = new File(disabledUserOperationsDir.getAbsolutePath() + File.separator + identifier+ ".txt");
		disabledFormulaFile.renameTo(formulaFile);
	}
	
	private void saveUserOperation(String identifier, String formula) throws IOException {
		File formulaFile = new File(userOperationsDir.getAbsolutePath() + File.separator + identifier+ ".txt");
		BufferedWriter outputStream = new BufferedWriter(new FileWriter(formulaFile));
		outputStream.write(formula);
		outputStream.close();
	}
	
	protected void reloadUserOperations(File operationsDir, Boolean disabled)  {
		
		File[] list = operationsDir.listFiles();
		for (File formulaFile : list) {
			String opName = formulaFile.getName().replace(".txt","");
			
			try {
				
				BufferedReader bufferedReader = new BufferedReader(new FileReader(formulaFile));
				String formula = bufferedReader.readLine();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					formula = formula + "\n"+ line;
				}
				bufferedReader.close();
				
				FormulaParser formulaParser = new FormulaParser(this, opName, formula);
				parsingQueue.offer(formulaParser);
	
			} catch (Exception e) {
				LOGGER.warn(e);
				moveToDisabled(opName);
			}
		}
		
		
		while(!parsingQueue.isEmpty()) {
			
			FormulaParser formulaParser = parsingQueue.poll();
			
			SortedSet<String> unresolvedStuff = new TreeSet<String>();
			for (FormulaParser fp : parsingQueue) {
				unresolvedStuff.add(fp.getOperationName());
			}
			
			try {
				
				runParsing(formulaParser);
				
				Operation parsedOp = formulaParser.getBuiltOperation();	
				if (parsedOp != null) {//Operation is complete
					
					LOGGER.info(this.getClass().getSimpleName() + ", Solved : "+parsedOp.getReference());
					parsedOp.setDisabled(disabled);
					currentOperations.put(parsedOp.getReference(), parsedOp);
					
				} else {//Operation not complete, we add it back to the queue or disable it
					
					if (!unresolvedStuff.contains(formulaParser.getMissingReference())) {
						LOGGER.info(this.getClass().getSimpleName() + ", Can't solve : "+formulaParser+". Disabling.");
						formulaParser.shutdown();
						moveToDisabled(formulaParser.getOperationName());
					} else {
						parsingQueue.offer(formulaParser);
					}
					
				}
				
			} catch (Exception e) {
				LOGGER.info(this.getClass().getSimpleName() + ", Error solving : "+formulaParser+". Disabling. Cause : "+e);
				formulaParser.shutdown();
				moveToDisabled(formulaParser.getOperationName());
			}
		
		}
	}

	protected void runParsing(FormulaParser formulaParser) {
		formulaParser.resume();
		
		while ( formulaParser.isThreadRunning() ) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				LOGGER.error(e,e);
			}
		}
	}

	public void updateEditableOperationLists() {
		antlrParser.updateEditableOperationLists(nativeOperations, getUserEnabledOperations());
	}
	
	public NextToken checkNextToken(String formula) throws IllegalArgumentException {
		return antlrParser.checkNextToken(new StringInputStream(formula));
	}

	public Map<String, Operation> getNativeOperations() {
		return nativeOperations;
	}
	
	public class InUsedExecption extends RuntimeException {
	
		private static final long serialVersionUID = 635237818106787530L;
		
		List<Operation> inUse;

		public InUsedExecption(List<Operation> inUse) {
			super();
			this.inUse = inUse;
		}

		public List<Operation> getInUse() {
			return inUse;
		}
	}
	
	public String operationListAsString(String sepParam, List<Operation> operations) {
		String opStr = "";
		String sep="";
		for (Operation operation : operations) {
			opStr = opStr + sep +operation.getReference();
			sep = sepParam;
		}
		return opStr;
	}

	public abstract void resetCaches();


}
