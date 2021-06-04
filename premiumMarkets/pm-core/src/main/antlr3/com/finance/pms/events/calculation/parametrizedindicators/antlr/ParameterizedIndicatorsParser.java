// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2021-05-29 20:10:42
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
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "AND", "AndBooleanMapCondition", 
		"CLOSEPARENTEHSIS", "CLOSESQRT", "COMMA", "COMMENT", "CrossDownConstantCondition", 
		"CrossDownDoubleMapCondition", "CrossUpConstantCondition", "CrossUpDoubleMapCondition", 
		"DAYS", "DownRatioCondition", "EqualConstantCondition", "EqualDoubleMapCondition", 
		"EqualStringConstantCondition", "EventInfoOpsCompoOperation", "HigherHighCondition", 
		"HigherLowCondition", "HistoricalData", "InfConstantCondition", "InfDoubleMapCondition", 
		"LENIENT", "LINE_COMMENT", "LinearDirectedTrendsCondition", "LinearOppositeTrendsCondition", 
		"LinearSimilarTrendsCondition", "LowerHighCondition", "LowerLowCondition", 
		"MATCHING", "MatchingBooleanMapCondition", "NOT", "NotBooleanMapCondition", 
		"NullCondition", "Number", "NumberToken", "OPENPARENTEHSIS", "OPENSQRT", 
		"OR", "Operation", "OperationOutput", "OrBooleanMapCondition", "PERCENT", 
		"PreAndSignalCondition", "ReverseCondition", "SEMICOLUMN", "StockOperation", 
		"String", "StringOperation", "StringToken", "SupConstantCondition", "SupDoubleMapCondition", 
		"SupportBreakDown", "SupportBreakUp", "TRUTHOF", "Tcheat", "TruthOfCondition", 
		"UpRatioCondition", "WITH", "WS", "WhiteChar", "'NaN'", "'adaptiveRate'", 
		"'also display'", "'bearish'", "'bullish'", "'crosses down historical'", 
		"'crosses down threshold'", "'crosses up historical'", "'crosses up threshold'", 
		"'direction'", "'ending within'", "'epsilon'", "'equals historical'", 
		"'equals threshold'", "'equals trend'", "'for'", "'goes down more than'", 
		"'goes up more than'", "'greed'", "'is above historical'", "'is above threshold'", 
		"'is bearish if not bullish'", "'is bearish when'", "'is below historical'", 
		"'is below threshold'", "'is bullish when'", "'is within'", "'makes a higher high spanning'", 
		"'makes a higher low spanning'", "'makes a lower high spanning'", "'makes a lower low spanning'", 
		"'makes a support break down spanning'", "'makes a support break up spanning'", 
		"'more than'", "'over'", "'override event list name with'", "'override start shift with'", 
		"'reverses down'", "'reverses up'", "'slope within'", "'smoothed'", "'spanning'", 
		"'starting within'", "'tolerance'", "'trends down'", "'trends flat'", 
		"'trends like'", "'trends unlike'", "'trends up'", "'type'"
	};
	public static final int EOF=-1;
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
	public static final int T__103=103;
	public static final int T__104=104;
	public static final int T__105=105;
	public static final int T__106=106;
	public static final int T__107=107;
	public static final int T__108=108;
	public static final int T__109=109;
	public static final int T__110=110;
	public static final int T__111=111;
	public static final int T__112=112;
	public static final int T__113=113;
	public static final int AND=4;
	public static final int AndBooleanMapCondition=5;
	public static final int CLOSEPARENTEHSIS=6;
	public static final int CLOSESQRT=7;
	public static final int COMMA=8;
	public static final int COMMENT=9;
	public static final int CrossDownConstantCondition=10;
	public static final int CrossDownDoubleMapCondition=11;
	public static final int CrossUpConstantCondition=12;
	public static final int CrossUpDoubleMapCondition=13;
	public static final int DAYS=14;
	public static final int DownRatioCondition=15;
	public static final int EqualConstantCondition=16;
	public static final int EqualDoubleMapCondition=17;
	public static final int EqualStringConstantCondition=18;
	public static final int EventInfoOpsCompoOperation=19;
	public static final int HigherHighCondition=20;
	public static final int HigherLowCondition=21;
	public static final int HistoricalData=22;
	public static final int InfConstantCondition=23;
	public static final int InfDoubleMapCondition=24;
	public static final int LENIENT=25;
	public static final int LINE_COMMENT=26;
	public static final int LinearDirectedTrendsCondition=27;
	public static final int LinearOppositeTrendsCondition=28;
	public static final int LinearSimilarTrendsCondition=29;
	public static final int LowerHighCondition=30;
	public static final int LowerLowCondition=31;
	public static final int MATCHING=32;
	public static final int MatchingBooleanMapCondition=33;
	public static final int NOT=34;
	public static final int NotBooleanMapCondition=35;
	public static final int NullCondition=36;
	public static final int Number=37;
	public static final int NumberToken=38;
	public static final int OPENPARENTEHSIS=39;
	public static final int OPENSQRT=40;
	public static final int OR=41;
	public static final int Operation=42;
	public static final int OperationOutput=43;
	public static final int OrBooleanMapCondition=44;
	public static final int PERCENT=45;
	public static final int PreAndSignalCondition=46;
	public static final int ReverseCondition=47;
	public static final int SEMICOLUMN=48;
	public static final int StockOperation=49;
	public static final int String=50;
	public static final int StringOperation=51;
	public static final int StringToken=52;
	public static final int SupConstantCondition=53;
	public static final int SupDoubleMapCondition=54;
	public static final int SupportBreakDown=55;
	public static final int SupportBreakUp=56;
	public static final int TRUTHOF=57;
	public static final int Tcheat=58;
	public static final int TruthOfCondition=59;
	public static final int UpRatioCondition=60;
	public static final int WITH=61;
	public static final int WS=62;
	public static final int WhiteChar=63;

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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:1: complete_expression : bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift na_event_list_name -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift na_event_list_name ) ;
	public final ParameterizedIndicatorsParser.complete_expression_return complete_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.complete_expression_return retval = new ParameterizedIndicatorsParser.complete_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope bcond =null;
		ParserRuleReturnScope bearish_condition1 =null;
		ParserRuleReturnScope also_display2 =null;
		ParserRuleReturnScope fixed_start_shift3 =null;
		ParserRuleReturnScope na_event_list_name4 =null;

		RewriteRuleSubtreeStream stream_na_event_list_name=new RewriteRuleSubtreeStream(adaptor,"rule na_event_list_name");
		RewriteRuleSubtreeStream stream_also_display=new RewriteRuleSubtreeStream(adaptor,"rule also_display");
		RewriteRuleSubtreeStream stream_bullish_condition=new RewriteRuleSubtreeStream(adaptor,"rule bullish_condition");
		RewriteRuleSubtreeStream stream_fixed_start_shift=new RewriteRuleSubtreeStream(adaptor,"rule fixed_start_shift");
		RewriteRuleSubtreeStream stream_bearish_condition=new RewriteRuleSubtreeStream(adaptor,"rule bearish_condition");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:21: (bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift na_event_list_name -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift na_event_list_name ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:165:4: bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift na_event_list_name
			{
			pushFollow(FOLLOW_bullish_condition_in_complete_expression450);
			bcond=bullish_condition();
			state._fsp--;

			stream_bullish_condition.add(bcond.getTree());
			pushFollow(FOLLOW_bearish_condition_in_complete_expression452);
			bearish_condition1=bearish_condition((bcond!=null?((CommonTree)bcond.getTree()):null));
			state._fsp--;

			stream_bearish_condition.add(bearish_condition1.getTree());
			pushFollow(FOLLOW_also_display_in_complete_expression455);
			also_display2=also_display();
			state._fsp--;

			stream_also_display.add(also_display2.getTree());
			pushFollow(FOLLOW_fixed_start_shift_in_complete_expression457);
			fixed_start_shift3=fixed_start_shift();
			state._fsp--;

			stream_fixed_start_shift.add(fixed_start_shift3.getTree());
			pushFollow(FOLLOW_na_event_list_name_in_complete_expression459);
			na_event_list_name4=na_event_list_name();
			state._fsp--;

			stream_na_event_list_name.add(na_event_list_name4.getTree());
			// AST REWRITE
			// elements: fixed_start_shift, na_event_list_name, also_display, bearish_condition, bullish_condition
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 165:109: -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift na_event_list_name )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:165:112: ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift na_event_list_name )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EventInfoOpsCompoOperation, "EventInfoOpsCompoOperation"), root_1);
				adaptor.addChild(root_1, stream_bullish_condition.nextTree());
				adaptor.addChild(root_1, stream_bearish_condition.nextTree());
				adaptor.addChild(root_1, stream_also_display.nextTree());
				adaptor.addChild(root_1, stream_fixed_start_shift.nextTree());
				adaptor.addChild(root_1, stream_na_event_list_name.nextTree());
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:168:1: bullish_condition : 'is bullish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression ;
	public final ParameterizedIndicatorsParser.bullish_condition_return bullish_condition() throws RecognitionException {
		ParameterizedIndicatorsParser.bullish_condition_return retval = new ParameterizedIndicatorsParser.bullish_condition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal5=null;
		Token WhiteChar6=null;
		Token WhiteChar8=null;
		Token SEMICOLUMN9=null;
		Token WhiteChar10=null;
		ParserRuleReturnScope primary_expression7 =null;

		CommonTree string_literal5_tree=null;
		CommonTree WhiteChar6_tree=null;
		CommonTree WhiteChar8_tree=null;
		CommonTree SEMICOLUMN9_tree=null;
		CommonTree WhiteChar10_tree=null;
		RewriteRuleTokenStream stream_SEMICOLUMN=new RewriteRuleTokenStream(adaptor,"token SEMICOLUMN");
		RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:168:19: ( 'is bullish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:2: 'is bullish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )*
			{
			string_literal5=(Token)match(input,89,FOLLOW_89_in_bullish_condition488);  
			stream_89.add(string_literal5);

			WhiteChar6=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition490);  
			stream_WhiteChar.add(WhiteChar6);

			pushFollow(FOLLOW_primary_expression_in_bullish_condition492);
			primary_expression7=primary_expression();
			state._fsp--;

			stream_primary_expression.add(primary_expression7.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:49: ( WhiteChar )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==WhiteChar) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:49: WhiteChar
					{
					WhiteChar8=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition494);  
					stream_WhiteChar.add(WhiteChar8);

					}
					break;

				default :
					break loop1;
				}
			}

			SEMICOLUMN9=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_bullish_condition497);  
			stream_SEMICOLUMN.add(SEMICOLUMN9);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:71: ( WhiteChar )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==WhiteChar) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:71: WhiteChar
					{
					WhiteChar10=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition499);  
					stream_WhiteChar.add(WhiteChar10);

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
			// 169:82: -> primary_expression
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:171:1: bearish_condition[CommonTree bcond] : ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> bearish_not_bullish );
	public final ParameterizedIndicatorsParser.bearish_condition_return bearish_condition(CommonTree bcond) throws RecognitionException {
		ParameterizedIndicatorsParser.bearish_condition_return retval = new ParameterizedIndicatorsParser.bearish_condition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal11=null;
		Token WhiteChar12=null;
		Token WhiteChar14=null;
		Token SEMICOLUMN15=null;
		Token WhiteChar16=null;
		Token WhiteChar18=null;
		Token SEMICOLUMN19=null;
		Token WhiteChar20=null;
		ParserRuleReturnScope primary_expression13 =null;
		ParserRuleReturnScope bearish_not_bullish17 =null;

		CommonTree string_literal11_tree=null;
		CommonTree WhiteChar12_tree=null;
		CommonTree WhiteChar14_tree=null;
		CommonTree SEMICOLUMN15_tree=null;
		CommonTree WhiteChar16_tree=null;
		CommonTree WhiteChar18_tree=null;
		CommonTree SEMICOLUMN19_tree=null;
		CommonTree WhiteChar20_tree=null;
		RewriteRuleTokenStream stream_SEMICOLUMN=new RewriteRuleTokenStream(adaptor,"token SEMICOLUMN");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
		RewriteRuleSubtreeStream stream_bearish_not_bullish=new RewriteRuleSubtreeStream(adaptor,"rule bearish_not_bullish");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:171:37: ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> bearish_not_bullish )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==86) ) {
				alt7=1;
			}
			else if ( (LA7_0==85) ) {
				alt7=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:172:2: 'is bearish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )*
					{
					string_literal11=(Token)match(input,86,FOLLOW_86_in_bearish_condition515);  
					stream_86.add(string_literal11);

					WhiteChar12=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition517);  
					stream_WhiteChar.add(WhiteChar12);

					pushFollow(FOLLOW_primary_expression_in_bearish_condition519);
					primary_expression13=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression13.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:172:49: ( WhiteChar )*
					loop3:
					while (true) {
						int alt3=2;
						int LA3_0 = input.LA(1);
						if ( (LA3_0==WhiteChar) ) {
							alt3=1;
						}

						switch (alt3) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:172:49: WhiteChar
							{
							WhiteChar14=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition521);  
							stream_WhiteChar.add(WhiteChar14);

							}
							break;

						default :
							break loop3;
						}
					}

					SEMICOLUMN15=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_bearish_condition524);  
					stream_SEMICOLUMN.add(SEMICOLUMN15);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:172:71: ( WhiteChar )*
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( (LA4_0==WhiteChar) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:172:71: WhiteChar
							{
							WhiteChar16=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition526);  
							stream_WhiteChar.add(WhiteChar16);

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
					// 172:82: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:2: bearish_not_bullish[$bcond] ( WhiteChar )* SEMICOLUMN ( WhiteChar )*
					{
					pushFollow(FOLLOW_bearish_not_bullish_in_bearish_condition536);
					bearish_not_bullish17=bearish_not_bullish(bcond);
					state._fsp--;

					stream_bearish_not_bullish.add(bearish_not_bullish17.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:30: ( WhiteChar )*
					loop5:
					while (true) {
						int alt5=2;
						int LA5_0 = input.LA(1);
						if ( (LA5_0==WhiteChar) ) {
							alt5=1;
						}

						switch (alt5) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:30: WhiteChar
							{
							WhiteChar18=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition539);  
							stream_WhiteChar.add(WhiteChar18);

							}
							break;

						default :
							break loop5;
						}
					}

					SEMICOLUMN19=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_bearish_condition542);  
					stream_SEMICOLUMN.add(SEMICOLUMN19);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:52: ( WhiteChar )*
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( (LA6_0==WhiteChar) ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:52: WhiteChar
							{
							WhiteChar20=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition544);  
							stream_WhiteChar.add(WhiteChar20);

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
					// 173:63: -> bearish_not_bullish
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:175:2: also_display : ( 'also display' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition );
	public final ParameterizedIndicatorsParser.also_display_return also_display() throws RecognitionException {
		ParameterizedIndicatorsParser.also_display_return retval = new ParameterizedIndicatorsParser.also_display_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal21=null;
		Token WhiteChar22=null;
		Token WhiteChar24=null;
		Token SEMICOLUMN25=null;
		ParserRuleReturnScope primary_expression23 =null;

		CommonTree string_literal21_tree=null;
		CommonTree WhiteChar22_tree=null;
		CommonTree WhiteChar24_tree=null;
		CommonTree SEMICOLUMN25_tree=null;
		RewriteRuleTokenStream stream_SEMICOLUMN=new RewriteRuleTokenStream(adaptor,"token SEMICOLUMN");
		RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:175:15: ( 'also display' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==66) ) {
				alt9=1;
			}
			else if ( (LA9_0==EOF||(LA9_0 >= 99 && LA9_0 <= 100)) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:3: 'also display' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN
					{
					string_literal21=(Token)match(input,66,FOLLOW_66_in_also_display561);  
					stream_66.add(string_literal21);

					WhiteChar22=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display563);  
					stream_WhiteChar.add(WhiteChar22);

					pushFollow(FOLLOW_primary_expression_in_also_display565);
					primary_expression23=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression23.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:47: ( WhiteChar )*
					loop8:
					while (true) {
						int alt8=2;
						int LA8_0 = input.LA(1);
						if ( (LA8_0==WhiteChar) ) {
							alt8=1;
						}

						switch (alt8) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:47: WhiteChar
							{
							WhiteChar24=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display567);  
							stream_WhiteChar.add(WhiteChar24);

							}
							break;

						default :
							break loop8;
						}
					}

					SEMICOLUMN25=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_also_display570);  
					stream_SEMICOLUMN.add(SEMICOLUMN25);

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
					// 176:69: -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:72: ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndBooleanMapCondition, "AndBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:97: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:177:3: 
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
					// 177:3: -> NullCondition
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:2: fixed_start_shift : ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS SEMICOLUMN ->| -> ^( Number NumberToken[\"-1\"] ) );
	public final ParameterizedIndicatorsParser.fixed_start_shift_return fixed_start_shift() throws RecognitionException {
		ParameterizedIndicatorsParser.fixed_start_shift_return retval = new ParameterizedIndicatorsParser.fixed_start_shift_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal26=null;
		Token WhiteChar27=null;
		Token WhiteChar28=null;
		Token DAYS29=null;
		Token SEMICOLUMN30=null;
		ParserRuleReturnScope fixedStartShift =null;

		CommonTree string_literal26_tree=null;
		CommonTree WhiteChar27_tree=null;
		CommonTree WhiteChar28_tree=null;
		CommonTree DAYS29_tree=null;
		CommonTree SEMICOLUMN30_tree=null;
		RewriteRuleTokenStream stream_SEMICOLUMN=new RewriteRuleTokenStream(adaptor,"token SEMICOLUMN");
		RewriteRuleTokenStream stream_100=new RewriteRuleTokenStream(adaptor,"token 100");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:20: ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS SEMICOLUMN ->| -> ^( Number NumberToken[\"-1\"] ) )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==100) ) {
				alt10=1;
			}
			else if ( (LA10_0==EOF||LA10_0==99) ) {
				alt10=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:3: 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS SEMICOLUMN
					{
					string_literal26=(Token)match(input,100,FOLLOW_100_in_fixed_start_shift605);  
					stream_100.add(string_literal26);

					WhiteChar27=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift607);  
					stream_WhiteChar.add(WhiteChar27);

					pushFollow(FOLLOW_constant_in_fixed_start_shift611);
					fixedStartShift=constant();
					state._fsp--;

					stream_constant.add(fixedStartShift.getTree());
					WhiteChar28=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift613);  
					stream_WhiteChar.add(WhiteChar28);

					DAYS29=(Token)match(input,DAYS,FOLLOW_DAYS_in_fixed_start_shift615);  
					stream_DAYS.add(DAYS29);

					SEMICOLUMN30=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_fixed_start_shift617);  
					stream_SEMICOLUMN.add(SEMICOLUMN30);

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
					// 180:92: ->
					{
						adaptor.addChild(root_0, (fixedStartShift!=null?((CommonTree)fixedStartShift.getTree()):null));
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:3: 
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
					// 181:3: -> ^( Number NumberToken[\"-1\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:6: ^( Number NumberToken[\"-1\"] )
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


	public static class na_event_list_name_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "na_event_list_name"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:2: na_event_list_name : ( 'override event list name with' WhiteChar eventListName= stringconstant SEMICOLUMN ->| -> ^( String StringToken[\"FROM PARENT\"] ) );
	public final ParameterizedIndicatorsParser.na_event_list_name_return na_event_list_name() throws RecognitionException {
		ParameterizedIndicatorsParser.na_event_list_name_return retval = new ParameterizedIndicatorsParser.na_event_list_name_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal31=null;
		Token WhiteChar32=null;
		Token SEMICOLUMN33=null;
		ParserRuleReturnScope eventListName =null;

		CommonTree string_literal31_tree=null;
		CommonTree WhiteChar32_tree=null;
		CommonTree SEMICOLUMN33_tree=null;
		RewriteRuleTokenStream stream_SEMICOLUMN=new RewriteRuleTokenStream(adaptor,"token SEMICOLUMN");
		RewriteRuleTokenStream stream_99=new RewriteRuleTokenStream(adaptor,"token 99");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_stringconstant=new RewriteRuleSubtreeStream(adaptor,"rule stringconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:21: ( 'override event list name with' WhiteChar eventListName= stringconstant SEMICOLUMN ->| -> ^( String StringToken[\"FROM PARENT\"] ) )
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==99) ) {
				alt11=1;
			}
			else if ( (LA11_0==EOF) ) {
				alt11=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}

			switch (alt11) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:3: 'override event list name with' WhiteChar eventListName= stringconstant SEMICOLUMN
					{
					string_literal31=(Token)match(input,99,FOLLOW_99_in_na_event_list_name646);  
					stream_99.add(string_literal31);

					WhiteChar32=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_na_event_list_name648);  
					stream_WhiteChar.add(WhiteChar32);

					pushFollow(FOLLOW_stringconstant_in_na_event_list_name652);
					eventListName=stringconstant();
					state._fsp--;

					stream_stringconstant.add(eventListName.getTree());
					SEMICOLUMN33=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_na_event_list_name654);  
					stream_SEMICOLUMN.add(SEMICOLUMN33);

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
					// 184:85: ->
					{
						adaptor.addChild(root_0, (eventListName!=null?((CommonTree)eventListName.getTree()):null));
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:3: 
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
					// 185:3: -> ^( String StringToken[\"FROM PARENT\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:6: ^( String StringToken[\"FROM PARENT\"] )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_1);
						adaptor.addChild(root_1, (CommonTree)adaptor.create(StringToken, "FROM PARENT"));
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
	// $ANTLR end "na_event_list_name"


	public static class bearish_not_bullish_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "bearish_not_bullish"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:1: bearish_not_bullish[CommonTree bcond] : 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ) ;
	public final ParameterizedIndicatorsParser.bearish_not_bullish_return bearish_not_bullish(CommonTree bcond) throws RecognitionException {
		ParameterizedIndicatorsParser.bearish_not_bullish_return retval = new ParameterizedIndicatorsParser.bearish_not_bullish_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal34=null;
		Token WhiteChar35=null;
		Token AND36=null;
		Token WhiteChar37=null;
		Token WhiteChar39=null;
		Token OR40=null;
		Token WhiteChar41=null;
		ParserRuleReturnScope primary_expression38 =null;
		ParserRuleReturnScope primary_expression42 =null;

		CommonTree string_literal34_tree=null;
		CommonTree WhiteChar35_tree=null;
		CommonTree AND36_tree=null;
		CommonTree WhiteChar37_tree=null;
		CommonTree WhiteChar39_tree=null;
		CommonTree OR40_tree=null;
		CommonTree WhiteChar41_tree=null;
		RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
		RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:39: ( 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:2: 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			{
			string_literal34=(Token)match(input,85,FOLLOW_85_in_bearish_not_bullish683);  
			stream_85.add(string_literal34);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:3: ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			int alt12=3;
			int LA12_0 = input.LA(1);
			if ( (LA12_0==WhiteChar) ) {
				switch ( input.LA(2) ) {
				case AND:
					{
					alt12=1;
					}
					break;
				case OR:
					{
					alt12=2;
					}
					break;
				case SEMICOLUMN:
				case WhiteChar:
					{
					alt12=3;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 12, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}
			else if ( (LA12_0==SEMICOLUMN) ) {
				alt12=3;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}

			switch (alt12) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:3: WhiteChar AND WhiteChar primary_expression
					{
					WhiteChar35=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish691);  
					stream_WhiteChar.add(WhiteChar35);

					AND36=(Token)match(input,AND,FOLLOW_AND_in_bearish_not_bullish693);  
					stream_AND.add(AND36);

					WhiteChar37=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish695);  
					stream_WhiteChar.add(WhiteChar37);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish697);
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
					// 191:46: -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:49: ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndBooleanMapCondition, "AndBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:74: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:109: ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_2);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:134: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_3 = (CommonTree)adaptor.nil();
						root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_3);
						adaptor.addChild(root_3, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_2, root_3);
						}

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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:3: WhiteChar OR WhiteChar primary_expression
					{
					WhiteChar39=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish730);  
					stream_WhiteChar.add(WhiteChar39);

					OR40=(Token)match(input,OR,FOLLOW_OR_in_bearish_not_bullish732);  
					stream_OR.add(OR40);

					WhiteChar41=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish734);  
					stream_WhiteChar.add(WhiteChar41);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish736);
					primary_expression42=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression42.getTree());
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
					// 192:45: -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:48: ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrBooleanMapCondition, "OrBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:72: ^( String StringToken[\"\\\"TRUE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:106: ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_2);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:131: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_3 = (CommonTree)adaptor.nil();
						root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_3);
						adaptor.addChild(root_3, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_2, root_3);
						}

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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:3: 
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
					// 193:3: -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:6: ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:31: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_1, root_2);
						}

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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:1: primary_expression : and_expression ;
	public final ParameterizedIndicatorsParser.primary_expression_return primary_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.primary_expression_return retval = new ParameterizedIndicatorsParser.primary_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope and_expression43 =null;


		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:20: ( and_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:3: and_expression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_and_expression_in_primary_expression799);
			and_expression43=and_expression();
			state._fsp--;

			adaptor.addChild(root_0, and_expression43.getTree());

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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:1: and_expression : or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndBooleanMapCondition or_expression ( or_expression )* ) ;
	public final ParameterizedIndicatorsParser.and_expression_return and_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.and_expression_return retval = new ParameterizedIndicatorsParser.and_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar45=null;
		Token AND46=null;
		Token WhiteChar47=null;
		ParserRuleReturnScope lenientParam =null;
		ParserRuleReturnScope or_expression44 =null;
		ParserRuleReturnScope or_expression48 =null;

		CommonTree WhiteChar45_tree=null;
		CommonTree AND46_tree=null;
		CommonTree WhiteChar47_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
		RewriteRuleSubtreeStream stream_or_expression=new RewriteRuleSubtreeStream(adaptor,"rule or_expression");
		RewriteRuleSubtreeStream stream_lenient=new RewriteRuleSubtreeStream(adaptor,"rule lenient");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:16: ( or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndBooleanMapCondition or_expression ( or_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:3: or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )*
			{
			pushFollow(FOLLOW_or_expression_in_and_expression811);
			or_expression44=or_expression();
			state._fsp--;

			stream_or_expression.add(or_expression44.getTree());
			pushFollow(FOLLOW_lenient_in_and_expression815);
			lenientParam=lenient();
			state._fsp--;

			stream_lenient.add(lenientParam.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:38: ( WhiteChar AND WhiteChar or_expression )*
			loop13:
			while (true) {
				int alt13=2;
				int LA13_0 = input.LA(1);
				if ( (LA13_0==WhiteChar) ) {
					int LA13_1 = input.LA(2);
					if ( (LA13_1==AND) ) {
						alt13=1;
					}

				}

				switch (alt13) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:39: WhiteChar AND WhiteChar or_expression
					{
					WhiteChar45=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression818);  
					stream_WhiteChar.add(WhiteChar45);

					AND46=(Token)match(input,AND,FOLLOW_AND_in_and_expression820);  
					stream_AND.add(AND46);

					WhiteChar47=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression822);  
					stream_WhiteChar.add(WhiteChar47);

					pushFollow(FOLLOW_or_expression_in_and_expression824);
					or_expression48=or_expression();
					state._fsp--;

					stream_or_expression.add(or_expression48.getTree());
					}
					break;

				default :
					break loop13;
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
			// 202:79: -> ^( AndBooleanMapCondition or_expression ( or_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:82: ^( AndBooleanMapCondition or_expression ( or_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndBooleanMapCondition, "AndBooleanMapCondition"), root_1);
				adaptor.addChild(root_1, (lenientParam!=null?((CommonTree)lenientParam.getTree()):null));
				adaptor.addChild(root_1, stream_or_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:142: ( or_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:1: or_expression : precondition_expression ( WhiteChar OR WhiteChar precondition_expression )* -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) precondition_expression ( precondition_expression )* ) ;
	public final ParameterizedIndicatorsParser.or_expression_return or_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.or_expression_return retval = new ParameterizedIndicatorsParser.or_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar50=null;
		Token OR51=null;
		Token WhiteChar52=null;
		ParserRuleReturnScope precondition_expression49 =null;
		ParserRuleReturnScope precondition_expression53 =null;

		CommonTree WhiteChar50_tree=null;
		CommonTree OR51_tree=null;
		CommonTree WhiteChar52_tree=null;
		RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_precondition_expression=new RewriteRuleSubtreeStream(adaptor,"rule precondition_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:15: ( precondition_expression ( WhiteChar OR WhiteChar precondition_expression )* -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) precondition_expression ( precondition_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:3: precondition_expression ( WhiteChar OR WhiteChar precondition_expression )*
			{
			pushFollow(FOLLOW_precondition_expression_in_or_expression851);
			precondition_expression49=precondition_expression();
			state._fsp--;

			stream_precondition_expression.add(precondition_expression49.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:27: ( WhiteChar OR WhiteChar precondition_expression )*
			loop14:
			while (true) {
				int alt14=2;
				int LA14_0 = input.LA(1);
				if ( (LA14_0==WhiteChar) ) {
					int LA14_1 = input.LA(2);
					if ( (LA14_1==OR) ) {
						alt14=1;
					}

				}

				switch (alt14) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:28: WhiteChar OR WhiteChar precondition_expression
					{
					WhiteChar50=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression854);  
					stream_WhiteChar.add(WhiteChar50);

					OR51=(Token)match(input,OR,FOLLOW_OR_in_or_expression856);  
					stream_OR.add(OR51);

					WhiteChar52=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression858);  
					stream_WhiteChar.add(WhiteChar52);

					pushFollow(FOLLOW_precondition_expression_in_or_expression860);
					precondition_expression53=precondition_expression();
					state._fsp--;

					stream_precondition_expression.add(precondition_expression53.getTree());
					}
					break;

				default :
					break loop14;
				}
			}

			// AST REWRITE
			// elements: precondition_expression, precondition_expression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 205:77: -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) precondition_expression ( precondition_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:80: ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) precondition_expression ( precondition_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrBooleanMapCondition, "OrBooleanMapCondition"), root_1);
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:104: ^( String StringToken[\"\\\"TRUE\\\"\"] )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
				adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, stream_precondition_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:162: ( precondition_expression )*
				while ( stream_precondition_expression.hasNext() ) {
					adaptor.addChild(root_1, stream_precondition_expression.nextTree());
				}
				stream_precondition_expression.reset();

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


	public static class precondition_expression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precondition_expression"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:1: precondition_expression : matches_expression ( WhiteChar WITH WhiteChar matches_expression )* -> ^( PreAndSignalCondition matches_expression ( matches_expression )* ) ;
	public final ParameterizedIndicatorsParser.precondition_expression_return precondition_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.precondition_expression_return retval = new ParameterizedIndicatorsParser.precondition_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar55=null;
		Token WITH56=null;
		Token WhiteChar57=null;
		ParserRuleReturnScope matches_expression54 =null;
		ParserRuleReturnScope matches_expression58 =null;

		CommonTree WhiteChar55_tree=null;
		CommonTree WITH56_tree=null;
		CommonTree WhiteChar57_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_WITH=new RewriteRuleTokenStream(adaptor,"token WITH");
		RewriteRuleSubtreeStream stream_matches_expression=new RewriteRuleSubtreeStream(adaptor,"rule matches_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:25: ( matches_expression ( WhiteChar WITH WhiteChar matches_expression )* -> ^( PreAndSignalCondition matches_expression ( matches_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:3: matches_expression ( WhiteChar WITH WhiteChar matches_expression )*
			{
			pushFollow(FOLLOW_matches_expression_in_precondition_expression892);
			matches_expression54=matches_expression();
			state._fsp--;

			stream_matches_expression.add(matches_expression54.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:22: ( WhiteChar WITH WhiteChar matches_expression )*
			loop15:
			while (true) {
				int alt15=2;
				int LA15_0 = input.LA(1);
				if ( (LA15_0==WhiteChar) ) {
					int LA15_1 = input.LA(2);
					if ( (LA15_1==WITH) ) {
						alt15=1;
					}

				}

				switch (alt15) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:23: WhiteChar WITH WhiteChar matches_expression
					{
					WhiteChar55=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_precondition_expression895);  
					stream_WhiteChar.add(WhiteChar55);

					WITH56=(Token)match(input,WITH,FOLLOW_WITH_in_precondition_expression897);  
					stream_WITH.add(WITH56);

					WhiteChar57=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_precondition_expression899);  
					stream_WhiteChar.add(WhiteChar57);

					pushFollow(FOLLOW_matches_expression_in_precondition_expression901);
					matches_expression58=matches_expression();
					state._fsp--;

					stream_matches_expression.add(matches_expression58.getTree());
					}
					break;

				default :
					break loop15;
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
			// 208:69: -> ^( PreAndSignalCondition matches_expression ( matches_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:72: ^( PreAndSignalCondition matches_expression ( matches_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(PreAndSignalCondition, "PreAndSignalCondition"), root_1);
				adaptor.addChild(root_1, stream_matches_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:115: ( matches_expression )*
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
	// $ANTLR end "precondition_expression"


	public static class matches_expression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "matches_expression"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:1: matches_expression : atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )? -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? ) ;
	public final ParameterizedIndicatorsParser.matches_expression_return matches_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.matches_expression_return retval = new ParameterizedIndicatorsParser.matches_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar60=null;
		Token MATCHING61=null;
		Token WhiteChar62=null;
		Token char_literal63=null;
		Token char_literal65=null;
		Token char_literal67=null;
		Token WhiteChar68=null;
		ParserRuleReturnScope atom59 =null;
		ParserRuleReturnScope constant64 =null;
		ParserRuleReturnScope constant66 =null;
		ParserRuleReturnScope atom69 =null;

		CommonTree WhiteChar60_tree=null;
		CommonTree MATCHING61_tree=null;
		CommonTree WhiteChar62_tree=null;
		CommonTree char_literal63_tree=null;
		CommonTree char_literal65_tree=null;
		CommonTree char_literal67_tree=null;
		CommonTree WhiteChar68_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_OPENSQRT=new RewriteRuleTokenStream(adaptor,"token OPENSQRT");
		RewriteRuleTokenStream stream_MATCHING=new RewriteRuleTokenStream(adaptor,"token MATCHING");
		RewriteRuleTokenStream stream_CLOSESQRT=new RewriteRuleTokenStream(adaptor,"token CLOSESQRT");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:20: ( atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )? -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:3: atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )?
			{
			pushFollow(FOLLOW_atom_in_matches_expression926);
			atom59=atom();
			state._fsp--;

			stream_atom.add(atom59.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:8: ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )?
			int alt17=2;
			int LA17_0 = input.LA(1);
			if ( (LA17_0==WhiteChar) ) {
				int LA17_1 = input.LA(2);
				if ( (LA17_1==MATCHING) ) {
					alt17=1;
				}
			}
			switch (alt17) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:9: WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom
					{
					WhiteChar60=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression929);  
					stream_WhiteChar.add(WhiteChar60);

					MATCHING61=(Token)match(input,MATCHING,FOLLOW_MATCHING_in_matches_expression931);  
					stream_MATCHING.add(MATCHING61);

					WhiteChar62=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression933);  
					stream_WhiteChar.add(WhiteChar62);

					char_literal63=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_matches_expression935);  
					stream_OPENSQRT.add(char_literal63);

					pushFollow(FOLLOW_constant_in_matches_expression937);
					constant64=constant();
					state._fsp--;

					stream_constant.add(constant64.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:51: ( ',' constant )*
					loop16:
					while (true) {
						int alt16=2;
						int LA16_0 = input.LA(1);
						if ( (LA16_0==COMMA) ) {
							alt16=1;
						}

						switch (alt16) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:52: ',' constant
							{
							char_literal65=(Token)match(input,COMMA,FOLLOW_COMMA_in_matches_expression940);  
							stream_COMMA.add(char_literal65);

							pushFollow(FOLLOW_constant_in_matches_expression942);
							constant66=constant();
							state._fsp--;

							stream_constant.add(constant66.getTree());
							}
							break;

						default :
							break loop16;
						}
					}

					char_literal67=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_matches_expression946);  
					stream_CLOSESQRT.add(char_literal67);

					WhiteChar68=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression948);  
					stream_WhiteChar.add(WhiteChar68);

					pushFollow(FOLLOW_atom_in_matches_expression950);
					atom69=atom();
					state._fsp--;

					stream_atom.add(atom69.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: atom, constant, atom
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 211:88: -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:91: ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(MatchingBooleanMapCondition, "MatchingBooleanMapCondition"), root_1);
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:121: ( constant )*
				while ( stream_constant.hasNext() ) {
					adaptor.addChild(root_1, stream_constant.nextTree());
				}
				stream_constant.reset();

				adaptor.addChild(root_1, stream_atom.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:136: ( atom )?
				if ( stream_atom.hasNext() ) {
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:1: atom : ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | NOT ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression ) | conjunctiontruthof );
	public final ParameterizedIndicatorsParser.atom_return atom() throws RecognitionException {
		ParameterizedIndicatorsParser.atom_return retval = new ParameterizedIndicatorsParser.atom_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal71=null;
		Token WhiteChar72=null;
		Token WhiteChar74=null;
		Token char_literal75=null;
		Token NOT76=null;
		Token WhiteChar77=null;
		Token char_literal78=null;
		Token WhiteChar79=null;
		Token WhiteChar81=null;
		Token char_literal82=null;
		ParserRuleReturnScope booleanhistory70 =null;
		ParserRuleReturnScope primary_expression73 =null;
		ParserRuleReturnScope primary_expression80 =null;
		ParserRuleReturnScope conjunctiontruthof83 =null;

		CommonTree char_literal71_tree=null;
		CommonTree WhiteChar72_tree=null;
		CommonTree WhiteChar74_tree=null;
		CommonTree char_literal75_tree=null;
		CommonTree NOT76_tree=null;
		CommonTree WhiteChar77_tree=null;
		CommonTree char_literal78_tree=null;
		CommonTree WhiteChar79_tree=null;
		CommonTree WhiteChar81_tree=null;
		CommonTree char_literal82_tree=null;
		RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_CLOSEPARENTEHSIS=new RewriteRuleTokenStream(adaptor,"token CLOSEPARENTEHSIS");
		RewriteRuleTokenStream stream_OPENPARENTEHSIS=new RewriteRuleTokenStream(adaptor,"token OPENPARENTEHSIS");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:6: ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | NOT ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression ) | conjunctiontruthof )
			int alt23=4;
			switch ( input.LA(1) ) {
			case HistoricalData:
			case Operation:
				{
				alt23=1;
				}
				break;
			case OPENPARENTEHSIS:
				{
				alt23=2;
				}
				break;
			case NOT:
				{
				alt23=3;
				}
				break;
			case TRUTHOF:
				{
				alt23=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}
			switch (alt23) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:3: booleanhistory
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_booleanhistory_in_atom978);
					booleanhistory70=booleanhistory();
					state._fsp--;

					adaptor.addChild(root_0, booleanhistory70.getTree());

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:3: '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					char_literal71=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom984);  
					stream_OPENPARENTEHSIS.add(char_literal71);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:7: ( WhiteChar )*
					loop18:
					while (true) {
						int alt18=2;
						int LA18_0 = input.LA(1);
						if ( (LA18_0==WhiteChar) ) {
							alt18=1;
						}

						switch (alt18) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:7: WhiteChar
							{
							WhiteChar72=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom986);  
							stream_WhiteChar.add(WhiteChar72);

							}
							break;

						default :
							break loop18;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom989);
					primary_expression73=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression73.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:37: ( WhiteChar )*
					loop19:
					while (true) {
						int alt19=2;
						int LA19_0 = input.LA(1);
						if ( (LA19_0==WhiteChar) ) {
							alt19=1;
						}

						switch (alt19) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:37: WhiteChar
							{
							WhiteChar74=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom991);  
							stream_WhiteChar.add(WhiteChar74);

							}
							break;

						default :
							break loop19;
						}
					}

					char_literal75=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom994);  
					stream_CLOSEPARENTEHSIS.add(char_literal75);

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
					// 215:52: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:3: NOT ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					NOT76=(Token)match(input,NOT,FOLLOW_NOT_in_atom1004);  
					stream_NOT.add(NOT76);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:7: ( WhiteChar )*
					loop20:
					while (true) {
						int alt20=2;
						int LA20_0 = input.LA(1);
						if ( (LA20_0==WhiteChar) ) {
							alt20=1;
						}

						switch (alt20) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:7: WhiteChar
							{
							WhiteChar77=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom1006);  
							stream_WhiteChar.add(WhiteChar77);

							}
							break;

						default :
							break loop20;
						}
					}

					char_literal78=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom1009);  
					stream_OPENPARENTEHSIS.add(char_literal78);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:22: ( WhiteChar )*
					loop21:
					while (true) {
						int alt21=2;
						int LA21_0 = input.LA(1);
						if ( (LA21_0==WhiteChar) ) {
							alt21=1;
						}

						switch (alt21) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:22: WhiteChar
							{
							WhiteChar79=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom1011);  
							stream_WhiteChar.add(WhiteChar79);

							}
							break;

						default :
							break loop21;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom1014);
					primary_expression80=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression80.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:52: ( WhiteChar )*
					loop22:
					while (true) {
						int alt22=2;
						int LA22_0 = input.LA(1);
						if ( (LA22_0==WhiteChar) ) {
							alt22=1;
						}

						switch (alt22) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:52: WhiteChar
							{
							WhiteChar81=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom1016);  
							stream_WhiteChar.add(WhiteChar81);

							}
							break;

						default :
							break loop22;
						}
					}

					char_literal82=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom1019);  
					stream_CLOSEPARENTEHSIS.add(char_literal82);

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
					// 216:67: -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:70: ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:95: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, stream_primary_expression.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 4 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:3: conjunctiontruthof
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_conjunctiontruthof_in_atom1040);
					conjunctiontruthof83=conjunctiontruthof();
					state._fsp--;

					adaptor.addChild(root_0, conjunctiontruthof83.getTree());

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


	public static class conjunctiontruthof_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "conjunctiontruthof"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:1: conjunctiontruthof : TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']' -> ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* ) ;
	public final ParameterizedIndicatorsParser.conjunctiontruthof_return conjunctiontruthof() throws RecognitionException {
		ParameterizedIndicatorsParser.conjunctiontruthof_return retval = new ParameterizedIndicatorsParser.conjunctiontruthof_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token TRUTHOF84=null;
		Token WhiteChar85=null;
		Token COMMA87=null;
		Token WhiteChar88=null;
		Token WhiteChar90=null;
		Token string_literal91=null;
		Token WhiteChar92=null;
		Token char_literal93=null;
		Token char_literal94=null;
		Token char_literal95=null;
		ParserRuleReturnScope min =null;
		ParserRuleReturnScope max =null;
		ParserRuleReturnScope primary_expression86 =null;
		ParserRuleReturnScope primary_expression89 =null;

		CommonTree TRUTHOF84_tree=null;
		CommonTree WhiteChar85_tree=null;
		CommonTree COMMA87_tree=null;
		CommonTree WhiteChar88_tree=null;
		CommonTree WhiteChar90_tree=null;
		CommonTree string_literal91_tree=null;
		CommonTree WhiteChar92_tree=null;
		CommonTree char_literal93_tree=null;
		CommonTree char_literal94_tree=null;
		CommonTree char_literal95_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_OPENSQRT=new RewriteRuleTokenStream(adaptor,"token OPENSQRT");
		RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
		RewriteRuleTokenStream stream_CLOSESQRT=new RewriteRuleTokenStream(adaptor,"token CLOSESQRT");
		RewriteRuleTokenStream stream_TRUTHOF=new RewriteRuleTokenStream(adaptor,"token TRUTHOF");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:20: ( TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']' -> ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:3: TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']'
			{
			TRUTHOF84=(Token)match(input,TRUTHOF,FOLLOW_TRUTHOF_in_conjunctiontruthof1052);  
			stream_TRUTHOF.add(TRUTHOF84);

			WhiteChar85=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof1054);  
			stream_WhiteChar.add(WhiteChar85);

			pushFollow(FOLLOW_primary_expression_in_conjunctiontruthof1056);
			primary_expression86=primary_expression();
			state._fsp--;

			stream_primary_expression.add(primary_expression86.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:40: ( COMMA WhiteChar primary_expression )*
			loop24:
			while (true) {
				int alt24=2;
				int LA24_0 = input.LA(1);
				if ( (LA24_0==COMMA) ) {
					alt24=1;
				}

				switch (alt24) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:41: COMMA WhiteChar primary_expression
					{
					COMMA87=(Token)match(input,COMMA,FOLLOW_COMMA_in_conjunctiontruthof1059);  
					stream_COMMA.add(COMMA87);

					WhiteChar88=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof1061);  
					stream_WhiteChar.add(WhiteChar88);

					pushFollow(FOLLOW_primary_expression_in_conjunctiontruthof1063);
					primary_expression89=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression89.getTree());
					}
					break;

				default :
					break loop24;
				}
			}

			WhiteChar90=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof1067);  
			stream_WhiteChar.add(WhiteChar90);

			string_literal91=(Token)match(input,90,FOLLOW_90_in_conjunctiontruthof1069);  
			stream_90.add(string_literal91);

			WhiteChar92=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof1071);  
			stream_WhiteChar.add(WhiteChar92);

			char_literal93=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_conjunctiontruthof1073);  
			stream_OPENSQRT.add(char_literal93);

			pushFollow(FOLLOW_constant_in_conjunctiontruthof1077);
			min=constant();
			state._fsp--;

			stream_constant.add(min.getTree());
			char_literal94=(Token)match(input,COMMA,FOLLOW_COMMA_in_conjunctiontruthof1079);  
			stream_COMMA.add(char_literal94);

			pushFollow(FOLLOW_constant_in_conjunctiontruthof1083);
			max=constant();
			state._fsp--;

			stream_constant.add(max.getTree());
			char_literal95=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_conjunctiontruthof1085);  
			stream_CLOSESQRT.add(char_literal95);

			// AST REWRITE
			// elements: primary_expression, primary_expression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 221:4: -> ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:7: ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TruthOfCondition, "TruthOfCondition"), root_1);
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:26: ^( String StringToken[\"\\\"TRUE\\\"\"] )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
				adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, (min!=null?((CommonTree)min.getTree()):null));
				adaptor.addChild(root_1, (max!=null?((CommonTree)max.getTree()):null));
				adaptor.addChild(root_1, stream_primary_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:103: ( primary_expression )*
				while ( stream_primary_expression.hasNext() ) {
					adaptor.addChild(root_1, stream_primary_expression.nextTree());
				}
				stream_primary_expression.reset();

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
	// $ANTLR end "conjunctiontruthof"


	public static class booleanhistory_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "booleanhistory"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:1: booleanhistory : firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) ;
	public final ParameterizedIndicatorsParser.booleanhistory_return booleanhistory() throws RecognitionException {
		ParameterizedIndicatorsParser.booleanhistory_return retval = new ParameterizedIndicatorsParser.booleanhistory_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar96=null;
		ParserRuleReturnScope firstOp =null;
		ParserRuleReturnScope presetcondition97 =null;
		ParserRuleReturnScope opcmpcondition98 =null;
		ParserRuleReturnScope constantcmp99 =null;

		CommonTree WhiteChar96_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_presetcondition=new RewriteRuleSubtreeStream(adaptor,"rule presetcondition");
		RewriteRuleSubtreeStream stream_constantcmp=new RewriteRuleSubtreeStream(adaptor,"rule constantcmp");
		RewriteRuleSubtreeStream stream_opcmpcondition=new RewriteRuleSubtreeStream(adaptor,"rule opcmpcondition");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:16: (firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:2: firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			{
			pushFollow(FOLLOW_operand_in_booleanhistory1124);
			firstOp=operand();
			state._fsp--;

			stream_operand.add(firstOp.getTree());
			WhiteChar96=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_booleanhistory1126);  
			stream_WhiteChar.add(WhiteChar96);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:28: ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			int alt25=3;
			switch ( input.LA(1) ) {
			case 70:
			case 72:
			case 80:
			case 81:
			case 91:
			case 92:
			case 93:
			case 94:
			case 95:
			case 96:
			case 101:
			case 102:
			case 108:
			case 109:
			case 112:
				{
				alt25=1;
				}
				break;
			case 69:
			case 71:
			case 76:
			case 83:
			case 87:
			case 110:
			case 111:
				{
				alt25=2;
				}
				break;
			case 77:
			case 78:
			case 84:
			case 88:
				{
				alt25=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 25, 0, input);
				throw nvae;
			}
			switch (alt25) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:3: presetcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_presetcondition_in_booleanhistory1132);
					presetcondition97=presetcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_presetcondition.add(presetcondition97.getTree());
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
					// 225:34: -> presetcondition
					{
						adaptor.addChild(root_0, stream_presetcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:3: opcmpcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_opcmpcondition_in_booleanhistory1143);
					opcmpcondition98=opcmpcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_opcmpcondition.add(opcmpcondition98.getTree());
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
					// 226:33: -> opcmpcondition
					{
						adaptor.addChild(root_0, stream_opcmpcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:3: constantcmp[$firstOp.tree]
					{
					pushFollow(FOLLOW_constantcmp_in_booleanhistory1154);
					constantcmp99=constantcmp((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_constantcmp.add(constantcmp99.getTree());
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
					// 227:30: -> constantcmp
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:1: operand : ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation );
	public final ParameterizedIndicatorsParser.operand_return operand() throws RecognitionException {
		ParameterizedIndicatorsParser.operand_return retval = new ParameterizedIndicatorsParser.operand_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token opName=null;
		Token HistoricalData100=null;

		CommonTree opName_tree=null;
		CommonTree HistoricalData100_tree=null;
		RewriteRuleTokenStream stream_Operation=new RewriteRuleTokenStream(adaptor,"token Operation");
		RewriteRuleTokenStream stream_HistoricalData=new RewriteRuleTokenStream(adaptor,"token HistoricalData");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:9: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation )
			int alt26=2;
			int LA26_0 = input.LA(1);
			if ( (LA26_0==HistoricalData) ) {
				alt26=1;
			}
			else if ( (LA26_0==Operation) ) {
				alt26=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 26, 0, input);
				throw nvae;
			}

			switch (alt26) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:11: HistoricalData
					{
					HistoricalData100=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_operand1170);  
					stream_HistoricalData.add(HistoricalData100);

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
					// 230:26: -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:29: ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:46: ^( OperationOutput HistoricalData )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
						adaptor.addChild(root_2, stream_HistoricalData.nextNode());
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:80: ^( String StringToken[\"\\\"THIS\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:117: opName= Operation
					{
					opName=(Token)match(input,Operation,FOLLOW_Operation_in_operand1197);  
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
					// 230:171: -> Operation
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:1: constant : ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) );
	public final ParameterizedIndicatorsParser.constant_return constant() throws RecognitionException {
		ParameterizedIndicatorsParser.constant_return retval = new ParameterizedIndicatorsParser.constant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NumberToken101=null;
		Token string_literal102=null;

		CommonTree NumberToken101_tree=null;
		CommonTree string_literal102_tree=null;
		RewriteRuleTokenStream stream_NumberToken=new RewriteRuleTokenStream(adaptor,"token NumberToken");
		RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:10: ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) )
			int alt27=2;
			int LA27_0 = input.LA(1);
			if ( (LA27_0==NumberToken) ) {
				alt27=1;
			}
			else if ( (LA27_0==64) ) {
				alt27=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 27, 0, input);
				throw nvae;
			}

			switch (alt27) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:13: NumberToken
					{
					NumberToken101=(Token)match(input,NumberToken,FOLLOW_NumberToken_in_constant1212);  
					stream_NumberToken.add(NumberToken101);

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
					// 232:25: -> ^( Number NumberToken )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:28: ^( Number NumberToken )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:52: 'NaN'
					{
					string_literal102=(Token)match(input,64,FOLLOW_64_in_constant1224);  
					stream_64.add(string_literal102);

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
					// 232:58: -> ^( Number NumberToken[\"NaN\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:61: ^( Number NumberToken[\"NaN\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:1: stringconstant : StringToken -> ^( String StringToken ) ;
	public final ParameterizedIndicatorsParser.stringconstant_return stringconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.stringconstant_return retval = new ParameterizedIndicatorsParser.stringconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token StringToken103=null;

		CommonTree StringToken103_tree=null;
		RewriteRuleTokenStream stream_StringToken=new RewriteRuleTokenStream(adaptor,"token StringToken");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:16: ( StringToken -> ^( String StringToken ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:18: StringToken
			{
			StringToken103=(Token)match(input,StringToken,FOLLOW_StringToken_in_stringconstant1240);  
			stream_StringToken.add(StringToken103);

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
			// 233:30: -> ^( String StringToken )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:33: ^( String StringToken )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:1: trendconstant : ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) );
	public final ParameterizedIndicatorsParser.trendconstant_return trendconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.trendconstant_return retval = new ParameterizedIndicatorsParser.trendconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal104=null;
		Token string_literal105=null;

		CommonTree string_literal104_tree=null;
		CommonTree string_literal105_tree=null;
		RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
		RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:15: ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) )
			int alt28=2;
			int LA28_0 = input.LA(1);
			if ( (LA28_0==68) ) {
				alt28=1;
			}
			else if ( (LA28_0==67) ) {
				alt28=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 28, 0, input);
				throw nvae;
			}

			switch (alt28) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:17: 'bullish'
					{
					string_literal104=(Token)match(input,68,FOLLOW_68_in_trendconstant1255);  
					stream_68.add(string_literal104);

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
					// 234:27: -> ^( String StringToken[\"\\\"bullish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:30: ^( String StringToken[\"\\\"bullish\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:69: 'bearish'
					{
					string_literal105=(Token)match(input,67,FOLLOW_67_in_trendconstant1268);  
					stream_67.add(string_literal105);

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
					// 234:79: -> ^( String StringToken[\"\\\"bearish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:82: ^( String StringToken[\"\\\"bearish\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:1: lenient : ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ;
	public final ParameterizedIndicatorsParser.lenient_return lenient() throws RecognitionException {
		ParameterizedIndicatorsParser.lenient_return retval = new ParameterizedIndicatorsParser.lenient_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar106=null;
		Token LENIENT107=null;

		CommonTree WhiteChar106_tree=null;
		CommonTree LENIENT107_tree=null;
		RewriteRuleTokenStream stream_LENIENT=new RewriteRuleTokenStream(adaptor,"token LENIENT");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:9: ( ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			int alt29=2;
			int LA29_0 = input.LA(1);
			if ( (LA29_0==WhiteChar) ) {
				int LA29_1 = input.LA(2);
				if ( (LA29_1==LENIENT) ) {
					alt29=1;
				}
				else if ( (LA29_1==AND||LA29_1==CLOSEPARENTEHSIS||LA29_1==SEMICOLUMN||LA29_1==WhiteChar||LA29_1==90) ) {
					alt29=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 29, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA29_0==CLOSEPARENTEHSIS||LA29_0==COMMA||LA29_0==SEMICOLUMN) ) {
				alt29=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 29, 0, input);
				throw nvae;
			}

			switch (alt29) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:12: WhiteChar LENIENT
					{
					WhiteChar106=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_lenient1285);  
					stream_WhiteChar.add(WhiteChar106);

					LENIENT107=(Token)match(input,LENIENT,FOLLOW_LENIENT_in_lenient1287);  
					stream_LENIENT.add(LENIENT107);

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
					// 235:30: -> ^( String StringToken[\"\\\"TRUE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:33: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:69: 
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
					// 235:69: -> ^( String StringToken[\"\\\"FALSE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:72: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:1: opcmpcondition[CommonTree firstOp] : ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )? | ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) );
	public final ParameterizedIndicatorsParser.opcmpcondition_return opcmpcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.opcmpcondition_return retval = new ParameterizedIndicatorsParser.opcmpcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal108=null;
		Token WhiteChar109=null;
		Token WhiteChar110=null;
		Token string_literal111=null;
		Token WhiteChar112=null;
		Token WhiteChar113=null;
		Token DAYS114=null;
		Token WhiteChar115=null;
		Token string_literal116=null;
		Token WhiteChar117=null;
		Token PERCENT118=null;
		Token string_literal119=null;
		Token WhiteChar120=null;
		Token WhiteChar121=null;
		Token string_literal122=null;
		Token WhiteChar123=null;
		Token WhiteChar124=null;
		Token DAYS125=null;
		Token WhiteChar126=null;
		Token string_literal127=null;
		Token WhiteChar128=null;
		Token PERCENT129=null;
		Token string_literal130=null;
		Token WhiteChar131=null;
		Token WhiteChar132=null;
		Token string_literal133=null;
		Token WhiteChar134=null;
		Token WhiteChar135=null;
		Token DAYS136=null;
		Token WhiteChar137=null;
		Token string_literal138=null;
		Token WhiteChar139=null;
		Token PERCENT140=null;
		Token string_literal141=null;
		Token WhiteChar142=null;
		Token WhiteChar143=null;
		Token string_literal144=null;
		Token WhiteChar145=null;
		Token WhiteChar146=null;
		Token DAYS147=null;
		Token WhiteChar148=null;
		Token string_literal149=null;
		Token WhiteChar150=null;
		Token WhiteChar151=null;
		Token DAYS152=null;
		Token WhiteChar153=null;
		Token string_literal154=null;
		Token WhiteChar155=null;
		Token PERCENT156=null;
		Token WhiteChar157=null;
		Token string_literal158=null;
		Token WhiteChar159=null;
		Token PERCENT160=null;
		Token WhiteChar161=null;
		Token string_literal162=null;
		Token WhiteChar163=null;
		Token WhiteChar164=null;
		Token string_literal165=null;
		Token WhiteChar166=null;
		Token WhiteChar167=null;
		Token DAYS168=null;
		Token WhiteChar169=null;
		Token string_literal170=null;
		Token WhiteChar171=null;
		Token WhiteChar172=null;
		Token DAYS173=null;
		Token WhiteChar174=null;
		Token string_literal175=null;
		Token WhiteChar176=null;
		Token PERCENT177=null;
		Token WhiteChar178=null;
		Token string_literal179=null;
		Token WhiteChar180=null;
		Token PERCENT181=null;
		Token WhiteChar182=null;
		Token string_literal183=null;
		Token WhiteChar184=null;
		Token WhiteChar185=null;
		Token string_literal186=null;
		Token WhiteChar187=null;
		Token WhiteChar188=null;
		Token DAYS189=null;
		Token WhiteChar190=null;
		Token string_literal191=null;
		Token WhiteChar192=null;
		Token WhiteChar193=null;
		Token DAYS194=null;
		Token WhiteChar195=null;
		Token string_literal196=null;
		Token WhiteChar197=null;
		Token WhiteChar198=null;
		Token string_literal199=null;
		Token WhiteChar200=null;
		Token string_literal201=null;
		Token WhiteChar202=null;
		Token WhiteChar203=null;
		Token string_literal204=null;
		Token WhiteChar205=null;
		Token WhiteChar206=null;
		Token DAYS207=null;
		Token WhiteChar208=null;
		Token string_literal209=null;
		Token WhiteChar210=null;
		Token WhiteChar211=null;
		Token DAYS212=null;
		Token WhiteChar213=null;
		Token string_literal214=null;
		Token WhiteChar215=null;
		ParserRuleReturnScope secondOp =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope epsilon =null;
		ParserRuleReturnScope spanningNbDays =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope adaptiveRate =null;
		ParserRuleReturnScope adaptive =null;
		ParserRuleReturnScope direction =null;

		CommonTree string_literal108_tree=null;
		CommonTree WhiteChar109_tree=null;
		CommonTree WhiteChar110_tree=null;
		CommonTree string_literal111_tree=null;
		CommonTree WhiteChar112_tree=null;
		CommonTree WhiteChar113_tree=null;
		CommonTree DAYS114_tree=null;
		CommonTree WhiteChar115_tree=null;
		CommonTree string_literal116_tree=null;
		CommonTree WhiteChar117_tree=null;
		CommonTree PERCENT118_tree=null;
		CommonTree string_literal119_tree=null;
		CommonTree WhiteChar120_tree=null;
		CommonTree WhiteChar121_tree=null;
		CommonTree string_literal122_tree=null;
		CommonTree WhiteChar123_tree=null;
		CommonTree WhiteChar124_tree=null;
		CommonTree DAYS125_tree=null;
		CommonTree WhiteChar126_tree=null;
		CommonTree string_literal127_tree=null;
		CommonTree WhiteChar128_tree=null;
		CommonTree PERCENT129_tree=null;
		CommonTree string_literal130_tree=null;
		CommonTree WhiteChar131_tree=null;
		CommonTree WhiteChar132_tree=null;
		CommonTree string_literal133_tree=null;
		CommonTree WhiteChar134_tree=null;
		CommonTree WhiteChar135_tree=null;
		CommonTree DAYS136_tree=null;
		CommonTree WhiteChar137_tree=null;
		CommonTree string_literal138_tree=null;
		CommonTree WhiteChar139_tree=null;
		CommonTree PERCENT140_tree=null;
		CommonTree string_literal141_tree=null;
		CommonTree WhiteChar142_tree=null;
		CommonTree WhiteChar143_tree=null;
		CommonTree string_literal144_tree=null;
		CommonTree WhiteChar145_tree=null;
		CommonTree WhiteChar146_tree=null;
		CommonTree DAYS147_tree=null;
		CommonTree WhiteChar148_tree=null;
		CommonTree string_literal149_tree=null;
		CommonTree WhiteChar150_tree=null;
		CommonTree WhiteChar151_tree=null;
		CommonTree DAYS152_tree=null;
		CommonTree WhiteChar153_tree=null;
		CommonTree string_literal154_tree=null;
		CommonTree WhiteChar155_tree=null;
		CommonTree PERCENT156_tree=null;
		CommonTree WhiteChar157_tree=null;
		CommonTree string_literal158_tree=null;
		CommonTree WhiteChar159_tree=null;
		CommonTree PERCENT160_tree=null;
		CommonTree WhiteChar161_tree=null;
		CommonTree string_literal162_tree=null;
		CommonTree WhiteChar163_tree=null;
		CommonTree WhiteChar164_tree=null;
		CommonTree string_literal165_tree=null;
		CommonTree WhiteChar166_tree=null;
		CommonTree WhiteChar167_tree=null;
		CommonTree DAYS168_tree=null;
		CommonTree WhiteChar169_tree=null;
		CommonTree string_literal170_tree=null;
		CommonTree WhiteChar171_tree=null;
		CommonTree WhiteChar172_tree=null;
		CommonTree DAYS173_tree=null;
		CommonTree WhiteChar174_tree=null;
		CommonTree string_literal175_tree=null;
		CommonTree WhiteChar176_tree=null;
		CommonTree PERCENT177_tree=null;
		CommonTree WhiteChar178_tree=null;
		CommonTree string_literal179_tree=null;
		CommonTree WhiteChar180_tree=null;
		CommonTree PERCENT181_tree=null;
		CommonTree WhiteChar182_tree=null;
		CommonTree string_literal183_tree=null;
		CommonTree WhiteChar184_tree=null;
		CommonTree WhiteChar185_tree=null;
		CommonTree string_literal186_tree=null;
		CommonTree WhiteChar187_tree=null;
		CommonTree WhiteChar188_tree=null;
		CommonTree DAYS189_tree=null;
		CommonTree WhiteChar190_tree=null;
		CommonTree string_literal191_tree=null;
		CommonTree WhiteChar192_tree=null;
		CommonTree WhiteChar193_tree=null;
		CommonTree DAYS194_tree=null;
		CommonTree WhiteChar195_tree=null;
		CommonTree string_literal196_tree=null;
		CommonTree WhiteChar197_tree=null;
		CommonTree WhiteChar198_tree=null;
		CommonTree string_literal199_tree=null;
		CommonTree WhiteChar200_tree=null;
		CommonTree string_literal201_tree=null;
		CommonTree WhiteChar202_tree=null;
		CommonTree WhiteChar203_tree=null;
		CommonTree string_literal204_tree=null;
		CommonTree WhiteChar205_tree=null;
		CommonTree WhiteChar206_tree=null;
		CommonTree DAYS207_tree=null;
		CommonTree WhiteChar208_tree=null;
		CommonTree string_literal209_tree=null;
		CommonTree WhiteChar210_tree=null;
		CommonTree WhiteChar211_tree=null;
		CommonTree DAYS212_tree=null;
		CommonTree WhiteChar213_tree=null;
		CommonTree string_literal214_tree=null;
		CommonTree WhiteChar215_tree=null;
		RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_110=new RewriteRuleTokenStream(adaptor,"token 110");
		RewriteRuleTokenStream stream_111=new RewriteRuleTokenStream(adaptor,"token 111");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_105=new RewriteRuleTokenStream(adaptor,"token 105");
		RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
		RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
		RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
		RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
		RewriteRuleTokenStream stream_87=new RewriteRuleTokenStream(adaptor,"token 87");
		RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
		RewriteRuleTokenStream stream_98=new RewriteRuleTokenStream(adaptor,"token 98");
		RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
		RewriteRuleSubtreeStream stream_stringconstant=new RewriteRuleSubtreeStream(adaptor,"rule stringconstant");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:37: ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )? | ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) )
			int alt37=7;
			switch ( input.LA(1) ) {
			case 83:
				{
				alt37=1;
				}
				break;
			case 87:
				{
				alt37=2;
				}
				break;
			case 76:
				{
				alt37=3;
				}
				break;
			case 69:
				{
				alt37=4;
				}
				break;
			case 71:
				{
				alt37=5;
				}
				break;
			case 110:
				{
				alt37=6;
				}
				break;
			case 111:
				{
				alt37=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 37, 0, input);
				throw nvae;
			}
			switch (alt37) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:4: 'is above historical' WhiteChar secondOp= operand
					{
					string_literal108=(Token)match(input,83,FOLLOW_83_in_opcmpcondition1324);  
					stream_83.add(string_literal108);

					WhiteChar109=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1326);  
					stream_WhiteChar.add(WhiteChar109);

					pushFollow(FOLLOW_operand_in_opcmpcondition1330);
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
					// 240:53: -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:56: ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupDoubleMapCondition, "SupDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:80: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:107: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )?
					int alt30=2;
					int LA30_0 = input.LA(1);
					if ( (LA30_0==WhiteChar) ) {
						int LA30_1 = input.LA(2);
						if ( (LA30_1==79) ) {
							alt30=1;
						}
					}
					switch (alt30) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:5: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar110=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1361);  
							stream_WhiteChar.add(WhiteChar110);

							string_literal111=(Token)match(input,79,FOLLOW_79_in_opcmpcondition1363);  
							stream_79.add(string_literal111);

							WhiteChar112=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1365);  
							stream_WhiteChar.add(WhiteChar112);

							pushFollow(FOLLOW_constant_in_opcmpcondition1369);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar113=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1371);  
							stream_WhiteChar.add(WhiteChar113);

							DAYS114=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1373);  
							stream_DAYS.add(DAYS114);

							WhiteChar115=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1375);  
							stream_WhiteChar.add(WhiteChar115);

							string_literal116=(Token)match(input,75,FOLLOW_75_in_opcmpcondition1377);  
							stream_75.add(string_literal116);

							WhiteChar117=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1379);  
							stream_WhiteChar.add(WhiteChar117);

							pushFollow(FOLLOW_constant_in_opcmpcondition1383);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT118=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1385);  
							stream_PERCENT.add(PERCENT118);

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
							// 242:5: -> ^( SupDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:8: ^( SupDoubleMapCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupDoubleMapCondition, "SupDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:4: 'is below historical' WhiteChar secondOp= operand
					{
					string_literal119=(Token)match(input,87,FOLLOW_87_in_opcmpcondition1417);  
					stream_87.add(string_literal119);

					WhiteChar120=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1419);  
					stream_WhiteChar.add(WhiteChar120);

					pushFollow(FOLLOW_operand_in_opcmpcondition1423);
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
					// 244:53: -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:56: ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfDoubleMapCondition, "InfDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:80: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:107: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )?
					int alt31=2;
					int LA31_0 = input.LA(1);
					if ( (LA31_0==WhiteChar) ) {
						int LA31_1 = input.LA(2);
						if ( (LA31_1==79) ) {
							alt31=1;
						}
					}
					switch (alt31) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:5: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar121=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1454);  
							stream_WhiteChar.add(WhiteChar121);

							string_literal122=(Token)match(input,79,FOLLOW_79_in_opcmpcondition1456);  
							stream_79.add(string_literal122);

							WhiteChar123=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1458);  
							stream_WhiteChar.add(WhiteChar123);

							pushFollow(FOLLOW_constant_in_opcmpcondition1462);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar124=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1464);  
							stream_WhiteChar.add(WhiteChar124);

							DAYS125=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1466);  
							stream_DAYS.add(DAYS125);

							WhiteChar126=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1468);  
							stream_WhiteChar.add(WhiteChar126);

							string_literal127=(Token)match(input,75,FOLLOW_75_in_opcmpcondition1470);  
							stream_75.add(string_literal127);

							WhiteChar128=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1472);  
							stream_WhiteChar.add(WhiteChar128);

							pushFollow(FOLLOW_constant_in_opcmpcondition1476);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT129=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1478);  
							stream_PERCENT.add(PERCENT129);

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
							// 246:5: -> ^( InfDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:8: ^( InfDoubleMapCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfDoubleMapCondition, "InfDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:4: 'equals historical' WhiteChar secondOp= operand
					{
					string_literal130=(Token)match(input,76,FOLLOW_76_in_opcmpcondition1509);  
					stream_76.add(string_literal130);

					WhiteChar131=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1511);  
					stream_WhiteChar.add(WhiteChar131);

					pushFollow(FOLLOW_operand_in_opcmpcondition1515);
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
					// 248:51: -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:54: ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualDoubleMapCondition, "EqualDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:80: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:107: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )?
					int alt32=2;
					int LA32_0 = input.LA(1);
					if ( (LA32_0==WhiteChar) ) {
						int LA32_1 = input.LA(2);
						if ( (LA32_1==79) ) {
							alt32=1;
						}
					}
					switch (alt32) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:5: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar132=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1546);  
							stream_WhiteChar.add(WhiteChar132);

							string_literal133=(Token)match(input,79,FOLLOW_79_in_opcmpcondition1548);  
							stream_79.add(string_literal133);

							WhiteChar134=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1550);  
							stream_WhiteChar.add(WhiteChar134);

							pushFollow(FOLLOW_constant_in_opcmpcondition1554);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar135=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1556);  
							stream_WhiteChar.add(WhiteChar135);

							DAYS136=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1558);  
							stream_DAYS.add(DAYS136);

							WhiteChar137=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1560);  
							stream_WhiteChar.add(WhiteChar137);

							string_literal138=(Token)match(input,75,FOLLOW_75_in_opcmpcondition1562);  
							stream_75.add(string_literal138);

							WhiteChar139=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1564);  
							stream_WhiteChar.add(WhiteChar139);

							pushFollow(FOLLOW_constant_in_opcmpcondition1568);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT140=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1570);  
							stream_PERCENT.add(PERCENT140);

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
							// 250:5: -> ^( EqualDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:8: ^( EqualDoubleMapCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualDoubleMapCondition, "EqualDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:3: ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:3: ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:4: 'crosses down historical' WhiteChar secondOp= operand
					{
					string_literal141=(Token)match(input,69,FOLLOW_69_in_opcmpcondition1603);  
					stream_69.add(string_literal141);

					WhiteChar142=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1605);  
					stream_WhiteChar.add(WhiteChar142);

					pushFollow(FOLLOW_operand_in_opcmpcondition1609);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
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
					// 255:4: -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:7: ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:37: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:66: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:95: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:124: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (CommonTree)adaptor.create(NullCondition, "NullCondition"));
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_1, (secondOp!=null?((CommonTree)secondOp.getTree()):null));
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:3: ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )?
					int alt34=2;
					int LA34_0 = input.LA(1);
					if ( (LA34_0==WhiteChar) ) {
						int LA34_1 = input.LA(2);
						if ( (LA34_1==105) ) {
							alt34=1;
						}
					}
					switch (alt34) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )?
							{
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:5: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar143=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1663);  
							stream_WhiteChar.add(WhiteChar143);

							string_literal144=(Token)match(input,105,FOLLOW_105_in_opcmpcondition1665);  
							stream_105.add(string_literal144);

							WhiteChar145=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1667);  
							stream_WhiteChar.add(WhiteChar145);

							pushFollow(FOLLOW_constant_in_opcmpcondition1671);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar146=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1673);  
							stream_WhiteChar.add(WhiteChar146);

							DAYS147=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1675);  
							stream_DAYS.add(DAYS147);

							WhiteChar148=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1677);  
							stream_WhiteChar.add(WhiteChar148);

							string_literal149=(Token)match(input,98,FOLLOW_98_in_opcmpcondition1679);  
							stream_98.add(string_literal149);

							WhiteChar150=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1681);  
							stream_WhiteChar.add(WhiteChar150);

							pushFollow(FOLLOW_constant_in_opcmpcondition1685);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar151=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1687);  
							stream_WhiteChar.add(WhiteChar151);

							DAYS152=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1689);  
							stream_DAYS.add(DAYS152);

							WhiteChar153=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1691);  
							stream_WhiteChar.add(WhiteChar153);

							string_literal154=(Token)match(input,75,FOLLOW_75_in_opcmpcondition1693);  
							stream_75.add(string_literal154);

							WhiteChar155=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1695);  
							stream_WhiteChar.add(WhiteChar155);

							pushFollow(FOLLOW_constant_in_opcmpcondition1699);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT156=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1701);  
							stream_PERCENT.add(PERCENT156);

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
							// 258:5: -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:8: ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:96: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (CommonTree)adaptor.create(NullCondition, "NullCondition"));
								adaptor.addChild(root_1, firstOp);
								adaptor.addChild(root_1, (secondOp!=null?((CommonTree)secondOp.getTree()):null));
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}

							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:4: ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )?
							int alt33=2;
							int LA33_0 = input.LA(1);
							if ( (LA33_0==WhiteChar) ) {
								int LA33_1 = input.LA(2);
								if ( (LA33_1==65) ) {
									alt33=1;
								}
							}
							switch (alt33) {
								case 1 :
									// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:5: WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom
									{
									WhiteChar157=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1741);  
									stream_WhiteChar.add(WhiteChar157);

									string_literal158=(Token)match(input,65,FOLLOW_65_in_opcmpcondition1743);  
									stream_65.add(string_literal158);

									WhiteChar159=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1745);  
									stream_WhiteChar.add(WhiteChar159);

									pushFollow(FOLLOW_constant_in_opcmpcondition1749);
									adaptiveRate=constant();
									state._fsp--;

									stream_constant.add(adaptiveRate.getTree());
									PERCENT160=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1751);  
									stream_PERCENT.add(PERCENT160);

									WhiteChar161=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1753);  
									stream_WhiteChar.add(WhiteChar161);

									pushFollow(FOLLOW_atom_in_opcmpcondition1757);
									adaptive=atom();
									state._fsp--;

									stream_atom.add(adaptive.getTree());
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
									// 261:5: -> ^( CrossDownDoubleMapCondition )
									{
										// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:8: ^( CrossDownDoubleMapCondition )
										{
										CommonTree root_1 = (CommonTree)adaptor.nil();
										root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
										adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
										adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
										adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
										adaptor.addChild(root_1, (adaptiveRate!=null?((CommonTree)adaptiveRate.getTree()):null));
										adaptor.addChild(root_1, (adaptive!=null?((CommonTree)adaptive.getTree()):null));
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

					}

					}
					break;
				case 5 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:3: ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:3: ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:4: 'crosses up historical' WhiteChar secondOp= operand
					{
					string_literal162=(Token)match(input,71,FOLLOW_71_in_opcmpcondition1800);  
					stream_71.add(string_literal162);

					WhiteChar163=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1802);  
					stream_WhiteChar.add(WhiteChar163);

					pushFollow(FOLLOW_operand_in_opcmpcondition1806);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
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
					// 265:4: -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:7: ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:35: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:64: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:93: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:122: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (CommonTree)adaptor.create(NullCondition, "NullCondition"));
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_1, (secondOp!=null?((CommonTree)secondOp.getTree()):null));
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:3: ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )?
					int alt36=2;
					int LA36_0 = input.LA(1);
					if ( (LA36_0==WhiteChar) ) {
						int LA36_1 = input.LA(2);
						if ( (LA36_1==105) ) {
							alt36=1;
						}
					}
					switch (alt36) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )?
							{
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:5: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar164=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1860);  
							stream_WhiteChar.add(WhiteChar164);

							string_literal165=(Token)match(input,105,FOLLOW_105_in_opcmpcondition1862);  
							stream_105.add(string_literal165);

							WhiteChar166=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1864);  
							stream_WhiteChar.add(WhiteChar166);

							pushFollow(FOLLOW_constant_in_opcmpcondition1868);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar167=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1870);  
							stream_WhiteChar.add(WhiteChar167);

							DAYS168=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1872);  
							stream_DAYS.add(DAYS168);

							WhiteChar169=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1874);  
							stream_WhiteChar.add(WhiteChar169);

							string_literal170=(Token)match(input,98,FOLLOW_98_in_opcmpcondition1876);  
							stream_98.add(string_literal170);

							WhiteChar171=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1878);  
							stream_WhiteChar.add(WhiteChar171);

							pushFollow(FOLLOW_constant_in_opcmpcondition1882);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar172=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1884);  
							stream_WhiteChar.add(WhiteChar172);

							DAYS173=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1886);  
							stream_DAYS.add(DAYS173);

							WhiteChar174=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1888);  
							stream_WhiteChar.add(WhiteChar174);

							string_literal175=(Token)match(input,75,FOLLOW_75_in_opcmpcondition1890);  
							stream_75.add(string_literal175);

							WhiteChar176=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1892);  
							stream_WhiteChar.add(WhiteChar176);

							pushFollow(FOLLOW_constant_in_opcmpcondition1896);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT177=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1898);  
							stream_PERCENT.add(PERCENT177);

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
							// 268:5: -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:268:8: ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:268:94: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (CommonTree)adaptor.create(NullCondition, "NullCondition"));
								adaptor.addChild(root_1, firstOp);
								adaptor.addChild(root_1, (secondOp!=null?((CommonTree)secondOp.getTree()):null));
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}

							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:4: ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )?
							int alt35=2;
							int LA35_0 = input.LA(1);
							if ( (LA35_0==WhiteChar) ) {
								int LA35_1 = input.LA(2);
								if ( (LA35_1==65) ) {
									alt35=1;
								}
							}
							switch (alt35) {
								case 1 :
									// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:5: WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom
									{
									WhiteChar178=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1938);  
									stream_WhiteChar.add(WhiteChar178);

									string_literal179=(Token)match(input,65,FOLLOW_65_in_opcmpcondition1940);  
									stream_65.add(string_literal179);

									WhiteChar180=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1942);  
									stream_WhiteChar.add(WhiteChar180);

									pushFollow(FOLLOW_constant_in_opcmpcondition1946);
									adaptiveRate=constant();
									state._fsp--;

									stream_constant.add(adaptiveRate.getTree());
									PERCENT181=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1948);  
									stream_PERCENT.add(PERCENT181);

									WhiteChar182=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1950);  
									stream_WhiteChar.add(WhiteChar182);

									pushFollow(FOLLOW_atom_in_opcmpcondition1954);
									adaptive=atom();
									state._fsp--;

									stream_atom.add(adaptive.getTree());
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
									// 271:5: -> ^( CrossUpDoubleMapCondition )
									{
										// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:8: ^( CrossUpDoubleMapCondition )
										{
										CommonTree root_1 = (CommonTree)adaptor.nil();
										root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
										adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
										adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
										adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
										adaptor.addChild(root_1, (adaptiveRate!=null?((CommonTree)adaptiveRate.getTree()):null));
										adaptor.addChild(root_1, (adaptive!=null?((CommonTree)adaptive.getTree()):null));
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

					}

					}
					break;
				case 6 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:4: 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal183=(Token)match(input,110,FOLLOW_110_in_opcmpcondition1999);  
					stream_110.add(string_literal183);

					WhiteChar184=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2001);  
					stream_WhiteChar.add(WhiteChar184);

					pushFollow(FOLLOW_operand_in_opcmpcondition2005);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar185=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2013);  
					stream_WhiteChar.add(WhiteChar185);

					string_literal186=(Token)match(input,98,FOLLOW_98_in_opcmpcondition2015);  
					stream_98.add(string_literal186);

					WhiteChar187=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2017);  
					stream_WhiteChar.add(WhiteChar187);

					pushFollow(FOLLOW_constant_in_opcmpcondition2021);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar188=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2023);  
					stream_WhiteChar.add(WhiteChar188);

					DAYS189=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition2025);  
					stream_DAYS.add(DAYS189);

					WhiteChar190=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2033);  
					stream_WhiteChar.add(WhiteChar190);

					string_literal191=(Token)match(input,79,FOLLOW_79_in_opcmpcondition2035);  
					stream_79.add(string_literal191);

					WhiteChar192=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2037);  
					stream_WhiteChar.add(WhiteChar192);

					pushFollow(FOLLOW_constant_in_opcmpcondition2041);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar193=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2043);  
					stream_WhiteChar.add(WhiteChar193);

					DAYS194=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition2045);  
					stream_DAYS.add(DAYS194);

					WhiteChar195=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2053);  
					stream_WhiteChar.add(WhiteChar195);

					string_literal196=(Token)match(input,73,FOLLOW_73_in_opcmpcondition2055);  
					stream_73.add(string_literal196);

					WhiteChar197=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2057);  
					stream_WhiteChar.add(WhiteChar197);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition2061);
					direction=stringconstant();
					state._fsp--;

					stream_stringconstant.add(direction.getTree());
					WhiteChar198=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2069);  
					stream_WhiteChar.add(WhiteChar198);

					string_literal199=(Token)match(input,75,FOLLOW_75_in_opcmpcondition2071);  
					stream_75.add(string_literal199);

					WhiteChar200=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2073);  
					stream_WhiteChar.add(WhiteChar200);

					pushFollow(FOLLOW_constant_in_opcmpcondition2077);
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
					// 281:7: -> ^( LinearSimilarTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:10: ^( LinearSimilarTrendsCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:4: 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant
					{
					string_literal201=(Token)match(input,111,FOLLOW_111_in_opcmpcondition2109);  
					stream_111.add(string_literal201);

					WhiteChar202=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2111);  
					stream_WhiteChar.add(WhiteChar202);

					pushFollow(FOLLOW_operand_in_opcmpcondition2115);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar203=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2123);  
					stream_WhiteChar.add(WhiteChar203);

					string_literal204=(Token)match(input,98,FOLLOW_98_in_opcmpcondition2125);  
					stream_98.add(string_literal204);

					WhiteChar205=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2127);  
					stream_WhiteChar.add(WhiteChar205);

					pushFollow(FOLLOW_constant_in_opcmpcondition2131);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar206=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2133);  
					stream_WhiteChar.add(WhiteChar206);

					DAYS207=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition2135);  
					stream_DAYS.add(DAYS207);

					WhiteChar208=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2143);  
					stream_WhiteChar.add(WhiteChar208);

					string_literal209=(Token)match(input,79,FOLLOW_79_in_opcmpcondition2145);  
					stream_79.add(string_literal209);

					WhiteChar210=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2147);  
					stream_WhiteChar.add(WhiteChar210);

					pushFollow(FOLLOW_constant_in_opcmpcondition2151);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar211=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2153);  
					stream_WhiteChar.add(WhiteChar211);

					DAYS212=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition2155);  
					stream_DAYS.add(DAYS212);

					WhiteChar213=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2163);  
					stream_WhiteChar.add(WhiteChar213);

					string_literal214=(Token)match(input,73,FOLLOW_73_in_opcmpcondition2165);  
					stream_73.add(string_literal214);

					WhiteChar215=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2167);  
					stream_WhiteChar.add(WhiteChar215);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition2171);
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
					// 286:7: -> ^( LinearOppositeTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:10: ^( LinearOppositeTrendsCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:1: constantcmp[CommonTree firstOp] : ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )? );
	public final ParameterizedIndicatorsParser.constantcmp_return constantcmp(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.constantcmp_return retval = new ParameterizedIndicatorsParser.constantcmp_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal216=null;
		Token WhiteChar217=null;
		Token WhiteChar218=null;
		Token string_literal219=null;
		Token WhiteChar220=null;
		Token WhiteChar221=null;
		Token DAYS222=null;
		Token WhiteChar223=null;
		Token string_literal224=null;
		Token WhiteChar225=null;
		Token WhiteChar226=null;
		Token DAYS227=null;
		Token string_literal228=null;
		Token WhiteChar229=null;
		Token WhiteChar230=null;
		Token string_literal231=null;
		Token WhiteChar232=null;
		Token WhiteChar233=null;
		Token DAYS234=null;
		Token WhiteChar235=null;
		Token string_literal236=null;
		Token WhiteChar237=null;
		Token WhiteChar238=null;
		Token DAYS239=null;
		Token WhiteChar240=null;
		Token string_literal241=null;
		Token WhiteChar242=null;
		Token PERCENT243=null;
		Token string_literal244=null;
		Token WhiteChar245=null;
		Token WhiteChar246=null;
		Token string_literal247=null;
		Token WhiteChar248=null;
		Token WhiteChar249=null;
		Token DAYS250=null;
		Token WhiteChar251=null;
		Token string_literal252=null;
		Token WhiteChar253=null;
		Token WhiteChar254=null;
		Token DAYS255=null;
		Token WhiteChar256=null;
		Token string_literal257=null;
		Token WhiteChar258=null;
		Token PERCENT259=null;
		Token string_literal260=null;
		Token WhiteChar261=null;
		Token WhiteChar262=null;
		Token string_literal263=null;
		Token WhiteChar264=null;
		Token WhiteChar265=null;
		Token DAYS266=null;
		Token WhiteChar267=null;
		Token string_literal268=null;
		Token WhiteChar269=null;
		Token WhiteChar270=null;
		Token DAYS271=null;
		Token WhiteChar272=null;
		Token string_literal273=null;
		Token WhiteChar274=null;
		Token PERCENT275=null;
		ParserRuleReturnScope trendSignal =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope threshold =null;
		ParserRuleReturnScope epsilon =null;

		CommonTree string_literal216_tree=null;
		CommonTree WhiteChar217_tree=null;
		CommonTree WhiteChar218_tree=null;
		CommonTree string_literal219_tree=null;
		CommonTree WhiteChar220_tree=null;
		CommonTree WhiteChar221_tree=null;
		CommonTree DAYS222_tree=null;
		CommonTree WhiteChar223_tree=null;
		CommonTree string_literal224_tree=null;
		CommonTree WhiteChar225_tree=null;
		CommonTree WhiteChar226_tree=null;
		CommonTree DAYS227_tree=null;
		CommonTree string_literal228_tree=null;
		CommonTree WhiteChar229_tree=null;
		CommonTree WhiteChar230_tree=null;
		CommonTree string_literal231_tree=null;
		CommonTree WhiteChar232_tree=null;
		CommonTree WhiteChar233_tree=null;
		CommonTree DAYS234_tree=null;
		CommonTree WhiteChar235_tree=null;
		CommonTree string_literal236_tree=null;
		CommonTree WhiteChar237_tree=null;
		CommonTree WhiteChar238_tree=null;
		CommonTree DAYS239_tree=null;
		CommonTree WhiteChar240_tree=null;
		CommonTree string_literal241_tree=null;
		CommonTree WhiteChar242_tree=null;
		CommonTree PERCENT243_tree=null;
		CommonTree string_literal244_tree=null;
		CommonTree WhiteChar245_tree=null;
		CommonTree WhiteChar246_tree=null;
		CommonTree string_literal247_tree=null;
		CommonTree WhiteChar248_tree=null;
		CommonTree WhiteChar249_tree=null;
		CommonTree DAYS250_tree=null;
		CommonTree WhiteChar251_tree=null;
		CommonTree string_literal252_tree=null;
		CommonTree WhiteChar253_tree=null;
		CommonTree WhiteChar254_tree=null;
		CommonTree DAYS255_tree=null;
		CommonTree WhiteChar256_tree=null;
		CommonTree string_literal257_tree=null;
		CommonTree WhiteChar258_tree=null;
		CommonTree PERCENT259_tree=null;
		CommonTree string_literal260_tree=null;
		CommonTree WhiteChar261_tree=null;
		CommonTree WhiteChar262_tree=null;
		CommonTree string_literal263_tree=null;
		CommonTree WhiteChar264_tree=null;
		CommonTree WhiteChar265_tree=null;
		CommonTree DAYS266_tree=null;
		CommonTree WhiteChar267_tree=null;
		CommonTree string_literal268_tree=null;
		CommonTree WhiteChar269_tree=null;
		CommonTree WhiteChar270_tree=null;
		CommonTree DAYS271_tree=null;
		CommonTree WhiteChar272_tree=null;
		CommonTree string_literal273_tree=null;
		CommonTree WhiteChar274_tree=null;
		CommonTree PERCENT275_tree=null;
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");
		RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
		RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
		RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
		RewriteRuleTokenStream stream_98=new RewriteRuleTokenStream(adaptor,"token 98");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_trendconstant=new RewriteRuleSubtreeStream(adaptor,"rule trendconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:34: ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )? )
			int alt42=4;
			switch ( input.LA(1) ) {
			case 78:
				{
				alt42=1;
				}
				break;
			case 77:
				{
				alt42=2;
				}
				break;
			case 84:
				{
				alt42=3;
				}
				break;
			case 88:
				{
				alt42=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 42, 0, input);
				throw nvae;
			}
			switch (alt42) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:4: 'equals trend' WhiteChar trendSignal= trendconstant
					{
					string_literal216=(Token)match(input,78,FOLLOW_78_in_constantcmp2208);  
					stream_78.add(string_literal216);

					WhiteChar217=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2210);  
					stream_WhiteChar.add(WhiteChar217);

					pushFollow(FOLLOW_trendconstant_in_constantcmp2214);
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
					// 290:55: -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:58: ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualStringConstantCondition, "EqualStringConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_trendconstant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:103: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:130: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					int alt38=2;
					int LA38_0 = input.LA(1);
					if ( (LA38_0==WhiteChar) ) {
						int LA38_1 = input.LA(2);
						if ( (LA38_1==98) ) {
							alt38=1;
						}
					}
					switch (alt38) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar218=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2248);  
							stream_WhiteChar.add(WhiteChar218);

							string_literal219=(Token)match(input,98,FOLLOW_98_in_constantcmp2250);  
							stream_98.add(string_literal219);

							WhiteChar220=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2252);  
							stream_WhiteChar.add(WhiteChar220);

							pushFollow(FOLLOW_constant_in_constantcmp2256);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar221=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2258);  
							stream_WhiteChar.add(WhiteChar221);

							DAYS222=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2260);  
							stream_DAYS.add(DAYS222);

							WhiteChar223=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2262);  
							stream_WhiteChar.add(WhiteChar223);

							string_literal224=(Token)match(input,79,FOLLOW_79_in_constantcmp2264);  
							stream_79.add(string_literal224);

							WhiteChar225=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2266);  
							stream_WhiteChar.add(WhiteChar225);

							pushFollow(FOLLOW_constant_in_constantcmp2270);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar226=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2272);  
							stream_WhiteChar.add(WhiteChar226);

							DAYS227=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2274);  
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
							// 291:129: -> ^( EqualStringConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:132: ^( EqualStringConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:4: 'equals threshold' WhiteChar threshold= constant
					{
					string_literal228=(Token)match(input,77,FOLLOW_77_in_constantcmp2298);  
					stream_77.add(string_literal228);

					WhiteChar229=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2300);  
					stream_WhiteChar.add(WhiteChar229);

					pushFollow(FOLLOW_constant_in_constantcmp2304);
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
					// 293:52: -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:55: ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualConstantCondition, "EqualConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:143: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )?
					int alt39=2;
					int LA39_0 = input.LA(1);
					if ( (LA39_0==WhiteChar) ) {
						int LA39_1 = input.LA(2);
						if ( (LA39_1==98) ) {
							alt39=1;
						}
					}
					switch (alt39) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar230=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2345);  
							stream_WhiteChar.add(WhiteChar230);

							string_literal231=(Token)match(input,98,FOLLOW_98_in_constantcmp2347);  
							stream_98.add(string_literal231);

							WhiteChar232=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2349);  
							stream_WhiteChar.add(WhiteChar232);

							pushFollow(FOLLOW_constant_in_constantcmp2353);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar233=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2355);  
							stream_WhiteChar.add(WhiteChar233);

							DAYS234=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2357);  
							stream_DAYS.add(DAYS234);

							WhiteChar235=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2363);  
							stream_WhiteChar.add(WhiteChar235);

							string_literal236=(Token)match(input,79,FOLLOW_79_in_constantcmp2365);  
							stream_79.add(string_literal236);

							WhiteChar237=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2367);  
							stream_WhiteChar.add(WhiteChar237);

							pushFollow(FOLLOW_constant_in_constantcmp2371);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar238=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2373);  
							stream_WhiteChar.add(WhiteChar238);

							DAYS239=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2375);  
							stream_DAYS.add(DAYS239);

							WhiteChar240=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2381);  
							stream_WhiteChar.add(WhiteChar240);

							string_literal241=(Token)match(input,75,FOLLOW_75_in_constantcmp2383);  
							stream_75.add(string_literal241);

							WhiteChar242=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2385);  
							stream_WhiteChar.add(WhiteChar242);

							pushFollow(FOLLOW_constant_in_constantcmp2389);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT243=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2391);  
							stream_PERCENT.add(PERCENT243);

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
							// 297:5: -> ^( EqualConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:8: ^( EqualConstantCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualConstantCondition, "EqualConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:4: 'is above threshold' WhiteChar threshold= constant
					{
					string_literal244=(Token)match(input,84,FOLLOW_84_in_constantcmp2421);  
					stream_84.add(string_literal244);

					WhiteChar245=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2423);  
					stream_WhiteChar.add(WhiteChar245);

					pushFollow(FOLLOW_constant_in_constantcmp2427);
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
					// 298:54: -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:57: ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupConstantCondition, "SupConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:143: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )?
					int alt40=2;
					int LA40_0 = input.LA(1);
					if ( (LA40_0==WhiteChar) ) {
						int LA40_1 = input.LA(2);
						if ( (LA40_1==98) ) {
							alt40=1;
						}
					}
					switch (alt40) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar246=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2468);  
							stream_WhiteChar.add(WhiteChar246);

							string_literal247=(Token)match(input,98,FOLLOW_98_in_constantcmp2470);  
							stream_98.add(string_literal247);

							WhiteChar248=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2472);  
							stream_WhiteChar.add(WhiteChar248);

							pushFollow(FOLLOW_constant_in_constantcmp2476);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar249=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2478);  
							stream_WhiteChar.add(WhiteChar249);

							DAYS250=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2480);  
							stream_DAYS.add(DAYS250);

							WhiteChar251=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2486);  
							stream_WhiteChar.add(WhiteChar251);

							string_literal252=(Token)match(input,79,FOLLOW_79_in_constantcmp2488);  
							stream_79.add(string_literal252);

							WhiteChar253=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2490);  
							stream_WhiteChar.add(WhiteChar253);

							pushFollow(FOLLOW_constant_in_constantcmp2494);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar254=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2496);  
							stream_WhiteChar.add(WhiteChar254);

							DAYS255=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2498);  
							stream_DAYS.add(DAYS255);

							WhiteChar256=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2504);  
							stream_WhiteChar.add(WhiteChar256);

							string_literal257=(Token)match(input,75,FOLLOW_75_in_constantcmp2506);  
							stream_75.add(string_literal257);

							WhiteChar258=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2508);  
							stream_WhiteChar.add(WhiteChar258);

							pushFollow(FOLLOW_constant_in_constantcmp2512);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT259=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2514);  
							stream_PERCENT.add(PERCENT259);

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
							// 302:5: -> ^( SupConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:8: ^( SupConstantCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupConstantCondition, "SupConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:303:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:303:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:303:4: 'is below threshold' WhiteChar threshold= constant
					{
					string_literal260=(Token)match(input,88,FOLLOW_88_in_constantcmp2544);  
					stream_88.add(string_literal260);

					WhiteChar261=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2546);  
					stream_WhiteChar.add(WhiteChar261);

					pushFollow(FOLLOW_constant_in_constantcmp2550);
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
					// 303:54: -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:303:57: ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfConstantCondition, "InfConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:303:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:303:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:303:143: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )?
					int alt41=2;
					int LA41_0 = input.LA(1);
					if ( (LA41_0==WhiteChar) ) {
						int LA41_1 = input.LA(2);
						if ( (LA41_1==98) ) {
							alt41=1;
						}
					}
					switch (alt41) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar262=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2591);  
							stream_WhiteChar.add(WhiteChar262);

							string_literal263=(Token)match(input,98,FOLLOW_98_in_constantcmp2593);  
							stream_98.add(string_literal263);

							WhiteChar264=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2595);  
							stream_WhiteChar.add(WhiteChar264);

							pushFollow(FOLLOW_constant_in_constantcmp2599);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar265=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2601);  
							stream_WhiteChar.add(WhiteChar265);

							DAYS266=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2603);  
							stream_DAYS.add(DAYS266);

							WhiteChar267=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2609);  
							stream_WhiteChar.add(WhiteChar267);

							string_literal268=(Token)match(input,79,FOLLOW_79_in_constantcmp2611);  
							stream_79.add(string_literal268);

							WhiteChar269=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2613);  
							stream_WhiteChar.add(WhiteChar269);

							pushFollow(FOLLOW_constant_in_constantcmp2617);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar270=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2619);  
							stream_WhiteChar.add(WhiteChar270);

							DAYS271=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2621);  
							stream_DAYS.add(DAYS271);

							WhiteChar272=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2627);  
							stream_WhiteChar.add(WhiteChar272);

							string_literal273=(Token)match(input,75,FOLLOW_75_in_constantcmp2629);  
							stream_75.add(string_literal273);

							WhiteChar274=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2631);  
							stream_WhiteChar.add(WhiteChar274);

							pushFollow(FOLLOW_constant_in_constantcmp2635);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT275=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2637);  
							stream_PERCENT.add(PERCENT275);

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
							// 307:5: -> ^( InfConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:307:8: ^( InfConstantCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfConstantCondition, "InfConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:1: presetcondition[CommonTree firstOp] : ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) ) | ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) ) | ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) ) );
	public final ParameterizedIndicatorsParser.presetcondition_return presetcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.presetcondition_return retval = new ParameterizedIndicatorsParser.presetcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal276=null;
		Token WhiteChar277=null;
		Token string_literal278=null;
		Token WhiteChar279=null;
		Token PERCENT280=null;
		Token WhiteChar281=null;
		Token string_literal282=null;
		Token WhiteChar283=null;
		Token WhiteChar284=null;
		Token DAYS285=null;
		Token string_literal286=null;
		Token WhiteChar287=null;
		Token string_literal288=null;
		Token WhiteChar289=null;
		Token PERCENT290=null;
		Token WhiteChar291=null;
		Token string_literal292=null;
		Token WhiteChar293=null;
		Token WhiteChar294=null;
		Token DAYS295=null;
		Token string_literal296=null;
		Token WhiteChar297=null;
		Token PERCENT298=null;
		Token WhiteChar299=null;
		Token string_literal300=null;
		Token WhiteChar301=null;
		Token WhiteChar302=null;
		Token DAYS303=null;
		Token WhiteChar304=null;
		Token string_literal305=null;
		Token WhiteChar306=null;
		Token WhiteChar307=null;
		Token DAYS308=null;
		Token string_literal309=null;
		Token WhiteChar310=null;
		Token PERCENT311=null;
		Token WhiteChar312=null;
		Token string_literal313=null;
		Token WhiteChar314=null;
		Token WhiteChar315=null;
		Token DAYS316=null;
		Token WhiteChar317=null;
		Token string_literal318=null;
		Token WhiteChar319=null;
		Token WhiteChar320=null;
		Token DAYS321=null;
		Token string_literal322=null;
		Token WhiteChar323=null;
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
		Token PERCENT337=null;
		Token string_literal338=null;
		Token WhiteChar339=null;
		Token WhiteChar340=null;
		Token string_literal341=null;
		Token WhiteChar342=null;
		Token WhiteChar343=null;
		Token DAYS344=null;
		Token WhiteChar345=null;
		Token string_literal346=null;
		Token WhiteChar347=null;
		Token WhiteChar348=null;
		Token DAYS349=null;
		Token WhiteChar350=null;
		Token string_literal351=null;
		Token WhiteChar352=null;
		Token PERCENT353=null;
		Token string_literal354=null;
		Token WhiteChar355=null;
		Token WhiteChar356=null;
		Token DAYS357=null;
		Token WhiteChar358=null;
		Token string_literal359=null;
		Token WhiteChar360=null;
		Token WhiteChar361=null;
		Token DAYS362=null;
		Token WhiteChar363=null;
		Token string_literal364=null;
		Token WhiteChar365=null;
		Token WhiteChar366=null;
		Token DAYS367=null;
		Token WhiteChar368=null;
		Token string_literal369=null;
		Token WhiteChar370=null;
		Token WhiteChar371=null;
		Token DAYS372=null;
		Token WhiteChar373=null;
		Token string_literal374=null;
		Token WhiteChar375=null;
		Token WhiteChar376=null;
		Token string_literal377=null;
		Token WhiteChar378=null;
		Token WhiteChar379=null;
		Token string_literal380=null;
		Token WhiteChar381=null;
		Token char_literal382=null;
		Token char_literal383=null;
		Token char_literal384=null;
		Token WhiteChar385=null;
		Token string_literal386=null;
		Token WhiteChar387=null;
		Token char_literal388=null;
		Token char_literal389=null;
		Token char_literal390=null;
		Token WhiteChar391=null;
		Token string_literal392=null;
		Token WhiteChar393=null;
		Token char_literal394=null;
		Token char_literal395=null;
		Token char_literal396=null;
		Token string_literal397=null;
		Token WhiteChar398=null;
		Token WhiteChar399=null;
		Token DAYS400=null;
		Token WhiteChar401=null;
		Token string_literal402=null;
		Token WhiteChar403=null;
		Token WhiteChar404=null;
		Token DAYS405=null;
		Token WhiteChar406=null;
		Token string_literal407=null;
		Token WhiteChar408=null;
		Token WhiteChar409=null;
		Token DAYS410=null;
		Token WhiteChar411=null;
		Token string_literal412=null;
		Token WhiteChar413=null;
		Token WhiteChar414=null;
		Token DAYS415=null;
		Token WhiteChar416=null;
		Token string_literal417=null;
		Token WhiteChar418=null;
		Token WhiteChar419=null;
		Token string_literal420=null;
		Token WhiteChar421=null;
		Token WhiteChar422=null;
		Token string_literal423=null;
		Token WhiteChar424=null;
		Token char_literal425=null;
		Token char_literal426=null;
		Token char_literal427=null;
		Token WhiteChar428=null;
		Token string_literal429=null;
		Token WhiteChar430=null;
		Token char_literal431=null;
		Token char_literal432=null;
		Token char_literal433=null;
		Token WhiteChar434=null;
		Token string_literal435=null;
		Token WhiteChar436=null;
		Token char_literal437=null;
		Token char_literal438=null;
		Token char_literal439=null;
		Token string_literal440=null;
		Token WhiteChar441=null;
		Token WhiteChar442=null;
		Token DAYS443=null;
		Token WhiteChar444=null;
		Token string_literal445=null;
		Token WhiteChar446=null;
		Token WhiteChar447=null;
		Token DAYS448=null;
		Token WhiteChar449=null;
		Token string_literal450=null;
		Token WhiteChar451=null;
		Token WhiteChar452=null;
		Token DAYS453=null;
		Token WhiteChar454=null;
		Token string_literal455=null;
		Token WhiteChar456=null;
		Token WhiteChar457=null;
		Token DAYS458=null;
		Token WhiteChar459=null;
		Token string_literal460=null;
		Token WhiteChar461=null;
		Token WhiteChar462=null;
		Token string_literal463=null;
		Token WhiteChar464=null;
		Token WhiteChar465=null;
		Token string_literal466=null;
		Token WhiteChar467=null;
		Token char_literal468=null;
		Token char_literal469=null;
		Token char_literal470=null;
		Token WhiteChar471=null;
		Token string_literal472=null;
		Token WhiteChar473=null;
		Token char_literal474=null;
		Token char_literal475=null;
		Token char_literal476=null;
		Token WhiteChar477=null;
		Token string_literal478=null;
		Token WhiteChar479=null;
		Token char_literal480=null;
		Token char_literal481=null;
		Token char_literal482=null;
		Token string_literal483=null;
		Token WhiteChar484=null;
		Token WhiteChar485=null;
		Token DAYS486=null;
		Token WhiteChar487=null;
		Token string_literal488=null;
		Token WhiteChar489=null;
		Token WhiteChar490=null;
		Token DAYS491=null;
		Token WhiteChar492=null;
		Token string_literal493=null;
		Token WhiteChar494=null;
		Token WhiteChar495=null;
		Token DAYS496=null;
		Token WhiteChar497=null;
		Token string_literal498=null;
		Token WhiteChar499=null;
		Token WhiteChar500=null;
		Token DAYS501=null;
		Token WhiteChar502=null;
		Token string_literal503=null;
		Token WhiteChar504=null;
		Token WhiteChar505=null;
		Token string_literal506=null;
		Token WhiteChar507=null;
		Token WhiteChar508=null;
		Token string_literal509=null;
		Token WhiteChar510=null;
		Token char_literal511=null;
		Token char_literal512=null;
		Token char_literal513=null;
		Token WhiteChar514=null;
		Token string_literal515=null;
		Token WhiteChar516=null;
		Token char_literal517=null;
		Token char_literal518=null;
		Token char_literal519=null;
		Token WhiteChar520=null;
		Token string_literal521=null;
		Token WhiteChar522=null;
		Token char_literal523=null;
		Token char_literal524=null;
		Token char_literal525=null;
		Token string_literal526=null;
		Token WhiteChar527=null;
		Token WhiteChar528=null;
		Token DAYS529=null;
		Token WhiteChar530=null;
		Token string_literal531=null;
		Token WhiteChar532=null;
		Token WhiteChar533=null;
		Token DAYS534=null;
		Token WhiteChar535=null;
		Token string_literal536=null;
		Token WhiteChar537=null;
		Token WhiteChar538=null;
		Token DAYS539=null;
		Token WhiteChar540=null;
		Token string_literal541=null;
		Token WhiteChar542=null;
		Token WhiteChar543=null;
		Token DAYS544=null;
		Token WhiteChar545=null;
		Token string_literal546=null;
		Token WhiteChar547=null;
		Token char_literal548=null;
		Token char_literal549=null;
		Token char_literal550=null;
		Token WhiteChar551=null;
		Token string_literal552=null;
		Token WhiteChar553=null;
		Token string_literal554=null;
		Token WhiteChar555=null;
		Token WhiteChar556=null;
		Token DAYS557=null;
		Token WhiteChar558=null;
		Token string_literal559=null;
		Token WhiteChar560=null;
		Token WhiteChar561=null;
		Token DAYS562=null;
		Token WhiteChar563=null;
		Token string_literal564=null;
		Token WhiteChar565=null;
		Token WhiteChar566=null;
		Token DAYS567=null;
		Token WhiteChar568=null;
		Token string_literal569=null;
		Token WhiteChar570=null;
		Token WhiteChar571=null;
		Token DAYS572=null;
		Token WhiteChar573=null;
		Token string_literal574=null;
		Token WhiteChar575=null;
		Token char_literal576=null;
		Token char_literal577=null;
		Token char_literal578=null;
		Token WhiteChar579=null;
		Token string_literal580=null;
		Token WhiteChar581=null;
		Token string_literal582=null;
		Token WhiteChar583=null;
		Token string_literal584=null;
		Token WhiteChar585=null;
		Token WhiteChar586=null;
		Token DAYS587=null;
		Token WhiteChar588=null;
		Token string_literal589=null;
		Token WhiteChar590=null;
		Token WhiteChar591=null;
		Token DAYS592=null;
		Token WhiteChar593=null;
		Token string_literal594=null;
		Token WhiteChar595=null;
		Token string_literal596=null;
		Token WhiteChar597=null;
		Token string_literal598=null;
		Token WhiteChar599=null;
		Token WhiteChar600=null;
		Token DAYS601=null;
		Token WhiteChar602=null;
		Token string_literal603=null;
		Token WhiteChar604=null;
		Token WhiteChar605=null;
		Token DAYS606=null;
		Token WhiteChar607=null;
		Token string_literal608=null;
		Token WhiteChar609=null;
		Token string_literal610=null;
		Token WhiteChar611=null;
		Token string_literal612=null;
		Token WhiteChar613=null;
		Token WhiteChar614=null;
		Token DAYS615=null;
		Token WhiteChar616=null;
		Token string_literal617=null;
		Token WhiteChar618=null;
		Token WhiteChar619=null;
		Token DAYS620=null;
		Token WhiteChar621=null;
		Token string_literal622=null;
		Token WhiteChar623=null;
		ParserRuleReturnScope percentdown =null;
		ParserRuleReturnScope spanningNbDays =null;
		ParserRuleReturnScope percentup =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope threshold =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope epsilon =null;
		ParserRuleReturnScope lookBack =null;
		ParserRuleReturnScope remanencePeriod =null;
		ParserRuleReturnScope extremesSpan =null;
		ParserRuleReturnScope smoothP =null;
		ParserRuleReturnScope greed =null;
		ParserRuleReturnScope type =null;
		ParserRuleReturnScope lowestStart =null;
		ParserRuleReturnScope highestStart =null;
		ParserRuleReturnScope lowestEnd =null;
		ParserRuleReturnScope highestEnd =null;
		ParserRuleReturnScope minSlope =null;
		ParserRuleReturnScope maxSlope =null;
		ParserRuleReturnScope tolerance =null;

		CommonTree string_literal276_tree=null;
		CommonTree WhiteChar277_tree=null;
		CommonTree string_literal278_tree=null;
		CommonTree WhiteChar279_tree=null;
		CommonTree PERCENT280_tree=null;
		CommonTree WhiteChar281_tree=null;
		CommonTree string_literal282_tree=null;
		CommonTree WhiteChar283_tree=null;
		CommonTree WhiteChar284_tree=null;
		CommonTree DAYS285_tree=null;
		CommonTree string_literal286_tree=null;
		CommonTree WhiteChar287_tree=null;
		CommonTree string_literal288_tree=null;
		CommonTree WhiteChar289_tree=null;
		CommonTree PERCENT290_tree=null;
		CommonTree WhiteChar291_tree=null;
		CommonTree string_literal292_tree=null;
		CommonTree WhiteChar293_tree=null;
		CommonTree WhiteChar294_tree=null;
		CommonTree DAYS295_tree=null;
		CommonTree string_literal296_tree=null;
		CommonTree WhiteChar297_tree=null;
		CommonTree PERCENT298_tree=null;
		CommonTree WhiteChar299_tree=null;
		CommonTree string_literal300_tree=null;
		CommonTree WhiteChar301_tree=null;
		CommonTree WhiteChar302_tree=null;
		CommonTree DAYS303_tree=null;
		CommonTree WhiteChar304_tree=null;
		CommonTree string_literal305_tree=null;
		CommonTree WhiteChar306_tree=null;
		CommonTree WhiteChar307_tree=null;
		CommonTree DAYS308_tree=null;
		CommonTree string_literal309_tree=null;
		CommonTree WhiteChar310_tree=null;
		CommonTree PERCENT311_tree=null;
		CommonTree WhiteChar312_tree=null;
		CommonTree string_literal313_tree=null;
		CommonTree WhiteChar314_tree=null;
		CommonTree WhiteChar315_tree=null;
		CommonTree DAYS316_tree=null;
		CommonTree WhiteChar317_tree=null;
		CommonTree string_literal318_tree=null;
		CommonTree WhiteChar319_tree=null;
		CommonTree WhiteChar320_tree=null;
		CommonTree DAYS321_tree=null;
		CommonTree string_literal322_tree=null;
		CommonTree WhiteChar323_tree=null;
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
		CommonTree PERCENT337_tree=null;
		CommonTree string_literal338_tree=null;
		CommonTree WhiteChar339_tree=null;
		CommonTree WhiteChar340_tree=null;
		CommonTree string_literal341_tree=null;
		CommonTree WhiteChar342_tree=null;
		CommonTree WhiteChar343_tree=null;
		CommonTree DAYS344_tree=null;
		CommonTree WhiteChar345_tree=null;
		CommonTree string_literal346_tree=null;
		CommonTree WhiteChar347_tree=null;
		CommonTree WhiteChar348_tree=null;
		CommonTree DAYS349_tree=null;
		CommonTree WhiteChar350_tree=null;
		CommonTree string_literal351_tree=null;
		CommonTree WhiteChar352_tree=null;
		CommonTree PERCENT353_tree=null;
		CommonTree string_literal354_tree=null;
		CommonTree WhiteChar355_tree=null;
		CommonTree WhiteChar356_tree=null;
		CommonTree DAYS357_tree=null;
		CommonTree WhiteChar358_tree=null;
		CommonTree string_literal359_tree=null;
		CommonTree WhiteChar360_tree=null;
		CommonTree WhiteChar361_tree=null;
		CommonTree DAYS362_tree=null;
		CommonTree WhiteChar363_tree=null;
		CommonTree string_literal364_tree=null;
		CommonTree WhiteChar365_tree=null;
		CommonTree WhiteChar366_tree=null;
		CommonTree DAYS367_tree=null;
		CommonTree WhiteChar368_tree=null;
		CommonTree string_literal369_tree=null;
		CommonTree WhiteChar370_tree=null;
		CommonTree WhiteChar371_tree=null;
		CommonTree DAYS372_tree=null;
		CommonTree WhiteChar373_tree=null;
		CommonTree string_literal374_tree=null;
		CommonTree WhiteChar375_tree=null;
		CommonTree WhiteChar376_tree=null;
		CommonTree string_literal377_tree=null;
		CommonTree WhiteChar378_tree=null;
		CommonTree WhiteChar379_tree=null;
		CommonTree string_literal380_tree=null;
		CommonTree WhiteChar381_tree=null;
		CommonTree char_literal382_tree=null;
		CommonTree char_literal383_tree=null;
		CommonTree char_literal384_tree=null;
		CommonTree WhiteChar385_tree=null;
		CommonTree string_literal386_tree=null;
		CommonTree WhiteChar387_tree=null;
		CommonTree char_literal388_tree=null;
		CommonTree char_literal389_tree=null;
		CommonTree char_literal390_tree=null;
		CommonTree WhiteChar391_tree=null;
		CommonTree string_literal392_tree=null;
		CommonTree WhiteChar393_tree=null;
		CommonTree char_literal394_tree=null;
		CommonTree char_literal395_tree=null;
		CommonTree char_literal396_tree=null;
		CommonTree string_literal397_tree=null;
		CommonTree WhiteChar398_tree=null;
		CommonTree WhiteChar399_tree=null;
		CommonTree DAYS400_tree=null;
		CommonTree WhiteChar401_tree=null;
		CommonTree string_literal402_tree=null;
		CommonTree WhiteChar403_tree=null;
		CommonTree WhiteChar404_tree=null;
		CommonTree DAYS405_tree=null;
		CommonTree WhiteChar406_tree=null;
		CommonTree string_literal407_tree=null;
		CommonTree WhiteChar408_tree=null;
		CommonTree WhiteChar409_tree=null;
		CommonTree DAYS410_tree=null;
		CommonTree WhiteChar411_tree=null;
		CommonTree string_literal412_tree=null;
		CommonTree WhiteChar413_tree=null;
		CommonTree WhiteChar414_tree=null;
		CommonTree DAYS415_tree=null;
		CommonTree WhiteChar416_tree=null;
		CommonTree string_literal417_tree=null;
		CommonTree WhiteChar418_tree=null;
		CommonTree WhiteChar419_tree=null;
		CommonTree string_literal420_tree=null;
		CommonTree WhiteChar421_tree=null;
		CommonTree WhiteChar422_tree=null;
		CommonTree string_literal423_tree=null;
		CommonTree WhiteChar424_tree=null;
		CommonTree char_literal425_tree=null;
		CommonTree char_literal426_tree=null;
		CommonTree char_literal427_tree=null;
		CommonTree WhiteChar428_tree=null;
		CommonTree string_literal429_tree=null;
		CommonTree WhiteChar430_tree=null;
		CommonTree char_literal431_tree=null;
		CommonTree char_literal432_tree=null;
		CommonTree char_literal433_tree=null;
		CommonTree WhiteChar434_tree=null;
		CommonTree string_literal435_tree=null;
		CommonTree WhiteChar436_tree=null;
		CommonTree char_literal437_tree=null;
		CommonTree char_literal438_tree=null;
		CommonTree char_literal439_tree=null;
		CommonTree string_literal440_tree=null;
		CommonTree WhiteChar441_tree=null;
		CommonTree WhiteChar442_tree=null;
		CommonTree DAYS443_tree=null;
		CommonTree WhiteChar444_tree=null;
		CommonTree string_literal445_tree=null;
		CommonTree WhiteChar446_tree=null;
		CommonTree WhiteChar447_tree=null;
		CommonTree DAYS448_tree=null;
		CommonTree WhiteChar449_tree=null;
		CommonTree string_literal450_tree=null;
		CommonTree WhiteChar451_tree=null;
		CommonTree WhiteChar452_tree=null;
		CommonTree DAYS453_tree=null;
		CommonTree WhiteChar454_tree=null;
		CommonTree string_literal455_tree=null;
		CommonTree WhiteChar456_tree=null;
		CommonTree WhiteChar457_tree=null;
		CommonTree DAYS458_tree=null;
		CommonTree WhiteChar459_tree=null;
		CommonTree string_literal460_tree=null;
		CommonTree WhiteChar461_tree=null;
		CommonTree WhiteChar462_tree=null;
		CommonTree string_literal463_tree=null;
		CommonTree WhiteChar464_tree=null;
		CommonTree WhiteChar465_tree=null;
		CommonTree string_literal466_tree=null;
		CommonTree WhiteChar467_tree=null;
		CommonTree char_literal468_tree=null;
		CommonTree char_literal469_tree=null;
		CommonTree char_literal470_tree=null;
		CommonTree WhiteChar471_tree=null;
		CommonTree string_literal472_tree=null;
		CommonTree WhiteChar473_tree=null;
		CommonTree char_literal474_tree=null;
		CommonTree char_literal475_tree=null;
		CommonTree char_literal476_tree=null;
		CommonTree WhiteChar477_tree=null;
		CommonTree string_literal478_tree=null;
		CommonTree WhiteChar479_tree=null;
		CommonTree char_literal480_tree=null;
		CommonTree char_literal481_tree=null;
		CommonTree char_literal482_tree=null;
		CommonTree string_literal483_tree=null;
		CommonTree WhiteChar484_tree=null;
		CommonTree WhiteChar485_tree=null;
		CommonTree DAYS486_tree=null;
		CommonTree WhiteChar487_tree=null;
		CommonTree string_literal488_tree=null;
		CommonTree WhiteChar489_tree=null;
		CommonTree WhiteChar490_tree=null;
		CommonTree DAYS491_tree=null;
		CommonTree WhiteChar492_tree=null;
		CommonTree string_literal493_tree=null;
		CommonTree WhiteChar494_tree=null;
		CommonTree WhiteChar495_tree=null;
		CommonTree DAYS496_tree=null;
		CommonTree WhiteChar497_tree=null;
		CommonTree string_literal498_tree=null;
		CommonTree WhiteChar499_tree=null;
		CommonTree WhiteChar500_tree=null;
		CommonTree DAYS501_tree=null;
		CommonTree WhiteChar502_tree=null;
		CommonTree string_literal503_tree=null;
		CommonTree WhiteChar504_tree=null;
		CommonTree WhiteChar505_tree=null;
		CommonTree string_literal506_tree=null;
		CommonTree WhiteChar507_tree=null;
		CommonTree WhiteChar508_tree=null;
		CommonTree string_literal509_tree=null;
		CommonTree WhiteChar510_tree=null;
		CommonTree char_literal511_tree=null;
		CommonTree char_literal512_tree=null;
		CommonTree char_literal513_tree=null;
		CommonTree WhiteChar514_tree=null;
		CommonTree string_literal515_tree=null;
		CommonTree WhiteChar516_tree=null;
		CommonTree char_literal517_tree=null;
		CommonTree char_literal518_tree=null;
		CommonTree char_literal519_tree=null;
		CommonTree WhiteChar520_tree=null;
		CommonTree string_literal521_tree=null;
		CommonTree WhiteChar522_tree=null;
		CommonTree char_literal523_tree=null;
		CommonTree char_literal524_tree=null;
		CommonTree char_literal525_tree=null;
		CommonTree string_literal526_tree=null;
		CommonTree WhiteChar527_tree=null;
		CommonTree WhiteChar528_tree=null;
		CommonTree DAYS529_tree=null;
		CommonTree WhiteChar530_tree=null;
		CommonTree string_literal531_tree=null;
		CommonTree WhiteChar532_tree=null;
		CommonTree WhiteChar533_tree=null;
		CommonTree DAYS534_tree=null;
		CommonTree WhiteChar535_tree=null;
		CommonTree string_literal536_tree=null;
		CommonTree WhiteChar537_tree=null;
		CommonTree WhiteChar538_tree=null;
		CommonTree DAYS539_tree=null;
		CommonTree WhiteChar540_tree=null;
		CommonTree string_literal541_tree=null;
		CommonTree WhiteChar542_tree=null;
		CommonTree WhiteChar543_tree=null;
		CommonTree DAYS544_tree=null;
		CommonTree WhiteChar545_tree=null;
		CommonTree string_literal546_tree=null;
		CommonTree WhiteChar547_tree=null;
		CommonTree char_literal548_tree=null;
		CommonTree char_literal549_tree=null;
		CommonTree char_literal550_tree=null;
		CommonTree WhiteChar551_tree=null;
		CommonTree string_literal552_tree=null;
		CommonTree WhiteChar553_tree=null;
		CommonTree string_literal554_tree=null;
		CommonTree WhiteChar555_tree=null;
		CommonTree WhiteChar556_tree=null;
		CommonTree DAYS557_tree=null;
		CommonTree WhiteChar558_tree=null;
		CommonTree string_literal559_tree=null;
		CommonTree WhiteChar560_tree=null;
		CommonTree WhiteChar561_tree=null;
		CommonTree DAYS562_tree=null;
		CommonTree WhiteChar563_tree=null;
		CommonTree string_literal564_tree=null;
		CommonTree WhiteChar565_tree=null;
		CommonTree WhiteChar566_tree=null;
		CommonTree DAYS567_tree=null;
		CommonTree WhiteChar568_tree=null;
		CommonTree string_literal569_tree=null;
		CommonTree WhiteChar570_tree=null;
		CommonTree WhiteChar571_tree=null;
		CommonTree DAYS572_tree=null;
		CommonTree WhiteChar573_tree=null;
		CommonTree string_literal574_tree=null;
		CommonTree WhiteChar575_tree=null;
		CommonTree char_literal576_tree=null;
		CommonTree char_literal577_tree=null;
		CommonTree char_literal578_tree=null;
		CommonTree WhiteChar579_tree=null;
		CommonTree string_literal580_tree=null;
		CommonTree WhiteChar581_tree=null;
		CommonTree string_literal582_tree=null;
		CommonTree WhiteChar583_tree=null;
		CommonTree string_literal584_tree=null;
		CommonTree WhiteChar585_tree=null;
		CommonTree WhiteChar586_tree=null;
		CommonTree DAYS587_tree=null;
		CommonTree WhiteChar588_tree=null;
		CommonTree string_literal589_tree=null;
		CommonTree WhiteChar590_tree=null;
		CommonTree WhiteChar591_tree=null;
		CommonTree DAYS592_tree=null;
		CommonTree WhiteChar593_tree=null;
		CommonTree string_literal594_tree=null;
		CommonTree WhiteChar595_tree=null;
		CommonTree string_literal596_tree=null;
		CommonTree WhiteChar597_tree=null;
		CommonTree string_literal598_tree=null;
		CommonTree WhiteChar599_tree=null;
		CommonTree WhiteChar600_tree=null;
		CommonTree DAYS601_tree=null;
		CommonTree WhiteChar602_tree=null;
		CommonTree string_literal603_tree=null;
		CommonTree WhiteChar604_tree=null;
		CommonTree WhiteChar605_tree=null;
		CommonTree DAYS606_tree=null;
		CommonTree WhiteChar607_tree=null;
		CommonTree string_literal608_tree=null;
		CommonTree WhiteChar609_tree=null;
		CommonTree string_literal610_tree=null;
		CommonTree WhiteChar611_tree=null;
		CommonTree string_literal612_tree=null;
		CommonTree WhiteChar613_tree=null;
		CommonTree WhiteChar614_tree=null;
		CommonTree DAYS615_tree=null;
		CommonTree WhiteChar616_tree=null;
		CommonTree string_literal617_tree=null;
		CommonTree WhiteChar618_tree=null;
		CommonTree WhiteChar619_tree=null;
		CommonTree DAYS620_tree=null;
		CommonTree WhiteChar621_tree=null;
		CommonTree string_literal622_tree=null;
		CommonTree WhiteChar623_tree=null;
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_112=new RewriteRuleTokenStream(adaptor,"token 112");
		RewriteRuleTokenStream stream_113=new RewriteRuleTokenStream(adaptor,"token 113");
		RewriteRuleTokenStream stream_91=new RewriteRuleTokenStream(adaptor,"token 91");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
		RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
		RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
		RewriteRuleTokenStream stream_94=new RewriteRuleTokenStream(adaptor,"token 94");
		RewriteRuleTokenStream stream_CLOSESQRT=new RewriteRuleTokenStream(adaptor,"token CLOSESQRT");
		RewriteRuleTokenStream stream_95=new RewriteRuleTokenStream(adaptor,"token 95");
		RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
		RewriteRuleTokenStream stream_96=new RewriteRuleTokenStream(adaptor,"token 96");
		RewriteRuleTokenStream stream_97=new RewriteRuleTokenStream(adaptor,"token 97");
		RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
		RewriteRuleTokenStream stream_98=new RewriteRuleTokenStream(adaptor,"token 98");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
		RewriteRuleTokenStream stream_101=new RewriteRuleTokenStream(adaptor,"token 101");
		RewriteRuleTokenStream stream_102=new RewriteRuleTokenStream(adaptor,"token 102");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_103=new RewriteRuleTokenStream(adaptor,"token 103");
		RewriteRuleTokenStream stream_104=new RewriteRuleTokenStream(adaptor,"token 104");
		RewriteRuleTokenStream stream_OPENSQRT=new RewriteRuleTokenStream(adaptor,"token OPENSQRT");
		RewriteRuleTokenStream stream_105=new RewriteRuleTokenStream(adaptor,"token 105");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_106=new RewriteRuleTokenStream(adaptor,"token 106");
		RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
		RewriteRuleTokenStream stream_107=new RewriteRuleTokenStream(adaptor,"token 107");
		RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
		RewriteRuleTokenStream stream_108=new RewriteRuleTokenStream(adaptor,"token 108");
		RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
		RewriteRuleTokenStream stream_109=new RewriteRuleTokenStream(adaptor,"token 109");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_stringconstant=new RewriteRuleSubtreeStream(adaptor,"rule stringconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:38: ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) ) | ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) ) | ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) ) )
			int alt49=15;
			switch ( input.LA(1) ) {
			case 101:
				{
				alt49=1;
				}
				break;
			case 102:
				{
				alt49=2;
				}
				break;
			case 80:
				{
				alt49=3;
				}
				break;
			case 81:
				{
				alt49=4;
				}
				break;
			case 72:
				{
				alt49=5;
				}
				break;
			case 70:
				{
				alt49=6;
				}
				break;
			case 91:
				{
				alt49=7;
				}
				break;
			case 92:
				{
				alt49=8;
				}
				break;
			case 93:
				{
				alt49=9;
				}
				break;
			case 94:
				{
				alt49=10;
				}
				break;
			case 95:
				{
				alt49=11;
				}
				break;
			case 96:
				{
				alt49=12;
				}
				break;
			case 109:
				{
				alt49=13;
				}
				break;
			case 112:
				{
				alt49=14;
				}
				break;
			case 108:
				{
				alt49=15;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 49, 0, input);
				throw nvae;
			}
			switch (alt49) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:311:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:311:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:311:4: 'reverses down'
					{
					string_literal276=(Token)match(input,101,FOLLOW_101_in_presetcondition2674);  
					stream_101.add(string_literal276);

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
					// 311:20: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:311:23: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:311:42: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:311:70: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:311:99: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:312:7: ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					int alt43=2;
					int LA43_0 = input.LA(1);
					if ( (LA43_0==WhiteChar) ) {
						int LA43_1 = input.LA(2);
						if ( (LA43_1==97) ) {
							alt43=1;
						}
					}
					switch (alt43) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:312:9: WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar277=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2714);  
							stream_WhiteChar.add(WhiteChar277);

							string_literal278=(Token)match(input,97,FOLLOW_97_in_presetcondition2716);  
							stream_97.add(string_literal278);

							WhiteChar279=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2718);  
							stream_WhiteChar.add(WhiteChar279);

							pushFollow(FOLLOW_constant_in_presetcondition2722);
							percentdown=constant();
							state._fsp--;

							stream_constant.add(percentdown.getTree());
							PERCENT280=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2724);  
							stream_PERCENT.add(PERCENT280);

							WhiteChar281=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2726);  
							stream_WhiteChar.add(WhiteChar281);

							string_literal282=(Token)match(input,105,FOLLOW_105_in_presetcondition2728);  
							stream_105.add(string_literal282);

							WhiteChar283=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2730);  
							stream_WhiteChar.add(WhiteChar283);

							pushFollow(FOLLOW_constant_in_presetcondition2734);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar284=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2736);  
							stream_WhiteChar.add(WhiteChar284);

							DAYS285=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2738);  
							stream_DAYS.add(DAYS285);

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
							// 313:7: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:10: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:29: ^( Number NumberToken[\"-1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:4: 'reverses up'
					{
					string_literal286=(Token)match(input,102,FOLLOW_102_in_presetcondition2774);  
					stream_102.add(string_literal286);

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
					// 314:18: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:21: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:40: ^( Number NumberToken[\"1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:67: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:96: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:315:7: ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					int alt44=2;
					int LA44_0 = input.LA(1);
					if ( (LA44_0==WhiteChar) ) {
						int LA44_1 = input.LA(2);
						if ( (LA44_1==97) ) {
							alt44=1;
						}
					}
					switch (alt44) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:315:9: WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar287=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2814);  
							stream_WhiteChar.add(WhiteChar287);

							string_literal288=(Token)match(input,97,FOLLOW_97_in_presetcondition2816);  
							stream_97.add(string_literal288);

							WhiteChar289=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2818);  
							stream_WhiteChar.add(WhiteChar289);

							pushFollow(FOLLOW_constant_in_presetcondition2822);
							percentup=constant();
							state._fsp--;

							stream_constant.add(percentup.getTree());
							PERCENT290=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2824);  
							stream_PERCENT.add(PERCENT290);

							WhiteChar291=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2826);  
							stream_WhiteChar.add(WhiteChar291);

							string_literal292=(Token)match(input,105,FOLLOW_105_in_presetcondition2828);  
							stream_105.add(string_literal292);

							WhiteChar293=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2830);  
							stream_WhiteChar.add(WhiteChar293);

							pushFollow(FOLLOW_constant_in_presetcondition2834);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar294=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2836);  
							stream_WhiteChar.add(WhiteChar294);

							DAYS295=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2838);  
							stream_DAYS.add(DAYS295);

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
							// 316:7: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:316:10: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:316:29: ^( Number NumberToken[\"1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:4: 'goes down more than' WhiteChar percentdown= constant PERCENT
					{
					string_literal296=(Token)match(input,80,FOLLOW_80_in_presetcondition2875);  
					stream_80.add(string_literal296);

					WhiteChar297=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2877);  
					stream_WhiteChar.add(WhiteChar297);

					pushFollow(FOLLOW_constant_in_presetcondition2881);
					percentdown=constant();
					state._fsp--;

					stream_constant.add(percentdown.getTree());
					PERCENT298=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2883);  
					stream_PERCENT.add(PERCENT298);

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
					// 318:65: -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:68: ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:98: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:127: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:156: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:185: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:319:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt45=2;
					int LA45_0 = input.LA(1);
					if ( (LA45_0==WhiteChar) ) {
						int LA45_1 = input.LA(2);
						if ( (LA45_1==105) ) {
							alt45=1;
						}
					}
					switch (alt45) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:319:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar299=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2932);  
							stream_WhiteChar.add(WhiteChar299);

							string_literal300=(Token)match(input,105,FOLLOW_105_in_presetcondition2934);  
							stream_105.add(string_literal300);

							WhiteChar301=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2936);  
							stream_WhiteChar.add(WhiteChar301);

							pushFollow(FOLLOW_constant_in_presetcondition2940);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar302=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2942);  
							stream_WhiteChar.add(WhiteChar302);

							DAYS303=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2944);  
							stream_DAYS.add(DAYS303);

							WhiteChar304=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2955);  
							stream_WhiteChar.add(WhiteChar304);

							string_literal305=(Token)match(input,79,FOLLOW_79_in_presetcondition2957);  
							stream_79.add(string_literal305);

							WhiteChar306=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2959);  
							stream_WhiteChar.add(WhiteChar306);

							pushFollow(FOLLOW_constant_in_presetcondition2963);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar307=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2965);  
							stream_WhiteChar.add(WhiteChar307);

							DAYS308=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2967);  
							stream_DAYS.add(DAYS308);

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
							// 321:7: -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:321:10: ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentdown!=null?((CommonTree)percentdown.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:321:74: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:321:121: ^( Number NumberToken[\"0.0\"] )
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
				case 4 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:4: 'goes up more than' WhiteChar percentup= constant PERCENT
					{
					string_literal309=(Token)match(input,81,FOLLOW_81_in_presetcondition3011);  
					stream_81.add(string_literal309);

					WhiteChar310=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3013);  
					stream_WhiteChar.add(WhiteChar310);

					pushFollow(FOLLOW_constant_in_presetcondition3017);
					percentup=constant();
					state._fsp--;

					stream_constant.add(percentup.getTree());
					PERCENT311=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition3019);  
					stream_PERCENT.add(PERCENT311);

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
					// 322:61: -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:64: ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:92: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:121: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:150: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:179: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:323:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt46=2;
					int LA46_0 = input.LA(1);
					if ( (LA46_0==WhiteChar) ) {
						int LA46_1 = input.LA(2);
						if ( (LA46_1==105) ) {
							alt46=1;
						}
					}
					switch (alt46) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:323:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar312=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3068);  
							stream_WhiteChar.add(WhiteChar312);

							string_literal313=(Token)match(input,105,FOLLOW_105_in_presetcondition3070);  
							stream_105.add(string_literal313);

							WhiteChar314=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3072);  
							stream_WhiteChar.add(WhiteChar314);

							pushFollow(FOLLOW_constant_in_presetcondition3076);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar315=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3078);  
							stream_WhiteChar.add(WhiteChar315);

							DAYS316=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3080);  
							stream_DAYS.add(DAYS316);

							WhiteChar317=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3091);  
							stream_WhiteChar.add(WhiteChar317);

							string_literal318=(Token)match(input,79,FOLLOW_79_in_presetcondition3093);  
							stream_79.add(string_literal318);

							WhiteChar319=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3095);  
							stream_WhiteChar.add(WhiteChar319);

							pushFollow(FOLLOW_constant_in_presetcondition3099);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar320=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3101);  
							stream_WhiteChar.add(WhiteChar320);

							DAYS321=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3103);  
							stream_DAYS.add(DAYS321);

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
							// 325:7: -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:325:10: ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentup!=null?((CommonTree)percentup.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:325:70: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:325:117: ^( Number NumberToken[\"0.0\"] )
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
				case 5 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:4: 'crosses up threshold' WhiteChar threshold= constant
					{
					string_literal322=(Token)match(input,72,FOLLOW_72_in_presetcondition3148);  
					stream_72.add(string_literal322);

					WhiteChar323=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3150);  
					stream_WhiteChar.add(WhiteChar323);

					pushFollow(FOLLOW_constant_in_presetcondition3154);
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
					// 327:56: -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:59: ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:95: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:124: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:153: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:182: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:328:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt47=2;
					int LA47_0 = input.LA(1);
					if ( (LA47_0==WhiteChar) ) {
						int LA47_1 = input.LA(2);
						if ( (LA47_1==105) ) {
							alt47=1;
						}
					}
					switch (alt47) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:328:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar324=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3203);  
							stream_WhiteChar.add(WhiteChar324);

							string_literal325=(Token)match(input,105,FOLLOW_105_in_presetcondition3205);  
							stream_105.add(string_literal325);

							WhiteChar326=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3207);  
							stream_WhiteChar.add(WhiteChar326);

							pushFollow(FOLLOW_constant_in_presetcondition3211);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar327=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3213);  
							stream_WhiteChar.add(WhiteChar327);

							DAYS328=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3215);  
							stream_DAYS.add(DAYS328);

							WhiteChar329=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3226);  
							stream_WhiteChar.add(WhiteChar329);

							string_literal330=(Token)match(input,98,FOLLOW_98_in_presetcondition3228);  
							stream_98.add(string_literal330);

							WhiteChar331=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3230);  
							stream_WhiteChar.add(WhiteChar331);

							pushFollow(FOLLOW_constant_in_presetcondition3234);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar332=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3236);  
							stream_WhiteChar.add(WhiteChar332);

							DAYS333=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3238);  
							stream_DAYS.add(DAYS333);

							WhiteChar334=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3248);  
							stream_WhiteChar.add(WhiteChar334);

							string_literal335=(Token)match(input,75,FOLLOW_75_in_presetcondition3250);  
							stream_75.add(string_literal335);

							WhiteChar336=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3252);  
							stream_WhiteChar.add(WhiteChar336);

							pushFollow(FOLLOW_constant_in_presetcondition3256);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT337=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition3258);  
							stream_PERCENT.add(PERCENT337);

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
							// 331:7: -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:331:10: ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:331:97: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:332:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:332:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:332:4: 'crosses down threshold' WhiteChar threshold= constant
					{
					string_literal338=(Token)match(input,70,FOLLOW_70_in_presetcondition3297);  
					stream_70.add(string_literal338);

					WhiteChar339=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3299);  
					stream_WhiteChar.add(WhiteChar339);

					pushFollow(FOLLOW_constant_in_presetcondition3303);
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
					// 332:58: -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:332:61: ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:332:99: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:332:128: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:332:157: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:332:186: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:333:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt48=2;
					int LA48_0 = input.LA(1);
					if ( (LA48_0==WhiteChar) ) {
						int LA48_1 = input.LA(2);
						if ( (LA48_1==105) ) {
							alt48=1;
						}
					}
					switch (alt48) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:333:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar340=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3352);  
							stream_WhiteChar.add(WhiteChar340);

							string_literal341=(Token)match(input,105,FOLLOW_105_in_presetcondition3354);  
							stream_105.add(string_literal341);

							WhiteChar342=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3356);  
							stream_WhiteChar.add(WhiteChar342);

							pushFollow(FOLLOW_constant_in_presetcondition3360);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar343=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3362);  
							stream_WhiteChar.add(WhiteChar343);

							DAYS344=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3364);  
							stream_DAYS.add(DAYS344);

							WhiteChar345=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3375);  
							stream_WhiteChar.add(WhiteChar345);

							string_literal346=(Token)match(input,98,FOLLOW_98_in_presetcondition3377);  
							stream_98.add(string_literal346);

							WhiteChar347=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3379);  
							stream_WhiteChar.add(WhiteChar347);

							pushFollow(FOLLOW_constant_in_presetcondition3383);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar348=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3385);  
							stream_WhiteChar.add(WhiteChar348);

							DAYS349=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3387);  
							stream_DAYS.add(DAYS349);

							WhiteChar350=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3397);  
							stream_WhiteChar.add(WhiteChar350);

							string_literal351=(Token)match(input,75,FOLLOW_75_in_presetcondition3399);  
							stream_75.add(string_literal351);

							WhiteChar352=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3401);  
							stream_WhiteChar.add(WhiteChar352);

							pushFollow(FOLLOW_constant_in_presetcondition3405);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT353=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition3407);  
							stream_PERCENT.add(PERCENT353);

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
							// 336:7: -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:336:10: ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:336:99: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:338:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:338:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:338:4: 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal354=(Token)match(input,91,FOLLOW_91_in_presetcondition3447);  
					stream_91.add(string_literal354);

					WhiteChar355=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3449);  
					stream_WhiteChar.add(WhiteChar355);

					pushFollow(FOLLOW_constant_in_presetcondition3453);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar356=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3455);  
					stream_WhiteChar.add(WhiteChar356);

					DAYS357=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3457);  
					stream_DAYS.add(DAYS357);

					WhiteChar358=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3464);  
					stream_WhiteChar.add(WhiteChar358);

					string_literal359=(Token)match(input,98,FOLLOW_98_in_presetcondition3466);  
					stream_98.add(string_literal359);

					WhiteChar360=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3468);  
					stream_WhiteChar.add(WhiteChar360);

					pushFollow(FOLLOW_constant_in_presetcondition3472);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar361=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3474);  
					stream_WhiteChar.add(WhiteChar361);

					DAYS362=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3476);  
					stream_DAYS.add(DAYS362);

					WhiteChar363=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3483);  
					stream_WhiteChar.add(WhiteChar363);

					string_literal364=(Token)match(input,79,FOLLOW_79_in_presetcondition3485);  
					stream_79.add(string_literal364);

					WhiteChar365=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3487);  
					stream_WhiteChar.add(WhiteChar365);

					pushFollow(FOLLOW_constant_in_presetcondition3491);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar366=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3493);  
					stream_WhiteChar.add(WhiteChar366);

					DAYS367=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3495);  
					stream_DAYS.add(DAYS367);

					WhiteChar368=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3502);  
					stream_WhiteChar.add(WhiteChar368);

					string_literal369=(Token)match(input,104,FOLLOW_104_in_presetcondition3504);  
					stream_104.add(string_literal369);

					WhiteChar370=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3506);  
					stream_WhiteChar.add(WhiteChar370);

					pushFollow(FOLLOW_constant_in_presetcondition3510);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar371=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3512);  
					stream_WhiteChar.add(WhiteChar371);

					DAYS372=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3514);  
					stream_DAYS.add(DAYS372);

					WhiteChar373=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3521);  
					stream_WhiteChar.add(WhiteChar373);

					string_literal374=(Token)match(input,82,FOLLOW_82_in_presetcondition3523);  
					stream_82.add(string_literal374);

					WhiteChar375=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3525);  
					stream_WhiteChar.add(WhiteChar375);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3529);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar376=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3535);  
					stream_WhiteChar.add(WhiteChar376);

					string_literal377=(Token)match(input,113,FOLLOW_113_in_presetcondition3537);  
					stream_113.add(string_literal377);

					WhiteChar378=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3539);  
					stream_WhiteChar.add(WhiteChar378);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3543);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar379=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3549);  
					stream_WhiteChar.add(WhiteChar379);

					string_literal380=(Token)match(input,106,FOLLOW_106_in_presetcondition3551);  
					stream_106.add(string_literal380);

					WhiteChar381=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3553);  
					stream_WhiteChar.add(WhiteChar381);

					char_literal382=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3555);  
					stream_OPENSQRT.add(char_literal382);

					pushFollow(FOLLOW_constant_in_presetcondition3559);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal383=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3561);  
					stream_COMMA.add(char_literal383);

					pushFollow(FOLLOW_constant_in_presetcondition3565);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal384=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3567);  
					stream_CLOSESQRT.add(char_literal384);

					WhiteChar385=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3569);  
					stream_WhiteChar.add(WhiteChar385);

					string_literal386=(Token)match(input,74,FOLLOW_74_in_presetcondition3571);  
					stream_74.add(string_literal386);

					WhiteChar387=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3573);  
					stream_WhiteChar.add(WhiteChar387);

					char_literal388=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3575);  
					stream_OPENSQRT.add(char_literal388);

					pushFollow(FOLLOW_constant_in_presetcondition3579);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal389=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3581);  
					stream_COMMA.add(char_literal389);

					pushFollow(FOLLOW_constant_in_presetcondition3585);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal390=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3587);  
					stream_CLOSESQRT.add(char_literal390);

					WhiteChar391=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3593);  
					stream_WhiteChar.add(WhiteChar391);

					string_literal392=(Token)match(input,103,FOLLOW_103_in_presetcondition3595);  
					stream_103.add(string_literal392);

					WhiteChar393=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3597);  
					stream_WhiteChar.add(WhiteChar393);

					char_literal394=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3599);  
					stream_OPENSQRT.add(char_literal394);

					pushFollow(FOLLOW_constant_in_presetcondition3603);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal395=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3605);  
					stream_COMMA.add(char_literal395);

					pushFollow(FOLLOW_constant_in_presetcondition3609);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal396=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3611);  
					stream_CLOSESQRT.add(char_literal396);

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
					// 346:4: -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:346:7: ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(HigherHighCondition, "HigherHighCondition"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						adaptor.addChild(root_1, (greed!=null?((CommonTree)greed.getTree()):null));
						adaptor.addChild(root_1, (type!=null?((CommonTree)type.getTree()):null));
						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						adaptor.addChild(root_1, (lowestEnd!=null?((CommonTree)lowestEnd.getTree()):null));
						adaptor.addChild(root_1, (highestEnd!=null?((CommonTree)highestEnd.getTree()):null));
						adaptor.addChild(root_1, (minSlope!=null?((CommonTree)minSlope.getTree()):null));
						adaptor.addChild(root_1, (maxSlope!=null?((CommonTree)maxSlope.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:346:246: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:347:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:347:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:347:4: 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal397=(Token)match(input,92,FOLLOW_92_in_presetcondition3662);  
					stream_92.add(string_literal397);

					WhiteChar398=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3664);  
					stream_WhiteChar.add(WhiteChar398);

					pushFollow(FOLLOW_constant_in_presetcondition3668);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar399=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3670);  
					stream_WhiteChar.add(WhiteChar399);

					DAYS400=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3672);  
					stream_DAYS.add(DAYS400);

					WhiteChar401=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3679);  
					stream_WhiteChar.add(WhiteChar401);

					string_literal402=(Token)match(input,98,FOLLOW_98_in_presetcondition3681);  
					stream_98.add(string_literal402);

					WhiteChar403=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3683);  
					stream_WhiteChar.add(WhiteChar403);

					pushFollow(FOLLOW_constant_in_presetcondition3687);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar404=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3689);  
					stream_WhiteChar.add(WhiteChar404);

					DAYS405=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3691);  
					stream_DAYS.add(DAYS405);

					WhiteChar406=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3698);  
					stream_WhiteChar.add(WhiteChar406);

					string_literal407=(Token)match(input,79,FOLLOW_79_in_presetcondition3700);  
					stream_79.add(string_literal407);

					WhiteChar408=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3702);  
					stream_WhiteChar.add(WhiteChar408);

					pushFollow(FOLLOW_constant_in_presetcondition3706);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar409=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3708);  
					stream_WhiteChar.add(WhiteChar409);

					DAYS410=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3710);  
					stream_DAYS.add(DAYS410);

					WhiteChar411=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3717);  
					stream_WhiteChar.add(WhiteChar411);

					string_literal412=(Token)match(input,104,FOLLOW_104_in_presetcondition3719);  
					stream_104.add(string_literal412);

					WhiteChar413=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3721);  
					stream_WhiteChar.add(WhiteChar413);

					pushFollow(FOLLOW_constant_in_presetcondition3725);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar414=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3727);  
					stream_WhiteChar.add(WhiteChar414);

					DAYS415=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3729);  
					stream_DAYS.add(DAYS415);

					WhiteChar416=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3736);  
					stream_WhiteChar.add(WhiteChar416);

					string_literal417=(Token)match(input,82,FOLLOW_82_in_presetcondition3738);  
					stream_82.add(string_literal417);

					WhiteChar418=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3740);  
					stream_WhiteChar.add(WhiteChar418);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3744);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar419=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3750);  
					stream_WhiteChar.add(WhiteChar419);

					string_literal420=(Token)match(input,113,FOLLOW_113_in_presetcondition3752);  
					stream_113.add(string_literal420);

					WhiteChar421=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3754);  
					stream_WhiteChar.add(WhiteChar421);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3758);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar422=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3764);  
					stream_WhiteChar.add(WhiteChar422);

					string_literal423=(Token)match(input,106,FOLLOW_106_in_presetcondition3766);  
					stream_106.add(string_literal423);

					WhiteChar424=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3768);  
					stream_WhiteChar.add(WhiteChar424);

					char_literal425=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3770);  
					stream_OPENSQRT.add(char_literal425);

					pushFollow(FOLLOW_constant_in_presetcondition3774);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal426=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3776);  
					stream_COMMA.add(char_literal426);

					pushFollow(FOLLOW_constant_in_presetcondition3780);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal427=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3782);  
					stream_CLOSESQRT.add(char_literal427);

					WhiteChar428=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3784);  
					stream_WhiteChar.add(WhiteChar428);

					string_literal429=(Token)match(input,74,FOLLOW_74_in_presetcondition3786);  
					stream_74.add(string_literal429);

					WhiteChar430=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3788);  
					stream_WhiteChar.add(WhiteChar430);

					char_literal431=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3790);  
					stream_OPENSQRT.add(char_literal431);

					pushFollow(FOLLOW_constant_in_presetcondition3794);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal432=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3796);  
					stream_COMMA.add(char_literal432);

					pushFollow(FOLLOW_constant_in_presetcondition3800);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal433=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3802);  
					stream_CLOSESQRT.add(char_literal433);

					WhiteChar434=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3808);  
					stream_WhiteChar.add(WhiteChar434);

					string_literal435=(Token)match(input,103,FOLLOW_103_in_presetcondition3810);  
					stream_103.add(string_literal435);

					WhiteChar436=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3812);  
					stream_WhiteChar.add(WhiteChar436);

					char_literal437=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3814);  
					stream_OPENSQRT.add(char_literal437);

					pushFollow(FOLLOW_constant_in_presetcondition3818);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal438=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3820);  
					stream_COMMA.add(char_literal438);

					pushFollow(FOLLOW_constant_in_presetcondition3824);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal439=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3826);  
					stream_CLOSESQRT.add(char_literal439);

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
					// 355:4: -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:355:7: ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(HigherLowCondition, "HigherLowCondition"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						adaptor.addChild(root_1, (greed!=null?((CommonTree)greed.getTree()):null));
						adaptor.addChild(root_1, (type!=null?((CommonTree)type.getTree()):null));
						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						adaptor.addChild(root_1, (lowestEnd!=null?((CommonTree)lowestEnd.getTree()):null));
						adaptor.addChild(root_1, (highestEnd!=null?((CommonTree)highestEnd.getTree()):null));
						adaptor.addChild(root_1, (minSlope!=null?((CommonTree)minSlope.getTree()):null));
						adaptor.addChild(root_1, (maxSlope!=null?((CommonTree)maxSlope.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:355:245: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:356:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:356:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:356:4: 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal440=(Token)match(input,93,FOLLOW_93_in_presetcondition3877);  
					stream_93.add(string_literal440);

					WhiteChar441=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3879);  
					stream_WhiteChar.add(WhiteChar441);

					pushFollow(FOLLOW_constant_in_presetcondition3883);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar442=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3885);  
					stream_WhiteChar.add(WhiteChar442);

					DAYS443=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3887);  
					stream_DAYS.add(DAYS443);

					WhiteChar444=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3894);  
					stream_WhiteChar.add(WhiteChar444);

					string_literal445=(Token)match(input,98,FOLLOW_98_in_presetcondition3896);  
					stream_98.add(string_literal445);

					WhiteChar446=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3898);  
					stream_WhiteChar.add(WhiteChar446);

					pushFollow(FOLLOW_constant_in_presetcondition3902);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar447=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3904);  
					stream_WhiteChar.add(WhiteChar447);

					DAYS448=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3906);  
					stream_DAYS.add(DAYS448);

					WhiteChar449=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3913);  
					stream_WhiteChar.add(WhiteChar449);

					string_literal450=(Token)match(input,79,FOLLOW_79_in_presetcondition3915);  
					stream_79.add(string_literal450);

					WhiteChar451=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3917);  
					stream_WhiteChar.add(WhiteChar451);

					pushFollow(FOLLOW_constant_in_presetcondition3921);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar452=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3923);  
					stream_WhiteChar.add(WhiteChar452);

					DAYS453=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3925);  
					stream_DAYS.add(DAYS453);

					WhiteChar454=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3932);  
					stream_WhiteChar.add(WhiteChar454);

					string_literal455=(Token)match(input,104,FOLLOW_104_in_presetcondition3934);  
					stream_104.add(string_literal455);

					WhiteChar456=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3936);  
					stream_WhiteChar.add(WhiteChar456);

					pushFollow(FOLLOW_constant_in_presetcondition3940);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar457=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3942);  
					stream_WhiteChar.add(WhiteChar457);

					DAYS458=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3944);  
					stream_DAYS.add(DAYS458);

					WhiteChar459=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3951);  
					stream_WhiteChar.add(WhiteChar459);

					string_literal460=(Token)match(input,82,FOLLOW_82_in_presetcondition3953);  
					stream_82.add(string_literal460);

					WhiteChar461=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3955);  
					stream_WhiteChar.add(WhiteChar461);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3959);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar462=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3965);  
					stream_WhiteChar.add(WhiteChar462);

					string_literal463=(Token)match(input,113,FOLLOW_113_in_presetcondition3967);  
					stream_113.add(string_literal463);

					WhiteChar464=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3969);  
					stream_WhiteChar.add(WhiteChar464);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3973);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar465=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3979);  
					stream_WhiteChar.add(WhiteChar465);

					string_literal466=(Token)match(input,106,FOLLOW_106_in_presetcondition3981);  
					stream_106.add(string_literal466);

					WhiteChar467=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3983);  
					stream_WhiteChar.add(WhiteChar467);

					char_literal468=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3985);  
					stream_OPENSQRT.add(char_literal468);

					pushFollow(FOLLOW_constant_in_presetcondition3989);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal469=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3991);  
					stream_COMMA.add(char_literal469);

					pushFollow(FOLLOW_constant_in_presetcondition3995);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal470=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3997);  
					stream_CLOSESQRT.add(char_literal470);

					WhiteChar471=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3999);  
					stream_WhiteChar.add(WhiteChar471);

					string_literal472=(Token)match(input,74,FOLLOW_74_in_presetcondition4001);  
					stream_74.add(string_literal472);

					WhiteChar473=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4003);  
					stream_WhiteChar.add(WhiteChar473);

					char_literal474=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4005);  
					stream_OPENSQRT.add(char_literal474);

					pushFollow(FOLLOW_constant_in_presetcondition4009);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal475=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4011);  
					stream_COMMA.add(char_literal475);

					pushFollow(FOLLOW_constant_in_presetcondition4015);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal476=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4017);  
					stream_CLOSESQRT.add(char_literal476);

					WhiteChar477=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4023);  
					stream_WhiteChar.add(WhiteChar477);

					string_literal478=(Token)match(input,103,FOLLOW_103_in_presetcondition4025);  
					stream_103.add(string_literal478);

					WhiteChar479=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4027);  
					stream_WhiteChar.add(WhiteChar479);

					char_literal480=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4029);  
					stream_OPENSQRT.add(char_literal480);

					pushFollow(FOLLOW_constant_in_presetcondition4033);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal481=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4035);  
					stream_COMMA.add(char_literal481);

					pushFollow(FOLLOW_constant_in_presetcondition4039);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal482=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4041);  
					stream_CLOSESQRT.add(char_literal482);

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
					// 364:4: -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:364:7: ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LowerHighCondition, "LowerHighCondition"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						adaptor.addChild(root_1, (greed!=null?((CommonTree)greed.getTree()):null));
						adaptor.addChild(root_1, (type!=null?((CommonTree)type.getTree()):null));
						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						adaptor.addChild(root_1, (lowestEnd!=null?((CommonTree)lowestEnd.getTree()):null));
						adaptor.addChild(root_1, (highestEnd!=null?((CommonTree)highestEnd.getTree()):null));
						adaptor.addChild(root_1, (minSlope!=null?((CommonTree)minSlope.getTree()):null));
						adaptor.addChild(root_1, (maxSlope!=null?((CommonTree)maxSlope.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:364:245: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:365:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:365:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:365:4: 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal483=(Token)match(input,94,FOLLOW_94_in_presetcondition4092);  
					stream_94.add(string_literal483);

					WhiteChar484=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4094);  
					stream_WhiteChar.add(WhiteChar484);

					pushFollow(FOLLOW_constant_in_presetcondition4098);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar485=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4100);  
					stream_WhiteChar.add(WhiteChar485);

					DAYS486=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4102);  
					stream_DAYS.add(DAYS486);

					WhiteChar487=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4109);  
					stream_WhiteChar.add(WhiteChar487);

					string_literal488=(Token)match(input,98,FOLLOW_98_in_presetcondition4111);  
					stream_98.add(string_literal488);

					WhiteChar489=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4113);  
					stream_WhiteChar.add(WhiteChar489);

					pushFollow(FOLLOW_constant_in_presetcondition4117);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar490=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4119);  
					stream_WhiteChar.add(WhiteChar490);

					DAYS491=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4121);  
					stream_DAYS.add(DAYS491);

					WhiteChar492=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4128);  
					stream_WhiteChar.add(WhiteChar492);

					string_literal493=(Token)match(input,79,FOLLOW_79_in_presetcondition4130);  
					stream_79.add(string_literal493);

					WhiteChar494=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4132);  
					stream_WhiteChar.add(WhiteChar494);

					pushFollow(FOLLOW_constant_in_presetcondition4136);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar495=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4138);  
					stream_WhiteChar.add(WhiteChar495);

					DAYS496=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4140);  
					stream_DAYS.add(DAYS496);

					WhiteChar497=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4147);  
					stream_WhiteChar.add(WhiteChar497);

					string_literal498=(Token)match(input,104,FOLLOW_104_in_presetcondition4149);  
					stream_104.add(string_literal498);

					WhiteChar499=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4151);  
					stream_WhiteChar.add(WhiteChar499);

					pushFollow(FOLLOW_constant_in_presetcondition4155);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar500=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4157);  
					stream_WhiteChar.add(WhiteChar500);

					DAYS501=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4159);  
					stream_DAYS.add(DAYS501);

					WhiteChar502=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4166);  
					stream_WhiteChar.add(WhiteChar502);

					string_literal503=(Token)match(input,82,FOLLOW_82_in_presetcondition4168);  
					stream_82.add(string_literal503);

					WhiteChar504=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4170);  
					stream_WhiteChar.add(WhiteChar504);

					pushFollow(FOLLOW_stringconstant_in_presetcondition4174);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar505=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4180);  
					stream_WhiteChar.add(WhiteChar505);

					string_literal506=(Token)match(input,113,FOLLOW_113_in_presetcondition4182);  
					stream_113.add(string_literal506);

					WhiteChar507=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4184);  
					stream_WhiteChar.add(WhiteChar507);

					pushFollow(FOLLOW_stringconstant_in_presetcondition4188);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar508=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4194);  
					stream_WhiteChar.add(WhiteChar508);

					string_literal509=(Token)match(input,106,FOLLOW_106_in_presetcondition4196);  
					stream_106.add(string_literal509);

					WhiteChar510=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4198);  
					stream_WhiteChar.add(WhiteChar510);

					char_literal511=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4200);  
					stream_OPENSQRT.add(char_literal511);

					pushFollow(FOLLOW_constant_in_presetcondition4204);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal512=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4206);  
					stream_COMMA.add(char_literal512);

					pushFollow(FOLLOW_constant_in_presetcondition4210);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal513=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4212);  
					stream_CLOSESQRT.add(char_literal513);

					WhiteChar514=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4214);  
					stream_WhiteChar.add(WhiteChar514);

					string_literal515=(Token)match(input,74,FOLLOW_74_in_presetcondition4216);  
					stream_74.add(string_literal515);

					WhiteChar516=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4218);  
					stream_WhiteChar.add(WhiteChar516);

					char_literal517=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4220);  
					stream_OPENSQRT.add(char_literal517);

					pushFollow(FOLLOW_constant_in_presetcondition4224);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal518=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4226);  
					stream_COMMA.add(char_literal518);

					pushFollow(FOLLOW_constant_in_presetcondition4230);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal519=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4232);  
					stream_CLOSESQRT.add(char_literal519);

					WhiteChar520=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4238);  
					stream_WhiteChar.add(WhiteChar520);

					string_literal521=(Token)match(input,103,FOLLOW_103_in_presetcondition4240);  
					stream_103.add(string_literal521);

					WhiteChar522=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4242);  
					stream_WhiteChar.add(WhiteChar522);

					char_literal523=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4244);  
					stream_OPENSQRT.add(char_literal523);

					pushFollow(FOLLOW_constant_in_presetcondition4248);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal524=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4250);  
					stream_COMMA.add(char_literal524);

					pushFollow(FOLLOW_constant_in_presetcondition4254);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal525=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4256);  
					stream_CLOSESQRT.add(char_literal525);

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
					// 373:4: -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:373:7: ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LowerLowCondition, "LowerLowCondition"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						adaptor.addChild(root_1, (greed!=null?((CommonTree)greed.getTree()):null));
						adaptor.addChild(root_1, (type!=null?((CommonTree)type.getTree()):null));
						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						adaptor.addChild(root_1, (lowestEnd!=null?((CommonTree)lowestEnd.getTree()):null));
						adaptor.addChild(root_1, (highestEnd!=null?((CommonTree)highestEnd.getTree()):null));
						adaptor.addChild(root_1, (minSlope!=null?((CommonTree)minSlope.getTree()):null));
						adaptor.addChild(root_1, (maxSlope!=null?((CommonTree)maxSlope.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:373:244: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:375:4: ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:375:4: ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:375:5: 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant
					{
					string_literal526=(Token)match(input,95,FOLLOW_95_in_presetcondition4309);  
					stream_95.add(string_literal526);

					WhiteChar527=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4311);  
					stream_WhiteChar.add(WhiteChar527);

					pushFollow(FOLLOW_constant_in_presetcondition4315);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar528=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4317);  
					stream_WhiteChar.add(WhiteChar528);

					DAYS529=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4319);  
					stream_DAYS.add(DAYS529);

					WhiteChar530=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4326);  
					stream_WhiteChar.add(WhiteChar530);

					string_literal531=(Token)match(input,98,FOLLOW_98_in_presetcondition4328);  
					stream_98.add(string_literal531);

					WhiteChar532=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4330);  
					stream_WhiteChar.add(WhiteChar532);

					pushFollow(FOLLOW_constant_in_presetcondition4334);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar533=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4336);  
					stream_WhiteChar.add(WhiteChar533);

					DAYS534=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4338);  
					stream_DAYS.add(DAYS534);

					WhiteChar535=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4345);  
					stream_WhiteChar.add(WhiteChar535);

					string_literal536=(Token)match(input,79,FOLLOW_79_in_presetcondition4347);  
					stream_79.add(string_literal536);

					WhiteChar537=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4349);  
					stream_WhiteChar.add(WhiteChar537);

					pushFollow(FOLLOW_constant_in_presetcondition4353);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar538=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4355);  
					stream_WhiteChar.add(WhiteChar538);

					DAYS539=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4357);  
					stream_DAYS.add(DAYS539);

					WhiteChar540=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4364);  
					stream_WhiteChar.add(WhiteChar540);

					string_literal541=(Token)match(input,104,FOLLOW_104_in_presetcondition4366);  
					stream_104.add(string_literal541);

					WhiteChar542=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4368);  
					stream_WhiteChar.add(WhiteChar542);

					pushFollow(FOLLOW_constant_in_presetcondition4372);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar543=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4374);  
					stream_WhiteChar.add(WhiteChar543);

					DAYS544=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4376);  
					stream_DAYS.add(DAYS544);

					WhiteChar545=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4383);  
					stream_WhiteChar.add(WhiteChar545);

					string_literal546=(Token)match(input,106,FOLLOW_106_in_presetcondition4385);  
					stream_106.add(string_literal546);

					WhiteChar547=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4387);  
					stream_WhiteChar.add(WhiteChar547);

					char_literal548=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4389);  
					stream_OPENSQRT.add(char_literal548);

					pushFollow(FOLLOW_constant_in_presetcondition4393);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal549=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4395);  
					stream_COMMA.add(char_literal549);

					pushFollow(FOLLOW_constant_in_presetcondition4399);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal550=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4401);  
					stream_CLOSESQRT.add(char_literal550);

					WhiteChar551=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4407);  
					stream_WhiteChar.add(WhiteChar551);

					string_literal552=(Token)match(input,107,FOLLOW_107_in_presetcondition4409);  
					stream_107.add(string_literal552);

					WhiteChar553=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4411);  
					stream_WhiteChar.add(WhiteChar553);

					pushFollow(FOLLOW_constant_in_presetcondition4415);
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
					// 381:4: -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:7: ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupportBreakDown, "SupportBreakDown"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:104: ^( String StringToken[\"\\\"greedy\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"greedy\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:140: ^( String StringToken[\"\\\"smooth\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"smooth\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:217: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:246: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:275: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:304: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:382:4: ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:382:4: ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:382:5: 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant
					{
					string_literal554=(Token)match(input,96,FOLLOW_96_in_presetcondition4492);  
					stream_96.add(string_literal554);

					WhiteChar555=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4494);  
					stream_WhiteChar.add(WhiteChar555);

					pushFollow(FOLLOW_constant_in_presetcondition4498);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar556=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4500);  
					stream_WhiteChar.add(WhiteChar556);

					DAYS557=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4502);  
					stream_DAYS.add(DAYS557);

					WhiteChar558=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4512);  
					stream_WhiteChar.add(WhiteChar558);

					string_literal559=(Token)match(input,98,FOLLOW_98_in_presetcondition4514);  
					stream_98.add(string_literal559);

					WhiteChar560=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4516);  
					stream_WhiteChar.add(WhiteChar560);

					pushFollow(FOLLOW_constant_in_presetcondition4520);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar561=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4522);  
					stream_WhiteChar.add(WhiteChar561);

					DAYS562=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4524);  
					stream_DAYS.add(DAYS562);

					WhiteChar563=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4534);  
					stream_WhiteChar.add(WhiteChar563);

					string_literal564=(Token)match(input,79,FOLLOW_79_in_presetcondition4536);  
					stream_79.add(string_literal564);

					WhiteChar565=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4538);  
					stream_WhiteChar.add(WhiteChar565);

					pushFollow(FOLLOW_constant_in_presetcondition4542);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar566=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4544);  
					stream_WhiteChar.add(WhiteChar566);

					DAYS567=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4546);  
					stream_DAYS.add(DAYS567);

					WhiteChar568=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4556);  
					stream_WhiteChar.add(WhiteChar568);

					string_literal569=(Token)match(input,104,FOLLOW_104_in_presetcondition4558);  
					stream_104.add(string_literal569);

					WhiteChar570=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4560);  
					stream_WhiteChar.add(WhiteChar570);

					pushFollow(FOLLOW_constant_in_presetcondition4564);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar571=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4566);  
					stream_WhiteChar.add(WhiteChar571);

					DAYS572=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4568);  
					stream_DAYS.add(DAYS572);

					WhiteChar573=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4578);  
					stream_WhiteChar.add(WhiteChar573);

					string_literal574=(Token)match(input,106,FOLLOW_106_in_presetcondition4580);  
					stream_106.add(string_literal574);

					WhiteChar575=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4582);  
					stream_WhiteChar.add(WhiteChar575);

					char_literal576=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4584);  
					stream_OPENSQRT.add(char_literal576);

					pushFollow(FOLLOW_constant_in_presetcondition4588);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal577=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4590);  
					stream_COMMA.add(char_literal577);

					pushFollow(FOLLOW_constant_in_presetcondition4594);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal578=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4596);  
					stream_CLOSESQRT.add(char_literal578);

					WhiteChar579=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4606);  
					stream_WhiteChar.add(WhiteChar579);

					string_literal580=(Token)match(input,107,FOLLOW_107_in_presetcondition4608);  
					stream_107.add(string_literal580);

					WhiteChar581=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4610);  
					stream_WhiteChar.add(WhiteChar581);

					pushFollow(FOLLOW_constant_in_presetcondition4614);
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
					// 388:6: -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:388:9: ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupportBreakUp, "SupportBreakUp"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:388:104: ^( String StringToken[\"\\\"greedy\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"greedy\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:388:140: ^( String StringToken[\"\\\"smooth\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"smooth\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:388:217: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:388:246: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:388:275: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:388:304: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:390:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:390:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:390:4: 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal582=(Token)match(input,109,FOLLOW_109_in_presetcondition4693);  
					stream_109.add(string_literal582);

					WhiteChar583=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4701);  
					stream_WhiteChar.add(WhiteChar583);

					string_literal584=(Token)match(input,98,FOLLOW_98_in_presetcondition4703);  
					stream_98.add(string_literal584);

					WhiteChar585=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4705);  
					stream_WhiteChar.add(WhiteChar585);

					pushFollow(FOLLOW_constant_in_presetcondition4709);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar586=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4711);  
					stream_WhiteChar.add(WhiteChar586);

					DAYS587=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4713);  
					stream_DAYS.add(DAYS587);

					WhiteChar588=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4721);  
					stream_WhiteChar.add(WhiteChar588);

					string_literal589=(Token)match(input,79,FOLLOW_79_in_presetcondition4723);  
					stream_79.add(string_literal589);

					WhiteChar590=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4725);  
					stream_WhiteChar.add(WhiteChar590);

					pushFollow(FOLLOW_constant_in_presetcondition4729);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar591=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4731);  
					stream_WhiteChar.add(WhiteChar591);

					DAYS592=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4733);  
					stream_DAYS.add(DAYS592);

					WhiteChar593=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4741);  
					stream_WhiteChar.add(WhiteChar593);

					string_literal594=(Token)match(input,75,FOLLOW_75_in_presetcondition4743);  
					stream_75.add(string_literal594);

					WhiteChar595=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4745);  
					stream_WhiteChar.add(WhiteChar595);

					pushFollow(FOLLOW_constant_in_presetcondition4749);
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
					// 394:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:394:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:394:79: ^( String StringToken[\"\\\"flat\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"flat\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					}
					break;
				case 14 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:395:3: ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:395:3: ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:395:4: 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal596=(Token)match(input,112,FOLLOW_112_in_presetcondition4784);  
					stream_112.add(string_literal596);

					WhiteChar597=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4792);  
					stream_WhiteChar.add(WhiteChar597);

					string_literal598=(Token)match(input,98,FOLLOW_98_in_presetcondition4794);  
					stream_98.add(string_literal598);

					WhiteChar599=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4796);  
					stream_WhiteChar.add(WhiteChar599);

					pushFollow(FOLLOW_constant_in_presetcondition4800);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar600=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4802);  
					stream_WhiteChar.add(WhiteChar600);

					DAYS601=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4804);  
					stream_DAYS.add(DAYS601);

					WhiteChar602=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4812);  
					stream_WhiteChar.add(WhiteChar602);

					string_literal603=(Token)match(input,79,FOLLOW_79_in_presetcondition4814);  
					stream_79.add(string_literal603);

					WhiteChar604=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4816);  
					stream_WhiteChar.add(WhiteChar604);

					pushFollow(FOLLOW_constant_in_presetcondition4820);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar605=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4822);  
					stream_WhiteChar.add(WhiteChar605);

					DAYS606=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4824);  
					stream_DAYS.add(DAYS606);

					WhiteChar607=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4832);  
					stream_WhiteChar.add(WhiteChar607);

					string_literal608=(Token)match(input,75,FOLLOW_75_in_presetcondition4834);  
					stream_75.add(string_literal608);

					WhiteChar609=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4836);  
					stream_WhiteChar.add(WhiteChar609);

					pushFollow(FOLLOW_constant_in_presetcondition4840);
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
					// 399:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:399:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:399:79: ^( String StringToken[\"\\\"up\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"up\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					}
					break;
				case 15 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:400:3: ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:400:3: ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:400:4: 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal610=(Token)match(input,108,FOLLOW_108_in_presetcondition4875);  
					stream_108.add(string_literal610);

					WhiteChar611=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4883);  
					stream_WhiteChar.add(WhiteChar611);

					string_literal612=(Token)match(input,98,FOLLOW_98_in_presetcondition4885);  
					stream_98.add(string_literal612);

					WhiteChar613=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4887);  
					stream_WhiteChar.add(WhiteChar613);

					pushFollow(FOLLOW_constant_in_presetcondition4891);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar614=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4893);  
					stream_WhiteChar.add(WhiteChar614);

					DAYS615=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4895);  
					stream_DAYS.add(DAYS615);

					WhiteChar616=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4903);  
					stream_WhiteChar.add(WhiteChar616);

					string_literal617=(Token)match(input,79,FOLLOW_79_in_presetcondition4905);  
					stream_79.add(string_literal617);

					WhiteChar618=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4907);  
					stream_WhiteChar.add(WhiteChar618);

					pushFollow(FOLLOW_constant_in_presetcondition4911);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar619=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4913);  
					stream_WhiteChar.add(WhiteChar619);

					DAYS620=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4915);  
					stream_DAYS.add(DAYS620);

					WhiteChar621=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4923);  
					stream_WhiteChar.add(WhiteChar621);

					string_literal622=(Token)match(input,75,FOLLOW_75_in_presetcondition4925);  
					stream_75.add(string_literal622);

					WhiteChar623=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4927);  
					stream_WhiteChar.add(WhiteChar623);

					pushFollow(FOLLOW_constant_in_presetcondition4931);
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
					// 404:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:404:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:404:79: ^( String StringToken[\"\\\"down\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"down\""));
						adaptor.addChild(root_1, root_2);
						}

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



	public static final BitSet FOLLOW_bullish_condition_in_complete_expression450 = new BitSet(new long[]{0x0000000000000000L,0x0000000000600000L});
	public static final BitSet FOLLOW_bearish_condition_in_complete_expression452 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000004L});
	public static final BitSet FOLLOW_also_display_in_complete_expression455 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
	public static final BitSet FOLLOW_fixed_start_shift_in_complete_expression457 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_na_event_list_name_in_complete_expression459 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_89_in_bullish_condition488 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition490 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bullish_condition492 = new BitSet(new long[]{0x8001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition494 = new BitSet(new long[]{0x8001000000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bullish_condition497 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition499 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_86_in_bearish_condition515 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition517 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_condition519 = new BitSet(new long[]{0x8001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition521 = new BitSet(new long[]{0x8001000000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bearish_condition524 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition526 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_bearish_not_bullish_in_bearish_condition536 = new BitSet(new long[]{0x8001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition539 = new BitSet(new long[]{0x8001000000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bearish_condition542 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition544 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_66_in_also_display561 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display563 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_also_display565 = new BitSet(new long[]{0x8001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display567 = new BitSet(new long[]{0x8001000000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_also_display570 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_100_in_fixed_start_shift605 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift607 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_fixed_start_shift611 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift613 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_fixed_start_shift615 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_fixed_start_shift617 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_99_in_na_event_list_name646 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_na_event_list_name648 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_na_event_list_name652 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_na_event_list_name654 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_85_in_bearish_not_bullish683 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish691 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_bearish_not_bullish693 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish695 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish697 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish730 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OR_in_bearish_not_bullish732 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish734 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish736 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_and_expression_in_primary_expression799 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_expression_in_and_expression811 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_lenient_in_and_expression815 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression818 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_and_expression820 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression822 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_or_expression_in_and_expression824 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_precondition_expression_in_or_expression851 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression854 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OR_in_or_expression856 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression858 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_precondition_expression_in_or_expression860 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_matches_expression_in_precondition_expression892 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_precondition_expression895 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WITH_in_precondition_expression897 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_precondition_expression899 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_matches_expression_in_precondition_expression901 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_atom_in_matches_expression926 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression929 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_MATCHING_in_matches_expression931 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression933 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_matches_expression935 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_matches_expression937 = new BitSet(new long[]{0x0000000000000180L});
	public static final BitSet FOLLOW_COMMA_in_matches_expression940 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_matches_expression942 = new BitSet(new long[]{0x0000000000000180L});
	public static final BitSet FOLLOW_CLOSESQRT_in_matches_expression946 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression948 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_atom_in_matches_expression950 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_booleanhistory_in_atom978 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom984 = new BitSet(new long[]{0x8200048400400000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom986 = new BitSet(new long[]{0x8200048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_atom989 = new BitSet(new long[]{0x8000000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom991 = new BitSet(new long[]{0x8000000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom994 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_atom1004 = new BitSet(new long[]{0x8000008000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom1006 = new BitSet(new long[]{0x8000008000000000L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom1009 = new BitSet(new long[]{0x8200048400400000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom1011 = new BitSet(new long[]{0x8200048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_atom1014 = new BitSet(new long[]{0x8000000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom1016 = new BitSet(new long[]{0x8000000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom1019 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_conjunctiontruthof_in_atom1040 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUTHOF_in_conjunctiontruthof1052 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof1054 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_conjunctiontruthof1056 = new BitSet(new long[]{0x8000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_conjunctiontruthof1059 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof1061 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_conjunctiontruthof1063 = new BitSet(new long[]{0x8000000000000100L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof1067 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_90_in_conjunctiontruthof1069 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof1071 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_conjunctiontruthof1073 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_conjunctiontruthof1077 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_conjunctiontruthof1079 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_conjunctiontruthof1083 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_conjunctiontruthof1085 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_booleanhistory1124 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_booleanhistory1126 = new BitSet(new long[]{0x0000000000000000L,0x0001F061F99B71E0L});
	public static final BitSet FOLLOW_presetcondition_in_booleanhistory1132 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_opcmpcondition_in_booleanhistory1143 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constantcmp_in_booleanhistory1154 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_operand1170 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Operation_in_operand1197 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NumberToken_in_constant1212 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_64_in_constant1224 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_StringToken_in_stringconstant1240 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_68_in_trendconstant1255 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_67_in_trendconstant1268 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_lenient1285 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_LENIENT_in_lenient1287 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_83_in_opcmpcondition1324 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1326 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1330 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1361 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_opcmpcondition1363 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1365 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1369 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1371 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1373 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1375 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition1377 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1379 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1383 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1385 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_87_in_opcmpcondition1417 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1419 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1423 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1454 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_opcmpcondition1456 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1458 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1462 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1464 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1466 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1468 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition1470 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1472 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1476 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1478 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_76_in_opcmpcondition1509 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1511 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1515 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1546 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_opcmpcondition1548 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1550 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1554 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1556 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1558 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1560 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition1562 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1564 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1568 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1570 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_opcmpcondition1603 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1605 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1609 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1663 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_opcmpcondition1665 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1667 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1671 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1673 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1675 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1677 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_opcmpcondition1679 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1681 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1685 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1687 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1689 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1691 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition1693 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1695 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1699 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1701 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1741 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_opcmpcondition1743 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1745 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1749 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1751 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1753 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_atom_in_opcmpcondition1757 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_71_in_opcmpcondition1800 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1802 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1806 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1860 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_opcmpcondition1862 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1864 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1868 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1870 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1872 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1874 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_opcmpcondition1876 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1878 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1882 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1884 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1886 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1888 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition1890 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1892 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1896 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1898 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1938 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_opcmpcondition1940 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1942 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1946 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1948 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1950 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_atom_in_opcmpcondition1954 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_110_in_opcmpcondition1999 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2001 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition2005 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2013 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_opcmpcondition2015 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2017 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2021 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2023 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition2025 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2033 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_opcmpcondition2035 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2037 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2041 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2043 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition2045 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2053 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_opcmpcondition2055 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2057 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition2061 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2069 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition2071 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2073 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2077 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_111_in_opcmpcondition2109 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2111 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition2115 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2123 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_opcmpcondition2125 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2127 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2131 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2133 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition2135 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2143 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_opcmpcondition2145 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2147 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2151 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2153 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition2155 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2163 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_opcmpcondition2165 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2167 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition2171 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_78_in_constantcmp2208 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2210 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000018L});
	public static final BitSet FOLLOW_trendconstant_in_constantcmp2214 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2248 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_constantcmp2250 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2252 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2256 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2258 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2260 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2262 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_constantcmp2264 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2266 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2270 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2272 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2274 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_77_in_constantcmp2298 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2300 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2304 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2345 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_constantcmp2347 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2349 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2353 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2355 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2357 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2363 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_constantcmp2365 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2367 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2371 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2373 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2375 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2381 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_constantcmp2383 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2385 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2389 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2391 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_84_in_constantcmp2421 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2423 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2427 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2468 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_constantcmp2470 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2472 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2476 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2478 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2480 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2486 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_constantcmp2488 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2490 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2494 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2496 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2498 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2504 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_constantcmp2506 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2508 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2512 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2514 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_88_in_constantcmp2544 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2546 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2550 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2591 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_constantcmp2593 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2595 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2599 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2601 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2603 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2609 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_constantcmp2611 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2613 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2617 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2619 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2621 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2627 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_constantcmp2629 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2631 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2635 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2637 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_101_in_presetcondition2674 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2714 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_presetcondition2716 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2718 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition2722 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2724 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2726 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_presetcondition2728 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2730 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition2734 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2736 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2738 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_102_in_presetcondition2774 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2814 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_presetcondition2816 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2818 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition2822 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2824 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2826 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_presetcondition2828 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2830 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition2834 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2836 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2838 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_80_in_presetcondition2875 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2877 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition2881 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2883 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2932 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_presetcondition2934 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2936 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition2940 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2942 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2944 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2955 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition2957 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2959 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition2963 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2965 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2967 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_81_in_presetcondition3011 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3013 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3017 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition3019 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3068 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_presetcondition3070 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3072 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3076 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3078 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3080 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3091 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition3093 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3095 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3099 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3101 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3103 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_72_in_presetcondition3148 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3150 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3154 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3203 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_presetcondition3205 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3207 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3211 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3213 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3215 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3226 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition3228 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3230 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3234 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3236 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3238 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3248 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_presetcondition3250 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3252 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3256 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition3258 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_70_in_presetcondition3297 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3299 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3303 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3352 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_presetcondition3354 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3356 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3360 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3362 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3364 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3375 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition3377 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3379 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3383 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3385 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3387 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3397 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_presetcondition3399 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3401 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3405 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition3407 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_91_in_presetcondition3447 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3449 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3453 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3455 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3457 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3464 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition3466 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3468 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3472 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3474 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3476 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3483 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition3485 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3487 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3491 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3493 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3495 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3502 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition3504 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3506 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3510 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3512 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3514 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3521 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
	public static final BitSet FOLLOW_82_in_presetcondition3523 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3525 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3529 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3535 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
	public static final BitSet FOLLOW_113_in_presetcondition3537 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3539 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3543 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3549 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition3551 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3553 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3555 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3559 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3561 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3565 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3567 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3569 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition3571 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3573 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3575 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3579 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3581 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3585 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3587 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3593 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition3595 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3597 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3599 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3603 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3605 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3609 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3611 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_92_in_presetcondition3662 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3664 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3668 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3670 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3672 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3679 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition3681 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3683 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3687 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3689 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3691 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3698 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition3700 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3702 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3706 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3708 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3710 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3717 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition3719 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3721 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3725 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3727 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3729 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3736 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
	public static final BitSet FOLLOW_82_in_presetcondition3738 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3740 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3744 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3750 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
	public static final BitSet FOLLOW_113_in_presetcondition3752 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3754 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3758 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3764 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition3766 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3768 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3770 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3774 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3776 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3780 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3782 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3784 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition3786 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3788 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3790 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3794 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3796 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3800 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3802 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3808 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition3810 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3812 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3814 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3818 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3820 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3824 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3826 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_93_in_presetcondition3877 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3879 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3883 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3885 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3887 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3894 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition3896 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3898 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3902 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3904 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3906 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3913 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition3915 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3917 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3921 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3923 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3925 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3932 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition3934 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3936 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3940 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3942 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3944 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3951 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
	public static final BitSet FOLLOW_82_in_presetcondition3953 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3955 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3959 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3965 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
	public static final BitSet FOLLOW_113_in_presetcondition3967 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3969 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3973 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3979 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition3981 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3983 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3985 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3989 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3991 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3995 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3997 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3999 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition4001 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4003 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4005 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4009 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4011 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4015 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4017 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4023 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition4025 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4027 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4029 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4033 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4035 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4039 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4041 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_94_in_presetcondition4092 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4094 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4098 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4100 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4102 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4109 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition4111 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4113 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4117 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4119 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4121 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4128 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition4130 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4132 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4136 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4138 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4140 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4147 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition4149 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4151 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4155 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4157 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4159 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4166 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
	public static final BitSet FOLLOW_82_in_presetcondition4168 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4170 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition4174 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4180 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
	public static final BitSet FOLLOW_113_in_presetcondition4182 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4184 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition4188 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4194 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition4196 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4198 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4200 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4204 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4206 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4210 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4212 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4214 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition4216 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4218 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4220 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4224 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4226 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4230 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4232 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4238 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition4240 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4242 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4244 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4248 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4250 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4254 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4256 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_95_in_presetcondition4309 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4311 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4315 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4317 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4319 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4326 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition4328 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4330 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4334 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4336 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4338 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4345 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition4347 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4349 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4353 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4355 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4357 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4364 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition4366 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4368 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4372 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4374 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4376 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4383 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition4385 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4387 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4389 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4393 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4395 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4399 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4401 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4407 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_presetcondition4409 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4411 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4415 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_96_in_presetcondition4492 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4494 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4498 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4500 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4502 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4512 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition4514 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4516 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4520 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4522 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4524 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4534 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition4536 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4538 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4542 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4544 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4546 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4556 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition4558 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4560 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4564 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4566 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4568 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4578 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition4580 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4582 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4584 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4588 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4590 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4594 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4596 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4606 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_presetcondition4608 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4610 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4614 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_109_in_presetcondition4693 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4701 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition4703 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4705 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4709 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4711 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4713 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4721 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition4723 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4725 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4729 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4731 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4733 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4741 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_presetcondition4743 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4745 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4749 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_112_in_presetcondition4784 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4792 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition4794 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4796 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4800 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4802 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4804 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4812 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition4814 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4816 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4820 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4822 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4824 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4832 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_presetcondition4834 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4836 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4840 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_108_in_presetcondition4875 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4883 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition4885 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4887 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4891 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4893 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4895 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4903 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition4905 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4907 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4911 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4913 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4915 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4923 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_presetcondition4925 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4927 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4931 = new BitSet(new long[]{0x0000000000000002L});
}
