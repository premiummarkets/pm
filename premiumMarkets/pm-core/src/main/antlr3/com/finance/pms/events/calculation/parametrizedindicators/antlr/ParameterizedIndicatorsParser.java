// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2018-09-15 23:31:37
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
		"NOT", "NotDoubleMapCondition", "NullCondition", "Number", "NumberToken", 
		"OPENPARENTEHSIS", "OR", "Operation", "OperationOutput", "OrDoubleMapCondition", 
		"PERCENT", "ReverseCondition", "StockOperation", "String", "StringOperation", 
		"StringToken", "SupConstantCondition", "SupDoubleMapCondition", "Tcheat", 
		"UpRatioCondition", "WS", "WhiteChar", "','", "'NaN'", "'['", "']'", "'also display'", 
		"'bearish'", "'bullish'", "'crosses down historical'", "'crosses down threshold'", 
		"'crosses up historical'", "'crosses up threshold'", "'direction'", "'ending within'", 
		"'epsilon'", "'equals historical'", "'equals threshold'", "'equals trend'", 
		"'for'", "'goes down more than'", "'goes up more than'", "'is above historical'", 
		"'is above threshold'", "'is bearish if not bullish'", "'is bearish when'", 
		"'is below historical'", "'is below threshold'", "'is bullish when'", 
		"'makes a higher high spanning'", "'makes a higher low spanning'", "'makes a lower high spanning'", 
		"'makes a lower low spanning'", "'more than'", "'over'", "'override start shift with'", 
		"'reverses down'", "'reverses up'", "'smoothed'", "'spanning'", "'starting within'", 
		"'trends flat'", "'trends like'", "'trends unlike'"
	};
	public static final int EOF=-1;
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
	public static final int NOT=31;
	public static final int NotDoubleMapCondition=32;
	public static final int NullCondition=33;
	public static final int Number=34;
	public static final int NumberToken=35;
	public static final int OPENPARENTEHSIS=36;
	public static final int OR=37;
	public static final int Operation=38;
	public static final int OperationOutput=39;
	public static final int OrDoubleMapCondition=40;
	public static final int PERCENT=41;
	public static final int ReverseCondition=42;
	public static final int StockOperation=43;
	public static final int String=44;
	public static final int StringOperation=45;
	public static final int StringToken=46;
	public static final int SupConstantCondition=47;
	public static final int SupDoubleMapCondition=48;
	public static final int Tcheat=49;
	public static final int UpRatioCondition=50;
	public static final int WS=51;
	public static final int WhiteChar=52;

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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:149:1: complete_expression : bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:149:21: (bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:151:4: bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift
			{
			pushFollow(FOLLOW_bullish_condition_in_complete_expression363);
			bcond=bullish_condition();
			state._fsp--;

			stream_bullish_condition.add(bcond.getTree());
			pushFollow(FOLLOW_bearish_condition_in_complete_expression365);
			bearish_condition1=bearish_condition((bcond!=null?((CommonTree)bcond.getTree()):null));
			state._fsp--;

			stream_bearish_condition.add(bearish_condition1.getTree());
			pushFollow(FOLLOW_also_display_in_complete_expression368);
			also_display2=also_display();
			state._fsp--;

			stream_also_display.add(also_display2.getTree());
			pushFollow(FOLLOW_fixed_start_shift_in_complete_expression370);
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
			// 151:90: -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:151:93: ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:154:1: bullish_condition : 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression ;
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
		RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:154:19: ( 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:2: 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )*
			{
			string_literal4=(Token)match(input,79,FOLLOW_79_in_bullish_condition399);  
			stream_79.add(string_literal4);

			WhiteChar5=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition401);  
			stream_WhiteChar.add(WhiteChar5);

			pushFollow(FOLLOW_primary_expression_in_bullish_condition403);
			primary_expression6=primary_expression();
			state._fsp--;

			stream_primary_expression.add(primary_expression6.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:49: ( WhiteChar )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==WhiteChar) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:49: WhiteChar
					{
					WhiteChar7=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition405);  
					stream_WhiteChar.add(WhiteChar7);

					}
					break;

				default :
					break loop1;
				}
			}

			COMMA8=(Token)match(input,COMMA,FOLLOW_COMMA_in_bullish_condition408);  
			stream_COMMA.add(COMMA8);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:66: ( WhiteChar )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==WhiteChar) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:66: WhiteChar
					{
					WhiteChar9=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition410);  
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
			// 155:77: -> primary_expression
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:157:1: bearish_condition[CommonTree bcond] : ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* COMMA ( WhiteChar )* -> bearish_not_bullish );
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
		RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
		RewriteRuleSubtreeStream stream_bearish_not_bullish=new RewriteRuleSubtreeStream(adaptor,"rule bearish_not_bullish");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:157:37: ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* COMMA ( WhiteChar )* -> bearish_not_bullish )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==76) ) {
				alt7=1;
			}
			else if ( (LA7_0==75) ) {
				alt7=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:158:2: 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )*
					{
					string_literal10=(Token)match(input,76,FOLLOW_76_in_bearish_condition426);  
					stream_76.add(string_literal10);

					WhiteChar11=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition428);  
					stream_WhiteChar.add(WhiteChar11);

					pushFollow(FOLLOW_primary_expression_in_bearish_condition431);
					primary_expression12=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression12.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:158:50: ( WhiteChar )*
					loop3:
					while (true) {
						int alt3=2;
						int LA3_0 = input.LA(1);
						if ( (LA3_0==WhiteChar) ) {
							alt3=1;
						}

						switch (alt3) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:158:50: WhiteChar
							{
							WhiteChar13=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition433);  
							stream_WhiteChar.add(WhiteChar13);

							}
							break;

						default :
							break loop3;
						}
					}

					COMMA14=(Token)match(input,COMMA,FOLLOW_COMMA_in_bearish_condition436);  
					stream_COMMA.add(COMMA14);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:158:67: ( WhiteChar )*
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( (LA4_0==WhiteChar) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:158:67: WhiteChar
							{
							WhiteChar15=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition438);  
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
					// 158:78: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:2: bearish_not_bullish[$bcond] ( WhiteChar )* COMMA ( WhiteChar )*
					{
					pushFollow(FOLLOW_bearish_not_bullish_in_bearish_condition448);
					bearish_not_bullish16=bearish_not_bullish(bcond);
					state._fsp--;

					stream_bearish_not_bullish.add(bearish_not_bullish16.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:30: ( WhiteChar )*
					loop5:
					while (true) {
						int alt5=2;
						int LA5_0 = input.LA(1);
						if ( (LA5_0==WhiteChar) ) {
							alt5=1;
						}

						switch (alt5) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:30: WhiteChar
							{
							WhiteChar17=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition451);  
							stream_WhiteChar.add(WhiteChar17);

							}
							break;

						default :
							break loop5;
						}
					}

					COMMA18=(Token)match(input,COMMA,FOLLOW_COMMA_in_bearish_condition454);  
					stream_COMMA.add(COMMA18);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:47: ( WhiteChar )*
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( (LA6_0==WhiteChar) ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:47: WhiteChar
							{
							WhiteChar19=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition456);  
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
					// 159:58: -> bearish_not_bullish
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:161:2: also_display : ( 'also display' WhiteChar primary_expression ( WhiteChar )* COMMA -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition );
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
		RewriteRuleTokenStream stream_57=new RewriteRuleTokenStream(adaptor,"token 57");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:161:15: ( 'also display' WhiteChar primary_expression ( WhiteChar )* COMMA -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==57) ) {
				alt9=1;
			}
			else if ( (LA9_0==EOF||LA9_0==86) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:3: 'also display' WhiteChar primary_expression ( WhiteChar )* COMMA
					{
					string_literal20=(Token)match(input,57,FOLLOW_57_in_also_display473);  
					stream_57.add(string_literal20);

					WhiteChar21=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display475);  
					stream_WhiteChar.add(WhiteChar21);

					pushFollow(FOLLOW_primary_expression_in_also_display477);
					primary_expression22=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression22.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:47: ( WhiteChar )*
					loop8:
					while (true) {
						int alt8=2;
						int LA8_0 = input.LA(1);
						if ( (LA8_0==WhiteChar) ) {
							alt8=1;
						}

						switch (alt8) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:47: WhiteChar
							{
							WhiteChar23=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display479);  
							stream_WhiteChar.add(WhiteChar23);

							}
							break;

						default :
							break loop8;
						}
					}

					COMMA24=(Token)match(input,COMMA,FOLLOW_COMMA_in_also_display482);  
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
					// 162:64: -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:67: ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:91: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:3: 
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
					// 163:3: -> NullCondition
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:165:2: fixed_start_shift : ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS COMMA ->| -> ^( Number NumberToken[\"-1\"] ) );
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
		RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:165:20: ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS COMMA ->| -> ^( Number NumberToken[\"-1\"] ) )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==86) ) {
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:3: 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS COMMA
					{
					string_literal25=(Token)match(input,86,FOLLOW_86_in_fixed_start_shift517);  
					stream_86.add(string_literal25);

					WhiteChar26=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift519);  
					stream_WhiteChar.add(WhiteChar26);

					pushFollow(FOLLOW_constant_in_fixed_start_shift523);
					fixedStartShift=constant();
					state._fsp--;

					stream_constant.add(fixedStartShift.getTree());
					WhiteChar27=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift525);  
					stream_WhiteChar.add(WhiteChar27);

					DAYS28=(Token)match(input,DAYS,FOLLOW_DAYS_in_fixed_start_shift527);  
					stream_DAYS.add(DAYS28);

					COMMA29=(Token)match(input,COMMA,FOLLOW_COMMA_in_fixed_start_shift529);  
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
					// 166:87: ->
					{
						adaptor.addChild(root_0, (fixedStartShift!=null?((CommonTree)fixedStartShift.getTree()):null));
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
					// 167:3: -> ^( Number NumberToken[\"-1\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:6: ^( Number NumberToken[\"-1\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:1: bearish_not_bullish[CommonTree bcond] : 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) ) ;
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
		RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:39: ( 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:171:2: 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) )
			{
			string_literal30=(Token)match(input,75,FOLLOW_75_in_bearish_not_bullish559);  
			stream_75.add(string_literal30);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:172:3: ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:3: WhiteChar AND WhiteChar primary_expression
					{
					WhiteChar31=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish568);  
					stream_WhiteChar.add(WhiteChar31);

					AND32=(Token)match(input,AND,FOLLOW_AND_in_bearish_not_bullish570);  
					stream_AND.add(AND32);

					WhiteChar33=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish572);  
					stream_WhiteChar.add(WhiteChar33);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish574);
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
					// 173:46: -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:49: ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:73: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:173:108: ^( NotDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:174:3: WhiteChar OR WhiteChar primary_expression
					{
					WhiteChar35=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish601);  
					stream_WhiteChar.add(WhiteChar35);

					OR36=(Token)match(input,OR,FOLLOW_OR_in_bearish_not_bullish603);  
					stream_OR.add(OR36);

					WhiteChar37=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish605);  
					stream_WhiteChar.add(WhiteChar37);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish608);
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
					// 174:46: -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:174:49: ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrDoubleMapCondition, "OrDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:174:72: ^( NotDoubleMapCondition )
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
					// 175:3: -> ^( NotDoubleMapCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:175:6: ^( NotDoubleMapCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:1: primary_expression : and_expression ;
	public final ParameterizedIndicatorsParser.primary_expression_return primary_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.primary_expression_return retval = new ParameterizedIndicatorsParser.primary_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope and_expression39 =null;


		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:20: ( and_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:2: and_expression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_and_expression_in_primary_expression648);
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:1: and_expression : or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndDoubleMapCondition or_expression ( or_expression )* ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:16: ( or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndDoubleMapCondition or_expression ( or_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:3: or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )*
			{
			pushFollow(FOLLOW_or_expression_in_and_expression661);
			or_expression40=or_expression();
			state._fsp--;

			stream_or_expression.add(or_expression40.getTree());
			pushFollow(FOLLOW_lenient_in_and_expression665);
			lenientParam=lenient();
			state._fsp--;

			stream_lenient.add(lenientParam.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:38: ( WhiteChar AND WhiteChar or_expression )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:39: WhiteChar AND WhiteChar or_expression
					{
					WhiteChar41=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression668);  
					stream_WhiteChar.add(WhiteChar41);

					AND42=(Token)match(input,AND,FOLLOW_AND_in_and_expression670);  
					stream_AND.add(AND42);

					WhiteChar43=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression672);  
					stream_WhiteChar.add(WhiteChar43);

					pushFollow(FOLLOW_or_expression_in_and_expression674);
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
			// 184:79: -> ^( AndDoubleMapCondition or_expression ( or_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:82: ^( AndDoubleMapCondition or_expression ( or_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
				adaptor.addChild(root_1, (lenientParam!=null?((CommonTree)lenientParam.getTree()):null));
				adaptor.addChild(root_1, stream_or_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:142: ( or_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:1: or_expression : atom ( WhiteChar OR WhiteChar atom )* -> ^( OrDoubleMapCondition atom ( atom )* ) ;
	public final ParameterizedIndicatorsParser.or_expression_return or_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.or_expression_return retval = new ParameterizedIndicatorsParser.or_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar46=null;
		Token OR47=null;
		Token WhiteChar48=null;
		ParserRuleReturnScope atom45 =null;
		ParserRuleReturnScope atom49 =null;

		CommonTree WhiteChar46_tree=null;
		CommonTree OR47_tree=null;
		CommonTree WhiteChar48_tree=null;
		RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:15: ( atom ( WhiteChar OR WhiteChar atom )* -> ^( OrDoubleMapCondition atom ( atom )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:3: atom ( WhiteChar OR WhiteChar atom )*
			{
			pushFollow(FOLLOW_atom_in_or_expression702);
			atom45=atom();
			state._fsp--;

			stream_atom.add(atom45.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:8: ( WhiteChar OR WhiteChar atom )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:9: WhiteChar OR WhiteChar atom
					{
					WhiteChar46=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression705);  
					stream_WhiteChar.add(WhiteChar46);

					OR47=(Token)match(input,OR,FOLLOW_OR_in_or_expression707);  
					stream_OR.add(OR47);

					WhiteChar48=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression709);  
					stream_WhiteChar.add(WhiteChar48);

					pushFollow(FOLLOW_atom_in_or_expression711);
					atom49=atom();
					state._fsp--;

					stream_atom.add(atom49.getTree());
					}
					break;

				default :
					break loop13;
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
			// 187:39: -> ^( OrDoubleMapCondition atom ( atom )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:42: ^( OrDoubleMapCondition atom ( atom )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrDoubleMapCondition, "OrDoubleMapCondition"), root_1);
				adaptor.addChild(root_1, stream_atom.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:70: ( atom )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:1: atom : ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotDoubleMapCondition primary_expression ) );
	public final ParameterizedIndicatorsParser.atom_return atom() throws RecognitionException {
		ParameterizedIndicatorsParser.atom_return retval = new ParameterizedIndicatorsParser.atom_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal51=null;
		Token WhiteChar52=null;
		Token WhiteChar54=null;
		Token char_literal55=null;
		Token string_literal56=null;
		Token WhiteChar57=null;
		Token char_literal58=null;
		Token WhiteChar59=null;
		Token WhiteChar61=null;
		Token char_literal62=null;
		ParserRuleReturnScope booleanhistory50 =null;
		ParserRuleReturnScope primary_expression53 =null;
		ParserRuleReturnScope primary_expression60 =null;

		CommonTree char_literal51_tree=null;
		CommonTree WhiteChar52_tree=null;
		CommonTree WhiteChar54_tree=null;
		CommonTree char_literal55_tree=null;
		CommonTree string_literal56_tree=null;
		CommonTree WhiteChar57_tree=null;
		CommonTree char_literal58_tree=null;
		CommonTree WhiteChar59_tree=null;
		CommonTree WhiteChar61_tree=null;
		CommonTree char_literal62_tree=null;
		RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_CLOSEPARENTEHSIS=new RewriteRuleTokenStream(adaptor,"token CLOSEPARENTEHSIS");
		RewriteRuleTokenStream stream_OPENPARENTEHSIS=new RewriteRuleTokenStream(adaptor,"token OPENPARENTEHSIS");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:6: ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotDoubleMapCondition primary_expression ) )
			int alt19=3;
			switch ( input.LA(1) ) {
			case HistoricalData:
			case Operation:
				{
				alt19=1;
				}
				break;
			case OPENPARENTEHSIS:
				{
				alt19=2;
				}
				break;
			case NOT:
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:3: booleanhistory
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_booleanhistory_in_atom737);
					booleanhistory50=booleanhistory();
					state._fsp--;

					adaptor.addChild(root_0, booleanhistory50.getTree());

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:3: '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					char_literal51=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom744);  
					stream_OPENPARENTEHSIS.add(char_literal51);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:7: ( WhiteChar )*
					loop14:
					while (true) {
						int alt14=2;
						int LA14_0 = input.LA(1);
						if ( (LA14_0==WhiteChar) ) {
							alt14=1;
						}

						switch (alt14) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:7: WhiteChar
							{
							WhiteChar52=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom746);  
							stream_WhiteChar.add(WhiteChar52);

							}
							break;

						default :
							break loop14;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom749);
					primary_expression53=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression53.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:37: ( WhiteChar )*
					loop15:
					while (true) {
						int alt15=2;
						int LA15_0 = input.LA(1);
						if ( (LA15_0==WhiteChar) ) {
							alt15=1;
						}

						switch (alt15) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:37: WhiteChar
							{
							WhiteChar54=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom751);  
							stream_WhiteChar.add(WhiteChar54);

							}
							break;

						default :
							break loop15;
						}
					}

					char_literal55=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom754);  
					stream_CLOSEPARENTEHSIS.add(char_literal55);

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
					// 191:52: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:3: 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					string_literal56=(Token)match(input,NOT,FOLLOW_NOT_in_atom765);  
					stream_NOT.add(string_literal56);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:9: ( WhiteChar )*
					loop16:
					while (true) {
						int alt16=2;
						int LA16_0 = input.LA(1);
						if ( (LA16_0==WhiteChar) ) {
							alt16=1;
						}

						switch (alt16) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:9: WhiteChar
							{
							WhiteChar57=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom767);  
							stream_WhiteChar.add(WhiteChar57);

							}
							break;

						default :
							break loop16;
						}
					}

					char_literal58=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom770);  
					stream_OPENPARENTEHSIS.add(char_literal58);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:24: ( WhiteChar )*
					loop17:
					while (true) {
						int alt17=2;
						int LA17_0 = input.LA(1);
						if ( (LA17_0==WhiteChar) ) {
							alt17=1;
						}

						switch (alt17) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:24: WhiteChar
							{
							WhiteChar59=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom772);  
							stream_WhiteChar.add(WhiteChar59);

							}
							break;

						default :
							break loop17;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom775);
					primary_expression60=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression60.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:54: ( WhiteChar )*
					loop18:
					while (true) {
						int alt18=2;
						int LA18_0 = input.LA(1);
						if ( (LA18_0==WhiteChar) ) {
							alt18=1;
						}

						switch (alt18) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:54: WhiteChar
							{
							WhiteChar61=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom777);  
							stream_WhiteChar.add(WhiteChar61);

							}
							break;

						default :
							break loop18;
						}
					}

					char_literal62=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom780);  
					stream_CLOSEPARENTEHSIS.add(char_literal62);

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
					// 192:69: -> ^( NotDoubleMapCondition primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:72: ^( NotDoubleMapCondition primary_expression )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:1: booleanhistory : firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) ;
	public final ParameterizedIndicatorsParser.booleanhistory_return booleanhistory() throws RecognitionException {
		ParameterizedIndicatorsParser.booleanhistory_return retval = new ParameterizedIndicatorsParser.booleanhistory_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar63=null;
		ParserRuleReturnScope firstOp =null;
		ParserRuleReturnScope presetcondition64 =null;
		ParserRuleReturnScope opcmpcondition65 =null;
		ParserRuleReturnScope constantcmp66 =null;

		CommonTree WhiteChar63_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_presetcondition=new RewriteRuleSubtreeStream(adaptor,"rule presetcondition");
		RewriteRuleSubtreeStream stream_constantcmp=new RewriteRuleSubtreeStream(adaptor,"rule constantcmp");
		RewriteRuleSubtreeStream stream_opcmpcondition=new RewriteRuleSubtreeStream(adaptor,"rule opcmpcondition");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:16: (firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:18: firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			{
			pushFollow(FOLLOW_operand_in_booleanhistory802);
			firstOp=operand();
			state._fsp--;

			stream_operand.add(firstOp.getTree());
			WhiteChar63=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_booleanhistory804);  
			stream_WhiteChar.add(WhiteChar63);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:44: ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			int alt20=3;
			switch ( input.LA(1) ) {
			case 61:
			case 63:
			case 71:
			case 72:
			case 80:
			case 81:
			case 82:
			case 83:
			case 87:
			case 88:
			case 92:
				{
				alt20=1;
				}
				break;
			case 60:
			case 62:
			case 67:
			case 73:
			case 77:
			case 93:
			case 94:
				{
				alt20=2;
				}
				break;
			case 68:
			case 69:
			case 74:
			case 78:
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:46: presetcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_presetcondition_in_booleanhistory808);
					presetcondition64=presetcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_presetcondition.add(presetcondition64.getTree());
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
					// 195:77: -> presetcondition
					{
						adaptor.addChild(root_0, stream_presetcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:98: opcmpcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_opcmpcondition_in_booleanhistory817);
					opcmpcondition65=opcmpcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_opcmpcondition.add(opcmpcondition65.getTree());
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
					// 195:128: -> opcmpcondition
					{
						adaptor.addChild(root_0, stream_opcmpcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:147: constantcmp[$firstOp.tree]
					{
					pushFollow(FOLLOW_constantcmp_in_booleanhistory825);
					constantcmp66=constantcmp((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_constantcmp.add(constantcmp66.getTree());
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
					// 195:174: -> constantcmp
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:1: operand : ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation );
	public final ParameterizedIndicatorsParser.operand_return operand() throws RecognitionException {
		ParameterizedIndicatorsParser.operand_return retval = new ParameterizedIndicatorsParser.operand_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token opName=null;
		Token HistoricalData67=null;

		CommonTree opName_tree=null;
		CommonTree HistoricalData67_tree=null;
		RewriteRuleTokenStream stream_Operation=new RewriteRuleTokenStream(adaptor,"token Operation");
		RewriteRuleTokenStream stream_HistoricalData=new RewriteRuleTokenStream(adaptor,"token HistoricalData");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:9: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation )
			int alt21=2;
			int LA21_0 = input.LA(1);
			if ( (LA21_0==HistoricalData) ) {
				alt21=1;
			}
			else if ( (LA21_0==Operation) ) {
				alt21=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 21, 0, input);
				throw nvae;
			}

			switch (alt21) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:11: HistoricalData
					{
					HistoricalData67=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_operand839);  
					stream_HistoricalData.add(HistoricalData67);

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
					// 196:26: -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:29: ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:46: ^( OperationOutput HistoricalData )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
						adaptor.addChild(root_2, stream_HistoricalData.nextNode());
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:80: ^( String StringToken[\"\\\"THIS\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:117: opName= Operation
					{
					opName=(Token)match(input,Operation,FOLLOW_Operation_in_operand866);  
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
					// 196:171: -> Operation
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:1: constant : ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) );
	public final ParameterizedIndicatorsParser.constant_return constant() throws RecognitionException {
		ParameterizedIndicatorsParser.constant_return retval = new ParameterizedIndicatorsParser.constant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NumberToken68=null;
		Token string_literal69=null;

		CommonTree NumberToken68_tree=null;
		CommonTree string_literal69_tree=null;
		RewriteRuleTokenStream stream_NumberToken=new RewriteRuleTokenStream(adaptor,"token NumberToken");
		RewriteRuleTokenStream stream_54=new RewriteRuleTokenStream(adaptor,"token 54");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:10: ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) )
			int alt22=2;
			int LA22_0 = input.LA(1);
			if ( (LA22_0==NumberToken) ) {
				alt22=1;
			}
			else if ( (LA22_0==54) ) {
				alt22=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}

			switch (alt22) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:13: NumberToken
					{
					NumberToken68=(Token)match(input,NumberToken,FOLLOW_NumberToken_in_constant880);  
					stream_NumberToken.add(NumberToken68);

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
					// 197:25: -> ^( Number NumberToken )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:28: ^( Number NumberToken )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:52: 'NaN'
					{
					string_literal69=(Token)match(input,54,FOLLOW_54_in_constant892);  
					stream_54.add(string_literal69);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 197:58: -> ^( Number NumberToken[\"NaN\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:61: ^( Number NumberToken[\"NaN\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:1: stringconstant : StringToken -> ^( String StringToken ) ;
	public final ParameterizedIndicatorsParser.stringconstant_return stringconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.stringconstant_return retval = new ParameterizedIndicatorsParser.stringconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token StringToken70=null;

		CommonTree StringToken70_tree=null;
		RewriteRuleTokenStream stream_StringToken=new RewriteRuleTokenStream(adaptor,"token StringToken");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:16: ( StringToken -> ^( String StringToken ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:18: StringToken
			{
			StringToken70=(Token)match(input,StringToken,FOLLOW_StringToken_in_stringconstant908);  
			stream_StringToken.add(StringToken70);

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
			// 198:30: -> ^( String StringToken )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:33: ^( String StringToken )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:1: trendconstant : ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) );
	public final ParameterizedIndicatorsParser.trendconstant_return trendconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.trendconstant_return retval = new ParameterizedIndicatorsParser.trendconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal71=null;
		Token string_literal72=null;

		CommonTree string_literal71_tree=null;
		CommonTree string_literal72_tree=null;
		RewriteRuleTokenStream stream_58=new RewriteRuleTokenStream(adaptor,"token 58");
		RewriteRuleTokenStream stream_59=new RewriteRuleTokenStream(adaptor,"token 59");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:15: ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) )
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( (LA23_0==59) ) {
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:17: 'bullish'
					{
					string_literal71=(Token)match(input,59,FOLLOW_59_in_trendconstant923);  
					stream_59.add(string_literal71);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 199:27: -> ^( String StringToken[\"\\\"bullish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:30: ^( String StringToken[\"\\\"bullish\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:69: 'bearish'
					{
					string_literal72=(Token)match(input,58,FOLLOW_58_in_trendconstant936);  
					stream_58.add(string_literal72);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 199:79: -> ^( String StringToken[\"\\\"bearish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:82: ^( String StringToken[\"\\\"bearish\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:1: lenient : ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ;
	public final ParameterizedIndicatorsParser.lenient_return lenient() throws RecognitionException {
		ParameterizedIndicatorsParser.lenient_return retval = new ParameterizedIndicatorsParser.lenient_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar73=null;
		Token LENIENT74=null;

		CommonTree WhiteChar73_tree=null;
		CommonTree LENIENT74_tree=null;
		RewriteRuleTokenStream stream_LENIENT=new RewriteRuleTokenStream(adaptor,"token LENIENT");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:9: ( ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			int alt24=2;
			int LA24_0 = input.LA(1);
			if ( (LA24_0==WhiteChar) ) {
				int LA24_1 = input.LA(2);
				if ( (LA24_1==LENIENT) ) {
					alt24=1;
				}
				else if ( (LA24_1==AND||(LA24_1 >= CLOSEPARENTEHSIS && LA24_1 <= COMMA)||LA24_1==WhiteChar) ) {
					alt24=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 24, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( ((LA24_0 >= CLOSEPARENTEHSIS && LA24_0 <= COMMA)) ) {
				alt24=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 24, 0, input);
				throw nvae;
			}

			switch (alt24) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:12: WhiteChar LENIENT
					{
					WhiteChar73=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_lenient953);  
					stream_WhiteChar.add(WhiteChar73);

					LENIENT74=(Token)match(input,LENIENT,FOLLOW_LENIENT_in_lenient955);  
					stream_LENIENT.add(LENIENT74);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 200:30: -> ^( String StringToken[\"\\\"TRUE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:33: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:69: 
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
					// 200:69: -> ^( String StringToken[\"\\\"FALSE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:72: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:1: opcmpcondition[CommonTree firstOp] : ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) );
	public final ParameterizedIndicatorsParser.opcmpcondition_return opcmpcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.opcmpcondition_return retval = new ParameterizedIndicatorsParser.opcmpcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal75=null;
		Token WhiteChar76=null;
		Token WhiteChar77=null;
		Token string_literal78=null;
		Token WhiteChar79=null;
		Token WhiteChar80=null;
		Token DAYS81=null;
		Token string_literal82=null;
		Token WhiteChar83=null;
		Token WhiteChar84=null;
		Token string_literal85=null;
		Token WhiteChar86=null;
		Token WhiteChar87=null;
		Token DAYS88=null;
		Token string_literal89=null;
		Token WhiteChar90=null;
		Token WhiteChar91=null;
		Token string_literal92=null;
		Token WhiteChar93=null;
		Token WhiteChar94=null;
		Token DAYS95=null;
		Token string_literal96=null;
		Token WhiteChar97=null;
		Token WhiteChar99=null;
		Token string_literal100=null;
		Token WhiteChar101=null;
		Token WhiteChar102=null;
		Token DAYS103=null;
		Token WhiteChar104=null;
		Token string_literal105=null;
		Token WhiteChar106=null;
		Token WhiteChar107=null;
		Token DAYS108=null;
		Token string_literal109=null;
		Token WhiteChar110=null;
		Token WhiteChar112=null;
		Token string_literal113=null;
		Token WhiteChar114=null;
		Token WhiteChar115=null;
		Token DAYS116=null;
		Token WhiteChar117=null;
		Token string_literal118=null;
		Token WhiteChar119=null;
		Token WhiteChar120=null;
		Token DAYS121=null;
		Token string_literal122=null;
		Token WhiteChar123=null;
		Token WhiteChar124=null;
		Token string_literal125=null;
		Token WhiteChar126=null;
		Token WhiteChar127=null;
		Token DAYS128=null;
		Token WhiteChar129=null;
		Token string_literal130=null;
		Token WhiteChar131=null;
		Token WhiteChar132=null;
		Token DAYS133=null;
		Token WhiteChar134=null;
		Token string_literal135=null;
		Token WhiteChar136=null;
		Token WhiteChar137=null;
		Token string_literal138=null;
		Token WhiteChar139=null;
		Token string_literal140=null;
		Token WhiteChar141=null;
		Token WhiteChar142=null;
		Token string_literal143=null;
		Token WhiteChar144=null;
		Token WhiteChar145=null;
		Token DAYS146=null;
		Token WhiteChar147=null;
		Token string_literal148=null;
		Token WhiteChar149=null;
		Token WhiteChar150=null;
		Token DAYS151=null;
		Token WhiteChar152=null;
		Token string_literal153=null;
		Token WhiteChar154=null;
		ParserRuleReturnScope secondOp =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope spanningNbDays =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope direction =null;
		ParserRuleReturnScope epsilon =null;
		ParserRuleReturnScope operand98 =null;
		ParserRuleReturnScope operand111 =null;

		CommonTree string_literal75_tree=null;
		CommonTree WhiteChar76_tree=null;
		CommonTree WhiteChar77_tree=null;
		CommonTree string_literal78_tree=null;
		CommonTree WhiteChar79_tree=null;
		CommonTree WhiteChar80_tree=null;
		CommonTree DAYS81_tree=null;
		CommonTree string_literal82_tree=null;
		CommonTree WhiteChar83_tree=null;
		CommonTree WhiteChar84_tree=null;
		CommonTree string_literal85_tree=null;
		CommonTree WhiteChar86_tree=null;
		CommonTree WhiteChar87_tree=null;
		CommonTree DAYS88_tree=null;
		CommonTree string_literal89_tree=null;
		CommonTree WhiteChar90_tree=null;
		CommonTree WhiteChar91_tree=null;
		CommonTree string_literal92_tree=null;
		CommonTree WhiteChar93_tree=null;
		CommonTree WhiteChar94_tree=null;
		CommonTree DAYS95_tree=null;
		CommonTree string_literal96_tree=null;
		CommonTree WhiteChar97_tree=null;
		CommonTree WhiteChar99_tree=null;
		CommonTree string_literal100_tree=null;
		CommonTree WhiteChar101_tree=null;
		CommonTree WhiteChar102_tree=null;
		CommonTree DAYS103_tree=null;
		CommonTree WhiteChar104_tree=null;
		CommonTree string_literal105_tree=null;
		CommonTree WhiteChar106_tree=null;
		CommonTree WhiteChar107_tree=null;
		CommonTree DAYS108_tree=null;
		CommonTree string_literal109_tree=null;
		CommonTree WhiteChar110_tree=null;
		CommonTree WhiteChar112_tree=null;
		CommonTree string_literal113_tree=null;
		CommonTree WhiteChar114_tree=null;
		CommonTree WhiteChar115_tree=null;
		CommonTree DAYS116_tree=null;
		CommonTree WhiteChar117_tree=null;
		CommonTree string_literal118_tree=null;
		CommonTree WhiteChar119_tree=null;
		CommonTree WhiteChar120_tree=null;
		CommonTree DAYS121_tree=null;
		CommonTree string_literal122_tree=null;
		CommonTree WhiteChar123_tree=null;
		CommonTree WhiteChar124_tree=null;
		CommonTree string_literal125_tree=null;
		CommonTree WhiteChar126_tree=null;
		CommonTree WhiteChar127_tree=null;
		CommonTree DAYS128_tree=null;
		CommonTree WhiteChar129_tree=null;
		CommonTree string_literal130_tree=null;
		CommonTree WhiteChar131_tree=null;
		CommonTree WhiteChar132_tree=null;
		CommonTree DAYS133_tree=null;
		CommonTree WhiteChar134_tree=null;
		CommonTree string_literal135_tree=null;
		CommonTree WhiteChar136_tree=null;
		CommonTree WhiteChar137_tree=null;
		CommonTree string_literal138_tree=null;
		CommonTree WhiteChar139_tree=null;
		CommonTree string_literal140_tree=null;
		CommonTree WhiteChar141_tree=null;
		CommonTree WhiteChar142_tree=null;
		CommonTree string_literal143_tree=null;
		CommonTree WhiteChar144_tree=null;
		CommonTree WhiteChar145_tree=null;
		CommonTree DAYS146_tree=null;
		CommonTree WhiteChar147_tree=null;
		CommonTree string_literal148_tree=null;
		CommonTree WhiteChar149_tree=null;
		CommonTree WhiteChar150_tree=null;
		CommonTree DAYS151_tree=null;
		CommonTree WhiteChar152_tree=null;
		CommonTree string_literal153_tree=null;
		CommonTree WhiteChar154_tree=null;
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
		RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
		RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
		RewriteRuleTokenStream stream_94=new RewriteRuleTokenStream(adaptor,"token 94");
		RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
		RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
		RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");
		RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_stringconstant=new RewriteRuleSubtreeStream(adaptor,"rule stringconstant");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:37: ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) )
			int alt30=7;
			switch ( input.LA(1) ) {
			case 73:
				{
				alt30=1;
				}
				break;
			case 77:
				{
				alt30=2;
				}
				break;
			case 67:
				{
				alt30=3;
				}
				break;
			case 60:
				{
				alt30=4;
				}
				break;
			case 62:
				{
				alt30=5;
				}
				break;
			case 93:
				{
				alt30=6;
				}
				break;
			case 94:
				{
				alt30=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 30, 0, input);
				throw nvae;
			}
			switch (alt30) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:4: 'is above historical' WhiteChar secondOp= operand
					{
					string_literal75=(Token)match(input,73,FOLLOW_73_in_opcmpcondition992);  
					stream_73.add(string_literal75);

					WhiteChar76=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition994);  
					stream_WhiteChar.add(WhiteChar76);

					pushFollow(FOLLOW_operand_in_opcmpcondition998);
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
					// 205:53: -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:56: ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupDoubleMapCondition, "SupDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:80: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )?
					int alt25=2;
					int LA25_0 = input.LA(1);
					if ( (LA25_0==WhiteChar) ) {
						int LA25_1 = input.LA(2);
						if ( (LA25_1==70) ) {
							alt25=1;
						}
					}
					switch (alt25) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:6: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar77=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1024);  
							stream_WhiteChar.add(WhiteChar77);

							string_literal78=(Token)match(input,70,FOLLOW_70_in_opcmpcondition1026);  
							stream_70.add(string_literal78);

							WhiteChar79=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1028);  
							stream_WhiteChar.add(WhiteChar79);

							pushFollow(FOLLOW_constant_in_opcmpcondition1032);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar80=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1034);  
							stream_WhiteChar.add(WhiteChar80);

							DAYS81=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1036);  
							stream_DAYS.add(DAYS81);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 206:66: -> ^( SupDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:69: ^( SupDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:4: 'is below historical' WhiteChar secondOp= operand
					{
					string_literal82=(Token)match(input,77,FOLLOW_77_in_opcmpcondition1058);  
					stream_77.add(string_literal82);

					WhiteChar83=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1060);  
					stream_WhiteChar.add(WhiteChar83);

					pushFollow(FOLLOW_operand_in_opcmpcondition1064);
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
					// 207:53: -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:56: ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfDoubleMapCondition, "InfDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:80: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:5: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )?
					int alt26=2;
					int LA26_0 = input.LA(1);
					if ( (LA26_0==WhiteChar) ) {
						int LA26_1 = input.LA(2);
						if ( (LA26_1==70) ) {
							alt26=1;
						}
					}
					switch (alt26) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar84=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1092);  
							stream_WhiteChar.add(WhiteChar84);

							string_literal85=(Token)match(input,70,FOLLOW_70_in_opcmpcondition1094);  
							stream_70.add(string_literal85);

							WhiteChar86=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1096);  
							stream_WhiteChar.add(WhiteChar86);

							pushFollow(FOLLOW_constant_in_opcmpcondition1100);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar87=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1102);  
							stream_WhiteChar.add(WhiteChar87);

							DAYS88=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1104);  
							stream_DAYS.add(DAYS88);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 208:67: -> ^( InfDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:70: ^( InfDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:4: 'equals historical' WhiteChar secondOp= operand
					{
					string_literal89=(Token)match(input,67,FOLLOW_67_in_opcmpcondition1126);  
					stream_67.add(string_literal89);

					WhiteChar90=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1128);  
					stream_WhiteChar.add(WhiteChar90);

					pushFollow(FOLLOW_operand_in_opcmpcondition1132);
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
					// 209:51: -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:54: ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualDoubleMapCondition, "EqualDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:80: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:5: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )?
					int alt27=2;
					int LA27_0 = input.LA(1);
					if ( (LA27_0==WhiteChar) ) {
						int LA27_1 = input.LA(2);
						if ( (LA27_1==70) ) {
							alt27=1;
						}
					}
					switch (alt27) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar91=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1159);  
							stream_WhiteChar.add(WhiteChar91);

							string_literal92=(Token)match(input,70,FOLLOW_70_in_opcmpcondition1161);  
							stream_70.add(string_literal92);

							WhiteChar93=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1163);  
							stream_WhiteChar.add(WhiteChar93);

							pushFollow(FOLLOW_constant_in_opcmpcondition1167);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar94=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1169);  
							stream_WhiteChar.add(WhiteChar94);

							DAYS95=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1171);  
							stream_DAYS.add(DAYS95);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 210:67: -> ^( EqualDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:70: ^( EqualDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:3: ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:3: ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:4: 'crosses down historical' WhiteChar operand
					{
					string_literal96=(Token)match(input,60,FOLLOW_60_in_opcmpcondition1194);  
					stream_60.add(string_literal96);

					WhiteChar97=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1196);  
					stream_WhiteChar.add(WhiteChar97);

					pushFollow(FOLLOW_operand_in_opcmpcondition1198);
					operand98=operand();
					state._fsp--;

					stream_operand.add(operand98.getTree());
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
					// 212:48: -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:51: ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:81: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:110: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:9: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )?
					int alt28=2;
					int LA28_0 = input.LA(1);
					if ( (LA28_0==WhiteChar) ) {
						int LA28_1 = input.LA(2);
						if ( (LA28_1==90) ) {
							alt28=1;
						}
					}
					switch (alt28) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:11: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar99=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1235);  
							stream_WhiteChar.add(WhiteChar99);

							string_literal100=(Token)match(input,90,FOLLOW_90_in_opcmpcondition1237);  
							stream_90.add(string_literal100);

							WhiteChar101=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1239);  
							stream_WhiteChar.add(WhiteChar101);

							pushFollow(FOLLOW_constant_in_opcmpcondition1243);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar102=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1245);  
							stream_WhiteChar.add(WhiteChar102);

							DAYS103=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1247);  
							stream_DAYS.add(DAYS103);

							WhiteChar104=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1260);  
							stream_WhiteChar.add(WhiteChar104);

							string_literal105=(Token)match(input,85,FOLLOW_85_in_opcmpcondition1262);  
							stream_85.add(string_literal105);

							WhiteChar106=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1264);  
							stream_WhiteChar.add(WhiteChar106);

							pushFollow(FOLLOW_constant_in_opcmpcondition1268);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar107=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1270);  
							stream_WhiteChar.add(WhiteChar107);

							DAYS108=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1272);  
							stream_DAYS.add(DAYS108);

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
							// 215:11: -> ^( CrossDownDoubleMapCondition operand )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:14: ^( CrossDownDoubleMapCondition operand )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:3: ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:3: ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:4: 'crosses up historical' WhiteChar operand
					{
					string_literal109=(Token)match(input,62,FOLLOW_62_in_opcmpcondition1308);  
					stream_62.add(string_literal109);

					WhiteChar110=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1310);  
					stream_WhiteChar.add(WhiteChar110);

					pushFollow(FOLLOW_operand_in_opcmpcondition1312);
					operand111=operand();
					state._fsp--;

					stream_operand.add(operand111.getTree());
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
					// 217:46: -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:49: ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:77: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:106: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:8: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )?
					int alt29=2;
					int LA29_0 = input.LA(1);
					if ( (LA29_0==WhiteChar) ) {
						int LA29_1 = input.LA(2);
						if ( (LA29_1==90) ) {
							alt29=1;
						}
					}
					switch (alt29) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:218:10: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar112=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1348);  
							stream_WhiteChar.add(WhiteChar112);

							string_literal113=(Token)match(input,90,FOLLOW_90_in_opcmpcondition1350);  
							stream_90.add(string_literal113);

							WhiteChar114=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1352);  
							stream_WhiteChar.add(WhiteChar114);

							pushFollow(FOLLOW_constant_in_opcmpcondition1356);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar115=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1358);  
							stream_WhiteChar.add(WhiteChar115);

							DAYS116=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1360);  
							stream_DAYS.add(DAYS116);

							WhiteChar117=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1371);  
							stream_WhiteChar.add(WhiteChar117);

							string_literal118=(Token)match(input,85,FOLLOW_85_in_opcmpcondition1373);  
							stream_85.add(string_literal118);

							WhiteChar119=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1375);  
							stream_WhiteChar.add(WhiteChar119);

							pushFollow(FOLLOW_constant_in_opcmpcondition1379);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar120=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1381);  
							stream_WhiteChar.add(WhiteChar120);

							DAYS121=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1383);  
							stream_DAYS.add(DAYS121);

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
							// 220:10: -> ^( CrossUpDoubleMapCondition operand )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:13: ^( CrossUpDoubleMapCondition operand )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:4: 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal122=(Token)match(input,93,FOLLOW_93_in_opcmpcondition1418);  
					stream_93.add(string_literal122);

					WhiteChar123=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1420);  
					stream_WhiteChar.add(WhiteChar123);

					pushFollow(FOLLOW_operand_in_opcmpcondition1424);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar124=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1432);  
					stream_WhiteChar.add(WhiteChar124);

					string_literal125=(Token)match(input,85,FOLLOW_85_in_opcmpcondition1434);  
					stream_85.add(string_literal125);

					WhiteChar126=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1436);  
					stream_WhiteChar.add(WhiteChar126);

					pushFollow(FOLLOW_constant_in_opcmpcondition1440);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar127=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1442);  
					stream_WhiteChar.add(WhiteChar127);

					DAYS128=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1444);  
					stream_DAYS.add(DAYS128);

					WhiteChar129=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1452);  
					stream_WhiteChar.add(WhiteChar129);

					string_literal130=(Token)match(input,70,FOLLOW_70_in_opcmpcondition1454);  
					stream_70.add(string_literal130);

					WhiteChar131=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1456);  
					stream_WhiteChar.add(WhiteChar131);

					pushFollow(FOLLOW_constant_in_opcmpcondition1460);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar132=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1462);  
					stream_WhiteChar.add(WhiteChar132);

					DAYS133=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1464);  
					stream_DAYS.add(DAYS133);

					WhiteChar134=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1472);  
					stream_WhiteChar.add(WhiteChar134);

					string_literal135=(Token)match(input,64,FOLLOW_64_in_opcmpcondition1474);  
					stream_64.add(string_literal135);

					WhiteChar136=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1476);  
					stream_WhiteChar.add(WhiteChar136);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition1480);
					direction=stringconstant();
					state._fsp--;

					stream_stringconstant.add(direction.getTree());
					WhiteChar137=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1488);  
					stream_WhiteChar.add(WhiteChar137);

					string_literal138=(Token)match(input,66,FOLLOW_66_in_opcmpcondition1490);  
					stream_66.add(string_literal138);

					WhiteChar139=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1492);  
					stream_WhiteChar.add(WhiteChar139);

					pushFollow(FOLLOW_constant_in_opcmpcondition1496);
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
					// 227:7: -> ^( LinearSimilarTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:10: ^( LinearSimilarTrendsCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:4: 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant
					{
					string_literal140=(Token)match(input,94,FOLLOW_94_in_opcmpcondition1529);  
					stream_94.add(string_literal140);

					WhiteChar141=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1531);  
					stream_WhiteChar.add(WhiteChar141);

					pushFollow(FOLLOW_operand_in_opcmpcondition1535);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar142=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1543);  
					stream_WhiteChar.add(WhiteChar142);

					string_literal143=(Token)match(input,85,FOLLOW_85_in_opcmpcondition1545);  
					stream_85.add(string_literal143);

					WhiteChar144=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1547);  
					stream_WhiteChar.add(WhiteChar144);

					pushFollow(FOLLOW_constant_in_opcmpcondition1551);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar145=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1553);  
					stream_WhiteChar.add(WhiteChar145);

					DAYS146=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1555);  
					stream_DAYS.add(DAYS146);

					WhiteChar147=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1563);  
					stream_WhiteChar.add(WhiteChar147);

					string_literal148=(Token)match(input,70,FOLLOW_70_in_opcmpcondition1565);  
					stream_70.add(string_literal148);

					WhiteChar149=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1567);  
					stream_WhiteChar.add(WhiteChar149);

					pushFollow(FOLLOW_constant_in_opcmpcondition1571);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar150=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1573);  
					stream_WhiteChar.add(WhiteChar150);

					DAYS151=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1575);  
					stream_DAYS.add(DAYS151);

					WhiteChar152=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1583);  
					stream_WhiteChar.add(WhiteChar152);

					string_literal153=(Token)match(input,64,FOLLOW_64_in_opcmpcondition1585);  
					stream_64.add(string_literal153);

					WhiteChar154=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1587);  
					stream_WhiteChar.add(WhiteChar154);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition1591);
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
					// 233:7: -> ^( LinearOppositeTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:10: ^( LinearOppositeTrendsCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:1: constantcmp[CommonTree firstOp] : ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )? );
	public final ParameterizedIndicatorsParser.constantcmp_return constantcmp(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.constantcmp_return retval = new ParameterizedIndicatorsParser.constantcmp_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal155=null;
		Token WhiteChar156=null;
		Token WhiteChar157=null;
		Token string_literal158=null;
		Token WhiteChar159=null;
		Token WhiteChar160=null;
		Token DAYS161=null;
		Token WhiteChar162=null;
		Token string_literal163=null;
		Token WhiteChar164=null;
		Token WhiteChar165=null;
		Token DAYS166=null;
		Token string_literal167=null;
		Token WhiteChar168=null;
		Token WhiteChar169=null;
		Token string_literal170=null;
		Token WhiteChar171=null;
		Token WhiteChar172=null;
		Token DAYS173=null;
		Token WhiteChar174=null;
		Token string_literal175=null;
		Token WhiteChar176=null;
		Token WhiteChar177=null;
		Token DAYS178=null;
		Token string_literal179=null;
		Token WhiteChar180=null;
		Token WhiteChar181=null;
		Token string_literal182=null;
		Token WhiteChar183=null;
		Token WhiteChar184=null;
		Token DAYS185=null;
		Token WhiteChar186=null;
		Token string_literal187=null;
		Token WhiteChar188=null;
		Token WhiteChar189=null;
		Token DAYS190=null;
		Token string_literal191=null;
		Token WhiteChar192=null;
		Token WhiteChar193=null;
		Token string_literal194=null;
		Token WhiteChar195=null;
		Token WhiteChar196=null;
		Token DAYS197=null;
		Token WhiteChar198=null;
		Token string_literal199=null;
		Token WhiteChar200=null;
		Token WhiteChar201=null;
		Token DAYS202=null;
		ParserRuleReturnScope trendSignal =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope threshold =null;

		CommonTree string_literal155_tree=null;
		CommonTree WhiteChar156_tree=null;
		CommonTree WhiteChar157_tree=null;
		CommonTree string_literal158_tree=null;
		CommonTree WhiteChar159_tree=null;
		CommonTree WhiteChar160_tree=null;
		CommonTree DAYS161_tree=null;
		CommonTree WhiteChar162_tree=null;
		CommonTree string_literal163_tree=null;
		CommonTree WhiteChar164_tree=null;
		CommonTree WhiteChar165_tree=null;
		CommonTree DAYS166_tree=null;
		CommonTree string_literal167_tree=null;
		CommonTree WhiteChar168_tree=null;
		CommonTree WhiteChar169_tree=null;
		CommonTree string_literal170_tree=null;
		CommonTree WhiteChar171_tree=null;
		CommonTree WhiteChar172_tree=null;
		CommonTree DAYS173_tree=null;
		CommonTree WhiteChar174_tree=null;
		CommonTree string_literal175_tree=null;
		CommonTree WhiteChar176_tree=null;
		CommonTree WhiteChar177_tree=null;
		CommonTree DAYS178_tree=null;
		CommonTree string_literal179_tree=null;
		CommonTree WhiteChar180_tree=null;
		CommonTree WhiteChar181_tree=null;
		CommonTree string_literal182_tree=null;
		CommonTree WhiteChar183_tree=null;
		CommonTree WhiteChar184_tree=null;
		CommonTree DAYS185_tree=null;
		CommonTree WhiteChar186_tree=null;
		CommonTree string_literal187_tree=null;
		CommonTree WhiteChar188_tree=null;
		CommonTree WhiteChar189_tree=null;
		CommonTree DAYS190_tree=null;
		CommonTree string_literal191_tree=null;
		CommonTree WhiteChar192_tree=null;
		CommonTree WhiteChar193_tree=null;
		CommonTree string_literal194_tree=null;
		CommonTree WhiteChar195_tree=null;
		CommonTree WhiteChar196_tree=null;
		CommonTree DAYS197_tree=null;
		CommonTree WhiteChar198_tree=null;
		CommonTree string_literal199_tree=null;
		CommonTree WhiteChar200_tree=null;
		CommonTree WhiteChar201_tree=null;
		CommonTree DAYS202_tree=null;
		RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
		RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");
		RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_trendconstant=new RewriteRuleSubtreeStream(adaptor,"rule trendconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:34: ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )? )
			int alt35=4;
			switch ( input.LA(1) ) {
			case 69:
				{
				alt35=1;
				}
				break;
			case 68:
				{
				alt35=2;
				}
				break;
			case 74:
				{
				alt35=3;
				}
				break;
			case 78:
				{
				alt35=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 35, 0, input);
				throw nvae;
			}
			switch (alt35) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:4: 'equals trend' WhiteChar trendSignal= trendconstant
					{
					string_literal155=(Token)match(input,69,FOLLOW_69_in_constantcmp1629);  
					stream_69.add(string_literal155);

					WhiteChar156=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1631);  
					stream_WhiteChar.add(WhiteChar156);

					pushFollow(FOLLOW_trendconstant_in_constantcmp1635);
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
					// 238:55: -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:58: ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualStringConstantCondition, "EqualStringConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_trendconstant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:103: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:130: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					int alt31=2;
					int LA31_0 = input.LA(1);
					if ( (LA31_0==WhiteChar) ) {
						int LA31_1 = input.LA(2);
						if ( (LA31_1==85) ) {
							alt31=1;
						}
					}
					switch (alt31) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar157=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1669);  
							stream_WhiteChar.add(WhiteChar157);

							string_literal158=(Token)match(input,85,FOLLOW_85_in_constantcmp1671);  
							stream_85.add(string_literal158);

							WhiteChar159=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1673);  
							stream_WhiteChar.add(WhiteChar159);

							pushFollow(FOLLOW_constant_in_constantcmp1677);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar160=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1679);  
							stream_WhiteChar.add(WhiteChar160);

							DAYS161=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1681);  
							stream_DAYS.add(DAYS161);

							WhiteChar162=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1683);  
							stream_WhiteChar.add(WhiteChar162);

							string_literal163=(Token)match(input,70,FOLLOW_70_in_constantcmp1685);  
							stream_70.add(string_literal163);

							WhiteChar164=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1687);  
							stream_WhiteChar.add(WhiteChar164);

							pushFollow(FOLLOW_constant_in_constantcmp1691);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar165=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1693);  
							stream_WhiteChar.add(WhiteChar165);

							DAYS166=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1695);  
							stream_DAYS.add(DAYS166);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 239:129: -> ^( EqualStringConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:132: ^( EqualStringConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:4: 'equals threshold' WhiteChar threshold= constant
					{
					string_literal167=(Token)match(input,68,FOLLOW_68_in_constantcmp1723);  
					stream_68.add(string_literal167);

					WhiteChar168=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1725);  
					stream_WhiteChar.add(WhiteChar168);

					pushFollow(FOLLOW_constant_in_constantcmp1729);
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
					// 241:52: -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:55: ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualConstantCondition, "EqualConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:116: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )?
					int alt32=2;
					int LA32_0 = input.LA(1);
					if ( (LA32_0==WhiteChar) ) {
						int LA32_1 = input.LA(2);
						if ( (LA32_1==85) ) {
							alt32=1;
						}
					}
					switch (alt32) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar169=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1763);  
							stream_WhiteChar.add(WhiteChar169);

							string_literal170=(Token)match(input,85,FOLLOW_85_in_constantcmp1765);  
							stream_85.add(string_literal170);

							WhiteChar171=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1767);  
							stream_WhiteChar.add(WhiteChar171);

							pushFollow(FOLLOW_constant_in_constantcmp1771);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar172=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1773);  
							stream_WhiteChar.add(WhiteChar172);

							DAYS173=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1775);  
							stream_DAYS.add(DAYS173);

							WhiteChar174=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1777);  
							stream_WhiteChar.add(WhiteChar174);

							string_literal175=(Token)match(input,70,FOLLOW_70_in_constantcmp1779);  
							stream_70.add(string_literal175);

							WhiteChar176=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1781);  
							stream_WhiteChar.add(WhiteChar176);

							pushFollow(FOLLOW_constant_in_constantcmp1785);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar177=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1787);  
							stream_WhiteChar.add(WhiteChar177);

							DAYS178=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1789);  
							stream_DAYS.add(DAYS178);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 242:129: -> ^( EqualConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:132: ^( EqualConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:4: 'is above threshold' WhiteChar threshold= constant
					{
					string_literal179=(Token)match(input,74,FOLLOW_74_in_constantcmp1818);  
					stream_74.add(string_literal179);

					WhiteChar180=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1820);  
					stream_WhiteChar.add(WhiteChar180);

					pushFollow(FOLLOW_constant_in_constantcmp1824);
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
					// 244:54: -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:57: ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupConstantCondition, "SupConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:116: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )?
					int alt33=2;
					int LA33_0 = input.LA(1);
					if ( (LA33_0==WhiteChar) ) {
						int LA33_1 = input.LA(2);
						if ( (LA33_1==85) ) {
							alt33=1;
						}
					}
					switch (alt33) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar181=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1858);  
							stream_WhiteChar.add(WhiteChar181);

							string_literal182=(Token)match(input,85,FOLLOW_85_in_constantcmp1860);  
							stream_85.add(string_literal182);

							WhiteChar183=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1862);  
							stream_WhiteChar.add(WhiteChar183);

							pushFollow(FOLLOW_constant_in_constantcmp1866);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar184=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1868);  
							stream_WhiteChar.add(WhiteChar184);

							DAYS185=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1870);  
							stream_DAYS.add(DAYS185);

							WhiteChar186=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1872);  
							stream_WhiteChar.add(WhiteChar186);

							string_literal187=(Token)match(input,70,FOLLOW_70_in_constantcmp1874);  
							stream_70.add(string_literal187);

							WhiteChar188=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1876);  
							stream_WhiteChar.add(WhiteChar188);

							pushFollow(FOLLOW_constant_in_constantcmp1880);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar189=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1882);  
							stream_WhiteChar.add(WhiteChar189);

							DAYS190=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1884);  
							stream_DAYS.add(DAYS190);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 245:129: -> ^( SupConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:132: ^( SupConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:4: 'is below threshold' WhiteChar threshold= constant
					{
					string_literal191=(Token)match(input,78,FOLLOW_78_in_constantcmp1913);  
					stream_78.add(string_literal191);

					WhiteChar192=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1915);  
					stream_WhiteChar.add(WhiteChar192);

					pushFollow(FOLLOW_constant_in_constantcmp1919);
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
					// 247:54: -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:57: ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfConstantCondition, "InfConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:116: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )?
					int alt34=2;
					int LA34_0 = input.LA(1);
					if ( (LA34_0==WhiteChar) ) {
						int LA34_1 = input.LA(2);
						if ( (LA34_1==85) ) {
							alt34=1;
						}
					}
					switch (alt34) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar193=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1953);  
							stream_WhiteChar.add(WhiteChar193);

							string_literal194=(Token)match(input,85,FOLLOW_85_in_constantcmp1955);  
							stream_85.add(string_literal194);

							WhiteChar195=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1957);  
							stream_WhiteChar.add(WhiteChar195);

							pushFollow(FOLLOW_constant_in_constantcmp1961);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar196=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1963);  
							stream_WhiteChar.add(WhiteChar196);

							DAYS197=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1965);  
							stream_DAYS.add(DAYS197);

							WhiteChar198=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1967);  
							stream_WhiteChar.add(WhiteChar198);

							string_literal199=(Token)match(input,70,FOLLOW_70_in_constantcmp1969);  
							stream_70.add(string_literal199);

							WhiteChar200=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1971);  
							stream_WhiteChar.add(WhiteChar200);

							pushFollow(FOLLOW_constant_in_constantcmp1975);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar201=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1977);  
							stream_WhiteChar.add(WhiteChar201);

							DAYS202=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1979);  
							stream_DAYS.add(DAYS202);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 248:129: -> ^( InfConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:132: ^( InfConstantCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:1: presetcondition[CommonTree firstOp] : ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherHighCondition ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherLowCondition ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerHighCondition ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerLowCondition ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearFlatTrendsCondition ) ) );
	public final ParameterizedIndicatorsParser.presetcondition_return presetcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.presetcondition_return retval = new ParameterizedIndicatorsParser.presetcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal203=null;
		Token WhiteChar204=null;
		Token string_literal205=null;
		Token WhiteChar206=null;
		Token PERCENT207=null;
		Token WhiteChar208=null;
		Token string_literal209=null;
		Token WhiteChar210=null;
		Token WhiteChar211=null;
		Token DAYS212=null;
		Token string_literal213=null;
		Token WhiteChar214=null;
		Token string_literal215=null;
		Token WhiteChar216=null;
		Token PERCENT217=null;
		Token WhiteChar218=null;
		Token string_literal219=null;
		Token WhiteChar220=null;
		Token WhiteChar221=null;
		Token DAYS222=null;
		Token string_literal223=null;
		Token WhiteChar224=null;
		Token PERCENT225=null;
		Token WhiteChar226=null;
		Token string_literal227=null;
		Token WhiteChar228=null;
		Token WhiteChar229=null;
		Token DAYS230=null;
		Token WhiteChar231=null;
		Token string_literal232=null;
		Token WhiteChar233=null;
		Token WhiteChar234=null;
		Token DAYS235=null;
		Token string_literal236=null;
		Token WhiteChar237=null;
		Token PERCENT238=null;
		Token WhiteChar239=null;
		Token string_literal240=null;
		Token WhiteChar241=null;
		Token WhiteChar242=null;
		Token DAYS243=null;
		Token WhiteChar244=null;
		Token string_literal245=null;
		Token WhiteChar246=null;
		Token WhiteChar247=null;
		Token DAYS248=null;
		Token string_literal249=null;
		Token WhiteChar250=null;
		Token WhiteChar251=null;
		Token string_literal252=null;
		Token WhiteChar253=null;
		Token WhiteChar254=null;
		Token DAYS255=null;
		Token WhiteChar256=null;
		Token string_literal257=null;
		Token WhiteChar258=null;
		Token WhiteChar259=null;
		Token DAYS260=null;
		Token string_literal261=null;
		Token WhiteChar262=null;
		Token WhiteChar263=null;
		Token string_literal264=null;
		Token WhiteChar265=null;
		Token WhiteChar266=null;
		Token DAYS267=null;
		Token WhiteChar268=null;
		Token string_literal269=null;
		Token WhiteChar270=null;
		Token WhiteChar271=null;
		Token DAYS272=null;
		Token string_literal273=null;
		Token WhiteChar274=null;
		Token WhiteChar275=null;
		Token DAYS276=null;
		Token WhiteChar277=null;
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
		Token char_literal295=null;
		Token char_literal296=null;
		Token char_literal297=null;
		Token WhiteChar298=null;
		Token string_literal299=null;
		Token WhiteChar300=null;
		Token char_literal301=null;
		Token char_literal302=null;
		Token char_literal303=null;
		Token string_literal304=null;
		Token WhiteChar305=null;
		Token WhiteChar306=null;
		Token DAYS307=null;
		Token WhiteChar308=null;
		Token string_literal309=null;
		Token WhiteChar310=null;
		Token WhiteChar311=null;
		Token DAYS312=null;
		Token WhiteChar313=null;
		Token string_literal314=null;
		Token WhiteChar315=null;
		Token WhiteChar316=null;
		Token DAYS317=null;
		Token WhiteChar318=null;
		Token string_literal319=null;
		Token WhiteChar320=null;
		Token WhiteChar321=null;
		Token DAYS322=null;
		Token WhiteChar323=null;
		Token string_literal324=null;
		Token WhiteChar325=null;
		Token char_literal326=null;
		Token char_literal327=null;
		Token char_literal328=null;
		Token WhiteChar329=null;
		Token string_literal330=null;
		Token WhiteChar331=null;
		Token char_literal332=null;
		Token char_literal333=null;
		Token char_literal334=null;
		Token string_literal335=null;
		Token WhiteChar336=null;
		Token WhiteChar337=null;
		Token DAYS338=null;
		Token WhiteChar339=null;
		Token string_literal340=null;
		Token WhiteChar341=null;
		Token WhiteChar342=null;
		Token DAYS343=null;
		Token WhiteChar344=null;
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
		Token char_literal357=null;
		Token char_literal358=null;
		Token char_literal359=null;
		Token WhiteChar360=null;
		Token string_literal361=null;
		Token WhiteChar362=null;
		Token char_literal363=null;
		Token char_literal364=null;
		Token char_literal365=null;
		Token string_literal366=null;
		Token WhiteChar367=null;
		Token WhiteChar368=null;
		Token DAYS369=null;
		Token WhiteChar370=null;
		Token string_literal371=null;
		Token WhiteChar372=null;
		Token WhiteChar373=null;
		Token DAYS374=null;
		Token WhiteChar375=null;
		Token string_literal376=null;
		Token WhiteChar377=null;
		Token WhiteChar378=null;
		Token DAYS379=null;
		Token WhiteChar380=null;
		Token string_literal381=null;
		Token WhiteChar382=null;
		Token WhiteChar383=null;
		Token DAYS384=null;
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
		ParserRuleReturnScope epsilon =null;

		CommonTree string_literal203_tree=null;
		CommonTree WhiteChar204_tree=null;
		CommonTree string_literal205_tree=null;
		CommonTree WhiteChar206_tree=null;
		CommonTree PERCENT207_tree=null;
		CommonTree WhiteChar208_tree=null;
		CommonTree string_literal209_tree=null;
		CommonTree WhiteChar210_tree=null;
		CommonTree WhiteChar211_tree=null;
		CommonTree DAYS212_tree=null;
		CommonTree string_literal213_tree=null;
		CommonTree WhiteChar214_tree=null;
		CommonTree string_literal215_tree=null;
		CommonTree WhiteChar216_tree=null;
		CommonTree PERCENT217_tree=null;
		CommonTree WhiteChar218_tree=null;
		CommonTree string_literal219_tree=null;
		CommonTree WhiteChar220_tree=null;
		CommonTree WhiteChar221_tree=null;
		CommonTree DAYS222_tree=null;
		CommonTree string_literal223_tree=null;
		CommonTree WhiteChar224_tree=null;
		CommonTree PERCENT225_tree=null;
		CommonTree WhiteChar226_tree=null;
		CommonTree string_literal227_tree=null;
		CommonTree WhiteChar228_tree=null;
		CommonTree WhiteChar229_tree=null;
		CommonTree DAYS230_tree=null;
		CommonTree WhiteChar231_tree=null;
		CommonTree string_literal232_tree=null;
		CommonTree WhiteChar233_tree=null;
		CommonTree WhiteChar234_tree=null;
		CommonTree DAYS235_tree=null;
		CommonTree string_literal236_tree=null;
		CommonTree WhiteChar237_tree=null;
		CommonTree PERCENT238_tree=null;
		CommonTree WhiteChar239_tree=null;
		CommonTree string_literal240_tree=null;
		CommonTree WhiteChar241_tree=null;
		CommonTree WhiteChar242_tree=null;
		CommonTree DAYS243_tree=null;
		CommonTree WhiteChar244_tree=null;
		CommonTree string_literal245_tree=null;
		CommonTree WhiteChar246_tree=null;
		CommonTree WhiteChar247_tree=null;
		CommonTree DAYS248_tree=null;
		CommonTree string_literal249_tree=null;
		CommonTree WhiteChar250_tree=null;
		CommonTree WhiteChar251_tree=null;
		CommonTree string_literal252_tree=null;
		CommonTree WhiteChar253_tree=null;
		CommonTree WhiteChar254_tree=null;
		CommonTree DAYS255_tree=null;
		CommonTree WhiteChar256_tree=null;
		CommonTree string_literal257_tree=null;
		CommonTree WhiteChar258_tree=null;
		CommonTree WhiteChar259_tree=null;
		CommonTree DAYS260_tree=null;
		CommonTree string_literal261_tree=null;
		CommonTree WhiteChar262_tree=null;
		CommonTree WhiteChar263_tree=null;
		CommonTree string_literal264_tree=null;
		CommonTree WhiteChar265_tree=null;
		CommonTree WhiteChar266_tree=null;
		CommonTree DAYS267_tree=null;
		CommonTree WhiteChar268_tree=null;
		CommonTree string_literal269_tree=null;
		CommonTree WhiteChar270_tree=null;
		CommonTree WhiteChar271_tree=null;
		CommonTree DAYS272_tree=null;
		CommonTree string_literal273_tree=null;
		CommonTree WhiteChar274_tree=null;
		CommonTree WhiteChar275_tree=null;
		CommonTree DAYS276_tree=null;
		CommonTree WhiteChar277_tree=null;
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
		CommonTree char_literal295_tree=null;
		CommonTree char_literal296_tree=null;
		CommonTree char_literal297_tree=null;
		CommonTree WhiteChar298_tree=null;
		CommonTree string_literal299_tree=null;
		CommonTree WhiteChar300_tree=null;
		CommonTree char_literal301_tree=null;
		CommonTree char_literal302_tree=null;
		CommonTree char_literal303_tree=null;
		CommonTree string_literal304_tree=null;
		CommonTree WhiteChar305_tree=null;
		CommonTree WhiteChar306_tree=null;
		CommonTree DAYS307_tree=null;
		CommonTree WhiteChar308_tree=null;
		CommonTree string_literal309_tree=null;
		CommonTree WhiteChar310_tree=null;
		CommonTree WhiteChar311_tree=null;
		CommonTree DAYS312_tree=null;
		CommonTree WhiteChar313_tree=null;
		CommonTree string_literal314_tree=null;
		CommonTree WhiteChar315_tree=null;
		CommonTree WhiteChar316_tree=null;
		CommonTree DAYS317_tree=null;
		CommonTree WhiteChar318_tree=null;
		CommonTree string_literal319_tree=null;
		CommonTree WhiteChar320_tree=null;
		CommonTree WhiteChar321_tree=null;
		CommonTree DAYS322_tree=null;
		CommonTree WhiteChar323_tree=null;
		CommonTree string_literal324_tree=null;
		CommonTree WhiteChar325_tree=null;
		CommonTree char_literal326_tree=null;
		CommonTree char_literal327_tree=null;
		CommonTree char_literal328_tree=null;
		CommonTree WhiteChar329_tree=null;
		CommonTree string_literal330_tree=null;
		CommonTree WhiteChar331_tree=null;
		CommonTree char_literal332_tree=null;
		CommonTree char_literal333_tree=null;
		CommonTree char_literal334_tree=null;
		CommonTree string_literal335_tree=null;
		CommonTree WhiteChar336_tree=null;
		CommonTree WhiteChar337_tree=null;
		CommonTree DAYS338_tree=null;
		CommonTree WhiteChar339_tree=null;
		CommonTree string_literal340_tree=null;
		CommonTree WhiteChar341_tree=null;
		CommonTree WhiteChar342_tree=null;
		CommonTree DAYS343_tree=null;
		CommonTree WhiteChar344_tree=null;
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
		CommonTree char_literal357_tree=null;
		CommonTree char_literal358_tree=null;
		CommonTree char_literal359_tree=null;
		CommonTree WhiteChar360_tree=null;
		CommonTree string_literal361_tree=null;
		CommonTree WhiteChar362_tree=null;
		CommonTree char_literal363_tree=null;
		CommonTree char_literal364_tree=null;
		CommonTree char_literal365_tree=null;
		CommonTree string_literal366_tree=null;
		CommonTree WhiteChar367_tree=null;
		CommonTree WhiteChar368_tree=null;
		CommonTree DAYS369_tree=null;
		CommonTree WhiteChar370_tree=null;
		CommonTree string_literal371_tree=null;
		CommonTree WhiteChar372_tree=null;
		CommonTree WhiteChar373_tree=null;
		CommonTree DAYS374_tree=null;
		CommonTree WhiteChar375_tree=null;
		CommonTree string_literal376_tree=null;
		CommonTree WhiteChar377_tree=null;
		CommonTree WhiteChar378_tree=null;
		CommonTree DAYS379_tree=null;
		CommonTree WhiteChar380_tree=null;
		CommonTree string_literal381_tree=null;
		CommonTree WhiteChar382_tree=null;
		CommonTree WhiteChar383_tree=null;
		CommonTree DAYS384_tree=null;
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
		RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");
		RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
		RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
		RewriteRuleTokenStream stream_91=new RewriteRuleTokenStream(adaptor,"token 91");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
		RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
		RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
		RewriteRuleTokenStream stream_53=new RewriteRuleTokenStream(adaptor,"token 53");
		RewriteRuleTokenStream stream_55=new RewriteRuleTokenStream(adaptor,"token 55");
		RewriteRuleTokenStream stream_56=new RewriteRuleTokenStream(adaptor,"token 56");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
		RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
		RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
		RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
		RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
		RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
		RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
		RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");
		RewriteRuleTokenStream stream_87=new RewriteRuleTokenStream(adaptor,"token 87");
		RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:38: ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherHighCondition ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherLowCondition ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerHighCondition ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerLowCondition ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearFlatTrendsCondition ) ) )
			int alt42=11;
			switch ( input.LA(1) ) {
			case 87:
				{
				alt42=1;
				}
				break;
			case 88:
				{
				alt42=2;
				}
				break;
			case 71:
				{
				alt42=3;
				}
				break;
			case 72:
				{
				alt42=4;
				}
				break;
			case 63:
				{
				alt42=5;
				}
				break;
			case 61:
				{
				alt42=6;
				}
				break;
			case 80:
				{
				alt42=7;
				}
				break;
			case 81:
				{
				alt42=8;
				}
				break;
			case 82:
				{
				alt42=9;
				}
				break;
			case 83:
				{
				alt42=10;
				}
				break;
			case 92:
				{
				alt42=11;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 42, 0, input);
				throw nvae;
			}
			switch (alt42) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:4: 'reverses down'
					{
					string_literal203=(Token)match(input,87,FOLLOW_87_in_presetcondition2011);  
					stream_87.add(string_literal203);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 253:20: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:23: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:42: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:70: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:99: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:7: ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					int alt36=2;
					int LA36_0 = input.LA(1);
					if ( (LA36_0==WhiteChar) ) {
						int LA36_1 = input.LA(2);
						if ( (LA36_1==84) ) {
							alt36=1;
						}
					}
					switch (alt36) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:9: WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar204=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2051);  
							stream_WhiteChar.add(WhiteChar204);

							string_literal205=(Token)match(input,84,FOLLOW_84_in_presetcondition2053);  
							stream_84.add(string_literal205);

							WhiteChar206=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2055);  
							stream_WhiteChar.add(WhiteChar206);

							pushFollow(FOLLOW_constant_in_presetcondition2059);
							percentdown=constant();
							state._fsp--;

							stream_constant.add(percentdown.getTree());
							PERCENT207=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2061);  
							stream_PERCENT.add(PERCENT207);

							WhiteChar208=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2063);  
							stream_WhiteChar.add(WhiteChar208);

							string_literal209=(Token)match(input,90,FOLLOW_90_in_presetcondition2065);  
							stream_90.add(string_literal209);

							WhiteChar210=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2067);  
							stream_WhiteChar.add(WhiteChar210);

							pushFollow(FOLLOW_constant_in_presetcondition2071);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar211=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2073);  
							stream_WhiteChar.add(WhiteChar211);

							DAYS212=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2075);  
							stream_DAYS.add(DAYS212);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 255:7: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:10: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:29: ^( Number NumberToken[\"-1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:4: 'reverses up'
					{
					string_literal213=(Token)match(input,88,FOLLOW_88_in_presetcondition2119);  
					stream_88.add(string_literal213);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 257:18: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:21: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:40: ^( Number NumberToken[\"1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:67: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:96: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:7: ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					int alt37=2;
					int LA37_0 = input.LA(1);
					if ( (LA37_0==WhiteChar) ) {
						int LA37_1 = input.LA(2);
						if ( (LA37_1==84) ) {
							alt37=1;
						}
					}
					switch (alt37) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:9: WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar214=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2159);  
							stream_WhiteChar.add(WhiteChar214);

							string_literal215=(Token)match(input,84,FOLLOW_84_in_presetcondition2161);  
							stream_84.add(string_literal215);

							WhiteChar216=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2163);  
							stream_WhiteChar.add(WhiteChar216);

							pushFollow(FOLLOW_constant_in_presetcondition2167);
							percentup=constant();
							state._fsp--;

							stream_constant.add(percentup.getTree());
							PERCENT217=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2169);  
							stream_PERCENT.add(PERCENT217);

							WhiteChar218=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2171);  
							stream_WhiteChar.add(WhiteChar218);

							string_literal219=(Token)match(input,90,FOLLOW_90_in_presetcondition2173);  
							stream_90.add(string_literal219);

							WhiteChar220=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2175);  
							stream_WhiteChar.add(WhiteChar220);

							pushFollow(FOLLOW_constant_in_presetcondition2179);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar221=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2181);  
							stream_WhiteChar.add(WhiteChar221);

							DAYS222=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2183);  
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
							// 259:7: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:259:10: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:259:29: ^( Number NumberToken[\"1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:4: 'goes down more than' WhiteChar percentdown= constant PERCENT
					{
					string_literal223=(Token)match(input,71,FOLLOW_71_in_presetcondition2226);  
					stream_71.add(string_literal223);

					WhiteChar224=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2228);  
					stream_WhiteChar.add(WhiteChar224);

					pushFollow(FOLLOW_constant_in_presetcondition2232);
					percentdown=constant();
					state._fsp--;

					stream_constant.add(percentdown.getTree());
					PERCENT225=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2234);  
					stream_PERCENT.add(PERCENT225);

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
					// 261:65: -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:68: ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:98: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:127: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:156: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar226=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2276);  
							stream_WhiteChar.add(WhiteChar226);

							string_literal227=(Token)match(input,90,FOLLOW_90_in_presetcondition2278);  
							stream_90.add(string_literal227);

							WhiteChar228=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2280);  
							stream_WhiteChar.add(WhiteChar228);

							pushFollow(FOLLOW_constant_in_presetcondition2284);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar229=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2286);  
							stream_WhiteChar.add(WhiteChar229);

							DAYS230=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2288);  
							stream_DAYS.add(DAYS230);

							WhiteChar231=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2299);  
							stream_WhiteChar.add(WhiteChar231);

							string_literal232=(Token)match(input,70,FOLLOW_70_in_presetcondition2301);  
							stream_70.add(string_literal232);

							WhiteChar233=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2303);  
							stream_WhiteChar.add(WhiteChar233);

							pushFollow(FOLLOW_constant_in_presetcondition2307);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar234=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2309);  
							stream_WhiteChar.add(WhiteChar234);

							DAYS235=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2311);  
							stream_DAYS.add(DAYS235);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 264:7: -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:10: ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentdown!=null?((CommonTree)percentdown.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:75: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:4: 'goes up more than' WhiteChar percentup= constant PERCENT
					{
					string_literal236=(Token)match(input,72,FOLLOW_72_in_presetcondition2349);  
					stream_72.add(string_literal236);

					WhiteChar237=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2351);  
					stream_WhiteChar.add(WhiteChar237);

					pushFollow(FOLLOW_constant_in_presetcondition2355);
					percentup=constant();
					state._fsp--;

					stream_constant.add(percentup.getTree());
					PERCENT238=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2357);  
					stream_PERCENT.add(PERCENT238);

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
					// 265:61: -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:64: ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:92: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:121: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:150: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt39=2;
					int LA39_0 = input.LA(1);
					if ( (LA39_0==WhiteChar) ) {
						int LA39_1 = input.LA(2);
						if ( (LA39_1==90) ) {
							alt39=1;
						}
					}
					switch (alt39) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar239=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2399);  
							stream_WhiteChar.add(WhiteChar239);

							string_literal240=(Token)match(input,90,FOLLOW_90_in_presetcondition2401);  
							stream_90.add(string_literal240);

							WhiteChar241=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2403);  
							stream_WhiteChar.add(WhiteChar241);

							pushFollow(FOLLOW_constant_in_presetcondition2407);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar242=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2409);  
							stream_WhiteChar.add(WhiteChar242);

							DAYS243=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2411);  
							stream_DAYS.add(DAYS243);

							WhiteChar244=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2422);  
							stream_WhiteChar.add(WhiteChar244);

							string_literal245=(Token)match(input,70,FOLLOW_70_in_presetcondition2424);  
							stream_70.add(string_literal245);

							WhiteChar246=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2426);  
							stream_WhiteChar.add(WhiteChar246);

							pushFollow(FOLLOW_constant_in_presetcondition2430);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar247=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2432);  
							stream_WhiteChar.add(WhiteChar247);

							DAYS248=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2434);  
							stream_DAYS.add(DAYS248);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 268:7: -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:268:10: ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentup!=null?((CommonTree)percentup.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:268:71: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:4: 'crosses up threshold' WhiteChar threshold= constant
					{
					string_literal249=(Token)match(input,63,FOLLOW_63_in_presetcondition2479);  
					stream_63.add(string_literal249);

					WhiteChar250=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2481);  
					stream_WhiteChar.add(WhiteChar250);

					pushFollow(FOLLOW_constant_in_presetcondition2485);
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
					// 270:56: -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:59: ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:95: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:124: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:153: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt40=2;
					int LA40_0 = input.LA(1);
					if ( (LA40_0==WhiteChar) ) {
						int LA40_1 = input.LA(2);
						if ( (LA40_1==90) ) {
							alt40=1;
						}
					}
					switch (alt40) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar251=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2527);  
							stream_WhiteChar.add(WhiteChar251);

							string_literal252=(Token)match(input,90,FOLLOW_90_in_presetcondition2529);  
							stream_90.add(string_literal252);

							WhiteChar253=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2531);  
							stream_WhiteChar.add(WhiteChar253);

							pushFollow(FOLLOW_constant_in_presetcondition2535);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar254=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2537);  
							stream_WhiteChar.add(WhiteChar254);

							DAYS255=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2539);  
							stream_DAYS.add(DAYS255);

							WhiteChar256=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2550);  
							stream_WhiteChar.add(WhiteChar256);

							string_literal257=(Token)match(input,85,FOLLOW_85_in_presetcondition2552);  
							stream_85.add(string_literal257);

							WhiteChar258=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2554);  
							stream_WhiteChar.add(WhiteChar258);

							pushFollow(FOLLOW_constant_in_presetcondition2558);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar259=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2560);  
							stream_WhiteChar.add(WhiteChar259);

							DAYS260=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2562);  
							stream_DAYS.add(DAYS260);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 273:7: -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:273:10: ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:273:97: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:4: 'crosses down threshold' WhiteChar threshold= constant
					{
					string_literal261=(Token)match(input,61,FOLLOW_61_in_presetcondition2608);  
					stream_61.add(string_literal261);

					WhiteChar262=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2610);  
					stream_WhiteChar.add(WhiteChar262);

					pushFollow(FOLLOW_constant_in_presetcondition2614);
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
					// 275:58: -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:61: ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:99: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:128: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:275:157: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt41=2;
					int LA41_0 = input.LA(1);
					if ( (LA41_0==WhiteChar) ) {
						int LA41_1 = input.LA(2);
						if ( (LA41_1==90) ) {
							alt41=1;
						}
					}
					switch (alt41) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar263=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2656);  
							stream_WhiteChar.add(WhiteChar263);

							string_literal264=(Token)match(input,90,FOLLOW_90_in_presetcondition2658);  
							stream_90.add(string_literal264);

							WhiteChar265=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2660);  
							stream_WhiteChar.add(WhiteChar265);

							pushFollow(FOLLOW_constant_in_presetcondition2664);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar266=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2666);  
							stream_WhiteChar.add(WhiteChar266);

							DAYS267=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2668);  
							stream_DAYS.add(DAYS267);

							WhiteChar268=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2679);  
							stream_WhiteChar.add(WhiteChar268);

							string_literal269=(Token)match(input,85,FOLLOW_85_in_presetcondition2681);  
							stream_85.add(string_literal269);

							WhiteChar270=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2683);  
							stream_WhiteChar.add(WhiteChar270);

							pushFollow(FOLLOW_constant_in_presetcondition2687);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar271=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2689);  
							stream_WhiteChar.add(WhiteChar271);

							DAYS272=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2691);  
							stream_DAYS.add(DAYS272);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 278:7: -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:10: ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:99: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:280:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherHighCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:280:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherHighCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:280:4: 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']'
					{
					string_literal273=(Token)match(input,80,FOLLOW_80_in_presetcondition2729);  
					stream_80.add(string_literal273);

					WhiteChar274=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2731);  
					stream_WhiteChar.add(WhiteChar274);

					pushFollow(FOLLOW_constant_in_presetcondition2735);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar275=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2737);  
					stream_WhiteChar.add(WhiteChar275);

					DAYS276=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2739);  
					stream_DAYS.add(DAYS276);

					WhiteChar277=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2746);  
					stream_WhiteChar.add(WhiteChar277);

					string_literal278=(Token)match(input,85,FOLLOW_85_in_presetcondition2748);  
					stream_85.add(string_literal278);

					WhiteChar279=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2750);  
					stream_WhiteChar.add(WhiteChar279);

					pushFollow(FOLLOW_constant_in_presetcondition2754);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar280=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2756);  
					stream_WhiteChar.add(WhiteChar280);

					DAYS281=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2758);  
					stream_DAYS.add(DAYS281);

					WhiteChar282=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2765);  
					stream_WhiteChar.add(WhiteChar282);

					string_literal283=(Token)match(input,70,FOLLOW_70_in_presetcondition2767);  
					stream_70.add(string_literal283);

					WhiteChar284=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2769);  
					stream_WhiteChar.add(WhiteChar284);

					pushFollow(FOLLOW_constant_in_presetcondition2773);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar285=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2775);  
					stream_WhiteChar.add(WhiteChar285);

					DAYS286=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2777);  
					stream_DAYS.add(DAYS286);

					WhiteChar287=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2784);  
					stream_WhiteChar.add(WhiteChar287);

					string_literal288=(Token)match(input,89,FOLLOW_89_in_presetcondition2786);  
					stream_89.add(string_literal288);

					WhiteChar289=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2788);  
					stream_WhiteChar.add(WhiteChar289);

					pushFollow(FOLLOW_constant_in_presetcondition2792);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar290=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2794);  
					stream_WhiteChar.add(WhiteChar290);

					DAYS291=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2796);  
					stream_DAYS.add(DAYS291);

					WhiteChar292=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2803);  
					stream_WhiteChar.add(WhiteChar292);

					string_literal293=(Token)match(input,91,FOLLOW_91_in_presetcondition2805);  
					stream_91.add(string_literal293);

					WhiteChar294=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2807);  
					stream_WhiteChar.add(WhiteChar294);

					char_literal295=(Token)match(input,55,FOLLOW_55_in_presetcondition2809);  
					stream_55.add(char_literal295);

					pushFollow(FOLLOW_constant_in_presetcondition2813);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal296=(Token)match(input,53,FOLLOW_53_in_presetcondition2815);  
					stream_53.add(char_literal296);

					pushFollow(FOLLOW_constant_in_presetcondition2819);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal297=(Token)match(input,56,FOLLOW_56_in_presetcondition2821);  
					stream_56.add(char_literal297);

					WhiteChar298=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2823);  
					stream_WhiteChar.add(WhiteChar298);

					string_literal299=(Token)match(input,65,FOLLOW_65_in_presetcondition2825);  
					stream_65.add(string_literal299);

					WhiteChar300=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2827);  
					stream_WhiteChar.add(WhiteChar300);

					char_literal301=(Token)match(input,55,FOLLOW_55_in_presetcondition2829);  
					stream_55.add(char_literal301);

					pushFollow(FOLLOW_constant_in_presetcondition2833);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal302=(Token)match(input,53,FOLLOW_53_in_presetcondition2835);  
					stream_53.add(char_literal302);

					pushFollow(FOLLOW_constant_in_presetcondition2839);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal303=(Token)match(input,56,FOLLOW_56_in_presetcondition2841);  
					stream_56.add(char_literal303);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 285:4: -> ^( HigherHighCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:285:7: ^( HigherHighCondition )
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
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					}
					break;
				case 8 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherLowCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherLowCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:4: 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']'
					{
					string_literal304=(Token)match(input,81,FOLLOW_81_in_presetcondition2877);  
					stream_81.add(string_literal304);

					WhiteChar305=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2879);  
					stream_WhiteChar.add(WhiteChar305);

					pushFollow(FOLLOW_constant_in_presetcondition2883);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar306=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2885);  
					stream_WhiteChar.add(WhiteChar306);

					DAYS307=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2887);  
					stream_DAYS.add(DAYS307);

					WhiteChar308=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2894);  
					stream_WhiteChar.add(WhiteChar308);

					string_literal309=(Token)match(input,85,FOLLOW_85_in_presetcondition2896);  
					stream_85.add(string_literal309);

					WhiteChar310=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2898);  
					stream_WhiteChar.add(WhiteChar310);

					pushFollow(FOLLOW_constant_in_presetcondition2902);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar311=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2904);  
					stream_WhiteChar.add(WhiteChar311);

					DAYS312=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2906);  
					stream_DAYS.add(DAYS312);

					WhiteChar313=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2913);  
					stream_WhiteChar.add(WhiteChar313);

					string_literal314=(Token)match(input,70,FOLLOW_70_in_presetcondition2915);  
					stream_70.add(string_literal314);

					WhiteChar315=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2917);  
					stream_WhiteChar.add(WhiteChar315);

					pushFollow(FOLLOW_constant_in_presetcondition2921);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar316=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2923);  
					stream_WhiteChar.add(WhiteChar316);

					DAYS317=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2925);  
					stream_DAYS.add(DAYS317);

					WhiteChar318=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2932);  
					stream_WhiteChar.add(WhiteChar318);

					string_literal319=(Token)match(input,89,FOLLOW_89_in_presetcondition2934);  
					stream_89.add(string_literal319);

					WhiteChar320=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2936);  
					stream_WhiteChar.add(WhiteChar320);

					pushFollow(FOLLOW_constant_in_presetcondition2940);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar321=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2942);  
					stream_WhiteChar.add(WhiteChar321);

					DAYS322=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2944);  
					stream_DAYS.add(DAYS322);

					WhiteChar323=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2951);  
					stream_WhiteChar.add(WhiteChar323);

					string_literal324=(Token)match(input,91,FOLLOW_91_in_presetcondition2953);  
					stream_91.add(string_literal324);

					WhiteChar325=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2955);  
					stream_WhiteChar.add(WhiteChar325);

					char_literal326=(Token)match(input,55,FOLLOW_55_in_presetcondition2957);  
					stream_55.add(char_literal326);

					pushFollow(FOLLOW_constant_in_presetcondition2961);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal327=(Token)match(input,53,FOLLOW_53_in_presetcondition2963);  
					stream_53.add(char_literal327);

					pushFollow(FOLLOW_constant_in_presetcondition2967);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal328=(Token)match(input,56,FOLLOW_56_in_presetcondition2969);  
					stream_56.add(char_literal328);

					WhiteChar329=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2971);  
					stream_WhiteChar.add(WhiteChar329);

					string_literal330=(Token)match(input,65,FOLLOW_65_in_presetcondition2973);  
					stream_65.add(string_literal330);

					WhiteChar331=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2975);  
					stream_WhiteChar.add(WhiteChar331);

					char_literal332=(Token)match(input,55,FOLLOW_55_in_presetcondition2977);  
					stream_55.add(char_literal332);

					pushFollow(FOLLOW_constant_in_presetcondition2981);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal333=(Token)match(input,53,FOLLOW_53_in_presetcondition2983);  
					stream_53.add(char_literal333);

					pushFollow(FOLLOW_constant_in_presetcondition2987);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal334=(Token)match(input,56,FOLLOW_56_in_presetcondition2989);  
					stream_56.add(char_literal334);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 291:4: -> ^( HigherLowCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:7: ^( HigherLowCondition )
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
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					}
					break;
				case 9 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerHighCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerHighCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:4: 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']'
					{
					string_literal335=(Token)match(input,82,FOLLOW_82_in_presetcondition3025);  
					stream_82.add(string_literal335);

					WhiteChar336=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3027);  
					stream_WhiteChar.add(WhiteChar336);

					pushFollow(FOLLOW_constant_in_presetcondition3031);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar337=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3033);  
					stream_WhiteChar.add(WhiteChar337);

					DAYS338=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3035);  
					stream_DAYS.add(DAYS338);

					WhiteChar339=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3042);  
					stream_WhiteChar.add(WhiteChar339);

					string_literal340=(Token)match(input,85,FOLLOW_85_in_presetcondition3044);  
					stream_85.add(string_literal340);

					WhiteChar341=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3046);  
					stream_WhiteChar.add(WhiteChar341);

					pushFollow(FOLLOW_constant_in_presetcondition3050);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar342=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3052);  
					stream_WhiteChar.add(WhiteChar342);

					DAYS343=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3054);  
					stream_DAYS.add(DAYS343);

					WhiteChar344=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3061);  
					stream_WhiteChar.add(WhiteChar344);

					string_literal345=(Token)match(input,70,FOLLOW_70_in_presetcondition3063);  
					stream_70.add(string_literal345);

					WhiteChar346=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3065);  
					stream_WhiteChar.add(WhiteChar346);

					pushFollow(FOLLOW_constant_in_presetcondition3069);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar347=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3071);  
					stream_WhiteChar.add(WhiteChar347);

					DAYS348=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3073);  
					stream_DAYS.add(DAYS348);

					WhiteChar349=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3080);  
					stream_WhiteChar.add(WhiteChar349);

					string_literal350=(Token)match(input,89,FOLLOW_89_in_presetcondition3082);  
					stream_89.add(string_literal350);

					WhiteChar351=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3084);  
					stream_WhiteChar.add(WhiteChar351);

					pushFollow(FOLLOW_constant_in_presetcondition3088);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar352=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3090);  
					stream_WhiteChar.add(WhiteChar352);

					DAYS353=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3092);  
					stream_DAYS.add(DAYS353);

					WhiteChar354=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3099);  
					stream_WhiteChar.add(WhiteChar354);

					string_literal355=(Token)match(input,91,FOLLOW_91_in_presetcondition3101);  
					stream_91.add(string_literal355);

					WhiteChar356=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3103);  
					stream_WhiteChar.add(WhiteChar356);

					char_literal357=(Token)match(input,55,FOLLOW_55_in_presetcondition3105);  
					stream_55.add(char_literal357);

					pushFollow(FOLLOW_constant_in_presetcondition3109);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal358=(Token)match(input,53,FOLLOW_53_in_presetcondition3111);  
					stream_53.add(char_literal358);

					pushFollow(FOLLOW_constant_in_presetcondition3115);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal359=(Token)match(input,56,FOLLOW_56_in_presetcondition3117);  
					stream_56.add(char_literal359);

					WhiteChar360=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3119);  
					stream_WhiteChar.add(WhiteChar360);

					string_literal361=(Token)match(input,65,FOLLOW_65_in_presetcondition3121);  
					stream_65.add(string_literal361);

					WhiteChar362=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3123);  
					stream_WhiteChar.add(WhiteChar362);

					char_literal363=(Token)match(input,55,FOLLOW_55_in_presetcondition3125);  
					stream_55.add(char_literal363);

					pushFollow(FOLLOW_constant_in_presetcondition3129);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal364=(Token)match(input,53,FOLLOW_53_in_presetcondition3131);  
					stream_53.add(char_literal364);

					pushFollow(FOLLOW_constant_in_presetcondition3135);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal365=(Token)match(input,56,FOLLOW_56_in_presetcondition3137);  
					stream_56.add(char_literal365);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 297:4: -> ^( LowerHighCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:7: ^( LowerHighCondition )
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
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					}
					break;
				case 10 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerLowCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerLowCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:298:4: 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']'
					{
					string_literal366=(Token)match(input,83,FOLLOW_83_in_presetcondition3173);  
					stream_83.add(string_literal366);

					WhiteChar367=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3175);  
					stream_WhiteChar.add(WhiteChar367);

					pushFollow(FOLLOW_constant_in_presetcondition3179);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar368=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3181);  
					stream_WhiteChar.add(WhiteChar368);

					DAYS369=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3183);  
					stream_DAYS.add(DAYS369);

					WhiteChar370=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3190);  
					stream_WhiteChar.add(WhiteChar370);

					string_literal371=(Token)match(input,85,FOLLOW_85_in_presetcondition3192);  
					stream_85.add(string_literal371);

					WhiteChar372=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3194);  
					stream_WhiteChar.add(WhiteChar372);

					pushFollow(FOLLOW_constant_in_presetcondition3198);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar373=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3200);  
					stream_WhiteChar.add(WhiteChar373);

					DAYS374=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3202);  
					stream_DAYS.add(DAYS374);

					WhiteChar375=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3209);  
					stream_WhiteChar.add(WhiteChar375);

					string_literal376=(Token)match(input,70,FOLLOW_70_in_presetcondition3211);  
					stream_70.add(string_literal376);

					WhiteChar377=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3213);  
					stream_WhiteChar.add(WhiteChar377);

					pushFollow(FOLLOW_constant_in_presetcondition3217);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar378=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3219);  
					stream_WhiteChar.add(WhiteChar378);

					DAYS379=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3221);  
					stream_DAYS.add(DAYS379);

					WhiteChar380=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3228);  
					stream_WhiteChar.add(WhiteChar380);

					string_literal381=(Token)match(input,89,FOLLOW_89_in_presetcondition3230);  
					stream_89.add(string_literal381);

					WhiteChar382=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3232);  
					stream_WhiteChar.add(WhiteChar382);

					pushFollow(FOLLOW_constant_in_presetcondition3236);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar383=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3238);  
					stream_WhiteChar.add(WhiteChar383);

					DAYS384=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3240);  
					stream_DAYS.add(DAYS384);

					WhiteChar385=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3247);  
					stream_WhiteChar.add(WhiteChar385);

					string_literal386=(Token)match(input,91,FOLLOW_91_in_presetcondition3249);  
					stream_91.add(string_literal386);

					WhiteChar387=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3251);  
					stream_WhiteChar.add(WhiteChar387);

					char_literal388=(Token)match(input,55,FOLLOW_55_in_presetcondition3253);  
					stream_55.add(char_literal388);

					pushFollow(FOLLOW_constant_in_presetcondition3257);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal389=(Token)match(input,53,FOLLOW_53_in_presetcondition3259);  
					stream_53.add(char_literal389);

					pushFollow(FOLLOW_constant_in_presetcondition3263);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal390=(Token)match(input,56,FOLLOW_56_in_presetcondition3265);  
					stream_56.add(char_literal390);

					WhiteChar391=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3267);  
					stream_WhiteChar.add(WhiteChar391);

					string_literal392=(Token)match(input,65,FOLLOW_65_in_presetcondition3269);  
					stream_65.add(string_literal392);

					WhiteChar393=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3271);  
					stream_WhiteChar.add(WhiteChar393);

					char_literal394=(Token)match(input,55,FOLLOW_55_in_presetcondition3273);  
					stream_55.add(char_literal394);

					pushFollow(FOLLOW_constant_in_presetcondition3277);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal395=(Token)match(input,53,FOLLOW_53_in_presetcondition3279);  
					stream_53.add(char_literal395);

					pushFollow(FOLLOW_constant_in_presetcondition3283);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal396=(Token)match(input,56,FOLLOW_56_in_presetcondition3285);  
					stream_56.add(char_literal396);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 303:4: -> ^( LowerLowCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:303:7: ^( LowerLowCondition )
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
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					}
					break;
				case 11 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:305:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearFlatTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:305:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearFlatTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:305:4: 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal397=(Token)match(input,92,FOLLOW_92_in_presetcondition3322);  
					stream_92.add(string_literal397);

					WhiteChar398=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3330);  
					stream_WhiteChar.add(WhiteChar398);

					string_literal399=(Token)match(input,85,FOLLOW_85_in_presetcondition3332);  
					stream_85.add(string_literal399);

					WhiteChar400=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3334);  
					stream_WhiteChar.add(WhiteChar400);

					pushFollow(FOLLOW_constant_in_presetcondition3338);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar401=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3340);  
					stream_WhiteChar.add(WhiteChar401);

					DAYS402=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3342);  
					stream_DAYS.add(DAYS402);

					WhiteChar403=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3350);  
					stream_WhiteChar.add(WhiteChar403);

					string_literal404=(Token)match(input,70,FOLLOW_70_in_presetcondition3352);  
					stream_70.add(string_literal404);

					WhiteChar405=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3354);  
					stream_WhiteChar.add(WhiteChar405);

					pushFollow(FOLLOW_constant_in_presetcondition3358);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar406=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3360);  
					stream_WhiteChar.add(WhiteChar406);

					DAYS407=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3362);  
					stream_DAYS.add(DAYS407);

					WhiteChar408=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3370);  
					stream_WhiteChar.add(WhiteChar408);

					string_literal409=(Token)match(input,66,FOLLOW_66_in_presetcondition3372);  
					stream_66.add(string_literal409);

					WhiteChar410=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3374);  
					stream_WhiteChar.add(WhiteChar410);

					pushFollow(FOLLOW_constant_in_presetcondition3378);
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
					// 309:7: -> ^( LinearFlatTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:10: ^( LinearFlatTrendsCondition )
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



	public static final BitSet FOLLOW_bullish_condition_in_complete_expression363 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001800L});
	public static final BitSet FOLLOW_bearish_condition_in_complete_expression365 = new BitSet(new long[]{0x0200000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_also_display_in_complete_expression368 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_fixed_start_shift_in_complete_expression370 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_79_in_bullish_condition399 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition401 = new BitSet(new long[]{0x0000005080200000L});
	public static final BitSet FOLLOW_primary_expression_in_bullish_condition403 = new BitSet(new long[]{0x0010000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition405 = new BitSet(new long[]{0x0010000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bullish_condition408 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition410 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_76_in_bearish_condition426 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition428 = new BitSet(new long[]{0x0000005080200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_condition431 = new BitSet(new long[]{0x0010000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition433 = new BitSet(new long[]{0x0010000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bearish_condition436 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition438 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_bearish_not_bullish_in_bearish_condition448 = new BitSet(new long[]{0x0010000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition451 = new BitSet(new long[]{0x0010000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bearish_condition454 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition456 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_57_in_also_display473 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display475 = new BitSet(new long[]{0x0000005080200000L});
	public static final BitSet FOLLOW_primary_expression_in_also_display477 = new BitSet(new long[]{0x0010000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display479 = new BitSet(new long[]{0x0010000000000080L});
	public static final BitSet FOLLOW_COMMA_in_also_display482 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_86_in_fixed_start_shift517 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift519 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_fixed_start_shift523 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift525 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_fixed_start_shift527 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_COMMA_in_fixed_start_shift529 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_75_in_bearish_not_bullish559 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish568 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_bearish_not_bullish570 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish572 = new BitSet(new long[]{0x0000005080200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish574 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish601 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_OR_in_bearish_not_bullish603 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish605 = new BitSet(new long[]{0x0000005080200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish608 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_and_expression_in_primary_expression648 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_expression_in_and_expression661 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_lenient_in_and_expression665 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression668 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_and_expression670 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression672 = new BitSet(new long[]{0x0000005080200000L});
	public static final BitSet FOLLOW_or_expression_in_and_expression674 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_atom_in_or_expression702 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression705 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_OR_in_or_expression707 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression709 = new BitSet(new long[]{0x0000005080200000L});
	public static final BitSet FOLLOW_atom_in_or_expression711 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_booleanhistory_in_atom737 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom744 = new BitSet(new long[]{0x0010005080200000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom746 = new BitSet(new long[]{0x0010005080200000L});
	public static final BitSet FOLLOW_primary_expression_in_atom749 = new BitSet(new long[]{0x0010000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom751 = new BitSet(new long[]{0x0010000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom754 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_atom765 = new BitSet(new long[]{0x0010001000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom767 = new BitSet(new long[]{0x0010001000000000L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom770 = new BitSet(new long[]{0x0010005080200000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom772 = new BitSet(new long[]{0x0010005080200000L});
	public static final BitSet FOLLOW_primary_expression_in_atom775 = new BitSet(new long[]{0x0010000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom777 = new BitSet(new long[]{0x0010000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom780 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_booleanhistory802 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_booleanhistory804 = new BitSet(new long[]{0xF000000000000000L,0x00000000718F67B8L});
	public static final BitSet FOLLOW_presetcondition_in_booleanhistory808 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_opcmpcondition_in_booleanhistory817 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constantcmp_in_booleanhistory825 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_operand839 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Operation_in_operand866 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NumberToken_in_constant880 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_54_in_constant892 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_StringToken_in_stringconstant908 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_59_in_trendconstant923 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_58_in_trendconstant936 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_lenient953 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_LENIENT_in_lenient955 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_73_in_opcmpcondition992 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition994 = new BitSet(new long[]{0x0000004000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition998 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1024 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_opcmpcondition1026 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1028 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1032 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1034 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1036 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_77_in_opcmpcondition1058 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1060 = new BitSet(new long[]{0x0000004000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1064 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1092 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_opcmpcondition1094 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1096 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1100 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1102 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1104 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_67_in_opcmpcondition1126 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1128 = new BitSet(new long[]{0x0000004000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1132 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1159 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_opcmpcondition1161 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1163 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1167 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1169 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1171 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_60_in_opcmpcondition1194 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1196 = new BitSet(new long[]{0x0000004000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1198 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1235 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_90_in_opcmpcondition1237 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1239 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1243 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1245 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1247 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1260 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_opcmpcondition1262 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1264 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1268 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1270 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1272 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_62_in_opcmpcondition1308 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1310 = new BitSet(new long[]{0x0000004000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1312 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1348 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_90_in_opcmpcondition1350 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1352 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1356 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1358 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1360 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1371 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_opcmpcondition1373 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1375 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1379 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1381 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1383 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_93_in_opcmpcondition1418 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1420 = new BitSet(new long[]{0x0000004000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1424 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1432 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_opcmpcondition1434 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1436 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1440 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1442 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1444 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1452 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_opcmpcondition1454 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1456 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1460 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1462 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1464 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1472 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_64_in_opcmpcondition1474 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1476 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition1480 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1488 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_opcmpcondition1490 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1492 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1496 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_94_in_opcmpcondition1529 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1531 = new BitSet(new long[]{0x0000004000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1535 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1543 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_opcmpcondition1545 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1547 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1551 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1553 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1555 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1563 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_opcmpcondition1565 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1567 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1571 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1573 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1575 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1583 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_64_in_opcmpcondition1585 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1587 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition1591 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_constantcmp1629 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1631 = new BitSet(new long[]{0x0C00000000000000L});
	public static final BitSet FOLLOW_trendconstant_in_constantcmp1635 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1669 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_constantcmp1671 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1673 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1677 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1679 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1681 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1683 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_constantcmp1685 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1687 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1691 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1693 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1695 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_68_in_constantcmp1723 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1725 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1729 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1763 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_constantcmp1765 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1767 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1771 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1773 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1775 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1777 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_constantcmp1779 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1781 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1785 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1787 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1789 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_74_in_constantcmp1818 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1820 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1824 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1858 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_constantcmp1860 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1862 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1866 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1868 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1870 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1872 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_constantcmp1874 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1876 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1880 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1882 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1884 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_78_in_constantcmp1913 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1915 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1919 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1953 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_constantcmp1955 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1957 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1961 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1963 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1965 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1967 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_constantcmp1969 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1971 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1975 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1977 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1979 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_87_in_presetcondition2011 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2051 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_84_in_presetcondition2053 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2055 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2059 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2061 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2063 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_90_in_presetcondition2065 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2067 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2071 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2073 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2075 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_88_in_presetcondition2119 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2159 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_84_in_presetcondition2161 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2163 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2167 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2169 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2171 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_90_in_presetcondition2173 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2175 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2179 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2181 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2183 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_71_in_presetcondition2226 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2228 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2232 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2234 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2276 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_90_in_presetcondition2278 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2280 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2284 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2286 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2288 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2299 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_presetcondition2301 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2303 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2307 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2309 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2311 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_72_in_presetcondition2349 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2351 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2355 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2357 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2399 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_90_in_presetcondition2401 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2403 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2407 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2409 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2411 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2422 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_presetcondition2424 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2426 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2430 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2432 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2434 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_63_in_presetcondition2479 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2481 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2485 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2527 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_90_in_presetcondition2529 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2531 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2535 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2537 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2539 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2550 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_presetcondition2552 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2554 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2558 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2560 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2562 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_61_in_presetcondition2608 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2610 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2614 = new BitSet(new long[]{0x0010000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2656 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_90_in_presetcondition2658 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2660 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2664 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2666 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2668 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2679 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_presetcondition2681 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2683 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2687 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2689 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2691 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_80_in_presetcondition2729 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2731 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2735 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2737 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2739 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2746 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_presetcondition2748 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2750 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2754 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2756 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2758 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2765 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_presetcondition2767 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2769 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2773 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2775 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2777 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2784 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_89_in_presetcondition2786 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2788 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2792 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2794 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2796 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2803 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_presetcondition2805 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2807 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_55_in_presetcondition2809 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2813 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition2815 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2819 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_56_in_presetcondition2821 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2823 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition2825 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2827 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_55_in_presetcondition2829 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2833 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition2835 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2839 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_56_in_presetcondition2841 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_81_in_presetcondition2877 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2879 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2883 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2885 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2887 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2894 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_presetcondition2896 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2898 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2902 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2904 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2906 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2913 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_presetcondition2915 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2917 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2921 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2923 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2925 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2932 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_89_in_presetcondition2934 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2936 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2940 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2942 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2944 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2951 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_presetcondition2953 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2955 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_55_in_presetcondition2957 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2961 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition2963 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2967 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_56_in_presetcondition2969 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2971 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition2973 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2975 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_55_in_presetcondition2977 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2981 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition2983 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2987 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_56_in_presetcondition2989 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_82_in_presetcondition3025 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3027 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3031 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3033 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3035 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3042 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_presetcondition3044 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3046 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3050 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3052 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3054 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3061 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_presetcondition3063 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3065 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3069 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3071 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3073 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3080 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_89_in_presetcondition3082 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3084 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3088 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3090 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3092 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3099 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_presetcondition3101 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3103 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_55_in_presetcondition3105 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3109 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition3111 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3115 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_56_in_presetcondition3117 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3119 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition3121 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3123 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_55_in_presetcondition3125 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3129 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition3131 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3135 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_56_in_presetcondition3137 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_83_in_presetcondition3173 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3175 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3179 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3181 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3183 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3190 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_presetcondition3192 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3194 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3198 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3200 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3202 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3209 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_presetcondition3211 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3213 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3217 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3219 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3221 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3228 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_89_in_presetcondition3230 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3232 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3236 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3238 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3240 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3247 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_presetcondition3249 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3251 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_55_in_presetcondition3253 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3257 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition3259 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3263 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_56_in_presetcondition3265 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3267 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition3269 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3271 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_55_in_presetcondition3273 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3277 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition3279 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3283 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_56_in_presetcondition3285 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_92_in_presetcondition3322 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3330 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_presetcondition3332 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3334 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3338 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3340 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3342 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3350 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_presetcondition3352 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3354 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3358 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3360 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3362 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3370 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_presetcondition3372 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3374 = new BitSet(new long[]{0x0040000800000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3378 = new BitSet(new long[]{0x0000000000000002L});
}
