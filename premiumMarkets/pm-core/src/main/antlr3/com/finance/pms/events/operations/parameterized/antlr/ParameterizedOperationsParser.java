// $ANTLR 3.5 /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g 2013-05-27 16:09:23
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
		"LINE_COMMENT", "Nativeop", "Number", "OperationOutput", "OutputSelector", 
		"StockOperation", "Userop", "WS", "'('", "')'", "','"
	};
	public static final int EOF=-1;
	public static final int T__15=15;
	public static final int T__16=16;
	public static final int T__17=17;
	public static final int COMMENT=4;
	public static final int Double=5;
	public static final int HistoricalData=6;
	public static final int LINE_COMMENT=7;
	public static final int Nativeop=8;
	public static final int Number=9;
	public static final int OperationOutput=10;
	public static final int OutputSelector=11;
	public static final int StockOperation=12;
	public static final int Userop=13;
	public static final int WS=14;

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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:112:1: indicatorexpr : expression -> expression ;
	public final ParameterizedOperationsParser.indicatorexpr_return indicatorexpr() throws RecognitionException {
		ParameterizedOperationsParser.indicatorexpr_return retval = new ParameterizedOperationsParser.indicatorexpr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope expression1 =null;

		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:112:15: ( expression -> expression )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:112:17: expression
			{
			pushFollow(FOLLOW_expression_in_indicatorexpr91);
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
			// 112:28: -> expression
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:113:1: expression : ( nativeop | userop );
	public final ParameterizedOperationsParser.expression_return expression() throws RecognitionException {
		ParameterizedOperationsParser.expression_return retval = new ParameterizedOperationsParser.expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope nativeop2 =null;
		ParserRuleReturnScope userop3 =null;


		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:113:12: ( nativeop | userop )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:113:14: nativeop
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_nativeop_in_expression103);
					nativeop2=nativeop();
					state._fsp--;

					adaptor.addChild(root_0, nativeop2.getTree());

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:113:25: userop
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_userop_in_expression107);
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:115:1: nativeop : opName= Nativeop (outSelect= OutputSelector )? '(' (pars+= params )? ')' -> ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? ) ;
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
		RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
		RewriteRuleTokenStream stream_16=new RewriteRuleTokenStream(adaptor,"token 16");
		RewriteRuleSubtreeStream stream_params=new RewriteRuleSubtreeStream(adaptor,"rule params");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:115:10: (opName= Nativeop (outSelect= OutputSelector )? '(' (pars+= params )? ')' -> ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:116:2: opName= Nativeop (outSelect= OutputSelector )? '(' (pars+= params )? ')'
			{
			opName=(Token)match(input,Nativeop,FOLLOW_Nativeop_in_nativeop119);  
			stream_Nativeop.add(opName);

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:116:18: (outSelect= OutputSelector )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==OutputSelector) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:116:20: outSelect= OutputSelector
					{
					outSelect=(Token)match(input,OutputSelector,FOLLOW_OutputSelector_in_nativeop125);  
					stream_OutputSelector.add(outSelect);

					}
					break;

			}

			outputSelectorHint((opName!=null?opName.getText():null), outSelect);
			char_literal4=(Token)match(input,15,FOLLOW_15_in_nativeop132);  
			stream_15.add(char_literal4);

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:116:100: (pars+= params )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==HistoricalData||(LA3_0 >= Nativeop && LA3_0 <= Number)||LA3_0==Userop) ) {
				alt3=1;
			}
			switch (alt3) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:116:101: pars+= params
					{
					pushFollow(FOLLOW_params_in_nativeop137);
					pars=params();
					state._fsp--;

					stream_params.add(pars.getTree());
					if (list_pars==null) list_pars=new ArrayList<Object>();
					list_pars.add(pars.getTree());
					}
					break;

			}

			checkParamExhaust(opName, list_pars);
			char_literal5=(Token)match(input,16,FOLLOW_16_in_nativeop143);  
			stream_16.add(char_literal5);

			// AST REWRITE
			// elements: Nativeop, OutputSelector, params
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 116:157: -> ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? )
			{
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:116:160: ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(stream_Nativeop.nextNode(), root_1);
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:116:171: ( ^( OperationOutput OutputSelector ) )?
				if ( stream_OutputSelector.hasNext() ) {
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:116:171: ^( OperationOutput OutputSelector )
					{
					CommonTree root_2 = (CommonTree)adaptor.nil();
					root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
					adaptor.addChild(root_2, stream_OutputSelector.nextNode());
					adaptor.addChild(root_1, root_2);
					}

				}
				stream_OutputSelector.reset();

				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:116:206: ( params )?
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:117:1: userop : opName= Userop '(' (pars+= params )? ')' -> ^( Userop ( params )? ) ;
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
		RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
		RewriteRuleTokenStream stream_16=new RewriteRuleTokenStream(adaptor,"token 16");
		RewriteRuleTokenStream stream_Userop=new RewriteRuleTokenStream(adaptor,"token Userop");
		RewriteRuleSubtreeStream stream_params=new RewriteRuleSubtreeStream(adaptor,"rule params");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:117:8: (opName= Userop '(' (pars+= params )? ')' -> ^( Userop ( params )? ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:118:2: opName= Userop '(' (pars+= params )? ')'
			{
			opName=(Token)match(input,Userop,FOLLOW_Userop_in_userop170);  
			stream_Userop.add(opName);

			char_literal6=(Token)match(input,15,FOLLOW_15_in_userop172);  
			stream_15.add(char_literal6);

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:118:20: (pars+= params )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==HistoricalData||(LA4_0 >= Nativeop && LA4_0 <= Number)||LA4_0==Userop) ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:118:21: pars+= params
					{
					pushFollow(FOLLOW_params_in_userop177);
					pars=params();
					state._fsp--;

					stream_params.add(pars.getTree());
					if (list_pars==null) list_pars=new ArrayList<Object>();
					list_pars.add(pars.getTree());
					}
					break;

			}

			checkParamExhaust(opName, list_pars);
			char_literal7=(Token)match(input,16,FOLLOW_16_in_userop183);  
			stream_16.add(char_literal7);

			// AST REWRITE
			// elements: Userop, params
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 118:78: -> ^( Userop ( params )? )
			{
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:118:81: ^( Userop ( params )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(stream_Userop.nextNode(), root_1);
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:118:90: ( params )?
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:120:1: params : param ( ',' param )* -> ( param )+ ;
	public final ParameterizedOperationsParser.params_return params() throws RecognitionException {
		ParameterizedOperationsParser.params_return retval = new ParameterizedOperationsParser.params_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal9=null;
		ParserRuleReturnScope param8 =null;
		ParserRuleReturnScope param10 =null;

		CommonTree char_literal9_tree=null;
		RewriteRuleTokenStream stream_17=new RewriteRuleTokenStream(adaptor,"token 17");
		RewriteRuleSubtreeStream stream_param=new RewriteRuleSubtreeStream(adaptor,"rule param");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:120:8: ( param ( ',' param )* -> ( param )+ )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:120:10: param ( ',' param )*
			{
			pushFollow(FOLLOW_param_in_params204);
			param8=param();
			state._fsp--;

			stream_param.add(param8.getTree());
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:120:16: ( ',' param )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==17) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:120:17: ',' param
					{
					char_literal9=(Token)match(input,17,FOLLOW_17_in_params207);  
					stream_17.add(char_literal9);

					pushFollow(FOLLOW_param_in_params209);
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
			// 120:29: -> ( param )+
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:121:1: param : ( Number -> ^( Double Number ) | operand );
	public final ParameterizedOperationsParser.param_return param() throws RecognitionException {
		ParameterizedOperationsParser.param_return retval = new ParameterizedOperationsParser.param_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token Number11=null;
		ParserRuleReturnScope operand12 =null;

		CommonTree Number11_tree=null;
		RewriteRuleTokenStream stream_Number=new RewriteRuleTokenStream(adaptor,"token Number");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:121:7: ( Number -> ^( Double Number ) | operand )
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==Number) ) {
				alt6=1;
			}
			else if ( (LA6_0==HistoricalData||LA6_0==Nativeop||LA6_0==Userop) ) {
				alt6=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}

			switch (alt6) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:121:9: Number
					{
					Number11=(Token)match(input,Number,FOLLOW_Number_in_param224);  
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
					// 121:16: -> ^( Double Number )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:121:20: ^( Double Number )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:121:39: operand
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_operand_in_param237);
					operand12=operand();
					state._fsp--;

					adaptor.addChild(root_0, operand12.getTree());

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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:122:1: operand : ( stockhistory -> stockhistory | expression );
	public final ParameterizedOperationsParser.operand_return operand() throws RecognitionException {
		ParameterizedOperationsParser.operand_return retval = new ParameterizedOperationsParser.operand_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope stockhistory13 =null;
		ParserRuleReturnScope expression14 =null;

		RewriteRuleSubtreeStream stream_stockhistory=new RewriteRuleSubtreeStream(adaptor,"rule stockhistory");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:122:9: ( stockhistory -> stockhistory | expression )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:122:11: stockhistory
					{
					pushFollow(FOLLOW_stockhistory_in_operand245);
					stockhistory13=stockhistory();
					state._fsp--;

					stream_stockhistory.add(stockhistory13.getTree());
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
					// 122:24: -> stockhistory
					{
						adaptor.addChild(root_0, stream_stockhistory.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:122:42: expression
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_expression_in_operand253);
					expression14=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression14.getTree());

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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:123:1: stockhistory : HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ) ;
	public final ParameterizedOperationsParser.stockhistory_return stockhistory() throws RecognitionException {
		ParameterizedOperationsParser.stockhistory_return retval = new ParameterizedOperationsParser.stockhistory_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token HistoricalData15=null;

		CommonTree HistoricalData15_tree=null;
		RewriteRuleTokenStream stream_HistoricalData=new RewriteRuleTokenStream(adaptor,"token HistoricalData");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:123:14: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:123:16: HistoricalData
			{
			HistoricalData15=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_stockhistory261);  
			stream_HistoricalData.add(HistoricalData15);

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
			// 123:31: -> ^( StockOperation ^( OperationOutput HistoricalData ) )
			{
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:123:34: ^( StockOperation ^( OperationOutput HistoricalData ) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:123:51: ^( OperationOutput HistoricalData )
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



	public static final BitSet FOLLOW_expression_in_indicatorexpr91 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nativeop_in_expression103 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_userop_in_expression107 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Nativeop_in_nativeop119 = new BitSet(new long[]{0x0000000000008800L});
	public static final BitSet FOLLOW_OutputSelector_in_nativeop125 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_nativeop132 = new BitSet(new long[]{0x0000000000012340L});
	public static final BitSet FOLLOW_params_in_nativeop137 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_nativeop143 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Userop_in_userop170 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_userop172 = new BitSet(new long[]{0x0000000000012340L});
	public static final BitSet FOLLOW_params_in_userop177 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_userop183 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_param_in_params204 = new BitSet(new long[]{0x0000000000020002L});
	public static final BitSet FOLLOW_17_in_params207 = new BitSet(new long[]{0x0000000000002340L});
	public static final BitSet FOLLOW_param_in_params209 = new BitSet(new long[]{0x0000000000020002L});
	public static final BitSet FOLLOW_Number_in_param224 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_param237 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_stockhistory_in_operand245 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_operand253 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_stockhistory261 = new BitSet(new long[]{0x0000000000000002L});
}
