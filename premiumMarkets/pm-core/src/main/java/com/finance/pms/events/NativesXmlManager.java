package com.finance.pms.events;

import java.io.File;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.nativeops.BandNormalizerOperation;
import com.finance.pms.events.operations.nativeops.Division;
import com.finance.pms.events.operations.nativeops.FlipOperation;
import com.finance.pms.events.operations.nativeops.LeftShifterOperation;
import com.finance.pms.events.operations.nativeops.NativeOperations;
import com.finance.pms.events.operations.nativeops.NativeOperationsBasic;
import com.finance.pms.events.operations.nativeops.PMAroonOperation;
import com.finance.pms.events.operations.nativeops.PMLogRocOperation;
import com.finance.pms.events.operations.nativeops.PMMACDOperation;
import com.finance.pms.events.operations.nativeops.PMMightyChaikinOperation;
import com.finance.pms.events.operations.nativeops.PMSMAOperation;
import com.finance.pms.events.operations.nativeops.Product;
import com.finance.pms.events.operations.nativeops.Subtraction;
import com.finance.pms.events.operations.nativeops.Sum;
import com.finance.pms.events.operations.nativeops.TalibMacdOperation;
import com.finance.pms.events.operations.nativeops.TalibSmaOperation;
import com.finance.pms.events.operations.nativeops.UnaryDivision;
import com.finance.pms.events.operations.nativeops.UnaryProduct;
import com.finance.pms.events.operations.nativeops.UnarySum;


public class NativesXmlManager {
	
	private static MyLogger LOGGER = MyLogger.getLogger(NativesXmlManager.class);
	
	protected String xmlfile; //= System.getProperty("installdir")+File.separator+"nativeops.xml";
	
	public NativesXmlManager(String xmlfile) {
		super();
		this.xmlfile = System.getProperty("installdir")+File.separator+xmlfile+".xml";
	}

	public NativeOperations initNativeOperations () {
		
		NativeOperations nativeOperations = initNativeOperationInstance();
		
		//Arithm //=> Arithm are added here so that they show in ui only (indeed they are not parameterised but not in the operation grammar either)
		//This is different with condition which are instantiated on the fly => conditions are indeed hard coded in the indicator grammar.)
		//TODO match the same dynamic as operations in indicators?. This requires more work as the grammar rules are different from on condition to another.
		Sum sum = new Sum();
		nativeOperations.addOperation(sum);
		Product product = new Product();
		nativeOperations.addOperation(product);
		Division division = new Division();
		nativeOperations.addOperation(division);
		Subtraction subtraction = new Subtraction();
		nativeOperations.addOperation(subtraction);
		
		UnarySum unarySum = new UnarySum();
		nativeOperations.addOperation(unarySum);
		UnaryProduct unaryProduct = new UnaryProduct();
		nativeOperations.addOperation(unaryProduct);
		UnaryDivision unaryDivision = new UnaryDivision();
		nativeOperations.addOperation(unaryDivision);
		
		//Talib
		TalibMacdOperation talibMacdOperation = new TalibMacdOperation();
		nativeOperations.addOperation(talibMacdOperation);
		TalibSmaOperation talibSmaOperation = new TalibSmaOperation();
		nativeOperations.addOperation(talibSmaOperation);
		
		//Pm
		PMMACDOperation pmmacdOperation = new PMMACDOperation();
		nativeOperations.addOperation(pmmacdOperation);
		PMSMAOperation pmsmaOperation = new PMSMAOperation();
		nativeOperations.addOperation(pmsmaOperation);
		PMLogRocOperation houseTrendOperation = new PMLogRocOperation();
		nativeOperations.addOperation(houseTrendOperation);
		PMAroonOperation pmAroonOperation = new PMAroonOperation();
		nativeOperations.addOperation(pmAroonOperation);
		PMMightyChaikinOperation pmMChaikinOperation = new PMMightyChaikinOperation();
		nativeOperations.addOperation(pmMChaikinOperation);
		
		//Other
		FlipOperation flipOperation = new FlipOperation();
		nativeOperations.addOperation(flipOperation);
		LeftShifterOperation leftShiterOperation = new LeftShifterOperation();
		nativeOperations.addOperation(leftShiterOperation);
		BandNormalizerOperation bandNormalizerOperation = new BandNormalizerOperation();
		nativeOperations.addOperation(bandNormalizerOperation);
		
		return nativeOperations;
	}

	public void saveNativeOperations(NativeOperations nativeOperations) {
		try {
			JAXBContext context = JAXBContext.newInstance(NativeOperationsBasic.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(nativeOperations, System.out);
			m.marshal(nativeOperations, new File(xmlfile));
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
	}
	
	protected NativeOperations initNativeOperationInstance() {
		return new NativeOperationsBasic();
	}

	public NativeOperations loadNativeOperations() throws Exception {

		try {
			JAXBContext context = JAXBContext.newInstance(NativeOperationsBasic.class);
			Unmarshaller um = context.createUnmarshaller();
			NativeOperations nativeOps = (NativeOperations) um.unmarshal(new FileReader(xmlfile));
			return nativeOps;
		} catch (Exception e) {
			LOGGER.error(e,e);
			throw e;
		}
	}

}
