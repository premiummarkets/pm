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
package com.finance.pms.events.calculation.antlr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Optional;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.apache.tools.ant.filters.StringInputStream;

import com.finance.pms.PostInitMonitor;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.SelectedIndicatorsCalculationService;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.LeafOperation;
import com.finance.pms.events.operations.nativeops.OperationReferenceOperation;
import com.finance.pms.events.operations.nativeops.OperationReferenceValue;

public abstract class ParameterizedBuilder extends Observable {

	public static final String userParameterizedPath = System.getProperty("installdir") + File.separator + "userParameterized";
	public enum ObsMsgType {OPERATION_CrUD, OPERATION_cRud, OPERATION_cRud_IgnoreDisabled, UPDATE_OPS_INMEM_INSTANCES, RESET_OPS_INMEM_INSTANCES, CREATE_INDICATOR};

	protected class ObsMsg {
		private ObsMsgType type;
		private Operation  operation;
		private Optional<String> oldIdentifier;
		public ObsMsg(ObsMsgType type, Operation operation, Optional<String> oldIdentifier) {
			super();
			this.type = type;
			this.operation = operation;
			this.oldIdentifier = oldIdentifier;
		}
		public ObsMsgType getType() {
			return type;
		}
		public Operation getOperation() {
			return operation;
		}
		public Optional<String> getOldIdentifier() {
			return oldIdentifier;
		}
	}

	private static MyLogger LOGGER = MyLogger.getLogger(ParameterizedBuilder.class);

	protected String[] operationPackages;

	public File userOperationsDir;
	protected File disabledUserOperationsDir;
	protected File trashUserOperationsDir;

	protected ANTLRParserHelper antlrParser;
	protected ParsingQueueProvider parsingQueueProvider;

	//Pre parameterised xml native ops.
	protected ConcurrentHashMap<String, Operation> nativeOperations;

	private String opsFilterExpr;

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

	//Returns everything (Sync==true)
	public Map<String, Operation> getCurrentOperations() {
		return getCurrentOperations(true);
	}

	//Returns everything - for Builder internal use
	public Map<String, Operation> getCurrentOperations(boolean waitForSync) {
		if (waitForSync) PostInitMonitor.waitForOptPostInitEnd();
		return parsingQueueProvider.getCurrentOperations();
	}
	
	//Return This Builder specific natives
	public Map<String, Operation> getNativeOperations() {
		return nativeOperations;
	}
	
	//Return User operations (ie Everything but natives) (Sync==true)
	public Map<String, Operation> getUserCurrentOperations() {
		return getUserCurrentOperations(true);
	}

	//Return User operations (ie Everything but natives) - for Builder internal use
	public Map<String, Operation> getUserCurrentOperations(boolean waitForSync) {
		Map<String, Operation> map = new HashMap<String, Operation>();
		for (Operation operation : getCurrentOperations(waitForSync).values()) { //Removing natives
			if (operation.getFormulae() != null) map.put(operation.getReference(), operation);
		}
		return map;
	}
	
	//Returns Everything which is Enabled User operations (ie User operations but natives and disabled)
	public Map<String, Operation> getUserEnabledOperations() {
		Map<String, Operation> map = new HashMap<String, Operation>();
		for (Operation operation : getUserCurrentOperations().values()) { //Removing natives and disabled
			if (operation.getFormulae() != null && !operation.getDisabled()) map.put(operation.getReference(), operation);
		}
		return map;
	}
	
	//Returns This Builder specific User operations (ie This Builder specific but natives)
	public Map<String, Operation> getThisParserCompliantUserCurrentOperations() {
		Map<String, Operation> map = new HashMap<String, Operation>();
		for (Operation operation : getThisParserCompliantOperations().values()) { //Removing natives
			if (operation.getFormulae() != null) map.put(operation.getReference(), operation);
		}
		return map;
	}
	
	//Returns Everything which is This Builder specific (Sync==true)
	public abstract Map<String, Operation> getThisParserCompliantOperations();

	//Returns This Builder specific Enabled User operations (ie This Builder specific but natives and disabled)
	public Map<String, Operation> getThisParserCompliantUserEnabledOperations() {
		Map<String, Operation> map = new HashMap<String, Operation>();
		for (Operation operation : getThisParserCompliantOperations().values()) { //Removing natives and disabled
			if (operation.getFormulae() != null && !operation.getDisabled()) map.put(operation.getReference(), operation);
		}
		return map;
	}

	protected Operation fetchOneNativeOperation(String opRef) {
		return getNativeOperations().get(opRef);
	}

