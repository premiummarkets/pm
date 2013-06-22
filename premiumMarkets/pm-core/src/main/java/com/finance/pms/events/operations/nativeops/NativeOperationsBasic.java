package com.finance.pms.events.operations.nativeops;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;

@XmlRootElement
public class NativeOperationsBasic  implements NativeOperations {
	
	@XmlElementWrapper(name = "operations")
	@XmlElement(name = "operation")
	Set<Operation> operations;
	

	public NativeOperationsBasic() {
		super();
		operations = new HashSet<Operation>();
		
	}

	@Override
	public Map<String, Operation> getOperations() {
		Map<String, Operation> ret = new HashMap<String, Operation>();
		for (Operation operation : operations) {
			//TODO Lower?
			ret.put(operation.getReference(), operation);
		}
		return ret;
	}
	
	public void addOperation(Operation operation) {
		operations.add(operation);
	}

}
