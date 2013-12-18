package com.finance.pms.events.calculation.parametrizedindicators;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.calculation.antlr.ANTLRIndicatorsParserHelper;
import com.finance.pms.events.calculation.antlr.ParameterizedBuilder;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.parameterized.ParameterizedOperationBuilder;

//@Service("parameterizedIndicatorsBuilder")
public class ParameterizedIndicatorsBuilder extends ParameterizedBuilder {
	
	//@Autowired
	ParameterizedOperationBuilder parameterizedOperationBuilder;
	
	public ParameterizedIndicatorsBuilder(ParameterizedOperationBuilder parameterizedOperationBuilder) {
		this();
		this.parameterizedOperationBuilder = parameterizedOperationBuilder;
	}
	
	public ParameterizedIndicatorsBuilder() {
		
		super();
		operationPackages = new String[] {"com.finance.pms.events.operations.conditional.", "com.finance.pms.events.operations.nativeops."};
		antlrParser = new ANTLRIndicatorsParserHelper();
		
		userOperationsDir = new File(System.getProperty("installdir") + File.separator + "userParameterized" + File.separator + "indicators");
		if(!userOperationsDir.exists()) userOperationsDir.mkdirs();
		
		disabledUserOperationsDir = new File(System.getProperty("installdir") + File.separator + "userParameterized" + File.separator + "disabledIndicators");
		if(!disabledUserOperationsDir.exists()) disabledUserOperationsDir.mkdirs();
		
		trashUserOperationsDir = new File(System.getProperty("installdir") + File.separator + "userParameterized" + File.separator + "trashedIndicators");
		if(!trashUserOperationsDir.exists()) trashUserOperationsDir.mkdirs();
		
		NativeParametrizedIndicators nativeIndicatorsContainer = NativeParametrizedIndicators.loadNativeIndicators();
		nativeOperations = nativeIndicatorsContainer.getCalculators();
		getCurrentOperations().putAll(nativeOperations);
	}

//	@PostConstruct
	public void init() {
		
		resetUserOperations();
		
		//Is called when operations are deleted, changed or added
		this.parameterizedOperationBuilder.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				
				if (arg == null || !(arg instanceof ObsMsg)) throw new InvalidParameterException();
				
				ObsMsg msg = (ObsMsg) arg;
				
				switch(msg.type) { 
				case DELETE :
					List<Operation> checkInUse = actualCheckInUse(getCurrentOperations().values(), msg.operation);
					if (!checkInUse.isEmpty()) throw new InUsedExecption(checkInUse);
					break;
				case CHANGE :	
					actualReplaceInUse(getCurrentOperations().values(), msg.operation);
					break;
				case RESET :
					resetUserOperations();
					updateEditableOperationLists();
					break;
				}
			}
		});

	}

	private void resetUserOperations() {
		reloadUserOperations(userOperationsDir, false);
		reloadUserOperations(disabledUserOperationsDir, true);
	}

	@Override
	protected Operation fetchNativeOperation(String opRef) {
		return parameterizedOperationBuilder.getCurrentOperations().get(opRef);
	}
	
	@Override
	protected Operation fetchUserOperation(String opRef) {
		return parameterizedOperationBuilder.getUserCurrentOperations().get(opRef);
	}
	
	@Override
	protected Operation fetchAsyncNativeOperation(String opRef) {
		return parameterizedOperationBuilder.getCurrentOperations(false).get(opRef);
	}

	@Override
	protected Operation fetchAsyncUserOperation(String opRef) {
		return parameterizedOperationBuilder.getUserCurrentOperations(false).get(opRef);
	}

	@Override
	public void addFormula(String identifier, String formula) throws IOException {
		super.addFormula(identifier, formula);
	}

	@Override
	public void updateEditableOperationLists() {
		antlrParser.updateEditableOperationLists(parameterizedOperationBuilder.getNativeOperations(), parameterizedOperationBuilder.getUserEnabledOperations());
	}

	//Is called when Indicators are changed
	@Override
	public List<Operation> checkInUse(Operation operation) {
		//We don't check root indicator operations as they can't be reused
		return new ArrayList<Operation>();
	}

	@Override
	public void replaceInUse(Operation operation) {
		//We don't check root indicator operations as they can't be reused
	}
	
	@Override
	public List<Operation> notifyChanged(Operation operation) {
		//Nothing
		return new ArrayList<Operation>();
	}

	//Is called when Indicators are changed
	@Override
	protected void updateCaches(Operation operation, Boolean isNewOp) {
		EventDefinition.refreshMaxPassPrefsEventInfo();
	}

	@Override
	public void resetCaches() {
		throw new NotImplementedException();
	}	

	@Override
	protected String infererNewFormula(Map<String, Operation> duplOperands, String sourceFormula) {
		
		String destFormula = sourceFormula;
		for (String sourceOpRef : duplOperands.keySet()) {
			destFormula = destFormula.replaceAll(" "+sourceOpRef+"( |;)", " "+duplOperands.get(sourceOpRef).getReference()+"$1");
		}
		
		return destFormula;
	}

	@Override
	protected ParameterizedBuilder subjacentDuplicator() {
		return this.parameterizedOperationBuilder;
	}
	
}
