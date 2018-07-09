// $ANTLR 3.5.2 com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g 2018-06-19 22:08:51
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
		"MAType", "MATypeToken", "Nativeop", "Number", "NumberToken", "OperationOutput", 
		"OutputSelector", "StockOperation", "String", "StringToken", "Userop", 
		"WS", "'('", "')'", "','"
	};
	public static final int EOF=-1;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int T__21=21;
	public static final int COMMENT=4;
	public static final int HistoricalData=5;
	public static final int LINE_COMMENT=6;
	public static final int MAType=7;
	public static final int MATypeToken=8;
	public static final int Nativeop=9;
	public static final int Number=10;
	public static final int NumberToken=11;
	public static final int OperationOutput=12;
	public static final int OutputSelector=13;
	public static final int StockOperation=14;
	public static final int String=15;
	public static final int StringToken=16;
	public static final int Userop=17;
	public static final int WS=18;

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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:117:1: indicatorexpr : expression -> expression ;
	public final ParameterizedOperationsParser.indicatorexpr_return indicatorexpr() throws RecognitionException {
		ParameterizedOperationsParser.indicatorexpr_return retval = new ParameterizedOperationsParser.indicatorexpr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope expression1 =null;

		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:117:15: ( expression -> expression )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:117:17: expression
			{
			pushFollow(FOLLOW_expression_in_indicatorexpr99);
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
			// 117:28: -> expression
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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:118:1: expression : ( nativeop | userop );
	public final ParameterizedOperationsParser.expression_return expression() throws RecognitionException {
		ParameterizedOperationsParser.expression_return retval = new ParameterizedOperationsParser.expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope nativeop2 =null;
		ParserRuleReturnScope userop3 =null;


		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:118:12: ( nativeop | userop )
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:118:14: nativeop
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_nativeop_in_expression111);
					nativeop2=nativeop();
					state._fsp--;

					adaptor.addChild(root_0, nativeop2.getTree());

					}
					break;
				case 2 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:118:25: userop
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_userop_in_expression115);
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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:120:1: nativeop : opName= Nativeop (outSelect= OutputSelector )? '(' (pars+= params )? ')' -> ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? ) ;
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
		RewriteRuleTokenStream stream_19=new RewriteRuleTokenStream(adaptor,"token 19");
		RewriteRuleTokenStream stream_20=new RewriteRuleTokenStream(adaptor,"token 20");
		RewriteRuleSubtreeStream stream_params=new RewriteRuleSubtreeStream(adaptor,"rule params");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:120:10: (opName= Nativeop (outSelect= OutputSelector )? '(' (pars+= params )? ')' -> ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:121:2: opName= Nativeop (outSelect= OutputSelector )? '(' (pars+= params )? ')'
			{
			opName=(Token)match(input,Nativeop,FOLLOW_Nativeop_in_nativeop127);  
			stream_Nativeop.add(opName);

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:121:18: (outSelect= OutputSelector )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==OutputSelector) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:121:20: outSelect= OutputSelector
					{
					outSelect=(Token)match(input,OutputSelector,FOLLOW_OutputSelector_in_nativeop133);  
					stream_OutputSelector.add(outSelect);

					}
					break;

			}

			outputSelectorHint(opName, outSelect);
			char_literal4=(Token)match(input,19,FOLLOW_19_in_nativeop140);  
			stream_19.add(char_literal4);

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:121:95: (pars+= params )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==HistoricalData||(LA3_0 >= MATypeToken && LA3_0 <= Nativeop)||LA3_0==NumberToken||(LA3_0 >= StringToken && LA3_0 <= Userop)) ) {
				alt3=1;
			}
			switch (alt3) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:121:96: pars+= params
					{
					pushFollow(FOLLOW_params_in_nativeop145);
					pars=params();
					state._fsp--;

					stream_params.add(pars.getTree());
					if (list_pars==null) list_pars=new ArrayList<Object>();
					list_pars.add(pars.getTree());
					}
					break;

			}

			checkParamExhaust(opName, list_pars);
			char_literal5=(Token)match(input,20,FOLLOW_20_in_nativeop151);  
			stream_20.add(char_literal5);

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
			// 121:152: -> ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? )
			{
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:121:155: ^( Nativeop ( ^( OperationOutput OutputSelector ) )? ( params )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(stream_Nativeop.nextNode(), root_1);
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:121:166: ( ^( OperationOutput OutputSelector ) )?
				if ( stream_OutputSelector.hasNext() ) {
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:121:166: ^( OperationOutput OutputSelector )
					{
					CommonTree root_2 = (CommonTree)adaptor.nil();
					root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
					adaptor.addChild(root_2, stream_OutputSelector.nextNode());
					adaptor.addChild(root_1, root_2);
					}

				}
				stream_OutputSelector.reset();

				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:121:201: ( params )?
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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:122:1: userop : opName= Userop '(' (pars+= params )? ')' -> ^( Userop ( params )? ) ;
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
		RewriteRuleTokenStream stream_19=new RewriteRuleTokenStream(adaptor,"token 19");
		RewriteRuleTokenStream stream_Userop=new RewriteRuleTokenStream(adaptor,"token Userop");
		RewriteRuleTokenStream stream_20=new RewriteRuleTokenStream(adaptor,"token 20");
		RewriteRuleSubtreeStream stream_params=new RewriteRuleSubtreeStream(adaptor,"rule params");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:122:8: (opName= Userop '(' (pars+= params )? ')' -> ^( Userop ( params )? ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:123:2: opName= Userop '(' (pars+= params )? ')'
			{
			opName=(Token)match(input,Userop,FOLLOW_Userop_in_userop178);  
			stream_Userop.add(opName);

			char_literal6=(Token)match(input,19,FOLLOW_19_in_userop180);  
			stream_19.add(char_literal6);

			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:123:20: (pars+= params )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==HistoricalData||(LA4_0 >= MATypeToken && LA4_0 <= Nativeop)||LA4_0==NumberToken||(LA4_0 >= StringToken && LA4_0 <= Userop)) ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:123:21: pars+= params
					{
					pushFollow(FOLLOW_params_in_userop185);
					pars=params();
					state._fsp--;

					stream_params.add(pars.getTree());
					if (list_pars==null) list_pars=new ArrayList<Object>();
					list_pars.add(pars.getTree());
					}
					break;

			}

			checkParamExhaust(opName, list_pars);
			char_literal7=(Token)match(input,20,FOLLOW_20_in_userop191);  
			stream_20.add(char_literal7);

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
			// 123:78: -> ^( Userop ( params )? )
			{
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:123:81: ^( Userop ( params )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(stream_Userop.nextNode(), root_1);
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:123:90: ( params )?
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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:125:1: params : param ( ',' param )* -> ( param )+ ;
	public final ParameterizedOperationsParser.params_return params() throws RecognitionException {
		ParameterizedOperationsParser.params_return retval = new ParameterizedOperationsParser.params_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal9=null;
		ParserRuleReturnScope param8 =null;
		ParserRuleReturnScope param10 =null;

		CommonTree char_literal9_tree=null;
		RewriteRuleTokenStream stream_21=new RewriteRuleTokenStream(adaptor,"token 21");
		RewriteRuleSubtreeStream stream_param=new RewriteRuleSubtreeStream(adaptor,"rule param");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:125:8: ( param ( ',' param )* -> ( param )+ )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:125:10: param ( ',' param )*
			{
			pushFollow(FOLLOW_param_in_params212);
			param8=param();
			state._fsp--;

			stream_param.add(param8.getTree());
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:125:16: ( ',' param )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==21) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:125:17: ',' param
					{
					char_literal9=(Token)match(input,21,FOLLOW_21_in_params215);  
					stream_21.add(char_literal9);

					pushFollow(FOLLOW_param_in_params217);
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
			// 125:29: -> ( param )+
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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:1: param : ( NumberToken -> ^( Number NumberToken ) | MATypeToken -> ^( MAType MATypeToken ) | StringToken -> ^( String StringToken ) | operand );
	public final ParameterizedOperationsParser.param_return param() throws RecognitionException {
		ParameterizedOperationsParser.param_return retval = new ParameterizedOperationsParser.param_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NumberToken11=null;
		Token MATypeToken12=null;
		Token StringToken13=null;
		ParserRuleReturnScope operand14 =null;

		CommonTree NumberToken11_tree=null;
		CommonTree MATypeToken12_tree=null;
		CommonTree StringToken13_tree=null;
		RewriteRuleTokenStream stream_MATypeToken=new RewriteRuleTokenStream(adaptor,"token MATypeToken");
		RewriteRuleTokenStream stream_NumberToken=new RewriteRuleTokenStream(adaptor,"token NumberToken");
		RewriteRuleTokenStream stream_StringToken=new RewriteRuleTokenStream(adaptor,"token StringToken");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:7: ( NumberToken -> ^( Number NumberToken ) | MATypeToken -> ^( MAType MATypeToken ) | StringToken -> ^( String StringToken ) | operand )
			int alt6=4;
			switch ( input.LA(1) ) {
			case NumberToken:
				{
				alt6=1;
				}
				break;
			case MATypeToken:
				{
				alt6=2;
				}
				break;
			case StringToken:
				{
				alt6=3;
				}
				break;
			case HistoricalData:
			case Nativeop:
			case Userop:
				{
				alt6=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}
			switch (alt6) {
				case 1 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:9: NumberToken
					{
					NumberToken11=(Token)match(input,NumberToken,FOLLOW_NumberToken_in_param232);  
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
					// 126:21: -> ^( Number NumberToken )
					{
						// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:25: ^( Number NumberToken )
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:49: MATypeToken
					{
					MATypeToken12=(Token)match(input,MATypeToken,FOLLOW_MATypeToken_in_param245);  
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
					// 126:61: -> ^( MAType MATypeToken )
					{
						// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:64: ^( MAType MATypeToken )
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:88: StringToken
					{
					StringToken13=(Token)match(input,StringToken,FOLLOW_StringToken_in_param257);  
					stream_StringToken.add(StringToken13);

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
					// 126:100: -> ^( String StringToken )
					{
						// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:104: ^( String StringToken )
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
				case 4 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:126:128: operand
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_operand_in_param270);
					operand14=operand();
					state._fsp--;

					adaptor.addChild(root_0, operand14.getTree());

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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:1: operand : ( stockhistory -> stockhistory | expression );
	public final ParameterizedOperationsParser.operand_return operand() throws RecognitionException {
		ParameterizedOperationsParser.operand_return retval = new ParameterizedOperationsParser.operand_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope stockhistory15 =null;
		ParserRuleReturnScope expression16 =null;

		RewriteRuleSubtreeStream stream_stockhistory=new RewriteRuleSubtreeStream(adaptor,"rule stockhistory");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:9: ( stockhistory -> stockhistory | expression )
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
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:11: stockhistory
					{
					pushFollow(FOLLOW_stockhistory_in_operand278);
					stockhistory15=stockhistory();
					state._fsp--;

					stream_stockhistory.add(stockhistory15.getTree());
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
					// 127:24: -> stockhistory
					{
						adaptor.addChild(root_0, stream_stockhistory.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:127:42: expression
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_expression_in_operand286);
					expression16=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression16.getTree());

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
	// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:128:1: stockhistory : HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ) ;
	public final ParameterizedOperationsParser.stockhistory_return stockhistory() throws RecognitionException {
		ParameterizedOperationsParser.stockhistory_return retval = new ParameterizedOperationsParser.stockhistory_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token HistoricalData17=null;

		CommonTree HistoricalData17_tree=null;
		RewriteRuleTokenStream stream_HistoricalData=new RewriteRuleTokenStream(adaptor,"token HistoricalData");

		try {
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:128:14: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ) )
			// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:128:16: HistoricalData
			{
			HistoricalData17=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_stockhistory294);  
			stream_HistoricalData.add(HistoricalData17);

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
			// 128:31: -> ^( StockOperation ^( OperationOutput HistoricalData ) )
			{
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:128:34: ^( StockOperation ^( OperationOutput HistoricalData ) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
				// com/finance/pms/events/operations/parameterized/antlr/ParameterizedOperations.g:128:51: ^( OperationOutput HistoricalData )
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



	public static final BitSet FOLLOW_expression_in_indicatorexpr99 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nativeop_in_expression111 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_userop_in_expression115 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Nativeop_in_nativeop127 = new BitSet(new long[]{0x0000000000082000L});
	public static final BitSet FOLLOW_OutputSelector_in_nativeop133 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_nativeop140 = new BitSet(new long[]{0x0000000000130B20L});
	public static final BitSet FOLLOW_params_in_nativeop145 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_nativeop151 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Userop_in_userop178 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_userop180 = new BitSet(new long[]{0x0000000000130B20L});
	public static final BitSet FOLLOW_params_in_userop185 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_userop191 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_param_in_params212 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_21_in_params215 = new BitSet(new long[]{0x0000000000030B20L});
	public static final BitSet FOLLOW_param_in_params217 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_NumberToken_in_param232 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MATypeToken_in_param245 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_StringToken_in_param257 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_param270 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_stockhistory_in_operand278 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_operand286 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_stockhistory294 = new BitSet(new long[]{0x0000000000000002L});
}
