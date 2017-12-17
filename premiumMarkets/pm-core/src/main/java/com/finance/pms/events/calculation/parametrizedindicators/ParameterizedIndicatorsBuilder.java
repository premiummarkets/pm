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
package com.finance.pms.events.calculation.parametrizedindicators;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.IndicatorCalculationServiceMain;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.calculation.antlr.ANTLRIndicatorsParserHelper;
import com.finance.pms.events.calculation.antlr.ParameterizedBuilder;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.conditional.OperationsCompositioner;
import com.finance.pms.events.operations.parameterized.ParameterizedOperationBuilder;

//@Service("parameterizedIndicatorsBuilder")
public class ParameterizedIndicatorsBuilder extends ParameterizedBuilder {
    
    private static MyLogger LOGGER = MyLogger.getLogger(ParameterizedIndicatorsBuilder.class);
	
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

			    switch(msg.getType()) { 
			    case OPERATION_CRUD :    //Any ops change or status change
			        List<Operation> checkInUse = actualCheckInUse(getCurrentOperations().values(), msg.getOperation());
			        if (!checkInUse.isEmpty()) {
			            LOGGER.info("Operation "+msg.getOperation()+" has been changed, deleting related events for : "+checkInUse);
			            cleanEventFor(checkInUse);
			            for (Operation opInUse : checkInUse) {
			                EventModel.dirtyCacheFor((EventInfo) opInUse);
			            }
			            EventModel.updateEventInfoStamp();
			            throw new InUsedExecption(checkInUse);
			        }
			        break;
			    case UPDATE_OPS_INMEM_INSTANCES :	//This is just updating the ops lists after an ops crud so no need to delete events.
			        actualReplaceInUse(getCurrentOperations().values(), msg.getOperation());
			        break;
			    case RESET_OPS_INMEM_INSTANCES :    //Reset ops list from scratch
			        resetUserOperations();
			        updateEditableOperationLists();
			        break;
			    }
			}

            private void cleanEventFor(List<Operation> checkInUse) {
                OperationsCompositioner[] cHoldersInUse = checkInUse.stream()
                    .filter(op -> op instanceof OperationsCompositioner)
                    .collect(Collectors.toList()).toArray(new OperationsCompositioner[0]);
                EventsResources.getInstance().crudDeleteEventsForIndicators(IndicatorCalculationServiceMain.UI_ANALYSIS, EventModel.DEFAULT_DATE, EventSignalConfig.getNewDate(), cHoldersInUse);
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
	protected List<Operation> updateCaches(Operation operation, Boolean isNewOp) {
		EventDefinition.refreshMaxPassPrefsEventInfo();
		return new ArrayList<>();
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
