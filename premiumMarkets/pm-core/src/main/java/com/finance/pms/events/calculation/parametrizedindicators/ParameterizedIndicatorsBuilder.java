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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.stream.Collectors;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.InvalidParameterException;
import com.finance.pms.events.calculation.antlr.ANTLRIndicatorsParserHelper;
import com.finance.pms.events.calculation.antlr.ParameterizedBuilder;
import com.finance.pms.events.calculation.antlr.ParsingQueueProvider;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;
import com.finance.pms.events.operations.parameterized.ParameterizedOperationBuilder;

//@Service("parameterizedIndicatorsBuilder")
public class ParameterizedIndicatorsBuilder extends ParameterizedBuilder {

	private static MyLogger LOGGER = MyLogger.getLogger(ParameterizedIndicatorsBuilder.class);

	//@Autowired
	ParameterizedOperationBuilder parameterizedOperationBuilder;

	public ParameterizedIndicatorsBuilder(ParsingQueueProvider parsingQueueProvider, ParameterizedOperationBuilder parameterizedOperationBuilder) {
		
		this.parsingQueueProvider = parsingQueueProvider;
		this.parameterizedOperationBuilder = parameterizedOperationBuilder;

		operationPackages = new String[] {"com.finance.pms.events.operations.conditional.", "com.finance.pms.events.operations.nativeops."};
		antlrParser = new ANTLRIndicatorsParserHelper();

		userOperationsDir = new File(userParameterizedPath + File.separator + "indicators");
		if(!userOperationsDir.exists()) userOperationsDir.mkdirs();

		disabledUserOperationsDir = new File(userParameterizedPath + File.separator + "disabledIndicators");
		if(!disabledUserOperationsDir.exists()) disabledUserOperationsDir.mkdirs();

		trashUserOperationsDir = new File(userParameterizedPath + File.separator + "trashedIndicators");
		if(!trashUserOperationsDir.exists()) trashUserOperationsDir.mkdirs();

		NativeParametrizedIndicators nativeIndicatorsContainer = NativeParametrizedIndicators.loadNativeIndicators();
		nativeOperations = nativeIndicatorsContainer.getCalculators();
		getCurrentOperations().putAll(nativeOperations);
	}
	
