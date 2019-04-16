// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2019-04-15 23:28:18
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
		"ReverseCondition", "SEMICOLUMN", "StockOperation", "String", "StringOperation", 
		"StringToken", "SupConstantCondition", "SupDoubleMapCondition", "SupportBreakDown", 
		"SupportBreakUp", "TRUTHOF", "Tcheat", "TruthOfCondition", "UpRatioCondition", 
		"WS", "WhiteChar", "'NaN'", "'also display'", "'bearish'", "'bullish'", 
		"'crosses down historical'", "'crosses down threshold'", "'crosses up historical'", 
		"'crosses up threshold'", "'direction'", "'ending within'", "'epsilon'", 
		"'equals historical'", "'equals threshold'", "'equals trend'", "'for'", 
		"'goes down more than'", "'goes up more than'", "'greed'", "'is above historical'", 
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
			// elements: bearish_condition, fixed_start_shift, bullish_condition, also_display
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
		RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:19: ( 'is bullish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:2: 'is bullish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )*
			{
			string_literal4=(Token)match(input,86,FOLLOW_86_in_bullish_condition472);  
			stream_86.add(string_literal4);

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
		RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
		RewriteRuleSubtreeStream stream_bearish_not_bullish=new RewriteRuleSubtreeStream(adaptor,"rule bearish_not_bullish");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:37: ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> bearish_not_bullish )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==83) ) {
				alt7=1;
			}
			else if ( (LA7_0==82) ) {
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
					string_literal10=(Token)match(input,83,FOLLOW_83_in_bearish_condition499);  
					stream_83.add(string_literal10);

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
		RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:15: ( 'also display' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==63) ) {
				alt9=1;
			}
			else if ( (LA9_0==EOF||LA9_0==96) ) {
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
					string_literal20=(Token)match(input,63,FOLLOW_63_in_also_display545);  
					stream_63.add(string_literal20);

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
		RewriteRuleTokenStream stream_96=new RewriteRuleTokenStream(adaptor,"token 96");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:177:20: ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS SEMICOLUMN ->| -> ^( Number NumberToken[\"-1\"] ) )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==96) ) {
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
					string_literal25=(Token)match(input,96,FOLLOW_96_in_fixed_start_shift589);  
					stream_96.add(string_literal25);

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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:182:1: bearish_not_bullish[CommonTree bcond] : 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( NotBooleanMapCondition ) primary_expression ) | -> ^( NotBooleanMapCondition ) ) ;
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
		RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:182:39: ( 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( NotBooleanMapCondition ) primary_expression ) | -> ^( NotBooleanMapCondition ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:2: 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( NotBooleanMapCondition ) primary_expression ) | -> ^( NotBooleanMapCondition ) )
			{
			string_literal30=(Token)match(input,82,FOLLOW_82_in_bearish_not_bullish630);  
			stream_82.add(string_literal30);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:3: ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( NotBooleanMapCondition ) primary_expression ) | -> ^( NotBooleanMapCondition ) )
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
					// 185:46: -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:49: ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ) primary_expression )
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

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:109: ^( NotBooleanMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:3: WhiteChar OR WhiteChar primary_expression
					{
					WhiteChar35=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish670);  
					stream_WhiteChar.add(WhiteChar35);

					OR36=(Token)match(input,OR,FOLLOW_OR_in_bearish_not_bullish672);  
					stream_OR.add(OR36);

					WhiteChar37=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish674);  
					stream_WhiteChar.add(WhiteChar37);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish676);
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
					// 186:45: -> ^( OrBooleanMapCondition ^( NotBooleanMapCondition ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:48: ^( OrBooleanMapCondition ^( NotBooleanMapCondition ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrBooleanMapCondition, "OrBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:72: ^( NotBooleanMapCondition )
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
					// 187:3: -> ^( NotBooleanMapCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:6: ^( NotBooleanMapCondition )
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


			pushFollow(FOLLOW_and_expression_in_primary_expression718);
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
			pushFollow(FOLLOW_or_expression_in_and_expression730);
			or_expression40=or_expression();
			state._fsp--;

			stream_or_expression.add(or_expression40.getTree());
			pushFollow(FOLLOW_lenient_in_and_expression734);
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
					WhiteChar41=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression737);  
					stream_WhiteChar.add(WhiteChar41);

					AND42=(Token)match(input,AND,FOLLOW_AND_in_and_expression739);  
					stream_AND.add(AND42);

					WhiteChar43=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression741);  
					stream_WhiteChar.add(WhiteChar43);

					pushFollow(FOLLOW_or_expression_in_and_expression743);
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:1: or_expression : matches_expression ( WhiteChar OR WhiteChar matches_expression )* -> ^( OrBooleanMapCondition matches_expression ( matches_expression )* ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:15: ( matches_expression ( WhiteChar OR WhiteChar matches_expression )* -> ^( OrBooleanMapCondition matches_expression ( matches_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:3: matches_expression ( WhiteChar OR WhiteChar matches_expression )*
			{
			pushFollow(FOLLOW_matches_expression_in_or_expression770);
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
					WhiteChar46=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression773);  
					stream_WhiteChar.add(WhiteChar46);

					OR47=(Token)match(input,OR,FOLLOW_OR_in_or_expression775);  
					stream_OR.add(OR47);

					WhiteChar48=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression777);  
					stream_WhiteChar.add(WhiteChar48);

					pushFollow(FOLLOW_matches_expression_in_or_expression779);
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
			// 199:67: -> ^( OrBooleanMapCondition matches_expression ( matches_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:70: ^( OrBooleanMapCondition matches_expression ( matches_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrBooleanMapCondition, "OrBooleanMapCondition"), root_1);
				adaptor.addChild(root_1, stream_matches_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:113: ( matches_expression )*
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
			pushFollow(FOLLOW_atom_in_matches_expression804);
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
					WhiteChar51=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression807);  
					stream_WhiteChar.add(WhiteChar51);

					MATCHING52=(Token)match(input,MATCHING,FOLLOW_MATCHING_in_matches_expression809);  
					stream_MATCHING.add(MATCHING52);

					WhiteChar53=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression811);  
					stream_WhiteChar.add(WhiteChar53);

					char_literal54=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_matches_expression813);  
					stream_OPENSQRT.add(char_literal54);

					pushFollow(FOLLOW_constant_in_matches_expression815);
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
							char_literal56=(Token)match(input,COMMA,FOLLOW_COMMA_in_matches_expression818);  
							stream_COMMA.add(char_literal56);

							pushFollow(FOLLOW_constant_in_matches_expression820);
							constant57=constant();
							state._fsp--;

							stream_constant.add(constant57.getTree());
							}
							break;

						default :
							break loop14;
						}
					}

					char_literal58=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_matches_expression824);  
					stream_CLOSESQRT.add(char_literal58);

					WhiteChar59=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression826);  
					stream_WhiteChar.add(WhiteChar59);

					pushFollow(FOLLOW_atom_in_matches_expression828);
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:1: atom : ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | NOT ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotBooleanMapCondition primary_expression ) | conjunctiontruthof );
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:6: ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | NOT ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotBooleanMapCondition primary_expression ) | conjunctiontruthof )
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


					pushFollow(FOLLOW_booleanhistory_in_atom856);
					booleanhistory61=booleanhistory();
					state._fsp--;

					adaptor.addChild(root_0, booleanhistory61.getTree());

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:3: '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					char_literal62=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom862);  
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
							WhiteChar63=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom864);  
							stream_WhiteChar.add(WhiteChar63);

							}
							break;

						default :
							break loop16;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom867);
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
							WhiteChar65=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom869);  
							stream_WhiteChar.add(WhiteChar65);

							}
							break;

						default :
							break loop17;
						}
					}

					char_literal66=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom872);  
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
					NOT67=(Token)match(input,NOT,FOLLOW_NOT_in_atom882);  
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
							WhiteChar68=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom884);  
							stream_WhiteChar.add(WhiteChar68);

							}
							break;

						default :
							break loop18;
						}
					}

					char_literal69=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom887);  
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
							WhiteChar70=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom889);  
							stream_WhiteChar.add(WhiteChar70);

							}
							break;

						default :
							break loop19;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom892);
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
							WhiteChar72=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom894);  
							stream_WhiteChar.add(WhiteChar72);

							}
							break;

						default :
							break loop20;
						}
					}

					char_literal73=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom897);  
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
					// 207:67: -> ^( NotBooleanMapCondition primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:70: ^( NotBooleanMapCondition primary_expression )
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
				case 4 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:3: conjunctiontruthof
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_conjunctiontruthof_in_atom911);
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:1: conjunctiontruthof : TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']' -> ^( TruthOfCondition primary_expression ( primary_expression )* ) ;
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
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_OPENSQRT=new RewriteRuleTokenStream(adaptor,"token OPENSQRT");
		RewriteRuleTokenStream stream_CLOSESQRT=new RewriteRuleTokenStream(adaptor,"token CLOSESQRT");
		RewriteRuleTokenStream stream_87=new RewriteRuleTokenStream(adaptor,"token 87");
		RewriteRuleTokenStream stream_TRUTHOF=new RewriteRuleTokenStream(adaptor,"token TRUTHOF");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:20: ( TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']' -> ^( TruthOfCondition primary_expression ( primary_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:3: TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']'
			{
			TRUTHOF75=(Token)match(input,TRUTHOF,FOLLOW_TRUTHOF_in_conjunctiontruthof923);  
			stream_TRUTHOF.add(TRUTHOF75);

			WhiteChar76=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof925);  
			stream_WhiteChar.add(WhiteChar76);

			pushFollow(FOLLOW_primary_expression_in_conjunctiontruthof927);
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
					COMMA78=(Token)match(input,COMMA,FOLLOW_COMMA_in_conjunctiontruthof930);  
					stream_COMMA.add(COMMA78);

					WhiteChar79=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof932);  
					stream_WhiteChar.add(WhiteChar79);

					pushFollow(FOLLOW_primary_expression_in_conjunctiontruthof934);
					primary_expression80=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression80.getTree());
					}
					break;

				default :
					break loop22;
				}
			}

			WhiteChar81=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof938);  
			stream_WhiteChar.add(WhiteChar81);

			string_literal82=(Token)match(input,87,FOLLOW_87_in_conjunctiontruthof940);  
			stream_87.add(string_literal82);

			WhiteChar83=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof942);  
			stream_WhiteChar.add(WhiteChar83);

			char_literal84=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_conjunctiontruthof944);  
			stream_OPENSQRT.add(char_literal84);

			pushFollow(FOLLOW_constant_in_conjunctiontruthof948);
			min=constant();
			state._fsp--;

			stream_constant.add(min.getTree());
			char_literal85=(Token)match(input,COMMA,FOLLOW_COMMA_in_conjunctiontruthof950);  
			stream_COMMA.add(char_literal85);

			pushFollow(FOLLOW_constant_in_conjunctiontruthof954);
			max=constant();
			state._fsp--;

			stream_constant.add(max.getTree());
			char_literal86=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_conjunctiontruthof956);  
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
			// 211:148: -> ^( TruthOfCondition primary_expression ( primary_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:151: ^( TruthOfCondition primary_expression ( primary_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TruthOfCondition, "TruthOfCondition"), root_1);
				adaptor.addChild(root_1, (min!=null?((CommonTree)min.getTree()):null));
				adaptor.addChild(root_1, (max!=null?((CommonTree)max.getTree()):null));
				adaptor.addChild(root_1, stream_primary_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:213: ( primary_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:1: booleanhistory : firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:16: (firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:2: firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			{
			pushFollow(FOLLOW_operand_in_booleanhistory984);
			firstOp=operand();
			state._fsp--;

			stream_operand.add(firstOp.getTree());
			WhiteChar87=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_booleanhistory986);  
			stream_WhiteChar.add(WhiteChar87);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:28: ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			int alt23=3;
			switch ( input.LA(1) ) {
			case 67:
			case 69:
			case 77:
			case 78:
			case 88:
			case 89:
			case 90:
			case 91:
			case 92:
			case 93:
			case 97:
			case 98:
			case 104:
			case 105:
			case 108:
				{
				alt23=1;
				}
				break;
			case 66:
			case 68:
			case 73:
			case 80:
			case 84:
			case 106:
			case 107:
				{
				alt23=2;
				}
				break;
			case 74:
			case 75:
			case 81:
			case 85:
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:3: presetcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_presetcondition_in_booleanhistory992);
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
					// 215:34: -> presetcondition
					{
						adaptor.addChild(root_0, stream_presetcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:3: opcmpcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_opcmpcondition_in_booleanhistory1003);
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
					// 216:33: -> opcmpcondition
					{
						adaptor.addChild(root_0, stream_opcmpcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:3: constantcmp[$firstOp.tree]
					{
					pushFollow(FOLLOW_constantcmp_in_booleanhistory1014);
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
					// 217:30: -> constantcmp
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:1: operand : ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation );
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:9: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:11: HistoricalData
					{
					HistoricalData91=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_operand1030);  
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
					// 220:26: -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:29: ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:46: ^( OperationOutput HistoricalData )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
						adaptor.addChild(root_2, stream_HistoricalData.nextNode());
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:80: ^( String StringToken[\"\\\"THIS\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:117: opName= Operation
					{
					opName=(Token)match(input,Operation,FOLLOW_Operation_in_operand1057);  
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
					// 220:171: -> Operation
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:1: constant : ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) );
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:10: ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:13: NumberToken
					{
					NumberToken92=(Token)match(input,NumberToken,FOLLOW_NumberToken_in_constant1072);  
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
					// 222:25: -> ^( Number NumberToken )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:28: ^( Number NumberToken )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:52: 'NaN'
					{
					string_literal93=(Token)match(input,62,FOLLOW_62_in_constant1084);  
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
					// 222:58: -> ^( Number NumberToken[\"NaN\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:61: ^( Number NumberToken[\"NaN\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:1: stringconstant : StringToken -> ^( String StringToken ) ;
	public final ParameterizedIndicatorsParser.stringconstant_return stringconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.stringconstant_return retval = new ParameterizedIndicatorsParser.stringconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token StringToken94=null;

		CommonTree StringToken94_tree=null;
		RewriteRuleTokenStream stream_StringToken=new RewriteRuleTokenStream(adaptor,"token StringToken");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:16: ( StringToken -> ^( String StringToken ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:18: StringToken
			{
			StringToken94=(Token)match(input,StringToken,FOLLOW_StringToken_in_stringconstant1100);  
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
			// 223:30: -> ^( String StringToken )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:33: ^( String StringToken )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:1: trendconstant : ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) );
	public final ParameterizedIndicatorsParser.trendconstant_return trendconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.trendconstant_return retval = new ParameterizedIndicatorsParser.trendconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal95=null;
		Token string_literal96=null;

		CommonTree string_literal95_tree=null;
		CommonTree string_literal96_tree=null;
		RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
		RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:15: ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) )
			int alt26=2;
			int LA26_0 = input.LA(1);
			if ( (LA26_0==65) ) {
				alt26=1;
			}
			else if ( (LA26_0==64) ) {
				alt26=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 26, 0, input);
				throw nvae;
			}

			switch (alt26) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:17: 'bullish'
					{
					string_literal95=(Token)match(input,65,FOLLOW_65_in_trendconstant1115);  
					stream_65.add(string_literal95);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 224:27: -> ^( String StringToken[\"\\\"bullish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:30: ^( String StringToken[\"\\\"bullish\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:69: 'bearish'
					{
					string_literal96=(Token)match(input,64,FOLLOW_64_in_trendconstant1128);  
					stream_64.add(string_literal96);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 224:79: -> ^( String StringToken[\"\\\"bearish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:82: ^( String StringToken[\"\\\"bearish\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:1: lenient : ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:9: ( ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			int alt27=2;
			int LA27_0 = input.LA(1);
			if ( (LA27_0==WhiteChar) ) {
				int LA27_1 = input.LA(2);
				if ( (LA27_1==LENIENT) ) {
					alt27=1;
				}
				else if ( (LA27_1==AND||LA27_1==CLOSEPARENTEHSIS||LA27_1==SEMICOLUMN||LA27_1==WhiteChar||LA27_1==87) ) {
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:12: WhiteChar LENIENT
					{
					WhiteChar97=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_lenient1145);  
					stream_WhiteChar.add(WhiteChar97);

					LENIENT98=(Token)match(input,LENIENT,FOLLOW_LENIENT_in_lenient1147);  
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
					// 225:30: -> ^( String StringToken[\"\\\"TRUE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:33: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:69: 
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
					// 225:69: -> ^( String StringToken[\"\\\"FALSE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:72: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:1: opcmpcondition[CommonTree firstOp] : ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( CrossDownDoubleMapCondition operand ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( CrossUpDoubleMapCondition operand ) )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) );
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
		Token WhiteChar109=null;
		Token PERCENT110=null;
		Token string_literal111=null;
		Token WhiteChar112=null;
		Token WhiteChar113=null;
		Token string_literal114=null;
		Token WhiteChar115=null;
		Token WhiteChar116=null;
		Token DAYS117=null;
		Token WhiteChar118=null;
		Token string_literal119=null;
		Token WhiteChar120=null;
		Token WhiteChar121=null;
		Token PERCENT122=null;
		Token string_literal123=null;
		Token WhiteChar124=null;
		Token WhiteChar125=null;
		Token string_literal126=null;
		Token WhiteChar127=null;
		Token WhiteChar128=null;
		Token DAYS129=null;
		Token WhiteChar130=null;
		Token string_literal131=null;
		Token WhiteChar132=null;
		Token WhiteChar133=null;
		Token PERCENT134=null;
		Token string_literal135=null;
		Token WhiteChar136=null;
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
		Token WhiteChar151=null;
		Token PERCENT152=null;
		Token string_literal153=null;
		Token WhiteChar154=null;
		Token WhiteChar156=null;
		Token string_literal157=null;
		Token WhiteChar158=null;
		Token WhiteChar159=null;
		Token DAYS160=null;
		Token WhiteChar161=null;
		Token string_literal162=null;
		Token WhiteChar163=null;
		Token WhiteChar164=null;
		Token DAYS165=null;
		Token WhiteChar166=null;
		Token string_literal167=null;
		Token WhiteChar168=null;
		Token WhiteChar169=null;
		Token PERCENT170=null;
		Token string_literal171=null;
		Token WhiteChar172=null;
		Token WhiteChar173=null;
		Token string_literal174=null;
		Token WhiteChar175=null;
		Token WhiteChar176=null;
		Token DAYS177=null;
		Token WhiteChar178=null;
		Token string_literal179=null;
		Token WhiteChar180=null;
		Token WhiteChar181=null;
		Token DAYS182=null;
		Token WhiteChar183=null;
		Token string_literal184=null;
		Token WhiteChar185=null;
		Token WhiteChar186=null;
		Token string_literal187=null;
		Token WhiteChar188=null;
		Token string_literal189=null;
		Token WhiteChar190=null;
		Token WhiteChar191=null;
		Token string_literal192=null;
		Token WhiteChar193=null;
		Token WhiteChar194=null;
		Token DAYS195=null;
		Token WhiteChar196=null;
		Token string_literal197=null;
		Token WhiteChar198=null;
		Token WhiteChar199=null;
		Token DAYS200=null;
		Token WhiteChar201=null;
		Token string_literal202=null;
		Token WhiteChar203=null;
		ParserRuleReturnScope secondOp =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope epsilon =null;
		ParserRuleReturnScope spanningNbDays =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope direction =null;
		ParserRuleReturnScope operand137 =null;
		ParserRuleReturnScope operand155 =null;

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
		CommonTree WhiteChar109_tree=null;
		CommonTree PERCENT110_tree=null;
		CommonTree string_literal111_tree=null;
		CommonTree WhiteChar112_tree=null;
		CommonTree WhiteChar113_tree=null;
		CommonTree string_literal114_tree=null;
		CommonTree WhiteChar115_tree=null;
		CommonTree WhiteChar116_tree=null;
		CommonTree DAYS117_tree=null;
		CommonTree WhiteChar118_tree=null;
		CommonTree string_literal119_tree=null;
		CommonTree WhiteChar120_tree=null;
		CommonTree WhiteChar121_tree=null;
		CommonTree PERCENT122_tree=null;
		CommonTree string_literal123_tree=null;
		CommonTree WhiteChar124_tree=null;
		CommonTree WhiteChar125_tree=null;
		CommonTree string_literal126_tree=null;
		CommonTree WhiteChar127_tree=null;
		CommonTree WhiteChar128_tree=null;
		CommonTree DAYS129_tree=null;
		CommonTree WhiteChar130_tree=null;
		CommonTree string_literal131_tree=null;
		CommonTree WhiteChar132_tree=null;
		CommonTree WhiteChar133_tree=null;
		CommonTree PERCENT134_tree=null;
		CommonTree string_literal135_tree=null;
		CommonTree WhiteChar136_tree=null;
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
		CommonTree WhiteChar151_tree=null;
		CommonTree PERCENT152_tree=null;
		CommonTree string_literal153_tree=null;
		CommonTree WhiteChar154_tree=null;
		CommonTree WhiteChar156_tree=null;
		CommonTree string_literal157_tree=null;
		CommonTree WhiteChar158_tree=null;
		CommonTree WhiteChar159_tree=null;
		CommonTree DAYS160_tree=null;
		CommonTree WhiteChar161_tree=null;
		CommonTree string_literal162_tree=null;
		CommonTree WhiteChar163_tree=null;
		CommonTree WhiteChar164_tree=null;
		CommonTree DAYS165_tree=null;
		CommonTree WhiteChar166_tree=null;
		CommonTree string_literal167_tree=null;
		CommonTree WhiteChar168_tree=null;
		CommonTree WhiteChar169_tree=null;
		CommonTree PERCENT170_tree=null;
		CommonTree string_literal171_tree=null;
		CommonTree WhiteChar172_tree=null;
		CommonTree WhiteChar173_tree=null;
		CommonTree string_literal174_tree=null;
		CommonTree WhiteChar175_tree=null;
		CommonTree WhiteChar176_tree=null;
		CommonTree DAYS177_tree=null;
		CommonTree WhiteChar178_tree=null;
		CommonTree string_literal179_tree=null;
		CommonTree WhiteChar180_tree=null;
		CommonTree WhiteChar181_tree=null;
		CommonTree DAYS182_tree=null;
		CommonTree WhiteChar183_tree=null;
		CommonTree string_literal184_tree=null;
		CommonTree WhiteChar185_tree=null;
		CommonTree WhiteChar186_tree=null;
		CommonTree string_literal187_tree=null;
		CommonTree WhiteChar188_tree=null;
		CommonTree string_literal189_tree=null;
		CommonTree WhiteChar190_tree=null;
		CommonTree WhiteChar191_tree=null;
		CommonTree string_literal192_tree=null;
		CommonTree WhiteChar193_tree=null;
		CommonTree WhiteChar194_tree=null;
		CommonTree DAYS195_tree=null;
		CommonTree WhiteChar196_tree=null;
		CommonTree string_literal197_tree=null;
		CommonTree WhiteChar198_tree=null;
		CommonTree WhiteChar199_tree=null;
		CommonTree DAYS200_tree=null;
		CommonTree WhiteChar201_tree=null;
		CommonTree string_literal202_tree=null;
		CommonTree WhiteChar203_tree=null;
		RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
		RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_101=new RewriteRuleTokenStream(adaptor,"token 101");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_106=new RewriteRuleTokenStream(adaptor,"token 106");
		RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
		RewriteRuleTokenStream stream_107=new RewriteRuleTokenStream(adaptor,"token 107");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
		RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
		RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
		RewriteRuleTokenStream stream_95=new RewriteRuleTokenStream(adaptor,"token 95");
		RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_stringconstant=new RewriteRuleSubtreeStream(adaptor,"rule stringconstant");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:37: ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( CrossDownDoubleMapCondition operand ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( CrossUpDoubleMapCondition operand ) )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) )
			int alt33=7;
			switch ( input.LA(1) ) {
			case 80:
				{
				alt33=1;
				}
				break;
			case 84:
				{
				alt33=2;
				}
				break;
			case 73:
				{
				alt33=3;
				}
				break;
			case 66:
				{
				alt33=4;
				}
				break;
			case 68:
				{
				alt33=5;
				}
				break;
			case 106:
				{
				alt33=6;
				}
				break;
			case 107:
				{
				alt33=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 33, 0, input);
				throw nvae;
			}
			switch (alt33) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( SupDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:4: 'is above historical' WhiteChar secondOp= operand
					{
					string_literal99=(Token)match(input,80,FOLLOW_80_in_opcmpcondition1183);  
					stream_80.add(string_literal99);

					WhiteChar100=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1185);  
					stream_WhiteChar.add(WhiteChar100);

					pushFollow(FOLLOW_operand_in_opcmpcondition1189);
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
					// 229:53: -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:56: ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupDoubleMapCondition, "SupDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:80: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:107: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( SupDoubleMapCondition ) )?
					int alt28=2;
					int LA28_0 = input.LA(1);
					if ( (LA28_0==WhiteChar) ) {
						int LA28_1 = input.LA(2);
						if ( (LA28_1==76) ) {
							alt28=1;
						}
					}
					switch (alt28) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:6: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT
							{
							WhiteChar101=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1222);  
							stream_WhiteChar.add(WhiteChar101);

							string_literal102=(Token)match(input,76,FOLLOW_76_in_opcmpcondition1224);  
							stream_76.add(string_literal102);

							WhiteChar103=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1226);  
							stream_WhiteChar.add(WhiteChar103);

							pushFollow(FOLLOW_constant_in_opcmpcondition1230);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar104=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1232);  
							stream_WhiteChar.add(WhiteChar104);

							DAYS105=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1234);  
							stream_DAYS.add(DAYS105);

							WhiteChar106=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1239);  
							stream_WhiteChar.add(WhiteChar106);

							string_literal107=(Token)match(input,72,FOLLOW_72_in_opcmpcondition1241);  
							stream_72.add(string_literal107);

							WhiteChar108=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1243);  
							stream_WhiteChar.add(WhiteChar108);

							pushFollow(FOLLOW_constant_in_opcmpcondition1247);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							WhiteChar109=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1249);  
							stream_WhiteChar.add(WhiteChar109);

							PERCENT110=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1251);  
							stream_PERCENT.add(PERCENT110);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 232:4: -> ^( SupDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:7: ^( SupDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( InfDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:4: 'is below historical' WhiteChar secondOp= operand
					{
					string_literal111=(Token)match(input,84,FOLLOW_84_in_opcmpcondition1278);  
					stream_84.add(string_literal111);

					WhiteChar112=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1280);  
					stream_WhiteChar.add(WhiteChar112);

					pushFollow(FOLLOW_operand_in_opcmpcondition1284);
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
					// 233:53: -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:56: ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfDoubleMapCondition, "InfDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:80: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:107: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:5: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( InfDoubleMapCondition ) )?
					int alt29=2;
					int LA29_0 = input.LA(1);
					if ( (LA29_0==WhiteChar) ) {
						int LA29_1 = input.LA(2);
						if ( (LA29_1==76) ) {
							alt29=1;
						}
					}
					switch (alt29) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT
							{
							WhiteChar113=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1318);  
							stream_WhiteChar.add(WhiteChar113);

							string_literal114=(Token)match(input,76,FOLLOW_76_in_opcmpcondition1320);  
							stream_76.add(string_literal114);

							WhiteChar115=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1322);  
							stream_WhiteChar.add(WhiteChar115);

							pushFollow(FOLLOW_constant_in_opcmpcondition1326);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar116=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1328);  
							stream_WhiteChar.add(WhiteChar116);

							DAYS117=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1330);  
							stream_DAYS.add(DAYS117);

							WhiteChar118=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1336);  
							stream_WhiteChar.add(WhiteChar118);

							string_literal119=(Token)match(input,72,FOLLOW_72_in_opcmpcondition1338);  
							stream_72.add(string_literal119);

							WhiteChar120=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1340);  
							stream_WhiteChar.add(WhiteChar120);

							pushFollow(FOLLOW_constant_in_opcmpcondition1344);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							WhiteChar121=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1346);  
							stream_WhiteChar.add(WhiteChar121);

							PERCENT122=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1348);  
							stream_PERCENT.add(PERCENT122);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 236:5: -> ^( InfDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:8: ^( InfDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( EqualDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:4: 'equals historical' WhiteChar secondOp= operand
					{
					string_literal123=(Token)match(input,73,FOLLOW_73_in_opcmpcondition1376);  
					stream_73.add(string_literal123);

					WhiteChar124=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1378);  
					stream_WhiteChar.add(WhiteChar124);

					pushFollow(FOLLOW_operand_in_opcmpcondition1382);
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
					// 237:51: -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:54: ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualDoubleMapCondition, "EqualDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:80: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:107: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:5: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( EqualDoubleMapCondition ) )?
					int alt30=2;
					int LA30_0 = input.LA(1);
					if ( (LA30_0==WhiteChar) ) {
						int LA30_1 = input.LA(2);
						if ( (LA30_1==76) ) {
							alt30=1;
						}
					}
					switch (alt30) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT
							{
							WhiteChar125=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1416);  
							stream_WhiteChar.add(WhiteChar125);

							string_literal126=(Token)match(input,76,FOLLOW_76_in_opcmpcondition1418);  
							stream_76.add(string_literal126);

							WhiteChar127=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1420);  
							stream_WhiteChar.add(WhiteChar127);

							pushFollow(FOLLOW_constant_in_opcmpcondition1424);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar128=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1426);  
							stream_WhiteChar.add(WhiteChar128);

							DAYS129=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1428);  
							stream_DAYS.add(DAYS129);

							WhiteChar130=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1434);  
							stream_WhiteChar.add(WhiteChar130);

							string_literal131=(Token)match(input,72,FOLLOW_72_in_opcmpcondition1436);  
							stream_72.add(string_literal131);

							WhiteChar132=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1438);  
							stream_WhiteChar.add(WhiteChar132);

							pushFollow(FOLLOW_constant_in_opcmpcondition1442);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							WhiteChar133=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1444);  
							stream_WhiteChar.add(WhiteChar133);

							PERCENT134=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1446);  
							stream_PERCENT.add(PERCENT134);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 240:5: -> ^( EqualDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:8: ^( EqualDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:3: ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( CrossDownDoubleMapCondition operand ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:3: ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:4: 'crosses down historical' WhiteChar operand
					{
					string_literal135=(Token)match(input,66,FOLLOW_66_in_opcmpcondition1475);  
					stream_66.add(string_literal135);

					WhiteChar136=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1477);  
					stream_WhiteChar.add(WhiteChar136);

					pushFollow(FOLLOW_operand_in_opcmpcondition1479);
					operand137=operand();
					state._fsp--;

					stream_operand.add(operand137.getTree());
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
					// 242:48: -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:51: ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:81: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:110: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:139: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:5: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( CrossDownDoubleMapCondition operand ) )?
					int alt31=2;
					int LA31_0 = input.LA(1);
					if ( (LA31_0==WhiteChar) ) {
						int LA31_1 = input.LA(2);
						if ( (LA31_1==101) ) {
							alt31=1;
						}
					}
					switch (alt31) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:7: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT
							{
							WhiteChar138=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1519);  
							stream_WhiteChar.add(WhiteChar138);

							string_literal139=(Token)match(input,101,FOLLOW_101_in_opcmpcondition1521);  
							stream_101.add(string_literal139);

							WhiteChar140=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1523);  
							stream_WhiteChar.add(WhiteChar140);

							pushFollow(FOLLOW_constant_in_opcmpcondition1527);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar141=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1529);  
							stream_WhiteChar.add(WhiteChar141);

							DAYS142=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1531);  
							stream_DAYS.add(DAYS142);

							WhiteChar143=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1537);  
							stream_WhiteChar.add(WhiteChar143);

							string_literal144=(Token)match(input,95,FOLLOW_95_in_opcmpcondition1539);  
							stream_95.add(string_literal144);

							WhiteChar145=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1541);  
							stream_WhiteChar.add(WhiteChar145);

							pushFollow(FOLLOW_constant_in_opcmpcondition1545);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar146=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1547);  
							stream_WhiteChar.add(WhiteChar146);

							DAYS147=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1549);  
							stream_DAYS.add(DAYS147);

							WhiteChar148=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1555);  
							stream_WhiteChar.add(WhiteChar148);

							string_literal149=(Token)match(input,72,FOLLOW_72_in_opcmpcondition1557);  
							stream_72.add(string_literal149);

							WhiteChar150=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1559);  
							stream_WhiteChar.add(WhiteChar150);

							pushFollow(FOLLOW_constant_in_opcmpcondition1563);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							WhiteChar151=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1565);  
							stream_WhiteChar.add(WhiteChar151);

							PERCENT152=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1567);  
							stream_PERCENT.add(PERCENT152);

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
							// 246:5: -> ^( CrossDownDoubleMapCondition operand )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:8: ^( CrossDownDoubleMapCondition operand )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:3: ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( CrossUpDoubleMapCondition operand ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:3: ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:4: 'crosses up historical' WhiteChar operand
					{
					string_literal153=(Token)match(input,68,FOLLOW_68_in_opcmpcondition1597);  
					stream_68.add(string_literal153);

					WhiteChar154=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1599);  
					stream_WhiteChar.add(WhiteChar154);

					pushFollow(FOLLOW_operand_in_opcmpcondition1601);
					operand155=operand();
					state._fsp--;

					stream_operand.add(operand155.getTree());
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
					// 247:46: -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:49: ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:77: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:106: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:135: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:5: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( CrossUpDoubleMapCondition operand ) )?
					int alt32=2;
					int LA32_0 = input.LA(1);
					if ( (LA32_0==WhiteChar) ) {
						int LA32_1 = input.LA(2);
						if ( (LA32_1==101) ) {
							alt32=1;
						}
					}
					switch (alt32) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:7: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT
							{
							WhiteChar156=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1641);  
							stream_WhiteChar.add(WhiteChar156);

							string_literal157=(Token)match(input,101,FOLLOW_101_in_opcmpcondition1643);  
							stream_101.add(string_literal157);

							WhiteChar158=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1645);  
							stream_WhiteChar.add(WhiteChar158);

							pushFollow(FOLLOW_constant_in_opcmpcondition1649);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar159=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1651);  
							stream_WhiteChar.add(WhiteChar159);

							DAYS160=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1653);  
							stream_DAYS.add(DAYS160);

							WhiteChar161=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1659);  
							stream_WhiteChar.add(WhiteChar161);

							string_literal162=(Token)match(input,95,FOLLOW_95_in_opcmpcondition1661);  
							stream_95.add(string_literal162);

							WhiteChar163=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1663);  
							stream_WhiteChar.add(WhiteChar163);

							pushFollow(FOLLOW_constant_in_opcmpcondition1667);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar164=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1669);  
							stream_WhiteChar.add(WhiteChar164);

							DAYS165=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1671);  
							stream_DAYS.add(DAYS165);

							WhiteChar166=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1677);  
							stream_WhiteChar.add(WhiteChar166);

							string_literal167=(Token)match(input,72,FOLLOW_72_in_opcmpcondition1679);  
							stream_72.add(string_literal167);

							WhiteChar168=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1681);  
							stream_WhiteChar.add(WhiteChar168);

							pushFollow(FOLLOW_constant_in_opcmpcondition1685);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							WhiteChar169=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1687);  
							stream_WhiteChar.add(WhiteChar169);

							PERCENT170=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1689);  
							stream_PERCENT.add(PERCENT170);

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
							// 251:5: -> ^( CrossUpDoubleMapCondition operand )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:8: ^( CrossUpDoubleMapCondition operand )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:4: 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal171=(Token)match(input,106,FOLLOW_106_in_opcmpcondition1720);  
					stream_106.add(string_literal171);

					WhiteChar172=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1722);  
					stream_WhiteChar.add(WhiteChar172);

					pushFollow(FOLLOW_operand_in_opcmpcondition1726);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar173=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1734);  
					stream_WhiteChar.add(WhiteChar173);

					string_literal174=(Token)match(input,95,FOLLOW_95_in_opcmpcondition1736);  
					stream_95.add(string_literal174);

					WhiteChar175=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1738);  
					stream_WhiteChar.add(WhiteChar175);

					pushFollow(FOLLOW_constant_in_opcmpcondition1742);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar176=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1744);  
					stream_WhiteChar.add(WhiteChar176);

					DAYS177=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1746);  
					stream_DAYS.add(DAYS177);

					WhiteChar178=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1754);  
					stream_WhiteChar.add(WhiteChar178);

					string_literal179=(Token)match(input,76,FOLLOW_76_in_opcmpcondition1756);  
					stream_76.add(string_literal179);

					WhiteChar180=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1758);  
					stream_WhiteChar.add(WhiteChar180);

					pushFollow(FOLLOW_constant_in_opcmpcondition1762);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar181=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1764);  
					stream_WhiteChar.add(WhiteChar181);

					DAYS182=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1766);  
					stream_DAYS.add(DAYS182);

					WhiteChar183=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1774);  
					stream_WhiteChar.add(WhiteChar183);

					string_literal184=(Token)match(input,70,FOLLOW_70_in_opcmpcondition1776);  
					stream_70.add(string_literal184);

					WhiteChar185=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1778);  
					stream_WhiteChar.add(WhiteChar185);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition1782);
					direction=stringconstant();
					state._fsp--;

					stream_stringconstant.add(direction.getTree());
					WhiteChar186=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1790);  
					stream_WhiteChar.add(WhiteChar186);

					string_literal187=(Token)match(input,72,FOLLOW_72_in_opcmpcondition1792);  
					stream_72.add(string_literal187);

					WhiteChar188=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1794);  
					stream_WhiteChar.add(WhiteChar188);

					pushFollow(FOLLOW_constant_in_opcmpcondition1798);
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
					// 258:7: -> ^( LinearSimilarTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:10: ^( LinearSimilarTrendsCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:259:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:259:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:259:4: 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant
					{
					string_literal189=(Token)match(input,107,FOLLOW_107_in_opcmpcondition1830);  
					stream_107.add(string_literal189);

					WhiteChar190=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1832);  
					stream_WhiteChar.add(WhiteChar190);

					pushFollow(FOLLOW_operand_in_opcmpcondition1836);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar191=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1844);  
					stream_WhiteChar.add(WhiteChar191);

					string_literal192=(Token)match(input,95,FOLLOW_95_in_opcmpcondition1846);  
					stream_95.add(string_literal192);

					WhiteChar193=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1848);  
					stream_WhiteChar.add(WhiteChar193);

					pushFollow(FOLLOW_constant_in_opcmpcondition1852);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar194=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1854);  
					stream_WhiteChar.add(WhiteChar194);

					DAYS195=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1856);  
					stream_DAYS.add(DAYS195);

					WhiteChar196=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1864);  
					stream_WhiteChar.add(WhiteChar196);

					string_literal197=(Token)match(input,76,FOLLOW_76_in_opcmpcondition1866);  
					stream_76.add(string_literal197);

					WhiteChar198=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1868);  
					stream_WhiteChar.add(WhiteChar198);

					pushFollow(FOLLOW_constant_in_opcmpcondition1872);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar199=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1874);  
					stream_WhiteChar.add(WhiteChar199);

					DAYS200=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1876);  
					stream_DAYS.add(DAYS200);

					WhiteChar201=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1884);  
					stream_WhiteChar.add(WhiteChar201);

					string_literal202=(Token)match(input,70,FOLLOW_70_in_opcmpcondition1886);  
					stream_70.add(string_literal202);

					WhiteChar203=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1888);  
					stream_WhiteChar.add(WhiteChar203);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition1892);
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
					// 263:7: -> ^( LinearOppositeTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:10: ^( LinearOppositeTrendsCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:1: constantcmp[CommonTree firstOp] : ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( InfConstantCondition ) )? );
	public final ParameterizedIndicatorsParser.constantcmp_return constantcmp(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.constantcmp_return retval = new ParameterizedIndicatorsParser.constantcmp_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal204=null;
		Token WhiteChar205=null;
		Token WhiteChar206=null;
		Token string_literal207=null;
		Token WhiteChar208=null;
		Token WhiteChar209=null;
		Token DAYS210=null;
		Token WhiteChar211=null;
		Token string_literal212=null;
		Token WhiteChar213=null;
		Token WhiteChar214=null;
		Token DAYS215=null;
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
		Token WhiteChar228=null;
		Token string_literal229=null;
		Token WhiteChar230=null;
		Token WhiteChar231=null;
		Token PERCENT232=null;
		Token string_literal233=null;
		Token WhiteChar234=null;
		Token WhiteChar235=null;
		Token string_literal236=null;
		Token WhiteChar237=null;
		Token WhiteChar238=null;
		Token DAYS239=null;
		Token WhiteChar240=null;
		Token string_literal241=null;
		Token WhiteChar242=null;
		Token WhiteChar243=null;
		Token DAYS244=null;
		Token WhiteChar245=null;
		Token string_literal246=null;
		Token WhiteChar247=null;
		Token WhiteChar248=null;
		Token PERCENT249=null;
		Token string_literal250=null;
		Token WhiteChar251=null;
		Token WhiteChar252=null;
		Token string_literal253=null;
		Token WhiteChar254=null;
		Token WhiteChar255=null;
		Token DAYS256=null;
		Token WhiteChar257=null;
		Token string_literal258=null;
		Token WhiteChar259=null;
		Token WhiteChar260=null;
		Token DAYS261=null;
		Token WhiteChar262=null;
		Token string_literal263=null;
		Token WhiteChar264=null;
		Token WhiteChar265=null;
		Token PERCENT266=null;
		ParserRuleReturnScope trendSignal =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope threshold =null;
		ParserRuleReturnScope epsilon =null;

		CommonTree string_literal204_tree=null;
		CommonTree WhiteChar205_tree=null;
		CommonTree WhiteChar206_tree=null;
		CommonTree string_literal207_tree=null;
		CommonTree WhiteChar208_tree=null;
		CommonTree WhiteChar209_tree=null;
		CommonTree DAYS210_tree=null;
		CommonTree WhiteChar211_tree=null;
		CommonTree string_literal212_tree=null;
		CommonTree WhiteChar213_tree=null;
		CommonTree WhiteChar214_tree=null;
		CommonTree DAYS215_tree=null;
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
		CommonTree WhiteChar228_tree=null;
		CommonTree string_literal229_tree=null;
		CommonTree WhiteChar230_tree=null;
		CommonTree WhiteChar231_tree=null;
		CommonTree PERCENT232_tree=null;
		CommonTree string_literal233_tree=null;
		CommonTree WhiteChar234_tree=null;
		CommonTree WhiteChar235_tree=null;
		CommonTree string_literal236_tree=null;
		CommonTree WhiteChar237_tree=null;
		CommonTree WhiteChar238_tree=null;
		CommonTree DAYS239_tree=null;
		CommonTree WhiteChar240_tree=null;
		CommonTree string_literal241_tree=null;
		CommonTree WhiteChar242_tree=null;
		CommonTree WhiteChar243_tree=null;
		CommonTree DAYS244_tree=null;
		CommonTree WhiteChar245_tree=null;
		CommonTree string_literal246_tree=null;
		CommonTree WhiteChar247_tree=null;
		CommonTree WhiteChar248_tree=null;
		CommonTree PERCENT249_tree=null;
		CommonTree string_literal250_tree=null;
		CommonTree WhiteChar251_tree=null;
		CommonTree WhiteChar252_tree=null;
		CommonTree string_literal253_tree=null;
		CommonTree WhiteChar254_tree=null;
		CommonTree WhiteChar255_tree=null;
		CommonTree DAYS256_tree=null;
		CommonTree WhiteChar257_tree=null;
		CommonTree string_literal258_tree=null;
		CommonTree WhiteChar259_tree=null;
		CommonTree WhiteChar260_tree=null;
		CommonTree DAYS261_tree=null;
		CommonTree WhiteChar262_tree=null;
		CommonTree string_literal263_tree=null;
		CommonTree WhiteChar264_tree=null;
		CommonTree WhiteChar265_tree=null;
		CommonTree PERCENT266_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
		RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
		RewriteRuleTokenStream stream_95=new RewriteRuleTokenStream(adaptor,"token 95");
		RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
		RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");
		RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
		RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_trendconstant=new RewriteRuleSubtreeStream(adaptor,"rule trendconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:34: ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( InfConstantCondition ) )? )
			int alt38=4;
			switch ( input.LA(1) ) {
			case 75:
				{
				alt38=1;
				}
				break;
			case 74:
				{
				alt38=2;
				}
				break;
			case 81:
				{
				alt38=3;
				}
				break;
			case 85:
				{
				alt38=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 38, 0, input);
				throw nvae;
			}
			switch (alt38) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:4: 'equals trend' WhiteChar trendSignal= trendconstant
					{
					string_literal204=(Token)match(input,75,FOLLOW_75_in_constantcmp1929);  
					stream_75.add(string_literal204);

					WhiteChar205=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1931);  
					stream_WhiteChar.add(WhiteChar205);

					pushFollow(FOLLOW_trendconstant_in_constantcmp1935);
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
					// 267:55: -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:58: ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualStringConstantCondition, "EqualStringConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_trendconstant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:103: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:130: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:268:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					int alt34=2;
					int LA34_0 = input.LA(1);
					if ( (LA34_0==WhiteChar) ) {
						int LA34_1 = input.LA(2);
						if ( (LA34_1==95) ) {
							alt34=1;
						}
					}
					switch (alt34) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:268:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar206=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1969);  
							stream_WhiteChar.add(WhiteChar206);

							string_literal207=(Token)match(input,95,FOLLOW_95_in_constantcmp1971);  
							stream_95.add(string_literal207);

							WhiteChar208=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1973);  
							stream_WhiteChar.add(WhiteChar208);

							pushFollow(FOLLOW_constant_in_constantcmp1977);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar209=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1979);  
							stream_WhiteChar.add(WhiteChar209);

							DAYS210=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1981);  
							stream_DAYS.add(DAYS210);

							WhiteChar211=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1983);  
							stream_WhiteChar.add(WhiteChar211);

							string_literal212=(Token)match(input,76,FOLLOW_76_in_constantcmp1985);  
							stream_76.add(string_literal212);

							WhiteChar213=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1987);  
							stream_WhiteChar.add(WhiteChar213);

							pushFollow(FOLLOW_constant_in_constantcmp1991);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar214=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1993);  
							stream_WhiteChar.add(WhiteChar214);

							DAYS215=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1995);  
							stream_DAYS.add(DAYS215);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 268:129: -> ^( EqualStringConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:268:132: ^( EqualStringConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( EqualConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:4: 'equals threshold' WhiteChar threshold= constant
					{
					string_literal216=(Token)match(input,74,FOLLOW_74_in_constantcmp2019);  
					stream_74.add(string_literal216);

					WhiteChar217=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2021);  
					stream_WhiteChar.add(WhiteChar217);

					pushFollow(FOLLOW_constant_in_constantcmp2025);
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
					// 270:52: -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:55: ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualConstantCondition, "EqualConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:143: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( EqualConstantCondition ) )?
					int alt35=2;
					int LA35_0 = input.LA(1);
					if ( (LA35_0==WhiteChar) ) {
						int LA35_1 = input.LA(2);
						if ( (LA35_1==95) ) {
							alt35=1;
						}
					}
					switch (alt35) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT
							{
							WhiteChar218=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2066);  
							stream_WhiteChar.add(WhiteChar218);

							string_literal219=(Token)match(input,95,FOLLOW_95_in_constantcmp2068);  
							stream_95.add(string_literal219);

							WhiteChar220=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2070);  
							stream_WhiteChar.add(WhiteChar220);

							pushFollow(FOLLOW_constant_in_constantcmp2074);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar221=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2076);  
							stream_WhiteChar.add(WhiteChar221);

							DAYS222=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2078);  
							stream_DAYS.add(DAYS222);

							WhiteChar223=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2084);  
							stream_WhiteChar.add(WhiteChar223);

							string_literal224=(Token)match(input,76,FOLLOW_76_in_constantcmp2086);  
							stream_76.add(string_literal224);

							WhiteChar225=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2088);  
							stream_WhiteChar.add(WhiteChar225);

							pushFollow(FOLLOW_constant_in_constantcmp2092);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar226=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2094);  
							stream_WhiteChar.add(WhiteChar226);

							DAYS227=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2096);  
							stream_DAYS.add(DAYS227);

							WhiteChar228=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2102);  
							stream_WhiteChar.add(WhiteChar228);

							string_literal229=(Token)match(input,72,FOLLOW_72_in_constantcmp2104);  
							stream_72.add(string_literal229);

							WhiteChar230=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2106);  
							stream_WhiteChar.add(WhiteChar230);

							pushFollow(FOLLOW_constant_in_constantcmp2110);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							WhiteChar231=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2112);  
							stream_WhiteChar.add(WhiteChar231);

							PERCENT232=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2114);  
							stream_PERCENT.add(PERCENT232);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 274:5: -> ^( EqualConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:8: ^( EqualConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( SupConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:4: 'is above threshold' WhiteChar threshold= constant
					{
					string_literal233=(Token)match(input,81,FOLLOW_81_in_constantcmp2144);  
					stream_81.add(string_literal233);

					WhiteChar234=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2146);  
					stream_WhiteChar.add(WhiteChar234);

					pushFollow(FOLLOW_constant_in_constantcmp2150);
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
					// 275:54: -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:57: ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupConstantCondition, "SupConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:143: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( SupConstantCondition ) )?
					int alt36=2;
					int LA36_0 = input.LA(1);
					if ( (LA36_0==WhiteChar) ) {
						int LA36_1 = input.LA(2);
						if ( (LA36_1==95) ) {
							alt36=1;
						}
					}
					switch (alt36) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT
							{
							WhiteChar235=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2191);  
							stream_WhiteChar.add(WhiteChar235);

							string_literal236=(Token)match(input,95,FOLLOW_95_in_constantcmp2193);  
							stream_95.add(string_literal236);

							WhiteChar237=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2195);  
							stream_WhiteChar.add(WhiteChar237);

							pushFollow(FOLLOW_constant_in_constantcmp2199);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar238=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2201);  
							stream_WhiteChar.add(WhiteChar238);

							DAYS239=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2203);  
							stream_DAYS.add(DAYS239);

							WhiteChar240=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2209);  
							stream_WhiteChar.add(WhiteChar240);

							string_literal241=(Token)match(input,76,FOLLOW_76_in_constantcmp2211);  
							stream_76.add(string_literal241);

							WhiteChar242=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2213);  
							stream_WhiteChar.add(WhiteChar242);

							pushFollow(FOLLOW_constant_in_constantcmp2217);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar243=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2219);  
							stream_WhiteChar.add(WhiteChar243);

							DAYS244=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2221);  
							stream_DAYS.add(DAYS244);

							WhiteChar245=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2227);  
							stream_WhiteChar.add(WhiteChar245);

							string_literal246=(Token)match(input,72,FOLLOW_72_in_constantcmp2229);  
							stream_72.add(string_literal246);

							WhiteChar247=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2231);  
							stream_WhiteChar.add(WhiteChar247);

							pushFollow(FOLLOW_constant_in_constantcmp2235);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							WhiteChar248=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2237);  
							stream_WhiteChar.add(WhiteChar248);

							PERCENT249=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2239);  
							stream_PERCENT.add(PERCENT249);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 279:5: -> ^( SupConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:279:8: ^( SupConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:280:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( InfConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:280:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:280:4: 'is below threshold' WhiteChar threshold= constant
					{
					string_literal250=(Token)match(input,85,FOLLOW_85_in_constantcmp2269);  
					stream_85.add(string_literal250);

					WhiteChar251=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2271);  
					stream_WhiteChar.add(WhiteChar251);

					pushFollow(FOLLOW_constant_in_constantcmp2275);
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
					// 280:54: -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:280:57: ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfConstantCondition, "InfConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:280:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:280:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:280:143: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( InfConstantCondition ) )?
					int alt37=2;
					int LA37_0 = input.LA(1);
					if ( (LA37_0==WhiteChar) ) {
						int LA37_1 = input.LA(2);
						if ( (LA37_1==95) ) {
							alt37=1;
						}
					}
					switch (alt37) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT
							{
							WhiteChar252=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2316);  
							stream_WhiteChar.add(WhiteChar252);

							string_literal253=(Token)match(input,95,FOLLOW_95_in_constantcmp2318);  
							stream_95.add(string_literal253);

							WhiteChar254=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2320);  
							stream_WhiteChar.add(WhiteChar254);

							pushFollow(FOLLOW_constant_in_constantcmp2324);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar255=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2326);  
							stream_WhiteChar.add(WhiteChar255);

							DAYS256=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2328);  
							stream_DAYS.add(DAYS256);

							WhiteChar257=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2334);  
							stream_WhiteChar.add(WhiteChar257);

							string_literal258=(Token)match(input,76,FOLLOW_76_in_constantcmp2336);  
							stream_76.add(string_literal258);

							WhiteChar259=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2338);  
							stream_WhiteChar.add(WhiteChar259);

							pushFollow(FOLLOW_constant_in_constantcmp2342);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar260=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2344);  
							stream_WhiteChar.add(WhiteChar260);

							DAYS261=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2346);  
							stream_DAYS.add(DAYS261);

							WhiteChar262=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2352);  
							stream_WhiteChar.add(WhiteChar262);

							string_literal263=(Token)match(input,72,FOLLOW_72_in_constantcmp2354);  
							stream_72.add(string_literal263);

							WhiteChar264=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2356);  
							stream_WhiteChar.add(WhiteChar264);

							pushFollow(FOLLOW_constant_in_constantcmp2360);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							WhiteChar265=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2362);  
							stream_WhiteChar.add(WhiteChar265);

							PERCENT266=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2364);  
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
							// 284:5: -> ^( InfConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:284:8: ^( InfConstantCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:1: presetcondition[CommonTree firstOp] : ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) ) | ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) ) | ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) ) );
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
		Token WhiteChar328=null;
		Token PERCENT329=null;
		Token string_literal330=null;
		Token WhiteChar331=null;
		Token WhiteChar332=null;
		Token string_literal333=null;
		Token WhiteChar334=null;
		Token WhiteChar335=null;
		Token DAYS336=null;
		Token WhiteChar337=null;
		Token string_literal338=null;
		Token WhiteChar339=null;
		Token WhiteChar340=null;
		Token DAYS341=null;
		Token WhiteChar342=null;
		Token string_literal343=null;
		Token WhiteChar344=null;
		Token WhiteChar345=null;
		Token PERCENT346=null;
		Token string_literal347=null;
		Token WhiteChar348=null;
		Token WhiteChar349=null;
		Token DAYS350=null;
		Token WhiteChar351=null;
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
		Token string_literal370=null;
		Token WhiteChar371=null;
		Token WhiteChar372=null;
		Token string_literal373=null;
		Token WhiteChar374=null;
		Token char_literal375=null;
		Token char_literal376=null;
		Token char_literal377=null;
		Token WhiteChar378=null;
		Token string_literal379=null;
		Token WhiteChar380=null;
		Token char_literal381=null;
		Token char_literal382=null;
		Token char_literal383=null;
		Token WhiteChar384=null;
		Token string_literal385=null;
		Token WhiteChar386=null;
		Token char_literal387=null;
		Token char_literal388=null;
		Token char_literal389=null;
		Token string_literal390=null;
		Token WhiteChar391=null;
		Token WhiteChar392=null;
		Token DAYS393=null;
		Token WhiteChar394=null;
		Token string_literal395=null;
		Token WhiteChar396=null;
		Token WhiteChar397=null;
		Token DAYS398=null;
		Token WhiteChar399=null;
		Token string_literal400=null;
		Token WhiteChar401=null;
		Token WhiteChar402=null;
		Token DAYS403=null;
		Token WhiteChar404=null;
		Token string_literal405=null;
		Token WhiteChar406=null;
		Token WhiteChar407=null;
		Token DAYS408=null;
		Token WhiteChar409=null;
		Token string_literal410=null;
		Token WhiteChar411=null;
		Token WhiteChar412=null;
		Token string_literal413=null;
		Token WhiteChar414=null;
		Token WhiteChar415=null;
		Token string_literal416=null;
		Token WhiteChar417=null;
		Token char_literal418=null;
		Token char_literal419=null;
		Token char_literal420=null;
		Token WhiteChar421=null;
		Token string_literal422=null;
		Token WhiteChar423=null;
		Token char_literal424=null;
		Token char_literal425=null;
		Token char_literal426=null;
		Token WhiteChar427=null;
		Token string_literal428=null;
		Token WhiteChar429=null;
		Token char_literal430=null;
		Token char_literal431=null;
		Token char_literal432=null;
		Token string_literal433=null;
		Token WhiteChar434=null;
		Token WhiteChar435=null;
		Token DAYS436=null;
		Token WhiteChar437=null;
		Token string_literal438=null;
		Token WhiteChar439=null;
		Token WhiteChar440=null;
		Token DAYS441=null;
		Token WhiteChar442=null;
		Token string_literal443=null;
		Token WhiteChar444=null;
		Token WhiteChar445=null;
		Token DAYS446=null;
		Token WhiteChar447=null;
		Token string_literal448=null;
		Token WhiteChar449=null;
		Token WhiteChar450=null;
		Token DAYS451=null;
		Token WhiteChar452=null;
		Token string_literal453=null;
		Token WhiteChar454=null;
		Token WhiteChar455=null;
		Token string_literal456=null;
		Token WhiteChar457=null;
		Token WhiteChar458=null;
		Token string_literal459=null;
		Token WhiteChar460=null;
		Token char_literal461=null;
		Token char_literal462=null;
		Token char_literal463=null;
		Token WhiteChar464=null;
		Token string_literal465=null;
		Token WhiteChar466=null;
		Token char_literal467=null;
		Token char_literal468=null;
		Token char_literal469=null;
		Token WhiteChar470=null;
		Token string_literal471=null;
		Token WhiteChar472=null;
		Token char_literal473=null;
		Token char_literal474=null;
		Token char_literal475=null;
		Token string_literal476=null;
		Token WhiteChar477=null;
		Token WhiteChar478=null;
		Token DAYS479=null;
		Token WhiteChar480=null;
		Token string_literal481=null;
		Token WhiteChar482=null;
		Token WhiteChar483=null;
		Token DAYS484=null;
		Token WhiteChar485=null;
		Token string_literal486=null;
		Token WhiteChar487=null;
		Token WhiteChar488=null;
		Token DAYS489=null;
		Token WhiteChar490=null;
		Token string_literal491=null;
		Token WhiteChar492=null;
		Token WhiteChar493=null;
		Token DAYS494=null;
		Token WhiteChar495=null;
		Token string_literal496=null;
		Token WhiteChar497=null;
		Token WhiteChar498=null;
		Token string_literal499=null;
		Token WhiteChar500=null;
		Token WhiteChar501=null;
		Token string_literal502=null;
		Token WhiteChar503=null;
		Token char_literal504=null;
		Token char_literal505=null;
		Token char_literal506=null;
		Token WhiteChar507=null;
		Token string_literal508=null;
		Token WhiteChar509=null;
		Token char_literal510=null;
		Token char_literal511=null;
		Token char_literal512=null;
		Token WhiteChar513=null;
		Token string_literal514=null;
		Token WhiteChar515=null;
		Token char_literal516=null;
		Token char_literal517=null;
		Token char_literal518=null;
		Token string_literal519=null;
		Token WhiteChar520=null;
		Token WhiteChar521=null;
		Token DAYS522=null;
		Token WhiteChar523=null;
		Token string_literal524=null;
		Token WhiteChar525=null;
		Token WhiteChar526=null;
		Token DAYS527=null;
		Token WhiteChar528=null;
		Token string_literal529=null;
		Token WhiteChar530=null;
		Token WhiteChar531=null;
		Token DAYS532=null;
		Token WhiteChar533=null;
		Token string_literal534=null;
		Token WhiteChar535=null;
		Token WhiteChar536=null;
		Token DAYS537=null;
		Token WhiteChar538=null;
		Token string_literal539=null;
		Token WhiteChar540=null;
		Token char_literal541=null;
		Token char_literal542=null;
		Token char_literal543=null;
		Token WhiteChar544=null;
		Token string_literal545=null;
		Token WhiteChar546=null;
		Token string_literal547=null;
		Token WhiteChar548=null;
		Token WhiteChar549=null;
		Token DAYS550=null;
		Token WhiteChar551=null;
		Token string_literal552=null;
		Token WhiteChar553=null;
		Token WhiteChar554=null;
		Token DAYS555=null;
		Token WhiteChar556=null;
		Token string_literal557=null;
		Token WhiteChar558=null;
		Token WhiteChar559=null;
		Token DAYS560=null;
		Token WhiteChar561=null;
		Token string_literal562=null;
		Token WhiteChar563=null;
		Token WhiteChar564=null;
		Token DAYS565=null;
		Token WhiteChar566=null;
		Token string_literal567=null;
		Token WhiteChar568=null;
		Token char_literal569=null;
		Token char_literal570=null;
		Token char_literal571=null;
		Token WhiteChar572=null;
		Token string_literal573=null;
		Token WhiteChar574=null;
		Token string_literal575=null;
		Token WhiteChar576=null;
		Token string_literal577=null;
		Token WhiteChar578=null;
		Token WhiteChar579=null;
		Token DAYS580=null;
		Token WhiteChar581=null;
		Token string_literal582=null;
		Token WhiteChar583=null;
		Token WhiteChar584=null;
		Token DAYS585=null;
		Token WhiteChar586=null;
		Token string_literal587=null;
		Token WhiteChar588=null;
		Token string_literal589=null;
		Token WhiteChar590=null;
		Token string_literal591=null;
		Token WhiteChar592=null;
		Token WhiteChar593=null;
		Token DAYS594=null;
		Token WhiteChar595=null;
		Token string_literal596=null;
		Token WhiteChar597=null;
		Token WhiteChar598=null;
		Token DAYS599=null;
		Token WhiteChar600=null;
		Token string_literal601=null;
		Token WhiteChar602=null;
		Token string_literal603=null;
		Token WhiteChar604=null;
		Token string_literal605=null;
		Token WhiteChar606=null;
		Token WhiteChar607=null;
		Token DAYS608=null;
		Token WhiteChar609=null;
		Token string_literal610=null;
		Token WhiteChar611=null;
		Token WhiteChar612=null;
		Token DAYS613=null;
		Token WhiteChar614=null;
		Token string_literal615=null;
		Token WhiteChar616=null;
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
		CommonTree WhiteChar328_tree=null;
		CommonTree PERCENT329_tree=null;
		CommonTree string_literal330_tree=null;
		CommonTree WhiteChar331_tree=null;
		CommonTree WhiteChar332_tree=null;
		CommonTree string_literal333_tree=null;
		CommonTree WhiteChar334_tree=null;
		CommonTree WhiteChar335_tree=null;
		CommonTree DAYS336_tree=null;
		CommonTree WhiteChar337_tree=null;
		CommonTree string_literal338_tree=null;
		CommonTree WhiteChar339_tree=null;
		CommonTree WhiteChar340_tree=null;
		CommonTree DAYS341_tree=null;
		CommonTree WhiteChar342_tree=null;
		CommonTree string_literal343_tree=null;
		CommonTree WhiteChar344_tree=null;
		CommonTree WhiteChar345_tree=null;
		CommonTree PERCENT346_tree=null;
		CommonTree string_literal347_tree=null;
		CommonTree WhiteChar348_tree=null;
		CommonTree WhiteChar349_tree=null;
		CommonTree DAYS350_tree=null;
		CommonTree WhiteChar351_tree=null;
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
		CommonTree string_literal370_tree=null;
		CommonTree WhiteChar371_tree=null;
		CommonTree WhiteChar372_tree=null;
		CommonTree string_literal373_tree=null;
		CommonTree WhiteChar374_tree=null;
		CommonTree char_literal375_tree=null;
		CommonTree char_literal376_tree=null;
		CommonTree char_literal377_tree=null;
		CommonTree WhiteChar378_tree=null;
		CommonTree string_literal379_tree=null;
		CommonTree WhiteChar380_tree=null;
		CommonTree char_literal381_tree=null;
		CommonTree char_literal382_tree=null;
		CommonTree char_literal383_tree=null;
		CommonTree WhiteChar384_tree=null;
		CommonTree string_literal385_tree=null;
		CommonTree WhiteChar386_tree=null;
		CommonTree char_literal387_tree=null;
		CommonTree char_literal388_tree=null;
		CommonTree char_literal389_tree=null;
		CommonTree string_literal390_tree=null;
		CommonTree WhiteChar391_tree=null;
		CommonTree WhiteChar392_tree=null;
		CommonTree DAYS393_tree=null;
		CommonTree WhiteChar394_tree=null;
		CommonTree string_literal395_tree=null;
		CommonTree WhiteChar396_tree=null;
		CommonTree WhiteChar397_tree=null;
		CommonTree DAYS398_tree=null;
		CommonTree WhiteChar399_tree=null;
		CommonTree string_literal400_tree=null;
		CommonTree WhiteChar401_tree=null;
		CommonTree WhiteChar402_tree=null;
		CommonTree DAYS403_tree=null;
		CommonTree WhiteChar404_tree=null;
		CommonTree string_literal405_tree=null;
		CommonTree WhiteChar406_tree=null;
		CommonTree WhiteChar407_tree=null;
		CommonTree DAYS408_tree=null;
		CommonTree WhiteChar409_tree=null;
		CommonTree string_literal410_tree=null;
		CommonTree WhiteChar411_tree=null;
		CommonTree WhiteChar412_tree=null;
		CommonTree string_literal413_tree=null;
		CommonTree WhiteChar414_tree=null;
		CommonTree WhiteChar415_tree=null;
		CommonTree string_literal416_tree=null;
		CommonTree WhiteChar417_tree=null;
		CommonTree char_literal418_tree=null;
		CommonTree char_literal419_tree=null;
		CommonTree char_literal420_tree=null;
		CommonTree WhiteChar421_tree=null;
		CommonTree string_literal422_tree=null;
		CommonTree WhiteChar423_tree=null;
		CommonTree char_literal424_tree=null;
		CommonTree char_literal425_tree=null;
		CommonTree char_literal426_tree=null;
		CommonTree WhiteChar427_tree=null;
		CommonTree string_literal428_tree=null;
		CommonTree WhiteChar429_tree=null;
		CommonTree char_literal430_tree=null;
		CommonTree char_literal431_tree=null;
		CommonTree char_literal432_tree=null;
		CommonTree string_literal433_tree=null;
		CommonTree WhiteChar434_tree=null;
		CommonTree WhiteChar435_tree=null;
		CommonTree DAYS436_tree=null;
		CommonTree WhiteChar437_tree=null;
		CommonTree string_literal438_tree=null;
		CommonTree WhiteChar439_tree=null;
		CommonTree WhiteChar440_tree=null;
		CommonTree DAYS441_tree=null;
		CommonTree WhiteChar442_tree=null;
		CommonTree string_literal443_tree=null;
		CommonTree WhiteChar444_tree=null;
		CommonTree WhiteChar445_tree=null;
		CommonTree DAYS446_tree=null;
		CommonTree WhiteChar447_tree=null;
		CommonTree string_literal448_tree=null;
		CommonTree WhiteChar449_tree=null;
		CommonTree WhiteChar450_tree=null;
		CommonTree DAYS451_tree=null;
		CommonTree WhiteChar452_tree=null;
		CommonTree string_literal453_tree=null;
		CommonTree WhiteChar454_tree=null;
		CommonTree WhiteChar455_tree=null;
		CommonTree string_literal456_tree=null;
		CommonTree WhiteChar457_tree=null;
		CommonTree WhiteChar458_tree=null;
		CommonTree string_literal459_tree=null;
		CommonTree WhiteChar460_tree=null;
		CommonTree char_literal461_tree=null;
		CommonTree char_literal462_tree=null;
		CommonTree char_literal463_tree=null;
		CommonTree WhiteChar464_tree=null;
		CommonTree string_literal465_tree=null;
		CommonTree WhiteChar466_tree=null;
		CommonTree char_literal467_tree=null;
		CommonTree char_literal468_tree=null;
		CommonTree char_literal469_tree=null;
		CommonTree WhiteChar470_tree=null;
		CommonTree string_literal471_tree=null;
		CommonTree WhiteChar472_tree=null;
		CommonTree char_literal473_tree=null;
		CommonTree char_literal474_tree=null;
		CommonTree char_literal475_tree=null;
		CommonTree string_literal476_tree=null;
		CommonTree WhiteChar477_tree=null;
		CommonTree WhiteChar478_tree=null;
		CommonTree DAYS479_tree=null;
		CommonTree WhiteChar480_tree=null;
		CommonTree string_literal481_tree=null;
		CommonTree WhiteChar482_tree=null;
		CommonTree WhiteChar483_tree=null;
		CommonTree DAYS484_tree=null;
		CommonTree WhiteChar485_tree=null;
		CommonTree string_literal486_tree=null;
		CommonTree WhiteChar487_tree=null;
		CommonTree WhiteChar488_tree=null;
		CommonTree DAYS489_tree=null;
		CommonTree WhiteChar490_tree=null;
		CommonTree string_literal491_tree=null;
		CommonTree WhiteChar492_tree=null;
		CommonTree WhiteChar493_tree=null;
		CommonTree DAYS494_tree=null;
		CommonTree WhiteChar495_tree=null;
		CommonTree string_literal496_tree=null;
		CommonTree WhiteChar497_tree=null;
		CommonTree WhiteChar498_tree=null;
		CommonTree string_literal499_tree=null;
		CommonTree WhiteChar500_tree=null;
		CommonTree WhiteChar501_tree=null;
		CommonTree string_literal502_tree=null;
		CommonTree WhiteChar503_tree=null;
		CommonTree char_literal504_tree=null;
		CommonTree char_literal505_tree=null;
		CommonTree char_literal506_tree=null;
		CommonTree WhiteChar507_tree=null;
		CommonTree string_literal508_tree=null;
		CommonTree WhiteChar509_tree=null;
		CommonTree char_literal510_tree=null;
		CommonTree char_literal511_tree=null;
		CommonTree char_literal512_tree=null;
		CommonTree WhiteChar513_tree=null;
		CommonTree string_literal514_tree=null;
		CommonTree WhiteChar515_tree=null;
		CommonTree char_literal516_tree=null;
		CommonTree char_literal517_tree=null;
		CommonTree char_literal518_tree=null;
		CommonTree string_literal519_tree=null;
		CommonTree WhiteChar520_tree=null;
		CommonTree WhiteChar521_tree=null;
		CommonTree DAYS522_tree=null;
		CommonTree WhiteChar523_tree=null;
		CommonTree string_literal524_tree=null;
		CommonTree WhiteChar525_tree=null;
		CommonTree WhiteChar526_tree=null;
		CommonTree DAYS527_tree=null;
		CommonTree WhiteChar528_tree=null;
		CommonTree string_literal529_tree=null;
		CommonTree WhiteChar530_tree=null;
		CommonTree WhiteChar531_tree=null;
		CommonTree DAYS532_tree=null;
		CommonTree WhiteChar533_tree=null;
		CommonTree string_literal534_tree=null;
		CommonTree WhiteChar535_tree=null;
		CommonTree WhiteChar536_tree=null;
		CommonTree DAYS537_tree=null;
		CommonTree WhiteChar538_tree=null;
		CommonTree string_literal539_tree=null;
		CommonTree WhiteChar540_tree=null;
		CommonTree char_literal541_tree=null;
		CommonTree char_literal542_tree=null;
		CommonTree char_literal543_tree=null;
		CommonTree WhiteChar544_tree=null;
		CommonTree string_literal545_tree=null;
		CommonTree WhiteChar546_tree=null;
		CommonTree string_literal547_tree=null;
		CommonTree WhiteChar548_tree=null;
		CommonTree WhiteChar549_tree=null;
		CommonTree DAYS550_tree=null;
		CommonTree WhiteChar551_tree=null;
		CommonTree string_literal552_tree=null;
		CommonTree WhiteChar553_tree=null;
		CommonTree WhiteChar554_tree=null;
		CommonTree DAYS555_tree=null;
		CommonTree WhiteChar556_tree=null;
		CommonTree string_literal557_tree=null;
		CommonTree WhiteChar558_tree=null;
		CommonTree WhiteChar559_tree=null;
		CommonTree DAYS560_tree=null;
		CommonTree WhiteChar561_tree=null;
		CommonTree string_literal562_tree=null;
		CommonTree WhiteChar563_tree=null;
		CommonTree WhiteChar564_tree=null;
		CommonTree DAYS565_tree=null;
		CommonTree WhiteChar566_tree=null;
		CommonTree string_literal567_tree=null;
		CommonTree WhiteChar568_tree=null;
		CommonTree char_literal569_tree=null;
		CommonTree char_literal570_tree=null;
		CommonTree char_literal571_tree=null;
		CommonTree WhiteChar572_tree=null;
		CommonTree string_literal573_tree=null;
		CommonTree WhiteChar574_tree=null;
		CommonTree string_literal575_tree=null;
		CommonTree WhiteChar576_tree=null;
		CommonTree string_literal577_tree=null;
		CommonTree WhiteChar578_tree=null;
		CommonTree WhiteChar579_tree=null;
		CommonTree DAYS580_tree=null;
		CommonTree WhiteChar581_tree=null;
		CommonTree string_literal582_tree=null;
		CommonTree WhiteChar583_tree=null;
		CommonTree WhiteChar584_tree=null;
		CommonTree DAYS585_tree=null;
		CommonTree WhiteChar586_tree=null;
		CommonTree string_literal587_tree=null;
		CommonTree WhiteChar588_tree=null;
		CommonTree string_literal589_tree=null;
		CommonTree WhiteChar590_tree=null;
		CommonTree string_literal591_tree=null;
		CommonTree WhiteChar592_tree=null;
		CommonTree WhiteChar593_tree=null;
		CommonTree DAYS594_tree=null;
		CommonTree WhiteChar595_tree=null;
		CommonTree string_literal596_tree=null;
		CommonTree WhiteChar597_tree=null;
		CommonTree WhiteChar598_tree=null;
		CommonTree DAYS599_tree=null;
		CommonTree WhiteChar600_tree=null;
		CommonTree string_literal601_tree=null;
		CommonTree WhiteChar602_tree=null;
		CommonTree string_literal603_tree=null;
		CommonTree WhiteChar604_tree=null;
		CommonTree string_literal605_tree=null;
		CommonTree WhiteChar606_tree=null;
		CommonTree WhiteChar607_tree=null;
		CommonTree DAYS608_tree=null;
		CommonTree WhiteChar609_tree=null;
		CommonTree string_literal610_tree=null;
		CommonTree WhiteChar611_tree=null;
		CommonTree WhiteChar612_tree=null;
		CommonTree DAYS613_tree=null;
		CommonTree WhiteChar614_tree=null;
		CommonTree string_literal615_tree=null;
		CommonTree WhiteChar616_tree=null;
		RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");
		RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
		RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
		RewriteRuleTokenStream stream_91=new RewriteRuleTokenStream(adaptor,"token 91");
		RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
		RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
		RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
		RewriteRuleTokenStream stream_94=new RewriteRuleTokenStream(adaptor,"token 94");
		RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
		RewriteRuleTokenStream stream_95=new RewriteRuleTokenStream(adaptor,"token 95");
		RewriteRuleTokenStream stream_CLOSESQRT=new RewriteRuleTokenStream(adaptor,"token CLOSESQRT");
		RewriteRuleTokenStream stream_97=new RewriteRuleTokenStream(adaptor,"token 97");
		RewriteRuleTokenStream stream_98=new RewriteRuleTokenStream(adaptor,"token 98");
		RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_99=new RewriteRuleTokenStream(adaptor,"token 99");
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
		RewriteRuleTokenStream stream_108=new RewriteRuleTokenStream(adaptor,"token 108");
		RewriteRuleTokenStream stream_109=new RewriteRuleTokenStream(adaptor,"token 109");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_stringconstant=new RewriteRuleSubtreeStream(adaptor,"rule stringconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:38: ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) ) | ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) ) | ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) ) )
			int alt45=15;
			switch ( input.LA(1) ) {
			case 97:
				{
				alt45=1;
				}
				break;
			case 98:
				{
				alt45=2;
				}
				break;
			case 77:
				{
				alt45=3;
				}
				break;
			case 78:
				{
				alt45=4;
				}
				break;
			case 69:
				{
				alt45=5;
				}
				break;
			case 67:
				{
				alt45=6;
				}
				break;
			case 88:
				{
				alt45=7;
				}
				break;
			case 89:
				{
				alt45=8;
				}
				break;
			case 90:
				{
				alt45=9;
				}
				break;
			case 91:
				{
				alt45=10;
				}
				break;
			case 92:
				{
				alt45=11;
				}
				break;
			case 93:
				{
				alt45=12;
				}
				break;
			case 105:
				{
				alt45=13;
				}
				break;
			case 108:
				{
				alt45=14;
				}
				break;
			case 104:
				{
				alt45=15;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 45, 0, input);
				throw nvae;
			}
			switch (alt45) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:4: 'reverses down'
					{
					string_literal267=(Token)match(input,97,FOLLOW_97_in_presetcondition2401);  
					stream_97.add(string_literal267);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 288:20: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:23: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:42: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:70: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:99: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:7: ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					int alt39=2;
					int LA39_0 = input.LA(1);
					if ( (LA39_0==WhiteChar) ) {
						int LA39_1 = input.LA(2);
						if ( (LA39_1==94) ) {
							alt39=1;
						}
					}
					switch (alt39) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:289:9: WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar268=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2441);  
							stream_WhiteChar.add(WhiteChar268);

							string_literal269=(Token)match(input,94,FOLLOW_94_in_presetcondition2443);  
							stream_94.add(string_literal269);

							WhiteChar270=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2445);  
							stream_WhiteChar.add(WhiteChar270);

							pushFollow(FOLLOW_constant_in_presetcondition2449);
							percentdown=constant();
							state._fsp--;

							stream_constant.add(percentdown.getTree());
							PERCENT271=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2451);  
							stream_PERCENT.add(PERCENT271);

							WhiteChar272=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2453);  
							stream_WhiteChar.add(WhiteChar272);

							string_literal273=(Token)match(input,101,FOLLOW_101_in_presetcondition2455);  
							stream_101.add(string_literal273);

							WhiteChar274=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2457);  
							stream_WhiteChar.add(WhiteChar274);

							pushFollow(FOLLOW_constant_in_presetcondition2461);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar275=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2463);  
							stream_WhiteChar.add(WhiteChar275);

							DAYS276=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2465);  
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
							// 290:7: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:10: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:290:29: ^( Number NumberToken[\"-1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:4: 'reverses up'
					{
					string_literal277=(Token)match(input,98,FOLLOW_98_in_presetcondition2501);  
					stream_98.add(string_literal277);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 291:18: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:21: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:40: ^( Number NumberToken[\"1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:67: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:96: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:7: ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					int alt40=2;
					int LA40_0 = input.LA(1);
					if ( (LA40_0==WhiteChar) ) {
						int LA40_1 = input.LA(2);
						if ( (LA40_1==94) ) {
							alt40=1;
						}
					}
					switch (alt40) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:9: WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar278=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2541);  
							stream_WhiteChar.add(WhiteChar278);

							string_literal279=(Token)match(input,94,FOLLOW_94_in_presetcondition2543);  
							stream_94.add(string_literal279);

							WhiteChar280=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2545);  
							stream_WhiteChar.add(WhiteChar280);

							pushFollow(FOLLOW_constant_in_presetcondition2549);
							percentup=constant();
							state._fsp--;

							stream_constant.add(percentup.getTree());
							PERCENT281=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2551);  
							stream_PERCENT.add(PERCENT281);

							WhiteChar282=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2553);  
							stream_WhiteChar.add(WhiteChar282);

							string_literal283=(Token)match(input,101,FOLLOW_101_in_presetcondition2555);  
							stream_101.add(string_literal283);

							WhiteChar284=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2557);  
							stream_WhiteChar.add(WhiteChar284);

							pushFollow(FOLLOW_constant_in_presetcondition2561);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar285=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2563);  
							stream_WhiteChar.add(WhiteChar285);

							DAYS286=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2565);  
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
							// 293:7: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:10: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:29: ^( Number NumberToken[\"1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:4: 'goes down more than' WhiteChar percentdown= constant PERCENT
					{
					string_literal287=(Token)match(input,77,FOLLOW_77_in_presetcondition2602);  
					stream_77.add(string_literal287);

					WhiteChar288=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2604);  
					stream_WhiteChar.add(WhiteChar288);

					pushFollow(FOLLOW_constant_in_presetcondition2608);
					percentdown=constant();
					state._fsp--;

					stream_constant.add(percentdown.getTree());
					PERCENT289=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2610);  
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
					// 295:65: -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:68: ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:98: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:127: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:156: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:295:185: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:296:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt41=2;
					int LA41_0 = input.LA(1);
					if ( (LA41_0==WhiteChar) ) {
						int LA41_1 = input.LA(2);
						if ( (LA41_1==101) ) {
							alt41=1;
						}
					}
					switch (alt41) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:296:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar290=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2659);  
							stream_WhiteChar.add(WhiteChar290);

							string_literal291=(Token)match(input,101,FOLLOW_101_in_presetcondition2661);  
							stream_101.add(string_literal291);

							WhiteChar292=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2663);  
							stream_WhiteChar.add(WhiteChar292);

							pushFollow(FOLLOW_constant_in_presetcondition2667);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar293=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2669);  
							stream_WhiteChar.add(WhiteChar293);

							DAYS294=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2671);  
							stream_DAYS.add(DAYS294);

							WhiteChar295=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2682);  
							stream_WhiteChar.add(WhiteChar295);

							string_literal296=(Token)match(input,76,FOLLOW_76_in_presetcondition2684);  
							stream_76.add(string_literal296);

							WhiteChar297=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2686);  
							stream_WhiteChar.add(WhiteChar297);

							pushFollow(FOLLOW_constant_in_presetcondition2690);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar298=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2692);  
							stream_WhiteChar.add(WhiteChar298);

							DAYS299=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2694);  
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
							// 298:7: -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:10: ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentdown!=null?((CommonTree)percentdown.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:74: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:121: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:4: 'goes up more than' WhiteChar percentup= constant PERCENT
					{
					string_literal300=(Token)match(input,78,FOLLOW_78_in_presetcondition2738);  
					stream_78.add(string_literal300);

					WhiteChar301=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2740);  
					stream_WhiteChar.add(WhiteChar301);

					pushFollow(FOLLOW_constant_in_presetcondition2744);
					percentup=constant();
					state._fsp--;

					stream_constant.add(percentup.getTree());
					PERCENT302=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2746);  
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
					// 299:61: -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:64: ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:92: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:121: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:150: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:179: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:300:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt42=2;
					int LA42_0 = input.LA(1);
					if ( (LA42_0==WhiteChar) ) {
						int LA42_1 = input.LA(2);
						if ( (LA42_1==101) ) {
							alt42=1;
						}
					}
					switch (alt42) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:300:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar303=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2795);  
							stream_WhiteChar.add(WhiteChar303);

							string_literal304=(Token)match(input,101,FOLLOW_101_in_presetcondition2797);  
							stream_101.add(string_literal304);

							WhiteChar305=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2799);  
							stream_WhiteChar.add(WhiteChar305);

							pushFollow(FOLLOW_constant_in_presetcondition2803);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar306=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2805);  
							stream_WhiteChar.add(WhiteChar306);

							DAYS307=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2807);  
							stream_DAYS.add(DAYS307);

							WhiteChar308=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2818);  
							stream_WhiteChar.add(WhiteChar308);

							string_literal309=(Token)match(input,76,FOLLOW_76_in_presetcondition2820);  
							stream_76.add(string_literal309);

							WhiteChar310=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2822);  
							stream_WhiteChar.add(WhiteChar310);

							pushFollow(FOLLOW_constant_in_presetcondition2826);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar311=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2828);  
							stream_WhiteChar.add(WhiteChar311);

							DAYS312=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2830);  
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
							// 302:7: -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:10: ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentup!=null?((CommonTree)percentup.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:70: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:302:117: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:4: 'crosses up threshold' WhiteChar threshold= constant
					{
					string_literal313=(Token)match(input,69,FOLLOW_69_in_presetcondition2875);  
					stream_69.add(string_literal313);

					WhiteChar314=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2877);  
					stream_WhiteChar.add(WhiteChar314);

					pushFollow(FOLLOW_constant_in_presetcondition2881);
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
					// 304:56: -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:59: ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:95: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:124: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:153: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:182: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:305:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt43=2;
					int LA43_0 = input.LA(1);
					if ( (LA43_0==WhiteChar) ) {
						int LA43_1 = input.LA(2);
						if ( (LA43_1==101) ) {
							alt43=1;
						}
					}
					switch (alt43) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:305:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT
							{
							WhiteChar315=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2930);  
							stream_WhiteChar.add(WhiteChar315);

							string_literal316=(Token)match(input,101,FOLLOW_101_in_presetcondition2932);  
							stream_101.add(string_literal316);

							WhiteChar317=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2934);  
							stream_WhiteChar.add(WhiteChar317);

							pushFollow(FOLLOW_constant_in_presetcondition2938);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar318=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2940);  
							stream_WhiteChar.add(WhiteChar318);

							DAYS319=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2942);  
							stream_DAYS.add(DAYS319);

							WhiteChar320=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2953);  
							stream_WhiteChar.add(WhiteChar320);

							string_literal321=(Token)match(input,95,FOLLOW_95_in_presetcondition2955);  
							stream_95.add(string_literal321);

							WhiteChar322=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2957);  
							stream_WhiteChar.add(WhiteChar322);

							pushFollow(FOLLOW_constant_in_presetcondition2961);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar323=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2963);  
							stream_WhiteChar.add(WhiteChar323);

							DAYS324=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2965);  
							stream_DAYS.add(DAYS324);

							WhiteChar325=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2975);  
							stream_WhiteChar.add(WhiteChar325);

							string_literal326=(Token)match(input,72,FOLLOW_72_in_presetcondition2977);  
							stream_72.add(string_literal326);

							WhiteChar327=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2979);  
							stream_WhiteChar.add(WhiteChar327);

							pushFollow(FOLLOW_constant_in_presetcondition2983);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							WhiteChar328=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2985);  
							stream_WhiteChar.add(WhiteChar328);

							PERCENT329=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2987);  
							stream_PERCENT.add(PERCENT329);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 308:7: -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:308:10: ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:308:97: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:4: 'crosses down threshold' WhiteChar threshold= constant
					{
					string_literal330=(Token)match(input,67,FOLLOW_67_in_presetcondition3026);  
					stream_67.add(string_literal330);

					WhiteChar331=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3028);  
					stream_WhiteChar.add(WhiteChar331);

					pushFollow(FOLLOW_constant_in_presetcondition3032);
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
					// 309:58: -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:61: ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:99: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:128: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:157: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:186: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:310:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt44=2;
					int LA44_0 = input.LA(1);
					if ( (LA44_0==WhiteChar) ) {
						int LA44_1 = input.LA(2);
						if ( (LA44_1==101) ) {
							alt44=1;
						}
					}
					switch (alt44) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:310:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant WhiteChar PERCENT
							{
							WhiteChar332=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3081);  
							stream_WhiteChar.add(WhiteChar332);

							string_literal333=(Token)match(input,101,FOLLOW_101_in_presetcondition3083);  
							stream_101.add(string_literal333);

							WhiteChar334=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3085);  
							stream_WhiteChar.add(WhiteChar334);

							pushFollow(FOLLOW_constant_in_presetcondition3089);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar335=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3091);  
							stream_WhiteChar.add(WhiteChar335);

							DAYS336=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3093);  
							stream_DAYS.add(DAYS336);

							WhiteChar337=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3104);  
							stream_WhiteChar.add(WhiteChar337);

							string_literal338=(Token)match(input,95,FOLLOW_95_in_presetcondition3106);  
							stream_95.add(string_literal338);

							WhiteChar339=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3108);  
							stream_WhiteChar.add(WhiteChar339);

							pushFollow(FOLLOW_constant_in_presetcondition3112);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar340=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3114);  
							stream_WhiteChar.add(WhiteChar340);

							DAYS341=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3116);  
							stream_DAYS.add(DAYS341);

							WhiteChar342=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3126);  
							stream_WhiteChar.add(WhiteChar342);

							string_literal343=(Token)match(input,72,FOLLOW_72_in_presetcondition3128);  
							stream_72.add(string_literal343);

							WhiteChar344=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3130);  
							stream_WhiteChar.add(WhiteChar344);

							pushFollow(FOLLOW_constant_in_presetcondition3134);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							WhiteChar345=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3136);  
							stream_WhiteChar.add(WhiteChar345);

							PERCENT346=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition3138);  
							stream_PERCENT.add(PERCENT346);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 313:7: -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:10: ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:99: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:315:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:315:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:315:4: 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal347=(Token)match(input,88,FOLLOW_88_in_presetcondition3178);  
					stream_88.add(string_literal347);

					WhiteChar348=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3180);  
					stream_WhiteChar.add(WhiteChar348);

					pushFollow(FOLLOW_constant_in_presetcondition3184);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar349=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3186);  
					stream_WhiteChar.add(WhiteChar349);

					DAYS350=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3188);  
					stream_DAYS.add(DAYS350);

					WhiteChar351=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3195);  
					stream_WhiteChar.add(WhiteChar351);

					string_literal352=(Token)match(input,95,FOLLOW_95_in_presetcondition3197);  
					stream_95.add(string_literal352);

					WhiteChar353=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3199);  
					stream_WhiteChar.add(WhiteChar353);

					pushFollow(FOLLOW_constant_in_presetcondition3203);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar354=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3205);  
					stream_WhiteChar.add(WhiteChar354);

					DAYS355=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3207);  
					stream_DAYS.add(DAYS355);

					WhiteChar356=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3214);  
					stream_WhiteChar.add(WhiteChar356);

					string_literal357=(Token)match(input,76,FOLLOW_76_in_presetcondition3216);  
					stream_76.add(string_literal357);

					WhiteChar358=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3218);  
					stream_WhiteChar.add(WhiteChar358);

					pushFollow(FOLLOW_constant_in_presetcondition3222);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar359=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3224);  
					stream_WhiteChar.add(WhiteChar359);

					DAYS360=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3226);  
					stream_DAYS.add(DAYS360);

					WhiteChar361=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3233);  
					stream_WhiteChar.add(WhiteChar361);

					string_literal362=(Token)match(input,100,FOLLOW_100_in_presetcondition3235);  
					stream_100.add(string_literal362);

					WhiteChar363=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3237);  
					stream_WhiteChar.add(WhiteChar363);

					pushFollow(FOLLOW_constant_in_presetcondition3241);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar364=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3243);  
					stream_WhiteChar.add(WhiteChar364);

					DAYS365=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3245);  
					stream_DAYS.add(DAYS365);

					WhiteChar366=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3252);  
					stream_WhiteChar.add(WhiteChar366);

					string_literal367=(Token)match(input,79,FOLLOW_79_in_presetcondition3254);  
					stream_79.add(string_literal367);

					WhiteChar368=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3256);  
					stream_WhiteChar.add(WhiteChar368);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3260);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar369=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3266);  
					stream_WhiteChar.add(WhiteChar369);

					string_literal370=(Token)match(input,109,FOLLOW_109_in_presetcondition3268);  
					stream_109.add(string_literal370);

					WhiteChar371=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3270);  
					stream_WhiteChar.add(WhiteChar371);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3274);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar372=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3280);  
					stream_WhiteChar.add(WhiteChar372);

					string_literal373=(Token)match(input,102,FOLLOW_102_in_presetcondition3282);  
					stream_102.add(string_literal373);

					WhiteChar374=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3284);  
					stream_WhiteChar.add(WhiteChar374);

					char_literal375=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3286);  
					stream_OPENSQRT.add(char_literal375);

					pushFollow(FOLLOW_constant_in_presetcondition3290);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal376=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3292);  
					stream_COMMA.add(char_literal376);

					pushFollow(FOLLOW_constant_in_presetcondition3296);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal377=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3298);  
					stream_CLOSESQRT.add(char_literal377);

					WhiteChar378=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3300);  
					stream_WhiteChar.add(WhiteChar378);

					string_literal379=(Token)match(input,71,FOLLOW_71_in_presetcondition3302);  
					stream_71.add(string_literal379);

					WhiteChar380=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3304);  
					stream_WhiteChar.add(WhiteChar380);

					char_literal381=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3306);  
					stream_OPENSQRT.add(char_literal381);

					pushFollow(FOLLOW_constant_in_presetcondition3310);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal382=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3312);  
					stream_COMMA.add(char_literal382);

					pushFollow(FOLLOW_constant_in_presetcondition3316);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal383=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3318);  
					stream_CLOSESQRT.add(char_literal383);

					WhiteChar384=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3324);  
					stream_WhiteChar.add(WhiteChar384);

					string_literal385=(Token)match(input,99,FOLLOW_99_in_presetcondition3326);  
					stream_99.add(string_literal385);

					WhiteChar386=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3328);  
					stream_WhiteChar.add(WhiteChar386);

					char_literal387=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3330);  
					stream_OPENSQRT.add(char_literal387);

					pushFollow(FOLLOW_constant_in_presetcondition3334);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal388=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3336);  
					stream_COMMA.add(char_literal388);

					pushFollow(FOLLOW_constant_in_presetcondition3340);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal389=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3342);  
					stream_CLOSESQRT.add(char_literal389);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 323:4: -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:323:7: ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:323:246: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:4: 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal390=(Token)match(input,89,FOLLOW_89_in_presetcondition3393);  
					stream_89.add(string_literal390);

					WhiteChar391=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3395);  
					stream_WhiteChar.add(WhiteChar391);

					pushFollow(FOLLOW_constant_in_presetcondition3399);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar392=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3401);  
					stream_WhiteChar.add(WhiteChar392);

					DAYS393=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3403);  
					stream_DAYS.add(DAYS393);

					WhiteChar394=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3410);  
					stream_WhiteChar.add(WhiteChar394);

					string_literal395=(Token)match(input,95,FOLLOW_95_in_presetcondition3412);  
					stream_95.add(string_literal395);

					WhiteChar396=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3414);  
					stream_WhiteChar.add(WhiteChar396);

					pushFollow(FOLLOW_constant_in_presetcondition3418);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar397=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3420);  
					stream_WhiteChar.add(WhiteChar397);

					DAYS398=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3422);  
					stream_DAYS.add(DAYS398);

					WhiteChar399=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3429);  
					stream_WhiteChar.add(WhiteChar399);

					string_literal400=(Token)match(input,76,FOLLOW_76_in_presetcondition3431);  
					stream_76.add(string_literal400);

					WhiteChar401=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3433);  
					stream_WhiteChar.add(WhiteChar401);

					pushFollow(FOLLOW_constant_in_presetcondition3437);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar402=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3439);  
					stream_WhiteChar.add(WhiteChar402);

					DAYS403=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3441);  
					stream_DAYS.add(DAYS403);

					WhiteChar404=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3448);  
					stream_WhiteChar.add(WhiteChar404);

					string_literal405=(Token)match(input,100,FOLLOW_100_in_presetcondition3450);  
					stream_100.add(string_literal405);

					WhiteChar406=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3452);  
					stream_WhiteChar.add(WhiteChar406);

					pushFollow(FOLLOW_constant_in_presetcondition3456);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar407=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3458);  
					stream_WhiteChar.add(WhiteChar407);

					DAYS408=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3460);  
					stream_DAYS.add(DAYS408);

					WhiteChar409=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3467);  
					stream_WhiteChar.add(WhiteChar409);

					string_literal410=(Token)match(input,79,FOLLOW_79_in_presetcondition3469);  
					stream_79.add(string_literal410);

					WhiteChar411=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3471);  
					stream_WhiteChar.add(WhiteChar411);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3475);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar412=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3481);  
					stream_WhiteChar.add(WhiteChar412);

					string_literal413=(Token)match(input,109,FOLLOW_109_in_presetcondition3483);  
					stream_109.add(string_literal413);

					WhiteChar414=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3485);  
					stream_WhiteChar.add(WhiteChar414);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3489);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar415=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3495);  
					stream_WhiteChar.add(WhiteChar415);

					string_literal416=(Token)match(input,102,FOLLOW_102_in_presetcondition3497);  
					stream_102.add(string_literal416);

					WhiteChar417=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3499);  
					stream_WhiteChar.add(WhiteChar417);

					char_literal418=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3501);  
					stream_OPENSQRT.add(char_literal418);

					pushFollow(FOLLOW_constant_in_presetcondition3505);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal419=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3507);  
					stream_COMMA.add(char_literal419);

					pushFollow(FOLLOW_constant_in_presetcondition3511);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal420=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3513);  
					stream_CLOSESQRT.add(char_literal420);

					WhiteChar421=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3515);  
					stream_WhiteChar.add(WhiteChar421);

					string_literal422=(Token)match(input,71,FOLLOW_71_in_presetcondition3517);  
					stream_71.add(string_literal422);

					WhiteChar423=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3519);  
					stream_WhiteChar.add(WhiteChar423);

					char_literal424=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3521);  
					stream_OPENSQRT.add(char_literal424);

					pushFollow(FOLLOW_constant_in_presetcondition3525);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal425=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3527);  
					stream_COMMA.add(char_literal425);

					pushFollow(FOLLOW_constant_in_presetcondition3531);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal426=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3533);  
					stream_CLOSESQRT.add(char_literal426);

					WhiteChar427=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3539);  
					stream_WhiteChar.add(WhiteChar427);

					string_literal428=(Token)match(input,99,FOLLOW_99_in_presetcondition3541);  
					stream_99.add(string_literal428);

					WhiteChar429=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3543);  
					stream_WhiteChar.add(WhiteChar429);

					char_literal430=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3545);  
					stream_OPENSQRT.add(char_literal430);

					pushFollow(FOLLOW_constant_in_presetcondition3549);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal431=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3551);  
					stream_COMMA.add(char_literal431);

					pushFollow(FOLLOW_constant_in_presetcondition3555);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal432=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3557);  
					stream_CLOSESQRT.add(char_literal432);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 332:4: -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:332:7: ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:332:245: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:333:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:333:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:333:4: 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal433=(Token)match(input,90,FOLLOW_90_in_presetcondition3608);  
					stream_90.add(string_literal433);

					WhiteChar434=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3610);  
					stream_WhiteChar.add(WhiteChar434);

					pushFollow(FOLLOW_constant_in_presetcondition3614);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar435=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3616);  
					stream_WhiteChar.add(WhiteChar435);

					DAYS436=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3618);  
					stream_DAYS.add(DAYS436);

					WhiteChar437=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3625);  
					stream_WhiteChar.add(WhiteChar437);

					string_literal438=(Token)match(input,95,FOLLOW_95_in_presetcondition3627);  
					stream_95.add(string_literal438);

					WhiteChar439=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3629);  
					stream_WhiteChar.add(WhiteChar439);

					pushFollow(FOLLOW_constant_in_presetcondition3633);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar440=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3635);  
					stream_WhiteChar.add(WhiteChar440);

					DAYS441=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3637);  
					stream_DAYS.add(DAYS441);

					WhiteChar442=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3644);  
					stream_WhiteChar.add(WhiteChar442);

					string_literal443=(Token)match(input,76,FOLLOW_76_in_presetcondition3646);  
					stream_76.add(string_literal443);

					WhiteChar444=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3648);  
					stream_WhiteChar.add(WhiteChar444);

					pushFollow(FOLLOW_constant_in_presetcondition3652);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar445=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3654);  
					stream_WhiteChar.add(WhiteChar445);

					DAYS446=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3656);  
					stream_DAYS.add(DAYS446);

					WhiteChar447=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3663);  
					stream_WhiteChar.add(WhiteChar447);

					string_literal448=(Token)match(input,100,FOLLOW_100_in_presetcondition3665);  
					stream_100.add(string_literal448);

					WhiteChar449=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3667);  
					stream_WhiteChar.add(WhiteChar449);

					pushFollow(FOLLOW_constant_in_presetcondition3671);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar450=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3673);  
					stream_WhiteChar.add(WhiteChar450);

					DAYS451=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3675);  
					stream_DAYS.add(DAYS451);

					WhiteChar452=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3682);  
					stream_WhiteChar.add(WhiteChar452);

					string_literal453=(Token)match(input,79,FOLLOW_79_in_presetcondition3684);  
					stream_79.add(string_literal453);

					WhiteChar454=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3686);  
					stream_WhiteChar.add(WhiteChar454);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3690);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar455=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3696);  
					stream_WhiteChar.add(WhiteChar455);

					string_literal456=(Token)match(input,109,FOLLOW_109_in_presetcondition3698);  
					stream_109.add(string_literal456);

					WhiteChar457=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3700);  
					stream_WhiteChar.add(WhiteChar457);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3704);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar458=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3710);  
					stream_WhiteChar.add(WhiteChar458);

					string_literal459=(Token)match(input,102,FOLLOW_102_in_presetcondition3712);  
					stream_102.add(string_literal459);

					WhiteChar460=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3714);  
					stream_WhiteChar.add(WhiteChar460);

					char_literal461=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3716);  
					stream_OPENSQRT.add(char_literal461);

					pushFollow(FOLLOW_constant_in_presetcondition3720);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal462=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3722);  
					stream_COMMA.add(char_literal462);

					pushFollow(FOLLOW_constant_in_presetcondition3726);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal463=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3728);  
					stream_CLOSESQRT.add(char_literal463);

					WhiteChar464=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3730);  
					stream_WhiteChar.add(WhiteChar464);

					string_literal465=(Token)match(input,71,FOLLOW_71_in_presetcondition3732);  
					stream_71.add(string_literal465);

					WhiteChar466=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3734);  
					stream_WhiteChar.add(WhiteChar466);

					char_literal467=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3736);  
					stream_OPENSQRT.add(char_literal467);

					pushFollow(FOLLOW_constant_in_presetcondition3740);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal468=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3742);  
					stream_COMMA.add(char_literal468);

					pushFollow(FOLLOW_constant_in_presetcondition3746);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal469=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3748);  
					stream_CLOSESQRT.add(char_literal469);

					WhiteChar470=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3754);  
					stream_WhiteChar.add(WhiteChar470);

					string_literal471=(Token)match(input,99,FOLLOW_99_in_presetcondition3756);  
					stream_99.add(string_literal471);

					WhiteChar472=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3758);  
					stream_WhiteChar.add(WhiteChar472);

					char_literal473=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3760);  
					stream_OPENSQRT.add(char_literal473);

					pushFollow(FOLLOW_constant_in_presetcondition3764);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal474=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3766);  
					stream_COMMA.add(char_literal474);

					pushFollow(FOLLOW_constant_in_presetcondition3770);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal475=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3772);  
					stream_CLOSESQRT.add(char_literal475);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 341:4: -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:341:7: ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:341:245: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:342:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:342:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:342:4: 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal476=(Token)match(input,91,FOLLOW_91_in_presetcondition3823);  
					stream_91.add(string_literal476);

					WhiteChar477=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3825);  
					stream_WhiteChar.add(WhiteChar477);

					pushFollow(FOLLOW_constant_in_presetcondition3829);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar478=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3831);  
					stream_WhiteChar.add(WhiteChar478);

					DAYS479=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3833);  
					stream_DAYS.add(DAYS479);

					WhiteChar480=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3840);  
					stream_WhiteChar.add(WhiteChar480);

					string_literal481=(Token)match(input,95,FOLLOW_95_in_presetcondition3842);  
					stream_95.add(string_literal481);

					WhiteChar482=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3844);  
					stream_WhiteChar.add(WhiteChar482);

					pushFollow(FOLLOW_constant_in_presetcondition3848);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar483=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3850);  
					stream_WhiteChar.add(WhiteChar483);

					DAYS484=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3852);  
					stream_DAYS.add(DAYS484);

					WhiteChar485=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3859);  
					stream_WhiteChar.add(WhiteChar485);

					string_literal486=(Token)match(input,76,FOLLOW_76_in_presetcondition3861);  
					stream_76.add(string_literal486);

					WhiteChar487=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3863);  
					stream_WhiteChar.add(WhiteChar487);

					pushFollow(FOLLOW_constant_in_presetcondition3867);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar488=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3869);  
					stream_WhiteChar.add(WhiteChar488);

					DAYS489=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3871);  
					stream_DAYS.add(DAYS489);

					WhiteChar490=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3878);  
					stream_WhiteChar.add(WhiteChar490);

					string_literal491=(Token)match(input,100,FOLLOW_100_in_presetcondition3880);  
					stream_100.add(string_literal491);

					WhiteChar492=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3882);  
					stream_WhiteChar.add(WhiteChar492);

					pushFollow(FOLLOW_constant_in_presetcondition3886);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar493=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3888);  
					stream_WhiteChar.add(WhiteChar493);

					DAYS494=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3890);  
					stream_DAYS.add(DAYS494);

					WhiteChar495=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3897);  
					stream_WhiteChar.add(WhiteChar495);

					string_literal496=(Token)match(input,79,FOLLOW_79_in_presetcondition3899);  
					stream_79.add(string_literal496);

					WhiteChar497=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3901);  
					stream_WhiteChar.add(WhiteChar497);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3905);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar498=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3911);  
					stream_WhiteChar.add(WhiteChar498);

					string_literal499=(Token)match(input,109,FOLLOW_109_in_presetcondition3913);  
					stream_109.add(string_literal499);

					WhiteChar500=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3915);  
					stream_WhiteChar.add(WhiteChar500);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3919);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar501=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3925);  
					stream_WhiteChar.add(WhiteChar501);

					string_literal502=(Token)match(input,102,FOLLOW_102_in_presetcondition3927);  
					stream_102.add(string_literal502);

					WhiteChar503=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3929);  
					stream_WhiteChar.add(WhiteChar503);

					char_literal504=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3931);  
					stream_OPENSQRT.add(char_literal504);

					pushFollow(FOLLOW_constant_in_presetcondition3935);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal505=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3937);  
					stream_COMMA.add(char_literal505);

					pushFollow(FOLLOW_constant_in_presetcondition3941);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal506=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3943);  
					stream_CLOSESQRT.add(char_literal506);

					WhiteChar507=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3945);  
					stream_WhiteChar.add(WhiteChar507);

					string_literal508=(Token)match(input,71,FOLLOW_71_in_presetcondition3947);  
					stream_71.add(string_literal508);

					WhiteChar509=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3949);  
					stream_WhiteChar.add(WhiteChar509);

					char_literal510=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3951);  
					stream_OPENSQRT.add(char_literal510);

					pushFollow(FOLLOW_constant_in_presetcondition3955);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal511=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3957);  
					stream_COMMA.add(char_literal511);

					pushFollow(FOLLOW_constant_in_presetcondition3961);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal512=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3963);  
					stream_CLOSESQRT.add(char_literal512);

					WhiteChar513=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3969);  
					stream_WhiteChar.add(WhiteChar513);

					string_literal514=(Token)match(input,99,FOLLOW_99_in_presetcondition3971);  
					stream_99.add(string_literal514);

					WhiteChar515=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3973);  
					stream_WhiteChar.add(WhiteChar515);

					char_literal516=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3975);  
					stream_OPENSQRT.add(char_literal516);

					pushFollow(FOLLOW_constant_in_presetcondition3979);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal517=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3981);  
					stream_COMMA.add(char_literal517);

					pushFollow(FOLLOW_constant_in_presetcondition3985);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal518=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3987);  
					stream_CLOSESQRT.add(char_literal518);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 350:4: -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:350:7: ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:350:244: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:352:4: ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:352:4: ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:352:5: 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant
					{
					string_literal519=(Token)match(input,92,FOLLOW_92_in_presetcondition4040);  
					stream_92.add(string_literal519);

					WhiteChar520=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4042);  
					stream_WhiteChar.add(WhiteChar520);

					pushFollow(FOLLOW_constant_in_presetcondition4046);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar521=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4048);  
					stream_WhiteChar.add(WhiteChar521);

					DAYS522=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4050);  
					stream_DAYS.add(DAYS522);

					WhiteChar523=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4057);  
					stream_WhiteChar.add(WhiteChar523);

					string_literal524=(Token)match(input,95,FOLLOW_95_in_presetcondition4059);  
					stream_95.add(string_literal524);

					WhiteChar525=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4061);  
					stream_WhiteChar.add(WhiteChar525);

					pushFollow(FOLLOW_constant_in_presetcondition4065);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar526=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4067);  
					stream_WhiteChar.add(WhiteChar526);

					DAYS527=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4069);  
					stream_DAYS.add(DAYS527);

					WhiteChar528=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4076);  
					stream_WhiteChar.add(WhiteChar528);

					string_literal529=(Token)match(input,76,FOLLOW_76_in_presetcondition4078);  
					stream_76.add(string_literal529);

					WhiteChar530=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4080);  
					stream_WhiteChar.add(WhiteChar530);

					pushFollow(FOLLOW_constant_in_presetcondition4084);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar531=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4086);  
					stream_WhiteChar.add(WhiteChar531);

					DAYS532=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4088);  
					stream_DAYS.add(DAYS532);

					WhiteChar533=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4095);  
					stream_WhiteChar.add(WhiteChar533);

					string_literal534=(Token)match(input,100,FOLLOW_100_in_presetcondition4097);  
					stream_100.add(string_literal534);

					WhiteChar535=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4099);  
					stream_WhiteChar.add(WhiteChar535);

					pushFollow(FOLLOW_constant_in_presetcondition4103);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar536=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4105);  
					stream_WhiteChar.add(WhiteChar536);

					DAYS537=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4107);  
					stream_DAYS.add(DAYS537);

					WhiteChar538=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4114);  
					stream_WhiteChar.add(WhiteChar538);

					string_literal539=(Token)match(input,102,FOLLOW_102_in_presetcondition4116);  
					stream_102.add(string_literal539);

					WhiteChar540=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4118);  
					stream_WhiteChar.add(WhiteChar540);

					char_literal541=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4120);  
					stream_OPENSQRT.add(char_literal541);

					pushFollow(FOLLOW_constant_in_presetcondition4124);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal542=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4126);  
					stream_COMMA.add(char_literal542);

					pushFollow(FOLLOW_constant_in_presetcondition4130);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal543=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4132);  
					stream_CLOSESQRT.add(char_literal543);

					WhiteChar544=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4138);  
					stream_WhiteChar.add(WhiteChar544);

					string_literal545=(Token)match(input,103,FOLLOW_103_in_presetcondition4140);  
					stream_103.add(string_literal545);

					WhiteChar546=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4142);  
					stream_WhiteChar.add(WhiteChar546);

					pushFollow(FOLLOW_constant_in_presetcondition4146);
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
					// 358:4: -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:358:7: ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupportBreakDown, "SupportBreakDown"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:358:104: ^( String StringToken[\"\\\"greedy\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"greedy\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:358:140: ^( String StringToken[\"\\\"smooth\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"smooth\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:358:217: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:358:246: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:358:275: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:358:304: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:359:4: ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:359:4: ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:359:5: 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant
					{
					string_literal547=(Token)match(input,93,FOLLOW_93_in_presetcondition4223);  
					stream_93.add(string_literal547);

					WhiteChar548=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4225);  
					stream_WhiteChar.add(WhiteChar548);

					pushFollow(FOLLOW_constant_in_presetcondition4229);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar549=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4231);  
					stream_WhiteChar.add(WhiteChar549);

					DAYS550=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4233);  
					stream_DAYS.add(DAYS550);

					WhiteChar551=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4243);  
					stream_WhiteChar.add(WhiteChar551);

					string_literal552=(Token)match(input,95,FOLLOW_95_in_presetcondition4245);  
					stream_95.add(string_literal552);

					WhiteChar553=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4247);  
					stream_WhiteChar.add(WhiteChar553);

					pushFollow(FOLLOW_constant_in_presetcondition4251);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar554=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4253);  
					stream_WhiteChar.add(WhiteChar554);

					DAYS555=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4255);  
					stream_DAYS.add(DAYS555);

					WhiteChar556=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4265);  
					stream_WhiteChar.add(WhiteChar556);

					string_literal557=(Token)match(input,76,FOLLOW_76_in_presetcondition4267);  
					stream_76.add(string_literal557);

					WhiteChar558=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4269);  
					stream_WhiteChar.add(WhiteChar558);

					pushFollow(FOLLOW_constant_in_presetcondition4273);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar559=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4275);  
					stream_WhiteChar.add(WhiteChar559);

					DAYS560=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4277);  
					stream_DAYS.add(DAYS560);

					WhiteChar561=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4287);  
					stream_WhiteChar.add(WhiteChar561);

					string_literal562=(Token)match(input,100,FOLLOW_100_in_presetcondition4289);  
					stream_100.add(string_literal562);

					WhiteChar563=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4291);  
					stream_WhiteChar.add(WhiteChar563);

					pushFollow(FOLLOW_constant_in_presetcondition4295);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar564=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4297);  
					stream_WhiteChar.add(WhiteChar564);

					DAYS565=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4299);  
					stream_DAYS.add(DAYS565);

					WhiteChar566=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4309);  
					stream_WhiteChar.add(WhiteChar566);

					string_literal567=(Token)match(input,102,FOLLOW_102_in_presetcondition4311);  
					stream_102.add(string_literal567);

					WhiteChar568=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4313);  
					stream_WhiteChar.add(WhiteChar568);

					char_literal569=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4315);  
					stream_OPENSQRT.add(char_literal569);

					pushFollow(FOLLOW_constant_in_presetcondition4319);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal570=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4321);  
					stream_COMMA.add(char_literal570);

					pushFollow(FOLLOW_constant_in_presetcondition4325);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal571=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4327);  
					stream_CLOSESQRT.add(char_literal571);

					WhiteChar572=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4337);  
					stream_WhiteChar.add(WhiteChar572);

					string_literal573=(Token)match(input,103,FOLLOW_103_in_presetcondition4339);  
					stream_103.add(string_literal573);

					WhiteChar574=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4341);  
					stream_WhiteChar.add(WhiteChar574);

					pushFollow(FOLLOW_constant_in_presetcondition4345);
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
					// 365:6: -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:365:9: ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupportBreakUp, "SupportBreakUp"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:365:104: ^( String StringToken[\"\\\"greedy\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"greedy\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:365:140: ^( String StringToken[\"\\\"smooth\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"smooth\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:365:217: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:365:246: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:365:275: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:365:304: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:367:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:367:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:367:4: 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal575=(Token)match(input,105,FOLLOW_105_in_presetcondition4424);  
					stream_105.add(string_literal575);

					WhiteChar576=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4432);  
					stream_WhiteChar.add(WhiteChar576);

					string_literal577=(Token)match(input,95,FOLLOW_95_in_presetcondition4434);  
					stream_95.add(string_literal577);

					WhiteChar578=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4436);  
					stream_WhiteChar.add(WhiteChar578);

					pushFollow(FOLLOW_constant_in_presetcondition4440);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar579=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4442);  
					stream_WhiteChar.add(WhiteChar579);

					DAYS580=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4444);  
					stream_DAYS.add(DAYS580);

					WhiteChar581=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4452);  
					stream_WhiteChar.add(WhiteChar581);

					string_literal582=(Token)match(input,76,FOLLOW_76_in_presetcondition4454);  
					stream_76.add(string_literal582);

					WhiteChar583=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4456);  
					stream_WhiteChar.add(WhiteChar583);

					pushFollow(FOLLOW_constant_in_presetcondition4460);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar584=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4462);  
					stream_WhiteChar.add(WhiteChar584);

					DAYS585=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4464);  
					stream_DAYS.add(DAYS585);

					WhiteChar586=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4472);  
					stream_WhiteChar.add(WhiteChar586);

					string_literal587=(Token)match(input,72,FOLLOW_72_in_presetcondition4474);  
					stream_72.add(string_literal587);

					WhiteChar588=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4476);  
					stream_WhiteChar.add(WhiteChar588);

					pushFollow(FOLLOW_constant_in_presetcondition4480);
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
					// 371:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:371:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:371:79: ^( String StringToken[\"\\\"flat\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:372:3: ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:372:3: ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:372:4: 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal589=(Token)match(input,108,FOLLOW_108_in_presetcondition4515);  
					stream_108.add(string_literal589);

					WhiteChar590=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4523);  
					stream_WhiteChar.add(WhiteChar590);

					string_literal591=(Token)match(input,95,FOLLOW_95_in_presetcondition4525);  
					stream_95.add(string_literal591);

					WhiteChar592=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4527);  
					stream_WhiteChar.add(WhiteChar592);

					pushFollow(FOLLOW_constant_in_presetcondition4531);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar593=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4533);  
					stream_WhiteChar.add(WhiteChar593);

					DAYS594=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4535);  
					stream_DAYS.add(DAYS594);

					WhiteChar595=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4543);  
					stream_WhiteChar.add(WhiteChar595);

					string_literal596=(Token)match(input,76,FOLLOW_76_in_presetcondition4545);  
					stream_76.add(string_literal596);

					WhiteChar597=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4547);  
					stream_WhiteChar.add(WhiteChar597);

					pushFollow(FOLLOW_constant_in_presetcondition4551);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar598=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4553);  
					stream_WhiteChar.add(WhiteChar598);

					DAYS599=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4555);  
					stream_DAYS.add(DAYS599);

					WhiteChar600=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4563);  
					stream_WhiteChar.add(WhiteChar600);

					string_literal601=(Token)match(input,72,FOLLOW_72_in_presetcondition4565);  
					stream_72.add(string_literal601);

					WhiteChar602=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4567);  
					stream_WhiteChar.add(WhiteChar602);

					pushFollow(FOLLOW_constant_in_presetcondition4571);
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
					// 376:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:376:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:376:79: ^( String StringToken[\"\\\"up\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:377:3: ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:377:3: ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:377:4: 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal603=(Token)match(input,104,FOLLOW_104_in_presetcondition4606);  
					stream_104.add(string_literal603);

					WhiteChar604=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4614);  
					stream_WhiteChar.add(WhiteChar604);

					string_literal605=(Token)match(input,95,FOLLOW_95_in_presetcondition4616);  
					stream_95.add(string_literal605);

					WhiteChar606=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4618);  
					stream_WhiteChar.add(WhiteChar606);

					pushFollow(FOLLOW_constant_in_presetcondition4622);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar607=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4624);  
					stream_WhiteChar.add(WhiteChar607);

					DAYS608=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4626);  
					stream_DAYS.add(DAYS608);

					WhiteChar609=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4634);  
					stream_WhiteChar.add(WhiteChar609);

					string_literal610=(Token)match(input,76,FOLLOW_76_in_presetcondition4636);  
					stream_76.add(string_literal610);

					WhiteChar611=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4638);  
					stream_WhiteChar.add(WhiteChar611);

					pushFollow(FOLLOW_constant_in_presetcondition4642);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar612=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4644);  
					stream_WhiteChar.add(WhiteChar612);

					DAYS613=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4646);  
					stream_DAYS.add(DAYS613);

					WhiteChar614=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4654);  
					stream_WhiteChar.add(WhiteChar614);

					string_literal615=(Token)match(input,72,FOLLOW_72_in_presetcondition4656);  
					stream_72.add(string_literal615);

					WhiteChar616=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4658);  
					stream_WhiteChar.add(WhiteChar616);

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
					// 381:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:79: ^( String StringToken[\"\\\"down\\\"\"] )
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



	public static final BitSet FOLLOW_bullish_condition_in_complete_expression436 = new BitSet(new long[]{0x0000000000000000L,0x00000000000C0000L});
	public static final BitSet FOLLOW_bearish_condition_in_complete_expression438 = new BitSet(new long[]{0x8000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_also_display_in_complete_expression441 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_fixed_start_shift_in_complete_expression443 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_86_in_bullish_condition472 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition474 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bullish_condition476 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition478 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bullish_condition481 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition483 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_83_in_bearish_condition499 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition501 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_condition503 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition505 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bearish_condition508 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition510 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_bearish_not_bullish_in_bearish_condition520 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition523 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bearish_condition526 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition528 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_63_in_also_display545 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display547 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_also_display549 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display551 = new BitSet(new long[]{0x2000800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_also_display554 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_96_in_fixed_start_shift589 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift591 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_fixed_start_shift595 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift597 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_fixed_start_shift599 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_fixed_start_shift601 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_82_in_bearish_not_bullish630 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish638 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_bearish_not_bullish640 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish642 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish644 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish670 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OR_in_bearish_not_bullish672 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish674 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish676 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_and_expression_in_primary_expression718 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_expression_in_and_expression730 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_lenient_in_and_expression734 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression737 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_and_expression739 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression741 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_or_expression_in_and_expression743 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_matches_expression_in_or_expression770 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression773 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OR_in_or_expression775 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression777 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_matches_expression_in_or_expression779 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_atom_in_matches_expression804 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression807 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_MATCHING_in_matches_expression809 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression811 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_matches_expression813 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_matches_expression815 = new BitSet(new long[]{0x0000000000000180L});
	public static final BitSet FOLLOW_COMMA_in_matches_expression818 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_matches_expression820 = new BitSet(new long[]{0x0000000000000180L});
	public static final BitSet FOLLOW_CLOSESQRT_in_matches_expression824 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression826 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_atom_in_matches_expression828 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_booleanhistory_in_atom856 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom862 = new BitSet(new long[]{0x2100048400400000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom864 = new BitSet(new long[]{0x2100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_atom867 = new BitSet(new long[]{0x2000000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom869 = new BitSet(new long[]{0x2000000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom872 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_atom882 = new BitSet(new long[]{0x2000008000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom884 = new BitSet(new long[]{0x2000008000000000L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom887 = new BitSet(new long[]{0x2100048400400000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom889 = new BitSet(new long[]{0x2100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_atom892 = new BitSet(new long[]{0x2000000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom894 = new BitSet(new long[]{0x2000000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom897 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_conjunctiontruthof_in_atom911 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUTHOF_in_conjunctiontruthof923 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof925 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_conjunctiontruthof927 = new BitSet(new long[]{0x2000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_conjunctiontruthof930 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof932 = new BitSet(new long[]{0x0100048400400000L});
	public static final BitSet FOLLOW_primary_expression_in_conjunctiontruthof934 = new BitSet(new long[]{0x2000000000000100L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof938 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_87_in_conjunctiontruthof940 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof942 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_conjunctiontruthof944 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_conjunctiontruthof948 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_conjunctiontruthof950 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_conjunctiontruthof954 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_conjunctiontruthof956 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_booleanhistory984 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_booleanhistory986 = new BitSet(new long[]{0x0000000000000000L,0x00001F063F336E3CL});
	public static final BitSet FOLLOW_presetcondition_in_booleanhistory992 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_opcmpcondition_in_booleanhistory1003 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constantcmp_in_booleanhistory1014 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_operand1030 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Operation_in_operand1057 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NumberToken_in_constant1072 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_62_in_constant1084 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_StringToken_in_stringconstant1100 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_trendconstant1115 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_64_in_trendconstant1128 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_lenient1145 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_LENIENT_in_lenient1147 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_80_in_opcmpcondition1183 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1185 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1189 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1222 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_opcmpcondition1224 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1226 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1230 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1232 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1234 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1239 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_opcmpcondition1241 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1243 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1247 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1249 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1251 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_84_in_opcmpcondition1278 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1280 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1284 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1318 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_opcmpcondition1320 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1322 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1326 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1328 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1330 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1336 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_opcmpcondition1338 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1340 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1344 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1346 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1348 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_73_in_opcmpcondition1376 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1378 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1382 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1416 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_opcmpcondition1418 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1420 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1424 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1426 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1428 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1434 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_opcmpcondition1436 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1438 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1442 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1444 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1446 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_66_in_opcmpcondition1475 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1477 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1479 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1519 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_opcmpcondition1521 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1523 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1527 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1529 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1531 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1537 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_opcmpcondition1539 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1541 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1545 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1547 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1549 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1555 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_opcmpcondition1557 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1559 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1563 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1565 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1567 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_68_in_opcmpcondition1597 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1599 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1601 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1641 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_opcmpcondition1643 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1645 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1649 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1651 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1653 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1659 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_opcmpcondition1661 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1663 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1667 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1669 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1671 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_opcmpcondition1679 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1681 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1685 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1687 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1689 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_106_in_opcmpcondition1720 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1722 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1726 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1734 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_opcmpcondition1736 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1738 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1742 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1744 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1746 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1754 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_opcmpcondition1756 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1758 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1762 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1764 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1766 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1774 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_opcmpcondition1776 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1778 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition1782 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1790 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_opcmpcondition1792 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1794 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1798 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_107_in_opcmpcondition1830 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1832 = new BitSet(new long[]{0x0000040000400000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1836 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1844 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_opcmpcondition1846 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1848 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1852 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1854 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1856 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1864 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_opcmpcondition1866 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1868 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1872 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1874 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1876 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1884 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_opcmpcondition1886 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1888 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition1892 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_75_in_constantcmp1929 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1931 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000003L});
	public static final BitSet FOLLOW_trendconstant_in_constantcmp1935 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1969 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_constantcmp1971 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1973 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1977 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1979 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1981 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1983 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_constantcmp1985 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1987 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1991 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1993 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1995 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_74_in_constantcmp2019 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2021 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2025 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2066 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_constantcmp2068 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2070 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2074 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2076 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2078 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2084 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_constantcmp2086 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2088 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2092 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2094 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2096 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2102 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_constantcmp2104 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2106 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2110 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2112 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2114 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_81_in_constantcmp2144 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2146 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2150 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2191 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_constantcmp2193 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2195 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2199 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2201 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2203 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2209 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_constantcmp2211 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2213 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2217 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2219 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2221 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2227 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_constantcmp2229 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2231 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2235 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2237 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2239 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_85_in_constantcmp2269 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2271 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2275 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2316 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_constantcmp2318 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2320 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2324 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2326 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2328 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2334 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_constantcmp2336 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2338 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2342 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2344 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2346 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2352 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_constantcmp2354 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2356 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2360 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2362 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2364 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_97_in_presetcondition2401 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2441 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_94_in_presetcondition2443 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2445 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2449 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2451 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2453 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_presetcondition2455 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2457 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2461 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2463 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2465 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_98_in_presetcondition2501 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2541 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_94_in_presetcondition2543 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2545 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2549 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2551 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2553 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_presetcondition2555 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2557 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2561 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2563 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2565 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_77_in_presetcondition2602 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2604 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2608 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2610 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2659 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_presetcondition2661 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2663 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2667 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2669 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2671 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2682 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_presetcondition2684 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2686 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2690 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2692 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2694 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_78_in_presetcondition2738 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2740 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2744 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2746 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2795 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_presetcondition2797 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2799 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2803 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2805 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2807 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2818 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_presetcondition2820 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2822 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2826 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2828 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2830 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_presetcondition2875 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2877 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2881 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2930 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_presetcondition2932 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2934 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2938 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2940 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2942 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2953 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition2955 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2957 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2961 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2963 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2965 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2975 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_presetcondition2977 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2979 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2983 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2985 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2987 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_67_in_presetcondition3026 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3028 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3032 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3081 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_101_in_presetcondition3083 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3085 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3089 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3091 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3093 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3104 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition3106 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3108 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3112 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3114 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3116 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3126 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_presetcondition3128 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3130 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3134 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3136 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition3138 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_88_in_presetcondition3178 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3180 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3184 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3186 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3188 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3195 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition3197 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3199 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3203 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3205 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3207 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3214 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_presetcondition3216 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3218 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3222 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3224 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3226 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3233 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition3235 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3237 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3241 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3243 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3245 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3252 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition3254 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3256 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3260 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3266 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_presetcondition3268 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3270 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3274 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3280 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition3282 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3284 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3286 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3290 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3292 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3296 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3298 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3300 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_presetcondition3302 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3304 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3306 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3310 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3312 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3316 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3318 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3324 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_99_in_presetcondition3326 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3328 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3330 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3334 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3336 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3340 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3342 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_89_in_presetcondition3393 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3395 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3399 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3401 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3403 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3410 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition3412 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3414 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3418 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3420 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3422 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3429 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_presetcondition3431 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3433 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3437 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3439 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3441 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3448 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition3450 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3452 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3456 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3458 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3460 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3467 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition3469 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3471 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3475 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3481 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_presetcondition3483 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3485 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3489 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3495 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition3497 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3499 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3501 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3505 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3507 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3511 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3513 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3515 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_presetcondition3517 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3519 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3521 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3525 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3527 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3531 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3533 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3539 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_99_in_presetcondition3541 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3543 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3545 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3549 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3551 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3555 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3557 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_90_in_presetcondition3608 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3610 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3614 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3616 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3618 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3625 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition3627 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3629 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3633 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3635 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3637 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3644 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_presetcondition3646 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3648 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3652 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3654 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3656 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3663 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition3665 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3667 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3671 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3673 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3675 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3682 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition3684 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3686 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3690 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3696 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_presetcondition3698 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3700 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3704 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3710 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition3712 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3714 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3716 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3720 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3722 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3726 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3728 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3730 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_presetcondition3732 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3734 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3736 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3740 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3742 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3746 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3748 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3754 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_99_in_presetcondition3756 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3758 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3760 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3764 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3766 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3770 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3772 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_91_in_presetcondition3823 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3825 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3829 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3831 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3833 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3840 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition3842 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3844 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3848 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3850 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3852 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3859 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_presetcondition3861 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3863 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3867 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3869 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3871 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3878 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition3880 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3882 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3886 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3888 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3890 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3897 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition3899 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3901 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3905 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3911 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_presetcondition3913 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3915 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3919 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3925 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition3927 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3929 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3931 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3935 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3937 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3941 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3943 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3945 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_presetcondition3947 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3949 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3951 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3955 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3957 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3961 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3963 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3969 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_99_in_presetcondition3971 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3973 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3975 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3979 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3981 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3985 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3987 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_92_in_presetcondition4040 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4042 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4046 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4048 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4050 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4057 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition4059 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4061 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4065 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4067 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4069 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4076 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_presetcondition4078 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4080 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4084 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4086 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4088 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4095 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition4097 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4099 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4103 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4105 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4107 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4114 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition4116 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4118 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4120 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4124 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4126 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4130 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4132 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4138 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition4140 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4142 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4146 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_93_in_presetcondition4223 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4225 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4229 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4231 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4233 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4243 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition4245 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4247 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4251 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4253 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4255 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4265 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_presetcondition4267 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4269 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4273 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4275 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4277 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4287 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition4289 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4291 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4295 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4297 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4299 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4309 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_102_in_presetcondition4311 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4313 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4315 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4319 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4321 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4325 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4327 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4337 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_presetcondition4339 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4341 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4345 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_105_in_presetcondition4424 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4432 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition4434 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4436 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4440 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4442 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4444 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4452 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_presetcondition4454 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4456 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4460 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4462 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4464 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4472 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_presetcondition4474 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4476 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4480 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_108_in_presetcondition4515 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4523 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition4525 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4527 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4531 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4533 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4535 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4543 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_presetcondition4545 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4547 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4551 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4553 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4555 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4563 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_presetcondition4565 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4567 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4571 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_104_in_presetcondition4606 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4614 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_presetcondition4616 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4618 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4622 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4624 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4626 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4634 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_presetcondition4636 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4638 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4642 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4644 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4646 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4654 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_presetcondition4656 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4658 = new BitSet(new long[]{0x4000004000000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition4662 = new BitSet(new long[]{0x0000000000000002L});
}
