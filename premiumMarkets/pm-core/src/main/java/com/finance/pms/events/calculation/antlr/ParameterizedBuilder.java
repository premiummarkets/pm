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

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;

public abstract class ParameterizedBuilder extends Observable {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ParameterizedBuilder.class);
	
	private Queue<FormulaParser> parsingQueue;
	
	protected String[] operationPackages;
	protected File userOperationsDir;
	protected File disabledUserOperationsDir;
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
	
	protected Operation fetchNativeOperation(String opRef) {
		return getNativeOperations().get(opRef);
	}
	

	protected Operation fetchUserOperation(String opRef) {
		return getUserCurrentOperations().get(opRef);
	}

	public Map<String, Operation> getCurrentOperations() {
		return currentOperations;
	}
	

	public Map<String, Operation> getUserCurrentOperations() {
	
		Map<String, Operation> map = new HashMap<String, Operation>();
		for (Operation operation : currentOperations.values()) {
			if (operation.getFormula() != null) map.put(operation.getReference(), operation);
		}
		return map;
	}
	
	public Map<String, Operation> getUserEnabledOperations() {
		
		Map<String, Operation> map = new HashMap<String, Operation>();
		for (Operation operation : currentOperations.values()) {
			if (operation.getFormula() != null && !operation.getDisabled()) map.put(operation.getReference(), operation);
		}
		return map;
	}
	
	public void addFormula(String identifier, String formula) throws IOException {
		
		FormulaParser formulaParser = null;
		try {
			
			formulaParser = new FormulaParser(this, identifier, formula);
			
			runParsing(formulaParser);
			
			Operation operation = formulaParser.getBuiltOperation();	
			
			if (operation != null) {
				saveUserOperation(identifier, formula);
				Operation alreadyExists = currentOperations.get(operation.getReference());
				if (alreadyExists != null) {
					try {
						replaceInUse(operation);
					} catch (StackOverflowError e) {
						throw new InstantiationException("Can't solve : "+formulaParser+". The operation is calling its self. Please fix.");
					}
				} 
				currentOperations.put(operation.getReference(), operation);
				
				File disabledFormulaFile = new File(disabledUserOperationsDir.getAbsolutePath() + File.separator + identifier+ ".txt");
				disabledFormulaFile.delete();
				currentOperations.get(operation.getReference()).setDisabled(false);
				
			} else {
				throw new InstantiationException("Can't solve : "+formulaParser+". Please fix.");
			}
			
		} catch (Exception e) {
			throw new IOException(e);
			
		} finally {
			if (formulaParser != null) formulaParser.shutdown();
		}
		
		updateCaches();
		
	}
	
	public void removeFormula(String identifier) throws IOException {
		
		try {
			
			Operation operation = currentOperations.get(identifier);
			
			List<Operation> checkInUse = checkInUse(operation);
			if (!checkInUse.isEmpty()) throw new RuntimeException("'"+ identifier +"' is used by "+operationListAsString(", ", checkInUse)+". Please delete these first.");
			
			File formulaFile = new File(userOperationsDir.getAbsolutePath() + File.separator + identifier+ ".txt");
			formulaFile.delete();
			File disabledFormulaFile = new File(disabledUserOperationsDir.getAbsolutePath() + File.separator + identifier+ ".txt");
			disabledFormulaFile.delete();
			
			currentOperations.remove(identifier);
			
		} catch (Exception e) {
			throw new IOException(e);
		}

		updateCaches();

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
			if (operation != operationToCheck && operation instanceof DoubleMapOperation && operationToCheck.getReference().equals(operation.getReference())) {
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
			
			//if (operations.get(i) != replacementOp && operations.get(i) instanceof DoubleMapOperation && replacementOp.getReference().equals(operations.get(i).getReference())) {
			if (operations.get(i) instanceof DoubleMapOperation && replacementOp.getReference().equals(operations.get(i).getReference())) {
				parent.replaceOperand(i, replacementOp);
			}
			if (operations.get(i).getOperands() != null) {
				actualReplacenUseRecusrsive(operations.get(i), replacementOp);
			}
			
		}
	}
	

	public void disableFormula(String identifier) throws IOException  {

		Operation operation = currentOperations.get(identifier);
		if (operation == null) return;
		
		try {
			List<Operation> checkInUse = checkInUse(operation);
			if (!checkInUse.isEmpty()) throw new RuntimeException("'"+ identifier +"' is used by "+operationListAsString(", ", checkInUse)+". Please delete these first.");

			moveToDisabled(identifier);
			operation.setDisabled(true);

		} catch (Exception e) {
			throw new IOException(e);
		}

		updateCaches();

	}

	public void enableFormula(String identifier) throws IOException {

		Operation operation = currentOperations.get(identifier);
		if (operation == null) return;

		try {

			moveToEnabled(identifier);
			operation.setDisabled(false);

		} catch (Exception e) {
			throw new IOException(e);
		}

		updateCaches();

	}
	
	protected abstract void updateCaches();
	
	protected void moveToDisabled(String identifier) {
		File formulaFile = new File(userOperationsDir.getAbsolutePath() + File.separator + identifier+ ".txt");
		File disabledFormulaFile = new File(disabledUserOperationsDir.getAbsolutePath() + File.separator + identifier+ ".txt");
		formulaFile.renameTo(disabledFormulaFile);
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
		outputStream.flush();
		
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
			
			SortedSet<FormulaParser> queueState = new TreeSet<FormulaParser>();
			for (FormulaParser formulaParser : parsingQueue) {
				queueState.add(formulaParser.clone());
			}
			
			FormulaParser formulaParser = parsingQueue.poll();
			
			try {
				
				runParsing(formulaParser);
				
				Operation parsedOp = formulaParser.getBuiltOperation();	
				if (parsedOp != null) {//Operation is complete
					
					LOGGER.info("Solved : "+parsedOp.getReference());
					parsedOp.setDisabled(disabled);
					currentOperations.put(parsedOp.getReference(), parsedOp);
					
				} else {//Operation not complete, we add it back to the queue
					
					if (queueState.contains(formulaParser)) {
						LOGGER.info("Can't solve : "+formulaParser+". Disabling.");
						formulaParser.shutdown();
						moveToDisabled(formulaParser.getOperationName());
					} else {
						parsingQueue.offer(formulaParser);
					}
					
				}
				
			} catch (Exception e) {
				LOGGER.info("Error solving : "+formulaParser+". Disabling. Cause : "+e);
				formulaParser.shutdown();
				moveToDisabled(formulaParser.getOperationName());
			}
		
		}
	}

	protected void runParsing(FormulaParser formulaParser) {
		formulaParser.resume();
		
		while (formulaParser.getHoldingThread() == null || (formulaParser.getHoldingThread().isAlive() && !formulaParser.getThreadSuspended()) ) {
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

}
