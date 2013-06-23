package com.finance.pms.events.operations.parameterized;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.NativesXmlManager;
import com.finance.pms.events.calculation.antlr.ANTLROperationsParserHelper;
import com.finance.pms.events.calculation.antlr.ParameterizedBuilder;
import com.finance.pms.events.operations.EmptyMarker;
import com.finance.pms.events.operations.EmptyOperationMarker;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.NativeOperations;
import com.finance.pms.events.operations.nativeops.talib.TalibOperationGenerator;

public class ParameterizedOperationBuilder  extends ParameterizedBuilder {

	private static final int NB_EMPTY_RESOLUTION_ITER = 1;
	
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
			//Solving first pass ops (Ie ops depending only on natives or/and params)
			Map<String, Operation> loadedUserOperations = reloadUserOperations(userOperationsDir);
			currentOperations.putAll(loadedUserOperations);

			//Solving snd pass ops (Ie ops depending only on other user ops)
			for (int i =  0 ; i < NB_EMPTY_RESOLUTION_ITER ; i++) {

				//Solve Empty
				solveEmptyOperations();
				LOGGER.info("Solved operations for iteration "+i+" . Empty operation left : "+getEmptyMarkers());

				//Remove infinite reentrant
				Set<String> deads = detectedDeadLocksFor(currentOperations.values(), "");
				for (String dead : deads) {
					EmptyMarker deadEmpty = getEmptyMarkers().get(dead);
					if (deadEmpty != null) {
						crippled.addAll(emptyMarkerRelatives(deadEmpty));
					} else {
						moveToDisabled(dead);
						currentOperations.remove(dead);
						crippled.add(dead);
					}
					
				}
				LOGGER.info("Dead locked operations disabled at iteration "+i+" : "+deads);

			}

			if (!getEmptyMarkers().isEmpty()) {
				LOGGER.warn("Unsolved operation left after "+NB_EMPTY_RESOLUTION_ITER+" iterations. Empty operation left : "+getEmptyMarkers());
				for (EmptyMarker empty : getEmptyMarkers().values()) {
					crippled.addAll(emptyMarkerRelatives(empty));
				}
			}
			
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		
		for (String crippledOp : crippled) {
			getEmptyMarkers().remove(crippledOp);
		}
		LOGGER.info("Empty operation left after cleaning. Empty operation left : "+getEmptyMarkers());
		
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
	
	private void solveEmptyOperations() {
		EmptyOperationMarker.solveEmpty(currentOperations);
	}
	

	private Set<String> detectedDeadLocksFor(Collection<Operation> operations, String parentRefA) {
		
		Set<String> deads = new HashSet<String>();
		for (Operation operation : operations) {
			String parentRef = parentRefA;
			if (parentRefA.isEmpty()) parentRef = operation.getReference();
			if (operation.getFormula() != null || operation instanceof EmptyOperationMarker) {
				if (operation instanceof EmptyOperationMarker) {
					if (parentRef.equals(((EmptyOperationMarker) operation).getMissingRootOperationRef())) {
						LOGGER.warn("Dead lock detected for "+operation);
						deads.add(operation.getReference());
					}
				}
				Set<String> deadOperands = detectedDeadLocksFor(operation.getOperands(), parentRef);
				deads.addAll(deadOperands);
				if (!deadOperands.isEmpty()) deads.add(operation.getReference());//if some operands are dead the parent must be as well ...
			}
		}
		
		return deads;
	}	

}
