package com.finance.pms.events.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface EmptyMarker {

	String getMissingRootOperationRef();

	List<ParentLink> getParents();

	void setOperands(ArrayList<Operation> operands);
	
	Set<String> linkedOperations();

}
