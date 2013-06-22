package com.finance.pms.events.calculation.antlr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.DOTTreeGenerator;
import org.antlr.stringtemplate.StringTemplate;
import org.apache.tools.ant.filters.StringInputStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.EmptyMarker;
import com.finance.pms.events.operations.EmptyOperationMarker;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;

public abstract class ParameterizedBuilder extends Observable {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ParameterizedBuilder.class);
	
	protected String[] operationPackage;
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
			//return (""+desrc.charAt(0)).toUpperCase() + desrc.toLowerCase().substring(1);
			return (""+desrc.charAt(0)).toUpperCase() + desrc.substring(1);
		} else {
			return desrc.toUpperCase();
		}
		
	}
	
	public ParameterizedBuilder() {
		super();
		currentOperations = new HashMap<String, Operation>();
	}

	protected Map<String, EmptyMarker> getEmptyMarkers() {
		return EmptyOperationMarker.emptyOperations;
	}

	public Operation parse(String formula) throws Exception {
		
		CommonTree commonTree = antlrParser.buildTree(new StringInputStream(formula));
		
		if (LOGGER.isDebugEnabled()) {
			DOTTreeGenerator gen = new DOTTreeGenerator();
			StringTemplate st = gen.toDOT(commonTree);
			LOGGER.debug(st);
		}
	  
		Operation builtOperation = buildOperation(commonTree);
		builtOperation.setFormula(formula);
		return builtOperation;
	}
	
	protected Operation buildOperation(CommonTree commonTree) throws Exception {
		
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
				
				if (builtOperation != null) operands.add(builtOperation);
			}
	
			//Node
			operation = instanciateOperation(commonTree, operands, outputSelector);
			
			for ( int i = 0; i < operands.size(); i++ ) {
				if (operands.get(i) instanceof EmptyOperationMarker) {
					((EmptyOperationMarker) operands.get(i)).addParent(operation, i);
				}
			}
	
		}
		
		return operation;
		
	}

	protected Operation fetchNativeOperation(String opRef) {
		return getNativeOperations().get(opRef);
	}

	@SuppressWarnings("unchecked")
	private Operation instanciateOperation(CommonTree child, ArrayList<Operation> operands, String outputSelector) throws Exception {
		
		Operation nativeOperations = fetchNativeOperation(child.getText());
		
		//If there is a pre parameterised operation, we use it
		if (nativeOperations != null) {
			LOGGER.debug("Cloning pre parameterise op : "+nativeOperations);

			Operation clone = (Operation) nativeOperations.clone();
			clone.setOperands(operands);
			clone.setOutputSelector(outputSelector);
			return clone;
		}

		//Else we instantiate a new one (in that case all the operands must be present)
		for (String opPackage : operationPackage) {

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
		LOGGER.warn("child obj : "+child +"; root obj "+child.getAncestor(0)+ "; operands : "+operands+". Error instantiating. Will be left empty for now.");
		EmptyMarker emptyOperationMarker = getEmptyMarkerInstance(child);
		emptyOperationMarker.setOperands(operands);
		return (Operation) emptyOperationMarker;



	}

	protected EmptyMarker getEmptyMarkerInstance(CommonTree child) {
		return EmptyOperationMarker.getInstance(child.getText(), "Empty "+child.getText());
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
		
		try {
			
			Operation operation = parseFormula(identifier, formula);
			saveUserOperation(identifier, formula);
			currentOperations.put(operation.getReference(), operation);
			
			File disabledFormulaFile = new File(disabledUserOperationsDir.getAbsolutePath() + File.separator + identifier+ ".txt");
			disabledFormulaFile.delete();
			currentOperations.get(operation.getReference()).setDisabled(false);
			
			
		} catch (Exception e) {
			throw new IOException(e);
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
	
	protected  Map<String, Operation> reloadUserOperations(File operationsDir)  {
		
		Map<String, Operation> userOps = new HashMap<String, Operation>();
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
				Operation parsedOp = parseFormula(opName, formula);
				userOps.put(parsedOp.getReference(), parsedOp);
				
			} catch (Exception e) {
				LOGGER.warn(e);
				moveToDisabled(opName);
			}
		}
		return userOps;
	}
	
	private Operation parseFormula(String identifier, String formula) throws InstantiationException {
		Operation newOperation;
		try {
			newOperation = parse(formula);
			if (newOperation == null) throw new java.lang.InstantiationException("The following formula is invalid '"+identifier+"' : "+formula);
		} catch (Exception e) {
			LOGGER.warn(e);
			throw new java.lang.InstantiationException("The following formula contains invalid operation(s) '"+identifier+"' : "+formula+"\n\t Related exception : "+e.toString()+((e.getCause() != null)?"\n\t Related cause : "+e.getCause().toString():""));
		}
		newOperation.setReference(identifier);
		return newOperation;
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
