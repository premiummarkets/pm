package com.finance.pms.events.operations.nativeops;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.math3.util.Precision;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.files.SeriesPrinter;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.util.ValueManipulator;

public class IOsExporterOperation extends StringerOperation {

	private static final int FIRST_INPUT = 3;
	private static MyLogger LOGGER = MyLogger.getLogger(IOsExporterOperation.class);
	
	
	public IOsExporterOperation(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public IOsExporterOperation() {
		this("iosExporter", "Exports all input datasets to a file. The file name is generated: <runtime-id>_ <stock-symbol> _k_training_ <random-id> .csv",
				new NumberOperation("number", "rouding", "Rouding precision", new NumberValue(Double.NaN)),
				new StringOperation("string", "exportFolder", "Export folder path", new StringValue("")),
				new StringOperation("string", "headerPrefixe", "Prefix of the column headers", new StringValue("")),
				new DoubleMapOperation("data", "datasets", "Datasets to export (usually a list of iosAssembler)", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public IOsExporterOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Double rounding = ((NumberValue)inputs.get(0)).getNumberValue();
		String exportFolder = ((StringValue) inputs.get(1)).getValue(targetStock);
		String headersPrefix = ((StringValue) inputs.get(2)).getValue(targetStock);
		
		String fileSuffix = targetStock.getStock().getSymbol() + "_k_training_" + UUID.randomUUID();

		try {
			@SuppressWarnings("unchecked")
			List<? extends NumericableMapValue> developpedInputs = (List<? extends NumericableMapValue>) inputs.subList(FIRST_INPUT, inputs.size());
			SortedMap<Date, double[]> factorisedInput = ValueManipulator.inputListToArray(targetStock, developpedInputs, false, true);
			List<String> inputsOperandsRefs = ValueManipulator.extractOperandFormulaeShort(getOperands().subList(FIRST_INPUT, getOperands().size()), developpedInputs);
			
			if (!Double.isNaN(rounding)) {
				factorisedInput = factorisedInput.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> {
					return Arrays.stream(e.getValue()).map(d -> Precision.round(d, rounding.intValue(), BigDecimal.ROUND_HALF_EVEN)).toArray();
				}, (a,b) -> a, TreeMap::new));
			}
			
			LinkedHashMap<String, SortedMap<Date, double[]>> series = new LinkedHashMap<>();
			series.put(headersPrefix, factorisedInput);
			LinkedHashMap<String, List<String>> headersPrefixes = new LinkedHashMap<>();
			headersPrefixes.put(headersPrefix, inputsOperandsRefs);
			String filePath = SeriesPrinter.printo(fileSuffix, exportFolder, headersPrefixes, series);
			return new StringValue(filePath);
		} catch (Exception e) {
			LOGGER.error(this.getReference() + " : " + e, e);
		}

		return new StringValue("None");
	}

	@Override
	public int operandsRequiredStartShift() {
		return 0;
	}
	
	@Override
	public Boolean isIdemPotent() {
		return false;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock) {
		//Nothing as exports have a unique name
	}

}
