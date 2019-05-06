// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2019-05-06 20:38:03
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
		"'makes a support break up spanning'", "'more than'", "'over'", "'override start shift with'", 
		"'reverses down'", "'reverses up'", "'slope within'", "'smoothed'", "'spanning'", 
		"'starting within'", "'tolerance'", "'trends down'", "'trends flat'", 
		"'trends like'", "'trends unlike'", "'trends up'", "'type'"
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:161:1: complete_expression : bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:161:21: (bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:4: bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift
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
			// AST REWRITE
			// elements: bearish_condition, fixed_start_shift, also_display, bullish_condition
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 163:90: -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:93: ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:1: bullish_condition : 'is bullish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression ;
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
		RewriteRuleTokenStream stream_87=new RewriteRuleTokenStream(adaptor,"token 87");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:19: ( 'is bullish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:2: 'is bullish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )*
			{
			string_literal4=(Token)match(input,87,FOLLOW_87_in_bullish_condition472);  
			stream_87.add(string_literal4);

			WhiteChar5=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition474);  
			stream_WhiteChar.add(WhiteChar5);

			pushFollow(FOLLOW_primary_expression_in_bullish_condition476);
			primary_expression6=primary_expression();
			state._fsp--;

			stream_primary_expression.add(primary_expression6.getTree());
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
					WhiteChar7=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition478);  
					stream_WhiteChar.add(WhiteChar7);

					}
					break;

				default :
					break loop1;
				}
			}

			SEMICOLUMN8=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_bullish_condition481);  
			stream_SEMICOLUMN.add(SEMICOLUMN8);

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
					WhiteChar9=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition483);  
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
					string_literal10=(Token)match(input,84,FOLLOW_84_in_bearish_condition499);  
					stream_84.add(string_literal10);

					WhiteChar11=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition501);  
					stream_WhiteChar.add(WhiteChar11);

					pushFollow(FOLLOW_primary_expression_in_bearish_condition503);
					primary_expression12=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression12.getTree());
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
							WhiteChar13=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition505);  
							stream_WhiteChar.add(WhiteChar13);

							}
							break;

						default :
							break loop3;
						}
					}

					SEMICOLUMN14=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_bearish_condition508);  
					stream_SEMICOLUMN.add(SEMICOLUMN14);

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
							WhiteChar15=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition510);  
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
					pushFollow(FOLLOW_bearish_not_bullish_in_bearish_condition520);
					bearish_not_bullish16=bearish_not_bullish(bcond);
					state._fsp--;

					stream_bearish_not_bullish.add(bearish_not_bullish16.getTree());
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
							WhiteChar17=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition523);  
							stream_WhiteChar.add(WhiteChar17);

							}
							break;

						default :
							break loop5;
						}
					}

					SEMICOLUMN18=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_bearish_condition526);  
					stream_SEMICOLUMN.add(SEMICOLUMN18);

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
							WhiteChar19=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition528);  
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
		RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:15: ( 'also display' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==64) ) {
				alt9=1;
			}
			else if ( (LA9_0==EOF||LA9_0==97) ) {
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
					string_literal20=(Token)match(input,64,FOLLOW_64_in_also_display545);  
					stream_64.add(string_literal20);

					WhiteChar21=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display547);  
					stream_WhiteChar.add(WhiteChar21);

					pushFollow(FOLLOW_primary_expression_in_also_display549);
					primary_expression22=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression22.getTree());
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
							WhiteChar23=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display551);  
							stream_WhiteChar.add(WhiteChar23);

							}
							break;

						default :
							break loop8;
						}
					}

					SEMICOLUMN24=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_also_display554);  
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
		RewriteRuleTokenStream stream_97=new RewriteRuleTokenStream(adaptor,"token 97");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:177:20: ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS SEMICOLUMN ->| -> ^( Number NumberToken[\"-1\"] ) )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==97) ) {
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:178:3: 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS SEMICOLUMN
					{
					string_literal25=(Token)match(input,97,FOLLOW_97_in_fixed_start_shift589);  
					stream_97.add(string_literal25);

					WhiteChar26=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift591);  
					stream_WhiteChar.add(WhiteChar26);

					pushFollow(FOLLOW_constant_in_fixed_start_shift595);
					fixedStartShift=constant();
					state._fsp--;

					stream_constant.add(fixedStartShift.getTree());
					WhiteChar27=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift597);  
					stream_WhiteChar.add(WhiteChar27);

					DAYS28=(Token)match(input,DAYS,FOLLOW_DAYS_in_fixed_start_shift599);  
					stream_DAYS.add(DAYS28);

					SEMICOLUMN29=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_fixed_start_shift601);  
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


	public static class bearish_not_bullish_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "bearish_not_bullish"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:182:1: bearish_not_bullish[CommonTree bcond] : 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ) ;
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
		RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:182:39: ( 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:2: 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			{
			string_literal30=(Token)match(input,83,FOLLOW_83_in_bearish_not_bullish630);  
			stream_83.add(string_literal30);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:3: ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:3: WhiteChar AND WhiteChar primary_expression
					{
					WhiteChar31=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish638);  
					stream_WhiteChar.add(WhiteChar31);

					AND32=(Token)match(input,AND,FOLLOW_AND_in_bearish_not_bullish640);  
					stream_AND.add(AND32);

					WhiteChar33=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish642);  
					stream_WhiteChar.add(WhiteChar33);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish644);
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
					// 185:46: -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:49: ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndBooleanMapCondition, "AndBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:74: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:109: ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_2);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:134: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:3: WhiteChar OR WhiteChar primary_expression
					{
					WhiteChar35=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish677);  
					stream_WhiteChar.add(WhiteChar35);

					OR36=(Token)match(input,OR,FOLLOW_OR_in_bearish_not_bullish679);  
					stream_OR.add(OR36);

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
					// 186:45: -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:48: ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrBooleanMapCondition, "OrBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:72: ^( String StringToken[\"\\\"TRUE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:106: ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_2);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:131: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:3: 
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
					// 187:3: -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:6: ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:31: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:1: primary_expression : and_expression ;
	public final ParameterizedIndicatorsParser.primary_expression_return primary_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.primary_expression_return retval = new ParameterizedIndicatorsParser.primary_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope and_expression39 =null;


		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:20: ( and_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:3: and_expression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_and_expression_in_primary_expression746);
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:1: and_expression : or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndBooleanMapCondition or_expression ( or_expression )* ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:16: ( or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndBooleanMapCondition or_expression ( or_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:3: or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )*
			{
			pushFollow(FOLLOW_or_expression_in_and_expression758);
			or_expression40=or_expression();
			state._fsp--;

			stream_or_expression.add(or_expression40.getTree());
			pushFollow(FOLLOW_lenient_in_and_expression762);
			lenientParam=lenient();
			state._fsp--;

			stream_lenient.add(lenientParam.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:38: ( WhiteChar AND WhiteChar or_expression )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:39: WhiteChar AND WhiteChar or_expression
					{
					WhiteChar41=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression765);  
					stream_WhiteChar.add(WhiteChar41);

					AND42=(Token)match(input,AND,FOLLOW_AND_in_and_expression767);  
					stream_AND.add(AND42);

					WhiteChar43=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression769);  
					stream_WhiteChar.add(WhiteChar43);

					pushFollow(FOLLOW_or_expression_in_and_expression771);
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
			// 196:79: -> ^( AndBooleanMapCondition or_expression ( or_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:82: ^( AndBooleanMapCondition or_expression ( or_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndBooleanMapCondition, "AndBooleanMapCondition"), root_1);
				adaptor.addChild(root_1, (lenientParam!=null?((CommonTree)lenientParam.getTree()):null));
				adaptor.addChild(root_1, stream_or_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:142: ( or_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:1: or_expression : matches_expression ( WhiteChar OR WhiteChar matches_expression )* -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) matches_expression ( matches_expression )* ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:15: ( matches_expression ( WhiteChar OR WhiteChar matches_expression )* -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) matches_expression ( matches_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:3: matches_expression ( WhiteChar OR WhiteChar matches_expression )*
			{
			pushFollow(FOLLOW_matches_expression_in_or_expression798);
			matches_expression45=matches_expression();
			state._fsp--;

			stream_matches_expression.add(matches_expression45.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:22: ( WhiteChar OR WhiteChar matches_expression )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:23: WhiteChar OR WhiteChar matches_expression
					{
					WhiteChar46=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression801);  
					stream_WhiteChar.add(WhiteChar46);

					OR47=(Token)match(input,OR,FOLLOW_OR_in_or_expression803);  
					stream_OR.add(OR47);

					WhiteChar48=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression805);  
					stream_WhiteChar.add(WhiteChar48);

					pushFollow(FOLLOW_matches_expression_in_or_expression807);
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
			// 199:67: -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) matches_expression ( matches_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:70: ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) matches_expression ( matches_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrBooleanMapCondition, "OrBooleanMapCondition"), root_1);
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:94: ^( String StringToken[\"\\\"TRUE\\\"\"] )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
				adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, stream_matches_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:147: ( matches_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:1: matches_expression : atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )? -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:20: ( atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )? -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:3: atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )?
			{
			pushFollow(FOLLOW_atom_in_matches_expression839);
			atom50=atom();
			state._fsp--;

			stream_atom.add(atom50.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:8: ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )?
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:9: WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom
					{
					WhiteChar51=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression842);  
					stream_WhiteChar.add(WhiteChar51);

					MATCHING52=(Token)match(input,MATCHING,FOLLOW_MATCHING_in_matches_expression844);  
					stream_MATCHING.add(MATCHING52);

					WhiteChar53=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression846);  
					stream_WhiteChar.add(WhiteChar53);

					char_literal54=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_matches_expression848);  
					stream_OPENSQRT.add(char_literal54);

					pushFollow(FOLLOW_constant_in_matches_expression850);
					constant55=constant();
					state._fsp--;

					stream_constant.add(constant55.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:51: ( ',' constant )*
					loop14:
					while (true) {
						int alt14=2;
						int LA14_0 = input.LA(1);
						if ( (LA14_0==COMMA) ) {
							alt14=1;
						}

						switch (alt14) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:52: ',' constant
							{
							char_literal56=(Token)match(input,COMMA,FOLLOW_COMMA_in_matches_expression853);  
							stream_COMMA.add(char_literal56);

							pushFollow(FOLLOW_constant_in_matches_expression855);
							constant57=constant();
							state._fsp--;

							stream_constant.add(constant57.getTree());
							}
							break;

						default :
							break loop14;
						}
					}

					char_literal58=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_matches_expression859);  
					stream_CLOSESQRT.add(char_literal58);

					WhiteChar59=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression861);  
					stream_WhiteChar.add(WhiteChar59);

					pushFollow(FOLLOW_atom_in_matches_expression863);
					atom60=atom();
					state._fsp--;

					stream_atom.add(atom60.getTree());
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
			// 202:88: -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:91: ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(MatchingBooleanMapCondition, "MatchingBooleanMapCondition"), root_1);
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:121: ( constant )*
				while ( stream_constant.hasNext() ) {
					adaptor.addChild(root_1, stream_constant.nextTree());
				}
				stream_constant.reset();

				adaptor.addChild(root_1, stream_atom.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:136: ( atom )?
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:1: atom : ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | NOT ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression ) | conjunctiontruthof );
	public final ParameterizedIndicatorsParser.atom_return atom() throws RecognitionException {
		ParameterizedIndicatorsParser.atom_return retval = new ParameterizedIndicatorsParser.atom_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal62=null;
		Token WhiteChar63=null;
		Token WhiteChar65=null;
		Token char_literal66=null;
		Token NOT67=null;
		Token WhiteChar68=null;
		Token char_literal69=null;
		Token WhiteChar70=null;
		Token WhiteChar72=null;
		Token char_literal73=null;
		ParserRuleReturnScope booleanhistory61 =null;
		ParserRuleReturnScope primary_expression64 =null;
		ParserRuleReturnScope primary_expression71 =null;
		ParserRuleReturnScope conjunctiontruthof74 =null;

		CommonTree char_literal62_tree=null;
		CommonTree WhiteChar63_tree=null;
		CommonTree WhiteChar65_tree=null;
		CommonTree char_literal66_tree=null;
		CommonTree NOT67_tree=null;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:6: ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | NOT ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression ) | conjunctiontruthof )
			int alt21=4;
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
			case TRUTHOF:
				{
				alt21=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 21, 0, input);
				throw nvae;
			}
			switch (alt21) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:3: booleanhistory
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_booleanhistory_in_atom891);
					booleanhistory61=booleanhistory();
					state._fsp--;

					adaptor.addChild(root_0, booleanhistory61.getTree());

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:3: '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					char_literal62=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom897);  
					stream_OPENPARENTEHSIS.add(char_literal62);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:7: ( WhiteChar )*
					loop16:
					while (true) {
						int alt16=2;
						int LA16_0 = input.LA(1);
						if ( (LA16_0==WhiteChar) ) {
							alt16=1;
						}

						switch (alt16) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:7: WhiteChar
							{
							WhiteChar63=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom899);  
							stream_WhiteChar.add(WhiteChar63);

							}
							break;

						default :
							break loop16;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom902);
					primary_expression64=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression64.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:37: ( WhiteChar )*
					loop17:
					while (true) {
						int alt17=2;
						int LA17_0 = input.LA(1);
						if ( (LA17_0==WhiteChar) ) {
							alt17=1;
						}

						switch (alt17) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:37: WhiteChar
							{
							WhiteChar65=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom904);  
							stream_WhiteChar.add(WhiteChar65);

							}
							break;

						default :
							break loop17;
						}
					}

					char_literal66=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom907);  
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
					// 206:52: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:3: NOT ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					NOT67=(Token)match(input,NOT,FOLLOW_NOT_in_atom917);  
					stream_NOT.add(NOT67);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:7: ( WhiteChar )*
					loop18:
					while (true) {
						int alt18=2;
						int LA18_0 = input.LA(1);
						if ( (LA18_0==WhiteChar) ) {
							alt18=1;
						}

						switch (alt18) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:7: WhiteChar
							{
							WhiteChar68=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom919);  
							stream_WhiteChar.add(WhiteChar68);

							}
							break;

						default :
							break loop18;
						}
					}

					char_literal69=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom922);  
					stream_OPENPARENTEHSIS.add(char_literal69);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:22: ( WhiteChar )*
					loop19:
					while (true) {
						int alt19=2;
						int LA19_0 = input.LA(1);
						if ( (LA19_0==WhiteChar) ) {
							alt19=1;
						}

						switch (alt19) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:22: WhiteChar
							{
							WhiteChar70=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom924);  
							stream_WhiteChar.add(WhiteChar70);

							}
							break;

						default :
							break loop19;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom927);
					primary_expression71=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression71.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:52: ( WhiteChar )*
					loop20:
					while (true) {
						int alt20=2;
						int LA20_0 = input.LA(1);
						if ( (LA20_0==WhiteChar) ) {
							alt20=1;
						}

						switch (alt20) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:52: WhiteChar
							{
							WhiteChar72=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom929);  
							stream_WhiteChar.add(WhiteChar72);

							}
							break;

						default :
							break loop20;
						}
					}

					char_literal73=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom932);  
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
					// 207:67: -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:70: ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:95: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:3: conjunctiontruthof
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_conjunctiontruthof_in_atom953);
					conjunctiontruthof74=conjunctiontruthof();
					state._fsp--;

					adaptor.addChild(root_0, conjunctiontruthof74.getTree());

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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:1: conjunctiontruthof : TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']' -> ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* ) ;
	public final ParameterizedIndicatorsParser.conjunctiontruthof_return conjunctiontruthof() throws RecognitionException {
		ParameterizedIndicatorsParser.conjunctiontruthof_return retval = new ParameterizedIndicatorsParser.conjunctiontruthof_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token TRUTHOF75=null;
		Token WhiteChar76=null;
		Token COMMA78=null;
		Token WhiteChar79=null;
		Token WhiteChar81=null;
		Token string_literal82=null;
		Token WhiteChar83=null;
		Token char_literal84=null;
		Token char_literal85=null;
		Token char_literal86=null;
		ParserRuleReturnScope min =null;
		ParserRuleReturnScope max =null;
		ParserRuleReturnScope primary_expression77 =null;
		ParserRuleReturnScope primary_expression80 =null;

		CommonTree TRUTHOF75_tree=null;
		CommonTree WhiteChar76_tree=null;
		CommonTree COMMA78_tree=null;
		CommonTree WhiteChar79_tree=null;
		CommonTree WhiteChar81_tree=null;
		CommonTree string_literal82_tree=null;
		CommonTree WhiteChar83_tree=null;
		CommonTree char_literal84_tree=null;
		CommonTree char_literal85_tree=null;
		CommonTree char_literal86_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_OPENSQRT=new RewriteRuleTokenStream(adaptor,"token OPENSQRT");
		RewriteRuleTokenStream stream_CLOSESQRT=new RewriteRuleTokenStream(adaptor,"token CLOSESQRT");
		RewriteRuleTokenStream stream_TRUTHOF=new RewriteRuleTokenStream(adaptor,"token TRUTHOF");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:20: ( TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']' -> ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:3: TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']'
			{
			TRUTHOF75=(Token)match(input,TRUTHOF,FOLLOW_TRUTHOF_in_conjunctiontruthof965);  
			stream_TRUTHOF.add(TRUTHOF75);

			WhiteChar76=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof967);  
			stream_WhiteChar.add(WhiteChar76);

			pushFollow(FOLLOW_primary_expression_in_conjunctiontruthof969);
			primary_expression77=primary_expression();
			state._fsp--;

			stream_primary_expression.add(primary_expression77.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:40: ( COMMA WhiteChar primary_expression )*
			loop22:
			while (true) {
				int alt22=2;
				int LA22_0 = input.LA(1);
				if ( (LA22_0==COMMA) ) {
					alt22=1;
				}

				switch (alt22) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:41: COMMA WhiteChar primary_expression
					{
					COMMA78=(Token)match(input,COMMA,FOLLOW_COMMA_in_conjunctiontruthof972);  
					stream_COMMA.add(COMMA78);

					WhiteChar79=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof974);  
					stream_WhiteChar.add(WhiteChar79);

					pushFollow(FOLLOW_primary_expression_in_conjunctiontruthof976);
					primary_expression80=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression80.getTree());
					}
					break;

				default :
					break loop22;
				}
			}

			WhiteChar81=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof980);  
			stream_WhiteChar.add(WhiteChar81);

			string_literal82=(Token)match(input,88,FOLLOW_88_in_conjunctiontruthof982);  
			stream_88.add(string_literal82);

			WhiteChar83=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof984);  
			stream_WhiteChar.add(WhiteChar83);

			char_literal84=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_conjunctiontruthof986);  
			stream_OPENSQRT.add(char_literal84);

			pushFollow(FOLLOW_constant_in_conjunctiontruthof990);
			min=constant();
			state._fsp--;

			stream_constant.add(min.getTree());
			char_literal85=(Token)match(input,COMMA,FOLLOW_COMMA_in_conjunctiontruthof992);  
			stream_COMMA.add(char_literal85);

			pushFollow(FOLLOW_constant_in_conjunctiontruthof996);
			max=constant();
			state._fsp--;

			stream_constant.add(max.getTree());
			char_literal86=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_conjunctiontruthof998);  
			stream_CLOSESQRT.add(char_literal86);

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
			// 212:4: -> ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:7: ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TruthOfCondition, "TruthOfCondition"), root_1);
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:26: ^( String StringToken[\"\\\"TRUE\\\"\"] )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
				adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, (min!=null?((CommonTree)min.getTree()):null));
				adaptor.addChild(root_1, (max!=null?((CommonTree)max.getTree()):null));
				adaptor.addChild(root_1, stream_primary_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:103: ( primary_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:1: booleanhistory : firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) ;
	public final ParameterizedIndicatorsParser.booleanhistory_return booleanhistory() throws RecognitionException {
		ParameterizedIndicatorsParser.booleanhistory_return retval = new ParameterizedIndicatorsParser.booleanhistory_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar87=null;
		ParserRuleReturnScope firstOp =null;
		ParserRuleReturnScope presetcondition88 =null;
		ParserRuleReturnScope opcmpcondition89 =null;
		ParserRuleReturnScope constantcmp90 =null;

		CommonTree WhiteChar87_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_presetcondition=new RewriteRuleSubtreeStream(adaptor,"rule presetcondition");
		RewriteRuleSubtreeStream stream_constantcmp=new RewriteRuleSubtreeStream(adaptor,"rule constantcmp");
		RewriteRuleSubtreeStream stream_opcmpcondition=new RewriteRuleSubtreeStream(adaptor,"rule opcmpcondition");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:16: (firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:2: firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			{
			pushFollow(FOLLOW_operand_in_booleanhistory1037);
			firstOp=operand();
			state._fsp--;

			stream_operand.add(firstOp.getTree());
			WhiteChar87=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_booleanhistory1039);  
			stream_WhiteChar.add(WhiteChar87);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:28: ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			int alt23=3;
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
			case 98:
			case 99:
			case 105:
			case 106:
			case 109:
				{
				alt23=1;
				}
				break;
			case 67:
			case 69:
			case 74:
			case 81:
			case 85:
			case 107:
			case 108:
				{
				alt23=2;
				}
				break;
			case 75:
			case 76:
			case 82:
			case 86:
				{
				alt23=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}
			switch (alt23) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:3: presetcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_presetcondition_in_booleanhistory1045);
					presetcondition88=presetcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_presetcondition.add(presetcondition88.getTree());
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
					// 216:34: -> presetcondition
					{
						adaptor.addChild(root_0, stream_presetcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:3: opcmpcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_opcmpcondition_in_booleanhistory1056);
					opcmpcondition89=opcmpcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_opcmpcondition.add(opcmpcondition89.getTree());
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
					// 217:33: -> opcmpcondition
					{
						adaptor.addChild(root_0, stream_opcmpcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:3: constantcmp[$firstOp.tree]
					{
					pushFollow(FOLLOW_constantcmp_in_booleanhistory1067);
					constantcmp90=constantcmp((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_constantcmp.add(constantcmp90.getTree());
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
					// 218:30: -> constantcmp
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:1: operand : ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation );
	public final ParameterizedIndicatorsParser.operand_return operand() throws RecognitionException {
		ParameterizedIndicatorsParser.operand_return retval = new ParameterizedIndicatorsParser.operand_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token opName=null;
		Token HistoricalData91=null;

		CommonTree opName_tree=null;
		CommonTree HistoricalData91_tree=null;
		RewriteRuleTokenStream stream_Operation=new RewriteRuleTokenStream(adaptor,"token Operation");
		RewriteRuleTokenStream stream_HistoricalData=new RewriteRuleTokenStream(adaptor,"token HistoricalData");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:9: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation )
			int alt24=2;
			int LA24_0 = input.LA(1);
			if ( (LA24_0==HistoricalData) ) {
				alt24=1;
			}
			else if ( (LA24_0==Operation) ) {
				alt24=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 24, 0, input);
				throw nvae;
			}

			switch (alt24) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:11: HistoricalData
					{
					HistoricalData91=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_operand1083);  
					stream_HistoricalData.add(HistoricalData91);

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
					// 221:26: -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:29: ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:46: ^( OperationOutput HistoricalData )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
						adaptor.addChild(root_2, stream_HistoricalData.nextNode());
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:80: ^( String StringToken[\"\\\"THIS\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:117: opName= Operation
					{
					opName=(Token)match(input,Operation,FOLLOW_Operation_in_operand1110);  
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
					// 221:171: -> Operation
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:1: constant : ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) );
	public final ParameterizedIndicatorsParser.constant_return constant() throws RecognitionException {
		ParameterizedIndicatorsParser.constant_return retval = new ParameterizedIndicatorsParser.constant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NumberToken92=null;
		Token string_literal93=null;

		CommonTree NumberToken92_tree=null;
		CommonTree string_literal93_tree=null;
		RewriteRuleTokenStream stream_NumberToken=new RewriteRuleTokenStream(adaptor,"token NumberToken");
		RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:10: ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) )
			int alt25=2;
			int LA25_0 = input.LA(1);
			if ( (LA25_0==NumberToken) ) {
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:13: NumberToken
					{
					NumberToken92=(Token)match(input,NumberToken,FOLLOW_NumberToken_in_constant1125);  
					stream_NumberToken.add(NumberToken92);

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
					// 223:25: -> ^( Number NumberToken )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:28: ^( Number NumberToken )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:52: 'NaN'
					{
					string_literal93=(Token)match(input,62,FOLLOW_62_in_constant1137);  
					stream_62.add(string_literal93);

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
					// 223:58: -> ^( Number NumberToken[\"NaN\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:61: ^( Number NumberToken[\"NaN\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:1: stringconstant : StringToken -> ^( String StringToken ) ;
	public final ParameterizedIndicatorsParser.stringconstant_return stringconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.stringconstant_return retval = new ParameterizedIndicatorsParser.stringconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token StringToken94=null;

		CommonTree StringToken94_tree=null;
		RewriteRuleTokenStream stream_StringToken=new RewriteRuleTokenStream(adaptor,"token StringToken");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:16: ( StringToken -> ^( String StringToken ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:18: StringToken
			{
			StringToken94=(Token)match(input,StringToken,FOLLOW_StringToken_in_stringconstant1153);  
			stream_StringToken.add(StringToken94);

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
			// 224:30: -> ^( String StringToken )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:33: ^( String StringToken )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:1: trendconstant : ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) );
	public final ParameterizedIndicatorsParser.trendconstant_return trendconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.trendconstant_return retval = new ParameterizedIndicatorsParser.trendconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal95=null;
		Token string_literal96=null;

		CommonTree string_literal95_tree=null;
		CommonTree string_literal96_tree=null;
		RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
		RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:15: ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) )
			int alt26=2;
			int LA26_0 = input.LA(1);
			if ( (LA26_0==66) ) {
				alt26=1;
			}
			else if ( (LA26_0==65) ) {
				alt26=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 26, 0, input);
				throw nvae;
			}

			switch (alt26) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:17: 'bullish'
					{
					string_literal95=(Token)match(input,66,FOLLOW_66_in_trendconstant1168);  
					stream_66.add(string_literal95);

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
					// 225:27: -> ^( String StringToken[\"\\\"bullish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:30: ^( String StringToken[\"\\\"bullish\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:69: 'bearish'
					{
					string_literal96=(Token)match(input,65,FOLLOW_65_in_trendconstant1181);  
					stream_65.add(string_literal96);

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
					// 225:79: -> ^( String StringToken[\"\\\"bearish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:82: ^( String StringToken[\"\\\"bearish\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:1: lenient : ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ;
	public final ParameterizedIndicatorsParser.lenient_return lenient() throws RecognitionException {
		ParameterizedIndicatorsParser.lenient_return retval = new ParameterizedIndicatorsParser.lenient_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar97=null;
		Token LENIENT98=null;

		CommonTree WhiteChar97_tree=null;
		CommonTree LENIENT98_tree=null;
		RewriteRuleTokenStream stream_LENIENT=new RewriteRuleTokenStream(adaptor,"token LENIENT");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:9: ( ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			int alt27=2;
			int LA27_0 = input.LA(1);
			if ( (LA27_0==WhiteChar) ) {
				int LA27_1 = input.LA(2);
				if ( (LA27_1==LENIENT) ) {
					alt27=1;
				}
				else if ( (LA27_1==AND||LA27_1==CLOSEPARENTEHSIS||LA27_1==SEMICOLUMN||LA27_1==WhiteChar||LA27_1==88) ) {
					alt27=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 27, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA27_0==CLOSEPARENTEHSIS||LA27_0==COMMA||LA27_0==SEMICOLUMN) ) {
				alt27=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 27, 0, input);
				throw nvae;
			}

			switch (alt27) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:12: WhiteChar LENIENT
					{
					WhiteChar97=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_lenient1198);  
					stream_WhiteChar.add(WhiteChar97);

					LENIENT98=(Token)match(input,LENIENT,FOLLOW_LENIENT_in_lenient1200);  
					stream_LENIENT.add(LENIENT98);

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
					// 226:30: -> ^( String StringToken[\"\\\"TRUE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:33: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:69: 
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
					// 226:69: -> ^( String StringToken[\"\\\"FALSE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:72: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:1: opcmpcondition[CommonTree firstOp] : ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )? | ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) );
	public final ParameterizedIndicatorsParser.opcmpcondition_return opcmpcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.opcmpcondition_return retval = new ParameterizedIndicatorsParser.opcmpcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal99=null;
		Token WhiteChar100=null;
		Token WhiteChar101=null;
		Token string_literal102=null;
		Token WhiteChar103=null;
		Token WhiteChar104=null;
		Token DAYS105=null;
		Token WhiteChar106=null;
		Token string_literal107=null;
		Token WhiteChar108=null;
		Token PERCENT109=null;
		Token string_literal110=null;
		Token WhiteChar111=null;
		Token WhiteChar112=null;
		Token string_literal113=null;
		Token WhiteChar114=null;
		Token WhiteChar115=null;
		Token DAYS116=null;
		Token WhiteChar117=null;
		Token string_literal118=null;
		Token WhiteChar119=null;
		Token PERCENT120=null;
		Token string_literal121=null;
		Token WhiteChar122=null;
		Token WhiteChar123=null;
		Token string_literal124=null;
		Token WhiteChar125=null;
		Token WhiteChar126=null;
		Token DAYS127=null;
		Token WhiteChar128=null;
		Token string_literal129=null;
		Token WhiteChar130=null;
		Token PERCENT131=null;
		Token string_literal132=null;
		Token WhiteChar133=null;
		Token WhiteChar134=null;
		Token string_literal135=null;
		Token WhiteChar136=null;
		Token WhiteChar137=null;
		Token DAYS138=null;
		Token WhiteChar139=null;
		Token string_literal140=null;
		Token WhiteChar141=null;
		Token WhiteChar142=null;
		Token DAYS143=null;
		Token WhiteChar144=null;
		Token string_literal145=null;
		Token WhiteChar146=null;
		Token PERCENT147=null;
		Token WhiteChar148=null;
		Token string_literal149=null;
		Token WhiteChar150=null;
		Token PERCENT151=null;
		Token WhiteChar152=null;
		Token string_literal153=null;
		Token WhiteChar154=null;
		Token WhiteChar155=null;
		Token string_literal156=null;
		Token WhiteChar157=null;
		Token WhiteChar158=null;
		Token DAYS159=null;
		Token WhiteChar160=null;
		Token string_literal161=null;
		Token WhiteChar162=null;
		Token WhiteChar163=null;
		Token DAYS164=null;
		Token WhiteChar165=null;
		Token string_literal166=null;
		Token WhiteChar167=null;
		Token PERCENT168=null;
		Token WhiteChar169=null;
		Token string_literal170=null;
		Token WhiteChar171=null;
		Token PERCENT172=null;
		Token WhiteChar173=null;
		Token string_literal174=null;
		Token WhiteChar175=null;
		Token WhiteChar176=null;
		Token string_literal177=null;
		Token WhiteChar178=null;
		Token WhiteChar179=null;
		Token DAYS180=null;
		Token WhiteChar181=null;
		Token string_literal182=null;
		Token WhiteChar183=null;
		Token WhiteChar184=null;
		Token DAYS185=null;
		Token WhiteChar186=null;
		Token string_literal187=null;
		Token WhiteChar188=null;
		Token WhiteChar189=null;
		Token string_literal190=null;
		Token WhiteChar191=null;
		Token string_literal192=null;
		Token WhiteChar193=null;
		Token WhiteChar194=null;
		Token string_literal195=null;
		Token WhiteChar196=null;
		Token WhiteChar197=null;
		Token DAYS198=null;
		Token WhiteChar199=null;
		Token string_literal200=null;
		Token WhiteChar201=null;
		Token WhiteChar202=null;
		Token DAYS203=null;
		Token WhiteChar204=null;
		Token string_literal205=null;
		Token WhiteChar206=null;
		ParserRuleReturnScope secondOp =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope epsilon =null;
		ParserRuleReturnScope spanningNbDays =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope adaptiveRate =null;
		ParserRuleReturnScope adaptive =null;
		ParserRuleReturnScope direction =null;

		CommonTree string_literal99_tree=null;
		CommonTree WhiteChar100_tree=null;
		CommonTree WhiteChar101_tree=null;
		CommonTree string_literal102_tree=null;
		CommonTree WhiteChar103_tree=null;
		CommonTree WhiteChar104_tree=null;
		CommonTree DAYS105_tree=null;
		CommonTree WhiteChar106_tree=null;
		CommonTree string_literal107_tree=null;
		CommonTree WhiteChar108_tree=null;
		CommonTree PERCENT109_tree=null;
		CommonTree string_literal110_tree=null;
		CommonTree WhiteChar111_tree=null;
		CommonTree WhiteChar112_tree=null;
		CommonTree string_literal113_tree=null;
		CommonTree WhiteChar114_tree=null;
		CommonTree WhiteChar115_tree=null;
		CommonTree DAYS116_tree=null;
		CommonTree WhiteChar117_tree=null;
		CommonTree string_literal118_tree=null;
		CommonTree WhiteChar119_tree=null;
		CommonTree PERCENT120_tree=null;
		CommonTree string_literal121_tree=null;
		CommonTree WhiteChar122_tree=null;
		CommonTree WhiteChar123_tree=null;
		CommonTree string_literal124_tree=null;
		CommonTree WhiteChar125_tree=null;
		CommonTree WhiteChar126_tree=null;
		CommonTree DAYS127_tree=null;
		CommonTree WhiteChar128_tree=null;
		CommonTree string_literal129_tree=null;
		CommonTree WhiteChar130_tree=null;
		CommonTree PERCENT131_tree=null;
		CommonTree string_literal132_tree=null;
		CommonTree WhiteChar133_tree=null;
		CommonTree WhiteChar134_tree=null;
		CommonTree string_literal135_tree=null;
		CommonTree WhiteChar136_tree=null;
		CommonTree WhiteChar137_tree=null;
		CommonTree DAYS138_tree=null;
		CommonTree WhiteChar139_tree=null;
		CommonTree string_literal140_tree=null;
		CommonTree WhiteChar141_tree=null;
		CommonTree WhiteChar142_tree=null;
		CommonTree DAYS143_tree=null;
		CommonTree WhiteChar144_tree=null;
		CommonTree string_literal145_tree=null;
		CommonTree WhiteChar146_tree=null;
		CommonTree PERCENT147_tree=null;
		CommonTree WhiteChar148_tree=null;
		CommonTree string_literal149_tree=null;
		CommonTree WhiteChar150_tree=null;
		CommonTree PERCENT151_tree=null;
		CommonTree WhiteChar152_tree=null;
		CommonTree string_literal153_tree=null;
		CommonTree WhiteChar154_tree=null;
		CommonTree WhiteChar155_tree=null;
		CommonTree string_literal156_tree=null;
		CommonTree WhiteChar157_tree=null;
		CommonTree WhiteChar158_tree=null;
		CommonTree DAYS159_tree=null;
		CommonTree WhiteChar160_tree=null;
		CommonTree string_literal161_tree=null;
		CommonTree WhiteChar162_tree=null;
		CommonTree WhiteChar163_tree=null;
		CommonTree DAYS164_tree=null;
		CommonTree WhiteChar165_tree=null;
		CommonTree string_literal166_tree=null;
		CommonTree WhiteChar167_tree=null;
		CommonTree PERCENT168_tree=null;
		CommonTree WhiteChar169_tree=null;
		CommonTree string_literal170_tree=null;
		CommonTree WhiteChar171_tree=null;
		CommonTree PERCENT172_tree=null;
		CommonTree WhiteChar173_tree=null;
		CommonTree string_literal174_tree=null;
		CommonTree WhiteChar175_tree=null;
		CommonTree WhiteChar176_tree=null;
		CommonTree string_literal177_tree=null;
		CommonTree WhiteChar178_tree=null;
		CommonTree WhiteChar179_tree=null;
		CommonTree DAYS180_tree=null;
		CommonTree WhiteChar181_tree=null;
		CommonTree string_literal182_tree=null;
		CommonTree WhiteChar183_tree=null;
		CommonTree WhiteChar184_tree=null;
		CommonTree DAYS185_tree=null;
		CommonTree WhiteChar186_tree=null;
		CommonTree string_literal187_tree=null;
		CommonTree WhiteChar188_tree=null;
		CommonTree WhiteChar189_tree=null;
		CommonTree string_literal190_tree=null;
		CommonTree WhiteChar191_tree=null;
		CommonTree string_literal192_tree=null;
		CommonTree WhiteChar193_tree=null;
		CommonTree WhiteChar194_tree=null;
		CommonTree string_literal195_tree=null;
		CommonTree WhiteChar196_tree=null;
		CommonTree WhiteChar197_tree=null;
		CommonTree DAYS198_tree=null;
		CommonTree WhiteChar199_tree=null;
		CommonTree string_literal200_tree=null;
		CommonTree WhiteChar201_tree=null;
		CommonTree WhiteChar202_tree=null;
		CommonTree DAYS203_tree=null;
		CommonTree WhiteChar204_tree=null;
		CommonTree string_literal205_tree=null;
		CommonTree WhiteChar206_tree=null;
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_102=new RewriteRuleTokenStream(adaptor,"token 102");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_107=new RewriteRuleTokenStream(adaptor,"token 107");
		RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
		RewriteRuleTokenStream stream_108=new RewriteRuleTokenStream(adaptor,"token 108");
		RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:37: ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )? | ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) )
			int alt35=7;
			switch ( input.LA(1) ) {
			case 81:
				{
				alt35=1;
				}
				break;
			case 85:
				{
				alt35=2;
				}
				break;
			case 74:
				{
				alt35=3;
				}
				break;
			case 67:
				{
				alt35=4;
				}
				break;
			case 69:
				{
				alt35=5;
				}
				break;
			case 107:
				{
				alt35=6;
				}
				break;
			case 108:
				{
				alt35=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 35, 0, input);
				throw nvae;
			}
			switch (alt35) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:4: 'is above historical' WhiteChar secondOp= operand
					{
					string_literal99=(Token)match(input,81,FOLLOW_81_in_opcmpcondition1237);  
					stream_81.add(string_literal99);

					WhiteChar100=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1239);  
					stream_WhiteChar.add(WhiteChar100);

					pushFollow(FOLLOW_operand_in_opcmpcondition1243);
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
					// 231:53: -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:56: ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupDoubleMapCondition, "SupDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:80: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:107: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )?
					int alt28=2;
					int LA28_0 = input.LA(1);
					if ( (LA28_0==WhiteChar) ) {
						int LA28_1 = input.LA(2);
						if ( (LA28_1==77) ) {
							alt28=1;
						}
					}
					switch (alt28) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:5: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar101=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1274);  
							stream_WhiteChar.add(WhiteChar101);

							string_literal102=(Token)match(input,77,FOLLOW_77_in_opcmpcondition1276);  
							stream_77.add(string_literal102);

							WhiteChar103=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1278);  
							stream_WhiteChar.add(WhiteChar103);

							pushFollow(FOLLOW_constant_in_opcmpcondition1282);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar104=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1284);  
							stream_WhiteChar.add(WhiteChar104);

							DAYS105=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1286);  
							stream_DAYS.add(DAYS105);

							WhiteChar106=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1288);  
							stream_WhiteChar.add(WhiteChar106);

							string_literal107=(Token)match(input,73,FOLLOW_73_in_opcmpcondition1290);  
							stream_73.add(string_literal107);

							WhiteChar108=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1292);  
							stream_WhiteChar.add(WhiteChar108);

							pushFollow(FOLLOW_constant_in_opcmpcondition1296);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT109=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1298);  
							stream_PERCENT.add(PERCENT109);

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
							// 233:5: -> ^( SupDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:8: ^( SupDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:4: 'is below historical' WhiteChar secondOp= operand
					{
					string_literal110=(Token)match(input,85,FOLLOW_85_in_opcmpcondition1330);  
					stream_85.add(string_literal110);

					WhiteChar111=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1332);  
					stream_WhiteChar.add(WhiteChar111);

					pushFollow(FOLLOW_operand_in_opcmpcondition1336);
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
					// 235:53: -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:56: ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfDoubleMapCondition, "InfDoubleMapCondition"), root_1);
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )?
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
							WhiteChar112=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1367);  
							stream_WhiteChar.add(WhiteChar112);

							string_literal113=(Token)match(input,77,FOLLOW_77_in_opcmpcondition1369);  
							stream_77.add(string_literal113);

							WhiteChar114=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1371);  
							stream_WhiteChar.add(WhiteChar114);

							pushFollow(FOLLOW_constant_in_opcmpcondition1375);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar115=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1377);  
							stream_WhiteChar.add(WhiteChar115);

							DAYS116=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1379);  
							stream_DAYS.add(DAYS116);

							WhiteChar117=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1381);  
							stream_WhiteChar.add(WhiteChar117);

							string_literal118=(Token)match(input,73,FOLLOW_73_in_opcmpcondition1383);  
							stream_73.add(string_literal118);

							WhiteChar119=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1385);  
							stream_WhiteChar.add(WhiteChar119);

							pushFollow(FOLLOW_constant_in_opcmpcondition1389);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT120=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1391);  
							stream_PERCENT.add(PERCENT120);

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
							// 237:5: -> ^( InfDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:8: ^( InfDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:4: 'equals historical' WhiteChar secondOp= operand
					{
					string_literal121=(Token)match(input,74,FOLLOW_74_in_opcmpcondition1422);  
					stream_74.add(string_literal121);

					WhiteChar122=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1424);  
					stream_WhiteChar.add(WhiteChar122);

					pushFollow(FOLLOW_operand_in_opcmpcondition1428);
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
					// 239:51: -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:54: ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualDoubleMapCondition, "EqualDoubleMapCondition"), root_1);
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )?
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
							WhiteChar123=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1459);  
							stream_WhiteChar.add(WhiteChar123);

							string_literal124=(Token)match(input,77,FOLLOW_77_in_opcmpcondition1461);  
							stream_77.add(string_literal124);

							WhiteChar125=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1463);  
							stream_WhiteChar.add(WhiteChar125);

							pushFollow(FOLLOW_constant_in_opcmpcondition1467);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar126=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1469);  
							stream_WhiteChar.add(WhiteChar126);

							DAYS127=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1471);  
							stream_DAYS.add(DAYS127);

							WhiteChar128=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1473);  
							stream_WhiteChar.add(WhiteChar128);

							string_literal129=(Token)match(input,73,FOLLOW_73_in_opcmpcondition1475);  
							stream_73.add(string_literal129);

							WhiteChar130=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1477);  
							stream_WhiteChar.add(WhiteChar130);

							pushFollow(FOLLOW_constant_in_opcmpcondition1481);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT131=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1483);  
							stream_PERCENT.add(PERCENT131);

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
							// 241:5: -> ^( EqualDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:8: ^( EqualDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:3: ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:3: ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:4: 'crosses down historical' WhiteChar secondOp= operand
					{
					string_literal132=(Token)match(input,67,FOLLOW_67_in_opcmpcondition1516);  
					stream_67.add(string_literal132);

					WhiteChar133=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1518);  
					stream_WhiteChar.add(WhiteChar133);

					pushFollow(FOLLOW_operand_in_opcmpcondition1522);
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
					// 246:4: -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:7: ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:37: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:66: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:95: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:124: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:3: ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )?
					int alt32=2;
					int LA32_0 = input.LA(1);
					if ( (LA32_0==WhiteChar) ) {
						int LA32_1 = input.LA(2);
						if ( (LA32_1==102) ) {
							alt32=1;
						}
					}
					switch (alt32) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )?
							{
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:5: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar134=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1576);  
							stream_WhiteChar.add(WhiteChar134);

							string_literal135=(Token)match(input,102,FOLLOW_102_in_opcmpcondition1578);  
							stream_102.add(string_literal135);

							WhiteChar136=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1580);  
							stream_WhiteChar.add(WhiteChar136);

							pushFollow(FOLLOW_constant_in_opcmpcondition1584);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar137=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1586);  
							stream_WhiteChar.add(WhiteChar137);

							DAYS138=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1588);  
							stream_DAYS.add(DAYS138);

							WhiteChar139=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1590);  
							stream_WhiteChar.add(WhiteChar139);

							string_literal140=(Token)match(input,96,FOLLOW_96_in_opcmpcondition1592);  
							stream_96.add(string_literal140);

							WhiteChar141=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1594);  
							stream_WhiteChar.add(WhiteChar141);

							pushFollow(FOLLOW_constant_in_opcmpcondition1598);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar142=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1600);  
							stream_WhiteChar.add(WhiteChar142);

							DAYS143=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1602);  
							stream_DAYS.add(DAYS143);

							WhiteChar144=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1604);  
							stream_WhiteChar.add(WhiteChar144);

							string_literal145=(Token)match(input,73,FOLLOW_73_in_opcmpcondition1606);  
							stream_73.add(string_literal145);

							WhiteChar146=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1608);  
							stream_WhiteChar.add(WhiteChar146);

							pushFollow(FOLLOW_constant_in_opcmpcondition1612);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT147=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1614);  
							stream_PERCENT.add(PERCENT147);

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
							// 249:5: -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:8: ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:96: ^( Number NumberToken[\"0.0\"] )
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

							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:4: ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )?
							int alt31=2;
							int LA31_0 = input.LA(1);
							if ( (LA31_0==WhiteChar) ) {
								int LA31_1 = input.LA(2);
								if ( (LA31_1==63) ) {
									alt31=1;
								}
							}
							switch (alt31) {
								case 1 :
									// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:5: WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom
									{
									WhiteChar148=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1654);  
									stream_WhiteChar.add(WhiteChar148);

									string_literal149=(Token)match(input,63,FOLLOW_63_in_opcmpcondition1656);  
									stream_63.add(string_literal149);

									WhiteChar150=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1658);  
									stream_WhiteChar.add(WhiteChar150);

									pushFollow(FOLLOW_constant_in_opcmpcondition1662);
									adaptiveRate=constant();
									state._fsp--;

									stream_constant.add(adaptiveRate.getTree());
									PERCENT151=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1664);  
									stream_PERCENT.add(PERCENT151);

									WhiteChar152=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1666);  
									stream_WhiteChar.add(WhiteChar152);

									pushFollow(FOLLOW_atom_in_opcmpcondition1670);
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
									// 252:5: -> ^( CrossDownDoubleMapCondition )
									{
										// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:8: ^( CrossDownDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:3: ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:3: ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:4: 'crosses up historical' WhiteChar secondOp= operand
					{
					string_literal153=(Token)match(input,69,FOLLOW_69_in_opcmpcondition1713);  
					stream_69.add(string_literal153);

					WhiteChar154=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1715);  
					stream_WhiteChar.add(WhiteChar154);

					pushFollow(FOLLOW_operand_in_opcmpcondition1719);
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
					// 256:4: -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:7: ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:35: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:64: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:93: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:122: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:3: ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )?
					int alt34=2;
					int LA34_0 = input.LA(1);
					if ( (LA34_0==WhiteChar) ) {
						int LA34_1 = input.LA(2);
						if ( (LA34_1==102) ) {
							alt34=1;
						}
					}
					switch (alt34) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )?
							{
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:5: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar155=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1773);  
							stream_WhiteChar.add(WhiteChar155);

							string_literal156=(Token)match(input,102,FOLLOW_102_in_opcmpcondition1775);  
							stream_102.add(string_literal156);

							WhiteChar157=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1777);  
							stream_WhiteChar.add(WhiteChar157);

							pushFollow(FOLLOW_constant_in_opcmpcondition1781);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar158=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1783);  
							stream_WhiteChar.add(WhiteChar158);

							DAYS159=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1785);  
							stream_DAYS.add(DAYS159);

							WhiteChar160=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1787);  
							stream_WhiteChar.add(WhiteChar160);

							string_literal161=(Token)match(input,96,FOLLOW_96_in_opcmpcondition1789);  
							stream_96.add(string_literal161);

							WhiteChar162=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1791);  
							stream_WhiteChar.add(WhiteChar162);

							pushFollow(FOLLOW_constant_in_opcmpcondition1795);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar163=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1797);  
							stream_WhiteChar.add(WhiteChar163);

							DAYS164=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1799);  
							stream_DAYS.add(DAYS164);

							WhiteChar165=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1801);  
							stream_WhiteChar.add(WhiteChar165);

							string_literal166=(Token)match(input,73,FOLLOW_73_in_opcmpcondition1803);  
							stream_73.add(string_literal166);

							WhiteChar167=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1805);  
							stream_WhiteChar.add(WhiteChar167);

							pushFollow(FOLLOW_constant_in_opcmpcondition1809);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT168=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1811);  
							stream_PERCENT.add(PERCENT168);

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
							// 259:5: -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:259:8: ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:259:94: ^( Number NumberToken[\"0.0\"] )
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

							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:4: ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )?
							int alt33=2;
							int LA33_0 = input.LA(1);
							if ( (LA33_0==WhiteChar) ) {
								int LA33_1 = input.LA(2);
								if ( (LA33_1==63) ) {
									alt33=1;
								}
							}
							switch (alt33) {
								case 1 :
									// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:5: WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom
									{
									WhiteChar169=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1851);  
									stream_WhiteChar.add(WhiteChar169);

									string_literal170=(Token)match(input,63,FOLLOW_63_in_opcmpcondition1853);  
									stream_63.add(string_literal170);

									WhiteChar171=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1855);  
									stream_WhiteChar.add(WhiteChar171);

									pushFollow(FOLLOW_constant_in_opcmpcondition1859);
									adaptiveRate=constant();
									state._fsp--;

									stream_constant.add(adaptiveRate.getTree());
									PERCENT172=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1861);  
									stream_PERCENT.add(PERCENT172);

									WhiteChar173=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1863);  
									stream_WhiteChar.add(WhiteChar173);

									pushFollow(FOLLOW_atom_in_opcmpcondition1867);
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
									// 262:5: -> ^( CrossUpDoubleMapCondition )
									{
										// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:8: ^( CrossUpDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:4: 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal174=(Token)match(input,107,FOLLOW_107_in_opcmpcondition1912);  
					stream_107.add(string_literal174);

					WhiteChar175=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1914);  
					stream_WhiteChar.add(WhiteChar175);

					pushFollow(FOLLOW_operand_in_opcmpcondition1918);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar176=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1926);  
					stream_WhiteChar.add(WhiteChar176);

					string_literal177=(Token)match(input,96,FOLLOW_96_in_opcmpcondition1928);  
					stream_96.add(string_literal177);

					WhiteChar178=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1930);  
					stream_WhiteChar.add(WhiteChar178);

					pushFollow(FOLLOW_constant_in_opcmpcondition1934);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar179=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1936);  
					stream_WhiteChar.add(WhiteChar179);

					DAYS180=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1938);  
					stream_DAYS.add(DAYS180);

					WhiteChar181=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1946);  
					stream_WhiteChar.add(WhiteChar181);

					string_literal182=(Token)match(input,77,FOLLOW_77_in_opcmpcondition1948);  
					stream_77.add(string_literal182);

					WhiteChar183=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1950);  
					stream_WhiteChar.add(WhiteChar183);

					pushFollow(FOLLOW_constant_in_opcmpcondition1954);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar184=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1956);  
					stream_WhiteChar.add(WhiteChar184);

					DAYS185=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1958);  
					stream_DAYS.add(DAYS185);

					WhiteChar186=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1966);  
					stream_WhiteChar.add(WhiteChar186);

					string_literal187=(Token)match(input,71,FOLLOW_71_in_opcmpcondition1968);  
					stream_71.add(string_literal187);

					WhiteChar188=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1970);  
					stream_WhiteChar.add(WhiteChar188);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition1974);
					direction=stringconstant();
					state._fsp--;

					stream_stringconstant.add(direction.getTree());
					WhiteChar189=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1982);  
					stream_WhiteChar.add(WhiteChar189);

					string_literal190=(Token)match(input,73,FOLLOW_73_in_opcmpcondition1984);  
					stream_73.add(string_literal190);

					WhiteChar191=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1986);  
					stream_WhiteChar.add(WhiteChar191);

					pushFollow(FOLLOW_constant_in_opcmpcondition1990);
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
					// 272:7: -> ^( LinearSimilarTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:272:10: ^( LinearSimilarTrendsCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:273:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:273:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:273:4: 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant
					{
					string_literal192=(Token)match(input,108,FOLLOW_108_in_opcmpcondition2022);  
					stream_108.add(string_literal192);

					WhiteChar193=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2024);  
					stream_WhiteChar.add(WhiteChar193);

					pushFollow(FOLLOW_operand_in_opcmpcondition2028);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar194=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2036);  
					stream_WhiteChar.add(WhiteChar194);

					string_literal195=(Token)match(input,96,FOLLOW_96_in_opcmpcondition2038);  
					stream_96.add(string_literal195);

					WhiteChar196=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2040);  
					stream_WhiteChar.add(WhiteChar196);

					pushFollow(FOLLOW_constant_in_opcmpcondition2044);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar197=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2046);  
					stream_WhiteChar.add(WhiteChar197);

					DAYS198=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition2048);  
					stream_DAYS.add(DAYS198);

					WhiteChar199=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2056);  
					stream_WhiteChar.add(WhiteChar199);

					string_literal200=(Token)match(input,77,FOLLOW_77_in_opcmpcondition2058);  
					stream_77.add(string_literal200);

					WhiteChar201=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2060);  
					stream_WhiteChar.add(WhiteChar201);

					pushFollow(FOLLOW_constant_in_opcmpcondition2064);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar202=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2066);  
					stream_WhiteChar.add(WhiteChar202);

					DAYS203=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition2068);  
					stream_DAYS.add(DAYS203);

					WhiteChar204=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2076);  
					stream_WhiteChar.add(WhiteChar204);

					string_literal205=(Token)match(input,71,FOLLOW_71_in_opcmpcondition2078);  
					stream_71.add(string_literal205);

					WhiteChar206=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2080);  
					stream_WhiteChar.add(WhiteChar206);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition2084);
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
					// 277:7: -> ^( LinearOppositeTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:277:10: ^( LinearOppositeTrendsCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:279:1: constantcmp[CommonTree firstOp] : ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )? );
	public final ParameterizedIndicatorsParser.constantcmp_return constantcmp(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.constantcmp_return retval = new ParameterizedIndicatorsParser.constantcmp_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal207=null;
		Token WhiteChar208=null;
		Token WhiteChar209=null;
		Token string_literal210=null;
		Token WhiteChar211=null;
		Token WhiteChar212=null;
		Token DAYS213=null;
		Token WhiteChar214=null;
		Token string_literal215=null;
		Token WhiteChar216=null;
		Token WhiteChar217=null;
		Token DAYS218=null;
		Token string_literal219=null;
		Token WhiteChar220=null;
		Token WhiteChar221=null;
		Token string_literal222=null;
		Token WhiteChar223=null;
		Token WhiteChar224=null;
		Token DAYS225=null;
		Token WhiteChar226=null;
		Token string_literal227=null;
		Token WhiteChar228=null;
		Token WhiteChar229=null;
		Token DAYS230=null;
		Token WhiteChar231=null;
		Token string_literal232=null;
		Token WhiteChar233=null;
		Token PERCENT234=null;
		Token string_literal235=null;
		Token WhiteChar236=null;
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
		Token WhiteChar247=null;
		Token string_literal248=null;
		Token WhiteChar249=null;
		Token PERCENT250=null;
		Token string_literal251=null;
		Token WhiteChar252=null;
		Token WhiteChar253=null;
		Token string_literal254=null;
		Token WhiteChar255=null;
		Token WhiteChar256=null;
		Token DAYS257=null;
		Token WhiteChar258=null;
		Token string_literal259=null;
		Token WhiteChar260=null;
		Token WhiteChar261=null;
		Token DAYS262=null;
		Token WhiteChar263=null;
		Token string_literal264=null;
		Token WhiteChar265=null;
		Token PERCENT266=null;
		ParserRuleReturnScope trendSignal =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope threshold =null;
		ParserRuleReturnScope epsilon =null;

		CommonTree string_literal207_tree=null;
		CommonTree WhiteChar208_tree=null;
		CommonTree WhiteChar209_tree=null;
		CommonTree string_literal210_tree=null;
		CommonTree WhiteChar211_tree=null;
		CommonTree WhiteChar212_tree=null;
		CommonTree DAYS213_tree=null;
		CommonTree WhiteChar214_tree=null;
		CommonTree string_literal215_tree=null;
		CommonTree WhiteChar216_tree=null;
		CommonTree WhiteChar217_tree=null;
		CommonTree DAYS218_tree=null;
		CommonTree string_literal219_tree=null;
		CommonTree WhiteChar220_tree=null;
		CommonTree WhiteChar221_tree=null;
		CommonTree string_literal222_tree=null;
		CommonTree WhiteChar223_tree=null;
		CommonTree WhiteChar224_tree=null;
		CommonTree DAYS225_tree=null;
		CommonTree WhiteChar226_tree=null;
		CommonTree string_literal227_tree=null;
		CommonTree WhiteChar228_tree=null;
		CommonTree WhiteChar229_tree=null;
		CommonTree DAYS230_tree=null;
		CommonTree WhiteChar231_tree=null;
		CommonTree string_literal232_tree=null;
		CommonTree WhiteChar233_tree=null;
		CommonTree PERCENT234_tree=null;
		CommonTree string_literal235_tree=null;
		CommonTree WhiteChar236_tree=null;
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
		CommonTree WhiteChar247_tree=null;
		CommonTree string_literal248_tree=null;
		CommonTree WhiteChar249_tree=null;
		CommonTree PERCENT250_tree=null;
		CommonTree string_literal251_tree=null;
		CommonTree WhiteChar252_tree=null;
		CommonTree WhiteChar253_tree=null;
		CommonTree string_literal254_tree=null;
		CommonTree WhiteChar255_tree=null;
		CommonTree WhiteChar256_tree=null;
		CommonTree DAYS257_tree=null;
		CommonTree WhiteChar258_tree=null;
		CommonTree string_literal259_tree=null;
		CommonTree WhiteChar260_tree=null;
		CommonTree WhiteChar261_tree=null;
		CommonTree DAYS262_tree=null;
		CommonTree WhiteChar263_tree=null;
		CommonTree string_literal264_tree=null;
		CommonTree WhiteChar265_tree=null;
		CommonTree PERCENT266_tree=null;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:279:34: ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )? )
			int alt40=4;
			switch ( input.LA(1) ) {
			case 76:
				{
				alt40=1;
				}
				break;
			case 75:
				{
				alt40=2;
				}
				break;
			case 82:
				{
				alt40=3;
				}
				break;
			case 86:
				{
				alt40=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 40, 0, input);
				throw nvae;
			}
			switch (alt40) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:4: 'equals trend' WhiteChar trendSignal= trendconstant
					{
					string_literal207=(Token)match(input,76,FOLLOW_76_in_constantcmp2121);  
					stream_76.add(string_literal207);

					WhiteChar208=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2123);  
					stream_WhiteChar.add(WhiteChar208);

					pushFollow(FOLLOW_trendconstant_in_constantcmp2127);
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
					// 281:55: -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:58: ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualStringConstantCondition, "EqualStringConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_trendconstant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:103: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:130: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					int alt36=2;
					int LA36_0 = input.LA(1);
					if ( (LA36_0==WhiteChar) ) {
						int LA36_1 = input.LA(2);
						if ( (LA36_1==96) ) {
							alt36=1;
						}
					}
					switch (alt36) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar209=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2161);  
							stream_WhiteChar.add(WhiteChar209);

							string_literal210=(Token)match(input,96,FOLLOW_96_in_constantcmp2163);  
							stream_96.add(string_literal210);

							WhiteChar211=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2165);  
							stream_WhiteChar.add(WhiteChar211);

							pushFollow(FOLLOW_constant_in_constantcmp2169);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar212=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2171);  
							stream_WhiteChar.add(WhiteChar212);

							DAYS213=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2173);  
							stream_DAYS.add(DAYS213);

							WhiteChar214=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2175);  
							stream_WhiteChar.add(WhiteChar214);

							string_literal215=(Token)match(input,77,FOLLOW_77_in_constantcmp2177);  
							stream_77.add(string_literal215);

							WhiteChar216=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2179);  
							stream_WhiteChar.add(WhiteChar216);

							pushFollow(FOLLOW_constant_in_constantcmp2183);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar217=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2185);  
							stream_WhiteChar.add(WhiteChar217);

							DAYS218=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2187);  
							stream_DAYS.add(DAYS218);

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
							// 282:129: -> ^( EqualStringConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:132: ^( EqualStringConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:284:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:284:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:284:4: 'equals threshold' WhiteChar threshold= constant
					{
					string_literal219=(Token)match(input,75,FOLLOW_75_in_constantcmp2211);  
					stream_75.add(string_literal219);

					WhiteChar220=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2213);  
					stream_WhiteChar.add(WhiteChar220);

					pushFollow(FOLLOW_constant_in_constantcmp2217);
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
					// 284:52: -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:284:55: ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualConstantCondition, "EqualConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:284:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:284:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:284:143: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:285:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:285:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar221=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2258);  
							stream_WhiteChar.add(WhiteChar221);

							string_literal222=(Token)match(input,96,FOLLOW_96_in_constantcmp2260);  
							stream_96.add(string_literal222);

							WhiteChar223=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2262);  
							stream_WhiteChar.add(WhiteChar223);

							pushFollow(FOLLOW_constant_in_constantcmp2266);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar224=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2268);  
							stream_WhiteChar.add(WhiteChar224);

							DAYS225=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2270);  
							stream_DAYS.add(DAYS225);

							WhiteChar226=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2276);  
							stream_WhiteChar.add(WhiteChar226);

							string_literal227=(Token)match(input,77,FOLLOW_77_in_constantcmp2278);  
							stream_77.add(string_literal227);

							WhiteChar228=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2280);  
							stream_WhiteChar.add(WhiteChar228);

							pushFollow(FOLLOW_constant_in_constantcmp2284);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar229=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2286);  
							stream_WhiteChar.add(WhiteChar229);

							DAYS230=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2288);  
							stream_DAYS.add(DAYS230);

							WhiteChar231=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2294);  
							stream_WhiteChar.add(WhiteChar231);

							string_literal232=(Token)match(input,73,FOLLOW_73_in_constantcmp2296);  
							stream_73.add(string_literal232);

							WhiteChar233=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2298);  
							stream_WhiteChar.add(WhiteChar233);

							pushFollow(FOLLOW_constant_in_constantcmp2302);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT234=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2304);  
							stream_PERCENT.add(PERCENT234);

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
							// 288:5: -> ^( EqualConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:8: ^( EqualConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:4: 'is above threshold' WhiteChar threshold= constant
					{
					string_literal235=(Token)match(input,82,FOLLOW_82_in_constantcmp2334);  
					stream_82.add(string_literal235);

					WhiteChar236=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2336);  
					stream_WhiteChar.add(WhiteChar236);

					pushFollow(FOLLOW_constant_in_constantcmp2340);
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
					// 289:54: -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:57: ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupConstantCondition, "SupConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:143: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar237=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2381);  
							stream_WhiteChar.add(WhiteChar237);

							string_literal238=(Token)match(input,96,FOLLOW_96_in_constantcmp2383);  
							stream_96.add(string_literal238);

							WhiteChar239=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2385);  
							stream_WhiteChar.add(WhiteChar239);

							pushFollow(FOLLOW_constant_in_constantcmp2389);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar240=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2391);  
							stream_WhiteChar.add(WhiteChar240);

							DAYS241=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2393);  
							stream_DAYS.add(DAYS241);

							WhiteChar242=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2399);  
							stream_WhiteChar.add(WhiteChar242);

							string_literal243=(Token)match(input,77,FOLLOW_77_in_constantcmp2401);  
							stream_77.add(string_literal243);

							WhiteChar244=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2403);  
							stream_WhiteChar.add(WhiteChar244);

							pushFollow(FOLLOW_constant_in_constantcmp2407);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar245=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2409);  
							stream_WhiteChar.add(WhiteChar245);

							DAYS246=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2411);  
							stream_DAYS.add(DAYS246);

							WhiteChar247=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2417);  
							stream_WhiteChar.add(WhiteChar247);

							string_literal248=(Token)match(input,73,FOLLOW_73_in_constantcmp2419);  
							stream_73.add(string_literal248);

							WhiteChar249=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2421);  
							stream_WhiteChar.add(WhiteChar249);

							pushFollow(FOLLOW_constant_in_constantcmp2425);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT250=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2427);  
							stream_PERCENT.add(PERCENT250);

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
							// 293:5: -> ^( SupConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:8: ^( SupConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:4: 'is below threshold' WhiteChar threshold= constant
					{
					string_literal251=(Token)match(input,86,FOLLOW_86_in_constantcmp2457);  
					stream_86.add(string_literal251);

					WhiteChar252=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2459);  
					stream_WhiteChar.add(WhiteChar252);

					pushFollow(FOLLOW_constant_in_constantcmp2463);
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
					// 294:54: -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:57: ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfConstantCondition, "InfConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:143: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar253=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2504);  
							stream_WhiteChar.add(WhiteChar253);

							string_literal254=(Token)match(input,96,FOLLOW_96_in_constantcmp2506);  
							stream_96.add(string_literal254);

							WhiteChar255=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2508);  
							stream_WhiteChar.add(WhiteChar255);

							pushFollow(FOLLOW_constant_in_constantcmp2512);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar256=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2514);  
							stream_WhiteChar.add(WhiteChar256);

							DAYS257=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2516);  
							stream_DAYS.add(DAYS257);

							WhiteChar258=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2522);  
							stream_WhiteChar.add(WhiteChar258);

							string_literal259=(Token)match(input,77,FOLLOW_77_in_constantcmp2524);  
							stream_77.add(string_literal259);

							WhiteChar260=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2526);  
							stream_WhiteChar.add(WhiteChar260);

							pushFollow(FOLLOW_constant_in_constantcmp2530);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar261=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2532);  
							stream_WhiteChar.add(WhiteChar261);

							DAYS262=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2534);  
							stream_DAYS.add(DAYS262);

							WhiteChar263=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2540);  
							stream_WhiteChar.add(WhiteChar263);

							string_literal264=(Token)match(input,73,FOLLOW_73_in_constantcmp2542);  
							stream_73.add(string_literal264);

							WhiteChar265=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2544);  
							stream_WhiteChar.add(WhiteChar265);

							pushFollow(FOLLOW_constant_in_constantcmp2548);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT266=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2550);  
							stream_PERCENT.add(PERCENT266);

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
							// 298:5: -> ^( InfConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:8: ^( InfConstantCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:300:1: presetcondition[CommonTree firstOp] : ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) ) | ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) ) | ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) ) );
	public final ParameterizedIndicatorsParser.presetcondition_return presetcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.presetcondition_return retval = new ParameterizedIndicatorsParser.presetcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal267=null;
		Token WhiteChar268=null;
		Token string_literal269=null;
		Token WhiteChar270=null;
		Token PERCENT271=null;
		Token WhiteChar272=null;
		Token string_literal273=null;
		Token WhiteChar274=null;
		Token WhiteChar275=null;
		Token DAYS276=null;
		Token string_literal277=null;
		Token WhiteChar278=null;
		Token string_literal279=null;
		Token WhiteChar280=null;
		Token PERCENT281=null;
		Token WhiteChar282=null;
		Token string_literal283=null;
		Token WhiteChar284=null;
		Token WhiteChar285=null;
		Token DAYS286=null;
		Token string_literal287=null;
		Token WhiteChar288=null;
		Token PERCENT289=null;
		Token WhiteChar290=null;
		Token string_literal291=null;
		Token WhiteChar292=null;
		Token WhiteChar293=null;
		Token DAYS294=null;
		Token WhiteChar295=null;
		Token string_literal296=null;
		Token WhiteChar297=null;
		Token WhiteChar298=null;
		Token DAYS299=null;
		Token string_literal300=null;
		Token WhiteChar301=null;
		Token PERCENT302=null;
		Token WhiteChar303=null;
		Token string_literal304=null;
		Token WhiteChar305=null;
		Token WhiteChar306=null;
		Token DAYS307=null;
		Token WhiteChar308=null;
		Token string_literal309=null;
		Token WhiteChar310=null;
		Token WhiteChar311=null;
		Token DAYS312=null;
		Token string_literal313=null;
		Token WhiteChar314=null;
		Token WhiteChar315=null;
		Token string_literal316=null;
		Token WhiteChar317=null;
		Token WhiteChar318=null;
		Token DAYS319=null;
		Token WhiteChar320=null;
		Token string_literal321=null;
		Token WhiteChar322=null;
		Token WhiteChar323=null;
		Token DAYS324=null;
		Token WhiteChar325=null;
		Token string_literal326=null;
		Token WhiteChar327=null;
		Token PERCENT328=null;
		Token string_literal329=null;
		Token WhiteChar330=null;
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
		Token PERCENT344=null;
		Token string_literal345=null;
		Token WhiteChar346=null;
		Token WhiteChar347=null;
		Token DAYS348=null;
		Token WhiteChar349=null;
		Token string_literal350=null;
		Token WhiteChar351=null;
		Token WhiteChar352=null;
		Token DAYS353=null;
		Token WhiteChar354=null;
		Token string_literal355=null;
		Token WhiteChar356=null;
		Token WhiteChar357=null;
		Token DAYS358=null;
		Token WhiteChar359=null;
		Token string_literal360=null;
		Token WhiteChar361=null;
		Token WhiteChar362=null;
		Token DAYS363=null;
		Token WhiteChar364=null;
		Token string_literal365=null;
		Token WhiteChar366=null;
		Token WhiteChar367=null;
		Token string_literal368=null;
		Token WhiteChar369=null;
		Token WhiteChar370=null;
		Token string_literal371=null;
		Token WhiteChar372=null;
		Token char_literal373=null;
		Token char_literal374=null;
		Token char_literal375=null;
		Token WhiteChar376=null;
		Token string_literal377=null;
		Token WhiteChar378=null;
		Token char_literal379=null;
		Token char_literal380=null;
		Token char_literal381=null;
		Token WhiteChar382=null;
		Token string_literal383=null;
		Token WhiteChar384=null;
		Token char_literal385=null;
		Token char_literal386=null;
		Token char_literal387=null;
		Token string_literal388=null;
		Token WhiteChar389=null;
		Token WhiteChar390=null;
		Token DAYS391=null;
		Token WhiteChar392=null;
		Token string_literal393=null;
		Token WhiteChar394=null;
		Token WhiteChar395=null;
		Token DAYS396=null;
		Token WhiteChar397=null;
		Token string_literal398=null;
		Token WhiteChar399=null;
		Token WhiteChar400=null;
		Token DAYS401=null;
		Token WhiteChar402=null;
		Token string_literal403=null;
		Token WhiteChar404=null;
		Token WhiteChar405=null;
		Token DAYS406=null;
		Token WhiteChar407=null;
		Token string_literal408=null;
		Token WhiteChar409=null;
		Token WhiteChar410=null;
		Token string_literal411=null;
		Token WhiteChar412=null;
		Token WhiteChar413=null;
		Token string_literal414=null;
		Token WhiteChar415=null;
		Token char_literal416=null;
		Token char_literal417=null;
		Token char_literal418=null;
		Token WhiteChar419=null;
		Token string_literal420=null;
		Token WhiteChar421=null;
		Token char_literal422=null;
		Token char_literal423=null;
		Token char_literal424=null;
		Token WhiteChar425=null;
		Token string_literal426=null;
		Token WhiteChar427=null;
		Token char_literal428=null;
		Token char_literal429=null;
		Token char_literal430=null;
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
		Token WhiteChar448=null;
		Token DAYS449=null;
		Token WhiteChar450=null;
		Token string_literal451=null;
		Token WhiteChar452=null;
		Token WhiteChar453=null;
		Token string_literal454=null;
		Token WhiteChar455=null;
		Token WhiteChar456=null;
		Token string_literal457=null;
		Token WhiteChar458=null;
		Token char_literal459=null;
		Token char_literal460=null;
		Token char_literal461=null;
		Token WhiteChar462=null;
		Token string_literal463=null;
		Token WhiteChar464=null;
		Token char_literal465=null;
		Token char_literal466=null;
		Token char_literal467=null;
		Token WhiteChar468=null;
		Token string_literal469=null;
		Token WhiteChar470=null;
		Token char_literal471=null;
		Token char_literal472=null;
		Token char_literal473=null;
		Token string_literal474=null;
		Token WhiteChar475=null;
		Token WhiteChar476=null;
		Token DAYS477=null;
		Token WhiteChar478=null;
		Token string_literal479=null;
		Token WhiteChar480=null;
		Token WhiteChar481=null;
		Token DAYS482=null;
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
		Token string_literal497=null;
		Token WhiteChar498=null;
		Token WhiteChar499=null;
		Token string_literal500=null;
		Token WhiteChar501=null;
		Token char_literal502=null;
		Token char_literal503=null;
		Token char_literal504=null;
		Token WhiteChar505=null;
		Token string_literal506=null;
		Token WhiteChar507=null;
		Token char_literal508=null;
		Token char_literal509=null;
		Token char_literal510=null;
		Token WhiteChar511=null;
		Token string_literal512=null;
		Token WhiteChar513=null;
		Token char_literal514=null;
		Token char_literal515=null;
		Token char_literal516=null;
		Token string_literal517=null;
		Token WhiteChar518=null;
		Token WhiteChar519=null;
		Token DAYS520=null;
		Token WhiteChar521=null;
		Token string_literal522=null;
		Token WhiteChar523=null;
		Token WhiteChar524=null;
		Token DAYS525=null;
		Token WhiteChar526=null;
		Token string_literal527=null;
		Token WhiteChar528=null;
		Token WhiteChar529=null;
		Token DAYS530=null;
		Token WhiteChar531=null;
		Token string_literal532=null;
		Token WhiteChar533=null;
		Token WhiteChar534=null;
		Token DAYS535=null;
		Token WhiteChar536=null;
		Token string_literal537=null;
		Token WhiteChar538=null;
		Token char_literal539=null;
		Token char_literal540=null;
		Token char_literal541=null;
		Token WhiteChar542=null;
		Token string_literal543=null;
		Token WhiteChar544=null;
		Token string_literal545=null;
		Token WhiteChar546=null;
		Token WhiteChar547=null;
		Token DAYS548=null;
		Token WhiteChar549=null;
		Token string_literal550=null;
		Token WhiteChar551=null;
		Token WhiteChar552=null;
		Token DAYS553=null;
		Token WhiteChar554=null;
		Token string_literal555=null;
		Token WhiteChar556=null;
		Token WhiteChar557=null;
		Token DAYS558=null;
		Token WhiteChar559=null;
		Token string_literal560=null;
		Token WhiteChar561=null;
		Token WhiteChar562=null;
		Token DAYS563=null;
		Token WhiteChar564=null;
		Token string_literal565=null;
		Token WhiteChar566=null;
		Token char_literal567=null;
		Token char_literal568=null;
		Token char_literal569=null;
		Token WhiteChar570=null;
		Token string_literal571=null;
		Token WhiteChar572=null;
		Token string_literal573=null;
		Token WhiteChar574=null;
		Token string_literal575=null;
		Token WhiteChar576=null;
		Token WhiteChar577=null;
		Token DAYS578=null;
		Token WhiteChar579=null;
		Token string_literal580=null;
		Token WhiteChar581=null;
		Token WhiteChar582=null;
		Token DAYS583=null;
		Token WhiteChar584=null;
		Token string_literal585=null;
		Token WhiteChar586=null;
		Token string_literal587=null;
		Token WhiteChar588=null;
		Token string_literal589=null;
		Token WhiteChar590=null;
		Token WhiteChar591=null;
		Token DAYS592=null;
		Token WhiteChar593=null;
		Token string_literal594=null;
		Token WhiteChar595=null;
		Token WhiteChar596=null;
		Token DAYS597=null;
		Token WhiteChar598=null;
		Token string_literal599=null;
		Token WhiteChar600=null;
		Token string_literal601=null;
		Token WhiteChar602=null;
		Token string_literal603=null;
		Token WhiteChar604=null;
		Token WhiteChar605=null;
		Token DAYS606=null;
		Token WhiteChar607=null;
		Token string_literal608=null;
		Token WhiteChar609=null;
		Token WhiteChar610=null;
		Token DAYS611=null;
		Token WhiteChar612=null;
		Token string_literal613=null;
		Token WhiteChar614=null;
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

		CommonTree string_literal267_tree=null;
		CommonTree WhiteChar268_tree=null;
		CommonTree string_literal269_tree=null;
		CommonTree WhiteChar270_tree=null;
		CommonTree PERCENT271_tree=null;
		CommonTree WhiteChar272_tree=null;
		CommonTree string_literal273_tree=null;
		CommonTree WhiteChar274_tree=null;
		CommonTree WhiteChar275_tree=null;
		CommonTree DAYS276_tree=null;
		CommonTree string_literal277_tree=null;
		CommonTree WhiteChar278_tree=null;
		CommonTree string_literal279_tree=null;
		CommonTree WhiteChar280_tree=null;
		CommonTree PERCENT281_tree=null;
		CommonTree WhiteChar282_tree=null;
		CommonTree string_literal283_tree=null;
		CommonTree WhiteChar284_tree=null;
		CommonTree WhiteChar285_tree=null;
		CommonTree DAYS286_tree=null;
		CommonTree string_literal287_tree=null;
		CommonTree WhiteChar288_tree=null;
		CommonTree PERCENT289_tree=null;
		CommonTree WhiteChar290_tree=null;
		CommonTree string_literal291_tree=null;
		CommonTree WhiteChar292_tree=null;
		CommonTree WhiteChar293_tree=null;
		CommonTree DAYS294_tree=null;
		CommonTree WhiteChar295_tree=null;
		CommonTree string_literal296_tree=null;
		CommonTree WhiteChar297_tree=null;
		CommonTree WhiteChar298_tree=null;
		CommonTree DAYS299_tree=null;
		CommonTree string_literal300_tree=null;
		CommonTree WhiteChar301_tree=null;
		CommonTree PERCENT302_tree=null;
		CommonTree WhiteChar303_tree=null;
		CommonTree string_literal304_tree=null;
		CommonTree WhiteChar305_tree=null;
		CommonTree WhiteChar306_tree=null;
		CommonTree DAYS307_tree=null;
		CommonTree WhiteChar308_tree=null;
		CommonTree string_literal309_tree=null;
		CommonTree WhiteChar310_tree=null;
		CommonTree WhiteChar311_tree=null;
		CommonTree DAYS312_tree=null;
		CommonTree string_literal313_tree=null;
		CommonTree WhiteChar314_tree=null;
		CommonTree WhiteChar315_tree=null;
		CommonTree string_literal316_tree=null;
		CommonTree WhiteChar317_tree=null;
		CommonTree WhiteChar318_tree=null;
		CommonTree DAYS319_tree=null;
		CommonTree WhiteChar320_tree=null;
		CommonTree string_literal321_tree=null;
		CommonTree WhiteChar322_tree=null;
		CommonTree WhiteChar323_tree=null;
		CommonTree DAYS324_tree=null;
		CommonTree WhiteChar325_tree=null;
		CommonTree string_literal326_tree=null;
		CommonTree WhiteChar327_tree=null;
		CommonTree PERCENT328_tree=null;
		CommonTree string_literal329_tree=null;
		CommonTree WhiteChar330_tree=null;
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
		CommonTree PERCENT344_tree=null;
		CommonTree string_literal345_tree=null;
		CommonTree WhiteChar346_tree=null;
		CommonTree WhiteChar347_tree=null;
		CommonTree DAYS348_tree=null;
		CommonTree WhiteChar349_tree=null;
		CommonTree string_literal350_tree=null;
		CommonTree WhiteChar351_tree=null;
		CommonTree WhiteChar352_tree=null;
		CommonTree DAYS353_tree=null;
		CommonTree WhiteChar354_tree=null;
		CommonTree string_literal355_tree=null;
		CommonTree WhiteChar356_tree=null;
		CommonTree WhiteChar357_tree=null;
		CommonTree DAYS358_tree=null;
		CommonTree WhiteChar359_tree=null;
		CommonTree string_literal360_tree=null;
		CommonTree WhiteChar361_tree=null;
		CommonTree WhiteChar362_tree=null;
		CommonTree DAYS363_tree=null;
		CommonTree WhiteChar364_tree=null;
		CommonTree string_literal365_tree=null;
		CommonTree WhiteChar366_tree=null;
		CommonTree WhiteChar367_tree=null;
		CommonTree string_literal368_tree=null;
		CommonTree WhiteChar369_tree=null;
		CommonTree WhiteChar370_tree=null;
		CommonTree string_literal371_tree=null;
		CommonTree WhiteChar372_tree=null;
		CommonTree char_literal373_tree=null;
		CommonTree char_literal374_tree=null;
		CommonTree char_literal375_tree=null;
		CommonTree WhiteChar376_tree=null;
		CommonTree string_literal377_tree=null;
		CommonTree WhiteChar378_tree=null;
		CommonTree char_literal379_tree=null;
		CommonTree char_literal380_tree=null;
		CommonTree char_literal381_tree=null;
		CommonTree WhiteChar382_tree=null;
		CommonTree string_literal383_tree=null;
		CommonTree WhiteChar384_tree=null;
		CommonTree char_literal385_tree=null;
		CommonTree char_literal386_tree=null;
		CommonTree char_literal387_tree=null;
		CommonTree string_literal388_tree=null;
		CommonTree WhiteChar389_tree=null;
		CommonTree WhiteChar390_tree=null;
		CommonTree DAYS391_tree=null;
		CommonTree WhiteChar392_tree=null;
		CommonTree string_literal393_tree=null;
		CommonTree WhiteChar394_tree=null;
		CommonTree WhiteChar395_tree=null;
		CommonTree DAYS396_tree=null;
		CommonTree WhiteChar397_tree=null;
		CommonTree string_literal398_tree=null;
		CommonTree WhiteChar399_tree=null;
		CommonTree WhiteChar400_tree=null;
		CommonTree DAYS401_tree=null;
		CommonTree WhiteChar402_tree=null;
		CommonTree string_literal403_tree=null;
		CommonTree WhiteChar404_tree=null;
		CommonTree WhiteChar405_tree=null;
		CommonTree DAYS406_tree=null;
		CommonTree WhiteChar407_tree=null;
		CommonTree string_literal408_tree=null;
		CommonTree WhiteChar409_tree=null;
		CommonTree WhiteChar410_tree=null;
		CommonTree string_literal411_tree=null;
		CommonTree WhiteChar412_tree=null;
		CommonTree WhiteChar413_tree=null;
		CommonTree string_literal414_tree=null;
		CommonTree WhiteChar415_tree=null;
		CommonTree char_literal416_tree=null;
		CommonTree char_literal417_tree=null;
		CommonTree char_literal418_tree=null;
		CommonTree WhiteChar419_tree=null;
		CommonTree string_literal420_tree=null;
		CommonTree WhiteChar421_tree=null;
		CommonTree char_literal422_tree=null;
		CommonTree char_literal423_tree=null;
		CommonTree char_literal424_tree=null;
		CommonTree WhiteChar425_tree=null;
		CommonTree string_literal426_tree=null;
		CommonTree WhiteChar427_tree=null;
		CommonTree char_literal428_tree=null;
		CommonTree char_literal429_tree=null;
		CommonTree char_literal430_tree=null;
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
		CommonTree WhiteChar448_tree=null;
		CommonTree DAYS449_tree=null;
		CommonTree WhiteChar450_tree=null;
		CommonTree string_literal451_tree=null;
		CommonTree WhiteChar452_tree=null;
		CommonTree WhiteChar453_tree=null;
		CommonTree string_literal454_tree=null;
		CommonTree WhiteChar455_tree=null;
		CommonTree WhiteChar456_tree=null;
		CommonTree string_literal457_tree=null;
		CommonTree WhiteChar458_tree=null;
		CommonTree char_literal459_tree=null;
		CommonTree char_literal460_tree=null;
		CommonTree char_literal461_tree=null;
		CommonTree WhiteChar462_tree=null;
		CommonTree string_literal463_tree=null;
		CommonTree WhiteChar464_tree=null;
		CommonTree char_literal465_tree=null;
		CommonTree char_literal466_tree=null;
		CommonTree char_literal467_tree=null;
		CommonTree WhiteChar468_tree=null;
		CommonTree string_literal469_tree=null;
		CommonTree WhiteChar470_tree=null;
		CommonTree char_literal471_tree=null;
		CommonTree char_literal472_tree=null;
		CommonTree char_literal473_tree=null;
		CommonTree string_literal474_tree=null;
		CommonTree WhiteChar475_tree=null;
		CommonTree WhiteChar476_tree=null;
		CommonTree DAYS477_tree=null;
		CommonTree WhiteChar478_tree=null;
		CommonTree string_literal479_tree=null;
		CommonTree WhiteChar480_tree=null;
		CommonTree WhiteChar481_tree=null;
		CommonTree DAYS482_tree=null;
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
		CommonTree string_literal497_tree=null;
		CommonTree WhiteChar498_tree=null;
		CommonTree WhiteChar499_tree=null;
		CommonTree string_literal500_tree=null;
		CommonTree WhiteChar501_tree=null;
		CommonTree char_literal502_tree=null;
		CommonTree char_literal503_tree=null;
		CommonTree char_literal504_tree=null;
		CommonTree WhiteChar505_tree=null;
		CommonTree string_literal506_tree=null;
		CommonTree WhiteChar507_tree=null;
		CommonTree char_literal508_tree=null;
		CommonTree char_literal509_tree=null;
		CommonTree char_literal510_tree=null;
		CommonTree WhiteChar511_tree=null;
		CommonTree string_literal512_tree=null;
		CommonTree WhiteChar513_tree=null;
		CommonTree char_literal514_tree=null;
		CommonTree char_literal515_tree=null;
		CommonTree char_literal516_tree=null;
		CommonTree string_literal517_tree=null;
		CommonTree WhiteChar518_tree=null;
		CommonTree WhiteChar519_tree=null;
		CommonTree DAYS520_tree=null;
		CommonTree WhiteChar521_tree=null;
		CommonTree string_literal522_tree=null;
		CommonTree WhiteChar523_tree=null;
		CommonTree WhiteChar524_tree=null;
		CommonTree DAYS525_tree=null;
		CommonTree WhiteChar526_tree=null;
		CommonTree string_literal527_tree=null;
		CommonTree WhiteChar528_tree=null;
		CommonTree WhiteChar529_tree=null;
		CommonTree DAYS530_tree=null;
		CommonTree WhiteChar531_tree=null;
		CommonTree string_literal532_tree=null;
		CommonTree WhiteChar533_tree=null;
		CommonTree WhiteChar534_tree=null;
		CommonTree DAYS535_tree=null;
		CommonTree WhiteChar536_tree=null;
		CommonTree string_literal537_tree=null;
		CommonTree WhiteChar538_tree=null;
		CommonTree char_literal539_tree=null;
		CommonTree char_literal540_tree=null;
		CommonTree char_literal541_tree=null;
		CommonTree WhiteChar542_tree=null;
		CommonTree string_literal543_tree=null;
		CommonTree WhiteChar544_tree=null;
		CommonTree string_literal545_tree=null;
		CommonTree WhiteChar546_tree=null;
		CommonTree WhiteChar547_tree=null;
		CommonTree DAYS548_tree=null;
		CommonTree WhiteChar549_tree=null;
		CommonTree string_literal550_tree=null;
		CommonTree WhiteChar551_tree=null;
		CommonTree WhiteChar552_tree=null;
		CommonTree DAYS553_tree=null;
		CommonTree WhiteChar554_tree=null;
		CommonTree string_literal555_tree=null;
		CommonTree WhiteChar556_tree=null;
		CommonTree WhiteChar557_tree=null;
		CommonTree DAYS558_tree=null;
		CommonTree WhiteChar559_tree=null;
		CommonTree string_literal560_tree=null;
		CommonTree WhiteChar561_tree=null;
		CommonTree WhiteChar562_tree=null;
		CommonTree DAYS563_tree=null;
		CommonTree WhiteChar564_tree=null;
		CommonTree string_literal565_tree=null;
		CommonTree WhiteChar566_tree=null;
		CommonTree char_literal567_tree=null;
		CommonTree char_literal568_tree=null;
		CommonTree char_literal569_tree=null;
		CommonTree WhiteChar570_tree=null;
		CommonTree string_literal571_tree=null;
		CommonTree WhiteChar572_tree=null;
		CommonTree string_literal573_tree=null;
		CommonTree WhiteChar574_tree=null;
		CommonTree string_literal575_tree=null;
		CommonTree WhiteChar576_tree=null;
		CommonTree WhiteChar577_tree=null;
		CommonTree DAYS578_tree=null;
		CommonTree WhiteChar579_tree=null;
		CommonTree string_literal580_tree=null;
		CommonTree WhiteChar581_tree=null;
		CommonTree WhiteChar582_tree=null;
		CommonTree DAYS583_tree=null;
		CommonTree WhiteChar584_tree=null;
		CommonTree string_literal585_tree=null;
		CommonTree WhiteChar586_tree=null;
		CommonTree string_literal587_tree=null;
		CommonTree WhiteChar588_tree=null;
		CommonTree string_literal589_tree=null;
		CommonTree WhiteChar590_tree=null;
		CommonTree WhiteChar591_tree=null;
		CommonTree DAYS592_tree=null;
		CommonTree WhiteChar593_tree=null;
		CommonTree string_literal594_tree=null;
		CommonTree WhiteChar595_tree=null;
		CommonTree WhiteChar596_tree=null;
		CommonTree DAYS597_tree=null;
		CommonTree WhiteChar598_tree=null;
		CommonTree string_literal599_tree=null;
		CommonTree WhiteChar600_tree=null;
		CommonTree string_literal601_tree=null;
		CommonTree WhiteChar602_tree=null;
		CommonTree string_literal603_tree=null;
		CommonTree WhiteChar604_tree=null;
		CommonTree WhiteChar605_tree=null;
		CommonTree DAYS606_tree=null;
		CommonTree WhiteChar607_tree=null;
		CommonTree string_literal608_tree=null;
		CommonTree WhiteChar609_tree=null;
		CommonTree WhiteChar610_tree=null;
		CommonTree DAYS611_tree=null;
		CommonTree WhiteChar612_tree=null;
		CommonTree string_literal613_tree=null;
		CommonTree WhiteChar614_tree=null;
		RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");
		RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_110=new RewriteRuleTokenStream(adaptor,"token 110");
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
		RewriteRuleTokenStream stream_98=new RewriteRuleTokenStream(adaptor,"token 98");
		RewriteRuleTokenStream stream_99=new RewriteRuleTokenStream(adaptor,"token 99");
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
		RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
		RewriteRuleTokenStream stream_100=new RewriteRuleTokenStream(adaptor,"token 100");
		RewriteRuleTokenStream stream_101=new RewriteRuleTokenStream(adaptor,"token 101");
		RewriteRuleTokenStream stream_102=new RewriteRuleTokenStream(adaptor,"token 102");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_103=new RewriteRuleTokenStream(adaptor,"token 103");
		RewriteRuleTokenStream stream_OPENSQRT=new RewriteRuleTokenStream(adaptor,"token OPENSQRT");
		RewriteRuleTokenStream stream_104=new RewriteRuleTokenStream(adaptor,"token 104");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_105=new RewriteRuleTokenStream(adaptor,"token 105");
		RewriteRuleTokenStream stream_106=new RewriteRuleTokenStream(adaptor,"token 106");
		RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
		RewriteRuleTokenStream stream_109=new RewriteRuleTokenStream(adaptor,"token 109");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_stringconstant=new RewriteRuleSubtreeStream(adaptor,"rule stringconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:300:38: ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) ) | ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) ) | ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) ) )
			int alt47=15;
			switch ( input.LA(1) ) {
			case 98:
				{
				alt47=1;
				}
				break;
			case 99:
				{
				alt47=2;
				}
				break;
			case 78:
				{
				alt47=3;
				}
				break;
			case 79:
				{
				alt47=4;
				}
				break;
			case 70:
				{
				alt47=5;
				}
				break;
			case 68:
				{
				alt47=6;
				}
				break;
			case 89:
				{
				alt47=7;
				}
				break;
			case 90:
				{
				alt47=8;
				}
				break;
			case 91:
				{
				alt47=9;
				}
				break;
			case 92:
				{
				alt47=10;
				}
				break;
			case 93:
				{
				alt47=11;
				}
				break;
			case 94:
				{
				alt47=12;
				}
				break;
			case 106:
				{
				alt47=13;
				}
				break;
			case 109:
				{
				alt47=14;
				}
				break;
			case 105:
				{
				alt47=15;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 47, 0, input);
				throw nvae;
			}
			switch (alt47) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:4: 'reverses down'
					{
					string_literal267=(Token)match(input,98,FOLLOW_98_in_presetcondition2587);  
					stream_98.add(string_literal267);

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
					// 302:20: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:23: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:42: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:70: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:99: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:303:7: ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					int alt41=2;
					int LA41_0 = input.LA(1);
					if ( (LA41_0==WhiteChar) ) {
						int LA41_1 = input.LA(2);
						if ( (LA41_1==95) ) {
							alt41=1;
						}
					}
					switch (alt41) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:303:9: WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar268=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2627);  
							stream_WhiteChar.add(WhiteChar268);

							string_literal269=(Token)match(input,95,FOLLOW_95_in_presetcondition2629);  
							stream_95.add(string_literal269);

							WhiteChar270=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2631);  
							stream_WhiteChar.add(WhiteChar270);

							pushFollow(FOLLOW_constant_in_presetcondition2635);
							percentdown=constant();
							state._fsp--;

							stream_constant.add(percentdown.getTree());
							PERCENT271=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2637);  
							stream_PERCENT.add(PERCENT271);

							WhiteChar272=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2639);  
							stream_WhiteChar.add(WhiteChar272);

							string_literal273=(Token)match(input,102,FOLLOW_102_in_presetcondition2641);  
							stream_102.add(string_literal273);

							WhiteChar274=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2643);  
							stream_WhiteChar.add(WhiteChar274);

							pushFollow(FOLLOW_constant_in_presetcondition2647);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar275=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2649);  
							stream_WhiteChar.add(WhiteChar275);

							DAYS276=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2651);  
							stream_DAYS.add(DAYS276);

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
							// 304:7: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:10: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:29: ^( Number NumberToken[\"-1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:305:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:305:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:305:4: 'reverses up'
					{
					string_literal277=(Token)match(input,99,FOLLOW_99_in_presetcondition2687);  
					stream_99.add(string_literal277);

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
					// 305:18: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:305:21: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:305:40: ^( Number NumberToken[\"1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:305:67: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:305:96: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:306:7: ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:306:9: WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar278=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2727);  
							stream_WhiteChar.add(WhiteChar278);

							string_literal279=(Token)match(input,95,FOLLOW_95_in_presetcondition2729);  
							stream_95.add(string_literal279);

							WhiteChar280=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2731);  
							stream_WhiteChar.add(WhiteChar280);

							pushFollow(FOLLOW_constant_in_presetcondition2735);
							percentup=constant();
							state._fsp--;

							stream_constant.add(percentup.getTree());
							PERCENT281=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2737);  
							stream_PERCENT.add(PERCENT281);

							WhiteChar282=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2739);  
							stream_WhiteChar.add(WhiteChar282);

							string_literal283=(Token)match(input,102,FOLLOW_102_in_presetcondition2741);  
							stream_102.add(string_literal283);

							WhiteChar284=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2743);  
							stream_WhiteChar.add(WhiteChar284);

							pushFollow(FOLLOW_constant_in_presetcondition2747);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar285=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2749);  
							stream_WhiteChar.add(WhiteChar285);

							DAYS286=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2751);  
							stream_DAYS.add(DAYS286);

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
							// 307:7: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:307:10: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:307:29: ^( Number NumberToken[\"1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:4: 'goes down more than' WhiteChar percentdown= constant PERCENT
					{
					string_literal287=(Token)match(input,78,FOLLOW_78_in_presetcondition2788);  
					stream_78.add(string_literal287);

					WhiteChar288=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2790);  
					stream_WhiteChar.add(WhiteChar288);

					pushFollow(FOLLOW_constant_in_presetcondition2794);
					percentdown=constant();
					state._fsp--;

					stream_constant.add(percentdown.getTree());
					PERCENT289=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2796);  
					stream_PERCENT.add(PERCENT289);

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
					// 309:65: -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:68: ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:98: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:127: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:156: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:185: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:310:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt43=2;
					int LA43_0 = input.LA(1);
					if ( (LA43_0==WhiteChar) ) {
						int LA43_1 = input.LA(2);
						if ( (LA43_1==102) ) {
							alt43=1;
						}
					}
					switch (alt43) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:310:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar290=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2845);  
							stream_WhiteChar.add(WhiteChar290);

							string_literal291=(Token)match(input,102,FOLLOW_102_in_presetcondition2847);  
							stream_102.add(string_literal291);

							WhiteChar292=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2849);  
							stream_WhiteChar.add(WhiteChar292);

							pushFollow(FOLLOW_constant_in_presetcondition2853);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar293=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2855);  
							stream_WhiteChar.add(WhiteChar293);

							DAYS294=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2857);  
							stream_DAYS.add(DAYS294);

							WhiteChar295=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2868);  
							stream_WhiteChar.add(WhiteChar295);

							string_literal296=(Token)match(input,77,FOLLOW_77_in_presetcondition2870);  
							stream_77.add(string_literal296);

							WhiteChar297=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2872);  
							stream_WhiteChar.add(WhiteChar297);

							pushFollow(FOLLOW_constant_in_presetcondition2876);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar298=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2878);  
							stream_WhiteChar.add(WhiteChar298);

							DAYS299=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2880);  
							stream_DAYS.add(DAYS299);

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
							// 312:7: -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:312:10: ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentdown!=null?((CommonTree)percentdown.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:312:74: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:312:121: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:4: 'goes up more than' WhiteChar percentup= constant PERCENT
					{
					string_literal300=(Token)match(input,79,FOLLOW_79_in_presetcondition2924);  
					stream_79.add(string_literal300);

					WhiteChar301=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2926);  
					stream_WhiteChar.add(WhiteChar301);

					pushFollow(FOLLOW_constant_in_presetcondition2930);
					percentup=constant();
					state._fsp--;

					stream_constant.add(percentup.getTree());
					PERCENT302=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2932);  
					stream_PERCENT.add(PERCENT302);

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
					// 313:61: -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:64: ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:92: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:121: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:150: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:179: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt44=2;
					int LA44_0 = input.LA(1);
					if ( (LA44_0==WhiteChar) ) {
						int LA44_1 = input.LA(2);
						if ( (LA44_1==102) ) {
							alt44=1;
						}
					}
					switch (alt44) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:314:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar303=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2981);  
							stream_WhiteChar.add(WhiteChar303);

							string_literal304=(Token)match(input,102,FOLLOW_102_in_presetcondition2983);  
							stream_102.add(string_literal304);

							WhiteChar305=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2985);  
							stream_WhiteChar.add(WhiteChar305);

							pushFollow(FOLLOW_constant_in_presetcondition2989);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar306=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2991);  
							stream_WhiteChar.add(WhiteChar306);

							DAYS307=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2993);  
							stream_DAYS.add(DAYS307);

							WhiteChar308=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3004);  
							stream_WhiteChar.add(WhiteChar308);

							string_literal309=(Token)match(input,77,FOLLOW_77_in_presetcondition3006);  
							stream_77.add(string_literal309);

							WhiteChar310=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3008);  
							stream_WhiteChar.add(WhiteChar310);

							pushFollow(FOLLOW_constant_in_presetcondition3012);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar311=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3014);  
							stream_WhiteChar.add(WhiteChar311);

							DAYS312=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3016);  
							stream_DAYS.add(DAYS312);

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
							// 316:7: -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:316:10: ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentup!=null?((CommonTree)percentup.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:316:70: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:316:117: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:4: 'crosses up threshold' WhiteChar threshold= constant
					{
					string_literal313=(Token)match(input,70,FOLLOW_70_in_presetcondition3061);  
					stream_70.add(string_literal313);

					WhiteChar314=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3063);  
					stream_WhiteChar.add(WhiteChar314);

					pushFollow(FOLLOW_constant_in_presetcondition3067);
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
					// 318:56: -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:59: ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:95: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:124: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:153: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:182: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:319:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt45=2;
					int LA45_0 = input.LA(1);
					if ( (LA45_0==WhiteChar) ) {
						int LA45_1 = input.LA(2);
						if ( (LA45_1==102) ) {
							alt45=1;
						}
					}
					switch (alt45) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:319:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar315=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3116);  
							stream_WhiteChar.add(WhiteChar315);

							string_literal316=(Token)match(input,102,FOLLOW_102_in_presetcondition3118);  
							stream_102.add(string_literal316);

							WhiteChar317=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3120);  
							stream_WhiteChar.add(WhiteChar317);

							pushFollow(FOLLOW_constant_in_presetcondition3124);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar318=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3126);  
							stream_WhiteChar.add(WhiteChar318);

							DAYS319=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3128);  
							stream_DAYS.add(DAYS319);

							WhiteChar320=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3139);  
							stream_WhiteChar.add(WhiteChar320);

							string_literal321=(Token)match(input,96,FOLLOW_96_in_presetcondition3141);  
							stream_96.add(string_literal321);

							WhiteChar322=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3143);  
							stream_WhiteChar.add(WhiteChar322);

							pushFollow(FOLLOW_constant_in_presetcondition3147);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar323=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3149);  
							stream_WhiteChar.add(WhiteChar323);

							DAYS324=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3151);  
							stream_DAYS.add(DAYS324);

							WhiteChar325=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3161);  
							stream_WhiteChar.add(WhiteChar325);

							string_literal326=(Token)match(input,73,FOLLOW_73_in_presetcondition3163);  
							stream_73.add(string_literal326);

							WhiteChar327=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3165);  
							stream_WhiteChar.add(WhiteChar327);

							pushFollow(FOLLOW_constant_in_presetcondition3169);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT328=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition3171);  
							stream_PERCENT.add(PERCENT328);

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
							// 322:7: -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:10: ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:97: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:323:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:323:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:323:4: 'crosses down threshold' WhiteChar threshold= constant
					{
					string_literal329=(Token)match(input,68,FOLLOW_68_in_presetcondition3210);  
					stream_68.add(string_literal329);

					WhiteChar330=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3212);  
					stream_WhiteChar.add(WhiteChar330);

					pushFollow(FOLLOW_constant_in_presetcondition3216);
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
					// 323:58: -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:323:61: ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:323:99: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:323:128: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:323:157: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:323:186: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt46=2;
					int LA46_0 = input.LA(1);
					if ( (LA46_0==WhiteChar) ) {
						int LA46_1 = input.LA(2);
						if ( (LA46_1==102) ) {
							alt46=1;
						}
					}
					switch (alt46) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar331=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3265);  
							stream_WhiteChar.add(WhiteChar331);

							string_literal332=(Token)match(input,102,FOLLOW_102_in_presetcondition3267);  
							stream_102.add(string_literal332);

							WhiteChar333=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3269);  
							stream_WhiteChar.add(WhiteChar333);

							pushFollow(FOLLOW_constant_in_presetcondition3273);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar334=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3275);  
							stream_WhiteChar.add(WhiteChar334);

							DAYS335=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3277);  
							stream_DAYS.add(DAYS335);

							WhiteChar336=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3288);  
							stream_WhiteChar.add(WhiteChar336);

							string_literal337=(Token)match(input,96,FOLLOW_96_in_presetcondition3290);  
							stream_96.add(string_literal337);

							WhiteChar338=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3292);  
							stream_WhiteChar.add(WhiteChar338);

							pushFollow(FOLLOW_constant_in_presetcondition3296);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar339=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3298);  
							stream_WhiteChar.add(WhiteChar339);

							DAYS340=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3300);  
							stream_DAYS.add(DAYS340);

							WhiteChar341=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3310);  
							stream_WhiteChar.add(WhiteChar341);

							string_literal342=(Token)match(input,73,FOLLOW_73_in_presetcondition3312);  
							stream_73.add(string_literal342);

							WhiteChar343=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3314);  
							stream_WhiteChar.add(WhiteChar343);

							pushFollow(FOLLOW_constant_in_presetcondition3318);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT344=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition3320);  
							stream_PERCENT.add(PERCENT344);

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
							// 327:7: -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:10: ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:99: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:329:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:329:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:329:4: 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal345=(Token)match(input,89,FOLLOW_89_in_presetcondition3360);  
					stream_89.add(string_literal345);

					WhiteChar346=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3362);  
					stream_WhiteChar.add(WhiteChar346);

					pushFollow(FOLLOW_constant_in_presetcondition3366);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar347=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3368);  
					stream_WhiteChar.add(WhiteChar347);

					DAYS348=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3370);  
					stream_DAYS.add(DAYS348);

					WhiteChar349=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3377);  
					stream_WhiteChar.add(WhiteChar349);

					string_literal350=(Token)match(input,96,FOLLOW_96_in_presetcondition3379);  
					stream_96.add(string_literal350);

					WhiteChar351=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3381);  
					stream_WhiteChar.add(WhiteChar351);

					pushFollow(FOLLOW_constant_in_presetcondition3385);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar352=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3387);  
					stream_WhiteChar.add(WhiteChar352);

					DAYS353=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3389);  
					stream_DAYS.add(DAYS353);

					WhiteChar354=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3396);  
					stream_WhiteChar.add(WhiteChar354);

					string_literal355=(Token)match(input,77,FOLLOW_77_in_presetcondition3398);  
					stream_77.add(string_literal355);

					WhiteChar356=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3400);  
					stream_WhiteChar.add(WhiteChar356);

					pushFollow(FOLLOW_constant_in_presetcondition3404);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar357=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3406);  
					stream_WhiteChar.add(WhiteChar357);

					DAYS358=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3408);  
					stream_DAYS.add(DAYS358);

					WhiteChar359=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3415);  
					stream_WhiteChar.add(WhiteChar359);

					string_literal360=(Token)match(input,101,FOLLOW_101_in_presetcondition3417);  
					stream_101.add(string_literal360);

					WhiteChar361=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3419);  
					stream_WhiteChar.add(WhiteChar361);

					pushFollow(FOLLOW_constant_in_presetcondition3423);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar362=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3425);  
					stream_WhiteChar.add(WhiteChar362);

					DAYS363=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3427);  
					stream_DAYS.add(DAYS363);

					WhiteChar364=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3434);  
					stream_WhiteChar.add(WhiteChar364);

					string_literal365=(Token)match(input,80,FOLLOW_80_in_presetcondition3436);  
					stream_80.add(string_literal365);

					WhiteChar366=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3438);  
					stream_WhiteChar.add(WhiteChar366);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3442);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar367=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3448);  
					stream_WhiteChar.add(WhiteChar367);

					string_literal368=(Token)match(input,110,FOLLOW_110_in_presetcondition3450);  
					stream_110.add(string_literal368);

					WhiteChar369=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3452);  
					stream_WhiteChar.add(WhiteChar369);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3456);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar370=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3462);  
					stream_WhiteChar.add(WhiteChar370);

					string_literal371=(Token)match(input,103,FOLLOW_103_in_presetcondition3464);  
					stream_103.add(string_literal371);

					WhiteChar372=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3466);  
					stream_WhiteChar.add(WhiteChar372);

					char_literal373=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3468);  
					stream_OPENSQRT.add(char_literal373);

					pushFollow(FOLLOW_constant_in_presetcondition3472);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal374=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3474);  
					stream_COMMA.add(char_literal374);

					pushFollow(FOLLOW_constant_in_presetcondition3478);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal375=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3480);  
					stream_CLOSESQRT.add(char_literal375);

					WhiteChar376=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3482);  
					stream_WhiteChar.add(WhiteChar376);

					string_literal377=(Token)match(input,72,FOLLOW_72_in_presetcondition3484);  
					stream_72.add(string_literal377);

					WhiteChar378=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3486);  
					stream_WhiteChar.add(WhiteChar378);

					char_literal379=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3488);  
					stream_OPENSQRT.add(char_literal379);

					pushFollow(FOLLOW_constant_in_presetcondition3492);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal380=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3494);  
					stream_COMMA.add(char_literal380);

					pushFollow(FOLLOW_constant_in_presetcondition3498);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal381=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3500);  
					stream_CLOSESQRT.add(char_literal381);

					WhiteChar382=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3506);  
					stream_WhiteChar.add(WhiteChar382);

					string_literal383=(Token)match(input,100,FOLLOW_100_in_presetcondition3508);  
					stream_100.add(string_literal383);

					WhiteChar384=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3510);  
					stream_WhiteChar.add(WhiteChar384);

					char_literal385=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3512);  
					stream_OPENSQRT.add(char_literal385);

					pushFollow(FOLLOW_constant_in_presetcondition3516);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal386=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3518);  
					stream_COMMA.add(char_literal386);

					pushFollow(FOLLOW_constant_in_presetcondition3522);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal387=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3524);  
					stream_CLOSESQRT.add(char_literal387);

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
					// 337:4: -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:337:7: ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:337:246: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:338:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:338:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:338:4: 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal388=(Token)match(input,90,FOLLOW_90_in_presetcondition3575);  
					stream_90.add(string_literal388);

					WhiteChar389=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3577);  
					stream_WhiteChar.add(WhiteChar389);

					pushFollow(FOLLOW_constant_in_presetcondition3581);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar390=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3583);  
					stream_WhiteChar.add(WhiteChar390);

					DAYS391=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3585);  
					stream_DAYS.add(DAYS391);

					WhiteChar392=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3592);  
					stream_WhiteChar.add(WhiteChar392);

					string_literal393=(Token)match(input,96,FOLLOW_96_in_presetcondition3594);  
					stream_96.add(string_literal393);

					WhiteChar394=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3596);  
					stream_WhiteChar.add(WhiteChar394);

					pushFollow(FOLLOW_constant_in_presetcondition3600);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar395=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3602);  
					stream_WhiteChar.add(WhiteChar395);

					DAYS396=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3604);  
					stream_DAYS.add(DAYS396);

					WhiteChar397=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3611);  
					stream_WhiteChar.add(WhiteChar397);

					string_literal398=(Token)match(input,77,FOLLOW_77_in_presetcondition3613);  
					stream_77.add(string_literal398);

					WhiteChar399=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3615);  
					stream_WhiteChar.add(WhiteChar399);

					pushFollow(FOLLOW_constant_in_presetcondition3619);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar400=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3621);  
					stream_WhiteChar.add(WhiteChar400);

					DAYS401=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3623);  
					stream_DAYS.add(DAYS401);

					WhiteChar402=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3630);  
					stream_WhiteChar.add(WhiteChar402);

					string_literal403=(Token)match(input,101,FOLLOW_101_in_presetcondition3632);  
					stream_101.add(string_literal403);

					WhiteChar404=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3634);  
					stream_WhiteChar.add(WhiteChar404);

					pushFollow(FOLLOW_constant_in_presetcondition3638);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar405=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3640);  
					stream_WhiteChar.add(WhiteChar405);

					DAYS406=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3642);  
					stream_DAYS.add(DAYS406);

					WhiteChar407=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3649);  
					stream_WhiteChar.add(WhiteChar407);

					string_literal408=(Token)match(input,80,FOLLOW_80_in_presetcondition3651);  
					stream_80.add(string_literal408);

					WhiteChar409=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3653);  
					stream_WhiteChar.add(WhiteChar409);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3657);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar410=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3663);  
					stream_WhiteChar.add(WhiteChar410);

					string_literal411=(Token)match(input,110,FOLLOW_110_in_presetcondition3665);  
					stream_110.add(string_literal411);

					WhiteChar412=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3667);  
					stream_WhiteChar.add(WhiteChar412);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3671);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar413=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3677);  
					stream_WhiteChar.add(WhiteChar413);

					string_literal414=(Token)match(input,103,FOLLOW_103_in_presetcondition3679);  
					stream_103.add(string_literal414);

					WhiteChar415=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3681);  
					stream_WhiteChar.add(WhiteChar415);

					char_literal416=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3683);  
					stream_OPENSQRT.add(char_literal416);

					pushFollow(FOLLOW_constant_in_presetcondition3687);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal417=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3689);  
					stream_COMMA.add(char_literal417);

					pushFollow(FOLLOW_constant_in_presetcondition3693);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal418=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3695);  
					stream_CLOSESQRT.add(char_literal418);

					WhiteChar419=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3697);  
					stream_WhiteChar.add(WhiteChar419);

					string_literal420=(Token)match(input,72,FOLLOW_72_in_presetcondition3699);  
					stream_72.add(string_literal420);

					WhiteChar421=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3701);  
					stream_WhiteChar.add(WhiteChar421);

					char_literal422=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3703);  
					stream_OPENSQRT.add(char_literal422);

					pushFollow(FOLLOW_constant_in_presetcondition3707);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal423=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3709);  
					stream_COMMA.add(char_literal423);

					pushFollow(FOLLOW_constant_in_presetcondition3713);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal424=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3715);  
					stream_CLOSESQRT.add(char_literal424);

					WhiteChar425=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3721);  
					stream_WhiteChar.add(WhiteChar425);

					string_literal426=(Token)match(input,100,FOLLOW_100_in_presetcondition3723);  
					stream_100.add(string_literal426);

					WhiteChar427=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3725);  
					stream_WhiteChar.add(WhiteChar427);

					char_literal428=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3727);  
					stream_OPENSQRT.add(char_literal428);

					pushFollow(FOLLOW_constant_in_presetcondition3731);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal429=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3733);  
					stream_COMMA.add(char_literal429);

					pushFollow(FOLLOW_constant_in_presetcondition3737);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal430=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3739);  
					stream_CLOSESQRT.add(char_literal430);

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
					// 346:4: -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:346:7: ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:346:245: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:347:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:347:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:347:4: 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal431=(Token)match(input,91,FOLLOW_91_in_presetcondition3790);  
					stream_91.add(string_literal431);

					WhiteChar432=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3792);  
					stream_WhiteChar.add(WhiteChar432);

					pushFollow(FOLLOW_constant_in_presetcondition3796);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar433=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3798);  
					stream_WhiteChar.add(WhiteChar433);

					DAYS434=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3800);  
					stream_DAYS.add(DAYS434);

					WhiteChar435=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3807);  
					stream_WhiteChar.add(WhiteChar435);

					string_literal436=(Token)match(input,96,FOLLOW_96_in_presetcondition3809);  
					stream_96.add(string_literal436);

					WhiteChar437=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3811);  
					stream_WhiteChar.add(WhiteChar437);

					pushFollow(FOLLOW_constant_in_presetcondition3815);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar438=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3817);  
					stream_WhiteChar.add(WhiteChar438);

					DAYS439=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3819);  
					stream_DAYS.add(DAYS439);

					WhiteChar440=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3826);  
					stream_WhiteChar.add(WhiteChar440);

					string_literal441=(Token)match(input,77,FOLLOW_77_in_presetcondition3828);  
					stream_77.add(string_literal441);

					WhiteChar442=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3830);  
					stream_WhiteChar.add(WhiteChar442);

					pushFollow(FOLLOW_constant_in_presetcondition3834);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar443=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3836);  
					stream_WhiteChar.add(WhiteChar443);

					DAYS444=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3838);  
					stream_DAYS.add(DAYS444);

					WhiteChar445=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3845);  
					stream_WhiteChar.add(WhiteChar445);

					string_literal446=(Token)match(input,101,FOLLOW_101_in_presetcondition3847);  
					stream_101.add(string_literal446);

					WhiteChar447=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3849);  
					stream_WhiteChar.add(WhiteChar447);

					pushFollow(FOLLOW_constant_in_presetcondition3853);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar448=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3855);  
					stream_WhiteChar.add(WhiteChar448);

					DAYS449=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3857);  
					stream_DAYS.add(DAYS449);

					WhiteChar450=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3864);  
					stream_WhiteChar.add(WhiteChar450);

					string_literal451=(Token)match(input,80,FOLLOW_80_in_presetcondition3866);  
					stream_80.add(string_literal451);

					WhiteChar452=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3868);  
					stream_WhiteChar.add(WhiteChar452);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3872);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar453=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3878);  
					stream_WhiteChar.add(WhiteChar453);

					string_literal454=(Token)match(input,110,FOLLOW_110_in_presetcondition3880);  
					stream_110.add(string_literal454);

					WhiteChar455=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3882);  
					stream_WhiteChar.add(WhiteChar455);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3886);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar456=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3892);  
					stream_WhiteChar.add(WhiteChar456);

					string_literal457=(Token)match(input,103,FOLLOW_103_in_presetcondition3894);  
					stream_103.add(string_literal457);

					WhiteChar458=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3896);  
					stream_WhiteChar.add(WhiteChar458);

					char_literal459=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3898);  
					stream_OPENSQRT.add(char_literal459);

					pushFollow(FOLLOW_constant_in_presetcondition3902);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal460=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3904);  
					stream_COMMA.add(char_literal460);

					pushFollow(FOLLOW_constant_in_presetcondition3908);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal461=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3910);  
					stream_CLOSESQRT.add(char_literal461);

					WhiteChar462=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3912);  
					stream_WhiteChar.add(WhiteChar462);

					string_literal463=(Token)match(input,72,FOLLOW_72_in_presetcondition3914);  
					stream_72.add(string_literal463);

					WhiteChar464=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3916);  
					stream_WhiteChar.add(WhiteChar464);

					char_literal465=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3918);  
					stream_OPENSQRT.add(char_literal465);

					pushFollow(FOLLOW_constant_in_presetcondition3922);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal466=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3924);  
					stream_COMMA.add(char_literal466);

					pushFollow(FOLLOW_constant_in_presetcondition3928);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal467=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3930);  
					stream_CLOSESQRT.add(char_literal467);

					WhiteChar468=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3936);  
					stream_WhiteChar.add(WhiteChar468);

					string_literal469=(Token)match(input,100,FOLLOW_100_in_presetcondition3938);  
					stream_100.add(string_literal469);

					WhiteChar470=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3940);  
					stream_WhiteChar.add(WhiteChar470);

					char_literal471=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3942);  
					stream_OPENSQRT.add(char_literal471);

					pushFollow(FOLLOW_constant_in_presetcondition3946);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal472=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3948);  
					stream_COMMA.add(char_literal472);

					pushFollow(FOLLOW_constant_in_presetcondition3952);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal473=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3954);  
					stream_CLOSESQRT.add(char_literal473);

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
					// 355:4: -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:355:7: ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) )
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
				case 10 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:356:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:356:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:356:4: 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal474=(Token)match(input,92,FOLLOW_92_in_presetcondition4005);  
					stream_92.add(string_literal474);

					WhiteChar475=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4007);  
					stream_WhiteChar.add(WhiteChar475);

					pushFollow(FOLLOW_constant_in_presetcondition4011);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar476=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4013);  
					stream_WhiteChar.add(WhiteChar476);

					DAYS477=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4015);  
					stream_DAYS.add(DAYS477);

					WhiteChar478=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4022);  
					stream_WhiteChar.add(WhiteChar478);

					string_literal479=(Token)match(input,96,FOLLOW_96_in_presetcondition4024);  
					stream_96.add(string_literal479);

					WhiteChar480=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4026);  
					stream_WhiteChar.add(WhiteChar480);

					pushFollow(FOLLOW_constant_in_presetcondition4030);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar481=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4032);  
					stream_WhiteChar.add(WhiteChar481);

					DAYS482=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4034);  
					stream_DAYS.add(DAYS482);

					WhiteChar483=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4041);  
					stream_WhiteChar.add(WhiteChar483);

					string_literal484=(Token)match(input,77,FOLLOW_77_in_presetcondition4043);  
					stream_77.add(string_literal484);

					WhiteChar485=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4045);  
					stream_WhiteChar.add(WhiteChar485);

					pushFollow(FOLLOW_constant_in_presetcondition4049);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar486=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4051);  
					stream_WhiteChar.add(WhiteChar486);

					DAYS487=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4053);  
					stream_DAYS.add(DAYS487);

					WhiteChar488=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4060);  
					stream_WhiteChar.add(WhiteChar488);

					string_literal489=(Token)match(input,101,FOLLOW_101_in_presetcondition4062);  
					stream_101.add(string_literal489);

					WhiteChar490=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4064);  
					stream_WhiteChar.add(WhiteChar490);

					pushFollow(FOLLOW_constant_in_presetcondition4068);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar491=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4070);  
					stream_WhiteChar.add(WhiteChar491);

					DAYS492=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4072);  
					stream_DAYS.add(DAYS492);

					WhiteChar493=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4079);  
					stream_WhiteChar.add(WhiteChar493);

					string_literal494=(Token)match(input,80,FOLLOW_80_in_presetcondition4081);  
					stream_80.add(string_literal494);

					WhiteChar495=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4083);  
					stream_WhiteChar.add(WhiteChar495);

					pushFollow(FOLLOW_stringconstant_in_presetcondition4087);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar496=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4093);  
					stream_WhiteChar.add(WhiteChar496);

					string_literal497=(Token)match(input,110,FOLLOW_110_in_presetcondition4095);  
					stream_110.add(string_literal497);

					WhiteChar498=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4097);  
					stream_WhiteChar.add(WhiteChar498);

					pushFollow(FOLLOW_stringconstant_in_presetcondition4101);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar499=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4107);  
					stream_WhiteChar.add(WhiteChar499);

					string_literal500=(Token)match(input,103,FOLLOW_103_in_presetcondition4109);  
					stream_103.add(string_literal500);

					WhiteChar501=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4111);  
					stream_WhiteChar.add(WhiteChar501);

					char_literal502=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4113);  
					stream_OPENSQRT.add(char_literal502);

					pushFollow(FOLLOW_constant_in_presetcondition4117);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal503=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4119);  
					stream_COMMA.add(char_literal503);

					pushFollow(FOLLOW_constant_in_presetcondition4123);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal504=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4125);  
					stream_CLOSESQRT.add(char_literal504);

					WhiteChar505=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4127);  
					stream_WhiteChar.add(WhiteChar505);

					string_literal506=(Token)match(input,72,FOLLOW_72_in_presetcondition4129);  
					stream_72.add(string_literal506);

					WhiteChar507=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4131);  
					stream_WhiteChar.add(WhiteChar507);

					char_literal508=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4133);  
					stream_OPENSQRT.add(char_literal508);

					pushFollow(FOLLOW_constant_in_presetcondition4137);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal509=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4139);  
					stream_COMMA.add(char_literal509);

					pushFollow(FOLLOW_constant_in_presetcondition4143);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal510=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4145);  
					stream_CLOSESQRT.add(char_literal510);

					WhiteChar511=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4151);  
					stream_WhiteChar.add(WhiteChar511);

					string_literal512=(Token)match(input,100,FOLLOW_100_in_presetcondition4153);  
					stream_100.add(string_literal512);

					WhiteChar513=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4155);  
					stream_WhiteChar.add(WhiteChar513);

					char_literal514=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4157);  
					stream_OPENSQRT.add(char_literal514);

					pushFollow(FOLLOW_constant_in_presetcondition4161);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal515=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4163);  
					stream_COMMA.add(char_literal515);

					pushFollow(FOLLOW_constant_in_presetcondition4167);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal516=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4169);  
					stream_CLOSESQRT.add(char_literal516);

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
					// 364:4: -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:364:7: ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:364:244: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:366:4: ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:366:4: ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:366:5: 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant
					{
					string_literal517=(Token)match(input,93,FOLLOW_93_in_presetcondition4222);  
					stream_93.add(string_literal517);

					WhiteChar518=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4224);  
					stream_WhiteChar.add(WhiteChar518);

					pushFollow(FOLLOW_constant_in_presetcondition4228);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar519=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4230);  
					stream_WhiteChar.add(WhiteChar519);

					DAYS520=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4232);  
					stream_DAYS.add(DAYS520);

					WhiteChar521=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4239);  
					stream_WhiteChar.add(WhiteChar521);

					string_literal522=(Token)match(input,96,FOLLOW_96_in_presetcondition4241);  
					stream_96.add(string_literal522);

					WhiteChar523=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4243);  
					stream_WhiteChar.add(WhiteChar523);

					pushFollow(FOLLOW_constant_in_presetcondition4247);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar524=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4249);  
					stream_WhiteChar.add(WhiteChar524);

					DAYS525=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4251);  
					stream_DAYS.add(DAYS525);

					WhiteChar526=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4258);  
					stream_WhiteChar.add(WhiteChar526);

					string_literal527=(Token)match(input,77,FOLLOW_77_in_presetcondition4260);  
					stream_77.add(string_literal527);

					WhiteChar528=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4262);  
					stream_WhiteChar.add(WhiteChar528);

					pushFollow(FOLLOW_constant_in_presetcondition4266);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar529=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4268);  
					stream_WhiteChar.add(WhiteChar529);

					DAYS530=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4270);  
					stream_DAYS.add(DAYS530);

					WhiteChar531=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4277);  
					stream_WhiteChar.add(WhiteChar531);

					string_literal532=(Token)match(input,101,FOLLOW_101_in_presetcondition4279);  
					stream_101.add(string_literal532);

					WhiteChar533=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4281);  
					stream_WhiteChar.add(WhiteChar533);

					pushFollow(FOLLOW_constant_in_presetcondition4285);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar534=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4287);  
					stream_WhiteChar.add(WhiteChar534);

					DAYS535=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4289);  
					stream_DAYS.add(DAYS535);

					WhiteChar536=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4296);  
					stream_WhiteChar.add(WhiteChar536);

					string_literal537=(Token)match(input,103,FOLLOW_103_in_presetcondition4298);  
					stream_103.add(string_literal537);

					WhiteChar538=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4300);  
					stream_WhiteChar.add(WhiteChar538);

					char_literal539=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4302);  
					stream_OPENSQRT.add(char_literal539);

					pushFollow(FOLLOW_constant_in_presetcondition4306);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal540=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4308);  
					stream_COMMA.add(char_literal540);

					pushFollow(FOLLOW_constant_in_presetcondition4312);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal541=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4314);  
					stream_CLOSESQRT.add(char_literal541);

					WhiteChar542=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4320);  
					stream_WhiteChar.add(WhiteChar542);

					string_literal543=(Token)match(input,104,FOLLOW_104_in_presetcondition4322);  
					stream_104.add(string_literal543);

					WhiteChar544=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4324);  
					stream_WhiteChar.add(WhiteChar544);

					pushFollow(FOLLOW_constant_in_presetcondition4328);
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
					// 372:4: -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:372:7: ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupportBreakDown, "SupportBreakDown"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:372:104: ^( String StringToken[\"\\\"greedy\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"greedy\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:372:140: ^( String StringToken[\"\\\"smooth\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"smooth\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:372:217: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:372:246: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:372:275: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:372:304: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:373:4: ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:373:4: ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:373:5: 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant
					{
					string_literal545=(Token)match(input,94,FOLLOW_94_in_presetcondition4405);  
					stream_94.add(string_literal545);

					WhiteChar546=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4407);  
					stream_WhiteChar.add(WhiteChar546);

					pushFollow(FOLLOW_constant_in_presetcondition4411);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar547=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4413);  
					stream_WhiteChar.add(WhiteChar547);

					DAYS548=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4415);  
					stream_DAYS.add(DAYS548);

					WhiteChar549=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4425);  
					stream_WhiteChar.add(WhiteChar549);

					string_literal550=(Token)match(input,96,FOLLOW_96_in_presetcondition4427);  
					stream_96.add(string_literal550);

					WhiteChar551=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4429);  
					stream_WhiteChar.add(WhiteChar551);

					pushFollow(FOLLOW_constant_in_presetcondition4433);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar552=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4435);  
					stream_WhiteChar.add(WhiteChar552);

					DAYS553=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4437);  
					stream_DAYS.add(DAYS553);

					WhiteChar554=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4447);  
					stream_WhiteChar.add(WhiteChar554);

					string_literal555=(Token)match(input,77,FOLLOW_77_in_presetcondition4449);  
					stream_77.add(string_literal555);

					WhiteChar556=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4451);  
					stream_WhiteChar.add(WhiteChar556);

					pushFollow(FOLLOW_constant_in_presetcondition4455);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar557=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4457);  
					stream_WhiteChar.add(WhiteChar557);

					DAYS558=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4459);  
					stream_DAYS.add(DAYS558);

					WhiteChar559=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4469);  
					stream_WhiteChar.add(WhiteChar559);

					string_literal560=(Token)match(input,101,FOLLOW_101_in_presetcondition4471);  
					stream_101.add(string_literal560);

					WhiteChar561=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4473);  
					stream_WhiteChar.add(WhiteChar561);

					pushFollow(FOLLOW_constant_in_presetcondition4477);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar562=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4479);  
					stream_WhiteChar.add(WhiteChar562);

					DAYS563=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4481);  
					stream_DAYS.add(DAYS563);

					WhiteChar564=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4491);  
					stream_WhiteChar.add(WhiteChar564);

					string_literal565=(Token)match(input,103,FOLLOW_103_in_presetcondition4493);  
					stream_103.add(string_literal565);

					WhiteChar566=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4495);  
					stream_WhiteChar.add(WhiteChar566);

					char_literal567=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4497);  
					stream_OPENSQRT.add(char_literal567);

					pushFollow(FOLLOW_constant_in_presetcondition4501);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal568=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4503);  
					stream_COMMA.add(char_literal568);

					pushFollow(FOLLOW_constant_in_presetcondition4507);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal569=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4509);  
					stream_CLOSESQRT.add(char_literal569);

					WhiteChar570=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4519);  
					stream_WhiteChar.add(WhiteChar570);

					string_literal571=(Token)match(input,104,FOLLOW_104_in_presetcondition4521);  
					stream_104.add(string_literal571);

					WhiteChar572=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4523);  
					stream_WhiteChar.add(WhiteChar572);

					pushFollow(FOLLOW_constant_in_presetcondition4527);
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
					// 379:6: -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:379:9: ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupportBreakUp, "SupportBreakUp"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:379:104: ^( String StringToken[\"\\\"greedy\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"greedy\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:379:140: ^( String StringToken[\"\\\"smooth\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"smooth\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:379:217: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:379:246: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:379:275: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:379:304: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:4: 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal573=(Token)match(input,106,FOLLOW_106_in_presetcondition4606);  
					stream_106.add(string_literal573);

					WhiteChar574=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4614);  
					stream_WhiteChar.add(WhiteChar574);

					string_literal575=(Token)match(input,96,FOLLOW_96_in_presetcondition4616);  
					stream_96.add(string_literal575);

					WhiteChar576=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4618);  
					stream_WhiteChar.add(WhiteChar576);

					pushFollow(FOLLOW_constant_in_presetcondition4622);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar577=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4624);  
					stream_WhiteChar.add(WhiteChar577);

					DAYS578=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4626);  
					stream_DAYS.add(DAYS578);

					WhiteChar579=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4634);  
					stream_WhiteChar.add(WhiteChar579);

					string_literal580=(Token)match(input,77,FOLLOW_77_in_presetcondition4636);  
					stream_77.add(string_literal580);

					WhiteChar581=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4638);  
					stream_WhiteChar.add(WhiteChar581);

					pushFollow(FOLLOW_constant_in_presetcondition4642);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar582=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4644);  
					stream_WhiteChar.add(WhiteChar582);

					DAYS583=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4646);  
					stream_DAYS.add(DAYS583);

					WhiteChar584=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4654);  
					stream_WhiteChar.add(WhiteChar584);

					string_literal585=(Token)match(input,73,FOLLOW_73_in_presetcondition4656);  
					stream_73.add(string_literal585);

					WhiteChar586=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4658);  
					stream_WhiteChar.add(WhiteChar586);

					pushFollow(FOLLOW_constant_in_presetcondition4662);
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
					// 385:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:385:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:385:79: ^( String StringToken[\"\\\"flat\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:386:3: ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:386:3: ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:386:4: 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal587=(Token)match(input,109,FOLLOW_109_in_presetcondition4697);  
					stream_109.add(string_literal587);

					WhiteChar588=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4705);  
					stream_WhiteChar.add(WhiteChar588);

					string_literal589=(Token)match(input,96,FOLLOW_96_in_presetcondition4707);  
					stream_96.add(string_literal589);

					WhiteChar590=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4709);  
					stream_WhiteChar.add(WhiteChar590);

					pushFollow(FOLLOW_constant_in_presetcondition4713);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar591=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4715);  
					stream_WhiteChar.add(WhiteChar591);

					DAYS592=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4717);  
					stream_DAYS.add(DAYS592);

					WhiteChar593=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4725);  
					stream_WhiteChar.add(WhiteChar593);

					string_literal594=(Token)match(input,77,FOLLOW_77_in_presetcondition4727);  
					stream_77.add(string_literal594);

					WhiteChar595=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4729);  
					stream_WhiteChar.add(WhiteChar595);

					pushFollow(FOLLOW_constant_in_presetcondition4733);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar596=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4735);  
					stream_WhiteChar.add(WhiteChar596);

					DAYS597=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4737);  
					stream_DAYS.add(DAYS597);

					WhiteChar598=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4745);  
					stream_WhiteChar.add(WhiteChar598);

					string_literal599=(Token)match(input,73,FOLLOW_73_in_presetcondition4747);  
					stream_73.add(string_literal599);

					WhiteChar600=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4749);  
					stream_WhiteChar.add(WhiteChar600);

					pushFollow(FOLLOW_constant_in_presetcondition4753);
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
					// 390:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:390:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:390:79: ^( String StringToken[\"\\\"up\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:391:3: ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:391:3: ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:391:4: 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal601=(Token)match(input,105,FOLLOW_105_in_presetcondition4788);  
					stream_105.add(string_literal601);

					WhiteChar602=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4796);  
					stream_WhiteChar.add(WhiteChar602);

					string_literal603=(Token)match(input,96,FOLLOW_96_in_presetcondition4798);  
					stream_96.add(string_literal603);

					WhiteChar604=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4800);  
					stream_WhiteChar.add(WhiteChar604);

					pushFollow(FOLLOW_constant_in_presetcondition4804);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar605=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4806);  
					stream_WhiteChar.add(WhiteChar605);

					DAYS606=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4808);  
					stream_DAYS.add(DAYS606);

					WhiteChar607=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4816);  
					stream_WhiteChar.add(WhiteChar607);

					string_literal608=(Token)match(input,77,FOLLOW_77_in_presetcondition4818);  
					stream_77.add(string_literal608);

					WhiteChar609=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4820);  
					stream_WhiteChar.add(WhiteChar609);

					pushFollow(FOLLOW_constant_in_presetcondition4824);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar610=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4826);  
					stream_WhiteChar.add(WhiteChar610);

					DAYS611=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4828);  
					stream_DAYS.add(DAYS611);

					WhiteChar612=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4836);  
					stream_WhiteChar.add(WhiteChar612);

					string_literal613=(Token)match(input,73,FOLLOW_73_in_presetcondition4838);  
					stream_73.add(string_literal613);

					WhiteChar614=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4840);  
					stream_WhiteChar.add(WhiteChar614);

					pushFollow(FOLLOW_constant_in_presetcondition4844);
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
					// 395:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:395:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:395:79: ^( String StringToken[\"\\\"down\\\"\"] )
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
	public static final BitSet FOLLOW_bearish_condition_in_complete_expression438 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000001L});
	public static final BitSet FOLLOW_also_display_in_complete_expression441 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_fixed_start_shift_in_complete_expression443 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_87_in_bullish_condition472 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition474 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bullish_condition476 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition478 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bullish_condition481 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition483 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_84_in_bearish_condition499 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition501 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_condition503 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition505 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bearish_condition508 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition510 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_bearish_not_bullish_in_bearish_condition520 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition523 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bearish_condition526 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition528 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_64_in_also_display545 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display547 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_also_display549 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display551 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_also_display554 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_97_in_fixed_start_shift589 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift591 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_fixed_start_shift595 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift597 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_fixed_start_shift599 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_fixed_start_shift601 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_83_in_bearish_not_bullish630 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish638 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_bearish_not_bullish640 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish642 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish644 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish677 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OR_in_bearish_not_bullish679 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish681 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish683 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_and_expression_in_primary_expression746 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_expression_in_and_expression758 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_lenient_in_and_expression762 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression765 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_and_expression767 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression769 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_or_expression_in_and_expression771 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_matches_expression_in_or_expression798 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression801 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OR_in_or_expression803 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression805 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_matches_expression_in_or_expression807 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_atom_in_matches_expression839 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression842 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_MATCHING_in_matches_expression844 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression846 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_matches_expression848 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_matches_expression850 = new BitSet(new long[]{0x0000000000000180L});
	public static final BitSet FOLLOW_COMMA_in_matches_expression853 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_matches_expression855 = new BitSet(new long[]{0x0000000000000180L});
	public static final BitSet FOLLOW_CLOSESQRT_in_matches_expression859 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression861 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_atom_in_matches_expression863 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_booleanhistory_in_atom891 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom897 = new BitSet(new long[]{0x2100048400400000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom899 = new BitSet(new long[]{0x2100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_atom902 = new BitSet(new long[]{0x2000000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom904 = new BitSet(new long[]{0x2000000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom907 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_atom917 = new BitSet(new long[]{0x2000008000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom919 = new BitSet(new long[]{0x2000008000000000L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom922 = new BitSet(new long[]{0x2100048400400000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom924 = new BitSet(new long[]{0x2100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_atom927 = new BitSet(new long[]{0x2000000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom929 = new BitSet(new long[]{0x2000000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom932 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_conjunctiontruthof_in_atom953 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUTHOF_in_conjunctiontruthof965 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof967 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_conjunctiontruthof969 = new BitSet(new long[]{0x2000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_conjunctiontruthof972 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof974 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_conjunctiontruthof976 = new BitSet(new long[]{0x2000000000000100L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof980 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_88_in_conjunctiontruthof982 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof984 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_conjunctiontruthof986 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_conjunctiontruthof990 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_conjunctiontruthof992 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_conjunctiontruthof996 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_conjunctiontruthof998 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_booleanhistory1037 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_booleanhistory1039 = new BitSet(new long[]{0x0000000000000000L,0x00003E0C7E66DC78L});
	public static final BitSet FOLLOW_presetcondition_in_booleanhistory1045 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_opcmpcondition_in_booleanhistory1056 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constantcmp_in_booleanhistory1067 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_operand1083 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Operation_in_operand1110 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NumberToken_in_constant1125 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_62_in_constant1137 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_StringToken_in_stringconstant1153 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_66_in_trendconstant1168 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_trendconstant1181 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_lenient1198 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_LENIENT_in_lenient1200 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_81_in_opcmpcondition1237 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1239 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1243 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1274 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_opcmpcondition1276 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1278 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1282 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1284 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1286 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1288 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_opcmpcondition1290 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1292 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1296 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1298 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_85_in_opcmpcondition1330 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1332 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1336 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1367 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_opcmpcondition1369 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1371 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1375 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1377 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1379 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1381 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_opcmpcondition1383 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1385 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1389 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1391 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_74_in_opcmpcondition1422 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1424 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1428 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1459 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_opcmpcondition1461 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1463 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1467 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1469 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1471 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1473 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_opcmpcondition1475 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1477 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1481 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1483 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_67_in_opcmpcondition1516 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1518 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1522 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1576 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_opcmpcondition1578 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1580 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1584 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1586 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1588 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1590 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_opcmpcondition1592 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1594 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1598 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1600 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1602 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1604 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_opcmpcondition1606 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1608 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1612 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1614 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1654 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_63_in_opcmpcondition1656 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1658 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1662 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1664 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1666 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_atom_in_opcmpcondition1670 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_opcmpcondition1713 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1715 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1719 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1773 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_opcmpcondition1775 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1777 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1781 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1783 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1785 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1787 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_opcmpcondition1789 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1791 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1795 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1797 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1799 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1801 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_opcmpcondition1803 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1805 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1809 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1811 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1851 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_63_in_opcmpcondition1853 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1855 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1859 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1861 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1863 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_atom_in_opcmpcondition1867 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_107_in_opcmpcondition1912 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1914 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1918 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1926 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_opcmpcondition1928 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1930 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1934 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1936 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1938 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1946 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_opcmpcondition1948 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1950 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1954 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1956 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1958 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1966 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_opcmpcondition1968 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1970 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition1974 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1982 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_opcmpcondition1984 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1986 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1990 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_108_in_opcmpcondition2022 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2024 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition2028 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2036 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_opcmpcondition2038 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2040 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2044 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2046 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition2048 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2056 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_opcmpcondition2058 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2060 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2064 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2066 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition2068 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2076 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_opcmpcondition2078 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2080 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition2084 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_76_in_constantcmp2121 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2123 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000006L});
	public static final BitSet FOLLOW_trendconstant_in_constantcmp2127 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2161 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_constantcmp2163 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2165 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2169 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2171 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2173 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2175 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_constantcmp2177 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2179 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2183 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2185 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2187 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_75_in_constantcmp2211 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2213 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2217 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2258 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_constantcmp2260 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2262 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2266 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2268 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2270 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2276 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_constantcmp2278 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2280 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2284 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2286 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2288 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2294 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_constantcmp2296 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2298 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2302 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2304 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_82_in_constantcmp2334 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2336 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2340 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2381 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_constantcmp2383 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2385 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2389 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2391 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2393 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2399 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_constantcmp2401 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2403 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2407 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2409 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2411 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2417 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_constantcmp2419 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2421 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2425 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2427 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_86_in_constantcmp2457 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2459 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2463 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2504 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_constantcmp2506 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2508 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2512 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2514 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2516 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2522 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_constantcmp2524 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2526 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2530 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2532 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2534 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2540 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_constantcmp2542 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2544 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2548 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2550 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_98_in_presetcondition2587 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2627 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition2629 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2631 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2635 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2637 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2639 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition2641 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2643 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2647 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2649 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2651 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_99_in_presetcondition2687 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2727 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition2729 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2731 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2735 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2737 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2739 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition2741 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2743 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2747 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2749 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2751 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_78_in_presetcondition2788 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2790 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2794 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2796 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2845 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition2847 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2849 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2853 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2855 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2857 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2868 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition2870 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2872 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2876 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2878 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2880 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_79_in_presetcondition2924 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2926 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2930 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2932 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2981 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition2983 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2985 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2989 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2991 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2993 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3004 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition3006 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3008 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3012 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3014 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3016 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_70_in_presetcondition3061 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3063 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3067 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3116 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition3118 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3120 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3124 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3126 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3128 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3139 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3141 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3143 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3147 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3149 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3151 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3161 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_presetcondition3163 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3165 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3169 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition3171 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_68_in_presetcondition3210 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3212 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3216 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3265 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition3267 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3269 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3273 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3275 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3277 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3288 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3290 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3292 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3296 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3298 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3300 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3310 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_presetcondition3312 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3314 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3318 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition3320 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_89_in_presetcondition3360 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3362 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3366 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3368 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3370 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3377 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3379 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3381 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3385 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3387 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3389 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3396 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition3398 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3400 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3404 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3406 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3408 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3415 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_presetcondition3417 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3419 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3423 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3425 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3427 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3434 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition3436 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3438 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3442 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3448 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_110_in_presetcondition3450 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3452 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3456 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3462 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition3464 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3466 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3468 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3472 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3474 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3478 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3480 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3482 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_presetcondition3484 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3486 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3488 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3492 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3494 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3498 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3500 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3506 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition3508 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3510 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3512 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3516 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3518 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3522 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3524 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_90_in_presetcondition3575 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3577 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3581 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3583 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3585 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3592 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3594 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3596 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3600 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3602 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3604 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3611 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition3613 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3615 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3619 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3621 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3623 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3630 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_presetcondition3632 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3634 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3638 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3640 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3642 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3649 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition3651 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3653 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3657 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3663 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_110_in_presetcondition3665 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3667 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3671 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3677 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition3679 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3681 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3683 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3687 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3689 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3693 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3695 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3697 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_presetcondition3699 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3701 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3703 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3707 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3709 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3713 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3715 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3721 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition3723 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3725 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3727 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3731 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3733 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3737 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3739 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_91_in_presetcondition3790 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3792 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3796 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3798 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3800 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3807 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition3809 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3811 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3815 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3817 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3819 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3826 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition3828 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3830 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3834 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3836 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3838 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3845 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_presetcondition3847 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3849 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3853 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3855 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3857 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3864 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition3866 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3868 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3872 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3878 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_110_in_presetcondition3880 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3882 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3886 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3892 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition3894 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3896 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3898 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3902 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3904 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3908 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3910 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3912 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_presetcondition3914 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3916 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3918 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3922 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3924 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3928 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3930 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3936 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition3938 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3940 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3942 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3946 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3948 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3952 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3954 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_92_in_presetcondition4005 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4007 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4011 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4013 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4015 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4022 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition4024 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4026 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4030 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4032 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4034 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4041 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition4043 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4045 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4049 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4051 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4053 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4060 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_presetcondition4062 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4064 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4068 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4070 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4072 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4079 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition4081 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4083 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition4087 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4093 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_110_in_presetcondition4095 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4097 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition4101 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4107 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition4109 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4111 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4113 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4117 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4119 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4123 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4125 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4127 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_presetcondition4129 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4131 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4133 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4137 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4139 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4143 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4145 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4151 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition4153 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4155 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4157 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4161 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4163 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4167 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4169 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_93_in_presetcondition4222 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4224 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4228 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4230 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4232 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4239 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition4241 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4243 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4247 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4249 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4251 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4258 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition4260 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4262 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4266 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4268 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4270 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4277 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_presetcondition4279 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4281 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4285 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4287 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4289 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4296 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition4298 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4300 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4302 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4306 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4308 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4312 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4314 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4320 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition4322 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4324 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4328 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_94_in_presetcondition4405 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4407 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4411 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4413 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4415 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4425 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition4427 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4429 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4433 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4435 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4437 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4447 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition4449 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4451 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4455 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4457 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4459 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4469 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_presetcondition4471 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4473 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4477 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4479 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4481 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4491 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition4493 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4495 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4497 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4501 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4503 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4507 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4509 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4519 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_104_in_presetcondition4521 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4523 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4527 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_106_in_presetcondition4606 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4614 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition4616 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4618 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4622 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4624 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4626 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4634 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition4636 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4638 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4642 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4644 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4646 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4654 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_presetcondition4656 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4658 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4662 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_109_in_presetcondition4697 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4705 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition4707 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4709 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4713 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4715 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4717 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4725 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition4727 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4729 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4733 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4735 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4737 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4745 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_presetcondition4747 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4749 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4753 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_105_in_presetcondition4788 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4796 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_presetcondition4798 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4800 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4804 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4806 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4808 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4816 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition4818 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4820 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4824 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4826 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4828 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4836 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_presetcondition4838 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4840 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4844 = new BitSet(new long[]{0x0000000000000002L});
}
