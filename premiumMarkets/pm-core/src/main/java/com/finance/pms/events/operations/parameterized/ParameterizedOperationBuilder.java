package com.finance.pms.events.operations.parameterized;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.NativesXmlManager;
import com.finance.pms.events.calculation.antlr.ANTLROperationsParserHelper;
import com.finance.pms.events.calculation.antlr.ParameterizedBuilder;
import com.finance.pms.events.operations.EmptyMarker;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.NativeOperations;
import com.finance.pms.events.operations.nativeops.talib.TalibOperationGenerator;

public class ParameterizedOperationBuilder  extends ParameterizedBuilder {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ParameterizedOperationBuilder.class);

	NativesXmlManager nativesXmlManager;

	public ParameterizedOperationBuilder(NativesXmlManager nativesXmlManager, TalibOperationGenerator talibOperationGenerator) {
		super();
		operationPackages = new String[] {"com.finance.pms.events.operations.nativeops."};
		antlrParser = new ANTLROperationsParserHelper();
		
		userOperationsDir = new File(System.getProperty("installdir") + File.separator + "userParameterized" + File.separator + "operations");
		if(!userOperationsDir.exists()) userOperationsDir.mkdirs();
		
		disabledUserOperationsDir = new File(System.getProperty("installdir") + File.separator + "userParameterized" + File.separator + "disabledOperations");
		if(!disabledUserOperationsDir.exists()) disabledUserOperationsDir.mkdirs();
		
		this.nativesXmlManager = nativesXmlManager;
		try {
			NativeOperations nativeOperationsContainer = nativesXmlManager.loadNativeOperations();
			nativeOperations = nativeOperationsContainer.getOperations();
			currentOperations.putAll(nativeOperations);
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		
		nativeOperations.putAll(talibOperationGenerator.generate());
		
		resetUserOperations();
		
	}

	public void setNativesXmlManager(NativesXmlManager nativesXmlManager) {
		this.nativesXmlManager = nativesXmlManager;
	}

	@Override
	public List<Operation> checkInUse(Operation operation) {
		
		List<Operation> actualCheckInUse = actualCheckInUse(currentOperations.values(), operation);
		actualCheckInUse.addAll(notifyChanged(operation));
		
		return actualCheckInUse;
	}
	
	@Override
	public void replaceInUse(Operation replacementOp) throws StackOverflowError {
		
		actualReplaceInUse(currentOperations.values(), replacementOp);
		List<Operation> indicatorsUsing = notifyChanged(replacementOp);
		actualReplaceInUse(indicatorsUsing, replacementOp);
		
	}
	

	@Override
	public List<Operation> notifyChanged(Operation operation) {
		
		List<Operation> actualCheckInUse = new ArrayList<Operation>();
		try {
			this.setChanged();
			this.notifyObservers(operation);
		} catch (InUsedExecption e) {
			actualCheckInUse.addAll(e.getInUse());
		}
		
		return actualCheckInUse;
	}


	@Override
	protected void updateCaches() {
		
		List<String> crippled = resetUserOperations();
		if (!crippled.isEmpty()) throw new RuntimeException("Some operations have invalid formulas. Please review : "+crippled);
		
		this.setChanged();
		this.notifyObservers();
		
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

	protected Set<String> emptyMarkerRelatives(EmptyMarker empty) throws IOException {
		Set<String> linkedOperations = empty.linkedOperations();
		for (String linkedOp : linkedOperations) {
			moveToDisabled(linkedOp);
			currentOperations.remove(linkedOp);
		}
		return linkedOperations;
	}


}
