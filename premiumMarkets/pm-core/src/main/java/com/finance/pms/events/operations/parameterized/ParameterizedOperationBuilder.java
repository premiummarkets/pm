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
package com.finance.pms.events.operations.parameterized;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.NativesXmlManager;
import com.finance.pms.events.calculation.antlr.ANTLROperationsParserHelper;
import com.finance.pms.events.calculation.antlr.ParameterizedBuilder;
import com.finance.pms.events.calculation.antlr.ParsingQueueProvider;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.NativeOperations;
import com.finance.pms.events.operations.nativeops.OperationReflectiveGenerator;
import com.finance.pms.events.operations.nativeops.ta.TalibIndicatorsCompositionerOperationReflectiveGenerator;
import com.finance.pms.events.operations.nativeops.talib.TalibOperationGenerator;

public class ParameterizedOperationBuilder extends ParameterizedBuilder {


	private static MyLogger LOGGER = MyLogger.getLogger(ParameterizedOperationBuilder.class);

	NativesXmlManager nativesXmlManager;

	public ParameterizedOperationBuilder(ParsingQueueProvider parsingQueueProvider, NativesXmlManager nativesXmlManager) {
		super();
		operationPackages = new String[] {"com.finance.pms.events.operations.nativeops."};
		antlrParser = new ANTLROperationsParserHelper();

		userOperationsDir = new File(userParameterizedPath + File.separator + "operations");
		if(!userOperationsDir.exists()) userOperationsDir.mkdirs();

		disabledUserOperationsDir = new File(userParameterizedPath + File.separator + "disabledOperations");
		if(!disabledUserOperationsDir.exists()) disabledUserOperationsDir.mkdirs();

		trashUserOperationsDir = new File(userParameterizedPath + File.separator + "trashedOperations");
		if(!trashUserOperationsDir.exists()) trashUserOperationsDir.mkdirs();

		this.nativesXmlManager = nativesXmlManager;
		this.parsingQueueProvider = parsingQueueProvider;
	}

	public void init(
			TalibOperationGenerator talibOperationGenerator,
			TalibIndicatorsCompositionerOperationReflectiveGenerator talibIndicatorsCompositionerOperationReflectiveGenerator,
			OperationReflectiveGenerator... otherOperationReflectiveGenerator) throws Exception {

		//Xml operations based on NativesXmlManager impl
		NativeOperations nativeOperationsContainer = nativesXmlManager.loadNativeOperations();
		nativeOperations = nativeOperationsContainer.getOperations();
		parsingQueueProvider.getCurrentOperations().putAll(nativeOperations);

		//Talib based operations (DoubleMapOperation)
		talibOperationGenerator.initSynoData();
		nativeOperations.putAll(talibOperationGenerator.generate());

		//Talib indicators based EventDefinition operations (EventMapOperation)
		nativeOperations.putAll(talibIndicatorsCompositionerOperationReflectiveGenerator.generate());

		//Other operations generator
		Arrays.stream(otherOperationReflectiveGenerator).forEach(opsGen -> nativeOperations.putAll(opsGen.generate()));

		resetUserOperations();
	}

	public void setNativesXmlManager(NativesXmlManager nativesXmlManager) {
		this.nativesXmlManager = nativesXmlManager;
	}

	/**
	 * For notifying the indicators builder and access the user indicators list.
	 */
	@Override
	public List<Operation> notifyChanged(Operation operation, Optional<String> oldIdentifier, ObsMsgType msgType) {

		List<Operation> actualCheckInUse = new ArrayList<Operation>();
		try {
			this.setChanged();
			this.notifyObservers(new ObsMsg(msgType, operation, oldIdentifier));
		} catch (InUseException e) {
			actualCheckInUse.addAll(e.getInUse());
		}

		return actualCheckInUse;
	}


	@Override
	public List<Operation> updateCaches(Operation operation, Optional<String> oldIdentifier) {

		List<Operation> actualCheckInUse = new ArrayList<Operation>();
		if (oldIdentifier.isPresent()) { //Not a new operation
			updateEditableOperationLists();
			try {
				this.setChanged();
				this.notifyObservers(new ObsMsg(ObsMsgType.UPDATE_OPS_INMEM_INSTANCES, operation, oldIdentifier));
			} catch (InUseException e) {
				actualCheckInUse.addAll(e.getInUse());
			}
		}
		return actualCheckInUse;
	}


	private List<String> resetUserOperations() {

		List<String> crippled = new ArrayList<String>();

		try {
			reloadUserOperations();
		} catch (Exception e) {
			LOGGER.error(e,e);
		}

		return crippled;
	}

	@Override
	public void resetCaches() {

		List<String> crippled = resetUserOperations();
		if (!crippled.isEmpty()) throw new RuntimeException("Some operations have invalid formulas. Please review : " + crippled);

		//TODO Remove update here and in observer as they are done in combo update?
		updateEditableOperationLists();
		this.setChanged();
		this.notifyObservers(new ObsMsg(ObsMsgType.RESET_OPS_INMEM_INSTANCES, null, Optional.empty()));

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
	
	@Override
	public Map<String, Operation> getThisParserCompliantOperations() {
		return getCurrentOperations().entrySet() 
		          .stream() 
		          .filter(map -> !(map.getValue() instanceof EventInfo)) 
		          .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
	}
	
	@Override
	protected void saveReplaceInUse(String oldIdentifier, String newIdentifier, List<Operation> usingOperations, List<Operation> usingIndicators) {
		if (!newIdentifier.equals(oldIdentifier)) {
			usingOperations.stream().forEach(i -> {
				try {
					i.setFormulae(i.getFormulae().replaceAll(oldIdentifier, newIdentifier));
					saveUserOperation(i.getReference(), i.getFormulae());
				} catch (IOException e1) {
					LOGGER.error(e1, e1);
				}
			});
			usingIndicators.stream().forEach(i -> {
				i.setFormulae(i.getFormulae().replaceAll(oldIdentifier, newIdentifier));
				notifyChanged(i, Optional.of("editedMarker"), ObsMsgType.OPERATION_CrUD);
			});
		}
	}

}
