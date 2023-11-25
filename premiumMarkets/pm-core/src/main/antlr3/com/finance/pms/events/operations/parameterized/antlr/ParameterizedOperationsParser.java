// $ANTLR 3.5.2 com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g 2023-11-17 17:30:44
 //parser
    package com.finance.pms.events.operations.parameterized.antlr;
    import com.finance.pms.events.calculation.antlr.IErrorReporter;
    import com.finance.pms.events.calculation.antlr.MyErrorReporter;
    import com.finance.pms.events.calculation.antlr.OpsParserDelegate;
    import com.finance.pms.events.calculation.antlr.ParamsCountException;
    import com.finance.pms.events.calculation.antlr.UnfinishedParameterException;
    import com.finance.pms.events.calculation.antlr.InvalidOperationException;
    import com.finance.pms.events.calculation.antlr.EditorOpDescr;
    import com.finance.pms.events.calculation.antlr.MissingOutputSelectorException;
    import com.finance.pms.events.calculation.antlr.InvalidOperationException;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class ParameterizedOperationsParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMMENT", "HistoricalData", "LINE_COMMENT", 
		"ListOperation", "MAType", "MATypeToken", "NaNNumber", "NamedListOperation", 
		"Nativeop", "Number", "NumberToken", "OperationOutput", "OperationReference", 
		"OperationReferenceToken", "OutputSelector", "StockOperation", "String", 
		"StringToken", "Userop", "WS", "'('", "')'", "','", "':'", "'['", "'[]'", 
		"']'", "'{'", "'{}'", "'}'"
	};
	public static final int EOF=-1;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int COMMENT=4;
	public static final int HistoricalData=5;
	public static final int LINE_COMMENT=6;
	public static final int ListOperation=7;
	public static final int MAType=8;
	public static final int MATypeToken=9;
	public static final int NaNNumber=10;
	public static final int NamedListOperation=11;
	public static final int Nativeop=12;
	public static final int Number=13;
	public static final int NumberToken=14;
	public static final int OperationOutput=15;
	public static final int OperationReference=16;
	public static final int OperationReferenceToken=17;
	public static final int OutputSelector=18;
	public static final int StockOperation=19;
	public static final int String=20;
	public static final int StringToken=21;
	public static final int Userop=22;
	public static final int WS=23;

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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:123:1: indicatorexpr : expression -> expression ;
	public final ParameterizedOperationsParser.indicatorexpr_return indicatorexpr() throws RecognitionException {
		ParameterizedOperationsParser.indicatorexpr_return retval = new ParameterizedOperationsParser.indicatorexpr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope expression1 =null;

		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:123:15: ( expression -> expression )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:123:17: expression
			{
			pushFollow(FOLLOW_expression_in_indicatorexpr114);
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
			// 123:28: -> expression
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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:124:1: expression : ( nativeop | userop );
	public final ParameterizedOperationsParser.expression_return expression() throws RecognitionException {
		ParameterizedOperationsParser.expression_return retval = new ParameterizedOperationsParser.expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope nativeop2 =null;
		ParserRuleReturnScope userop3 =null;


		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:124:12: ( nativeop | userop )
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:124:14: nativeop
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_nativeop_in_expression126);
					nativeop2=nativeop();
					state._fsp--;

					adaptor.addChild(root_0, nativeop2.getTree());

					}
					break;
				case 2 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:124:25: userop
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_userop_in_expression130);
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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:1: nativeop : opName= Nativeop (outSelect= OutputSelector )? '(' (pars+= params )? ')' -> ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? ) ;
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
		RewriteRuleTokenStream stream_24=new RewriteRuleTokenStream(adaptor,"token 24");
		RewriteRuleTokenStream stream_OutputSelector=new RewriteRuleTokenStream(adaptor,"token OutputSelector");
		RewriteRuleTokenStream stream_25=new RewriteRuleTokenStream(adaptor,"token 25");
		RewriteRuleSubtreeStream stream_params=new RewriteRuleSubtreeStream(adaptor,"rule params");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:10: (opName= Nativeop (outSelect= OutputSelector )? '(' (pars+= params )? ')' -> ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:2: opName= Nativeop (outSelect= OutputSelector )? '(' (pars+= params )? ')'
			{
			opName=(Token)match(input,Nativeop,FOLLOW_Nativeop_in_nativeop141);  
			stream_Nativeop.add(opName);

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:18: (outSelect= OutputSelector )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==OutputSelector) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:20: outSelect= OutputSelector
					{
					outSelect=(Token)match(input,OutputSelector,FOLLOW_OutputSelector_in_nativeop147);  
					stream_OutputSelector.add(outSelect);

					}
					break;

			}

			outputSelectorHint(opName, outSelect);
			char_literal4=(Token)match(input,24,FOLLOW_24_in_nativeop154);  
			stream_24.add(char_literal4);

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:95: (pars+= params )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==HistoricalData||(LA3_0 >= MATypeToken && LA3_0 <= NaNNumber)||LA3_0==Nativeop||LA3_0==NumberToken||LA3_0==OperationReferenceToken||(LA3_0 >= StringToken && LA3_0 <= Userop)||(LA3_0 >= 28 && LA3_0 <= 29)||(LA3_0 >= 31 && LA3_0 <= 32)) ) {
				alt3=1;
			}
			switch (alt3) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:96: pars+= params
					{
					pushFollow(FOLLOW_params_in_nativeop159);
					pars=params();
					state._fsp--;

					stream_params.add(pars.getTree());
					if (list_pars==null) list_pars=new ArrayList<Object>();
					list_pars.add(pars.getTree());
					}
					break;

			}

			checkParamExhaust(opName, list_pars);
			char_literal5=(Token)match(input,25,FOLLOW_25_in_nativeop165);  
			stream_25.add(char_literal5);

			// AST REWRITE
			// elements: OutputSelector, Nativeop, params
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 127:152: -> ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? )
			{
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:155: ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(stream_Nativeop.nextNode(), root_1);
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:166: ( ^( OperationOutput OutputSelector ) )?
				if ( stream_OutputSelector.hasNext() ) {
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:166: ^( OperationOutput OutputSelector )
					{
					CommonTree root_2 = (CommonTree)adaptor.nil();
					root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
					adaptor.addChild(root_2, stream_OutputSelector.nextNode());
					adaptor.addChild(root_1, root_2);
					}

				}
				stream_OutputSelector.reset();

				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:201: ( params )?
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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:128:1: userop : opName= Userop '(' (pars+= params )? ')' -> ^( Userop ( params )? ) ;
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
		RewriteRuleTokenStream stream_24=new RewriteRuleTokenStream(adaptor,"token 24");
		RewriteRuleTokenStream stream_25=new RewriteRuleTokenStream(adaptor,"token 25");
		RewriteRuleTokenStream stream_Userop=new RewriteRuleTokenStream(adaptor,"token Userop");
		RewriteRuleSubtreeStream stream_params=new RewriteRuleSubtreeStream(adaptor,"rule params");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:128:8: (opName= Userop '(' (pars+= params )? ')' -> ^( Userop ( params )? ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:129:2: opName= Userop '(' (pars+= params )? ')'
			{
			opName=(Token)match(input,Userop,FOLLOW_Userop_in_userop191);  
			stream_Userop.add(opName);

			char_literal6=(Token)match(input,24,FOLLOW_24_in_userop193);  
			stream_24.add(char_literal6);

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:129:20: (pars+= params )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==HistoricalData||(LA4_0 >= MATypeToken && LA4_0 <= NaNNumber)||LA4_0==Nativeop||LA4_0==NumberToken||LA4_0==OperationReferenceToken||(LA4_0 >= StringToken && LA4_0 <= Userop)||(LA4_0 >= 28 && LA4_0 <= 29)||(LA4_0 >= 31 && LA4_0 <= 32)) ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:129:21: pars+= params
					{
					pushFollow(FOLLOW_params_in_userop198);
					pars=params();
					state._fsp--;

					stream_params.add(pars.getTree());
					if (list_pars==null) list_pars=new ArrayList<Object>();
					list_pars.add(pars.getTree());
					}
					break;

			}

			checkParamExhaust(opName, list_pars);
			char_literal7=(Token)match(input,25,FOLLOW_25_in_userop204);  
			stream_25.add(char_literal7);

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
			// 129:77: -> ^( Userop ( params )? )
			{
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:129:80: ^( Userop ( params )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(stream_Userop.nextNode(), root_1);
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:129:89: ( params )?
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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:1: params : param ( ',' param )* -> ( param )+ ;
	public final ParameterizedOperationsParser.params_return params() throws RecognitionException {
		ParameterizedOperationsParser.params_return retval = new ParameterizedOperationsParser.params_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal9=null;
		ParserRuleReturnScope param8 =null;
		ParserRuleReturnScope param10 =null;

		CommonTree char_literal9_tree=null;
		RewriteRuleTokenStream stream_26=new RewriteRuleTokenStream(adaptor,"token 26");
		RewriteRuleSubtreeStream stream_param=new RewriteRuleSubtreeStream(adaptor,"rule param");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:8: ( param ( ',' param )* -> ( param )+ )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:10: param ( ',' param )*
			{
			pushFollow(FOLLOW_param_in_params222);
			param8=param();
			state._fsp--;

			stream_param.add(param8.getTree());
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:16: ( ',' param )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==26) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:131:17: ',' param
					{
					char_literal9=(Token)match(input,26,FOLLOW_26_in_params225);  
					stream_26.add(char_literal9);

					pushFollow(FOLLOW_param_in_params227);
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
			// 131:29: -> ( param )+
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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:132:1: param : ( NumberToken -> ^( Number NumberToken ) | NaNNumber -> ^( Number NumberToken[\"NaN\"] ) | MATypeToken -> ^( MAType MATypeToken ) | stringOperand | operand );
	public final ParameterizedOperationsParser.param_return param() throws RecognitionException {
		ParameterizedOperationsParser.param_return retval = new ParameterizedOperationsParser.param_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NumberToken11=null;
		Token NaNNumber12=null;
		Token MATypeToken13=null;
		ParserRuleReturnScope stringOperand14 =null;
		ParserRuleReturnScope operand15 =null;

		CommonTree NumberToken11_tree=null;
		CommonTree NaNNumber12_tree=null;
		CommonTree MATypeToken13_tree=null;
		RewriteRuleTokenStream stream_MATypeToken=new RewriteRuleTokenStream(adaptor,"token MATypeToken");
		RewriteRuleTokenStream stream_NumberToken=new RewriteRuleTokenStream(adaptor,"token NumberToken");
		RewriteRuleTokenStream stream_NaNNumber=new RewriteRuleTokenStream(adaptor,"token NaNNumber");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:132:7: ( NumberToken -> ^( Number NumberToken ) | NaNNumber -> ^( Number NumberToken[\"NaN\"] ) | MATypeToken -> ^( MAType MATypeToken ) | stringOperand | operand )
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
			case 28:
			case 29:
			case 31:
			case 32:
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:132:9: NumberToken
					{
					NumberToken11=(Token)match(input,NumberToken,FOLLOW_NumberToken_in_param242);  
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
					// 132:21: -> ^( Number NumberToken )
					{
						// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:132:25: ^( Number NumberToken )
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:132:49: NaNNumber
					{
					NaNNumber12=(Token)match(input,NaNNumber,FOLLOW_NaNNumber_in_param255);  
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
					// 132:59: -> ^( Number NumberToken[\"NaN\"] )
					{
						// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:132:62: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:132:93: MATypeToken
					{
					MATypeToken13=(Token)match(input,MATypeToken,FOLLOW_MATypeToken_in_param268);  
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
					// 132:105: -> ^( MAType MATypeToken )
					{
						// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:132:108: ^( MAType MATypeToken )
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:132:132: stringOperand
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_stringOperand_in_param280);
					stringOperand14=stringOperand();
					state._fsp--;

					adaptor.addChild(root_0, stringOperand14.getTree());

					}
					break;
				case 5 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:132:148: operand
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_operand_in_param284);
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


	public static class stringOperand_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "stringOperand"
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:1: stringOperand : StringToken -> ^( String StringToken ) ;
	public final ParameterizedOperationsParser.stringOperand_return stringOperand() throws RecognitionException {
		ParameterizedOperationsParser.stringOperand_return retval = new ParameterizedOperationsParser.stringOperand_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token StringToken16=null;

		CommonTree StringToken16_tree=null;
		RewriteRuleTokenStream stream_StringToken=new RewriteRuleTokenStream(adaptor,"token StringToken");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:15: ( StringToken -> ^( String StringToken ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:17: StringToken
			{
			StringToken16=(Token)match(input,StringToken,FOLLOW_StringToken_in_stringOperand291);  
			stream_StringToken.add(StringToken16);

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
			// 133:29: -> ^( String StringToken )
			{
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:133:32: ^( String StringToken )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_1);
				adaptor.addChild(root_1, stream_StringToken.nextNode());
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
	// $ANTLR end "stringOperand"


	public static class operand_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "operand"
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:1: operand : ( stockhistory -> stockhistory | listOParams | operationReference | namedListOParams | expression );
	public final ParameterizedOperationsParser.operand_return operand() throws RecognitionException {
		ParameterizedOperationsParser.operand_return retval = new ParameterizedOperationsParser.operand_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope stockhistory17 =null;
		ParserRuleReturnScope listOParams18 =null;
		ParserRuleReturnScope operationReference19 =null;
		ParserRuleReturnScope namedListOParams20 =null;
		ParserRuleReturnScope expression21 =null;

		RewriteRuleSubtreeStream stream_stockhistory=new RewriteRuleSubtreeStream(adaptor,"rule stockhistory");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:9: ( stockhistory -> stockhistory | listOParams | operationReference | namedListOParams | expression )
			int alt7=5;
			switch ( input.LA(1) ) {
			case HistoricalData:
				{
				alt7=1;
				}
				break;
			case 28:
			case 29:
				{
				alt7=2;
				}
				break;
			case OperationReferenceToken:
				{
				alt7=3;
				}
				break;
			case 31:
			case 32:
				{
				alt7=4;
				}
				break;
			case Nativeop:
			case Userop:
				{
				alt7=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}
			switch (alt7) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:11: stockhistory
					{
					pushFollow(FOLLOW_stockhistory_in_operand306);
					stockhistory17=stockhistory();
					state._fsp--;

					stream_stockhistory.add(stockhistory17.getTree());
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
					// 134:24: -> stockhistory
					{
						adaptor.addChild(root_0, stream_stockhistory.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:42: listOParams
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_listOParams_in_operand314);
					listOParams18=listOParams();
					state._fsp--;

					adaptor.addChild(root_0, listOParams18.getTree());

					}
					break;
				case 3 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:56: operationReference
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_operationReference_in_operand318);
					operationReference19=operationReference();
					state._fsp--;

					adaptor.addChild(root_0, operationReference19.getTree());

					}
					break;
				case 4 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:77: namedListOParams
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_namedListOParams_in_operand322);
					namedListOParams20=namedListOParams();
					state._fsp--;

					adaptor.addChild(root_0, namedListOParams20.getTree());

					}
					break;
				case 5 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:134:96: expression
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_expression_in_operand326);
					expression21=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression21.getTree());

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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:135:1: stockhistory : HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) ;
	public final ParameterizedOperationsParser.stockhistory_return stockhistory() throws RecognitionException {
		ParameterizedOperationsParser.stockhistory_return retval = new ParameterizedOperationsParser.stockhistory_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token HistoricalData22=null;

		CommonTree HistoricalData22_tree=null;
		RewriteRuleTokenStream stream_HistoricalData=new RewriteRuleTokenStream(adaptor,"token HistoricalData");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:135:14: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:135:16: HistoricalData
			{
			HistoricalData22=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_stockhistory334);  
			stream_HistoricalData.add(HistoricalData22);

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
			// 135:31: -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
			{
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:135:34: ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:135:51: ^( OperationOutput HistoricalData )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
				adaptor.addChild(root_2, stream_HistoricalData.nextNode());
				adaptor.addChild(root_1, root_2);
				}

				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:135:85: ^( String StringToken[\"\\\"THIS\\\"\"] )
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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:1: listOParams : ( '[]' -> ^( ListOperation ) | '[' param ( ',' param )* ']' -> ^( ListOperation ( param )+ ) );
	public final ParameterizedOperationsParser.listOParams_return listOParams() throws RecognitionException {
		ParameterizedOperationsParser.listOParams_return retval = new ParameterizedOperationsParser.listOParams_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal23=null;
		Token char_literal24=null;
		Token char_literal26=null;
		Token char_literal28=null;
		ParserRuleReturnScope param25 =null;
		ParserRuleReturnScope param27 =null;

		CommonTree string_literal23_tree=null;
		CommonTree char_literal24_tree=null;
		CommonTree char_literal26_tree=null;
		CommonTree char_literal28_tree=null;
		RewriteRuleTokenStream stream_26=new RewriteRuleTokenStream(adaptor,"token 26");
		RewriteRuleTokenStream stream_28=new RewriteRuleTokenStream(adaptor,"token 28");
		RewriteRuleTokenStream stream_29=new RewriteRuleTokenStream(adaptor,"token 29");
		RewriteRuleTokenStream stream_30=new RewriteRuleTokenStream(adaptor,"token 30");
		RewriteRuleSubtreeStream stream_param=new RewriteRuleSubtreeStream(adaptor,"rule param");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:13: ( '[]' -> ^( ListOperation ) | '[' param ( ',' param )* ']' -> ^( ListOperation ( param )+ ) )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==29) ) {
				alt9=1;
			}
			else if ( (LA9_0==28) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:15: '[]'
					{
					string_literal23=(Token)match(input,29,FOLLOW_29_in_listOParams360);  
					stream_29.add(string_literal23);

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
					// 136:20: -> ^( ListOperation )
					{
						// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:23: ^( ListOperation )
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:42: '[' param ( ',' param )* ']'
					{
					char_literal24=(Token)match(input,28,FOLLOW_28_in_listOParams370);  
					stream_28.add(char_literal24);

					pushFollow(FOLLOW_param_in_listOParams372);
					param25=param();
					state._fsp--;

					stream_param.add(param25.getTree());
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:52: ( ',' param )*
					loop8:
					while (true) {
						int alt8=2;
						int LA8_0 = input.LA(1);
						if ( (LA8_0==26) ) {
							alt8=1;
						}

						switch (alt8) {
						case 1 :
							// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:53: ',' param
							{
							char_literal26=(Token)match(input,26,FOLLOW_26_in_listOParams375);  
							stream_26.add(char_literal26);

							pushFollow(FOLLOW_param_in_listOParams377);
							param27=param();
							state._fsp--;

							stream_param.add(param27.getTree());
							}
							break;

						default :
							break loop8;
						}
					}

					char_literal28=(Token)match(input,30,FOLLOW_30_in_listOParams381);  
					stream_30.add(char_literal28);

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
					// 136:69: -> ^( ListOperation ( param )+ )
					{
						// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:136:72: ^( ListOperation ( param )+ )
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


	public static class namedListOParams_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "namedListOParams"
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:137:1: namedListOParams : ( '{}' -> ^( NamedListOperation ) | '{' stringOperand ':' param ( ',' stringOperand ':' param )* '}' -> ^( NamedListOperation ( stringOperand )+ ( param )+ ) );
	public final ParameterizedOperationsParser.namedListOParams_return namedListOParams() throws RecognitionException {
		ParameterizedOperationsParser.namedListOParams_return retval = new ParameterizedOperationsParser.namedListOParams_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal29=null;
		Token char_literal30=null;
		Token char_literal32=null;
		Token char_literal34=null;
		Token char_literal36=null;
		Token char_literal38=null;
		ParserRuleReturnScope stringOperand31 =null;
		ParserRuleReturnScope param33 =null;
		ParserRuleReturnScope stringOperand35 =null;
		ParserRuleReturnScope param37 =null;

		CommonTree string_literal29_tree=null;
		CommonTree char_literal30_tree=null;
		CommonTree char_literal32_tree=null;
		CommonTree char_literal34_tree=null;
		CommonTree char_literal36_tree=null;
		CommonTree char_literal38_tree=null;
		RewriteRuleTokenStream stream_33=new RewriteRuleTokenStream(adaptor,"token 33");
		RewriteRuleTokenStream stream_26=new RewriteRuleTokenStream(adaptor,"token 26");
		RewriteRuleTokenStream stream_27=new RewriteRuleTokenStream(adaptor,"token 27");
		RewriteRuleTokenStream stream_31=new RewriteRuleTokenStream(adaptor,"token 31");
		RewriteRuleTokenStream stream_32=new RewriteRuleTokenStream(adaptor,"token 32");
		RewriteRuleSubtreeStream stream_param=new RewriteRuleSubtreeStream(adaptor,"rule param");
		RewriteRuleSubtreeStream stream_stringOperand=new RewriteRuleSubtreeStream(adaptor,"rule stringOperand");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:137:18: ( '{}' -> ^( NamedListOperation ) | '{' stringOperand ':' param ( ',' stringOperand ':' param )* '}' -> ^( NamedListOperation ( stringOperand )+ ( param )+ ) )
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==32) ) {
				alt11=1;
			}
			else if ( (LA11_0==31) ) {
				alt11=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}

			switch (alt11) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:137:20: '{}'
					{
					string_literal29=(Token)match(input,32,FOLLOW_32_in_namedListOParams397);  
					stream_32.add(string_literal29);

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
					// 137:25: -> ^( NamedListOperation )
					{
						// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:137:28: ^( NamedListOperation )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NamedListOperation, "NamedListOperation"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:137:52: '{' stringOperand ':' param ( ',' stringOperand ':' param )* '}'
					{
					char_literal30=(Token)match(input,31,FOLLOW_31_in_namedListOParams407);  
					stream_31.add(char_literal30);

					pushFollow(FOLLOW_stringOperand_in_namedListOParams409);
					stringOperand31=stringOperand();
					state._fsp--;

					stream_stringOperand.add(stringOperand31.getTree());
					char_literal32=(Token)match(input,27,FOLLOW_27_in_namedListOParams411);  
					stream_27.add(char_literal32);

					pushFollow(FOLLOW_param_in_namedListOParams413);
					param33=param();
					state._fsp--;

					stream_param.add(param33.getTree());
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:137:80: ( ',' stringOperand ':' param )*
					loop10:
					while (true) {
						int alt10=2;
						int LA10_0 = input.LA(1);
						if ( (LA10_0==26) ) {
							alt10=1;
						}

						switch (alt10) {
						case 1 :
							// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:137:81: ',' stringOperand ':' param
							{
							char_literal34=(Token)match(input,26,FOLLOW_26_in_namedListOParams416);  
							stream_26.add(char_literal34);

							pushFollow(FOLLOW_stringOperand_in_namedListOParams418);
							stringOperand35=stringOperand();
							state._fsp--;

							stream_stringOperand.add(stringOperand35.getTree());
							char_literal36=(Token)match(input,27,FOLLOW_27_in_namedListOParams420);  
							stream_27.add(char_literal36);

							pushFollow(FOLLOW_param_in_namedListOParams422);
							param37=param();
							state._fsp--;

							stream_param.add(param37.getTree());
							}
							break;

						default :
							break loop10;
						}
					}

					char_literal38=(Token)match(input,33,FOLLOW_33_in_namedListOParams426);  
					stream_33.add(char_literal38);

					// AST REWRITE
					// elements: param, stringOperand
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 137:115: -> ^( NamedListOperation ( stringOperand )+ ( param )+ )
					{
						// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:137:118: ^( NamedListOperation ( stringOperand )+ ( param )+ )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NamedListOperation, "NamedListOperation"), root_1);
						if ( !(stream_stringOperand.hasNext()) ) {
							throw new RewriteEarlyExitException();
						}
						while ( stream_stringOperand.hasNext() ) {
							adaptor.addChild(root_1, stream_stringOperand.nextTree());
						}
						stream_stringOperand.reset();

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
	// $ANTLR end "namedListOParams"


	public static class operationReference_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "operationReference"
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:138:1: operationReference : OperationReferenceToken -> ^( OperationReference OperationReferenceToken ) ;
	public final ParameterizedOperationsParser.operationReference_return operationReference() throws RecognitionException {
		ParameterizedOperationsParser.operationReference_return retval = new ParameterizedOperationsParser.operationReference_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token OperationReferenceToken39=null;

		CommonTree OperationReferenceToken39_tree=null;
		RewriteRuleTokenStream stream_OperationReferenceToken=new RewriteRuleTokenStream(adaptor,"token OperationReferenceToken");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:138:19: ( OperationReferenceToken -> ^( OperationReference OperationReferenceToken ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:138:21: OperationReferenceToken
			{
			OperationReferenceToken39=(Token)match(input,OperationReferenceToken,FOLLOW_OperationReferenceToken_in_operationReference444);  
			stream_OperationReferenceToken.add(OperationReferenceToken39);

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
			// 138:45: -> ^( OperationReference OperationReferenceToken )
			{
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:138:48: ^( OperationReference OperationReferenceToken )
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



	public static final BitSet FOLLOW_expression_in_indicatorexpr114 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nativeop_in_expression126 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_userop_in_expression130 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Nativeop_in_nativeop141 = new BitSet(new long[]{0x0000000001040000L});
	public static final BitSet FOLLOW_OutputSelector_in_nativeop147 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_nativeop154 = new BitSet(new long[]{0x00000001B2625620L});
	public static final BitSet FOLLOW_params_in_nativeop159 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_25_in_nativeop165 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Userop_in_userop191 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_userop193 = new BitSet(new long[]{0x00000001B2625620L});
	public static final BitSet FOLLOW_params_in_userop198 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_25_in_userop204 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_param_in_params222 = new BitSet(new long[]{0x0000000004000002L});
	public static final BitSet FOLLOW_26_in_params225 = new BitSet(new long[]{0x00000001B0625620L});
	public static final BitSet FOLLOW_param_in_params227 = new BitSet(new long[]{0x0000000004000002L});
	public static final BitSet FOLLOW_NumberToken_in_param242 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NaNNumber_in_param255 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MATypeToken_in_param268 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_stringOperand_in_param280 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_param284 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_StringToken_in_stringOperand291 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_stockhistory_in_operand306 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_listOParams_in_operand314 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operationReference_in_operand318 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_namedListOParams_in_operand322 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_operand326 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_stockhistory334 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_29_in_listOParams360 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_28_in_listOParams370 = new BitSet(new long[]{0x00000001B0625620L});
	public static final BitSet FOLLOW_param_in_listOParams372 = new BitSet(new long[]{0x0000000044000000L});
	public static final BitSet FOLLOW_26_in_listOParams375 = new BitSet(new long[]{0x00000001B0625620L});
	public static final BitSet FOLLOW_param_in_listOParams377 = new BitSet(new long[]{0x0000000044000000L});
	public static final BitSet FOLLOW_30_in_listOParams381 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_32_in_namedListOParams397 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_31_in_namedListOParams407 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_stringOperand_in_namedListOParams409 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_namedListOParams411 = new BitSet(new long[]{0x00000001B0625620L});
	public static final BitSet FOLLOW_param_in_namedListOParams413 = new BitSet(new long[]{0x0000000204000000L});
	public static final BitSet FOLLOW_26_in_namedListOParams416 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_stringOperand_in_namedListOParams418 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_namedListOParams420 = new BitSet(new long[]{0x00000001B0625620L});
	public static final BitSet FOLLOW_param_in_namedListOParams422 = new BitSet(new long[]{0x0000000204000000L});
	public static final BitSet FOLLOW_33_in_namedListOParams426 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OperationReferenceToken_in_operationReference444 = new BitSet(new long[]{0x0000000000000002L});
}