	protected Operation fetchAsyncOneNativeOperation(String opRef) {
		return fetchOneNativeOperation(opRef);
	}

	protected Operation fetchOneUserOperation(String opRef) {
		return getUserCurrentOperations().get(opRef);
	}

	protected Operation fetchAsyncOneUserOperation(String opRef) {
		return getUserCurrentOperations(false).get(opRef);
	}

	public void addFormula(String identifier, String formula) throws IOException {

		FormulaParser formulaParser = null;
		Boolean isNewOp = false;
		try {

			if (LOGGER.isDebugEnabled()) LOGGER.debug("Creating parser for formula " + identifier);
			formulaParser = new FormulaParser(this, identifier, formula, false);

			if (LOGGER.isDebugEnabled()) LOGGER.debug("Parsing for formula " + identifier);
			runParsing(formulaParser);
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Parsing ok for formula " + identifier);

			Operation operation = formulaParser.getBuiltOperation();

			if (operation != null) {

				if (LOGGER.isDebugEnabled()) LOGGER.debug("Saving formula operation " + identifier);
				saveUserOperation(identifier, formula);

				Operation alreadyExists = getCurrentOperations().get(operation.getReference());
				isNewOp = (alreadyExists == null);

				if (!isNewOp) {
					try {
						LOGGER.info("Updating usage of " + identifier);
						replaceInUse(operation, identifier);
					} catch (StackOverflowError e) {
						throw new InstantiationException("Can't solve: " + formulaParser + ". The operation is calling its self. Please fix.");
					}
				}

				getCurrentOperations().put(operation.getReference(), operation);

				File disabledFormulaFile = new File(disabledUserOperationsDir.getAbsolutePath() + File.separator + identifier+ ".txt");
				disabledFormulaFile.delete();
				getCurrentOperations().get(operation.getReference()).setDisabled(false);

			} else {
				throw new InstantiationException("Can't solve: " + formulaParser + ". Please fix.");
			}

			updateCaches(operation, (isNewOp)?Optional.empty():Optional.of(identifier));

		} catch (Exception e) {
			LOGGER.error(e, e);
			throw new IOException(e);
		} finally {
			if (formulaParser != null) {
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Shutting down the parser for " + identifier);
				formulaParser.shutdown();
			}
		}

	}
	
	public void renameFormula(String oldIdentifier, String newIdentifier) throws IOException {

		FormulaParser formulaParser = null;
		try {
			
			Operation alreadyExists = getCurrentOperations().get(newIdentifier);
			if (alreadyExists != null) throw new InstantiationException("Can't rename: " + oldIdentifier + " to " + newIdentifier + ". Operation already exists.");

			Operation oldOperation = getCurrentOperations().get(oldIdentifier);
			if (oldOperation == null) throw new InstantiationException("Can't rename: " + oldIdentifier + " to " + newIdentifier + ". " + oldIdentifier + " does not exist.");
			
			
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Creating parser for formula " + newIdentifier);
			formulaParser = new FormulaParser(this, newIdentifier, oldOperation.getFormulae(), false);
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Parsing for formula " + newIdentifier);
			runParsing(formulaParser);
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Parsing ok for formula " + newIdentifier);
			Operation newOperation = formulaParser.getBuiltOperation();
			saveUserOperation(newIdentifier, oldOperation.getFormulae());
			
			try {
				LOGGER.info("Updating usage of " + oldIdentifier + " with " + newIdentifier);
				replaceInUse(newOperation, oldIdentifier);
			} catch (StackOverflowError e) {
				throw new InstantiationException("Can't rename: " + oldIdentifier + " to " + newIdentifier + ". The operation is calling its self? Please fix.");
			}
			
			getCurrentOperations().put(newOperation.getReference(), newOperation);
			updateCaches(newOperation, Optional.of(oldIdentifier));

			removeFormula(oldIdentifier);

		} catch (Exception e) {
			LOGGER.error(e, e);
			throw new IOException(e);
		} finally {
			if (formulaParser != null) {
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Shutting down the parser for " + newIdentifier);
				formulaParser.shutdown();
			}
		}
		
	}

