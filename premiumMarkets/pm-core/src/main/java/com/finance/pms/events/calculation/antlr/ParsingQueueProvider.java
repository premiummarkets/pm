package com.finance.pms.events.calculation.antlr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.parametrizedindicators.ParameterizedIndicatorsBuilder;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.parameterized.ParameterizedOperationBuilder;

public class ParsingQueueProvider {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ParsingQueueProvider.class);
	
	private Queue<FormulaParser> parsingQueue;
	
	//User defined formula.txt ops 
	private ConcurrentHashMap<String, Operation> currentOperations;
	
	public ParsingQueueProvider() {
		parsingQueue = new ConcurrentLinkedQueue<FormulaParser>();
		currentOperations = new ConcurrentHashMap<>();
	}


	private void fillParsingQueue(ParameterizedBuilder parameterizedBuilder, File operationsDir, Boolean isDisabled) {
		File[] list = Arrays.stream(operationsDir.listFiles()).filter(f -> !f.getName().equals("empty.txt")).collect(Collectors.toList()).toArray(new File[0]);
		for (File formulaFile : list) {
			String opName = formulaFile.getName().replace(".txt","");

			try {

				BufferedReader bufferedReader = new BufferedReader(new FileReader(formulaFile));
				String formula = bufferedReader.readLine();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					formula = formula + "\n"+ line;
				}
				bufferedReader.close();

				FormulaParser formulaParser = new FormulaParser(parameterizedBuilder, opName, formula, isDisabled);
				parsingQueue.offer(formulaParser);

			} catch (Exception e) {
				LOGGER.warn(e);
				parameterizedBuilder.moveToTrash(opName);
			}
		}
	}

	public Queue<FormulaParser> filledParsingQueue() {
		if (parsingQueue.isEmpty()) {
			final ParameterizedOperationBuilder parameterizedOperationBuilder = SpringContext.getSingleton().getBean(ParameterizedOperationBuilder.class);
			final ParameterizedIndicatorsBuilder parameterizedIndicatorsBuilder = SpringContext.getSingleton().getBean(ParameterizedIndicatorsBuilder.class);
			
			fillParsingQueue(parameterizedOperationBuilder, parameterizedOperationBuilder.userOperationsDir, false);
			fillParsingQueue(parameterizedIndicatorsBuilder, parameterizedIndicatorsBuilder.userOperationsDir, false);
			fillParsingQueue(parameterizedIndicatorsBuilder, parameterizedIndicatorsBuilder.disabledUserOperationsDir, true);
		}
		return parsingQueue;
	}
	
	
	public ConcurrentHashMap<String, Operation> getCurrentOperations() {
		return currentOperations;
	}

}
