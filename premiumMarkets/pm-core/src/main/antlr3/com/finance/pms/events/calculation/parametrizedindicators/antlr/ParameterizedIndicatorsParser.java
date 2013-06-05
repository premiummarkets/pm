// $ANTLR 3.5 /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2013-06-04 00:11:43
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
		"DAYS", "Double", "DownRatioCondition", "EqualConstantCondition", "EqualDoubleMapCondition", 
		"EventConditionHolder", "HigherHighCondition", "HigherLowCondition", "HistoricalData", 
		"InfConstantCondition", "InfDoubleMapCondition", "LINE_COMMENT", "LowerHighCondition", 
		"LowerLowCondition", "NOT", "NotDoubleMapCondition", "Number", "OPENPARENTEHSIS", 
		"OR", "Operation", "OperationOutput", "OrDoubleMapCondition", "PERCENT", 
		"ReverseCondition", "StockOperation", "StringOperation", "SupConstantCondition", 
		"SupDoubleMapCondition", "Tcheat", "UpRatioCondition", "WS", "WhiteChar", 
		"'crosses down historical'", "'crosses down threshold'", "'crosses up historical'", 
		"'crosses up threshold'", "'equals historical'", "'equals threshold'", 
		"'goes down more than'", "'goes up more than'", "'is above historical'", 
		"'is above threshold'", "'is bearish if not bullish'", "'is bearish when'", 
		"'is below historical'", "'is below threshold'", "'is bullish when'", 
		"'makes a higher high over'", "'makes a higher low over'", "'makes a lower high over'", 
		"'makes a lower low over'", "'more than'", "'over'", "'reverses down'", 
		"'reverses up'", "'smoothing threshold'"
	};
	public static final int EOF=-1;
	public static final int T__45=45;
	public static final int T__46=46;
	public static final int T__47=47;
	public static final int T__48=48;
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
	public static final int Double=14;
	public static final int DownRatioCondition=15;
	public static final int EqualConstantCondition=16;
	public static final int EqualDoubleMapCondition=17;
	public static final int EventConditionHolder=18;
	public static final int HigherHighCondition=19;
	public static final int HigherLowCondition=20;
	public static final int HistoricalData=21;
	public static final int InfConstantCondition=22;
	public static final int InfDoubleMapCondition=23;
	public static final int LINE_COMMENT=24;
	public static final int LowerHighCondition=25;
	public static final int LowerLowCondition=26;
	public static final int NOT=27;
	public static final int NotDoubleMapCondition=28;
	public static final int Number=29;
	public static final int OPENPARENTEHSIS=30;
	public static final int OR=31;
	public static final int Operation=32;
	public static final int OperationOutput=33;
	public static final int OrDoubleMapCondition=34;
	public static final int PERCENT=35;
	public static final int ReverseCondition=36;
	public static final int StockOperation=37;
	public static final int StringOperation=38;
	public static final int SupConstantCondition=39;
	public static final int SupDoubleMapCondition=40;
	public static final int Tcheat=41;
	public static final int UpRatioCondition=42;
	public static final int WS=43;
	public static final int WhiteChar=44;

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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:139:1: complete_expression : bcond= bullish_condition bearish_condition[$bcond.tree] -> ^( EventConditionHolder bullish_condition bearish_condition StringOperation ) ;
	public final ParameterizedIndicatorsParser.complete_expression_return complete_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.complete_expression_return retval = new ParameterizedIndicatorsParser.complete_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope bcond =null;
		ParserRuleReturnScope bearish_condition1 =null;

		RewriteRuleSubtreeStream stream_bullish_condition=new RewriteRuleSubtreeStream(adaptor,"rule bullish_condition");
		RewriteRuleSubtreeStream stream_bearish_condition=new RewriteRuleSubtreeStream(adaptor,"rule bearish_condition");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:139:21: (bcond= bullish_condition bearish_condition[$bcond.tree] -> ^( EventConditionHolder bullish_condition bearish_condition StringOperation ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:140:4: bcond= bullish_condition bearish_condition[$bcond.tree]
			{
			pushFollow(FOLLOW_bullish_condition_in_complete_expression308);
			bcond=bullish_condition();
			state._fsp--;

			stream_bullish_condition.add(bcond.getTree());
			pushFollow(FOLLOW_bearish_condition_in_complete_expression310);
			bearish_condition1=bearish_condition((bcond!=null?((CommonTree)bcond.getTree()):null));
			state._fsp--;

			stream_bearish_condition.add(bearish_condition1.getTree());
			// AST REWRITE
			// elements: bearish_condition, bullish_condition
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 140:59: -> ^( EventConditionHolder bullish_condition bearish_condition StringOperation )
			{
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:140:62: ^( EventConditionHolder bullish_condition bearish_condition StringOperation )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EventConditionHolder, "EventConditionHolder"), root_1);
				adaptor.addChild(root_1, stream_bullish_condition.nextTree());
				adaptor.addChild(root_1, stream_bearish_condition.nextTree());
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:143:1: bullish_condition : 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression ;
	public final ParameterizedIndicatorsParser.bullish_condition_return bullish_condition() throws RecognitionException {
		ParameterizedIndicatorsParser.bullish_condition_return retval = new ParameterizedIndicatorsParser.bullish_condition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal2=null;
		Token WhiteChar3=null;
		Token WhiteChar5=null;
		Token COMMA6=null;
		Token WhiteChar7=null;
		ParserRuleReturnScope primary_expression4 =null;

		CommonTree string_literal2_tree=null;
		CommonTree WhiteChar3_tree=null;
		CommonTree WhiteChar5_tree=null;
		CommonTree COMMA6_tree=null;
		CommonTree WhiteChar7_tree=null;
		RewriteRuleTokenStream stream_59=new RewriteRuleTokenStream(adaptor,"token 59");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:143:19: ( 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:144:2: 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )*
			{
			string_literal2=(Token)match(input,59,FOLLOW_59_in_bullish_condition337);  
			stream_59.add(string_literal2);

			WhiteChar3=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition339);  
			stream_WhiteChar.add(WhiteChar3);

			pushFollow(FOLLOW_primary_expression_in_bullish_condition341);
			primary_expression4=primary_expression();
			state._fsp--;

			stream_primary_expression.add(primary_expression4.getTree());
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:144:49: ( WhiteChar )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==WhiteChar) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:144:49: WhiteChar
					{
					WhiteChar5=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition343);  
					stream_WhiteChar.add(WhiteChar5);

					}
					break;

				default :
					break loop1;
				}
			}

			COMMA6=(Token)match(input,COMMA,FOLLOW_COMMA_in_bullish_condition346);  
			stream_COMMA.add(COMMA6);

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:144:66: ( WhiteChar )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==WhiteChar) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:144:66: WhiteChar
					{
					WhiteChar7=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition348);  
					stream_WhiteChar.add(WhiteChar7);

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
			// 144:77: -> primary_expression
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:146:1: bearish_condition[CommonTree bcond] : ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* COMMA -> bearish_not_bullish );
	public final ParameterizedIndicatorsParser.bearish_condition_return bearish_condition(CommonTree bcond) throws RecognitionException {
		ParameterizedIndicatorsParser.bearish_condition_return retval = new ParameterizedIndicatorsParser.bearish_condition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal8=null;
		Token WhiteChar9=null;
		Token WhiteChar11=null;
		Token COMMA12=null;
		Token WhiteChar14=null;
		Token COMMA15=null;
		ParserRuleReturnScope primary_expression10 =null;
		ParserRuleReturnScope bearish_not_bullish13 =null;

		CommonTree string_literal8_tree=null;
		CommonTree WhiteChar9_tree=null;
		CommonTree WhiteChar11_tree=null;
		CommonTree COMMA12_tree=null;
		CommonTree WhiteChar14_tree=null;
		CommonTree COMMA15_tree=null;
		RewriteRuleTokenStream stream_56=new RewriteRuleTokenStream(adaptor,"token 56");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_bearish_not_bullish=new RewriteRuleSubtreeStream(adaptor,"rule bearish_not_bullish");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:146:37: ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* COMMA -> bearish_not_bullish )
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==56) ) {
				alt5=1;
			}
			else if ( (LA5_0==55) ) {
				alt5=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}

			switch (alt5) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:147:2: 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA
					{
					string_literal8=(Token)match(input,56,FOLLOW_56_in_bearish_condition364);  
					stream_56.add(string_literal8);

					WhiteChar9=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition366);  
					stream_WhiteChar.add(WhiteChar9);

					pushFollow(FOLLOW_primary_expression_in_bearish_condition369);
					primary_expression10=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression10.getTree());
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:147:50: ( WhiteChar )*
					loop3:
					while (true) {
						int alt3=2;
						int LA3_0 = input.LA(1);
						if ( (LA3_0==WhiteChar) ) {
							alt3=1;
						}

						switch (alt3) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:147:50: WhiteChar
							{
							WhiteChar11=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition371);  
							stream_WhiteChar.add(WhiteChar11);

							}
							break;

						default :
							break loop3;
						}
					}

					COMMA12=(Token)match(input,COMMA,FOLLOW_COMMA_in_bearish_condition374);  
					stream_COMMA.add(COMMA12);

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
					// 147:67: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:148:2: bearish_not_bullish[$bcond] ( WhiteChar )* COMMA
					{
					pushFollow(FOLLOW_bearish_not_bullish_in_bearish_condition383);
					bearish_not_bullish13=bearish_not_bullish(bcond);
					state._fsp--;

					stream_bearish_not_bullish.add(bearish_not_bullish13.getTree());
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:148:30: ( WhiteChar )*
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( (LA4_0==WhiteChar) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:148:30: WhiteChar
							{
							WhiteChar14=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition386);  
							stream_WhiteChar.add(WhiteChar14);

							}
							break;

						default :
							break loop4;
						}
					}

					COMMA15=(Token)match(input,COMMA,FOLLOW_COMMA_in_bearish_condition389);  
					stream_COMMA.add(COMMA15);

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
					// 148:47: -> bearish_not_bullish
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


	public static class bearish_not_bullish_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "bearish_not_bullish"
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:151:1: bearish_not_bullish[CommonTree bcond] : 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) ) ;
	public final ParameterizedIndicatorsParser.bearish_not_bullish_return bearish_not_bullish(CommonTree bcond) throws RecognitionException {
		ParameterizedIndicatorsParser.bearish_not_bullish_return retval = new ParameterizedIndicatorsParser.bearish_not_bullish_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal16=null;
		Token WhiteChar17=null;
		Token AND18=null;
		Token WhiteChar19=null;
		Token WhiteChar21=null;
		Token OR22=null;
		Token WhiteChar23=null;
		ParserRuleReturnScope primary_expression20 =null;
		ParserRuleReturnScope primary_expression24 =null;

		CommonTree string_literal16_tree=null;
		CommonTree WhiteChar17_tree=null;
		CommonTree AND18_tree=null;
		CommonTree WhiteChar19_tree=null;
		CommonTree WhiteChar21_tree=null;
		CommonTree OR22_tree=null;
		CommonTree WhiteChar23_tree=null;
		RewriteRuleTokenStream stream_55=new RewriteRuleTokenStream(adaptor,"token 55");
		RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:151:39: ( 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:152:2: 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) )
			{
			string_literal16=(Token)match(input,55,FOLLOW_55_in_bearish_not_bullish406);  
			stream_55.add(string_literal16);

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:153:3: ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) )
			int alt6=3;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==WhiteChar) ) {
				switch ( input.LA(2) ) {
				case AND:
					{
					alt6=1;
					}
					break;
				case OR:
					{
					alt6=2;
					}
					break;
				case COMMA:
				case WhiteChar:
					{
					alt6=3;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 6, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}
			else if ( (LA6_0==COMMA) ) {
				alt6=3;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}

			switch (alt6) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:154:3: WhiteChar AND WhiteChar primary_expression
					{
					WhiteChar17=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish415);  
					stream_WhiteChar.add(WhiteChar17);

					AND18=(Token)match(input,AND,FOLLOW_AND_in_bearish_not_bullish417);  
					stream_AND.add(AND18);

					WhiteChar19=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish419);  
					stream_WhiteChar.add(WhiteChar19);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish421);
					primary_expression20=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression20.getTree());
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
					// 154:46: -> ^( AndDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:154:50: ^( AndDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:154:74: ^( NotDoubleMapCondition )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:3: WhiteChar OR WhiteChar primary_expression
					{
					WhiteChar21=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish442);  
					stream_WhiteChar.add(WhiteChar21);

					OR22=(Token)match(input,OR,FOLLOW_OR_in_bearish_not_bullish444);  
					stream_OR.add(OR22);

					WhiteChar23=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish446);  
					stream_WhiteChar.add(WhiteChar23);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish449);
					primary_expression24=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression24.getTree());
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
					// 155:46: -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:50: ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrDoubleMapCondition, "OrDoubleMapCondition"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:73: ^( NotDoubleMapCondition )
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
					// 156:3: -> ^( NotDoubleMapCondition )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:156:6: ^( NotDoubleMapCondition )
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:160:1: primary_expression : and_expression ;
	public final ParameterizedIndicatorsParser.primary_expression_return primary_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.primary_expression_return retval = new ParameterizedIndicatorsParser.primary_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope and_expression25 =null;


		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:160:20: ( and_expression )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:161:2: and_expression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_and_expression_in_primary_expression491);
			and_expression25=and_expression();
			state._fsp--;

			adaptor.addChild(root_0, and_expression25.getTree());

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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:164:1: and_expression : or_expression ( WhiteChar AND WhiteChar or_expression )* -> ^( AndDoubleMapCondition or_expression ( or_expression )* ) ;
	public final ParameterizedIndicatorsParser.and_expression_return and_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.and_expression_return retval = new ParameterizedIndicatorsParser.and_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar27=null;
		Token AND28=null;
		Token WhiteChar29=null;
		ParserRuleReturnScope or_expression26 =null;
		ParserRuleReturnScope or_expression30 =null;

		CommonTree WhiteChar27_tree=null;
		CommonTree AND28_tree=null;
		CommonTree WhiteChar29_tree=null;
		RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_or_expression=new RewriteRuleSubtreeStream(adaptor,"rule or_expression");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:164:16: ( or_expression ( WhiteChar AND WhiteChar or_expression )* -> ^( AndDoubleMapCondition or_expression ( or_expression )* ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:164:19: or_expression ( WhiteChar AND WhiteChar or_expression )*
			{
			pushFollow(FOLLOW_or_expression_in_and_expression503);
			or_expression26=or_expression();
			state._fsp--;

			stream_or_expression.add(or_expression26.getTree());
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:164:33: ( WhiteChar AND WhiteChar or_expression )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==WhiteChar) ) {
					int LA7_1 = input.LA(2);
					if ( (LA7_1==AND) ) {
						alt7=1;
					}

				}

				switch (alt7) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:164:34: WhiteChar AND WhiteChar or_expression
					{
					WhiteChar27=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression506);  
					stream_WhiteChar.add(WhiteChar27);

					AND28=(Token)match(input,AND,FOLLOW_AND_in_and_expression508);  
					stream_AND.add(AND28);

					WhiteChar29=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression510);  
					stream_WhiteChar.add(WhiteChar29);

					pushFollow(FOLLOW_or_expression_in_and_expression512);
					or_expression30=or_expression();
					state._fsp--;

					stream_or_expression.add(or_expression30.getTree());
					}
					break;

				default :
					break loop7;
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
			// 164:75: -> ^( AndDoubleMapCondition or_expression ( or_expression )* )
			{
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:164:79: ^( AndDoubleMapCondition or_expression ( or_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
				adaptor.addChild(root_1, stream_or_expression.nextTree());
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:164:117: ( or_expression )*
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:165:1: or_expression : atom ( WhiteChar OR WhiteChar atom )* -> ^( OrDoubleMapCondition atom ( atom )* ) ;
	public final ParameterizedIndicatorsParser.or_expression_return or_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.or_expression_return retval = new ParameterizedIndicatorsParser.or_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar32=null;
		Token OR33=null;
		Token WhiteChar34=null;
		ParserRuleReturnScope atom31 =null;
		ParserRuleReturnScope atom35 =null;

		CommonTree WhiteChar32_tree=null;
		CommonTree OR33_tree=null;
		CommonTree WhiteChar34_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
		RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:165:15: ( atom ( WhiteChar OR WhiteChar atom )* -> ^( OrDoubleMapCondition atom ( atom )* ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:165:18: atom ( WhiteChar OR WhiteChar atom )*
			{
			pushFollow(FOLLOW_atom_in_or_expression535);
			atom31=atom();
			state._fsp--;

			stream_atom.add(atom31.getTree());
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:165:23: ( WhiteChar OR WhiteChar atom )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0==WhiteChar) ) {
					int LA8_1 = input.LA(2);
					if ( (LA8_1==OR) ) {
						alt8=1;
					}

				}

				switch (alt8) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:165:24: WhiteChar OR WhiteChar atom
					{
					WhiteChar32=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression538);  
					stream_WhiteChar.add(WhiteChar32);

					OR33=(Token)match(input,OR,FOLLOW_OR_in_or_expression540);  
					stream_OR.add(OR33);

					WhiteChar34=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression542);  
					stream_WhiteChar.add(WhiteChar34);

					pushFollow(FOLLOW_atom_in_or_expression544);
					atom35=atom();
					state._fsp--;

					stream_atom.add(atom35.getTree());
					}
					break;

				default :
					break loop8;
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
			// 165:75: -> ^( OrDoubleMapCondition atom ( atom )* )
			{
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:165:79: ^( OrDoubleMapCondition atom ( atom )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrDoubleMapCondition, "OrDoubleMapCondition"), root_1);
				adaptor.addChild(root_1, stream_atom.nextTree());
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:165:107: ( atom )*
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:1: atom : ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotDoubleMapCondition primary_expression ) );
	public final ParameterizedIndicatorsParser.atom_return atom() throws RecognitionException {
		ParameterizedIndicatorsParser.atom_return retval = new ParameterizedIndicatorsParser.atom_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal37=null;
		Token WhiteChar38=null;
		Token WhiteChar40=null;
		Token char_literal41=null;
		Token string_literal42=null;
		Token WhiteChar43=null;
		Token char_literal44=null;
		Token WhiteChar45=null;
		Token WhiteChar47=null;
		Token char_literal48=null;
		ParserRuleReturnScope booleanhistory36 =null;
		ParserRuleReturnScope primary_expression39 =null;
		ParserRuleReturnScope primary_expression46 =null;

		CommonTree char_literal37_tree=null;
		CommonTree WhiteChar38_tree=null;
		CommonTree WhiteChar40_tree=null;
		CommonTree char_literal41_tree=null;
		CommonTree string_literal42_tree=null;
		CommonTree WhiteChar43_tree=null;
		CommonTree char_literal44_tree=null;
		CommonTree WhiteChar45_tree=null;
		CommonTree WhiteChar47_tree=null;
		CommonTree char_literal48_tree=null;
		RewriteRuleTokenStream stream_CLOSEPARENTEHSIS=new RewriteRuleTokenStream(adaptor,"token CLOSEPARENTEHSIS");
		RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
		RewriteRuleTokenStream stream_OPENPARENTEHSIS=new RewriteRuleTokenStream(adaptor,"token OPENPARENTEHSIS");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:6: ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotDoubleMapCondition primary_expression ) )
			int alt14=3;
			switch ( input.LA(1) ) {
			case HistoricalData:
			case Operation:
				{
				alt14=1;
				}
				break;
			case OPENPARENTEHSIS:
				{
				alt14=2;
				}
				break;
			case NOT:
				{
				alt14=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}
			switch (alt14) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:8: booleanhistory
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_booleanhistory_in_atom586);
					booleanhistory36=booleanhistory();
					state._fsp--;

					adaptor.addChild(root_0, booleanhistory36.getTree());

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:25: '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					char_literal37=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom590);  
					stream_OPENPARENTEHSIS.add(char_literal37);

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:29: ( WhiteChar )*
					loop9:
					while (true) {
						int alt9=2;
						int LA9_0 = input.LA(1);
						if ( (LA9_0==WhiteChar) ) {
							alt9=1;
						}

						switch (alt9) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:29: WhiteChar
							{
							WhiteChar38=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom592);  
							stream_WhiteChar.add(WhiteChar38);

							}
							break;

						default :
							break loop9;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom595);
					primary_expression39=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression39.getTree());
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:59: ( WhiteChar )*
					loop10:
					while (true) {
						int alt10=2;
						int LA10_0 = input.LA(1);
						if ( (LA10_0==WhiteChar) ) {
							alt10=1;
						}

						switch (alt10) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:59: WhiteChar
							{
							WhiteChar40=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom597);  
							stream_WhiteChar.add(WhiteChar40);

							}
							break;

						default :
							break loop10;
						}
					}

					char_literal41=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom600);  
					stream_CLOSEPARENTEHSIS.add(char_literal41);

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
					// 166:74: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:99: 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					string_literal42=(Token)match(input,NOT,FOLLOW_NOT_in_atom609);  
					stream_NOT.add(string_literal42);

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:105: ( WhiteChar )*
					loop11:
					while (true) {
						int alt11=2;
						int LA11_0 = input.LA(1);
						if ( (LA11_0==WhiteChar) ) {
							alt11=1;
						}

						switch (alt11) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:105: WhiteChar
							{
							WhiteChar43=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom611);  
							stream_WhiteChar.add(WhiteChar43);

							}
							break;

						default :
							break loop11;
						}
					}

					char_literal44=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom614);  
					stream_OPENPARENTEHSIS.add(char_literal44);

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:120: ( WhiteChar )*
					loop12:
					while (true) {
						int alt12=2;
						int LA12_0 = input.LA(1);
						if ( (LA12_0==WhiteChar) ) {
							alt12=1;
						}

						switch (alt12) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:120: WhiteChar
							{
							WhiteChar45=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom616);  
							stream_WhiteChar.add(WhiteChar45);

							}
							break;

						default :
							break loop12;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom619);
					primary_expression46=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression46.getTree());
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:150: ( WhiteChar )*
					loop13:
					while (true) {
						int alt13=2;
						int LA13_0 = input.LA(1);
						if ( (LA13_0==WhiteChar) ) {
							alt13=1;
						}

						switch (alt13) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:150: WhiteChar
							{
							WhiteChar47=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom621);  
							stream_WhiteChar.add(WhiteChar47);

							}
							break;

						default :
							break loop13;
						}
					}

					char_literal48=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom624);  
					stream_CLOSEPARENTEHSIS.add(char_literal48);

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
					// 166:165: -> ^( NotDoubleMapCondition primary_expression )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:168: ^( NotDoubleMapCondition primary_expression )
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:168:1: booleanhistory : firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) ;
	public final ParameterizedIndicatorsParser.booleanhistory_return booleanhistory() throws RecognitionException {
		ParameterizedIndicatorsParser.booleanhistory_return retval = new ParameterizedIndicatorsParser.booleanhistory_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar49=null;
		ParserRuleReturnScope firstOp =null;
		ParserRuleReturnScope presetcondition50 =null;
		ParserRuleReturnScope opcmpcondition51 =null;
		ParserRuleReturnScope constantcmp52 =null;

		CommonTree WhiteChar49_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_constantcmp=new RewriteRuleSubtreeStream(adaptor,"rule constantcmp");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");
		RewriteRuleSubtreeStream stream_opcmpcondition=new RewriteRuleSubtreeStream(adaptor,"rule opcmpcondition");
		RewriteRuleSubtreeStream stream_presetcondition=new RewriteRuleSubtreeStream(adaptor,"rule presetcondition");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:168:16: (firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:168:18: firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			{
			pushFollow(FOLLOW_operand_in_booleanhistory643);
			firstOp=operand();
			state._fsp--;

			stream_operand.add(firstOp.getTree());
			WhiteChar49=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_booleanhistory645);  
			stream_WhiteChar.add(WhiteChar49);

			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:168:44: ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			int alt15=3;
			switch ( input.LA(1) ) {
			case 46:
			case 48:
			case 51:
			case 52:
			case 60:
			case 61:
			case 62:
			case 63:
			case 66:
			case 67:
				{
				alt15=1;
				}
				break;
			case 45:
			case 47:
			case 49:
			case 53:
			case 57:
				{
				alt15=2;
				}
				break;
			case 50:
			case 54:
			case 58:
				{
				alt15=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}
			switch (alt15) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:168:46: presetcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_presetcondition_in_booleanhistory649);
					presetcondition50=presetcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_presetcondition.add(presetcondition50.getTree());
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
					// 168:78: -> presetcondition
					{
						adaptor.addChild(root_0, stream_presetcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:168:100: opcmpcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_opcmpcondition_in_booleanhistory660);
					opcmpcondition51=opcmpcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_opcmpcondition.add(opcmpcondition51.getTree());
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
					// 168:132: -> opcmpcondition
					{
						adaptor.addChild(root_0, stream_opcmpcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:168:152: constantcmp[$firstOp.tree]
					{
					pushFollow(FOLLOW_constantcmp_in_booleanhistory671);
					constantcmp52=constantcmp((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_constantcmp.add(constantcmp52.getTree());
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
					// 168:181: -> constantcmp
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:1: operand : ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ) |opName= Operation -> Operation );
	public final ParameterizedIndicatorsParser.operand_return operand() throws RecognitionException {
		ParameterizedIndicatorsParser.operand_return retval = new ParameterizedIndicatorsParser.operand_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token opName=null;
		Token HistoricalData53=null;

		CommonTree opName_tree=null;
		CommonTree HistoricalData53_tree=null;
		RewriteRuleTokenStream stream_Operation=new RewriteRuleTokenStream(adaptor,"token Operation");
		RewriteRuleTokenStream stream_HistoricalData=new RewriteRuleTokenStream(adaptor,"token HistoricalData");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:9: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ) |opName= Operation -> Operation )
			int alt16=2;
			int LA16_0 = input.LA(1);
			if ( (LA16_0==HistoricalData) ) {
				alt16=1;
			}
			else if ( (LA16_0==Operation) ) {
				alt16=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 16, 0, input);
				throw nvae;
			}

			switch (alt16) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:11: HistoricalData
					{
					HistoricalData53=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_operand688);  
					stream_HistoricalData.add(HistoricalData53);

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
					// 169:26: -> ^( StockOperation ^( OperationOutput HistoricalData ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:29: ^( StockOperation ^( OperationOutput HistoricalData ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:46: ^( OperationOutput HistoricalData )
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:83: opName= Operation
					{
					opName=(Token)match(input,Operation,FOLLOW_Operation_in_operand708);  
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
					// 169:137: -> Operation
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
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:1: constant : Number -> ^( Double Number ) ;
	public final ParameterizedIndicatorsParser.constant_return constant() throws RecognitionException {
		ParameterizedIndicatorsParser.constant_return retval = new ParameterizedIndicatorsParser.constant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token Number54=null;

		CommonTree Number54_tree=null;
		RewriteRuleTokenStream stream_Number=new RewriteRuleTokenStream(adaptor,"token Number");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:10: ( Number -> ^( Double Number ) )
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:13: Number
			{
			Number54=(Token)match(input,Number,FOLLOW_Number_in_constant722);  
			stream_Number.add(Number54);

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
			// 170:20: -> ^( Double Number )
			{
				// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:23: ^( Double Number )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_1);
				adaptor.addChild(root_1, stream_Number.nextNode());
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


	public static class opcmpcondition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "opcmpcondition"
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:172:1: opcmpcondition[CommonTree firstOp] : ( 'is above historical' WhiteChar operand -> ^( SupDoubleMapCondition operand ) | 'is below historical' WhiteChar operand -> ^( InfDoubleMapCondition operand ) | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Double Number[\"1.0\"] ) operand ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Double Number[\"1.0\"] ) operand ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition ) )? | 'equals historical' WhiteChar operand -> ^( EqualDoubleMapCondition operand ) );
	public final ParameterizedIndicatorsParser.opcmpcondition_return opcmpcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.opcmpcondition_return retval = new ParameterizedIndicatorsParser.opcmpcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal55=null;
		Token WhiteChar56=null;
		Token string_literal58=null;
		Token WhiteChar59=null;
		Token string_literal61=null;
		Token WhiteChar62=null;
		Token WhiteChar64=null;
		Token string_literal65=null;
		Token WhiteChar66=null;
		Token WhiteChar67=null;
		Token DAYS68=null;
		Token string_literal69=null;
		Token WhiteChar70=null;
		Token WhiteChar72=null;
		Token string_literal73=null;
		Token WhiteChar74=null;
		Token WhiteChar75=null;
		Token DAYS76=null;
		Token string_literal77=null;
		Token WhiteChar78=null;
		ParserRuleReturnScope nbDays =null;
		ParserRuleReturnScope operand57 =null;
		ParserRuleReturnScope operand60 =null;
		ParserRuleReturnScope operand63 =null;
		ParserRuleReturnScope operand71 =null;
		ParserRuleReturnScope operand79 =null;

		CommonTree string_literal55_tree=null;
		CommonTree WhiteChar56_tree=null;
		CommonTree string_literal58_tree=null;
		CommonTree WhiteChar59_tree=null;
		CommonTree string_literal61_tree=null;
		CommonTree WhiteChar62_tree=null;
		CommonTree WhiteChar64_tree=null;
		CommonTree string_literal65_tree=null;
		CommonTree WhiteChar66_tree=null;
		CommonTree WhiteChar67_tree=null;
		CommonTree DAYS68_tree=null;
		CommonTree string_literal69_tree=null;
		CommonTree WhiteChar70_tree=null;
		CommonTree WhiteChar72_tree=null;
		CommonTree string_literal73_tree=null;
		CommonTree WhiteChar74_tree=null;
		CommonTree WhiteChar75_tree=null;
		CommonTree DAYS76_tree=null;
		CommonTree string_literal77_tree=null;
		CommonTree WhiteChar78_tree=null;
		RewriteRuleTokenStream stream_49=new RewriteRuleTokenStream(adaptor,"token 49");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_45=new RewriteRuleTokenStream(adaptor,"token 45");
		RewriteRuleTokenStream stream_57=new RewriteRuleTokenStream(adaptor,"token 57");
		RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
		RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");
		RewriteRuleTokenStream stream_53=new RewriteRuleTokenStream(adaptor,"token 53");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:172:37: ( 'is above historical' WhiteChar operand -> ^( SupDoubleMapCondition operand ) | 'is below historical' WhiteChar operand -> ^( InfDoubleMapCondition operand ) | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Double Number[\"1.0\"] ) operand ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Double Number[\"1.0\"] ) operand ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition ) )? | 'equals historical' WhiteChar operand -> ^( EqualDoubleMapCondition operand ) )
			int alt19=5;
			switch ( input.LA(1) ) {
			case 53:
				{
				alt19=1;
				}
				break;
			case 57:
				{
				alt19=2;
				}
				break;
			case 45:
				{
				alt19=3;
				}
				break;
			case 47:
				{
				alt19=4;
				}
				break;
			case 49:
				{
				alt19=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}
			switch (alt19) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:3: 'is above historical' WhiteChar operand
					{
					string_literal55=(Token)match(input,53,FOLLOW_53_in_opcmpcondition744);  
					stream_53.add(string_literal55);

					WhiteChar56=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition746);  
					stream_WhiteChar.add(WhiteChar56);

					pushFollow(FOLLOW_operand_in_opcmpcondition748);
					operand57=operand();
					state._fsp--;

					stream_operand.add(operand57.getTree());
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
					// 173:43: -> ^( SupDoubleMapCondition operand )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:46: ^( SupDoubleMapCondition operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupDoubleMapCondition, "SupDoubleMapCondition"), root_1);
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_1, stream_operand.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:174:3: 'is below historical' WhiteChar operand
					{
					string_literal58=(Token)match(input,57,FOLLOW_57_in_opcmpcondition764);  
					stream_57.add(string_literal58);

					WhiteChar59=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition766);  
					stream_WhiteChar.add(WhiteChar59);

					pushFollow(FOLLOW_operand_in_opcmpcondition768);
					operand60=operand();
					state._fsp--;

					stream_operand.add(operand60.getTree());
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
					// 174:43: -> ^( InfDoubleMapCondition operand )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:174:46: ^( InfDoubleMapCondition operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfDoubleMapCondition, "InfDoubleMapCondition"), root_1);
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_1, stream_operand.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:175:3: ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Double Number[\"1.0\"] ) operand ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:175:3: ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Double Number[\"1.0\"] ) operand ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:175:4: 'crosses down historical' WhiteChar operand
					{
					string_literal61=(Token)match(input,45,FOLLOW_45_in_opcmpcondition785);  
					stream_45.add(string_literal61);

					WhiteChar62=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition787);  
					stream_WhiteChar.add(WhiteChar62);

					pushFollow(FOLLOW_operand_in_opcmpcondition789);
					operand63=operand();
					state._fsp--;

					stream_operand.add(operand63.getTree());
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
					// 175:48: -> ^( CrossDownDoubleMapCondition ^( Double Number[\"1.0\"] ) operand )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:175:51: ^( CrossDownDoubleMapCondition ^( Double Number[\"1.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:175:81: ^( Double Number[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_1, stream_operand.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:9: ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition ) )?
					int alt17=2;
					int LA17_0 = input.LA(1);
					if ( (LA17_0==WhiteChar) ) {
						int LA17_1 = input.LA(2);
						if ( (LA17_1==65) ) {
							alt17=1;
						}
					}
					switch (alt17) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:11: WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS
							{
							WhiteChar64=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition819);  
							stream_WhiteChar.add(WhiteChar64);

							string_literal65=(Token)match(input,65,FOLLOW_65_in_opcmpcondition821);  
							stream_65.add(string_literal65);

							WhiteChar66=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition823);  
							stream_WhiteChar.add(WhiteChar66);

							pushFollow(FOLLOW_constant_in_opcmpcondition827);
							nbDays=constant();
							state._fsp--;

							stream_constant.add(nbDays.getTree());
							WhiteChar67=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition829);  
							stream_WhiteChar.add(WhiteChar67);

							DAYS68=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition831);  
							stream_DAYS.add(DAYS68);

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
							// 176:69: -> ^( CrossDownDoubleMapCondition )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:72: ^( CrossDownDoubleMapCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:177:3: ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Double Number[\"1.0\"] ) operand ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:177:3: ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Double Number[\"1.0\"] ) operand ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:177:4: 'crosses up historical' WhiteChar operand
					{
					string_literal69=(Token)match(input,47,FOLLOW_47_in_opcmpcondition851);  
					stream_47.add(string_literal69);

					WhiteChar70=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition853);  
					stream_WhiteChar.add(WhiteChar70);

					pushFollow(FOLLOW_operand_in_opcmpcondition855);
					operand71=operand();
					state._fsp--;

					stream_operand.add(operand71.getTree());
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
					// 177:46: -> ^( CrossUpDoubleMapCondition ^( Double Number[\"1.0\"] ) operand )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:177:49: ^( CrossUpDoubleMapCondition ^( Double Number[\"1.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:177:77: ^( Double Number[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_1, stream_operand.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:178:8: ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition ) )?
					int alt18=2;
					int LA18_0 = input.LA(1);
					if ( (LA18_0==WhiteChar) ) {
						int LA18_1 = input.LA(2);
						if ( (LA18_1==65) ) {
							alt18=1;
						}
					}
					switch (alt18) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:178:10: WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS
							{
							WhiteChar72=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition885);  
							stream_WhiteChar.add(WhiteChar72);

							string_literal73=(Token)match(input,65,FOLLOW_65_in_opcmpcondition887);  
							stream_65.add(string_literal73);

							WhiteChar74=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition889);  
							stream_WhiteChar.add(WhiteChar74);

							pushFollow(FOLLOW_constant_in_opcmpcondition893);
							nbDays=constant();
							state._fsp--;

							stream_constant.add(nbDays.getTree());
							WhiteChar75=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition895);  
							stream_WhiteChar.add(WhiteChar75);

							DAYS76=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition897);  
							stream_DAYS.add(DAYS76);

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
							// 178:68: -> ^( CrossUpDoubleMapCondition )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:178:71: ^( CrossUpDoubleMapCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:3: 'equals historical' WhiteChar operand
					{
					string_literal77=(Token)match(input,49,FOLLOW_49_in_opcmpcondition916);  
					stream_49.add(string_literal77);

					WhiteChar78=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition918);  
					stream_WhiteChar.add(WhiteChar78);

					pushFollow(FOLLOW_operand_in_opcmpcondition920);
					operand79=operand();
					state._fsp--;

					stream_operand.add(operand79.getTree());
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
					// 179:41: -> ^( EqualDoubleMapCondition operand )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:44: ^( EqualDoubleMapCondition operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualDoubleMapCondition, "EqualDoubleMapCondition"), root_1);
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_1, stream_operand.nextTree());
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
	// $ANTLR end "opcmpcondition"


	public static class constantcmp_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "constantcmp"
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:1: constantcmp[CommonTree firstOp] : ( 'equals threshold' WhiteChar constant -> ^( EqualConstantCondition constant ) | 'is above threshold' WhiteChar constant -> ^( SupConstantCondition constant ) | 'is below threshold' WhiteChar constant -> ^( InfConstantCondition constant ) );
	public final ParameterizedIndicatorsParser.constantcmp_return constantcmp(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.constantcmp_return retval = new ParameterizedIndicatorsParser.constantcmp_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal80=null;
		Token WhiteChar81=null;
		Token string_literal83=null;
		Token WhiteChar84=null;
		Token string_literal86=null;
		Token WhiteChar87=null;
		ParserRuleReturnScope constant82 =null;
		ParserRuleReturnScope constant85 =null;
		ParserRuleReturnScope constant88 =null;

		CommonTree string_literal80_tree=null;
		CommonTree WhiteChar81_tree=null;
		CommonTree string_literal83_tree=null;
		CommonTree WhiteChar84_tree=null;
		CommonTree string_literal86_tree=null;
		CommonTree WhiteChar87_tree=null;
		RewriteRuleTokenStream stream_58=new RewriteRuleTokenStream(adaptor,"token 58");
		RewriteRuleTokenStream stream_54=new RewriteRuleTokenStream(adaptor,"token 54");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_50=new RewriteRuleTokenStream(adaptor,"token 50");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:34: ( 'equals threshold' WhiteChar constant -> ^( EqualConstantCondition constant ) | 'is above threshold' WhiteChar constant -> ^( SupConstantCondition constant ) | 'is below threshold' WhiteChar constant -> ^( InfConstantCondition constant ) )
			int alt20=3;
			switch ( input.LA(1) ) {
			case 50:
				{
				alt20=1;
				}
				break;
			case 54:
				{
				alt20=2;
				}
				break;
			case 58:
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:182:3: 'equals threshold' WhiteChar constant
					{
					string_literal80=(Token)match(input,50,FOLLOW_50_in_constantcmp943);  
					stream_50.add(string_literal80);

					WhiteChar81=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp945);  
					stream_WhiteChar.add(WhiteChar81);

					pushFollow(FOLLOW_constant_in_constantcmp947);
					constant82=constant();
					state._fsp--;

					stream_constant.add(constant82.getTree());
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
					// 182:41: -> ^( EqualConstantCondition constant )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:182:44: ^( EqualConstantCondition constant )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualConstantCondition, "EqualConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:3: 'is above threshold' WhiteChar constant
					{
					string_literal83=(Token)match(input,54,FOLLOW_54_in_constantcmp964);  
					stream_54.add(string_literal83);

					WhiteChar84=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp966);  
					stream_WhiteChar.add(WhiteChar84);

					pushFollow(FOLLOW_constant_in_constantcmp968);
					constant85=constant();
					state._fsp--;

					stream_constant.add(constant85.getTree());
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
					// 183:43: -> ^( SupConstantCondition constant )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:46: ^( SupConstantCondition constant )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupConstantCondition, "SupConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:3: 'is below threshold' WhiteChar constant
					{
					string_literal86=(Token)match(input,58,FOLLOW_58_in_constantcmp984);  
					stream_58.add(string_literal86);

					WhiteChar87=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp986);  
					stream_WhiteChar.add(WhiteChar87);

					pushFollow(FOLLOW_constant_in_constantcmp988);
					constant88=constant();
					state._fsp--;

					stream_constant.add(constant88.getTree());
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
					// 184:43: -> ^( InfConstantCondition constant )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:46: ^( InfConstantCondition constant )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfConstantCondition, "InfConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						adaptor.addChild(root_1, firstOp);
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
	// $ANTLR end "constantcmp"


	public static class presetcondition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "presetcondition"
	// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:1: presetcondition[CommonTree firstOp] : ( ( 'reverses down' -> ^( ReverseCondition ^( Double Number[\"-1\"] ) ^( Double Number[\"0.0\"] ) ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Double Number[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Double Number[\"1\"] ) ^( Double Number[\"0.0\"] ) ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Double Number[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ) )? | ( 'crosses up threshold' WhiteChar cnst= constant -> ^( CrossUpConstantCondition constant ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ) )? | ( 'crosses down threshold' WhiteChar cnst= constant -> ^( CrossDownConstantCondition constant ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ) )? | ( 'makes a higher high over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherHighCondition constant ^( Double Number[\"-1.0\"] ) ) ) ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant -> ^( HigherHighCondition ) )? | ( 'makes a higher low over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherLowCondition constant ^( Double Number[\"-1.0\"] ) ) ) ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant -> ^( HigherLowCondition ) )? | ( 'makes a lower high over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerHighCondition constant ^( Double Number[\"-1.0\"] ) ) ) ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant -> ^( LowerHighCondition ) )? | ( 'makes a lower low over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerLowCondition constant ^( Double Number[\"-1.0\"] ) ) ) ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant -> ^( LowerLowCondition ) )? );
	public final ParameterizedIndicatorsParser.presetcondition_return presetcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.presetcondition_return retval = new ParameterizedIndicatorsParser.presetcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal89=null;
		Token WhiteChar90=null;
		Token string_literal91=null;
		Token WhiteChar92=null;
		Token PERCENT93=null;
		Token WhiteChar94=null;
		Token string_literal95=null;
		Token WhiteChar96=null;
		Token WhiteChar97=null;
		Token DAYS98=null;
		Token string_literal99=null;
		Token WhiteChar100=null;
		Token string_literal101=null;
		Token WhiteChar102=null;
		Token PERCENT103=null;
		Token WhiteChar104=null;
		Token string_literal105=null;
		Token WhiteChar106=null;
		Token WhiteChar107=null;
		Token DAYS108=null;
		Token string_literal109=null;
		Token WhiteChar110=null;
		Token PERCENT111=null;
		Token WhiteChar112=null;
		Token string_literal113=null;
		Token WhiteChar114=null;
		Token WhiteChar115=null;
		Token DAYS116=null;
		Token string_literal117=null;
		Token WhiteChar118=null;
		Token PERCENT119=null;
		Token WhiteChar120=null;
		Token string_literal121=null;
		Token WhiteChar122=null;
		Token WhiteChar123=null;
		Token DAYS124=null;
		Token string_literal125=null;
		Token WhiteChar126=null;
		Token WhiteChar127=null;
		Token string_literal128=null;
		Token WhiteChar129=null;
		Token WhiteChar130=null;
		Token DAYS131=null;
		Token string_literal132=null;
		Token WhiteChar133=null;
		Token WhiteChar134=null;
		Token string_literal135=null;
		Token WhiteChar136=null;
		Token WhiteChar137=null;
		Token DAYS138=null;
		Token string_literal139=null;
		Token WhiteChar140=null;
		Token WhiteChar141=null;
		Token DAYS142=null;
		Token WhiteChar143=null;
		Token string_literal144=null;
		Token WhiteChar145=null;
		Token string_literal146=null;
		Token WhiteChar147=null;
		Token WhiteChar148=null;
		Token DAYS149=null;
		Token WhiteChar150=null;
		Token string_literal151=null;
		Token WhiteChar152=null;
		Token string_literal153=null;
		Token WhiteChar154=null;
		Token WhiteChar155=null;
		Token DAYS156=null;
		Token WhiteChar157=null;
		Token string_literal158=null;
		Token WhiteChar159=null;
		Token string_literal160=null;
		Token WhiteChar161=null;
		Token WhiteChar162=null;
		Token DAYS163=null;
		Token WhiteChar164=null;
		Token string_literal165=null;
		Token WhiteChar166=null;
		ParserRuleReturnScope percentdown =null;
		ParserRuleReturnScope nbDays =null;
		ParserRuleReturnScope percentup =null;
		ParserRuleReturnScope cnst =null;
		ParserRuleReturnScope lookBackSmthPeriod =null;

		CommonTree string_literal89_tree=null;
		CommonTree WhiteChar90_tree=null;
		CommonTree string_literal91_tree=null;
		CommonTree WhiteChar92_tree=null;
		CommonTree PERCENT93_tree=null;
		CommonTree WhiteChar94_tree=null;
		CommonTree string_literal95_tree=null;
		CommonTree WhiteChar96_tree=null;
		CommonTree WhiteChar97_tree=null;
		CommonTree DAYS98_tree=null;
		CommonTree string_literal99_tree=null;
		CommonTree WhiteChar100_tree=null;
		CommonTree string_literal101_tree=null;
		CommonTree WhiteChar102_tree=null;
		CommonTree PERCENT103_tree=null;
		CommonTree WhiteChar104_tree=null;
		CommonTree string_literal105_tree=null;
		CommonTree WhiteChar106_tree=null;
		CommonTree WhiteChar107_tree=null;
		CommonTree DAYS108_tree=null;
		CommonTree string_literal109_tree=null;
		CommonTree WhiteChar110_tree=null;
		CommonTree PERCENT111_tree=null;
		CommonTree WhiteChar112_tree=null;
		CommonTree string_literal113_tree=null;
		CommonTree WhiteChar114_tree=null;
		CommonTree WhiteChar115_tree=null;
		CommonTree DAYS116_tree=null;
		CommonTree string_literal117_tree=null;
		CommonTree WhiteChar118_tree=null;
		CommonTree PERCENT119_tree=null;
		CommonTree WhiteChar120_tree=null;
		CommonTree string_literal121_tree=null;
		CommonTree WhiteChar122_tree=null;
		CommonTree WhiteChar123_tree=null;
		CommonTree DAYS124_tree=null;
		CommonTree string_literal125_tree=null;
		CommonTree WhiteChar126_tree=null;
		CommonTree WhiteChar127_tree=null;
		CommonTree string_literal128_tree=null;
		CommonTree WhiteChar129_tree=null;
		CommonTree WhiteChar130_tree=null;
		CommonTree DAYS131_tree=null;
		CommonTree string_literal132_tree=null;
		CommonTree WhiteChar133_tree=null;
		CommonTree WhiteChar134_tree=null;
		CommonTree string_literal135_tree=null;
		CommonTree WhiteChar136_tree=null;
		CommonTree WhiteChar137_tree=null;
		CommonTree DAYS138_tree=null;
		CommonTree string_literal139_tree=null;
		CommonTree WhiteChar140_tree=null;
		CommonTree WhiteChar141_tree=null;
		CommonTree DAYS142_tree=null;
		CommonTree WhiteChar143_tree=null;
		CommonTree string_literal144_tree=null;
		CommonTree WhiteChar145_tree=null;
		CommonTree string_literal146_tree=null;
		CommonTree WhiteChar147_tree=null;
		CommonTree WhiteChar148_tree=null;
		CommonTree DAYS149_tree=null;
		CommonTree WhiteChar150_tree=null;
		CommonTree string_literal151_tree=null;
		CommonTree WhiteChar152_tree=null;
		CommonTree string_literal153_tree=null;
		CommonTree WhiteChar154_tree=null;
		CommonTree WhiteChar155_tree=null;
		CommonTree DAYS156_tree=null;
		CommonTree WhiteChar157_tree=null;
		CommonTree string_literal158_tree=null;
		CommonTree WhiteChar159_tree=null;
		CommonTree string_literal160_tree=null;
		CommonTree WhiteChar161_tree=null;
		CommonTree WhiteChar162_tree=null;
		CommonTree DAYS163_tree=null;
		CommonTree WhiteChar164_tree=null;
		CommonTree string_literal165_tree=null;
		CommonTree WhiteChar166_tree=null;
		RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
		RewriteRuleTokenStream stream_48=new RewriteRuleTokenStream(adaptor,"token 48");
		RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
		RewriteRuleTokenStream stream_51=new RewriteRuleTokenStream(adaptor,"token 51");
		RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");
		RewriteRuleTokenStream stream_52=new RewriteRuleTokenStream(adaptor,"token 52");
		RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
		RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
		RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
		RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:39: ( ( 'reverses down' -> ^( ReverseCondition ^( Double Number[\"-1\"] ) ^( Double Number[\"0.0\"] ) ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Double Number[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Double Number[\"1\"] ) ^( Double Number[\"0.0\"] ) ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Double Number[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ) )? | ( 'crosses up threshold' WhiteChar cnst= constant -> ^( CrossUpConstantCondition constant ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ) )? | ( 'crosses down threshold' WhiteChar cnst= constant -> ^( CrossDownConstantCondition constant ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ) )? | ( 'makes a higher high over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherHighCondition constant ^( Double Number[\"-1.0\"] ) ) ) ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant -> ^( HigherHighCondition ) )? | ( 'makes a higher low over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherLowCondition constant ^( Double Number[\"-1.0\"] ) ) ) ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant -> ^( HigherLowCondition ) )? | ( 'makes a lower high over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerHighCondition constant ^( Double Number[\"-1.0\"] ) ) ) ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant -> ^( LowerHighCondition ) )? | ( 'makes a lower low over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerLowCondition constant ^( Double Number[\"-1.0\"] ) ) ) ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant -> ^( LowerLowCondition ) )? )
			int alt31=10;
			switch ( input.LA(1) ) {
			case 66:
				{
				alt31=1;
				}
				break;
			case 67:
				{
				alt31=2;
				}
				break;
			case 51:
				{
				alt31=3;
				}
				break;
			case 52:
				{
				alt31=4;
				}
				break;
			case 48:
				{
				alt31=5;
				}
				break;
			case 46:
				{
				alt31=6;
				}
				break;
			case 60:
				{
				alt31=7;
				}
				break;
			case 61:
				{
				alt31=8;
				}
				break;
			case 62:
				{
				alt31=9;
				}
				break;
			case 63:
				{
				alt31=10;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 31, 0, input);
				throw nvae;
			}
			switch (alt31) {
				case 1 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:3: ( 'reverses down' -> ^( ReverseCondition ^( Double Number[\"-1\"] ) ^( Double Number[\"0.0\"] ) ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Double Number[\"-1\"] ) ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:3: ( 'reverses down' -> ^( ReverseCondition ^( Double Number[\"-1\"] ) ^( Double Number[\"0.0\"] ) ^( Double Number[\"1.0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:4: 'reverses down'
					{
					string_literal89=(Token)match(input,66,FOLLOW_66_in_presetcondition1016);  
					stream_66.add(string_literal89);

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
					// 188:20: -> ^( ReverseCondition ^( Double Number[\"-1\"] ) ^( Double Number[\"0.0\"] ) ^( Double Number[\"1.0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:23: ^( ReverseCondition ^( Double Number[\"-1\"] ) ^( Double Number[\"0.0\"] ) ^( Double Number[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:42: ^( Double Number[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:65: ^( Double Number[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:89: ^( Double Number[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:7: ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Double Number[\"-1\"] ) ) )?
					int alt21=2;
					int LA21_0 = input.LA(1);
					if ( (LA21_0==WhiteChar) ) {
						int LA21_1 = input.LA(2);
						if ( (LA21_1==64) ) {
							alt21=1;
						}
					}
					switch (alt21) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:9: WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS
							{
							WhiteChar90=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1056);  
							stream_WhiteChar.add(WhiteChar90);

							string_literal91=(Token)match(input,64,FOLLOW_64_in_presetcondition1058);  
							stream_64.add(string_literal91);

							WhiteChar92=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1060);  
							stream_WhiteChar.add(WhiteChar92);

							pushFollow(FOLLOW_constant_in_presetcondition1064);
							percentdown=constant();
							state._fsp--;

							stream_constant.add(percentdown.getTree());
							PERCENT93=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition1066);  
							stream_PERCENT.add(PERCENT93);

							WhiteChar94=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1068);  
							stream_WhiteChar.add(WhiteChar94);

							string_literal95=(Token)match(input,65,FOLLOW_65_in_presetcondition1070);  
							stream_65.add(string_literal95);

							WhiteChar96=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1072);  
							stream_WhiteChar.add(WhiteChar96);

							pushFollow(FOLLOW_constant_in_presetcondition1076);
							nbDays=constant();
							state._fsp--;

							stream_constant.add(nbDays.getTree());
							WhiteChar97=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1078);  
							stream_WhiteChar.add(WhiteChar97);

							DAYS98=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1080);  
							stream_DAYS.add(DAYS98);

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
							// 189:128: -> ^( ReverseCondition ^( Double Number[\"-1\"] ) )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:131: ^( ReverseCondition ^( Double Number[\"-1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:150: ^( Double Number[\"-1\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "-1"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (percentdown!=null?((CommonTree)percentdown.getTree()):null));
								adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:3: ( 'reverses up' -> ^( ReverseCondition ^( Double Number[\"1\"] ) ^( Double Number[\"0.0\"] ) ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Double Number[\"1\"] ) ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:3: ( 'reverses up' -> ^( ReverseCondition ^( Double Number[\"1\"] ) ^( Double Number[\"0.0\"] ) ^( Double Number[\"1.0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:4: 'reverses up'
					{
					string_literal99=(Token)match(input,67,FOLLOW_67_in_presetcondition1110);  
					stream_67.add(string_literal99);

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
					// 190:18: -> ^( ReverseCondition ^( Double Number[\"1\"] ) ^( Double Number[\"0.0\"] ) ^( Double Number[\"1.0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:21: ^( ReverseCondition ^( Double Number[\"1\"] ) ^( Double Number[\"0.0\"] ) ^( Double Number[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:40: ^( Double Number[\"1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "1"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:62: ^( Double Number[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:86: ^( Double Number[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:7: ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Double Number[\"1\"] ) ) )?
					int alt22=2;
					int LA22_0 = input.LA(1);
					if ( (LA22_0==WhiteChar) ) {
						int LA22_1 = input.LA(2);
						if ( (LA22_1==64) ) {
							alt22=1;
						}
					}
					switch (alt22) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:9: WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS
							{
							WhiteChar100=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1150);  
							stream_WhiteChar.add(WhiteChar100);

							string_literal101=(Token)match(input,64,FOLLOW_64_in_presetcondition1152);  
							stream_64.add(string_literal101);

							WhiteChar102=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1154);  
							stream_WhiteChar.add(WhiteChar102);

							pushFollow(FOLLOW_constant_in_presetcondition1158);
							percentup=constant();
							state._fsp--;

							stream_constant.add(percentup.getTree());
							PERCENT103=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition1160);  
							stream_PERCENT.add(PERCENT103);

							WhiteChar104=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1162);  
							stream_WhiteChar.add(WhiteChar104);

							string_literal105=(Token)match(input,65,FOLLOW_65_in_presetcondition1164);  
							stream_65.add(string_literal105);

							WhiteChar106=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1166);  
							stream_WhiteChar.add(WhiteChar106);

							pushFollow(FOLLOW_constant_in_presetcondition1170);
							nbDays=constant();
							state._fsp--;

							stream_constant.add(nbDays.getTree());
							WhiteChar107=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1172);  
							stream_WhiteChar.add(WhiteChar107);

							DAYS108=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1174);  
							stream_DAYS.add(DAYS108);

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
							// 191:126: -> ^( ReverseCondition ^( Double Number[\"1\"] ) )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:129: ^( ReverseCondition ^( Double Number[\"1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:148: ^( Double Number[\"1\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "1"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (percentup!=null?((CommonTree)percentup.getTree()):null));
								adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Double Number[\"1.0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:4: 'goes down more than' WhiteChar percentdown= constant PERCENT
					{
					string_literal109=(Token)match(input,51,FOLLOW_51_in_presetcondition1203);  
					stream_51.add(string_literal109);

					WhiteChar110=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1205);  
					stream_WhiteChar.add(WhiteChar110);

					pushFollow(FOLLOW_constant_in_presetcondition1209);
					percentdown=constant();
					state._fsp--;

					stream_constant.add(percentdown.getTree());
					PERCENT111=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition1211);  
					stream_PERCENT.add(PERCENT111);

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
					// 192:65: -> ^( DownRatioCondition constant ^( Double Number[\"1.0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:68: ^( DownRatioCondition constant ^( Double Number[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:98: ^( Double Number[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:7: ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ) )?
					int alt23=2;
					int LA23_0 = input.LA(1);
					if ( (LA23_0==WhiteChar) ) {
						int LA23_1 = input.LA(2);
						if ( (LA23_1==65) ) {
							alt23=1;
						}
					}
					switch (alt23) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:9: WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS
							{
							WhiteChar112=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1240);  
							stream_WhiteChar.add(WhiteChar112);

							string_literal113=(Token)match(input,65,FOLLOW_65_in_presetcondition1242);  
							stream_65.add(string_literal113);

							WhiteChar114=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1244);  
							stream_WhiteChar.add(WhiteChar114);

							pushFollow(FOLLOW_constant_in_presetcondition1248);
							nbDays=constant();
							state._fsp--;

							stream_constant.add(nbDays.getTree());
							WhiteChar115=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1250);  
							stream_WhiteChar.add(WhiteChar115);

							DAYS116=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1252);  
							stream_DAYS.add(DAYS116);

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
							// 193:67: -> ^( DownRatioCondition )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:70: ^( DownRatioCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentdown!=null?((CommonTree)percentdown.getTree()):null));
								adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Double Number[\"1.0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:4: 'goes up more than' WhiteChar percentup= constant PERCENT
					{
					string_literal117=(Token)match(input,52,FOLLOW_52_in_presetcondition1274);  
					stream_52.add(string_literal117);

					WhiteChar118=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1276);  
					stream_WhiteChar.add(WhiteChar118);

					pushFollow(FOLLOW_constant_in_presetcondition1280);
					percentup=constant();
					state._fsp--;

					stream_constant.add(percentup.getTree());
					PERCENT119=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition1282);  
					stream_PERCENT.add(PERCENT119);

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
					// 194:61: -> ^( UpRatioCondition constant ^( Double Number[\"1.0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:64: ^( UpRatioCondition constant ^( Double Number[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:92: ^( Double Number[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:7: ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ) )?
					int alt24=2;
					int LA24_0 = input.LA(1);
					if ( (LA24_0==WhiteChar) ) {
						int LA24_1 = input.LA(2);
						if ( (LA24_1==65) ) {
							alt24=1;
						}
					}
					switch (alt24) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:9: WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS
							{
							WhiteChar120=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1311);  
							stream_WhiteChar.add(WhiteChar120);

							string_literal121=(Token)match(input,65,FOLLOW_65_in_presetcondition1313);  
							stream_65.add(string_literal121);

							WhiteChar122=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1315);  
							stream_WhiteChar.add(WhiteChar122);

							pushFollow(FOLLOW_constant_in_presetcondition1319);
							nbDays=constant();
							state._fsp--;

							stream_constant.add(nbDays.getTree());
							WhiteChar123=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1321);  
							stream_WhiteChar.add(WhiteChar123);

							DAYS124=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1323);  
							stream_DAYS.add(DAYS124);

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
							// 195:67: -> ^( UpRatioCondition )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:70: ^( UpRatioCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentup!=null?((CommonTree)percentup.getTree()):null));
								adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:3: ( 'crosses up threshold' WhiteChar cnst= constant -> ^( CrossUpConstantCondition constant ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:3: ( 'crosses up threshold' WhiteChar cnst= constant -> ^( CrossUpConstantCondition constant ^( Double Number[\"1.0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:4: 'crosses up threshold' WhiteChar cnst= constant
					{
					string_literal125=(Token)match(input,48,FOLLOW_48_in_presetcondition1345);  
					stream_48.add(string_literal125);

					WhiteChar126=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1347);  
					stream_WhiteChar.add(WhiteChar126);

					pushFollow(FOLLOW_constant_in_presetcondition1351);
					cnst=constant();
					state._fsp--;

					stream_constant.add(cnst.getTree());
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
					// 196:51: -> ^( CrossUpConstantCondition constant ^( Double Number[\"1.0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:54: ^( CrossUpConstantCondition constant ^( Double Number[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:90: ^( Double Number[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:7: ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ) )?
					int alt25=2;
					int LA25_0 = input.LA(1);
					if ( (LA25_0==WhiteChar) ) {
						int LA25_1 = input.LA(2);
						if ( (LA25_1==65) ) {
							alt25=1;
						}
					}
					switch (alt25) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:9: WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS
							{
							WhiteChar127=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1379);  
							stream_WhiteChar.add(WhiteChar127);

							string_literal128=(Token)match(input,65,FOLLOW_65_in_presetcondition1381);  
							stream_65.add(string_literal128);

							WhiteChar129=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1383);  
							stream_WhiteChar.add(WhiteChar129);

							pushFollow(FOLLOW_constant_in_presetcondition1387);
							nbDays=constant();
							state._fsp--;

							stream_constant.add(nbDays.getTree());
							WhiteChar130=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1389);  
							stream_WhiteChar.add(WhiteChar130);

							DAYS131=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1391);  
							stream_DAYS.add(DAYS131);

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
							// 197:67: -> ^( CrossUpConstantCondition )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:70: ^( CrossUpConstantCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
								adaptor.addChild(root_1, (cnst!=null?((CommonTree)cnst.getTree()):null));
								adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:3: ( 'crosses down threshold' WhiteChar cnst= constant -> ^( CrossDownConstantCondition constant ^( Double Number[\"1.0\"] ) ) ) ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:3: ( 'crosses down threshold' WhiteChar cnst= constant -> ^( CrossDownConstantCondition constant ^( Double Number[\"1.0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:4: 'crosses down threshold' WhiteChar cnst= constant
					{
					string_literal132=(Token)match(input,46,FOLLOW_46_in_presetcondition1415);  
					stream_46.add(string_literal132);

					WhiteChar133=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1417);  
					stream_WhiteChar.add(WhiteChar133);

					pushFollow(FOLLOW_constant_in_presetcondition1421);
					cnst=constant();
					state._fsp--;

					stream_constant.add(cnst.getTree());
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
					// 198:53: -> ^( CrossDownConstantCondition constant ^( Double Number[\"1.0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:56: ^( CrossDownConstantCondition constant ^( Double Number[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:94: ^( Double Number[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:7: ( WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ) )?
					int alt26=2;
					int LA26_0 = input.LA(1);
					if ( (LA26_0==WhiteChar) ) {
						int LA26_1 = input.LA(2);
						if ( (LA26_1==65) ) {
							alt26=1;
						}
					}
					switch (alt26) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:9: WhiteChar 'over' WhiteChar nbDays= constant WhiteChar DAYS
							{
							WhiteChar134=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1449);  
							stream_WhiteChar.add(WhiteChar134);

							string_literal135=(Token)match(input,65,FOLLOW_65_in_presetcondition1451);  
							stream_65.add(string_literal135);

							WhiteChar136=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1453);  
							stream_WhiteChar.add(WhiteChar136);

							pushFollow(FOLLOW_constant_in_presetcondition1457);
							nbDays=constant();
							state._fsp--;

							stream_constant.add(nbDays.getTree());
							WhiteChar137=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1459);  
							stream_WhiteChar.add(WhiteChar137);

							DAYS138=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1461);  
							stream_DAYS.add(DAYS138);

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
							// 199:67: -> ^( CrossDownConstantCondition )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:70: ^( CrossDownConstantCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
								adaptor.addChild(root_1, (cnst!=null?((CommonTree)cnst.getTree()):null));
								adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
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
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:3: ( 'makes a higher high over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherHighCondition constant ^( Double Number[\"-1.0\"] ) ) ) ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant -> ^( HigherHighCondition ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:3: ( 'makes a higher high over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherHighCondition constant ^( Double Number[\"-1.0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:4: 'makes a higher high over' WhiteChar nbDays= constant WhiteChar DAYS
					{
					string_literal139=(Token)match(input,60,FOLLOW_60_in_presetcondition1483);  
					stream_60.add(string_literal139);

					WhiteChar140=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1485);  
					stream_WhiteChar.add(WhiteChar140);

					pushFollow(FOLLOW_constant_in_presetcondition1489);
					nbDays=constant();
					state._fsp--;

					stream_constant.add(nbDays.getTree());
					WhiteChar141=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1491);  
					stream_WhiteChar.add(WhiteChar141);

					DAYS142=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1493);  
					stream_DAYS.add(DAYS142);

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
					// 200:72: -> ^( HigherHighCondition constant ^( Double Number[\"-1.0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:75: ^( HigherHighCondition constant ^( Double Number[\"-1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(HigherHighCondition, "HigherHighCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:106: ^( Double Number[\"-1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "-1.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:8: ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant -> ^( HigherHighCondition ) )?
					int alt27=2;
					int LA27_0 = input.LA(1);
					if ( (LA27_0==WhiteChar) ) {
						int LA27_1 = input.LA(2);
						if ( (LA27_1==68) ) {
							alt27=1;
						}
					}
					switch (alt27) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:10: WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant
							{
							WhiteChar143=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1522);  
							stream_WhiteChar.add(WhiteChar143);

							string_literal144=(Token)match(input,68,FOLLOW_68_in_presetcondition1524);  
							stream_68.add(string_literal144);

							WhiteChar145=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1526);  
							stream_WhiteChar.add(WhiteChar145);

							pushFollow(FOLLOW_constant_in_presetcondition1530);
							lookBackSmthPeriod=constant();
							state._fsp--;

							stream_constant.add(lookBackSmthPeriod.getTree());
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
							// 201:80: -> ^( HigherHighCondition )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:83: ^( HigherHighCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(HigherHighCondition, "HigherHighCondition"), root_1);
								adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
								adaptor.addChild(root_1, (lookBackSmthPeriod!=null?((CommonTree)lookBackSmthPeriod.getTree()):null));
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
				case 8 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:3: ( 'makes a higher low over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherLowCondition constant ^( Double Number[\"-1.0\"] ) ) ) ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant -> ^( HigherLowCondition ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:3: ( 'makes a higher low over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherLowCondition constant ^( Double Number[\"-1.0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:4: 'makes a higher low over' WhiteChar nbDays= constant WhiteChar DAYS
					{
					string_literal146=(Token)match(input,61,FOLLOW_61_in_presetcondition1552);  
					stream_61.add(string_literal146);

					WhiteChar147=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1554);  
					stream_WhiteChar.add(WhiteChar147);

					pushFollow(FOLLOW_constant_in_presetcondition1558);
					nbDays=constant();
					state._fsp--;

					stream_constant.add(nbDays.getTree());
					WhiteChar148=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1560);  
					stream_WhiteChar.add(WhiteChar148);

					DAYS149=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1562);  
					stream_DAYS.add(DAYS149);

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
					// 202:71: -> ^( HigherLowCondition constant ^( Double Number[\"-1.0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:74: ^( HigherLowCondition constant ^( Double Number[\"-1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(HigherLowCondition, "HigherLowCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:104: ^( Double Number[\"-1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "-1.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:8: ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant -> ^( HigherLowCondition ) )?
					int alt28=2;
					int LA28_0 = input.LA(1);
					if ( (LA28_0==WhiteChar) ) {
						int LA28_1 = input.LA(2);
						if ( (LA28_1==68) ) {
							alt28=1;
						}
					}
					switch (alt28) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:10: WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant
							{
							WhiteChar150=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1591);  
							stream_WhiteChar.add(WhiteChar150);

							string_literal151=(Token)match(input,68,FOLLOW_68_in_presetcondition1593);  
							stream_68.add(string_literal151);

							WhiteChar152=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1595);  
							stream_WhiteChar.add(WhiteChar152);

							pushFollow(FOLLOW_constant_in_presetcondition1599);
							lookBackSmthPeriod=constant();
							state._fsp--;

							stream_constant.add(lookBackSmthPeriod.getTree());
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
							// 203:80: -> ^( HigherLowCondition )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:83: ^( HigherLowCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(HigherLowCondition, "HigherLowCondition"), root_1);
								adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
								adaptor.addChild(root_1, (lookBackSmthPeriod!=null?((CommonTree)lookBackSmthPeriod.getTree()):null));
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
				case 9 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:3: ( 'makes a lower high over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerHighCondition constant ^( Double Number[\"-1.0\"] ) ) ) ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant -> ^( LowerHighCondition ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:3: ( 'makes a lower high over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerHighCondition constant ^( Double Number[\"-1.0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:4: 'makes a lower high over' WhiteChar nbDays= constant WhiteChar DAYS
					{
					string_literal153=(Token)match(input,62,FOLLOW_62_in_presetcondition1621);  
					stream_62.add(string_literal153);

					WhiteChar154=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1623);  
					stream_WhiteChar.add(WhiteChar154);

					pushFollow(FOLLOW_constant_in_presetcondition1627);
					nbDays=constant();
					state._fsp--;

					stream_constant.add(nbDays.getTree());
					WhiteChar155=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1629);  
					stream_WhiteChar.add(WhiteChar155);

					DAYS156=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1631);  
					stream_DAYS.add(DAYS156);

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
					// 204:71: -> ^( LowerHighCondition constant ^( Double Number[\"-1.0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:74: ^( LowerHighCondition constant ^( Double Number[\"-1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LowerHighCondition, "LowerHighCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:104: ^( Double Number[\"-1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "-1.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:8: ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant -> ^( LowerHighCondition ) )?
					int alt29=2;
					int LA29_0 = input.LA(1);
					if ( (LA29_0==WhiteChar) ) {
						int LA29_1 = input.LA(2);
						if ( (LA29_1==68) ) {
							alt29=1;
						}
					}
					switch (alt29) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:10: WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant
							{
							WhiteChar157=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1660);  
							stream_WhiteChar.add(WhiteChar157);

							string_literal158=(Token)match(input,68,FOLLOW_68_in_presetcondition1662);  
							stream_68.add(string_literal158);

							WhiteChar159=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1664);  
							stream_WhiteChar.add(WhiteChar159);

							pushFollow(FOLLOW_constant_in_presetcondition1668);
							lookBackSmthPeriod=constant();
							state._fsp--;

							stream_constant.add(lookBackSmthPeriod.getTree());
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
							// 205:80: -> ^( LowerHighCondition )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:83: ^( LowerHighCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LowerHighCondition, "LowerHighCondition"), root_1);
								adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
								adaptor.addChild(root_1, (lookBackSmthPeriod!=null?((CommonTree)lookBackSmthPeriod.getTree()):null));
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
				case 10 :
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:3: ( 'makes a lower low over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerLowCondition constant ^( Double Number[\"-1.0\"] ) ) ) ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant -> ^( LowerLowCondition ) )?
					{
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:3: ( 'makes a lower low over' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerLowCondition constant ^( Double Number[\"-1.0\"] ) ) )
					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:4: 'makes a lower low over' WhiteChar nbDays= constant WhiteChar DAYS
					{
					string_literal160=(Token)match(input,63,FOLLOW_63_in_presetcondition1690);  
					stream_63.add(string_literal160);

					WhiteChar161=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1692);  
					stream_WhiteChar.add(WhiteChar161);

					pushFollow(FOLLOW_constant_in_presetcondition1696);
					nbDays=constant();
					state._fsp--;

					stream_constant.add(nbDays.getTree());
					WhiteChar162=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1698);  
					stream_WhiteChar.add(WhiteChar162);

					DAYS163=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1700);  
					stream_DAYS.add(DAYS163);

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
					// 206:70: -> ^( LowerLowCondition constant ^( Double Number[\"-1.0\"] ) )
					{
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:73: ^( LowerLowCondition constant ^( Double Number[\"-1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LowerLowCondition, "LowerLowCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:102: ^( Double Number[\"-1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Double, "Double"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(Number, "-1.0"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:8: ( WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant -> ^( LowerLowCondition ) )?
					int alt30=2;
					int LA30_0 = input.LA(1);
					if ( (LA30_0==WhiteChar) ) {
						int LA30_1 = input.LA(2);
						if ( (LA30_1==68) ) {
							alt30=1;
						}
					}
					switch (alt30) {
						case 1 :
							// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:10: WhiteChar 'smoothing threshold' WhiteChar lookBackSmthPeriod= constant
							{
							WhiteChar164=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1729);  
							stream_WhiteChar.add(WhiteChar164);

							string_literal165=(Token)match(input,68,FOLLOW_68_in_presetcondition1731);  
							stream_68.add(string_literal165);

							WhiteChar166=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1733);  
							stream_WhiteChar.add(WhiteChar166);

							pushFollow(FOLLOW_constant_in_presetcondition1737);
							lookBackSmthPeriod=constant();
							state._fsp--;

							stream_constant.add(lookBackSmthPeriod.getTree());
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
							// 207:80: -> ^( LowerLowCondition )
							{
								// /home/guil/Developpement/git/pmsqueak/premiumMarkets/pm-core/src/main/antlr3/com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:83: ^( LowerLowCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LowerLowCondition, "LowerLowCondition"), root_1);
								adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
								adaptor.addChild(root_1, (lookBackSmthPeriod!=null?((CommonTree)lookBackSmthPeriod.getTree()):null));
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
	// $ANTLR end "presetcondition"

	// Delegated rules



	public static final BitSet FOLLOW_bullish_condition_in_complete_expression308 = new BitSet(new long[]{0x0180000000000000L});
	public static final BitSet FOLLOW_bearish_condition_in_complete_expression310 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_59_in_bullish_condition337 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition339 = new BitSet(new long[]{0x0000000148200000L});
	public static final BitSet FOLLOW_primary_expression_in_bullish_condition341 = new BitSet(new long[]{0x0000100000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition343 = new BitSet(new long[]{0x0000100000000080L});
	public static final BitSet FOLLOW_COMMA_in_bullish_condition346 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition348 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_56_in_bearish_condition364 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition366 = new BitSet(new long[]{0x0000000148200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_condition369 = new BitSet(new long[]{0x0000100000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition371 = new BitSet(new long[]{0x0000100000000080L});
	public static final BitSet FOLLOW_COMMA_in_bearish_condition374 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_bearish_not_bullish_in_bearish_condition383 = new BitSet(new long[]{0x0000100000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition386 = new BitSet(new long[]{0x0000100000000080L});
	public static final BitSet FOLLOW_COMMA_in_bearish_condition389 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_55_in_bearish_not_bullish406 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish415 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_bearish_not_bullish417 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish419 = new BitSet(new long[]{0x0000000148200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish421 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish442 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_OR_in_bearish_not_bullish444 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish446 = new BitSet(new long[]{0x0000000148200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish449 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_and_expression_in_primary_expression491 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_expression_in_and_expression503 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression506 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_and_expression508 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression510 = new BitSet(new long[]{0x0000000148200000L});
	public static final BitSet FOLLOW_or_expression_in_and_expression512 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_atom_in_or_expression535 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression538 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_OR_in_or_expression540 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression542 = new BitSet(new long[]{0x0000000148200000L});
	public static final BitSet FOLLOW_atom_in_or_expression544 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_booleanhistory_in_atom586 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom590 = new BitSet(new long[]{0x0000100148200000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom592 = new BitSet(new long[]{0x0000100148200000L});
	public static final BitSet FOLLOW_primary_expression_in_atom595 = new BitSet(new long[]{0x0000100000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom597 = new BitSet(new long[]{0x0000100000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom600 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_atom609 = new BitSet(new long[]{0x0000100040000000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom611 = new BitSet(new long[]{0x0000100040000000L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom614 = new BitSet(new long[]{0x0000100148200000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom616 = new BitSet(new long[]{0x0000100148200000L});
	public static final BitSet FOLLOW_primary_expression_in_atom619 = new BitSet(new long[]{0x0000100000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom621 = new BitSet(new long[]{0x0000100000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom624 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_booleanhistory643 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_booleanhistory645 = new BitSet(new long[]{0xF67FE00000000000L,0x000000000000000CL});
	public static final BitSet FOLLOW_presetcondition_in_booleanhistory649 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_opcmpcondition_in_booleanhistory660 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constantcmp_in_booleanhistory671 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_operand688 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Operation_in_operand708 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Number_in_constant722 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_53_in_opcmpcondition744 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition746 = new BitSet(new long[]{0x0000000100200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition748 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_57_in_opcmpcondition764 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition766 = new BitSet(new long[]{0x0000000100200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition768 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_45_in_opcmpcondition785 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition787 = new BitSet(new long[]{0x0000000100200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition789 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition819 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_opcmpcondition821 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition823 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition827 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition829 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition831 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_47_in_opcmpcondition851 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition853 = new BitSet(new long[]{0x0000000100200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition855 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition885 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_opcmpcondition887 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition889 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition893 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition895 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition897 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_49_in_opcmpcondition916 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition918 = new BitSet(new long[]{0x0000000100200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition920 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_50_in_constantcmp943 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp945 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp947 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_54_in_constantcmp964 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp966 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp968 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_58_in_constantcmp984 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp986 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp988 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_66_in_presetcondition1016 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1056 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_64_in_presetcondition1058 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1060 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1064 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition1066 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1068 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition1070 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1072 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1076 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1078 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1080 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_67_in_presetcondition1110 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1150 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_64_in_presetcondition1152 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1154 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1158 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition1160 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1162 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition1164 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1166 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1170 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1172 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1174 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_51_in_presetcondition1203 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1205 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1209 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition1211 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1240 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition1242 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1244 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1248 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1250 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1252 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_52_in_presetcondition1274 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1276 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1280 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition1282 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1311 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition1313 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1315 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1319 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1321 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1323 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_48_in_presetcondition1345 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1347 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1351 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1379 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition1381 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1383 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1387 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1389 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1391 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_46_in_presetcondition1415 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1417 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1421 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1449 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition1451 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1453 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1457 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1459 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1461 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_60_in_presetcondition1483 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1485 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1489 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1491 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1493 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1522 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_68_in_presetcondition1524 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1526 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1530 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_61_in_presetcondition1552 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1554 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1558 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1560 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1562 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1591 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_68_in_presetcondition1593 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1595 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1599 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_62_in_presetcondition1621 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1623 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1627 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1629 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1631 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1660 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_68_in_presetcondition1662 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1664 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1668 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_63_in_presetcondition1690 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1692 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1696 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1698 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1700 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1729 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_68_in_presetcondition1731 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1733 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1737 = new BitSet(new long[]{0x0000000000000002L});
}
