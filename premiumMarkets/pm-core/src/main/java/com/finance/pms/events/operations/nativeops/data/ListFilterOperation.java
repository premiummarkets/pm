
package com.finance.pms.events.operations.nativeops.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.AnyValueListValue;
import com.finance.pms.events.operations.nativeops.ListOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.StringerOperation;
import com.finance.pms.events.operations.nativeops.Value;

public class ListFilterOperation extends StringerOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(ListFilterOperation.class);

	public ListFilterOperation(String reference, String description, Operation ... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public ListFilterOperation() {
		this("listFilter", "Filters data from a list. Only the last match is returned.",
				new ListOperation("list", "ListOfLines", "A string containing a comma separated list of things. As if a line a csv dataset.",  null),
				new NumberOperation("number","columnIndex", "Index of the column to retrieve", new NumberValue(0.0)),
				new StringOperation("string","pattern", "Simple pattern (.*[]{,}+ accepted) or contained characters sequence, used to filter one line.", new StringValue("NONE")),
				new StringOperation("string","default", "Default value.", new StringValue("NONE")));
	}

	public StringValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		@SuppressWarnings("unchecked")
		List<StringValue> list = ((AnyValueListValue<StringValue>) inputs.get(0)).getValue(targetStock);
		int retrievedColumnIndex = ((NumberValue) inputs.get(1)).getNumberValue().intValue();
		String filterPatternString = ((StringValue) inputs.get(2)).getValue(targetStock);
		String defaultValue = ((StringValue) inputs.get(3)).getValue(targetStock);

		List<String> matchingLines = new ArrayList<>();

		for (StringValue lineValue : list) {
			String line = lineValue.getValue(targetStock);
			if (line.isEmpty() || line.startsWith("#")) continue;
			String[] rowSplit = line.split(",");

			try {
				boolean lineMatches = line.matches(filterPatternString) || line.contains(filterPatternString);
				if (lineMatches) {
					matchingLines.add(rowSplit[retrievedColumnIndex]);
				}
			} catch (Exception e) {
				LOGGER.warn("Unreadable line: " + line.substring(0, Math.min(line.length(), 200)) + "..");
			}
		}

		if (matchingLines.size() > 1) {
			LOGGER.warn("Dubious results retrieved (multiple matches): " + matchingLines + " for " + filterPatternString);
		}

		String result = defaultValue;
		if (!matchingLines.isEmpty()) {
			result = matchingLines.get(matchingLines.size()-1); // The last match prevails
			LOGGER.info("Results: " + result + " for " + filterPatternString);
		} else {
			LOGGER.info("No results (empty matches), using default: " + result + " for " + filterPatternString);
		}

		return new StringValue(result);
	}

	@Override
	public Boolean isIdemPotent(TargetStockInfo targetStock) {
		return true;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return 0;
	}

	@Override
	public boolean isForbidThisParameterValue() {
		return false;
	}
}
