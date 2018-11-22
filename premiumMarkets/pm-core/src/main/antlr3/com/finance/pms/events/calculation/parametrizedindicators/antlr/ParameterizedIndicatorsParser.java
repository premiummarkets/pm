// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2018-11-21 17:01:02
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
		"SupportBreakUp", "Tcheat", "UpRatioCondition", "WS", "WhiteChar", "'NaN'", 
		"'also display'", "'bearish'", "'bullish'", "'crosses down historical'", 
		"'crosses down threshold'", "'crosses up historical'", "'crosses up threshold'", 
		"'direction'", "'ending within'", "'epsilon'", "'equals historical'", 
		"'equals threshold'", "'equals trend'", "'for'", "'goes down more than'", 
		"'goes up more than'", "'greed'", "'is above historical'", "'is above threshold'", 
		"'is bearish if not bullish'", "'is bearish when'", "'is below historical'", 
		"'is below threshold'", "'is bullish when'", "'makes a higher high spanning'", 
		"'makes a higher low spanning'", "'makes a lower high spanning'", "'makes a lower low spanning'", 
		"'makes a support break down spanning'", "'makes a support break up spanning'", 
		"'more than'", "'over'", "'override start shift with'", "'reverses down'", 
		"'reverses up'", "'slope within'", "'smoothed'", "'spanning'", "'starting within'", 
		"'tolerance'", "'trends down'", "'trends flat'", "'trends like'", "'trends unlike'", 
		"'trends up'", "'type'"
	};
	public static final int EOF=-1;
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
	public static final int T__103=103;
	public static final int T__104=104;
	public static final int T__105=105;
	public static final int T__106=106;
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
	public static final int Tcheat=56;
	public static final int UpRatioCondition=57;
	public static final int WS=58;
	public static final int WhiteChar=59;

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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:157:1: complete_expression : bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:157:21: (bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:4: bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift
			{
			pushFollow(FOLLOW_bullish_condition_in_complete_expression416);
			bcond=bullish_condition();
			state._fsp--;

			stream_bullish_condition.add(bcond.getTree());
			pushFollow(FOLLOW_bearish_condition_in_complete_expression418);
			bearish_condition1=bearish_condition((bcond!=null?((CommonTree)bcond.getTree()):null));
			state._fsp--;

			stream_bearish_condition.add(bearish_condition1.getTree());
			pushFollow(FOLLOW_also_display_in_complete_expression421);
			also_display2=also_display();
			state._fsp--;

			stream_also_display.add(also_display2.getTree());
			pushFollow(FOLLOW_fixed_start_shift_in_complete_expression423);
			fixed_start_shift3=fixed_start_shift();
			state._fsp--;

			stream_fixed_start_shift.add(fixed_start_shift3.getTree());
			// AST REWRITE
			// elements: also_display, bearish_condition, bullish_condition, fixed_start_shift
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 159:90: -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:93: ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:1: bullish_condition : 'is bullish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression ;
	public final ParameterizedIndicatorsParser.bullish_condition_return bullish_condition() throws RecognitionException {
		ParameterizedIndicatorsParser.bullish_condition_return retval = new ParameterizedIndicatorsParser.bullish_condition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal4=null;
		Token WhiteChar5=null;
		Token WhiteChar7=null;
		Token SEMICOLUMN8=null;
		Token WhiteChar9=null;
		ParserRuleReturnScope primary_expression6 =null;

		CommonTree string_literal4_tree=null;
		CommonTree WhiteChar5_tree=null;
		CommonTree WhiteChar7_tree=null;
		CommonTree SEMICOLUMN8_tree=null;
		CommonTree WhiteChar9_tree=null;
		RewriteRuleTokenStream stream_SEMICOLUMN=new RewriteRuleTokenStream(adaptor,"token SEMICOLUMN");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:19: ( 'is bullish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:2: 'is bullish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )*
			{
			string_literal4=(Token)match(input,84,FOLLOW_84_in_bullish_condition452);  
			stream_84.add(string_literal4);

			WhiteChar5=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition454);  
			stream_WhiteChar.add(WhiteChar5);

			pushFollow(FOLLOW_primary_expression_in_bullish_condition456);
			primary_expression6=primary_expression();
			state._fsp--;

			stream_primary_expression.add(primary_expression6.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:49: ( WhiteChar )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==WhiteChar) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:49: WhiteChar
					{
					WhiteChar7=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition458);  
					stream_WhiteChar.add(WhiteChar7);

					}
					break;

				default :
					break loop1;
				}
			}

			SEMICOLUMN8=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_bullish_condition461);  
			stream_SEMICOLUMN.add(SEMICOLUMN8);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:71: ( WhiteChar )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==WhiteChar) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:71: WhiteChar
					{
					WhiteChar9=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition463);  
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
			// 163:82: -> primary_expression
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:165:1: bearish_condition[CommonTree bcond] : ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> bearish_not_bullish );
	public final ParameterizedIndicatorsParser.bearish_condition_return bearish_condition(CommonTree bcond) throws RecognitionException {
		ParameterizedIndicatorsParser.bearish_condition_return retval = new ParameterizedIndicatorsParser.bearish_condition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal10=null;
		Token WhiteChar11=null;
		Token WhiteChar13=null;
		Token SEMICOLUMN14=null;
		Token WhiteChar15=null;
		Token WhiteChar17=null;
		Token SEMICOLUMN18=null;
		Token WhiteChar19=null;
		ParserRuleReturnScope primary_expression12 =null;
		ParserRuleReturnScope bearish_not_bullish16 =null;

		CommonTree string_literal10_tree=null;
		CommonTree WhiteChar11_tree=null;
		CommonTree WhiteChar13_tree=null;
		CommonTree SEMICOLUMN14_tree=null;
		CommonTree WhiteChar15_tree=null;
		CommonTree WhiteChar17_tree=null;
		CommonTree SEMICOLUMN18_tree=null;
		CommonTree WhiteChar19_tree=null;
		RewriteRuleTokenStream stream_SEMICOLUMN=new RewriteRuleTokenStream(adaptor,"token SEMICOLUMN");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
		RewriteRuleSubtreeStream stream_bearish_not_bullish=new RewriteRuleSubtreeStream(adaptor,"rule bearish_not_bullish");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:165:37: ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> bearish_not_bullish )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==81) ) {
				alt7=1;
			}
			else if ( (LA7_0==80) ) {
				alt7=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:2: 'is bearish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )*
					{
					string_literal10=(Token)match(input,81,FOLLOW_81_in_bearish_condition479);  
					stream_81.add(string_literal10);

					WhiteChar11=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition481);  
					stream_WhiteChar.add(WhiteChar11);

					pushFollow(FOLLOW_primary_expression_in_bearish_condition483);
					primary_expression12=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression12.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:49: ( WhiteChar )*
					loop3:
					while (true) {
						int alt3=2;
						int LA3_0 = input.LA(1);
						if ( (LA3_0==WhiteChar) ) {
							alt3=1;
						}

						switch (alt3) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:49: WhiteChar
							{
							WhiteChar13=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition485);  
							stream_WhiteChar.add(WhiteChar13);

							}
							break;

						default :
							break loop3;
						}
					}

					SEMICOLUMN14=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_bearish_condition488);  
					stream_SEMICOLUMN.add(SEMICOLUMN14);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:71: ( WhiteChar )*
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( (LA4_0==WhiteChar) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:71: WhiteChar
							{
							WhiteChar15=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition490);  
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
					// 166:82: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:2: bearish_not_bullish[$bcond] ( WhiteChar )* SEMICOLUMN ( WhiteChar )*
					{
					pushFollow(FOLLOW_bearish_not_bullish_in_bearish_condition500);
					bearish_not_bullish16=bearish_not_bullish(bcond);
					state._fsp--;

					stream_bearish_not_bullish.add(bearish_not_bullish16.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:30: ( WhiteChar )*
					loop5:
					while (true) {
						int alt5=2;
						int LA5_0 = input.LA(1);
						if ( (LA5_0==WhiteChar) ) {
							alt5=1;
						}

						switch (alt5) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:30: WhiteChar
							{
							WhiteChar17=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition503);  
							stream_WhiteChar.add(WhiteChar17);

							}
							break;

						default :
							break loop5;
						}
					}

					SEMICOLUMN18=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_bearish_condition506);  
					stream_SEMICOLUMN.add(SEMICOLUMN18);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:52: ( WhiteChar )*
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( (LA6_0==WhiteChar) ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:52: WhiteChar
							{
							WhiteChar19=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition508);  
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
					// 167:63: -> bearish_not_bullish
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:2: also_display : ( 'also display' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition );
	public final ParameterizedIndicatorsParser.also_display_return also_display() throws RecognitionException {
		ParameterizedIndicatorsParser.also_display_return retval = new ParameterizedIndicatorsParser.also_display_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal20=null;
		Token WhiteChar21=null;
		Token WhiteChar23=null;
		Token SEMICOLUMN24=null;
		ParserRuleReturnScope primary_expression22 =null;

		CommonTree string_literal20_tree=null;
		CommonTree WhiteChar21_tree=null;
		CommonTree WhiteChar23_tree=null;
		CommonTree SEMICOLUMN24_tree=null;
		RewriteRuleTokenStream stream_SEMICOLUMN=new RewriteRuleTokenStream(adaptor,"token SEMICOLUMN");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:15: ( 'also display' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==61) ) {
				alt9=1;
			}
			else if ( (LA9_0==EOF||LA9_0==93) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:3: 'also display' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN
					{
					string_literal20=(Token)match(input,61,FOLLOW_61_in_also_display525);  
					stream_61.add(string_literal20);

					WhiteChar21=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display527);  
					stream_WhiteChar.add(WhiteChar21);

					pushFollow(FOLLOW_primary_expression_in_also_display529);
					primary_expression22=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression22.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:47: ( WhiteChar )*
					loop8:
					while (true) {
						int alt8=2;
						int LA8_0 = input.LA(1);
						if ( (LA8_0==WhiteChar) ) {
							alt8=1;
						}

						switch (alt8) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:47: WhiteChar
							{
							WhiteChar23=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display531);  
							stream_WhiteChar.add(WhiteChar23);

							}
							break;

						default :
							break loop8;
						}
					}

					SEMICOLUMN24=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_also_display534);  
					stream_SEMICOLUMN.add(SEMICOLUMN24);

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
					// 170:69: -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:72: ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndBooleanMapCondition, "AndBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:97: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// 171:3: -> NullCondition
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:2: fixed_start_shift : ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS SEMICOLUMN ->| -> ^( Number NumberToken[\"-1\"] ) );
	public final ParameterizedIndicatorsParser.fixed_start_shift_return fixed_start_shift() throws RecognitionException {
		ParameterizedIndicatorsParser.fixed_start_shift_return retval = new ParameterizedIndicatorsParser.fixed_start_shift_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal25=null;
		Token WhiteChar26=null;
		Token WhiteChar27=null;
		Token DAYS28=null;
		Token SEMICOLUMN29=null;
		ParserRuleReturnScope fixedStartShift =null;

		CommonTree string_literal25_tree=null;
		CommonTree WhiteChar26_tree=null;
		CommonTree WhiteChar27_tree=null;
		CommonTree DAYS28_tree=null;
		CommonTree SEMICOLUMN29_tree=null;
		RewriteRuleTokenStream stream_SEMICOLUMN=new RewriteRuleTokenStream(adaptor,"token SEMICOLUMN");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:20: ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS SEMICOLUMN ->| -> ^( Number NumberToken[\"-1\"] ) )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==93) ) {
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:174:3: 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS SEMICOLUMN
					{
					string_literal25=(Token)match(input,93,FOLLOW_93_in_fixed_start_shift569);  
					stream_93.add(string_literal25);

					WhiteChar26=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift571);  
					stream_WhiteChar.add(WhiteChar26);

					pushFollow(FOLLOW_constant_in_fixed_start_shift575);
					fixedStartShift=constant();
					state._fsp--;

					stream_constant.add(fixedStartShift.getTree());
					WhiteChar27=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift577);  
					stream_WhiteChar.add(WhiteChar27);

					DAYS28=(Token)match(input,DAYS,FOLLOW_DAYS_in_fixed_start_shift579);  
					stream_DAYS.add(DAYS28);

					SEMICOLUMN29=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_fixed_start_shift581);  
					stream_SEMICOLUMN.add(SEMICOLUMN29);

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
					// 174:92: ->
					{
						adaptor.addChild(root_0, (fixedStartShift!=null?((CommonTree)fixedStartShift.getTree()):null));
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
					// 175:3: -> ^( Number NumberToken[\"-1\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:175:6: ^( Number NumberToken[\"-1\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:178:1: bearish_not_bullish[CommonTree bcond] : 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( NotBooleanMapCondition ) primary_expression ) | -> ^( NotBooleanMapCondition ) ) ;
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
		RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
		RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:178:39: ( 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( NotBooleanMapCondition ) primary_expression ) | -> ^( NotBooleanMapCondition ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:2: 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( NotBooleanMapCondition ) primary_expression ) | -> ^( NotBooleanMapCondition ) )
			{
			string_literal30=(Token)match(input,80,FOLLOW_80_in_bearish_not_bullish611);  
			stream_80.add(string_literal30);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:3: ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( NotBooleanMapCondition ) primary_expression ) | -> ^( NotBooleanMapCondition ) )
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
				case SEMICOLUMN:
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
			else if ( (LA11_0==SEMICOLUMN) ) {
				alt11=3;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}

			switch (alt11) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:3: WhiteChar AND WhiteChar primary_expression
					{
					WhiteChar31=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish619);  
					stream_WhiteChar.add(WhiteChar31);

					AND32=(Token)match(input,AND,FOLLOW_AND_in_bearish_not_bullish621);  
					stream_AND.add(AND32);

					WhiteChar33=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish623);  
					stream_WhiteChar.add(WhiteChar33);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish625);
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
					// 181:46: -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:49: ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndBooleanMapCondition, "AndBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:74: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:109: ^( NotBooleanMapCondition )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_2);
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:182:3: WhiteChar OR WhiteChar primary_expression
					{
					WhiteChar35=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish651);  
					stream_WhiteChar.add(WhiteChar35);

					OR36=(Token)match(input,OR,FOLLOW_OR_in_bearish_not_bullish653);  
					stream_OR.add(OR36);

					WhiteChar37=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish655);  
					stream_WhiteChar.add(WhiteChar37);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish657);
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
					// 182:45: -> ^( OrBooleanMapCondition ^( NotBooleanMapCondition ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:182:48: ^( OrBooleanMapCondition ^( NotBooleanMapCondition ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrBooleanMapCondition, "OrBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:182:72: ^( NotBooleanMapCondition )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_2);
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
					// 183:3: -> ^( NotBooleanMapCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:6: ^( NotBooleanMapCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_1);
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:1: primary_expression : and_expression ;
	public final ParameterizedIndicatorsParser.primary_expression_return primary_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.primary_expression_return retval = new ParameterizedIndicatorsParser.primary_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope and_expression39 =null;


		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:20: ( and_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:2: and_expression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_and_expression_in_primary_expression698);
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:1: and_expression : or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndBooleanMapCondition or_expression ( or_expression )* ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:16: ( or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndBooleanMapCondition or_expression ( or_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:3: or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )*
			{
			pushFollow(FOLLOW_or_expression_in_and_expression709);
			or_expression40=or_expression();
			state._fsp--;

			stream_or_expression.add(or_expression40.getTree());
			pushFollow(FOLLOW_lenient_in_and_expression713);
			lenientParam=lenient();
			state._fsp--;

			stream_lenient.add(lenientParam.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:38: ( WhiteChar AND WhiteChar or_expression )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:39: WhiteChar AND WhiteChar or_expression
					{
					WhiteChar41=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression716);  
					stream_WhiteChar.add(WhiteChar41);

					AND42=(Token)match(input,AND,FOLLOW_AND_in_and_expression718);  
					stream_AND.add(AND42);

					WhiteChar43=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression720);  
					stream_WhiteChar.add(WhiteChar43);

					pushFollow(FOLLOW_or_expression_in_and_expression722);
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
			// 192:79: -> ^( AndBooleanMapCondition or_expression ( or_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:82: ^( AndBooleanMapCondition or_expression ( or_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndBooleanMapCondition, "AndBooleanMapCondition"), root_1);
				adaptor.addChild(root_1, (lenientParam!=null?((CommonTree)lenientParam.getTree()):null));
				adaptor.addChild(root_1, stream_or_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:142: ( or_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:1: or_expression : matches_expression ( WhiteChar OR WhiteChar matches_expression )* -> ^( OrBooleanMapCondition matches_expression ( matches_expression )* ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:15: ( matches_expression ( WhiteChar OR WhiteChar matches_expression )* -> ^( OrBooleanMapCondition matches_expression ( matches_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:3: matches_expression ( WhiteChar OR WhiteChar matches_expression )*
			{
			pushFollow(FOLLOW_matches_expression_in_or_expression749);
			matches_expression45=matches_expression();
			state._fsp--;

			stream_matches_expression.add(matches_expression45.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:22: ( WhiteChar OR WhiteChar matches_expression )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:23: WhiteChar OR WhiteChar matches_expression
					{
					WhiteChar46=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression752);  
					stream_WhiteChar.add(WhiteChar46);

					OR47=(Token)match(input,OR,FOLLOW_OR_in_or_expression754);  
					stream_OR.add(OR47);

					WhiteChar48=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression756);  
					stream_WhiteChar.add(WhiteChar48);

					pushFollow(FOLLOW_matches_expression_in_or_expression758);
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
			// 195:67: -> ^( OrBooleanMapCondition matches_expression ( matches_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:70: ^( OrBooleanMapCondition matches_expression ( matches_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrBooleanMapCondition, "OrBooleanMapCondition"), root_1);
				adaptor.addChild(root_1, stream_matches_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:113: ( matches_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:1: matches_expression : atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )? -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? ) ;
	public final ParameterizedIndicatorsParser.matches_expression_return matches_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.matches_expression_return retval = new ParameterizedIndicatorsParser.matches_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar51=null;
		Token MATCHING52=null;
		Token WhiteChar53=null;
		Token char_literal54=null;
		Token char_literal56=null;
		Token char_literal58=null;
		Token WhiteChar59=null;
		ParserRuleReturnScope atom50 =null;
		ParserRuleReturnScope constant55 =null;
		ParserRuleReturnScope constant57 =null;
		ParserRuleReturnScope atom60 =null;

		CommonTree WhiteChar51_tree=null;
		CommonTree MATCHING52_tree=null;
		CommonTree WhiteChar53_tree=null;
		CommonTree char_literal54_tree=null;
		CommonTree char_literal56_tree=null;
		CommonTree char_literal58_tree=null;
		CommonTree WhiteChar59_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_OPENSQRT=new RewriteRuleTokenStream(adaptor,"token OPENSQRT");
		RewriteRuleTokenStream stream_MATCHING=new RewriteRuleTokenStream(adaptor,"token MATCHING");
		RewriteRuleTokenStream stream_CLOSESQRT=new RewriteRuleTokenStream(adaptor,"token CLOSESQRT");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:20: ( atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )? -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:3: atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )?
			{
			pushFollow(FOLLOW_atom_in_matches_expression783);
			atom50=atom();
			state._fsp--;

			stream_atom.add(atom50.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:8: ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )?
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0==WhiteChar) ) {
				int LA15_1 = input.LA(2);
				if ( (LA15_1==MATCHING) ) {
					alt15=1;
				}
			}
			switch (alt15) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:9: WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom
					{
					WhiteChar51=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression786);  
					stream_WhiteChar.add(WhiteChar51);

					MATCHING52=(Token)match(input,MATCHING,FOLLOW_MATCHING_in_matches_expression788);  
					stream_MATCHING.add(MATCHING52);

					WhiteChar53=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression790);  
					stream_WhiteChar.add(WhiteChar53);

					char_literal54=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_matches_expression792);  
					stream_OPENSQRT.add(char_literal54);

					pushFollow(FOLLOW_constant_in_matches_expression794);
					constant55=constant();
					state._fsp--;

					stream_constant.add(constant55.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:51: ( ',' constant )*
					loop14:
					while (true) {
						int alt14=2;
						int LA14_0 = input.LA(1);
						if ( (LA14_0==COMMA) ) {
							alt14=1;
						}

						switch (alt14) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:52: ',' constant
							{
							char_literal56=(Token)match(input,COMMA,FOLLOW_COMMA_in_matches_expression797);  
							stream_COMMA.add(char_literal56);

							pushFollow(FOLLOW_constant_in_matches_expression799);
							constant57=constant();
							state._fsp--;

							stream_constant.add(constant57.getTree());
							}
							break;

						default :
							break loop14;
						}
					}

					char_literal58=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_matches_expression803);  
					stream_CLOSESQRT.add(char_literal58);

					WhiteChar59=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression805);  
					stream_WhiteChar.add(WhiteChar59);

					pushFollow(FOLLOW_atom_in_matches_expression807);
					atom60=atom();
					state._fsp--;

					stream_atom.add(atom60.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: constant, atom, atom
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 198:88: -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:91: ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(MatchingBooleanMapCondition, "MatchingBooleanMapCondition"), root_1);
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:121: ( constant )*
				while ( stream_constant.hasNext() ) {
					adaptor.addChild(root_1, stream_constant.nextTree());
				}
				stream_constant.reset();

				adaptor.addChild(root_1, stream_atom.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:136: ( atom )?
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:1: atom : ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotBooleanMapCondition primary_expression ) );
	public final ParameterizedIndicatorsParser.atom_return atom() throws RecognitionException {
		ParameterizedIndicatorsParser.atom_return retval = new ParameterizedIndicatorsParser.atom_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal62=null;
		Token WhiteChar63=null;
		Token WhiteChar65=null;
		Token char_literal66=null;
		Token string_literal67=null;
		Token WhiteChar68=null;
		Token char_literal69=null;
		Token WhiteChar70=null;
		Token WhiteChar72=null;
		Token char_literal73=null;
		ParserRuleReturnScope booleanhistory61 =null;
		ParserRuleReturnScope primary_expression64 =null;
		ParserRuleReturnScope primary_expression71 =null;

		CommonTree char_literal62_tree=null;
		CommonTree WhiteChar63_tree=null;
		CommonTree WhiteChar65_tree=null;
		CommonTree char_literal66_tree=null;
		CommonTree string_literal67_tree=null;
		CommonTree WhiteChar68_tree=null;
		CommonTree char_literal69_tree=null;
		CommonTree WhiteChar70_tree=null;
		CommonTree WhiteChar72_tree=null;
		CommonTree char_literal73_tree=null;
		RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_CLOSEPARENTEHSIS=new RewriteRuleTokenStream(adaptor,"token CLOSEPARENTEHSIS");
		RewriteRuleTokenStream stream_OPENPARENTEHSIS=new RewriteRuleTokenStream(adaptor,"token OPENPARENTEHSIS");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:6: ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotBooleanMapCondition primary_expression ) )
			int alt21=3;
			switch ( input.LA(1) ) {
			case HistoricalData:
			case Operation:
				{
				alt21=1;
				}
				break;
			case OPENPARENTEHSIS:
				{
				alt21=2;
				}
				break;
			case NOT:
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:3: booleanhistory
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_booleanhistory_in_atom835);
					booleanhistory61=booleanhistory();
					state._fsp--;

					adaptor.addChild(root_0, booleanhistory61.getTree());

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:3: '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					char_literal62=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom841);  
					stream_OPENPARENTEHSIS.add(char_literal62);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:7: ( WhiteChar )*
					loop16:
					while (true) {
						int alt16=2;
						int LA16_0 = input.LA(1);
						if ( (LA16_0==WhiteChar) ) {
							alt16=1;
						}

						switch (alt16) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:7: WhiteChar
							{
							WhiteChar63=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom843);  
							stream_WhiteChar.add(WhiteChar63);

							}
							break;

						default :
							break loop16;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom846);
					primary_expression64=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression64.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:37: ( WhiteChar )*
					loop17:
					while (true) {
						int alt17=2;
						int LA17_0 = input.LA(1);
						if ( (LA17_0==WhiteChar) ) {
							alt17=1;
						}

						switch (alt17) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:37: WhiteChar
							{
							WhiteChar65=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom848);  
							stream_WhiteChar.add(WhiteChar65);

							}
							break;

						default :
							break loop17;
						}
					}

					char_literal66=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom851);  
					stream_CLOSEPARENTEHSIS.add(char_literal66);

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
					// 202:52: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:3: 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					string_literal67=(Token)match(input,NOT,FOLLOW_NOT_in_atom861);  
					stream_NOT.add(string_literal67);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:9: ( WhiteChar )*
					loop18:
					while (true) {
						int alt18=2;
						int LA18_0 = input.LA(1);
						if ( (LA18_0==WhiteChar) ) {
							alt18=1;
						}

						switch (alt18) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:9: WhiteChar
							{
							WhiteChar68=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom863);  
							stream_WhiteChar.add(WhiteChar68);

							}
							break;

						default :
							break loop18;
						}
					}

					char_literal69=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom866);  
					stream_OPENPARENTEHSIS.add(char_literal69);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:24: ( WhiteChar )*
					loop19:
					while (true) {
						int alt19=2;
						int LA19_0 = input.LA(1);
						if ( (LA19_0==WhiteChar) ) {
							alt19=1;
						}

						switch (alt19) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:24: WhiteChar
							{
							WhiteChar70=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom868);  
							stream_WhiteChar.add(WhiteChar70);

							}
							break;

						default :
							break loop19;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom871);
					primary_expression71=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression71.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:54: ( WhiteChar )*
					loop20:
					while (true) {
						int alt20=2;
						int LA20_0 = input.LA(1);
						if ( (LA20_0==WhiteChar) ) {
							alt20=1;
						}

						switch (alt20) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:54: WhiteChar
							{
							WhiteChar72=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom873);  
							stream_WhiteChar.add(WhiteChar72);

							}
							break;

						default :
							break loop20;
						}
					}

					char_literal73=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom876);  
					stream_CLOSEPARENTEHSIS.add(char_literal73);

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
					// 203:69: -> ^( NotBooleanMapCondition primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:72: ^( NotBooleanMapCondition primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_1);
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:1: booleanhistory : firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) ;
	public final ParameterizedIndicatorsParser.booleanhistory_return booleanhistory() throws RecognitionException {
		ParameterizedIndicatorsParser.booleanhistory_return retval = new ParameterizedIndicatorsParser.booleanhistory_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar74=null;
		ParserRuleReturnScope firstOp =null;
		ParserRuleReturnScope presetcondition75 =null;
		ParserRuleReturnScope opcmpcondition76 =null;
		ParserRuleReturnScope constantcmp77 =null;

		CommonTree WhiteChar74_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_presetcondition=new RewriteRuleSubtreeStream(adaptor,"rule presetcondition");
		RewriteRuleSubtreeStream stream_constantcmp=new RewriteRuleSubtreeStream(adaptor,"rule constantcmp");
		RewriteRuleSubtreeStream stream_opcmpcondition=new RewriteRuleSubtreeStream(adaptor,"rule opcmpcondition");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:16: (firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:2: firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			{
			pushFollow(FOLLOW_operand_in_booleanhistory899);
			firstOp=operand();
			state._fsp--;

			stream_operand.add(firstOp.getTree());
			WhiteChar74=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_booleanhistory901);  
			stream_WhiteChar.add(WhiteChar74);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:28: ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			int alt22=3;
			switch ( input.LA(1) ) {
			case 65:
			case 67:
			case 75:
			case 76:
			case 85:
			case 86:
			case 87:
			case 88:
			case 89:
			case 90:
			case 94:
			case 95:
			case 101:
			case 102:
			case 105:
				{
				alt22=1;
				}
				break;
			case 64:
			case 66:
			case 71:
			case 78:
			case 82:
			case 103:
			case 104:
				{
				alt22=2;
				}
				break;
			case 72:
			case 73:
			case 79:
			case 83:
				{
				alt22=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}
			switch (alt22) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:3: presetcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_presetcondition_in_booleanhistory907);
					presetcondition75=presetcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_presetcondition.add(presetcondition75.getTree());
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
					// 209:34: -> presetcondition
					{
						adaptor.addChild(root_0, stream_presetcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:3: opcmpcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_opcmpcondition_in_booleanhistory918);
					opcmpcondition76=opcmpcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_opcmpcondition.add(opcmpcondition76.getTree());
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
					// 210:33: -> opcmpcondition
					{
						adaptor.addChild(root_0, stream_opcmpcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:3: constantcmp[$firstOp.tree]
					{
					pushFollow(FOLLOW_constantcmp_in_booleanhistory929);
					constantcmp77=constantcmp((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_constantcmp.add(constantcmp77.getTree());
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
					// 211:30: -> constantcmp
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:1: operand : ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation );
	public final ParameterizedIndicatorsParser.operand_return operand() throws RecognitionException {
		ParameterizedIndicatorsParser.operand_return retval = new ParameterizedIndicatorsParser.operand_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token opName=null;
		Token HistoricalData78=null;

		CommonTree opName_tree=null;
		CommonTree HistoricalData78_tree=null;
		RewriteRuleTokenStream stream_Operation=new RewriteRuleTokenStream(adaptor,"token Operation");
		RewriteRuleTokenStream stream_HistoricalData=new RewriteRuleTokenStream(adaptor,"token HistoricalData");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:9: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation )
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( (LA23_0==HistoricalData) ) {
				alt23=1;
			}
			else if ( (LA23_0==Operation) ) {
				alt23=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}

			switch (alt23) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:11: HistoricalData
					{
					HistoricalData78=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_operand944);  
					stream_HistoricalData.add(HistoricalData78);

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
					// 213:26: -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:29: ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:46: ^( OperationOutput HistoricalData )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
						adaptor.addChild(root_2, stream_HistoricalData.nextNode());
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:80: ^( String StringToken[\"\\\"THIS\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:117: opName= Operation
					{
					opName=(Token)match(input,Operation,FOLLOW_Operation_in_operand971);  
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
					// 213:171: -> Operation
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:1: constant : ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) );
	public final ParameterizedIndicatorsParser.constant_return constant() throws RecognitionException {
		ParameterizedIndicatorsParser.constant_return retval = new ParameterizedIndicatorsParser.constant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NumberToken79=null;
		Token string_literal80=null;

		CommonTree NumberToken79_tree=null;
		CommonTree string_literal80_tree=null;
		RewriteRuleTokenStream stream_NumberToken=new RewriteRuleTokenStream(adaptor,"token NumberToken");
		RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:10: ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) )
			int alt24=2;
			int LA24_0 = input.LA(1);
			if ( (LA24_0==NumberToken) ) {
				alt24=1;
			}
			else if ( (LA24_0==60) ) {
				alt24=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 24, 0, input);
				throw nvae;
			}

			switch (alt24) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:13: NumberToken
					{
					NumberToken79=(Token)match(input,NumberToken,FOLLOW_NumberToken_in_constant985);  
					stream_NumberToken.add(NumberToken79);

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
					// 214:25: -> ^( Number NumberToken )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:28: ^( Number NumberToken )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:52: 'NaN'
					{
					string_literal80=(Token)match(input,60,FOLLOW_60_in_constant997);  
					stream_60.add(string_literal80);

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
					// 214:58: -> ^( Number NumberToken[\"NaN\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:61: ^( Number NumberToken[\"NaN\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:1: stringconstant : StringToken -> ^( String StringToken ) ;
	public final ParameterizedIndicatorsParser.stringconstant_return stringconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.stringconstant_return retval = new ParameterizedIndicatorsParser.stringconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token StringToken81=null;

		CommonTree StringToken81_tree=null;
		RewriteRuleTokenStream stream_StringToken=new RewriteRuleTokenStream(adaptor,"token StringToken");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:16: ( StringToken -> ^( String StringToken ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:18: StringToken
			{
			StringToken81=(Token)match(input,StringToken,FOLLOW_StringToken_in_stringconstant1013);  
			stream_StringToken.add(StringToken81);

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
			// 215:30: -> ^( String StringToken )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:33: ^( String StringToken )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:1: trendconstant : ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) );
	public final ParameterizedIndicatorsParser.trendconstant_return trendconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.trendconstant_return retval = new ParameterizedIndicatorsParser.trendconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal82=null;
		Token string_literal83=null;

		CommonTree string_literal82_tree=null;
		CommonTree string_literal83_tree=null;
		RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
		RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:15: ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) )
			int alt25=2;
			int LA25_0 = input.LA(1);
			if ( (LA25_0==63) ) {
				alt25=1;
			}
			else if ( (LA25_0==62) ) {
				alt25=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 25, 0, input);
				throw nvae;
			}

			switch (alt25) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:17: 'bullish'
					{
					string_literal82=(Token)match(input,63,FOLLOW_63_in_trendconstant1028);  
					stream_63.add(string_literal82);

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
					// 216:27: -> ^( String StringToken[\"\\\"bullish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:30: ^( String StringToken[\"\\\"bullish\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:69: 'bearish'
					{
					string_literal83=(Token)match(input,62,FOLLOW_62_in_trendconstant1041);  
					stream_62.add(string_literal83);

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
					// 216:79: -> ^( String StringToken[\"\\\"bearish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:82: ^( String StringToken[\"\\\"bearish\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:1: lenient : ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ;
	public final ParameterizedIndicatorsParser.lenient_return lenient() throws RecognitionException {
		ParameterizedIndicatorsParser.lenient_return retval = new ParameterizedIndicatorsParser.lenient_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar84=null;
		Token LENIENT85=null;

		CommonTree WhiteChar84_tree=null;
		CommonTree LENIENT85_tree=null;
		RewriteRuleTokenStream stream_LENIENT=new RewriteRuleTokenStream(adaptor,"token LENIENT");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:9: ( ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			int alt26=2;
			int LA26_0 = input.LA(1);
			if ( (LA26_0==WhiteChar) ) {
				int LA26_1 = input.LA(2);
				if ( (LA26_1==LENIENT) ) {
					alt26=1;
				}
				else if ( (LA26_1==AND||LA26_1==CLOSEPARENTEHSIS||LA26_1==SEMICOLUMN||LA26_1==WhiteChar) ) {
					alt26=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 26, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA26_0==CLOSEPARENTEHSIS||LA26_0==SEMICOLUMN) ) {
				alt26=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 26, 0, input);
				throw nvae;
			}

			switch (alt26) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:12: WhiteChar LENIENT
					{
					WhiteChar84=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_lenient1058);  
					stream_WhiteChar.add(WhiteChar84);

					LENIENT85=(Token)match(input,LENIENT,FOLLOW_LENIENT_in_lenient1060);  
					stream_LENIENT.add(LENIENT85);

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
					// 217:30: -> ^( String StringToken[\"\\\"TRUE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:33: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:69: 
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
					// 217:69: -> ^( String StringToken[\"\\\"FALSE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:72: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:1: opcmpcondition[CommonTree firstOp] : ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) );
	public final ParameterizedIndicatorsParser.opcmpcondition_return opcmpcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.opcmpcondition_return retval = new ParameterizedIndicatorsParser.opcmpcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal86=null;
		Token WhiteChar87=null;
		Token WhiteChar88=null;
		Token string_literal89=null;
		Token WhiteChar90=null;
		Token WhiteChar91=null;
		Token DAYS92=null;
		Token string_literal93=null;
		Token WhiteChar94=null;
		Token WhiteChar95=null;
		Token string_literal96=null;
		Token WhiteChar97=null;
		Token WhiteChar98=null;
		Token DAYS99=null;
		Token string_literal100=null;
		Token WhiteChar101=null;
		Token WhiteChar102=null;
		Token string_literal103=null;
		Token WhiteChar104=null;
		Token WhiteChar105=null;
		Token DAYS106=null;
		Token string_literal107=null;
		Token WhiteChar108=null;
		Token WhiteChar110=null;
		Token string_literal111=null;
		Token WhiteChar112=null;
		Token WhiteChar113=null;
		Token DAYS114=null;
		Token WhiteChar115=null;
		Token string_literal116=null;
		Token WhiteChar117=null;
		Token WhiteChar118=null;
		Token DAYS119=null;
		Token string_literal120=null;
		Token WhiteChar121=null;
		Token WhiteChar123=null;
		Token string_literal124=null;
		Token WhiteChar125=null;
		Token WhiteChar126=null;
		Token DAYS127=null;
		Token WhiteChar128=null;
		Token string_literal129=null;
		Token WhiteChar130=null;
		Token WhiteChar131=null;
		Token DAYS132=null;
		Token string_literal133=null;
		Token WhiteChar134=null;
		Token WhiteChar135=null;
		Token string_literal136=null;
		Token WhiteChar137=null;
		Token WhiteChar138=null;
		Token DAYS139=null;
		Token WhiteChar140=null;
		Token string_literal141=null;
		Token WhiteChar142=null;
		Token WhiteChar143=null;
		Token DAYS144=null;
		Token WhiteChar145=null;
		Token string_literal146=null;
		Token WhiteChar147=null;
		Token WhiteChar148=null;
		Token string_literal149=null;
		Token WhiteChar150=null;
		Token string_literal151=null;
		Token WhiteChar152=null;
		Token WhiteChar153=null;
		Token string_literal154=null;
		Token WhiteChar155=null;
		Token WhiteChar156=null;
		Token DAYS157=null;
		Token WhiteChar158=null;
		Token string_literal159=null;
		Token WhiteChar160=null;
		Token WhiteChar161=null;
		Token DAYS162=null;
		Token WhiteChar163=null;
		Token string_literal164=null;
		Token WhiteChar165=null;
		ParserRuleReturnScope secondOp =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope spanningNbDays =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope direction =null;
		ParserRuleReturnScope epsilon =null;
		ParserRuleReturnScope operand109 =null;
		ParserRuleReturnScope operand122 =null;

		CommonTree string_literal86_tree=null;
		CommonTree WhiteChar87_tree=null;
		CommonTree WhiteChar88_tree=null;
		CommonTree string_literal89_tree=null;
		CommonTree WhiteChar90_tree=null;
		CommonTree WhiteChar91_tree=null;
		CommonTree DAYS92_tree=null;
		CommonTree string_literal93_tree=null;
		CommonTree WhiteChar94_tree=null;
		CommonTree WhiteChar95_tree=null;
		CommonTree string_literal96_tree=null;
		CommonTree WhiteChar97_tree=null;
		CommonTree WhiteChar98_tree=null;
		CommonTree DAYS99_tree=null;
		CommonTree string_literal100_tree=null;
		CommonTree WhiteChar101_tree=null;
		CommonTree WhiteChar102_tree=null;
		CommonTree string_literal103_tree=null;
		CommonTree WhiteChar104_tree=null;
		CommonTree WhiteChar105_tree=null;
		CommonTree DAYS106_tree=null;
		CommonTree string_literal107_tree=null;
		CommonTree WhiteChar108_tree=null;
		CommonTree WhiteChar110_tree=null;
		CommonTree string_literal111_tree=null;
		CommonTree WhiteChar112_tree=null;
		CommonTree WhiteChar113_tree=null;
		CommonTree DAYS114_tree=null;
		CommonTree WhiteChar115_tree=null;
		CommonTree string_literal116_tree=null;
		CommonTree WhiteChar117_tree=null;
		CommonTree WhiteChar118_tree=null;
		CommonTree DAYS119_tree=null;
		CommonTree string_literal120_tree=null;
		CommonTree WhiteChar121_tree=null;
		CommonTree WhiteChar123_tree=null;
		CommonTree string_literal124_tree=null;
		CommonTree WhiteChar125_tree=null;
		CommonTree WhiteChar126_tree=null;
		CommonTree DAYS127_tree=null;
		CommonTree WhiteChar128_tree=null;
		CommonTree string_literal129_tree=null;
		CommonTree WhiteChar130_tree=null;
		CommonTree WhiteChar131_tree=null;
		CommonTree DAYS132_tree=null;
		CommonTree string_literal133_tree=null;
		CommonTree WhiteChar134_tree=null;
		CommonTree WhiteChar135_tree=null;
		CommonTree string_literal136_tree=null;
		CommonTree WhiteChar137_tree=null;
		CommonTree WhiteChar138_tree=null;
		CommonTree DAYS139_tree=null;
		CommonTree WhiteChar140_tree=null;
		CommonTree string_literal141_tree=null;
		CommonTree WhiteChar142_tree=null;
		CommonTree WhiteChar143_tree=null;
		CommonTree DAYS144_tree=null;
		CommonTree WhiteChar145_tree=null;
		CommonTree string_literal146_tree=null;
		CommonTree WhiteChar147_tree=null;
		CommonTree WhiteChar148_tree=null;
		CommonTree string_literal149_tree=null;
		CommonTree WhiteChar150_tree=null;
		CommonTree string_literal151_tree=null;
		CommonTree WhiteChar152_tree=null;
		CommonTree WhiteChar153_tree=null;
		CommonTree string_literal154_tree=null;
		CommonTree WhiteChar155_tree=null;
		CommonTree WhiteChar156_tree=null;
		CommonTree DAYS157_tree=null;
		CommonTree WhiteChar158_tree=null;
		CommonTree string_literal159_tree=null;
		CommonTree WhiteChar160_tree=null;
		CommonTree WhiteChar161_tree=null;
		CommonTree DAYS162_tree=null;
		CommonTree WhiteChar163_tree=null;
		CommonTree string_literal164_tree=null;
		CommonTree WhiteChar165_tree=null;
		RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
		RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
		RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_103=new RewriteRuleTokenStream(adaptor,"token 103");
		RewriteRuleTokenStream stream_104=new RewriteRuleTokenStream(adaptor,"token 104");
		RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
		RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
		RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
		RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
		RewriteRuleTokenStream stream_98=new RewriteRuleTokenStream(adaptor,"token 98");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_stringconstant=new RewriteRuleSubtreeStream(adaptor,"rule stringconstant");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:37: ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) )
			int alt32=7;
			switch ( input.LA(1) ) {
			case 78:
				{
				alt32=1;
				}
				break;
			case 82:
				{
				alt32=2;
				}
				break;
			case 71:
				{
				alt32=3;
				}
				break;
			case 64:
				{
				alt32=4;
				}
				break;
			case 66:
				{
				alt32=5;
				}
				break;
			case 103:
				{
				alt32=6;
				}
				break;
			case 104:
				{
				alt32=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 32, 0, input);
				throw nvae;
			}
			switch (alt32) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:4: 'is above historical' WhiteChar secondOp= operand
					{
					string_literal86=(Token)match(input,78,FOLLOW_78_in_opcmpcondition1097);  
					stream_78.add(string_literal86);

					WhiteChar87=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1099);  
					stream_WhiteChar.add(WhiteChar87);

					pushFollow(FOLLOW_operand_in_opcmpcondition1103);
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
					// 222:53: -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:56: ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupDoubleMapCondition, "SupDoubleMapCondition"), root_1);
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:6: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar88=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1129);  
							stream_WhiteChar.add(WhiteChar88);

							string_literal89=(Token)match(input,74,FOLLOW_74_in_opcmpcondition1131);  
							stream_74.add(string_literal89);

							WhiteChar90=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1133);  
							stream_WhiteChar.add(WhiteChar90);

							pushFollow(FOLLOW_constant_in_opcmpcondition1137);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar91=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1139);  
							stream_WhiteChar.add(WhiteChar91);

							DAYS92=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1141);  
							stream_DAYS.add(DAYS92);

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
							// 223:66: -> ^( SupDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:69: ^( SupDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:4: 'is below historical' WhiteChar secondOp= operand
					{
					string_literal93=(Token)match(input,82,FOLLOW_82_in_opcmpcondition1163);  
					stream_82.add(string_literal93);

					WhiteChar94=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1165);  
					stream_WhiteChar.add(WhiteChar94);

					pushFollow(FOLLOW_operand_in_opcmpcondition1169);
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
					// 224:53: -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:56: ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfDoubleMapCondition, "InfDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:80: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:5: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar95=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1197);  
							stream_WhiteChar.add(WhiteChar95);

							string_literal96=(Token)match(input,74,FOLLOW_74_in_opcmpcondition1199);  
							stream_74.add(string_literal96);

							WhiteChar97=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1201);  
							stream_WhiteChar.add(WhiteChar97);

							pushFollow(FOLLOW_constant_in_opcmpcondition1205);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar98=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1207);  
							stream_WhiteChar.add(WhiteChar98);

							DAYS99=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1209);  
							stream_DAYS.add(DAYS99);

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
							// 225:67: -> ^( InfDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:70: ^( InfDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:4: 'equals historical' WhiteChar secondOp= operand
					{
					string_literal100=(Token)match(input,71,FOLLOW_71_in_opcmpcondition1231);  
					stream_71.add(string_literal100);

					WhiteChar101=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1233);  
					stream_WhiteChar.add(WhiteChar101);

					pushFollow(FOLLOW_operand_in_opcmpcondition1237);
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
					// 226:51: -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:54: ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualDoubleMapCondition, "EqualDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:80: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:5: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )?
					int alt29=2;
					int LA29_0 = input.LA(1);
					if ( (LA29_0==WhiteChar) ) {
						int LA29_1 = input.LA(2);
						if ( (LA29_1==74) ) {
							alt29=1;
						}
					}
					switch (alt29) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar102=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1264);  
							stream_WhiteChar.add(WhiteChar102);

							string_literal103=(Token)match(input,74,FOLLOW_74_in_opcmpcondition1266);  
							stream_74.add(string_literal103);

							WhiteChar104=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1268);  
							stream_WhiteChar.add(WhiteChar104);

							pushFollow(FOLLOW_constant_in_opcmpcondition1272);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar105=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1274);  
							stream_WhiteChar.add(WhiteChar105);

							DAYS106=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1276);  
							stream_DAYS.add(DAYS106);

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
							// 227:67: -> ^( EqualDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:70: ^( EqualDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:3: ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:3: ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:4: 'crosses down historical' WhiteChar operand
					{
					string_literal107=(Token)match(input,64,FOLLOW_64_in_opcmpcondition1299);  
					stream_64.add(string_literal107);

					WhiteChar108=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1301);  
					stream_WhiteChar.add(WhiteChar108);

					pushFollow(FOLLOW_operand_in_opcmpcondition1303);
					operand109=operand();
					state._fsp--;

					stream_operand.add(operand109.getTree());
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
					// 229:48: -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:51: ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:81: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:110: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:9: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )?
					int alt30=2;
					int LA30_0 = input.LA(1);
					if ( (LA30_0==WhiteChar) ) {
						int LA30_1 = input.LA(2);
						if ( (LA30_1==98) ) {
							alt30=1;
						}
					}
					switch (alt30) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:11: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar110=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1340);  
							stream_WhiteChar.add(WhiteChar110);

							string_literal111=(Token)match(input,98,FOLLOW_98_in_opcmpcondition1342);  
							stream_98.add(string_literal111);

							WhiteChar112=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1344);  
							stream_WhiteChar.add(WhiteChar112);

							pushFollow(FOLLOW_constant_in_opcmpcondition1348);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar113=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1350);  
							stream_WhiteChar.add(WhiteChar113);

							DAYS114=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1352);  
							stream_DAYS.add(DAYS114);

							WhiteChar115=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1365);  
							stream_WhiteChar.add(WhiteChar115);

							string_literal116=(Token)match(input,92,FOLLOW_92_in_opcmpcondition1367);  
							stream_92.add(string_literal116);

							WhiteChar117=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1369);  
							stream_WhiteChar.add(WhiteChar117);

							pushFollow(FOLLOW_constant_in_opcmpcondition1373);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar118=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1375);  
							stream_WhiteChar.add(WhiteChar118);

							DAYS119=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1377);  
							stream_DAYS.add(DAYS119);

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
							// 232:11: -> ^( CrossDownDoubleMapCondition operand )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:14: ^( CrossDownDoubleMapCondition operand )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:3: ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:3: ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:4: 'crosses up historical' WhiteChar operand
					{
					string_literal120=(Token)match(input,66,FOLLOW_66_in_opcmpcondition1413);  
					stream_66.add(string_literal120);

					WhiteChar121=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1415);  
					stream_WhiteChar.add(WhiteChar121);

					pushFollow(FOLLOW_operand_in_opcmpcondition1417);
					operand122=operand();
					state._fsp--;

					stream_operand.add(operand122.getTree());
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
					// 234:46: -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:49: ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:77: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:106: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:8: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )?
					int alt31=2;
					int LA31_0 = input.LA(1);
					if ( (LA31_0==WhiteChar) ) {
						int LA31_1 = input.LA(2);
						if ( (LA31_1==98) ) {
							alt31=1;
						}
					}
					switch (alt31) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:10: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar123=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1453);  
							stream_WhiteChar.add(WhiteChar123);

							string_literal124=(Token)match(input,98,FOLLOW_98_in_opcmpcondition1455);  
							stream_98.add(string_literal124);

							WhiteChar125=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1457);  
							stream_WhiteChar.add(WhiteChar125);

							pushFollow(FOLLOW_constant_in_opcmpcondition1461);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar126=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1463);  
							stream_WhiteChar.add(WhiteChar126);

							DAYS127=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1465);  
							stream_DAYS.add(DAYS127);

							WhiteChar128=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1476);  
							stream_WhiteChar.add(WhiteChar128);

							string_literal129=(Token)match(input,92,FOLLOW_92_in_opcmpcondition1478);  
							stream_92.add(string_literal129);

							WhiteChar130=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1480);  
							stream_WhiteChar.add(WhiteChar130);

							pushFollow(FOLLOW_constant_in_opcmpcondition1484);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar131=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1486);  
							stream_WhiteChar.add(WhiteChar131);

							DAYS132=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1488);  
							stream_DAYS.add(DAYS132);

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
							// 237:10: -> ^( CrossUpDoubleMapCondition operand )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:13: ^( CrossUpDoubleMapCondition operand )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:4: 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal133=(Token)match(input,103,FOLLOW_103_in_opcmpcondition1523);  
					stream_103.add(string_literal133);

					WhiteChar134=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1525);  
					stream_WhiteChar.add(WhiteChar134);

					pushFollow(FOLLOW_operand_in_opcmpcondition1529);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar135=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1537);  
					stream_WhiteChar.add(WhiteChar135);

					string_literal136=(Token)match(input,92,FOLLOW_92_in_opcmpcondition1539);  
					stream_92.add(string_literal136);

					WhiteChar137=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1541);  
					stream_WhiteChar.add(WhiteChar137);

					pushFollow(FOLLOW_constant_in_opcmpcondition1545);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar138=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1547);  
					stream_WhiteChar.add(WhiteChar138);

					DAYS139=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1549);  
					stream_DAYS.add(DAYS139);

					WhiteChar140=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1557);  
					stream_WhiteChar.add(WhiteChar140);

					string_literal141=(Token)match(input,74,FOLLOW_74_in_opcmpcondition1559);  
					stream_74.add(string_literal141);

					WhiteChar142=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1561);  
					stream_WhiteChar.add(WhiteChar142);

					pushFollow(FOLLOW_constant_in_opcmpcondition1565);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar143=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1567);  
					stream_WhiteChar.add(WhiteChar143);

					DAYS144=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1569);  
					stream_DAYS.add(DAYS144);

					WhiteChar145=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1577);  
					stream_WhiteChar.add(WhiteChar145);

					string_literal146=(Token)match(input,68,FOLLOW_68_in_opcmpcondition1579);  
					stream_68.add(string_literal146);

					WhiteChar147=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1581);  
					stream_WhiteChar.add(WhiteChar147);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition1585);
					direction=stringconstant();
					state._fsp--;

					stream_stringconstant.add(direction.getTree());
					WhiteChar148=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1593);  
					stream_WhiteChar.add(WhiteChar148);

					string_literal149=(Token)match(input,70,FOLLOW_70_in_opcmpcondition1595);  
					stream_70.add(string_literal149);

					WhiteChar150=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1597);  
					stream_WhiteChar.add(WhiteChar150);

					pushFollow(FOLLOW_constant_in_opcmpcondition1601);
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
					// 244:7: -> ^( LinearSimilarTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:10: ^( LinearSimilarTrendsCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:4: 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant
					{
					string_literal151=(Token)match(input,104,FOLLOW_104_in_opcmpcondition1634);  
					stream_104.add(string_literal151);

					WhiteChar152=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1636);  
					stream_WhiteChar.add(WhiteChar152);

					pushFollow(FOLLOW_operand_in_opcmpcondition1640);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar153=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1648);  
					stream_WhiteChar.add(WhiteChar153);

					string_literal154=(Token)match(input,92,FOLLOW_92_in_opcmpcondition1650);  
					stream_92.add(string_literal154);

					WhiteChar155=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1652);  
					stream_WhiteChar.add(WhiteChar155);

					pushFollow(FOLLOW_constant_in_opcmpcondition1656);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar156=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1658);  
					stream_WhiteChar.add(WhiteChar156);

					DAYS157=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1660);  
					stream_DAYS.add(DAYS157);

					WhiteChar158=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1668);  
					stream_WhiteChar.add(WhiteChar158);

					string_literal159=(Token)match(input,74,FOLLOW_74_in_opcmpcondition1670);  
					stream_74.add(string_literal159);

					WhiteChar160=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1672);  
					stream_WhiteChar.add(WhiteChar160);

					pushFollow(FOLLOW_constant_in_opcmpcondition1676);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar161=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1678);  
					stream_WhiteChar.add(WhiteChar161);

					DAYS162=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1680);  
					stream_DAYS.add(DAYS162);

					WhiteChar163=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1688);  
					stream_WhiteChar.add(WhiteChar163);

					string_literal164=(Token)match(input,68,FOLLOW_68_in_opcmpcondition1690);  
					stream_68.add(string_literal164);

					WhiteChar165=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1692);  
					stream_WhiteChar.add(WhiteChar165);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition1696);
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
					// 250:7: -> ^( LinearOppositeTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:10: ^( LinearOppositeTrendsCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:1: constantcmp[CommonTree firstOp] : ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )? );
	public final ParameterizedIndicatorsParser.constantcmp_return constantcmp(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.constantcmp_return retval = new ParameterizedIndicatorsParser.constantcmp_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal166=null;
		Token WhiteChar167=null;
		Token WhiteChar168=null;
		Token string_literal169=null;
		Token WhiteChar170=null;
		Token WhiteChar171=null;
		Token DAYS172=null;
		Token WhiteChar173=null;
		Token string_literal174=null;
		Token WhiteChar175=null;
		Token WhiteChar176=null;
		Token DAYS177=null;
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
		Token string_literal190=null;
		Token WhiteChar191=null;
		Token WhiteChar192=null;
		Token string_literal193=null;
		Token WhiteChar194=null;
		Token WhiteChar195=null;
		Token DAYS196=null;
		Token WhiteChar197=null;
		Token string_literal198=null;
		Token WhiteChar199=null;
		Token WhiteChar200=null;
		Token DAYS201=null;
		Token string_literal202=null;
		Token WhiteChar203=null;
		Token WhiteChar204=null;
		Token string_literal205=null;
		Token WhiteChar206=null;
		Token WhiteChar207=null;
		Token DAYS208=null;
		Token WhiteChar209=null;
		Token string_literal210=null;
		Token WhiteChar211=null;
		Token WhiteChar212=null;
		Token DAYS213=null;
		ParserRuleReturnScope trendSignal =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope threshold =null;

		CommonTree string_literal166_tree=null;
		CommonTree WhiteChar167_tree=null;
		CommonTree WhiteChar168_tree=null;
		CommonTree string_literal169_tree=null;
		CommonTree WhiteChar170_tree=null;
		CommonTree WhiteChar171_tree=null;
		CommonTree DAYS172_tree=null;
		CommonTree WhiteChar173_tree=null;
		CommonTree string_literal174_tree=null;
		CommonTree WhiteChar175_tree=null;
		CommonTree WhiteChar176_tree=null;
		CommonTree DAYS177_tree=null;
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
		CommonTree string_literal190_tree=null;
		CommonTree WhiteChar191_tree=null;
		CommonTree WhiteChar192_tree=null;
		CommonTree string_literal193_tree=null;
		CommonTree WhiteChar194_tree=null;
		CommonTree WhiteChar195_tree=null;
		CommonTree DAYS196_tree=null;
		CommonTree WhiteChar197_tree=null;
		CommonTree string_literal198_tree=null;
		CommonTree WhiteChar199_tree=null;
		CommonTree WhiteChar200_tree=null;
		CommonTree DAYS201_tree=null;
		CommonTree string_literal202_tree=null;
		CommonTree WhiteChar203_tree=null;
		CommonTree WhiteChar204_tree=null;
		CommonTree string_literal205_tree=null;
		CommonTree WhiteChar206_tree=null;
		CommonTree WhiteChar207_tree=null;
		CommonTree DAYS208_tree=null;
		CommonTree WhiteChar209_tree=null;
		CommonTree string_literal210_tree=null;
		CommonTree WhiteChar211_tree=null;
		CommonTree WhiteChar212_tree=null;
		CommonTree DAYS213_tree=null;
		RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
		RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
		RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
		RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
		RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_trendconstant=new RewriteRuleSubtreeStream(adaptor,"rule trendconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:34: ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )? )
			int alt37=4;
			switch ( input.LA(1) ) {
			case 73:
				{
				alt37=1;
				}
				break;
			case 72:
				{
				alt37=2;
				}
				break;
			case 79:
				{
				alt37=3;
				}
				break;
			case 83:
				{
				alt37=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 37, 0, input);
				throw nvae;
			}
			switch (alt37) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:4: 'equals trend' WhiteChar trendSignal= trendconstant
					{
					string_literal166=(Token)match(input,73,FOLLOW_73_in_constantcmp1734);  
					stream_73.add(string_literal166);

					WhiteChar167=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1736);  
					stream_WhiteChar.add(WhiteChar167);

					pushFollow(FOLLOW_trendconstant_in_constantcmp1740);
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
					// 255:55: -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:58: ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualStringConstantCondition, "EqualStringConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_trendconstant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:103: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:130: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					int alt33=2;
					int LA33_0 = input.LA(1);
					if ( (LA33_0==WhiteChar) ) {
						int LA33_1 = input.LA(2);
						if ( (LA33_1==92) ) {
							alt33=1;
						}
					}
					switch (alt33) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar168=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1774);  
							stream_WhiteChar.add(WhiteChar168);

							string_literal169=(Token)match(input,92,FOLLOW_92_in_constantcmp1776);  
							stream_92.add(string_literal169);

							WhiteChar170=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1778);  
							stream_WhiteChar.add(WhiteChar170);

							pushFollow(FOLLOW_constant_in_constantcmp1782);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar171=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1784);  
							stream_WhiteChar.add(WhiteChar171);

							DAYS172=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1786);  
							stream_DAYS.add(DAYS172);

							WhiteChar173=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1788);  
							stream_WhiteChar.add(WhiteChar173);

							string_literal174=(Token)match(input,74,FOLLOW_74_in_constantcmp1790);  
							stream_74.add(string_literal174);

							WhiteChar175=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1792);  
							stream_WhiteChar.add(WhiteChar175);

							pushFollow(FOLLOW_constant_in_constantcmp1796);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar176=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1798);  
							stream_WhiteChar.add(WhiteChar176);

							DAYS177=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1800);  
							stream_DAYS.add(DAYS177);

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
							// 256:129: -> ^( EqualStringConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:132: ^( EqualStringConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:4: 'equals threshold' WhiteChar threshold= constant
					{
					string_literal178=(Token)match(input,72,FOLLOW_72_in_constantcmp1828);  
					stream_72.add(string_literal178);

					WhiteChar179=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1830);  
					stream_WhiteChar.add(WhiteChar179);

					pushFollow(FOLLOW_constant_in_constantcmp1834);
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
					// 258:52: -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:55: ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualConstantCondition, "EqualConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:116: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:259:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )?
					int alt34=2;
					int LA34_0 = input.LA(1);
					if ( (LA34_0==WhiteChar) ) {
						int LA34_1 = input.LA(2);
						if ( (LA34_1==92) ) {
							alt34=1;
						}
					}
					switch (alt34) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:259:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar180=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1868);  
							stream_WhiteChar.add(WhiteChar180);

							string_literal181=(Token)match(input,92,FOLLOW_92_in_constantcmp1870);  
							stream_92.add(string_literal181);

							WhiteChar182=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1872);  
							stream_WhiteChar.add(WhiteChar182);

							pushFollow(FOLLOW_constant_in_constantcmp1876);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar183=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1878);  
							stream_WhiteChar.add(WhiteChar183);

							DAYS184=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1880);  
							stream_DAYS.add(DAYS184);

							WhiteChar185=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1882);  
							stream_WhiteChar.add(WhiteChar185);

							string_literal186=(Token)match(input,74,FOLLOW_74_in_constantcmp1884);  
							stream_74.add(string_literal186);

							WhiteChar187=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1886);  
							stream_WhiteChar.add(WhiteChar187);

							pushFollow(FOLLOW_constant_in_constantcmp1890);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar188=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1892);  
							stream_WhiteChar.add(WhiteChar188);

							DAYS189=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1894);  
							stream_DAYS.add(DAYS189);

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
							// 259:129: -> ^( EqualConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:259:132: ^( EqualConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:4: 'is above threshold' WhiteChar threshold= constant
					{
					string_literal190=(Token)match(input,79,FOLLOW_79_in_constantcmp1923);  
					stream_79.add(string_literal190);

					WhiteChar191=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1925);  
					stream_WhiteChar.add(WhiteChar191);

					pushFollow(FOLLOW_constant_in_constantcmp1929);
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
					// 261:54: -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:57: ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupConstantCondition, "SupConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:116: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )?
					int alt35=2;
					int LA35_0 = input.LA(1);
					if ( (LA35_0==WhiteChar) ) {
						int LA35_1 = input.LA(2);
						if ( (LA35_1==92) ) {
							alt35=1;
						}
					}
					switch (alt35) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar192=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1963);  
							stream_WhiteChar.add(WhiteChar192);

							string_literal193=(Token)match(input,92,FOLLOW_92_in_constantcmp1965);  
							stream_92.add(string_literal193);

							WhiteChar194=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1967);  
							stream_WhiteChar.add(WhiteChar194);

							pushFollow(FOLLOW_constant_in_constantcmp1971);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar195=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1973);  
							stream_WhiteChar.add(WhiteChar195);

							DAYS196=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1975);  
							stream_DAYS.add(DAYS196);

							WhiteChar197=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1977);  
							stream_WhiteChar.add(WhiteChar197);

							string_literal198=(Token)match(input,74,FOLLOW_74_in_constantcmp1979);  
							stream_74.add(string_literal198);

							WhiteChar199=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1981);  
							stream_WhiteChar.add(WhiteChar199);

							pushFollow(FOLLOW_constant_in_constantcmp1985);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar200=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1987);  
							stream_WhiteChar.add(WhiteChar200);

							DAYS201=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1989);  
							stream_DAYS.add(DAYS201);

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
							// 262:129: -> ^( SupConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:132: ^( SupConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:4: 'is below threshold' WhiteChar threshold= constant
					{
					string_literal202=(Token)match(input,83,FOLLOW_83_in_constantcmp2018);  
					stream_83.add(string_literal202);

					WhiteChar203=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2020);  
					stream_WhiteChar.add(WhiteChar203);

					pushFollow(FOLLOW_constant_in_constantcmp2024);
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
					// 264:54: -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:57: ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfConstantCondition, "InfConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:116: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )?
					int alt36=2;
					int LA36_0 = input.LA(1);
					if ( (LA36_0==WhiteChar) ) {
						int LA36_1 = input.LA(2);
						if ( (LA36_1==92) ) {
							alt36=1;
						}
					}
					switch (alt36) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar204=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2058);  
							stream_WhiteChar.add(WhiteChar204);

							string_literal205=(Token)match(input,92,FOLLOW_92_in_constantcmp2060);  
							stream_92.add(string_literal205);

							WhiteChar206=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2062);  
							stream_WhiteChar.add(WhiteChar206);

							pushFollow(FOLLOW_constant_in_constantcmp2066);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar207=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2068);  
							stream_WhiteChar.add(WhiteChar207);

							DAYS208=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2070);  
							stream_DAYS.add(DAYS208);

							WhiteChar209=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2072);  
							stream_WhiteChar.add(WhiteChar209);

							string_literal210=(Token)match(input,74,FOLLOW_74_in_constantcmp2074);  
							stream_74.add(string_literal210);

							WhiteChar211=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2076);  
							stream_WhiteChar.add(WhiteChar211);

							pushFollow(FOLLOW_constant_in_constantcmp2080);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar212=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2082);  
							stream_WhiteChar.add(WhiteChar212);

							DAYS213=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2084);  
							stream_DAYS.add(DAYS213);

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
							// 265:129: -> ^( InfConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:132: ^( InfConstantCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:268:1: presetcondition[CommonTree firstOp] : ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) ) | ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) ) | ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) ) );
	public final ParameterizedIndicatorsParser.presetcondition_return presetcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.presetcondition_return retval = new ParameterizedIndicatorsParser.presetcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal214=null;
		Token WhiteChar215=null;
		Token string_literal216=null;
		Token WhiteChar217=null;
		Token PERCENT218=null;
		Token WhiteChar219=null;
		Token string_literal220=null;
		Token WhiteChar221=null;
		Token WhiteChar222=null;
		Token DAYS223=null;
		Token string_literal224=null;
		Token WhiteChar225=null;
		Token string_literal226=null;
		Token WhiteChar227=null;
		Token PERCENT228=null;
		Token WhiteChar229=null;
		Token string_literal230=null;
		Token WhiteChar231=null;
		Token WhiteChar232=null;
		Token DAYS233=null;
		Token string_literal234=null;
		Token WhiteChar235=null;
		Token PERCENT236=null;
		Token WhiteChar237=null;
		Token string_literal238=null;
		Token WhiteChar239=null;
		Token WhiteChar240=null;
		Token DAYS241=null;
		Token WhiteChar242=null;
		Token string_literal243=null;
		Token WhiteChar244=null;
		Token WhiteChar245=null;
		Token DAYS246=null;
		Token string_literal247=null;
		Token WhiteChar248=null;
		Token PERCENT249=null;
		Token WhiteChar250=null;
		Token string_literal251=null;
		Token WhiteChar252=null;
		Token WhiteChar253=null;
		Token DAYS254=null;
		Token WhiteChar255=null;
		Token string_literal256=null;
		Token WhiteChar257=null;
		Token WhiteChar258=null;
		Token DAYS259=null;
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
		Token string_literal272=null;
		Token WhiteChar273=null;
		Token WhiteChar274=null;
		Token string_literal275=null;
		Token WhiteChar276=null;
		Token WhiteChar277=null;
		Token DAYS278=null;
		Token WhiteChar279=null;
		Token string_literal280=null;
		Token WhiteChar281=null;
		Token WhiteChar282=null;
		Token DAYS283=null;
		Token string_literal284=null;
		Token WhiteChar285=null;
		Token WhiteChar286=null;
		Token DAYS287=null;
		Token WhiteChar288=null;
		Token string_literal289=null;
		Token WhiteChar290=null;
		Token WhiteChar291=null;
		Token DAYS292=null;
		Token WhiteChar293=null;
		Token string_literal294=null;
		Token WhiteChar295=null;
		Token WhiteChar296=null;
		Token DAYS297=null;
		Token WhiteChar298=null;
		Token string_literal299=null;
		Token WhiteChar300=null;
		Token WhiteChar301=null;
		Token DAYS302=null;
		Token WhiteChar303=null;
		Token string_literal304=null;
		Token WhiteChar305=null;
		Token WhiteChar306=null;
		Token string_literal307=null;
		Token WhiteChar308=null;
		Token WhiteChar309=null;
		Token string_literal310=null;
		Token WhiteChar311=null;
		Token char_literal312=null;
		Token char_literal313=null;
		Token char_literal314=null;
		Token WhiteChar315=null;
		Token string_literal316=null;
		Token WhiteChar317=null;
		Token char_literal318=null;
		Token char_literal319=null;
		Token char_literal320=null;
		Token WhiteChar321=null;
		Token string_literal322=null;
		Token WhiteChar323=null;
		Token char_literal324=null;
		Token char_literal325=null;
		Token char_literal326=null;
		Token string_literal327=null;
		Token WhiteChar328=null;
		Token WhiteChar329=null;
		Token DAYS330=null;
		Token WhiteChar331=null;
		Token string_literal332=null;
		Token WhiteChar333=null;
		Token WhiteChar334=null;
		Token DAYS335=null;
		Token WhiteChar336=null;
		Token string_literal337=null;
		Token WhiteChar338=null;
		Token WhiteChar339=null;
		Token DAYS340=null;
		Token WhiteChar341=null;
		Token string_literal342=null;
		Token WhiteChar343=null;
		Token WhiteChar344=null;
		Token DAYS345=null;
		Token WhiteChar346=null;
		Token string_literal347=null;
		Token WhiteChar348=null;
		Token WhiteChar349=null;
		Token string_literal350=null;
		Token WhiteChar351=null;
		Token WhiteChar352=null;
		Token string_literal353=null;
		Token WhiteChar354=null;
		Token char_literal355=null;
		Token char_literal356=null;
		Token char_literal357=null;
		Token WhiteChar358=null;
		Token string_literal359=null;
		Token WhiteChar360=null;
		Token char_literal361=null;
		Token char_literal362=null;
		Token char_literal363=null;
		Token WhiteChar364=null;
		Token string_literal365=null;
		Token WhiteChar366=null;
		Token char_literal367=null;
		Token char_literal368=null;
		Token char_literal369=null;
		Token string_literal370=null;
		Token WhiteChar371=null;
		Token WhiteChar372=null;
		Token DAYS373=null;
		Token WhiteChar374=null;
		Token string_literal375=null;
		Token WhiteChar376=null;
		Token WhiteChar377=null;
		Token DAYS378=null;
		Token WhiteChar379=null;
		Token string_literal380=null;
		Token WhiteChar381=null;
		Token WhiteChar382=null;
		Token DAYS383=null;
		Token WhiteChar384=null;
		Token string_literal385=null;
		Token WhiteChar386=null;
		Token WhiteChar387=null;
		Token DAYS388=null;
		Token WhiteChar389=null;
		Token string_literal390=null;
		Token WhiteChar391=null;
		Token WhiteChar392=null;
		Token string_literal393=null;
		Token WhiteChar394=null;
		Token WhiteChar395=null;
		Token string_literal396=null;
		Token WhiteChar397=null;
		Token char_literal398=null;
		Token char_literal399=null;
		Token char_literal400=null;
		Token WhiteChar401=null;
		Token string_literal402=null;
		Token WhiteChar403=null;
		Token char_literal404=null;
		Token char_literal405=null;
		Token char_literal406=null;
		Token WhiteChar407=null;
		Token string_literal408=null;
		Token WhiteChar409=null;
		Token char_literal410=null;
		Token char_literal411=null;
		Token char_literal412=null;
		Token string_literal413=null;
		Token WhiteChar414=null;
		Token WhiteChar415=null;
		Token DAYS416=null;
		Token WhiteChar417=null;
		Token string_literal418=null;
		Token WhiteChar419=null;
		Token WhiteChar420=null;
		Token DAYS421=null;
		Token WhiteChar422=null;
		Token string_literal423=null;
		Token WhiteChar424=null;
		Token WhiteChar425=null;
		Token DAYS426=null;
		Token WhiteChar427=null;
		Token string_literal428=null;
		Token WhiteChar429=null;
		Token WhiteChar430=null;
		Token DAYS431=null;
		Token WhiteChar432=null;
		Token string_literal433=null;
		Token WhiteChar434=null;
		Token WhiteChar435=null;
		Token string_literal436=null;
		Token WhiteChar437=null;
		Token WhiteChar438=null;
		Token string_literal439=null;
		Token WhiteChar440=null;
		Token char_literal441=null;
		Token char_literal442=null;
		Token char_literal443=null;
		Token WhiteChar444=null;
		Token string_literal445=null;
		Token WhiteChar446=null;
		Token char_literal447=null;
		Token char_literal448=null;
		Token char_literal449=null;
		Token WhiteChar450=null;
		Token string_literal451=null;
		Token WhiteChar452=null;
		Token char_literal453=null;
		Token char_literal454=null;
		Token char_literal455=null;
		Token string_literal456=null;
		Token WhiteChar457=null;
		Token WhiteChar458=null;
		Token DAYS459=null;
		Token WhiteChar460=null;
		Token string_literal461=null;
		Token WhiteChar462=null;
		Token WhiteChar463=null;
		Token DAYS464=null;
		Token WhiteChar465=null;
		Token string_literal466=null;
		Token WhiteChar467=null;
		Token WhiteChar468=null;
		Token DAYS469=null;
		Token WhiteChar470=null;
		Token string_literal471=null;
		Token WhiteChar472=null;
		Token WhiteChar473=null;
		Token DAYS474=null;
		Token WhiteChar475=null;
		Token string_literal476=null;
		Token WhiteChar477=null;
		Token char_literal478=null;
		Token char_literal479=null;
		Token char_literal480=null;
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
		Token WhiteChar496=null;
		Token DAYS497=null;
		Token WhiteChar498=null;
		Token string_literal499=null;
		Token WhiteChar500=null;
		Token WhiteChar501=null;
		Token DAYS502=null;
		Token WhiteChar503=null;
		Token string_literal504=null;
		Token WhiteChar505=null;
		Token char_literal506=null;
		Token char_literal507=null;
		Token char_literal508=null;
		Token WhiteChar509=null;
		Token string_literal510=null;
		Token WhiteChar511=null;
		Token string_literal512=null;
		Token WhiteChar513=null;
		Token string_literal514=null;
		Token WhiteChar515=null;
		Token WhiteChar516=null;
		Token DAYS517=null;
		Token WhiteChar518=null;
		Token string_literal519=null;
		Token WhiteChar520=null;
		Token WhiteChar521=null;
		Token DAYS522=null;
		Token WhiteChar523=null;
		Token string_literal524=null;
		Token WhiteChar525=null;
		Token string_literal526=null;
		Token WhiteChar527=null;
		Token string_literal528=null;
		Token WhiteChar529=null;
		Token WhiteChar530=null;
		Token DAYS531=null;
		Token WhiteChar532=null;
		Token string_literal533=null;
		Token WhiteChar534=null;
		Token WhiteChar535=null;
		Token DAYS536=null;
		Token WhiteChar537=null;
		Token string_literal538=null;
		Token WhiteChar539=null;
		Token string_literal540=null;
		Token WhiteChar541=null;
		Token string_literal542=null;
		Token WhiteChar543=null;
		Token WhiteChar544=null;
		Token DAYS545=null;
		Token WhiteChar546=null;
		Token string_literal547=null;
		Token WhiteChar548=null;
		Token WhiteChar549=null;
		Token DAYS550=null;
		Token WhiteChar551=null;
		Token string_literal552=null;
		Token WhiteChar553=null;
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
		ParserRuleReturnScope greed =null;
		ParserRuleReturnScope type =null;
		ParserRuleReturnScope lowestStart =null;
		ParserRuleReturnScope highestStart =null;
		ParserRuleReturnScope lowestEnd =null;
		ParserRuleReturnScope highestEnd =null;
		ParserRuleReturnScope minSlope =null;
		ParserRuleReturnScope maxSlope =null;
		ParserRuleReturnScope tolerance =null;
		ParserRuleReturnScope epsilon =null;

		CommonTree string_literal214_tree=null;
		CommonTree WhiteChar215_tree=null;
		CommonTree string_literal216_tree=null;
		CommonTree WhiteChar217_tree=null;
		CommonTree PERCENT218_tree=null;
		CommonTree WhiteChar219_tree=null;
		CommonTree string_literal220_tree=null;
		CommonTree WhiteChar221_tree=null;
		CommonTree WhiteChar222_tree=null;
		CommonTree DAYS223_tree=null;
		CommonTree string_literal224_tree=null;
		CommonTree WhiteChar225_tree=null;
		CommonTree string_literal226_tree=null;
		CommonTree WhiteChar227_tree=null;
		CommonTree PERCENT228_tree=null;
		CommonTree WhiteChar229_tree=null;
		CommonTree string_literal230_tree=null;
		CommonTree WhiteChar231_tree=null;
		CommonTree WhiteChar232_tree=null;
		CommonTree DAYS233_tree=null;
		CommonTree string_literal234_tree=null;
		CommonTree WhiteChar235_tree=null;
		CommonTree PERCENT236_tree=null;
		CommonTree WhiteChar237_tree=null;
		CommonTree string_literal238_tree=null;
		CommonTree WhiteChar239_tree=null;
		CommonTree WhiteChar240_tree=null;
		CommonTree DAYS241_tree=null;
		CommonTree WhiteChar242_tree=null;
		CommonTree string_literal243_tree=null;
		CommonTree WhiteChar244_tree=null;
		CommonTree WhiteChar245_tree=null;
		CommonTree DAYS246_tree=null;
		CommonTree string_literal247_tree=null;
		CommonTree WhiteChar248_tree=null;
		CommonTree PERCENT249_tree=null;
		CommonTree WhiteChar250_tree=null;
		CommonTree string_literal251_tree=null;
		CommonTree WhiteChar252_tree=null;
		CommonTree WhiteChar253_tree=null;
		CommonTree DAYS254_tree=null;
		CommonTree WhiteChar255_tree=null;
		CommonTree string_literal256_tree=null;
		CommonTree WhiteChar257_tree=null;
		CommonTree WhiteChar258_tree=null;
		CommonTree DAYS259_tree=null;
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
		CommonTree string_literal272_tree=null;
		CommonTree WhiteChar273_tree=null;
		CommonTree WhiteChar274_tree=null;
		CommonTree string_literal275_tree=null;
		CommonTree WhiteChar276_tree=null;
		CommonTree WhiteChar277_tree=null;
		CommonTree DAYS278_tree=null;
		CommonTree WhiteChar279_tree=null;
		CommonTree string_literal280_tree=null;
		CommonTree WhiteChar281_tree=null;
		CommonTree WhiteChar282_tree=null;
		CommonTree DAYS283_tree=null;
		CommonTree string_literal284_tree=null;
		CommonTree WhiteChar285_tree=null;
		CommonTree WhiteChar286_tree=null;
		CommonTree DAYS287_tree=null;
		CommonTree WhiteChar288_tree=null;
		CommonTree string_literal289_tree=null;
		CommonTree WhiteChar290_tree=null;
		CommonTree WhiteChar291_tree=null;
		CommonTree DAYS292_tree=null;
		CommonTree WhiteChar293_tree=null;
		CommonTree string_literal294_tree=null;
		CommonTree WhiteChar295_tree=null;
		CommonTree WhiteChar296_tree=null;
		CommonTree DAYS297_tree=null;
		CommonTree WhiteChar298_tree=null;
		CommonTree string_literal299_tree=null;
		CommonTree WhiteChar300_tree=null;
		CommonTree WhiteChar301_tree=null;
		CommonTree DAYS302_tree=null;
		CommonTree WhiteChar303_tree=null;
		CommonTree string_literal304_tree=null;
		CommonTree WhiteChar305_tree=null;
		CommonTree WhiteChar306_tree=null;
		CommonTree string_literal307_tree=null;
		CommonTree WhiteChar308_tree=null;
		CommonTree WhiteChar309_tree=null;
		CommonTree string_literal310_tree=null;
		CommonTree WhiteChar311_tree=null;
		CommonTree char_literal312_tree=null;
		CommonTree char_literal313_tree=null;
		CommonTree char_literal314_tree=null;
		CommonTree WhiteChar315_tree=null;
		CommonTree string_literal316_tree=null;
		CommonTree WhiteChar317_tree=null;
		CommonTree char_literal318_tree=null;
		CommonTree char_literal319_tree=null;
		CommonTree char_literal320_tree=null;
		CommonTree WhiteChar321_tree=null;
		CommonTree string_literal322_tree=null;
		CommonTree WhiteChar323_tree=null;
		CommonTree char_literal324_tree=null;
		CommonTree char_literal325_tree=null;
		CommonTree char_literal326_tree=null;
		CommonTree string_literal327_tree=null;
		CommonTree WhiteChar328_tree=null;
		CommonTree WhiteChar329_tree=null;
		CommonTree DAYS330_tree=null;
		CommonTree WhiteChar331_tree=null;
		CommonTree string_literal332_tree=null;
		CommonTree WhiteChar333_tree=null;
		CommonTree WhiteChar334_tree=null;
		CommonTree DAYS335_tree=null;
		CommonTree WhiteChar336_tree=null;
		CommonTree string_literal337_tree=null;
		CommonTree WhiteChar338_tree=null;
		CommonTree WhiteChar339_tree=null;
		CommonTree DAYS340_tree=null;
		CommonTree WhiteChar341_tree=null;
		CommonTree string_literal342_tree=null;
		CommonTree WhiteChar343_tree=null;
		CommonTree WhiteChar344_tree=null;
		CommonTree DAYS345_tree=null;
		CommonTree WhiteChar346_tree=null;
		CommonTree string_literal347_tree=null;
		CommonTree WhiteChar348_tree=null;
		CommonTree WhiteChar349_tree=null;
		CommonTree string_literal350_tree=null;
		CommonTree WhiteChar351_tree=null;
		CommonTree WhiteChar352_tree=null;
		CommonTree string_literal353_tree=null;
		CommonTree WhiteChar354_tree=null;
		CommonTree char_literal355_tree=null;
		CommonTree char_literal356_tree=null;
		CommonTree char_literal357_tree=null;
		CommonTree WhiteChar358_tree=null;
		CommonTree string_literal359_tree=null;
		CommonTree WhiteChar360_tree=null;
		CommonTree char_literal361_tree=null;
		CommonTree char_literal362_tree=null;
		CommonTree char_literal363_tree=null;
		CommonTree WhiteChar364_tree=null;
		CommonTree string_literal365_tree=null;
		CommonTree WhiteChar366_tree=null;
		CommonTree char_literal367_tree=null;
		CommonTree char_literal368_tree=null;
		CommonTree char_literal369_tree=null;
		CommonTree string_literal370_tree=null;
		CommonTree WhiteChar371_tree=null;
		CommonTree WhiteChar372_tree=null;
		CommonTree DAYS373_tree=null;
		CommonTree WhiteChar374_tree=null;
		CommonTree string_literal375_tree=null;
		CommonTree WhiteChar376_tree=null;
		CommonTree WhiteChar377_tree=null;
		CommonTree DAYS378_tree=null;
		CommonTree WhiteChar379_tree=null;
		CommonTree string_literal380_tree=null;
		CommonTree WhiteChar381_tree=null;
		CommonTree WhiteChar382_tree=null;
		CommonTree DAYS383_tree=null;
		CommonTree WhiteChar384_tree=null;
		CommonTree string_literal385_tree=null;
		CommonTree WhiteChar386_tree=null;
		CommonTree WhiteChar387_tree=null;
		CommonTree DAYS388_tree=null;
		CommonTree WhiteChar389_tree=null;
		CommonTree string_literal390_tree=null;
		CommonTree WhiteChar391_tree=null;
		CommonTree WhiteChar392_tree=null;
		CommonTree string_literal393_tree=null;
		CommonTree WhiteChar394_tree=null;
		CommonTree WhiteChar395_tree=null;
		CommonTree string_literal396_tree=null;
		CommonTree WhiteChar397_tree=null;
		CommonTree char_literal398_tree=null;
		CommonTree char_literal399_tree=null;
		CommonTree char_literal400_tree=null;
		CommonTree WhiteChar401_tree=null;
		CommonTree string_literal402_tree=null;
		CommonTree WhiteChar403_tree=null;
		CommonTree char_literal404_tree=null;
		CommonTree char_literal405_tree=null;
		CommonTree char_literal406_tree=null;
		CommonTree WhiteChar407_tree=null;
		CommonTree string_literal408_tree=null;
		CommonTree WhiteChar409_tree=null;
		CommonTree char_literal410_tree=null;
		CommonTree char_literal411_tree=null;
		CommonTree char_literal412_tree=null;
		CommonTree string_literal413_tree=null;
		CommonTree WhiteChar414_tree=null;
		CommonTree WhiteChar415_tree=null;
		CommonTree DAYS416_tree=null;
		CommonTree WhiteChar417_tree=null;
		CommonTree string_literal418_tree=null;
		CommonTree WhiteChar419_tree=null;
		CommonTree WhiteChar420_tree=null;
		CommonTree DAYS421_tree=null;
		CommonTree WhiteChar422_tree=null;
		CommonTree string_literal423_tree=null;
		CommonTree WhiteChar424_tree=null;
		CommonTree WhiteChar425_tree=null;
		CommonTree DAYS426_tree=null;
		CommonTree WhiteChar427_tree=null;
		CommonTree string_literal428_tree=null;
		CommonTree WhiteChar429_tree=null;
		CommonTree WhiteChar430_tree=null;
		CommonTree DAYS431_tree=null;
		CommonTree WhiteChar432_tree=null;
		CommonTree string_literal433_tree=null;
		CommonTree WhiteChar434_tree=null;
		CommonTree WhiteChar435_tree=null;
		CommonTree string_literal436_tree=null;
		CommonTree WhiteChar437_tree=null;
		CommonTree WhiteChar438_tree=null;
		CommonTree string_literal439_tree=null;
		CommonTree WhiteChar440_tree=null;
		CommonTree char_literal441_tree=null;
		CommonTree char_literal442_tree=null;
		CommonTree char_literal443_tree=null;
		CommonTree WhiteChar444_tree=null;
		CommonTree string_literal445_tree=null;
		CommonTree WhiteChar446_tree=null;
		CommonTree char_literal447_tree=null;
		CommonTree char_literal448_tree=null;
		CommonTree char_literal449_tree=null;
		CommonTree WhiteChar450_tree=null;
		CommonTree string_literal451_tree=null;
		CommonTree WhiteChar452_tree=null;
		CommonTree char_literal453_tree=null;
		CommonTree char_literal454_tree=null;
		CommonTree char_literal455_tree=null;
		CommonTree string_literal456_tree=null;
		CommonTree WhiteChar457_tree=null;
		CommonTree WhiteChar458_tree=null;
		CommonTree DAYS459_tree=null;
		CommonTree WhiteChar460_tree=null;
		CommonTree string_literal461_tree=null;
		CommonTree WhiteChar462_tree=null;
		CommonTree WhiteChar463_tree=null;
		CommonTree DAYS464_tree=null;
		CommonTree WhiteChar465_tree=null;
		CommonTree string_literal466_tree=null;
		CommonTree WhiteChar467_tree=null;
		CommonTree WhiteChar468_tree=null;
		CommonTree DAYS469_tree=null;
		CommonTree WhiteChar470_tree=null;
		CommonTree string_literal471_tree=null;
		CommonTree WhiteChar472_tree=null;
		CommonTree WhiteChar473_tree=null;
		CommonTree DAYS474_tree=null;
		CommonTree WhiteChar475_tree=null;
		CommonTree string_literal476_tree=null;
		CommonTree WhiteChar477_tree=null;
		CommonTree char_literal478_tree=null;
		CommonTree char_literal479_tree=null;
		CommonTree char_literal480_tree=null;
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
		CommonTree WhiteChar496_tree=null;
		CommonTree DAYS497_tree=null;
		CommonTree WhiteChar498_tree=null;
		CommonTree string_literal499_tree=null;
		CommonTree WhiteChar500_tree=null;
		CommonTree WhiteChar501_tree=null;
		CommonTree DAYS502_tree=null;
		CommonTree WhiteChar503_tree=null;
		CommonTree string_literal504_tree=null;
		CommonTree WhiteChar505_tree=null;
		CommonTree char_literal506_tree=null;
		CommonTree char_literal507_tree=null;
		CommonTree char_literal508_tree=null;
		CommonTree WhiteChar509_tree=null;
		CommonTree string_literal510_tree=null;
		CommonTree WhiteChar511_tree=null;
		CommonTree string_literal512_tree=null;
		CommonTree WhiteChar513_tree=null;
		CommonTree string_literal514_tree=null;
		CommonTree WhiteChar515_tree=null;
		CommonTree WhiteChar516_tree=null;
		CommonTree DAYS517_tree=null;
		CommonTree WhiteChar518_tree=null;
		CommonTree string_literal519_tree=null;
		CommonTree WhiteChar520_tree=null;
		CommonTree WhiteChar521_tree=null;
		CommonTree DAYS522_tree=null;
		CommonTree WhiteChar523_tree=null;
		CommonTree string_literal524_tree=null;
		CommonTree WhiteChar525_tree=null;
		CommonTree string_literal526_tree=null;
		CommonTree WhiteChar527_tree=null;
		CommonTree string_literal528_tree=null;
		CommonTree WhiteChar529_tree=null;
		CommonTree WhiteChar530_tree=null;
		CommonTree DAYS531_tree=null;
		CommonTree WhiteChar532_tree=null;
		CommonTree string_literal533_tree=null;
		CommonTree WhiteChar534_tree=null;
		CommonTree WhiteChar535_tree=null;
		CommonTree DAYS536_tree=null;
		CommonTree WhiteChar537_tree=null;
		CommonTree string_literal538_tree=null;
		CommonTree WhiteChar539_tree=null;
		CommonTree string_literal540_tree=null;
		CommonTree WhiteChar541_tree=null;
		CommonTree string_literal542_tree=null;
		CommonTree WhiteChar543_tree=null;
		CommonTree WhiteChar544_tree=null;
		CommonTree DAYS545_tree=null;
		CommonTree WhiteChar546_tree=null;
		CommonTree string_literal547_tree=null;
		CommonTree WhiteChar548_tree=null;
		CommonTree WhiteChar549_tree=null;
		CommonTree DAYS550_tree=null;
		CommonTree WhiteChar551_tree=null;
		CommonTree string_literal552_tree=null;
		CommonTree WhiteChar553_tree=null;
		RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");
		RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
		RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
		RewriteRuleTokenStream stream_91=new RewriteRuleTokenStream(adaptor,"token 91");
		RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_94=new RewriteRuleTokenStream(adaptor,"token 94");
		RewriteRuleTokenStream stream_95=new RewriteRuleTokenStream(adaptor,"token 95");
		RewriteRuleTokenStream stream_CLOSESQRT=new RewriteRuleTokenStream(adaptor,"token CLOSESQRT");
		RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
		RewriteRuleTokenStream stream_96=new RewriteRuleTokenStream(adaptor,"token 96");
		RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
		RewriteRuleTokenStream stream_97=new RewriteRuleTokenStream(adaptor,"token 97");
		RewriteRuleTokenStream stream_98=new RewriteRuleTokenStream(adaptor,"token 98");
		RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_99=new RewriteRuleTokenStream(adaptor,"token 99");
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_100=new RewriteRuleTokenStream(adaptor,"token 100");
		RewriteRuleTokenStream stream_101=new RewriteRuleTokenStream(adaptor,"token 101");
		RewriteRuleTokenStream stream_102=new RewriteRuleTokenStream(adaptor,"token 102");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_OPENSQRT=new RewriteRuleTokenStream(adaptor,"token OPENSQRT");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_105=new RewriteRuleTokenStream(adaptor,"token 105");
		RewriteRuleTokenStream stream_106=new RewriteRuleTokenStream(adaptor,"token 106");
		RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");
		RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
		RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");
		RewriteRuleTokenStream stream_87=new RewriteRuleTokenStream(adaptor,"token 87");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_stringconstant=new RewriteRuleSubtreeStream(adaptor,"rule stringconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:268:38: ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) ) | ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) ) | ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) ) )
			int alt44=15;
			switch ( input.LA(1) ) {
			case 94:
				{
				alt44=1;
				}
				break;
			case 95:
				{
				alt44=2;
				}
				break;
			case 75:
				{
				alt44=3;
				}
				break;
			case 76:
				{
				alt44=4;
				}
				break;
			case 67:
				{
				alt44=5;
				}
				break;
			case 65:
				{
				alt44=6;
				}
				break;
			case 85:
				{
				alt44=7;
				}
				break;
			case 86:
				{
				alt44=8;
				}
				break;
			case 87:
				{
				alt44=9;
				}
				break;
			case 88:
				{
				alt44=10;
				}
				break;
			case 89:
				{
				alt44=11;
				}
				break;
			case 90:
				{
				alt44=12;
				}
				break;
			case 102:
				{
				alt44=13;
				}
				break;
			case 105:
				{
				alt44=14;
				}
				break;
			case 101:
				{
				alt44=15;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 44, 0, input);
				throw nvae;
			}
			switch (alt44) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:4: 'reverses down'
					{
					string_literal214=(Token)match(input,94,FOLLOW_94_in_presetcondition2116);  
					stream_94.add(string_literal214);

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
					// 270:20: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:23: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:42: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:70: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:99: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:7: ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					int alt38=2;
					int LA38_0 = input.LA(1);
					if ( (LA38_0==WhiteChar) ) {
						int LA38_1 = input.LA(2);
						if ( (LA38_1==91) ) {
							alt38=1;
						}
					}
					switch (alt38) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:9: WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar215=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2156);  
							stream_WhiteChar.add(WhiteChar215);

							string_literal216=(Token)match(input,91,FOLLOW_91_in_presetcondition2158);  
							stream_91.add(string_literal216);

							WhiteChar217=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2160);  
							stream_WhiteChar.add(WhiteChar217);

							pushFollow(FOLLOW_constant_in_presetcondition2164);
							percentdown=constant();
							state._fsp--;

							stream_constant.add(percentdown.getTree());
							PERCENT218=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2166);  
							stream_PERCENT.add(PERCENT218);

							WhiteChar219=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2168);  
							stream_WhiteChar.add(WhiteChar219);

							string_literal220=(Token)match(input,98,FOLLOW_98_in_presetcondition2170);  
							stream_98.add(string_literal220);

							WhiteChar221=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2172);  
							stream_WhiteChar.add(WhiteChar221);

							pushFollow(FOLLOW_constant_in_presetcondition2176);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar222=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2178);  
							stream_WhiteChar.add(WhiteChar222);

							DAYS223=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2180);  
							stream_DAYS.add(DAYS223);

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
							// 272:7: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:272:10: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:272:29: ^( Number NumberToken[\"-1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:4: 'reverses up'
					{
					string_literal224=(Token)match(input,95,FOLLOW_95_in_presetcondition2224);  
					stream_95.add(string_literal224);

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
					// 274:18: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:21: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:40: ^( Number NumberToken[\"1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:67: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:96: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:7: ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					int alt39=2;
					int LA39_0 = input.LA(1);
					if ( (LA39_0==WhiteChar) ) {
						int LA39_1 = input.LA(2);
						if ( (LA39_1==91) ) {
							alt39=1;
						}
					}
					switch (alt39) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:9: WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar225=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2264);  
							stream_WhiteChar.add(WhiteChar225);

							string_literal226=(Token)match(input,91,FOLLOW_91_in_presetcondition2266);  
							stream_91.add(string_literal226);

							WhiteChar227=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2268);  
							stream_WhiteChar.add(WhiteChar227);

							pushFollow(FOLLOW_constant_in_presetcondition2272);
							percentup=constant();
							state._fsp--;

							stream_constant.add(percentup.getTree());
							PERCENT228=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2274);  
							stream_PERCENT.add(PERCENT228);

							WhiteChar229=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2276);  
							stream_WhiteChar.add(WhiteChar229);

							string_literal230=(Token)match(input,98,FOLLOW_98_in_presetcondition2278);  
							stream_98.add(string_literal230);

							WhiteChar231=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2280);  
							stream_WhiteChar.add(WhiteChar231);

							pushFollow(FOLLOW_constant_in_presetcondition2284);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar232=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2286);  
							stream_WhiteChar.add(WhiteChar232);

							DAYS233=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2288);  
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
							// 276:7: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:10: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:29: ^( Number NumberToken[\"1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:4: 'goes down more than' WhiteChar percentdown= constant PERCENT
					{
					string_literal234=(Token)match(input,75,FOLLOW_75_in_presetcondition2331);  
					stream_75.add(string_literal234);

					WhiteChar235=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2333);  
					stream_WhiteChar.add(WhiteChar235);

					pushFollow(FOLLOW_constant_in_presetcondition2337);
					percentdown=constant();
					state._fsp--;

					stream_constant.add(percentdown.getTree());
					PERCENT236=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2339);  
					stream_PERCENT.add(PERCENT236);

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
					// 278:65: -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:68: ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:98: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:127: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:156: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:279:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:279:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar237=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2381);  
							stream_WhiteChar.add(WhiteChar237);

							string_literal238=(Token)match(input,98,FOLLOW_98_in_presetcondition2383);  
							stream_98.add(string_literal238);

							WhiteChar239=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2385);  
							stream_WhiteChar.add(WhiteChar239);

							pushFollow(FOLLOW_constant_in_presetcondition2389);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar240=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2391);  
							stream_WhiteChar.add(WhiteChar240);

							DAYS241=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2393);  
							stream_DAYS.add(DAYS241);

							WhiteChar242=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2404);  
							stream_WhiteChar.add(WhiteChar242);

							string_literal243=(Token)match(input,74,FOLLOW_74_in_presetcondition2406);  
							stream_74.add(string_literal243);

							WhiteChar244=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2408);  
							stream_WhiteChar.add(WhiteChar244);

							pushFollow(FOLLOW_constant_in_presetcondition2412);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar245=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2414);  
							stream_WhiteChar.add(WhiteChar245);

							DAYS246=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2416);  
							stream_DAYS.add(DAYS246);

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
							// 281:7: -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:10: ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentdown!=null?((CommonTree)percentdown.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:74: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:4: 'goes up more than' WhiteChar percentup= constant PERCENT
					{
					string_literal247=(Token)match(input,76,FOLLOW_76_in_presetcondition2453);  
					stream_76.add(string_literal247);

					WhiteChar248=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2455);  
					stream_WhiteChar.add(WhiteChar248);

					pushFollow(FOLLOW_constant_in_presetcondition2459);
					percentup=constant();
					state._fsp--;

					stream_constant.add(percentup.getTree());
					PERCENT249=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2461);  
					stream_PERCENT.add(PERCENT249);

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
					// 282:61: -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:64: ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:92: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:121: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:150: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:283:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:283:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar250=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2503);  
							stream_WhiteChar.add(WhiteChar250);

							string_literal251=(Token)match(input,98,FOLLOW_98_in_presetcondition2505);  
							stream_98.add(string_literal251);

							WhiteChar252=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2507);  
							stream_WhiteChar.add(WhiteChar252);

							pushFollow(FOLLOW_constant_in_presetcondition2511);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar253=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2513);  
							stream_WhiteChar.add(WhiteChar253);

							DAYS254=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2515);  
							stream_DAYS.add(DAYS254);

							WhiteChar255=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2526);  
							stream_WhiteChar.add(WhiteChar255);

							string_literal256=(Token)match(input,74,FOLLOW_74_in_presetcondition2528);  
							stream_74.add(string_literal256);

							WhiteChar257=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2530);  
							stream_WhiteChar.add(WhiteChar257);

							pushFollow(FOLLOW_constant_in_presetcondition2534);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar258=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2536);  
							stream_WhiteChar.add(WhiteChar258);

							DAYS259=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2538);  
							stream_DAYS.add(DAYS259);

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
							// 285:7: -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:285:10: ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentup!=null?((CommonTree)percentup.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:285:70: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:4: 'crosses up threshold' WhiteChar threshold= constant
					{
					string_literal260=(Token)match(input,67,FOLLOW_67_in_presetcondition2582);  
					stream_67.add(string_literal260);

					WhiteChar261=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2584);  
					stream_WhiteChar.add(WhiteChar261);

					pushFollow(FOLLOW_constant_in_presetcondition2588);
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
					// 287:56: -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:59: ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:95: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:124: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:153: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt42=2;
					int LA42_0 = input.LA(1);
					if ( (LA42_0==WhiteChar) ) {
						int LA42_1 = input.LA(2);
						if ( (LA42_1==98) ) {
							alt42=1;
						}
					}
					switch (alt42) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar262=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2630);  
							stream_WhiteChar.add(WhiteChar262);

							string_literal263=(Token)match(input,98,FOLLOW_98_in_presetcondition2632);  
							stream_98.add(string_literal263);

							WhiteChar264=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2634);  
							stream_WhiteChar.add(WhiteChar264);

							pushFollow(FOLLOW_constant_in_presetcondition2638);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar265=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2640);  
							stream_WhiteChar.add(WhiteChar265);

							DAYS266=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2642);  
							stream_DAYS.add(DAYS266);

							WhiteChar267=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2653);  
							stream_WhiteChar.add(WhiteChar267);

							string_literal268=(Token)match(input,92,FOLLOW_92_in_presetcondition2655);  
							stream_92.add(string_literal268);

							WhiteChar269=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2657);  
							stream_WhiteChar.add(WhiteChar269);

							pushFollow(FOLLOW_constant_in_presetcondition2661);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar270=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2663);  
							stream_WhiteChar.add(WhiteChar270);

							DAYS271=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2665);  
							stream_DAYS.add(DAYS271);

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
							// 290:7: -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:10: ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:97: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:4: 'crosses down threshold' WhiteChar threshold= constant
					{
					string_literal272=(Token)match(input,65,FOLLOW_65_in_presetcondition2711);  
					stream_65.add(string_literal272);

					WhiteChar273=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2713);  
					stream_WhiteChar.add(WhiteChar273);

					pushFollow(FOLLOW_constant_in_presetcondition2717);
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
					// 292:58: -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:61: ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:99: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:128: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:157: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt43=2;
					int LA43_0 = input.LA(1);
					if ( (LA43_0==WhiteChar) ) {
						int LA43_1 = input.LA(2);
						if ( (LA43_1==98) ) {
							alt43=1;
						}
					}
					switch (alt43) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar274=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2759);  
							stream_WhiteChar.add(WhiteChar274);

							string_literal275=(Token)match(input,98,FOLLOW_98_in_presetcondition2761);  
							stream_98.add(string_literal275);

							WhiteChar276=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2763);  
							stream_WhiteChar.add(WhiteChar276);

							pushFollow(FOLLOW_constant_in_presetcondition2767);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar277=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2769);  
							stream_WhiteChar.add(WhiteChar277);

							DAYS278=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2771);  
							stream_DAYS.add(DAYS278);

							WhiteChar279=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2782);  
							stream_WhiteChar.add(WhiteChar279);

							string_literal280=(Token)match(input,92,FOLLOW_92_in_presetcondition2784);  
							stream_92.add(string_literal280);

							WhiteChar281=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2786);  
							stream_WhiteChar.add(WhiteChar281);

							pushFollow(FOLLOW_constant_in_presetcondition2790);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar282=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2792);  
							stream_WhiteChar.add(WhiteChar282);

							DAYS283=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2794);  
							stream_DAYS.add(DAYS283);

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
							// 295:7: -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:10: ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:99: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:4: 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal284=(Token)match(input,85,FOLLOW_85_in_presetcondition2832);  
					stream_85.add(string_literal284);

					WhiteChar285=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2834);  
					stream_WhiteChar.add(WhiteChar285);

					pushFollow(FOLLOW_constant_in_presetcondition2838);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar286=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2840);  
					stream_WhiteChar.add(WhiteChar286);

					DAYS287=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2842);  
					stream_DAYS.add(DAYS287);

					WhiteChar288=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2849);  
					stream_WhiteChar.add(WhiteChar288);

					string_literal289=(Token)match(input,92,FOLLOW_92_in_presetcondition2851);  
					stream_92.add(string_literal289);

					WhiteChar290=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2853);  
					stream_WhiteChar.add(WhiteChar290);

					pushFollow(FOLLOW_constant_in_presetcondition2857);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar291=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2859);  
					stream_WhiteChar.add(WhiteChar291);

					DAYS292=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2861);  
					stream_DAYS.add(DAYS292);

					WhiteChar293=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2868);  
					stream_WhiteChar.add(WhiteChar293);

					string_literal294=(Token)match(input,74,FOLLOW_74_in_presetcondition2870);  
					stream_74.add(string_literal294);

					WhiteChar295=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2872);  
					stream_WhiteChar.add(WhiteChar295);

					pushFollow(FOLLOW_constant_in_presetcondition2876);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar296=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2878);  
					stream_WhiteChar.add(WhiteChar296);

					DAYS297=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2880);  
					stream_DAYS.add(DAYS297);

					WhiteChar298=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2887);  
					stream_WhiteChar.add(WhiteChar298);

					string_literal299=(Token)match(input,97,FOLLOW_97_in_presetcondition2889);  
					stream_97.add(string_literal299);

					WhiteChar300=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2891);  
					stream_WhiteChar.add(WhiteChar300);

					pushFollow(FOLLOW_constant_in_presetcondition2895);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar301=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2897);  
					stream_WhiteChar.add(WhiteChar301);

					DAYS302=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2899);  
					stream_DAYS.add(DAYS302);

					WhiteChar303=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2906);  
					stream_WhiteChar.add(WhiteChar303);

					string_literal304=(Token)match(input,77,FOLLOW_77_in_presetcondition2908);  
					stream_77.add(string_literal304);

					WhiteChar305=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2910);  
					stream_WhiteChar.add(WhiteChar305);

					pushFollow(FOLLOW_stringconstant_in_presetcondition2914);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar306=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2920);  
					stream_WhiteChar.add(WhiteChar306);

					string_literal307=(Token)match(input,106,FOLLOW_106_in_presetcondition2922);  
					stream_106.add(string_literal307);

					WhiteChar308=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2924);  
					stream_WhiteChar.add(WhiteChar308);

					pushFollow(FOLLOW_stringconstant_in_presetcondition2928);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar309=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2934);  
					stream_WhiteChar.add(WhiteChar309);

					string_literal310=(Token)match(input,99,FOLLOW_99_in_presetcondition2936);  
					stream_99.add(string_literal310);

					WhiteChar311=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2938);  
					stream_WhiteChar.add(WhiteChar311);

					char_literal312=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition2940);  
					stream_OPENSQRT.add(char_literal312);

					pushFollow(FOLLOW_constant_in_presetcondition2944);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal313=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition2946);  
					stream_COMMA.add(char_literal313);

					pushFollow(FOLLOW_constant_in_presetcondition2950);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal314=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition2952);  
					stream_CLOSESQRT.add(char_literal314);

					WhiteChar315=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2954);  
					stream_WhiteChar.add(WhiteChar315);

					string_literal316=(Token)match(input,69,FOLLOW_69_in_presetcondition2956);  
					stream_69.add(string_literal316);

					WhiteChar317=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2958);  
					stream_WhiteChar.add(WhiteChar317);

					char_literal318=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition2960);  
					stream_OPENSQRT.add(char_literal318);

					pushFollow(FOLLOW_constant_in_presetcondition2964);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal319=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition2966);  
					stream_COMMA.add(char_literal319);

					pushFollow(FOLLOW_constant_in_presetcondition2970);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal320=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition2972);  
					stream_CLOSESQRT.add(char_literal320);

					WhiteChar321=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2978);  
					stream_WhiteChar.add(WhiteChar321);

					string_literal322=(Token)match(input,96,FOLLOW_96_in_presetcondition2980);  
					stream_96.add(string_literal322);

					WhiteChar323=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2982);  
					stream_WhiteChar.add(WhiteChar323);

					char_literal324=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition2984);  
					stream_OPENSQRT.add(char_literal324);

					pushFollow(FOLLOW_constant_in_presetcondition2988);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal325=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition2990);  
					stream_COMMA.add(char_literal325);

					pushFollow(FOLLOW_constant_in_presetcondition2994);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal326=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition2996);  
					stream_CLOSESQRT.add(char_literal326);

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
					// 305:4: -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:305:7: ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:305:246: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:306:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:306:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:306:4: 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal327=(Token)match(input,86,FOLLOW_86_in_presetcondition3047);  
					stream_86.add(string_literal327);

					WhiteChar328=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3049);  
					stream_WhiteChar.add(WhiteChar328);

					pushFollow(FOLLOW_constant_in_presetcondition3053);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar329=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3055);  
					stream_WhiteChar.add(WhiteChar329);

					DAYS330=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3057);  
					stream_DAYS.add(DAYS330);

					WhiteChar331=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3064);  
					stream_WhiteChar.add(WhiteChar331);

					string_literal332=(Token)match(input,92,FOLLOW_92_in_presetcondition3066);  
					stream_92.add(string_literal332);

					WhiteChar333=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3068);  
					stream_WhiteChar.add(WhiteChar333);

					pushFollow(FOLLOW_constant_in_presetcondition3072);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar334=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3074);  
					stream_WhiteChar.add(WhiteChar334);

					DAYS335=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3076);  
					stream_DAYS.add(DAYS335);

					WhiteChar336=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3083);  
					stream_WhiteChar.add(WhiteChar336);

					string_literal337=(Token)match(input,74,FOLLOW_74_in_presetcondition3085);  
					stream_74.add(string_literal337);

					WhiteChar338=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3087);  
					stream_WhiteChar.add(WhiteChar338);

					pushFollow(FOLLOW_constant_in_presetcondition3091);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar339=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3093);  
					stream_WhiteChar.add(WhiteChar339);

					DAYS340=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3095);  
					stream_DAYS.add(DAYS340);

					WhiteChar341=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3102);  
					stream_WhiteChar.add(WhiteChar341);

					string_literal342=(Token)match(input,97,FOLLOW_97_in_presetcondition3104);  
					stream_97.add(string_literal342);

					WhiteChar343=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3106);  
					stream_WhiteChar.add(WhiteChar343);

					pushFollow(FOLLOW_constant_in_presetcondition3110);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar344=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3112);  
					stream_WhiteChar.add(WhiteChar344);

					DAYS345=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3114);  
					stream_DAYS.add(DAYS345);

					WhiteChar346=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3121);  
					stream_WhiteChar.add(WhiteChar346);

					string_literal347=(Token)match(input,77,FOLLOW_77_in_presetcondition3123);  
					stream_77.add(string_literal347);

					WhiteChar348=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3125);  
					stream_WhiteChar.add(WhiteChar348);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3129);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar349=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3135);  
					stream_WhiteChar.add(WhiteChar349);

					string_literal350=(Token)match(input,106,FOLLOW_106_in_presetcondition3137);  
					stream_106.add(string_literal350);

					WhiteChar351=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3139);  
					stream_WhiteChar.add(WhiteChar351);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3143);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar352=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3149);  
					stream_WhiteChar.add(WhiteChar352);

					string_literal353=(Token)match(input,99,FOLLOW_99_in_presetcondition3151);  
					stream_99.add(string_literal353);

					WhiteChar354=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3153);  
					stream_WhiteChar.add(WhiteChar354);

					char_literal355=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3155);  
					stream_OPENSQRT.add(char_literal355);

					pushFollow(FOLLOW_constant_in_presetcondition3159);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal356=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3161);  
					stream_COMMA.add(char_literal356);

					pushFollow(FOLLOW_constant_in_presetcondition3165);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal357=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3167);  
					stream_CLOSESQRT.add(char_literal357);

					WhiteChar358=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3169);  
					stream_WhiteChar.add(WhiteChar358);

					string_literal359=(Token)match(input,69,FOLLOW_69_in_presetcondition3171);  
					stream_69.add(string_literal359);

					WhiteChar360=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3173);  
					stream_WhiteChar.add(WhiteChar360);

					char_literal361=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3175);  
					stream_OPENSQRT.add(char_literal361);

					pushFollow(FOLLOW_constant_in_presetcondition3179);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal362=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3181);  
					stream_COMMA.add(char_literal362);

					pushFollow(FOLLOW_constant_in_presetcondition3185);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal363=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3187);  
					stream_CLOSESQRT.add(char_literal363);

					WhiteChar364=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3193);  
					stream_WhiteChar.add(WhiteChar364);

					string_literal365=(Token)match(input,96,FOLLOW_96_in_presetcondition3195);  
					stream_96.add(string_literal365);

					WhiteChar366=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3197);  
					stream_WhiteChar.add(WhiteChar366);

					char_literal367=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3199);  
					stream_OPENSQRT.add(char_literal367);

					pushFollow(FOLLOW_constant_in_presetcondition3203);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal368=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3205);  
					stream_COMMA.add(char_literal368);

					pushFollow(FOLLOW_constant_in_presetcondition3209);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal369=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3211);  
					stream_CLOSESQRT.add(char_literal369);

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
					// 314:4: -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:7: ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:245: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:315:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:315:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:315:4: 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal370=(Token)match(input,87,FOLLOW_87_in_presetcondition3262);  
					stream_87.add(string_literal370);

					WhiteChar371=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3264);  
					stream_WhiteChar.add(WhiteChar371);

					pushFollow(FOLLOW_constant_in_presetcondition3268);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar372=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3270);  
					stream_WhiteChar.add(WhiteChar372);

					DAYS373=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3272);  
					stream_DAYS.add(DAYS373);

					WhiteChar374=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3279);  
					stream_WhiteChar.add(WhiteChar374);

					string_literal375=(Token)match(input,92,FOLLOW_92_in_presetcondition3281);  
					stream_92.add(string_literal375);

					WhiteChar376=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3283);  
					stream_WhiteChar.add(WhiteChar376);

					pushFollow(FOLLOW_constant_in_presetcondition3287);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar377=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3289);  
					stream_WhiteChar.add(WhiteChar377);

					DAYS378=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3291);  
					stream_DAYS.add(DAYS378);

					WhiteChar379=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3298);  
					stream_WhiteChar.add(WhiteChar379);

					string_literal380=(Token)match(input,74,FOLLOW_74_in_presetcondition3300);  
					stream_74.add(string_literal380);

					WhiteChar381=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3302);  
					stream_WhiteChar.add(WhiteChar381);

					pushFollow(FOLLOW_constant_in_presetcondition3306);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar382=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3308);  
					stream_WhiteChar.add(WhiteChar382);

					DAYS383=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3310);  
					stream_DAYS.add(DAYS383);

					WhiteChar384=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3317);  
					stream_WhiteChar.add(WhiteChar384);

					string_literal385=(Token)match(input,97,FOLLOW_97_in_presetcondition3319);  
					stream_97.add(string_literal385);

					WhiteChar386=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3321);  
					stream_WhiteChar.add(WhiteChar386);

					pushFollow(FOLLOW_constant_in_presetcondition3325);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar387=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3327);  
					stream_WhiteChar.add(WhiteChar387);

					DAYS388=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3329);  
					stream_DAYS.add(DAYS388);

					WhiteChar389=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3336);  
					stream_WhiteChar.add(WhiteChar389);

					string_literal390=(Token)match(input,77,FOLLOW_77_in_presetcondition3338);  
					stream_77.add(string_literal390);

					WhiteChar391=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3340);  
					stream_WhiteChar.add(WhiteChar391);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3344);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar392=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3350);  
					stream_WhiteChar.add(WhiteChar392);

					string_literal393=(Token)match(input,106,FOLLOW_106_in_presetcondition3352);  
					stream_106.add(string_literal393);

					WhiteChar394=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3354);  
					stream_WhiteChar.add(WhiteChar394);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3358);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar395=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3364);  
					stream_WhiteChar.add(WhiteChar395);

					string_literal396=(Token)match(input,99,FOLLOW_99_in_presetcondition3366);  
					stream_99.add(string_literal396);

					WhiteChar397=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3368);  
					stream_WhiteChar.add(WhiteChar397);

					char_literal398=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3370);  
					stream_OPENSQRT.add(char_literal398);

					pushFollow(FOLLOW_constant_in_presetcondition3374);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal399=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3376);  
					stream_COMMA.add(char_literal399);

					pushFollow(FOLLOW_constant_in_presetcondition3380);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal400=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3382);  
					stream_CLOSESQRT.add(char_literal400);

					WhiteChar401=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3384);  
					stream_WhiteChar.add(WhiteChar401);

					string_literal402=(Token)match(input,69,FOLLOW_69_in_presetcondition3386);  
					stream_69.add(string_literal402);

					WhiteChar403=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3388);  
					stream_WhiteChar.add(WhiteChar403);

					char_literal404=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3390);  
					stream_OPENSQRT.add(char_literal404);

					pushFollow(FOLLOW_constant_in_presetcondition3394);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal405=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3396);  
					stream_COMMA.add(char_literal405);

					pushFollow(FOLLOW_constant_in_presetcondition3400);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal406=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3402);  
					stream_CLOSESQRT.add(char_literal406);

					WhiteChar407=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3408);  
					stream_WhiteChar.add(WhiteChar407);

					string_literal408=(Token)match(input,96,FOLLOW_96_in_presetcondition3410);  
					stream_96.add(string_literal408);

					WhiteChar409=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3412);  
					stream_WhiteChar.add(WhiteChar409);

					char_literal410=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3414);  
					stream_OPENSQRT.add(char_literal410);

					pushFollow(FOLLOW_constant_in_presetcondition3418);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal411=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3420);  
					stream_COMMA.add(char_literal411);

					pushFollow(FOLLOW_constant_in_presetcondition3424);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal412=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3426);  
					stream_CLOSESQRT.add(char_literal412);

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
					// 323:4: -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:323:7: ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:323:245: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:4: 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal413=(Token)match(input,88,FOLLOW_88_in_presetcondition3477);  
					stream_88.add(string_literal413);

					WhiteChar414=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3479);  
					stream_WhiteChar.add(WhiteChar414);

					pushFollow(FOLLOW_constant_in_presetcondition3483);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar415=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3485);  
					stream_WhiteChar.add(WhiteChar415);

					DAYS416=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3487);  
					stream_DAYS.add(DAYS416);

					WhiteChar417=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3494);  
					stream_WhiteChar.add(WhiteChar417);

					string_literal418=(Token)match(input,92,FOLLOW_92_in_presetcondition3496);  
					stream_92.add(string_literal418);

					WhiteChar419=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3498);  
					stream_WhiteChar.add(WhiteChar419);

					pushFollow(FOLLOW_constant_in_presetcondition3502);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar420=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3504);  
					stream_WhiteChar.add(WhiteChar420);

					DAYS421=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3506);  
					stream_DAYS.add(DAYS421);

					WhiteChar422=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3513);  
					stream_WhiteChar.add(WhiteChar422);

					string_literal423=(Token)match(input,74,FOLLOW_74_in_presetcondition3515);  
					stream_74.add(string_literal423);

					WhiteChar424=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3517);  
					stream_WhiteChar.add(WhiteChar424);

					pushFollow(FOLLOW_constant_in_presetcondition3521);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar425=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3523);  
					stream_WhiteChar.add(WhiteChar425);

					DAYS426=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3525);  
					stream_DAYS.add(DAYS426);

					WhiteChar427=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3532);  
					stream_WhiteChar.add(WhiteChar427);

					string_literal428=(Token)match(input,97,FOLLOW_97_in_presetcondition3534);  
					stream_97.add(string_literal428);

					WhiteChar429=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3536);  
					stream_WhiteChar.add(WhiteChar429);

					pushFollow(FOLLOW_constant_in_presetcondition3540);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar430=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3542);  
					stream_WhiteChar.add(WhiteChar430);

					DAYS431=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3544);  
					stream_DAYS.add(DAYS431);

					WhiteChar432=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3551);  
					stream_WhiteChar.add(WhiteChar432);

					string_literal433=(Token)match(input,77,FOLLOW_77_in_presetcondition3553);  
					stream_77.add(string_literal433);

					WhiteChar434=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3555);  
					stream_WhiteChar.add(WhiteChar434);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3559);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar435=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3565);  
					stream_WhiteChar.add(WhiteChar435);

					string_literal436=(Token)match(input,106,FOLLOW_106_in_presetcondition3567);  
					stream_106.add(string_literal436);

					WhiteChar437=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3569);  
					stream_WhiteChar.add(WhiteChar437);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3573);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar438=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3579);  
					stream_WhiteChar.add(WhiteChar438);

					string_literal439=(Token)match(input,99,FOLLOW_99_in_presetcondition3581);  
					stream_99.add(string_literal439);

					WhiteChar440=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3583);  
					stream_WhiteChar.add(WhiteChar440);

					char_literal441=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3585);  
					stream_OPENSQRT.add(char_literal441);

					pushFollow(FOLLOW_constant_in_presetcondition3589);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal442=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3591);  
					stream_COMMA.add(char_literal442);

					pushFollow(FOLLOW_constant_in_presetcondition3595);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal443=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3597);  
					stream_CLOSESQRT.add(char_literal443);

					WhiteChar444=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3599);  
					stream_WhiteChar.add(WhiteChar444);

					string_literal445=(Token)match(input,69,FOLLOW_69_in_presetcondition3601);  
					stream_69.add(string_literal445);

					WhiteChar446=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3603);  
					stream_WhiteChar.add(WhiteChar446);

					char_literal447=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3605);  
					stream_OPENSQRT.add(char_literal447);

					pushFollow(FOLLOW_constant_in_presetcondition3609);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal448=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3611);  
					stream_COMMA.add(char_literal448);

					pushFollow(FOLLOW_constant_in_presetcondition3615);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal449=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3617);  
					stream_CLOSESQRT.add(char_literal449);

					WhiteChar450=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3623);  
					stream_WhiteChar.add(WhiteChar450);

					string_literal451=(Token)match(input,96,FOLLOW_96_in_presetcondition3625);  
					stream_96.add(string_literal451);

					WhiteChar452=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3627);  
					stream_WhiteChar.add(WhiteChar452);

					char_literal453=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3629);  
					stream_OPENSQRT.add(char_literal453);

					pushFollow(FOLLOW_constant_in_presetcondition3633);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal454=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3635);  
					stream_COMMA.add(char_literal454);

					pushFollow(FOLLOW_constant_in_presetcondition3639);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal455=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3641);  
					stream_CLOSESQRT.add(char_literal455);

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
					// 332:4: -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:332:7: ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:332:244: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:334:4: ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:334:4: ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:334:5: 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant
					{
					string_literal456=(Token)match(input,89,FOLLOW_89_in_presetcondition3694);  
					stream_89.add(string_literal456);

					WhiteChar457=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3696);  
					stream_WhiteChar.add(WhiteChar457);

					pushFollow(FOLLOW_constant_in_presetcondition3700);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar458=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3702);  
					stream_WhiteChar.add(WhiteChar458);

					DAYS459=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3704);  
					stream_DAYS.add(DAYS459);

					WhiteChar460=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3711);  
					stream_WhiteChar.add(WhiteChar460);

					string_literal461=(Token)match(input,92,FOLLOW_92_in_presetcondition3713);  
					stream_92.add(string_literal461);

					WhiteChar462=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3715);  
					stream_WhiteChar.add(WhiteChar462);

					pushFollow(FOLLOW_constant_in_presetcondition3719);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar463=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3721);  
					stream_WhiteChar.add(WhiteChar463);

					DAYS464=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3723);  
					stream_DAYS.add(DAYS464);

					WhiteChar465=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3730);  
					stream_WhiteChar.add(WhiteChar465);

					string_literal466=(Token)match(input,74,FOLLOW_74_in_presetcondition3732);  
					stream_74.add(string_literal466);

					WhiteChar467=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3734);  
					stream_WhiteChar.add(WhiteChar467);

					pushFollow(FOLLOW_constant_in_presetcondition3738);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar468=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3740);  
					stream_WhiteChar.add(WhiteChar468);

					DAYS469=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3742);  
					stream_DAYS.add(DAYS469);

					WhiteChar470=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3749);  
					stream_WhiteChar.add(WhiteChar470);

					string_literal471=(Token)match(input,97,FOLLOW_97_in_presetcondition3751);  
					stream_97.add(string_literal471);

					WhiteChar472=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3753);  
					stream_WhiteChar.add(WhiteChar472);

					pushFollow(FOLLOW_constant_in_presetcondition3757);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar473=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3759);  
					stream_WhiteChar.add(WhiteChar473);

					DAYS474=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3761);  
					stream_DAYS.add(DAYS474);

					WhiteChar475=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3768);  
					stream_WhiteChar.add(WhiteChar475);

					string_literal476=(Token)match(input,99,FOLLOW_99_in_presetcondition3770);  
					stream_99.add(string_literal476);

					WhiteChar477=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3772);  
					stream_WhiteChar.add(WhiteChar477);

					char_literal478=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3774);  
					stream_OPENSQRT.add(char_literal478);

					pushFollow(FOLLOW_constant_in_presetcondition3778);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal479=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3780);  
					stream_COMMA.add(char_literal479);

					pushFollow(FOLLOW_constant_in_presetcondition3784);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal480=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3786);  
					stream_CLOSESQRT.add(char_literal480);

					WhiteChar481=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3792);  
					stream_WhiteChar.add(WhiteChar481);

					string_literal482=(Token)match(input,100,FOLLOW_100_in_presetcondition3794);  
					stream_100.add(string_literal482);

					WhiteChar483=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3796);  
					stream_WhiteChar.add(WhiteChar483);

					pushFollow(FOLLOW_constant_in_presetcondition3800);
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
					// 340:4: -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:340:7: ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupportBreakDown, "SupportBreakDown"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:340:104: ^( String StringToken[\"\\\"greedy\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"greedy\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:340:140: ^( String StringToken[\"\\\"smooth\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"smooth\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:340:217: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:340:246: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:340:275: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:340:304: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:342:4: ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:342:4: ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:342:5: 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant
					{
					string_literal484=(Token)match(input,90,FOLLOW_90_in_presetcondition3878);  
					stream_90.add(string_literal484);

					WhiteChar485=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3880);  
					stream_WhiteChar.add(WhiteChar485);

					pushFollow(FOLLOW_constant_in_presetcondition3884);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar486=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3886);  
					stream_WhiteChar.add(WhiteChar486);

					DAYS487=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3888);  
					stream_DAYS.add(DAYS487);

					WhiteChar488=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3898);  
					stream_WhiteChar.add(WhiteChar488);

					string_literal489=(Token)match(input,92,FOLLOW_92_in_presetcondition3900);  
					stream_92.add(string_literal489);

					WhiteChar490=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3902);  
					stream_WhiteChar.add(WhiteChar490);

					pushFollow(FOLLOW_constant_in_presetcondition3906);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar491=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3908);  
					stream_WhiteChar.add(WhiteChar491);

					DAYS492=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3910);  
					stream_DAYS.add(DAYS492);

					WhiteChar493=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3920);  
					stream_WhiteChar.add(WhiteChar493);

					string_literal494=(Token)match(input,74,FOLLOW_74_in_presetcondition3922);  
					stream_74.add(string_literal494);

					WhiteChar495=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3924);  
					stream_WhiteChar.add(WhiteChar495);

					pushFollow(FOLLOW_constant_in_presetcondition3928);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar496=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3930);  
					stream_WhiteChar.add(WhiteChar496);

					DAYS497=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3932);  
					stream_DAYS.add(DAYS497);

					WhiteChar498=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3942);  
					stream_WhiteChar.add(WhiteChar498);

					string_literal499=(Token)match(input,97,FOLLOW_97_in_presetcondition3944);  
					stream_97.add(string_literal499);

					WhiteChar500=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3946);  
					stream_WhiteChar.add(WhiteChar500);

					pushFollow(FOLLOW_constant_in_presetcondition3950);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar501=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3952);  
					stream_WhiteChar.add(WhiteChar501);

					DAYS502=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3954);  
					stream_DAYS.add(DAYS502);

					WhiteChar503=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3964);  
					stream_WhiteChar.add(WhiteChar503);

					string_literal504=(Token)match(input,99,FOLLOW_99_in_presetcondition3966);  
					stream_99.add(string_literal504);

					WhiteChar505=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3968);  
					stream_WhiteChar.add(WhiteChar505);

					char_literal506=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3970);  
					stream_OPENSQRT.add(char_literal506);

					pushFollow(FOLLOW_constant_in_presetcondition3974);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal507=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3976);  
					stream_COMMA.add(char_literal507);

					pushFollow(FOLLOW_constant_in_presetcondition3980);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal508=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3982);  
					stream_CLOSESQRT.add(char_literal508);

					WhiteChar509=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3992);  
					stream_WhiteChar.add(WhiteChar509);

					string_literal510=(Token)match(input,100,FOLLOW_100_in_presetcondition3994);  
					stream_100.add(string_literal510);

					WhiteChar511=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3996);  
					stream_WhiteChar.add(WhiteChar511);

					pushFollow(FOLLOW_constant_in_presetcondition4000);
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
					// 348:6: -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:348:9: ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupportBreakUp, "SupportBreakUp"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:348:104: ^( String StringToken[\"\\\"greedy\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"greedy\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:348:140: ^( String StringToken[\"\\\"smooth\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"smooth\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:348:217: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:348:246: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:348:275: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:348:304: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:350:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:350:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:350:4: 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal512=(Token)match(input,102,FOLLOW_102_in_presetcondition4079);  
					stream_102.add(string_literal512);

					WhiteChar513=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4087);  
					stream_WhiteChar.add(WhiteChar513);

					string_literal514=(Token)match(input,92,FOLLOW_92_in_presetcondition4089);  
					stream_92.add(string_literal514);

					WhiteChar515=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4091);  
					stream_WhiteChar.add(WhiteChar515);

					pushFollow(FOLLOW_constant_in_presetcondition4095);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar516=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4097);  
					stream_WhiteChar.add(WhiteChar516);

					DAYS517=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4099);  
					stream_DAYS.add(DAYS517);

					WhiteChar518=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4107);  
					stream_WhiteChar.add(WhiteChar518);

					string_literal519=(Token)match(input,74,FOLLOW_74_in_presetcondition4109);  
					stream_74.add(string_literal519);

					WhiteChar520=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4111);  
					stream_WhiteChar.add(WhiteChar520);

					pushFollow(FOLLOW_constant_in_presetcondition4115);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar521=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4117);  
					stream_WhiteChar.add(WhiteChar521);

					DAYS522=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4119);  
					stream_DAYS.add(DAYS522);

					WhiteChar523=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4127);  
					stream_WhiteChar.add(WhiteChar523);

					string_literal524=(Token)match(input,70,FOLLOW_70_in_presetcondition4129);  
					stream_70.add(string_literal524);

					WhiteChar525=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4131);  
					stream_WhiteChar.add(WhiteChar525);

					pushFollow(FOLLOW_constant_in_presetcondition4135);
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
					// 354:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:354:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:354:79: ^( String StringToken[\"\\\"flat\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:356:3: ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:356:3: ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:356:4: 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal526=(Token)match(input,105,FOLLOW_105_in_presetcondition4171);  
					stream_105.add(string_literal526);

					WhiteChar527=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4179);  
					stream_WhiteChar.add(WhiteChar527);

					string_literal528=(Token)match(input,92,FOLLOW_92_in_presetcondition4181);  
					stream_92.add(string_literal528);

					WhiteChar529=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4183);  
					stream_WhiteChar.add(WhiteChar529);

					pushFollow(FOLLOW_constant_in_presetcondition4187);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar530=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4189);  
					stream_WhiteChar.add(WhiteChar530);

					DAYS531=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4191);  
					stream_DAYS.add(DAYS531);

					WhiteChar532=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4199);  
					stream_WhiteChar.add(WhiteChar532);

					string_literal533=(Token)match(input,74,FOLLOW_74_in_presetcondition4201);  
					stream_74.add(string_literal533);

					WhiteChar534=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4203);  
					stream_WhiteChar.add(WhiteChar534);

					pushFollow(FOLLOW_constant_in_presetcondition4207);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar535=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4209);  
					stream_WhiteChar.add(WhiteChar535);

					DAYS536=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4211);  
					stream_DAYS.add(DAYS536);

					WhiteChar537=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4219);  
					stream_WhiteChar.add(WhiteChar537);

					string_literal538=(Token)match(input,70,FOLLOW_70_in_presetcondition4221);  
					stream_70.add(string_literal538);

					WhiteChar539=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4223);  
					stream_WhiteChar.add(WhiteChar539);

					pushFollow(FOLLOW_constant_in_presetcondition4227);
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
					// 360:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:360:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:360:79: ^( String StringToken[\"\\\"up\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:362:3: ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:362:3: ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:362:4: 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal540=(Token)match(input,101,FOLLOW_101_in_presetcondition4263);  
					stream_101.add(string_literal540);

					WhiteChar541=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4271);  
					stream_WhiteChar.add(WhiteChar541);

					string_literal542=(Token)match(input,92,FOLLOW_92_in_presetcondition4273);  
					stream_92.add(string_literal542);

					WhiteChar543=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4275);  
					stream_WhiteChar.add(WhiteChar543);

					pushFollow(FOLLOW_constant_in_presetcondition4279);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar544=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4281);  
					stream_WhiteChar.add(WhiteChar544);

					DAYS545=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4283);  
					stream_DAYS.add(DAYS545);

					WhiteChar546=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4291);  
					stream_WhiteChar.add(WhiteChar546);

					string_literal547=(Token)match(input,74,FOLLOW_74_in_presetcondition4293);  
					stream_74.add(string_literal547);

					WhiteChar548=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4295);  
					stream_WhiteChar.add(WhiteChar548);

					pushFollow(FOLLOW_constant_in_presetcondition4299);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar549=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4301);  
					stream_WhiteChar.add(WhiteChar549);

					DAYS550=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4303);  
					stream_DAYS.add(DAYS550);

					WhiteChar551=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4311);  
					stream_WhiteChar.add(WhiteChar551);

					string_literal552=(Token)match(input,70,FOLLOW_70_in_presetcondition4313);  
					stream_70.add(string_literal552);

					WhiteChar553=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4315);  
					stream_WhiteChar.add(WhiteChar553);

					pushFollow(FOLLOW_constant_in_presetcondition4319);
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
					// 366:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:366:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:366:79: ^( String StringToken[\"\\\"down\\\"\"] )
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



	public static final BitSet FOLLOW_bullish_condition_in_complete_expression416 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
	public static final BitSet FOLLOW_bearish_condition_in_complete_expression418 = new BitSet(new long[]{0x2000000000000000L,0x0000000020000000L});
	public static final BitSet FOLLOW_also_display_in_complete_expression421 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
	public static final BitSet FOLLOW_fixed_start_shift_in_complete_expression423 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_84_in_bullish_condition452 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition454 = new BitSet(new long[]{0x0000048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bullish_condition456 = new BitSet(new long[]{0x0800800000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition458 = new BitSet(new long[]{0x0800800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bullish_condition461 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition463 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_81_in_bearish_condition479 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition481 = new BitSet(new long[]{0x0000048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_condition483 = new BitSet(new long[]{0x0800800000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition485 = new BitSet(new long[]{0x0800800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bearish_condition488 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition490 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_bearish_not_bullish_in_bearish_condition500 = new BitSet(new long[]{0x0800800000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition503 = new BitSet(new long[]{0x0800800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bearish_condition506 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition508 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_61_in_also_display525 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display527 = new BitSet(new long[]{0x0000048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_also_display529 = new BitSet(new long[]{0x0800800000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display531 = new BitSet(new long[]{0x0800800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_also_display534 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_93_in_fixed_start_shift569 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift571 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_fixed_start_shift575 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift577 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_fixed_start_shift579 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_fixed_start_shift581 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_80_in_bearish_not_bullish611 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish619 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_bearish_not_bullish621 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish623 = new BitSet(new long[]{0x0000048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish625 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish651 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OR_in_bearish_not_bullish653 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish655 = new BitSet(new long[]{0x0000048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish657 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_and_expression_in_primary_expression698 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_expression_in_and_expression709 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_lenient_in_and_expression713 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression716 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_and_expression718 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression720 = new BitSet(new long[]{0x0000048400400000L});
	public static final BitSet FOLLOW_or_expression_in_and_expression722 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_matches_expression_in_or_expression749 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression752 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OR_in_or_expression754 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression756 = new BitSet(new long[]{0x0000048400400000L});
	public static final BitSet FOLLOW_matches_expression_in_or_expression758 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_atom_in_matches_expression783 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression786 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_MATCHING_in_matches_expression788 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression790 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_matches_expression792 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_matches_expression794 = new BitSet(new long[]{0x0000000000000180L});
	public static final BitSet FOLLOW_COMMA_in_matches_expression797 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_matches_expression799 = new BitSet(new long[]{0x0000000000000180L});
	public static final BitSet FOLLOW_CLOSESQRT_in_matches_expression803 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression805 = new BitSet(new long[]{0x0000048400400000L});
	public static final BitSet FOLLOW_atom_in_matches_expression807 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_booleanhistory_in_atom835 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom841 = new BitSet(new long[]{0x0800048400400000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom843 = new BitSet(new long[]{0x0800048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_atom846 = new BitSet(new long[]{0x0800000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom848 = new BitSet(new long[]{0x0800000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom851 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_atom861 = new BitSet(new long[]{0x0800008000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom863 = new BitSet(new long[]{0x0800008000000000L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom866 = new BitSet(new long[]{0x0800048400400000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom868 = new BitSet(new long[]{0x0800048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_atom871 = new BitSet(new long[]{0x0800000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom873 = new BitSet(new long[]{0x0800000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom876 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_booleanhistory899 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_booleanhistory901 = new BitSet(new long[]{0x0000000000000000L,0x000003E0C7ECDB8FL});
	public static final BitSet FOLLOW_presetcondition_in_booleanhistory907 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_opcmpcondition_in_booleanhistory918 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constantcmp_in_booleanhistory929 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_operand944 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Operation_in_operand971 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NumberToken_in_constant985 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_60_in_constant997 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_StringToken_in_stringconstant1013 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_63_in_trendconstant1028 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_62_in_trendconstant1041 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_lenient1058 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_LENIENT_in_lenient1060 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_78_in_opcmpcondition1097 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1099 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1103 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1129 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_opcmpcondition1131 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1133 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1137 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1139 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1141 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_82_in_opcmpcondition1163 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1165 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1169 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1197 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_opcmpcondition1199 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1201 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1205 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1207 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1209 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_71_in_opcmpcondition1231 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1233 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1237 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1264 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_opcmpcondition1266 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1268 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1272 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1274 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1276 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_64_in_opcmpcondition1299 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1301 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1303 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1340 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_opcmpcondition1342 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1344 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1348 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1350 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1352 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1365 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_opcmpcondition1367 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1369 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1373 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1375 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1377 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_66_in_opcmpcondition1413 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1415 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1417 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1453 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_opcmpcondition1455 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1457 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1461 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1463 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1465 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1476 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_opcmpcondition1478 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1480 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1484 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1486 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1488 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_103_in_opcmpcondition1523 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1525 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1529 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1537 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_opcmpcondition1539 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1541 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1545 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1547 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1549 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1557 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_opcmpcondition1559 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1561 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1565 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1567 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1569 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1577 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_68_in_opcmpcondition1579 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1581 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition1585 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1593 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_opcmpcondition1595 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1597 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1601 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_104_in_opcmpcondition1634 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1636 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1640 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1648 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_opcmpcondition1650 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1652 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1656 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1658 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1660 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1668 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_opcmpcondition1670 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1672 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1676 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1678 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1680 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1688 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_68_in_opcmpcondition1690 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1692 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition1696 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_73_in_constantcmp1734 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1736 = new BitSet(new long[]{0xC000000000000000L});
	public static final BitSet FOLLOW_trendconstant_in_constantcmp1740 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1774 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_constantcmp1776 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1778 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1782 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1784 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1786 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1788 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_constantcmp1790 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1792 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1796 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1798 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1800 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_72_in_constantcmp1828 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1830 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1834 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1868 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_constantcmp1870 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1872 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1876 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1878 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1880 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1882 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_constantcmp1884 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1886 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1890 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1892 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1894 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_79_in_constantcmp1923 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1925 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1929 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1963 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_constantcmp1965 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1967 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1971 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1973 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1975 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1977 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_constantcmp1979 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1981 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1985 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1987 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1989 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_83_in_constantcmp2018 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2020 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2024 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2058 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_constantcmp2060 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2062 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2066 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2068 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2070 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2072 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_constantcmp2074 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2076 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2080 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2082 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2084 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_94_in_presetcondition2116 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2156 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_presetcondition2158 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2160 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2164 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2166 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2168 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition2170 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2172 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2176 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2178 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2180 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_95_in_presetcondition2224 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2264 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_presetcondition2266 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2268 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2272 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2274 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2276 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition2278 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2280 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2284 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2286 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2288 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_75_in_presetcondition2331 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2333 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2337 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2339 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2381 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition2383 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2385 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2389 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2391 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2393 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2404 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition2406 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2408 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2412 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2414 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2416 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_76_in_presetcondition2453 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2455 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2459 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2461 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2503 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition2505 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2507 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2511 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2513 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2515 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2526 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition2528 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2530 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2534 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2536 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2538 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_67_in_presetcondition2582 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2584 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2588 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2630 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition2632 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2634 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2638 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2640 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2642 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2653 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_presetcondition2655 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2657 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2661 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2663 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2665 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition2711 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2713 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2717 = new BitSet(new long[]{0x0800000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2759 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_98_in_presetcondition2761 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2763 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2767 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2769 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2771 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2782 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_presetcondition2784 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2786 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2790 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2792 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2794 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_85_in_presetcondition2832 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2834 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2838 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2840 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2842 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2849 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_presetcondition2851 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2853 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2857 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2859 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2861 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2868 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition2870 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2872 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2876 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2878 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2880 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2887 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_presetcondition2889 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2891 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2895 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2897 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2899 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2906 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition2908 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2910 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition2914 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2920 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition2922 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2924 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition2928 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2934 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_99_in_presetcondition2936 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2938 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition2940 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2944 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition2946 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2950 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition2952 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2954 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_presetcondition2956 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2958 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition2960 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2964 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition2966 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2970 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition2972 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2978 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition2980 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2982 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition2984 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2988 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition2990 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2994 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition2996 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_86_in_presetcondition3047 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3049 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3053 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3055 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3057 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3064 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_presetcondition3066 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3068 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3072 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3074 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3076 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3083 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition3085 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3087 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3091 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3093 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3095 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3102 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_presetcondition3104 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3106 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3110 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3112 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3114 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3121 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition3123 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3125 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3129 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3135 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition3137 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3139 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3143 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3149 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_99_in_presetcondition3151 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3153 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3155 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3159 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3161 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3165 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3167 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3169 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_presetcondition3171 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3173 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3175 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3179 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3181 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3185 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3187 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3193 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3195 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3197 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3199 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3203 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3205 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3209 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3211 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_87_in_presetcondition3262 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3264 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3268 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3270 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3272 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3279 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_presetcondition3281 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3283 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3287 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3289 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3291 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3298 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition3300 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3302 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3306 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3308 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3310 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3317 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_presetcondition3319 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3321 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3325 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3327 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3329 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3336 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition3338 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3340 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3344 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3350 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition3352 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3354 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3358 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3364 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_99_in_presetcondition3366 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3368 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3370 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3374 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3376 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3380 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3382 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3384 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_presetcondition3386 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3388 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3390 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3394 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3396 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3400 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3402 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3408 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3410 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3412 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3414 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3418 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3420 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3424 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3426 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_88_in_presetcondition3477 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3479 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3483 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3485 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3487 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3494 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_presetcondition3496 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3498 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3502 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3504 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3506 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3513 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition3515 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3517 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3521 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3523 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3525 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3532 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_presetcondition3534 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3536 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3540 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3542 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3544 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3551 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition3553 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3555 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3559 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3565 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition3567 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3569 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3573 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3579 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_99_in_presetcondition3581 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3583 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3585 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3589 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3591 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3595 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3597 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3599 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_presetcondition3601 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3603 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3605 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3609 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3611 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3615 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3617 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3623 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3625 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3627 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3629 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3633 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3635 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3639 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3641 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_89_in_presetcondition3694 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3696 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3700 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3702 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3704 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3711 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_presetcondition3713 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3715 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3719 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3721 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3723 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3730 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition3732 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3734 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3738 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3740 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3742 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3749 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_presetcondition3751 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3753 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3757 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3759 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3761 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3768 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_99_in_presetcondition3770 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3772 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3774 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3778 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3780 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3784 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3786 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3792 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition3794 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3796 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3800 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_90_in_presetcondition3878 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3880 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3884 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3886 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3888 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3898 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_presetcondition3900 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3902 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3906 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3908 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3910 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3920 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition3922 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3924 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3928 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3930 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3932 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3942 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_presetcondition3944 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3946 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3950 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3952 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3954 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3964 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_99_in_presetcondition3966 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3968 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3970 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3974 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3976 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3980 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3982 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3992 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition3994 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3996 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4000 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_102_in_presetcondition4079 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4087 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_presetcondition4089 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4091 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4095 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4097 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4099 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4107 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition4109 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4111 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4115 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4117 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4119 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4127 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_presetcondition4129 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4131 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4135 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_105_in_presetcondition4171 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4179 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_presetcondition4181 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4183 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4187 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4189 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4191 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4199 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition4201 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4203 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4207 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4209 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4211 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4219 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_presetcondition4221 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4223 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4227 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_101_in_presetcondition4263 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4271 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_presetcondition4273 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4275 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4279 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4281 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4283 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4291 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition4293 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4295 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4299 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4301 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4303 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4311 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_presetcondition4313 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4315 = new BitSet(new long[]{0x1000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4319 = new BitSet(new long[]{0x0000000000000002L});
}