	public List<Operation> removeFormula(String identifier) {

			Operation operation = getCurrentOperations().get(identifier);

			List<Operation> checkInUse = checkInUse(operation);
			if (!checkInUse.isEmpty()) {
				//throw new RuntimeException("'" + identifier + "' is used by " + operationListAsString(", ", checkInUse) + ". Please delete these first.");
				LOGGER.warn("'" + identifier + "' is used by " + operationListAsString(", ", checkInUse) + ". Please delete these first.");
				return checkInUse;
			}

			//Delete pre existing trashed
			File formulaFile = new File(trashUserOperationsDir.getAbsolutePath() + File.separator + identifier + ".txt");
			formulaFile.delete();
			//Move
			moveToTrash(identifier);

			getCurrentOperations().remove(identifier);

			updateCaches(operation, Optional.empty()); //Optional.empty() (ie no previous id) because the dependency check has been made up front so no dependencies should exist any more (ie the deleted operation is guaranteed unused at this point. As if it was new!)
			
			return new ArrayList<>();
	}
	
	public void destroyFormula(String identifier) throws IOException {

		try {

			Operation operation = getCurrentOperations().get(identifier);

			List<Operation> checkInUse = checkInUse(operation);
			if (!checkInUse.isEmpty()) throw new RuntimeException("'" + identifier + "' is used by " + operationListAsString(", ", checkInUse) + ". Please delete these first.");

			//Delete hard delete (no move to trash)
			File formulaFileUser = new File(userOperationsDir.getAbsolutePath() + File.separator + identifier+ ".txt");
			formulaFileUser.delete();
			File formulaFileDis = new File(disabledUserOperationsDir.getAbsolutePath() + File.separator + identifier+ ".txt");
			formulaFileDis.delete();
			File formulaFileTrash = new File(trashUserOperationsDir.getAbsolutePath() + File.separator + identifier+ ".txt");
			formulaFileTrash.delete();

			getCurrentOperations().remove(identifier);

			updateCaches(operation, Optional.empty()); //Optional.empty() (ie no previous id) because the dependency check has been made up front so no dependencies should exist any more (ie the deleted operation is guaranteed unused at this point. As if it was new!)

		} catch (Exception e) {
			throw new IOException(e);
		}

	}

	protected abstract String infererNewFormula(Map<String, Operation> duplOperands, String formula);
	protected abstract ParameterizedBuilder subjacentDuplicator();

	protected String infereNextDuplIdx(String sourceReference) {

		int idx = 0;
		String destRef = null;
		do {
			destRef  = sourceReference+"D"+idx;
			idx++;
		} while (getCurrentOperations().get(destRef) != null);

		return destRef;

	}

	public List<Operation> checkInUse(Operation operation) {

		Map<String, Operation> currentOperationsMap = getCurrentOperations();
		List<Operation> values = new ArrayList<Operation>(currentOperationsMap.values());
		values.remove(values.indexOf(operation));

		List<Operation> actualCheckInUse = actualCheckInUse(values, operation);
//		//FIXME probably not needed anymore
//		actualCheckInUse.addAll(notifyChanged(operation, Optional.empty(),ObsMsgType.OPERATION_cRud));
		
		if (actualCheckInUse.contains(operation)) actualCheckInUse.remove(actualCheckInUse.indexOf(operation));
		LOGGER.info("Operations/Indicators using " + operation.getReference() + ": " + actualCheckInUse.stream().map(op -> op.getReference()).reduce((r,e) -> r + ", " + e));

		return actualCheckInUse;

	}

	private void replaceInUse(Operation replacementOp, String oldIdentifier) throws StackOverflowError {

		String newIdentifier = replacementOp.getReference();
		
		//All Ops and Inds: getCurrentOperations()
		List<Operation> usingOpsAndInds = actualReplaceInUse(getCurrentOperations().values(), replacementOp, oldIdentifier);
		LOGGER.info("Operations/Indicators using " + oldIdentifier + ": " + usingOpsAndInds.stream().map(op -> op.getReference()).reduce((r,e) -> r + ", " + e));

		List<Operation> usingOperations = usingOpsAndInds.stream() 
	        .filter(o -> !(o instanceof EventInfo)) 
	        .collect(Collectors.toList());
		
		List<Operation> usingIndicators = usingOpsAndInds.stream() 
		        .filter(o -> o instanceof EventInfo) 
		        .collect(Collectors.toList());

		LOGGER.info("Operations using " + newIdentifier + " after replace: " + usingOperations.stream().map(op -> op.getReference()).reduce((r,e) -> r + ", " + e));
		LOGGER.info("Indicators using " + newIdentifier + " after replace: " + usingIndicators.stream().map(op -> op.getReference()).reduce((r,e) -> r + ", " + e));
		
		saveReplaceInUse(oldIdentifier, newIdentifier, usingOperations, usingIndicators);

	}

