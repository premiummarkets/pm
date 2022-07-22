// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2022-05-26 20:30:06
 //parser
    package com.finance.pms.events.calculation.parametrizedindicators.antlr;
    import org.antlr.runtime.BitSet;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.RewriteRuleSubtreeStream;
import org.antlr.runtime.tree.RewriteRuleTokenStream;
import org.antlr.runtime.tree.TreeAdaptor;

import com.finance.pms.events.calculation.antlr.IndsParserDelegate;
import com.finance.pms.events.calculation.antlr.InvalidOperationException;
import com.finance.pms.events.calculation.antlr.MyErrorReporter;
import com.finance.pms.events.calculation.antlr.UnfinishedNestedCondition;


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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:1: primary_expression : and_expression ;
	public final ParameterizedIndicatorsParser.primary_expression_return primary_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.primary_expression_return retval = new ParameterizedIndicatorsParser.primary_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope and_expression43 =null;


		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:20: ( and_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:3: and_expression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_and_expression_in_primary_expression798);
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:1: and_expression : or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndBooleanMapCondition or_expression ( or_expression )* ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:16: ( or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndBooleanMapCondition or_expression ( or_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:3: or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )*
			{
			pushFollow(FOLLOW_or_expression_in_and_expression810);
			or_expression44=or_expression();
			state._fsp--;

			stream_or_expression.add(or_expression44.getTree());
			pushFollow(FOLLOW_lenient_in_and_expression814);
			lenientParam=lenient();
			state._fsp--;

			stream_lenient.add(lenientParam.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:38: ( WhiteChar AND WhiteChar or_expression )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:39: WhiteChar AND WhiteChar or_expression
					{
					WhiteChar45=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression817);  
					stream_WhiteChar.add(WhiteChar45);

					AND46=(Token)match(input,AND,FOLLOW_AND_in_and_expression819);  
					stream_AND.add(AND46);

					WhiteChar47=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression821);  
					stream_WhiteChar.add(WhiteChar47);

					pushFollow(FOLLOW_or_expression_in_and_expression823);
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
			// 201:79: -> ^( AndBooleanMapCondition or_expression ( or_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:82: ^( AndBooleanMapCondition or_expression ( or_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndBooleanMapCondition, "AndBooleanMapCondition"), root_1);
				adaptor.addChild(root_1, (lenientParam!=null?((CommonTree)lenientParam.getTree()):null));
				adaptor.addChild(root_1, stream_or_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:142: ( or_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:1: or_expression : precondition_expression ( WhiteChar OR WhiteChar precondition_expression )* -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) precondition_expression ( precondition_expression )* ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:15: ( precondition_expression ( WhiteChar OR WhiteChar precondition_expression )* -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) precondition_expression ( precondition_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:3: precondition_expression ( WhiteChar OR WhiteChar precondition_expression )*
			{
			pushFollow(FOLLOW_precondition_expression_in_or_expression850);
			precondition_expression49=precondition_expression();
			state._fsp--;

			stream_precondition_expression.add(precondition_expression49.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:27: ( WhiteChar OR WhiteChar precondition_expression )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:28: WhiteChar OR WhiteChar precondition_expression
					{
					WhiteChar50=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression853);  
					stream_WhiteChar.add(WhiteChar50);

					OR51=(Token)match(input,OR,FOLLOW_OR_in_or_expression855);  
					stream_OR.add(OR51);

					WhiteChar52=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression857);  
					stream_WhiteChar.add(WhiteChar52);

					pushFollow(FOLLOW_precondition_expression_in_or_expression859);
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
			// 204:77: -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) precondition_expression ( precondition_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:80: ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) precondition_expression ( precondition_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrBooleanMapCondition, "OrBooleanMapCondition"), root_1);
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:104: ^( String StringToken[\"\\\"TRUE\\\"\"] )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
				adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, stream_precondition_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:162: ( precondition_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:1: precondition_expression : matches_expression ( WhiteChar WITH WhiteChar matches_expression )* -> ^( PreAndSignalCondition matches_expression ( matches_expression )* ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:25: ( matches_expression ( WhiteChar WITH WhiteChar matches_expression )* -> ^( PreAndSignalCondition matches_expression ( matches_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:3: matches_expression ( WhiteChar WITH WhiteChar matches_expression )*
			{
			pushFollow(FOLLOW_matches_expression_in_precondition_expression891);
			matches_expression54=matches_expression();
			state._fsp--;

			stream_matches_expression.add(matches_expression54.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:22: ( WhiteChar WITH WhiteChar matches_expression )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:23: WhiteChar WITH WhiteChar matches_expression
					{
					WhiteChar55=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_precondition_expression894);  
					stream_WhiteChar.add(WhiteChar55);

					WITH56=(Token)match(input,WITH,FOLLOW_WITH_in_precondition_expression896);  
					stream_WITH.add(WITH56);

					WhiteChar57=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_precondition_expression898);  
					stream_WhiteChar.add(WhiteChar57);

					pushFollow(FOLLOW_matches_expression_in_precondition_expression900);
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
			// 207:69: -> ^( PreAndSignalCondition matches_expression ( matches_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:72: ^( PreAndSignalCondition matches_expression ( matches_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(PreAndSignalCondition, "PreAndSignalCondition"), root_1);
				adaptor.addChild(root_1, stream_matches_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:115: ( matches_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:1: matches_expression : atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )? -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:20: ( atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )? -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:3: atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )?
			{
			pushFollow(FOLLOW_atom_in_matches_expression925);
			atom59=atom();
			state._fsp--;

			stream_atom.add(atom59.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:8: ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )?
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:9: WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom
					{
					WhiteChar60=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression928);  
					stream_WhiteChar.add(WhiteChar60);

					MATCHING61=(Token)match(input,MATCHING,FOLLOW_MATCHING_in_matches_expression930);  
					stream_MATCHING.add(MATCHING61);

					WhiteChar62=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression932);  
					stream_WhiteChar.add(WhiteChar62);

					char_literal63=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_matches_expression934);  
					stream_OPENSQRT.add(char_literal63);

					pushFollow(FOLLOW_constant_in_matches_expression936);
					constant64=constant();
					state._fsp--;

					stream_constant.add(constant64.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:51: ( ',' constant )*
					loop16:
					while (true) {
						int alt16=2;
						int LA16_0 = input.LA(1);
						if ( (LA16_0==COMMA) ) {
							alt16=1;
						}

						switch (alt16) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:52: ',' constant
							{
							char_literal65=(Token)match(input,COMMA,FOLLOW_COMMA_in_matches_expression939);  
							stream_COMMA.add(char_literal65);

							pushFollow(FOLLOW_constant_in_matches_expression941);
							constant66=constant();
							state._fsp--;

							stream_constant.add(constant66.getTree());
							}
							break;

						default :
							break loop16;
						}
					}

					char_literal67=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_matches_expression945);  
					stream_CLOSESQRT.add(char_literal67);

					WhiteChar68=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression947);  
					stream_WhiteChar.add(WhiteChar68);

					pushFollow(FOLLOW_atom_in_matches_expression949);
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
			// 210:88: -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:91: ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(MatchingBooleanMapCondition, "MatchingBooleanMapCondition"), root_1);
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:121: ( constant )*
				while ( stream_constant.hasNext() ) {
					adaptor.addChild(root_1, stream_constant.nextTree());
				}
				stream_constant.reset();

				adaptor.addChild(root_1, stream_atom.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:136: ( atom )?
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:1: atom : ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | NOT ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression ) | conjunctiontruthof );
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:6: ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | NOT ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression ) | conjunctiontruthof )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:3: booleanhistory
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_booleanhistory_in_atom977);
					booleanhistory70=booleanhistory();
					state._fsp--;

					adaptor.addChild(root_0, booleanhistory70.getTree());

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:3: '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					char_literal71=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom983);  
					stream_OPENPARENTEHSIS.add(char_literal71);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:7: ( WhiteChar )*
					loop18:
					while (true) {
						int alt18=2;
						int LA18_0 = input.LA(1);
						if ( (LA18_0==WhiteChar) ) {
							alt18=1;
						}

						switch (alt18) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:7: WhiteChar
							{
							WhiteChar72=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom985);  
							stream_WhiteChar.add(WhiteChar72);

							}
							break;

						default :
							break loop18;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom988);
					primary_expression73=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression73.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:37: ( WhiteChar )*
					loop19:
					while (true) {
						int alt19=2;
						int LA19_0 = input.LA(1);
						if ( (LA19_0==WhiteChar) ) {
							alt19=1;
						}

						switch (alt19) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:37: WhiteChar
							{
							WhiteChar74=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom990);  
							stream_WhiteChar.add(WhiteChar74);

							}
							break;

						default :
							break loop19;
						}
					}

					char_literal75=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom993);  
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
					// 214:52: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:3: NOT ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					NOT76=(Token)match(input,NOT,FOLLOW_NOT_in_atom1003);  
					stream_NOT.add(NOT76);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:7: ( WhiteChar )*
					loop20:
					while (true) {
						int alt20=2;
						int LA20_0 = input.LA(1);
						if ( (LA20_0==WhiteChar) ) {
							alt20=1;
						}

						switch (alt20) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:7: WhiteChar
							{
							WhiteChar77=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom1005);  
							stream_WhiteChar.add(WhiteChar77);

							}
							break;

						default :
							break loop20;
						}
					}

					char_literal78=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom1008);  
					stream_OPENPARENTEHSIS.add(char_literal78);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:22: ( WhiteChar )*
					loop21:
					while (true) {
						int alt21=2;
						int LA21_0 = input.LA(1);
						if ( (LA21_0==WhiteChar) ) {
							alt21=1;
						}

						switch (alt21) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:22: WhiteChar
							{
							WhiteChar79=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom1010);  
							stream_WhiteChar.add(WhiteChar79);

							}
							break;

						default :
							break loop21;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom1013);
					primary_expression80=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression80.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:52: ( WhiteChar )*
					loop22:
					while (true) {
						int alt22=2;
						int LA22_0 = input.LA(1);
						if ( (LA22_0==WhiteChar) ) {
							alt22=1;
						}

						switch (alt22) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:52: WhiteChar
							{
							WhiteChar81=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom1015);  
							stream_WhiteChar.add(WhiteChar81);

							}
							break;

						default :
							break loop22;
						}
					}

					char_literal82=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom1018);  
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
					// 215:67: -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:70: ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:95: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:3: conjunctiontruthof
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_conjunctiontruthof_in_atom1039);
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:1: conjunctiontruthof : TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']' -> ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:20: ( TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']' -> ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:3: TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']'
			{
			TRUTHOF84=(Token)match(input,TRUTHOF,FOLLOW_TRUTHOF_in_conjunctiontruthof1051);  
			stream_TRUTHOF.add(TRUTHOF84);

			WhiteChar85=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof1053);  
			stream_WhiteChar.add(WhiteChar85);

			pushFollow(FOLLOW_primary_expression_in_conjunctiontruthof1055);
			primary_expression86=primary_expression();
			state._fsp--;

			stream_primary_expression.add(primary_expression86.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:40: ( COMMA WhiteChar primary_expression )*
			loop24:
			while (true) {
				int alt24=2;
				int LA24_0 = input.LA(1);
				if ( (LA24_0==COMMA) ) {
					alt24=1;
				}

				switch (alt24) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:41: COMMA WhiteChar primary_expression
					{
					COMMA87=(Token)match(input,COMMA,FOLLOW_COMMA_in_conjunctiontruthof1058);  
					stream_COMMA.add(COMMA87);

					WhiteChar88=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof1060);  
					stream_WhiteChar.add(WhiteChar88);

					pushFollow(FOLLOW_primary_expression_in_conjunctiontruthof1062);
					primary_expression89=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression89.getTree());
					}
					break;

				default :
					break loop24;
				}
			}

			WhiteChar90=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof1066);  
			stream_WhiteChar.add(WhiteChar90);

			string_literal91=(Token)match(input,90,FOLLOW_90_in_conjunctiontruthof1068);  
			stream_90.add(string_literal91);

			WhiteChar92=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof1070);  
			stream_WhiteChar.add(WhiteChar92);

			char_literal93=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_conjunctiontruthof1072);  
			stream_OPENSQRT.add(char_literal93);

			pushFollow(FOLLOW_constant_in_conjunctiontruthof1076);
			min=constant();
			state._fsp--;

			stream_constant.add(min.getTree());
			char_literal94=(Token)match(input,COMMA,FOLLOW_COMMA_in_conjunctiontruthof1078);  
			stream_COMMA.add(char_literal94);

			pushFollow(FOLLOW_constant_in_conjunctiontruthof1082);
			max=constant();
			state._fsp--;

			stream_constant.add(max.getTree());
			char_literal95=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_conjunctiontruthof1084);  
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
			// 220:4: -> ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:7: ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TruthOfCondition, "TruthOfCondition"), root_1);
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:26: ^( String StringToken[\"\\\"TRUE\\\"\"] )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
				adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, (min!=null?((CommonTree)min.getTree()):null));
				adaptor.addChild(root_1, (max!=null?((CommonTree)max.getTree()):null));
				adaptor.addChild(root_1, stream_primary_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:103: ( primary_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:1: booleanhistory : firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:16: (firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:2: firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			{
			pushFollow(FOLLOW_operand_in_booleanhistory1123);
			firstOp=operand();
			state._fsp--;

			stream_operand.add(firstOp.getTree());
			WhiteChar96=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_booleanhistory1125);  
			stream_WhiteChar.add(WhiteChar96);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:28: ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:3: presetcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_presetcondition_in_booleanhistory1131);
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
					// 224:34: -> presetcondition
					{
						adaptor.addChild(root_0, stream_presetcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:3: opcmpcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_opcmpcondition_in_booleanhistory1142);
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
					// 225:33: -> opcmpcondition
					{
						adaptor.addChild(root_0, stream_opcmpcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:3: constantcmp[$firstOp.tree]
					{
					pushFollow(FOLLOW_constantcmp_in_booleanhistory1153);
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
					// 226:30: -> constantcmp
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:1: operand : ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation );
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:9: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:11: HistoricalData
					{
					HistoricalData100=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_operand1169);  
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
					// 229:26: -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:29: ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:46: ^( OperationOutput HistoricalData )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
						adaptor.addChild(root_2, stream_HistoricalData.nextNode());
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:80: ^( String StringToken[\"\\\"THIS\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:117: opName= Operation
					{
					opName=(Token)match(input,Operation,FOLLOW_Operation_in_operand1196);  
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
					// 229:171: -> Operation
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:1: constant : ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) );
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:10: ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:13: NumberToken
					{
					NumberToken101=(Token)match(input,NumberToken,FOLLOW_NumberToken_in_constant1211);  
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
					// 231:25: -> ^( Number NumberToken )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:28: ^( Number NumberToken )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:52: 'NaN'
					{
					string_literal102=(Token)match(input,64,FOLLOW_64_in_constant1223);  
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
					// 231:58: -> ^( Number NumberToken[\"NaN\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:61: ^( Number NumberToken[\"NaN\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:1: stringconstant : StringToken -> ^( String StringToken ) ;
	public final ParameterizedIndicatorsParser.stringconstant_return stringconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.stringconstant_return retval = new ParameterizedIndicatorsParser.stringconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token StringToken103=null;

		CommonTree StringToken103_tree=null;
		RewriteRuleTokenStream stream_StringToken=new RewriteRuleTokenStream(adaptor,"token StringToken");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:16: ( StringToken -> ^( String StringToken ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:18: StringToken
			{
			StringToken103=(Token)match(input,StringToken,FOLLOW_StringToken_in_stringconstant1239);  
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
			// 232:30: -> ^( String StringToken )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:33: ^( String StringToken )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:1: trendconstant : ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) );
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:15: ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:17: 'bullish'
					{
					string_literal104=(Token)match(input,68,FOLLOW_68_in_trendconstant1254);  
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
					// 233:27: -> ^( String StringToken[\"\\\"bullish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:30: ^( String StringToken[\"\\\"bullish\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:69: 'bearish'
					{
					string_literal105=(Token)match(input,67,FOLLOW_67_in_trendconstant1267);  
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
					// 233:79: -> ^( String StringToken[\"\\\"bearish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:82: ^( String StringToken[\"\\\"bearish\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:1: lenient : ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:9: ( ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:12: WhiteChar LENIENT
					{
					WhiteChar106=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_lenient1284);  
					stream_WhiteChar.add(WhiteChar106);

					LENIENT107=(Token)match(input,LENIENT,FOLLOW_LENIENT_in_lenient1286);  
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
					// 234:30: -> ^( String StringToken[\"\\\"TRUE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:33: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:69: 
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
					// 234:69: -> ^( String StringToken[\"\\\"FALSE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:72: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:1: opcmpcondition[CommonTree firstOp] : ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )? | ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) );
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:37: ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )? | ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:4: 'is above historical' WhiteChar secondOp= operand
					{
					string_literal108=(Token)match(input,83,FOLLOW_83_in_opcmpcondition1323);  
					stream_83.add(string_literal108);

					WhiteChar109=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1325);  
					stream_WhiteChar.add(WhiteChar109);

					pushFollow(FOLLOW_operand_in_opcmpcondition1329);
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
					// 239:53: -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:56: ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupDoubleMapCondition, "SupDoubleMapCondition"), root_1);
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:5: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar110=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1360);  
							stream_WhiteChar.add(WhiteChar110);

							string_literal111=(Token)match(input,79,FOLLOW_79_in_opcmpcondition1362);  
							stream_79.add(string_literal111);

							WhiteChar112=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1364);  
							stream_WhiteChar.add(WhiteChar112);

							pushFollow(FOLLOW_constant_in_opcmpcondition1368);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar113=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1370);  
							stream_WhiteChar.add(WhiteChar113);

							DAYS114=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1372);  
							stream_DAYS.add(DAYS114);

							WhiteChar115=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1374);  
							stream_WhiteChar.add(WhiteChar115);

							string_literal116=(Token)match(input,75,FOLLOW_75_in_opcmpcondition1376);  
							stream_75.add(string_literal116);

							WhiteChar117=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1378);  
							stream_WhiteChar.add(WhiteChar117);

							pushFollow(FOLLOW_constant_in_opcmpcondition1382);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT118=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1384);  
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
							// 241:5: -> ^( SupDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:8: ^( SupDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:4: 'is below historical' WhiteChar secondOp= operand
					{
					string_literal119=(Token)match(input,87,FOLLOW_87_in_opcmpcondition1416);  
					stream_87.add(string_literal119);

					WhiteChar120=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1418);  
					stream_WhiteChar.add(WhiteChar120);

					pushFollow(FOLLOW_operand_in_opcmpcondition1422);
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
					// 243:53: -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:56: ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfDoubleMapCondition, "InfDoubleMapCondition"), root_1);
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:5: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar121=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1453);  
							stream_WhiteChar.add(WhiteChar121);

							string_literal122=(Token)match(input,79,FOLLOW_79_in_opcmpcondition1455);  
							stream_79.add(string_literal122);

							WhiteChar123=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1457);  
							stream_WhiteChar.add(WhiteChar123);

							pushFollow(FOLLOW_constant_in_opcmpcondition1461);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar124=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1463);  
							stream_WhiteChar.add(WhiteChar124);

							DAYS125=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1465);  
							stream_DAYS.add(DAYS125);

							WhiteChar126=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1467);  
							stream_WhiteChar.add(WhiteChar126);

							string_literal127=(Token)match(input,75,FOLLOW_75_in_opcmpcondition1469);  
							stream_75.add(string_literal127);

							WhiteChar128=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1471);  
							stream_WhiteChar.add(WhiteChar128);

							pushFollow(FOLLOW_constant_in_opcmpcondition1475);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT129=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1477);  
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
							// 245:5: -> ^( InfDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:8: ^( InfDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:4: 'equals historical' WhiteChar secondOp= operand
					{
					string_literal130=(Token)match(input,76,FOLLOW_76_in_opcmpcondition1508);  
					stream_76.add(string_literal130);

					WhiteChar131=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1510);  
					stream_WhiteChar.add(WhiteChar131);

					pushFollow(FOLLOW_operand_in_opcmpcondition1514);
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
					// 247:51: -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:54: ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualDoubleMapCondition, "EqualDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:80: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:107: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:5: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar132=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1545);  
							stream_WhiteChar.add(WhiteChar132);

							string_literal133=(Token)match(input,79,FOLLOW_79_in_opcmpcondition1547);  
							stream_79.add(string_literal133);

							WhiteChar134=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1549);  
							stream_WhiteChar.add(WhiteChar134);

							pushFollow(FOLLOW_constant_in_opcmpcondition1553);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar135=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1555);  
							stream_WhiteChar.add(WhiteChar135);

							DAYS136=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1557);  
							stream_DAYS.add(DAYS136);

							WhiteChar137=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1559);  
							stream_WhiteChar.add(WhiteChar137);

							string_literal138=(Token)match(input,75,FOLLOW_75_in_opcmpcondition1561);  
							stream_75.add(string_literal138);

							WhiteChar139=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1563);  
							stream_WhiteChar.add(WhiteChar139);

							pushFollow(FOLLOW_constant_in_opcmpcondition1567);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT140=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1569);  
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
							// 249:5: -> ^( EqualDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:8: ^( EqualDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:3: ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:3: ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:4: 'crosses down historical' WhiteChar secondOp= operand
					{
					string_literal141=(Token)match(input,69,FOLLOW_69_in_opcmpcondition1602);  
					stream_69.add(string_literal141);

					WhiteChar142=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1604);  
					stream_WhiteChar.add(WhiteChar142);

					pushFollow(FOLLOW_operand_in_opcmpcondition1608);
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
					// 254:4: -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:7: ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:37: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:66: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:95: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:124: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:3: ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )?
							{
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:5: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar143=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1662);  
							stream_WhiteChar.add(WhiteChar143);

							string_literal144=(Token)match(input,105,FOLLOW_105_in_opcmpcondition1664);  
							stream_105.add(string_literal144);

							WhiteChar145=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1666);  
							stream_WhiteChar.add(WhiteChar145);

							pushFollow(FOLLOW_constant_in_opcmpcondition1670);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar146=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1672);  
							stream_WhiteChar.add(WhiteChar146);

							DAYS147=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1674);  
							stream_DAYS.add(DAYS147);

							WhiteChar148=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1676);  
							stream_WhiteChar.add(WhiteChar148);

							string_literal149=(Token)match(input,98,FOLLOW_98_in_opcmpcondition1678);  
							stream_98.add(string_literal149);

							WhiteChar150=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1680);  
							stream_WhiteChar.add(WhiteChar150);

							pushFollow(FOLLOW_constant_in_opcmpcondition1684);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar151=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1686);  
							stream_WhiteChar.add(WhiteChar151);

							DAYS152=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1688);  
							stream_DAYS.add(DAYS152);

							WhiteChar153=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1690);  
							stream_WhiteChar.add(WhiteChar153);

							string_literal154=(Token)match(input,75,FOLLOW_75_in_opcmpcondition1692);  
							stream_75.add(string_literal154);

							WhiteChar155=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1694);  
							stream_WhiteChar.add(WhiteChar155);

							pushFollow(FOLLOW_constant_in_opcmpcondition1698);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT156=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1700);  
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
							// 257:5: -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:8: ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:96: ^( Number NumberToken[\"0.0\"] )
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

							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:259:4: ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )?
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
									// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:259:5: WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom
									{
									WhiteChar157=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1740);  
									stream_WhiteChar.add(WhiteChar157);

									string_literal158=(Token)match(input,65,FOLLOW_65_in_opcmpcondition1742);  
									stream_65.add(string_literal158);

									WhiteChar159=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1744);  
									stream_WhiteChar.add(WhiteChar159);

									pushFollow(FOLLOW_constant_in_opcmpcondition1748);
									adaptiveRate=constant();
									state._fsp--;

									stream_constant.add(adaptiveRate.getTree());
									PERCENT160=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1750);  
									stream_PERCENT.add(PERCENT160);

									WhiteChar161=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1752);  
									stream_WhiteChar.add(WhiteChar161);

									pushFollow(FOLLOW_atom_in_opcmpcondition1756);
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
									// 260:5: -> ^( CrossDownDoubleMapCondition )
									{
										// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:8: ^( CrossDownDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:3: ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:3: ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:4: 'crosses up historical' WhiteChar secondOp= operand
					{
					string_literal162=(Token)match(input,71,FOLLOW_71_in_opcmpcondition1799);  
					stream_71.add(string_literal162);

					WhiteChar163=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1801);  
					stream_WhiteChar.add(WhiteChar163);

					pushFollow(FOLLOW_operand_in_opcmpcondition1805);
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
					// 264:4: -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:7: ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:35: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:64: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:93: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:122: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:3: ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )?
							{
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:5: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar164=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1859);  
							stream_WhiteChar.add(WhiteChar164);

							string_literal165=(Token)match(input,105,FOLLOW_105_in_opcmpcondition1861);  
							stream_105.add(string_literal165);

							WhiteChar166=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1863);  
							stream_WhiteChar.add(WhiteChar166);

							pushFollow(FOLLOW_constant_in_opcmpcondition1867);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar167=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1869);  
							stream_WhiteChar.add(WhiteChar167);

							DAYS168=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1871);  
							stream_DAYS.add(DAYS168);

							WhiteChar169=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1873);  
							stream_WhiteChar.add(WhiteChar169);

							string_literal170=(Token)match(input,98,FOLLOW_98_in_opcmpcondition1875);  
							stream_98.add(string_literal170);

							WhiteChar171=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1877);  
							stream_WhiteChar.add(WhiteChar171);

							pushFollow(FOLLOW_constant_in_opcmpcondition1881);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar172=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1883);  
							stream_WhiteChar.add(WhiteChar172);

							DAYS173=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1885);  
							stream_DAYS.add(DAYS173);

							WhiteChar174=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1887);  
							stream_WhiteChar.add(WhiteChar174);

							string_literal175=(Token)match(input,75,FOLLOW_75_in_opcmpcondition1889);  
							stream_75.add(string_literal175);

							WhiteChar176=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1891);  
							stream_WhiteChar.add(WhiteChar176);

							pushFollow(FOLLOW_constant_in_opcmpcondition1895);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT177=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1897);  
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
							// 267:5: -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:8: ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:94: ^( Number NumberToken[\"0.0\"] )
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

							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:269:4: ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )?
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
									// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:269:5: WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom
									{
									WhiteChar178=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1937);  
									stream_WhiteChar.add(WhiteChar178);

									string_literal179=(Token)match(input,65,FOLLOW_65_in_opcmpcondition1939);  
									stream_65.add(string_literal179);

									WhiteChar180=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1941);  
									stream_WhiteChar.add(WhiteChar180);

									pushFollow(FOLLOW_constant_in_opcmpcondition1945);
									adaptiveRate=constant();
									state._fsp--;

									stream_constant.add(adaptiveRate.getTree());
									PERCENT181=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1947);  
									stream_PERCENT.add(PERCENT181);

									WhiteChar182=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1949);  
									stream_WhiteChar.add(WhiteChar182);

									pushFollow(FOLLOW_atom_in_opcmpcondition1953);
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
									// 270:5: -> ^( CrossUpDoubleMapCondition )
									{
										// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:8: ^( CrossUpDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:4: 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal183=(Token)match(input,110,FOLLOW_110_in_opcmpcondition1998);  
					stream_110.add(string_literal183);

					WhiteChar184=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2000);  
					stream_WhiteChar.add(WhiteChar184);

					pushFollow(FOLLOW_operand_in_opcmpcondition2004);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar185=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2012);  
					stream_WhiteChar.add(WhiteChar185);

					string_literal186=(Token)match(input,98,FOLLOW_98_in_opcmpcondition2014);  
					stream_98.add(string_literal186);

					WhiteChar187=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2016);  
					stream_WhiteChar.add(WhiteChar187);

					pushFollow(FOLLOW_constant_in_opcmpcondition2020);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar188=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2022);  
					stream_WhiteChar.add(WhiteChar188);

					DAYS189=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition2024);  
					stream_DAYS.add(DAYS189);

					WhiteChar190=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2032);  
					stream_WhiteChar.add(WhiteChar190);

					string_literal191=(Token)match(input,79,FOLLOW_79_in_opcmpcondition2034);  
					stream_79.add(string_literal191);

					WhiteChar192=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2036);  
					stream_WhiteChar.add(WhiteChar192);

					pushFollow(FOLLOW_constant_in_opcmpcondition2040);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar193=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2042);  
					stream_WhiteChar.add(WhiteChar193);

					DAYS194=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition2044);  
					stream_DAYS.add(DAYS194);

					WhiteChar195=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2052);  
					stream_WhiteChar.add(WhiteChar195);

					string_literal196=(Token)match(input,73,FOLLOW_73_in_opcmpcondition2054);  
					stream_73.add(string_literal196);

					WhiteChar197=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2056);  
					stream_WhiteChar.add(WhiteChar197);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition2060);
					direction=stringconstant();
					state._fsp--;

					stream_stringconstant.add(direction.getTree());
					WhiteChar198=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2068);  
					stream_WhiteChar.add(WhiteChar198);

					string_literal199=(Token)match(input,75,FOLLOW_75_in_opcmpcondition2070);  
					stream_75.add(string_literal199);

					WhiteChar200=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2072);  
					stream_WhiteChar.add(WhiteChar200);

					pushFollow(FOLLOW_constant_in_opcmpcondition2076);
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
					// 280:7: -> ^( LinearSimilarTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:280:10: ^( LinearSimilarTrendsCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:4: 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant
					{
					string_literal201=(Token)match(input,111,FOLLOW_111_in_opcmpcondition2108);  
					stream_111.add(string_literal201);

					WhiteChar202=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2110);  
					stream_WhiteChar.add(WhiteChar202);

					pushFollow(FOLLOW_operand_in_opcmpcondition2114);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar203=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2122);  
					stream_WhiteChar.add(WhiteChar203);

					string_literal204=(Token)match(input,98,FOLLOW_98_in_opcmpcondition2124);  
					stream_98.add(string_literal204);

					WhiteChar205=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2126);  
					stream_WhiteChar.add(WhiteChar205);

					pushFollow(FOLLOW_constant_in_opcmpcondition2130);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar206=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2132);  
					stream_WhiteChar.add(WhiteChar206);

					DAYS207=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition2134);  
					stream_DAYS.add(DAYS207);

					WhiteChar208=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2142);  
					stream_WhiteChar.add(WhiteChar208);

					string_literal209=(Token)match(input,79,FOLLOW_79_in_opcmpcondition2144);  
					stream_79.add(string_literal209);

					WhiteChar210=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2146);  
					stream_WhiteChar.add(WhiteChar210);

					pushFollow(FOLLOW_constant_in_opcmpcondition2150);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar211=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2152);  
					stream_WhiteChar.add(WhiteChar211);

					DAYS212=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition2154);  
					stream_DAYS.add(DAYS212);

					WhiteChar213=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2162);  
					stream_WhiteChar.add(WhiteChar213);

					string_literal214=(Token)match(input,73,FOLLOW_73_in_opcmpcondition2164);  
					stream_73.add(string_literal214);

					WhiteChar215=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2166);  
					stream_WhiteChar.add(WhiteChar215);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition2170);
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
					// 285:7: -> ^( LinearOppositeTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:285:10: ^( LinearOppositeTrendsCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:1: constantcmp[CommonTree firstOp] : ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )? );
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:34: ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )? )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:4: 'equals trend' WhiteChar trendSignal= trendconstant
					{
					string_literal216=(Token)match(input,78,FOLLOW_78_in_constantcmp2207);  
					stream_78.add(string_literal216);

					WhiteChar217=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2209);  
					stream_WhiteChar.add(WhiteChar217);

					pushFollow(FOLLOW_trendconstant_in_constantcmp2213);
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
					// 289:55: -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:58: ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualStringConstantCondition, "EqualStringConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_trendconstant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:103: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:130: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar218=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2247);  
							stream_WhiteChar.add(WhiteChar218);

							string_literal219=(Token)match(input,98,FOLLOW_98_in_constantcmp2249);  
							stream_98.add(string_literal219);

							WhiteChar220=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2251);  
							stream_WhiteChar.add(WhiteChar220);

							pushFollow(FOLLOW_constant_in_constantcmp2255);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar221=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2257);  
							stream_WhiteChar.add(WhiteChar221);

							DAYS222=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2259);  
							stream_DAYS.add(DAYS222);

							WhiteChar223=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2261);  
							stream_WhiteChar.add(WhiteChar223);

							string_literal224=(Token)match(input,79,FOLLOW_79_in_constantcmp2263);  
							stream_79.add(string_literal224);

							WhiteChar225=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2265);  
							stream_WhiteChar.add(WhiteChar225);

							pushFollow(FOLLOW_constant_in_constantcmp2269);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar226=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2271);  
							stream_WhiteChar.add(WhiteChar226);

							DAYS227=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2273);  
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
							// 290:129: -> ^( EqualStringConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:132: ^( EqualStringConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:4: 'equals threshold' WhiteChar threshold= constant
					{
					string_literal228=(Token)match(input,77,FOLLOW_77_in_constantcmp2297);  
					stream_77.add(string_literal228);

					WhiteChar229=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2299);  
					stream_WhiteChar.add(WhiteChar229);

					pushFollow(FOLLOW_constant_in_constantcmp2303);
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
					// 292:52: -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:55: ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualConstantCondition, "EqualConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:143: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar230=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2344);  
							stream_WhiteChar.add(WhiteChar230);

							string_literal231=(Token)match(input,98,FOLLOW_98_in_constantcmp2346);  
							stream_98.add(string_literal231);

							WhiteChar232=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2348);  
							stream_WhiteChar.add(WhiteChar232);

							pushFollow(FOLLOW_constant_in_constantcmp2352);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar233=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2354);  
							stream_WhiteChar.add(WhiteChar233);

							DAYS234=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2356);  
							stream_DAYS.add(DAYS234);

							WhiteChar235=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2362);  
							stream_WhiteChar.add(WhiteChar235);

							string_literal236=(Token)match(input,79,FOLLOW_79_in_constantcmp2364);  
							stream_79.add(string_literal236);

							WhiteChar237=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2366);  
							stream_WhiteChar.add(WhiteChar237);

							pushFollow(FOLLOW_constant_in_constantcmp2370);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar238=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2372);  
							stream_WhiteChar.add(WhiteChar238);

							DAYS239=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2374);  
							stream_DAYS.add(DAYS239);

							WhiteChar240=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2380);  
							stream_WhiteChar.add(WhiteChar240);

							string_literal241=(Token)match(input,75,FOLLOW_75_in_constantcmp2382);  
							stream_75.add(string_literal241);

							WhiteChar242=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2384);  
							stream_WhiteChar.add(WhiteChar242);

							pushFollow(FOLLOW_constant_in_constantcmp2388);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT243=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2390);  
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
							// 296:5: -> ^( EqualConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:296:8: ^( EqualConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:4: 'is above threshold' WhiteChar threshold= constant
					{
					string_literal244=(Token)match(input,84,FOLLOW_84_in_constantcmp2420);  
					stream_84.add(string_literal244);

					WhiteChar245=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2422);  
					stream_WhiteChar.add(WhiteChar245);

					pushFollow(FOLLOW_constant_in_constantcmp2426);
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
					// 297:54: -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:57: ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupConstantCondition, "SupConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:143: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar246=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2467);  
							stream_WhiteChar.add(WhiteChar246);

							string_literal247=(Token)match(input,98,FOLLOW_98_in_constantcmp2469);  
							stream_98.add(string_literal247);

							WhiteChar248=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2471);  
							stream_WhiteChar.add(WhiteChar248);

							pushFollow(FOLLOW_constant_in_constantcmp2475);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar249=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2477);  
							stream_WhiteChar.add(WhiteChar249);

							DAYS250=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2479);  
							stream_DAYS.add(DAYS250);

							WhiteChar251=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2485);  
							stream_WhiteChar.add(WhiteChar251);

							string_literal252=(Token)match(input,79,FOLLOW_79_in_constantcmp2487);  
							stream_79.add(string_literal252);

							WhiteChar253=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2489);  
							stream_WhiteChar.add(WhiteChar253);

							pushFollow(FOLLOW_constant_in_constantcmp2493);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar254=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2495);  
							stream_WhiteChar.add(WhiteChar254);

							DAYS255=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2497);  
							stream_DAYS.add(DAYS255);

							WhiteChar256=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2503);  
							stream_WhiteChar.add(WhiteChar256);

							string_literal257=(Token)match(input,75,FOLLOW_75_in_constantcmp2505);  
							stream_75.add(string_literal257);

							WhiteChar258=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2507);  
							stream_WhiteChar.add(WhiteChar258);

							pushFollow(FOLLOW_constant_in_constantcmp2511);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT259=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2513);  
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
							// 301:5: -> ^( SupConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:301:8: ^( SupConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:4: 'is below threshold' WhiteChar threshold= constant
					{
					string_literal260=(Token)match(input,88,FOLLOW_88_in_constantcmp2543);  
					stream_88.add(string_literal260);

					WhiteChar261=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2545);  
					stream_WhiteChar.add(WhiteChar261);

					pushFollow(FOLLOW_constant_in_constantcmp2549);
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
					// 302:54: -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:57: ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfConstantCondition, "InfConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:143: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:303:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:303:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar262=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2590);  
							stream_WhiteChar.add(WhiteChar262);

							string_literal263=(Token)match(input,98,FOLLOW_98_in_constantcmp2592);  
							stream_98.add(string_literal263);

							WhiteChar264=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2594);  
							stream_WhiteChar.add(WhiteChar264);

							pushFollow(FOLLOW_constant_in_constantcmp2598);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar265=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2600);  
							stream_WhiteChar.add(WhiteChar265);

							DAYS266=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2602);  
							stream_DAYS.add(DAYS266);

							WhiteChar267=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2608);  
							stream_WhiteChar.add(WhiteChar267);

							string_literal268=(Token)match(input,79,FOLLOW_79_in_constantcmp2610);  
							stream_79.add(string_literal268);

							WhiteChar269=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2612);  
							stream_WhiteChar.add(WhiteChar269);

							pushFollow(FOLLOW_constant_in_constantcmp2616);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar270=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2618);  
							stream_WhiteChar.add(WhiteChar270);

							DAYS271=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2620);  
							stream_DAYS.add(DAYS271);

							WhiteChar272=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2626);  
							stream_WhiteChar.add(WhiteChar272);

							string_literal273=(Token)match(input,75,FOLLOW_75_in_constantcmp2628);  
							stream_75.add(string_literal273);

							WhiteChar274=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2630);  
							stream_WhiteChar.add(WhiteChar274);

							pushFollow(FOLLOW_constant_in_constantcmp2634);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT275=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2636);  
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
							// 306:5: -> ^( InfConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:306:8: ^( InfConstantCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:308:1: presetcondition[CommonTree firstOp] : ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) ) | ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) ) | ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) ) );
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:308:38: ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) ) | ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) ) | ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) ) )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:310:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:310:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:310:4: 'reverses down'
					{
					string_literal276=(Token)match(input,101,FOLLOW_101_in_presetcondition2673);  
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
					// 310:20: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:310:23: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:310:42: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:310:70: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:310:99: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:311:7: ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:311:9: WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar277=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2713);  
							stream_WhiteChar.add(WhiteChar277);

							string_literal278=(Token)match(input,97,FOLLOW_97_in_presetcondition2715);  
							stream_97.add(string_literal278);

							WhiteChar279=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2717);  
							stream_WhiteChar.add(WhiteChar279);

							pushFollow(FOLLOW_constant_in_presetcondition2721);
							percentdown=constant();
							state._fsp--;

							stream_constant.add(percentdown.getTree());
							PERCENT280=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2723);  
							stream_PERCENT.add(PERCENT280);

							WhiteChar281=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2725);  
							stream_WhiteChar.add(WhiteChar281);

							string_literal282=(Token)match(input,105,FOLLOW_105_in_presetcondition2727);  
							stream_105.add(string_literal282);

							WhiteChar283=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2729);  
							stream_WhiteChar.add(WhiteChar283);

							pushFollow(FOLLOW_constant_in_presetcondition2733);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar284=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2735);  
							stream_WhiteChar.add(WhiteChar284);

							DAYS285=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2737);  
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
							// 312:7: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:312:10: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:312:29: ^( Number NumberToken[\"-1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:4: 'reverses up'
					{
					string_literal286=(Token)match(input,102,FOLLOW_102_in_presetcondition2773);  
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
					// 313:18: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:21: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:40: ^( Number NumberToken[\"1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:67: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:96: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:7: ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:9: WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar287=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2813);  
							stream_WhiteChar.add(WhiteChar287);

							string_literal288=(Token)match(input,97,FOLLOW_97_in_presetcondition2815);  
							stream_97.add(string_literal288);

							WhiteChar289=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2817);  
							stream_WhiteChar.add(WhiteChar289);

							pushFollow(FOLLOW_constant_in_presetcondition2821);
							percentup=constant();
							state._fsp--;

							stream_constant.add(percentup.getTree());
							PERCENT290=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2823);  
							stream_PERCENT.add(PERCENT290);

							WhiteChar291=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2825);  
							stream_WhiteChar.add(WhiteChar291);

							string_literal292=(Token)match(input,105,FOLLOW_105_in_presetcondition2827);  
							stream_105.add(string_literal292);

							WhiteChar293=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2829);  
							stream_WhiteChar.add(WhiteChar293);

							pushFollow(FOLLOW_constant_in_presetcondition2833);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar294=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2835);  
							stream_WhiteChar.add(WhiteChar294);

							DAYS295=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2837);  
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
							// 315:7: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:315:10: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:315:29: ^( Number NumberToken[\"1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:4: 'goes down more than' WhiteChar percentdown= constant PERCENT
					{
					string_literal296=(Token)match(input,80,FOLLOW_80_in_presetcondition2874);  
					stream_80.add(string_literal296);

					WhiteChar297=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2876);  
					stream_WhiteChar.add(WhiteChar297);

					pushFollow(FOLLOW_constant_in_presetcondition2880);
					percentdown=constant();
					state._fsp--;

					stream_constant.add(percentdown.getTree());
					PERCENT298=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2882);  
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
					// 317:65: -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:68: ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:98: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:127: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:156: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:185: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar299=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2931);  
							stream_WhiteChar.add(WhiteChar299);

							string_literal300=(Token)match(input,105,FOLLOW_105_in_presetcondition2933);  
							stream_105.add(string_literal300);

							WhiteChar301=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2935);  
							stream_WhiteChar.add(WhiteChar301);

							pushFollow(FOLLOW_constant_in_presetcondition2939);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar302=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2941);  
							stream_WhiteChar.add(WhiteChar302);

							DAYS303=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2943);  
							stream_DAYS.add(DAYS303);

							WhiteChar304=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2954);  
							stream_WhiteChar.add(WhiteChar304);

							string_literal305=(Token)match(input,79,FOLLOW_79_in_presetcondition2956);  
							stream_79.add(string_literal305);

							WhiteChar306=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2958);  
							stream_WhiteChar.add(WhiteChar306);

							pushFollow(FOLLOW_constant_in_presetcondition2962);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar307=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2964);  
							stream_WhiteChar.add(WhiteChar307);

							DAYS308=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2966);  
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
							// 320:7: -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:320:10: ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentdown!=null?((CommonTree)percentdown.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:320:74: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:320:121: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:321:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:321:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:321:4: 'goes up more than' WhiteChar percentup= constant PERCENT
					{
					string_literal309=(Token)match(input,81,FOLLOW_81_in_presetcondition3010);  
					stream_81.add(string_literal309);

					WhiteChar310=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3012);  
					stream_WhiteChar.add(WhiteChar310);

					pushFollow(FOLLOW_constant_in_presetcondition3016);
					percentup=constant();
					state._fsp--;

					stream_constant.add(percentup.getTree());
					PERCENT311=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition3018);  
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
					// 321:61: -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:321:64: ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:321:92: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:321:121: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:321:150: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:321:179: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar312=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3067);  
							stream_WhiteChar.add(WhiteChar312);

							string_literal313=(Token)match(input,105,FOLLOW_105_in_presetcondition3069);  
							stream_105.add(string_literal313);

							WhiteChar314=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3071);  
							stream_WhiteChar.add(WhiteChar314);

							pushFollow(FOLLOW_constant_in_presetcondition3075);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar315=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3077);  
							stream_WhiteChar.add(WhiteChar315);

							DAYS316=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3079);  
							stream_DAYS.add(DAYS316);

							WhiteChar317=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3090);  
							stream_WhiteChar.add(WhiteChar317);

							string_literal318=(Token)match(input,79,FOLLOW_79_in_presetcondition3092);  
							stream_79.add(string_literal318);

							WhiteChar319=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3094);  
							stream_WhiteChar.add(WhiteChar319);

							pushFollow(FOLLOW_constant_in_presetcondition3098);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar320=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3100);  
							stream_WhiteChar.add(WhiteChar320);

							DAYS321=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3102);  
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
							// 324:7: -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:10: ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentup!=null?((CommonTree)percentup.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:70: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:117: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:326:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:326:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:326:4: 'crosses up threshold' WhiteChar threshold= constant
					{
					string_literal322=(Token)match(input,72,FOLLOW_72_in_presetcondition3147);  
					stream_72.add(string_literal322);

					WhiteChar323=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3149);  
					stream_WhiteChar.add(WhiteChar323);

					pushFollow(FOLLOW_constant_in_presetcondition3153);
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
					// 326:56: -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:326:59: ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:326:95: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:326:124: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:326:153: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:326:182: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar324=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3202);  
							stream_WhiteChar.add(WhiteChar324);

							string_literal325=(Token)match(input,105,FOLLOW_105_in_presetcondition3204);  
							stream_105.add(string_literal325);

							WhiteChar326=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3206);  
							stream_WhiteChar.add(WhiteChar326);

							pushFollow(FOLLOW_constant_in_presetcondition3210);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar327=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3212);  
							stream_WhiteChar.add(WhiteChar327);

							DAYS328=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3214);  
							stream_DAYS.add(DAYS328);

							WhiteChar329=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3225);  
							stream_WhiteChar.add(WhiteChar329);

							string_literal330=(Token)match(input,98,FOLLOW_98_in_presetcondition3227);  
							stream_98.add(string_literal330);

							WhiteChar331=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3229);  
							stream_WhiteChar.add(WhiteChar331);

							pushFollow(FOLLOW_constant_in_presetcondition3233);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar332=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3235);  
							stream_WhiteChar.add(WhiteChar332);

							DAYS333=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3237);  
							stream_DAYS.add(DAYS333);

							WhiteChar334=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3247);  
							stream_WhiteChar.add(WhiteChar334);

							string_literal335=(Token)match(input,75,FOLLOW_75_in_presetcondition3249);  
							stream_75.add(string_literal335);

							WhiteChar336=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3251);  
							stream_WhiteChar.add(WhiteChar336);

							pushFollow(FOLLOW_constant_in_presetcondition3255);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT337=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition3257);  
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
							// 330:7: -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:330:10: ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:330:97: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:331:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:331:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:331:4: 'crosses down threshold' WhiteChar threshold= constant
					{
					string_literal338=(Token)match(input,70,FOLLOW_70_in_presetcondition3296);  
					stream_70.add(string_literal338);

					WhiteChar339=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3298);  
					stream_WhiteChar.add(WhiteChar339);

					pushFollow(FOLLOW_constant_in_presetcondition3302);
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
					// 331:58: -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:331:61: ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:331:99: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:331:128: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:331:157: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:331:186: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:332:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:332:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar340=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3351);  
							stream_WhiteChar.add(WhiteChar340);

							string_literal341=(Token)match(input,105,FOLLOW_105_in_presetcondition3353);  
							stream_105.add(string_literal341);

							WhiteChar342=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3355);  
							stream_WhiteChar.add(WhiteChar342);

							pushFollow(FOLLOW_constant_in_presetcondition3359);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar343=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3361);  
							stream_WhiteChar.add(WhiteChar343);

							DAYS344=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3363);  
							stream_DAYS.add(DAYS344);

							WhiteChar345=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3374);  
							stream_WhiteChar.add(WhiteChar345);

							string_literal346=(Token)match(input,98,FOLLOW_98_in_presetcondition3376);  
							stream_98.add(string_literal346);

							WhiteChar347=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3378);  
							stream_WhiteChar.add(WhiteChar347);

							pushFollow(FOLLOW_constant_in_presetcondition3382);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar348=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3384);  
							stream_WhiteChar.add(WhiteChar348);

							DAYS349=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3386);  
							stream_DAYS.add(DAYS349);

							WhiteChar350=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3396);  
							stream_WhiteChar.add(WhiteChar350);

							string_literal351=(Token)match(input,75,FOLLOW_75_in_presetcondition3398);  
							stream_75.add(string_literal351);

							WhiteChar352=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3400);  
							stream_WhiteChar.add(WhiteChar352);

							pushFollow(FOLLOW_constant_in_presetcondition3404);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT353=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition3406);  
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
							// 335:7: -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:335:10: ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:335:99: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:337:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:337:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:337:4: 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal354=(Token)match(input,91,FOLLOW_91_in_presetcondition3446);  
					stream_91.add(string_literal354);

					WhiteChar355=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3448);  
					stream_WhiteChar.add(WhiteChar355);

					pushFollow(FOLLOW_constant_in_presetcondition3452);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar356=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3454);  
					stream_WhiteChar.add(WhiteChar356);

					DAYS357=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3456);  
					stream_DAYS.add(DAYS357);

					WhiteChar358=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3463);  
					stream_WhiteChar.add(WhiteChar358);

					string_literal359=(Token)match(input,98,FOLLOW_98_in_presetcondition3465);  
					stream_98.add(string_literal359);

					WhiteChar360=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3467);  
					stream_WhiteChar.add(WhiteChar360);

					pushFollow(FOLLOW_constant_in_presetcondition3471);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar361=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3473);  
					stream_WhiteChar.add(WhiteChar361);

					DAYS362=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3475);  
					stream_DAYS.add(DAYS362);

					WhiteChar363=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3482);  
					stream_WhiteChar.add(WhiteChar363);

					string_literal364=(Token)match(input,79,FOLLOW_79_in_presetcondition3484);  
					stream_79.add(string_literal364);

					WhiteChar365=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3486);  
					stream_WhiteChar.add(WhiteChar365);

					pushFollow(FOLLOW_constant_in_presetcondition3490);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar366=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3492);  
					stream_WhiteChar.add(WhiteChar366);

					DAYS367=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3494);  
					stream_DAYS.add(DAYS367);

					WhiteChar368=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3501);  
					stream_WhiteChar.add(WhiteChar368);

					string_literal369=(Token)match(input,104,FOLLOW_104_in_presetcondition3503);  
					stream_104.add(string_literal369);

					WhiteChar370=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3505);  
					stream_WhiteChar.add(WhiteChar370);

					pushFollow(FOLLOW_constant_in_presetcondition3509);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar371=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3511);  
					stream_WhiteChar.add(WhiteChar371);

					DAYS372=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3513);  
					stream_DAYS.add(DAYS372);

					WhiteChar373=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3520);  
					stream_WhiteChar.add(WhiteChar373);

					string_literal374=(Token)match(input,82,FOLLOW_82_in_presetcondition3522);  
					stream_82.add(string_literal374);

					WhiteChar375=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3524);  
					stream_WhiteChar.add(WhiteChar375);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3528);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar376=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3534);  
					stream_WhiteChar.add(WhiteChar376);

					string_literal377=(Token)match(input,113,FOLLOW_113_in_presetcondition3536);  
					stream_113.add(string_literal377);

					WhiteChar378=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3538);  
					stream_WhiteChar.add(WhiteChar378);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3542);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar379=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3548);  
					stream_WhiteChar.add(WhiteChar379);

					string_literal380=(Token)match(input,106,FOLLOW_106_in_presetcondition3550);  
					stream_106.add(string_literal380);

					WhiteChar381=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3552);  
					stream_WhiteChar.add(WhiteChar381);

					char_literal382=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3554);  
					stream_OPENSQRT.add(char_literal382);

					pushFollow(FOLLOW_constant_in_presetcondition3558);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal383=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3560);  
					stream_COMMA.add(char_literal383);

					pushFollow(FOLLOW_constant_in_presetcondition3564);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal384=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3566);  
					stream_CLOSESQRT.add(char_literal384);

					WhiteChar385=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3568);  
					stream_WhiteChar.add(WhiteChar385);

					string_literal386=(Token)match(input,74,FOLLOW_74_in_presetcondition3570);  
					stream_74.add(string_literal386);

					WhiteChar387=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3572);  
					stream_WhiteChar.add(WhiteChar387);

					char_literal388=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3574);  
					stream_OPENSQRT.add(char_literal388);

					pushFollow(FOLLOW_constant_in_presetcondition3578);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal389=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3580);  
					stream_COMMA.add(char_literal389);

					pushFollow(FOLLOW_constant_in_presetcondition3584);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal390=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3586);  
					stream_CLOSESQRT.add(char_literal390);

					WhiteChar391=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3592);  
					stream_WhiteChar.add(WhiteChar391);

					string_literal392=(Token)match(input,103,FOLLOW_103_in_presetcondition3594);  
					stream_103.add(string_literal392);

					WhiteChar393=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3596);  
					stream_WhiteChar.add(WhiteChar393);

					char_literal394=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3598);  
					stream_OPENSQRT.add(char_literal394);

					pushFollow(FOLLOW_constant_in_presetcondition3602);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal395=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3604);  
					stream_COMMA.add(char_literal395);

					pushFollow(FOLLOW_constant_in_presetcondition3608);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal396=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3610);  
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
					// 345:4: -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:345:7: ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:345:246: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:346:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:346:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:346:4: 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal397=(Token)match(input,92,FOLLOW_92_in_presetcondition3661);  
					stream_92.add(string_literal397);

					WhiteChar398=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3663);  
					stream_WhiteChar.add(WhiteChar398);

					pushFollow(FOLLOW_constant_in_presetcondition3667);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar399=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3669);  
					stream_WhiteChar.add(WhiteChar399);

					DAYS400=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3671);  
					stream_DAYS.add(DAYS400);

					WhiteChar401=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3678);  
					stream_WhiteChar.add(WhiteChar401);

					string_literal402=(Token)match(input,98,FOLLOW_98_in_presetcondition3680);  
					stream_98.add(string_literal402);

					WhiteChar403=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3682);  
					stream_WhiteChar.add(WhiteChar403);

					pushFollow(FOLLOW_constant_in_presetcondition3686);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar404=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3688);  
					stream_WhiteChar.add(WhiteChar404);

					DAYS405=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3690);  
					stream_DAYS.add(DAYS405);

					WhiteChar406=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3697);  
					stream_WhiteChar.add(WhiteChar406);

					string_literal407=(Token)match(input,79,FOLLOW_79_in_presetcondition3699);  
					stream_79.add(string_literal407);

					WhiteChar408=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3701);  
					stream_WhiteChar.add(WhiteChar408);

					pushFollow(FOLLOW_constant_in_presetcondition3705);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar409=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3707);  
					stream_WhiteChar.add(WhiteChar409);

					DAYS410=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3709);  
					stream_DAYS.add(DAYS410);

					WhiteChar411=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3716);  
					stream_WhiteChar.add(WhiteChar411);

					string_literal412=(Token)match(input,104,FOLLOW_104_in_presetcondition3718);  
					stream_104.add(string_literal412);

					WhiteChar413=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3720);  
					stream_WhiteChar.add(WhiteChar413);

					pushFollow(FOLLOW_constant_in_presetcondition3724);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar414=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3726);  
					stream_WhiteChar.add(WhiteChar414);

					DAYS415=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3728);  
					stream_DAYS.add(DAYS415);

					WhiteChar416=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3735);  
					stream_WhiteChar.add(WhiteChar416);

					string_literal417=(Token)match(input,82,FOLLOW_82_in_presetcondition3737);  
					stream_82.add(string_literal417);

					WhiteChar418=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3739);  
					stream_WhiteChar.add(WhiteChar418);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3743);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar419=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3749);  
					stream_WhiteChar.add(WhiteChar419);

					string_literal420=(Token)match(input,113,FOLLOW_113_in_presetcondition3751);  
					stream_113.add(string_literal420);

					WhiteChar421=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3753);  
					stream_WhiteChar.add(WhiteChar421);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3757);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar422=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3763);  
					stream_WhiteChar.add(WhiteChar422);

					string_literal423=(Token)match(input,106,FOLLOW_106_in_presetcondition3765);  
					stream_106.add(string_literal423);

					WhiteChar424=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3767);  
					stream_WhiteChar.add(WhiteChar424);

					char_literal425=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3769);  
					stream_OPENSQRT.add(char_literal425);

					pushFollow(FOLLOW_constant_in_presetcondition3773);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal426=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3775);  
					stream_COMMA.add(char_literal426);

					pushFollow(FOLLOW_constant_in_presetcondition3779);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal427=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3781);  
					stream_CLOSESQRT.add(char_literal427);

					WhiteChar428=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3783);  
					stream_WhiteChar.add(WhiteChar428);

					string_literal429=(Token)match(input,74,FOLLOW_74_in_presetcondition3785);  
					stream_74.add(string_literal429);

					WhiteChar430=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3787);  
					stream_WhiteChar.add(WhiteChar430);

					char_literal431=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3789);  
					stream_OPENSQRT.add(char_literal431);

					pushFollow(FOLLOW_constant_in_presetcondition3793);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal432=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3795);  
					stream_COMMA.add(char_literal432);

					pushFollow(FOLLOW_constant_in_presetcondition3799);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal433=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3801);  
					stream_CLOSESQRT.add(char_literal433);

					WhiteChar434=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3807);  
					stream_WhiteChar.add(WhiteChar434);

					string_literal435=(Token)match(input,103,FOLLOW_103_in_presetcondition3809);  
					stream_103.add(string_literal435);

					WhiteChar436=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3811);  
					stream_WhiteChar.add(WhiteChar436);

					char_literal437=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3813);  
					stream_OPENSQRT.add(char_literal437);

					pushFollow(FOLLOW_constant_in_presetcondition3817);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal438=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3819);  
					stream_COMMA.add(char_literal438);

					pushFollow(FOLLOW_constant_in_presetcondition3823);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal439=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3825);  
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
					// 354:4: -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:354:7: ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:354:245: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:355:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:355:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:355:4: 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal440=(Token)match(input,93,FOLLOW_93_in_presetcondition3876);  
					stream_93.add(string_literal440);

					WhiteChar441=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3878);  
					stream_WhiteChar.add(WhiteChar441);

					pushFollow(FOLLOW_constant_in_presetcondition3882);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar442=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3884);  
					stream_WhiteChar.add(WhiteChar442);

					DAYS443=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3886);  
					stream_DAYS.add(DAYS443);

					WhiteChar444=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3893);  
					stream_WhiteChar.add(WhiteChar444);

					string_literal445=(Token)match(input,98,FOLLOW_98_in_presetcondition3895);  
					stream_98.add(string_literal445);

					WhiteChar446=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3897);  
					stream_WhiteChar.add(WhiteChar446);

					pushFollow(FOLLOW_constant_in_presetcondition3901);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar447=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3903);  
					stream_WhiteChar.add(WhiteChar447);

					DAYS448=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3905);  
					stream_DAYS.add(DAYS448);

					WhiteChar449=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3912);  
					stream_WhiteChar.add(WhiteChar449);

					string_literal450=(Token)match(input,79,FOLLOW_79_in_presetcondition3914);  
					stream_79.add(string_literal450);

					WhiteChar451=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3916);  
					stream_WhiteChar.add(WhiteChar451);

					pushFollow(FOLLOW_constant_in_presetcondition3920);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar452=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3922);  
					stream_WhiteChar.add(WhiteChar452);

					DAYS453=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3924);  
					stream_DAYS.add(DAYS453);

					WhiteChar454=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3931);  
					stream_WhiteChar.add(WhiteChar454);

					string_literal455=(Token)match(input,104,FOLLOW_104_in_presetcondition3933);  
					stream_104.add(string_literal455);

					WhiteChar456=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3935);  
					stream_WhiteChar.add(WhiteChar456);

					pushFollow(FOLLOW_constant_in_presetcondition3939);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar457=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3941);  
					stream_WhiteChar.add(WhiteChar457);

					DAYS458=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3943);  
					stream_DAYS.add(DAYS458);

					WhiteChar459=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3950);  
					stream_WhiteChar.add(WhiteChar459);

					string_literal460=(Token)match(input,82,FOLLOW_82_in_presetcondition3952);  
					stream_82.add(string_literal460);

					WhiteChar461=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3954);  
					stream_WhiteChar.add(WhiteChar461);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3958);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar462=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3964);  
					stream_WhiteChar.add(WhiteChar462);

					string_literal463=(Token)match(input,113,FOLLOW_113_in_presetcondition3966);  
					stream_113.add(string_literal463);

					WhiteChar464=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3968);  
					stream_WhiteChar.add(WhiteChar464);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3972);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar465=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3978);  
					stream_WhiteChar.add(WhiteChar465);

					string_literal466=(Token)match(input,106,FOLLOW_106_in_presetcondition3980);  
					stream_106.add(string_literal466);

					WhiteChar467=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3982);  
					stream_WhiteChar.add(WhiteChar467);

					char_literal468=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3984);  
					stream_OPENSQRT.add(char_literal468);

					pushFollow(FOLLOW_constant_in_presetcondition3988);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal469=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3990);  
					stream_COMMA.add(char_literal469);

					pushFollow(FOLLOW_constant_in_presetcondition3994);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal470=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3996);  
					stream_CLOSESQRT.add(char_literal470);

					WhiteChar471=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3998);  
					stream_WhiteChar.add(WhiteChar471);

					string_literal472=(Token)match(input,74,FOLLOW_74_in_presetcondition4000);  
					stream_74.add(string_literal472);

					WhiteChar473=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4002);  
					stream_WhiteChar.add(WhiteChar473);

					char_literal474=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4004);  
					stream_OPENSQRT.add(char_literal474);

					pushFollow(FOLLOW_constant_in_presetcondition4008);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal475=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4010);  
					stream_COMMA.add(char_literal475);

					pushFollow(FOLLOW_constant_in_presetcondition4014);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal476=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4016);  
					stream_CLOSESQRT.add(char_literal476);

					WhiteChar477=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4022);  
					stream_WhiteChar.add(WhiteChar477);

					string_literal478=(Token)match(input,103,FOLLOW_103_in_presetcondition4024);  
					stream_103.add(string_literal478);

					WhiteChar479=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4026);  
					stream_WhiteChar.add(WhiteChar479);

					char_literal480=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4028);  
					stream_OPENSQRT.add(char_literal480);

					pushFollow(FOLLOW_constant_in_presetcondition4032);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal481=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4034);  
					stream_COMMA.add(char_literal481);

					pushFollow(FOLLOW_constant_in_presetcondition4038);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal482=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4040);  
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
					// 363:4: -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:363:7: ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:363:245: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:364:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:364:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:364:4: 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal483=(Token)match(input,94,FOLLOW_94_in_presetcondition4091);  
					stream_94.add(string_literal483);

					WhiteChar484=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4093);  
					stream_WhiteChar.add(WhiteChar484);

					pushFollow(FOLLOW_constant_in_presetcondition4097);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar485=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4099);  
					stream_WhiteChar.add(WhiteChar485);

					DAYS486=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4101);  
					stream_DAYS.add(DAYS486);

					WhiteChar487=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4108);  
					stream_WhiteChar.add(WhiteChar487);

					string_literal488=(Token)match(input,98,FOLLOW_98_in_presetcondition4110);  
					stream_98.add(string_literal488);

					WhiteChar489=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4112);  
					stream_WhiteChar.add(WhiteChar489);

					pushFollow(FOLLOW_constant_in_presetcondition4116);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar490=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4118);  
					stream_WhiteChar.add(WhiteChar490);

					DAYS491=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4120);  
					stream_DAYS.add(DAYS491);

					WhiteChar492=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4127);  
					stream_WhiteChar.add(WhiteChar492);

					string_literal493=(Token)match(input,79,FOLLOW_79_in_presetcondition4129);  
					stream_79.add(string_literal493);

					WhiteChar494=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4131);  
					stream_WhiteChar.add(WhiteChar494);

					pushFollow(FOLLOW_constant_in_presetcondition4135);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar495=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4137);  
					stream_WhiteChar.add(WhiteChar495);

					DAYS496=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4139);  
					stream_DAYS.add(DAYS496);

					WhiteChar497=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4146);  
					stream_WhiteChar.add(WhiteChar497);

					string_literal498=(Token)match(input,104,FOLLOW_104_in_presetcondition4148);  
					stream_104.add(string_literal498);

					WhiteChar499=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4150);  
					stream_WhiteChar.add(WhiteChar499);

					pushFollow(FOLLOW_constant_in_presetcondition4154);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar500=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4156);  
					stream_WhiteChar.add(WhiteChar500);

					DAYS501=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4158);  
					stream_DAYS.add(DAYS501);

					WhiteChar502=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4165);  
					stream_WhiteChar.add(WhiteChar502);

					string_literal503=(Token)match(input,82,FOLLOW_82_in_presetcondition4167);  
					stream_82.add(string_literal503);

					WhiteChar504=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4169);  
					stream_WhiteChar.add(WhiteChar504);

					pushFollow(FOLLOW_stringconstant_in_presetcondition4173);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar505=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4179);  
					stream_WhiteChar.add(WhiteChar505);

					string_literal506=(Token)match(input,113,FOLLOW_113_in_presetcondition4181);  
					stream_113.add(string_literal506);

					WhiteChar507=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4183);  
					stream_WhiteChar.add(WhiteChar507);

					pushFollow(FOLLOW_stringconstant_in_presetcondition4187);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar508=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4193);  
					stream_WhiteChar.add(WhiteChar508);

					string_literal509=(Token)match(input,106,FOLLOW_106_in_presetcondition4195);  
					stream_106.add(string_literal509);

					WhiteChar510=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4197);  
					stream_WhiteChar.add(WhiteChar510);

					char_literal511=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4199);  
					stream_OPENSQRT.add(char_literal511);

					pushFollow(FOLLOW_constant_in_presetcondition4203);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal512=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4205);  
					stream_COMMA.add(char_literal512);

					pushFollow(FOLLOW_constant_in_presetcondition4209);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal513=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4211);  
					stream_CLOSESQRT.add(char_literal513);

					WhiteChar514=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4213);  
					stream_WhiteChar.add(WhiteChar514);

					string_literal515=(Token)match(input,74,FOLLOW_74_in_presetcondition4215);  
					stream_74.add(string_literal515);

					WhiteChar516=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4217);  
					stream_WhiteChar.add(WhiteChar516);

					char_literal517=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4219);  
					stream_OPENSQRT.add(char_literal517);

					pushFollow(FOLLOW_constant_in_presetcondition4223);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal518=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4225);  
					stream_COMMA.add(char_literal518);

					pushFollow(FOLLOW_constant_in_presetcondition4229);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal519=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4231);  
					stream_CLOSESQRT.add(char_literal519);

					WhiteChar520=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4237);  
					stream_WhiteChar.add(WhiteChar520);

					string_literal521=(Token)match(input,103,FOLLOW_103_in_presetcondition4239);  
					stream_103.add(string_literal521);

					WhiteChar522=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4241);  
					stream_WhiteChar.add(WhiteChar522);

					char_literal523=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4243);  
					stream_OPENSQRT.add(char_literal523);

					pushFollow(FOLLOW_constant_in_presetcondition4247);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal524=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4249);  
					stream_COMMA.add(char_literal524);

					pushFollow(FOLLOW_constant_in_presetcondition4253);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal525=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4255);  
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
					// 372:4: -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:372:7: ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:372:244: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:374:4: ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:374:4: ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:374:5: 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant
					{
					string_literal526=(Token)match(input,95,FOLLOW_95_in_presetcondition4308);  
					stream_95.add(string_literal526);

					WhiteChar527=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4310);  
					stream_WhiteChar.add(WhiteChar527);

					pushFollow(FOLLOW_constant_in_presetcondition4314);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar528=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4316);  
					stream_WhiteChar.add(WhiteChar528);

					DAYS529=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4318);  
					stream_DAYS.add(DAYS529);

					WhiteChar530=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4325);  
					stream_WhiteChar.add(WhiteChar530);

					string_literal531=(Token)match(input,98,FOLLOW_98_in_presetcondition4327);  
					stream_98.add(string_literal531);

					WhiteChar532=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4329);  
					stream_WhiteChar.add(WhiteChar532);

					pushFollow(FOLLOW_constant_in_presetcondition4333);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar533=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4335);  
					stream_WhiteChar.add(WhiteChar533);

					DAYS534=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4337);  
					stream_DAYS.add(DAYS534);

					WhiteChar535=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4344);  
					stream_WhiteChar.add(WhiteChar535);

					string_literal536=(Token)match(input,79,FOLLOW_79_in_presetcondition4346);  
					stream_79.add(string_literal536);

					WhiteChar537=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4348);  
					stream_WhiteChar.add(WhiteChar537);

					pushFollow(FOLLOW_constant_in_presetcondition4352);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar538=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4354);  
					stream_WhiteChar.add(WhiteChar538);

					DAYS539=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4356);  
					stream_DAYS.add(DAYS539);

					WhiteChar540=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4363);  
					stream_WhiteChar.add(WhiteChar540);

					string_literal541=(Token)match(input,104,FOLLOW_104_in_presetcondition4365);  
					stream_104.add(string_literal541);

					WhiteChar542=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4367);  
					stream_WhiteChar.add(WhiteChar542);

					pushFollow(FOLLOW_constant_in_presetcondition4371);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar543=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4373);  
					stream_WhiteChar.add(WhiteChar543);

					DAYS544=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4375);  
					stream_DAYS.add(DAYS544);

					WhiteChar545=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4382);  
					stream_WhiteChar.add(WhiteChar545);

					string_literal546=(Token)match(input,106,FOLLOW_106_in_presetcondition4384);  
					stream_106.add(string_literal546);

					WhiteChar547=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4386);  
					stream_WhiteChar.add(WhiteChar547);

					char_literal548=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4388);  
					stream_OPENSQRT.add(char_literal548);

					pushFollow(FOLLOW_constant_in_presetcondition4392);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal549=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4394);  
					stream_COMMA.add(char_literal549);

					pushFollow(FOLLOW_constant_in_presetcondition4398);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal550=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4400);  
					stream_CLOSESQRT.add(char_literal550);

					WhiteChar551=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4406);  
					stream_WhiteChar.add(WhiteChar551);

					string_literal552=(Token)match(input,107,FOLLOW_107_in_presetcondition4408);  
					stream_107.add(string_literal552);

					WhiteChar553=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4410);  
					stream_WhiteChar.add(WhiteChar553);

					pushFollow(FOLLOW_constant_in_presetcondition4414);
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
					// 380:4: -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:380:7: ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupportBreakDown, "SupportBreakDown"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:380:104: ^( String StringToken[\"\\\"greedy\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"greedy\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:380:140: ^( String StringToken[\"\\\"smooth\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"smooth\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:380:217: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:380:246: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:380:275: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:380:304: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:4: ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:4: ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:5: 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant
					{
					string_literal554=(Token)match(input,96,FOLLOW_96_in_presetcondition4491);  
					stream_96.add(string_literal554);

					WhiteChar555=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4493);  
					stream_WhiteChar.add(WhiteChar555);

					pushFollow(FOLLOW_constant_in_presetcondition4497);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar556=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4499);  
					stream_WhiteChar.add(WhiteChar556);

					DAYS557=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4501);  
					stream_DAYS.add(DAYS557);

					WhiteChar558=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4511);  
					stream_WhiteChar.add(WhiteChar558);

					string_literal559=(Token)match(input,98,FOLLOW_98_in_presetcondition4513);  
					stream_98.add(string_literal559);

					WhiteChar560=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4515);  
					stream_WhiteChar.add(WhiteChar560);

					pushFollow(FOLLOW_constant_in_presetcondition4519);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar561=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4521);  
					stream_WhiteChar.add(WhiteChar561);

					DAYS562=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4523);  
					stream_DAYS.add(DAYS562);

					WhiteChar563=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4533);  
					stream_WhiteChar.add(WhiteChar563);

					string_literal564=(Token)match(input,79,FOLLOW_79_in_presetcondition4535);  
					stream_79.add(string_literal564);

					WhiteChar565=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4537);  
					stream_WhiteChar.add(WhiteChar565);

					pushFollow(FOLLOW_constant_in_presetcondition4541);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar566=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4543);  
					stream_WhiteChar.add(WhiteChar566);

					DAYS567=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4545);  
					stream_DAYS.add(DAYS567);

					WhiteChar568=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4555);  
					stream_WhiteChar.add(WhiteChar568);

					string_literal569=(Token)match(input,104,FOLLOW_104_in_presetcondition4557);  
					stream_104.add(string_literal569);

					WhiteChar570=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4559);  
					stream_WhiteChar.add(WhiteChar570);

					pushFollow(FOLLOW_constant_in_presetcondition4563);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar571=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4565);  
					stream_WhiteChar.add(WhiteChar571);

					DAYS572=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4567);  
					stream_DAYS.add(DAYS572);

					WhiteChar573=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4577);  
					stream_WhiteChar.add(WhiteChar573);

					string_literal574=(Token)match(input,106,FOLLOW_106_in_presetcondition4579);  
					stream_106.add(string_literal574);

					WhiteChar575=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4581);  
					stream_WhiteChar.add(WhiteChar575);

					char_literal576=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4583);  
					stream_OPENSQRT.add(char_literal576);

					pushFollow(FOLLOW_constant_in_presetcondition4587);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal577=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4589);  
					stream_COMMA.add(char_literal577);

					pushFollow(FOLLOW_constant_in_presetcondition4593);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal578=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4595);  
					stream_CLOSESQRT.add(char_literal578);

					WhiteChar579=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4605);  
					stream_WhiteChar.add(WhiteChar579);

					string_literal580=(Token)match(input,107,FOLLOW_107_in_presetcondition4607);  
					stream_107.add(string_literal580);

					WhiteChar581=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4609);  
					stream_WhiteChar.add(WhiteChar581);

					pushFollow(FOLLOW_constant_in_presetcondition4613);
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
					// 387:6: -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:387:9: ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupportBreakUp, "SupportBreakUp"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:387:104: ^( String StringToken[\"\\\"greedy\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"greedy\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:387:140: ^( String StringToken[\"\\\"smooth\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"smooth\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:387:217: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:387:246: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:387:275: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:387:304: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:389:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:389:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:389:4: 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal582=(Token)match(input,109,FOLLOW_109_in_presetcondition4692);  
					stream_109.add(string_literal582);

					WhiteChar583=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4700);  
					stream_WhiteChar.add(WhiteChar583);

					string_literal584=(Token)match(input,98,FOLLOW_98_in_presetcondition4702);  
					stream_98.add(string_literal584);

					WhiteChar585=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4704);  
					stream_WhiteChar.add(WhiteChar585);

					pushFollow(FOLLOW_constant_in_presetcondition4708);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar586=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4710);  
					stream_WhiteChar.add(WhiteChar586);

					DAYS587=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4712);  
					stream_DAYS.add(DAYS587);

					WhiteChar588=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4720);  
					stream_WhiteChar.add(WhiteChar588);

					string_literal589=(Token)match(input,79,FOLLOW_79_in_presetcondition4722);  
					stream_79.add(string_literal589);

					WhiteChar590=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4724);  
					stream_WhiteChar.add(WhiteChar590);

					pushFollow(FOLLOW_constant_in_presetcondition4728);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar591=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4730);  
					stream_WhiteChar.add(WhiteChar591);

					DAYS592=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4732);  
					stream_DAYS.add(DAYS592);

					WhiteChar593=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4740);  
					stream_WhiteChar.add(WhiteChar593);

					string_literal594=(Token)match(input,75,FOLLOW_75_in_presetcondition4742);  
					stream_75.add(string_literal594);

					WhiteChar595=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4744);  
					stream_WhiteChar.add(WhiteChar595);

					pushFollow(FOLLOW_constant_in_presetcondition4748);
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
					// 393:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:393:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:393:79: ^( String StringToken[\"\\\"flat\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:394:3: ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:394:3: ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:394:4: 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal596=(Token)match(input,112,FOLLOW_112_in_presetcondition4783);  
					stream_112.add(string_literal596);

					WhiteChar597=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4791);  
					stream_WhiteChar.add(WhiteChar597);

					string_literal598=(Token)match(input,98,FOLLOW_98_in_presetcondition4793);  
					stream_98.add(string_literal598);

					WhiteChar599=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4795);  
					stream_WhiteChar.add(WhiteChar599);

					pushFollow(FOLLOW_constant_in_presetcondition4799);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar600=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4801);  
					stream_WhiteChar.add(WhiteChar600);

					DAYS601=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4803);  
					stream_DAYS.add(DAYS601);

					WhiteChar602=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4811);  
					stream_WhiteChar.add(WhiteChar602);

					string_literal603=(Token)match(input,79,FOLLOW_79_in_presetcondition4813);  
					stream_79.add(string_literal603);

					WhiteChar604=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4815);  
					stream_WhiteChar.add(WhiteChar604);

					pushFollow(FOLLOW_constant_in_presetcondition4819);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar605=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4821);  
					stream_WhiteChar.add(WhiteChar605);

					DAYS606=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4823);  
					stream_DAYS.add(DAYS606);

					WhiteChar607=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4831);  
					stream_WhiteChar.add(WhiteChar607);

					string_literal608=(Token)match(input,75,FOLLOW_75_in_presetcondition4833);  
					stream_75.add(string_literal608);

					WhiteChar609=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4835);  
					stream_WhiteChar.add(WhiteChar609);

					pushFollow(FOLLOW_constant_in_presetcondition4839);
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
					// 398:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:398:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:398:79: ^( String StringToken[\"\\\"up\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:399:3: ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:399:3: ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:399:4: 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal610=(Token)match(input,108,FOLLOW_108_in_presetcondition4874);  
					stream_108.add(string_literal610);

					WhiteChar611=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4882);  
					stream_WhiteChar.add(WhiteChar611);

					string_literal612=(Token)match(input,98,FOLLOW_98_in_presetcondition4884);  
					stream_98.add(string_literal612);

					WhiteChar613=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4886);  
					stream_WhiteChar.add(WhiteChar613);

					pushFollow(FOLLOW_constant_in_presetcondition4890);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar614=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4892);  
					stream_WhiteChar.add(WhiteChar614);

					DAYS615=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4894);  
					stream_DAYS.add(DAYS615);

					WhiteChar616=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4902);  
					stream_WhiteChar.add(WhiteChar616);

					string_literal617=(Token)match(input,79,FOLLOW_79_in_presetcondition4904);  
					stream_79.add(string_literal617);

					WhiteChar618=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4906);  
					stream_WhiteChar.add(WhiteChar618);

					pushFollow(FOLLOW_constant_in_presetcondition4910);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar619=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4912);  
					stream_WhiteChar.add(WhiteChar619);

					DAYS620=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4914);  
					stream_DAYS.add(DAYS620);

					WhiteChar621=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4922);  
					stream_WhiteChar.add(WhiteChar621);

					string_literal622=(Token)match(input,75,FOLLOW_75_in_presetcondition4924);  
					stream_75.add(string_literal622);

					WhiteChar623=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4926);  
					stream_WhiteChar.add(WhiteChar623);

					pushFollow(FOLLOW_constant_in_presetcondition4930);
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
					// 403:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:403:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:403:79: ^( String StringToken[\"\\\"down\\\"\"] )
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
	public static final BitSet FOLLOW_and_expression_in_primary_expression798 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_expression_in_and_expression810 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_lenient_in_and_expression814 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression817 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_and_expression819 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression821 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_or_expression_in_and_expression823 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_precondition_expression_in_or_expression850 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression853 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OR_in_or_expression855 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression857 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_precondition_expression_in_or_expression859 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_matches_expression_in_precondition_expression891 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_precondition_expression894 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WITH_in_precondition_expression896 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_precondition_expression898 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_matches_expression_in_precondition_expression900 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_atom_in_matches_expression925 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression928 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_MATCHING_in_matches_expression930 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression932 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_matches_expression934 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_matches_expression936 = new BitSet(new long[]{0x0000000000000180L});
	public static final BitSet FOLLOW_COMMA_in_matches_expression939 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_matches_expression941 = new BitSet(new long[]{0x0000000000000180L});
	public static final BitSet FOLLOW_CLOSESQRT_in_matches_expression945 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression947 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_atom_in_matches_expression949 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_booleanhistory_in_atom977 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom983 = new BitSet(new long[]{0x8200048400400000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom985 = new BitSet(new long[]{0x8200048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_atom988 = new BitSet(new long[]{0x8000000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom990 = new BitSet(new long[]{0x8000000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom993 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_atom1003 = new BitSet(new long[]{0x8000008000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom1005 = new BitSet(new long[]{0x8000008000000000L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom1008 = new BitSet(new long[]{0x8200048400400000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom1010 = new BitSet(new long[]{0x8200048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_atom1013 = new BitSet(new long[]{0x8000000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom1015 = new BitSet(new long[]{0x8000000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom1018 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_conjunctiontruthof_in_atom1039 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUTHOF_in_conjunctiontruthof1051 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof1053 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_conjunctiontruthof1055 = new BitSet(new long[]{0x8000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_conjunctiontruthof1058 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof1060 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_conjunctiontruthof1062 = new BitSet(new long[]{0x8000000000000100L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof1066 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_90_in_conjunctiontruthof1068 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof1070 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_conjunctiontruthof1072 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_conjunctiontruthof1076 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_conjunctiontruthof1078 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_conjunctiontruthof1082 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_conjunctiontruthof1084 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_booleanhistory1123 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_booleanhistory1125 = new BitSet(new long[]{0x0000000000000000L,0x0001F061F99B71E0L});
	public static final BitSet FOLLOW_presetcondition_in_booleanhistory1131 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_opcmpcondition_in_booleanhistory1142 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constantcmp_in_booleanhistory1153 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_operand1169 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Operation_in_operand1196 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NumberToken_in_constant1211 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_64_in_constant1223 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_StringToken_in_stringconstant1239 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_68_in_trendconstant1254 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_67_in_trendconstant1267 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_lenient1284 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_LENIENT_in_lenient1286 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_83_in_opcmpcondition1323 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1325 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1329 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1360 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_opcmpcondition1362 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1364 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1368 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1370 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1372 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1374 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition1376 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1378 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1382 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1384 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_87_in_opcmpcondition1416 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1418 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1422 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1453 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_opcmpcondition1455 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1457 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1461 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1463 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1465 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1467 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition1469 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1471 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1475 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1477 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_76_in_opcmpcondition1508 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1510 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1514 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1545 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_opcmpcondition1547 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1549 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1553 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1555 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1557 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1559 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition1561 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1563 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1567 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1569 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_opcmpcondition1602 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1604 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1608 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1662 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_opcmpcondition1664 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1666 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1670 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1672 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1674 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1676 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_opcmpcondition1678 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1680 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1684 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1686 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1688 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1690 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition1692 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1694 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1698 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1700 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1740 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_opcmpcondition1742 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1744 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1748 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1750 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1752 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_atom_in_opcmpcondition1756 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_71_in_opcmpcondition1799 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1801 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1805 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1859 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_opcmpcondition1861 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1863 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1867 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1869 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1871 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1873 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_opcmpcondition1875 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1877 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1881 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1883 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1885 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1887 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition1889 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1891 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1895 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1897 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1937 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_opcmpcondition1939 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1941 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1945 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1947 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1949 = new BitSet(new long[]{0x0200048400400000L});
	public static final BitSet FOLLOW_atom_in_opcmpcondition1953 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_110_in_opcmpcondition1998 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2000 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition2004 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2012 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_opcmpcondition2014 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2016 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2020 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2022 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition2024 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2032 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_opcmpcondition2034 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2036 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2040 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2042 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition2044 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2052 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_opcmpcondition2054 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2056 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition2060 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2068 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition2070 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2072 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2076 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_111_in_opcmpcondition2108 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2110 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition2114 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2122 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_opcmpcondition2124 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2126 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2130 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2132 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition2134 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2142 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_opcmpcondition2144 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2146 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2150 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2152 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition2154 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2162 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_opcmpcondition2164 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2166 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition2170 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_78_in_constantcmp2207 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2209 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000018L});
	public static final BitSet FOLLOW_trendconstant_in_constantcmp2213 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2247 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_constantcmp2249 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2251 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2255 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2257 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2259 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2261 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_constantcmp2263 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2265 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2269 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2271 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2273 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_77_in_constantcmp2297 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2299 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2303 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2344 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_constantcmp2346 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2348 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2352 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2354 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2356 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2362 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_constantcmp2364 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2366 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2370 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2372 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2374 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2380 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_constantcmp2382 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2384 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2388 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2390 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_84_in_constantcmp2420 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2422 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2426 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2467 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_constantcmp2469 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2471 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2475 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2477 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2479 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2485 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_constantcmp2487 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2489 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2493 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2495 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2497 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2503 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_constantcmp2505 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2507 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2511 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2513 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_88_in_constantcmp2543 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2545 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2549 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2590 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_constantcmp2592 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2594 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2598 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2600 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2602 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2608 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_constantcmp2610 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2612 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2616 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2618 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2620 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2626 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_constantcmp2628 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2630 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_constantcmp2634 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2636 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_101_in_presetcondition2673 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2713 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_presetcondition2715 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2717 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition2721 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2723 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2725 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_presetcondition2727 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2729 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition2733 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2735 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2737 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_102_in_presetcondition2773 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2813 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_presetcondition2815 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2817 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition2821 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2823 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2825 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_presetcondition2827 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2829 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition2833 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2835 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2837 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_80_in_presetcondition2874 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2876 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition2880 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2882 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2931 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_presetcondition2933 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2935 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition2939 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2941 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2943 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2954 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition2956 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2958 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition2962 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2964 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2966 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_81_in_presetcondition3010 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3012 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3016 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition3018 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3067 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_presetcondition3069 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3071 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3075 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3077 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3079 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3090 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition3092 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3094 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3098 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3100 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3102 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_72_in_presetcondition3147 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3149 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3153 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3202 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_presetcondition3204 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3206 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3210 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3212 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3214 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3225 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition3227 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3229 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3233 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3235 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3237 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3247 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_presetcondition3249 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3251 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3255 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition3257 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_70_in_presetcondition3296 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3298 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3302 = new BitSet(new long[]{0x8000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3351 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_presetcondition3353 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3355 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3359 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3361 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3363 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3374 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition3376 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3378 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3382 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3384 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3386 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3396 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_presetcondition3398 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3400 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3404 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition3406 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_91_in_presetcondition3446 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3448 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3452 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3454 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3456 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3463 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition3465 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3467 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3471 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3473 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3475 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3482 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition3484 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3486 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3490 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3492 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3494 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3501 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition3503 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3505 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3509 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3511 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3513 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3520 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
	public static final BitSet FOLLOW_82_in_presetcondition3522 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3524 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3528 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3534 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
	public static final BitSet FOLLOW_113_in_presetcondition3536 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3538 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3542 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3548 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition3550 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3552 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3554 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3558 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3560 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3564 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3566 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3568 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition3570 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3572 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3574 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3578 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3580 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3584 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3586 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3592 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition3594 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3596 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3598 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3602 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3604 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3608 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3610 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_92_in_presetcondition3661 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3663 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3667 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3669 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3671 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3678 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition3680 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3682 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3686 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3688 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3690 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3697 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition3699 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3701 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3705 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3707 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3709 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3716 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition3718 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3720 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3724 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3726 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3728 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3735 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
	public static final BitSet FOLLOW_82_in_presetcondition3737 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3739 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3743 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3749 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
	public static final BitSet FOLLOW_113_in_presetcondition3751 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3753 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3757 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3763 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition3765 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3767 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3769 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3773 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3775 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3779 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3781 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3783 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition3785 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3787 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3789 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3793 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3795 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3799 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3801 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3807 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition3809 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3811 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3813 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3817 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3819 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3823 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3825 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_93_in_presetcondition3876 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3878 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3882 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3884 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3886 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3893 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition3895 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3897 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3901 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3903 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3905 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3912 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition3914 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3916 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3920 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3922 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3924 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3931 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition3933 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3935 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3939 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3941 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3943 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3950 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
	public static final BitSet FOLLOW_82_in_presetcondition3952 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3954 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3958 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3964 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
	public static final BitSet FOLLOW_113_in_presetcondition3966 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3968 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3972 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3978 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition3980 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3982 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3984 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3988 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3990 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition3994 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3996 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3998 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition4000 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4002 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4004 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4008 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4010 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4014 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4016 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4022 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition4024 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4026 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4028 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4032 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4034 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4038 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4040 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_94_in_presetcondition4091 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4093 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4097 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4099 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4101 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4108 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition4110 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4112 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4116 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4118 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4120 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4127 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition4129 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4131 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4135 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4137 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4139 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4146 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition4148 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4150 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4154 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4156 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4158 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4165 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
	public static final BitSet FOLLOW_82_in_presetcondition4167 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4169 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition4173 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4179 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
	public static final BitSet FOLLOW_113_in_presetcondition4181 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4183 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition4187 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4193 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition4195 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4197 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4199 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4203 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4205 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4209 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4211 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4213 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition4215 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4217 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4219 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4223 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4225 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4229 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4231 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4237 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition4239 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4241 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4243 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4247 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4249 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4253 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4255 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_95_in_presetcondition4308 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4310 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4314 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4316 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4318 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4325 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition4327 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4329 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4333 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4335 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4337 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4344 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition4346 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4348 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4352 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4354 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4356 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4363 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition4365 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4367 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4371 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4373 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4375 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4382 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition4384 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4386 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4388 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4392 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4394 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4398 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4400 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4406 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_presetcondition4408 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4410 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4414 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_96_in_presetcondition4491 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4493 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4497 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4499 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4501 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4511 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition4513 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4515 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4519 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4521 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4523 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4533 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition4535 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4537 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4541 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4543 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4545 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4555 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition4557 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4559 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4563 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4565 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4567 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4577 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition4579 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4581 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4583 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4587 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4589 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4593 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4595 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4605 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_presetcondition4607 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4609 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4613 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_109_in_presetcondition4692 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4700 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition4702 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4704 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4708 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4710 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4712 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4720 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition4722 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4724 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4728 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4730 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4732 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4740 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_presetcondition4742 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4744 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4748 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_112_in_presetcondition4783 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4791 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition4793 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4795 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4799 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4801 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4803 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4811 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition4813 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4815 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4819 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4821 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4823 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4831 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_presetcondition4833 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4835 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4839 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_108_in_presetcondition4874 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4882 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition4884 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4886 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4890 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4892 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4894 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4902 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition4904 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4906 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4910 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4912 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4914 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4922 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_presetcondition4924 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4926 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_constant_in_presetcondition4930 = new BitSet(new long[]{0x0000000000000002L});
}