	//@PostConstruct
	public void init() {

		resetUserOperations();

		//Is called when operations are deleted, changed or added
		this.parameterizedOperationBuilder.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {

				if (arg == null || !(arg instanceof ObsMsg)) throw new InvalidParameterException();

				ObsMsg msg = (ObsMsg) arg;

				Operation operation = msg.getOperation();
				switch(msg.getType()) {
				case OPERATION_cRud :					//This is when we want to check if an operation is used by indicators (nothing to delete here)
				{
					checkInUsed(operation, getUserCurrentOperations());
					break;
				}
				case OPERATION_cRud_IgnoreDisabled :	//Idem but ignoring disabled in the check
				{
					checkInUsed(operation, getThisParserCompliantUserEnabledOperations());
					break;
				}
				case OPERATION_CrUD :					//Any Operation change or status change
				{
					if (operation != null) {
						clearPreviousCalculationsUsing(operation);
						if (msg.getOldIdentifier().isPresent()) {
							try {
								String reference = operation.getReference();
								saveUserOperation(reference, operation.getFormulae());
							} catch (IOException e1) {
								LOGGER.error(e1, e1);
							}
						}
					}
					break;
				}
				case CREATE_INDICATOR :					//Create a default indicator
					if (operation == null || !getCurrentOperations().containsKey(operation.getReference())) return;
					String reference = operation.getReference();
					String indicatorFormula =  String.format("is bullish when %s is above threshold 0; is bearish when %s is below threshold 0;", reference, reference);
					//Check existing references
					List<Operation> usingIndicators = checkInUse(operation);
					Optional<Operation> alreadyExisting = usingIndicators.stream()
							.filter(op -> (op instanceof EventInfoOpsCompoOperation))
							.filter(op -> (op.getFormulae() != null) && !op.getDisabled() && op.getFormulae().replaceAll("[\\n\\s]", "").contains(indicatorFormula.replaceAll("[\\n\\s]", "")))
							.findAny();
					//Proceed
					List<Operation> usingOps = new ArrayList<>();
					if (alreadyExisting.isEmpty()) {
						try {
							String indicatorId = msg.getOldIdentifier().orElseThrow();
							if (getCurrentOperations().get(indicatorId) != null) {
								//Name already used.
								throw new RuntimeException("Name already used: " + indicatorId);
							}
							addFormula(indicatorId, indicatorFormula);
							usingOps.add(getCurrentOperations().get(indicatorId));
						} catch (IOException e) {
							throw new RuntimeException("Could not create default indicator for " + reference + " with formula " + indicatorFormula);
						}
					} else {
						usingOps.add(alreadyExisting.get());
					}
					throw new InUseException(usingOps);
					//break;
				case UPDATE_OPS_INMEM_INSTANCES :		//This is just updating the ops lists after an ops crud so no need to delete events.
					if (operation != null) {
						String oldIdentifier = msg.getOldIdentifier().orElseThrow();;
						actualReplaceInUse(getCurrentOperations().values(), operation, oldIdentifier);
					}
					break;
				case RESET_OPS_INMEM_INSTANCES :		//Reset ops list from scratch
					resetCaches();
					resetUserOperations();
					updateEditableOperationLists();
					break;
				}
			}

			private void checkInUsed(Operation operation, Map<String, Operation> userOperations) {
				if (operation != null) {
					List<Operation> impactedIndicators = actualCheckInUse(userOperations.values(), operation);
					if (!impactedIndicators.isEmpty()) {
						throw new InUseException(impactedIndicators);
					}
				}
			}

		});

	}

	@Override
	public void clearPreviousCalculationsUsing(Operation operation) throws InUseException {
		try {
			super.clearPreviousCalculationsUsing(operation);
		} catch (InUseException e1) {
			List<Operation> impactedIndicators = e1.getInUse();
			LOGGER.info("Operation " + operation.getReference() + " has been changed, deleting indicators calculation caches for : " + impactedIndicators.stream().map(op -> op.getReference()).reduce((r,e) -> r + ", " + e));
			resetCachesFor(impactedIndicators);
		}
	}

	private void resetCachesFor(Collection<Operation> impactedIndicators) {
		for (Operation opInUse : impactedIndicators) {
			if (opInUse instanceof EventInfo) {
				EventModel.dirtyCacheFor((EventInfo) opInUse, true);
			} else {
				LOGGER.warn("This is not an EventInfo : " + opInUse + ". No cache held needs marked dirty.");
			}
		}
		EventModel.updateEventInfoStamp();
	}

	private void resetUserOperations() {
		reloadUserOperations();
	}

	@Override
	protected Operation fetchOneNativeOperation(String opRef) {
		return parameterizedOperationBuilder.getCurrentOperations().get(opRef);
	}

	@Override
	protected Operation fetchOneUserOperation(String opRef) {
		return parameterizedOperationBuilder.getUserCurrentOperations().get(opRef);
	}

	@Override
	protected Operation fetchAsyncOneNativeOperation(String opRef) {
		return parameterizedOperationBuilder.getCurrentOperations(false).get(opRef);
	}

	@Override
	protected Operation fetchAsyncOneUserOperation(String opRef) {
		return parameterizedOperationBuilder.getUserCurrentOperations(false).get(opRef);
	}

	@Override
	public void addFormula(String identifier, String formula) throws IOException {
		super.addFormula(identifier, formula);
	}

	@Override
	public void updateEditableOperationLists() {
		antlrParser.updateEditableOperationLists(parameterizedOperationBuilder.getNativeOperations(), parameterizedOperationBuilder.getThisParserCompliantUserEnabledOperations());
	}

	@Override
	public List<Operation> notifyChanged(Operation operation, Optional<String> oldIdentifier, ObsMsgType msgType) {
		//Nothing
		return new ArrayList<Operation>();
	}

	//Is called when Indicators are changed
	@Override
	protected List<Operation> updateCaches(Operation operation, Optional<String> oldIdentifier) {
		EventDefinition.refreshMaxPassPrefsEventInfo();
		return new ArrayList<>();
	}

	@Override
	public void resetCaches() {
		//getUserEnabledOperations().values().stream().forEach(o -> clearPreviousCalculations(o)); 	//XXX this will delete ALL the calculated events for ALL operations
		resetCachesFor(getThisParserCompliantUserEnabledOperations().values());
	}

	@Override
	protected String infererNewFormula(Map<String, Operation> duplOperands, String sourceFormula) {

		String destFormula = sourceFormula;
		for (String sourceOpRef : duplOperands.keySet()) {
			destFormula = destFormula.replaceAll(" " + sourceOpRef + "( |;)", " " + duplOperands.get(sourceOpRef).getReference() + "$1");
		}

		return destFormula;
	}

	@Override
	protected ParameterizedBuilder subjacentDuplicator() {
		return this.parameterizedOperationBuilder;
	}

	@Override
	public Map<String, Operation> getThisParserCompliantOperations() {
		return getCurrentOperations().entrySet() 
		          .stream() 
		          .filter(map -> map.getValue() instanceof EventInfo) 
		          .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
	}
	

	@Override
	protected void saveReplaceInUse(String oldIdentifier, String newIdentifier, List<Operation> usingOperations, List<Operation> usingIndicators) {
		if (!newIdentifier.equals(oldIdentifier)) {
			usingOperations.stream().forEach(i -> {
				try {
					i.setFormulae(i.getFormulae().replaceAll(oldIdentifier, newIdentifier));
					parameterizedOperationBuilder.saveUserOperation(i.getReference(), i.getFormulae());
				} catch (IOException e1) {
					LOGGER.error(e1, e1);
				}
			});
			usingIndicators.stream().forEach(i -> {
				try {
					i.setFormulae(i.getFormulae().replaceAll(oldIdentifier, newIdentifier));
					saveUserOperation(i.getReference(), i.getFormulae());
				} catch (IOException e1) {
					LOGGER.error(e1, e1);
				}
			});
		}
	}

	@Override
	public Optional<Operation> createDefaultIndicator(String identifier) {
		return Optional.empty();
	}

}
