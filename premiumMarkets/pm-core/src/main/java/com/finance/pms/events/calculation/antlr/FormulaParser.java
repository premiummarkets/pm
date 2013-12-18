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

public class FormulaParser implements Runnable, Comparable<FormulaParser> , Cloneable {
	
	private static MyLogger LOGGER = MyLogger.getLogger(FormulaParser.class);

	private ParameterizedBuilder parameterizedBuilder;
	
	private Thread holdingThread;
	private Boolean threadSuspended;
	private Boolean shutdown;
	
	private String operationName;
	private String formula;

	private Operation builtOperation;
	private String missingReference;


	public FormulaParser(ParameterizedBuilder parameterizedBuilder, String operationName, String formula) {
		super();
		this.parameterizedBuilder = parameterizedBuilder;
		this.formula = formula;
		this.operationName = operationName;
		
		this.missingReference = operationName;
		this.threadSuspended = false;
		this.shutdown = false;
		
	}

	@Override
	public void run() {
		
		//this.holdingThread = Thread.currentThread();
		
		try {
			
			builtOperation = parseFormula(operationName, formula);
			
		} catch (ExitParsingException e) {
			interrupt();
			LOGGER.error(this + "." + e.toString());
		} catch (InterruptedException e) {
			interrupt();
			LOGGER.info(this + " has been shutdown.");
		} catch (Exception e) {
			interrupt();
			LOGGER.warn(e);
			parameterizedBuilder.moveToDisabled(operationName);
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
			LOGGER.debug(st);
		}

		Operation builtOperation = buildOperation(commonTree);
		if (builtOperation != null) builtOperation.setFormula(formula);
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
				if ((child.getChildCount() == 1 && child.getChild(0).getChildCount() == 0)) {
					
					if (child.getToken().getText().equals("OperationOutput")) {
						//Output selector
						 outputSelector = child.getChild(0).getText().replaceFirst(":", "");
					} else {
						//Value
						builtOperation = buildLeafOperation(child);
					}
					
				} else {
					//Sub op
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
	
	@SuppressWarnings("unchecked")
	private Operation instanciateOperation(CommonTree child, ArrayList<Operation> operands, String outputSelector) throws Exception {
		
		//If there is a pre parameterised operation, we use it
		Operation nativeOperations = fetchNativeOperation(child.getText());
		if (nativeOperations != null) {
			LOGGER.debug("Cloning pre parameterise op : "+nativeOperations);

			Operation clone = (Operation) nativeOperations.clone();
			clone.setOperands(operands);
			clone.setOutputSelector(outputSelector);
			return clone;
		}
		
		Operation userOperation = fetchUserOperation(child.getText());
		
		//No native. If the is a user operation, we use it
		if (userOperation != null) {
			LOGGER.debug("Using user op : "+userOperation);
			if (!operands.isEmpty()) throw new IllegalArgumentException("User operations can't take operands as they are parametrised and must be referenced without any parameter.");
			return userOperation;
		}
 
		//Else we instantiate a new one (in that case all the operands must be present)
		for (String opPackage : parameterizedBuilder.operationPackages) {

			try {

				String childText = opPackage + child.getToken().getText();
				LOGGER.debug("Instantiating NON pre parameterise op : "+childText);

				Class<Operation> opClass = (Class<Operation>) Class.forName(childText);
				Constructor<Operation> constructor = opClass.getConstructor(ArrayList.class, String.class);
				return constructor.newInstance(operands, outputSelector);

			} catch (Exception e) {
				LOGGER.debug(e.toString() + ", cause " + ((e.getCause() != null)?e.getCause().toString():"unknown")+". child obj : "+child +"; root obj "+child.getAncestor(0)+ "; operands : "+operands+". Can't Instantiate : "+opPackage);
			}

		}

		//All above as failed : snd pass marker (for user defined operations)
		LOGGER.warn(this.operationName+ " is missing reference : "+child +"; Parent object "+child.getAncestor(0)+ "; operands : "+operands+". Error instantiating. Will be parked for now.");
		missingReference = child.getText();
		threadSuspended = true;
        synchronized(this) {
            while (threadSuspended)
                wait();
        }
        
        //We try again at resume time
        if (!shutdown) {
        	return instanciateOperation(child, operands, outputSelector);
        } else {
        	throw new InterruptedException();
        }
        
	}
	
	private Operation fetchNativeOperation(String opRef) {
		return parameterizedBuilder.fetchAsyncNativeOperation(opRef);
	}
	
	private Operation fetchUserOperation(String opRef) {
		return parameterizedBuilder.fetchAsyncUserOperation(opRef);
	}

	
	@SuppressWarnings("unchecked")
	private Operation buildLeafOperation(CommonTree child) throws Exception {
		
		String childTxt = child.getToken().getText();
		String value = child.getChild(0).getText();
		String valueClassName = "unknown";
		String opClassName = "unknown";
		try {
			
			valueClassName = "com.finance.pms.events.operations.nativeops."+childTxt + "Value";
			opClassName = "com.finance.pms.events.operations.nativeops."+childTxt + "Operation";

			Class<Value<?>> valueClass = (Class<Value<?>>) Class.forName(valueClassName);
			Constructor<Value<?>> valueConstructor = valueClass.getConstructor(String.class);
			Value<?> valueInstance = valueConstructor.newInstance(value);
			
			Class<Operation> opClass = (Class<Operation>) Class.forName(opClassName);
			Constructor<Operation> opConstructor = opClass.getConstructor();
			Operation opInstance = opConstructor.newInstance();
			
			opInstance.setParameter(valueInstance);
			return opInstance;
			
		} catch (InvocationTargetException e) {
			LOGGER.warn("Child obj : " + child + ", Class name :" + valueClassName+", Const value : "+value, e);
			throw e;
		} catch (Exception e) {
			LOGGER.error("Child obj : " + child + ", Class name :" + valueClassName+", Const value : "+value, e);
			throw e;
		}
	}
	
	
	public Operation getBuiltOperation() {
		return builtOperation;
	}

	public void resume() {
		
		if (holdingThread == null) {//Not started yet
			Thread thread = new Thread(this);
			holdingThread = thread;
			thread.start();
		} else {//We resume
			synchronized (this) {
				 threadSuspended = !threadSuspended;
			     if (!threadSuspended) notify();
			}
		}
		
	}
	
	public void shutdown() {
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

}
