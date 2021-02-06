// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2021-02-04 11:08:56
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
		"ReverseCondition", "SEMICOLUMN", "StockOperation", "String", "StringOperation", 
		"StringToken", "SupConstantCondition", "SupDoubleMapCondition", "SupportBreakDown", 
		"SupportBreakUp", "TRUTHOF", "Tcheat", "TruthOfCondition", "UpRatioCondition", 
		"WS", "WhiteChar", "'NaN'", "'adaptiveRate'", "'also display'", "'bearish'", 
		"'bullish'", "'crosses down historical'", "'crosses down threshold'", 
		"'crosses up historical'", "'crosses up threshold'", "'direction'", "'ending within'", 
		"'epsilon'", "'equals historical'", "'equals threshold'", "'equals trend'", 
		"'for'", "'goes down more than'", "'goes up more than'", "'greed'", "'is above historical'", 
		"'is above threshold'", "'is bearish if not bullish'", "'is bearish when'", 
		"'is below historical'", "'is below threshold'", "'is bullish when'", 
		"'is within'", "'makes a higher high spanning'", "'makes a higher low spanning'", 
		"'makes a lower high spanning'", "'makes a lower low spanning'", "'makes a support break down spanning'", 
		"'makes a support break up spanning'", "'more than'", "'over'", "'override event list name with'", 
		"'override start shift with'", "'reverses down'", "'reverses up'", "'slope within'", 
		"'smoothed'", "'spanning'", "'starting within'", "'tolerance'", "'trends down'", 
		"'trends flat'", "'trends like'", "'trends unlike'", "'trends up'", "'type'"
	};
	public static final int EOF=-1;
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
	public static final int T__103=103;
	public static final int T__104=104;
	public static final int T__105=105;
	public static final int T__106=106;
	public static final int T__107=107;
	public static final int T__108=108;
	public static final int T__109=109;
	public static final int T__110=110;
	public static final int T__111=111;
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
	public static final int ReverseCondition=46;
	public static final int SEMICOLUMN=47;
	public static final int StockOperation=48;
	public static final int String=49;
	public static final int StringOperation=50;
	public static final int StringToken=51;
	public static final int SupConstantCondition=52;
	public static final int SupDoubleMapCondition=53;
	public static final int SupportBreakDown=54;
	public static final int SupportBreakUp=55;
	public static final int TRUTHOF=56;
	public static final int Tcheat=57;
	public static final int TruthOfCondition=58;
	public static final int UpRatioCondition=59;
	public static final int WS=60;
	public static final int WhiteChar=61;

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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:161:1: complete_expression : bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift na_event_list_name -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift na_event_list_name ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:161:21: (bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift na_event_list_name -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift na_event_list_name ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:4: bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift na_event_list_name
			{
			pushFollow(FOLLOW_bullish_condition_in_complete_expression436);
			bcond=bullish_condition();
			state._fsp--;

			stream_bullish_condition.add(bcond.getTree());
			pushFollow(FOLLOW_bearish_condition_in_complete_expression438);
			bearish_condition1=bearish_condition((bcond!=null?((CommonTree)bcond.getTree()):null));
			state._fsp--;

			stream_bearish_condition.add(bearish_condition1.getTree());
			pushFollow(FOLLOW_also_display_in_complete_expression441);
			also_display2=also_display();
			state._fsp--;

			stream_also_display.add(also_display2.getTree());
			pushFollow(FOLLOW_fixed_start_shift_in_complete_expression443);
			fixed_start_shift3=fixed_start_shift();
			state._fsp--;

			stream_fixed_start_shift.add(fixed_start_shift3.getTree());
			pushFollow(FOLLOW_na_event_list_name_in_complete_expression445);
			na_event_list_name4=na_event_list_name();
			state._fsp--;

			stream_na_event_list_name.add(na_event_list_name4.getTree());
			// AST REWRITE
			// elements: bullish_condition, bearish_condition, also_display, na_event_list_name, fixed_start_shift
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 163:109: -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift na_event_list_name )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:112: ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift na_event_list_name )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:1: bullish_condition : 'is bullish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression ;
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
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_87=new RewriteRuleTokenStream(adaptor,"token 87");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:19: ( 'is bullish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:2: 'is bullish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )*
			{
			string_literal5=(Token)match(input,87,FOLLOW_87_in_bullish_condition474);  
			stream_87.add(string_literal5);

			WhiteChar6=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition476);  
			stream_WhiteChar.add(WhiteChar6);

			pushFollow(FOLLOW_primary_expression_in_bullish_condition478);
			primary_expression7=primary_expression();
			state._fsp--;

			stream_primary_expression.add(primary_expression7.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:49: ( WhiteChar )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==WhiteChar) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:49: WhiteChar
					{
					WhiteChar8=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition480);  
					stream_WhiteChar.add(WhiteChar8);

					}
					break;

				default :
					break loop1;
				}
			}

			SEMICOLUMN9=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_bullish_condition483);  
			stream_SEMICOLUMN.add(SEMICOLUMN9);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:71: ( WhiteChar )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==WhiteChar) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:71: WhiteChar
					{
					WhiteChar10=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition485);  
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
			// 167:82: -> primary_expression
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:1: bearish_condition[CommonTree bcond] : ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> bearish_not_bullish );
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
		RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
		RewriteRuleSubtreeStream stream_bearish_not_bullish=new RewriteRuleSubtreeStream(adaptor,"rule bearish_not_bullish");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:37: ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> bearish_not_bullish )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==84) ) {
				alt7=1;
			}
			else if ( (LA7_0==83) ) {
				alt7=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:2: 'is bearish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )*
					{
					string_literal11=(Token)match(input,84,FOLLOW_84_in_bearish_condition501);  
					stream_84.add(string_literal11);

					WhiteChar12=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition503);  
					stream_WhiteChar.add(WhiteChar12);

					pushFollow(FOLLOW_primary_expression_in_bearish_condition505);
					primary_expression13=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression13.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:49: ( WhiteChar )*
					loop3:
					while (true) {
						int alt3=2;
						int LA3_0 = input.LA(1);
						if ( (LA3_0==WhiteChar) ) {
							alt3=1;
						}

						switch (alt3) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:49: WhiteChar
							{
							WhiteChar14=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition507);  
							stream_WhiteChar.add(WhiteChar14);

							}
							break;

						default :
							break loop3;
						}
					}

					SEMICOLUMN15=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_bearish_condition510);  
					stream_SEMICOLUMN.add(SEMICOLUMN15);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:71: ( WhiteChar )*
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( (LA4_0==WhiteChar) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:71: WhiteChar
							{
							WhiteChar16=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition512);  
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
					// 170:82: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:171:2: bearish_not_bullish[$bcond] ( WhiteChar )* SEMICOLUMN ( WhiteChar )*
					{
					pushFollow(FOLLOW_bearish_not_bullish_in_bearish_condition522);
					bearish_not_bullish17=bearish_not_bullish(bcond);
					state._fsp--;

					stream_bearish_not_bullish.add(bearish_not_bullish17.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:171:30: ( WhiteChar )*
					loop5:
					while (true) {
						int alt5=2;
						int LA5_0 = input.LA(1);
						if ( (LA5_0==WhiteChar) ) {
							alt5=1;
						}

						switch (alt5) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:171:30: WhiteChar
							{
							WhiteChar18=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition525);  
							stream_WhiteChar.add(WhiteChar18);

							}
							break;

						default :
							break loop5;
						}
					}

					SEMICOLUMN19=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_bearish_condition528);  
					stream_SEMICOLUMN.add(SEMICOLUMN19);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:171:52: ( WhiteChar )*
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( (LA6_0==WhiteChar) ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:171:52: WhiteChar
							{
							WhiteChar20=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition530);  
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
					// 171:63: -> bearish_not_bullish
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:2: also_display : ( 'also display' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition );
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
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:15: ( 'also display' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==64) ) {
				alt9=1;
			}
			else if ( (LA9_0==EOF||(LA9_0 >= 97 && LA9_0 <= 98)) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:174:3: 'also display' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN
					{
					string_literal21=(Token)match(input,64,FOLLOW_64_in_also_display547);  
					stream_64.add(string_literal21);

					WhiteChar22=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display549);  
					stream_WhiteChar.add(WhiteChar22);

					pushFollow(FOLLOW_primary_expression_in_also_display551);
					primary_expression23=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression23.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:174:47: ( WhiteChar )*
					loop8:
					while (true) {
						int alt8=2;
						int LA8_0 = input.LA(1);
						if ( (LA8_0==WhiteChar) ) {
							alt8=1;
						}

						switch (alt8) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:174:47: WhiteChar
							{
							WhiteChar24=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display553);  
							stream_WhiteChar.add(WhiteChar24);

							}
							break;

						default :
							break loop8;
						}
					}

					SEMICOLUMN25=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_also_display556);  
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
					// 174:69: -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:174:72: ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndBooleanMapCondition, "AndBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:174:97: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:175:3: 
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
					// 175:3: -> NullCondition
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:177:2: fixed_start_shift : ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS SEMICOLUMN ->| -> ^( Number NumberToken[\"-1\"] ) );
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
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_98=new RewriteRuleTokenStream(adaptor,"token 98");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:177:20: ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS SEMICOLUMN ->| -> ^( Number NumberToken[\"-1\"] ) )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==98) ) {
				alt10=1;
			}
			else if ( (LA10_0==EOF||LA10_0==97) ) {
				alt10=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:178:3: 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS SEMICOLUMN
					{
					string_literal26=(Token)match(input,98,FOLLOW_98_in_fixed_start_shift591);  
					stream_98.add(string_literal26);

					WhiteChar27=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift593);  
					stream_WhiteChar.add(WhiteChar27);

					pushFollow(FOLLOW_constant_in_fixed_start_shift597);
					fixedStartShift=constant();
					state._fsp--;

					stream_constant.add(fixedStartShift.getTree());
					WhiteChar28=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift599);  
					stream_WhiteChar.add(WhiteChar28);

					DAYS29=(Token)match(input,DAYS,FOLLOW_DAYS_in_fixed_start_shift601);  
					stream_DAYS.add(DAYS29);

					SEMICOLUMN30=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_fixed_start_shift603);  
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
					// 178:92: ->
					{
						adaptor.addChild(root_0, (fixedStartShift!=null?((CommonTree)fixedStartShift.getTree()):null));
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
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
					// 179:3: -> ^( Number NumberToken[\"-1\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:6: ^( Number NumberToken[\"-1\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:2: na_event_list_name : ( 'override event list name with' WhiteChar eventListName= stringconstant SEMICOLUMN ->| -> ^( String StringToken[\"FROM PARENT\"] ) );
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
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_97=new RewriteRuleTokenStream(adaptor,"token 97");
		RewriteRuleSubtreeStream stream_stringconstant=new RewriteRuleSubtreeStream(adaptor,"rule stringconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:21: ( 'override event list name with' WhiteChar eventListName= stringconstant SEMICOLUMN ->| -> ^( String StringToken[\"FROM PARENT\"] ) )
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==97) ) {
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:182:3: 'override event list name with' WhiteChar eventListName= stringconstant SEMICOLUMN
					{
					string_literal31=(Token)match(input,97,FOLLOW_97_in_na_event_list_name632);  
					stream_97.add(string_literal31);

					WhiteChar32=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_na_event_list_name634);  
					stream_WhiteChar.add(WhiteChar32);

					pushFollow(FOLLOW_stringconstant_in_na_event_list_name638);
					eventListName=stringconstant();
					state._fsp--;

					stream_stringconstant.add(eventListName.getTree());
					SEMICOLUMN33=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_na_event_list_name640);  
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
					// 182:85: ->
					{
						adaptor.addChild(root_0, (eventListName!=null?((CommonTree)eventListName.getTree()):null));
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:3: 
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
					// 183:3: -> ^( String StringToken[\"FROM PARENT\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:6: ^( String StringToken[\"FROM PARENT\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:1: bearish_not_bullish[CommonTree bcond] : 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ) ;
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
		RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:39: ( 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:2: 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			{
			string_literal34=(Token)match(input,83,FOLLOW_83_in_bearish_not_bullish669);  
			stream_83.add(string_literal34);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:3: ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:3: WhiteChar AND WhiteChar primary_expression
					{
					WhiteChar35=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish677);  
					stream_WhiteChar.add(WhiteChar35);

					AND36=(Token)match(input,AND,FOLLOW_AND_in_bearish_not_bullish679);  
					stream_AND.add(AND36);

					WhiteChar37=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish681);  
					stream_WhiteChar.add(WhiteChar37);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish683);
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
					// 189:46: -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:49: ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndBooleanMapCondition, "AndBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:74: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:109: ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_2);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:134: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:3: WhiteChar OR WhiteChar primary_expression
					{
					WhiteChar39=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish716);  
					stream_WhiteChar.add(WhiteChar39);

					OR40=(Token)match(input,OR,FOLLOW_OR_in_bearish_not_bullish718);  
					stream_OR.add(OR40);

					WhiteChar41=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish720);  
					stream_WhiteChar.add(WhiteChar41);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish722);
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
					// 190:45: -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:48: ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrBooleanMapCondition, "OrBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:72: ^( String StringToken[\"\\\"TRUE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:106: ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_2);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:131: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:3: 
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
					// 191:3: -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:6: ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:31: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:1: primary_expression : and_expression ;
	public final ParameterizedIndicatorsParser.primary_expression_return primary_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.primary_expression_return retval = new ParameterizedIndicatorsParser.primary_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope and_expression43 =null;


		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:20: ( and_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:3: and_expression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_and_expression_in_primary_expression785);
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:1: and_expression : or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndBooleanMapCondition or_expression ( or_expression )* ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:16: ( or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndBooleanMapCondition or_expression ( or_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:3: or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )*
			{
			pushFollow(FOLLOW_or_expression_in_and_expression797);
			or_expression44=or_expression();
			state._fsp--;

			stream_or_expression.add(or_expression44.getTree());
			pushFollow(FOLLOW_lenient_in_and_expression801);
			lenientParam=lenient();
			state._fsp--;

			stream_lenient.add(lenientParam.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:38: ( WhiteChar AND WhiteChar or_expression )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:39: WhiteChar AND WhiteChar or_expression
					{
					WhiteChar45=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression804);  
					stream_WhiteChar.add(WhiteChar45);

					AND46=(Token)match(input,AND,FOLLOW_AND_in_and_expression806);  
					stream_AND.add(AND46);

					WhiteChar47=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression808);  
					stream_WhiteChar.add(WhiteChar47);

					pushFollow(FOLLOW_or_expression_in_and_expression810);
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
			// 200:79: -> ^( AndBooleanMapCondition or_expression ( or_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:82: ^( AndBooleanMapCondition or_expression ( or_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndBooleanMapCondition, "AndBooleanMapCondition"), root_1);
				adaptor.addChild(root_1, (lenientParam!=null?((CommonTree)lenientParam.getTree()):null));
				adaptor.addChild(root_1, stream_or_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:142: ( or_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:1: or_expression : matches_expression ( WhiteChar OR WhiteChar matches_expression )* -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) matches_expression ( matches_expression )* ) ;
	public final ParameterizedIndicatorsParser.or_expression_return or_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.or_expression_return retval = new ParameterizedIndicatorsParser.or_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar50=null;
		Token OR51=null;
		Token WhiteChar52=null;
		ParserRuleReturnScope matches_expression49 =null;
		ParserRuleReturnScope matches_expression53 =null;

		CommonTree WhiteChar50_tree=null;
		CommonTree OR51_tree=null;
		CommonTree WhiteChar52_tree=null;
		RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_matches_expression=new RewriteRuleSubtreeStream(adaptor,"rule matches_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:15: ( matches_expression ( WhiteChar OR WhiteChar matches_expression )* -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) matches_expression ( matches_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:3: matches_expression ( WhiteChar OR WhiteChar matches_expression )*
			{
			pushFollow(FOLLOW_matches_expression_in_or_expression837);
			matches_expression49=matches_expression();
			state._fsp--;

			stream_matches_expression.add(matches_expression49.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:22: ( WhiteChar OR WhiteChar matches_expression )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:23: WhiteChar OR WhiteChar matches_expression
					{
					WhiteChar50=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression840);  
					stream_WhiteChar.add(WhiteChar50);

					OR51=(Token)match(input,OR,FOLLOW_OR_in_or_expression842);  
					stream_OR.add(OR51);

					WhiteChar52=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression844);  
					stream_WhiteChar.add(WhiteChar52);

					pushFollow(FOLLOW_matches_expression_in_or_expression846);
					matches_expression53=matches_expression();
					state._fsp--;

					stream_matches_expression.add(matches_expression53.getTree());
					}
					break;

				default :
					break loop14;
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
			// 203:67: -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) matches_expression ( matches_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:70: ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) matches_expression ( matches_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrBooleanMapCondition, "OrBooleanMapCondition"), root_1);
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:94: ^( String StringToken[\"\\\"TRUE\\\"\"] )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
				adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, stream_matches_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:147: ( matches_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:1: matches_expression : atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )? -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? ) ;
	public final ParameterizedIndicatorsParser.matches_expression_return matches_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.matches_expression_return retval = new ParameterizedIndicatorsParser.matches_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar55=null;
		Token MATCHING56=null;
		Token WhiteChar57=null;
		Token char_literal58=null;
		Token char_literal60=null;
		Token char_literal62=null;
		Token WhiteChar63=null;
		ParserRuleReturnScope atom54 =null;
		ParserRuleReturnScope constant59 =null;
		ParserRuleReturnScope constant61 =null;
		ParserRuleReturnScope atom64 =null;

		CommonTree WhiteChar55_tree=null;
		CommonTree MATCHING56_tree=null;
		CommonTree WhiteChar57_tree=null;
		CommonTree char_literal58_tree=null;
		CommonTree char_literal60_tree=null;
		CommonTree char_literal62_tree=null;
		CommonTree WhiteChar63_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_OPENSQRT=new RewriteRuleTokenStream(adaptor,"token OPENSQRT");
		RewriteRuleTokenStream stream_MATCHING=new RewriteRuleTokenStream(adaptor,"token MATCHING");
		RewriteRuleTokenStream stream_CLOSESQRT=new RewriteRuleTokenStream(adaptor,"token CLOSESQRT");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:20: ( atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )? -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:3: atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )?
			{
			pushFollow(FOLLOW_atom_in_matches_expression878);
			atom54=atom();
			state._fsp--;

			stream_atom.add(atom54.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:8: ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )?
			int alt16=2;
			int LA16_0 = input.LA(1);
			if ( (LA16_0==WhiteChar) ) {
				int LA16_1 = input.LA(2);
				if ( (LA16_1==MATCHING) ) {
					alt16=1;
				}
			}
			switch (alt16) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:9: WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom
					{
					WhiteChar55=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression881);  
					stream_WhiteChar.add(WhiteChar55);

					MATCHING56=(Token)match(input,MATCHING,FOLLOW_MATCHING_in_matches_expression883);  
					stream_MATCHING.add(MATCHING56);

					WhiteChar57=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression885);  
					stream_WhiteChar.add(WhiteChar57);

					char_literal58=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_matches_expression887);  
					stream_OPENSQRT.add(char_literal58);

					pushFollow(FOLLOW_constant_in_matches_expression889);
					constant59=constant();
					state._fsp--;

					stream_constant.add(constant59.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:51: ( ',' constant )*
					loop15:
					while (true) {
						int alt15=2;
						int LA15_0 = input.LA(1);
						if ( (LA15_0==COMMA) ) {
							alt15=1;
						}

						switch (alt15) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:52: ',' constant
							{
							char_literal60=(Token)match(input,COMMA,FOLLOW_COMMA_in_matches_expression892);  
							stream_COMMA.add(char_literal60);

							pushFollow(FOLLOW_constant_in_matches_expression894);
							constant61=constant();
							state._fsp--;

							stream_constant.add(constant61.getTree());
							}
							break;

						default :
							break loop15;
						}
					}

					char_literal62=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_matches_expression898);  
					stream_CLOSESQRT.add(char_literal62);

					WhiteChar63=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression900);  
					stream_WhiteChar.add(WhiteChar63);

					pushFollow(FOLLOW_atom_in_matches_expression902);
					atom64=atom();
					state._fsp--;

					stream_atom.add(atom64.getTree());
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
			// 206:88: -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:91: ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(MatchingBooleanMapCondition, "MatchingBooleanMapCondition"), root_1);
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:121: ( constant )*
				while ( stream_constant.hasNext() ) {
					adaptor.addChild(root_1, stream_constant.nextTree());
				}
				stream_constant.reset();

				adaptor.addChild(root_1, stream_atom.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:136: ( atom )?
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:1: atom : ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | NOT ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression ) | conjunctiontruthof );
	public final ParameterizedIndicatorsParser.atom_return atom() throws RecognitionException {
		ParameterizedIndicatorsParser.atom_return retval = new ParameterizedIndicatorsParser.atom_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal66=null;
		Token WhiteChar67=null;
		Token WhiteChar69=null;
		Token char_literal70=null;
		Token NOT71=null;
		Token WhiteChar72=null;
		Token char_literal73=null;
		Token WhiteChar74=null;
		Token WhiteChar76=null;
		Token char_literal77=null;
		ParserRuleReturnScope booleanhistory65 =null;
		ParserRuleReturnScope primary_expression68 =null;
		ParserRuleReturnScope primary_expression75 =null;
		ParserRuleReturnScope conjunctiontruthof78 =null;

		CommonTree char_literal66_tree=null;
		CommonTree WhiteChar67_tree=null;
		CommonTree WhiteChar69_tree=null;
		CommonTree char_literal70_tree=null;
		CommonTree NOT71_tree=null;
		CommonTree WhiteChar72_tree=null;
		CommonTree char_literal73_tree=null;
		CommonTree WhiteChar74_tree=null;
		CommonTree WhiteChar76_tree=null;
		CommonTree char_literal77_tree=null;
		RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_CLOSEPARENTEHSIS=new RewriteRuleTokenStream(adaptor,"token CLOSEPARENTEHSIS");
		RewriteRuleTokenStream stream_OPENPARENTEHSIS=new RewriteRuleTokenStream(adaptor,"token OPENPARENTEHSIS");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:6: ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | NOT ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression ) | conjunctiontruthof )
			int alt22=4;
			switch ( input.LA(1) ) {
			case HistoricalData:
			case Operation:
				{
				alt22=1;
				}
				break;
			case OPENPARENTEHSIS:
				{
				alt22=2;
				}
				break;
			case NOT:
				{
				alt22=3;
				}
				break;
			case TRUTHOF:
				{
				alt22=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}
			switch (alt22) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:3: booleanhistory
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_booleanhistory_in_atom930);
					booleanhistory65=booleanhistory();
					state._fsp--;

					adaptor.addChild(root_0, booleanhistory65.getTree());

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:3: '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					char_literal66=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom936);  
					stream_OPENPARENTEHSIS.add(char_literal66);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:7: ( WhiteChar )*
					loop17:
					while (true) {
						int alt17=2;
						int LA17_0 = input.LA(1);
						if ( (LA17_0==WhiteChar) ) {
							alt17=1;
						}

						switch (alt17) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:7: WhiteChar
							{
							WhiteChar67=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom938);  
							stream_WhiteChar.add(WhiteChar67);

							}
							break;

						default :
							break loop17;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom941);
					primary_expression68=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression68.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:37: ( WhiteChar )*
					loop18:
					while (true) {
						int alt18=2;
						int LA18_0 = input.LA(1);
						if ( (LA18_0==WhiteChar) ) {
							alt18=1;
						}

						switch (alt18) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:37: WhiteChar
							{
							WhiteChar69=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom943);  
							stream_WhiteChar.add(WhiteChar69);

							}
							break;

						default :
							break loop18;
						}
					}

					char_literal70=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom946);  
					stream_CLOSEPARENTEHSIS.add(char_literal70);

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
					// 210:52: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:3: NOT ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					NOT71=(Token)match(input,NOT,FOLLOW_NOT_in_atom956);  
					stream_NOT.add(NOT71);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:7: ( WhiteChar )*
					loop19:
					while (true) {
						int alt19=2;
						int LA19_0 = input.LA(1);
						if ( (LA19_0==WhiteChar) ) {
							alt19=1;
						}

						switch (alt19) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:7: WhiteChar
							{
							WhiteChar72=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom958);  
							stream_WhiteChar.add(WhiteChar72);

							}
							break;

						default :
							break loop19;
						}
					}

					char_literal73=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom961);  
					stream_OPENPARENTEHSIS.add(char_literal73);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:22: ( WhiteChar )*
					loop20:
					while (true) {
						int alt20=2;
						int LA20_0 = input.LA(1);
						if ( (LA20_0==WhiteChar) ) {
							alt20=1;
						}

						switch (alt20) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:22: WhiteChar
							{
							WhiteChar74=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom963);  
							stream_WhiteChar.add(WhiteChar74);

							}
							break;

						default :
							break loop20;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom966);
					primary_expression75=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression75.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:52: ( WhiteChar )*
					loop21:
					while (true) {
						int alt21=2;
						int LA21_0 = input.LA(1);
						if ( (LA21_0==WhiteChar) ) {
							alt21=1;
						}

						switch (alt21) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:52: WhiteChar
							{
							WhiteChar76=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom968);  
							stream_WhiteChar.add(WhiteChar76);

							}
							break;

						default :
							break loop21;
						}
					}

					char_literal77=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom971);  
					stream_CLOSEPARENTEHSIS.add(char_literal77);

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
					// 211:67: -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:70: ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:95: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:3: conjunctiontruthof
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_conjunctiontruthof_in_atom992);
					conjunctiontruthof78=conjunctiontruthof();
					state._fsp--;

					adaptor.addChild(root_0, conjunctiontruthof78.getTree());

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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:1: conjunctiontruthof : TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']' -> ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* ) ;
	public final ParameterizedIndicatorsParser.conjunctiontruthof_return conjunctiontruthof() throws RecognitionException {
		ParameterizedIndicatorsParser.conjunctiontruthof_return retval = new ParameterizedIndicatorsParser.conjunctiontruthof_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token TRUTHOF79=null;
		Token WhiteChar80=null;
		Token COMMA82=null;
		Token WhiteChar83=null;
		Token WhiteChar85=null;
		Token string_literal86=null;
		Token WhiteChar87=null;
		Token char_literal88=null;
		Token char_literal89=null;
		Token char_literal90=null;
		ParserRuleReturnScope min =null;
		ParserRuleReturnScope max =null;
		ParserRuleReturnScope primary_expression81 =null;
		ParserRuleReturnScope primary_expression84 =null;

		CommonTree TRUTHOF79_tree=null;
		CommonTree WhiteChar80_tree=null;
		CommonTree COMMA82_tree=null;
		CommonTree WhiteChar83_tree=null;
		CommonTree WhiteChar85_tree=null;
		CommonTree string_literal86_tree=null;
		CommonTree WhiteChar87_tree=null;
		CommonTree char_literal88_tree=null;
		CommonTree char_literal89_tree=null;
		CommonTree char_literal90_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_OPENSQRT=new RewriteRuleTokenStream(adaptor,"token OPENSQRT");
		RewriteRuleTokenStream stream_CLOSESQRT=new RewriteRuleTokenStream(adaptor,"token CLOSESQRT");
		RewriteRuleTokenStream stream_TRUTHOF=new RewriteRuleTokenStream(adaptor,"token TRUTHOF");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:20: ( TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']' -> ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:3: TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']'
			{
			TRUTHOF79=(Token)match(input,TRUTHOF,FOLLOW_TRUTHOF_in_conjunctiontruthof1004);  
			stream_TRUTHOF.add(TRUTHOF79);

			WhiteChar80=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof1006);  
			stream_WhiteChar.add(WhiteChar80);

			pushFollow(FOLLOW_primary_expression_in_conjunctiontruthof1008);
			primary_expression81=primary_expression();
			state._fsp--;

			stream_primary_expression.add(primary_expression81.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:40: ( COMMA WhiteChar primary_expression )*
			loop23:
			while (true) {
				int alt23=2;
				int LA23_0 = input.LA(1);
				if ( (LA23_0==COMMA) ) {
					alt23=1;
				}

				switch (alt23) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:41: COMMA WhiteChar primary_expression
					{
					COMMA82=(Token)match(input,COMMA,FOLLOW_COMMA_in_conjunctiontruthof1011);  
					stream_COMMA.add(COMMA82);

					WhiteChar83=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof1013);  
					stream_WhiteChar.add(WhiteChar83);

					pushFollow(FOLLOW_primary_expression_in_conjunctiontruthof1015);
					primary_expression84=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression84.getTree());
					}
					break;

				default :
					break loop23;
				}
			}

			WhiteChar85=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof1019);  
			stream_WhiteChar.add(WhiteChar85);

			string_literal86=(Token)match(input,88,FOLLOW_88_in_conjunctiontruthof1021);  
			stream_88.add(string_literal86);

			WhiteChar87=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof1023);  
			stream_WhiteChar.add(WhiteChar87);

			char_literal88=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_conjunctiontruthof1025);  
			stream_OPENSQRT.add(char_literal88);

			pushFollow(FOLLOW_constant_in_conjunctiontruthof1029);
			min=constant();
			state._fsp--;

			stream_constant.add(min.getTree());
			char_literal89=(Token)match(input,COMMA,FOLLOW_COMMA_in_conjunctiontruthof1031);  
			stream_COMMA.add(char_literal89);

			pushFollow(FOLLOW_constant_in_conjunctiontruthof1035);
			max=constant();
			state._fsp--;

			stream_constant.add(max.getTree());
			char_literal90=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_conjunctiontruthof1037);  
			stream_CLOSESQRT.add(char_literal90);

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
			// 216:4: -> ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:7: ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TruthOfCondition, "TruthOfCondition"), root_1);
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:26: ^( String StringToken[\"\\\"TRUE\\\"\"] )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
				adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, (min!=null?((CommonTree)min.getTree()):null));
				adaptor.addChild(root_1, (max!=null?((CommonTree)max.getTree()):null));
				adaptor.addChild(root_1, stream_primary_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:103: ( primary_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:1: booleanhistory : firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) ;
	public final ParameterizedIndicatorsParser.booleanhistory_return booleanhistory() throws RecognitionException {
		ParameterizedIndicatorsParser.booleanhistory_return retval = new ParameterizedIndicatorsParser.booleanhistory_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar91=null;
		ParserRuleReturnScope firstOp =null;
		ParserRuleReturnScope presetcondition92 =null;
		ParserRuleReturnScope opcmpcondition93 =null;
		ParserRuleReturnScope constantcmp94 =null;

		CommonTree WhiteChar91_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_presetcondition=new RewriteRuleSubtreeStream(adaptor,"rule presetcondition");
		RewriteRuleSubtreeStream stream_constantcmp=new RewriteRuleSubtreeStream(adaptor,"rule constantcmp");
		RewriteRuleSubtreeStream stream_opcmpcondition=new RewriteRuleSubtreeStream(adaptor,"rule opcmpcondition");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:16: (firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:2: firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			{
			pushFollow(FOLLOW_operand_in_booleanhistory1076);
			firstOp=operand();
			state._fsp--;

			stream_operand.add(firstOp.getTree());
			WhiteChar91=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_booleanhistory1078);  
			stream_WhiteChar.add(WhiteChar91);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:28: ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			int alt24=3;
			switch ( input.LA(1) ) {
			case 68:
			case 70:
			case 78:
			case 79:
			case 89:
			case 90:
			case 91:
			case 92:
			case 93:
			case 94:
			case 99:
			case 100:
			case 106:
			case 107:
			case 110:
				{
				alt24=1;
				}
				break;
			case 67:
			case 69:
			case 74:
			case 81:
			case 85:
			case 108:
			case 109:
				{
				alt24=2;
				}
				break;
			case 75:
			case 76:
			case 82:
			case 86:
				{
				alt24=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 24, 0, input);
				throw nvae;
			}
			switch (alt24) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:3: presetcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_presetcondition_in_booleanhistory1084);
					presetcondition92=presetcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_presetcondition.add(presetcondition92.getTree());
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
					// 220:34: -> presetcondition
					{
						adaptor.addChild(root_0, stream_presetcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:3: opcmpcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_opcmpcondition_in_booleanhistory1095);
					opcmpcondition93=opcmpcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_opcmpcondition.add(opcmpcondition93.getTree());
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
					// 221:33: -> opcmpcondition
					{
						adaptor.addChild(root_0, stream_opcmpcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:3: constantcmp[$firstOp.tree]
					{
					pushFollow(FOLLOW_constantcmp_in_booleanhistory1106);
					constantcmp94=constantcmp((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_constantcmp.add(constantcmp94.getTree());
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
					// 222:30: -> constantcmp
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:1: operand : ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation );
	public final ParameterizedIndicatorsParser.operand_return operand() throws RecognitionException {
		ParameterizedIndicatorsParser.operand_return retval = new ParameterizedIndicatorsParser.operand_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token opName=null;
		Token HistoricalData95=null;

		CommonTree opName_tree=null;
		CommonTree HistoricalData95_tree=null;
		RewriteRuleTokenStream stream_Operation=new RewriteRuleTokenStream(adaptor,"token Operation");
		RewriteRuleTokenStream stream_HistoricalData=new RewriteRuleTokenStream(adaptor,"token HistoricalData");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:9: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation )
			int alt25=2;
			int LA25_0 = input.LA(1);
			if ( (LA25_0==HistoricalData) ) {
				alt25=1;
			}
			else if ( (LA25_0==Operation) ) {
				alt25=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 25, 0, input);
				throw nvae;
			}

			switch (alt25) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:11: HistoricalData
					{
					HistoricalData95=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_operand1122);  
					stream_HistoricalData.add(HistoricalData95);

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
					// 225:26: -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:29: ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:46: ^( OperationOutput HistoricalData )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
						adaptor.addChild(root_2, stream_HistoricalData.nextNode());
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:80: ^( String StringToken[\"\\\"THIS\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:117: opName= Operation
					{
					opName=(Token)match(input,Operation,FOLLOW_Operation_in_operand1149);  
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
					// 225:171: -> Operation
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:1: constant : ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) );
	public final ParameterizedIndicatorsParser.constant_return constant() throws RecognitionException {
		ParameterizedIndicatorsParser.constant_return retval = new ParameterizedIndicatorsParser.constant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NumberToken96=null;
		Token string_literal97=null;

		CommonTree NumberToken96_tree=null;
		CommonTree string_literal97_tree=null;
		RewriteRuleTokenStream stream_NumberToken=new RewriteRuleTokenStream(adaptor,"token NumberToken");
		RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:10: ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) )
			int alt26=2;
			int LA26_0 = input.LA(1);
			if ( (LA26_0==NumberToken) ) {
				alt26=1;
			}
			else if ( (LA26_0==62) ) {
				alt26=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 26, 0, input);
				throw nvae;
			}

			switch (alt26) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:13: NumberToken
					{
					NumberToken96=(Token)match(input,NumberToken,FOLLOW_NumberToken_in_constant1164);  
					stream_NumberToken.add(NumberToken96);

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
					// 227:25: -> ^( Number NumberToken )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:28: ^( Number NumberToken )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:52: 'NaN'
					{
					string_literal97=(Token)match(input,62,FOLLOW_62_in_constant1176);  
					stream_62.add(string_literal97);

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
					// 227:58: -> ^( Number NumberToken[\"NaN\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:61: ^( Number NumberToken[\"NaN\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:1: stringconstant : StringToken -> ^( String StringToken ) ;
	public final ParameterizedIndicatorsParser.stringconstant_return stringconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.stringconstant_return retval = new ParameterizedIndicatorsParser.stringconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token StringToken98=null;

		CommonTree StringToken98_tree=null;
		RewriteRuleTokenStream stream_StringToken=new RewriteRuleTokenStream(adaptor,"token StringToken");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:16: ( StringToken -> ^( String StringToken ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:18: StringToken
			{
			StringToken98=(Token)match(input,StringToken,FOLLOW_StringToken_in_stringconstant1192);  
			stream_StringToken.add(StringToken98);

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
			// 228:30: -> ^( String StringToken )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:33: ^( String StringToken )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:1: trendconstant : ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) );
	public final ParameterizedIndicatorsParser.trendconstant_return trendconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.trendconstant_return retval = new ParameterizedIndicatorsParser.trendconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal99=null;
		Token string_literal100=null;

		CommonTree string_literal99_tree=null;
		CommonTree string_literal100_tree=null;
		RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
		RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:15: ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) )
			int alt27=2;
			int LA27_0 = input.LA(1);
			if ( (LA27_0==66) ) {
				alt27=1;
			}
			else if ( (LA27_0==65) ) {
				alt27=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 27, 0, input);
				throw nvae;
			}

			switch (alt27) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:17: 'bullish'
					{
					string_literal99=(Token)match(input,66,FOLLOW_66_in_trendconstant1207);  
					stream_66.add(string_literal99);

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
					// 229:27: -> ^( String StringToken[\"\\\"bullish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:30: ^( String StringToken[\"\\\"bullish\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:69: 'bearish'
					{
					string_literal100=(Token)match(input,65,FOLLOW_65_in_trendconstant1220);  
					stream_65.add(string_literal100);

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
					// 229:79: -> ^( String StringToken[\"\\\"bearish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:82: ^( String StringToken[\"\\\"bearish\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:1: lenient : ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ;
	public final ParameterizedIndicatorsParser.lenient_return lenient() throws RecognitionException {
		ParameterizedIndicatorsParser.lenient_return retval = new ParameterizedIndicatorsParser.lenient_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar101=null;
		Token LENIENT102=null;

		CommonTree WhiteChar101_tree=null;
		CommonTree LENIENT102_tree=null;
		RewriteRuleTokenStream stream_LENIENT=new RewriteRuleTokenStream(adaptor,"token LENIENT");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:9: ( ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			int alt28=2;
			int LA28_0 = input.LA(1);
			if ( (LA28_0==WhiteChar) ) {
				int LA28_1 = input.LA(2);
				if ( (LA28_1==LENIENT) ) {
					alt28=1;
				}
				else if ( (LA28_1==AND||LA28_1==CLOSEPARENTEHSIS||LA28_1==SEMICOLUMN||LA28_1==WhiteChar||LA28_1==88) ) {
					alt28=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 28, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA28_0==CLOSEPARENTEHSIS||LA28_0==COMMA||LA28_0==SEMICOLUMN) ) {
				alt28=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 28, 0, input);
				throw nvae;
			}

			switch (alt28) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:12: WhiteChar LENIENT
					{
					WhiteChar101=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_lenient1237);  
					stream_WhiteChar.add(WhiteChar101);

					LENIENT102=(Token)match(input,LENIENT,FOLLOW_LENIENT_in_lenient1239);  
					stream_LENIENT.add(LENIENT102);

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
					// 230:30: -> ^( String StringToken[\"\\\"TRUE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:33: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:69: 
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
					// 230:69: -> ^( String StringToken[\"\\\"FALSE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:72: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:1: opcmpcondition[CommonTree firstOp] : ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )? | ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) );
	public final ParameterizedIndicatorsParser.opcmpcondition_return opcmpcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.opcmpcondition_return retval = new ParameterizedIndicatorsParser.opcmpcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal103=null;
		Token WhiteChar104=null;
		Token WhiteChar105=null;
		Token string_literal106=null;
		Token WhiteChar107=null;
		Token WhiteChar108=null;
		Token DAYS109=null;
		Token WhiteChar110=null;
		Token string_literal111=null;
		Token WhiteChar112=null;
		Token PERCENT113=null;
		Token string_literal114=null;
		Token WhiteChar115=null;
		Token WhiteChar116=null;
		Token string_literal117=null;
		Token WhiteChar118=null;
		Token WhiteChar119=null;
		Token DAYS120=null;
		Token WhiteChar121=null;
		Token string_literal122=null;
		Token WhiteChar123=null;
		Token PERCENT124=null;
		Token string_literal125=null;
		Token WhiteChar126=null;
		Token WhiteChar127=null;
		Token string_literal128=null;
		Token WhiteChar129=null;
		Token WhiteChar130=null;
		Token DAYS131=null;
		Token WhiteChar132=null;
		Token string_literal133=null;
		Token WhiteChar134=null;
		Token PERCENT135=null;
		Token string_literal136=null;
		Token WhiteChar137=null;
		Token WhiteChar138=null;
		Token string_literal139=null;
		Token WhiteChar140=null;
		Token WhiteChar141=null;
		Token DAYS142=null;
		Token WhiteChar143=null;
		Token string_literal144=null;
		Token WhiteChar145=null;
		Token WhiteChar146=null;
		Token DAYS147=null;
		Token WhiteChar148=null;
		Token string_literal149=null;
		Token WhiteChar150=null;
		Token PERCENT151=null;
		Token WhiteChar152=null;
		Token string_literal153=null;
		Token WhiteChar154=null;
		Token PERCENT155=null;
		Token WhiteChar156=null;
		Token string_literal157=null;
		Token WhiteChar158=null;
		Token WhiteChar159=null;
		Token string_literal160=null;
		Token WhiteChar161=null;
		Token WhiteChar162=null;
		Token DAYS163=null;
		Token WhiteChar164=null;
		Token string_literal165=null;
		Token WhiteChar166=null;
		Token WhiteChar167=null;
		Token DAYS168=null;
		Token WhiteChar169=null;
		Token string_literal170=null;
		Token WhiteChar171=null;
		Token PERCENT172=null;
		Token WhiteChar173=null;
		Token string_literal174=null;
		Token WhiteChar175=null;
		Token PERCENT176=null;
		Token WhiteChar177=null;
		Token string_literal178=null;
		Token WhiteChar179=null;
		Token WhiteChar180=null;
		Token string_literal181=null;
		Token WhiteChar182=null;
		Token WhiteChar183=null;
		Token DAYS184=null;
		Token WhiteChar185=null;
		Token string_literal186=null;
		Token WhiteChar187=null;
		Token WhiteChar188=null;
		Token DAYS189=null;
		Token WhiteChar190=null;
		Token string_literal191=null;
		Token WhiteChar192=null;
		Token WhiteChar193=null;
		Token string_literal194=null;
		Token WhiteChar195=null;
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
		Token WhiteChar208=null;
		Token string_literal209=null;
		Token WhiteChar210=null;
		ParserRuleReturnScope secondOp =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope epsilon =null;
		ParserRuleReturnScope spanningNbDays =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope adaptiveRate =null;
		ParserRuleReturnScope adaptive =null;
		ParserRuleReturnScope direction =null;

		CommonTree string_literal103_tree=null;
		CommonTree WhiteChar104_tree=null;
		CommonTree WhiteChar105_tree=null;
		CommonTree string_literal106_tree=null;
		CommonTree WhiteChar107_tree=null;
		CommonTree WhiteChar108_tree=null;
		CommonTree DAYS109_tree=null;
		CommonTree WhiteChar110_tree=null;
		CommonTree string_literal111_tree=null;
		CommonTree WhiteChar112_tree=null;
		CommonTree PERCENT113_tree=null;
		CommonTree string_literal114_tree=null;
		CommonTree WhiteChar115_tree=null;
		CommonTree WhiteChar116_tree=null;
		CommonTree string_literal117_tree=null;
		CommonTree WhiteChar118_tree=null;
		CommonTree WhiteChar119_tree=null;
		CommonTree DAYS120_tree=null;
		CommonTree WhiteChar121_tree=null;
		CommonTree string_literal122_tree=null;
		CommonTree WhiteChar123_tree=null;
		CommonTree PERCENT124_tree=null;
		CommonTree string_literal125_tree=null;
		CommonTree WhiteChar126_tree=null;
		CommonTree WhiteChar127_tree=null;
		CommonTree string_literal128_tree=null;
		CommonTree WhiteChar129_tree=null;
		CommonTree WhiteChar130_tree=null;
		CommonTree DAYS131_tree=null;
		CommonTree WhiteChar132_tree=null;
		CommonTree string_literal133_tree=null;
		CommonTree WhiteChar134_tree=null;
		CommonTree PERCENT135_tree=null;
		CommonTree string_literal136_tree=null;
		CommonTree WhiteChar137_tree=null;
		CommonTree WhiteChar138_tree=null;
		CommonTree string_literal139_tree=null;
		CommonTree WhiteChar140_tree=null;
		CommonTree WhiteChar141_tree=null;
		CommonTree DAYS142_tree=null;
		CommonTree WhiteChar143_tree=null;
		CommonTree string_literal144_tree=null;
		CommonTree WhiteChar145_tree=null;
		CommonTree WhiteChar146_tree=null;
		CommonTree DAYS147_tree=null;
		CommonTree WhiteChar148_tree=null;
		CommonTree string_literal149_tree=null;
		CommonTree WhiteChar150_tree=null;
		CommonTree PERCENT151_tree=null;
		CommonTree WhiteChar152_tree=null;
		CommonTree string_literal153_tree=null;
		CommonTree WhiteChar154_tree=null;
		CommonTree PERCENT155_tree=null;
		CommonTree WhiteChar156_tree=null;
		CommonTree string_literal157_tree=null;
		CommonTree WhiteChar158_tree=null;
		CommonTree WhiteChar159_tree=null;
		CommonTree string_literal160_tree=null;
		CommonTree WhiteChar161_tree=null;
		CommonTree WhiteChar162_tree=null;
		CommonTree DAYS163_tree=null;
		CommonTree WhiteChar164_tree=null;
		CommonTree string_literal165_tree=null;
		CommonTree WhiteChar166_tree=null;
		CommonTree WhiteChar167_tree=null;
		CommonTree DAYS168_tree=null;
		CommonTree WhiteChar169_tree=null;
		CommonTree string_literal170_tree=null;
		CommonTree WhiteChar171_tree=null;
		CommonTree PERCENT172_tree=null;
		CommonTree WhiteChar173_tree=null;
		CommonTree string_literal174_tree=null;
		CommonTree WhiteChar175_tree=null;
		CommonTree PERCENT176_tree=null;
		CommonTree WhiteChar177_tree=null;
		CommonTree string_literal178_tree=null;
		CommonTree WhiteChar179_tree=null;
		CommonTree WhiteChar180_tree=null;
		CommonTree string_literal181_tree=null;
		CommonTree WhiteChar182_tree=null;
		CommonTree WhiteChar183_tree=null;
		CommonTree DAYS184_tree=null;
		CommonTree WhiteChar185_tree=null;
		CommonTree string_literal186_tree=null;
		CommonTree WhiteChar187_tree=null;
		CommonTree WhiteChar188_tree=null;
		CommonTree DAYS189_tree=null;
		CommonTree WhiteChar190_tree=null;
		CommonTree string_literal191_tree=null;
		CommonTree WhiteChar192_tree=null;
		CommonTree WhiteChar193_tree=null;
		CommonTree string_literal194_tree=null;
		CommonTree WhiteChar195_tree=null;
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
		CommonTree WhiteChar208_tree=null;
		CommonTree string_literal209_tree=null;
		CommonTree WhiteChar210_tree=null;
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_103=new RewriteRuleTokenStream(adaptor,"token 103");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
		RewriteRuleTokenStream stream_108=new RewriteRuleTokenStream(adaptor,"token 108");
		RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
		RewriteRuleTokenStream stream_109=new RewriteRuleTokenStream(adaptor,"token 109");
		RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
		RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");
		RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
		RewriteRuleTokenStream stream_96=new RewriteRuleTokenStream(adaptor,"token 96");
		RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
		RewriteRuleSubtreeStream stream_stringconstant=new RewriteRuleSubtreeStream(adaptor,"rule stringconstant");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:37: ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )? | ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) )
			int alt36=7;
			switch ( input.LA(1) ) {
			case 81:
				{
				alt36=1;
				}
				break;
			case 85:
				{
				alt36=2;
				}
				break;
			case 74:
				{
				alt36=3;
				}
				break;
			case 67:
				{
				alt36=4;
				}
				break;
			case 69:
				{
				alt36=5;
				}
				break;
			case 108:
				{
				alt36=6;
				}
				break;
			case 109:
				{
				alt36=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 36, 0, input);
				throw nvae;
			}
			switch (alt36) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:4: 'is above historical' WhiteChar secondOp= operand
					{
					string_literal103=(Token)match(input,81,FOLLOW_81_in_opcmpcondition1276);  
					stream_81.add(string_literal103);

					WhiteChar104=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1278);  
					stream_WhiteChar.add(WhiteChar104);

					pushFollow(FOLLOW_operand_in_opcmpcondition1282);
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
					// 235:53: -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:56: ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupDoubleMapCondition, "SupDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:80: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:107: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )?
					int alt29=2;
					int LA29_0 = input.LA(1);
					if ( (LA29_0==WhiteChar) ) {
						int LA29_1 = input.LA(2);
						if ( (LA29_1==77) ) {
							alt29=1;
						}
					}
					switch (alt29) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:5: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar105=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1313);  
							stream_WhiteChar.add(WhiteChar105);

							string_literal106=(Token)match(input,77,FOLLOW_77_in_opcmpcondition1315);  
							stream_77.add(string_literal106);

							WhiteChar107=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1317);  
							stream_WhiteChar.add(WhiteChar107);

							pushFollow(FOLLOW_constant_in_opcmpcondition1321);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar108=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1323);  
							stream_WhiteChar.add(WhiteChar108);

							DAYS109=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1325);  
							stream_DAYS.add(DAYS109);

							WhiteChar110=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1327);  
							stream_WhiteChar.add(WhiteChar110);

							string_literal111=(Token)match(input,73,FOLLOW_73_in_opcmpcondition1329);  
							stream_73.add(string_literal111);

							WhiteChar112=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1331);  
							stream_WhiteChar.add(WhiteChar112);

							pushFollow(FOLLOW_constant_in_opcmpcondition1335);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT113=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1337);  
							stream_PERCENT.add(PERCENT113);

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
							// 237:5: -> ^( SupDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:8: ^( SupDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:4: 'is below historical' WhiteChar secondOp= operand
					{
					string_literal114=(Token)match(input,85,FOLLOW_85_in_opcmpcondition1369);  
					stream_85.add(string_literal114);

					WhiteChar115=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1371);  
					stream_WhiteChar.add(WhiteChar115);

					pushFollow(FOLLOW_operand_in_opcmpcondition1375);
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
					// 239:53: -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:56: ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfDoubleMapCondition, "InfDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:80: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:107: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )?
					int alt30=2;
					int LA30_0 = input.LA(1);
					if ( (LA30_0==WhiteChar) ) {
						int LA30_1 = input.LA(2);
						if ( (LA30_1==77) ) {
							alt30=1;
						}
					}
					switch (alt30) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:5: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar116=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1406);  
							stream_WhiteChar.add(WhiteChar116);

							string_literal117=(Token)match(input,77,FOLLOW_77_in_opcmpcondition1408);  
							stream_77.add(string_literal117);

							WhiteChar118=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1410);  
							stream_WhiteChar.add(WhiteChar118);

							pushFollow(FOLLOW_constant_in_opcmpcondition1414);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar119=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1416);  
							stream_WhiteChar.add(WhiteChar119);

							DAYS120=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1418);  
							stream_DAYS.add(DAYS120);

							WhiteChar121=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1420);  
							stream_WhiteChar.add(WhiteChar121);

							string_literal122=(Token)match(input,73,FOLLOW_73_in_opcmpcondition1422);  
							stream_73.add(string_literal122);

							WhiteChar123=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1424);  
							stream_WhiteChar.add(WhiteChar123);

							pushFollow(FOLLOW_constant_in_opcmpcondition1428);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT124=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1430);  
							stream_PERCENT.add(PERCENT124);

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
							// 241:5: -> ^( InfDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:8: ^( InfDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:4: 'equals historical' WhiteChar secondOp= operand
					{
					string_literal125=(Token)match(input,74,FOLLOW_74_in_opcmpcondition1461);  
					stream_74.add(string_literal125);

					WhiteChar126=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1463);  
					stream_WhiteChar.add(WhiteChar126);

					pushFollow(FOLLOW_operand_in_opcmpcondition1467);
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
					// 243:51: -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:54: ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualDoubleMapCondition, "EqualDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:80: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:107: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )?
					int alt31=2;
					int LA31_0 = input.LA(1);
					if ( (LA31_0==WhiteChar) ) {
						int LA31_1 = input.LA(2);
						if ( (LA31_1==77) ) {
							alt31=1;
						}
					}
					switch (alt31) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:5: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar127=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1498);  
							stream_WhiteChar.add(WhiteChar127);

							string_literal128=(Token)match(input,77,FOLLOW_77_in_opcmpcondition1500);  
							stream_77.add(string_literal128);

							WhiteChar129=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1502);  
							stream_WhiteChar.add(WhiteChar129);

							pushFollow(FOLLOW_constant_in_opcmpcondition1506);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar130=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1508);  
							stream_WhiteChar.add(WhiteChar130);

							DAYS131=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1510);  
							stream_DAYS.add(DAYS131);

							WhiteChar132=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1512);  
							stream_WhiteChar.add(WhiteChar132);

							string_literal133=(Token)match(input,73,FOLLOW_73_in_opcmpcondition1514);  
							stream_73.add(string_literal133);

							WhiteChar134=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1516);  
							stream_WhiteChar.add(WhiteChar134);

							pushFollow(FOLLOW_constant_in_opcmpcondition1520);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT135=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1522);  
							stream_PERCENT.add(PERCENT135);

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
							// 245:5: -> ^( EqualDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:8: ^( EqualDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:3: ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:3: ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:4: 'crosses down historical' WhiteChar secondOp= operand
					{
					string_literal136=(Token)match(input,67,FOLLOW_67_in_opcmpcondition1555);  
					stream_67.add(string_literal136);

					WhiteChar137=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1557);  
					stream_WhiteChar.add(WhiteChar137);

					pushFollow(FOLLOW_operand_in_opcmpcondition1561);
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
					// 250:4: -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:7: ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:37: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:66: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:95: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:124: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:3: ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )?
					int alt33=2;
					int LA33_0 = input.LA(1);
					if ( (LA33_0==WhiteChar) ) {
						int LA33_1 = input.LA(2);
						if ( (LA33_1==103) ) {
							alt33=1;
						}
					}
					switch (alt33) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )?
							{
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:5: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar138=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1615);  
							stream_WhiteChar.add(WhiteChar138);

							string_literal139=(Token)match(input,103,FOLLOW_103_in_opcmpcondition1617);  
							stream_103.add(string_literal139);

							WhiteChar140=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1619);  
							stream_WhiteChar.add(WhiteChar140);

							pushFollow(FOLLOW_constant_in_opcmpcondition1623);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar141=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1625);  
							stream_WhiteChar.add(WhiteChar141);

							DAYS142=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1627);  
							stream_DAYS.add(DAYS142);

							WhiteChar143=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1629);  
							stream_WhiteChar.add(WhiteChar143);

							string_literal144=(Token)match(input,96,FOLLOW_96_in_opcmpcondition1631);  
							stream_96.add(string_literal144);

							WhiteChar145=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1633);  
							stream_WhiteChar.add(WhiteChar145);

							pushFollow(FOLLOW_constant_in_opcmpcondition1637);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar146=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1639);  
							stream_WhiteChar.add(WhiteChar146);

							DAYS147=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1641);  
							stream_DAYS.add(DAYS147);

							WhiteChar148=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1643);  
							stream_WhiteChar.add(WhiteChar148);

							string_literal149=(Token)match(input,73,FOLLOW_73_in_opcmpcondition1645);  
							stream_73.add(string_literal149);

							WhiteChar150=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1647);  
							stream_WhiteChar.add(WhiteChar150);

							pushFollow(FOLLOW_constant_in_opcmpcondition1651);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT151=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1653);  
							stream_PERCENT.add(PERCENT151);

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
							// 253:5: -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:8: ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:96: ^( Number NumberToken[\"0.0\"] )
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

							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:4: ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )?
							int alt32=2;
							int LA32_0 = input.LA(1);
							if ( (LA32_0==WhiteChar) ) {
								int LA32_1 = input.LA(2);
								if ( (LA32_1==63) ) {
									alt32=1;
								}
							}
							switch (alt32) {
								case 1 :
									// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:5: WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom
									{
									WhiteChar152=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1693);  
									stream_WhiteChar.add(WhiteChar152);

									string_literal153=(Token)match(input,63,FOLLOW_63_in_opcmpcondition1695);  
									stream_63.add(string_literal153);

									WhiteChar154=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1697);  
									stream_WhiteChar.add(WhiteChar154);

									pushFollow(FOLLOW_constant_in_opcmpcondition1701);
									adaptiveRate=constant();
									state._fsp--;

									stream_constant.add(adaptiveRate.getTree());
									PERCENT155=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1703);  
									stream_PERCENT.add(PERCENT155);

									WhiteChar156=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1705);  
									stream_WhiteChar.add(WhiteChar156);

									pushFollow(FOLLOW_atom_in_opcmpcondition1709);
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
									// 256:5: -> ^( CrossDownDoubleMapCondition )
									{
										// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:8: ^( CrossDownDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:259:3: ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:259:3: ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:259:4: 'crosses up historical' WhiteChar secondOp= operand
					{
					string_literal157=(Token)match(input,69,FOLLOW_69_in_opcmpcondition1752);  
					stream_69.add(string_literal157);

					WhiteChar158=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1754);  
					stream_WhiteChar.add(WhiteChar158);

					pushFollow(FOLLOW_operand_in_opcmpcondition1758);
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
					// 260:4: -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:7: ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:35: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:64: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:93: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:122: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:3: ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )?
					int alt35=2;
					int LA35_0 = input.LA(1);
					if ( (LA35_0==WhiteChar) ) {
						int LA35_1 = input.LA(2);
						if ( (LA35_1==103) ) {
							alt35=1;
						}
					}
					switch (alt35) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )?
							{
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:5: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar159=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1812);  
							stream_WhiteChar.add(WhiteChar159);

							string_literal160=(Token)match(input,103,FOLLOW_103_in_opcmpcondition1814);  
							stream_103.add(string_literal160);

							WhiteChar161=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1816);  
							stream_WhiteChar.add(WhiteChar161);

							pushFollow(FOLLOW_constant_in_opcmpcondition1820);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar162=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1822);  
							stream_WhiteChar.add(WhiteChar162);

							DAYS163=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1824);  
							stream_DAYS.add(DAYS163);

							WhiteChar164=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1826);  
							stream_WhiteChar.add(WhiteChar164);

							string_literal165=(Token)match(input,96,FOLLOW_96_in_opcmpcondition1828);  
							stream_96.add(string_literal165);

							WhiteChar166=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1830);  
							stream_WhiteChar.add(WhiteChar166);

							pushFollow(FOLLOW_constant_in_opcmpcondition1834);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar167=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1836);  
							stream_WhiteChar.add(WhiteChar167);

							DAYS168=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1838);  
							stream_DAYS.add(DAYS168);

							WhiteChar169=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1840);  
							stream_WhiteChar.add(WhiteChar169);

							string_literal170=(Token)match(input,73,FOLLOW_73_in_opcmpcondition1842);  
							stream_73.add(string_literal170);

							WhiteChar171=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1844);  
							stream_WhiteChar.add(WhiteChar171);

							pushFollow(FOLLOW_constant_in_opcmpcondition1848);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT172=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1850);  
							stream_PERCENT.add(PERCENT172);

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
							// 263:5: -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:8: ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:94: ^( Number NumberToken[\"0.0\"] )
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

							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:4: ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )?
							int alt34=2;
							int LA34_0 = input.LA(1);
							if ( (LA34_0==WhiteChar) ) {
								int LA34_1 = input.LA(2);
								if ( (LA34_1==63) ) {
									alt34=1;
								}
							}
							switch (alt34) {
								case 1 :
									// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:5: WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom
									{
									WhiteChar173=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1890);  
									stream_WhiteChar.add(WhiteChar173);

									string_literal174=(Token)match(input,63,FOLLOW_63_in_opcmpcondition1892);  
									stream_63.add(string_literal174);

									WhiteChar175=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1894);  
									stream_WhiteChar.add(WhiteChar175);

									pushFollow(FOLLOW_constant_in_opcmpcondition1898);
									adaptiveRate=constant();
									state._fsp--;

									stream_constant.add(adaptiveRate.getTree());
									PERCENT176=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1900);  
									stream_PERCENT.add(PERCENT176);

									WhiteChar177=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1902);  
									stream_WhiteChar.add(WhiteChar177);

									pushFollow(FOLLOW_atom_in_opcmpcondition1906);
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
									// 266:5: -> ^( CrossUpDoubleMapCondition )
									{
										// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:8: ^( CrossUpDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:4: 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal178=(Token)match(input,108,FOLLOW_108_in_opcmpcondition1951);  
					stream_108.add(string_literal178);

					WhiteChar179=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1953);  
					stream_WhiteChar.add(WhiteChar179);

					pushFollow(FOLLOW_operand_in_opcmpcondition1957);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar180=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1965);  
					stream_WhiteChar.add(WhiteChar180);

					string_literal181=(Token)match(input,96,FOLLOW_96_in_opcmpcondition1967);  
					stream_96.add(string_literal181);

					WhiteChar182=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1969);  
					stream_WhiteChar.add(WhiteChar182);

					pushFollow(FOLLOW_constant_in_opcmpcondition1973);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar183=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1975);  
					stream_WhiteChar.add(WhiteChar183);

					DAYS184=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1977);  
					stream_DAYS.add(DAYS184);

					WhiteChar185=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1985);  
					stream_WhiteChar.add(WhiteChar185);

					string_literal186=(Token)match(input,77,FOLLOW_77_in_opcmpcondition1987);  
					stream_77.add(string_literal186);

					WhiteChar187=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1989);  
					stream_WhiteChar.add(WhiteChar187);

					pushFollow(FOLLOW_constant_in_opcmpcondition1993);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar188=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1995);  
					stream_WhiteChar.add(WhiteChar188);

					DAYS189=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1997);  
					stream_DAYS.add(DAYS189);

					WhiteChar190=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2005);  
					stream_WhiteChar.add(WhiteChar190);

					string_literal191=(Token)match(input,71,FOLLOW_71_in_opcmpcondition2007);  
					stream_71.add(string_literal191);

					WhiteChar192=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2009);  
					stream_WhiteChar.add(WhiteChar192);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition2013);
					direction=stringconstant();
					state._fsp--;

					stream_stringconstant.add(direction.getTree());
					WhiteChar193=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2021);  
					stream_WhiteChar.add(WhiteChar193);

					string_literal194=(Token)match(input,73,FOLLOW_73_in_opcmpcondition2023);  
					stream_73.add(string_literal194);

					WhiteChar195=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2025);  
					stream_WhiteChar.add(WhiteChar195);

					pushFollow(FOLLOW_constant_in_opcmpcondition2029);
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
					// 276:7: -> ^( LinearSimilarTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:10: ^( LinearSimilarTrendsCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:277:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:277:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:277:4: 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant
					{
					string_literal196=(Token)match(input,109,FOLLOW_109_in_opcmpcondition2061);  
					stream_109.add(string_literal196);

					WhiteChar197=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2063);  
					stream_WhiteChar.add(WhiteChar197);

					pushFollow(FOLLOW_operand_in_opcmpcondition2067);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar198=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2075);  
					stream_WhiteChar.add(WhiteChar198);

					string_literal199=(Token)match(input,96,FOLLOW_96_in_opcmpcondition2077);  
					stream_96.add(string_literal199);

					WhiteChar200=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2079);  
					stream_WhiteChar.add(WhiteChar200);

					pushFollow(FOLLOW_constant_in_opcmpcondition2083);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar201=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2085);  
					stream_WhiteChar.add(WhiteChar201);

					DAYS202=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition2087);  
					stream_DAYS.add(DAYS202);

					WhiteChar203=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2095);  
					stream_WhiteChar.add(WhiteChar203);

					string_literal204=(Token)match(input,77,FOLLOW_77_in_opcmpcondition2097);  
					stream_77.add(string_literal204);

					WhiteChar205=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2099);  
					stream_WhiteChar.add(WhiteChar205);

					pushFollow(FOLLOW_constant_in_opcmpcondition2103);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar206=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2105);  
					stream_WhiteChar.add(WhiteChar206);

					DAYS207=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition2107);  
					stream_DAYS.add(DAYS207);

					WhiteChar208=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2115);  
					stream_WhiteChar.add(WhiteChar208);

					string_literal209=(Token)match(input,71,FOLLOW_71_in_opcmpcondition2117);  
					stream_71.add(string_literal209);

					WhiteChar210=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2119);  
					stream_WhiteChar.add(WhiteChar210);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition2123);
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
					// 281:7: -> ^( LinearOppositeTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:10: ^( LinearOppositeTrendsCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:283:1: constantcmp[CommonTree firstOp] : ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )? );
	public final ParameterizedIndicatorsParser.constantcmp_return constantcmp(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.constantcmp_return retval = new ParameterizedIndicatorsParser.constantcmp_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal211=null;
		Token WhiteChar212=null;
		Token WhiteChar213=null;
		Token string_literal214=null;
		Token WhiteChar215=null;
		Token WhiteChar216=null;
		Token DAYS217=null;
		Token WhiteChar218=null;
		Token string_literal219=null;
		Token WhiteChar220=null;
		Token WhiteChar221=null;
		Token DAYS222=null;
		Token string_literal223=null;
		Token WhiteChar224=null;
		Token WhiteChar225=null;
		Token string_literal226=null;
		Token WhiteChar227=null;
		Token WhiteChar228=null;
		Token DAYS229=null;
		Token WhiteChar230=null;
		Token string_literal231=null;
		Token WhiteChar232=null;
		Token WhiteChar233=null;
		Token DAYS234=null;
		Token WhiteChar235=null;
		Token string_literal236=null;
		Token WhiteChar237=null;
		Token PERCENT238=null;
		Token string_literal239=null;
		Token WhiteChar240=null;
		Token WhiteChar241=null;
		Token string_literal242=null;
		Token WhiteChar243=null;
		Token WhiteChar244=null;
		Token DAYS245=null;
		Token WhiteChar246=null;
		Token string_literal247=null;
		Token WhiteChar248=null;
		Token WhiteChar249=null;
		Token DAYS250=null;
		Token WhiteChar251=null;
		Token string_literal252=null;
		Token WhiteChar253=null;
		Token PERCENT254=null;
		Token string_literal255=null;
		Token WhiteChar256=null;
		Token WhiteChar257=null;
		Token string_literal258=null;
		Token WhiteChar259=null;
		Token WhiteChar260=null;
		Token DAYS261=null;
		Token WhiteChar262=null;
		Token string_literal263=null;
		Token WhiteChar264=null;
		Token WhiteChar265=null;
		Token DAYS266=null;
		Token WhiteChar267=null;
		Token string_literal268=null;
		Token WhiteChar269=null;
		Token PERCENT270=null;
		ParserRuleReturnScope trendSignal =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope threshold =null;
		ParserRuleReturnScope epsilon =null;

		CommonTree string_literal211_tree=null;
		CommonTree WhiteChar212_tree=null;
		CommonTree WhiteChar213_tree=null;
		CommonTree string_literal214_tree=null;
		CommonTree WhiteChar215_tree=null;
		CommonTree WhiteChar216_tree=null;
		CommonTree DAYS217_tree=null;
		CommonTree WhiteChar218_tree=null;
		CommonTree string_literal219_tree=null;
		CommonTree WhiteChar220_tree=null;
		CommonTree WhiteChar221_tree=null;
		CommonTree DAYS222_tree=null;
		CommonTree string_literal223_tree=null;
		CommonTree WhiteChar224_tree=null;
		CommonTree WhiteChar225_tree=null;
		CommonTree string_literal226_tree=null;
		CommonTree WhiteChar227_tree=null;
		CommonTree WhiteChar228_tree=null;
		CommonTree DAYS229_tree=null;
		CommonTree WhiteChar230_tree=null;
		CommonTree string_literal231_tree=null;
		CommonTree WhiteChar232_tree=null;
		CommonTree WhiteChar233_tree=null;
		CommonTree DAYS234_tree=null;
		CommonTree WhiteChar235_tree=null;
		CommonTree string_literal236_tree=null;
		CommonTree WhiteChar237_tree=null;
		CommonTree PERCENT238_tree=null;
		CommonTree string_literal239_tree=null;
		CommonTree WhiteChar240_tree=null;
		CommonTree WhiteChar241_tree=null;
		CommonTree string_literal242_tree=null;
		CommonTree WhiteChar243_tree=null;
		CommonTree WhiteChar244_tree=null;
		CommonTree DAYS245_tree=null;
		CommonTree WhiteChar246_tree=null;
		CommonTree string_literal247_tree=null;
		CommonTree WhiteChar248_tree=null;
		CommonTree WhiteChar249_tree=null;
		CommonTree DAYS250_tree=null;
		CommonTree WhiteChar251_tree=null;
		CommonTree string_literal252_tree=null;
		CommonTree WhiteChar253_tree=null;
		CommonTree PERCENT254_tree=null;
		CommonTree string_literal255_tree=null;
		CommonTree WhiteChar256_tree=null;
		CommonTree WhiteChar257_tree=null;
		CommonTree string_literal258_tree=null;
		CommonTree WhiteChar259_tree=null;
		CommonTree WhiteChar260_tree=null;
		CommonTree DAYS261_tree=null;
		CommonTree WhiteChar262_tree=null;
		CommonTree string_literal263_tree=null;
		CommonTree WhiteChar264_tree=null;
		CommonTree WhiteChar265_tree=null;
		CommonTree DAYS266_tree=null;
		CommonTree WhiteChar267_tree=null;
		CommonTree string_literal268_tree=null;
		CommonTree WhiteChar269_tree=null;
		CommonTree PERCENT270_tree=null;
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
		RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
		RewriteRuleTokenStream stream_96=new RewriteRuleTokenStream(adaptor,"token 96");
		RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
		RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
		RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_trendconstant=new RewriteRuleSubtreeStream(adaptor,"rule trendconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:283:34: ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )? )
			int alt41=4;
			switch ( input.LA(1) ) {
			case 76:
				{
				alt41=1;
				}
				break;
			case 75:
				{
				alt41=2;
				}
				break;
			case 82:
				{
				alt41=3;
				}
				break;
			case 86:
				{
				alt41=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 41, 0, input);
				throw nvae;
			}
			switch (alt41) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:285:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:285:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:285:4: 'equals trend' WhiteChar trendSignal= trendconstant
					{
					string_literal211=(Token)match(input,76,FOLLOW_76_in_constantcmp2160);  
					stream_76.add(string_literal211);

					WhiteChar212=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2162);  
					stream_WhiteChar.add(WhiteChar212);

					pushFollow(FOLLOW_trendconstant_in_constantcmp2166);
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
					// 285:55: -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:285:58: ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualStringConstantCondition, "EqualStringConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_trendconstant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:285:103: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:285:130: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					int alt37=2;
					int LA37_0 = input.LA(1);
					if ( (LA37_0==WhiteChar) ) {
						int LA37_1 = input.LA(2);
						if ( (LA37_1==96) ) {
							alt37=1;
						}
					}
					switch (alt37) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar213=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2200);  
							stream_WhiteChar.add(WhiteChar213);

							string_literal214=(Token)match(input,96,FOLLOW_96_in_constantcmp2202);  
							stream_96.add(string_literal214);

							WhiteChar215=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2204);  
							stream_WhiteChar.add(WhiteChar215);

							pushFollow(FOLLOW_constant_in_constantcmp2208);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar216=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2210);  
							stream_WhiteChar.add(WhiteChar216);

							DAYS217=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2212);  
							stream_DAYS.add(DAYS217);

							WhiteChar218=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2214);  
							stream_WhiteChar.add(WhiteChar218);

							string_literal219=(Token)match(input,77,FOLLOW_77_in_constantcmp2216);  
							stream_77.add(string_literal219);

							WhiteChar220=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2218);  
							stream_WhiteChar.add(WhiteChar220);

							pushFollow(FOLLOW_constant_in_constantcmp2222);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar221=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2224);  
							stream_WhiteChar.add(WhiteChar221);

							DAYS222=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2226);  
							stream_DAYS.add(DAYS222);

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
							// 286:129: -> ^( EqualStringConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:132: ^( EqualStringConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:4: 'equals threshold' WhiteChar threshold= constant
					{
					string_literal223=(Token)match(input,75,FOLLOW_75_in_constantcmp2250);  
					stream_75.add(string_literal223);

					WhiteChar224=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2252);  
					stream_WhiteChar.add(WhiteChar224);

					pushFollow(FOLLOW_constant_in_constantcmp2256);
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
					// 288:52: -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:55: ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualConstantCondition, "EqualConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:143: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )?
					int alt38=2;
					int LA38_0 = input.LA(1);
					if ( (LA38_0==WhiteChar) ) {
						int LA38_1 = input.LA(2);
						if ( (LA38_1==96) ) {
							alt38=1;
						}
					}
					switch (alt38) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar225=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2297);  
							stream_WhiteChar.add(WhiteChar225);

							string_literal226=(Token)match(input,96,FOLLOW_96_in_constantcmp2299);  
							stream_96.add(string_literal226);

							WhiteChar227=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2301);  
							stream_WhiteChar.add(WhiteChar227);

							pushFollow(FOLLOW_constant_in_constantcmp2305);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar228=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2307);  
							stream_WhiteChar.add(WhiteChar228);

							DAYS229=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2309);  
							stream_DAYS.add(DAYS229);

							WhiteChar230=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2315);  
							stream_WhiteChar.add(WhiteChar230);

							string_literal231=(Token)match(input,77,FOLLOW_77_in_constantcmp2317);  
							stream_77.add(string_literal231);

							WhiteChar232=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2319);  
							stream_WhiteChar.add(WhiteChar232);

							pushFollow(FOLLOW_constant_in_constantcmp2323);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar233=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2325);  
							stream_WhiteChar.add(WhiteChar233);

							DAYS234=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2327);  
							stream_DAYS.add(DAYS234);

							WhiteChar235=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2333);  
							stream_WhiteChar.add(WhiteChar235);

							string_literal236=(Token)match(input,73,FOLLOW_73_in_constantcmp2335);  
							stream_73.add(string_literal236);

							WhiteChar237=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2337);  
							stream_WhiteChar.add(WhiteChar237);

							pushFollow(FOLLOW_constant_in_constantcmp2341);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT238=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2343);  
							stream_PERCENT.add(PERCENT238);

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
							// 292:5: -> ^( EqualConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:8: ^( EqualConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:4: 'is above threshold' WhiteChar threshold= constant
					{
					string_literal239=(Token)match(input,82,FOLLOW_82_in_constantcmp2373);  
					stream_82.add(string_literal239);

					WhiteChar240=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2375);  
					stream_WhiteChar.add(WhiteChar240);

					pushFollow(FOLLOW_constant_in_constantcmp2379);
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
					// 293:54: -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:57: ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupConstantCondition, "SupConstantCondition"), root_1);
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )?
					int alt39=2;
					int LA39_0 = input.LA(1);
					if ( (LA39_0==WhiteChar) ) {
						int LA39_1 = input.LA(2);
						if ( (LA39_1==96) ) {
							alt39=1;
						}
					}
					switch (alt39) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar241=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2420);  
							stream_WhiteChar.add(WhiteChar241);

							string_literal242=(Token)match(input,96,FOLLOW_96_in_constantcmp2422);  
							stream_96.add(string_literal242);

							WhiteChar243=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2424);  
							stream_WhiteChar.add(WhiteChar243);

							pushFollow(FOLLOW_constant_in_constantcmp2428);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar244=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2430);  
							stream_WhiteChar.add(WhiteChar244);

							DAYS245=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2432);  
							stream_DAYS.add(DAYS245);

							WhiteChar246=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2438);  
							stream_WhiteChar.add(WhiteChar246);

							string_literal247=(Token)match(input,77,FOLLOW_77_in_constantcmp2440);  
							stream_77.add(string_literal247);

							WhiteChar248=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2442);  
							stream_WhiteChar.add(WhiteChar248);

							pushFollow(FOLLOW_constant_in_constantcmp2446);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar249=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2448);  
							stream_WhiteChar.add(WhiteChar249);

							DAYS250=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2450);  
							stream_DAYS.add(DAYS250);

							WhiteChar251=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2456);  
							stream_WhiteChar.add(WhiteChar251);

							string_literal252=(Token)match(input,73,FOLLOW_73_in_constantcmp2458);  
							stream_73.add(string_literal252);

							WhiteChar253=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2460);  
							stream_WhiteChar.add(WhiteChar253);

							pushFollow(FOLLOW_constant_in_constantcmp2464);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT254=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2466);  
							stream_PERCENT.add(PERCENT254);

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
							// 297:5: -> ^( SupConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:8: ^( SupConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:4: 'is below threshold' WhiteChar threshold= constant
					{
					string_literal255=(Token)match(input,86,FOLLOW_86_in_constantcmp2496);  
					stream_86.add(string_literal255);

					WhiteChar256=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2498);  
					stream_WhiteChar.add(WhiteChar256);

					pushFollow(FOLLOW_constant_in_constantcmp2502);
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
					// 298:54: -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:57: ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfConstantCondition, "InfConstantCondition"), root_1);
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )?
					int alt40=2;
					int LA40_0 = input.LA(1);
					if ( (LA40_0==WhiteChar) ) {
						int LA40_1 = input.LA(2);
						if ( (LA40_1==96) ) {
							alt40=1;
						}
					}
					switch (alt40) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar257=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2543);  
							stream_WhiteChar.add(WhiteChar257);

							string_literal258=(Token)match(input,96,FOLLOW_96_in_constantcmp2545);  
							stream_96.add(string_literal258);

							WhiteChar259=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2547);  
							stream_WhiteChar.add(WhiteChar259);

							pushFollow(FOLLOW_constant_in_constantcmp2551);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar260=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2553);  
							stream_WhiteChar.add(WhiteChar260);

							DAYS261=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2555);  
							stream_DAYS.add(DAYS261);

							WhiteChar262=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2561);  
							stream_WhiteChar.add(WhiteChar262);

							string_literal263=(Token)match(input,77,FOLLOW_77_in_constantcmp2563);  
							stream_77.add(string_literal263);

							WhiteChar264=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2565);  
							stream_WhiteChar.add(WhiteChar264);

							pushFollow(FOLLOW_constant_in_constantcmp2569);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar265=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2571);  
							stream_WhiteChar.add(WhiteChar265);

							DAYS266=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2573);  
							stream_DAYS.add(DAYS266);

							WhiteChar267=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2579);  
							stream_WhiteChar.add(WhiteChar267);

							string_literal268=(Token)match(input,73,FOLLOW_73_in_constantcmp2581);  
							stream_73.add(string_literal268);

							WhiteChar269=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2583);  
							stream_WhiteChar.add(WhiteChar269);

							pushFollow(FOLLOW_constant_in_constantcmp2587);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT270=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2589);  
							stream_PERCENT.add(PERCENT270);

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
							// 302:5: -> ^( InfConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:8: ^( InfConstantCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:1: presetcondition[CommonTree firstOp] : ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) ) | ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) ) | ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) ) );
	public final ParameterizedIndicatorsParser.presetcondition_return presetcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.presetcondition_return retval = new ParameterizedIndicatorsParser.presetcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal271=null;
		Token WhiteChar272=null;
		Token string_literal273=null;
		Token WhiteChar274=null;
		Token PERCENT275=null;
		Token WhiteChar276=null;
		Token string_literal277=null;
		Token WhiteChar278=null;
		Token WhiteChar279=null;
		Token DAYS280=null;
		Token string_literal281=null;
		Token WhiteChar282=null;
		Token string_literal283=null;
		Token WhiteChar284=null;
		Token PERCENT285=null;
		Token WhiteChar286=null;
		Token string_literal287=null;
		Token WhiteChar288=null;
		Token WhiteChar289=null;
		Token DAYS290=null;
		Token string_literal291=null;
		Token WhiteChar292=null;
		Token PERCENT293=null;
		Token WhiteChar294=null;
		Token string_literal295=null;
		Token WhiteChar296=null;
		Token WhiteChar297=null;
		Token DAYS298=null;
		Token WhiteChar299=null;
		Token string_literal300=null;
		Token WhiteChar301=null;
		Token WhiteChar302=null;
		Token DAYS303=null;
		Token string_literal304=null;
		Token WhiteChar305=null;
		Token PERCENT306=null;
		Token WhiteChar307=null;
		Token string_literal308=null;
		Token WhiteChar309=null;
		Token WhiteChar310=null;
		Token DAYS311=null;
		Token WhiteChar312=null;
		Token string_literal313=null;
		Token WhiteChar314=null;
		Token WhiteChar315=null;
		Token DAYS316=null;
		Token string_literal317=null;
		Token WhiteChar318=null;
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
		Token PERCENT332=null;
		Token string_literal333=null;
		Token WhiteChar334=null;
		Token WhiteChar335=null;
		Token string_literal336=null;
		Token WhiteChar337=null;
		Token WhiteChar338=null;
		Token DAYS339=null;
		Token WhiteChar340=null;
		Token string_literal341=null;
		Token WhiteChar342=null;
		Token WhiteChar343=null;
		Token DAYS344=null;
		Token WhiteChar345=null;
		Token string_literal346=null;
		Token WhiteChar347=null;
		Token PERCENT348=null;
		Token string_literal349=null;
		Token WhiteChar350=null;
		Token WhiteChar351=null;
		Token DAYS352=null;
		Token WhiteChar353=null;
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
		Token string_literal372=null;
		Token WhiteChar373=null;
		Token WhiteChar374=null;
		Token string_literal375=null;
		Token WhiteChar376=null;
		Token char_literal377=null;
		Token char_literal378=null;
		Token char_literal379=null;
		Token WhiteChar380=null;
		Token string_literal381=null;
		Token WhiteChar382=null;
		Token char_literal383=null;
		Token char_literal384=null;
		Token char_literal385=null;
		Token WhiteChar386=null;
		Token string_literal387=null;
		Token WhiteChar388=null;
		Token char_literal389=null;
		Token char_literal390=null;
		Token char_literal391=null;
		Token string_literal392=null;
		Token WhiteChar393=null;
		Token WhiteChar394=null;
		Token DAYS395=null;
		Token WhiteChar396=null;
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
		Token string_literal415=null;
		Token WhiteChar416=null;
		Token WhiteChar417=null;
		Token string_literal418=null;
		Token WhiteChar419=null;
		Token char_literal420=null;
		Token char_literal421=null;
		Token char_literal422=null;
		Token WhiteChar423=null;
		Token string_literal424=null;
		Token WhiteChar425=null;
		Token char_literal426=null;
		Token char_literal427=null;
		Token char_literal428=null;
		Token WhiteChar429=null;
		Token string_literal430=null;
		Token WhiteChar431=null;
		Token char_literal432=null;
		Token char_literal433=null;
		Token char_literal434=null;
		Token string_literal435=null;
		Token WhiteChar436=null;
		Token WhiteChar437=null;
		Token DAYS438=null;
		Token WhiteChar439=null;
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
		Token string_literal458=null;
		Token WhiteChar459=null;
		Token WhiteChar460=null;
		Token string_literal461=null;
		Token WhiteChar462=null;
		Token char_literal463=null;
		Token char_literal464=null;
		Token char_literal465=null;
		Token WhiteChar466=null;
		Token string_literal467=null;
		Token WhiteChar468=null;
		Token char_literal469=null;
		Token char_literal470=null;
		Token char_literal471=null;
		Token WhiteChar472=null;
		Token string_literal473=null;
		Token WhiteChar474=null;
		Token char_literal475=null;
		Token char_literal476=null;
		Token char_literal477=null;
		Token string_literal478=null;
		Token WhiteChar479=null;
		Token WhiteChar480=null;
		Token DAYS481=null;
		Token WhiteChar482=null;
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
		Token string_literal501=null;
		Token WhiteChar502=null;
		Token WhiteChar503=null;
		Token string_literal504=null;
		Token WhiteChar505=null;
		Token char_literal506=null;
		Token char_literal507=null;
		Token char_literal508=null;
		Token WhiteChar509=null;
		Token string_literal510=null;
		Token WhiteChar511=null;
		Token char_literal512=null;
		Token char_literal513=null;
		Token char_literal514=null;
		Token WhiteChar515=null;
		Token string_literal516=null;
		Token WhiteChar517=null;
		Token char_literal518=null;
		Token char_literal519=null;
		Token char_literal520=null;
		Token string_literal521=null;
		Token WhiteChar522=null;
		Token WhiteChar523=null;
		Token DAYS524=null;
		Token WhiteChar525=null;
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
		Token char_literal543=null;
		Token char_literal544=null;
		Token char_literal545=null;
		Token WhiteChar546=null;
		Token string_literal547=null;
		Token WhiteChar548=null;
		Token string_literal549=null;
		Token WhiteChar550=null;
		Token WhiteChar551=null;
		Token DAYS552=null;
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
		Token char_literal571=null;
		Token char_literal572=null;
		Token char_literal573=null;
		Token WhiteChar574=null;
		Token string_literal575=null;
		Token WhiteChar576=null;
		Token string_literal577=null;
		Token WhiteChar578=null;
		Token string_literal579=null;
		Token WhiteChar580=null;
		Token WhiteChar581=null;
		Token DAYS582=null;
		Token WhiteChar583=null;
		Token string_literal584=null;
		Token WhiteChar585=null;
		Token WhiteChar586=null;
		Token DAYS587=null;
		Token WhiteChar588=null;
		Token string_literal589=null;
		Token WhiteChar590=null;
		Token string_literal591=null;
		Token WhiteChar592=null;
		Token string_literal593=null;
		Token WhiteChar594=null;
		Token WhiteChar595=null;
		Token DAYS596=null;
		Token WhiteChar597=null;
		Token string_literal598=null;
		Token WhiteChar599=null;
		Token WhiteChar600=null;
		Token DAYS601=null;
		Token WhiteChar602=null;
		Token string_literal603=null;
		Token WhiteChar604=null;
		Token string_literal605=null;
		Token WhiteChar606=null;
		Token string_literal607=null;
		Token WhiteChar608=null;
		Token WhiteChar609=null;
		Token DAYS610=null;
		Token WhiteChar611=null;
		Token string_literal612=null;
		Token WhiteChar613=null;
		Token WhiteChar614=null;
		Token DAYS615=null;
		Token WhiteChar616=null;
		Token string_literal617=null;
		Token WhiteChar618=null;
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

		CommonTree string_literal271_tree=null;
		CommonTree WhiteChar272_tree=null;
		CommonTree string_literal273_tree=null;
		CommonTree WhiteChar274_tree=null;
		CommonTree PERCENT275_tree=null;
		CommonTree WhiteChar276_tree=null;
		CommonTree string_literal277_tree=null;
		CommonTree WhiteChar278_tree=null;
		CommonTree WhiteChar279_tree=null;
		CommonTree DAYS280_tree=null;
		CommonTree string_literal281_tree=null;
		CommonTree WhiteChar282_tree=null;
		CommonTree string_literal283_tree=null;
		CommonTree WhiteChar284_tree=null;
		CommonTree PERCENT285_tree=null;
		CommonTree WhiteChar286_tree=null;
		CommonTree string_literal287_tree=null;
		CommonTree WhiteChar288_tree=null;
		CommonTree WhiteChar289_tree=null;
		CommonTree DAYS290_tree=null;
		CommonTree string_literal291_tree=null;
		CommonTree WhiteChar292_tree=null;
		CommonTree PERCENT293_tree=null;
		CommonTree WhiteChar294_tree=null;
		CommonTree string_literal295_tree=null;
		CommonTree WhiteChar296_tree=null;
		CommonTree WhiteChar297_tree=null;
		CommonTree DAYS298_tree=null;
		CommonTree WhiteChar299_tree=null;
		CommonTree string_literal300_tree=null;
		CommonTree WhiteChar301_tree=null;
		CommonTree WhiteChar302_tree=null;
		CommonTree DAYS303_tree=null;
		CommonTree string_literal304_tree=null;
		CommonTree WhiteChar305_tree=null;
		CommonTree PERCENT306_tree=null;
		CommonTree WhiteChar307_tree=null;
		CommonTree string_literal308_tree=null;
		CommonTree WhiteChar309_tree=null;
		CommonTree WhiteChar310_tree=null;
		CommonTree DAYS311_tree=null;
		CommonTree WhiteChar312_tree=null;
		CommonTree string_literal313_tree=null;
		CommonTree WhiteChar314_tree=null;
		CommonTree WhiteChar315_tree=null;
		CommonTree DAYS316_tree=null;
		CommonTree string_literal317_tree=null;
		CommonTree WhiteChar318_tree=null;
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
		CommonTree PERCENT332_tree=null;
		CommonTree string_literal333_tree=null;
		CommonTree WhiteChar334_tree=null;
		CommonTree WhiteChar335_tree=null;
		CommonTree string_literal336_tree=null;
		CommonTree WhiteChar337_tree=null;
		CommonTree WhiteChar338_tree=null;
		CommonTree DAYS339_tree=null;
		CommonTree WhiteChar340_tree=null;
		CommonTree string_literal341_tree=null;
		CommonTree WhiteChar342_tree=null;
		CommonTree WhiteChar343_tree=null;
		CommonTree DAYS344_tree=null;
		CommonTree WhiteChar345_tree=null;
		CommonTree string_literal346_tree=null;
		CommonTree WhiteChar347_tree=null;
		CommonTree PERCENT348_tree=null;
		CommonTree string_literal349_tree=null;
		CommonTree WhiteChar350_tree=null;
		CommonTree WhiteChar351_tree=null;
		CommonTree DAYS352_tree=null;
		CommonTree WhiteChar353_tree=null;
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
		CommonTree string_literal372_tree=null;
		CommonTree WhiteChar373_tree=null;
		CommonTree WhiteChar374_tree=null;
		CommonTree string_literal375_tree=null;
		CommonTree WhiteChar376_tree=null;
		CommonTree char_literal377_tree=null;
		CommonTree char_literal378_tree=null;
		CommonTree char_literal379_tree=null;
		CommonTree WhiteChar380_tree=null;
		CommonTree string_literal381_tree=null;
		CommonTree WhiteChar382_tree=null;
		CommonTree char_literal383_tree=null;
		CommonTree char_literal384_tree=null;
		CommonTree char_literal385_tree=null;
		CommonTree WhiteChar386_tree=null;
		CommonTree string_literal387_tree=null;
		CommonTree WhiteChar388_tree=null;
		CommonTree char_literal389_tree=null;
		CommonTree char_literal390_tree=null;
		CommonTree char_literal391_tree=null;
		CommonTree string_literal392_tree=null;
		CommonTree WhiteChar393_tree=null;
		CommonTree WhiteChar394_tree=null;
		CommonTree DAYS395_tree=null;
		CommonTree WhiteChar396_tree=null;
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
		CommonTree string_literal415_tree=null;
		CommonTree WhiteChar416_tree=null;
		CommonTree WhiteChar417_tree=null;
		CommonTree string_literal418_tree=null;
		CommonTree WhiteChar419_tree=null;
		CommonTree char_literal420_tree=null;
		CommonTree char_literal421_tree=null;
		CommonTree char_literal422_tree=null;
		CommonTree WhiteChar423_tree=null;
		CommonTree string_literal424_tree=null;
		CommonTree WhiteChar425_tree=null;
		CommonTree char_literal426_tree=null;
		CommonTree char_literal427_tree=null;
		CommonTree char_literal428_tree=null;
		CommonTree WhiteChar429_tree=null;
		CommonTree string_literal430_tree=null;
		CommonTree WhiteChar431_tree=null;
		CommonTree char_literal432_tree=null;
		CommonTree char_literal433_tree=null;
		CommonTree char_literal434_tree=null;
		CommonTree string_literal435_tree=null;
		CommonTree WhiteChar436_tree=null;
		CommonTree WhiteChar437_tree=null;
		CommonTree DAYS438_tree=null;
		CommonTree WhiteChar439_tree=null;
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
		CommonTree string_literal458_tree=null;
		CommonTree WhiteChar459_tree=null;
		CommonTree WhiteChar460_tree=null;
		CommonTree string_literal461_tree=null;
		CommonTree WhiteChar462_tree=null;
		CommonTree char_literal463_tree=null;
		CommonTree char_literal464_tree=null;
		CommonTree char_literal465_tree=null;
		CommonTree WhiteChar466_tree=null;
		CommonTree string_literal467_tree=null;
		CommonTree WhiteChar468_tree=null;
		CommonTree char_literal469_tree=null;
		CommonTree char_literal470_tree=null;
		CommonTree char_literal471_tree=null;
		CommonTree WhiteChar472_tree=null;
		CommonTree string_literal473_tree=null;
		CommonTree WhiteChar474_tree=null;
		CommonTree char_literal475_tree=null;
		CommonTree char_literal476_tree=null;
		CommonTree char_literal477_tree=null;
		CommonTree string_literal478_tree=null;
		CommonTree WhiteChar479_tree=null;
		CommonTree WhiteChar480_tree=null;
		CommonTree DAYS481_tree=null;
		CommonTree WhiteChar482_tree=null;
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
		CommonTree string_literal501_tree=null;
		CommonTree WhiteChar502_tree=null;
		CommonTree WhiteChar503_tree=null;
		CommonTree string_literal504_tree=null;
		CommonTree WhiteChar505_tree=null;
		CommonTree char_literal506_tree=null;
		CommonTree char_literal507_tree=null;
		CommonTree char_literal508_tree=null;
		CommonTree WhiteChar509_tree=null;
		CommonTree string_literal510_tree=null;
		CommonTree WhiteChar511_tree=null;
		CommonTree char_literal512_tree=null;
		CommonTree char_literal513_tree=null;
		CommonTree char_literal514_tree=null;
		CommonTree WhiteChar515_tree=null;
		CommonTree string_literal516_tree=null;
		CommonTree WhiteChar517_tree=null;
		CommonTree char_literal518_tree=null;
		CommonTree char_literal519_tree=null;
		CommonTree char_literal520_tree=null;
		CommonTree string_literal521_tree=null;
		CommonTree WhiteChar522_tree=null;
		CommonTree WhiteChar523_tree=null;
		CommonTree DAYS524_tree=null;
		CommonTree WhiteChar525_tree=null;
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
		CommonTree char_literal543_tree=null;
		CommonTree char_literal544_tree=null;
		CommonTree char_literal545_tree=null;
		CommonTree WhiteChar546_tree=null;
		CommonTree string_literal547_tree=null;
		CommonTree WhiteChar548_tree=null;
		CommonTree string_literal549_tree=null;
		CommonTree WhiteChar550_tree=null;
		CommonTree WhiteChar551_tree=null;
		CommonTree DAYS552_tree=null;
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
		CommonTree char_literal571_tree=null;
		CommonTree char_literal572_tree=null;
		CommonTree char_literal573_tree=null;
		CommonTree WhiteChar574_tree=null;
		CommonTree string_literal575_tree=null;
		CommonTree WhiteChar576_tree=null;
		CommonTree string_literal577_tree=null;
		CommonTree WhiteChar578_tree=null;
		CommonTree string_literal579_tree=null;
		CommonTree WhiteChar580_tree=null;
		CommonTree WhiteChar581_tree=null;
		CommonTree DAYS582_tree=null;
		CommonTree WhiteChar583_tree=null;
		CommonTree string_literal584_tree=null;
		CommonTree WhiteChar585_tree=null;
		CommonTree WhiteChar586_tree=null;
		CommonTree DAYS587_tree=null;
		CommonTree WhiteChar588_tree=null;
		CommonTree string_literal589_tree=null;
		CommonTree WhiteChar590_tree=null;
		CommonTree string_literal591_tree=null;
		CommonTree WhiteChar592_tree=null;
		CommonTree string_literal593_tree=null;
		CommonTree WhiteChar594_tree=null;
		CommonTree WhiteChar595_tree=null;
		CommonTree DAYS596_tree=null;
		CommonTree WhiteChar597_tree=null;
		CommonTree string_literal598_tree=null;
		CommonTree WhiteChar599_tree=null;
		CommonTree WhiteChar600_tree=null;
		CommonTree DAYS601_tree=null;
		CommonTree WhiteChar602_tree=null;
		CommonTree string_literal603_tree=null;
		CommonTree WhiteChar604_tree=null;
		CommonTree string_literal605_tree=null;
		CommonTree WhiteChar606_tree=null;
		CommonTree string_literal607_tree=null;
		CommonTree WhiteChar608_tree=null;
		CommonTree WhiteChar609_tree=null;
		CommonTree DAYS610_tree=null;
		CommonTree WhiteChar611_tree=null;
		CommonTree string_literal612_tree=null;
		CommonTree WhiteChar613_tree=null;
		CommonTree WhiteChar614_tree=null;
		CommonTree DAYS615_tree=null;
		CommonTree WhiteChar616_tree=null;
		CommonTree string_literal617_tree=null;
		CommonTree WhiteChar618_tree=null;
		RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");
		RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_110=new RewriteRuleTokenStream(adaptor,"token 110");
		RewriteRuleTokenStream stream_111=new RewriteRuleTokenStream(adaptor,"token 111");
		RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
		RewriteRuleTokenStream stream_91=new RewriteRuleTokenStream(adaptor,"token 91");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
		RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
		RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
		RewriteRuleTokenStream stream_94=new RewriteRuleTokenStream(adaptor,"token 94");
		RewriteRuleTokenStream stream_95=new RewriteRuleTokenStream(adaptor,"token 95");
		RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
		RewriteRuleTokenStream stream_CLOSESQRT=new RewriteRuleTokenStream(adaptor,"token CLOSESQRT");
		RewriteRuleTokenStream stream_96=new RewriteRuleTokenStream(adaptor,"token 96");
		RewriteRuleTokenStream stream_99=new RewriteRuleTokenStream(adaptor,"token 99");
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
		RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
		RewriteRuleTokenStream stream_100=new RewriteRuleTokenStream(adaptor,"token 100");
		RewriteRuleTokenStream stream_101=new RewriteRuleTokenStream(adaptor,"token 101");
		RewriteRuleTokenStream stream_102=new RewriteRuleTokenStream(adaptor,"token 102");
		RewriteRuleTokenStream stream_103=new RewriteRuleTokenStream(adaptor,"token 103");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_104=new RewriteRuleTokenStream(adaptor,"token 104");
		RewriteRuleTokenStream stream_OPENSQRT=new RewriteRuleTokenStream(adaptor,"token OPENSQRT");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_105=new RewriteRuleTokenStream(adaptor,"token 105");
		RewriteRuleTokenStream stream_106=new RewriteRuleTokenStream(adaptor,"token 106");
		RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
		RewriteRuleTokenStream stream_107=new RewriteRuleTokenStream(adaptor,"token 107");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_stringconstant=new RewriteRuleSubtreeStream(adaptor,"rule stringconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:38: ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) ) | ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) ) | ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) ) )
			int alt48=15;
			switch ( input.LA(1) ) {
			case 99:
				{
				alt48=1;
				}
				break;
			case 100:
				{
				alt48=2;
				}
				break;
			case 78:
				{
				alt48=3;
				}
				break;
			case 79:
				{
				alt48=4;
				}
				break;
			case 70:
				{
				alt48=5;
				}
				break;
			case 68:
				{
				alt48=6;
				}
				break;
			case 89:
				{
				alt48=7;
				}
				break;
			case 90:
				{
				alt48=8;
				}
				break;
			case 91:
				{
				alt48=9;
				}
				break;
			case 92:
				{
				alt48=10;
				}
				break;
			case 93:
				{
				alt48=11;
				}
				break;
			case 94:
				{
				alt48=12;
				}
				break;
			case 107:
				{
				alt48=13;
				}
				break;
			case 110:
				{
				alt48=14;
				}
				break;
			case 106:
				{
				alt48=15;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 48, 0, input);
				throw nvae;
			}
			switch (alt48) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:306:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:306:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:306:4: 'reverses down'
					{
					string_literal271=(Token)match(input,99,FOLLOW_99_in_presetcondition2626);  
					stream_99.add(string_literal271);

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
					// 306:20: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:306:23: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:306:42: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:306:70: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:306:99: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:307:7: ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					int alt42=2;
					int LA42_0 = input.LA(1);
					if ( (LA42_0==WhiteChar) ) {
						int LA42_1 = input.LA(2);
						if ( (LA42_1==95) ) {
							alt42=1;
						}
					}
					switch (alt42) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:307:9: WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar272=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2666);  
							stream_WhiteChar.add(WhiteChar272);

							string_literal273=(Token)match(input,95,FOLLOW_95_in_presetcondition2668);  
							stream_95.add(string_literal273);

							WhiteChar274=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2670);  
							stream_WhiteChar.add(WhiteChar274);

							pushFollow(FOLLOW_constant_in_presetcondition2674);
							percentdown=constant();
							state._fsp--;

							stream_constant.add(percentdown.getTree());
							PERCENT275=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2676);  
							stream_PERCENT.add(PERCENT275);

							WhiteChar276=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2678);  
							stream_WhiteChar.add(WhiteChar276);

							string_literal277=(Token)match(input,103,FOLLOW_103_in_presetcondition2680);  
							stream_103.add(string_literal277);

							WhiteChar278=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2682);  
							stream_WhiteChar.add(WhiteChar278);

							pushFollow(FOLLOW_constant_in_presetcondition2686);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar279=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2688);  
							stream_WhiteChar.add(WhiteChar279);

							DAYS280=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2690);  
							stream_DAYS.add(DAYS280);

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
							// 308:7: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:308:10: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:308:29: ^( Number NumberToken[\"-1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:4: 'reverses up'
					{
					string_literal281=(Token)match(input,100,FOLLOW_100_in_presetcondition2726);  
					stream_100.add(string_literal281);

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
					// 309:18: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:21: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:40: ^( Number NumberToken[\"1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:67: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:96: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:310:7: ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					int alt43=2;
					int LA43_0 = input.LA(1);
					if ( (LA43_0==WhiteChar) ) {
						int LA43_1 = input.LA(2);
						if ( (LA43_1==95) ) {
							alt43=1;
						}
					}
					switch (alt43) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:310:9: WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar282=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2766);  
							stream_WhiteChar.add(WhiteChar282);

							string_literal283=(Token)match(input,95,FOLLOW_95_in_presetcondition2768);  
							stream_95.add(string_literal283);

							WhiteChar284=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2770);  
							stream_WhiteChar.add(WhiteChar284);

							pushFollow(FOLLOW_constant_in_presetcondition2774);
							percentup=constant();
							state._fsp--;

							stream_constant.add(percentup.getTree());
							PERCENT285=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2776);  
							stream_PERCENT.add(PERCENT285);

							WhiteChar286=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2778);  
							stream_WhiteChar.add(WhiteChar286);

							string_literal287=(Token)match(input,103,FOLLOW_103_in_presetcondition2780);  
							stream_103.add(string_literal287);

							WhiteChar288=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2782);  
							stream_WhiteChar.add(WhiteChar288);

							pushFollow(FOLLOW_constant_in_presetcondition2786);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar289=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2788);  
							stream_WhiteChar.add(WhiteChar289);

							DAYS290=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2790);  
							stream_DAYS.add(DAYS290);

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
							// 311:7: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:311:10: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:311:29: ^( Number NumberToken[\"1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:4: 'goes down more than' WhiteChar percentdown= constant PERCENT
					{
					string_literal291=(Token)match(input,78,FOLLOW_78_in_presetcondition2827);  
					stream_78.add(string_literal291);

					WhiteChar292=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2829);  
					stream_WhiteChar.add(WhiteChar292);

					pushFollow(FOLLOW_constant_in_presetcondition2833);
					percentdown=constant();
					state._fsp--;

					stream_constant.add(percentdown.getTree());
					PERCENT293=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2835);  
					stream_PERCENT.add(PERCENT293);

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
					// 313:65: -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:68: ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:98: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:127: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:156: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:185: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt44=2;
					int LA44_0 = input.LA(1);
					if ( (LA44_0==WhiteChar) ) {
						int LA44_1 = input.LA(2);
						if ( (LA44_1==103) ) {
							alt44=1;
						}
					}
					switch (alt44) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar294=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2884);  
							stream_WhiteChar.add(WhiteChar294);

							string_literal295=(Token)match(input,103,FOLLOW_103_in_presetcondition2886);  
							stream_103.add(string_literal295);

							WhiteChar296=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2888);  
							stream_WhiteChar.add(WhiteChar296);

							pushFollow(FOLLOW_constant_in_presetcondition2892);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar297=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2894);  
							stream_WhiteChar.add(WhiteChar297);

							DAYS298=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2896);  
							stream_DAYS.add(DAYS298);

							WhiteChar299=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2907);  
							stream_WhiteChar.add(WhiteChar299);

							string_literal300=(Token)match(input,77,FOLLOW_77_in_presetcondition2909);  
							stream_77.add(string_literal300);

							WhiteChar301=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2911);  
							stream_WhiteChar.add(WhiteChar301);

							pushFollow(FOLLOW_constant_in_presetcondition2915);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar302=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2917);  
							stream_WhiteChar.add(WhiteChar302);

							DAYS303=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2919);  
							stream_DAYS.add(DAYS303);

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
							// 316:7: -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:316:10: ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentdown!=null?((CommonTree)percentdown.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:316:74: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:316:121: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:4: 'goes up more than' WhiteChar percentup= constant PERCENT
					{
					string_literal304=(Token)match(input,79,FOLLOW_79_in_presetcondition2963);  
					stream_79.add(string_literal304);

					WhiteChar305=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2965);  
					stream_WhiteChar.add(WhiteChar305);

					pushFollow(FOLLOW_constant_in_presetcondition2969);
					percentup=constant();
					state._fsp--;

					stream_constant.add(percentup.getTree());
					PERCENT306=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2971);  
					stream_PERCENT.add(PERCENT306);

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
					// 317:61: -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:64: ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:92: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:121: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:150: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:179: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt45=2;
					int LA45_0 = input.LA(1);
					if ( (LA45_0==WhiteChar) ) {
						int LA45_1 = input.LA(2);
						if ( (LA45_1==103) ) {
							alt45=1;
						}
					}
					switch (alt45) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar307=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3020);  
							stream_WhiteChar.add(WhiteChar307);

							string_literal308=(Token)match(input,103,FOLLOW_103_in_presetcondition3022);  
							stream_103.add(string_literal308);

							WhiteChar309=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3024);  
							stream_WhiteChar.add(WhiteChar309);

							pushFollow(FOLLOW_constant_in_presetcondition3028);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar310=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3030);  
							stream_WhiteChar.add(WhiteChar310);

							DAYS311=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3032);  
							stream_DAYS.add(DAYS311);

							WhiteChar312=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3043);  
							stream_WhiteChar.add(WhiteChar312);

							string_literal313=(Token)match(input,77,FOLLOW_77_in_presetcondition3045);  
							stream_77.add(string_literal313);

							WhiteChar314=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3047);  
							stream_WhiteChar.add(WhiteChar314);

							pushFollow(FOLLOW_constant_in_presetcondition3051);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar315=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3053);  
							stream_WhiteChar.add(WhiteChar315);

							DAYS316=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3055);  
							stream_DAYS.add(DAYS316);

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
							// 320:7: -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:320:10: ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentup!=null?((CommonTree)percentup.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:320:70: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:320:117: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:4: 'crosses up threshold' WhiteChar threshold= constant
					{
					string_literal317=(Token)match(input,70,FOLLOW_70_in_presetcondition3100);  
					stream_70.add(string_literal317);

					WhiteChar318=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3102);  
					stream_WhiteChar.add(WhiteChar318);

					pushFollow(FOLLOW_constant_in_presetcondition3106);
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
					// 322:56: -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:59: ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:95: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:124: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:153: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:182: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:323:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt46=2;
					int LA46_0 = input.LA(1);
					if ( (LA46_0==WhiteChar) ) {
						int LA46_1 = input.LA(2);
						if ( (LA46_1==103) ) {
							alt46=1;
						}
					}
					switch (alt46) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:323:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar319=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3155);  
							stream_WhiteChar.add(WhiteChar319);

							string_literal320=(Token)match(input,103,FOLLOW_103_in_presetcondition3157);  
							stream_103.add(string_literal320);

							WhiteChar321=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3159);  
							stream_WhiteChar.add(WhiteChar321);

							pushFollow(FOLLOW_constant_in_presetcondition3163);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar322=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3165);  
							stream_WhiteChar.add(WhiteChar322);

							DAYS323=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3167);  
							stream_DAYS.add(DAYS323);

							WhiteChar324=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3178);  
							stream_WhiteChar.add(WhiteChar324);

							string_literal325=(Token)match(input,96,FOLLOW_96_in_presetcondition3180);  
							stream_96.add(string_literal325);

							WhiteChar326=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3182);  
							stream_WhiteChar.add(WhiteChar326);

							pushFollow(FOLLOW_constant_in_presetcondition3186);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar327=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3188);  
							stream_WhiteChar.add(WhiteChar327);

							DAYS328=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3190);  
							stream_DAYS.add(DAYS328);

							WhiteChar329=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3200);  
							stream_WhiteChar.add(WhiteChar329);

							string_literal330=(Token)match(input,73,FOLLOW_73_in_presetcondition3202);  
							stream_73.add(string_literal330);

							WhiteChar331=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3204);  
							stream_WhiteChar.add(WhiteChar331);

							pushFollow(FOLLOW_constant_in_presetcondition3208);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT332=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition3210);  
							stream_PERCENT.add(PERCENT332);

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
							// 326:7: -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:326:10: ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:326:97: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:4: 'crosses down threshold' WhiteChar threshold= constant
					{
					string_literal333=(Token)match(input,68,FOLLOW_68_in_presetcondition3249);  
					stream_68.add(string_literal333);

					WhiteChar334=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3251);  
					stream_WhiteChar.add(WhiteChar334);

					pushFollow(FOLLOW_constant_in_presetcondition3255);
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
					// 327:58: -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:61: ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:99: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:128: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:157: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:186: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:328:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt47=2;
					int LA47_0 = input.LA(1);
					if ( (LA47_0==WhiteChar) ) {
						int LA47_1 = input.LA(2);
						if ( (LA47_1==103) ) {
							alt47=1;
						}
					}
					switch (alt47) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:328:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar335=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3304);  
							stream_WhiteChar.add(WhiteChar335);

							string_literal336=(Token)match(input,103,FOLLOW_103_in_presetcondition3306);  
							stream_103.add(string_literal336);

							WhiteChar337=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3308);  
							stream_WhiteChar.add(WhiteChar337);

							pushFollow(FOLLOW_constant_in_presetcondition3312);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar338=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3314);  
							stream_WhiteChar.add(WhiteChar338);

							DAYS339=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3316);  
							stream_DAYS.add(DAYS339);

							WhiteChar340=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3327);  
							stream_WhiteChar.add(WhiteChar340);

							string_literal341=(Token)match(input,96,FOLLOW_96_in_presetcondition3329);  
							stream_96.add(string_literal341);

							WhiteChar342=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3331);  
							stream_WhiteChar.add(WhiteChar342);

							pushFollow(FOLLOW_constant_in_presetcondition3335);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar343=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3337);  
							stream_WhiteChar.add(WhiteChar343);

							DAYS344=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3339);  
							stream_DAYS.add(DAYS344);

							WhiteChar345=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3349);  
							stream_WhiteChar.add(WhiteChar345);

							string_literal346=(Token)match(input,73,FOLLOW_73_in_presetcondition3351);  
							stream_73.add(string_literal346);

							WhiteChar347=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3353);  
							stream_WhiteChar.add(WhiteChar347);

							pushFollow(FOLLOW_constant_in_presetcondition3357);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT348=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition3359);  
							stream_PERCENT.add(PERCENT348);

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
							// 331:7: -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:331:10: ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:331:99: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:333:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:333:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:333:4: 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal349=(Token)match(input,89,FOLLOW_89_in_presetcondition3399);  
					stream_89.add(string_literal349);

					WhiteChar350=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3401);  
					stream_WhiteChar.add(WhiteChar350);

					pushFollow(FOLLOW_constant_in_presetcondition3405);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar351=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3407);  
					stream_WhiteChar.add(WhiteChar351);

					DAYS352=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3409);  
					stream_DAYS.add(DAYS352);

					WhiteChar353=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3416);  
					stream_WhiteChar.add(WhiteChar353);

					string_literal354=(Token)match(input,96,FOLLOW_96_in_presetcondition3418);  
					stream_96.add(string_literal354);

					WhiteChar355=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3420);  
					stream_WhiteChar.add(WhiteChar355);

					pushFollow(FOLLOW_constant_in_presetcondition3424);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar356=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3426);  
					stream_WhiteChar.add(WhiteChar356);

					DAYS357=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3428);  
					stream_DAYS.add(DAYS357);

					WhiteChar358=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3435);  
					stream_WhiteChar.add(WhiteChar358);

					string_literal359=(Token)match(input,77,FOLLOW_77_in_presetcondition3437);  
					stream_77.add(string_literal359);

					WhiteChar360=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3439);  
					stream_WhiteChar.add(WhiteChar360);

					pushFollow(FOLLOW_constant_in_presetcondition3443);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar361=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3445);  
					stream_WhiteChar.add(WhiteChar361);

					DAYS362=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3447);  
					stream_DAYS.add(DAYS362);

					WhiteChar363=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3454);  
					stream_WhiteChar.add(WhiteChar363);

					string_literal364=(Token)match(input,102,FOLLOW_102_in_presetcondition3456);  
					stream_102.add(string_literal364);

					WhiteChar365=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3458);  
					stream_WhiteChar.add(WhiteChar365);

					pushFollow(FOLLOW_constant_in_presetcondition3462);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar366=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3464);  
					stream_WhiteChar.add(WhiteChar366);

					DAYS367=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3466);  
					stream_DAYS.add(DAYS367);

					WhiteChar368=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3473);  
					stream_WhiteChar.add(WhiteChar368);

					string_literal369=(Token)match(input,80,FOLLOW_80_in_presetcondition3475);  
					stream_80.add(string_literal369);

					WhiteChar370=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3477);  
					stream_WhiteChar.add(WhiteChar370);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3481);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar371=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3487);  
					stream_WhiteChar.add(WhiteChar371);

					string_literal372=(Token)match(input,111,FOLLOW_111_in_presetcondition3489);  
					stream_111.add(string_literal372);

					WhiteChar373=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3491);  
					stream_WhiteChar.add(WhiteChar373);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3495);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar374=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3501);  
					stream_WhiteChar.add(WhiteChar374);

					string_literal375=(Token)match(input,104,FOLLOW_104_in_presetcondition3503);  
					stream_104.add(string_literal375);

					WhiteChar376=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3505);  
					stream_WhiteChar.add(WhiteChar376);

					char_literal377=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3507);  
					stream_OPENSQRT.add(char_literal377);

					pushFollow(FOLLOW_constant_in_presetcondition3511);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal378=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3513);  
					stream_COMMA.add(char_literal378);

					pushFollow(FOLLOW_constant_in_presetcondition3517);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal379=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3519);  
					stream_CLOSESQRT.add(char_literal379);

					WhiteChar380=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3521);  
					stream_WhiteChar.add(WhiteChar380);

					string_literal381=(Token)match(input,72,FOLLOW_72_in_presetcondition3523);  
					stream_72.add(string_literal381);

					WhiteChar382=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3525);  
					stream_WhiteChar.add(WhiteChar382);

					char_literal383=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3527);  
					stream_OPENSQRT.add(char_literal383);

					pushFollow(FOLLOW_constant_in_presetcondition3531);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal384=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3533);  
					stream_COMMA.add(char_literal384);

					pushFollow(FOLLOW_constant_in_presetcondition3537);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal385=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3539);  
					stream_CLOSESQRT.add(char_literal385);

					WhiteChar386=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3545);  
					stream_WhiteChar.add(WhiteChar386);

					string_literal387=(Token)match(input,101,FOLLOW_101_in_presetcondition3547);  
					stream_101.add(string_literal387);

					WhiteChar388=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3549);  
					stream_WhiteChar.add(WhiteChar388);

					char_literal389=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3551);  
					stream_OPENSQRT.add(char_literal389);

					pushFollow(FOLLOW_constant_in_presetcondition3555);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal390=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3557);  
					stream_COMMA.add(char_literal390);

					pushFollow(FOLLOW_constant_in_presetcondition3561);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal391=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3563);  
					stream_CLOSESQRT.add(char_literal391);

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
					// 341:4: -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:341:7: ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:341:246: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:342:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:342:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:342:4: 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal392=(Token)match(input,90,FOLLOW_90_in_presetcondition3614);  
					stream_90.add(string_literal392);

					WhiteChar393=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3616);  
					stream_WhiteChar.add(WhiteChar393);

					pushFollow(FOLLOW_constant_in_presetcondition3620);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar394=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3622);  
					stream_WhiteChar.add(WhiteChar394);

					DAYS395=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3624);  
					stream_DAYS.add(DAYS395);

					WhiteChar396=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3631);  
					stream_WhiteChar.add(WhiteChar396);

					string_literal397=(Token)match(input,96,FOLLOW_96_in_presetcondition3633);  
					stream_96.add(string_literal397);

					WhiteChar398=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3635);  
					stream_WhiteChar.add(WhiteChar398);

					pushFollow(FOLLOW_constant_in_presetcondition3639);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar399=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3641);  
					stream_WhiteChar.add(WhiteChar399);

					DAYS400=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3643);  
					stream_DAYS.add(DAYS400);

					WhiteChar401=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3650);  
					stream_WhiteChar.add(WhiteChar401);

					string_literal402=(Token)match(input,77,FOLLOW_77_in_presetcondition3652);  
					stream_77.add(string_literal402);

					WhiteChar403=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3654);  
					stream_WhiteChar.add(WhiteChar403);

					pushFollow(FOLLOW_constant_in_presetcondition3658);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar404=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3660);  
					stream_WhiteChar.add(WhiteChar404);

					DAYS405=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3662);  
					stream_DAYS.add(DAYS405);

					WhiteChar406=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3669);  
					stream_WhiteChar.add(WhiteChar406);

					string_literal407=(Token)match(input,102,FOLLOW_102_in_presetcondition3671);  
					stream_102.add(string_literal407);

					WhiteChar408=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3673);  
					stream_WhiteChar.add(WhiteChar408);

					pushFollow(FOLLOW_constant_in_presetcondition3677);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar409=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3679);  
					stream_WhiteChar.add(WhiteChar409);

					DAYS410=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3681);  
					stream_DAYS.add(DAYS410);

					WhiteChar411=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3688);  
					stream_WhiteChar.add(WhiteChar411);

					string_literal412=(Token)match(input,80,FOLLOW_80_in_presetcondition3690);  
					stream_80.add(string_literal412);

					WhiteChar413=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3692);  
					stream_WhiteChar.add(WhiteChar413);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3696);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar414=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3702);  
					stream_WhiteChar.add(WhiteChar414);

					string_literal415=(Token)match(input,111,FOLLOW_111_in_presetcondition3704);  
					stream_111.add(string_literal415);

					WhiteChar416=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3706);  
					stream_WhiteChar.add(WhiteChar416);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3710);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar417=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3716);  
					stream_WhiteChar.add(WhiteChar417);

					string_literal418=(Token)match(input,104,FOLLOW_104_in_presetcondition3718);  
					stream_104.add(string_literal418);

					WhiteChar419=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3720);  
					stream_WhiteChar.add(WhiteChar419);

					char_literal420=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3722);  
					stream_OPENSQRT.add(char_literal420);

					pushFollow(FOLLOW_constant_in_presetcondition3726);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal421=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3728);  
					stream_COMMA.add(char_literal421);

					pushFollow(FOLLOW_constant_in_presetcondition3732);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal422=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3734);  
					stream_CLOSESQRT.add(char_literal422);

					WhiteChar423=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3736);  
					stream_WhiteChar.add(WhiteChar423);

					string_literal424=(Token)match(input,72,FOLLOW_72_in_presetcondition3738);  
					stream_72.add(string_literal424);

					WhiteChar425=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3740);  
					stream_WhiteChar.add(WhiteChar425);

					char_literal426=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3742);  
					stream_OPENSQRT.add(char_literal426);

					pushFollow(FOLLOW_constant_in_presetcondition3746);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal427=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3748);  
					stream_COMMA.add(char_literal427);

					pushFollow(FOLLOW_constant_in_presetcondition3752);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal428=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3754);  
					stream_CLOSESQRT.add(char_literal428);

					WhiteChar429=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3760);  
					stream_WhiteChar.add(WhiteChar429);

					string_literal430=(Token)match(input,101,FOLLOW_101_in_presetcondition3762);  
					stream_101.add(string_literal430);

					WhiteChar431=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3764);  
					stream_WhiteChar.add(WhiteChar431);

					char_literal432=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3766);  
					stream_OPENSQRT.add(char_literal432);

					pushFollow(FOLLOW_constant_in_presetcondition3770);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal433=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3772);  
					stream_COMMA.add(char_literal433);

					pushFollow(FOLLOW_constant_in_presetcondition3776);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal434=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3778);  
					stream_CLOSESQRT.add(char_literal434);

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
					// 350:4: -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:350:7: ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:350:245: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:351:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:351:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:351:4: 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal435=(Token)match(input,91,FOLLOW_91_in_presetcondition3829);  
					stream_91.add(string_literal435);

					WhiteChar436=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3831);  
					stream_WhiteChar.add(WhiteChar436);

					pushFollow(FOLLOW_constant_in_presetcondition3835);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar437=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3837);  
					stream_WhiteChar.add(WhiteChar437);

					DAYS438=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3839);  
					stream_DAYS.add(DAYS438);

					WhiteChar439=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3846);  
					stream_WhiteChar.add(WhiteChar439);

					string_literal440=(Token)match(input,96,FOLLOW_96_in_presetcondition3848);  
					stream_96.add(string_literal440);

					WhiteChar441=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3850);  
					stream_WhiteChar.add(WhiteChar441);

					pushFollow(FOLLOW_constant_in_presetcondition3854);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar442=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3856);  
					stream_WhiteChar.add(WhiteChar442);

					DAYS443=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3858);  
					stream_DAYS.add(DAYS443);

					WhiteChar444=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3865);  
					stream_WhiteChar.add(WhiteChar444);

					string_literal445=(Token)match(input,77,FOLLOW_77_in_presetcondition3867);  
					stream_77.add(string_literal445);

					WhiteChar446=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3869);  
					stream_WhiteChar.add(WhiteChar446);

					pushFollow(FOLLOW_constant_in_presetcondition3873);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar447=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3875);  
					stream_WhiteChar.add(WhiteChar447);

					DAYS448=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3877);  
					stream_DAYS.add(DAYS448);

					WhiteChar449=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3884);  
					stream_WhiteChar.add(WhiteChar449);

					string_literal450=(Token)match(input,102,FOLLOW_102_in_presetcondition3886);  
					stream_102.add(string_literal450);

					WhiteChar451=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3888);  
					stream_WhiteChar.add(WhiteChar451);

					pushFollow(FOLLOW_constant_in_presetcondition3892);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar452=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3894);  
					stream_WhiteChar.add(WhiteChar452);

					DAYS453=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3896);  
					stream_DAYS.add(DAYS453);

					WhiteChar454=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3903);  
					stream_WhiteChar.add(WhiteChar454);

					string_literal455=(Token)match(input,80,FOLLOW_80_in_presetcondition3905);  
					stream_80.add(string_literal455);

					WhiteChar456=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3907);  
					stream_WhiteChar.add(WhiteChar456);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3911);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar457=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3917);  
					stream_WhiteChar.add(WhiteChar457);

					string_literal458=(Token)match(input,111,FOLLOW_111_in_presetcondition3919);  
					stream_111.add(string_literal458);

					WhiteChar459=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3921);  
					stream_WhiteChar.add(WhiteChar459);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3925);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar460=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3931);  
					stream_WhiteChar.add(WhiteChar460);

					string_literal461=(Token)match(input,104,FOLLOW_104_in_presetcondition3933);  
					stream_104.add(string_literal461);

					WhiteChar462=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3935);  
					stream_WhiteChar.add(WhiteChar462);

					char_literal463=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3937);  
					stream_OPENSQRT.add(char_literal463);

					pushFollow(FOLLOW_constant_in_presetcondition3941);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal464=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3943);  
					stream_COMMA.add(char_literal464);

					pushFollow(FOLLOW_constant_in_presetcondition3947);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal465=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3949);  
					stream_CLOSESQRT.add(char_literal465);

					WhiteChar466=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3951);  
					stream_WhiteChar.add(WhiteChar466);

					string_literal467=(Token)match(input,72,FOLLOW_72_in_presetcondition3953);  
					stream_72.add(string_literal467);

					WhiteChar468=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3955);  
					stream_WhiteChar.add(WhiteChar468);

					char_literal469=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3957);  
					stream_OPENSQRT.add(char_literal469);

					pushFollow(FOLLOW_constant_in_presetcondition3961);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal470=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3963);  
					stream_COMMA.add(char_literal470);

					pushFollow(FOLLOW_constant_in_presetcondition3967);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal471=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3969);  
					stream_CLOSESQRT.add(char_literal471);

					WhiteChar472=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3975);  
					stream_WhiteChar.add(WhiteChar472);

					string_literal473=(Token)match(input,101,FOLLOW_101_in_presetcondition3977);  
					stream_101.add(string_literal473);

					WhiteChar474=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3979);  
					stream_WhiteChar.add(WhiteChar474);

					char_literal475=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3981);  
					stream_OPENSQRT.add(char_literal475);

					pushFollow(FOLLOW_constant_in_presetcondition3985);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal476=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3987);  
					stream_COMMA.add(char_literal476);

					pushFollow(FOLLOW_constant_in_presetcondition3991);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal477=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3993);  
					stream_CLOSESQRT.add(char_literal477);

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
					// 359:4: -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:359:7: ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:359:245: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:360:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:360:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:360:4: 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal478=(Token)match(input,92,FOLLOW_92_in_presetcondition4044);  
					stream_92.add(string_literal478);

					WhiteChar479=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4046);  
					stream_WhiteChar.add(WhiteChar479);

					pushFollow(FOLLOW_constant_in_presetcondition4050);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar480=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4052);  
					stream_WhiteChar.add(WhiteChar480);

					DAYS481=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4054);  
					stream_DAYS.add(DAYS481);

					WhiteChar482=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4061);  
					stream_WhiteChar.add(WhiteChar482);

					string_literal483=(Token)match(input,96,FOLLOW_96_in_presetcondition4063);  
					stream_96.add(string_literal483);

					WhiteChar484=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4065);  
					stream_WhiteChar.add(WhiteChar484);

					pushFollow(FOLLOW_constant_in_presetcondition4069);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar485=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4071);  
					stream_WhiteChar.add(WhiteChar485);

					DAYS486=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4073);  
					stream_DAYS.add(DAYS486);

					WhiteChar487=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4080);  
					stream_WhiteChar.add(WhiteChar487);

					string_literal488=(Token)match(input,77,FOLLOW_77_in_presetcondition4082);  
					stream_77.add(string_literal488);

					WhiteChar489=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4084);  
					stream_WhiteChar.add(WhiteChar489);

					pushFollow(FOLLOW_constant_in_presetcondition4088);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar490=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4090);  
					stream_WhiteChar.add(WhiteChar490);

					DAYS491=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4092);  
					stream_DAYS.add(DAYS491);

					WhiteChar492=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4099);  
					stream_WhiteChar.add(WhiteChar492);

					string_literal493=(Token)match(input,102,FOLLOW_102_in_presetcondition4101);  
					stream_102.add(string_literal493);

					WhiteChar494=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4103);  
					stream_WhiteChar.add(WhiteChar494);

					pushFollow(FOLLOW_constant_in_presetcondition4107);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar495=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4109);  
					stream_WhiteChar.add(WhiteChar495);

					DAYS496=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4111);  
					stream_DAYS.add(DAYS496);

					WhiteChar497=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4118);  
					stream_WhiteChar.add(WhiteChar497);

					string_literal498=(Token)match(input,80,FOLLOW_80_in_presetcondition4120);  
					stream_80.add(string_literal498);

					WhiteChar499=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4122);  
					stream_WhiteChar.add(WhiteChar499);

					pushFollow(FOLLOW_stringconstant_in_presetcondition4126);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar500=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4132);  
					stream_WhiteChar.add(WhiteChar500);

					string_literal501=(Token)match(input,111,FOLLOW_111_in_presetcondition4134);  
					stream_111.add(string_literal501);

					WhiteChar502=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4136);  
					stream_WhiteChar.add(WhiteChar502);

					pushFollow(FOLLOW_stringconstant_in_presetcondition4140);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar503=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4146);  
					stream_WhiteChar.add(WhiteChar503);

					string_literal504=(Token)match(input,104,FOLLOW_104_in_presetcondition4148);  
					stream_104.add(string_literal504);

					WhiteChar505=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4150);  
					stream_WhiteChar.add(WhiteChar505);

					char_literal506=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4152);  
					stream_OPENSQRT.add(char_literal506);

					pushFollow(FOLLOW_constant_in_presetcondition4156);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal507=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4158);  
					stream_COMMA.add(char_literal507);

					pushFollow(FOLLOW_constant_in_presetcondition4162);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal508=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4164);  
					stream_CLOSESQRT.add(char_literal508);

					WhiteChar509=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4166);  
					stream_WhiteChar.add(WhiteChar509);

					string_literal510=(Token)match(input,72,FOLLOW_72_in_presetcondition4168);  
					stream_72.add(string_literal510);

					WhiteChar511=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4170);  
					stream_WhiteChar.add(WhiteChar511);

					char_literal512=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4172);  
					stream_OPENSQRT.add(char_literal512);

					pushFollow(FOLLOW_constant_in_presetcondition4176);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal513=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4178);  
					stream_COMMA.add(char_literal513);

					pushFollow(FOLLOW_constant_in_presetcondition4182);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal514=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4184);  
					stream_CLOSESQRT.add(char_literal514);

					WhiteChar515=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4190);  
					stream_WhiteChar.add(WhiteChar515);

					string_literal516=(Token)match(input,101,FOLLOW_101_in_presetcondition4192);  
					stream_101.add(string_literal516);

					WhiteChar517=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4194);  
					stream_WhiteChar.add(WhiteChar517);

					char_literal518=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4196);  
					stream_OPENSQRT.add(char_literal518);

					pushFollow(FOLLOW_constant_in_presetcondition4200);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal519=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4202);  
					stream_COMMA.add(char_literal519);

					pushFollow(FOLLOW_constant_in_presetcondition4206);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal520=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4208);  
					stream_CLOSESQRT.add(char_literal520);

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
					// 368:4: -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:368:7: ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:368:244: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:370:4: ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:370:4: ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:370:5: 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant
					{
					string_literal521=(Token)match(input,93,FOLLOW_93_in_presetcondition4261);  
					stream_93.add(string_literal521);

					WhiteChar522=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4263);  
					stream_WhiteChar.add(WhiteChar522);

					pushFollow(FOLLOW_constant_in_presetcondition4267);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar523=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4269);  
					stream_WhiteChar.add(WhiteChar523);

					DAYS524=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4271);  
					stream_DAYS.add(DAYS524);

					WhiteChar525=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4278);  
					stream_WhiteChar.add(WhiteChar525);

					string_literal526=(Token)match(input,96,FOLLOW_96_in_presetcondition4280);  
					stream_96.add(string_literal526);

					WhiteChar527=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4282);  
					stream_WhiteChar.add(WhiteChar527);

					pushFollow(FOLLOW_constant_in_presetcondition4286);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar528=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4288);  
					stream_WhiteChar.add(WhiteChar528);

					DAYS529=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4290);  
					stream_DAYS.add(DAYS529);

					WhiteChar530=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4297);  
					stream_WhiteChar.add(WhiteChar530);

					string_literal531=(Token)match(input,77,FOLLOW_77_in_presetcondition4299);  
					stream_77.add(string_literal531);

					WhiteChar532=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4301);  
					stream_WhiteChar.add(WhiteChar532);

					pushFollow(FOLLOW_constant_in_presetcondition4305);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar533=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4307);  
					stream_WhiteChar.add(WhiteChar533);

					DAYS534=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4309);  
					stream_DAYS.add(DAYS534);

					WhiteChar535=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4316);  
					stream_WhiteChar.add(WhiteChar535);

					string_literal536=(Token)match(input,102,FOLLOW_102_in_presetcondition4318);  
					stream_102.add(string_literal536);

					WhiteChar537=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4320);  
					stream_WhiteChar.add(WhiteChar537);

					pushFollow(FOLLOW_constant_in_presetcondition4324);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar538=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4326);  
					stream_WhiteChar.add(WhiteChar538);

					DAYS539=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4328);  
					stream_DAYS.add(DAYS539);

					WhiteChar540=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4335);  
					stream_WhiteChar.add(WhiteChar540);

					string_literal541=(Token)match(input,104,FOLLOW_104_in_presetcondition4337);  
					stream_104.add(string_literal541);

					WhiteChar542=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4339);  
					stream_WhiteChar.add(WhiteChar542);

					char_literal543=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4341);  
					stream_OPENSQRT.add(char_literal543);

					pushFollow(FOLLOW_constant_in_presetcondition4345);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal544=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4347);  
					stream_COMMA.add(char_literal544);

					pushFollow(FOLLOW_constant_in_presetcondition4351);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal545=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4353);  
					stream_CLOSESQRT.add(char_literal545);

					WhiteChar546=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4359);  
					stream_WhiteChar.add(WhiteChar546);

					string_literal547=(Token)match(input,105,FOLLOW_105_in_presetcondition4361);  
					stream_105.add(string_literal547);

					WhiteChar548=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4363);  
					stream_WhiteChar.add(WhiteChar548);

					pushFollow(FOLLOW_constant_in_presetcondition4367);
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
					// 376:4: -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:376:7: ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupportBreakDown, "SupportBreakDown"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:376:104: ^( String StringToken[\"\\\"greedy\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"greedy\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:376:140: ^( String StringToken[\"\\\"smooth\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"smooth\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:376:217: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:376:246: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:376:275: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:376:304: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:377:4: ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:377:4: ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:377:5: 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant
					{
					string_literal549=(Token)match(input,94,FOLLOW_94_in_presetcondition4444);  
					stream_94.add(string_literal549);

					WhiteChar550=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4446);  
					stream_WhiteChar.add(WhiteChar550);

					pushFollow(FOLLOW_constant_in_presetcondition4450);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar551=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4452);  
					stream_WhiteChar.add(WhiteChar551);

					DAYS552=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4454);  
					stream_DAYS.add(DAYS552);

					WhiteChar553=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4464);  
					stream_WhiteChar.add(WhiteChar553);

					string_literal554=(Token)match(input,96,FOLLOW_96_in_presetcondition4466);  
					stream_96.add(string_literal554);

					WhiteChar555=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4468);  
					stream_WhiteChar.add(WhiteChar555);

					pushFollow(FOLLOW_constant_in_presetcondition4472);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar556=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4474);  
					stream_WhiteChar.add(WhiteChar556);

					DAYS557=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4476);  
					stream_DAYS.add(DAYS557);

					WhiteChar558=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4486);  
					stream_WhiteChar.add(WhiteChar558);

					string_literal559=(Token)match(input,77,FOLLOW_77_in_presetcondition4488);  
					stream_77.add(string_literal559);

					WhiteChar560=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4490);  
					stream_WhiteChar.add(WhiteChar560);

					pushFollow(FOLLOW_constant_in_presetcondition4494);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar561=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4496);  
					stream_WhiteChar.add(WhiteChar561);

					DAYS562=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4498);  
					stream_DAYS.add(DAYS562);

					WhiteChar563=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4508);  
					stream_WhiteChar.add(WhiteChar563);

					string_literal564=(Token)match(input,102,FOLLOW_102_in_presetcondition4510);  
					stream_102.add(string_literal564);

					WhiteChar565=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4512);  
					stream_WhiteChar.add(WhiteChar565);

					pushFollow(FOLLOW_constant_in_presetcondition4516);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar566=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4518);  
					stream_WhiteChar.add(WhiteChar566);

					DAYS567=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4520);  
					stream_DAYS.add(DAYS567);

					WhiteChar568=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4530);  
					stream_WhiteChar.add(WhiteChar568);

					string_literal569=(Token)match(input,104,FOLLOW_104_in_presetcondition4532);  
					stream_104.add(string_literal569);

					WhiteChar570=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4534);  
					stream_WhiteChar.add(WhiteChar570);

					char_literal571=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4536);  
					stream_OPENSQRT.add(char_literal571);

					pushFollow(FOLLOW_constant_in_presetcondition4540);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal572=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4542);  
					stream_COMMA.add(char_literal572);

					pushFollow(FOLLOW_constant_in_presetcondition4546);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal573=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4548);  
					stream_CLOSESQRT.add(char_literal573);

					WhiteChar574=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4558);  
					stream_WhiteChar.add(WhiteChar574);

					string_literal575=(Token)match(input,105,FOLLOW_105_in_presetcondition4560);  
					stream_105.add(string_literal575);

					WhiteChar576=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4562);  
					stream_WhiteChar.add(WhiteChar576);

					pushFollow(FOLLOW_constant_in_presetcondition4566);
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
					// 383:6: -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:383:9: ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupportBreakUp, "SupportBreakUp"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:383:104: ^( String StringToken[\"\\\"greedy\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"greedy\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:383:140: ^( String StringToken[\"\\\"smooth\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"smooth\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:383:217: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:383:246: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:383:275: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:383:304: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:385:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:385:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:385:4: 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal577=(Token)match(input,107,FOLLOW_107_in_presetcondition4645);  
					stream_107.add(string_literal577);

					WhiteChar578=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4653);  
					stream_WhiteChar.add(WhiteChar578);

					string_literal579=(Token)match(input,96,FOLLOW_96_in_presetcondition4655);  
					stream_96.add(string_literal579);

					WhiteChar580=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4657);  
					stream_WhiteChar.add(WhiteChar580);

					pushFollow(FOLLOW_constant_in_presetcondition4661);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar581=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4663);  
					stream_WhiteChar.add(WhiteChar581);

					DAYS582=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4665);  
					stream_DAYS.add(DAYS582);

					WhiteChar583=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4673);  
					stream_WhiteChar.add(WhiteChar583);

					string_literal584=(Token)match(input,77,FOLLOW_77_in_presetcondition4675);  
					stream_77.add(string_literal584);

					WhiteChar585=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4677);  
					stream_WhiteChar.add(WhiteChar585);

					pushFollow(FOLLOW_constant_in_presetcondition4681);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar586=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4683);  
					stream_WhiteChar.add(WhiteChar586);

					DAYS587=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4685);  
					stream_DAYS.add(DAYS587);

					WhiteChar588=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4693);  
					stream_WhiteChar.add(WhiteChar588);

					string_literal589=(Token)match(input,73,FOLLOW_73_in_presetcondition4695);  
					stream_73.add(string_literal589);

					WhiteChar590=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4697);  
					stream_WhiteChar.add(WhiteChar590);

					pushFollow(FOLLOW_constant_in_presetcondition4701);
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
					// 389:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:389:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:389:79: ^( String StringToken[\"\\\"flat\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:390:3: ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:390:3: ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:390:4: 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal591=(Token)match(input,110,FOLLOW_110_in_presetcondition4736);  
					stream_110.add(string_literal591);

					WhiteChar592=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4744);  
					stream_WhiteChar.add(WhiteChar592);

					string_literal593=(Token)match(input,96,FOLLOW_96_in_presetcondition4746);  
					stream_96.add(string_literal593);

					WhiteChar594=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4748);  
					stream_WhiteChar.add(WhiteChar594);

					pushFollow(FOLLOW_constant_in_presetcondition4752);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar595=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4754);  
					stream_WhiteChar.add(WhiteChar595);

					DAYS596=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4756);  
					stream_DAYS.add(DAYS596);

					WhiteChar597=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4764);  
					stream_WhiteChar.add(WhiteChar597);

					string_literal598=(Token)match(input,77,FOLLOW_77_in_presetcondition4766);  
					stream_77.add(string_literal598);

					WhiteChar599=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4768);  
					stream_WhiteChar.add(WhiteChar599);

					pushFollow(FOLLOW_constant_in_presetcondition4772);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar600=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4774);  
					stream_WhiteChar.add(WhiteChar600);

					DAYS601=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4776);  
					stream_DAYS.add(DAYS601);

					WhiteChar602=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4784);  
					stream_WhiteChar.add(WhiteChar602);

					string_literal603=(Token)match(input,73,FOLLOW_73_in_presetcondition4786);  
					stream_73.add(string_literal603);

					WhiteChar604=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4788);  
					stream_WhiteChar.add(WhiteChar604);

					pushFollow(FOLLOW_constant_in_presetcondition4792);
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
					// 394:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:394:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:394:79: ^( String StringToken[\"\\\"up\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:395:3: ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:395:3: ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:395:4: 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal605=(Token)match(input,106,FOLLOW_106_in_presetcondition4827);  
					stream_106.add(string_literal605);

					WhiteChar606=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4835);  
					stream_WhiteChar.add(WhiteChar606);

					string_literal607=(Token)match(input,96,FOLLOW_96_in_presetcondition4837);  
					stream_96.add(string_literal607);

					WhiteChar608=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4839);  
					stream_WhiteChar.add(WhiteChar608);

					pushFollow(FOLLOW_constant_in_presetcondition4843);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar609=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4845);  
					stream_WhiteChar.add(WhiteChar609);

					DAYS610=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4847);  
					stream_DAYS.add(DAYS610);

					WhiteChar611=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4855);  
					stream_WhiteChar.add(WhiteChar611);

					string_literal612=(Token)match(input,77,FOLLOW_77_in_presetcondition4857);  
					stream_77.add(string_literal612);

					WhiteChar613=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4859);  
					stream_WhiteChar.add(WhiteChar613);

					pushFollow(FOLLOW_constant_in_presetcondition4863);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar614=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4865);  
					stream_WhiteChar.add(WhiteChar614);

					DAYS615=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4867);  
					stream_DAYS.add(DAYS615);

					WhiteChar616=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4875);  
					stream_WhiteChar.add(WhiteChar616);

					string_literal617=(Token)match(input,73,FOLLOW_73_in_presetcondition4877);  
					stream_73.add(string_literal617);

					WhiteChar618=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4879);  
					stream_WhiteChar.add(WhiteChar618);

					pushFollow(FOLLOW_constant_in_presetcondition4883);
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
					// 399:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:399:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:399:79: ^( String StringToken[\"\\\"down\\\"\"] )
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



	public static final BitSet FOLLOW_bullish_condition_in_complete_expression436 = new BitSet(new long[]{0x0000000000000000L,0x0000000000180000L});
	public static final BitSet FOLLOW_bearish_condition_in_complete_expression438 = new BitSet(new long[]{0x0000000000000000L,0x0000000600000001L});
	public static final BitSet FOLLOW_also_display_in_complete_expression441 = new BitSet(new long[]{0x0000000000000000L,0x0000000600000000L});
	public static final BitSet FOLLOW_fixed_start_shift_in_complete_expression443 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_na_event_list_name_in_complete_expression445 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_87_in_bullish_condition474 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition476 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bullish_condition478 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition480 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bullish_condition483 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition485 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_84_in_bearish_condition501 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition503 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_condition505 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition507 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bearish_condition510 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition512 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_bearish_not_bullish_in_bearish_condition522 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition525 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bearish_condition528 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition530 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_64_in_also_display547 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display549 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_also_display551 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display553 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_also_display556 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_98_in_fixed_start_shift591 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift593 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_fixed_start_shift597 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift599 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_fixed_start_shift601 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_fixed_start_shift603 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_97_in_na_event_list_name632 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_na_event_list_name634 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_na_event_list_name638 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_na_event_list_name640 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_83_in_bearish_not_bullish669 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish677 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_bearish_not_bullish679 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish681 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish683 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish716 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OR_in_bearish_not_bullish718 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish720 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish722 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_and_expression_in_primary_expression785 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_expression_in_and_expression797 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_lenient_in_and_expression801 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression804 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_and_expression806 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression808 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_or_expression_in_and_expression810 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_matches_expression_in_or_expression837 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression840 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OR_in_or_expression842 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression844 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_matches_expression_in_or_expression846 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_atom_in_matches_expression878 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression881 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_MATCHING_in_matches_expression883 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression885 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_matches_expression887 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_matches_expression889 = new BitSet(new long[]{0x0000000000000180L});
	public static final BitSet FOLLOW_COMMA_in_matches_expression892 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_matches_expression894 = new BitSet(new long[]{0x0000000000000180L});
	public static final BitSet FOLLOW_CLOSESQRT_in_matches_expression898 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression900 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_atom_in_matches_expression902 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_booleanhistory_in_atom930 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom936 = new BitSet(new long[]{0x2100048400400000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom938 = new BitSet(new long[]{0x2100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_atom941 = new BitSet(new long[]{0x2000000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom943 = new BitSet(new long[]{0x2000000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom946 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_atom956 = new BitSet(new long[]{0x2000008000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom958 = new BitSet(new long[]{0x2000008000000000L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom961 = new BitSet(new long[]{0x2100048400400000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom963 = new BitSet(new long[]{0x2100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_atom966 = new BitSet(new long[]{0x2000000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom968 = new BitSet(new long[]{0x2000000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom971 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_conjunctiontruthof_in_atom992 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUTHOF_in_conjunctiontruthof1004 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof1006 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_conjunctiontruthof1008 = new BitSet(new long[]{0x2000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_conjunctiontruthof1011 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof1013 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_conjunctiontruthof1015 = new BitSet(new long[]{0x2000000000000100L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof1019 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_88_in_conjunctiontruthof1021 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof1023 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_conjunctiontruthof1025 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_conjunctiontruthof1029 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_conjunctiontruthof1031 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_conjunctiontruthof1035 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_conjunctiontruthof1037 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_booleanhistory1076 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_booleanhistory1078 = new BitSet(new long[]{0x0000000000000000L,0x00007C187E66DC78L});
	public static final BitSet FOLLOW_presetcondition_in_booleanhistory1084 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_opcmpcondition_in_booleanhistory1095 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constantcmp_in_booleanhistory1106 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_operand1122 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Operation_in_operand1149 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NumberToken_in_constant1164 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_62_in_constant1176 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_StringToken_in_stringconstant1192 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_66_in_trendconstant1207 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_trendconstant1220 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_lenient1237 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_LENIENT_in_lenient1239 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_81_in_opcmpcondition1276 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1278 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1282 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1313 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_opcmpcondition1315 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1317 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1321 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1323 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1325 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1327 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_opcmpcondition1329 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1331 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1335 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1337 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_85_in_opcmpcondition1369 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1371 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1375 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1406 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_opcmpcondition1408 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1410 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1414 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1416 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1418 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1420 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_opcmpcondition1422 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1424 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1428 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1430 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_74_in_opcmpcondition1461 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1463 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1467 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1498 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_opcmpcondition1500 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1502 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1506 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1508 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1510 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1512 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_opcmpcondition1514 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1516 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1520 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1522 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_67_in_opcmpcondition1555 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1557 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1561 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1615 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_opcmpcondition1617 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1619 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1623 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1625 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1627 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1629 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_opcmpcondition1631 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1633 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1637 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1639 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1641 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1643 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_opcmpcondition1645 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1647 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1651 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1653 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1693 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_63_in_opcmpcondition1695 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1697 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1701 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1703 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1705 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_atom_in_opcmpcondition1709 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_opcmpcondition1752 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1754 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1758 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1812 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_opcmpcondition1814 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1816 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1820 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1822 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1824 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1826 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_opcmpcondition1828 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1830 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1834 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1836 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1838 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1840 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_opcmpcondition1842 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1844 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1848 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1850 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1890 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_63_in_opcmpcondition1892 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1894 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1898 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1900 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1902 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_atom_in_opcmpcondition1906 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_108_in_opcmpcondition1951 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1953 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1957 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1965 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_opcmpcondition1967 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1969 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1973 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1975 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1977 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1985 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_opcmpcondition1987 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1989 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1993 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1995 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1997 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2005 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_opcmpcondition2007 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2009 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition2013 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2021 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_opcmpcondition2023 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2025 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2029 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_109_in_opcmpcondition2061 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2063 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition2067 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2075 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_opcmpcondition2077 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2079 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2083 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2085 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition2087 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2095 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_opcmpcondition2097 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2099 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2103 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2105 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition2107 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2115 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_opcmpcondition2117 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2119 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition2123 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_76_in_constantcmp2160 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2162 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000006L});
	public static final BitSet FOLLOW_trendconstant_in_constantcmp2166 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2200 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_constantcmp2202 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2204 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2208 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2210 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2212 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2214 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_constantcmp2216 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2218 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2222 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2224 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2226 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_75_in_constantcmp2250 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2252 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2256 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2297 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_constantcmp2299 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2301 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2305 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2307 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2309 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2315 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_constantcmp2317 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2319 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2323 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2325 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2327 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2333 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_constantcmp2335 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2337 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2341 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2343 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_82_in_constantcmp2373 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2375 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2379 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2420 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_constantcmp2422 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2424 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2428 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2430 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2432 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2438 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_constantcmp2440 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2442 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2446 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2448 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2450 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2456 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_constantcmp2458 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2460 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2464 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2466 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_86_in_constantcmp2496 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2498 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2502 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2543 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_constantcmp2545 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2547 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2551 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2553 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2555 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2561 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_constantcmp2563 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2565 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2569 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2571 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2573 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2579 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_constantcmp2581 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2583 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2587 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2589 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_99_in_presetcondition2626 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2666 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition2668 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2670 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2674 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2676 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2678 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition2680 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2682 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2686 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2688 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2690 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_100_in_presetcondition2726 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2766 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition2768 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2770 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2774 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2776 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2778 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition2780 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2782 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2786 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2788 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2790 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_78_in_presetcondition2827 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2829 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2833 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2835 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2884 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition2886 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2888 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2892 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2894 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2896 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2907 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition2909 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2911 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2915 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2917 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2919 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_79_in_presetcondition2963 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2965 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2969 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2971 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3020 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition3022 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3024 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3028 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3030 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3032 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3043 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition3045 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3047 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3051 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3053 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3055 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_70_in_presetcondition3100 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3102 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3106 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3155 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition3157 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3159 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3163 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3165 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3167 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3178 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3180 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3182 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3186 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3188 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3190 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3200 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_presetcondition3202 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3204 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3208 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition3210 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_68_in_presetcondition3249 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3251 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3255 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3304 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition3306 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3308 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3312 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3314 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3316 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3327 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3329 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3331 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3335 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3337 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3339 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3349 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_presetcondition3351 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3353 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3357 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition3359 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_89_in_presetcondition3399 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3401 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3405 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3407 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3409 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3416 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3418 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3420 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3424 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3426 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3428 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3435 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition3437 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3439 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3443 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3445 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3447 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3454 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition3456 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3458 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3462 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3464 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3466 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3473 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition3475 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3477 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3481 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3487 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
	public static final BitSet FOLLOW_111_in_presetcondition3489 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3491 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3495 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3501 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition3503 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3505 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3507 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3511 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3513 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3517 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3519 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3521 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_presetcondition3523 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3525 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3527 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3531 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3533 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3537 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3539 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3545 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_presetcondition3547 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3549 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3551 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3555 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3557 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3561 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3563 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_90_in_presetcondition3614 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3616 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3620 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3622 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3624 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3631 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3633 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3635 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3639 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3641 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3643 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3650 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition3652 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3654 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3658 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3660 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3662 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3669 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition3671 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3673 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3677 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3679 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3681 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3688 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition3690 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3692 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3696 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3702 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
	public static final BitSet FOLLOW_111_in_presetcondition3704 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3706 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3710 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3716 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition3718 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3720 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3722 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3726 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3728 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3732 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3734 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3736 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_presetcondition3738 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3740 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3742 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3746 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3748 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3752 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3754 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3760 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_presetcondition3762 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3764 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3766 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3770 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3772 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3776 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3778 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_91_in_presetcondition3829 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3831 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3835 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3837 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3839 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3846 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3848 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3850 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3854 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3856 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3858 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3865 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition3867 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3869 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3873 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3875 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3877 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3884 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition3886 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3888 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3892 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3894 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3896 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3903 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition3905 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3907 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3911 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3917 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
	public static final BitSet FOLLOW_111_in_presetcondition3919 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3921 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3925 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3931 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition3933 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3935 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3937 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3941 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3943 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3947 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3949 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3951 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_presetcondition3953 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3955 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3957 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3961 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3963 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3967 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3969 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3975 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_presetcondition3977 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3979 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3981 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3985 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3987 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3991 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3993 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_92_in_presetcondition4044 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4046 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4050 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4052 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4054 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4061 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition4063 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4065 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4069 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4071 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4073 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4080 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition4082 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4084 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4088 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4090 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4092 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4099 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition4101 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4103 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4107 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4109 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4111 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4118 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition4120 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4122 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition4126 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4132 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
	public static final BitSet FOLLOW_111_in_presetcondition4134 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4136 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition4140 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4146 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition4148 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4150 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4152 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4156 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4158 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4162 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4164 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4166 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_presetcondition4168 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4170 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4172 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4176 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4178 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4182 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4184 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4190 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_presetcondition4192 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4194 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4196 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4200 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4202 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4206 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4208 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_93_in_presetcondition4261 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4263 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4267 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4269 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4271 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4278 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition4280 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4282 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4286 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4288 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4290 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4297 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition4299 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4301 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4305 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4307 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4309 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4316 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition4318 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4320 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4324 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4326 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4328 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4335 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition4337 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4339 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4341 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4345 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4347 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4351 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4353 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4359 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_presetcondition4361 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4363 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4367 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_94_in_presetcondition4444 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4446 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4450 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4452 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4454 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4464 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition4466 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4468 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4472 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4474 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4476 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4486 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition4488 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4490 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4494 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4496 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4498 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4508 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition4510 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4512 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4516 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4518 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4520 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4530 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition4532 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4534 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4536 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4540 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4542 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4546 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4548 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4558 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_presetcondition4560 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4562 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4566 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_107_in_presetcondition4645 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4653 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition4655 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4657 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4661 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4663 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4665 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4673 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition4675 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4677 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4681 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4683 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4685 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4693 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_presetcondition4695 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4697 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4701 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_110_in_presetcondition4736 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4744 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition4746 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4748 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4752 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4754 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4756 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4764 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition4766 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4768 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4772 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4774 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4776 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4784 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_presetcondition4786 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4788 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4792 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_106_in_presetcondition4827 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4835 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition4837 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4839 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4843 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4845 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4847 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4855 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition4857 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4859 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4863 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4865 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4867 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4875 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_presetcondition4877 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4879 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4883 = new BitSet(new long[]{0x0000000000000002L});
}
