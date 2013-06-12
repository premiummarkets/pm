// $ANTLR 3.5 /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g 2013-06-09 22:47:57
 //parser
    package com.finance.pms.events.operations.parameterized.antlr;
    import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.RuleReturnScope;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.RewriteEarlyExitException;
import org.antlr.runtime.tree.RewriteRuleSubtreeStream;
import org.antlr.runtime.tree.RewriteRuleTokenStream;
import org.antlr.runtime.tree.TreeAdaptor;

import com.finance.pms.events.calculation.antlr.InvalidOperationException;
import com.finance.pms.events.calculation.antlr.MissingOutputSelectorException;
import com.finance.pms.events.calculation.antlr.MyErrorReporter;
import com.finance.pms.events.calculation.antlr.OpsParserDelegate;
import com.finance.pms.events.calculation.antlr.ParamsCountException;
import com.finance.pms.events.calculation.antlr.UnfinishedParameterException;


@SuppressWarnings("all")
public class ParameterizedOperationsParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMMENT", "Double", "HistoricalData", 
		"LINE_COMMENT", "MAType", "MATypeToken", "Nativeop", "Number", "OperationOutput", 
		"OutputSelector", "StockOperation", "Userop", "WS", "'('", "')'", "','"
	};
	public static final int EOF=-1;
	public static final int T__17=17;
	public static final int T__18=18;
	public static final int T__19=19;
	public static final int COMMENT=4;
	public static final int Double=5;
	public static final int HistoricalData=6;
	public static final int LINE_COMMENT=7;
	public static final int MAType=8;
	public static final int MATypeToken=9;
	public static final int Nativeop=10;
	public static final int Number=11;
	public static final int OperationOutput=12;
	public static final int OutputSelector=13;
	public static final int StockOperation=14;
	public static final int Userop=15;
	public static final int WS=16;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public ParameterizedOperationsParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public ParameterizedOperationsParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return ParameterizedOperationsParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g"; }

	 //parser

	    private MyErrorReporter errorReporter;
	    private OpsParserDelegate parserDelegate;

		  public void setParserDelegate(OpsParserDelegate parserDelegate) {
		      this.parserDelegate = parserDelegate;
		  }
		  
		  public OpsParserDelegate getParserDelegate() {
		     return parserDelegate;
		  }

	    public void setMyErrorReporter(MyErrorReporter errorReporter) {
	        this.errorReporter = errorReporter;
	    }

			@Override
	    public void reportError(RecognitionException e) {
	        if (this.errorReporter !=null) {
	          this.errorReporter.report(e);
	        } else {
	          super.reportError(e);
	        }
	    }
	    
	    public Boolean checkParamExhaust(Token opName, List<Object> params) throws ParamsCountException, UnfinishedParameterException, InvalidOperationException {
	       return  parserDelegate.checkParamExhaust(opName, params);
	     }
	     
	    private void  outputSelectorHint(String opToken, Token outputSelector) throws MissingOutputSelectorException, InvalidOperationException {
	        parserDelegate.outputSelectorHint(opToken, outputSelector);
	    }



	public static class indicatorexpr_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "indicatorexpr"
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:116:1: indicatorexpr : expression -> expression ;
	public final ParameterizedOperationsParser.indicatorexpr_return indicatorexpr() throws RecognitionException {
		ParameterizedOperationsParser.indicatorexpr_return retval = new ParameterizedOperationsParser.indicatorexpr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope expression1 =null;

		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:116:15: ( expression -> expression )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:116:17: expression
			{
			pushFollow(FOLLOW_expression_in_indicatorexpr94);
			expression1=expression();
			state._fsp--;

			stream_expression.add(expression1.getTree());
			// AST REWRITE
			// elements: expression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 116:28: -> expression
			{
				adaptor.addChild(root_0, stream_expression.nextTree());
			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "indicatorexpr"


	public static class expression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expression"
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:117:1: expression : ( nativeop | userop );
	public final ParameterizedOperationsParser.expression_return expression() throws RecognitionException {
		ParameterizedOperationsParser.expression_return retval = new ParameterizedOperationsParser.expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope nativeop2 =null;
		ParserRuleReturnScope userop3 =null;


		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:117:12: ( nativeop | userop )
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0==Nativeop) ) {
				alt1=1;
			}
			else if ( (LA1_0==Userop) ) {
				alt1=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}

			switch (alt1) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:117:14: nativeop
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_nativeop_in_expression106);
					nativeop2=nativeop();
					state._fsp--;

					adaptor.addChild(root_0, nativeop2.getTree());

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:117:25: userop
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_userop_in_expression110);
					userop3=userop();
					state._fsp--;

					adaptor.addChild(root_0, userop3.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expression"


	public static class nativeop_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "nativeop"
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:119:1: nativeop : opName= Nativeop (outSelect= OutputSelector )? '(' (pars+= params )? ')' -> ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? ) ;
	public final ParameterizedOperationsParser.nativeop_return nativeop() throws RecognitionException {
		ParameterizedOperationsParser.nativeop_return retval = new ParameterizedOperationsParser.nativeop_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token opName=null;
		Token outSelect=null;
		Token char_literal4=null;
		Token char_literal5=null;
		List<Object> list_pars=null;
		RuleReturnScope pars = null;
		CommonTree opName_tree=null;
		CommonTree outSelect_tree=null;
		CommonTree char_literal4_tree=null;
		CommonTree char_literal5_tree=null;
		RewriteRuleTokenStream stream_Nativeop=new RewriteRuleTokenStream(adaptor,"token Nativeop");
		RewriteRuleTokenStream stream_OutputSelector=new RewriteRuleTokenStream(adaptor,"token OutputSelector");
		RewriteRuleTokenStream stream_17=new RewriteRuleTokenStream(adaptor,"token 17");
		RewriteRuleTokenStream stream_18=new RewriteRuleTokenStream(adaptor,"token 18");
		RewriteRuleSubtreeStream stream_params=new RewriteRuleSubtreeStream(adaptor,"rule params");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:119:10: (opName= Nativeop (outSelect= OutputSelector )? '(' (pars+= params )? ')' -> ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:120:2: opName= Nativeop (outSelect= OutputSelector )? '(' (pars+= params )? ')'
			{
			opName=(Token)match(input,Nativeop,FOLLOW_Nativeop_in_nativeop122);  
			stream_Nativeop.add(opName);

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:120:18: (outSelect= OutputSelector )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==OutputSelector) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:120:20: outSelect= OutputSelector
					{
					outSelect=(Token)match(input,OutputSelector,FOLLOW_OutputSelector_in_nativeop128);  
					stream_OutputSelector.add(outSelect);

					}
					break;

			}

			outputSelectorHint((opName!=null?opName.getText():null), outSelect);
			char_literal4=(Token)match(input,17,FOLLOW_17_in_nativeop135);  
			stream_17.add(char_literal4);

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:120:100: (pars+= params )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==HistoricalData||(LA3_0 >= MATypeToken && LA3_0 <= Number)||LA3_0==Userop) ) {
				alt3=1;
			}
			switch (alt3) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:120:101: pars+= params
					{
					pushFollow(FOLLOW_params_in_nativeop140);
					pars=params();
					state._fsp--;

					stream_params.add(pars.getTree());
					if (list_pars==null) list_pars=new ArrayList<Object>();
					list_pars.add(pars.getTree());
					}
					break;

			}

			checkParamExhaust(opName, list_pars);
			char_literal5=(Token)match(input,18,FOLLOW_18_in_nativeop146);  
			stream_18.add(char_literal5);

			// AST REWRITE
			// elements: Nativeop, params, OutputSelector
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 120:157: -> ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? )
			{
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:120:160: ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(stream_Nativeop.nextNode(), root_1);
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:120:171: ( ^( OperationOutput OutputSelector ) )?
				if ( stream_OutputSelector.hasNext() ) {
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:120:171: ^( OperationOutput OutputSelector )
					{
					CommonTree root_2 = (CommonTree)adaptor.nil();
					root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
					adaptor.addChild(root_2, stream_OutputSelector.nextNode());
					adaptor.addChild(root_1, root_2);
					}

				}
				stream_OutputSelector.reset();

				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:120:206: ( params )?
				if ( stream_params.hasNext() ) {
					adaptor.addChild(root_1, stream_params.nextTree());
				}
				stream_params.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "nativeop"


	public static class userop_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "userop"
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:121:1: userop : opName= Userop '(' (pars+= params )? ')' -> ^( Userop ( params )? ) ;
	public final ParameterizedOperationsParser.userop_return userop() throws RecognitionException {
		ParameterizedOperationsParser.userop_return retval = new ParameterizedOperationsParser.userop_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token opName=null;
		Token char_literal6=null;
		Token char_literal7=null;
		List<Object> list_pars=null;
		RuleReturnScope pars = null;
		CommonTree opName_tree=null;
		CommonTree char_literal6_tree=null;
		CommonTree char_literal7_tree=null;
		RewriteRuleTokenStream stream_17=new RewriteRuleTokenStream(adaptor,"token 17");
		RewriteRuleTokenStream stream_18=new RewriteRuleTokenStream(adaptor,"token 18");
		RewriteRuleTokenStream stream_Userop=new RewriteRuleTokenStream(adaptor,"token Userop");
		RewriteRuleSubtreeStream stream_params=new RewriteRuleSubtreeStream(adaptor,"rule params");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:121:8: (opName= Userop '(' (pars+= params )? ')' -> ^( Userop ( params )? ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:122:2: opName= Userop '(' (pars+= params )? ')'
			{
			opName=(Token)match(input,Userop,FOLLOW_Userop_in_userop173);  
			stream_Userop.add(opName);

			char_literal6=(Token)match(input,17,FOLLOW_17_in_userop175);  
			stream_17.add(char_literal6);

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:122:20: (pars+= params )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==HistoricalData||(LA4_0 >= MATypeToken && LA4_0 <= Number)||LA4_0==Userop) ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:122:21: pars+= params
					{
					pushFollow(FOLLOW_params_in_userop180);
					pars=params();
					state._fsp--;

					stream_params.add(pars.getTree());
					if (list_pars==null) list_pars=new ArrayList<Object>();
					list_pars.add(pars.getTree());
					}
					break;

			}

			checkParamExhaust(opName, list_pars);
			char_literal7=(Token)match(input,18,FOLLOW_18_in_userop186);  
			stream_18.add(char_literal7);

			// AST REWRITE
			// elements: params, Userop
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 122:78: -> ^( Userop ( params )? )
			{
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:122:81: ^( Userop ( params )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(stream_Userop.nextNode(), root_1);
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:122:90: ( params )?
				if ( stream_params.hasNext() ) {
					adaptor.addChild(root_1, stream_params.nextTree());
				}
				stream_params.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "userop"


	public static class params_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "params"
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:124:1: params : param ( ',' param )* -> ( param )+ ;
	public final ParameterizedOperationsParser.params_return params() throws RecognitionException {
		ParameterizedOperationsParser.params_return retval = new ParameterizedOperationsParser.params_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal9=null;
		ParserRuleReturnScope param8 =null;
		ParserRuleReturnScope param10 =null;

		CommonTree char_literal9_tree=null;
		RewriteRuleTokenStream stream_19=new RewriteRuleTokenStream(adaptor,"token 19");
		RewriteRuleSubtreeStream stream_param=new RewriteRuleSubtreeStream(adaptor,"rule param");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:124:8: ( param ( ',' param )* -> ( param )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:124:10: param ( ',' param )*
			{
			pushFollow(FOLLOW_param_in_params207);
			param8=param();
			state._fsp--;

			stream_param.add(param8.getTree());
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:124:16: ( ',' param )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==19) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:124:17: ',' param
					{
					char_literal9=(Token)match(input,19,FOLLOW_19_in_params210);  
					stream_19.add(char_literal9);

					pushFollow(FOLLOW_param_in_params212);
					param10=param();
					state._fsp--;

					stream_param.add(param10.getTree());
					}
					break;

				default :
					break loop5;
				}
			}

			// AST REWRITE
			// elements: param
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 124:29: -> ( param )+
			{
				if ( !(stream_param.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_param.hasNext() ) {
					adaptor.addChild(root_0, stream_param.nextTree());
				}
				stream_param.reset();

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "params"


	public static class param_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "param"
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:125:1: param : ( Number -> ^( Double Number ) | MATypeToken -> ^( MAType MATypeToken ) | operand );
	public final ParameterizedOperationsParser.param_return param() throws RecognitionException {
		ParameterizedOperationsParser.param_return retval = new ParameterizedOperationsParser.param_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token Number11=null;
		Token MATypeToken12=null;
		ParserRuleReturnScope operand13 =null;

		CommonTree Number11_tree=null;
		CommonTree MATypeToken12_tree=null;
		RewriteRuleTokenStream stream_MATypeToken=new RewriteRuleTokenStream(adaptor,"token MATypeToken");
		RewriteRuleTokenStream stream_Number=new RewriteRuleTokenStream(adaptor,"token Number");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:125:7: ( Number -> ^( Double Number ) | MATypeToken -> ^( MAType MATypeToken ) | operand )
			int alt6=3;
			switch ( input.LA(1) ) {
			case Number:
				{
				alt6=1;
				}
				break;
			case MATypeToken:
				{
				alt6=2;
				}
				break;
			case HistoricalData:
			case Nativeop:
			case Userop:
				{
				alt6=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}
			switch (alt6) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:125:9: Number
					{
					Number11=(Token)match(input,Number,FOLLOW_Number_in_param227);  
					stream_Number.add(Number11);

					// AST REWRITE
					// elements: Number
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 125:16: -> ^( Double Number )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:125:20: ^( Double Number )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_1);
						adaptor.addChild(root_1, stream_Number.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:125:39: MATypeToken
					{
					MATypeToken12=(Token)match(input,MATypeToken,FOLLOW_MATypeToken_in_param240);  
					stream_MATypeToken.add(MATypeToken12);

					// AST REWRITE
					// elements: MATypeToken
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 125:51: -> ^( MAType MATypeToken )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:125:54: ^( MAType MATypeToken )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(MAType, "MAType"), root_1);
						adaptor.addChild(root_1, stream_MATypeToken.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:125:78: operand
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_operand_in_param252);
					operand13=operand();
					state._fsp--;

					adaptor.addChild(root_0, operand13.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "param"


	public static class operand_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "operand"
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:1: operand : ( stockhistory -> stockhistory | expression );
	public final ParameterizedOperationsParser.operand_return operand() throws RecognitionException {
		ParameterizedOperationsParser.operand_return retval = new ParameterizedOperationsParser.operand_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope stockhistory14 =null;
		ParserRuleReturnScope expression15 =null;

		RewriteRuleSubtreeStream stream_stockhistory=new RewriteRuleSubtreeStream(adaptor,"rule stockhistory");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:9: ( stockhistory -> stockhistory | expression )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==HistoricalData) ) {
				alt7=1;
			}
			else if ( (LA7_0==Nativeop||LA7_0==Userop) ) {
				alt7=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:11: stockhistory
					{
					pushFollow(FOLLOW_stockhistory_in_operand260);
					stockhistory14=stockhistory();
					state._fsp--;

					stream_stockhistory.add(stockhistory14.getTree());
					// AST REWRITE
					// elements: stockhistory
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 126:24: -> stockhistory
					{
						adaptor.addChild(root_0, stream_stockhistory.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:42: expression
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_expression_in_operand268);
					expression15=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression15.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "operand"


	public static class stockhistory_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "stockhistory"
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:1: stockhistory : HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ) ;
	public final ParameterizedOperationsParser.stockhistory_return stockhistory() throws RecognitionException {
		ParameterizedOperationsParser.stockhistory_return retval = new ParameterizedOperationsParser.stockhistory_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token HistoricalData16=null;

		CommonTree HistoricalData16_tree=null;
		RewriteRuleTokenStream stream_HistoricalData=new RewriteRuleTokenStream(adaptor,"token HistoricalData");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:14: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:16: HistoricalData
			{
			HistoricalData16=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_stockhistory276);  
			stream_HistoricalData.add(HistoricalData16);

			// AST REWRITE
			// elements: HistoricalData
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 127:31: -> ^( StockOperation ^( OperationOutput HistoricalData ) )
			{
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:34: ^( StockOperation ^( OperationOutput HistoricalData ) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:51: ^( OperationOutput HistoricalData )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
				adaptor.addChild(root_2, stream_HistoricalData.nextNode());
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "stockhistory"

	// Delegated rules



	public static final BitSet FOLLOW_expression_in_indicatorexpr94 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nativeop_in_expression106 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_userop_in_expression110 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Nativeop_in_nativeop122 = new BitSet(new long[]{0x0000000000022000L});
	public static final BitSet FOLLOW_OutputSelector_in_nativeop128 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_nativeop135 = new BitSet(new long[]{0x0000000000048E40L});
	public static final BitSet FOLLOW_params_in_nativeop140 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_18_in_nativeop146 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Userop_in_userop173 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_userop175 = new BitSet(new long[]{0x0000000000048E40L});
	public static final BitSet FOLLOW_params_in_userop180 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_18_in_userop186 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_param_in_params207 = new BitSet(new long[]{0x0000000000080002L});
	public static final BitSet FOLLOW_19_in_params210 = new BitSet(new long[]{0x0000000000008E40L});
	public static final BitSet FOLLOW_param_in_params212 = new BitSet(new long[]{0x0000000000080002L});
	public static final BitSet FOLLOW_Number_in_param227 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MATypeToken_in_param240 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_param252 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_stockhistory_in_operand260 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_operand268 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_stockhistory276 = new BitSet(new long[]{0x0000000000000002L});
}
