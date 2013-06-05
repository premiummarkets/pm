package com.finance.pms.events.calculation.parametrizedindicators;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.conditional.EventConditionHolder;
import com.finance.pms.events.operations.nativeops.NativeOperationsBasic;

@XmlRootElement
public class NativeParametrizedIndicators {
	
	private static MyLogger LOGGER = MyLogger.getLogger(NativeOperationsBasic.class);
	private static final String XMLFILE = System.getProperty("installdir")+File.separator+"nativeIndicators.xml";
	
	
	@XmlElementWrapper(name = "calculators")
	@XmlElement(name = "calculator")
	private List<Operation> calculators;

	public NativeParametrizedIndicators() {
		super();
		calculators = new ArrayList<Operation>();
	}
	
	public static void initNativeIndicators() {
		
		NativeParametrizedIndicators nativeParametrizedIndicators = new NativeParametrizedIndicators();
		
		EventConditionHolder baseEventConditionHolder = new EventConditionHolder();
		nativeParametrizedIndicators.calculators.add(baseEventConditionHolder);
		
		try {
			JAXBContext context = JAXBContext.newInstance(NativeParametrizedIndicators.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(nativeParametrizedIndicators, System.out);
			m.marshal(nativeParametrizedIndicators, new File(XMLFILE));
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
	}
	
	public static NativeParametrizedIndicators loadNativeIndicators () {

		try {
			JAXBContext context = JAXBContext.newInstance(NativeParametrizedIndicators.class);
			Unmarshaller um = context.createUnmarshaller();
			NativeParametrizedIndicators nativeOps = (NativeParametrizedIndicators) um.unmarshal(new FileReader(XMLFILE));
			return nativeOps;
		} catch (Exception e) {
			LOGGER.error(e,e);
			throw new RuntimeException(e);
		}
	}

	public Map<String, Operation> getCalculators() {
		Map<String, Operation> ret = new HashMap<String, Operation>();
		for (Operation operation : calculators) {
			ret.put(operation.getReference().toLowerCase(), operation);
		}
		return ret;
	}


}
