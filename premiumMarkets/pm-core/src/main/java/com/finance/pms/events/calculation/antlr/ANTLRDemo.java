package com.finance.pms.events.calculation.antlr;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.DOTTreeGenerator;
import org.antlr.stringtemplate.StringTemplate;
import org.apache.tools.ant.filters.StringInputStream;

import com.finance.pms.events.operations.parameterized.antlr.ParameterizedOperationsLexer;
import com.finance.pms.events.operations.parameterized.antlr.ParameterizedOperationsParser;


public class ANTLRDemo {

	public static void main(String[] args) throws Exception {

		StringInputStream inputStream = new StringInputStream("sum (sma(9, macd (1,2,3, close)) , close)");
		ANTLRInputStream in = new ANTLRInputStream(inputStream);
		ParameterizedOperationsLexer lexer = new ParameterizedOperationsLexer(in);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ParameterizedOperationsParser parser = new ParameterizedOperationsParser(tokens);
		CommonTree tree  = (CommonTree) parser.indicatorexpr().getTree();
		DOTTreeGenerator gen = new DOTTreeGenerator();
	    StringTemplate st = gen.toDOT(tree);
	    System.out.println(st);
	}

}
