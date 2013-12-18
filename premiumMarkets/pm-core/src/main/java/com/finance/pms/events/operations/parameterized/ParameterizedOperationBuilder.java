package com.finance.pms.events.operations.parameterized;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.NativesXmlManager;
import com.finance.pms.events.calculation.antlr.ANTLROperationsParserHelper;
import com.finance.pms.events.calculation.antlr.ParameterizedBuilder;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.NativeOperations;
import com.finance.pms.events.operations.nativeops.talib.TalibOperationGenerator;

public class ParameterizedOperationBuilder  extends ParameterizedBuilder {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ParameterizedOperationBuilder.class);

	NativesXmlManager nativesXmlManager;

	public ParameterizedOperationBuilder(NativesXmlManager nativesXmlManager) {
		super();
		operationPackages = new String[] {"com.finance.pms.events.operations.nativeops."};
		antlrParser = new ANTLROperationsParserHelper();
		
		userOperationsDir = new File(System.getProperty("installdir") + File.separator + "userParameterized" + File.separator + "operations");
		if(!userOperationsDir.exists()) userOperationsDir.mkdirs();
		
		disabledUserOperationsDir = new File(System.getProperty("installdir") + File.separator + "userParameterized" + File.separator + "disabledOperations");
		if(!disabledUserOperationsDir.exists()) disabledUserOperationsDir.mkdirs();
		
		trashUserOperationsDir = new File(System.getProperty("installdir") + File.separator + "userParameterized" + File.separator + "trashedOperations");
		if(!trashUserOperationsDir.exists()) trashUserOperationsDir.mkdirs();
		
		this.nativesXmlManager = nativesXmlManager;

	}

	public void  init(TalibOperationGenerator talibOperationGenerator) throws Exception {
		
			NativeOperations nativeOperationsContainer = nativesXmlManager.loadNativeOperations();
			nativeOperations = nativeOperationsContainer.getOperations();
			currentOperations.putAll(nativeOperations);

			talibOperationGenerator.initSynoData();
			nativeOperations.putAll(talibOperationGenerator.generate());

			resetUserOperations();
	}

	public void setNativesXmlManager(NativesXmlManager nativesXmlManager) {
		this.nativesXmlManager = nativesXmlManager;
	}

	@Override
	public List<Operation> checkInUse(Operation operation) {
		
		List<Operation> values = new ArrayList<Operation>(getCurrentOperations().values());
		values.remove(values.indexOf(operation));
		
		List<Operation> actualCheckInUse = actualCheckInUse(values, operation);
		actualCheckInUse.addAll(notifyChanged(operation));
		
		return actualCheckInUse;
	}
	
	@Override
	public void replaceInUse(Operation replacementOp) throws StackOverflowError {
		
		actualReplaceInUse(getCurrentOperations().values(), replacementOp);
		List<Operation> indicatorsUsing = notifyChanged(replacementOp);
		actualReplaceInUse(indicatorsUsing, replacementOp);
		
	}
	

	@Override
	public List<Operation> notifyChanged(Operation operation) {
		
		List<Operation> actualCheckInUse = new ArrayList<Operation>();
		try {
			this.setChanged();
			this.notifyObservers(new ObsMsg(ObsMsgType.DELETE, operation));
		} catch (InUsedExecption e) {
			actualCheckInUse.addAll(e.getInUse());
		}
		
		return actualCheckInUse;
	}


	@Override
	protected void updateCaches(Operation operation, Boolean isNewOp) {
		
		if (!isNewOp) {
			updateEditableOperationLists();
			
			this.setChanged();
			this.notifyObservers(new ObsMsg(ObsMsgType.CHANGE, operation));
		}
		
	}
	
	
	private List<String> resetUserOperations() {

		List<String> crippled = new ArrayList<String>(); 

		try {
			reloadUserOperations(userOperationsDir, false);
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		
		return crippled;
	}
	
	@Override
	public void resetCaches() {
		
		List<String> crippled = resetUserOperations();
		if (!crippled.isEmpty()) throw new RuntimeException("Some operations have invalid formulas. Please review : "+crippled);
		
		updateEditableOperationLists();

		this.setChanged();
		this.notifyObservers(new ObsMsg(ObsMsgType.RESET, null));
		
	}
	
	@Override
	protected String infererNewFormula(Map<String, Operation> duplOperands, String sourceFormula) {
		
		String destFormula = sourceFormula;
		for (String sourceOpRef : duplOperands.keySet()) {
			destFormula = destFormula.replaceAll("(\\(|,)"+sourceOpRef+"\\(\\)", "$1"+duplOperands.get(sourceOpRef).getReference()+"()");
		}
		
		return destFormula;
	}

	@Override
	protected ParameterizedBuilder subjacentDuplicator() {
		return this;
	}

}