	protected abstract void saveReplaceInUse(String oldIdentifier, String newIdentifier, List<Operation> usingOperations, List<Operation> usingIndicators);

	public abstract List<Operation> notifyChanged(Operation operation, Optional<String> oldIdentifier, ObsMsgType msgType);


	protected List<Operation> actualCheckInUse(Collection<Operation> operations, Operation operationToCheck) {

		if (operationToCheck == null) return new ArrayList<>();

		List<Operation> operationUsing = new ArrayList<Operation>();
		for (Operation operation : operations) {
			try {
				actualCheckInUseRecursive(Arrays.asList(new Operation[]{operation}), operationToCheck);
			} catch (Exception e) {
				operationUsing.add(operation);
			}
		}

		return operationUsing;
	}

	@SuppressWarnings("unchecked")
	private void actualCheckInUseRecursive(Collection<Operation> operations, Operation operationToCheck) {

		for (Operation operation : operations) {

			if (	(!(operation instanceof LeafOperation) && operationToCheck.getReference().equals(operation.getReference())) ||
					(operation instanceof OperationReferenceOperation && 
							operation.getParameter() != null &&
							operationToCheck.getReference().equals(((OperationReferenceValue<? extends Operation>) operation.getParameter()).getValue(null).getReference()))
			) {
				throw new RuntimeException("'" + operationToCheck.getReference() + "' is used by some other operations. Please delete these first.");
			}
			if (operation.getOperands() != null) {
				actualCheckInUseRecursive(operation.getOperands(), operationToCheck);
			}

		}
	}

	protected List<Operation> actualReplaceInUse(Collection<Operation> operations, Operation replacemetOp, String oldIdentifier) {

		if (replacemetOp == null) return new ArrayList<>();

		List<Operation> operationUsing = new ArrayList<Operation>();
		for (Operation operation : operations) {
			Boolean replacementOpIsUsedByOperation = actualReplacenUseRecursive(operation, replacemetOp, oldIdentifier);
			if (replacementOpIsUsedByOperation) operationUsing.add(operation);
		}

		return operationUsing;
	}


	@SuppressWarnings("unchecked")
	private Boolean actualReplacenUseRecursive(Operation parent, Operation replacementOp, String oldIdentifier) {
		Boolean replacementOpIsUsedByParent = false;
		List<Operation> operations = parent.getOperands();
		for (int i = 0; i < operations.size(); i++) {
			
			//Standard
			if (!(operations.get(i) instanceof LeafOperation) && oldIdentifier.equals(operations.get(i).getReference())) {
				
				parent.replaceOperand(i, replacementOp); //effective replacement
				replacementOpIsUsedByParent = true;
				
			}
			if (operations.get(i).getOperands() != null) {
				replacementOpIsUsedByParent = actualReplacenUseRecursive(operations.get(i), replacementOp, oldIdentifier) || replacementOpIsUsedByParent;
			}
			
			//OperationReferenceOperation
			if (operations.get(i) instanceof OperationReferenceOperation) {
				OperationReferenceValue<? extends Operation> operationReferenceParameter = (OperationReferenceValue<? extends Operation>) operations.get(i).getParameter();
				if (operationReferenceParameter != null) {
					Operation referedOperation = operationReferenceParameter.getValue(null);
					if (oldIdentifier.equals(referedOperation.getReference())) {
						operations.get(i).setParameter(new OperationReferenceValue<Operation>((Operation) replacementOp.clone())); //effective replacement
						replacementOpIsUsedByParent = true;
					}
					replacementOpIsUsedByParent = actualReplacenUseRecursive(referedOperation, replacementOp, oldIdentifier) || replacementOpIsUsedByParent;
				}
			}

		}
		return replacementOpIsUsedByParent;
	}

	protected abstract List<Operation> updateCaches(Operation operation, Optional<String> oldIdentifier);

	protected void moveToTrash(String identifier) {
		Boolean moved = move(identifier, userOperationsDir.getAbsolutePath(), trashUserOperationsDir.getAbsolutePath(), true);
		if (!moved) move(identifier, disabledUserOperationsDir.getAbsolutePath(), trashUserOperationsDir.getAbsolutePath(), true);
	}

	protected void moveToDisabled(String identifier) {
		move(identifier, userOperationsDir.getAbsolutePath(), disabledUserOperationsDir.getAbsolutePath(), false);
	}

