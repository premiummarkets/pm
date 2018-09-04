// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2018-09-04 19:43:12
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
		"LENIENT", "LINE_COMMENT", "LowerHighCondition", "LowerLowCondition", 
		"NOT", "NotDoubleMapCondition", "NullCondition", "Number", "NumberToken", 
		"OPENPARENTEHSIS", "OR", "Operation", "OperationOutput", "OrDoubleMapCondition", 
		"PERCENT", "ReverseCondition", "StockOperation", "String", "StringOperation", 
		"StringToken", "SupConstantCondition", "SupDoubleMapCondition", "Tcheat", 
		"UpRatioCondition", "WS", "WhiteChar", "','", "'NaN'", "'['", "']'", "'also display'", 
		"'bearish'", "'bullish'", "'crosses down historical'", "'crosses down threshold'", 
		"'crosses up historical'", "'crosses up threshold'", "'ending within'", 
		"'equals historical'", "'equals threshold'", "'equals trend'", "'for'", 
		"'goes down more than'", "'goes up more than'", "'is above historical'", 
		"'is above threshold'", "'is bearish if not bullish'", "'is bearish when'", 
		"'is below historical'", "'is below threshold'", "'is bullish when'", 
		"'makes a higher high spanning'", "'makes a higher low spanning'", "'makes a lower high spanning'", 
		"'makes a lower low spanning'", "'more than'", "'over'", "'override start shift with'", 
		"'reverses down'", "'reverses up'", "'smoothed'", "'spanning'", "'starting within'"
	};
	public static final int EOF=-1;
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
	public static final int LowerHighCondition=26;
	public static final int LowerLowCondition=27;
	public static final int NOT=28;
	public static final int NotDoubleMapCondition=29;
	public static final int NullCondition=30;
	public static final int Number=31;
	public static final int NumberToken=32;
	public static final int OPENPARENTEHSIS=33;
	public static final int OR=34;
	public static final int Operation=35;
	public static final int OperationOutput=36;
	public static final int OrDoubleMapCondition=37;
	public static final int PERCENT=38;
	public static final int ReverseCondition=39;
	public static final int StockOperation=40;
	public static final int String=41;
	public static final int StringOperation=42;
	public static final int StringToken=43;
	public static final int SupConstantCondition=44;
	public static final int SupDoubleMapCondition=45;
	public static final int Tcheat=46;
	public static final int UpRatioCondition=47;
	public static final int WS=48;
	public static final int WhiteChar=49;

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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:145:1: complete_expression : bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:145:21: (bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:147:4: bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift
			{
			pushFollow(FOLLOW_bullish_condition_in_complete_expression344);
			bcond=bullish_condition();
			state._fsp--;

			stream_bullish_condition.add(bcond.getTree());
			pushFollow(FOLLOW_bearish_condition_in_complete_expression346);
			bearish_condition1=bearish_condition((bcond!=null?((CommonTree)bcond.getTree()):null));
			state._fsp--;

			stream_bearish_condition.add(bearish_condition1.getTree());
			pushFollow(FOLLOW_also_display_in_complete_expression349);
			also_display2=also_display();
			state._fsp--;

			stream_also_display.add(also_display2.getTree());
			pushFollow(FOLLOW_fixed_start_shift_in_complete_expression351);
			fixed_start_shift3=fixed_start_shift();
			state._fsp--;

			stream_fixed_start_shift.add(fixed_start_shift3.getTree());
			// AST REWRITE
			// elements: bullish_condition, bearish_condition, fixed_start_shift, also_display
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 147:90: -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:147:93: ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:150:1: bullish_condition : 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression ;
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
		RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:150:19: ( 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:151:2: 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )*
			{
			string_literal4=(Token)match(input,74,FOLLOW_74_in_bullish_condition381);  
			stream_74.add(string_literal4);

			WhiteChar5=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition383);  
			stream_WhiteChar.add(WhiteChar5);

			pushFollow(FOLLOW_primary_expression_in_bullish_condition385);
			primary_expression6=primary_expression();
			state._fsp--;

			stream_primary_expression.add(primary_expression6.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:151:49: ( WhiteChar )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==WhiteChar) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:151:49: WhiteChar
					{
					WhiteChar7=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition387);  
					stream_WhiteChar.add(WhiteChar7);

					}
					break;

				default :
					break loop1;
				}
			}

			COMMA8=(Token)match(input,COMMA,FOLLOW_COMMA_in_bullish_condition390);  
			stream_COMMA.add(COMMA8);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:151:66: ( WhiteChar )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==WhiteChar) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:151:66: WhiteChar
					{
					WhiteChar9=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition392);  
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
			// 151:77: -> primary_expression
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:153:1: bearish_condition[CommonTree bcond] : ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* COMMA ( WhiteChar )* -> bearish_not_bullish );
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
		RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
		RewriteRuleSubtreeStream stream_bearish_not_bullish=new RewriteRuleSubtreeStream(adaptor,"rule bearish_not_bullish");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:153:37: ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* COMMA ( WhiteChar )* -> bearish_not_bullish )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==71) ) {
				alt7=1;
			}
			else if ( (LA7_0==70) ) {
				alt7=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:154:2: 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )*
					{
					string_literal10=(Token)match(input,71,FOLLOW_71_in_bearish_condition408);  
					stream_71.add(string_literal10);

					WhiteChar11=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition410);  
					stream_WhiteChar.add(WhiteChar11);

					pushFollow(FOLLOW_primary_expression_in_bearish_condition413);
					primary_expression12=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression12.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:154:50: ( WhiteChar )*
					loop3:
					while (true) {
						int alt3=2;
						int LA3_0 = input.LA(1);
						if ( (LA3_0==WhiteChar) ) {
							alt3=1;
						}

						switch (alt3) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:154:50: WhiteChar
							{
							WhiteChar13=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition415);  
							stream_WhiteChar.add(WhiteChar13);

							}
							break;

						default :
							break loop3;
						}
					}

					COMMA14=(Token)match(input,COMMA,FOLLOW_COMMA_in_bearish_condition418);  
					stream_COMMA.add(COMMA14);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:154:67: ( WhiteChar )*
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( (LA4_0==WhiteChar) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:154:67: WhiteChar
							{
							WhiteChar15=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition420);  
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
					// 154:78: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:2: bearish_not_bullish[$bcond] ( WhiteChar )* COMMA ( WhiteChar )*
					{
					pushFollow(FOLLOW_bearish_not_bullish_in_bearish_condition430);
					bearish_not_bullish16=bearish_not_bullish(bcond);
					state._fsp--;

					stream_bearish_not_bullish.add(bearish_not_bullish16.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:30: ( WhiteChar )*
					loop5:
					while (true) {
						int alt5=2;
						int LA5_0 = input.LA(1);
						if ( (LA5_0==WhiteChar) ) {
							alt5=1;
						}

						switch (alt5) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:30: WhiteChar
							{
							WhiteChar17=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition433);  
							stream_WhiteChar.add(WhiteChar17);

							}
							break;

						default :
							break loop5;
						}
					}

					COMMA18=(Token)match(input,COMMA,FOLLOW_COMMA_in_bearish_condition436);  
					stream_COMMA.add(COMMA18);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:47: ( WhiteChar )*
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( (LA6_0==WhiteChar) ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:47: WhiteChar
							{
							WhiteChar19=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition438);  
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
					// 155:58: -> bearish_not_bullish
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:157:2: also_display : ( 'also display' WhiteChar primary_expression ( WhiteChar )* COMMA -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition );
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
		RewriteRuleTokenStream stream_54=new RewriteRuleTokenStream(adaptor,"token 54");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:157:15: ( 'also display' WhiteChar primary_expression ( WhiteChar )* COMMA -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==54) ) {
				alt9=1;
			}
			else if ( (LA9_0==EOF||LA9_0==81) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:158:3: 'also display' WhiteChar primary_expression ( WhiteChar )* COMMA
					{
					string_literal20=(Token)match(input,54,FOLLOW_54_in_also_display455);  
					stream_54.add(string_literal20);

					WhiteChar21=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display457);  
					stream_WhiteChar.add(WhiteChar21);

					pushFollow(FOLLOW_primary_expression_in_also_display459);
					primary_expression22=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression22.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:158:47: ( WhiteChar )*
					loop8:
					while (true) {
						int alt8=2;
						int LA8_0 = input.LA(1);
						if ( (LA8_0==WhiteChar) ) {
							alt8=1;
						}

						switch (alt8) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:158:47: WhiteChar
							{
							WhiteChar23=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display461);  
							stream_WhiteChar.add(WhiteChar23);

							}
							break;

						default :
							break loop8;
						}
					}

					COMMA24=(Token)match(input,COMMA,FOLLOW_COMMA_in_also_display464);  
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
					// 158:64: -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:158:67: ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:158:91: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:3: 
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
					// 159:3: -> NullCondition
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:161:2: fixed_start_shift : ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS COMMA ->| -> ^( Number NumberToken[\"-1\"] ) );
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
		RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:161:20: ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS COMMA ->| -> ^( Number NumberToken[\"-1\"] ) )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==81) ) {
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:3: 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS COMMA
					{
					string_literal25=(Token)match(input,81,FOLLOW_81_in_fixed_start_shift499);  
					stream_81.add(string_literal25);

					WhiteChar26=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift501);  
					stream_WhiteChar.add(WhiteChar26);

					pushFollow(FOLLOW_constant_in_fixed_start_shift505);
					fixedStartShift=constant();
					state._fsp--;

					stream_constant.add(fixedStartShift.getTree());
					WhiteChar27=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift507);  
					stream_WhiteChar.add(WhiteChar27);

					DAYS28=(Token)match(input,DAYS,FOLLOW_DAYS_in_fixed_start_shift509);  
					stream_DAYS.add(DAYS28);

					COMMA29=(Token)match(input,COMMA,FOLLOW_COMMA_in_fixed_start_shift511);  
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
					// 162:87: ->
					{
						adaptor.addChild(root_0, (fixedStartShift!=null?((CommonTree)fixedStartShift.getTree()):null));
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
					// 163:3: -> ^( Number NumberToken[\"-1\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:6: ^( Number NumberToken[\"-1\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:1: bearish_not_bullish[CommonTree bcond] : 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) ) ;
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
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:39: ( 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:2: 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) )
			{
			string_literal30=(Token)match(input,70,FOLLOW_70_in_bearish_not_bullish541);  
			stream_70.add(string_literal30);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:168:3: ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:3: WhiteChar AND WhiteChar primary_expression
					{
					WhiteChar31=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish550);  
					stream_WhiteChar.add(WhiteChar31);

					AND32=(Token)match(input,AND,FOLLOW_AND_in_bearish_not_bullish552);  
					stream_AND.add(AND32);

					WhiteChar33=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish554);  
					stream_WhiteChar.add(WhiteChar33);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish556);
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
					// 169:46: -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:49: ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:73: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:108: ^( NotDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:3: WhiteChar OR WhiteChar primary_expression
					{
					WhiteChar35=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish583);  
					stream_WhiteChar.add(WhiteChar35);

					OR36=(Token)match(input,OR,FOLLOW_OR_in_bearish_not_bullish585);  
					stream_OR.add(OR36);

					WhiteChar37=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish587);  
					stream_WhiteChar.add(WhiteChar37);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish590);
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
					// 170:46: -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:49: ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrDoubleMapCondition, "OrDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:72: ^( NotDoubleMapCondition )
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
					// 171:3: -> ^( NotDoubleMapCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:171:6: ^( NotDoubleMapCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:175:1: primary_expression : and_expression ;
	public final ParameterizedIndicatorsParser.primary_expression_return primary_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.primary_expression_return retval = new ParameterizedIndicatorsParser.primary_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope and_expression39 =null;


		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:175:20: ( and_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:2: and_expression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_and_expression_in_primary_expression630);
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:1: and_expression : or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndDoubleMapCondition or_expression ( or_expression )* ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:16: ( or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndDoubleMapCondition or_expression ( or_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:3: or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )*
			{
			pushFollow(FOLLOW_or_expression_in_and_expression643);
			or_expression40=or_expression();
			state._fsp--;

			stream_or_expression.add(or_expression40.getTree());
			pushFollow(FOLLOW_lenient_in_and_expression647);
			lenientParam=lenient();
			state._fsp--;

			stream_lenient.add(lenientParam.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:38: ( WhiteChar AND WhiteChar or_expression )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:39: WhiteChar AND WhiteChar or_expression
					{
					WhiteChar41=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression650);  
					stream_WhiteChar.add(WhiteChar41);

					AND42=(Token)match(input,AND,FOLLOW_AND_in_and_expression652);  
					stream_AND.add(AND42);

					WhiteChar43=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression654);  
					stream_WhiteChar.add(WhiteChar43);

					pushFollow(FOLLOW_or_expression_in_and_expression656);
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
			// 180:79: -> ^( AndDoubleMapCondition or_expression ( or_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:82: ^( AndDoubleMapCondition or_expression ( or_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
				adaptor.addChild(root_1, (lenientParam!=null?((CommonTree)lenientParam.getTree()):null));
				adaptor.addChild(root_1, stream_or_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:142: ( or_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:182:1: or_expression : atom ( WhiteChar OR WhiteChar atom )* -> ^( OrDoubleMapCondition atom ( atom )* ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:182:15: ( atom ( WhiteChar OR WhiteChar atom )* -> ^( OrDoubleMapCondition atom ( atom )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:3: atom ( WhiteChar OR WhiteChar atom )*
			{
			pushFollow(FOLLOW_atom_in_or_expression684);
			atom45=atom();
			state._fsp--;

			stream_atom.add(atom45.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:8: ( WhiteChar OR WhiteChar atom )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:9: WhiteChar OR WhiteChar atom
					{
					WhiteChar46=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression687);  
					stream_WhiteChar.add(WhiteChar46);

					OR47=(Token)match(input,OR,FOLLOW_OR_in_or_expression689);  
					stream_OR.add(OR47);

					WhiteChar48=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression691);  
					stream_WhiteChar.add(WhiteChar48);

					pushFollow(FOLLOW_atom_in_or_expression693);
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
			// 183:39: -> ^( OrDoubleMapCondition atom ( atom )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:42: ^( OrDoubleMapCondition atom ( atom )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrDoubleMapCondition, "OrDoubleMapCondition"), root_1);
				adaptor.addChild(root_1, stream_atom.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:70: ( atom )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:1: atom : ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotDoubleMapCondition primary_expression ) );
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:185:6: ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotDoubleMapCondition primary_expression ) )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:3: booleanhistory
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_booleanhistory_in_atom719);
					booleanhistory50=booleanhistory();
					state._fsp--;

					adaptor.addChild(root_0, booleanhistory50.getTree());

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:3: '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					char_literal51=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom726);  
					stream_OPENPARENTEHSIS.add(char_literal51);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:7: ( WhiteChar )*
					loop14:
					while (true) {
						int alt14=2;
						int LA14_0 = input.LA(1);
						if ( (LA14_0==WhiteChar) ) {
							alt14=1;
						}

						switch (alt14) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:7: WhiteChar
							{
							WhiteChar52=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom728);  
							stream_WhiteChar.add(WhiteChar52);

							}
							break;

						default :
							break loop14;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom731);
					primary_expression53=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression53.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:37: ( WhiteChar )*
					loop15:
					while (true) {
						int alt15=2;
						int LA15_0 = input.LA(1);
						if ( (LA15_0==WhiteChar) ) {
							alt15=1;
						}

						switch (alt15) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:37: WhiteChar
							{
							WhiteChar54=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom733);  
							stream_WhiteChar.add(WhiteChar54);

							}
							break;

						default :
							break loop15;
						}
					}

					char_literal55=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom736);  
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
					// 187:52: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:3: 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					string_literal56=(Token)match(input,NOT,FOLLOW_NOT_in_atom747);  
					stream_NOT.add(string_literal56);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:9: ( WhiteChar )*
					loop16:
					while (true) {
						int alt16=2;
						int LA16_0 = input.LA(1);
						if ( (LA16_0==WhiteChar) ) {
							alt16=1;
						}

						switch (alt16) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:9: WhiteChar
							{
							WhiteChar57=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom749);  
							stream_WhiteChar.add(WhiteChar57);

							}
							break;

						default :
							break loop16;
						}
					}

					char_literal58=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom752);  
					stream_OPENPARENTEHSIS.add(char_literal58);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:24: ( WhiteChar )*
					loop17:
					while (true) {
						int alt17=2;
						int LA17_0 = input.LA(1);
						if ( (LA17_0==WhiteChar) ) {
							alt17=1;
						}

						switch (alt17) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:24: WhiteChar
							{
							WhiteChar59=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom754);  
							stream_WhiteChar.add(WhiteChar59);

							}
							break;

						default :
							break loop17;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom757);
					primary_expression60=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression60.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:54: ( WhiteChar )*
					loop18:
					while (true) {
						int alt18=2;
						int LA18_0 = input.LA(1);
						if ( (LA18_0==WhiteChar) ) {
							alt18=1;
						}

						switch (alt18) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:54: WhiteChar
							{
							WhiteChar61=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom759);  
							stream_WhiteChar.add(WhiteChar61);

							}
							break;

						default :
							break loop18;
						}
					}

					char_literal62=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom762);  
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
					// 188:69: -> ^( NotDoubleMapCondition primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:72: ^( NotDoubleMapCondition primary_expression )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:1: booleanhistory : firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:16: (firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:18: firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			{
			pushFollow(FOLLOW_operand_in_booleanhistory784);
			firstOp=operand();
			state._fsp--;

			stream_operand.add(firstOp.getTree());
			WhiteChar63=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_booleanhistory786);  
			stream_WhiteChar.add(WhiteChar63);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:44: ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			int alt20=3;
			switch ( input.LA(1) ) {
			case 58:
			case 60:
			case 66:
			case 67:
			case 75:
			case 76:
			case 77:
			case 78:
			case 82:
			case 83:
				{
				alt20=1;
				}
				break;
			case 57:
			case 59:
			case 62:
			case 68:
			case 72:
				{
				alt20=2;
				}
				break;
			case 63:
			case 64:
			case 69:
			case 73:
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:46: presetcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_presetcondition_in_booleanhistory790);
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
					// 191:77: -> presetcondition
					{
						adaptor.addChild(root_0, stream_presetcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:98: opcmpcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_opcmpcondition_in_booleanhistory799);
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
					// 191:128: -> opcmpcondition
					{
						adaptor.addChild(root_0, stream_opcmpcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:147: constantcmp[$firstOp.tree]
					{
					pushFollow(FOLLOW_constantcmp_in_booleanhistory807);
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
					// 191:174: -> constantcmp
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:1: operand : ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation );
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:9: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:11: HistoricalData
					{
					HistoricalData67=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_operand821);  
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
					// 192:26: -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:29: ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:46: ^( OperationOutput HistoricalData )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
						adaptor.addChild(root_2, stream_HistoricalData.nextNode());
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:80: ^( String StringToken[\"\\\"THIS\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:117: opName= Operation
					{
					opName=(Token)match(input,Operation,FOLLOW_Operation_in_operand848);  
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
					// 192:171: -> Operation
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:1: constant : ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) );
	public final ParameterizedIndicatorsParser.constant_return constant() throws RecognitionException {
		ParameterizedIndicatorsParser.constant_return retval = new ParameterizedIndicatorsParser.constant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NumberToken68=null;
		Token string_literal69=null;

		CommonTree NumberToken68_tree=null;
		CommonTree string_literal69_tree=null;
		RewriteRuleTokenStream stream_NumberToken=new RewriteRuleTokenStream(adaptor,"token NumberToken");
		RewriteRuleTokenStream stream_51=new RewriteRuleTokenStream(adaptor,"token 51");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:10: ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) )
			int alt22=2;
			int LA22_0 = input.LA(1);
			if ( (LA22_0==NumberToken) ) {
				alt22=1;
			}
			else if ( (LA22_0==51) ) {
				alt22=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}

			switch (alt22) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:13: NumberToken
					{
					NumberToken68=(Token)match(input,NumberToken,FOLLOW_NumberToken_in_constant862);  
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
					// 193:25: -> ^( Number NumberToken )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:28: ^( Number NumberToken )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:52: 'NaN'
					{
					string_literal69=(Token)match(input,51,FOLLOW_51_in_constant874);  
					stream_51.add(string_literal69);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 193:58: -> ^( Number NumberToken[\"NaN\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:61: ^( Number NumberToken[\"NaN\"] )
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


	public static class trendconstant_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "trendconstant"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:1: trendconstant : ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) );
	public final ParameterizedIndicatorsParser.trendconstant_return trendconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.trendconstant_return retval = new ParameterizedIndicatorsParser.trendconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal70=null;
		Token string_literal71=null;

		CommonTree string_literal70_tree=null;
		CommonTree string_literal71_tree=null;
		RewriteRuleTokenStream stream_55=new RewriteRuleTokenStream(adaptor,"token 55");
		RewriteRuleTokenStream stream_56=new RewriteRuleTokenStream(adaptor,"token 56");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:15: ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) )
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( (LA23_0==56) ) {
				alt23=1;
			}
			else if ( (LA23_0==55) ) {
				alt23=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}

			switch (alt23) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:17: 'bullish'
					{
					string_literal70=(Token)match(input,56,FOLLOW_56_in_trendconstant890);  
					stream_56.add(string_literal70);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 194:27: -> ^( String StringToken[\"\\\"bullish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:30: ^( String StringToken[\"\\\"bullish\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:69: 'bearish'
					{
					string_literal71=(Token)match(input,55,FOLLOW_55_in_trendconstant903);  
					stream_55.add(string_literal71);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 194:79: -> ^( String StringToken[\"\\\"bearish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:82: ^( String StringToken[\"\\\"bearish\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:1: lenient : ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ;
	public final ParameterizedIndicatorsParser.lenient_return lenient() throws RecognitionException {
		ParameterizedIndicatorsParser.lenient_return retval = new ParameterizedIndicatorsParser.lenient_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar72=null;
		Token LENIENT73=null;

		CommonTree WhiteChar72_tree=null;
		CommonTree LENIENT73_tree=null;
		RewriteRuleTokenStream stream_LENIENT=new RewriteRuleTokenStream(adaptor,"token LENIENT");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:9: ( ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:12: WhiteChar LENIENT
					{
					WhiteChar72=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_lenient920);  
					stream_WhiteChar.add(WhiteChar72);

					LENIENT73=(Token)match(input,LENIENT,FOLLOW_LENIENT_in_lenient922);  
					stream_LENIENT.add(LENIENT73);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 195:30: -> ^( String StringToken[\"\\\"TRUE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:33: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:69: 
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
					// 195:69: -> ^( String StringToken[\"\\\"FALSE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:72: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:1: opcmpcondition[CommonTree firstOp] : ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )? );
	public final ParameterizedIndicatorsParser.opcmpcondition_return opcmpcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.opcmpcondition_return retval = new ParameterizedIndicatorsParser.opcmpcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal74=null;
		Token WhiteChar75=null;
		Token WhiteChar76=null;
		Token string_literal77=null;
		Token WhiteChar78=null;
		Token WhiteChar79=null;
		Token DAYS80=null;
		Token string_literal81=null;
		Token WhiteChar82=null;
		Token WhiteChar83=null;
		Token string_literal84=null;
		Token WhiteChar85=null;
		Token WhiteChar86=null;
		Token DAYS87=null;
		Token string_literal88=null;
		Token WhiteChar89=null;
		Token WhiteChar90=null;
		Token string_literal91=null;
		Token WhiteChar92=null;
		Token WhiteChar93=null;
		Token DAYS94=null;
		Token string_literal95=null;
		Token WhiteChar96=null;
		Token WhiteChar98=null;
		Token string_literal99=null;
		Token WhiteChar100=null;
		Token WhiteChar101=null;
		Token DAYS102=null;
		Token WhiteChar103=null;
		Token string_literal104=null;
		Token WhiteChar105=null;
		Token WhiteChar106=null;
		Token DAYS107=null;
		Token string_literal108=null;
		Token WhiteChar109=null;
		Token WhiteChar111=null;
		Token string_literal112=null;
		Token WhiteChar113=null;
		Token WhiteChar114=null;
		Token DAYS115=null;
		Token WhiteChar116=null;
		Token string_literal117=null;
		Token WhiteChar118=null;
		Token WhiteChar119=null;
		Token DAYS120=null;
		ParserRuleReturnScope secondOp =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope spanningNbDays =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope operand97 =null;
		ParserRuleReturnScope operand110 =null;

		CommonTree string_literal74_tree=null;
		CommonTree WhiteChar75_tree=null;
		CommonTree WhiteChar76_tree=null;
		CommonTree string_literal77_tree=null;
		CommonTree WhiteChar78_tree=null;
		CommonTree WhiteChar79_tree=null;
		CommonTree DAYS80_tree=null;
		CommonTree string_literal81_tree=null;
		CommonTree WhiteChar82_tree=null;
		CommonTree WhiteChar83_tree=null;
		CommonTree string_literal84_tree=null;
		CommonTree WhiteChar85_tree=null;
		CommonTree WhiteChar86_tree=null;
		CommonTree DAYS87_tree=null;
		CommonTree string_literal88_tree=null;
		CommonTree WhiteChar89_tree=null;
		CommonTree WhiteChar90_tree=null;
		CommonTree string_literal91_tree=null;
		CommonTree WhiteChar92_tree=null;
		CommonTree WhiteChar93_tree=null;
		CommonTree DAYS94_tree=null;
		CommonTree string_literal95_tree=null;
		CommonTree WhiteChar96_tree=null;
		CommonTree WhiteChar98_tree=null;
		CommonTree string_literal99_tree=null;
		CommonTree WhiteChar100_tree=null;
		CommonTree WhiteChar101_tree=null;
		CommonTree DAYS102_tree=null;
		CommonTree WhiteChar103_tree=null;
		CommonTree string_literal104_tree=null;
		CommonTree WhiteChar105_tree=null;
		CommonTree WhiteChar106_tree=null;
		CommonTree DAYS107_tree=null;
		CommonTree string_literal108_tree=null;
		CommonTree WhiteChar109_tree=null;
		CommonTree WhiteChar111_tree=null;
		CommonTree string_literal112_tree=null;
		CommonTree WhiteChar113_tree=null;
		CommonTree WhiteChar114_tree=null;
		CommonTree DAYS115_tree=null;
		CommonTree WhiteChar116_tree=null;
		CommonTree string_literal117_tree=null;
		CommonTree WhiteChar118_tree=null;
		CommonTree WhiteChar119_tree=null;
		CommonTree DAYS120_tree=null;
		RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
		RewriteRuleTokenStream stream_57=new RewriteRuleTokenStream(adaptor,"token 57");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_59=new RewriteRuleTokenStream(adaptor,"token 59");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
		RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
		RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
		RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");
		RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:37: ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )? )
			int alt30=5;
			switch ( input.LA(1) ) {
			case 68:
				{
				alt30=1;
				}
				break;
			case 72:
				{
				alt30=2;
				}
				break;
			case 62:
				{
				alt30=3;
				}
				break;
			case 57:
				{
				alt30=4;
				}
				break;
			case 59:
				{
				alt30=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 30, 0, input);
				throw nvae;
			}
			switch (alt30) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:4: 'is above historical' WhiteChar secondOp= operand
					{
					string_literal74=(Token)match(input,68,FOLLOW_68_in_opcmpcondition958);  
					stream_68.add(string_literal74);

					WhiteChar75=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition960);  
					stream_WhiteChar.add(WhiteChar75);

					pushFollow(FOLLOW_operand_in_opcmpcondition964);
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
					// 199:53: -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:56: ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupDoubleMapCondition, "SupDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:80: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:6: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar76=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition990);  
							stream_WhiteChar.add(WhiteChar76);

							string_literal77=(Token)match(input,65,FOLLOW_65_in_opcmpcondition992);  
							stream_65.add(string_literal77);

							WhiteChar78=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition994);  
							stream_WhiteChar.add(WhiteChar78);

							pushFollow(FOLLOW_constant_in_opcmpcondition998);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar79=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1000);  
							stream_WhiteChar.add(WhiteChar79);

							DAYS80=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1002);  
							stream_DAYS.add(DAYS80);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 200:66: -> ^( SupDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:69: ^( SupDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:4: 'is below historical' WhiteChar secondOp= operand
					{
					string_literal81=(Token)match(input,72,FOLLOW_72_in_opcmpcondition1024);  
					stream_72.add(string_literal81);

					WhiteChar82=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1026);  
					stream_WhiteChar.add(WhiteChar82);

					pushFollow(FOLLOW_operand_in_opcmpcondition1030);
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
					// 201:53: -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:56: ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfDoubleMapCondition, "InfDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:80: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:5: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar83=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1058);  
							stream_WhiteChar.add(WhiteChar83);

							string_literal84=(Token)match(input,65,FOLLOW_65_in_opcmpcondition1060);  
							stream_65.add(string_literal84);

							WhiteChar85=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1062);  
							stream_WhiteChar.add(WhiteChar85);

							pushFollow(FOLLOW_constant_in_opcmpcondition1066);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar86=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1068);  
							stream_WhiteChar.add(WhiteChar86);

							DAYS87=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1070);  
							stream_DAYS.add(DAYS87);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 202:67: -> ^( InfDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:70: ^( InfDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:4: 'equals historical' WhiteChar secondOp= operand
					{
					string_literal88=(Token)match(input,62,FOLLOW_62_in_opcmpcondition1092);  
					stream_62.add(string_literal88);

					WhiteChar89=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1094);  
					stream_WhiteChar.add(WhiteChar89);

					pushFollow(FOLLOW_operand_in_opcmpcondition1098);
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
					// 203:51: -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:54: ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualDoubleMapCondition, "EqualDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:80: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:5: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )?
					int alt27=2;
					int LA27_0 = input.LA(1);
					if ( (LA27_0==WhiteChar) ) {
						int LA27_1 = input.LA(2);
						if ( (LA27_1==65) ) {
							alt27=1;
						}
					}
					switch (alt27) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar90=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1125);  
							stream_WhiteChar.add(WhiteChar90);

							string_literal91=(Token)match(input,65,FOLLOW_65_in_opcmpcondition1127);  
							stream_65.add(string_literal91);

							WhiteChar92=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1129);  
							stream_WhiteChar.add(WhiteChar92);

							pushFollow(FOLLOW_constant_in_opcmpcondition1133);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar93=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1135);  
							stream_WhiteChar.add(WhiteChar93);

							DAYS94=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1137);  
							stream_DAYS.add(DAYS94);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 204:67: -> ^( EqualDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:70: ^( EqualDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:3: ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:3: ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:4: 'crosses down historical' WhiteChar operand
					{
					string_literal95=(Token)match(input,57,FOLLOW_57_in_opcmpcondition1160);  
					stream_57.add(string_literal95);

					WhiteChar96=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1162);  
					stream_WhiteChar.add(WhiteChar96);

					pushFollow(FOLLOW_operand_in_opcmpcondition1164);
					operand97=operand();
					state._fsp--;

					stream_operand.add(operand97.getTree());
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
					// 206:48: -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:51: ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:81: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:206:110: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:9: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )?
					int alt28=2;
					int LA28_0 = input.LA(1);
					if ( (LA28_0==WhiteChar) ) {
						int LA28_1 = input.LA(2);
						if ( (LA28_1==85) ) {
							alt28=1;
						}
					}
					switch (alt28) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:11: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar98=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1201);  
							stream_WhiteChar.add(WhiteChar98);

							string_literal99=(Token)match(input,85,FOLLOW_85_in_opcmpcondition1203);  
							stream_85.add(string_literal99);

							WhiteChar100=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1205);  
							stream_WhiteChar.add(WhiteChar100);

							pushFollow(FOLLOW_constant_in_opcmpcondition1209);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar101=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1211);  
							stream_WhiteChar.add(WhiteChar101);

							DAYS102=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1213);  
							stream_DAYS.add(DAYS102);

							WhiteChar103=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1226);  
							stream_WhiteChar.add(WhiteChar103);

							string_literal104=(Token)match(input,80,FOLLOW_80_in_opcmpcondition1228);  
							stream_80.add(string_literal104);

							WhiteChar105=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1230);  
							stream_WhiteChar.add(WhiteChar105);

							pushFollow(FOLLOW_constant_in_opcmpcondition1234);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar106=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1236);  
							stream_WhiteChar.add(WhiteChar106);

							DAYS107=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1238);  
							stream_DAYS.add(DAYS107);

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
							// 209:11: -> ^( CrossDownDoubleMapCondition operand )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:209:14: ^( CrossDownDoubleMapCondition operand )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:3: ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:3: ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:4: 'crosses up historical' WhiteChar operand
					{
					string_literal108=(Token)match(input,59,FOLLOW_59_in_opcmpcondition1274);  
					stream_59.add(string_literal108);

					WhiteChar109=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1276);  
					stream_WhiteChar.add(WhiteChar109);

					pushFollow(FOLLOW_operand_in_opcmpcondition1278);
					operand110=operand();
					state._fsp--;

					stream_operand.add(operand110.getTree());
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
					// 211:46: -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:49: ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:77: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:106: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:8: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )?
					int alt29=2;
					int LA29_0 = input.LA(1);
					if ( (LA29_0==WhiteChar) ) {
						int LA29_1 = input.LA(2);
						if ( (LA29_1==85) ) {
							alt29=1;
						}
					}
					switch (alt29) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:10: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar111=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1314);  
							stream_WhiteChar.add(WhiteChar111);

							string_literal112=(Token)match(input,85,FOLLOW_85_in_opcmpcondition1316);  
							stream_85.add(string_literal112);

							WhiteChar113=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1318);  
							stream_WhiteChar.add(WhiteChar113);

							pushFollow(FOLLOW_constant_in_opcmpcondition1322);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar114=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1324);  
							stream_WhiteChar.add(WhiteChar114);

							DAYS115=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1326);  
							stream_DAYS.add(DAYS115);

							WhiteChar116=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1337);  
							stream_WhiteChar.add(WhiteChar116);

							string_literal117=(Token)match(input,80,FOLLOW_80_in_opcmpcondition1339);  
							stream_80.add(string_literal117);

							WhiteChar118=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1341);  
							stream_WhiteChar.add(WhiteChar118);

							pushFollow(FOLLOW_constant_in_opcmpcondition1345);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar119=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1347);  
							stream_WhiteChar.add(WhiteChar119);

							DAYS120=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1349);  
							stream_DAYS.add(DAYS120);

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
							// 214:10: -> ^( CrossUpDoubleMapCondition operand )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:13: ^( CrossUpDoubleMapCondition operand )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:1: constantcmp[CommonTree firstOp] : ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )? );
	public final ParameterizedIndicatorsParser.constantcmp_return constantcmp(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.constantcmp_return retval = new ParameterizedIndicatorsParser.constantcmp_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

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
		ParserRuleReturnScope trendSignal =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope threshold =null;

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
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
		RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
		RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
		RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
		RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_trendconstant=new RewriteRuleSubtreeStream(adaptor,"rule trendconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:34: ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )? )
			int alt35=4;
			switch ( input.LA(1) ) {
			case 64:
				{
				alt35=1;
				}
				break;
			case 63:
				{
				alt35=2;
				}
				break;
			case 69:
				{
				alt35=3;
				}
				break;
			case 73:
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:4: 'equals trend' WhiteChar trendSignal= trendconstant
					{
					string_literal121=(Token)match(input,64,FOLLOW_64_in_constantcmp1391);  
					stream_64.add(string_literal121);

					WhiteChar122=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1393);  
					stream_WhiteChar.add(WhiteChar122);

					pushFollow(FOLLOW_trendconstant_in_constantcmp1397);
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
					// 219:55: -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:58: ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualStringConstantCondition, "EqualStringConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_trendconstant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:103: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:130: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					int alt31=2;
					int LA31_0 = input.LA(1);
					if ( (LA31_0==WhiteChar) ) {
						int LA31_1 = input.LA(2);
						if ( (LA31_1==80) ) {
							alt31=1;
						}
					}
					switch (alt31) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar123=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1431);  
							stream_WhiteChar.add(WhiteChar123);

							string_literal124=(Token)match(input,80,FOLLOW_80_in_constantcmp1433);  
							stream_80.add(string_literal124);

							WhiteChar125=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1435);  
							stream_WhiteChar.add(WhiteChar125);

							pushFollow(FOLLOW_constant_in_constantcmp1439);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar126=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1441);  
							stream_WhiteChar.add(WhiteChar126);

							DAYS127=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1443);  
							stream_DAYS.add(DAYS127);

							WhiteChar128=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1445);  
							stream_WhiteChar.add(WhiteChar128);

							string_literal129=(Token)match(input,65,FOLLOW_65_in_constantcmp1447);  
							stream_65.add(string_literal129);

							WhiteChar130=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1449);  
							stream_WhiteChar.add(WhiteChar130);

							pushFollow(FOLLOW_constant_in_constantcmp1453);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar131=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1455);  
							stream_WhiteChar.add(WhiteChar131);

							DAYS132=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1457);  
							stream_DAYS.add(DAYS132);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 220:129: -> ^( EqualStringConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:132: ^( EqualStringConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:4: 'equals threshold' WhiteChar threshold= constant
					{
					string_literal133=(Token)match(input,63,FOLLOW_63_in_constantcmp1485);  
					stream_63.add(string_literal133);

					WhiteChar134=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1487);  
					stream_WhiteChar.add(WhiteChar134);

					pushFollow(FOLLOW_constant_in_constantcmp1491);
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
					// 222:52: -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:55: ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualConstantCondition, "EqualConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:116: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )?
					int alt32=2;
					int LA32_0 = input.LA(1);
					if ( (LA32_0==WhiteChar) ) {
						int LA32_1 = input.LA(2);
						if ( (LA32_1==80) ) {
							alt32=1;
						}
					}
					switch (alt32) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar135=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1525);  
							stream_WhiteChar.add(WhiteChar135);

							string_literal136=(Token)match(input,80,FOLLOW_80_in_constantcmp1527);  
							stream_80.add(string_literal136);

							WhiteChar137=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1529);  
							stream_WhiteChar.add(WhiteChar137);

							pushFollow(FOLLOW_constant_in_constantcmp1533);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar138=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1535);  
							stream_WhiteChar.add(WhiteChar138);

							DAYS139=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1537);  
							stream_DAYS.add(DAYS139);

							WhiteChar140=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1539);  
							stream_WhiteChar.add(WhiteChar140);

							string_literal141=(Token)match(input,65,FOLLOW_65_in_constantcmp1541);  
							stream_65.add(string_literal141);

							WhiteChar142=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1543);  
							stream_WhiteChar.add(WhiteChar142);

							pushFollow(FOLLOW_constant_in_constantcmp1547);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar143=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1549);  
							stream_WhiteChar.add(WhiteChar143);

							DAYS144=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1551);  
							stream_DAYS.add(DAYS144);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 223:129: -> ^( EqualConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:132: ^( EqualConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:4: 'is above threshold' WhiteChar threshold= constant
					{
					string_literal145=(Token)match(input,69,FOLLOW_69_in_constantcmp1580);  
					stream_69.add(string_literal145);

					WhiteChar146=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1582);  
					stream_WhiteChar.add(WhiteChar146);

					pushFollow(FOLLOW_constant_in_constantcmp1586);
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
					// 225:54: -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:57: ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupConstantCondition, "SupConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:116: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )?
					int alt33=2;
					int LA33_0 = input.LA(1);
					if ( (LA33_0==WhiteChar) ) {
						int LA33_1 = input.LA(2);
						if ( (LA33_1==80) ) {
							alt33=1;
						}
					}
					switch (alt33) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar147=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1620);  
							stream_WhiteChar.add(WhiteChar147);

							string_literal148=(Token)match(input,80,FOLLOW_80_in_constantcmp1622);  
							stream_80.add(string_literal148);

							WhiteChar149=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1624);  
							stream_WhiteChar.add(WhiteChar149);

							pushFollow(FOLLOW_constant_in_constantcmp1628);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar150=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1630);  
							stream_WhiteChar.add(WhiteChar150);

							DAYS151=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1632);  
							stream_DAYS.add(DAYS151);

							WhiteChar152=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1634);  
							stream_WhiteChar.add(WhiteChar152);

							string_literal153=(Token)match(input,65,FOLLOW_65_in_constantcmp1636);  
							stream_65.add(string_literal153);

							WhiteChar154=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1638);  
							stream_WhiteChar.add(WhiteChar154);

							pushFollow(FOLLOW_constant_in_constantcmp1642);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar155=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1644);  
							stream_WhiteChar.add(WhiteChar155);

							DAYS156=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1646);  
							stream_DAYS.add(DAYS156);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 226:129: -> ^( SupConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:132: ^( SupConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:4: 'is below threshold' WhiteChar threshold= constant
					{
					string_literal157=(Token)match(input,73,FOLLOW_73_in_constantcmp1675);  
					stream_73.add(string_literal157);

					WhiteChar158=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1677);  
					stream_WhiteChar.add(WhiteChar158);

					pushFollow(FOLLOW_constant_in_constantcmp1681);
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
					// 228:54: -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:57: ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfConstantCondition, "InfConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:116: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )?
					int alt34=2;
					int LA34_0 = input.LA(1);
					if ( (LA34_0==WhiteChar) ) {
						int LA34_1 = input.LA(2);
						if ( (LA34_1==80) ) {
							alt34=1;
						}
					}
					switch (alt34) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar159=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1715);  
							stream_WhiteChar.add(WhiteChar159);

							string_literal160=(Token)match(input,80,FOLLOW_80_in_constantcmp1717);  
							stream_80.add(string_literal160);

							WhiteChar161=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1719);  
							stream_WhiteChar.add(WhiteChar161);

							pushFollow(FOLLOW_constant_in_constantcmp1723);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar162=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1725);  
							stream_WhiteChar.add(WhiteChar162);

							DAYS163=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1727);  
							stream_DAYS.add(DAYS163);

							WhiteChar164=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1729);  
							stream_WhiteChar.add(WhiteChar164);

							string_literal165=(Token)match(input,65,FOLLOW_65_in_constantcmp1731);  
							stream_65.add(string_literal165);

							WhiteChar166=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1733);  
							stream_WhiteChar.add(WhiteChar166);

							pushFollow(FOLLOW_constant_in_constantcmp1737);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar167=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1739);  
							stream_WhiteChar.add(WhiteChar167);

							DAYS168=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1741);  
							stream_DAYS.add(DAYS168);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 229:129: -> ^( InfConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:132: ^( InfConstantCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:1: presetcondition[CommonTree firstOp] : ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherHighCondition ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherLowCondition ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerHighCondition ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerLowCondition ) ) );
	public final ParameterizedIndicatorsParser.presetcondition_return presetcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.presetcondition_return retval = new ParameterizedIndicatorsParser.presetcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal169=null;
		Token WhiteChar170=null;
		Token string_literal171=null;
		Token WhiteChar172=null;
		Token PERCENT173=null;
		Token WhiteChar174=null;
		Token string_literal175=null;
		Token WhiteChar176=null;
		Token WhiteChar177=null;
		Token DAYS178=null;
		Token string_literal179=null;
		Token WhiteChar180=null;
		Token string_literal181=null;
		Token WhiteChar182=null;
		Token PERCENT183=null;
		Token WhiteChar184=null;
		Token string_literal185=null;
		Token WhiteChar186=null;
		Token WhiteChar187=null;
		Token DAYS188=null;
		Token string_literal189=null;
		Token WhiteChar190=null;
		Token PERCENT191=null;
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
		Token PERCENT204=null;
		Token WhiteChar205=null;
		Token string_literal206=null;
		Token WhiteChar207=null;
		Token WhiteChar208=null;
		Token DAYS209=null;
		Token WhiteChar210=null;
		Token string_literal211=null;
		Token WhiteChar212=null;
		Token WhiteChar213=null;
		Token DAYS214=null;
		Token string_literal215=null;
		Token WhiteChar216=null;
		Token WhiteChar217=null;
		Token string_literal218=null;
		Token WhiteChar219=null;
		Token WhiteChar220=null;
		Token DAYS221=null;
		Token WhiteChar222=null;
		Token string_literal223=null;
		Token WhiteChar224=null;
		Token WhiteChar225=null;
		Token DAYS226=null;
		Token string_literal227=null;
		Token WhiteChar228=null;
		Token WhiteChar229=null;
		Token string_literal230=null;
		Token WhiteChar231=null;
		Token WhiteChar232=null;
		Token DAYS233=null;
		Token WhiteChar234=null;
		Token string_literal235=null;
		Token WhiteChar236=null;
		Token WhiteChar237=null;
		Token DAYS238=null;
		Token string_literal239=null;
		Token WhiteChar240=null;
		Token WhiteChar241=null;
		Token DAYS242=null;
		Token WhiteChar243=null;
		Token string_literal244=null;
		Token WhiteChar245=null;
		Token WhiteChar246=null;
		Token DAYS247=null;
		Token WhiteChar248=null;
		Token string_literal249=null;
		Token WhiteChar250=null;
		Token WhiteChar251=null;
		Token DAYS252=null;
		Token WhiteChar253=null;
		Token string_literal254=null;
		Token WhiteChar255=null;
		Token WhiteChar256=null;
		Token DAYS257=null;
		Token WhiteChar258=null;
		Token string_literal259=null;
		Token WhiteChar260=null;
		Token char_literal261=null;
		Token char_literal262=null;
		Token char_literal263=null;
		Token WhiteChar264=null;
		Token string_literal265=null;
		Token WhiteChar266=null;
		Token char_literal267=null;
		Token char_literal268=null;
		Token char_literal269=null;
		Token string_literal270=null;
		Token WhiteChar271=null;
		Token WhiteChar272=null;
		Token DAYS273=null;
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
		Token WhiteChar284=null;
		Token string_literal285=null;
		Token WhiteChar286=null;
		Token WhiteChar287=null;
		Token DAYS288=null;
		Token WhiteChar289=null;
		Token string_literal290=null;
		Token WhiteChar291=null;
		Token char_literal292=null;
		Token char_literal293=null;
		Token char_literal294=null;
		Token WhiteChar295=null;
		Token string_literal296=null;
		Token WhiteChar297=null;
		Token char_literal298=null;
		Token char_literal299=null;
		Token char_literal300=null;
		Token string_literal301=null;
		Token WhiteChar302=null;
		Token WhiteChar303=null;
		Token DAYS304=null;
		Token WhiteChar305=null;
		Token string_literal306=null;
		Token WhiteChar307=null;
		Token WhiteChar308=null;
		Token DAYS309=null;
		Token WhiteChar310=null;
		Token string_literal311=null;
		Token WhiteChar312=null;
		Token WhiteChar313=null;
		Token DAYS314=null;
		Token WhiteChar315=null;
		Token string_literal316=null;
		Token WhiteChar317=null;
		Token WhiteChar318=null;
		Token DAYS319=null;
		Token WhiteChar320=null;
		Token string_literal321=null;
		Token WhiteChar322=null;
		Token char_literal323=null;
		Token char_literal324=null;
		Token char_literal325=null;
		Token WhiteChar326=null;
		Token string_literal327=null;
		Token WhiteChar328=null;
		Token char_literal329=null;
		Token char_literal330=null;
		Token char_literal331=null;
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
		Token DAYS350=null;
		Token WhiteChar351=null;
		Token string_literal352=null;
		Token WhiteChar353=null;
		Token char_literal354=null;
		Token char_literal355=null;
		Token char_literal356=null;
		Token WhiteChar357=null;
		Token string_literal358=null;
		Token WhiteChar359=null;
		Token char_literal360=null;
		Token char_literal361=null;
		Token char_literal362=null;
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

		CommonTree string_literal169_tree=null;
		CommonTree WhiteChar170_tree=null;
		CommonTree string_literal171_tree=null;
		CommonTree WhiteChar172_tree=null;
		CommonTree PERCENT173_tree=null;
		CommonTree WhiteChar174_tree=null;
		CommonTree string_literal175_tree=null;
		CommonTree WhiteChar176_tree=null;
		CommonTree WhiteChar177_tree=null;
		CommonTree DAYS178_tree=null;
		CommonTree string_literal179_tree=null;
		CommonTree WhiteChar180_tree=null;
		CommonTree string_literal181_tree=null;
		CommonTree WhiteChar182_tree=null;
		CommonTree PERCENT183_tree=null;
		CommonTree WhiteChar184_tree=null;
		CommonTree string_literal185_tree=null;
		CommonTree WhiteChar186_tree=null;
		CommonTree WhiteChar187_tree=null;
		CommonTree DAYS188_tree=null;
		CommonTree string_literal189_tree=null;
		CommonTree WhiteChar190_tree=null;
		CommonTree PERCENT191_tree=null;
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
		CommonTree PERCENT204_tree=null;
		CommonTree WhiteChar205_tree=null;
		CommonTree string_literal206_tree=null;
		CommonTree WhiteChar207_tree=null;
		CommonTree WhiteChar208_tree=null;
		CommonTree DAYS209_tree=null;
		CommonTree WhiteChar210_tree=null;
		CommonTree string_literal211_tree=null;
		CommonTree WhiteChar212_tree=null;
		CommonTree WhiteChar213_tree=null;
		CommonTree DAYS214_tree=null;
		CommonTree string_literal215_tree=null;
		CommonTree WhiteChar216_tree=null;
		CommonTree WhiteChar217_tree=null;
		CommonTree string_literal218_tree=null;
		CommonTree WhiteChar219_tree=null;
		CommonTree WhiteChar220_tree=null;
		CommonTree DAYS221_tree=null;
		CommonTree WhiteChar222_tree=null;
		CommonTree string_literal223_tree=null;
		CommonTree WhiteChar224_tree=null;
		CommonTree WhiteChar225_tree=null;
		CommonTree DAYS226_tree=null;
		CommonTree string_literal227_tree=null;
		CommonTree WhiteChar228_tree=null;
		CommonTree WhiteChar229_tree=null;
		CommonTree string_literal230_tree=null;
		CommonTree WhiteChar231_tree=null;
		CommonTree WhiteChar232_tree=null;
		CommonTree DAYS233_tree=null;
		CommonTree WhiteChar234_tree=null;
		CommonTree string_literal235_tree=null;
		CommonTree WhiteChar236_tree=null;
		CommonTree WhiteChar237_tree=null;
		CommonTree DAYS238_tree=null;
		CommonTree string_literal239_tree=null;
		CommonTree WhiteChar240_tree=null;
		CommonTree WhiteChar241_tree=null;
		CommonTree DAYS242_tree=null;
		CommonTree WhiteChar243_tree=null;
		CommonTree string_literal244_tree=null;
		CommonTree WhiteChar245_tree=null;
		CommonTree WhiteChar246_tree=null;
		CommonTree DAYS247_tree=null;
		CommonTree WhiteChar248_tree=null;
		CommonTree string_literal249_tree=null;
		CommonTree WhiteChar250_tree=null;
		CommonTree WhiteChar251_tree=null;
		CommonTree DAYS252_tree=null;
		CommonTree WhiteChar253_tree=null;
		CommonTree string_literal254_tree=null;
		CommonTree WhiteChar255_tree=null;
		CommonTree WhiteChar256_tree=null;
		CommonTree DAYS257_tree=null;
		CommonTree WhiteChar258_tree=null;
		CommonTree string_literal259_tree=null;
		CommonTree WhiteChar260_tree=null;
		CommonTree char_literal261_tree=null;
		CommonTree char_literal262_tree=null;
		CommonTree char_literal263_tree=null;
		CommonTree WhiteChar264_tree=null;
		CommonTree string_literal265_tree=null;
		CommonTree WhiteChar266_tree=null;
		CommonTree char_literal267_tree=null;
		CommonTree char_literal268_tree=null;
		CommonTree char_literal269_tree=null;
		CommonTree string_literal270_tree=null;
		CommonTree WhiteChar271_tree=null;
		CommonTree WhiteChar272_tree=null;
		CommonTree DAYS273_tree=null;
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
		CommonTree WhiteChar284_tree=null;
		CommonTree string_literal285_tree=null;
		CommonTree WhiteChar286_tree=null;
		CommonTree WhiteChar287_tree=null;
		CommonTree DAYS288_tree=null;
		CommonTree WhiteChar289_tree=null;
		CommonTree string_literal290_tree=null;
		CommonTree WhiteChar291_tree=null;
		CommonTree char_literal292_tree=null;
		CommonTree char_literal293_tree=null;
		CommonTree char_literal294_tree=null;
		CommonTree WhiteChar295_tree=null;
		CommonTree string_literal296_tree=null;
		CommonTree WhiteChar297_tree=null;
		CommonTree char_literal298_tree=null;
		CommonTree char_literal299_tree=null;
		CommonTree char_literal300_tree=null;
		CommonTree string_literal301_tree=null;
		CommonTree WhiteChar302_tree=null;
		CommonTree WhiteChar303_tree=null;
		CommonTree DAYS304_tree=null;
		CommonTree WhiteChar305_tree=null;
		CommonTree string_literal306_tree=null;
		CommonTree WhiteChar307_tree=null;
		CommonTree WhiteChar308_tree=null;
		CommonTree DAYS309_tree=null;
		CommonTree WhiteChar310_tree=null;
		CommonTree string_literal311_tree=null;
		CommonTree WhiteChar312_tree=null;
		CommonTree WhiteChar313_tree=null;
		CommonTree DAYS314_tree=null;
		CommonTree WhiteChar315_tree=null;
		CommonTree string_literal316_tree=null;
		CommonTree WhiteChar317_tree=null;
		CommonTree WhiteChar318_tree=null;
		CommonTree DAYS319_tree=null;
		CommonTree WhiteChar320_tree=null;
		CommonTree string_literal321_tree=null;
		CommonTree WhiteChar322_tree=null;
		CommonTree char_literal323_tree=null;
		CommonTree char_literal324_tree=null;
		CommonTree char_literal325_tree=null;
		CommonTree WhiteChar326_tree=null;
		CommonTree string_literal327_tree=null;
		CommonTree WhiteChar328_tree=null;
		CommonTree char_literal329_tree=null;
		CommonTree char_literal330_tree=null;
		CommonTree char_literal331_tree=null;
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
		CommonTree DAYS350_tree=null;
		CommonTree WhiteChar351_tree=null;
		CommonTree string_literal352_tree=null;
		CommonTree WhiteChar353_tree=null;
		CommonTree char_literal354_tree=null;
		CommonTree char_literal355_tree=null;
		CommonTree char_literal356_tree=null;
		CommonTree WhiteChar357_tree=null;
		CommonTree string_literal358_tree=null;
		CommonTree WhiteChar359_tree=null;
		CommonTree char_literal360_tree=null;
		CommonTree char_literal361_tree=null;
		CommonTree char_literal362_tree=null;
		RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
		RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
		RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
		RewriteRuleTokenStream stream_58=new RewriteRuleTokenStream(adaptor,"token 58");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
		RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
		RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
		RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
		RewriteRuleTokenStream stream_50=new RewriteRuleTokenStream(adaptor,"token 50");
		RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
		RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
		RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");
		RewriteRuleTokenStream stream_52=new RewriteRuleTokenStream(adaptor,"token 52");
		RewriteRuleTokenStream stream_53=new RewriteRuleTokenStream(adaptor,"token 53");
		RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
		RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
		RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");
		RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:38: ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherHighCondition ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherLowCondition ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerHighCondition ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerLowCondition ) ) )
			int alt42=10;
			switch ( input.LA(1) ) {
			case 82:
				{
				alt42=1;
				}
				break;
			case 83:
				{
				alt42=2;
				}
				break;
			case 66:
				{
				alt42=3;
				}
				break;
			case 67:
				{
				alt42=4;
				}
				break;
			case 60:
				{
				alt42=5;
				}
				break;
			case 58:
				{
				alt42=6;
				}
				break;
			case 75:
				{
				alt42=7;
				}
				break;
			case 76:
				{
				alt42=8;
				}
				break;
			case 77:
				{
				alt42=9;
				}
				break;
			case 78:
				{
				alt42=10;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 42, 0, input);
				throw nvae;
			}
			switch (alt42) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:4: 'reverses down'
					{
					string_literal169=(Token)match(input,82,FOLLOW_82_in_presetcondition1773);  
					stream_82.add(string_literal169);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 234:20: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:23: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:42: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:70: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:99: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:7: ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					int alt36=2;
					int LA36_0 = input.LA(1);
					if ( (LA36_0==WhiteChar) ) {
						int LA36_1 = input.LA(2);
						if ( (LA36_1==79) ) {
							alt36=1;
						}
					}
					switch (alt36) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:9: WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar170=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1813);  
							stream_WhiteChar.add(WhiteChar170);

							string_literal171=(Token)match(input,79,FOLLOW_79_in_presetcondition1815);  
							stream_79.add(string_literal171);

							WhiteChar172=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1817);  
							stream_WhiteChar.add(WhiteChar172);

							pushFollow(FOLLOW_constant_in_presetcondition1821);
							percentdown=constant();
							state._fsp--;

							stream_constant.add(percentdown.getTree());
							PERCENT173=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition1823);  
							stream_PERCENT.add(PERCENT173);

							WhiteChar174=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1825);  
							stream_WhiteChar.add(WhiteChar174);

							string_literal175=(Token)match(input,85,FOLLOW_85_in_presetcondition1827);  
							stream_85.add(string_literal175);

							WhiteChar176=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1829);  
							stream_WhiteChar.add(WhiteChar176);

							pushFollow(FOLLOW_constant_in_presetcondition1833);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar177=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1835);  
							stream_WhiteChar.add(WhiteChar177);

							DAYS178=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1837);  
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
							// 236:7: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:10: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:29: ^( Number NumberToken[\"-1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:4: 'reverses up'
					{
					string_literal179=(Token)match(input,83,FOLLOW_83_in_presetcondition1881);  
					stream_83.add(string_literal179);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 238:18: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:21: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:40: ^( Number NumberToken[\"1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:67: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:96: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:7: ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					int alt37=2;
					int LA37_0 = input.LA(1);
					if ( (LA37_0==WhiteChar) ) {
						int LA37_1 = input.LA(2);
						if ( (LA37_1==79) ) {
							alt37=1;
						}
					}
					switch (alt37) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:9: WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar180=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1921);  
							stream_WhiteChar.add(WhiteChar180);

							string_literal181=(Token)match(input,79,FOLLOW_79_in_presetcondition1923);  
							stream_79.add(string_literal181);

							WhiteChar182=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1925);  
							stream_WhiteChar.add(WhiteChar182);

							pushFollow(FOLLOW_constant_in_presetcondition1929);
							percentup=constant();
							state._fsp--;

							stream_constant.add(percentup.getTree());
							PERCENT183=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition1931);  
							stream_PERCENT.add(PERCENT183);

							WhiteChar184=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1933);  
							stream_WhiteChar.add(WhiteChar184);

							string_literal185=(Token)match(input,85,FOLLOW_85_in_presetcondition1935);  
							stream_85.add(string_literal185);

							WhiteChar186=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1937);  
							stream_WhiteChar.add(WhiteChar186);

							pushFollow(FOLLOW_constant_in_presetcondition1941);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar187=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1943);  
							stream_WhiteChar.add(WhiteChar187);

							DAYS188=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1945);  
							stream_DAYS.add(DAYS188);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 240:7: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:10: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:29: ^( Number NumberToken[\"1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:4: 'goes down more than' WhiteChar percentdown= constant PERCENT
					{
					string_literal189=(Token)match(input,66,FOLLOW_66_in_presetcondition1988);  
					stream_66.add(string_literal189);

					WhiteChar190=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1990);  
					stream_WhiteChar.add(WhiteChar190);

					pushFollow(FOLLOW_constant_in_presetcondition1994);
					percentdown=constant();
					state._fsp--;

					stream_constant.add(percentdown.getTree());
					PERCENT191=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition1996);  
					stream_PERCENT.add(PERCENT191);

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
					// 242:65: -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:68: ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:98: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:127: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:156: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt38=2;
					int LA38_0 = input.LA(1);
					if ( (LA38_0==WhiteChar) ) {
						int LA38_1 = input.LA(2);
						if ( (LA38_1==85) ) {
							alt38=1;
						}
					}
					switch (alt38) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar192=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2038);  
							stream_WhiteChar.add(WhiteChar192);

							string_literal193=(Token)match(input,85,FOLLOW_85_in_presetcondition2040);  
							stream_85.add(string_literal193);

							WhiteChar194=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2042);  
							stream_WhiteChar.add(WhiteChar194);

							pushFollow(FOLLOW_constant_in_presetcondition2046);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar195=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2048);  
							stream_WhiteChar.add(WhiteChar195);

							DAYS196=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2050);  
							stream_DAYS.add(DAYS196);

							WhiteChar197=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2061);  
							stream_WhiteChar.add(WhiteChar197);

							string_literal198=(Token)match(input,65,FOLLOW_65_in_presetcondition2063);  
							stream_65.add(string_literal198);

							WhiteChar199=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2065);  
							stream_WhiteChar.add(WhiteChar199);

							pushFollow(FOLLOW_constant_in_presetcondition2069);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar200=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2071);  
							stream_WhiteChar.add(WhiteChar200);

							DAYS201=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2073);  
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
							// 245:7: -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:10: ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentdown!=null?((CommonTree)percentdown.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:75: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:4: 'goes up more than' WhiteChar percentup= constant PERCENT
					{
					string_literal202=(Token)match(input,67,FOLLOW_67_in_presetcondition2111);  
					stream_67.add(string_literal202);

					WhiteChar203=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2113);  
					stream_WhiteChar.add(WhiteChar203);

					pushFollow(FOLLOW_constant_in_presetcondition2117);
					percentup=constant();
					state._fsp--;

					stream_constant.add(percentup.getTree());
					PERCENT204=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2119);  
					stream_PERCENT.add(PERCENT204);

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
					// 246:61: -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:64: ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:92: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:121: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:150: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt39=2;
					int LA39_0 = input.LA(1);
					if ( (LA39_0==WhiteChar) ) {
						int LA39_1 = input.LA(2);
						if ( (LA39_1==85) ) {
							alt39=1;
						}
					}
					switch (alt39) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar205=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2161);  
							stream_WhiteChar.add(WhiteChar205);

							string_literal206=(Token)match(input,85,FOLLOW_85_in_presetcondition2163);  
							stream_85.add(string_literal206);

							WhiteChar207=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2165);  
							stream_WhiteChar.add(WhiteChar207);

							pushFollow(FOLLOW_constant_in_presetcondition2169);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar208=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2171);  
							stream_WhiteChar.add(WhiteChar208);

							DAYS209=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2173);  
							stream_DAYS.add(DAYS209);

							WhiteChar210=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2184);  
							stream_WhiteChar.add(WhiteChar210);

							string_literal211=(Token)match(input,65,FOLLOW_65_in_presetcondition2186);  
							stream_65.add(string_literal211);

							WhiteChar212=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2188);  
							stream_WhiteChar.add(WhiteChar212);

							pushFollow(FOLLOW_constant_in_presetcondition2192);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar213=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2194);  
							stream_WhiteChar.add(WhiteChar213);

							DAYS214=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2196);  
							stream_DAYS.add(DAYS214);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 249:7: -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:10: ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentup!=null?((CommonTree)percentup.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:71: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:4: 'crosses up threshold' WhiteChar threshold= constant
					{
					string_literal215=(Token)match(input,60,FOLLOW_60_in_presetcondition2241);  
					stream_60.add(string_literal215);

					WhiteChar216=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2243);  
					stream_WhiteChar.add(WhiteChar216);

					pushFollow(FOLLOW_constant_in_presetcondition2247);
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
					// 251:56: -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:59: ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:95: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:124: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:153: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt40=2;
					int LA40_0 = input.LA(1);
					if ( (LA40_0==WhiteChar) ) {
						int LA40_1 = input.LA(2);
						if ( (LA40_1==85) ) {
							alt40=1;
						}
					}
					switch (alt40) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar217=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2289);  
							stream_WhiteChar.add(WhiteChar217);

							string_literal218=(Token)match(input,85,FOLLOW_85_in_presetcondition2291);  
							stream_85.add(string_literal218);

							WhiteChar219=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2293);  
							stream_WhiteChar.add(WhiteChar219);

							pushFollow(FOLLOW_constant_in_presetcondition2297);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar220=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2299);  
							stream_WhiteChar.add(WhiteChar220);

							DAYS221=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2301);  
							stream_DAYS.add(DAYS221);

							WhiteChar222=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2312);  
							stream_WhiteChar.add(WhiteChar222);

							string_literal223=(Token)match(input,80,FOLLOW_80_in_presetcondition2314);  
							stream_80.add(string_literal223);

							WhiteChar224=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2316);  
							stream_WhiteChar.add(WhiteChar224);

							pushFollow(FOLLOW_constant_in_presetcondition2320);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar225=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2322);  
							stream_WhiteChar.add(WhiteChar225);

							DAYS226=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2324);  
							stream_DAYS.add(DAYS226);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 254:7: -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:10: ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:97: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:4: 'crosses down threshold' WhiteChar threshold= constant
					{
					string_literal227=(Token)match(input,58,FOLLOW_58_in_presetcondition2370);  
					stream_58.add(string_literal227);

					WhiteChar228=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2372);  
					stream_WhiteChar.add(WhiteChar228);

					pushFollow(FOLLOW_constant_in_presetcondition2376);
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
					// 256:58: -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:61: ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:99: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:128: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:157: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt41=2;
					int LA41_0 = input.LA(1);
					if ( (LA41_0==WhiteChar) ) {
						int LA41_1 = input.LA(2);
						if ( (LA41_1==85) ) {
							alt41=1;
						}
					}
					switch (alt41) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar229=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2418);  
							stream_WhiteChar.add(WhiteChar229);

							string_literal230=(Token)match(input,85,FOLLOW_85_in_presetcondition2420);  
							stream_85.add(string_literal230);

							WhiteChar231=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2422);  
							stream_WhiteChar.add(WhiteChar231);

							pushFollow(FOLLOW_constant_in_presetcondition2426);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar232=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2428);  
							stream_WhiteChar.add(WhiteChar232);

							DAYS233=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2430);  
							stream_DAYS.add(DAYS233);

							WhiteChar234=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2441);  
							stream_WhiteChar.add(WhiteChar234);

							string_literal235=(Token)match(input,80,FOLLOW_80_in_presetcondition2443);  
							stream_80.add(string_literal235);

							WhiteChar236=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2445);  
							stream_WhiteChar.add(WhiteChar236);

							pushFollow(FOLLOW_constant_in_presetcondition2449);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar237=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2451);  
							stream_WhiteChar.add(WhiteChar237);

							DAYS238=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2453);  
							stream_DAYS.add(DAYS238);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 259:7: -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:259:10: ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:259:99: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherHighCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherHighCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:4: 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']'
					{
					string_literal239=(Token)match(input,75,FOLLOW_75_in_presetcondition2491);  
					stream_75.add(string_literal239);

					WhiteChar240=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2493);  
					stream_WhiteChar.add(WhiteChar240);

					pushFollow(FOLLOW_constant_in_presetcondition2497);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar241=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2499);  
					stream_WhiteChar.add(WhiteChar241);

					DAYS242=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2501);  
					stream_DAYS.add(DAYS242);

					WhiteChar243=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2508);  
					stream_WhiteChar.add(WhiteChar243);

					string_literal244=(Token)match(input,80,FOLLOW_80_in_presetcondition2510);  
					stream_80.add(string_literal244);

					WhiteChar245=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2512);  
					stream_WhiteChar.add(WhiteChar245);

					pushFollow(FOLLOW_constant_in_presetcondition2516);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar246=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2518);  
					stream_WhiteChar.add(WhiteChar246);

					DAYS247=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2520);  
					stream_DAYS.add(DAYS247);

					WhiteChar248=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2527);  
					stream_WhiteChar.add(WhiteChar248);

					string_literal249=(Token)match(input,65,FOLLOW_65_in_presetcondition2529);  
					stream_65.add(string_literal249);

					WhiteChar250=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2531);  
					stream_WhiteChar.add(WhiteChar250);

					pushFollow(FOLLOW_constant_in_presetcondition2535);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar251=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2537);  
					stream_WhiteChar.add(WhiteChar251);

					DAYS252=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2539);  
					stream_DAYS.add(DAYS252);

					WhiteChar253=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2546);  
					stream_WhiteChar.add(WhiteChar253);

					string_literal254=(Token)match(input,84,FOLLOW_84_in_presetcondition2548);  
					stream_84.add(string_literal254);

					WhiteChar255=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2550);  
					stream_WhiteChar.add(WhiteChar255);

					pushFollow(FOLLOW_constant_in_presetcondition2554);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar256=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2556);  
					stream_WhiteChar.add(WhiteChar256);

					DAYS257=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2558);  
					stream_DAYS.add(DAYS257);

					WhiteChar258=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2565);  
					stream_WhiteChar.add(WhiteChar258);

					string_literal259=(Token)match(input,86,FOLLOW_86_in_presetcondition2567);  
					stream_86.add(string_literal259);

					WhiteChar260=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2569);  
					stream_WhiteChar.add(WhiteChar260);

					char_literal261=(Token)match(input,52,FOLLOW_52_in_presetcondition2571);  
					stream_52.add(char_literal261);

					pushFollow(FOLLOW_constant_in_presetcondition2575);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal262=(Token)match(input,50,FOLLOW_50_in_presetcondition2577);  
					stream_50.add(char_literal262);

					pushFollow(FOLLOW_constant_in_presetcondition2581);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal263=(Token)match(input,53,FOLLOW_53_in_presetcondition2583);  
					stream_53.add(char_literal263);

					WhiteChar264=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2585);  
					stream_WhiteChar.add(WhiteChar264);

					string_literal265=(Token)match(input,61,FOLLOW_61_in_presetcondition2587);  
					stream_61.add(string_literal265);

					WhiteChar266=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2589);  
					stream_WhiteChar.add(WhiteChar266);

					char_literal267=(Token)match(input,52,FOLLOW_52_in_presetcondition2591);  
					stream_52.add(char_literal267);

					pushFollow(FOLLOW_constant_in_presetcondition2595);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal268=(Token)match(input,50,FOLLOW_50_in_presetcondition2597);  
					stream_50.add(char_literal268);

					pushFollow(FOLLOW_constant_in_presetcondition2601);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal269=(Token)match(input,53,FOLLOW_53_in_presetcondition2603);  
					stream_53.add(char_literal269);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 266:4: -> ^( HigherHighCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:7: ^( HigherHighCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherLowCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherLowCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:4: 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']'
					{
					string_literal270=(Token)match(input,76,FOLLOW_76_in_presetcondition2639);  
					stream_76.add(string_literal270);

					WhiteChar271=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2641);  
					stream_WhiteChar.add(WhiteChar271);

					pushFollow(FOLLOW_constant_in_presetcondition2645);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar272=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2647);  
					stream_WhiteChar.add(WhiteChar272);

					DAYS273=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2649);  
					stream_DAYS.add(DAYS273);

					WhiteChar274=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2656);  
					stream_WhiteChar.add(WhiteChar274);

					string_literal275=(Token)match(input,80,FOLLOW_80_in_presetcondition2658);  
					stream_80.add(string_literal275);

					WhiteChar276=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2660);  
					stream_WhiteChar.add(WhiteChar276);

					pushFollow(FOLLOW_constant_in_presetcondition2664);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar277=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2666);  
					stream_WhiteChar.add(WhiteChar277);

					DAYS278=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2668);  
					stream_DAYS.add(DAYS278);

					WhiteChar279=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2675);  
					stream_WhiteChar.add(WhiteChar279);

					string_literal280=(Token)match(input,65,FOLLOW_65_in_presetcondition2677);  
					stream_65.add(string_literal280);

					WhiteChar281=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2679);  
					stream_WhiteChar.add(WhiteChar281);

					pushFollow(FOLLOW_constant_in_presetcondition2683);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar282=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2685);  
					stream_WhiteChar.add(WhiteChar282);

					DAYS283=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2687);  
					stream_DAYS.add(DAYS283);

					WhiteChar284=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2694);  
					stream_WhiteChar.add(WhiteChar284);

					string_literal285=(Token)match(input,84,FOLLOW_84_in_presetcondition2696);  
					stream_84.add(string_literal285);

					WhiteChar286=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2698);  
					stream_WhiteChar.add(WhiteChar286);

					pushFollow(FOLLOW_constant_in_presetcondition2702);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar287=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2704);  
					stream_WhiteChar.add(WhiteChar287);

					DAYS288=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2706);  
					stream_DAYS.add(DAYS288);

					WhiteChar289=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2713);  
					stream_WhiteChar.add(WhiteChar289);

					string_literal290=(Token)match(input,86,FOLLOW_86_in_presetcondition2715);  
					stream_86.add(string_literal290);

					WhiteChar291=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2717);  
					stream_WhiteChar.add(WhiteChar291);

					char_literal292=(Token)match(input,52,FOLLOW_52_in_presetcondition2719);  
					stream_52.add(char_literal292);

					pushFollow(FOLLOW_constant_in_presetcondition2723);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal293=(Token)match(input,50,FOLLOW_50_in_presetcondition2725);  
					stream_50.add(char_literal293);

					pushFollow(FOLLOW_constant_in_presetcondition2729);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal294=(Token)match(input,53,FOLLOW_53_in_presetcondition2731);  
					stream_53.add(char_literal294);

					WhiteChar295=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2733);  
					stream_WhiteChar.add(WhiteChar295);

					string_literal296=(Token)match(input,61,FOLLOW_61_in_presetcondition2735);  
					stream_61.add(string_literal296);

					WhiteChar297=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2737);  
					stream_WhiteChar.add(WhiteChar297);

					char_literal298=(Token)match(input,52,FOLLOW_52_in_presetcondition2739);  
					stream_52.add(char_literal298);

					pushFollow(FOLLOW_constant_in_presetcondition2743);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal299=(Token)match(input,50,FOLLOW_50_in_presetcondition2745);  
					stream_50.add(char_literal299);

					pushFollow(FOLLOW_constant_in_presetcondition2749);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal300=(Token)match(input,53,FOLLOW_53_in_presetcondition2751);  
					stream_53.add(char_literal300);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 272:4: -> ^( HigherLowCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:272:7: ^( HigherLowCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:273:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerHighCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:273:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerHighCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:273:4: 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']'
					{
					string_literal301=(Token)match(input,77,FOLLOW_77_in_presetcondition2787);  
					stream_77.add(string_literal301);

					WhiteChar302=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2789);  
					stream_WhiteChar.add(WhiteChar302);

					pushFollow(FOLLOW_constant_in_presetcondition2793);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar303=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2795);  
					stream_WhiteChar.add(WhiteChar303);

					DAYS304=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2797);  
					stream_DAYS.add(DAYS304);

					WhiteChar305=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2804);  
					stream_WhiteChar.add(WhiteChar305);

					string_literal306=(Token)match(input,80,FOLLOW_80_in_presetcondition2806);  
					stream_80.add(string_literal306);

					WhiteChar307=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2808);  
					stream_WhiteChar.add(WhiteChar307);

					pushFollow(FOLLOW_constant_in_presetcondition2812);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar308=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2814);  
					stream_WhiteChar.add(WhiteChar308);

					DAYS309=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2816);  
					stream_DAYS.add(DAYS309);

					WhiteChar310=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2823);  
					stream_WhiteChar.add(WhiteChar310);

					string_literal311=(Token)match(input,65,FOLLOW_65_in_presetcondition2825);  
					stream_65.add(string_literal311);

					WhiteChar312=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2827);  
					stream_WhiteChar.add(WhiteChar312);

					pushFollow(FOLLOW_constant_in_presetcondition2831);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar313=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2833);  
					stream_WhiteChar.add(WhiteChar313);

					DAYS314=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2835);  
					stream_DAYS.add(DAYS314);

					WhiteChar315=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2842);  
					stream_WhiteChar.add(WhiteChar315);

					string_literal316=(Token)match(input,84,FOLLOW_84_in_presetcondition2844);  
					stream_84.add(string_literal316);

					WhiteChar317=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2846);  
					stream_WhiteChar.add(WhiteChar317);

					pushFollow(FOLLOW_constant_in_presetcondition2850);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar318=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2852);  
					stream_WhiteChar.add(WhiteChar318);

					DAYS319=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2854);  
					stream_DAYS.add(DAYS319);

					WhiteChar320=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2861);  
					stream_WhiteChar.add(WhiteChar320);

					string_literal321=(Token)match(input,86,FOLLOW_86_in_presetcondition2863);  
					stream_86.add(string_literal321);

					WhiteChar322=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2865);  
					stream_WhiteChar.add(WhiteChar322);

					char_literal323=(Token)match(input,52,FOLLOW_52_in_presetcondition2867);  
					stream_52.add(char_literal323);

					pushFollow(FOLLOW_constant_in_presetcondition2871);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal324=(Token)match(input,50,FOLLOW_50_in_presetcondition2873);  
					stream_50.add(char_literal324);

					pushFollow(FOLLOW_constant_in_presetcondition2877);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal325=(Token)match(input,53,FOLLOW_53_in_presetcondition2879);  
					stream_53.add(char_literal325);

					WhiteChar326=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2881);  
					stream_WhiteChar.add(WhiteChar326);

					string_literal327=(Token)match(input,61,FOLLOW_61_in_presetcondition2883);  
					stream_61.add(string_literal327);

					WhiteChar328=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2885);  
					stream_WhiteChar.add(WhiteChar328);

					char_literal329=(Token)match(input,52,FOLLOW_52_in_presetcondition2887);  
					stream_52.add(char_literal329);

					pushFollow(FOLLOW_constant_in_presetcondition2891);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal330=(Token)match(input,50,FOLLOW_50_in_presetcondition2893);  
					stream_50.add(char_literal330);

					pushFollow(FOLLOW_constant_in_presetcondition2897);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal331=(Token)match(input,53,FOLLOW_53_in_presetcondition2899);  
					stream_53.add(char_literal331);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 278:4: -> ^( LowerHighCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:7: ^( LowerHighCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:279:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerLowCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:279:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerLowCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:279:4: 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']'
					{
					string_literal332=(Token)match(input,78,FOLLOW_78_in_presetcondition2935);  
					stream_78.add(string_literal332);

					WhiteChar333=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2937);  
					stream_WhiteChar.add(WhiteChar333);

					pushFollow(FOLLOW_constant_in_presetcondition2941);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar334=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2943);  
					stream_WhiteChar.add(WhiteChar334);

					DAYS335=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2945);  
					stream_DAYS.add(DAYS335);

					WhiteChar336=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2952);  
					stream_WhiteChar.add(WhiteChar336);

					string_literal337=(Token)match(input,80,FOLLOW_80_in_presetcondition2954);  
					stream_80.add(string_literal337);

					WhiteChar338=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2956);  
					stream_WhiteChar.add(WhiteChar338);

					pushFollow(FOLLOW_constant_in_presetcondition2960);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar339=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2962);  
					stream_WhiteChar.add(WhiteChar339);

					DAYS340=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2964);  
					stream_DAYS.add(DAYS340);

					WhiteChar341=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2971);  
					stream_WhiteChar.add(WhiteChar341);

					string_literal342=(Token)match(input,65,FOLLOW_65_in_presetcondition2973);  
					stream_65.add(string_literal342);

					WhiteChar343=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2975);  
					stream_WhiteChar.add(WhiteChar343);

					pushFollow(FOLLOW_constant_in_presetcondition2979);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar344=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2981);  
					stream_WhiteChar.add(WhiteChar344);

					DAYS345=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2983);  
					stream_DAYS.add(DAYS345);

					WhiteChar346=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2990);  
					stream_WhiteChar.add(WhiteChar346);

					string_literal347=(Token)match(input,84,FOLLOW_84_in_presetcondition2992);  
					stream_84.add(string_literal347);

					WhiteChar348=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2994);  
					stream_WhiteChar.add(WhiteChar348);

					pushFollow(FOLLOW_constant_in_presetcondition2998);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar349=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3000);  
					stream_WhiteChar.add(WhiteChar349);

					DAYS350=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3002);  
					stream_DAYS.add(DAYS350);

					WhiteChar351=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3009);  
					stream_WhiteChar.add(WhiteChar351);

					string_literal352=(Token)match(input,86,FOLLOW_86_in_presetcondition3011);  
					stream_86.add(string_literal352);

					WhiteChar353=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3013);  
					stream_WhiteChar.add(WhiteChar353);

					char_literal354=(Token)match(input,52,FOLLOW_52_in_presetcondition3015);  
					stream_52.add(char_literal354);

					pushFollow(FOLLOW_constant_in_presetcondition3019);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal355=(Token)match(input,50,FOLLOW_50_in_presetcondition3021);  
					stream_50.add(char_literal355);

					pushFollow(FOLLOW_constant_in_presetcondition3025);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal356=(Token)match(input,53,FOLLOW_53_in_presetcondition3027);  
					stream_53.add(char_literal356);

					WhiteChar357=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3029);  
					stream_WhiteChar.add(WhiteChar357);

					string_literal358=(Token)match(input,61,FOLLOW_61_in_presetcondition3031);  
					stream_61.add(string_literal358);

					WhiteChar359=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3033);  
					stream_WhiteChar.add(WhiteChar359);

					char_literal360=(Token)match(input,52,FOLLOW_52_in_presetcondition3035);  
					stream_52.add(char_literal360);

					pushFollow(FOLLOW_constant_in_presetcondition3039);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal361=(Token)match(input,50,FOLLOW_50_in_presetcondition3041);  
					stream_50.add(char_literal361);

					pushFollow(FOLLOW_constant_in_presetcondition3045);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal362=(Token)match(input,53,FOLLOW_53_in_presetcondition3047);  
					stream_53.add(char_literal362);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 284:4: -> ^( LowerLowCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:284:7: ^( LowerLowCondition )
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



	public static final BitSet FOLLOW_bullish_condition_in_complete_expression344 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
	public static final BitSet FOLLOW_bearish_condition_in_complete_expression346 = new BitSet(new long[]{0x0040000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_also_display_in_complete_expression349 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_fixed_start_shift_in_complete_expression351 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_74_in_bullish_condition381 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition383 = new BitSet(new long[]{0x0000000A10200000L});
	public static final BitSet FOLLOW_primary_expression_in_bullish_condition385 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition387 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bullish_condition390 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition392 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_71_in_bearish_condition408 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition410 = new BitSet(new long[]{0x0000000A10200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_condition413 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition415 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bearish_condition418 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition420 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_bearish_not_bullish_in_bearish_condition430 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition433 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bearish_condition436 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition438 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_54_in_also_display455 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display457 = new BitSet(new long[]{0x0000000A10200000L});
	public static final BitSet FOLLOW_primary_expression_in_also_display459 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display461 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_COMMA_in_also_display464 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_81_in_fixed_start_shift499 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift501 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_fixed_start_shift505 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift507 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_fixed_start_shift509 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_COMMA_in_fixed_start_shift511 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_70_in_bearish_not_bullish541 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish550 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_bearish_not_bullish552 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish554 = new BitSet(new long[]{0x0000000A10200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish556 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish583 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_OR_in_bearish_not_bullish585 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish587 = new BitSet(new long[]{0x0000000A10200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish590 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_and_expression_in_primary_expression630 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_expression_in_and_expression643 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_lenient_in_and_expression647 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression650 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_and_expression652 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression654 = new BitSet(new long[]{0x0000000A10200000L});
	public static final BitSet FOLLOW_or_expression_in_and_expression656 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_atom_in_or_expression684 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression687 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_OR_in_or_expression689 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression691 = new BitSet(new long[]{0x0000000A10200000L});
	public static final BitSet FOLLOW_atom_in_or_expression693 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_booleanhistory_in_atom719 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom726 = new BitSet(new long[]{0x0002000A10200000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom728 = new BitSet(new long[]{0x0002000A10200000L});
	public static final BitSet FOLLOW_primary_expression_in_atom731 = new BitSet(new long[]{0x0002000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom733 = new BitSet(new long[]{0x0002000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom736 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_atom747 = new BitSet(new long[]{0x0002000200000000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom749 = new BitSet(new long[]{0x0002000200000000L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom752 = new BitSet(new long[]{0x0002000A10200000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom754 = new BitSet(new long[]{0x0002000A10200000L});
	public static final BitSet FOLLOW_primary_expression_in_atom757 = new BitSet(new long[]{0x0002000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom759 = new BitSet(new long[]{0x0002000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom762 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_booleanhistory784 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_booleanhistory786 = new BitSet(new long[]{0xDE00000000000000L,0x00000000000C7B3DL});
	public static final BitSet FOLLOW_presetcondition_in_booleanhistory790 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_opcmpcondition_in_booleanhistory799 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constantcmp_in_booleanhistory807 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_operand821 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Operation_in_operand848 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NumberToken_in_constant862 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_51_in_constant874 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_56_in_trendconstant890 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_55_in_trendconstant903 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_lenient920 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_LENIENT_in_lenient922 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_68_in_opcmpcondition958 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition960 = new BitSet(new long[]{0x0000000800200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition964 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition990 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_opcmpcondition992 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition994 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition998 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1000 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1002 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_72_in_opcmpcondition1024 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1026 = new BitSet(new long[]{0x0000000800200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1030 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1058 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_opcmpcondition1060 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1062 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1066 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1068 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1070 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_62_in_opcmpcondition1092 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1094 = new BitSet(new long[]{0x0000000800200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1098 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1125 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_opcmpcondition1127 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1129 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1133 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1135 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1137 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_57_in_opcmpcondition1160 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1162 = new BitSet(new long[]{0x0000000800200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1164 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1201 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_opcmpcondition1203 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1205 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1209 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1211 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1213 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1226 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_opcmpcondition1228 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1230 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1234 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1236 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1238 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_59_in_opcmpcondition1274 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1276 = new BitSet(new long[]{0x0000000800200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1278 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1314 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_opcmpcondition1316 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1318 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1322 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1324 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1326 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1337 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_opcmpcondition1339 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1341 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1345 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1347 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1349 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_64_in_constantcmp1391 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1393 = new BitSet(new long[]{0x0180000000000000L});
	public static final BitSet FOLLOW_trendconstant_in_constantcmp1397 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1431 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_constantcmp1433 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1435 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1439 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1441 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1443 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1445 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_constantcmp1447 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1449 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1453 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1455 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1457 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_63_in_constantcmp1485 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1487 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1491 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1525 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_constantcmp1527 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1529 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1533 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1535 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1537 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1539 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_constantcmp1541 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1543 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1547 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1549 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1551 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_constantcmp1580 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1582 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1586 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1620 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_constantcmp1622 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1624 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1628 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1630 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1632 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1634 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_constantcmp1636 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1638 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1642 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1644 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1646 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_73_in_constantcmp1675 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1677 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1681 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1715 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_constantcmp1717 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1719 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1723 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1725 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1727 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1729 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_constantcmp1731 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1733 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1737 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1739 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1741 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_82_in_presetcondition1773 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1813 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition1815 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1817 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1821 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition1823 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1825 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_presetcondition1827 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1829 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1833 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1835 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1837 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_83_in_presetcondition1881 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1921 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition1923 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1925 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1929 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition1931 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1933 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_presetcondition1935 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1937 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1941 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1943 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1945 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_66_in_presetcondition1988 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1990 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1994 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition1996 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2038 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_presetcondition2040 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2042 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2046 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2048 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2050 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2061 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition2063 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2065 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2069 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2071 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2073 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_67_in_presetcondition2111 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2113 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2117 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2119 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2161 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_presetcondition2163 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2165 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2169 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2171 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2173 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2184 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition2186 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2188 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2192 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2194 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2196 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_60_in_presetcondition2241 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2243 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2247 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2289 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_presetcondition2291 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2293 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2297 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2299 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2301 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2312 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition2314 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2316 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2320 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2322 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2324 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_58_in_presetcondition2370 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2372 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2376 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2418 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_presetcondition2420 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2422 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2426 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2428 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2430 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2441 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition2443 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2445 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2449 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2451 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2453 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_75_in_presetcondition2491 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2493 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2497 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2499 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2501 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2508 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition2510 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2512 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2516 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2518 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2520 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2527 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition2529 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2531 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2535 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2537 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2539 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2546 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_84_in_presetcondition2548 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2550 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2554 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2556 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2558 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2565 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_86_in_presetcondition2567 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2569 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_52_in_presetcondition2571 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2575 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_50_in_presetcondition2577 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2581 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition2583 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2585 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_presetcondition2587 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2589 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_52_in_presetcondition2591 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2595 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_50_in_presetcondition2597 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2601 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition2603 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_76_in_presetcondition2639 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2641 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2645 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2647 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2649 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2656 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition2658 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2660 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2664 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2666 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2668 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2675 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition2677 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2679 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2683 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2685 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2687 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2694 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_84_in_presetcondition2696 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2698 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2702 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2704 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2706 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2713 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_86_in_presetcondition2715 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2717 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_52_in_presetcondition2719 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2723 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_50_in_presetcondition2725 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2729 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition2731 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2733 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_presetcondition2735 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2737 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_52_in_presetcondition2739 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2743 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_50_in_presetcondition2745 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2749 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition2751 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_77_in_presetcondition2787 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2789 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2793 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2795 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2797 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2804 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition2806 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2808 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2812 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2814 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2816 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2823 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition2825 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2827 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2831 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2833 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2835 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2842 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_84_in_presetcondition2844 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2846 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2850 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2852 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2854 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2861 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_86_in_presetcondition2863 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2865 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_52_in_presetcondition2867 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2871 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_50_in_presetcondition2873 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2877 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition2879 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2881 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_presetcondition2883 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2885 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_52_in_presetcondition2887 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2891 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_50_in_presetcondition2893 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2897 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition2899 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_78_in_presetcondition2935 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2937 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2941 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2943 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2945 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2952 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition2954 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2956 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2960 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2962 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2964 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2971 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_presetcondition2973 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2975 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2979 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2981 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2983 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2990 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_84_in_presetcondition2992 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2994 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2998 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3000 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3002 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3009 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_86_in_presetcondition3011 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3013 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_52_in_presetcondition3015 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3019 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_50_in_presetcondition3021 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3025 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition3027 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3029 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_presetcondition3031 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3033 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_52_in_presetcondition3035 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3039 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_50_in_presetcondition3041 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3045 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition3047 = new BitSet(new long[]{0x0000000000000002L});
}
