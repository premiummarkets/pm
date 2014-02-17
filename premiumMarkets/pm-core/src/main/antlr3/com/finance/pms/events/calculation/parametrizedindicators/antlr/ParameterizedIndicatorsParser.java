// $ANTLR 3.5 /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2014-02-16 00:52:46
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
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "AND", "AndDoubleMapCondition", 
		"CLOSEPARENTEHSIS", "COMMA", "COMMENT", "CrossDownConstantCondition", 
		"CrossDownDoubleMapCondition", "CrossUpConstantCondition", "CrossUpDoubleMapCondition", 
		"DAYS", "DownRatioCondition", "EqualConstantCondition", "EqualDoubleMapCondition", 
		"EventConditionHolder", "HigherHighCondition", "HigherLowCondition", "HistoricalData", 
		"InfConstantCondition", "InfDoubleMapCondition", "LENIENT", "LINE_COMMENT", 
		"LowerHighCondition", "LowerLowCondition", "NOT", "NotDoubleMapCondition", 
		"NullCondition", "Number", "NumberToken", "OPENPARENTEHSIS", "OR", "Operation", 
		"OperationOutput", "OrDoubleMapCondition", "PERCENT", "ReverseCondition", 
		"StockOperation", "String", "StringOperation", "StringToken", "SupConstantCondition", 
		"SupDoubleMapCondition", "Tcheat", "UpRatioCondition", "WS", "WhiteChar", 
		"'also display'", "'crosses down historical'", "'crosses down threshold'", 
		"'crosses up historical'", "'crosses up threshold'", "'equals historical'", 
		"'equals threshold'", "'for'", "'goes down more than'", "'goes up more than'", 
		"'is above historical'", "'is above threshold'", "'is bearish if not bullish'", 
		"'is bearish when'", "'is below historical'", "'is below threshold'", 
		"'is bullish when'", "'makes a higher high spanning'", "'makes a higher low spanning'", 
		"'makes a lower high spanning'", "'makes a lower low spanning'", "'more than'", 
		"'over'", "'reverses down'", "'reverses up'", "'spanning'"
	};
	public static final int EOF=-1;
	public static final int T__49=49;
	public static final int T__50=50;
	public static final int T__51=51;
	public static final int T__52=52;
	public static final int T__53=53;
	public static final int T__54=54;
	public static final int T__55=55;
	public static final int T__56=56;
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
	public static final int EventConditionHolder=17;
	public static final int HigherHighCondition=18;
	public static final int HigherLowCondition=19;
	public static final int HistoricalData=20;
	public static final int InfConstantCondition=21;
	public static final int InfDoubleMapCondition=22;
	public static final int LENIENT=23;
	public static final int LINE_COMMENT=24;
	public static final int LowerHighCondition=25;
	public static final int LowerLowCondition=26;
	public static final int NOT=27;
	public static final int NotDoubleMapCondition=28;
	public static final int NullCondition=29;
	public static final int Number=30;
	public static final int NumberToken=31;
	public static final int OPENPARENTEHSIS=32;
	public static final int OR=33;
	public static final int Operation=34;
	public static final int OperationOutput=35;
	public static final int OrDoubleMapCondition=36;
	public static final int PERCENT=37;
	public static final int ReverseCondition=38;
	public static final int StockOperation=39;
	public static final int String=40;
	public static final int StringOperation=41;
	public static final int StringToken=42;
	public static final int SupConstantCondition=43;
	public static final int SupDoubleMapCondition=44;
	public static final int Tcheat=45;
	public static final int UpRatioCondition=46;
	public static final int WS=47;
	public static final int WhiteChar=48;

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
	@Override public String getGrammarFileName() { return "/home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g"; }



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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:143:1: complete_expression : bcond= bullish_condition bearish_condition[$bcond.tree] also_display -> ^( EventConditionHolder bullish_condition bearish_condition also_display StringOperation ) ;
	public final ParameterizedIndicatorsParser.complete_expression_return complete_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.complete_expression_return retval = new ParameterizedIndicatorsParser.complete_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope bcond =null;
		ParserRuleReturnScope bearish_condition1 =null;
		ParserRuleReturnScope also_display2 =null;

		RewriteRuleSubtreeStream stream_bullish_condition=new RewriteRuleSubtreeStream(adaptor,"rule bullish_condition");
		RewriteRuleSubtreeStream stream_also_display=new RewriteRuleSubtreeStream(adaptor,"rule also_display");
		RewriteRuleSubtreeStream stream_bearish_condition=new RewriteRuleSubtreeStream(adaptor,"rule bearish_condition");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:143:21: (bcond= bullish_condition bearish_condition[$bcond.tree] also_display -> ^( EventConditionHolder bullish_condition bearish_condition also_display StringOperation ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:144:4: bcond= bullish_condition bearish_condition[$bcond.tree] also_display
			{
			pushFollow(FOLLOW_bullish_condition_in_complete_expression328);
			bcond=bullish_condition();
			state._fsp--;

			stream_bullish_condition.add(bcond.getTree());
			pushFollow(FOLLOW_bearish_condition_in_complete_expression330);
			bearish_condition1=bearish_condition((bcond!=null?((CommonTree)bcond.getTree()):null));
			state._fsp--;

			stream_bearish_condition.add(bearish_condition1.getTree());
			pushFollow(FOLLOW_also_display_in_complete_expression333);
			also_display2=also_display();
			state._fsp--;

			stream_also_display.add(also_display2.getTree());
			// AST REWRITE
			// elements: also_display, bearish_condition, bullish_condition
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 144:72: -> ^( EventConditionHolder bullish_condition bearish_condition also_display StringOperation )
			{
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:144:75: ^( EventConditionHolder bullish_condition bearish_condition also_display StringOperation )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EventConditionHolder, "EventConditionHolder"), root_1);
				adaptor.addChild(root_1, stream_bullish_condition.nextTree());
				adaptor.addChild(root_1, stream_bearish_condition.nextTree());
				adaptor.addChild(root_1, stream_also_display.nextTree());
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:147:1: bullish_condition : 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression ;
	public final ParameterizedIndicatorsParser.bullish_condition_return bullish_condition() throws RecognitionException {
		ParameterizedIndicatorsParser.bullish_condition_return retval = new ParameterizedIndicatorsParser.bullish_condition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal3=null;
		Token WhiteChar4=null;
		Token WhiteChar6=null;
		Token COMMA7=null;
		Token WhiteChar8=null;
		ParserRuleReturnScope primary_expression5 =null;

		CommonTree string_literal3_tree=null;
		CommonTree WhiteChar4_tree=null;
		CommonTree WhiteChar6_tree=null;
		CommonTree COMMA7_tree=null;
		CommonTree WhiteChar8_tree=null;
		RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:147:19: ( 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:148:2: 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )*
			{
			string_literal3=(Token)match(input,65,FOLLOW_65_in_bullish_condition361);  
			stream_65.add(string_literal3);

			WhiteChar4=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition363);  
			stream_WhiteChar.add(WhiteChar4);

			pushFollow(FOLLOW_primary_expression_in_bullish_condition365);
			primary_expression5=primary_expression();
			state._fsp--;

			stream_primary_expression.add(primary_expression5.getTree());
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:148:49: ( WhiteChar )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==WhiteChar) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:148:49: WhiteChar
					{
					WhiteChar6=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition367);  
					stream_WhiteChar.add(WhiteChar6);

					}
					break;

				default :
					break loop1;
				}
			}

			COMMA7=(Token)match(input,COMMA,FOLLOW_COMMA_in_bullish_condition370);  
			stream_COMMA.add(COMMA7);

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:148:66: ( WhiteChar )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==WhiteChar) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:148:66: WhiteChar
					{
					WhiteChar8=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition372);  
					stream_WhiteChar.add(WhiteChar8);

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
			// 148:77: -> primary_expression
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:150:1: bearish_condition[CommonTree bcond] : ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* COMMA ( WhiteChar )* -> bearish_not_bullish );
	public final ParameterizedIndicatorsParser.bearish_condition_return bearish_condition(CommonTree bcond) throws RecognitionException {
		ParameterizedIndicatorsParser.bearish_condition_return retval = new ParameterizedIndicatorsParser.bearish_condition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal9=null;
		Token WhiteChar10=null;
		Token WhiteChar12=null;
		Token COMMA13=null;
		Token WhiteChar14=null;
		Token WhiteChar16=null;
		Token COMMA17=null;
		Token WhiteChar18=null;
		ParserRuleReturnScope primary_expression11 =null;
		ParserRuleReturnScope bearish_not_bullish15 =null;

		CommonTree string_literal9_tree=null;
		CommonTree WhiteChar10_tree=null;
		CommonTree WhiteChar12_tree=null;
		CommonTree COMMA13_tree=null;
		CommonTree WhiteChar14_tree=null;
		CommonTree WhiteChar16_tree=null;
		CommonTree COMMA17_tree=null;
		CommonTree WhiteChar18_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_bearish_not_bullish=new RewriteRuleSubtreeStream(adaptor,"rule bearish_not_bullish");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:150:37: ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* COMMA ( WhiteChar )* -> bearish_not_bullish )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==62) ) {
				alt7=1;
			}
			else if ( (LA7_0==61) ) {
				alt7=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:151:2: 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )*
					{
					string_literal9=(Token)match(input,62,FOLLOW_62_in_bearish_condition388);  
					stream_62.add(string_literal9);

					WhiteChar10=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition390);  
					stream_WhiteChar.add(WhiteChar10);

					pushFollow(FOLLOW_primary_expression_in_bearish_condition393);
					primary_expression11=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression11.getTree());
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:151:50: ( WhiteChar )*
					loop3:
					while (true) {
						int alt3=2;
						int LA3_0 = input.LA(1);
						if ( (LA3_0==WhiteChar) ) {
							alt3=1;
						}

						switch (alt3) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:151:50: WhiteChar
							{
							WhiteChar12=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition395);  
							stream_WhiteChar.add(WhiteChar12);

							}
							break;

						default :
							break loop3;
						}
					}

					COMMA13=(Token)match(input,COMMA,FOLLOW_COMMA_in_bearish_condition398);  
					stream_COMMA.add(COMMA13);

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:151:67: ( WhiteChar )*
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( (LA4_0==WhiteChar) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:151:67: WhiteChar
							{
							WhiteChar14=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition400);  
							stream_WhiteChar.add(WhiteChar14);

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
					// 151:78: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:152:2: bearish_not_bullish[$bcond] ( WhiteChar )* COMMA ( WhiteChar )*
					{
					pushFollow(FOLLOW_bearish_not_bullish_in_bearish_condition410);
					bearish_not_bullish15=bearish_not_bullish(bcond);
					state._fsp--;

					stream_bearish_not_bullish.add(bearish_not_bullish15.getTree());
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:152:30: ( WhiteChar )*
					loop5:
					while (true) {
						int alt5=2;
						int LA5_0 = input.LA(1);
						if ( (LA5_0==WhiteChar) ) {
							alt5=1;
						}

						switch (alt5) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:152:30: WhiteChar
							{
							WhiteChar16=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition413);  
							stream_WhiteChar.add(WhiteChar16);

							}
							break;

						default :
							break loop5;
						}
					}

					COMMA17=(Token)match(input,COMMA,FOLLOW_COMMA_in_bearish_condition416);  
					stream_COMMA.add(COMMA17);

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:152:47: ( WhiteChar )*
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( (LA6_0==WhiteChar) ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:152:47: WhiteChar
							{
							WhiteChar18=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition418);  
							stream_WhiteChar.add(WhiteChar18);

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
					// 152:58: -> bearish_not_bullish
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:154:2: also_display : ( 'also display' WhiteChar primary_expression ( WhiteChar )* COMMA -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition );
	public final ParameterizedIndicatorsParser.also_display_return also_display() throws RecognitionException {
		ParameterizedIndicatorsParser.also_display_return retval = new ParameterizedIndicatorsParser.also_display_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal19=null;
		Token WhiteChar20=null;
		Token WhiteChar22=null;
		Token COMMA23=null;
		ParserRuleReturnScope primary_expression21 =null;

		CommonTree string_literal19_tree=null;
		CommonTree WhiteChar20_tree=null;
		CommonTree WhiteChar22_tree=null;
		CommonTree COMMA23_tree=null;
		RewriteRuleTokenStream stream_49=new RewriteRuleTokenStream(adaptor,"token 49");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:154:15: ( 'also display' WhiteChar primary_expression ( WhiteChar )* COMMA -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==49) ) {
				alt9=1;
			}
			else if ( (LA9_0==EOF) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:3: 'also display' WhiteChar primary_expression ( WhiteChar )* COMMA
					{
					string_literal19=(Token)match(input,49,FOLLOW_49_in_also_display435);  
					stream_49.add(string_literal19);

					WhiteChar20=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display437);  
					stream_WhiteChar.add(WhiteChar20);

					pushFollow(FOLLOW_primary_expression_in_also_display439);
					primary_expression21=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression21.getTree());
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:47: ( WhiteChar )*
					loop8:
					while (true) {
						int alt8=2;
						int LA8_0 = input.LA(1);
						if ( (LA8_0==WhiteChar) ) {
							alt8=1;
						}

						switch (alt8) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:47: WhiteChar
							{
							WhiteChar22=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display441);  
							stream_WhiteChar.add(WhiteChar22);

							}
							break;

						default :
							break loop8;
						}
					}

					COMMA23=(Token)match(input,COMMA,FOLLOW_COMMA_in_also_display444);  
					stream_COMMA.add(COMMA23);

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
					// 155:64: -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:67: ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:91: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:156:3: 
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
					// 156:3: -> NullCondition
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


	public static class bearish_not_bullish_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "bearish_not_bullish"
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:1: bearish_not_bullish[CommonTree bcond] : 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) ) ;
	public final ParameterizedIndicatorsParser.bearish_not_bullish_return bearish_not_bullish(CommonTree bcond) throws RecognitionException {
		ParameterizedIndicatorsParser.bearish_not_bullish_return retval = new ParameterizedIndicatorsParser.bearish_not_bullish_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal24=null;
		Token WhiteChar25=null;
		Token AND26=null;
		Token WhiteChar27=null;
		Token WhiteChar29=null;
		Token OR30=null;
		Token WhiteChar31=null;
		ParserRuleReturnScope primary_expression28 =null;
		ParserRuleReturnScope primary_expression32 =null;

		CommonTree string_literal24_tree=null;
		CommonTree WhiteChar25_tree=null;
		CommonTree AND26_tree=null;
		CommonTree WhiteChar27_tree=null;
		CommonTree WhiteChar29_tree=null;
		CommonTree OR30_tree=null;
		CommonTree WhiteChar31_tree=null;
		RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
		RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:39: ( 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:160:2: 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) )
			{
			string_literal24=(Token)match(input,61,FOLLOW_61_in_bearish_not_bullish481);  
			stream_61.add(string_literal24);

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:161:3: ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) )
			int alt10=3;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==WhiteChar) ) {
				switch ( input.LA(2) ) {
				case AND:
					{
					alt10=1;
					}
					break;
				case OR:
					{
					alt10=2;
					}
					break;
				case COMMA:
				case WhiteChar:
					{
					alt10=3;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 10, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}
			else if ( (LA10_0==COMMA) ) {
				alt10=3;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:3: WhiteChar AND WhiteChar primary_expression
					{
					WhiteChar25=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish490);  
					stream_WhiteChar.add(WhiteChar25);

					AND26=(Token)match(input,AND,FOLLOW_AND_in_bearish_not_bullish492);  
					stream_AND.add(AND26);

					WhiteChar27=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish494);  
					stream_WhiteChar.add(WhiteChar27);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish496);
					primary_expression28=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression28.getTree());
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
					// 162:46: -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:50: ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:74: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:109: ^( NotDoubleMapCondition )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:3: WhiteChar OR WhiteChar primary_expression
					{
					WhiteChar29=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish524);  
					stream_WhiteChar.add(WhiteChar29);

					OR30=(Token)match(input,OR,FOLLOW_OR_in_bearish_not_bullish526);  
					stream_OR.add(OR30);

					WhiteChar31=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish528);  
					stream_WhiteChar.add(WhiteChar31);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish531);
					primary_expression32=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression32.getTree());
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
					// 163:46: -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:50: ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrDoubleMapCondition, "OrDoubleMapCondition"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:73: ^( NotDoubleMapCondition )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:164:3: 
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
					// 164:3: -> ^( NotDoubleMapCondition )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:164:6: ^( NotDoubleMapCondition )
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:168:1: primary_expression : and_expression ;
	public final ParameterizedIndicatorsParser.primary_expression_return primary_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.primary_expression_return retval = new ParameterizedIndicatorsParser.primary_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope and_expression33 =null;


		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:168:20: ( and_expression )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:2: and_expression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_and_expression_in_primary_expression573);
			and_expression33=and_expression();
			state._fsp--;

			adaptor.addChild(root_0, and_expression33.getTree());

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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:172:1: and_expression : or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndDoubleMapCondition or_expression ( or_expression )* ) ;
	public final ParameterizedIndicatorsParser.and_expression_return and_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.and_expression_return retval = new ParameterizedIndicatorsParser.and_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar35=null;
		Token AND36=null;
		Token WhiteChar37=null;
		ParserRuleReturnScope lenientParam =null;
		ParserRuleReturnScope or_expression34 =null;
		ParserRuleReturnScope or_expression38 =null;

		CommonTree WhiteChar35_tree=null;
		CommonTree AND36_tree=null;
		CommonTree WhiteChar37_tree=null;
		RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_or_expression=new RewriteRuleSubtreeStream(adaptor,"rule or_expression");
		RewriteRuleSubtreeStream stream_lenient=new RewriteRuleSubtreeStream(adaptor,"rule lenient");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:172:16: ( or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndDoubleMapCondition or_expression ( or_expression )* ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:3: or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )*
			{
			pushFollow(FOLLOW_or_expression_in_and_expression588);
			or_expression34=or_expression();
			state._fsp--;

			stream_or_expression.add(or_expression34.getTree());
			pushFollow(FOLLOW_lenient_in_and_expression592);
			lenientParam=lenient();
			state._fsp--;

			stream_lenient.add(lenientParam.getTree());
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:38: ( WhiteChar AND WhiteChar or_expression )*
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( (LA11_0==WhiteChar) ) {
					int LA11_1 = input.LA(2);
					if ( (LA11_1==AND) ) {
						alt11=1;
					}

				}

				switch (alt11) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:39: WhiteChar AND WhiteChar or_expression
					{
					WhiteChar35=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression595);  
					stream_WhiteChar.add(WhiteChar35);

					AND36=(Token)match(input,AND,FOLLOW_AND_in_and_expression597);  
					stream_AND.add(AND36);

					WhiteChar37=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression599);  
					stream_WhiteChar.add(WhiteChar37);

					pushFollow(FOLLOW_or_expression_in_and_expression601);
					or_expression38=or_expression();
					state._fsp--;

					stream_or_expression.add(or_expression38.getTree());
					}
					break;

				default :
					break loop11;
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
			// 173:80: -> ^( AndDoubleMapCondition or_expression ( or_expression )* )
			{
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:84: ^( AndDoubleMapCondition or_expression ( or_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
				adaptor.addChild(root_1, (lenientParam!=null?((CommonTree)lenientParam.getTree()):null));
				adaptor.addChild(root_1, stream_or_expression.nextTree());
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:144: ( or_expression )*
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:175:1: or_expression : atom ( WhiteChar OR WhiteChar atom )* -> ^( OrDoubleMapCondition atom ( atom )* ) ;
	public final ParameterizedIndicatorsParser.or_expression_return or_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.or_expression_return retval = new ParameterizedIndicatorsParser.or_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar40=null;
		Token OR41=null;
		Token WhiteChar42=null;
		ParserRuleReturnScope atom39 =null;
		ParserRuleReturnScope atom43 =null;

		CommonTree WhiteChar40_tree=null;
		CommonTree OR41_tree=null;
		CommonTree WhiteChar42_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
		RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:175:15: ( atom ( WhiteChar OR WhiteChar atom )* -> ^( OrDoubleMapCondition atom ( atom )* ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:3: atom ( WhiteChar OR WhiteChar atom )*
			{
			pushFollow(FOLLOW_atom_in_or_expression633);
			atom39=atom();
			state._fsp--;

			stream_atom.add(atom39.getTree());
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:8: ( WhiteChar OR WhiteChar atom )*
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( (LA12_0==WhiteChar) ) {
					int LA12_1 = input.LA(2);
					if ( (LA12_1==OR) ) {
						alt12=1;
					}

				}

				switch (alt12) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:9: WhiteChar OR WhiteChar atom
					{
					WhiteChar40=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression636);  
					stream_WhiteChar.add(WhiteChar40);

					OR41=(Token)match(input,OR,FOLLOW_OR_in_or_expression638);  
					stream_OR.add(OR41);

					WhiteChar42=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression640);  
					stream_WhiteChar.add(WhiteChar42);

					pushFollow(FOLLOW_atom_in_or_expression642);
					atom43=atom();
					state._fsp--;

					stream_atom.add(atom43.getTree());
					}
					break;

				default :
					break loop12;
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
			// 176:39: -> ^( OrDoubleMapCondition atom ( atom )* )
			{
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:43: ^( OrDoubleMapCondition atom ( atom )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrDoubleMapCondition, "OrDoubleMapCondition"), root_1);
				adaptor.addChild(root_1, stream_atom.nextTree());
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:71: ( atom )*
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
	// $ANTLR end "or_expression"


	public static class atom_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "atom"
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:178:1: atom : ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotDoubleMapCondition primary_expression ) );
	public final ParameterizedIndicatorsParser.atom_return atom() throws RecognitionException {
		ParameterizedIndicatorsParser.atom_return retval = new ParameterizedIndicatorsParser.atom_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal45=null;
		Token WhiteChar46=null;
		Token WhiteChar48=null;
		Token char_literal49=null;
		Token string_literal50=null;
		Token WhiteChar51=null;
		Token char_literal52=null;
		Token WhiteChar53=null;
		Token WhiteChar55=null;
		Token char_literal56=null;
		ParserRuleReturnScope booleanhistory44 =null;
		ParserRuleReturnScope primary_expression47 =null;
		ParserRuleReturnScope primary_expression54 =null;

		CommonTree char_literal45_tree=null;
		CommonTree WhiteChar46_tree=null;
		CommonTree WhiteChar48_tree=null;
		CommonTree char_literal49_tree=null;
		CommonTree string_literal50_tree=null;
		CommonTree WhiteChar51_tree=null;
		CommonTree char_literal52_tree=null;
		CommonTree WhiteChar53_tree=null;
		CommonTree WhiteChar55_tree=null;
		CommonTree char_literal56_tree=null;
		RewriteRuleTokenStream stream_CLOSEPARENTEHSIS=new RewriteRuleTokenStream(adaptor,"token CLOSEPARENTEHSIS");
		RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
		RewriteRuleTokenStream stream_OPENPARENTEHSIS=new RewriteRuleTokenStream(adaptor,"token OPENPARENTEHSIS");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:178:6: ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotDoubleMapCondition primary_expression ) )
			int alt18=3;
			switch ( input.LA(1) ) {
			case HistoricalData:
			case Operation:
				{
				alt18=1;
				}
				break;
			case OPENPARENTEHSIS:
				{
				alt18=2;
				}
				break;
			case NOT:
				{
				alt18=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 18, 0, input);
				throw nvae;
			}
			switch (alt18) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:3: booleanhistory
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_booleanhistory_in_atom669);
					booleanhistory44=booleanhistory();
					state._fsp--;

					adaptor.addChild(root_0, booleanhistory44.getTree());

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:3: '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					char_literal45=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom676);  
					stream_OPENPARENTEHSIS.add(char_literal45);

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:7: ( WhiteChar )*
					loop13:
					while (true) {
						int alt13=2;
						int LA13_0 = input.LA(1);
						if ( (LA13_0==WhiteChar) ) {
							alt13=1;
						}

						switch (alt13) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:7: WhiteChar
							{
							WhiteChar46=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom678);  
							stream_WhiteChar.add(WhiteChar46);

							}
							break;

						default :
							break loop13;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom681);
					primary_expression47=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression47.getTree());
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:37: ( WhiteChar )*
					loop14:
					while (true) {
						int alt14=2;
						int LA14_0 = input.LA(1);
						if ( (LA14_0==WhiteChar) ) {
							alt14=1;
						}

						switch (alt14) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:37: WhiteChar
							{
							WhiteChar48=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom683);  
							stream_WhiteChar.add(WhiteChar48);

							}
							break;

						default :
							break loop14;
						}
					}

					char_literal49=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom686);  
					stream_CLOSEPARENTEHSIS.add(char_literal49);

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
					// 180:52: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:3: 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					string_literal50=(Token)match(input,NOT,FOLLOW_NOT_in_atom698);  
					stream_NOT.add(string_literal50);

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:9: ( WhiteChar )*
					loop15:
					while (true) {
						int alt15=2;
						int LA15_0 = input.LA(1);
						if ( (LA15_0==WhiteChar) ) {
							alt15=1;
						}

						switch (alt15) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:9: WhiteChar
							{
							WhiteChar51=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom700);  
							stream_WhiteChar.add(WhiteChar51);

							}
							break;

						default :
							break loop15;
						}
					}

					char_literal52=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom703);  
					stream_OPENPARENTEHSIS.add(char_literal52);

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:24: ( WhiteChar )*
					loop16:
					while (true) {
						int alt16=2;
						int LA16_0 = input.LA(1);
						if ( (LA16_0==WhiteChar) ) {
							alt16=1;
						}

						switch (alt16) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:24: WhiteChar
							{
							WhiteChar53=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom705);  
							stream_WhiteChar.add(WhiteChar53);

							}
							break;

						default :
							break loop16;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom708);
					primary_expression54=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression54.getTree());
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:54: ( WhiteChar )*
					loop17:
					while (true) {
						int alt17=2;
						int LA17_0 = input.LA(1);
						if ( (LA17_0==WhiteChar) ) {
							alt17=1;
						}

						switch (alt17) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:54: WhiteChar
							{
							WhiteChar55=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom710);  
							stream_WhiteChar.add(WhiteChar55);

							}
							break;

						default :
							break loop17;
						}
					}

					char_literal56=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom713);  
					stream_CLOSEPARENTEHSIS.add(char_literal56);

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
					// 181:69: -> ^( NotDoubleMapCondition primary_expression )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:72: ^( NotDoubleMapCondition primary_expression )
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:1: booleanhistory : firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) ;
	public final ParameterizedIndicatorsParser.booleanhistory_return booleanhistory() throws RecognitionException {
		ParameterizedIndicatorsParser.booleanhistory_return retval = new ParameterizedIndicatorsParser.booleanhistory_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar57=null;
		ParserRuleReturnScope firstOp =null;
		ParserRuleReturnScope presetcondition58 =null;
		ParserRuleReturnScope opcmpcondition59 =null;
		ParserRuleReturnScope constantcmp60 =null;

		CommonTree WhiteChar57_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_constantcmp=new RewriteRuleSubtreeStream(adaptor,"rule constantcmp");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");
		RewriteRuleSubtreeStream stream_opcmpcondition=new RewriteRuleSubtreeStream(adaptor,"rule opcmpcondition");
		RewriteRuleSubtreeStream stream_presetcondition=new RewriteRuleSubtreeStream(adaptor,"rule presetcondition");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:16: (firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:18: firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			{
			pushFollow(FOLLOW_operand_in_booleanhistory735);
			firstOp=operand();
			state._fsp--;

			stream_operand.add(firstOp.getTree());
			WhiteChar57=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_booleanhistory737);  
			stream_WhiteChar.add(WhiteChar57);

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:44: ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			int alt19=3;
			switch ( input.LA(1) ) {
			case 51:
			case 53:
			case 57:
			case 58:
			case 66:
			case 67:
			case 68:
			case 69:
			case 72:
			case 73:
				{
				alt19=1;
				}
				break;
			case 50:
			case 52:
			case 54:
			case 59:
			case 63:
				{
				alt19=2;
				}
				break;
			case 55:
			case 60:
			case 64:
				{
				alt19=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}
			switch (alt19) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:46: presetcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_presetcondition_in_booleanhistory741);
					presetcondition58=presetcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_presetcondition.add(presetcondition58.getTree());
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
					// 184:78: -> presetcondition
					{
						adaptor.addChild(root_0, stream_presetcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:100: opcmpcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_opcmpcondition_in_booleanhistory752);
					opcmpcondition59=opcmpcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_opcmpcondition.add(opcmpcondition59.getTree());
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
					// 184:132: -> opcmpcondition
					{
						adaptor.addChild(root_0, stream_opcmpcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:152: constantcmp[$firstOp.tree]
					{
					pushFollow(FOLLOW_constantcmp_in_booleanhistory763);
					constantcmp60=constantcmp((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_constantcmp.add(constantcmp60.getTree());
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
					// 184:181: -> constantcmp
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:1: operand : ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ) |opName= Operation -> Operation );
	public final ParameterizedIndicatorsParser.operand_return operand() throws RecognitionException {
		ParameterizedIndicatorsParser.operand_return retval = new ParameterizedIndicatorsParser.operand_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token opName=null;
		Token HistoricalData61=null;

		CommonTree opName_tree=null;
		CommonTree HistoricalData61_tree=null;
		RewriteRuleTokenStream stream_Operation=new RewriteRuleTokenStream(adaptor,"token Operation");
		RewriteRuleTokenStream stream_HistoricalData=new RewriteRuleTokenStream(adaptor,"token HistoricalData");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:9: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ) |opName= Operation -> Operation )
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0==HistoricalData) ) {
				alt20=1;
			}
			else if ( (LA20_0==Operation) ) {
				alt20=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 20, 0, input);
				throw nvae;
			}

			switch (alt20) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:11: HistoricalData
					{
					HistoricalData61=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_operand780);  
					stream_HistoricalData.add(HistoricalData61);

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
					// 185:26: -> ^( StockOperation ^( OperationOutput HistoricalData ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:29: ^( StockOperation ^( OperationOutput HistoricalData ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:46: ^( OperationOutput HistoricalData )
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
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:83: opName= Operation
					{
					opName=(Token)match(input,Operation,FOLLOW_Operation_in_operand800);  
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
					// 185:137: -> Operation
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:1: constant : NumberToken -> ^( Number NumberToken ) ;
	public final ParameterizedIndicatorsParser.constant_return constant() throws RecognitionException {
		ParameterizedIndicatorsParser.constant_return retval = new ParameterizedIndicatorsParser.constant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NumberToken62=null;

		CommonTree NumberToken62_tree=null;
		RewriteRuleTokenStream stream_NumberToken=new RewriteRuleTokenStream(adaptor,"token NumberToken");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:10: ( NumberToken -> ^( Number NumberToken ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:13: NumberToken
			{
			NumberToken62=(Token)match(input,NumberToken,FOLLOW_NumberToken_in_constant814);  
			stream_NumberToken.add(NumberToken62);

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
			// 186:25: -> ^( Number NumberToken )
			{
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:28: ^( Number NumberToken )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_1);
				adaptor.addChild(root_1, stream_NumberToken.nextNode());
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
	// $ANTLR end "constant"


	public static class lenient_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "lenient"
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:1: lenient : ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ;
	public final ParameterizedIndicatorsParser.lenient_return lenient() throws RecognitionException {
		ParameterizedIndicatorsParser.lenient_return retval = new ParameterizedIndicatorsParser.lenient_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar63=null;
		Token LENIENT64=null;

		CommonTree WhiteChar63_tree=null;
		CommonTree LENIENT64_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_LENIENT=new RewriteRuleTokenStream(adaptor,"token LENIENT");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:9: ( ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			{
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			int alt21=2;
			int LA21_0 = input.LA(1);
			if ( (LA21_0==WhiteChar) ) {
				int LA21_1 = input.LA(2);
				if ( (LA21_1==LENIENT) ) {
					alt21=1;
				}
				else if ( (LA21_1==AND||(LA21_1 >= CLOSEPARENTEHSIS && LA21_1 <= COMMA)||LA21_1==WhiteChar) ) {
					alt21=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 21, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( ((LA21_0 >= CLOSEPARENTEHSIS && LA21_0 <= COMMA)) ) {
				alt21=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 21, 0, input);
				throw nvae;
			}

			switch (alt21) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:12: WhiteChar LENIENT
					{
					WhiteChar63=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_lenient831);  
					stream_WhiteChar.add(WhiteChar63);

					LENIENT64=(Token)match(input,LENIENT,FOLLOW_LENIENT_in_lenient833);  
					stream_LENIENT.add(LENIENT64);

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
					// 187:31: -> ^( String StringToken[\"\\\"TRUE\\\"\"] )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:34: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:70: 
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
					// 187:70: -> ^( String StringToken[\"\\\"FALSE\\\"\"] )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:73: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:1: opcmpcondition[CommonTree firstOp] : ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )? );
	public final ParameterizedIndicatorsParser.opcmpcondition_return opcmpcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.opcmpcondition_return retval = new ParameterizedIndicatorsParser.opcmpcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal65=null;
		Token WhiteChar66=null;
		Token WhiteChar67=null;
		Token string_literal68=null;
		Token WhiteChar69=null;
		Token WhiteChar70=null;
		Token DAYS71=null;
		Token string_literal72=null;
		Token WhiteChar73=null;
		Token WhiteChar74=null;
		Token string_literal75=null;
		Token WhiteChar76=null;
		Token WhiteChar77=null;
		Token DAYS78=null;
		Token string_literal79=null;
		Token WhiteChar80=null;
		Token WhiteChar81=null;
		Token string_literal82=null;
		Token WhiteChar83=null;
		Token WhiteChar84=null;
		Token DAYS85=null;
		Token string_literal86=null;
		Token WhiteChar87=null;
		Token WhiteChar89=null;
		Token string_literal90=null;
		Token WhiteChar91=null;
		Token WhiteChar92=null;
		Token DAYS93=null;
		Token WhiteChar94=null;
		Token string_literal95=null;
		Token WhiteChar96=null;
		Token WhiteChar97=null;
		Token DAYS98=null;
		Token string_literal99=null;
		Token WhiteChar100=null;
		Token WhiteChar102=null;
		Token string_literal103=null;
		Token WhiteChar104=null;
		Token WhiteChar105=null;
		Token DAYS106=null;
		Token WhiteChar107=null;
		Token string_literal108=null;
		Token WhiteChar109=null;
		Token WhiteChar110=null;
		Token DAYS111=null;
		ParserRuleReturnScope secondOp =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope spanningNbDays =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope operand88 =null;
		ParserRuleReturnScope operand101 =null;

		CommonTree string_literal65_tree=null;
		CommonTree WhiteChar66_tree=null;
		CommonTree WhiteChar67_tree=null;
		CommonTree string_literal68_tree=null;
		CommonTree WhiteChar69_tree=null;
		CommonTree WhiteChar70_tree=null;
		CommonTree DAYS71_tree=null;
		CommonTree string_literal72_tree=null;
		CommonTree WhiteChar73_tree=null;
		CommonTree WhiteChar74_tree=null;
		CommonTree string_literal75_tree=null;
		CommonTree WhiteChar76_tree=null;
		CommonTree WhiteChar77_tree=null;
		CommonTree DAYS78_tree=null;
		CommonTree string_literal79_tree=null;
		CommonTree WhiteChar80_tree=null;
		CommonTree WhiteChar81_tree=null;
		CommonTree string_literal82_tree=null;
		CommonTree WhiteChar83_tree=null;
		CommonTree WhiteChar84_tree=null;
		CommonTree DAYS85_tree=null;
		CommonTree string_literal86_tree=null;
		CommonTree WhiteChar87_tree=null;
		CommonTree WhiteChar89_tree=null;
		CommonTree string_literal90_tree=null;
		CommonTree WhiteChar91_tree=null;
		CommonTree WhiteChar92_tree=null;
		CommonTree DAYS93_tree=null;
		CommonTree WhiteChar94_tree=null;
		CommonTree string_literal95_tree=null;
		CommonTree WhiteChar96_tree=null;
		CommonTree WhiteChar97_tree=null;
		CommonTree DAYS98_tree=null;
		CommonTree string_literal99_tree=null;
		CommonTree WhiteChar100_tree=null;
		CommonTree WhiteChar102_tree=null;
		CommonTree string_literal103_tree=null;
		CommonTree WhiteChar104_tree=null;
		CommonTree WhiteChar105_tree=null;
		CommonTree DAYS106_tree=null;
		CommonTree WhiteChar107_tree=null;
		CommonTree string_literal108_tree=null;
		CommonTree WhiteChar109_tree=null;
		CommonTree WhiteChar110_tree=null;
		CommonTree DAYS111_tree=null;
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_59=new RewriteRuleTokenStream(adaptor,"token 59");
		RewriteRuleTokenStream stream_56=new RewriteRuleTokenStream(adaptor,"token 56");
		RewriteRuleTokenStream stream_52=new RewriteRuleTokenStream(adaptor,"token 52");
		RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
		RewriteRuleTokenStream stream_54=new RewriteRuleTokenStream(adaptor,"token 54");
		RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
		RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_50=new RewriteRuleTokenStream(adaptor,"token 50");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:37: ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )? )
			int alt27=5;
			switch ( input.LA(1) ) {
			case 59:
				{
				alt27=1;
				}
				break;
			case 63:
				{
				alt27=2;
				}
				break;
			case 54:
				{
				alt27=3;
				}
				break;
			case 50:
				{
				alt27=4;
				}
				break;
			case 52:
				{
				alt27=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 27, 0, input);
				throw nvae;
			}
			switch (alt27) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:4: 'is above historical' WhiteChar secondOp= operand
					{
					string_literal65=(Token)match(input,59,FOLLOW_59_in_opcmpcondition871);  
					stream_59.add(string_literal65);

					WhiteChar66=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition873);  
					stream_WhiteChar.add(WhiteChar66);

					pushFollow(FOLLOW_operand_in_opcmpcondition877);
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
					// 191:53: -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:56: ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupDoubleMapCondition, "SupDoubleMapCondition"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:80: ^( Number NumberToken[\"0\"] )
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

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )?
					int alt22=2;
					int LA22_0 = input.LA(1);
					if ( (LA22_0==WhiteChar) ) {
						int LA22_1 = input.LA(2);
						if ( (LA22_1==56) ) {
							alt22=1;
						}
					}
					switch (alt22) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:6: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar67=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition903);  
							stream_WhiteChar.add(WhiteChar67);

							string_literal68=(Token)match(input,56,FOLLOW_56_in_opcmpcondition905);  
							stream_56.add(string_literal68);

							WhiteChar69=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition907);  
							stream_WhiteChar.add(WhiteChar69);

							pushFollow(FOLLOW_constant_in_opcmpcondition911);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar70=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition913);  
							stream_WhiteChar.add(WhiteChar70);

							DAYS71=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition915);  
							stream_DAYS.add(DAYS71);

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
							// 192:66: -> ^( SupDoubleMapCondition )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:69: ^( SupDoubleMapCondition )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:4: 'is below historical' WhiteChar secondOp= operand
					{
					string_literal72=(Token)match(input,63,FOLLOW_63_in_opcmpcondition937);  
					stream_63.add(string_literal72);

					WhiteChar73=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition939);  
					stream_WhiteChar.add(WhiteChar73);

					pushFollow(FOLLOW_operand_in_opcmpcondition943);
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
					// 193:53: -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:56: ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfDoubleMapCondition, "InfDoubleMapCondition"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:80: ^( Number NumberToken[\"0\"] )
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

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:5: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )?
					int alt23=2;
					int LA23_0 = input.LA(1);
					if ( (LA23_0==WhiteChar) ) {
						int LA23_1 = input.LA(2);
						if ( (LA23_1==56) ) {
							alt23=1;
						}
					}
					switch (alt23) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar74=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition971);  
							stream_WhiteChar.add(WhiteChar74);

							string_literal75=(Token)match(input,56,FOLLOW_56_in_opcmpcondition973);  
							stream_56.add(string_literal75);

							WhiteChar76=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition975);  
							stream_WhiteChar.add(WhiteChar76);

							pushFollow(FOLLOW_constant_in_opcmpcondition979);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar77=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition981);  
							stream_WhiteChar.add(WhiteChar77);

							DAYS78=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition983);  
							stream_DAYS.add(DAYS78);

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
							// 194:67: -> ^( InfDoubleMapCondition )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:70: ^( InfDoubleMapCondition )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:4: 'equals historical' WhiteChar secondOp= operand
					{
					string_literal79=(Token)match(input,54,FOLLOW_54_in_opcmpcondition1005);  
					stream_54.add(string_literal79);

					WhiteChar80=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1007);  
					stream_WhiteChar.add(WhiteChar80);

					pushFollow(FOLLOW_operand_in_opcmpcondition1011);
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
					// 195:51: -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:54: ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualDoubleMapCondition, "EqualDoubleMapCondition"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:80: ^( Number NumberToken[\"0\"] )
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

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:5: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )?
					int alt24=2;
					int LA24_0 = input.LA(1);
					if ( (LA24_0==WhiteChar) ) {
						int LA24_1 = input.LA(2);
						if ( (LA24_1==56) ) {
							alt24=1;
						}
					}
					switch (alt24) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar81=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1038);  
							stream_WhiteChar.add(WhiteChar81);

							string_literal82=(Token)match(input,56,FOLLOW_56_in_opcmpcondition1040);  
							stream_56.add(string_literal82);

							WhiteChar83=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1042);  
							stream_WhiteChar.add(WhiteChar83);

							pushFollow(FOLLOW_constant_in_opcmpcondition1046);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar84=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1048);  
							stream_WhiteChar.add(WhiteChar84);

							DAYS85=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1050);  
							stream_DAYS.add(DAYS85);

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
							// 196:67: -> ^( EqualDoubleMapCondition )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:70: ^( EqualDoubleMapCondition )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:3: ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:3: ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:4: 'crosses down historical' WhiteChar operand
					{
					string_literal86=(Token)match(input,50,FOLLOW_50_in_opcmpcondition1075);  
					stream_50.add(string_literal86);

					WhiteChar87=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1077);  
					stream_WhiteChar.add(WhiteChar87);

					pushFollow(FOLLOW_operand_in_opcmpcondition1079);
					operand88=operand();
					state._fsp--;

					stream_operand.add(operand88.getTree());
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
					// 198:48: -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:51: ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:81: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:110: ^( Number NumberToken[\"0.0\"] )
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

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:9: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )?
					int alt25=2;
					int LA25_0 = input.LA(1);
					if ( (LA25_0==WhiteChar) ) {
						int LA25_1 = input.LA(2);
						if ( (LA25_1==74) ) {
							alt25=1;
						}
					}
					switch (alt25) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:11: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar89=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1116);  
							stream_WhiteChar.add(WhiteChar89);

							string_literal90=(Token)match(input,74,FOLLOW_74_in_opcmpcondition1118);  
							stream_74.add(string_literal90);

							WhiteChar91=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1120);  
							stream_WhiteChar.add(WhiteChar91);

							pushFollow(FOLLOW_constant_in_opcmpcondition1124);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar92=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1126);  
							stream_WhiteChar.add(WhiteChar92);

							DAYS93=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1128);  
							stream_DAYS.add(DAYS93);

							WhiteChar94=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1141);  
							stream_WhiteChar.add(WhiteChar94);

							string_literal95=(Token)match(input,71,FOLLOW_71_in_opcmpcondition1143);  
							stream_71.add(string_literal95);

							WhiteChar96=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1145);  
							stream_WhiteChar.add(WhiteChar96);

							pushFollow(FOLLOW_constant_in_opcmpcondition1149);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar97=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1151);  
							stream_WhiteChar.add(WhiteChar97);

							DAYS98=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1153);  
							stream_DAYS.add(DAYS98);

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
							// 201:11: -> ^( CrossDownDoubleMapCondition operand )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:14: ^( CrossDownDoubleMapCondition operand )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:3: ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:3: ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:4: 'crosses up historical' WhiteChar operand
					{
					string_literal99=(Token)match(input,52,FOLLOW_52_in_opcmpcondition1197);  
					stream_52.add(string_literal99);

					WhiteChar100=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1199);  
					stream_WhiteChar.add(WhiteChar100);

					pushFollow(FOLLOW_operand_in_opcmpcondition1201);
					operand101=operand();
					state._fsp--;

					stream_operand.add(operand101.getTree());
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
					// 203:46: -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:49: ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:77: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:106: ^( Number NumberToken[\"0.0\"] )
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

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:8: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )?
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
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:10: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar102=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1239);  
							stream_WhiteChar.add(WhiteChar102);

							string_literal103=(Token)match(input,74,FOLLOW_74_in_opcmpcondition1241);  
							stream_74.add(string_literal103);

							WhiteChar104=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1243);  
							stream_WhiteChar.add(WhiteChar104);

							pushFollow(FOLLOW_constant_in_opcmpcondition1247);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar105=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1249);  
							stream_WhiteChar.add(WhiteChar105);

							DAYS106=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1251);  
							stream_DAYS.add(DAYS106);

							WhiteChar107=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1262);  
							stream_WhiteChar.add(WhiteChar107);

							string_literal108=(Token)match(input,71,FOLLOW_71_in_opcmpcondition1264);  
							stream_71.add(string_literal108);

							WhiteChar109=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1266);  
							stream_WhiteChar.add(WhiteChar109);

							pushFollow(FOLLOW_constant_in_opcmpcondition1270);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar110=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1272);  
							stream_WhiteChar.add(WhiteChar110);

							DAYS111=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1274);  
							stream_DAYS.add(DAYS111);

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
							// 206:10: -> ^( CrossUpDoubleMapCondition operand )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:13: ^( CrossUpDoubleMapCondition operand )
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:1: constantcmp[CommonTree firstOp] : ( ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )? );
	public final ParameterizedIndicatorsParser.constantcmp_return constantcmp(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.constantcmp_return retval = new ParameterizedIndicatorsParser.constantcmp_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal112=null;
		Token WhiteChar113=null;
		Token WhiteChar114=null;
		Token string_literal115=null;
		Token WhiteChar116=null;
		Token WhiteChar117=null;
		Token DAYS118=null;
		Token WhiteChar119=null;
		Token string_literal120=null;
		Token WhiteChar121=null;
		Token WhiteChar122=null;
		Token DAYS123=null;
		Token string_literal124=null;
		Token WhiteChar125=null;
		Token WhiteChar126=null;
		Token string_literal127=null;
		Token WhiteChar128=null;
		Token WhiteChar129=null;
		Token DAYS130=null;
		Token WhiteChar131=null;
		Token string_literal132=null;
		Token WhiteChar133=null;
		Token WhiteChar134=null;
		Token DAYS135=null;
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
		ParserRuleReturnScope threshold =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope forNbDays =null;

		CommonTree string_literal112_tree=null;
		CommonTree WhiteChar113_tree=null;
		CommonTree WhiteChar114_tree=null;
		CommonTree string_literal115_tree=null;
		CommonTree WhiteChar116_tree=null;
		CommonTree WhiteChar117_tree=null;
		CommonTree DAYS118_tree=null;
		CommonTree WhiteChar119_tree=null;
		CommonTree string_literal120_tree=null;
		CommonTree WhiteChar121_tree=null;
		CommonTree WhiteChar122_tree=null;
		CommonTree DAYS123_tree=null;
		CommonTree string_literal124_tree=null;
		CommonTree WhiteChar125_tree=null;
		CommonTree WhiteChar126_tree=null;
		CommonTree string_literal127_tree=null;
		CommonTree WhiteChar128_tree=null;
		CommonTree WhiteChar129_tree=null;
		CommonTree DAYS130_tree=null;
		CommonTree WhiteChar131_tree=null;
		CommonTree string_literal132_tree=null;
		CommonTree WhiteChar133_tree=null;
		CommonTree WhiteChar134_tree=null;
		CommonTree DAYS135_tree=null;
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
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_56=new RewriteRuleTokenStream(adaptor,"token 56");
		RewriteRuleTokenStream stream_55=new RewriteRuleTokenStream(adaptor,"token 55");
		RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
		RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
		RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:34: ( ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )? )
			int alt31=3;
			switch ( input.LA(1) ) {
			case 55:
				{
				alt31=1;
				}
				break;
			case 60:
				{
				alt31=2;
				}
				break;
			case 64:
				{
				alt31=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 31, 0, input);
				throw nvae;
			}
			switch (alt31) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:4: 'equals threshold' WhiteChar threshold= constant
					{
					string_literal112=(Token)match(input,55,FOLLOW_55_in_constantcmp1324);  
					stream_55.add(string_literal112);

					WhiteChar113=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1326);  
					stream_WhiteChar.add(WhiteChar113);

					pushFollow(FOLLOW_constant_in_constantcmp1330);
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
					// 211:52: -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:55: ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualConstantCondition, "EqualConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:117: ^( Number NumberToken[\"0\"] )
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

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )?
					int alt28=2;
					int LA28_0 = input.LA(1);
					if ( (LA28_0==WhiteChar) ) {
						int LA28_1 = input.LA(2);
						if ( (LA28_1==71) ) {
							alt28=1;
						}
					}
					switch (alt28) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar114=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1365);  
							stream_WhiteChar.add(WhiteChar114);

							string_literal115=(Token)match(input,71,FOLLOW_71_in_constantcmp1367);  
							stream_71.add(string_literal115);

							WhiteChar116=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1369);  
							stream_WhiteChar.add(WhiteChar116);

							pushFollow(FOLLOW_constant_in_constantcmp1373);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar117=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1375);  
							stream_WhiteChar.add(WhiteChar117);

							DAYS118=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1377);  
							stream_DAYS.add(DAYS118);

							WhiteChar119=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1379);  
							stream_WhiteChar.add(WhiteChar119);

							string_literal120=(Token)match(input,56,FOLLOW_56_in_constantcmp1381);  
							stream_56.add(string_literal120);

							WhiteChar121=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1383);  
							stream_WhiteChar.add(WhiteChar121);

							pushFollow(FOLLOW_constant_in_constantcmp1387);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar122=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1389);  
							stream_WhiteChar.add(WhiteChar122);

							DAYS123=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1391);  
							stream_DAYS.add(DAYS123);

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
							// 212:129: -> ^( EqualConstantCondition )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:132: ^( EqualConstantCondition )
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
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:4: 'is above threshold' WhiteChar threshold= constant
					{
					string_literal124=(Token)match(input,60,FOLLOW_60_in_constantcmp1420);  
					stream_60.add(string_literal124);

					WhiteChar125=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1422);  
					stream_WhiteChar.add(WhiteChar125);

					pushFollow(FOLLOW_constant_in_constantcmp1426);
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
					// 214:54: -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:57: ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupConstantCondition, "SupConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:116: ^( Number NumberToken[\"0\"] )
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

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )?
					int alt29=2;
					int LA29_0 = input.LA(1);
					if ( (LA29_0==WhiteChar) ) {
						int LA29_1 = input.LA(2);
						if ( (LA29_1==71) ) {
							alt29=1;
						}
					}
					switch (alt29) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar126=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1460);  
							stream_WhiteChar.add(WhiteChar126);

							string_literal127=(Token)match(input,71,FOLLOW_71_in_constantcmp1462);  
							stream_71.add(string_literal127);

							WhiteChar128=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1464);  
							stream_WhiteChar.add(WhiteChar128);

							pushFollow(FOLLOW_constant_in_constantcmp1468);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar129=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1470);  
							stream_WhiteChar.add(WhiteChar129);

							DAYS130=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1472);  
							stream_DAYS.add(DAYS130);

							WhiteChar131=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1474);  
							stream_WhiteChar.add(WhiteChar131);

							string_literal132=(Token)match(input,56,FOLLOW_56_in_constantcmp1476);  
							stream_56.add(string_literal132);

							WhiteChar133=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1478);  
							stream_WhiteChar.add(WhiteChar133);

							pushFollow(FOLLOW_constant_in_constantcmp1482);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar134=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1484);  
							stream_WhiteChar.add(WhiteChar134);

							DAYS135=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1486);  
							stream_DAYS.add(DAYS135);

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
							// 215:129: -> ^( SupConstantCondition )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:132: ^( SupConstantCondition )
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
				case 3 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:4: 'is below threshold' WhiteChar threshold= constant
					{
					string_literal136=(Token)match(input,64,FOLLOW_64_in_constantcmp1515);  
					stream_64.add(string_literal136);

					WhiteChar137=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1517);  
					stream_WhiteChar.add(WhiteChar137);

					pushFollow(FOLLOW_constant_in_constantcmp1521);
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
					// 217:54: -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:57: ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfConstantCondition, "InfConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:117: ^( Number NumberToken[\"0\"] )
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

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )?
					int alt30=2;
					int LA30_0 = input.LA(1);
					if ( (LA30_0==WhiteChar) ) {
						int LA30_1 = input.LA(2);
						if ( (LA30_1==71) ) {
							alt30=1;
						}
					}
					switch (alt30) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar138=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1556);  
							stream_WhiteChar.add(WhiteChar138);

							string_literal139=(Token)match(input,71,FOLLOW_71_in_constantcmp1558);  
							stream_71.add(string_literal139);

							WhiteChar140=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1560);  
							stream_WhiteChar.add(WhiteChar140);

							pushFollow(FOLLOW_constant_in_constantcmp1564);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar141=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1566);  
							stream_WhiteChar.add(WhiteChar141);

							DAYS142=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1568);  
							stream_DAYS.add(DAYS142);

							WhiteChar143=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1570);  
							stream_WhiteChar.add(WhiteChar143);

							string_literal144=(Token)match(input,56,FOLLOW_56_in_constantcmp1572);  
							stream_56.add(string_literal144);

							WhiteChar145=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1574);  
							stream_WhiteChar.add(WhiteChar145);

							pushFollow(FOLLOW_constant_in_constantcmp1578);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar146=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1580);  
							stream_WhiteChar.add(WhiteChar146);

							DAYS147=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1582);  
							stream_DAYS.add(DAYS147);

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
							// 218:129: -> ^( InfConstantCondition )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:132: ^( InfConstantCondition )
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:1: presetcondition[CommonTree firstOp] : ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) ) );
	public final ParameterizedIndicatorsParser.presetcondition_return presetcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.presetcondition_return retval = new ParameterizedIndicatorsParser.presetcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal148=null;
		Token WhiteChar149=null;
		Token string_literal150=null;
		Token WhiteChar151=null;
		Token PERCENT152=null;
		Token WhiteChar153=null;
		Token string_literal154=null;
		Token WhiteChar155=null;
		Token WhiteChar156=null;
		Token DAYS157=null;
		Token string_literal158=null;
		Token WhiteChar159=null;
		Token string_literal160=null;
		Token WhiteChar161=null;
		Token PERCENT162=null;
		Token WhiteChar163=null;
		Token string_literal164=null;
		Token WhiteChar165=null;
		Token WhiteChar166=null;
		Token DAYS167=null;
		Token string_literal168=null;
		Token WhiteChar169=null;
		Token PERCENT170=null;
		Token WhiteChar171=null;
		Token string_literal172=null;
		Token WhiteChar173=null;
		Token WhiteChar174=null;
		Token DAYS175=null;
		Token WhiteChar176=null;
		Token string_literal177=null;
		Token WhiteChar178=null;
		Token WhiteChar179=null;
		Token DAYS180=null;
		Token string_literal181=null;
		Token WhiteChar182=null;
		Token PERCENT183=null;
		Token WhiteChar184=null;
		Token string_literal185=null;
		Token WhiteChar186=null;
		Token WhiteChar187=null;
		Token DAYS188=null;
		Token WhiteChar189=null;
		Token string_literal190=null;
		Token WhiteChar191=null;
		Token WhiteChar192=null;
		Token DAYS193=null;
		Token string_literal194=null;
		Token WhiteChar195=null;
		Token WhiteChar196=null;
		Token string_literal197=null;
		Token WhiteChar198=null;
		Token WhiteChar199=null;
		Token DAYS200=null;
		Token WhiteChar201=null;
		Token string_literal202=null;
		Token WhiteChar203=null;
		Token WhiteChar204=null;
		Token DAYS205=null;
		Token string_literal206=null;
		Token WhiteChar207=null;
		Token WhiteChar208=null;
		Token string_literal209=null;
		Token WhiteChar210=null;
		Token WhiteChar211=null;
		Token DAYS212=null;
		Token WhiteChar213=null;
		Token string_literal214=null;
		Token WhiteChar215=null;
		Token WhiteChar216=null;
		Token DAYS217=null;
		Token string_literal218=null;
		Token WhiteChar219=null;
		Token WhiteChar220=null;
		Token DAYS221=null;
		Token string_literal222=null;
		Token WhiteChar223=null;
		Token WhiteChar224=null;
		Token DAYS225=null;
		Token string_literal226=null;
		Token WhiteChar227=null;
		Token WhiteChar228=null;
		Token DAYS229=null;
		Token string_literal230=null;
		Token WhiteChar231=null;
		Token WhiteChar232=null;
		Token DAYS233=null;
		ParserRuleReturnScope percentdown =null;
		ParserRuleReturnScope spanningNbDays =null;
		ParserRuleReturnScope percentup =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope threshold =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope nbDays =null;

		CommonTree string_literal148_tree=null;
		CommonTree WhiteChar149_tree=null;
		CommonTree string_literal150_tree=null;
		CommonTree WhiteChar151_tree=null;
		CommonTree PERCENT152_tree=null;
		CommonTree WhiteChar153_tree=null;
		CommonTree string_literal154_tree=null;
		CommonTree WhiteChar155_tree=null;
		CommonTree WhiteChar156_tree=null;
		CommonTree DAYS157_tree=null;
		CommonTree string_literal158_tree=null;
		CommonTree WhiteChar159_tree=null;
		CommonTree string_literal160_tree=null;
		CommonTree WhiteChar161_tree=null;
		CommonTree PERCENT162_tree=null;
		CommonTree WhiteChar163_tree=null;
		CommonTree string_literal164_tree=null;
		CommonTree WhiteChar165_tree=null;
		CommonTree WhiteChar166_tree=null;
		CommonTree DAYS167_tree=null;
		CommonTree string_literal168_tree=null;
		CommonTree WhiteChar169_tree=null;
		CommonTree PERCENT170_tree=null;
		CommonTree WhiteChar171_tree=null;
		CommonTree string_literal172_tree=null;
		CommonTree WhiteChar173_tree=null;
		CommonTree WhiteChar174_tree=null;
		CommonTree DAYS175_tree=null;
		CommonTree WhiteChar176_tree=null;
		CommonTree string_literal177_tree=null;
		CommonTree WhiteChar178_tree=null;
		CommonTree WhiteChar179_tree=null;
		CommonTree DAYS180_tree=null;
		CommonTree string_literal181_tree=null;
		CommonTree WhiteChar182_tree=null;
		CommonTree PERCENT183_tree=null;
		CommonTree WhiteChar184_tree=null;
		CommonTree string_literal185_tree=null;
		CommonTree WhiteChar186_tree=null;
		CommonTree WhiteChar187_tree=null;
		CommonTree DAYS188_tree=null;
		CommonTree WhiteChar189_tree=null;
		CommonTree string_literal190_tree=null;
		CommonTree WhiteChar191_tree=null;
		CommonTree WhiteChar192_tree=null;
		CommonTree DAYS193_tree=null;
		CommonTree string_literal194_tree=null;
		CommonTree WhiteChar195_tree=null;
		CommonTree WhiteChar196_tree=null;
		CommonTree string_literal197_tree=null;
		CommonTree WhiteChar198_tree=null;
		CommonTree WhiteChar199_tree=null;
		CommonTree DAYS200_tree=null;
		CommonTree WhiteChar201_tree=null;
		CommonTree string_literal202_tree=null;
		CommonTree WhiteChar203_tree=null;
		CommonTree WhiteChar204_tree=null;
		CommonTree DAYS205_tree=null;
		CommonTree string_literal206_tree=null;
		CommonTree WhiteChar207_tree=null;
		CommonTree WhiteChar208_tree=null;
		CommonTree string_literal209_tree=null;
		CommonTree WhiteChar210_tree=null;
		CommonTree WhiteChar211_tree=null;
		CommonTree DAYS212_tree=null;
		CommonTree WhiteChar213_tree=null;
		CommonTree string_literal214_tree=null;
		CommonTree WhiteChar215_tree=null;
		CommonTree WhiteChar216_tree=null;
		CommonTree DAYS217_tree=null;
		CommonTree string_literal218_tree=null;
		CommonTree WhiteChar219_tree=null;
		CommonTree WhiteChar220_tree=null;
		CommonTree DAYS221_tree=null;
		CommonTree string_literal222_tree=null;
		CommonTree WhiteChar223_tree=null;
		CommonTree WhiteChar224_tree=null;
		CommonTree DAYS225_tree=null;
		CommonTree string_literal226_tree=null;
		CommonTree WhiteChar227_tree=null;
		CommonTree WhiteChar228_tree=null;
		CommonTree DAYS229_tree=null;
		CommonTree string_literal230_tree=null;
		CommonTree WhiteChar231_tree=null;
		CommonTree WhiteChar232_tree=null;
		CommonTree DAYS233_tree=null;
		RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
		RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
		RewriteRuleTokenStream stream_58=new RewriteRuleTokenStream(adaptor,"token 58");
		RewriteRuleTokenStream stream_57=new RewriteRuleTokenStream(adaptor,"token 57");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_56=new RewriteRuleTokenStream(adaptor,"token 56");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_51=new RewriteRuleTokenStream(adaptor,"token 51");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_53=new RewriteRuleTokenStream(adaptor,"token 53");
		RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
		RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
		RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
		RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:39: ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) ) )
			int alt38=10;
			switch ( input.LA(1) ) {
			case 72:
				{
				alt38=1;
				}
				break;
			case 73:
				{
				alt38=2;
				}
				break;
			case 57:
				{
				alt38=3;
				}
				break;
			case 58:
				{
				alt38=4;
				}
				break;
			case 53:
				{
				alt38=5;
				}
				break;
			case 51:
				{
				alt38=6;
				}
				break;
			case 66:
				{
				alt38=7;
				}
				break;
			case 67:
				{
				alt38=8;
				}
				break;
			case 68:
				{
				alt38=9;
				}
				break;
			case 69:
				{
				alt38=10;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 38, 0, input);
				throw nvae;
			}
			switch (alt38) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:4: 'reverses down'
					{
					string_literal148=(Token)match(input,72,FOLLOW_72_in_presetcondition1620);  
					stream_72.add(string_literal148);

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
					// 223:20: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:23: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:42: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:70: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:99: ^( Number NumberToken[\"1.0\"] )
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

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:7: ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					int alt32=2;
					int LA32_0 = input.LA(1);
					if ( (LA32_0==WhiteChar) ) {
						int LA32_1 = input.LA(2);
						if ( (LA32_1==70) ) {
							alt32=1;
						}
					}
					switch (alt32) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:9: WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar149=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1660);  
							stream_WhiteChar.add(WhiteChar149);

							string_literal150=(Token)match(input,70,FOLLOW_70_in_presetcondition1662);  
							stream_70.add(string_literal150);

							WhiteChar151=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1664);  
							stream_WhiteChar.add(WhiteChar151);

							pushFollow(FOLLOW_constant_in_presetcondition1668);
							percentdown=constant();
							state._fsp--;

							stream_constant.add(percentdown.getTree());
							PERCENT152=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition1670);  
							stream_PERCENT.add(PERCENT152);

							WhiteChar153=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1672);  
							stream_WhiteChar.add(WhiteChar153);

							string_literal154=(Token)match(input,74,FOLLOW_74_in_presetcondition1674);  
							stream_74.add(string_literal154);

							WhiteChar155=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1676);  
							stream_WhiteChar.add(WhiteChar155);

							pushFollow(FOLLOW_constant_in_presetcondition1680);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar156=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1682);  
							stream_WhiteChar.add(WhiteChar156);

							DAYS157=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1684);  
							stream_DAYS.add(DAYS157);

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
							// 225:7: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:10: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:29: ^( Number NumberToken[\"-1\"] )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:4: 'reverses up'
					{
					string_literal158=(Token)match(input,73,FOLLOW_73_in_presetcondition1728);  
					stream_73.add(string_literal158);

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
					// 227:18: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:21: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:40: ^( Number NumberToken[\"1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:67: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:96: ^( Number NumberToken[\"1.0\"] )
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

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:7: ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					int alt33=2;
					int LA33_0 = input.LA(1);
					if ( (LA33_0==WhiteChar) ) {
						int LA33_1 = input.LA(2);
						if ( (LA33_1==70) ) {
							alt33=1;
						}
					}
					switch (alt33) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:9: WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar159=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1768);  
							stream_WhiteChar.add(WhiteChar159);

							string_literal160=(Token)match(input,70,FOLLOW_70_in_presetcondition1770);  
							stream_70.add(string_literal160);

							WhiteChar161=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1772);  
							stream_WhiteChar.add(WhiteChar161);

							pushFollow(FOLLOW_constant_in_presetcondition1776);
							percentup=constant();
							state._fsp--;

							stream_constant.add(percentup.getTree());
							PERCENT162=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition1778);  
							stream_PERCENT.add(PERCENT162);

							WhiteChar163=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1780);  
							stream_WhiteChar.add(WhiteChar163);

							string_literal164=(Token)match(input,74,FOLLOW_74_in_presetcondition1782);  
							stream_74.add(string_literal164);

							WhiteChar165=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1784);  
							stream_WhiteChar.add(WhiteChar165);

							pushFollow(FOLLOW_constant_in_presetcondition1788);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar166=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1790);  
							stream_WhiteChar.add(WhiteChar166);

							DAYS167=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1792);  
							stream_DAYS.add(DAYS167);

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
							// 229:7: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:10: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:29: ^( Number NumberToken[\"1\"] )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:4: 'goes down more than' WhiteChar percentdown= constant PERCENT
					{
					string_literal168=(Token)match(input,57,FOLLOW_57_in_presetcondition1835);  
					stream_57.add(string_literal168);

					WhiteChar169=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1837);  
					stream_WhiteChar.add(WhiteChar169);

					pushFollow(FOLLOW_constant_in_presetcondition1841);
					percentdown=constant();
					state._fsp--;

					stream_constant.add(percentdown.getTree());
					PERCENT170=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition1843);  
					stream_PERCENT.add(PERCENT170);

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
					// 231:65: -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:68: ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:98: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:127: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:156: ^( Number NumberToken[\"0.0\"] )
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

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt34=2;
					int LA34_0 = input.LA(1);
					if ( (LA34_0==WhiteChar) ) {
						int LA34_1 = input.LA(2);
						if ( (LA34_1==74) ) {
							alt34=1;
						}
					}
					switch (alt34) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar171=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1886);  
							stream_WhiteChar.add(WhiteChar171);

							string_literal172=(Token)match(input,74,FOLLOW_74_in_presetcondition1888);  
							stream_74.add(string_literal172);

							WhiteChar173=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1890);  
							stream_WhiteChar.add(WhiteChar173);

							pushFollow(FOLLOW_constant_in_presetcondition1894);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar174=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1896);  
							stream_WhiteChar.add(WhiteChar174);

							DAYS175=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1898);  
							stream_DAYS.add(DAYS175);

							WhiteChar176=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1909);  
							stream_WhiteChar.add(WhiteChar176);

							string_literal177=(Token)match(input,56,FOLLOW_56_in_presetcondition1911);  
							stream_56.add(string_literal177);

							WhiteChar178=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1913);  
							stream_WhiteChar.add(WhiteChar178);

							pushFollow(FOLLOW_constant_in_presetcondition1917);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar179=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1919);  
							stream_WhiteChar.add(WhiteChar179);

							DAYS180=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1921);  
							stream_DAYS.add(DAYS180);

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
							// 234:7: -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:10: ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentdown!=null?((CommonTree)percentdown.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:75: ^( Number NumberToken[\"0.0\"] )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:4: 'goes up more than' WhiteChar percentup= constant PERCENT
					{
					string_literal181=(Token)match(input,58,FOLLOW_58_in_presetcondition1959);  
					stream_58.add(string_literal181);

					WhiteChar182=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1961);  
					stream_WhiteChar.add(WhiteChar182);

					pushFollow(FOLLOW_constant_in_presetcondition1965);
					percentup=constant();
					state._fsp--;

					stream_constant.add(percentup.getTree());
					PERCENT183=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition1967);  
					stream_PERCENT.add(PERCENT183);

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
					// 235:61: -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:64: ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:92: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:122: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:151: ^( Number NumberToken[\"0.0\"] )
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

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt35=2;
					int LA35_0 = input.LA(1);
					if ( (LA35_0==WhiteChar) ) {
						int LA35_1 = input.LA(2);
						if ( (LA35_1==74) ) {
							alt35=1;
						}
					}
					switch (alt35) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar184=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2011);  
							stream_WhiteChar.add(WhiteChar184);

							string_literal185=(Token)match(input,74,FOLLOW_74_in_presetcondition2013);  
							stream_74.add(string_literal185);

							WhiteChar186=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2015);  
							stream_WhiteChar.add(WhiteChar186);

							pushFollow(FOLLOW_constant_in_presetcondition2019);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar187=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2021);  
							stream_WhiteChar.add(WhiteChar187);

							DAYS188=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2023);  
							stream_DAYS.add(DAYS188);

							WhiteChar189=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2034);  
							stream_WhiteChar.add(WhiteChar189);

							string_literal190=(Token)match(input,56,FOLLOW_56_in_presetcondition2036);  
							stream_56.add(string_literal190);

							WhiteChar191=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2038);  
							stream_WhiteChar.add(WhiteChar191);

							pushFollow(FOLLOW_constant_in_presetcondition2042);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar192=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2044);  
							stream_WhiteChar.add(WhiteChar192);

							DAYS193=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2046);  
							stream_DAYS.add(DAYS193);

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
							// 238:7: -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:10: ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentup!=null?((CommonTree)percentup.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:71: ^( Number NumberToken[\"0.0\"] )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:4: 'crosses up threshold' WhiteChar threshold= constant
					{
					string_literal194=(Token)match(input,53,FOLLOW_53_in_presetcondition2091);  
					stream_53.add(string_literal194);

					WhiteChar195=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2093);  
					stream_WhiteChar.add(WhiteChar195);

					pushFollow(FOLLOW_constant_in_presetcondition2097);
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
					// 240:56: -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:59: ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:95: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:125: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:154: ^( Number NumberToken[\"0.0\"] )
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

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt36=2;
					int LA36_0 = input.LA(1);
					if ( (LA36_0==WhiteChar) ) {
						int LA36_1 = input.LA(2);
						if ( (LA36_1==74) ) {
							alt36=1;
						}
					}
					switch (alt36) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar196=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2140);  
							stream_WhiteChar.add(WhiteChar196);

							string_literal197=(Token)match(input,74,FOLLOW_74_in_presetcondition2142);  
							stream_74.add(string_literal197);

							WhiteChar198=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2144);  
							stream_WhiteChar.add(WhiteChar198);

							pushFollow(FOLLOW_constant_in_presetcondition2148);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar199=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2150);  
							stream_WhiteChar.add(WhiteChar199);

							DAYS200=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2152);  
							stream_DAYS.add(DAYS200);

							WhiteChar201=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2163);  
							stream_WhiteChar.add(WhiteChar201);

							string_literal202=(Token)match(input,71,FOLLOW_71_in_presetcondition2165);  
							stream_71.add(string_literal202);

							WhiteChar203=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2167);  
							stream_WhiteChar.add(WhiteChar203);

							pushFollow(FOLLOW_constant_in_presetcondition2171);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar204=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2173);  
							stream_WhiteChar.add(WhiteChar204);

							DAYS205=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2175);  
							stream_DAYS.add(DAYS205);

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
							// 243:7: -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:10: ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:97: ^( Number NumberToken[\"0.0\"] )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:4: 'crosses down threshold' WhiteChar threshold= constant
					{
					string_literal206=(Token)match(input,51,FOLLOW_51_in_presetcondition2221);  
					stream_51.add(string_literal206);

					WhiteChar207=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2223);  
					stream_WhiteChar.add(WhiteChar207);

					pushFollow(FOLLOW_constant_in_presetcondition2227);
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
					// 245:58: -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:61: ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:99: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:128: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:157: ^( Number NumberToken[\"0.0\"] )
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

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt37=2;
					int LA37_0 = input.LA(1);
					if ( (LA37_0==WhiteChar) ) {
						int LA37_1 = input.LA(2);
						if ( (LA37_1==74) ) {
							alt37=1;
						}
					}
					switch (alt37) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar208=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2269);  
							stream_WhiteChar.add(WhiteChar208);

							string_literal209=(Token)match(input,74,FOLLOW_74_in_presetcondition2271);  
							stream_74.add(string_literal209);

							WhiteChar210=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2273);  
							stream_WhiteChar.add(WhiteChar210);

							pushFollow(FOLLOW_constant_in_presetcondition2277);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar211=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2279);  
							stream_WhiteChar.add(WhiteChar211);

							DAYS212=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2281);  
							stream_DAYS.add(DAYS212);

							WhiteChar213=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2292);  
							stream_WhiteChar.add(WhiteChar213);

							string_literal214=(Token)match(input,71,FOLLOW_71_in_presetcondition2294);  
							stream_71.add(string_literal214);

							WhiteChar215=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2296);  
							stream_WhiteChar.add(WhiteChar215);

							pushFollow(FOLLOW_constant_in_presetcondition2300);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar216=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2302);  
							stream_WhiteChar.add(WhiteChar216);

							DAYS217=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2304);  
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
							// 248:7: -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:10: ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:99: ^( Number NumberToken[\"0.0\"] )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:3: ( 'makes a higher high spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) )
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:3: ( 'makes a higher high spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:4: 'makes a higher high spanning' WhiteChar nbDays= constant WhiteChar DAYS
					{
					string_literal218=(Token)match(input,66,FOLLOW_66_in_presetcondition2348);  
					stream_66.add(string_literal218);

					WhiteChar219=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2350);  
					stream_WhiteChar.add(WhiteChar219);

					pushFollow(FOLLOW_constant_in_presetcondition2354);
					nbDays=constant();
					state._fsp--;

					stream_constant.add(nbDays.getTree());
					WhiteChar220=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2356);  
					stream_WhiteChar.add(WhiteChar220);

					DAYS221=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2358);  
					stream_DAYS.add(DAYS221);

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
					// 250:76: -> ^( HigherHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:79: ^( HigherHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(HigherHighCondition, "HigherHighCondition"), root_1);
						adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:116: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:144: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:3: ( 'makes a higher low spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) )
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:3: ( 'makes a higher low spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:4: 'makes a higher low spanning' WhiteChar nbDays= constant WhiteChar DAYS
					{
					string_literal222=(Token)match(input,67,FOLLOW_67_in_presetcondition2390);  
					stream_67.add(string_literal222);

					WhiteChar223=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2392);  
					stream_WhiteChar.add(WhiteChar223);

					pushFollow(FOLLOW_constant_in_presetcondition2396);
					nbDays=constant();
					state._fsp--;

					stream_constant.add(nbDays.getTree());
					WhiteChar224=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2398);  
					stream_WhiteChar.add(WhiteChar224);

					DAYS225=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2400);  
					stream_DAYS.add(DAYS225);

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
					// 251:75: -> ^( HigherLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:78: ^( HigherLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(HigherLowCondition, "HigherLowCondition"), root_1);
						adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:114: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:142: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:3: ( 'makes a lower high spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) )
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:3: ( 'makes a lower high spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:4: 'makes a lower high spanning' WhiteChar nbDays= constant WhiteChar DAYS
					{
					string_literal226=(Token)match(input,68,FOLLOW_68_in_presetcondition2432);  
					stream_68.add(string_literal226);

					WhiteChar227=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2434);  
					stream_WhiteChar.add(WhiteChar227);

					pushFollow(FOLLOW_constant_in_presetcondition2438);
					nbDays=constant();
					state._fsp--;

					stream_constant.add(nbDays.getTree());
					WhiteChar228=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2440);  
					stream_WhiteChar.add(WhiteChar228);

					DAYS229=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2442);  
					stream_DAYS.add(DAYS229);

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
					// 252:75: -> ^( LowerHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:78: ^( LowerHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LowerHighCondition, "LowerHighCondition"), root_1);
						adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:114: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:142: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:3: ( 'makes a lower low spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) )
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:3: ( 'makes a lower low spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:4: 'makes a lower low spanning' WhiteChar nbDays= constant WhiteChar DAYS
					{
					string_literal230=(Token)match(input,69,FOLLOW_69_in_presetcondition2474);  
					stream_69.add(string_literal230);

					WhiteChar231=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2476);  
					stream_WhiteChar.add(WhiteChar231);

					pushFollow(FOLLOW_constant_in_presetcondition2480);
					nbDays=constant();
					state._fsp--;

					stream_constant.add(nbDays.getTree());
					WhiteChar232=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2482);  
					stream_WhiteChar.add(WhiteChar232);

					DAYS233=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2484);  
					stream_DAYS.add(DAYS233);

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
					// 253:74: -> ^( LowerLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:77: ^( LowerLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LowerLowCondition, "LowerLowCondition"), root_1);
						adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:112: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:140: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
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



	public static final BitSet FOLLOW_bullish_condition_in_complete_expression328 = new BitSet(new long[]{0x6000000000000000L});
	public static final BitSet FOLLOW_bearish_condition_in_complete_expression330 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_also_display_in_complete_expression333 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_bullish_condition361 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition363 = new BitSet(new long[]{0x0000000508100000L});
	public static final BitSet FOLLOW_primary_expression_in_bullish_condition365 = new BitSet(new long[]{0x0001000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition367 = new BitSet(new long[]{0x0001000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bullish_condition370 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition372 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_62_in_bearish_condition388 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition390 = new BitSet(new long[]{0x0000000508100000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_condition393 = new BitSet(new long[]{0x0001000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition395 = new BitSet(new long[]{0x0001000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bearish_condition398 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition400 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_bearish_not_bullish_in_bearish_condition410 = new BitSet(new long[]{0x0001000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition413 = new BitSet(new long[]{0x0001000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bearish_condition416 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition418 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_49_in_also_display435 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display437 = new BitSet(new long[]{0x0000000508100000L});
	public static final BitSet FOLLOW_primary_expression_in_also_display439 = new BitSet(new long[]{0x0001000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display441 = new BitSet(new long[]{0x0001000000000080L});
	public static final BitSet FOLLOW_COMMA_in_also_display444 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_61_in_bearish_not_bullish481 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish490 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_bearish_not_bullish492 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish494 = new BitSet(new long[]{0x0000000508100000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish496 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish524 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_OR_in_bearish_not_bullish526 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish528 = new BitSet(new long[]{0x0000000508100000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish531 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_and_expression_in_primary_expression573 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_expression_in_and_expression588 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_lenient_in_and_expression592 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression595 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_and_expression597 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression599 = new BitSet(new long[]{0x0000000508100000L});
	public static final BitSet FOLLOW_or_expression_in_and_expression601 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_atom_in_or_expression633 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression636 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_OR_in_or_expression638 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression640 = new BitSet(new long[]{0x0000000508100000L});
	public static final BitSet FOLLOW_atom_in_or_expression642 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_booleanhistory_in_atom669 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom676 = new BitSet(new long[]{0x0001000508100000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom678 = new BitSet(new long[]{0x0001000508100000L});
	public static final BitSet FOLLOW_primary_expression_in_atom681 = new BitSet(new long[]{0x0001000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom683 = new BitSet(new long[]{0x0001000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom686 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_atom698 = new BitSet(new long[]{0x0001000100000000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom700 = new BitSet(new long[]{0x0001000100000000L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom703 = new BitSet(new long[]{0x0001000508100000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom705 = new BitSet(new long[]{0x0001000508100000L});
	public static final BitSet FOLLOW_primary_expression_in_atom708 = new BitSet(new long[]{0x0001000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom710 = new BitSet(new long[]{0x0001000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom713 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_booleanhistory735 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_booleanhistory737 = new BitSet(new long[]{0x9EFC000000000000L,0x000000000000033DL});
	public static final BitSet FOLLOW_presetcondition_in_booleanhistory741 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_opcmpcondition_in_booleanhistory752 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constantcmp_in_booleanhistory763 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_operand780 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Operation_in_operand800 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NumberToken_in_constant814 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_lenient831 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_LENIENT_in_lenient833 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_59_in_opcmpcondition871 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition873 = new BitSet(new long[]{0x0000000400100000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition877 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition903 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_56_in_opcmpcondition905 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition907 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition911 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition913 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition915 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_63_in_opcmpcondition937 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition939 = new BitSet(new long[]{0x0000000400100000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition943 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition971 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_56_in_opcmpcondition973 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition975 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition979 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition981 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition983 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_54_in_opcmpcondition1005 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1007 = new BitSet(new long[]{0x0000000400100000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1011 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1038 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_56_in_opcmpcondition1040 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1042 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1046 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1048 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1050 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_50_in_opcmpcondition1075 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1077 = new BitSet(new long[]{0x0000000400100000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1079 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1116 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_opcmpcondition1118 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1120 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1124 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1126 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1128 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1141 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_opcmpcondition1143 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1145 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1149 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1151 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1153 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_52_in_opcmpcondition1197 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1199 = new BitSet(new long[]{0x0000000400100000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1201 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1239 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_opcmpcondition1241 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1243 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1247 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1249 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1251 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1262 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_opcmpcondition1264 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1266 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1270 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1272 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1274 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_55_in_constantcmp1324 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1326 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1330 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1365 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_constantcmp1367 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1369 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1373 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1375 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1377 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1379 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_56_in_constantcmp1381 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1383 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1387 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1389 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1391 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_60_in_constantcmp1420 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1422 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1426 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1460 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_constantcmp1462 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1464 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1468 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1470 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1472 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1474 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_56_in_constantcmp1476 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1478 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1482 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1484 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1486 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_64_in_constantcmp1515 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1517 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1521 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1556 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_constantcmp1558 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1560 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1564 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1566 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1568 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1570 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_56_in_constantcmp1572 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1574 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1578 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1580 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1582 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_72_in_presetcondition1620 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1660 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_presetcondition1662 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1664 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1668 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition1670 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1672 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition1674 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1676 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1680 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1682 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1684 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_73_in_presetcondition1728 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1768 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_presetcondition1770 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1772 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1776 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition1778 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1780 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition1782 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1784 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1788 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1790 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1792 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_57_in_presetcondition1835 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1837 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1841 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition1843 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1886 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition1888 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1890 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1894 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1896 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1898 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1909 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_56_in_presetcondition1911 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1913 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1917 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1919 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1921 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_58_in_presetcondition1959 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1961 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1965 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition1967 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2011 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition2013 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2015 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2019 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2021 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2023 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2034 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_56_in_presetcondition2036 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2038 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2042 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2044 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2046 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_53_in_presetcondition2091 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2093 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2097 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2140 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition2142 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2144 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2148 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2150 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2152 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2163 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_presetcondition2165 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2167 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2171 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2173 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2175 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_51_in_presetcondition2221 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2223 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2227 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2269 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition2271 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2273 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2277 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2279 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2281 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2292 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_presetcondition2294 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2296 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2300 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2302 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2304 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_66_in_presetcondition2348 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2350 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2354 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2356 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2358 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_67_in_presetcondition2390 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2392 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2396 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2398 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2400 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_68_in_presetcondition2432 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2434 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2438 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2440 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2442 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_presetcondition2474 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2476 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2480 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2482 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2484 = new BitSet(new long[]{0x0000000000000002L});
}