	private Boolean move(String identifier, String from, String to, boolean tamper) {
		File origFile = new File(from + File.separator + identifier + ".txt");
		File destFile = new File(to + File.separator + identifier + ".txt");
		boolean isRenamedTo = origFile.renameTo(destFile);
		if (isRenamedTo && tamper) destFile.setLastModified(new Date().getTime());
		return isRenamedTo;
	}

	public void saveUserOperation(String identifier, String formula) throws IOException {
		File formulaFile = new File(userOperationsDir.getAbsolutePath() + File.separator + identifier + ".txt");
		BufferedWriter outputStream = new BufferedWriter(new FileWriter(formulaFile));
		outputStream.write(formula);
		outputStream.close();
	}

	protected void reloadUserOperations() {

		Queue<FormulaParser> parsingQueue = parsingQueueProvider.filledParsingQueue();
		
		while(!parsingQueue.isEmpty()) {

			FormulaParser formulaParser = parsingQueue.poll();

			SortedSet<String> unresolvedStuff = new TreeSet<String>();
			for (FormulaParser fp : parsingQueue) {
				unresolvedStuff.add(fp.getOperationName());
			}

			try {

				runParsing(formulaParser);

				Operation parsedOp = formulaParser.getBuiltOperation();
				if (parsedOp != null) {//Operation is complete
					if (LOGGER.isDebugEnabled()) LOGGER.debug(this.getClass().getSimpleName() + ", Solved: " + parsedOp.getReference() + ", Disabled: " + formulaParser.isDisabled() + ", Formulae: " + parsedOp.getFormulae());
					parsedOp.setDisabled(formulaParser.isDisabled());
					parsingQueueProvider.getCurrentOperations().put(parsedOp.getReference(), parsedOp);

				} else {//Operation not complete, we add it back to the queue or disable it

					if (!unresolvedStuff.contains(formulaParser.getMissingReference())) {
						LOGGER.info(this.getClass().getSimpleName() + ", Can't solve: " + formulaParser + ". Disabling.");
						formulaParser.shutdown();
						moveToTrash(formulaParser.getOperationName());
					} else {
						parsingQueue.offer(formulaParser);
					}

				}

			} catch (Exception e) {
				LOGGER.info(this.getClass().getSimpleName() + ", Error solving: " + formulaParser + ". Disabling. Cause: " + e);
				formulaParser.shutdown();
				moveToTrash(formulaParser.getOperationName());
			}

		}
	}

	protected void runParsing(FormulaParser formulaParser) {
		formulaParser.resume();
		while ( formulaParser.isThreadRunning() ) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				LOGGER.error(e, e);
			}
		}
	}
	
	public Operation buildOneTimeOperation(String operationId, String formula) {
		FormulaParser formulaParser = null;
		try {
			formulaParser = new FormulaParser(this, operationId, formula, false);
			runParsing(formulaParser);
			Operation operation = formulaParser.getBuiltOperation();
			return operation;
		} finally {
			if (formulaParser != null) {
				formulaParser.shutdown();
			}
		}
	}

	public void updateEditableOperationLists() {
		antlrParser.updateEditableOperationLists(nativeOperations, getUserEnabledOperations());
	}
	
	public boolean isAntlrInitialised() {
		return antlrParser.isInitialised();
	}

	public NextToken checkNextToken(String formula) throws IllegalArgumentException {
		return antlrParser.checkNextToken(new StringInputStream(formula));
	}

	public class InUseException extends RuntimeException {

		private static final long serialVersionUID = 635237818106787530L;

		List<Operation> inUse;

		public InUseException(List<Operation> inUse) {
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
			opStr = opStr + sep + operation.getReference();
			sep = sepParam;
		}
		return opStr;
	}

	public abstract void resetCaches();

	public void clearPreviousCalculationsUsing(Operation operation) throws InUseException {
		List<Operation> impactedIndicators = actualCheckInUse(getCurrentOperations().values(), operation);
		if (!impactedIndicators.isEmpty()) {
			LOGGER.info("Operation " + operation.getReference() + " has been changed, invalidating operations: " + impactedIndicators.stream().map(op -> op.getReference()).reduce((r,e) -> r + ", "+e));
			invalidateOperations(impactedIndicators);
			throw new InUseException(impactedIndicators);
		}
	}

	//We invalidate only the impacted operations but not their operands (no recursion)
	private void invalidateOperations(List<Operation> impactedOps) {
		impactedOps.stream().forEach(o -> o.invalidateOperation(SelectedIndicatorsCalculationService.getAnalysisName(), Optional.empty(), Optional.of(o.getReference())));
	}

	public abstract Optional<Operation> createDefaultIndicator(String identifier) ;


}
