// $ANTLR 3.5.2 com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g 2023-10-19 18:41:29
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
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMMENT", "HistoricalData", "LINE_COMMENT", 
		"ListOperation", "MAType", "MATypeToken", "NaNNumber", "Nativeop", "Number", 
		"NumberToken", "OperationOutput", "OperationReference", "OperationReferenceToken", 
		"OutputSelector", "StockOperation", "String", "StringToken", "Userop", 
		"WS", "'('", "')'", "','", "'['", "'[]'", "']'"
	};
	public static final int EOF=-1;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int COMMENT=4;
	public static final int HistoricalData=5;
	public static final int LINE_COMMENT=6;
	public static final int ListOperation=7;
	public static final int MAType=8;
	public static final int MATypeToken=9;
	public static final int NaNNumber=10;
	public static final int Nativeop=11;
	public static final int Number=12;
	public static final int NumberToken=13;
	public static final int OperationOutput=14;
	public static final int OperationReference=15;
	public static final int OperationReferenceToken=16;
	public static final int OutputSelector=17;
	public static final int StockOperation=18;
	public static final int String=19;
	public static final int StringToken=20;
	public static final int Userop=21;
	public static final int WS=22;

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
	@Override public String getGrammarFileName() { return "com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g"; }

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
	     
	    private void  outputSelectorHint(Token opToken, Token outputSelector) throws MissingOutputSelectorException, InvalidOperationException, NoViableAltException {
	        parserDelegate.outputSelectorHint(opToken, outputSelector);
	    }



	public static class indicatorexpr_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "indicatorexpr"
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:122:1: indicatorexpr : expression -> expression ;
	public final ParameterizedOperationsParser.indicatorexpr_return indicatorexpr() throws RecognitionException {
		ParameterizedOperationsParser.indicatorexpr_return retval = new ParameterizedOperationsParser.indicatorexpr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope expression1 =null;

		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:122:15: ( expression -> expression )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:122:17: expression
			{
			pushFollow(FOLLOW_expression_in_indicatorexpr109);
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
			// 122:28: -> expression
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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:123:1: expression : ( nativeop | userop );
	public final ParameterizedOperationsParser.expression_return expression() throws RecognitionException {
		ParameterizedOperationsParser.expression_return retval = new ParameterizedOperationsParser.expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope nativeop2 =null;
		ParserRuleReturnScope userop3 =null;


		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:123:12: ( nativeop | userop )
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:123:14: nativeop
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_nativeop_in_expression121);
					nativeop2=nativeop();
					state._fsp--;

					adaptor.addChild(root_0, nativeop2.getTree());

					}
					break;
				case 2 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:123:25: userop
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_userop_in_expression125);
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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:125:1: nativeop : opName= Nativeop (outSelect= OutputSelector )? '(' (pars+= params )? ')' -> ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? ) ;
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
		RewriteRuleTokenStream stream_23=new RewriteRuleTokenStream(adaptor,"token 23");
		RewriteRuleTokenStream stream_24=new RewriteRuleTokenStream(adaptor,"token 24");
		RewriteRuleTokenStream stream_OutputSelector=new RewriteRuleTokenStream(adaptor,"token OutputSelector");
		RewriteRuleSubtreeStream stream_params=new RewriteRuleSubtreeStream(adaptor,"rule params");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:125:10: (opName= Nativeop (outSelect= OutputSelector )? '(' (pars+= params )? ')' -> ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:2: opName= Nativeop (outSelect= OutputSelector )? '(' (pars+= params )? ')'
			{
			opName=(Token)match(input,Nativeop,FOLLOW_Nativeop_in_nativeop136);  
			stream_Nativeop.add(opName);

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:18: (outSelect= OutputSelector )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==OutputSelector) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:20: outSelect= OutputSelector
					{
					outSelect=(Token)match(input,OutputSelector,FOLLOW_OutputSelector_in_nativeop142);  
					stream_OutputSelector.add(outSelect);

					}
					break;

			}

			outputSelectorHint(opName, outSelect);
			char_literal4=(Token)match(input,23,FOLLOW_23_in_nativeop149);  
			stream_23.add(char_literal4);

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:95: (pars+= params )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==HistoricalData||(LA3_0 >= MATypeToken && LA3_0 <= Nativeop)||LA3_0==NumberToken||LA3_0==OperationReferenceToken||(LA3_0 >= StringToken && LA3_0 <= Userop)||(LA3_0 >= 26 && LA3_0 <= 27)) ) {
				alt3=1;
			}
			switch (alt3) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:96: pars+= params
					{
					pushFollow(FOLLOW_params_in_nativeop154);
					pars=params();
					state._fsp--;

					stream_params.add(pars.getTree());
					if (list_pars==null) list_pars=new ArrayList<Object>();
					list_pars.add(pars.getTree());
					}
					break;

			}

			checkParamExhaust(opName, list_pars);
			char_literal5=(Token)match(input,24,FOLLOW_24_in_nativeop160);  
			stream_24.add(char_literal5);

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
			// 126:152: -> ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? )
			{
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:155: ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(stream_Nativeop.nextNode(), root_1);
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:166: ( ^( OperationOutput OutputSelector ) )?
				if ( stream_OutputSelector.hasNext() ) {
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:166: ^( OperationOutput OutputSelector )
					{
					CommonTree root_2 = (CommonTree)adaptor.nil();
					root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
					adaptor.addChild(root_2, stream_OutputSelector.nextNode());
					adaptor.addChild(root_1, root_2);
					}

				}
				stream_OutputSelector.reset();

				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:201: ( params )?
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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:1: userop : opName= Userop '(' (pars+= params )? ')' -> ^( Userop ( params )? ) ;
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
		RewriteRuleTokenStream stream_23=new RewriteRuleTokenStream(adaptor,"token 23");
		RewriteRuleTokenStream stream_24=new RewriteRuleTokenStream(adaptor,"token 24");
		RewriteRuleTokenStream stream_Userop=new RewriteRuleTokenStream(adaptor,"token Userop");
		RewriteRuleSubtreeStream stream_params=new RewriteRuleSubtreeStream(adaptor,"rule params");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:8: (opName= Userop '(' (pars+= params )? ')' -> ^( Userop ( params )? ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:128:2: opName= Userop '(' (pars+= params )? ')'
			{
			opName=(Token)match(input,Userop,FOLLOW_Userop_in_userop186);  
			stream_Userop.add(opName);

			char_literal6=(Token)match(input,23,FOLLOW_23_in_userop188);  
			stream_23.add(char_literal6);

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:128:20: (pars+= params )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==HistoricalData||(LA4_0 >= MATypeToken && LA4_0 <= Nativeop)||LA4_0==NumberToken||LA4_0==OperationReferenceToken||(LA4_0 >= StringToken && LA4_0 <= Userop)||(LA4_0 >= 26 && LA4_0 <= 27)) ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:128:21: pars+= params
					{
					pushFollow(FOLLOW_params_in_userop193);
					pars=params();
					state._fsp--;

					stream_params.add(pars.getTree());
					if (list_pars==null) list_pars=new ArrayList<Object>();
					list_pars.add(pars.getTree());
					}
					break;

			}

			checkParamExhaust(opName, list_pars);
			char_literal7=(Token)match(input,24,FOLLOW_24_in_userop199);  
			stream_24.add(char_literal7);

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
			// 128:77: -> ^( Userop ( params )? )
			{
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:128:80: ^( Userop ( params )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(stream_Userop.nextNode(), root_1);
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:128:89: ( params )?
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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:130:1: params : param ( ',' param )* -> ( param )+ ;
	public final ParameterizedOperationsParser.params_return params() throws RecognitionException {
		ParameterizedOperationsParser.params_return retval = new ParameterizedOperationsParser.params_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal9=null;
		ParserRuleReturnScope param8 =null;
		ParserRuleReturnScope param10 =null;

		CommonTree char_literal9_tree=null;
		RewriteRuleTokenStream stream_25=new RewriteRuleTokenStream(adaptor,"token 25");
		RewriteRuleSubtreeStream stream_param=new RewriteRuleSubtreeStream(adaptor,"rule param");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:130:8: ( param ( ',' param )* -> ( param )+ )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:130:10: param ( ',' param )*
			{
			pushFollow(FOLLOW_param_in_params217);
			param8=param();
			state._fsp--;

			stream_param.add(param8.getTree());
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:130:16: ( ',' param )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==25) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:130:17: ',' param
					{
					char_literal9=(Token)match(input,25,FOLLOW_25_in_params220);  
					stream_25.add(char_literal9);

					pushFollow(FOLLOW_param_in_params222);
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
			// 130:29: -> ( param )+
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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:1: param : ( NumberToken -> ^( Number NumberToken ) | NaNNumber -> ^( Number NumberToken[\"NaN\"] ) | MATypeToken -> ^( MAType MATypeToken ) | StringToken -> ^( String StringToken ) | operand );
	public final ParameterizedOperationsParser.param_return param() throws RecognitionException {
		ParameterizedOperationsParser.param_return retval = new ParameterizedOperationsParser.param_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NumberToken11=null;
		Token NaNNumber12=null;
		Token MATypeToken13=null;
		Token StringToken14=null;
		ParserRuleReturnScope operand15 =null;

		CommonTree NumberToken11_tree=null;
		CommonTree NaNNumber12_tree=null;
		CommonTree MATypeToken13_tree=null;
		CommonTree StringToken14_tree=null;
		RewriteRuleTokenStream stream_MATypeToken=new RewriteRuleTokenStream(adaptor,"token MATypeToken");
		RewriteRuleTokenStream stream_NumberToken=new RewriteRuleTokenStream(adaptor,"token NumberToken");
		RewriteRuleTokenStream stream_NaNNumber=new RewriteRuleTokenStream(adaptor,"token NaNNumber");
		RewriteRuleTokenStream stream_StringToken=new RewriteRuleTokenStream(adaptor,"token StringToken");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:7: ( NumberToken -> ^( Number NumberToken ) | NaNNumber -> ^( Number NumberToken[\"NaN\"] ) | MATypeToken -> ^( MAType MATypeToken ) | StringToken -> ^( String StringToken ) | operand )
			int alt6=5;
			switch ( input.LA(1) ) {
			case NumberToken:
				{
				alt6=1;
				}
				break;
			case NaNNumber:
				{
				alt6=2;
				}
				break;
			case MATypeToken:
				{
				alt6=3;
				}
				break;
			case StringToken:
				{
				alt6=4;
				}
				break;
			case HistoricalData:
			case Nativeop:
			case OperationReferenceToken:
			case Userop:
			case 26:
			case 27:
				{
				alt6=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}
			switch (alt6) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:9: NumberToken
					{
					NumberToken11=(Token)match(input,NumberToken,FOLLOW_NumberToken_in_param237);  
					stream_NumberToken.add(NumberToken11);

					// AST REWRITE
					// elements: NumberToken
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 131:21: -> ^( Number NumberToken )
					{
						// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:25: ^( Number NumberToken )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_1);
						adaptor.addChild(root_1, stream_NumberToken.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:49: NaNNumber
					{
					NaNNumber12=(Token)match(input,NaNNumber,FOLLOW_NaNNumber_in_param250);  
					stream_NaNNumber.add(NaNNumber12);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 131:59: -> ^( Number NumberToken[\"NaN\"] )
					{
						// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:62: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_1);
						adaptor.addChild(root_1, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:93: MATypeToken
					{
					MATypeToken13=(Token)match(input,MATypeToken,FOLLOW_MATypeToken_in_param263);  
					stream_MATypeToken.add(MATypeToken13);

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
					// 131:105: -> ^( MAType MATypeToken )
					{
						// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:108: ^( MAType MATypeToken )
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
				case 4 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:132: StringToken
					{
					StringToken14=(Token)match(input,StringToken,FOLLOW_StringToken_in_param275);  
					stream_StringToken.add(StringToken14);

					// AST REWRITE
					// elements: StringToken
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 131:144: -> ^( String StringToken )
					{
						// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:147: ^( String StringToken )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_1);
						adaptor.addChild(root_1, stream_StringToken.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 5 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:171: operand
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_operand_in_param287);
					operand15=operand();
					state._fsp--;

					adaptor.addChild(root_0, operand15.getTree());

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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:132:1: operand : ( stockhistory -> stockhistory | listOParams | operationReference | expression );
	public final ParameterizedOperationsParser.operand_return operand() throws RecognitionException {
		ParameterizedOperationsParser.operand_return retval = new ParameterizedOperationsParser.operand_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope stockhistory16 =null;
		ParserRuleReturnScope listOParams17 =null;
		ParserRuleReturnScope operationReference18 =null;
		ParserRuleReturnScope expression19 =null;

		RewriteRuleSubtreeStream stream_stockhistory=new RewriteRuleSubtreeStream(adaptor,"rule stockhistory");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:132:9: ( stockhistory -> stockhistory | listOParams | operationReference | expression )
			int alt7=4;
			switch ( input.LA(1) ) {
			case HistoricalData:
				{
				alt7=1;
				}
				break;
			case 26:
			case 27:
				{
				alt7=2;
				}
				break;
			case OperationReferenceToken:
				{
				alt7=3;
				}
				break;
			case Nativeop:
			case Userop:
				{
				alt7=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}
			switch (alt7) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:132:11: stockhistory
					{
					pushFollow(FOLLOW_stockhistory_in_operand294);
					stockhistory16=stockhistory();
					state._fsp--;

					stream_stockhistory.add(stockhistory16.getTree());
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
					// 132:24: -> stockhistory
					{
						adaptor.addChild(root_0, stream_stockhistory.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:132:42: listOParams
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_listOParams_in_operand302);
					listOParams17=listOParams();
					state._fsp--;

					adaptor.addChild(root_0, listOParams17.getTree());

					}
					break;
				case 3 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:132:56: operationReference
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_operationReference_in_operand306);
					operationReference18=operationReference();
					state._fsp--;

					adaptor.addChild(root_0, operationReference18.getTree());

					}
					break;
				case 4 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:132:77: expression
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_expression_in_operand310);
					expression19=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression19.getTree());

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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:1: stockhistory : HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) ;
	public final ParameterizedOperationsParser.stockhistory_return stockhistory() throws RecognitionException {
		ParameterizedOperationsParser.stockhistory_return retval = new ParameterizedOperationsParser.stockhistory_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token HistoricalData20=null;

		CommonTree HistoricalData20_tree=null;
		RewriteRuleTokenStream stream_HistoricalData=new RewriteRuleTokenStream(adaptor,"token HistoricalData");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:14: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:16: HistoricalData
			{
			HistoricalData20=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_stockhistory318);  
			stream_HistoricalData.add(HistoricalData20);

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
			// 133:31: -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
			{
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:34: ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:51: ^( OperationOutput HistoricalData )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
				adaptor.addChild(root_2, stream_HistoricalData.nextNode());
				adaptor.addChild(root_1, root_2);
				}

				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:85: ^( String StringToken[\"\\\"THIS\\\"\"] )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
				adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"THIS\""));
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


	public static class listOParams_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "listOParams"
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:1: listOParams : ( '[]' -> ^( ListOperation ) | '[' param ( ',' param )* ']' -> ^( ListOperation ( param )+ ) );
	public final ParameterizedOperationsParser.listOParams_return listOParams() throws RecognitionException {
		ParameterizedOperationsParser.listOParams_return retval = new ParameterizedOperationsParser.listOParams_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal21=null;
		Token char_literal22=null;
		Token char_literal24=null;
		Token char_literal26=null;
		ParserRuleReturnScope param23 =null;
		ParserRuleReturnScope param25 =null;

		CommonTree string_literal21_tree=null;
		CommonTree char_literal22_tree=null;
		CommonTree char_literal24_tree=null;
		CommonTree char_literal26_tree=null;
		RewriteRuleTokenStream stream_25=new RewriteRuleTokenStream(adaptor,"token 25");
		RewriteRuleTokenStream stream_26=new RewriteRuleTokenStream(adaptor,"token 26");
		RewriteRuleTokenStream stream_27=new RewriteRuleTokenStream(adaptor,"token 27");
		RewriteRuleTokenStream stream_28=new RewriteRuleTokenStream(adaptor,"token 28");
		RewriteRuleSubtreeStream stream_param=new RewriteRuleSubtreeStream(adaptor,"rule param");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:13: ( '[]' -> ^( ListOperation ) | '[' param ( ',' param )* ']' -> ^( ListOperation ( param )+ ) )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==27) ) {
				alt9=1;
			}
			else if ( (LA9_0==26) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:15: '[]'
					{
					string_literal21=(Token)match(input,27,FOLLOW_27_in_listOParams344);  
					stream_27.add(string_literal21);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 134:20: -> ^( ListOperation )
					{
						// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:23: ^( ListOperation )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ListOperation, "ListOperation"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:42: '[' param ( ',' param )* ']'
					{
					char_literal22=(Token)match(input,26,FOLLOW_26_in_listOParams354);  
					stream_26.add(char_literal22);

					pushFollow(FOLLOW_param_in_listOParams356);
					param23=param();
					state._fsp--;

					stream_param.add(param23.getTree());
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:52: ( ',' param )*
					loop8:
					while (true) {
						int alt8=2;
						int LA8_0 = input.LA(1);
						if ( (LA8_0==25) ) {
							alt8=1;
						}

						switch (alt8) {
						case 1 :
							// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:53: ',' param
							{
							char_literal24=(Token)match(input,25,FOLLOW_25_in_listOParams359);  
							stream_25.add(char_literal24);

							pushFollow(FOLLOW_param_in_listOParams361);
							param25=param();
							state._fsp--;

							stream_param.add(param25.getTree());
							}
							break;

						default :
							break loop8;
						}
					}

					char_literal26=(Token)match(input,28,FOLLOW_28_in_listOParams365);  
					stream_28.add(char_literal26);

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
					// 134:69: -> ^( ListOperation ( param )+ )
					{
						// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:72: ^( ListOperation ( param )+ )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ListOperation, "ListOperation"), root_1);
						if ( !(stream_param.hasNext()) ) {
							throw new RewriteEarlyExitException();
						}
						while ( stream_param.hasNext() ) {
							adaptor.addChild(root_1, stream_param.nextTree());
						}
						stream_param.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

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
	// $ANTLR end "listOParams"


	public static class operationReference_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "operationReference"
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:135:1: operationReference : OperationReferenceToken -> ^( OperationReference OperationReferenceToken ) ;
	public final ParameterizedOperationsParser.operationReference_return operationReference() throws RecognitionException {
		ParameterizedOperationsParser.operationReference_return retval = new ParameterizedOperationsParser.operationReference_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token OperationReferenceToken27=null;

		CommonTree OperationReferenceToken27_tree=null;
		RewriteRuleTokenStream stream_OperationReferenceToken=new RewriteRuleTokenStream(adaptor,"token OperationReferenceToken");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:135:19: ( OperationReferenceToken -> ^( OperationReference OperationReferenceToken ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:135:21: OperationReferenceToken
			{
			OperationReferenceToken27=(Token)match(input,OperationReferenceToken,FOLLOW_OperationReferenceToken_in_operationReference380);  
			stream_OperationReferenceToken.add(OperationReferenceToken27);

			// AST REWRITE
			// elements: OperationReferenceToken
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 135:45: -> ^( OperationReference OperationReferenceToken )
			{
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:135:48: ^( OperationReference OperationReferenceToken )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationReference, "OperationReference"), root_1);
				adaptor.addChild(root_1, stream_OperationReferenceToken.nextNode());
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
	// $ANTLR end "operationReference"

	// Delegated rules



	public static final BitSet FOLLOW_expression_in_indicatorexpr109 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nativeop_in_expression121 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_userop_in_expression125 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Nativeop_in_nativeop136 = new BitSet(new long[]{0x0000000000820000L});
	public static final BitSet FOLLOW_OutputSelector_in_nativeop142 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_nativeop149 = new BitSet(new long[]{0x000000000D312E20L});
	public static final BitSet FOLLOW_params_in_nativeop154 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_nativeop160 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Userop_in_userop186 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_userop188 = new BitSet(new long[]{0x000000000D312E20L});
	public static final BitSet FOLLOW_params_in_userop193 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_userop199 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_param_in_params217 = new BitSet(new long[]{0x0000000002000002L});
	public static final BitSet FOLLOW_25_in_params220 = new BitSet(new long[]{0x000000000C312E20L});
	public static final BitSet FOLLOW_param_in_params222 = new BitSet(new long[]{0x0000000002000002L});
	public static final BitSet FOLLOW_NumberToken_in_param237 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NaNNumber_in_param250 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MATypeToken_in_param263 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_StringToken_in_param275 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_param287 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_stockhistory_in_operand294 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_listOParams_in_operand302 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operationReference_in_operand306 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_operand310 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_stockhistory318 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_27_in_listOParams344 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_26_in_listOParams354 = new BitSet(new long[]{0x000000000C312E20L});
	public static final BitSet FOLLOW_param_in_listOParams356 = new BitSet(new long[]{0x0000000012000000L});
	public static final BitSet FOLLOW_25_in_listOParams359 = new BitSet(new long[]{0x000000000C312E20L});
	public static final BitSet FOLLOW_param_in_listOParams361 = new BitSet(new long[]{0x0000000012000000L});
	public static final BitSet FOLLOW_28_in_listOParams365 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OperationReferenceToken_in_operationReference380 = new BitSet(new long[]{0x0000000000000002L});
}
