// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2018-10-28 22:53:46
 //parser
    package com.finance.pms.events.calculation.parametrizedindicators.antlr;
    import com.finance.pms.events.calculation.antlr.MyErrorReporter;
    import com.finance.pms.events.calculation.antlr.IndsParserDelegate;
    import com.finance.pms.events.calculation.antlr.MissingOutputSelectorException;
    import com.finance.pms.events.calculation.antlr.UnfinishedNestedCondition;
    import com.finance.pms.events.calculation.antlr.InvalidOperationException;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class ParameterizedIndicatorsParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "AND", "AndDoubleMapCondition", 
		"CLOSEPARENTEHSIS", "COMMA", "COMMENT", "CrossDownConstantCondition", 
		"CrossDownDoubleMapCondition", "CrossUpConstantCondition", "CrossUpDoubleMapCondition", 
		"DAYS", "DownRatioCondition", "EqualConstantCondition", "EqualDoubleMapCondition", 
		"EqualStringConstantCondition", "EventInfoOpsCompoOperation", "HigherHighCondition", 
		"HigherLowCondition", "HistoricalData", "InfConstantCondition", "InfDoubleMapCondition", 
		"LENIENT", "LINE_COMMENT", "LinearFlatTrendsCondition", "LinearOppositeTrendsCondition", 
		"LinearSimilarTrendsCondition", "LowerHighCondition", "LowerLowCondition", 
		"MATCHES", "MatchingMapCondition", "NOT", "NotDoubleMapCondition", "NullCondition", 
		"Number", "NumberToken", "OPENPARENTEHSIS", "OR", "Operation", "OperationOutput", 
		"OrDoubleMapCondition", "PERCENT", "ReverseCondition", "StockOperation", 
		"String", "StringOperation", "StringToken", "SupConstantCondition", "SupDoubleMapCondition", 
		"SupportBreakDown", "SupportBreakUp", "Tcheat", "UpRatioCondition", "WS", 
		"WhiteChar", "','", "'NaN'", "'['", "']'", "'also display'", "'bearish'", 
		"'bullish'", "'crosses down historical'", "'crosses down threshold'", 
		"'crosses up historical'", "'crosses up threshold'", "'direction'", "'ending within'", 
		"'epsilon'", "'equals historical'", "'equals threshold'", "'equals trend'", 
		"'for'", "'goes down more than'", "'goes up more than'", "'is above historical'", 
		"'is above threshold'", "'is bearish if not bullish'", "'is bearish when'", 
		"'is below historical'", "'is below threshold'", "'is bullish when'", 
		"'makes a higher high spanning'", "'makes a higher low spanning'", "'makes a lower high spanning'", 
		"'makes a lower low spanning'", "'makes a support break down spanning'", 
		"'makes a support break up spanning'", "'more than'", "'over'", "'override start shift with'", 
		"'reverses down'", "'reverses up'", "'slope within'", "'smoothed'", "'spanning'", 
		"'starting within'", "'tolerance'", "'trends flat'", "'trends like'", 
		"'trends unlike'"
	};
	public static final int EOF=-1;
	public static final int T__57=57;
	public static final int T__58=58;
	public static final int T__59=59;
	public static final int T__60=60;
	public static final int T__61=61;
	public static final int T__62=62;
	public static final int T__63=63;
	public static final int T__64=64;
	public static final int T__65=65;
	public static final int T__66=66;
	public static final int T__67=67;
	public static final int T__68=68;
	public static final int T__69=69;
	public static final int T__70=70;
	public static final int T__71=71;
	public static final int T__72=72;
	public static final int T__73=73;
	public static final int T__74=74;
	public static final int T__75=75;
	public static final int T__76=76;
	public static final int T__77=77;
	public static final int T__78=78;
	public static final int T__79=79;
	public static final int T__80=80;
	public static final int T__81=81;
	public static final int T__82=82;
	public static final int T__83=83;
	public static final int T__84=84;
	public static final int T__85=85;
	public static final int T__86=86;
	public static final int T__87=87;
	public static final int T__88=88;
	public static final int T__89=89;
	public static final int T__90=90;
	public static final int T__91=91;
	public static final int T__92=92;
	public static final int T__93=93;
	public static final int T__94=94;
	public static final int T__95=95;
	public static final int T__96=96;
	public static final int T__97=97;
	public static final int T__98=98;
	public static final int T__99=99;
	public static final int T__100=100;
	public static final int T__101=101;
	public static final int T__102=102;
	public static final int AND=4;
	public static final int AndDoubleMapCondition=5;
	public static final int CLOSEPARENTEHSIS=6;
	public static final int COMMA=7;
	public static final int COMMENT=8;
	public static final int CrossDownConstantCondition=9;
	public static final int CrossDownDoubleMapCondition=10;
	public static final int CrossUpConstantCondition=11;
	public static final int CrossUpDoubleMapCondition=12;
	public static final int DAYS=13;
	public static final int DownRatioCondition=14;
	public static final int EqualConstantCondition=15;
	public static final int EqualDoubleMapCondition=16;
	public static final int EqualStringConstantCondition=17;
	public static final int EventInfoOpsCompoOperation=18;
	public static final int HigherHighCondition=19;
	public static final int HigherLowCondition=20;
	public static final int HistoricalData=21;
	public static final int InfConstantCondition=22;
	public static final int InfDoubleMapCondition=23;
	public static final int LENIENT=24;
	public static final int LINE_COMMENT=25;
	public static final int LinearFlatTrendsCondition=26;
	public static final int LinearOppositeTrendsCondition=27;
	public static final int LinearSimilarTrendsCondition=28;
	public static final int LowerHighCondition=29;
	public static final int LowerLowCondition=30;
	public static final int MATCHES=31;
	public static final int MatchingMapCondition=32;
	public static final int NOT=33;
	public static final int NotDoubleMapCondition=34;
	public static final int NullCondition=35;
	public static final int Number=36;
	public static final int NumberToken=37;
	public static final int OPENPARENTEHSIS=38;
	public static final int OR=39;
	public static final int Operation=40;
	public static final int OperationOutput=41;
	public static final int OrDoubleMapCondition=42;
	public static final int PERCENT=43;
	public static final int ReverseCondition=44;
	public static final int StockOperation=45;
	public static final int String=46;
	public static final int StringOperation=47;
	public static final int StringToken=48;
	public static final int SupConstantCondition=49;
	public static final int SupDoubleMapCondition=50;
	public static final int SupportBreakDown=51;
	public static final int SupportBreakUp=52;
	public static final int Tcheat=53;
	public static final int UpRatioCondition=54;
	public static final int WS=55;
	public static final int WhiteChar=56;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public ParameterizedIndicatorsParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public ParameterizedIndicatorsParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return ParameterizedIndicatorsParser.tokenNames; }
	@Override public String getGrammarFileName() { return "com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g"; }



	    private MyErrorReporter errorReporter;
	    private IndsParserDelegate parserDelegate;

	    public void setParserDelegate(IndsParserDelegate parserDelegate) {
	        this.parserDelegate = parserDelegate;
	    }
	    
	    public IndsParserDelegate getParserDelegate() {
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
	      
	    private void  checkParenthesis(String type)  throws UnfinishedNestedCondition {
	            parserDelegate.checkParenthesis(type);
	    }
	    
	    private void checkOperationValidity(Token opName) throws InvalidOperationException {
	             parserDelegate.checkOperationValidity(opName);
	    }


	public static class complete_expression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "complete_expression"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:153:1: complete_expression : bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation ) ;
	public final ParameterizedIndicatorsParser.complete_expression_return complete_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.complete_expression_return retval = new ParameterizedIndicatorsParser.complete_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope bcond =null;
		ParserRuleReturnScope bearish_condition1 =null;
		ParserRuleReturnScope also_display2 =null;
		ParserRuleReturnScope fixed_start_shift3 =null;

		RewriteRuleSubtreeStream stream_also_display=new RewriteRuleSubtreeStream(adaptor,"rule also_display");
		RewriteRuleSubtreeStream stream_bullish_condition=new RewriteRuleSubtreeStream(adaptor,"rule bullish_condition");
		RewriteRuleSubtreeStream stream_fixed_start_shift=new RewriteRuleSubtreeStream(adaptor,"rule fixed_start_shift");
		RewriteRuleSubtreeStream stream_bearish_condition=new RewriteRuleSubtreeStream(adaptor,"rule bearish_condition");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:153:21: (bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:4: bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift
			{
			pushFollow(FOLLOW_bullish_condition_in_complete_expression388);
			bcond=bullish_condition();
			state._fsp--;

			stream_bullish_condition.add(bcond.getTree());
			pushFollow(FOLLOW_bearish_condition_in_complete_expression390);
			bearish_condition1=bearish_condition((bcond!=null?((CommonTree)bcond.getTree()):null));
			state._fsp--;

			stream_bearish_condition.add(bearish_condition1.getTree());
			pushFollow(FOLLOW_also_display_in_complete_expression393);
			also_display2=also_display();
			state._fsp--;

			stream_also_display.add(also_display2.getTree());
			pushFollow(FOLLOW_fixed_start_shift_in_complete_expression395);
			fixed_start_shift3=fixed_start_shift();
			state._fsp--;

			stream_fixed_start_shift.add(fixed_start_shift3.getTree());
			// AST REWRITE
			// elements: fixed_start_shift, bullish_condition, also_display, bearish_condition
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 155:90: -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:93: ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EventInfoOpsCompoOperation, "EventInfoOpsCompoOperation"), root_1);
				adaptor.addChild(root_1, stream_bullish_condition.nextTree());
				adaptor.addChild(root_1, stream_bearish_condition.nextTree());
				adaptor.addChild(root_1, stream_also_display.nextTree());
				adaptor.addChild(root_1, stream_fixed_start_shift.nextTree());
				adaptor.addChild(root_1, (CommonTree)adaptor.create(StringOperation, "StringOperation"));
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
	// $ANTLR end "complete_expression"


	public static class bullish_condition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "bullish_condition"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:158:1: bullish_condition : 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression ;
	public final ParameterizedIndicatorsParser.bullish_condition_return bullish_condition() throws RecognitionException {
		ParameterizedIndicatorsParser.bullish_condition_return retval = new ParameterizedIndicatorsParser.bullish_condition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal4=null;
		Token WhiteChar5=null;
		Token WhiteChar7=null;
		Token COMMA8=null;
		Token WhiteChar9=null;
		ParserRuleReturnScope primary_expression6 =null;

		CommonTree string_literal4_tree=null;
		CommonTree WhiteChar5_tree=null;
		CommonTree WhiteChar7_tree=null;
		CommonTree COMMA8_tree=null;
		CommonTree WhiteChar9_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:158:19: ( 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:2: 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )*
			{
			string_literal4=(Token)match(input,83,FOLLOW_83_in_bullish_condition424);  
			stream_83.add(string_literal4);

			WhiteChar5=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition426);  
			stream_WhiteChar.add(WhiteChar5);

			pushFollow(FOLLOW_primary_expression_in_bullish_condition428);
			primary_expression6=primary_expression();
			state._fsp--;

			stream_primary_expression.add(primary_expression6.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:49: ( WhiteChar )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==WhiteChar) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:49: WhiteChar
					{
					WhiteChar7=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition430);  
					stream_WhiteChar.add(WhiteChar7);

					}
					break;

				default :
					break loop1;
				}
			}

			COMMA8=(Token)match(input,COMMA,FOLLOW_COMMA_in_bullish_condition433);  
			stream_COMMA.add(COMMA8);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:66: ( WhiteChar )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==WhiteChar) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:66: WhiteChar
					{
					WhiteChar9=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition435);  
					stream_WhiteChar.add(WhiteChar9);

					}
					break;

				default :
					break loop2;
				}
			}

			// AST REWRITE
			// elements: primary_expression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 159:77: -> primary_expression
			{
				adaptor.addChild(root_0, stream_primary_expression.nextTree());
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
	// $ANTLR end "bullish_condition"


	public static class bearish_condition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "bearish_condition"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:161:1: bearish_condition[CommonTree bcond] : ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* COMMA ( WhiteChar )* -> bearish_not_bullish );
	public final ParameterizedIndicatorsParser.bearish_condition_return bearish_condition(CommonTree bcond) throws RecognitionException {
		ParameterizedIndicatorsParser.bearish_condition_return retval = new ParameterizedIndicatorsParser.bearish_condition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal10=null;
		Token WhiteChar11=null;
		Token WhiteChar13=null;
		Token COMMA14=null;
		Token WhiteChar15=null;
		Token WhiteChar17=null;
		Token COMMA18=null;
		Token WhiteChar19=null;
		ParserRuleReturnScope primary_expression12 =null;
		ParserRuleReturnScope bearish_not_bullish16 =null;

		CommonTree string_literal10_tree=null;
		CommonTree WhiteChar11_tree=null;
		CommonTree WhiteChar13_tree=null;
		CommonTree COMMA14_tree=null;
		CommonTree WhiteChar15_tree=null;
		CommonTree WhiteChar17_tree=null;
		CommonTree COMMA18_tree=null;
		CommonTree WhiteChar19_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
		RewriteRuleSubtreeStream stream_bearish_not_bullish=new RewriteRuleSubtreeStream(adaptor,"rule bearish_not_bullish");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:161:37: ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* COMMA ( WhiteChar )* -> bearish_not_bullish )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==80) ) {
				alt7=1;
			}
			else if ( (LA7_0==79) ) {
				alt7=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:2: 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )*
					{
					string_literal10=(Token)match(input,80,FOLLOW_80_in_bearish_condition451);  
					stream_80.add(string_literal10);

					WhiteChar11=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition453);  
					stream_WhiteChar.add(WhiteChar11);

					pushFollow(FOLLOW_primary_expression_in_bearish_condition455);
					primary_expression12=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression12.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:49: ( WhiteChar )*
					loop3:
					while (true) {
						int alt3=2;
						int LA3_0 = input.LA(1);
						if ( (LA3_0==WhiteChar) ) {
							alt3=1;
						}

						switch (alt3) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:49: WhiteChar
							{
							WhiteChar13=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition457);  
							stream_WhiteChar.add(WhiteChar13);

							}
							break;

						default :
							break loop3;
						}
					}

					COMMA14=(Token)match(input,COMMA,FOLLOW_COMMA_in_bearish_condition460);  
					stream_COMMA.add(COMMA14);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:66: ( WhiteChar )*
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( (LA4_0==WhiteChar) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:66: WhiteChar
							{
							WhiteChar15=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition462);  
							stream_WhiteChar.add(WhiteChar15);

							}
							break;

						default :
							break loop4;
						}
					}

					// AST REWRITE
					// elements: primary_expression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 162:77: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:2: bearish_not_bullish[$bcond] ( WhiteChar )* COMMA ( WhiteChar )*
					{
					pushFollow(FOLLOW_bearish_not_bullish_in_bearish_condition472);
					bearish_not_bullish16=bearish_not_bullish(bcond);
					state._fsp--;

					stream_bearish_not_bullish.add(bearish_not_bullish16.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:30: ( WhiteChar )*
					loop5:
					while (true) {
						int alt5=2;
						int LA5_0 = input.LA(1);
						if ( (LA5_0==WhiteChar) ) {
							alt5=1;
						}

						switch (alt5) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:30: WhiteChar
							{
							WhiteChar17=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition475);  
							stream_WhiteChar.add(WhiteChar17);

							}
							break;

						default :
							break loop5;
						}
					}

					COMMA18=(Token)match(input,COMMA,FOLLOW_COMMA_in_bearish_condition478);  
					stream_COMMA.add(COMMA18);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:47: ( WhiteChar )*
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( (LA6_0==WhiteChar) ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:47: WhiteChar
							{
							WhiteChar19=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition480);  
							stream_WhiteChar.add(WhiteChar19);

							}
							break;

						default :
							break loop6;
						}
					}

					// AST REWRITE
					// elements: bearish_not_bullish
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 163:58: -> bearish_not_bullish
					{
						adaptor.addChild(root_0, stream_bearish_not_bullish.nextTree());
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
	// $ANTLR end "bearish_condition"


	public static class also_display_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "also_display"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:165:2: also_display : ( 'also display' WhiteChar primary_expression ( WhiteChar )* COMMA -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition );
	public final ParameterizedIndicatorsParser.also_display_return also_display() throws RecognitionException {
		ParameterizedIndicatorsParser.also_display_return retval = new ParameterizedIndicatorsParser.also_display_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal20=null;
		Token WhiteChar21=null;
		Token WhiteChar23=null;
		Token COMMA24=null;
		ParserRuleReturnScope primary_expression22 =null;

		CommonTree string_literal20_tree=null;
		CommonTree WhiteChar21_tree=null;
		CommonTree WhiteChar23_tree=null;
		CommonTree COMMA24_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:165:15: ( 'also display' WhiteChar primary_expression ( WhiteChar )* COMMA -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==61) ) {
				alt9=1;
			}
			else if ( (LA9_0==EOF||LA9_0==92) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:3: 'also display' WhiteChar primary_expression ( WhiteChar )* COMMA
					{
					string_literal20=(Token)match(input,61,FOLLOW_61_in_also_display497);  
					stream_61.add(string_literal20);

					WhiteChar21=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display499);  
					stream_WhiteChar.add(WhiteChar21);

					pushFollow(FOLLOW_primary_expression_in_also_display501);
					primary_expression22=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression22.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:47: ( WhiteChar )*
					loop8:
					while (true) {
						int alt8=2;
						int LA8_0 = input.LA(1);
						if ( (LA8_0==WhiteChar) ) {
							alt8=1;
						}

						switch (alt8) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:47: WhiteChar
							{
							WhiteChar23=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display503);  
							stream_WhiteChar.add(WhiteChar23);

							}
							break;

						default :
							break loop8;
						}
					}

					COMMA24=(Token)match(input,COMMA,FOLLOW_COMMA_in_also_display506);  
					stream_COMMA.add(COMMA24);

					// AST REWRITE
					// elements: primary_expression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 166:64: -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:67: ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:91: ^( String StringToken[\"\\\"TRUE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, stream_primary_expression.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:3: 
					{
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
					// 167:3: -> NullCondition
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(NullCondition, "NullCondition"));
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
	// $ANTLR end "also_display"


	public static class fixed_start_shift_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "fixed_start_shift"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:2: fixed_start_shift : ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS COMMA ->| -> ^( Number NumberToken[\"-1\"] ) );
	public final ParameterizedIndicatorsParser.fixed_start_shift_return fixed_start_shift() throws RecognitionException {
		ParameterizedIndicatorsParser.fixed_start_shift_return retval = new ParameterizedIndicatorsParser.fixed_start_shift_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal25=null;
		Token WhiteChar26=null;
		Token WhiteChar27=null;
		Token DAYS28=null;
		Token COMMA29=null;
		ParserRuleReturnScope fixedStartShift =null;

		CommonTree string_literal25_tree=null;
		CommonTree WhiteChar26_tree=null;
		CommonTree WhiteChar27_tree=null;
		CommonTree DAYS28_tree=null;
		CommonTree COMMA29_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:20: ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS COMMA ->| -> ^( Number NumberToken[\"-1\"] ) )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==92) ) {
				alt10=1;
			}
			else if ( (LA10_0==EOF) ) {
				alt10=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:3: 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS COMMA
					{
					string_literal25=(Token)match(input,92,FOLLOW_92_in_fixed_start_shift541);  
					stream_92.add(string_literal25);

					WhiteChar26=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift543);  
					stream_WhiteChar.add(WhiteChar26);

					pushFollow(FOLLOW_constant_in_fixed_start_shift547);
					fixedStartShift=constant();
					state._fsp--;

					stream_constant.add(fixedStartShift.getTree());
					WhiteChar27=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift549);  
					stream_WhiteChar.add(WhiteChar27);

					DAYS28=(Token)match(input,DAYS,FOLLOW_DAYS_in_fixed_start_shift551);  
					stream_DAYS.add(DAYS28);

					COMMA29=(Token)match(input,COMMA,FOLLOW_COMMA_in_fixed_start_shift553);  
					stream_COMMA.add(COMMA29);

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
					// 170:87: ->
					{
						adaptor.addChild(root_0, (fixedStartShift!=null?((CommonTree)fixedStartShift.getTree()):null));
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:171:3: 
					{
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
					// 171:3: -> ^( Number NumberToken[\"-1\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:171:6: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_1);
						adaptor.addChild(root_1, (CommonTree)adaptor.create(NumberToken, "-1"));
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
	// $ANTLR end "fixed_start_shift"


	public static class bearish_not_bullish_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "bearish_not_bullish"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:174:1: bearish_not_bullish[CommonTree bcond] : 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) ) ;
	public final ParameterizedIndicatorsParser.bearish_not_bullish_return bearish_not_bullish(CommonTree bcond) throws RecognitionException {
		ParameterizedIndicatorsParser.bearish_not_bullish_return retval = new ParameterizedIndicatorsParser.bearish_not_bullish_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal30=null;
		Token WhiteChar31=null;
		Token AND32=null;
		Token WhiteChar33=null;
		Token WhiteChar35=null;
		Token OR36=null;
		Token WhiteChar37=null;
		ParserRuleReturnScope primary_expression34 =null;
		ParserRuleReturnScope primary_expression38 =null;

		CommonTree string_literal30_tree=null;
		CommonTree WhiteChar31_tree=null;
		CommonTree AND32_tree=null;
		CommonTree WhiteChar33_tree=null;
		CommonTree WhiteChar35_tree=null;
		CommonTree OR36_tree=null;
		CommonTree WhiteChar37_tree=null;
		RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
		RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:174:39: ( 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:175:2: 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) )
			{
			string_literal30=(Token)match(input,79,FOLLOW_79_in_bearish_not_bullish583);  
			stream_79.add(string_literal30);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:3: ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) )
			int alt11=3;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==WhiteChar) ) {
				switch ( input.LA(2) ) {
				case AND:
					{
					alt11=1;
					}
					break;
				case OR:
					{
					alt11=2;
					}
					break;
				case COMMA:
				case WhiteChar:
					{
					alt11=3;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 11, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}
			else if ( (LA11_0==COMMA) ) {
				alt11=3;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}

			switch (alt11) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:177:3: WhiteChar AND WhiteChar primary_expression
					{
					WhiteChar31=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish592);  
					stream_WhiteChar.add(WhiteChar31);

					AND32=(Token)match(input,AND,FOLLOW_AND_in_bearish_not_bullish594);  
					stream_AND.add(AND32);

					WhiteChar33=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish596);  
					stream_WhiteChar.add(WhiteChar33);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish598);
					primary_expression34=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression34.getTree());
					// AST REWRITE
					// elements: primary_expression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 177:46: -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:177:49: ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:177:73: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:177:108: ^( NotDoubleMapCondition )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotDoubleMapCondition, "NotDoubleMapCondition"), root_2);
						adaptor.addChild(root_2, bcond);
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, stream_primary_expression.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:178:3: WhiteChar OR WhiteChar primary_expression
					{
					WhiteChar35=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish624);  
					stream_WhiteChar.add(WhiteChar35);

					OR36=(Token)match(input,OR,FOLLOW_OR_in_bearish_not_bullish626);  
					stream_OR.add(OR36);

					WhiteChar37=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish628);  
					stream_WhiteChar.add(WhiteChar37);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish630);
					primary_expression38=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression38.getTree());
					// AST REWRITE
					// elements: primary_expression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 178:45: -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:178:48: ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrDoubleMapCondition, "OrDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:178:71: ^( NotDoubleMapCondition )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotDoubleMapCondition, "NotDoubleMapCondition"), root_2);
						adaptor.addChild(root_2, bcond);
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, stream_primary_expression.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:3: 
					{
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
					// 179:3: -> ^( NotDoubleMapCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:6: ^( NotDoubleMapCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotDoubleMapCondition, "NotDoubleMapCondition"), root_1);
						adaptor.addChild(root_1, bcond);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}

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
	// $ANTLR end "bearish_not_bullish"


	public static class primary_expression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "primary_expression"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:1: primary_expression : and_expression ;
	public final ParameterizedIndicatorsParser.primary_expression_return primary_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.primary_expression_return retval = new ParameterizedIndicatorsParser.primary_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope and_expression39 =null;


		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:20: ( and_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:2: and_expression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_and_expression_in_primary_expression671);
			and_expression39=and_expression();
			state._fsp--;

			adaptor.addChild(root_0, and_expression39.getTree());

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
	// $ANTLR end "primary_expression"


	public static class and_expression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "and_expression"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:1: and_expression : or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndDoubleMapCondition or_expression ( or_expression )* ) ;
	public final ParameterizedIndicatorsParser.and_expression_return and_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.and_expression_return retval = new ParameterizedIndicatorsParser.and_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar41=null;
		Token AND42=null;
		Token WhiteChar43=null;
		ParserRuleReturnScope lenientParam =null;
		ParserRuleReturnScope or_expression40 =null;
		ParserRuleReturnScope or_expression44 =null;

		CommonTree WhiteChar41_tree=null;
		CommonTree AND42_tree=null;
		CommonTree WhiteChar43_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
		RewriteRuleSubtreeStream stream_or_expression=new RewriteRuleSubtreeStream(adaptor,"rule or_expression");
		RewriteRuleSubtreeStream stream_lenient=new RewriteRuleSubtreeStream(adaptor,"rule lenient");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:16: ( or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndDoubleMapCondition or_expression ( or_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:3: or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )*
			{
			pushFollow(FOLLOW_or_expression_in_and_expression682);
			or_expression40=or_expression();
			state._fsp--;

			stream_or_expression.add(or_expression40.getTree());
			pushFollow(FOLLOW_lenient_in_and_expression686);
			lenientParam=lenient();
			state._fsp--;

			stream_lenient.add(lenientParam.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:38: ( WhiteChar AND WhiteChar or_expression )*
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( (LA12_0==WhiteChar) ) {
					int LA12_1 = input.LA(2);
					if ( (LA12_1==AND) ) {
						alt12=1;
					}

				}

				switch (alt12) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:39: WhiteChar AND WhiteChar or_expression
					{
					WhiteChar41=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression689);  
					stream_WhiteChar.add(WhiteChar41);

					AND42=(Token)match(input,AND,FOLLOW_AND_in_and_expression691);  
					stream_AND.add(AND42);

					WhiteChar43=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression693);  
					stream_WhiteChar.add(WhiteChar43);

					pushFollow(FOLLOW_or_expression_in_and_expression695);
					or_expression44=or_expression();
					state._fsp--;

					stream_or_expression.add(or_expression44.getTree());
					}
					break;

				default :
					break loop12;
				}
			}

			// AST REWRITE
			// elements: or_expression, or_expression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 188:79: -> ^( AndDoubleMapCondition or_expression ( or_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:82: ^( AndDoubleMapCondition or_expression ( or_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
				adaptor.addChild(root_1, (lenientParam!=null?((CommonTree)lenientParam.getTree()):null));
				adaptor.addChild(root_1, stream_or_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:141: ( or_expression )*
				while ( stream_or_expression.hasNext() ) {
					adaptor.addChild(root_1, stream_or_expression.nextTree());
				}
				stream_or_expression.reset();

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
	// $ANTLR end "and_expression"


	public static class or_expression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "or_expression"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:1: or_expression : matches_expression ( WhiteChar OR WhiteChar matches_expression )* -> ^( OrDoubleMapCondition matches_expression ( matches_expression )* ) ;
	public final ParameterizedIndicatorsParser.or_expression_return or_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.or_expression_return retval = new ParameterizedIndicatorsParser.or_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar46=null;
		Token OR47=null;
		Token WhiteChar48=null;
		ParserRuleReturnScope matches_expression45 =null;
		ParserRuleReturnScope matches_expression49 =null;

		CommonTree WhiteChar46_tree=null;
		CommonTree OR47_tree=null;
		CommonTree WhiteChar48_tree=null;
		RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_matches_expression=new RewriteRuleSubtreeStream(adaptor,"rule matches_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:15: ( matches_expression ( WhiteChar OR WhiteChar matches_expression )* -> ^( OrDoubleMapCondition matches_expression ( matches_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:3: matches_expression ( WhiteChar OR WhiteChar matches_expression )*
			{
			pushFollow(FOLLOW_matches_expression_in_or_expression722);
			matches_expression45=matches_expression();
			state._fsp--;

			stream_matches_expression.add(matches_expression45.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:22: ( WhiteChar OR WhiteChar matches_expression )*
			loop13:
			while (true) {
				int alt13=2;
				int LA13_0 = input.LA(1);
				if ( (LA13_0==WhiteChar) ) {
					int LA13_1 = input.LA(2);
					if ( (LA13_1==OR) ) {
						alt13=1;
					}

				}

				switch (alt13) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:23: WhiteChar OR WhiteChar matches_expression
					{
					WhiteChar46=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression725);  
					stream_WhiteChar.add(WhiteChar46);

					OR47=(Token)match(input,OR,FOLLOW_OR_in_or_expression727);  
					stream_OR.add(OR47);

					WhiteChar48=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression729);  
					stream_WhiteChar.add(WhiteChar48);

					pushFollow(FOLLOW_matches_expression_in_or_expression731);
					matches_expression49=matches_expression();
					state._fsp--;

					stream_matches_expression.add(matches_expression49.getTree());
					}
					break;

				default :
					break loop13;
				}
			}

			// AST REWRITE
			// elements: matches_expression, matches_expression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 191:67: -> ^( OrDoubleMapCondition matches_expression ( matches_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:70: ^( OrDoubleMapCondition matches_expression ( matches_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrDoubleMapCondition, "OrDoubleMapCondition"), root_1);
				adaptor.addChild(root_1, stream_matches_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:112: ( matches_expression )*
				while ( stream_matches_expression.hasNext() ) {
					adaptor.addChild(root_1, stream_matches_expression.nextTree());
				}
				stream_matches_expression.reset();

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
	// $ANTLR end "or_expression"


	public static class matches_expression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "matches_expression"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:1: matches_expression : atom ( WhiteChar MATCHES WhiteChar atom )* -> ^( MatchingMapCondition atom ( atom )* ) ;
	public final ParameterizedIndicatorsParser.matches_expression_return matches_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.matches_expression_return retval = new ParameterizedIndicatorsParser.matches_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar51=null;
		Token MATCHES52=null;
		Token WhiteChar53=null;
		ParserRuleReturnScope atom50 =null;
		ParserRuleReturnScope atom54 =null;

		CommonTree WhiteChar51_tree=null;
		CommonTree MATCHES52_tree=null;
		CommonTree WhiteChar53_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_MATCHES=new RewriteRuleTokenStream(adaptor,"token MATCHES");
		RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:20: ( atom ( WhiteChar MATCHES WhiteChar atom )* -> ^( MatchingMapCondition atom ( atom )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:3: atom ( WhiteChar MATCHES WhiteChar atom )*
			{
			pushFollow(FOLLOW_atom_in_matches_expression756);
			atom50=atom();
			state._fsp--;

			stream_atom.add(atom50.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:8: ( WhiteChar MATCHES WhiteChar atom )*
			loop14:
			while (true) {
				int alt14=2;
				int LA14_0 = input.LA(1);
				if ( (LA14_0==WhiteChar) ) {
					int LA14_1 = input.LA(2);
					if ( (LA14_1==MATCHES) ) {
						alt14=1;
					}

				}

				switch (alt14) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:9: WhiteChar MATCHES WhiteChar atom
					{
					WhiteChar51=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression759);  
					stream_WhiteChar.add(WhiteChar51);

					MATCHES52=(Token)match(input,MATCHES,FOLLOW_MATCHES_in_matches_expression761);  
					stream_MATCHES.add(MATCHES52);

					WhiteChar53=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression763);  
					stream_WhiteChar.add(WhiteChar53);

					pushFollow(FOLLOW_atom_in_matches_expression765);
					atom54=atom();
					state._fsp--;

					stream_atom.add(atom54.getTree());
					}
					break;

				default :
					break loop14;
				}
			}

			// AST REWRITE
			// elements: atom, atom
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 194:44: -> ^( MatchingMapCondition atom ( atom )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:47: ^( MatchingMapCondition atom ( atom )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(MatchingMapCondition, "MatchingMapCondition"), root_1);
				adaptor.addChild(root_1, stream_atom.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:75: ( atom )*
				while ( stream_atom.hasNext() ) {
					adaptor.addChild(root_1, stream_atom.nextTree());
				}
				stream_atom.reset();

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
	// $ANTLR end "matches_expression"


	public static class atom_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "atom"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:1: atom : ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotDoubleMapCondition primary_expression ) );
	public final ParameterizedIndicatorsParser.atom_return atom() throws RecognitionException {
		ParameterizedIndicatorsParser.atom_return retval = new ParameterizedIndicatorsParser.atom_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal56=null;
		Token WhiteChar57=null;
		Token WhiteChar59=null;
		Token char_literal60=null;
		Token string_literal61=null;
		Token WhiteChar62=null;
		Token char_literal63=null;
		Token WhiteChar64=null;
		Token WhiteChar66=null;
		Token char_literal67=null;
		ParserRuleReturnScope booleanhistory55 =null;
		ParserRuleReturnScope primary_expression58 =null;
		ParserRuleReturnScope primary_expression65 =null;

		CommonTree char_literal56_tree=null;
		CommonTree WhiteChar57_tree=null;
		CommonTree WhiteChar59_tree=null;
		CommonTree char_literal60_tree=null;
		CommonTree string_literal61_tree=null;
		CommonTree WhiteChar62_tree=null;
		CommonTree char_literal63_tree=null;
		CommonTree WhiteChar64_tree=null;
		CommonTree WhiteChar66_tree=null;
		CommonTree char_literal67_tree=null;
		RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_CLOSEPARENTEHSIS=new RewriteRuleTokenStream(adaptor,"token CLOSEPARENTEHSIS");
		RewriteRuleTokenStream stream_OPENPARENTEHSIS=new RewriteRuleTokenStream(adaptor,"token OPENPARENTEHSIS");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:6: ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotDoubleMapCondition primary_expression ) )
			int alt20=3;
			switch ( input.LA(1) ) {
			case HistoricalData:
			case Operation:
				{
				alt20=1;
				}
				break;
			case OPENPARENTEHSIS:
				{
				alt20=2;
				}
				break;
			case NOT:
				{
				alt20=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 20, 0, input);
				throw nvae;
			}
			switch (alt20) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:3: booleanhistory
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_booleanhistory_in_atom791);
					booleanhistory55=booleanhistory();
					state._fsp--;

					adaptor.addChild(root_0, booleanhistory55.getTree());

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:3: '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					char_literal56=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom797);  
					stream_OPENPARENTEHSIS.add(char_literal56);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:7: ( WhiteChar )*
					loop15:
					while (true) {
						int alt15=2;
						int LA15_0 = input.LA(1);
						if ( (LA15_0==WhiteChar) ) {
							alt15=1;
						}

						switch (alt15) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:7: WhiteChar
							{
							WhiteChar57=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom799);  
							stream_WhiteChar.add(WhiteChar57);

							}
							break;

						default :
							break loop15;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom802);
					primary_expression58=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression58.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:37: ( WhiteChar )*
					loop16:
					while (true) {
						int alt16=2;
						int LA16_0 = input.LA(1);
						if ( (LA16_0==WhiteChar) ) {
							alt16=1;
						}

						switch (alt16) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:37: WhiteChar
							{
							WhiteChar59=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom804);  
							stream_WhiteChar.add(WhiteChar59);

							}
							break;

						default :
							break loop16;
						}
					}

					char_literal60=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom807);  
					stream_CLOSEPARENTEHSIS.add(char_literal60);

					// AST REWRITE
					// elements: primary_expression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 198:52: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:3: 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					string_literal61=(Token)match(input,NOT,FOLLOW_NOT_in_atom817);  
					stream_NOT.add(string_literal61);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:9: ( WhiteChar )*
					loop17:
					while (true) {
						int alt17=2;
						int LA17_0 = input.LA(1);
						if ( (LA17_0==WhiteChar) ) {
							alt17=1;
						}

						switch (alt17) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:9: WhiteChar
							{
							WhiteChar62=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom819);  
							stream_WhiteChar.add(WhiteChar62);

							}
							break;

						default :
							break loop17;
						}
					}

					char_literal63=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom822);  
					stream_OPENPARENTEHSIS.add(char_literal63);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:24: ( WhiteChar )*
					loop18:
					while (true) {
						int alt18=2;
						int LA18_0 = input.LA(1);
						if ( (LA18_0==WhiteChar) ) {
							alt18=1;
						}

						switch (alt18) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:24: WhiteChar
							{
							WhiteChar64=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom824);  
							stream_WhiteChar.add(WhiteChar64);

							}
							break;

						default :
							break loop18;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom827);
					primary_expression65=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression65.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:54: ( WhiteChar )*
					loop19:
					while (true) {
						int alt19=2;
						int LA19_0 = input.LA(1);
						if ( (LA19_0==WhiteChar) ) {
							alt19=1;
						}

						switch (alt19) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:54: WhiteChar
							{
							WhiteChar66=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom829);  
							stream_WhiteChar.add(WhiteChar66);

							}
							break;

						default :
							break loop19;
						}
					}

					char_literal67=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom832);  
					stream_CLOSEPARENTEHSIS.add(char_literal67);

					// AST REWRITE
					// elements: primary_expression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 199:69: -> ^( NotDoubleMapCondition primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:72: ^( NotDoubleMapCondition primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotDoubleMapCondition, "NotDoubleMapCondition"), root_1);
						adaptor.addChild(root_1, stream_primary_expression.nextTree());
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
	// $ANTLR end "atom"


	public static class booleanhistory_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "booleanhistory"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:1: booleanhistory : firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) ;
	public final ParameterizedIndicatorsParser.booleanhistory_return booleanhistory() throws RecognitionException {
		ParameterizedIndicatorsParser.booleanhistory_return retval = new ParameterizedIndicatorsParser.booleanhistory_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar68=null;
		ParserRuleReturnScope firstOp =null;
		ParserRuleReturnScope presetcondition69 =null;
		ParserRuleReturnScope opcmpcondition70 =null;
		ParserRuleReturnScope constantcmp71 =null;

		CommonTree WhiteChar68_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_presetcondition=new RewriteRuleSubtreeStream(adaptor,"rule presetcondition");
		RewriteRuleSubtreeStream stream_constantcmp=new RewriteRuleSubtreeStream(adaptor,"rule constantcmp");
		RewriteRuleSubtreeStream stream_opcmpcondition=new RewriteRuleSubtreeStream(adaptor,"rule opcmpcondition");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:16: (firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:2: firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			{
			pushFollow(FOLLOW_operand_in_booleanhistory855);
			firstOp=operand();
			state._fsp--;

			stream_operand.add(firstOp.getTree());
			WhiteChar68=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_booleanhistory857);  
			stream_WhiteChar.add(WhiteChar68);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:28: ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			int alt21=3;
			switch ( input.LA(1) ) {
			case 65:
			case 67:
			case 75:
			case 76:
			case 84:
			case 85:
			case 86:
			case 87:
			case 88:
			case 89:
			case 93:
			case 94:
			case 100:
				{
				alt21=1;
				}
				break;
			case 64:
			case 66:
			case 71:
			case 77:
			case 81:
			case 101:
			case 102:
				{
				alt21=2;
				}
				break;
			case 72:
			case 73:
			case 78:
			case 82:
				{
				alt21=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 21, 0, input);
				throw nvae;
			}
			switch (alt21) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:3: presetcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_presetcondition_in_booleanhistory863);
					presetcondition69=presetcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_presetcondition.add(presetcondition69.getTree());
					// AST REWRITE
					// elements: presetcondition
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 205:34: -> presetcondition
					{
						adaptor.addChild(root_0, stream_presetcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:3: opcmpcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_opcmpcondition_in_booleanhistory874);
					opcmpcondition70=opcmpcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_opcmpcondition.add(opcmpcondition70.getTree());
					// AST REWRITE
					// elements: opcmpcondition
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 206:33: -> opcmpcondition
					{
						adaptor.addChild(root_0, stream_opcmpcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:3: constantcmp[$firstOp.tree]
					{
					pushFollow(FOLLOW_constantcmp_in_booleanhistory885);
					constantcmp71=constantcmp((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_constantcmp.add(constantcmp71.getTree());
					// AST REWRITE
					// elements: constantcmp
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 207:30: -> constantcmp
					{
						adaptor.addChild(root_0, stream_constantcmp.nextTree());
					}


					retval.tree = root_0;

					}
					break;

			}

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
	// $ANTLR end "booleanhistory"


	public static class operand_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "operand"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:1: operand : ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation );
	public final ParameterizedIndicatorsParser.operand_return operand() throws RecognitionException {
		ParameterizedIndicatorsParser.operand_return retval = new ParameterizedIndicatorsParser.operand_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token opName=null;
		Token HistoricalData72=null;

		CommonTree opName_tree=null;
		CommonTree HistoricalData72_tree=null;
		RewriteRuleTokenStream stream_Operation=new RewriteRuleTokenStream(adaptor,"token Operation");
		RewriteRuleTokenStream stream_HistoricalData=new RewriteRuleTokenStream(adaptor,"token HistoricalData");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:9: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation )
			int alt22=2;
			int LA22_0 = input.LA(1);
			if ( (LA22_0==HistoricalData) ) {
				alt22=1;
			}
			else if ( (LA22_0==Operation) ) {
				alt22=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}

			switch (alt22) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:11: HistoricalData
					{
					HistoricalData72=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_operand900);  
					stream_HistoricalData.add(HistoricalData72);

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
					// 209:26: -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:29: ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:46: ^( OperationOutput HistoricalData )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
						adaptor.addChild(root_2, stream_HistoricalData.nextNode());
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:80: ^( String StringToken[\"\\\"THIS\\\"\"] )
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
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:117: opName= Operation
					{
					opName=(Token)match(input,Operation,FOLLOW_Operation_in_operand927);  
					stream_Operation.add(opName);

					checkOperationValidity(opName);
					// AST REWRITE
					// elements: Operation
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 209:171: -> Operation
					{
						adaptor.addChild(root_0, stream_Operation.nextNode());
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
	// $ANTLR end "operand"


	public static class constant_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "constant"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:1: constant : ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) );
	public final ParameterizedIndicatorsParser.constant_return constant() throws RecognitionException {
		ParameterizedIndicatorsParser.constant_return retval = new ParameterizedIndicatorsParser.constant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NumberToken73=null;
		Token string_literal74=null;

		CommonTree NumberToken73_tree=null;
		CommonTree string_literal74_tree=null;
		RewriteRuleTokenStream stream_58=new RewriteRuleTokenStream(adaptor,"token 58");
		RewriteRuleTokenStream stream_NumberToken=new RewriteRuleTokenStream(adaptor,"token NumberToken");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:10: ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) )
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( (LA23_0==NumberToken) ) {
				alt23=1;
			}
			else if ( (LA23_0==58) ) {
				alt23=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}

			switch (alt23) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:13: NumberToken
					{
					NumberToken73=(Token)match(input,NumberToken,FOLLOW_NumberToken_in_constant941);  
					stream_NumberToken.add(NumberToken73);

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
					// 210:25: -> ^( Number NumberToken )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:28: ^( Number NumberToken )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:52: 'NaN'
					{
					string_literal74=(Token)match(input,58,FOLLOW_58_in_constant953);  
					stream_58.add(string_literal74);

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
					// 210:58: -> ^( Number NumberToken[\"NaN\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:61: ^( Number NumberToken[\"NaN\"] )
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
	// $ANTLR end "constant"


	public static class stringconstant_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "stringconstant"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:1: stringconstant : StringToken -> ^( String StringToken ) ;
	public final ParameterizedIndicatorsParser.stringconstant_return stringconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.stringconstant_return retval = new ParameterizedIndicatorsParser.stringconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token StringToken75=null;

		CommonTree StringToken75_tree=null;
		RewriteRuleTokenStream stream_StringToken=new RewriteRuleTokenStream(adaptor,"token StringToken");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:16: ( StringToken -> ^( String StringToken ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:18: StringToken
			{
			StringToken75=(Token)match(input,StringToken,FOLLOW_StringToken_in_stringconstant969);  
			stream_StringToken.add(StringToken75);

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
			// 211:30: -> ^( String StringToken )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:33: ^( String StringToken )
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
	// $ANTLR end "stringconstant"


	public static class trendconstant_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "trendconstant"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:1: trendconstant : ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) );
	public final ParameterizedIndicatorsParser.trendconstant_return trendconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.trendconstant_return retval = new ParameterizedIndicatorsParser.trendconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal76=null;
		Token string_literal77=null;

		CommonTree string_literal76_tree=null;
		CommonTree string_literal77_tree=null;
		RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
		RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:15: ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) )
			int alt24=2;
			int LA24_0 = input.LA(1);
			if ( (LA24_0==63) ) {
				alt24=1;
			}
			else if ( (LA24_0==62) ) {
				alt24=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 24, 0, input);
				throw nvae;
			}

			switch (alt24) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:17: 'bullish'
					{
					string_literal76=(Token)match(input,63,FOLLOW_63_in_trendconstant984);  
					stream_63.add(string_literal76);

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
					// 212:27: -> ^( String StringToken[\"\\\"bullish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:30: ^( String StringToken[\"\\\"bullish\\\"\"] )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_1);
						adaptor.addChild(root_1, (CommonTree)adaptor.create(StringToken, "\"bullish\""));
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:69: 'bearish'
					{
					string_literal77=(Token)match(input,62,FOLLOW_62_in_trendconstant997);  
					stream_62.add(string_literal77);

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
					// 212:79: -> ^( String StringToken[\"\\\"bearish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:82: ^( String StringToken[\"\\\"bearish\\\"\"] )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_1);
						adaptor.addChild(root_1, (CommonTree)adaptor.create(StringToken, "\"bearish\""));
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
	// $ANTLR end "trendconstant"


	public static class lenient_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "lenient"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:1: lenient : ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ;
	public final ParameterizedIndicatorsParser.lenient_return lenient() throws RecognitionException {
		ParameterizedIndicatorsParser.lenient_return retval = new ParameterizedIndicatorsParser.lenient_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar78=null;
		Token LENIENT79=null;

		CommonTree WhiteChar78_tree=null;
		CommonTree LENIENT79_tree=null;
		RewriteRuleTokenStream stream_LENIENT=new RewriteRuleTokenStream(adaptor,"token LENIENT");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:9: ( ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			int alt25=2;
			int LA25_0 = input.LA(1);
			if ( (LA25_0==WhiteChar) ) {
				int LA25_1 = input.LA(2);
				if ( (LA25_1==LENIENT) ) {
					alt25=1;
				}
				else if ( (LA25_1==AND||(LA25_1 >= CLOSEPARENTEHSIS && LA25_1 <= COMMA)||LA25_1==WhiteChar) ) {
					alt25=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 25, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( ((LA25_0 >= CLOSEPARENTEHSIS && LA25_0 <= COMMA)) ) {
				alt25=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 25, 0, input);
				throw nvae;
			}

			switch (alt25) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:12: WhiteChar LENIENT
					{
					WhiteChar78=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_lenient1014);  
					stream_WhiteChar.add(WhiteChar78);

					LENIENT79=(Token)match(input,LENIENT,FOLLOW_LENIENT_in_lenient1016);  
					stream_LENIENT.add(LENIENT79);

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
					// 213:30: -> ^( String StringToken[\"\\\"TRUE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:33: ^( String StringToken[\"\\\"TRUE\\\"\"] )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_1);
						adaptor.addChild(root_1, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:69: 
					{
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
					// 213:69: -> ^( String StringToken[\"\\\"FALSE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:72: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_1);
						adaptor.addChild(root_1, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}

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
	// $ANTLR end "lenient"


	public static class opcmpcondition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "opcmpcondition"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:1: opcmpcondition[CommonTree firstOp] : ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) );
	public final ParameterizedIndicatorsParser.opcmpcondition_return opcmpcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.opcmpcondition_return retval = new ParameterizedIndicatorsParser.opcmpcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal80=null;
		Token WhiteChar81=null;
		Token WhiteChar82=null;
		Token string_literal83=null;
		Token WhiteChar84=null;
		Token WhiteChar85=null;
		Token DAYS86=null;
		Token string_literal87=null;
		Token WhiteChar88=null;
		Token WhiteChar89=null;
		Token string_literal90=null;
		Token WhiteChar91=null;
		Token WhiteChar92=null;
		Token DAYS93=null;
		Token string_literal94=null;
		Token WhiteChar95=null;
		Token WhiteChar96=null;
		Token string_literal97=null;
		Token WhiteChar98=null;
		Token WhiteChar99=null;
		Token DAYS100=null;
		Token string_literal101=null;
		Token WhiteChar102=null;
		Token WhiteChar104=null;
		Token string_literal105=null;
		Token WhiteChar106=null;
		Token WhiteChar107=null;
		Token DAYS108=null;
		Token WhiteChar109=null;
		Token string_literal110=null;
		Token WhiteChar111=null;
		Token WhiteChar112=null;
		Token DAYS113=null;
		Token string_literal114=null;
		Token WhiteChar115=null;
		Token WhiteChar117=null;
		Token string_literal118=null;
		Token WhiteChar119=null;
		Token WhiteChar120=null;
		Token DAYS121=null;
		Token WhiteChar122=null;
		Token string_literal123=null;
		Token WhiteChar124=null;
		Token WhiteChar125=null;
		Token DAYS126=null;
		Token string_literal127=null;
		Token WhiteChar128=null;
		Token WhiteChar129=null;
		Token string_literal130=null;
		Token WhiteChar131=null;
		Token WhiteChar132=null;
		Token DAYS133=null;
		Token WhiteChar134=null;
		Token string_literal135=null;
		Token WhiteChar136=null;
		Token WhiteChar137=null;
		Token DAYS138=null;
		Token WhiteChar139=null;
		Token string_literal140=null;
		Token WhiteChar141=null;
		Token WhiteChar142=null;
		Token string_literal143=null;
		Token WhiteChar144=null;
		Token string_literal145=null;
		Token WhiteChar146=null;
		Token WhiteChar147=null;
		Token string_literal148=null;
		Token WhiteChar149=null;
		Token WhiteChar150=null;
		Token DAYS151=null;
		Token WhiteChar152=null;
		Token string_literal153=null;
		Token WhiteChar154=null;
		Token WhiteChar155=null;
		Token DAYS156=null;
		Token WhiteChar157=null;
		Token string_literal158=null;
		Token WhiteChar159=null;
		ParserRuleReturnScope secondOp =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope spanningNbDays =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope direction =null;
		ParserRuleReturnScope epsilon =null;
		ParserRuleReturnScope operand103 =null;
		ParserRuleReturnScope operand116 =null;

		CommonTree string_literal80_tree=null;
		CommonTree WhiteChar81_tree=null;
		CommonTree WhiteChar82_tree=null;
		CommonTree string_literal83_tree=null;
		CommonTree WhiteChar84_tree=null;
		CommonTree WhiteChar85_tree=null;
		CommonTree DAYS86_tree=null;
		CommonTree string_literal87_tree=null;
		CommonTree WhiteChar88_tree=null;
		CommonTree WhiteChar89_tree=null;
		CommonTree string_literal90_tree=null;
		CommonTree WhiteChar91_tree=null;
		CommonTree WhiteChar92_tree=null;
		CommonTree DAYS93_tree=null;
		CommonTree string_literal94_tree=null;
		CommonTree WhiteChar95_tree=null;
		CommonTree WhiteChar96_tree=null;
		CommonTree string_literal97_tree=null;
		CommonTree WhiteChar98_tree=null;
		CommonTree WhiteChar99_tree=null;
		CommonTree DAYS100_tree=null;
		CommonTree string_literal101_tree=null;
		CommonTree WhiteChar102_tree=null;
		CommonTree WhiteChar104_tree=null;
		CommonTree string_literal105_tree=null;
		CommonTree WhiteChar106_tree=null;
		CommonTree WhiteChar107_tree=null;
		CommonTree DAYS108_tree=null;
		CommonTree WhiteChar109_tree=null;
		CommonTree string_literal110_tree=null;
		CommonTree WhiteChar111_tree=null;
		CommonTree WhiteChar112_tree=null;
		CommonTree DAYS113_tree=null;
		CommonTree string_literal114_tree=null;
		CommonTree WhiteChar115_tree=null;
		CommonTree WhiteChar117_tree=null;
		CommonTree string_literal118_tree=null;
		CommonTree WhiteChar119_tree=null;
		CommonTree WhiteChar120_tree=null;
		CommonTree DAYS121_tree=null;
		CommonTree WhiteChar122_tree=null;
		CommonTree string_literal123_tree=null;
		CommonTree WhiteChar124_tree=null;
		CommonTree WhiteChar125_tree=null;
		CommonTree DAYS126_tree=null;
		CommonTree string_literal127_tree=null;
		CommonTree WhiteChar128_tree=null;
		CommonTree WhiteChar129_tree=null;
		CommonTree string_literal130_tree=null;
		CommonTree WhiteChar131_tree=null;
		CommonTree WhiteChar132_tree=null;
		CommonTree DAYS133_tree=null;
		CommonTree WhiteChar134_tree=null;
		CommonTree string_literal135_tree=null;
		CommonTree WhiteChar136_tree=null;
		CommonTree WhiteChar137_tree=null;
		CommonTree DAYS138_tree=null;
		CommonTree WhiteChar139_tree=null;
		CommonTree string_literal140_tree=null;
		CommonTree WhiteChar141_tree=null;
		CommonTree WhiteChar142_tree=null;
		CommonTree string_literal143_tree=null;
		CommonTree WhiteChar144_tree=null;
		CommonTree string_literal145_tree=null;
		CommonTree WhiteChar146_tree=null;
		CommonTree WhiteChar147_tree=null;
		CommonTree string_literal148_tree=null;
		CommonTree WhiteChar149_tree=null;
		CommonTree WhiteChar150_tree=null;
		CommonTree DAYS151_tree=null;
		CommonTree WhiteChar152_tree=null;
		CommonTree string_literal153_tree=null;
		CommonTree WhiteChar154_tree=null;
		CommonTree WhiteChar155_tree=null;
		CommonTree DAYS156_tree=null;
		CommonTree WhiteChar157_tree=null;
		CommonTree string_literal158_tree=null;
		CommonTree WhiteChar159_tree=null;
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
		RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_101=new RewriteRuleTokenStream(adaptor,"token 101");
		RewriteRuleTokenStream stream_102=new RewriteRuleTokenStream(adaptor,"token 102");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_91=new RewriteRuleTokenStream(adaptor,"token 91");
		RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
		RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
		RewriteRuleTokenStream stream_97=new RewriteRuleTokenStream(adaptor,"token 97");
		RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_stringconstant=new RewriteRuleSubtreeStream(adaptor,"rule stringconstant");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:37: ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) )
			int alt31=7;
			switch ( input.LA(1) ) {
			case 77:
				{
				alt31=1;
				}
				break;
			case 81:
				{
				alt31=2;
				}
				break;
			case 71:
				{
				alt31=3;
				}
				break;
			case 64:
				{
				alt31=4;
				}
				break;
			case 66:
				{
				alt31=5;
				}
				break;
			case 101:
				{
				alt31=6;
				}
				break;
			case 102:
				{
				alt31=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 31, 0, input);
				throw nvae;
			}
			switch (alt31) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:4: 'is above historical' WhiteChar secondOp= operand
					{
					string_literal80=(Token)match(input,77,FOLLOW_77_in_opcmpcondition1053);  
					stream_77.add(string_literal80);

					WhiteChar81=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1055);  
					stream_WhiteChar.add(WhiteChar81);

					pushFollow(FOLLOW_operand_in_opcmpcondition1059);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					// AST REWRITE
					// elements: operand
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 218:53: -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:56: ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupDoubleMapCondition, "SupDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:80: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_1, stream_operand.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )?
					int alt26=2;
					int LA26_0 = input.LA(1);
					if ( (LA26_0==WhiteChar) ) {
						int LA26_1 = input.LA(2);
						if ( (LA26_1==74) ) {
							alt26=1;
						}
					}
					switch (alt26) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:6: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar82=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1085);  
							stream_WhiteChar.add(WhiteChar82);

							string_literal83=(Token)match(input,74,FOLLOW_74_in_opcmpcondition1087);  
							stream_74.add(string_literal83);

							WhiteChar84=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1089);  
							stream_WhiteChar.add(WhiteChar84);

							pushFollow(FOLLOW_constant_in_opcmpcondition1093);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar85=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1095);  
							stream_WhiteChar.add(WhiteChar85);

							DAYS86=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1097);  
							stream_DAYS.add(DAYS86);

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
							// 219:66: -> ^( SupDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:69: ^( SupDoubleMapCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupDoubleMapCondition, "SupDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								adaptor.addChild(root_1, firstOp);
								adaptor.addChild(root_1, (secondOp!=null?((CommonTree)secondOp.getTree()):null));
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;

					}

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:4: 'is below historical' WhiteChar secondOp= operand
					{
					string_literal87=(Token)match(input,81,FOLLOW_81_in_opcmpcondition1119);  
					stream_81.add(string_literal87);

					WhiteChar88=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1121);  
					stream_WhiteChar.add(WhiteChar88);

					pushFollow(FOLLOW_operand_in_opcmpcondition1125);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					// AST REWRITE
					// elements: operand
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 220:53: -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:56: ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfDoubleMapCondition, "InfDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:80: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_1, stream_operand.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:5: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )?
					int alt27=2;
					int LA27_0 = input.LA(1);
					if ( (LA27_0==WhiteChar) ) {
						int LA27_1 = input.LA(2);
						if ( (LA27_1==74) ) {
							alt27=1;
						}
					}
					switch (alt27) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar89=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1153);  
							stream_WhiteChar.add(WhiteChar89);

							string_literal90=(Token)match(input,74,FOLLOW_74_in_opcmpcondition1155);  
							stream_74.add(string_literal90);

							WhiteChar91=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1157);  
							stream_WhiteChar.add(WhiteChar91);

							pushFollow(FOLLOW_constant_in_opcmpcondition1161);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar92=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1163);  
							stream_WhiteChar.add(WhiteChar92);

							DAYS93=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1165);  
							stream_DAYS.add(DAYS93);

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
							// 221:67: -> ^( InfDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:70: ^( InfDoubleMapCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfDoubleMapCondition, "InfDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								adaptor.addChild(root_1, firstOp);
								adaptor.addChild(root_1, (secondOp!=null?((CommonTree)secondOp.getTree()):null));
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;

					}

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:4: 'equals historical' WhiteChar secondOp= operand
					{
					string_literal94=(Token)match(input,71,FOLLOW_71_in_opcmpcondition1187);  
					stream_71.add(string_literal94);

					WhiteChar95=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1189);  
					stream_WhiteChar.add(WhiteChar95);

					pushFollow(FOLLOW_operand_in_opcmpcondition1193);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					// AST REWRITE
					// elements: operand
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 222:51: -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:54: ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualDoubleMapCondition, "EqualDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:80: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_1, stream_operand.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:5: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )?
					int alt28=2;
					int LA28_0 = input.LA(1);
					if ( (LA28_0==WhiteChar) ) {
						int LA28_1 = input.LA(2);
						if ( (LA28_1==74) ) {
							alt28=1;
						}
					}
					switch (alt28) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar96=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1220);  
							stream_WhiteChar.add(WhiteChar96);

							string_literal97=(Token)match(input,74,FOLLOW_74_in_opcmpcondition1222);  
							stream_74.add(string_literal97);

							WhiteChar98=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1224);  
							stream_WhiteChar.add(WhiteChar98);

							pushFollow(FOLLOW_constant_in_opcmpcondition1228);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar99=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1230);  
							stream_WhiteChar.add(WhiteChar99);

							DAYS100=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1232);  
							stream_DAYS.add(DAYS100);

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
							// 223:67: -> ^( EqualDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:70: ^( EqualDoubleMapCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualDoubleMapCondition, "EqualDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								adaptor.addChild(root_1, firstOp);
								adaptor.addChild(root_1, (secondOp!=null?((CommonTree)secondOp.getTree()):null));
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;

					}

					}
					break;
				case 4 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:3: ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:3: ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:4: 'crosses down historical' WhiteChar operand
					{
					string_literal101=(Token)match(input,64,FOLLOW_64_in_opcmpcondition1255);  
					stream_64.add(string_literal101);

					WhiteChar102=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1257);  
					stream_WhiteChar.add(WhiteChar102);

					pushFollow(FOLLOW_operand_in_opcmpcondition1259);
					operand103=operand();
					state._fsp--;

					stream_operand.add(operand103.getTree());
					// AST REWRITE
					// elements: operand
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 225:48: -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:51: ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:81: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:110: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_1, stream_operand.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:9: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )?
					int alt29=2;
					int LA29_0 = input.LA(1);
					if ( (LA29_0==WhiteChar) ) {
						int LA29_1 = input.LA(2);
						if ( (LA29_1==97) ) {
							alt29=1;
						}
					}
					switch (alt29) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:11: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar104=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1296);  
							stream_WhiteChar.add(WhiteChar104);

							string_literal105=(Token)match(input,97,FOLLOW_97_in_opcmpcondition1298);  
							stream_97.add(string_literal105);

							WhiteChar106=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1300);  
							stream_WhiteChar.add(WhiteChar106);

							pushFollow(FOLLOW_constant_in_opcmpcondition1304);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar107=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1306);  
							stream_WhiteChar.add(WhiteChar107);

							DAYS108=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1308);  
							stream_DAYS.add(DAYS108);

							WhiteChar109=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1321);  
							stream_WhiteChar.add(WhiteChar109);

							string_literal110=(Token)match(input,91,FOLLOW_91_in_opcmpcondition1323);  
							stream_91.add(string_literal110);

							WhiteChar111=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1325);  
							stream_WhiteChar.add(WhiteChar111);

							pushFollow(FOLLOW_constant_in_opcmpcondition1329);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar112=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1331);  
							stream_WhiteChar.add(WhiteChar112);

							DAYS113=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1333);  
							stream_DAYS.add(DAYS113);

							// AST REWRITE
							// elements: operand
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 228:11: -> ^( CrossDownDoubleMapCondition operand )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:14: ^( CrossDownDoubleMapCondition operand )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, firstOp);
								adaptor.addChild(root_1, stream_operand.nextTree());
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;

					}

					}
					break;
				case 5 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:3: ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:3: ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:4: 'crosses up historical' WhiteChar operand
					{
					string_literal114=(Token)match(input,66,FOLLOW_66_in_opcmpcondition1369);  
					stream_66.add(string_literal114);

					WhiteChar115=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1371);  
					stream_WhiteChar.add(WhiteChar115);

					pushFollow(FOLLOW_operand_in_opcmpcondition1373);
					operand116=operand();
					state._fsp--;

					stream_operand.add(operand116.getTree());
					// AST REWRITE
					// elements: operand
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 230:46: -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:49: ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:77: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:106: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_1, stream_operand.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:8: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )?
					int alt30=2;
					int LA30_0 = input.LA(1);
					if ( (LA30_0==WhiteChar) ) {
						int LA30_1 = input.LA(2);
						if ( (LA30_1==97) ) {
							alt30=1;
						}
					}
					switch (alt30) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:10: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar117=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1409);  
							stream_WhiteChar.add(WhiteChar117);

							string_literal118=(Token)match(input,97,FOLLOW_97_in_opcmpcondition1411);  
							stream_97.add(string_literal118);

							WhiteChar119=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1413);  
							stream_WhiteChar.add(WhiteChar119);

							pushFollow(FOLLOW_constant_in_opcmpcondition1417);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar120=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1419);  
							stream_WhiteChar.add(WhiteChar120);

							DAYS121=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1421);  
							stream_DAYS.add(DAYS121);

							WhiteChar122=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1432);  
							stream_WhiteChar.add(WhiteChar122);

							string_literal123=(Token)match(input,91,FOLLOW_91_in_opcmpcondition1434);  
							stream_91.add(string_literal123);

							WhiteChar124=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1436);  
							stream_WhiteChar.add(WhiteChar124);

							pushFollow(FOLLOW_constant_in_opcmpcondition1440);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar125=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1442);  
							stream_WhiteChar.add(WhiteChar125);

							DAYS126=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1444);  
							stream_DAYS.add(DAYS126);

							// AST REWRITE
							// elements: operand
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 233:10: -> ^( CrossUpDoubleMapCondition operand )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:13: ^( CrossUpDoubleMapCondition operand )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, firstOp);
								adaptor.addChild(root_1, stream_operand.nextTree());
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;

					}

					}
					break;
				case 6 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:4: 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal127=(Token)match(input,101,FOLLOW_101_in_opcmpcondition1479);  
					stream_101.add(string_literal127);

					WhiteChar128=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1481);  
					stream_WhiteChar.add(WhiteChar128);

					pushFollow(FOLLOW_operand_in_opcmpcondition1485);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar129=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1493);  
					stream_WhiteChar.add(WhiteChar129);

					string_literal130=(Token)match(input,91,FOLLOW_91_in_opcmpcondition1495);  
					stream_91.add(string_literal130);

					WhiteChar131=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1497);  
					stream_WhiteChar.add(WhiteChar131);

					pushFollow(FOLLOW_constant_in_opcmpcondition1501);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar132=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1503);  
					stream_WhiteChar.add(WhiteChar132);

					DAYS133=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1505);  
					stream_DAYS.add(DAYS133);

					WhiteChar134=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1513);  
					stream_WhiteChar.add(WhiteChar134);

					string_literal135=(Token)match(input,74,FOLLOW_74_in_opcmpcondition1515);  
					stream_74.add(string_literal135);

					WhiteChar136=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1517);  
					stream_WhiteChar.add(WhiteChar136);

					pushFollow(FOLLOW_constant_in_opcmpcondition1521);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar137=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1523);  
					stream_WhiteChar.add(WhiteChar137);

					DAYS138=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1525);  
					stream_DAYS.add(DAYS138);

					WhiteChar139=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1533);  
					stream_WhiteChar.add(WhiteChar139);

					string_literal140=(Token)match(input,68,FOLLOW_68_in_opcmpcondition1535);  
					stream_68.add(string_literal140);

					WhiteChar141=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1537);  
					stream_WhiteChar.add(WhiteChar141);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition1541);
					direction=stringconstant();
					state._fsp--;

					stream_stringconstant.add(direction.getTree());
					WhiteChar142=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1549);  
					stream_WhiteChar.add(WhiteChar142);

					string_literal143=(Token)match(input,70,FOLLOW_70_in_opcmpcondition1551);  
					stream_70.add(string_literal143);

					WhiteChar144=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1553);  
					stream_WhiteChar.add(WhiteChar144);

					pushFollow(FOLLOW_constant_in_opcmpcondition1557);
					epsilon=constant();
					state._fsp--;

					stream_constant.add(epsilon.getTree());
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
					// 240:7: -> ^( LinearSimilarTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:10: ^( LinearSimilarTrendsCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearSimilarTrendsCondition, "LinearSimilarTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						adaptor.addChild(root_1, (direction!=null?((CommonTree)direction.getTree()):null));
						adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_1, (secondOp!=null?((CommonTree)secondOp.getTree()):null));
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					}
					break;
				case 7 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:4: 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant
					{
					string_literal145=(Token)match(input,102,FOLLOW_102_in_opcmpcondition1590);  
					stream_102.add(string_literal145);

					WhiteChar146=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1592);  
					stream_WhiteChar.add(WhiteChar146);

					pushFollow(FOLLOW_operand_in_opcmpcondition1596);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar147=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1604);  
					stream_WhiteChar.add(WhiteChar147);

					string_literal148=(Token)match(input,91,FOLLOW_91_in_opcmpcondition1606);  
					stream_91.add(string_literal148);

					WhiteChar149=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1608);  
					stream_WhiteChar.add(WhiteChar149);

					pushFollow(FOLLOW_constant_in_opcmpcondition1612);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar150=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1614);  
					stream_WhiteChar.add(WhiteChar150);

					DAYS151=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1616);  
					stream_DAYS.add(DAYS151);

					WhiteChar152=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1624);  
					stream_WhiteChar.add(WhiteChar152);

					string_literal153=(Token)match(input,74,FOLLOW_74_in_opcmpcondition1626);  
					stream_74.add(string_literal153);

					WhiteChar154=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1628);  
					stream_WhiteChar.add(WhiteChar154);

					pushFollow(FOLLOW_constant_in_opcmpcondition1632);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar155=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1634);  
					stream_WhiteChar.add(WhiteChar155);

					DAYS156=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1636);  
					stream_DAYS.add(DAYS156);

					WhiteChar157=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1644);  
					stream_WhiteChar.add(WhiteChar157);

					string_literal158=(Token)match(input,68,FOLLOW_68_in_opcmpcondition1646);  
					stream_68.add(string_literal158);

					WhiteChar159=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1648);  
					stream_WhiteChar.add(WhiteChar159);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition1652);
					direction=stringconstant();
					state._fsp--;

					stream_stringconstant.add(direction.getTree());
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
					// 246:7: -> ^( LinearOppositeTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:10: ^( LinearOppositeTrendsCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearOppositeTrendsCondition, "LinearOppositeTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						adaptor.addChild(root_1, (direction!=null?((CommonTree)direction.getTree()):null));
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_1, (secondOp!=null?((CommonTree)secondOp.getTree()):null));
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

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
	// $ANTLR end "opcmpcondition"


	public static class constantcmp_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "constantcmp"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:1: constantcmp[CommonTree firstOp] : ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )? );
	public final ParameterizedIndicatorsParser.constantcmp_return constantcmp(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.constantcmp_return retval = new ParameterizedIndicatorsParser.constantcmp_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal160=null;
		Token WhiteChar161=null;
		Token WhiteChar162=null;
		Token string_literal163=null;
		Token WhiteChar164=null;
		Token WhiteChar165=null;
		Token DAYS166=null;
		Token WhiteChar167=null;
		Token string_literal168=null;
		Token WhiteChar169=null;
		Token WhiteChar170=null;
		Token DAYS171=null;
		Token string_literal172=null;
		Token WhiteChar173=null;
		Token WhiteChar174=null;
		Token string_literal175=null;
		Token WhiteChar176=null;
		Token WhiteChar177=null;
		Token DAYS178=null;
		Token WhiteChar179=null;
		Token string_literal180=null;
		Token WhiteChar181=null;
		Token WhiteChar182=null;
		Token DAYS183=null;
		Token string_literal184=null;
		Token WhiteChar185=null;
		Token WhiteChar186=null;
		Token string_literal187=null;
		Token WhiteChar188=null;
		Token WhiteChar189=null;
		Token DAYS190=null;
		Token WhiteChar191=null;
		Token string_literal192=null;
		Token WhiteChar193=null;
		Token WhiteChar194=null;
		Token DAYS195=null;
		Token string_literal196=null;
		Token WhiteChar197=null;
		Token WhiteChar198=null;
		Token string_literal199=null;
		Token WhiteChar200=null;
		Token WhiteChar201=null;
		Token DAYS202=null;
		Token WhiteChar203=null;
		Token string_literal204=null;
		Token WhiteChar205=null;
		Token WhiteChar206=null;
		Token DAYS207=null;
		ParserRuleReturnScope trendSignal =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope threshold =null;

		CommonTree string_literal160_tree=null;
		CommonTree WhiteChar161_tree=null;
		CommonTree WhiteChar162_tree=null;
		CommonTree string_literal163_tree=null;
		CommonTree WhiteChar164_tree=null;
		CommonTree WhiteChar165_tree=null;
		CommonTree DAYS166_tree=null;
		CommonTree WhiteChar167_tree=null;
		CommonTree string_literal168_tree=null;
		CommonTree WhiteChar169_tree=null;
		CommonTree WhiteChar170_tree=null;
		CommonTree DAYS171_tree=null;
		CommonTree string_literal172_tree=null;
		CommonTree WhiteChar173_tree=null;
		CommonTree WhiteChar174_tree=null;
		CommonTree string_literal175_tree=null;
		CommonTree WhiteChar176_tree=null;
		CommonTree WhiteChar177_tree=null;
		CommonTree DAYS178_tree=null;
		CommonTree WhiteChar179_tree=null;
		CommonTree string_literal180_tree=null;
		CommonTree WhiteChar181_tree=null;
		CommonTree WhiteChar182_tree=null;
		CommonTree DAYS183_tree=null;
		CommonTree string_literal184_tree=null;
		CommonTree WhiteChar185_tree=null;
		CommonTree WhiteChar186_tree=null;
		CommonTree string_literal187_tree=null;
		CommonTree WhiteChar188_tree=null;
		CommonTree WhiteChar189_tree=null;
		CommonTree DAYS190_tree=null;
		CommonTree WhiteChar191_tree=null;
		CommonTree string_literal192_tree=null;
		CommonTree WhiteChar193_tree=null;
		CommonTree WhiteChar194_tree=null;
		CommonTree DAYS195_tree=null;
		CommonTree string_literal196_tree=null;
		CommonTree WhiteChar197_tree=null;
		CommonTree WhiteChar198_tree=null;
		CommonTree string_literal199_tree=null;
		CommonTree WhiteChar200_tree=null;
		CommonTree WhiteChar201_tree=null;
		CommonTree DAYS202_tree=null;
		CommonTree WhiteChar203_tree=null;
		CommonTree string_literal204_tree=null;
		CommonTree WhiteChar205_tree=null;
		CommonTree WhiteChar206_tree=null;
		CommonTree DAYS207_tree=null;
		RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_91=new RewriteRuleTokenStream(adaptor,"token 91");
		RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
		RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
		RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
		RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_trendconstant=new RewriteRuleSubtreeStream(adaptor,"rule trendconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:34: ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )? )
			int alt36=4;
			switch ( input.LA(1) ) {
			case 73:
				{
				alt36=1;
				}
				break;
			case 72:
				{
				alt36=2;
				}
				break;
			case 78:
				{
				alt36=3;
				}
				break;
			case 82:
				{
				alt36=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 36, 0, input);
				throw nvae;
			}
			switch (alt36) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:4: 'equals trend' WhiteChar trendSignal= trendconstant
					{
					string_literal160=(Token)match(input,73,FOLLOW_73_in_constantcmp1690);  
					stream_73.add(string_literal160);

					WhiteChar161=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1692);  
					stream_WhiteChar.add(WhiteChar161);

					pushFollow(FOLLOW_trendconstant_in_constantcmp1696);
					trendSignal=trendconstant();
					state._fsp--;

					stream_trendconstant.add(trendSignal.getTree());
					// AST REWRITE
					// elements: trendconstant
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 251:55: -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:58: ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualStringConstantCondition, "EqualStringConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_trendconstant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:103: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:130: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					int alt32=2;
					int LA32_0 = input.LA(1);
					if ( (LA32_0==WhiteChar) ) {
						int LA32_1 = input.LA(2);
						if ( (LA32_1==91) ) {
							alt32=1;
						}
					}
					switch (alt32) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar162=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1730);  
							stream_WhiteChar.add(WhiteChar162);

							string_literal163=(Token)match(input,91,FOLLOW_91_in_constantcmp1732);  
							stream_91.add(string_literal163);

							WhiteChar164=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1734);  
							stream_WhiteChar.add(WhiteChar164);

							pushFollow(FOLLOW_constant_in_constantcmp1738);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar165=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1740);  
							stream_WhiteChar.add(WhiteChar165);

							DAYS166=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1742);  
							stream_DAYS.add(DAYS166);

							WhiteChar167=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1744);  
							stream_WhiteChar.add(WhiteChar167);

							string_literal168=(Token)match(input,74,FOLLOW_74_in_constantcmp1746);  
							stream_74.add(string_literal168);

							WhiteChar169=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1748);  
							stream_WhiteChar.add(WhiteChar169);

							pushFollow(FOLLOW_constant_in_constantcmp1752);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar170=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1754);  
							stream_WhiteChar.add(WhiteChar170);

							DAYS171=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1756);  
							stream_DAYS.add(DAYS171);

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
							// 252:129: -> ^( EqualStringConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:132: ^( EqualStringConstantCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualStringConstantCondition, "EqualStringConstantCondition"), root_1);
								adaptor.addChild(root_1, (trendSignal!=null?((CommonTree)trendSignal.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								adaptor.addChild(root_1, firstOp);
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;

					}

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:4: 'equals threshold' WhiteChar threshold= constant
					{
					string_literal172=(Token)match(input,72,FOLLOW_72_in_constantcmp1784);  
					stream_72.add(string_literal172);

					WhiteChar173=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1786);  
					stream_WhiteChar.add(WhiteChar173);

					pushFollow(FOLLOW_constant_in_constantcmp1790);
					threshold=constant();
					state._fsp--;

					stream_constant.add(threshold.getTree());
					// AST REWRITE
					// elements: constant
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 254:52: -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:55: ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualConstantCondition, "EqualConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )?
					int alt33=2;
					int LA33_0 = input.LA(1);
					if ( (LA33_0==WhiteChar) ) {
						int LA33_1 = input.LA(2);
						if ( (LA33_1==91) ) {
							alt33=1;
						}
					}
					switch (alt33) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar174=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1824);  
							stream_WhiteChar.add(WhiteChar174);

							string_literal175=(Token)match(input,91,FOLLOW_91_in_constantcmp1826);  
							stream_91.add(string_literal175);

							WhiteChar176=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1828);  
							stream_WhiteChar.add(WhiteChar176);

							pushFollow(FOLLOW_constant_in_constantcmp1832);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar177=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1834);  
							stream_WhiteChar.add(WhiteChar177);

							DAYS178=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1836);  
							stream_DAYS.add(DAYS178);

							WhiteChar179=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1838);  
							stream_WhiteChar.add(WhiteChar179);

							string_literal180=(Token)match(input,74,FOLLOW_74_in_constantcmp1840);  
							stream_74.add(string_literal180);

							WhiteChar181=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1842);  
							stream_WhiteChar.add(WhiteChar181);

							pushFollow(FOLLOW_constant_in_constantcmp1846);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar182=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1848);  
							stream_WhiteChar.add(WhiteChar182);

							DAYS183=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1850);  
							stream_DAYS.add(DAYS183);

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
							// 255:129: -> ^( EqualConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:132: ^( EqualConstantCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualConstantCondition, "EqualConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								adaptor.addChild(root_1, firstOp);
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;

					}

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:4: 'is above threshold' WhiteChar threshold= constant
					{
					string_literal184=(Token)match(input,78,FOLLOW_78_in_constantcmp1879);  
					stream_78.add(string_literal184);

					WhiteChar185=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1881);  
					stream_WhiteChar.add(WhiteChar185);

					pushFollow(FOLLOW_constant_in_constantcmp1885);
					threshold=constant();
					state._fsp--;

					stream_constant.add(threshold.getTree());
					// AST REWRITE
					// elements: constant
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 257:54: -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:57: ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupConstantCondition, "SupConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )?
					int alt34=2;
					int LA34_0 = input.LA(1);
					if ( (LA34_0==WhiteChar) ) {
						int LA34_1 = input.LA(2);
						if ( (LA34_1==91) ) {
							alt34=1;
						}
					}
					switch (alt34) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar186=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1919);  
							stream_WhiteChar.add(WhiteChar186);

							string_literal187=(Token)match(input,91,FOLLOW_91_in_constantcmp1921);  
							stream_91.add(string_literal187);

							WhiteChar188=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1923);  
							stream_WhiteChar.add(WhiteChar188);

							pushFollow(FOLLOW_constant_in_constantcmp1927);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar189=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1929);  
							stream_WhiteChar.add(WhiteChar189);

							DAYS190=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1931);  
							stream_DAYS.add(DAYS190);

							WhiteChar191=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1933);  
							stream_WhiteChar.add(WhiteChar191);

							string_literal192=(Token)match(input,74,FOLLOW_74_in_constantcmp1935);  
							stream_74.add(string_literal192);

							WhiteChar193=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1937);  
							stream_WhiteChar.add(WhiteChar193);

							pushFollow(FOLLOW_constant_in_constantcmp1941);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar194=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1943);  
							stream_WhiteChar.add(WhiteChar194);

							DAYS195=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1945);  
							stream_DAYS.add(DAYS195);

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
							// 258:129: -> ^( SupConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:132: ^( SupConstantCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupConstantCondition, "SupConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								adaptor.addChild(root_1, firstOp);
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;

					}

					}
					break;
				case 4 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:4: 'is below threshold' WhiteChar threshold= constant
					{
					string_literal196=(Token)match(input,82,FOLLOW_82_in_constantcmp1974);  
					stream_82.add(string_literal196);

					WhiteChar197=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1976);  
					stream_WhiteChar.add(WhiteChar197);

					pushFollow(FOLLOW_constant_in_constantcmp1980);
					threshold=constant();
					state._fsp--;

					stream_constant.add(threshold.getTree());
					// AST REWRITE
					// elements: constant
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 260:54: -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:57: ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfConstantCondition, "InfConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )?
					int alt35=2;
					int LA35_0 = input.LA(1);
					if ( (LA35_0==WhiteChar) ) {
						int LA35_1 = input.LA(2);
						if ( (LA35_1==91) ) {
							alt35=1;
						}
					}
					switch (alt35) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar198=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2014);  
							stream_WhiteChar.add(WhiteChar198);

							string_literal199=(Token)match(input,91,FOLLOW_91_in_constantcmp2016);  
							stream_91.add(string_literal199);

							WhiteChar200=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2018);  
							stream_WhiteChar.add(WhiteChar200);

							pushFollow(FOLLOW_constant_in_constantcmp2022);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar201=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2024);  
							stream_WhiteChar.add(WhiteChar201);

							DAYS202=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2026);  
							stream_DAYS.add(DAYS202);

							WhiteChar203=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2028);  
							stream_WhiteChar.add(WhiteChar203);

							string_literal204=(Token)match(input,74,FOLLOW_74_in_constantcmp2030);  
							stream_74.add(string_literal204);

							WhiteChar205=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2032);  
							stream_WhiteChar.add(WhiteChar205);

							pushFollow(FOLLOW_constant_in_constantcmp2036);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar206=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2038);  
							stream_WhiteChar.add(WhiteChar206);

							DAYS207=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2040);  
							stream_DAYS.add(DAYS207);

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
							// 261:129: -> ^( InfConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:132: ^( InfConstantCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfConstantCondition, "InfConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								adaptor.addChild(root_1, firstOp);
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;

					}

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
	// $ANTLR end "constantcmp"


	public static class presetcondition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "presetcondition"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:1: presetcondition[CommonTree firstOp] : ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearFlatTrendsCondition ) ) );
	public final ParameterizedIndicatorsParser.presetcondition_return presetcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.presetcondition_return retval = new ParameterizedIndicatorsParser.presetcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal208=null;
		Token WhiteChar209=null;
		Token string_literal210=null;
		Token WhiteChar211=null;
		Token PERCENT212=null;
		Token WhiteChar213=null;
		Token string_literal214=null;
		Token WhiteChar215=null;
		Token WhiteChar216=null;
		Token DAYS217=null;
		Token string_literal218=null;
		Token WhiteChar219=null;
		Token string_literal220=null;
		Token WhiteChar221=null;
		Token PERCENT222=null;
		Token WhiteChar223=null;
		Token string_literal224=null;
		Token WhiteChar225=null;
		Token WhiteChar226=null;
		Token DAYS227=null;
		Token string_literal228=null;
		Token WhiteChar229=null;
		Token PERCENT230=null;
		Token WhiteChar231=null;
		Token string_literal232=null;
		Token WhiteChar233=null;
		Token WhiteChar234=null;
		Token DAYS235=null;
		Token WhiteChar236=null;
		Token string_literal237=null;
		Token WhiteChar238=null;
		Token WhiteChar239=null;
		Token DAYS240=null;
		Token string_literal241=null;
		Token WhiteChar242=null;
		Token PERCENT243=null;
		Token WhiteChar244=null;
		Token string_literal245=null;
		Token WhiteChar246=null;
		Token WhiteChar247=null;
		Token DAYS248=null;
		Token WhiteChar249=null;
		Token string_literal250=null;
		Token WhiteChar251=null;
		Token WhiteChar252=null;
		Token DAYS253=null;
		Token string_literal254=null;
		Token WhiteChar255=null;
		Token WhiteChar256=null;
		Token string_literal257=null;
		Token WhiteChar258=null;
		Token WhiteChar259=null;
		Token DAYS260=null;
		Token WhiteChar261=null;
		Token string_literal262=null;
		Token WhiteChar263=null;
		Token WhiteChar264=null;
		Token DAYS265=null;
		Token string_literal266=null;
		Token WhiteChar267=null;
		Token WhiteChar268=null;
		Token string_literal269=null;
		Token WhiteChar270=null;
		Token WhiteChar271=null;
		Token DAYS272=null;
		Token WhiteChar273=null;
		Token string_literal274=null;
		Token WhiteChar275=null;
		Token WhiteChar276=null;
		Token DAYS277=null;
		Token string_literal278=null;
		Token WhiteChar279=null;
		Token WhiteChar280=null;
		Token DAYS281=null;
		Token WhiteChar282=null;
		Token string_literal283=null;
		Token WhiteChar284=null;
		Token WhiteChar285=null;
		Token DAYS286=null;
		Token WhiteChar287=null;
		Token string_literal288=null;
		Token WhiteChar289=null;
		Token WhiteChar290=null;
		Token DAYS291=null;
		Token WhiteChar292=null;
		Token string_literal293=null;
		Token WhiteChar294=null;
		Token WhiteChar295=null;
		Token DAYS296=null;
		Token WhiteChar297=null;
		Token string_literal298=null;
		Token WhiteChar299=null;
		Token char_literal300=null;
		Token char_literal301=null;
		Token char_literal302=null;
		Token WhiteChar303=null;
		Token string_literal304=null;
		Token WhiteChar305=null;
		Token char_literal306=null;
		Token char_literal307=null;
		Token char_literal308=null;
		Token WhiteChar309=null;
		Token string_literal310=null;
		Token WhiteChar311=null;
		Token char_literal312=null;
		Token char_literal313=null;
		Token char_literal314=null;
		Token string_literal315=null;
		Token WhiteChar316=null;
		Token WhiteChar317=null;
		Token DAYS318=null;
		Token WhiteChar319=null;
		Token string_literal320=null;
		Token WhiteChar321=null;
		Token WhiteChar322=null;
		Token DAYS323=null;
		Token WhiteChar324=null;
		Token string_literal325=null;
		Token WhiteChar326=null;
		Token WhiteChar327=null;
		Token DAYS328=null;
		Token WhiteChar329=null;
		Token string_literal330=null;
		Token WhiteChar331=null;
		Token WhiteChar332=null;
		Token DAYS333=null;
		Token WhiteChar334=null;
		Token string_literal335=null;
		Token WhiteChar336=null;
		Token char_literal337=null;
		Token char_literal338=null;
		Token char_literal339=null;
		Token WhiteChar340=null;
		Token string_literal341=null;
		Token WhiteChar342=null;
		Token char_literal343=null;
		Token char_literal344=null;
		Token char_literal345=null;
		Token WhiteChar346=null;
		Token string_literal347=null;
		Token WhiteChar348=null;
		Token char_literal349=null;
		Token char_literal350=null;
		Token char_literal351=null;
		Token string_literal352=null;
		Token WhiteChar353=null;
		Token WhiteChar354=null;
		Token DAYS355=null;
		Token WhiteChar356=null;
		Token string_literal357=null;
		Token WhiteChar358=null;
		Token WhiteChar359=null;
		Token DAYS360=null;
		Token WhiteChar361=null;
		Token string_literal362=null;
		Token WhiteChar363=null;
		Token WhiteChar364=null;
		Token DAYS365=null;
		Token WhiteChar366=null;
		Token string_literal367=null;
		Token WhiteChar368=null;
		Token WhiteChar369=null;
		Token DAYS370=null;
		Token WhiteChar371=null;
		Token string_literal372=null;
		Token WhiteChar373=null;
		Token char_literal374=null;
		Token char_literal375=null;
		Token char_literal376=null;
		Token WhiteChar377=null;
		Token string_literal378=null;
		Token WhiteChar379=null;
		Token char_literal380=null;
		Token char_literal381=null;
		Token char_literal382=null;
		Token WhiteChar383=null;
		Token string_literal384=null;
		Token WhiteChar385=null;
		Token char_literal386=null;
		Token char_literal387=null;
		Token char_literal388=null;
		Token string_literal389=null;
		Token WhiteChar390=null;
		Token WhiteChar391=null;
		Token DAYS392=null;
		Token WhiteChar393=null;
		Token string_literal394=null;
		Token WhiteChar395=null;
		Token WhiteChar396=null;
		Token DAYS397=null;
		Token WhiteChar398=null;
		Token string_literal399=null;
		Token WhiteChar400=null;
		Token WhiteChar401=null;
		Token DAYS402=null;
		Token WhiteChar403=null;
		Token string_literal404=null;
		Token WhiteChar405=null;
		Token WhiteChar406=null;
		Token DAYS407=null;
		Token WhiteChar408=null;
		Token string_literal409=null;
		Token WhiteChar410=null;
		Token char_literal411=null;
		Token char_literal412=null;
		Token char_literal413=null;
		Token WhiteChar414=null;
		Token string_literal415=null;
		Token WhiteChar416=null;
		Token char_literal417=null;
		Token char_literal418=null;
		Token char_literal419=null;
		Token WhiteChar420=null;
		Token string_literal421=null;
		Token WhiteChar422=null;
		Token char_literal423=null;
		Token char_literal424=null;
		Token char_literal425=null;
		Token string_literal426=null;
		Token WhiteChar427=null;
		Token WhiteChar428=null;
		Token DAYS429=null;
		Token WhiteChar430=null;
		Token string_literal431=null;
		Token WhiteChar432=null;
		Token WhiteChar433=null;
		Token DAYS434=null;
		Token WhiteChar435=null;
		Token string_literal436=null;
		Token WhiteChar437=null;
		Token WhiteChar438=null;
		Token DAYS439=null;
		Token WhiteChar440=null;
		Token string_literal441=null;
		Token WhiteChar442=null;
		Token WhiteChar443=null;
		Token DAYS444=null;
		Token WhiteChar445=null;
		Token string_literal446=null;
		Token WhiteChar447=null;
		Token char_literal448=null;
		Token char_literal449=null;
		Token char_literal450=null;
		Token WhiteChar451=null;
		Token string_literal452=null;
		Token WhiteChar453=null;
		Token string_literal454=null;
		Token WhiteChar455=null;
		Token WhiteChar456=null;
		Token DAYS457=null;
		Token WhiteChar458=null;
		Token string_literal459=null;
		Token WhiteChar460=null;
		Token WhiteChar461=null;
		Token DAYS462=null;
		Token WhiteChar463=null;
		Token string_literal464=null;
		Token WhiteChar465=null;
		Token WhiteChar466=null;
		Token DAYS467=null;
		Token WhiteChar468=null;
		Token string_literal469=null;
		Token WhiteChar470=null;
		Token WhiteChar471=null;
		Token DAYS472=null;
		Token WhiteChar473=null;
		Token string_literal474=null;
		Token WhiteChar475=null;
		Token char_literal476=null;
		Token char_literal477=null;
		Token char_literal478=null;
		Token WhiteChar479=null;
		Token string_literal480=null;
		Token WhiteChar481=null;
		Token string_literal482=null;
		Token WhiteChar483=null;
		Token string_literal484=null;
		Token WhiteChar485=null;
		Token WhiteChar486=null;
		Token DAYS487=null;
		Token WhiteChar488=null;
		Token string_literal489=null;
		Token WhiteChar490=null;
		Token WhiteChar491=null;
		Token DAYS492=null;
		Token WhiteChar493=null;
		Token string_literal494=null;
		Token WhiteChar495=null;
		ParserRuleReturnScope percentdown =null;
		ParserRuleReturnScope spanningNbDays =null;
		ParserRuleReturnScope percentup =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope threshold =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope lookBack =null;
		ParserRuleReturnScope remanencePeriod =null;
		ParserRuleReturnScope extremesSpan =null;
		ParserRuleReturnScope smoothP =null;
		ParserRuleReturnScope lowestStart =null;
		ParserRuleReturnScope highestStart =null;
		ParserRuleReturnScope lowestEnd =null;
		ParserRuleReturnScope highestEnd =null;
		ParserRuleReturnScope minSlope =null;
		ParserRuleReturnScope maxSlope =null;
		ParserRuleReturnScope tolerance =null;
		ParserRuleReturnScope epsilon =null;

		CommonTree string_literal208_tree=null;
		CommonTree WhiteChar209_tree=null;
		CommonTree string_literal210_tree=null;
		CommonTree WhiteChar211_tree=null;
		CommonTree PERCENT212_tree=null;
		CommonTree WhiteChar213_tree=null;
		CommonTree string_literal214_tree=null;
		CommonTree WhiteChar215_tree=null;
		CommonTree WhiteChar216_tree=null;
		CommonTree DAYS217_tree=null;
		CommonTree string_literal218_tree=null;
		CommonTree WhiteChar219_tree=null;
		CommonTree string_literal220_tree=null;
		CommonTree WhiteChar221_tree=null;
		CommonTree PERCENT222_tree=null;
		CommonTree WhiteChar223_tree=null;
		CommonTree string_literal224_tree=null;
		CommonTree WhiteChar225_tree=null;
		CommonTree WhiteChar226_tree=null;
		CommonTree DAYS227_tree=null;
		CommonTree string_literal228_tree=null;
		CommonTree WhiteChar229_tree=null;
		CommonTree PERCENT230_tree=null;
		CommonTree WhiteChar231_tree=null;
		CommonTree string_literal232_tree=null;
		CommonTree WhiteChar233_tree=null;
		CommonTree WhiteChar234_tree=null;
		CommonTree DAYS235_tree=null;
		CommonTree WhiteChar236_tree=null;
		CommonTree string_literal237_tree=null;
		CommonTree WhiteChar238_tree=null;
		CommonTree WhiteChar239_tree=null;
		CommonTree DAYS240_tree=null;
		CommonTree string_literal241_tree=null;
		CommonTree WhiteChar242_tree=null;
		CommonTree PERCENT243_tree=null;
		CommonTree WhiteChar244_tree=null;
		CommonTree string_literal245_tree=null;
		CommonTree WhiteChar246_tree=null;
		CommonTree WhiteChar247_tree=null;
		CommonTree DAYS248_tree=null;
		CommonTree WhiteChar249_tree=null;
		CommonTree string_literal250_tree=null;
		CommonTree WhiteChar251_tree=null;
		CommonTree WhiteChar252_tree=null;
		CommonTree DAYS253_tree=null;
		CommonTree string_literal254_tree=null;
		CommonTree WhiteChar255_tree=null;
		CommonTree WhiteChar256_tree=null;
		CommonTree string_literal257_tree=null;
		CommonTree WhiteChar258_tree=null;
		CommonTree WhiteChar259_tree=null;
		CommonTree DAYS260_tree=null;
		CommonTree WhiteChar261_tree=null;
		CommonTree string_literal262_tree=null;
		CommonTree WhiteChar263_tree=null;
		CommonTree WhiteChar264_tree=null;
		CommonTree DAYS265_tree=null;
		CommonTree string_literal266_tree=null;
		CommonTree WhiteChar267_tree=null;
		CommonTree WhiteChar268_tree=null;
		CommonTree string_literal269_tree=null;
		CommonTree WhiteChar270_tree=null;
		CommonTree WhiteChar271_tree=null;
		CommonTree DAYS272_tree=null;
		CommonTree WhiteChar273_tree=null;
		CommonTree string_literal274_tree=null;
		CommonTree WhiteChar275_tree=null;
		CommonTree WhiteChar276_tree=null;
		CommonTree DAYS277_tree=null;
		CommonTree string_literal278_tree=null;
		CommonTree WhiteChar279_tree=null;
		CommonTree WhiteChar280_tree=null;
		CommonTree DAYS281_tree=null;
		CommonTree WhiteChar282_tree=null;
		CommonTree string_literal283_tree=null;
		CommonTree WhiteChar284_tree=null;
		CommonTree WhiteChar285_tree=null;
		CommonTree DAYS286_tree=null;
		CommonTree WhiteChar287_tree=null;
		CommonTree string_literal288_tree=null;
		CommonTree WhiteChar289_tree=null;
		CommonTree WhiteChar290_tree=null;
		CommonTree DAYS291_tree=null;
		CommonTree WhiteChar292_tree=null;
		CommonTree string_literal293_tree=null;
		CommonTree WhiteChar294_tree=null;
		CommonTree WhiteChar295_tree=null;
		CommonTree DAYS296_tree=null;
		CommonTree WhiteChar297_tree=null;
		CommonTree string_literal298_tree=null;
		CommonTree WhiteChar299_tree=null;
		CommonTree char_literal300_tree=null;
		CommonTree char_literal301_tree=null;
		CommonTree char_literal302_tree=null;
		CommonTree WhiteChar303_tree=null;
		CommonTree string_literal304_tree=null;
		CommonTree WhiteChar305_tree=null;
		CommonTree char_literal306_tree=null;
		CommonTree char_literal307_tree=null;
		CommonTree char_literal308_tree=null;
		CommonTree WhiteChar309_tree=null;
		CommonTree string_literal310_tree=null;
		CommonTree WhiteChar311_tree=null;
		CommonTree char_literal312_tree=null;
		CommonTree char_literal313_tree=null;
		CommonTree char_literal314_tree=null;
		CommonTree string_literal315_tree=null;
		CommonTree WhiteChar316_tree=null;
		CommonTree WhiteChar317_tree=null;
		CommonTree DAYS318_tree=null;
		CommonTree WhiteChar319_tree=null;
		CommonTree string_literal320_tree=null;
		CommonTree WhiteChar321_tree=null;
		CommonTree WhiteChar322_tree=null;
		CommonTree DAYS323_tree=null;
		CommonTree WhiteChar324_tree=null;
		CommonTree string_literal325_tree=null;
		CommonTree WhiteChar326_tree=null;
		CommonTree WhiteChar327_tree=null;
		CommonTree DAYS328_tree=null;
		CommonTree WhiteChar329_tree=null;
		CommonTree string_literal330_tree=null;
		CommonTree WhiteChar331_tree=null;
		CommonTree WhiteChar332_tree=null;
		CommonTree DAYS333_tree=null;
		CommonTree WhiteChar334_tree=null;
		CommonTree string_literal335_tree=null;
		CommonTree WhiteChar336_tree=null;
		CommonTree char_literal337_tree=null;
		CommonTree char_literal338_tree=null;
		CommonTree char_literal339_tree=null;
		CommonTree WhiteChar340_tree=null;
		CommonTree string_literal341_tree=null;
		CommonTree WhiteChar342_tree=null;
		CommonTree char_literal343_tree=null;
		CommonTree char_literal344_tree=null;
		CommonTree char_literal345_tree=null;
		CommonTree WhiteChar346_tree=null;
		CommonTree string_literal347_tree=null;
		CommonTree WhiteChar348_tree=null;
		CommonTree char_literal349_tree=null;
		CommonTree char_literal350_tree=null;
		CommonTree char_literal351_tree=null;
		CommonTree string_literal352_tree=null;
		CommonTree WhiteChar353_tree=null;
		CommonTree WhiteChar354_tree=null;
		CommonTree DAYS355_tree=null;
		CommonTree WhiteChar356_tree=null;
		CommonTree string_literal357_tree=null;
		CommonTree WhiteChar358_tree=null;
		CommonTree WhiteChar359_tree=null;
		CommonTree DAYS360_tree=null;
		CommonTree WhiteChar361_tree=null;
		CommonTree string_literal362_tree=null;
		CommonTree WhiteChar363_tree=null;
		CommonTree WhiteChar364_tree=null;
		CommonTree DAYS365_tree=null;
		CommonTree WhiteChar366_tree=null;
		CommonTree string_literal367_tree=null;
		CommonTree WhiteChar368_tree=null;
		CommonTree WhiteChar369_tree=null;
		CommonTree DAYS370_tree=null;
		CommonTree WhiteChar371_tree=null;
		CommonTree string_literal372_tree=null;
		CommonTree WhiteChar373_tree=null;
		CommonTree char_literal374_tree=null;
		CommonTree char_literal375_tree=null;
		CommonTree char_literal376_tree=null;
		CommonTree WhiteChar377_tree=null;
		CommonTree string_literal378_tree=null;
		CommonTree WhiteChar379_tree=null;
		CommonTree char_literal380_tree=null;
		CommonTree char_literal381_tree=null;
		CommonTree char_literal382_tree=null;
		CommonTree WhiteChar383_tree=null;
		CommonTree string_literal384_tree=null;
		CommonTree WhiteChar385_tree=null;
		CommonTree char_literal386_tree=null;
		CommonTree char_literal387_tree=null;
		CommonTree char_literal388_tree=null;
		CommonTree string_literal389_tree=null;
		CommonTree WhiteChar390_tree=null;
		CommonTree WhiteChar391_tree=null;
		CommonTree DAYS392_tree=null;
		CommonTree WhiteChar393_tree=null;
		CommonTree string_literal394_tree=null;
		CommonTree WhiteChar395_tree=null;
		CommonTree WhiteChar396_tree=null;
		CommonTree DAYS397_tree=null;
		CommonTree WhiteChar398_tree=null;
		CommonTree string_literal399_tree=null;
		CommonTree WhiteChar400_tree=null;
		CommonTree WhiteChar401_tree=null;
		CommonTree DAYS402_tree=null;
		CommonTree WhiteChar403_tree=null;
		CommonTree string_literal404_tree=null;
		CommonTree WhiteChar405_tree=null;
		CommonTree WhiteChar406_tree=null;
		CommonTree DAYS407_tree=null;
		CommonTree WhiteChar408_tree=null;
		CommonTree string_literal409_tree=null;
		CommonTree WhiteChar410_tree=null;
		CommonTree char_literal411_tree=null;
		CommonTree char_literal412_tree=null;
		CommonTree char_literal413_tree=null;
		CommonTree WhiteChar414_tree=null;
		CommonTree string_literal415_tree=null;
		CommonTree WhiteChar416_tree=null;
		CommonTree char_literal417_tree=null;
		CommonTree char_literal418_tree=null;
		CommonTree char_literal419_tree=null;
		CommonTree WhiteChar420_tree=null;
		CommonTree string_literal421_tree=null;
		CommonTree WhiteChar422_tree=null;
		CommonTree char_literal423_tree=null;
		CommonTree char_literal424_tree=null;
		CommonTree char_literal425_tree=null;
		CommonTree string_literal426_tree=null;
		CommonTree WhiteChar427_tree=null;
		CommonTree WhiteChar428_tree=null;
		CommonTree DAYS429_tree=null;
		CommonTree WhiteChar430_tree=null;
		CommonTree string_literal431_tree=null;
		CommonTree WhiteChar432_tree=null;
		CommonTree WhiteChar433_tree=null;
		CommonTree DAYS434_tree=null;
		CommonTree WhiteChar435_tree=null;
		CommonTree string_literal436_tree=null;
		CommonTree WhiteChar437_tree=null;
		CommonTree WhiteChar438_tree=null;
		CommonTree DAYS439_tree=null;
		CommonTree WhiteChar440_tree=null;
		CommonTree string_literal441_tree=null;
		CommonTree WhiteChar442_tree=null;
		CommonTree WhiteChar443_tree=null;
		CommonTree DAYS444_tree=null;
		CommonTree WhiteChar445_tree=null;
		CommonTree string_literal446_tree=null;
		CommonTree WhiteChar447_tree=null;
		CommonTree char_literal448_tree=null;
		CommonTree char_literal449_tree=null;
		CommonTree char_literal450_tree=null;
		CommonTree WhiteChar451_tree=null;
		CommonTree string_literal452_tree=null;
		CommonTree WhiteChar453_tree=null;
		CommonTree string_literal454_tree=null;
		CommonTree WhiteChar455_tree=null;
		CommonTree WhiteChar456_tree=null;
		CommonTree DAYS457_tree=null;
		CommonTree WhiteChar458_tree=null;
		CommonTree string_literal459_tree=null;
		CommonTree WhiteChar460_tree=null;
		CommonTree WhiteChar461_tree=null;
		CommonTree DAYS462_tree=null;
		CommonTree WhiteChar463_tree=null;
		CommonTree string_literal464_tree=null;
		CommonTree WhiteChar465_tree=null;
		CommonTree WhiteChar466_tree=null;
		CommonTree DAYS467_tree=null;
		CommonTree WhiteChar468_tree=null;
		CommonTree string_literal469_tree=null;
		CommonTree WhiteChar470_tree=null;
		CommonTree WhiteChar471_tree=null;
		CommonTree DAYS472_tree=null;
		CommonTree WhiteChar473_tree=null;
		CommonTree string_literal474_tree=null;
		CommonTree WhiteChar475_tree=null;
		CommonTree char_literal476_tree=null;
		CommonTree char_literal477_tree=null;
		CommonTree char_literal478_tree=null;
		CommonTree WhiteChar479_tree=null;
		CommonTree string_literal480_tree=null;
		CommonTree WhiteChar481_tree=null;
		CommonTree string_literal482_tree=null;
		CommonTree WhiteChar483_tree=null;
		CommonTree string_literal484_tree=null;
		CommonTree WhiteChar485_tree=null;
		CommonTree WhiteChar486_tree=null;
		CommonTree DAYS487_tree=null;
		CommonTree WhiteChar488_tree=null;
		CommonTree string_literal489_tree=null;
		CommonTree WhiteChar490_tree=null;
		CommonTree WhiteChar491_tree=null;
		CommonTree DAYS492_tree=null;
		CommonTree WhiteChar493_tree=null;
		CommonTree string_literal494_tree=null;
		CommonTree WhiteChar495_tree=null;
		RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");
		RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
		RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
		RewriteRuleTokenStream stream_91=new RewriteRuleTokenStream(adaptor,"token 91");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
		RewriteRuleTokenStream stream_94=new RewriteRuleTokenStream(adaptor,"token 94");
		RewriteRuleTokenStream stream_95=new RewriteRuleTokenStream(adaptor,"token 95");
		RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
		RewriteRuleTokenStream stream_96=new RewriteRuleTokenStream(adaptor,"token 96");
		RewriteRuleTokenStream stream_97=new RewriteRuleTokenStream(adaptor,"token 97");
		RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
		RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
		RewriteRuleTokenStream stream_98=new RewriteRuleTokenStream(adaptor,"token 98");
		RewriteRuleTokenStream stream_99=new RewriteRuleTokenStream(adaptor,"token 99");
		RewriteRuleTokenStream stream_57=new RewriteRuleTokenStream(adaptor,"token 57");
		RewriteRuleTokenStream stream_59=new RewriteRuleTokenStream(adaptor,"token 59");
		RewriteRuleTokenStream stream_100=new RewriteRuleTokenStream(adaptor,"token 100");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
		RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
		RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");
		RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
		RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");
		RewriteRuleTokenStream stream_87=new RewriteRuleTokenStream(adaptor,"token 87");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:38: ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearFlatTrendsCondition ) ) )
			int alt43=13;
			switch ( input.LA(1) ) {
			case 93:
				{
				alt43=1;
				}
				break;
			case 94:
				{
				alt43=2;
				}
				break;
			case 75:
				{
				alt43=3;
				}
				break;
			case 76:
				{
				alt43=4;
				}
				break;
			case 67:
				{
				alt43=5;
				}
				break;
			case 65:
				{
				alt43=6;
				}
				break;
			case 84:
				{
				alt43=7;
				}
				break;
			case 85:
				{
				alt43=8;
				}
				break;
			case 86:
				{
				alt43=9;
				}
				break;
			case 87:
				{
				alt43=10;
				}
				break;
			case 88:
				{
				alt43=11;
				}
				break;
			case 89:
				{
				alt43=12;
				}
				break;
			case 100:
				{
				alt43=13;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 43, 0, input);
				throw nvae;
			}
			switch (alt43) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:4: 'reverses down'
					{
					string_literal208=(Token)match(input,93,FOLLOW_93_in_presetcondition2072);  
					stream_93.add(string_literal208);

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
					// 266:20: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:23: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:42: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:70: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:99: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:7: ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					int alt37=2;
					int LA37_0 = input.LA(1);
					if ( (LA37_0==WhiteChar) ) {
						int LA37_1 = input.LA(2);
						if ( (LA37_1==90) ) {
							alt37=1;
						}
					}
					switch (alt37) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:9: WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar209=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2112);  
							stream_WhiteChar.add(WhiteChar209);

							string_literal210=(Token)match(input,90,FOLLOW_90_in_presetcondition2114);  
							stream_90.add(string_literal210);

							WhiteChar211=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2116);  
							stream_WhiteChar.add(WhiteChar211);

							pushFollow(FOLLOW_constant_in_presetcondition2120);
							percentdown=constant();
							state._fsp--;

							stream_constant.add(percentdown.getTree());
							PERCENT212=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2122);  
							stream_PERCENT.add(PERCENT212);

							WhiteChar213=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2124);  
							stream_WhiteChar.add(WhiteChar213);

							string_literal214=(Token)match(input,97,FOLLOW_97_in_presetcondition2126);  
							stream_97.add(string_literal214);

							WhiteChar215=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2128);  
							stream_WhiteChar.add(WhiteChar215);

							pushFollow(FOLLOW_constant_in_presetcondition2132);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar216=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2134);  
							stream_WhiteChar.add(WhiteChar216);

							DAYS217=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2136);  
							stream_DAYS.add(DAYS217);

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
							// 268:7: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:268:10: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:268:29: ^( Number NumberToken[\"-1\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (percentdown!=null?((CommonTree)percentdown.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, firstOp);
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;

					}

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:4: 'reverses up'
					{
					string_literal218=(Token)match(input,94,FOLLOW_94_in_presetcondition2180);  
					stream_94.add(string_literal218);

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
					// 270:18: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:21: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:40: ^( Number NumberToken[\"1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:67: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:96: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:7: ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					int alt38=2;
					int LA38_0 = input.LA(1);
					if ( (LA38_0==WhiteChar) ) {
						int LA38_1 = input.LA(2);
						if ( (LA38_1==90) ) {
							alt38=1;
						}
					}
					switch (alt38) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:9: WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar219=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2220);  
							stream_WhiteChar.add(WhiteChar219);

							string_literal220=(Token)match(input,90,FOLLOW_90_in_presetcondition2222);  
							stream_90.add(string_literal220);

							WhiteChar221=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2224);  
							stream_WhiteChar.add(WhiteChar221);

							pushFollow(FOLLOW_constant_in_presetcondition2228);
							percentup=constant();
							state._fsp--;

							stream_constant.add(percentup.getTree());
							PERCENT222=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2230);  
							stream_PERCENT.add(PERCENT222);

							WhiteChar223=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2232);  
							stream_WhiteChar.add(WhiteChar223);

							string_literal224=(Token)match(input,97,FOLLOW_97_in_presetcondition2234);  
							stream_97.add(string_literal224);

							WhiteChar225=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2236);  
							stream_WhiteChar.add(WhiteChar225);

							pushFollow(FOLLOW_constant_in_presetcondition2240);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar226=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2242);  
							stream_WhiteChar.add(WhiteChar226);

							DAYS227=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2244);  
							stream_DAYS.add(DAYS227);

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
							// 272:7: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:272:10: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:272:29: ^( Number NumberToken[\"1\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (percentup!=null?((CommonTree)percentup.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, firstOp);
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;

					}

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:4: 'goes down more than' WhiteChar percentdown= constant PERCENT
					{
					string_literal228=(Token)match(input,75,FOLLOW_75_in_presetcondition2287);  
					stream_75.add(string_literal228);

					WhiteChar229=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2289);  
					stream_WhiteChar.add(WhiteChar229);

					pushFollow(FOLLOW_constant_in_presetcondition2293);
					percentdown=constant();
					state._fsp--;

					stream_constant.add(percentdown.getTree());
					PERCENT230=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2295);  
					stream_PERCENT.add(PERCENT230);

					// AST REWRITE
					// elements: constant
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 274:65: -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:68: ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:98: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:127: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:156: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt39=2;
					int LA39_0 = input.LA(1);
					if ( (LA39_0==WhiteChar) ) {
						int LA39_1 = input.LA(2);
						if ( (LA39_1==97) ) {
							alt39=1;
						}
					}
					switch (alt39) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar231=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2337);  
							stream_WhiteChar.add(WhiteChar231);

							string_literal232=(Token)match(input,97,FOLLOW_97_in_presetcondition2339);  
							stream_97.add(string_literal232);

							WhiteChar233=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2341);  
							stream_WhiteChar.add(WhiteChar233);

							pushFollow(FOLLOW_constant_in_presetcondition2345);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar234=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2347);  
							stream_WhiteChar.add(WhiteChar234);

							DAYS235=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2349);  
							stream_DAYS.add(DAYS235);

							WhiteChar236=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2360);  
							stream_WhiteChar.add(WhiteChar236);

							string_literal237=(Token)match(input,74,FOLLOW_74_in_presetcondition2362);  
							stream_74.add(string_literal237);

							WhiteChar238=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2364);  
							stream_WhiteChar.add(WhiteChar238);

							pushFollow(FOLLOW_constant_in_presetcondition2368);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar239=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2370);  
							stream_WhiteChar.add(WhiteChar239);

							DAYS240=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2372);  
							stream_DAYS.add(DAYS240);

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
							// 277:7: -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:277:10: ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentdown!=null?((CommonTree)percentdown.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:277:75: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								adaptor.addChild(root_1, firstOp);
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;

					}

					}
					break;
				case 4 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:4: 'goes up more than' WhiteChar percentup= constant PERCENT
					{
					string_literal241=(Token)match(input,76,FOLLOW_76_in_presetcondition2410);  
					stream_76.add(string_literal241);

					WhiteChar242=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2412);  
					stream_WhiteChar.add(WhiteChar242);

					pushFollow(FOLLOW_constant_in_presetcondition2416);
					percentup=constant();
					state._fsp--;

					stream_constant.add(percentup.getTree());
					PERCENT243=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2418);  
					stream_PERCENT.add(PERCENT243);

					// AST REWRITE
					// elements: constant
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 278:61: -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:64: ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:92: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:121: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:150: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:279:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt40=2;
					int LA40_0 = input.LA(1);
					if ( (LA40_0==WhiteChar) ) {
						int LA40_1 = input.LA(2);
						if ( (LA40_1==97) ) {
							alt40=1;
						}
					}
					switch (alt40) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:279:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar244=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2460);  
							stream_WhiteChar.add(WhiteChar244);

							string_literal245=(Token)match(input,97,FOLLOW_97_in_presetcondition2462);  
							stream_97.add(string_literal245);

							WhiteChar246=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2464);  
							stream_WhiteChar.add(WhiteChar246);

							pushFollow(FOLLOW_constant_in_presetcondition2468);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar247=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2470);  
							stream_WhiteChar.add(WhiteChar247);

							DAYS248=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2472);  
							stream_DAYS.add(DAYS248);

							WhiteChar249=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2483);  
							stream_WhiteChar.add(WhiteChar249);

							string_literal250=(Token)match(input,74,FOLLOW_74_in_presetcondition2485);  
							stream_74.add(string_literal250);

							WhiteChar251=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2487);  
							stream_WhiteChar.add(WhiteChar251);

							pushFollow(FOLLOW_constant_in_presetcondition2491);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar252=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2493);  
							stream_WhiteChar.add(WhiteChar252);

							DAYS253=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2495);  
							stream_DAYS.add(DAYS253);

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
							// 281:7: -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:10: ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentup!=null?((CommonTree)percentup.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:71: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								adaptor.addChild(root_1, firstOp);
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;

					}

					}
					break;
				case 5 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:283:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:283:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:283:4: 'crosses up threshold' WhiteChar threshold= constant
					{
					string_literal254=(Token)match(input,67,FOLLOW_67_in_presetcondition2540);  
					stream_67.add(string_literal254);

					WhiteChar255=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2542);  
					stream_WhiteChar.add(WhiteChar255);

					pushFollow(FOLLOW_constant_in_presetcondition2546);
					threshold=constant();
					state._fsp--;

					stream_constant.add(threshold.getTree());
					// AST REWRITE
					// elements: constant
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 283:56: -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:283:59: ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:283:95: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:283:124: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:283:153: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:284:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt41=2;
					int LA41_0 = input.LA(1);
					if ( (LA41_0==WhiteChar) ) {
						int LA41_1 = input.LA(2);
						if ( (LA41_1==97) ) {
							alt41=1;
						}
					}
					switch (alt41) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:284:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar256=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2588);  
							stream_WhiteChar.add(WhiteChar256);

							string_literal257=(Token)match(input,97,FOLLOW_97_in_presetcondition2590);  
							stream_97.add(string_literal257);

							WhiteChar258=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2592);  
							stream_WhiteChar.add(WhiteChar258);

							pushFollow(FOLLOW_constant_in_presetcondition2596);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar259=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2598);  
							stream_WhiteChar.add(WhiteChar259);

							DAYS260=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2600);  
							stream_DAYS.add(DAYS260);

							WhiteChar261=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2611);  
							stream_WhiteChar.add(WhiteChar261);

							string_literal262=(Token)match(input,91,FOLLOW_91_in_presetcondition2613);  
							stream_91.add(string_literal262);

							WhiteChar263=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2615);  
							stream_WhiteChar.add(WhiteChar263);

							pushFollow(FOLLOW_constant_in_presetcondition2619);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar264=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2621);  
							stream_WhiteChar.add(WhiteChar264);

							DAYS265=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2623);  
							stream_DAYS.add(DAYS265);

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
							// 286:7: -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:10: ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:97: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, firstOp);
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;

					}

					}
					break;
				case 6 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:4: 'crosses down threshold' WhiteChar threshold= constant
					{
					string_literal266=(Token)match(input,65,FOLLOW_65_in_presetcondition2669);  
					stream_65.add(string_literal266);

					WhiteChar267=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2671);  
					stream_WhiteChar.add(WhiteChar267);

					pushFollow(FOLLOW_constant_in_presetcondition2675);
					threshold=constant();
					state._fsp--;

					stream_constant.add(threshold.getTree());
					// AST REWRITE
					// elements: constant
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 288:58: -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:61: ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:99: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:128: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:157: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt42=2;
					int LA42_0 = input.LA(1);
					if ( (LA42_0==WhiteChar) ) {
						int LA42_1 = input.LA(2);
						if ( (LA42_1==97) ) {
							alt42=1;
						}
					}
					switch (alt42) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar268=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2717);  
							stream_WhiteChar.add(WhiteChar268);

							string_literal269=(Token)match(input,97,FOLLOW_97_in_presetcondition2719);  
							stream_97.add(string_literal269);

							WhiteChar270=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2721);  
							stream_WhiteChar.add(WhiteChar270);

							pushFollow(FOLLOW_constant_in_presetcondition2725);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar271=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2727);  
							stream_WhiteChar.add(WhiteChar271);

							DAYS272=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2729);  
							stream_DAYS.add(DAYS272);

							WhiteChar273=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2740);  
							stream_WhiteChar.add(WhiteChar273);

							string_literal274=(Token)match(input,91,FOLLOW_91_in_presetcondition2742);  
							stream_91.add(string_literal274);

							WhiteChar275=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2744);  
							stream_WhiteChar.add(WhiteChar275);

							pushFollow(FOLLOW_constant_in_presetcondition2748);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar276=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2750);  
							stream_WhiteChar.add(WhiteChar276);

							DAYS277=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2752);  
							stream_DAYS.add(DAYS277);

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
							// 291:7: -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:10: ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:99: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, firstOp);
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;

					}

					}
					break;
				case 7 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:4: 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal278=(Token)match(input,84,FOLLOW_84_in_presetcondition2790);  
					stream_84.add(string_literal278);

					WhiteChar279=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2792);  
					stream_WhiteChar.add(WhiteChar279);

					pushFollow(FOLLOW_constant_in_presetcondition2796);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar280=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2798);  
					stream_WhiteChar.add(WhiteChar280);

					DAYS281=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2800);  
					stream_DAYS.add(DAYS281);

					WhiteChar282=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2807);  
					stream_WhiteChar.add(WhiteChar282);

					string_literal283=(Token)match(input,91,FOLLOW_91_in_presetcondition2809);  
					stream_91.add(string_literal283);

					WhiteChar284=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2811);  
					stream_WhiteChar.add(WhiteChar284);

					pushFollow(FOLLOW_constant_in_presetcondition2815);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar285=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2817);  
					stream_WhiteChar.add(WhiteChar285);

					DAYS286=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2819);  
					stream_DAYS.add(DAYS286);

					WhiteChar287=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2826);  
					stream_WhiteChar.add(WhiteChar287);

					string_literal288=(Token)match(input,74,FOLLOW_74_in_presetcondition2828);  
					stream_74.add(string_literal288);

					WhiteChar289=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2830);  
					stream_WhiteChar.add(WhiteChar289);

					pushFollow(FOLLOW_constant_in_presetcondition2834);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar290=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2836);  
					stream_WhiteChar.add(WhiteChar290);

					DAYS291=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2838);  
					stream_DAYS.add(DAYS291);

					WhiteChar292=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2845);  
					stream_WhiteChar.add(WhiteChar292);

					string_literal293=(Token)match(input,96,FOLLOW_96_in_presetcondition2847);  
					stream_96.add(string_literal293);

					WhiteChar294=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2849);  
					stream_WhiteChar.add(WhiteChar294);

					pushFollow(FOLLOW_constant_in_presetcondition2853);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar295=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2855);  
					stream_WhiteChar.add(WhiteChar295);

					DAYS296=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2857);  
					stream_DAYS.add(DAYS296);

					WhiteChar297=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2864);  
					stream_WhiteChar.add(WhiteChar297);

					string_literal298=(Token)match(input,98,FOLLOW_98_in_presetcondition2866);  
					stream_98.add(string_literal298);

					WhiteChar299=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2868);  
					stream_WhiteChar.add(WhiteChar299);

					char_literal300=(Token)match(input,59,FOLLOW_59_in_presetcondition2870);  
					stream_59.add(char_literal300);

					pushFollow(FOLLOW_constant_in_presetcondition2874);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal301=(Token)match(input,57,FOLLOW_57_in_presetcondition2876);  
					stream_57.add(char_literal301);

					pushFollow(FOLLOW_constant_in_presetcondition2880);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal302=(Token)match(input,60,FOLLOW_60_in_presetcondition2882);  
					stream_60.add(char_literal302);

					WhiteChar303=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2884);  
					stream_WhiteChar.add(WhiteChar303);

					string_literal304=(Token)match(input,69,FOLLOW_69_in_presetcondition2886);  
					stream_69.add(string_literal304);

					WhiteChar305=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2888);  
					stream_WhiteChar.add(WhiteChar305);

					char_literal306=(Token)match(input,59,FOLLOW_59_in_presetcondition2890);  
					stream_59.add(char_literal306);

					pushFollow(FOLLOW_constant_in_presetcondition2894);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal307=(Token)match(input,57,FOLLOW_57_in_presetcondition2896);  
					stream_57.add(char_literal307);

					pushFollow(FOLLOW_constant_in_presetcondition2900);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal308=(Token)match(input,60,FOLLOW_60_in_presetcondition2902);  
					stream_60.add(char_literal308);

					WhiteChar309=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2908);  
					stream_WhiteChar.add(WhiteChar309);

					string_literal310=(Token)match(input,95,FOLLOW_95_in_presetcondition2910);  
					stream_95.add(string_literal310);

					WhiteChar311=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2912);  
					stream_WhiteChar.add(WhiteChar311);

					char_literal312=(Token)match(input,59,FOLLOW_59_in_presetcondition2914);  
					stream_59.add(char_literal312);

					pushFollow(FOLLOW_constant_in_presetcondition2918);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal313=(Token)match(input,57,FOLLOW_57_in_presetcondition2920);  
					stream_57.add(char_literal313);

					pushFollow(FOLLOW_constant_in_presetcondition2924);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal314=(Token)match(input,60,FOLLOW_60_in_presetcondition2926);  
					stream_60.add(char_literal314);

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
					// 299:4: -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:7: ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(HigherHighCondition, "HigherHighCondition"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						adaptor.addChild(root_1, (lowestEnd!=null?((CommonTree)lowestEnd.getTree()):null));
						adaptor.addChild(root_1, (highestEnd!=null?((CommonTree)highestEnd.getTree()):null));
						adaptor.addChild(root_1, (minSlope!=null?((CommonTree)minSlope.getTree()):null));
						adaptor.addChild(root_1, (maxSlope!=null?((CommonTree)maxSlope.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:219: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					}
					break;
				case 8 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:300:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:300:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:300:4: 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal315=(Token)match(input,85,FOLLOW_85_in_presetcondition2973);  
					stream_85.add(string_literal315);

					WhiteChar316=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2975);  
					stream_WhiteChar.add(WhiteChar316);

					pushFollow(FOLLOW_constant_in_presetcondition2979);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar317=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2981);  
					stream_WhiteChar.add(WhiteChar317);

					DAYS318=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2983);  
					stream_DAYS.add(DAYS318);

					WhiteChar319=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2990);  
					stream_WhiteChar.add(WhiteChar319);

					string_literal320=(Token)match(input,91,FOLLOW_91_in_presetcondition2992);  
					stream_91.add(string_literal320);

					WhiteChar321=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2994);  
					stream_WhiteChar.add(WhiteChar321);

					pushFollow(FOLLOW_constant_in_presetcondition2998);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar322=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3000);  
					stream_WhiteChar.add(WhiteChar322);

					DAYS323=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3002);  
					stream_DAYS.add(DAYS323);

					WhiteChar324=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3009);  
					stream_WhiteChar.add(WhiteChar324);

					string_literal325=(Token)match(input,74,FOLLOW_74_in_presetcondition3011);  
					stream_74.add(string_literal325);

					WhiteChar326=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3013);  
					stream_WhiteChar.add(WhiteChar326);

					pushFollow(FOLLOW_constant_in_presetcondition3017);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar327=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3019);  
					stream_WhiteChar.add(WhiteChar327);

					DAYS328=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3021);  
					stream_DAYS.add(DAYS328);

					WhiteChar329=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3028);  
					stream_WhiteChar.add(WhiteChar329);

					string_literal330=(Token)match(input,96,FOLLOW_96_in_presetcondition3030);  
					stream_96.add(string_literal330);

					WhiteChar331=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3032);  
					stream_WhiteChar.add(WhiteChar331);

					pushFollow(FOLLOW_constant_in_presetcondition3036);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar332=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3038);  
					stream_WhiteChar.add(WhiteChar332);

					DAYS333=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3040);  
					stream_DAYS.add(DAYS333);

					WhiteChar334=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3047);  
					stream_WhiteChar.add(WhiteChar334);

					string_literal335=(Token)match(input,98,FOLLOW_98_in_presetcondition3049);  
					stream_98.add(string_literal335);

					WhiteChar336=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3051);  
					stream_WhiteChar.add(WhiteChar336);

					char_literal337=(Token)match(input,59,FOLLOW_59_in_presetcondition3053);  
					stream_59.add(char_literal337);

					pushFollow(FOLLOW_constant_in_presetcondition3057);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal338=(Token)match(input,57,FOLLOW_57_in_presetcondition3059);  
					stream_57.add(char_literal338);

					pushFollow(FOLLOW_constant_in_presetcondition3063);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal339=(Token)match(input,60,FOLLOW_60_in_presetcondition3065);  
					stream_60.add(char_literal339);

					WhiteChar340=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3067);  
					stream_WhiteChar.add(WhiteChar340);

					string_literal341=(Token)match(input,69,FOLLOW_69_in_presetcondition3069);  
					stream_69.add(string_literal341);

					WhiteChar342=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3071);  
					stream_WhiteChar.add(WhiteChar342);

					char_literal343=(Token)match(input,59,FOLLOW_59_in_presetcondition3073);  
					stream_59.add(char_literal343);

					pushFollow(FOLLOW_constant_in_presetcondition3077);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal344=(Token)match(input,57,FOLLOW_57_in_presetcondition3079);  
					stream_57.add(char_literal344);

					pushFollow(FOLLOW_constant_in_presetcondition3083);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal345=(Token)match(input,60,FOLLOW_60_in_presetcondition3085);  
					stream_60.add(char_literal345);

					WhiteChar346=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3091);  
					stream_WhiteChar.add(WhiteChar346);

					string_literal347=(Token)match(input,95,FOLLOW_95_in_presetcondition3093);  
					stream_95.add(string_literal347);

					WhiteChar348=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3095);  
					stream_WhiteChar.add(WhiteChar348);

					char_literal349=(Token)match(input,59,FOLLOW_59_in_presetcondition3097);  
					stream_59.add(char_literal349);

					pushFollow(FOLLOW_constant_in_presetcondition3101);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal350=(Token)match(input,57,FOLLOW_57_in_presetcondition3103);  
					stream_57.add(char_literal350);

					pushFollow(FOLLOW_constant_in_presetcondition3107);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal351=(Token)match(input,60,FOLLOW_60_in_presetcondition3109);  
					stream_60.add(char_literal351);

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
					// 306:4: -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:306:7: ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(HigherLowCondition, "HigherLowCondition"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						adaptor.addChild(root_1, (lowestEnd!=null?((CommonTree)lowestEnd.getTree()):null));
						adaptor.addChild(root_1, (highestEnd!=null?((CommonTree)highestEnd.getTree()):null));
						adaptor.addChild(root_1, (minSlope!=null?((CommonTree)minSlope.getTree()):null));
						adaptor.addChild(root_1, (maxSlope!=null?((CommonTree)maxSlope.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:306:218: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					}
					break;
				case 9 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:307:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:307:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:307:4: 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal352=(Token)match(input,86,FOLLOW_86_in_presetcondition3156);  
					stream_86.add(string_literal352);

					WhiteChar353=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3158);  
					stream_WhiteChar.add(WhiteChar353);

					pushFollow(FOLLOW_constant_in_presetcondition3162);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar354=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3164);  
					stream_WhiteChar.add(WhiteChar354);

					DAYS355=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3166);  
					stream_DAYS.add(DAYS355);

					WhiteChar356=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3173);  
					stream_WhiteChar.add(WhiteChar356);

					string_literal357=(Token)match(input,91,FOLLOW_91_in_presetcondition3175);  
					stream_91.add(string_literal357);

					WhiteChar358=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3177);  
					stream_WhiteChar.add(WhiteChar358);

					pushFollow(FOLLOW_constant_in_presetcondition3181);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar359=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3183);  
					stream_WhiteChar.add(WhiteChar359);

					DAYS360=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3185);  
					stream_DAYS.add(DAYS360);

					WhiteChar361=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3192);  
					stream_WhiteChar.add(WhiteChar361);

					string_literal362=(Token)match(input,74,FOLLOW_74_in_presetcondition3194);  
					stream_74.add(string_literal362);

					WhiteChar363=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3196);  
					stream_WhiteChar.add(WhiteChar363);

					pushFollow(FOLLOW_constant_in_presetcondition3200);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar364=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3202);  
					stream_WhiteChar.add(WhiteChar364);

					DAYS365=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3204);  
					stream_DAYS.add(DAYS365);

					WhiteChar366=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3211);  
					stream_WhiteChar.add(WhiteChar366);

					string_literal367=(Token)match(input,96,FOLLOW_96_in_presetcondition3213);  
					stream_96.add(string_literal367);

					WhiteChar368=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3215);  
					stream_WhiteChar.add(WhiteChar368);

					pushFollow(FOLLOW_constant_in_presetcondition3219);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar369=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3221);  
					stream_WhiteChar.add(WhiteChar369);

					DAYS370=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3223);  
					stream_DAYS.add(DAYS370);

					WhiteChar371=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3230);  
					stream_WhiteChar.add(WhiteChar371);

					string_literal372=(Token)match(input,98,FOLLOW_98_in_presetcondition3232);  
					stream_98.add(string_literal372);

					WhiteChar373=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3234);  
					stream_WhiteChar.add(WhiteChar373);

					char_literal374=(Token)match(input,59,FOLLOW_59_in_presetcondition3236);  
					stream_59.add(char_literal374);

					pushFollow(FOLLOW_constant_in_presetcondition3240);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal375=(Token)match(input,57,FOLLOW_57_in_presetcondition3242);  
					stream_57.add(char_literal375);

					pushFollow(FOLLOW_constant_in_presetcondition3246);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal376=(Token)match(input,60,FOLLOW_60_in_presetcondition3248);  
					stream_60.add(char_literal376);

					WhiteChar377=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3250);  
					stream_WhiteChar.add(WhiteChar377);

					string_literal378=(Token)match(input,69,FOLLOW_69_in_presetcondition3252);  
					stream_69.add(string_literal378);

					WhiteChar379=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3254);  
					stream_WhiteChar.add(WhiteChar379);

					char_literal380=(Token)match(input,59,FOLLOW_59_in_presetcondition3256);  
					stream_59.add(char_literal380);

					pushFollow(FOLLOW_constant_in_presetcondition3260);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal381=(Token)match(input,57,FOLLOW_57_in_presetcondition3262);  
					stream_57.add(char_literal381);

					pushFollow(FOLLOW_constant_in_presetcondition3266);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal382=(Token)match(input,60,FOLLOW_60_in_presetcondition3268);  
					stream_60.add(char_literal382);

					WhiteChar383=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3274);  
					stream_WhiteChar.add(WhiteChar383);

					string_literal384=(Token)match(input,95,FOLLOW_95_in_presetcondition3276);  
					stream_95.add(string_literal384);

					WhiteChar385=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3278);  
					stream_WhiteChar.add(WhiteChar385);

					char_literal386=(Token)match(input,59,FOLLOW_59_in_presetcondition3280);  
					stream_59.add(char_literal386);

					pushFollow(FOLLOW_constant_in_presetcondition3284);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal387=(Token)match(input,57,FOLLOW_57_in_presetcondition3286);  
					stream_57.add(char_literal387);

					pushFollow(FOLLOW_constant_in_presetcondition3290);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal388=(Token)match(input,60,FOLLOW_60_in_presetcondition3292);  
					stream_60.add(char_literal388);

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
					// 313:4: -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:7: ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LowerHighCondition, "LowerHighCondition"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						adaptor.addChild(root_1, (lowestEnd!=null?((CommonTree)lowestEnd.getTree()):null));
						adaptor.addChild(root_1, (highestEnd!=null?((CommonTree)highestEnd.getTree()):null));
						adaptor.addChild(root_1, (minSlope!=null?((CommonTree)minSlope.getTree()):null));
						adaptor.addChild(root_1, (maxSlope!=null?((CommonTree)maxSlope.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:218: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					}
					break;
				case 10 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:4: 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal389=(Token)match(input,87,FOLLOW_87_in_presetcondition3339);  
					stream_87.add(string_literal389);

					WhiteChar390=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3341);  
					stream_WhiteChar.add(WhiteChar390);

					pushFollow(FOLLOW_constant_in_presetcondition3345);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar391=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3347);  
					stream_WhiteChar.add(WhiteChar391);

					DAYS392=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3349);  
					stream_DAYS.add(DAYS392);

					WhiteChar393=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3356);  
					stream_WhiteChar.add(WhiteChar393);

					string_literal394=(Token)match(input,91,FOLLOW_91_in_presetcondition3358);  
					stream_91.add(string_literal394);

					WhiteChar395=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3360);  
					stream_WhiteChar.add(WhiteChar395);

					pushFollow(FOLLOW_constant_in_presetcondition3364);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar396=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3366);  
					stream_WhiteChar.add(WhiteChar396);

					DAYS397=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3368);  
					stream_DAYS.add(DAYS397);

					WhiteChar398=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3375);  
					stream_WhiteChar.add(WhiteChar398);

					string_literal399=(Token)match(input,74,FOLLOW_74_in_presetcondition3377);  
					stream_74.add(string_literal399);

					WhiteChar400=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3379);  
					stream_WhiteChar.add(WhiteChar400);

					pushFollow(FOLLOW_constant_in_presetcondition3383);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar401=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3385);  
					stream_WhiteChar.add(WhiteChar401);

					DAYS402=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3387);  
					stream_DAYS.add(DAYS402);

					WhiteChar403=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3394);  
					stream_WhiteChar.add(WhiteChar403);

					string_literal404=(Token)match(input,96,FOLLOW_96_in_presetcondition3396);  
					stream_96.add(string_literal404);

					WhiteChar405=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3398);  
					stream_WhiteChar.add(WhiteChar405);

					pushFollow(FOLLOW_constant_in_presetcondition3402);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar406=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3404);  
					stream_WhiteChar.add(WhiteChar406);

					DAYS407=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3406);  
					stream_DAYS.add(DAYS407);

					WhiteChar408=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3413);  
					stream_WhiteChar.add(WhiteChar408);

					string_literal409=(Token)match(input,98,FOLLOW_98_in_presetcondition3415);  
					stream_98.add(string_literal409);

					WhiteChar410=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3417);  
					stream_WhiteChar.add(WhiteChar410);

					char_literal411=(Token)match(input,59,FOLLOW_59_in_presetcondition3419);  
					stream_59.add(char_literal411);

					pushFollow(FOLLOW_constant_in_presetcondition3423);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal412=(Token)match(input,57,FOLLOW_57_in_presetcondition3425);  
					stream_57.add(char_literal412);

					pushFollow(FOLLOW_constant_in_presetcondition3429);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal413=(Token)match(input,60,FOLLOW_60_in_presetcondition3431);  
					stream_60.add(char_literal413);

					WhiteChar414=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3433);  
					stream_WhiteChar.add(WhiteChar414);

					string_literal415=(Token)match(input,69,FOLLOW_69_in_presetcondition3435);  
					stream_69.add(string_literal415);

					WhiteChar416=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3437);  
					stream_WhiteChar.add(WhiteChar416);

					char_literal417=(Token)match(input,59,FOLLOW_59_in_presetcondition3439);  
					stream_59.add(char_literal417);

					pushFollow(FOLLOW_constant_in_presetcondition3443);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal418=(Token)match(input,57,FOLLOW_57_in_presetcondition3445);  
					stream_57.add(char_literal418);

					pushFollow(FOLLOW_constant_in_presetcondition3449);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal419=(Token)match(input,60,FOLLOW_60_in_presetcondition3451);  
					stream_60.add(char_literal419);

					WhiteChar420=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3457);  
					stream_WhiteChar.add(WhiteChar420);

					string_literal421=(Token)match(input,95,FOLLOW_95_in_presetcondition3459);  
					stream_95.add(string_literal421);

					WhiteChar422=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3461);  
					stream_WhiteChar.add(WhiteChar422);

					char_literal423=(Token)match(input,59,FOLLOW_59_in_presetcondition3463);  
					stream_59.add(char_literal423);

					pushFollow(FOLLOW_constant_in_presetcondition3467);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal424=(Token)match(input,57,FOLLOW_57_in_presetcondition3469);  
					stream_57.add(char_literal424);

					pushFollow(FOLLOW_constant_in_presetcondition3473);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal425=(Token)match(input,60,FOLLOW_60_in_presetcondition3475);  
					stream_60.add(char_literal425);

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
					// 320:4: -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:320:7: ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LowerLowCondition, "LowerLowCondition"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						adaptor.addChild(root_1, (lowestEnd!=null?((CommonTree)lowestEnd.getTree()):null));
						adaptor.addChild(root_1, (highestEnd!=null?((CommonTree)highestEnd.getTree()):null));
						adaptor.addChild(root_1, (minSlope!=null?((CommonTree)minSlope.getTree()):null));
						adaptor.addChild(root_1, (maxSlope!=null?((CommonTree)maxSlope.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:320:217: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					}
					break;
				case 11 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:4: ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:4: ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:5: 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant
					{
					string_literal426=(Token)match(input,88,FOLLOW_88_in_presetcondition3524);  
					stream_88.add(string_literal426);

					WhiteChar427=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3526);  
					stream_WhiteChar.add(WhiteChar427);

					pushFollow(FOLLOW_constant_in_presetcondition3530);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar428=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3532);  
					stream_WhiteChar.add(WhiteChar428);

					DAYS429=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3534);  
					stream_DAYS.add(DAYS429);

					WhiteChar430=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3541);  
					stream_WhiteChar.add(WhiteChar430);

					string_literal431=(Token)match(input,91,FOLLOW_91_in_presetcondition3543);  
					stream_91.add(string_literal431);

					WhiteChar432=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3545);  
					stream_WhiteChar.add(WhiteChar432);

					pushFollow(FOLLOW_constant_in_presetcondition3549);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar433=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3551);  
					stream_WhiteChar.add(WhiteChar433);

					DAYS434=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3553);  
					stream_DAYS.add(DAYS434);

					WhiteChar435=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3560);  
					stream_WhiteChar.add(WhiteChar435);

					string_literal436=(Token)match(input,74,FOLLOW_74_in_presetcondition3562);  
					stream_74.add(string_literal436);

					WhiteChar437=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3564);  
					stream_WhiteChar.add(WhiteChar437);

					pushFollow(FOLLOW_constant_in_presetcondition3568);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar438=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3570);  
					stream_WhiteChar.add(WhiteChar438);

					DAYS439=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3572);  
					stream_DAYS.add(DAYS439);

					WhiteChar440=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3579);  
					stream_WhiteChar.add(WhiteChar440);

					string_literal441=(Token)match(input,96,FOLLOW_96_in_presetcondition3581);  
					stream_96.add(string_literal441);

					WhiteChar442=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3583);  
					stream_WhiteChar.add(WhiteChar442);

					pushFollow(FOLLOW_constant_in_presetcondition3587);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar443=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3589);  
					stream_WhiteChar.add(WhiteChar443);

					DAYS444=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3591);  
					stream_DAYS.add(DAYS444);

					WhiteChar445=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3598);  
					stream_WhiteChar.add(WhiteChar445);

					string_literal446=(Token)match(input,98,FOLLOW_98_in_presetcondition3600);  
					stream_98.add(string_literal446);

					WhiteChar447=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3602);  
					stream_WhiteChar.add(WhiteChar447);

					char_literal448=(Token)match(input,59,FOLLOW_59_in_presetcondition3604);  
					stream_59.add(char_literal448);

					pushFollow(FOLLOW_constant_in_presetcondition3608);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal449=(Token)match(input,57,FOLLOW_57_in_presetcondition3610);  
					stream_57.add(char_literal449);

					pushFollow(FOLLOW_constant_in_presetcondition3614);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal450=(Token)match(input,60,FOLLOW_60_in_presetcondition3616);  
					stream_60.add(char_literal450);

					WhiteChar451=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3622);  
					stream_WhiteChar.add(WhiteChar451);

					string_literal452=(Token)match(input,99,FOLLOW_99_in_presetcondition3624);  
					stream_99.add(string_literal452);

					WhiteChar453=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3626);  
					stream_WhiteChar.add(WhiteChar453);

					pushFollow(FOLLOW_constant_in_presetcondition3630);
					tolerance=constant();
					state._fsp--;

					stream_constant.add(tolerance.getTree());
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
					// 328:4: -> ^( SupportBreakDown ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:328:7: ^( SupportBreakDown ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupportBreakDown, "SupportBreakDown"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:328:145: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:328:174: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:328:203: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:328:232: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (tolerance!=null?((CommonTree)tolerance.getTree()):null));
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					}
					break;
				case 12 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:330:4: ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:330:4: ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:330:5: 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant
					{
					string_literal454=(Token)match(input,89,FOLLOW_89_in_presetcondition3694);  
					stream_89.add(string_literal454);

					WhiteChar455=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3696);  
					stream_WhiteChar.add(WhiteChar455);

					pushFollow(FOLLOW_constant_in_presetcondition3700);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar456=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3702);  
					stream_WhiteChar.add(WhiteChar456);

					DAYS457=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3704);  
					stream_DAYS.add(DAYS457);

					WhiteChar458=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3714);  
					stream_WhiteChar.add(WhiteChar458);

					string_literal459=(Token)match(input,91,FOLLOW_91_in_presetcondition3716);  
					stream_91.add(string_literal459);

					WhiteChar460=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3718);  
					stream_WhiteChar.add(WhiteChar460);

					pushFollow(FOLLOW_constant_in_presetcondition3722);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar461=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3724);  
					stream_WhiteChar.add(WhiteChar461);

					DAYS462=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3726);  
					stream_DAYS.add(DAYS462);

					WhiteChar463=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3736);  
					stream_WhiteChar.add(WhiteChar463);

					string_literal464=(Token)match(input,74,FOLLOW_74_in_presetcondition3738);  
					stream_74.add(string_literal464);

					WhiteChar465=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3740);  
					stream_WhiteChar.add(WhiteChar465);

					pushFollow(FOLLOW_constant_in_presetcondition3744);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar466=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3746);  
					stream_WhiteChar.add(WhiteChar466);

					DAYS467=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3748);  
					stream_DAYS.add(DAYS467);

					WhiteChar468=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3758);  
					stream_WhiteChar.add(WhiteChar468);

					string_literal469=(Token)match(input,96,FOLLOW_96_in_presetcondition3760);  
					stream_96.add(string_literal469);

					WhiteChar470=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3762);  
					stream_WhiteChar.add(WhiteChar470);

					pushFollow(FOLLOW_constant_in_presetcondition3766);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar471=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3768);  
					stream_WhiteChar.add(WhiteChar471);

					DAYS472=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3770);  
					stream_DAYS.add(DAYS472);

					WhiteChar473=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3780);  
					stream_WhiteChar.add(WhiteChar473);

					string_literal474=(Token)match(input,98,FOLLOW_98_in_presetcondition3782);  
					stream_98.add(string_literal474);

					WhiteChar475=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3784);  
					stream_WhiteChar.add(WhiteChar475);

					char_literal476=(Token)match(input,59,FOLLOW_59_in_presetcondition3786);  
					stream_59.add(char_literal476);

					pushFollow(FOLLOW_constant_in_presetcondition3790);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal477=(Token)match(input,57,FOLLOW_57_in_presetcondition3792);  
					stream_57.add(char_literal477);

					pushFollow(FOLLOW_constant_in_presetcondition3796);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal478=(Token)match(input,60,FOLLOW_60_in_presetcondition3798);  
					stream_60.add(char_literal478);

					WhiteChar479=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3808);  
					stream_WhiteChar.add(WhiteChar479);

					string_literal480=(Token)match(input,99,FOLLOW_99_in_presetcondition3810);  
					stream_99.add(string_literal480);

					WhiteChar481=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3812);  
					stream_WhiteChar.add(WhiteChar481);

					pushFollow(FOLLOW_constant_in_presetcondition3816);
					tolerance=constant();
					state._fsp--;

					stream_constant.add(tolerance.getTree());
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
					// 336:6: -> ^( SupportBreakUp ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:336:9: ^( SupportBreakUp ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupportBreakUp, "SupportBreakUp"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:336:145: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:336:174: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:336:203: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:336:232: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (tolerance!=null?((CommonTree)tolerance.getTree()):null));
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					}
					break;
				case 13 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:338:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearFlatTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:338:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearFlatTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:338:4: 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal482=(Token)match(input,100,FOLLOW_100_in_presetcondition3881);  
					stream_100.add(string_literal482);

					WhiteChar483=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3889);  
					stream_WhiteChar.add(WhiteChar483);

					string_literal484=(Token)match(input,91,FOLLOW_91_in_presetcondition3891);  
					stream_91.add(string_literal484);

					WhiteChar485=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3893);  
					stream_WhiteChar.add(WhiteChar485);

					pushFollow(FOLLOW_constant_in_presetcondition3897);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar486=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3899);  
					stream_WhiteChar.add(WhiteChar486);

					DAYS487=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3901);  
					stream_DAYS.add(DAYS487);

					WhiteChar488=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3909);  
					stream_WhiteChar.add(WhiteChar488);

					string_literal489=(Token)match(input,74,FOLLOW_74_in_presetcondition3911);  
					stream_74.add(string_literal489);

					WhiteChar490=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3913);  
					stream_WhiteChar.add(WhiteChar490);

					pushFollow(FOLLOW_constant_in_presetcondition3917);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar491=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3919);  
					stream_WhiteChar.add(WhiteChar491);

					DAYS492=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3921);  
					stream_DAYS.add(DAYS492);

					WhiteChar493=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3929);  
					stream_WhiteChar.add(WhiteChar493);

					string_literal494=(Token)match(input,70,FOLLOW_70_in_presetcondition3931);  
					stream_70.add(string_literal494);

					WhiteChar495=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3933);  
					stream_WhiteChar.add(WhiteChar495);

					pushFollow(FOLLOW_constant_in_presetcondition3937);
					epsilon=constant();
					state._fsp--;

					stream_constant.add(epsilon.getTree());
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
					// 342:7: -> ^( LinearFlatTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:342:10: ^( LinearFlatTrendsCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearFlatTrendsCondition, "LinearFlatTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

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
	// $ANTLR end "presetcondition"

	// Delegated rules



	public static final BitSet FOLLOW_bullish_condition_in_complete_expression388 = new BitSet(new long[]{0x0000000000000000L,0x0000000000018000L});
	public static final BitSet FOLLOW_bearish_condition_in_complete_expression390 = new BitSet(new long[]{0x2000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_also_display_in_complete_expression393 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_fixed_start_shift_in_complete_expression395 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_83_in_bullish_condition424 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition426 = new BitSet(new long[]{0x0000014200200000L});
	public static final BitSet FOLLOW_primary_expression_in_bullish_condition428 = new BitSet(new long[]{0x0100000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition430 = new BitSet(new long[]{0x0100000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bullish_condition433 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition435 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_80_in_bearish_condition451 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition453 = new BitSet(new long[]{0x0000014200200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_condition455 = new BitSet(new long[]{0x0100000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition457 = new BitSet(new long[]{0x0100000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bearish_condition460 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition462 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_bearish_not_bullish_in_bearish_condition472 = new BitSet(new long[]{0x0100000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition475 = new BitSet(new long[]{0x0100000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bearish_condition478 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition480 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_61_in_also_display497 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display499 = new BitSet(new long[]{0x0000014200200000L});
	public static final BitSet FOLLOW_primary_expression_in_also_display501 = new BitSet(new long[]{0x0100000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display503 = new BitSet(new long[]{0x0100000000000080L});
	public static final BitSet FOLLOW_COMMA_in_also_display506 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_92_in_fixed_start_shift541 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift543 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_fixed_start_shift547 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift549 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_fixed_start_shift551 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_COMMA_in_fixed_start_shift553 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_79_in_bearish_not_bullish583 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish592 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_bearish_not_bullish594 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish596 = new BitSet(new long[]{0x0000014200200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish598 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish624 = new BitSet(new long[]{0x0000008000000000L});
	public static final BitSet FOLLOW_OR_in_bearish_not_bullish626 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish628 = new BitSet(new long[]{0x0000014200200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish630 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_and_expression_in_primary_expression671 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_expression_in_and_expression682 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_lenient_in_and_expression686 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression689 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_and_expression691 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression693 = new BitSet(new long[]{0x0000014200200000L});
	public static final BitSet FOLLOW_or_expression_in_and_expression695 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_matches_expression_in_or_expression722 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression725 = new BitSet(new long[]{0x0000008000000000L});
	public static final BitSet FOLLOW_OR_in_or_expression727 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression729 = new BitSet(new long[]{0x0000014200200000L});
	public static final BitSet FOLLOW_matches_expression_in_or_expression731 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_atom_in_matches_expression756 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression759 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_MATCHES_in_matches_expression761 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression763 = new BitSet(new long[]{0x0000014200200000L});
	public static final BitSet FOLLOW_atom_in_matches_expression765 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_booleanhistory_in_atom791 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom797 = new BitSet(new long[]{0x0100014200200000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom799 = new BitSet(new long[]{0x0100014200200000L});
	public static final BitSet FOLLOW_primary_expression_in_atom802 = new BitSet(new long[]{0x0100000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom804 = new BitSet(new long[]{0x0100000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom807 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_atom817 = new BitSet(new long[]{0x0100004000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom819 = new BitSet(new long[]{0x0100004000000000L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom822 = new BitSet(new long[]{0x0100014200200000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom824 = new BitSet(new long[]{0x0100014200200000L});
	public static final BitSet FOLLOW_primary_expression_in_atom827 = new BitSet(new long[]{0x0100000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom829 = new BitSet(new long[]{0x0100000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom832 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_booleanhistory855 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_booleanhistory857 = new BitSet(new long[]{0x0000000000000000L,0x0000007063F67B8FL});
	public static final BitSet FOLLOW_presetcondition_in_booleanhistory863 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_opcmpcondition_in_booleanhistory874 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constantcmp_in_booleanhistory885 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_operand900 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Operation_in_operand927 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NumberToken_in_constant941 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_58_in_constant953 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_StringToken_in_stringconstant969 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_63_in_trendconstant984 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_62_in_trendconstant997 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_lenient1014 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_LENIENT_in_lenient1016 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_77_in_opcmpcondition1053 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1055 = new BitSet(new long[]{0x0000010000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1059 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1085 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_opcmpcondition1087 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1089 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1093 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1095 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1097 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_81_in_opcmpcondition1119 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1121 = new BitSet(new long[]{0x0000010000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1125 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1153 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_opcmpcondition1155 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1157 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1161 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1163 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1165 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_71_in_opcmpcondition1187 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1189 = new BitSet(new long[]{0x0000010000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1193 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1220 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_opcmpcondition1222 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1224 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1228 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1230 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1232 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_64_in_opcmpcondition1255 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1257 = new BitSet(new long[]{0x0000010000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1259 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1296 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_opcmpcondition1298 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1300 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1304 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1306 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1308 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1321 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_opcmpcondition1323 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1325 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1329 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1331 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1333 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_66_in_opcmpcondition1369 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1371 = new BitSet(new long[]{0x0000010000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1373 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1409 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_opcmpcondition1411 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1413 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1417 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1419 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1421 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1432 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_opcmpcondition1434 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1436 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1440 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1442 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1444 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_101_in_opcmpcondition1479 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1481 = new BitSet(new long[]{0x0000010000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1485 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1493 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_opcmpcondition1495 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1497 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1501 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1503 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1505 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1513 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_opcmpcondition1515 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1517 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1521 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1523 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1525 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1533 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_68_in_opcmpcondition1535 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1537 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition1541 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1549 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_opcmpcondition1551 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1553 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1557 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_102_in_opcmpcondition1590 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1592 = new BitSet(new long[]{0x0000010000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1596 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1604 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_opcmpcondition1606 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1608 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1612 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1614 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1616 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1624 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_opcmpcondition1626 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1628 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1632 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1634 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1636 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1644 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_68_in_opcmpcondition1646 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1648 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition1652 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_73_in_constantcmp1690 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1692 = new BitSet(new long[]{0xC000000000000000L});
	public static final BitSet FOLLOW_trendconstant_in_constantcmp1696 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1730 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_constantcmp1732 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1734 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1738 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1740 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1742 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1744 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_constantcmp1746 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1748 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1752 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1754 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1756 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_72_in_constantcmp1784 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1786 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1790 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1824 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_constantcmp1826 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1828 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1832 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1834 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1836 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1838 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_constantcmp1840 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1842 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1846 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1848 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1850 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_78_in_constantcmp1879 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1881 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1885 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1919 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_constantcmp1921 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1923 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1927 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1929 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1931 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1933 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_constantcmp1935 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1937 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1941 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1943 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1945 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_82_in_constantcmp1974 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1976 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1980 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2014 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_constantcmp2016 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2018 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2022 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2024 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2026 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2028 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_constantcmp2030 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2032 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2036 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2038 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2040 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_93_in_presetcondition2072 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2112 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_90_in_presetcondition2114 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2116 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2120 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2122 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2124 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_presetcondition2126 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2128 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2132 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2134 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2136 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_94_in_presetcondition2180 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2220 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_90_in_presetcondition2222 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2224 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2228 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2230 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2232 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_presetcondition2234 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2236 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2240 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2242 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2244 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_75_in_presetcondition2287 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2289 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2293 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2295 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2337 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_presetcondition2339 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2341 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2345 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2347 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2349 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2360 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition2362 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2364 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2368 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2370 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2372 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_76_in_presetcondition2410 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2412 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2416 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2418 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2460 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_presetcondition2462 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2464 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2468 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2470 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2472 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2483 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition2485 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2487 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2491 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2493 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2495 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_67_in_presetcondition2540 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2542 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2546 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2588 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_presetcondition2590 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2592 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2596 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2598 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2600 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2611 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_presetcondition2613 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2615 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2619 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2621 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2623 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition2669 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2671 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2675 = new BitSet(new long[]{0x0100000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2717 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_presetcondition2719 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2721 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2725 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2727 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2729 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2740 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_presetcondition2742 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2744 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2748 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2750 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2752 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_84_in_presetcondition2790 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2792 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2796 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2798 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2800 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2807 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_presetcondition2809 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2811 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2815 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2817 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2819 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2826 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition2828 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2830 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2834 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2836 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2838 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2845 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition2847 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2849 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2853 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2855 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2857 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2864 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition2866 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2868 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_59_in_presetcondition2870 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2874 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_57_in_presetcondition2876 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2880 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition2882 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2884 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_presetcondition2886 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2888 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_59_in_presetcondition2890 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2894 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_57_in_presetcondition2896 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2900 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition2902 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2908 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition2910 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2912 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_59_in_presetcondition2914 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2918 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_57_in_presetcondition2920 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2924 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition2926 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_85_in_presetcondition2973 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2975 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2979 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2981 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2983 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2990 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_presetcondition2992 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2994 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2998 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3000 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3002 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3009 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition3011 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3013 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3017 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3019 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3021 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3028 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3030 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3032 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3036 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3038 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3040 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3047 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition3049 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3051 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_59_in_presetcondition3053 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3057 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_57_in_presetcondition3059 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3063 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition3065 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3067 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_presetcondition3069 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3071 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_59_in_presetcondition3073 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3077 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_57_in_presetcondition3079 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3083 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition3085 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3091 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition3093 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3095 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_59_in_presetcondition3097 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3101 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_57_in_presetcondition3103 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3107 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition3109 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_86_in_presetcondition3156 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3158 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3162 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3164 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3166 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3173 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_presetcondition3175 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3177 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3181 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3183 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3185 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3192 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition3194 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3196 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3200 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3202 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3204 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3211 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3213 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3215 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3219 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3221 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3223 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3230 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition3232 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3234 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_59_in_presetcondition3236 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3240 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_57_in_presetcondition3242 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3246 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition3248 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3250 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_presetcondition3252 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3254 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_59_in_presetcondition3256 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3260 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_57_in_presetcondition3262 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3266 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition3268 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3274 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition3276 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3278 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_59_in_presetcondition3280 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3284 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_57_in_presetcondition3286 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3290 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition3292 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_87_in_presetcondition3339 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3341 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3345 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3347 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3349 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3356 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_presetcondition3358 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3360 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3364 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3366 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3368 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3375 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition3377 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3379 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3383 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3385 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3387 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3394 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3396 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3398 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3402 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3404 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3406 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3413 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition3415 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3417 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_59_in_presetcondition3419 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3423 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_57_in_presetcondition3425 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3429 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition3431 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3433 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_presetcondition3435 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3437 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_59_in_presetcondition3439 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3443 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_57_in_presetcondition3445 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3449 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition3451 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3457 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition3459 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3461 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_59_in_presetcondition3463 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3467 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_57_in_presetcondition3469 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3473 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition3475 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_88_in_presetcondition3524 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3526 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3530 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3532 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3534 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3541 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_presetcondition3543 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3545 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3549 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3551 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3553 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3560 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition3562 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3564 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3568 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3570 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3572 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3579 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3581 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3583 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3587 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3589 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3591 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3598 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition3600 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3602 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_59_in_presetcondition3604 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3608 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_57_in_presetcondition3610 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3614 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition3616 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3622 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_99_in_presetcondition3624 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3626 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3630 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_89_in_presetcondition3694 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3696 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3700 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3702 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3704 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3714 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_presetcondition3716 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3718 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3722 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3724 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3726 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3736 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition3738 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3740 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3744 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3746 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3748 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3758 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3760 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3762 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3766 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3768 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3770 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3780 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition3782 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3784 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_59_in_presetcondition3786 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3790 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_57_in_presetcondition3792 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3796 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition3798 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3808 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_99_in_presetcondition3810 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3812 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3816 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_100_in_presetcondition3881 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3889 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_presetcondition3891 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3893 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3897 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3899 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3901 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3909 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition3911 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3913 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3917 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3919 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3921 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3929 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_presetcondition3931 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3933 = new BitSet(new long[]{0x0400002000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3937 = new BitSet(new long[]{0x0000000000000002L});
}
