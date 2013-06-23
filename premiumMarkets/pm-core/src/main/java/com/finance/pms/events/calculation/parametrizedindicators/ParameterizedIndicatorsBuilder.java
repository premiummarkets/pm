package com.finance.pms.events.calculation.parametrizedindicators;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.annotation.PostConstruct;

import org.antlr.runtime.tree.CommonTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.calculation.antlr.ANTLRIndicatorsParserHelper;
import com.finance.pms.events.calculation.antlr.ParameterizedBuilder;
import com.finance.pms.events.operations.EmptyMarker;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.parameterized.ParameterizedOperationBuilder;

@Service("parameterizedIndicatorsBuilder")
public class ParameterizedIndicatorsBuilder extends ParameterizedBuilder {
	
	//private static MyLogger LOGGER = MyLogger.getLogger(ParameterizedIndicatorsBuilder.class);
	
	@Autowired
	ParameterizedOperationBuilder parameterizedOperationBuilder;
	
	//For tests
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
		
		NativeParametrizedIndicators nativeIndicatorsContainer = NativeParametrizedIndicators.loadNativeIndicators();
		nativeOperations = nativeIndicatorsContainer.getCalculators();
		currentOperations.putAll(nativeOperations);
	}

	@PostConstruct
	public void init() {
		
		resetUserOperations();
		
		//Is called when operations are changed
		this.parameterizedOperationBuilder.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				if (arg != null)  {
					
					List<Operation> checkInUse = actualCheckInUse(currentOperations.values(), (Operation)arg);
					//if (!checkInUse.isEmpty()) throw new RuntimeException("'"+ (Operation)arg +"' is used by "+checkInUse+". Please delete these first.");
					if (!checkInUse.isEmpty()) throw new InUsedExecption(checkInUse);

				} else {
					resetUserOperations();
				}
			}
		});

	}

	private void resetUserOperations() {
		Map<String, Operation> loadedUserOperations = reloadUserOperations(userOperationsDir);
		currentOperations.putAll(loadedUserOperations);
		
		Map<String, Operation> loadedDisabledUserOperations = reloadUserOperations(disabledUserOperationsDir);
		for (Operation disabledOp : loadedDisabledUserOperations.values()) {
			disabledOp.setDisabled(true);
		}
		currentOperations.putAll(loadedDisabledUserOperations);
	}

	@Override
	protected Operation fetchNativeOperation(String opRef) {
		return parameterizedOperationBuilder.getCurrentOperations().get(opRef);
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
		//We don't check root operations
		return new ArrayList<Operation>();
	}
	
	@Override
	public List<Operation> notifyChanged(Operation operation) {
		//Nothing
		return new ArrayList<Operation>();
	}

	//Is called when Indicators are changed
	@Override
	protected void updateCaches() {
		EventDefinition.refreshMaxPassPrefsEventInfo();
	}

	@Override
	protected EmptyMarker getEmptyMarkerInstance(CommonTree child) {
		throw new UnsupportedOperationException("Empty user operation "+child);
	}


	
	
	
	
	
}
