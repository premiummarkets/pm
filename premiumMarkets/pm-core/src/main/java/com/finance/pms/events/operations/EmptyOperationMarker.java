package com.finance.pms.events.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EmptyOperationMarker extends Operation  implements EmptyMarker {
	
	public static Map<String, EmptyMarker> emptyOperations = new HashMap<String,EmptyMarker>();
	
	Set<ParentLink> parents;
	Set<String> rootParentReferences;
	String missingRootOperationRef;
	
	
	public static EmptyMarker getInstance(String reference, String description) {
		EmptyMarker emptyOperationMarker = emptyOperations.get(reference);
		if (emptyOperationMarker == null) {
			emptyOperationMarker = new EmptyOperationMarker(reference, description);
			emptyOperations.put(reference, emptyOperationMarker);
		}
		return emptyOperationMarker;
	}
	
	private EmptyOperationMarker(String reference, String description) {
		super(reference, description);
		this.missingRootOperationRef = reference;
		parents = new HashSet<ParentLink>();
		rootParentReferences = new HashSet<String>();
	}

	@Override
	public Value<?> calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		throw new RuntimeException("An error slipped through as this operation is invalid. Please review the formula for :"+this.getReference()+". Invalid operation : "+this.missingRootOperationRef);
	}

	public void addParent(Operation parent, int position) {
		parents.add(new ParentLink(parent, position));
	}

	public static void solveEmpty(Map<String, Operation> currentOperations) {
		
		List<String> solvedOps = new ArrayList<String>();
		for (EmptyMarker emptyOp : EmptyOperationMarker.emptyOperations.values()) {
			
			Operation prevPassOp = currentOperations.get(emptyOp.getMissingRootOperationRef());
			if (prevPassOp != null && !(prevPassOp instanceof EmptyOperationMarker)) {
				for (ParentLink parentLink : emptyOp.getParents()) {
					parentLink.parent.getOperands().set(parentLink.paramPosision, (Operation) prevPassOp.clone());
				}
				solvedOps.add(emptyOp.getMissingRootOperationRef());
			}
		}
		
		for (String solved : solvedOps) {
			EmptyOperationMarker.emptyOperations.remove(solved);
		}
		
	}

	public Set<String> linkedOperations() {

		Set<String> linked = new HashSet<String>();
		//If some operands are empty the parent must be disabled as well ...
		for (ParentLink parentLink : getParents()) {
			linked.add(parentLink.getParent().getReference());
		}
		//We also need to remove the direct references
		linked.addAll(getRootParentReferences());
		//Remove the empty operation
		linked.add(missingRootOperationRef);
		
		return linked;
	}

	@Override
	public String toString() {
		return "EmptyOperationMarker [ super()=" + super.toString() +", parents=" + parents + ", missingRootOperationRef=" + missingRootOperationRef + "]";
	}

	public String getMissingRootOperationRef() {
		return missingRootOperationRef;
	}

	public Set<ParentLink> getParents() {
		return parents;
	}

	@Override
	public void setReference(String reference) {
		rootParentReferences.add(reference);
	}

	public Set<String> getRootParentReferences() {
		return rootParentReferences;
	}
	
	
	
}
