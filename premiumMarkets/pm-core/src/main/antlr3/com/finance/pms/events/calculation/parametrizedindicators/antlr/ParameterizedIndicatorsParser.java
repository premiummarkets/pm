// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2018-08-19 19:52:20
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
		"EqualStringConstantCondition", "EventInfoOpsCompoOperation", "HigherHighCondition", 
		"HigherLowCondition", "HistoricalData", "InfConstantCondition", "InfDoubleMapCondition", 
		"LENIENT", "LINE_COMMENT", "LowerHighCondition", "LowerLowCondition", 
		"NOT", "NotDoubleMapCondition", "NullCondition", "Number", "NumberToken", 
		"OPENPARENTEHSIS", "OR", "Operation", "OperationOutput", "OrDoubleMapCondition", 
		"PERCENT", "ReverseCondition", "StockOperation", "String", "StringOperation", 
		"StringToken", "SupConstantCondition", "SupDoubleMapCondition", "Tcheat", 
		"UpRatioCondition", "WS", "WhiteChar", "'also display'", "'bearish'", 
		"'bullish'", "'crosses down historical'", "'crosses down threshold'", 
		"'crosses up historical'", "'crosses up threshold'", "'equals historical'", 
		"'equals threshold'", "'equals trend'", "'for'", "'goes down more than'", 
		"'goes up more than'", "'is above historical'", "'is above threshold'", 
		"'is bearish if not bullish'", "'is bearish when'", "'is below historical'", 
		"'is below threshold'", "'is bullish when'", "'makes a higher high over'", 
		"'makes a higher low over'", "'makes a lower high over'", "'makes a lower low over'", 
		"'more than'", "'over'", "'override start shift with'", "'reverses down'", 
		"'reverses up'", "'smoothed'", "'spanning'"
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
			pushFollow(FOLLOW_bullish_condition_in_complete_expression341);
			bcond=bullish_condition();
			state._fsp--;

			stream_bullish_condition.add(bcond.getTree());
			pushFollow(FOLLOW_bearish_condition_in_complete_expression343);
			bearish_condition1=bearish_condition((bcond!=null?((CommonTree)bcond.getTree()):null));
			state._fsp--;

			stream_bearish_condition.add(bearish_condition1.getTree());
			pushFollow(FOLLOW_also_display_in_complete_expression346);
			also_display2=also_display();
			state._fsp--;

			stream_also_display.add(also_display2.getTree());
			pushFollow(FOLLOW_fixed_start_shift_in_complete_expression348);
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
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:150:19: ( 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:151:2: 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )*
			{
			string_literal4=(Token)match(input,69,FOLLOW_69_in_bullish_condition378);  
			stream_69.add(string_literal4);

			WhiteChar5=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition380);  
			stream_WhiteChar.add(WhiteChar5);

			pushFollow(FOLLOW_primary_expression_in_bullish_condition382);
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
					WhiteChar7=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition384);  
					stream_WhiteChar.add(WhiteChar7);

					}
					break;

				default :
					break loop1;
				}
			}

			COMMA8=(Token)match(input,COMMA,FOLLOW_COMMA_in_bullish_condition387);  
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
					WhiteChar9=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition389);  
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
		RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_bearish_not_bullish=new RewriteRuleSubtreeStream(adaptor,"rule bearish_not_bullish");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:153:37: ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* COMMA ( WhiteChar )* -> bearish_not_bullish )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==66) ) {
				alt7=1;
			}
			else if ( (LA7_0==65) ) {
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
					string_literal10=(Token)match(input,66,FOLLOW_66_in_bearish_condition405);  
					stream_66.add(string_literal10);

					WhiteChar11=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition407);  
					stream_WhiteChar.add(WhiteChar11);

					pushFollow(FOLLOW_primary_expression_in_bearish_condition410);
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
							WhiteChar13=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition412);  
							stream_WhiteChar.add(WhiteChar13);

							}
							break;

						default :
							break loop3;
						}
					}

					COMMA14=(Token)match(input,COMMA,FOLLOW_COMMA_in_bearish_condition415);  
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
							WhiteChar15=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition417);  
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
					pushFollow(FOLLOW_bearish_not_bullish_in_bearish_condition427);
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
							WhiteChar17=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition430);  
							stream_WhiteChar.add(WhiteChar17);

							}
							break;

						default :
							break loop5;
						}
					}

					COMMA18=(Token)match(input,COMMA,FOLLOW_COMMA_in_bearish_condition433);  
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
							WhiteChar19=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition435);  
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
		RewriteRuleTokenStream stream_50=new RewriteRuleTokenStream(adaptor,"token 50");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:157:15: ( 'also display' WhiteChar primary_expression ( WhiteChar )* COMMA -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==50) ) {
				alt9=1;
			}
			else if ( (LA9_0==EOF||LA9_0==76) ) {
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
					string_literal20=(Token)match(input,50,FOLLOW_50_in_also_display452);  
					stream_50.add(string_literal20);

					WhiteChar21=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display454);  
					stream_WhiteChar.add(WhiteChar21);

					pushFollow(FOLLOW_primary_expression_in_also_display456);
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
							WhiteChar23=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display458);  
							stream_WhiteChar.add(WhiteChar23);

							}
							break;

						default :
							break loop8;
						}
					}

					COMMA24=(Token)match(input,COMMA,FOLLOW_COMMA_in_also_display461);  
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
		RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:161:20: ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS COMMA ->| -> ^( Number NumberToken[\"-1\"] ) )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==76) ) {
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
					string_literal25=(Token)match(input,76,FOLLOW_76_in_fixed_start_shift496);  
					stream_76.add(string_literal25);

					WhiteChar26=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift498);  
					stream_WhiteChar.add(WhiteChar26);

					pushFollow(FOLLOW_constant_in_fixed_start_shift502);
					fixedStartShift=constant();
					state._fsp--;

					stream_constant.add(fixedStartShift.getTree());
					WhiteChar27=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift504);  
					stream_WhiteChar.add(WhiteChar27);

					DAYS28=(Token)match(input,DAYS,FOLLOW_DAYS_in_fixed_start_shift506);  
					stream_DAYS.add(DAYS28);

					COMMA29=(Token)match(input,COMMA,FOLLOW_COMMA_in_fixed_start_shift508);  
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
		RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:166:39: ( 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:2: 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) )
			{
			string_literal30=(Token)match(input,65,FOLLOW_65_in_bearish_not_bullish538);  
			stream_65.add(string_literal30);

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
					WhiteChar31=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish547);  
					stream_WhiteChar.add(WhiteChar31);

					AND32=(Token)match(input,AND,FOLLOW_AND_in_bearish_not_bullish549);  
					stream_AND.add(AND32);

					WhiteChar33=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish551);  
					stream_WhiteChar.add(WhiteChar33);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish553);
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:50: ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:74: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:109: ^( NotDoubleMapCondition )
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
					WhiteChar35=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish581);  
					stream_WhiteChar.add(WhiteChar35);

					OR36=(Token)match(input,OR,FOLLOW_OR_in_bearish_not_bullish583);  
					stream_OR.add(OR36);

					WhiteChar37=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish585);  
					stream_WhiteChar.add(WhiteChar37);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish588);
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:50: ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrDoubleMapCondition, "OrDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:73: ^( NotDoubleMapCondition )
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
			pushFollow(FOLLOW_or_expression_in_and_expression645);
			or_expression40=or_expression();
			state._fsp--;

			stream_or_expression.add(or_expression40.getTree());
			pushFollow(FOLLOW_lenient_in_and_expression649);
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
					WhiteChar41=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression652);  
					stream_WhiteChar.add(WhiteChar41);

					AND42=(Token)match(input,AND,FOLLOW_AND_in_and_expression654);  
					stream_AND.add(AND42);

					WhiteChar43=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression656);  
					stream_WhiteChar.add(WhiteChar43);

					pushFollow(FOLLOW_or_expression_in_and_expression658);
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
			// 180:80: -> ^( AndDoubleMapCondition or_expression ( or_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:84: ^( AndDoubleMapCondition or_expression ( or_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
				adaptor.addChild(root_1, (lenientParam!=null?((CommonTree)lenientParam.getTree()):null));
				adaptor.addChild(root_1, stream_or_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:144: ( or_expression )*
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
			pushFollow(FOLLOW_atom_in_or_expression690);
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
					WhiteChar46=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression693);  
					stream_WhiteChar.add(WhiteChar46);

					OR47=(Token)match(input,OR,FOLLOW_OR_in_or_expression695);  
					stream_OR.add(OR47);

					WhiteChar48=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression697);  
					stream_WhiteChar.add(WhiteChar48);

					pushFollow(FOLLOW_atom_in_or_expression699);
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
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:43: ^( OrDoubleMapCondition atom ( atom )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrDoubleMapCondition, "OrDoubleMapCondition"), root_1);
				adaptor.addChild(root_1, stream_atom.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:71: ( atom )*
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


					pushFollow(FOLLOW_booleanhistory_in_atom726);
					booleanhistory50=booleanhistory();
					state._fsp--;

					adaptor.addChild(root_0, booleanhistory50.getTree());

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:3: '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					char_literal51=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom733);  
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
							WhiteChar52=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom735);  
							stream_WhiteChar.add(WhiteChar52);

							}
							break;

						default :
							break loop14;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom738);
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
							WhiteChar54=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom740);  
							stream_WhiteChar.add(WhiteChar54);

							}
							break;

						default :
							break loop15;
						}
					}

					char_literal55=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom743);  
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
					string_literal56=(Token)match(input,NOT,FOLLOW_NOT_in_atom755);  
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
							WhiteChar57=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom757);  
							stream_WhiteChar.add(WhiteChar57);

							}
							break;

						default :
							break loop16;
						}
					}

					char_literal58=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom760);  
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
							WhiteChar59=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom762);  
							stream_WhiteChar.add(WhiteChar59);

							}
							break;

						default :
							break loop17;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom765);
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
							WhiteChar61=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom767);  
							stream_WhiteChar.add(WhiteChar61);

							}
							break;

						default :
							break loop18;
						}
					}

					char_literal62=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom770);  
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
			pushFollow(FOLLOW_operand_in_booleanhistory792);
			firstOp=operand();
			state._fsp--;

			stream_operand.add(firstOp.getTree());
			WhiteChar63=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_booleanhistory794);  
			stream_WhiteChar.add(WhiteChar63);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:44: ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			int alt20=3;
			switch ( input.LA(1) ) {
			case 54:
			case 56:
			case 61:
			case 62:
			case 70:
			case 71:
			case 72:
			case 73:
			case 77:
			case 78:
				{
				alt20=1;
				}
				break;
			case 53:
			case 55:
			case 57:
			case 63:
			case 67:
				{
				alt20=2;
				}
				break;
			case 58:
			case 59:
			case 64:
			case 68:
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
					pushFollow(FOLLOW_presetcondition_in_booleanhistory798);
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
					pushFollow(FOLLOW_opcmpcondition_in_booleanhistory807);
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
					pushFollow(FOLLOW_constantcmp_in_booleanhistory815);
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
					HistoricalData67=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_operand829);  
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
					opName=(Token)match(input,Operation,FOLLOW_Operation_in_operand856);  
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:1: constant : NumberToken -> ^( Number NumberToken ) ;
	public final ParameterizedIndicatorsParser.constant_return constant() throws RecognitionException {
		ParameterizedIndicatorsParser.constant_return retval = new ParameterizedIndicatorsParser.constant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NumberToken68=null;

		CommonTree NumberToken68_tree=null;
		RewriteRuleTokenStream stream_NumberToken=new RewriteRuleTokenStream(adaptor,"token NumberToken");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:10: ( NumberToken -> ^( Number NumberToken ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:13: NumberToken
			{
			NumberToken68=(Token)match(input,NumberToken,FOLLOW_NumberToken_in_constant870);  
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

		Token string_literal69=null;
		Token string_literal70=null;

		CommonTree string_literal69_tree=null;
		CommonTree string_literal70_tree=null;
		RewriteRuleTokenStream stream_51=new RewriteRuleTokenStream(adaptor,"token 51");
		RewriteRuleTokenStream stream_52=new RewriteRuleTokenStream(adaptor,"token 52");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:15: ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) )
			int alt22=2;
			int LA22_0 = input.LA(1);
			if ( (LA22_0==52) ) {
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:17: 'bullish'
					{
					string_literal69=(Token)match(input,52,FOLLOW_52_in_trendconstant886);  
					stream_52.add(string_literal69);

					// AST REWRITE
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
					string_literal70=(Token)match(input,51,FOLLOW_51_in_trendconstant899);  
					stream_51.add(string_literal70);

					// AST REWRITE
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

		Token WhiteChar71=null;
		Token LENIENT72=null;

		CommonTree WhiteChar71_tree=null;
		CommonTree LENIENT72_tree=null;
		RewriteRuleTokenStream stream_LENIENT=new RewriteRuleTokenStream(adaptor,"token LENIENT");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:9: ( ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( (LA23_0==WhiteChar) ) {
				int LA23_1 = input.LA(2);
				if ( (LA23_1==LENIENT) ) {
					alt23=1;
				}
				else if ( (LA23_1==AND||(LA23_1 >= CLOSEPARENTEHSIS && LA23_1 <= COMMA)||LA23_1==WhiteChar) ) {
					alt23=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 23, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( ((LA23_0 >= CLOSEPARENTEHSIS && LA23_0 <= COMMA)) ) {
				alt23=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}

			switch (alt23) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:12: WhiteChar LENIENT
					{
					WhiteChar71=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_lenient916);  
					stream_WhiteChar.add(WhiteChar71);

					LENIENT72=(Token)match(input,LENIENT,FOLLOW_LENIENT_in_lenient918);  
					stream_LENIENT.add(LENIENT72);

					// AST REWRITE
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

		Token string_literal73=null;
		Token WhiteChar74=null;
		Token WhiteChar75=null;
		Token string_literal76=null;
		Token WhiteChar77=null;
		Token WhiteChar78=null;
		Token DAYS79=null;
		Token string_literal80=null;
		Token WhiteChar81=null;
		Token WhiteChar82=null;
		Token string_literal83=null;
		Token WhiteChar84=null;
		Token WhiteChar85=null;
		Token DAYS86=null;
		Token string_literal87=null;
		Token WhiteChar88=null;
		Token WhiteChar89=null;
		Token string_literal90=null;
		Token WhiteChar91=null;
		Token WhiteChar92=null;
		Token DAYS93=null;
		Token string_literal94=null;
		Token WhiteChar95=null;
		Token WhiteChar97=null;
		Token string_literal98=null;
		Token WhiteChar99=null;
		Token WhiteChar100=null;
		Token DAYS101=null;
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
		ParserRuleReturnScope secondOp =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope spanningNbDays =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope operand96 =null;
		ParserRuleReturnScope operand109 =null;

		CommonTree string_literal73_tree=null;
		CommonTree WhiteChar74_tree=null;
		CommonTree WhiteChar75_tree=null;
		CommonTree string_literal76_tree=null;
		CommonTree WhiteChar77_tree=null;
		CommonTree WhiteChar78_tree=null;
		CommonTree DAYS79_tree=null;
		CommonTree string_literal80_tree=null;
		CommonTree WhiteChar81_tree=null;
		CommonTree WhiteChar82_tree=null;
		CommonTree string_literal83_tree=null;
		CommonTree WhiteChar84_tree=null;
		CommonTree WhiteChar85_tree=null;
		CommonTree DAYS86_tree=null;
		CommonTree string_literal87_tree=null;
		CommonTree WhiteChar88_tree=null;
		CommonTree WhiteChar89_tree=null;
		CommonTree string_literal90_tree=null;
		CommonTree WhiteChar91_tree=null;
		CommonTree WhiteChar92_tree=null;
		CommonTree DAYS93_tree=null;
		CommonTree string_literal94_tree=null;
		CommonTree WhiteChar95_tree=null;
		CommonTree WhiteChar97_tree=null;
		CommonTree string_literal98_tree=null;
		CommonTree WhiteChar99_tree=null;
		CommonTree WhiteChar100_tree=null;
		CommonTree DAYS101_tree=null;
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
		RewriteRuleTokenStream stream_55=new RewriteRuleTokenStream(adaptor,"token 55");
		RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
		RewriteRuleTokenStream stream_57=new RewriteRuleTokenStream(adaptor,"token 57");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
		RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
		RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
		RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
		RewriteRuleTokenStream stream_53=new RewriteRuleTokenStream(adaptor,"token 53");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:37: ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )? )
			int alt29=5;
			switch ( input.LA(1) ) {
			case 63:
				{
				alt29=1;
				}
				break;
			case 67:
				{
				alt29=2;
				}
				break;
			case 57:
				{
				alt29=3;
				}
				break;
			case 53:
				{
				alt29=4;
				}
				break;
			case 55:
				{
				alt29=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 29, 0, input);
				throw nvae;
			}
			switch (alt29) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:4: 'is above historical' WhiteChar secondOp= operand
					{
					string_literal73=(Token)match(input,63,FOLLOW_63_in_opcmpcondition954);  
					stream_63.add(string_literal73);

					WhiteChar74=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition956);  
					stream_WhiteChar.add(WhiteChar74);

					pushFollow(FOLLOW_operand_in_opcmpcondition960);
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
					int alt24=2;
					int LA24_0 = input.LA(1);
					if ( (LA24_0==WhiteChar) ) {
						int LA24_1 = input.LA(2);
						if ( (LA24_1==60) ) {
							alt24=1;
						}
					}
					switch (alt24) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:6: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar75=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition986);  
							stream_WhiteChar.add(WhiteChar75);

							string_literal76=(Token)match(input,60,FOLLOW_60_in_opcmpcondition988);  
							stream_60.add(string_literal76);

							WhiteChar77=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition990);  
							stream_WhiteChar.add(WhiteChar77);

							pushFollow(FOLLOW_constant_in_opcmpcondition994);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar78=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition996);  
							stream_WhiteChar.add(WhiteChar78);

							DAYS79=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition998);  
							stream_DAYS.add(DAYS79);

							// AST REWRITE
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
					string_literal80=(Token)match(input,67,FOLLOW_67_in_opcmpcondition1020);  
					stream_67.add(string_literal80);

					WhiteChar81=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1022);  
					stream_WhiteChar.add(WhiteChar81);

					pushFollow(FOLLOW_operand_in_opcmpcondition1026);
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
					int alt25=2;
					int LA25_0 = input.LA(1);
					if ( (LA25_0==WhiteChar) ) {
						int LA25_1 = input.LA(2);
						if ( (LA25_1==60) ) {
							alt25=1;
						}
					}
					switch (alt25) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar82=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1054);  
							stream_WhiteChar.add(WhiteChar82);

							string_literal83=(Token)match(input,60,FOLLOW_60_in_opcmpcondition1056);  
							stream_60.add(string_literal83);

							WhiteChar84=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1058);  
							stream_WhiteChar.add(WhiteChar84);

							pushFollow(FOLLOW_constant_in_opcmpcondition1062);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar85=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1064);  
							stream_WhiteChar.add(WhiteChar85);

							DAYS86=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1066);  
							stream_DAYS.add(DAYS86);

							// AST REWRITE
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
					string_literal87=(Token)match(input,57,FOLLOW_57_in_opcmpcondition1088);  
					stream_57.add(string_literal87);

					WhiteChar88=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1090);  
					stream_WhiteChar.add(WhiteChar88);

					pushFollow(FOLLOW_operand_in_opcmpcondition1094);
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
					int alt26=2;
					int LA26_0 = input.LA(1);
					if ( (LA26_0==WhiteChar) ) {
						int LA26_1 = input.LA(2);
						if ( (LA26_1==60) ) {
							alt26=1;
						}
					}
					switch (alt26) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar89=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1121);  
							stream_WhiteChar.add(WhiteChar89);

							string_literal90=(Token)match(input,60,FOLLOW_60_in_opcmpcondition1123);  
							stream_60.add(string_literal90);

							WhiteChar91=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1125);  
							stream_WhiteChar.add(WhiteChar91);

							pushFollow(FOLLOW_constant_in_opcmpcondition1129);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar92=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1131);  
							stream_WhiteChar.add(WhiteChar92);

							DAYS93=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1133);  
							stream_DAYS.add(DAYS93);

							// AST REWRITE
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
					string_literal94=(Token)match(input,53,FOLLOW_53_in_opcmpcondition1156);  
					stream_53.add(string_literal94);

					WhiteChar95=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1158);  
					stream_WhiteChar.add(WhiteChar95);

					pushFollow(FOLLOW_operand_in_opcmpcondition1160);
					operand96=operand();
					state._fsp--;

					stream_operand.add(operand96.getTree());
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
					int alt27=2;
					int LA27_0 = input.LA(1);
					if ( (LA27_0==WhiteChar) ) {
						int LA27_1 = input.LA(2);
						if ( (LA27_1==80) ) {
							alt27=1;
						}
					}
					switch (alt27) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:11: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar97=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1197);  
							stream_WhiteChar.add(WhiteChar97);

							string_literal98=(Token)match(input,80,FOLLOW_80_in_opcmpcondition1199);  
							stream_80.add(string_literal98);

							WhiteChar99=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1201);  
							stream_WhiteChar.add(WhiteChar99);

							pushFollow(FOLLOW_constant_in_opcmpcondition1205);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar100=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1207);  
							stream_WhiteChar.add(WhiteChar100);

							DAYS101=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1209);  
							stream_DAYS.add(DAYS101);

							WhiteChar102=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1222);  
							stream_WhiteChar.add(WhiteChar102);

							string_literal103=(Token)match(input,75,FOLLOW_75_in_opcmpcondition1224);  
							stream_75.add(string_literal103);

							WhiteChar104=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1226);  
							stream_WhiteChar.add(WhiteChar104);

							pushFollow(FOLLOW_constant_in_opcmpcondition1230);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar105=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1232);  
							stream_WhiteChar.add(WhiteChar105);

							DAYS106=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1234);  
							stream_DAYS.add(DAYS106);

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
					string_literal107=(Token)match(input,55,FOLLOW_55_in_opcmpcondition1270);  
					stream_55.add(string_literal107);

					WhiteChar108=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1272);  
					stream_WhiteChar.add(WhiteChar108);

					pushFollow(FOLLOW_operand_in_opcmpcondition1274);
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
					int alt28=2;
					int LA28_0 = input.LA(1);
					if ( (LA28_0==WhiteChar) ) {
						int LA28_1 = input.LA(2);
						if ( (LA28_1==80) ) {
							alt28=1;
						}
					}
					switch (alt28) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:10: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar110=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1311);  
							stream_WhiteChar.add(WhiteChar110);

							string_literal111=(Token)match(input,80,FOLLOW_80_in_opcmpcondition1313);  
							stream_80.add(string_literal111);

							WhiteChar112=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1315);  
							stream_WhiteChar.add(WhiteChar112);

							pushFollow(FOLLOW_constant_in_opcmpcondition1319);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar113=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1321);  
							stream_WhiteChar.add(WhiteChar113);

							DAYS114=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1323);  
							stream_DAYS.add(DAYS114);

							WhiteChar115=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1334);  
							stream_WhiteChar.add(WhiteChar115);

							string_literal116=(Token)match(input,75,FOLLOW_75_in_opcmpcondition1336);  
							stream_75.add(string_literal116);

							WhiteChar117=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1338);  
							stream_WhiteChar.add(WhiteChar117);

							pushFollow(FOLLOW_constant_in_opcmpcondition1342);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar118=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1344);  
							stream_WhiteChar.add(WhiteChar118);

							DAYS119=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1346);  
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

		Token string_literal120=null;
		Token WhiteChar121=null;
		Token WhiteChar122=null;
		Token string_literal123=null;
		Token WhiteChar124=null;
		Token WhiteChar125=null;
		Token DAYS126=null;
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
		Token WhiteChar139=null;
		Token string_literal140=null;
		Token WhiteChar141=null;
		Token WhiteChar142=null;
		Token DAYS143=null;
		Token string_literal144=null;
		Token WhiteChar145=null;
		Token WhiteChar146=null;
		Token string_literal147=null;
		Token WhiteChar148=null;
		Token WhiteChar149=null;
		Token DAYS150=null;
		Token WhiteChar151=null;
		Token string_literal152=null;
		Token WhiteChar153=null;
		Token WhiteChar154=null;
		Token DAYS155=null;
		Token string_literal156=null;
		Token WhiteChar157=null;
		Token WhiteChar158=null;
		Token string_literal159=null;
		Token WhiteChar160=null;
		Token WhiteChar161=null;
		Token DAYS162=null;
		Token WhiteChar163=null;
		Token string_literal164=null;
		Token WhiteChar165=null;
		Token WhiteChar166=null;
		Token DAYS167=null;
		ParserRuleReturnScope trendSignal =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope threshold =null;

		CommonTree string_literal120_tree=null;
		CommonTree WhiteChar121_tree=null;
		CommonTree WhiteChar122_tree=null;
		CommonTree string_literal123_tree=null;
		CommonTree WhiteChar124_tree=null;
		CommonTree WhiteChar125_tree=null;
		CommonTree DAYS126_tree=null;
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
		CommonTree WhiteChar139_tree=null;
		CommonTree string_literal140_tree=null;
		CommonTree WhiteChar141_tree=null;
		CommonTree WhiteChar142_tree=null;
		CommonTree DAYS143_tree=null;
		CommonTree string_literal144_tree=null;
		CommonTree WhiteChar145_tree=null;
		CommonTree WhiteChar146_tree=null;
		CommonTree string_literal147_tree=null;
		CommonTree WhiteChar148_tree=null;
		CommonTree WhiteChar149_tree=null;
		CommonTree DAYS150_tree=null;
		CommonTree WhiteChar151_tree=null;
		CommonTree string_literal152_tree=null;
		CommonTree WhiteChar153_tree=null;
		CommonTree WhiteChar154_tree=null;
		CommonTree DAYS155_tree=null;
		CommonTree string_literal156_tree=null;
		CommonTree WhiteChar157_tree=null;
		CommonTree WhiteChar158_tree=null;
		CommonTree string_literal159_tree=null;
		CommonTree WhiteChar160_tree=null;
		CommonTree WhiteChar161_tree=null;
		CommonTree DAYS162_tree=null;
		CommonTree WhiteChar163_tree=null;
		CommonTree string_literal164_tree=null;
		CommonTree WhiteChar165_tree=null;
		CommonTree WhiteChar166_tree=null;
		CommonTree DAYS167_tree=null;
		RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
		RewriteRuleTokenStream stream_58=new RewriteRuleTokenStream(adaptor,"token 58");
		RewriteRuleTokenStream stream_59=new RewriteRuleTokenStream(adaptor,"token 59");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
		RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
		RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_trendconstant=new RewriteRuleSubtreeStream(adaptor,"rule trendconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:34: ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )? )
			int alt34=4;
			switch ( input.LA(1) ) {
			case 59:
				{
				alt34=1;
				}
				break;
			case 58:
				{
				alt34=2;
				}
				break;
			case 64:
				{
				alt34=3;
				}
				break;
			case 68:
				{
				alt34=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 34, 0, input);
				throw nvae;
			}
			switch (alt34) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:4: 'equals trend' WhiteChar trendSignal= trendconstant
					{
					string_literal120=(Token)match(input,59,FOLLOW_59_in_constantcmp1388);  
					stream_59.add(string_literal120);

					WhiteChar121=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1390);  
					stream_WhiteChar.add(WhiteChar121);

					pushFollow(FOLLOW_trendconstant_in_constantcmp1394);
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
					int alt30=2;
					int LA30_0 = input.LA(1);
					if ( (LA30_0==WhiteChar) ) {
						int LA30_1 = input.LA(2);
						if ( (LA30_1==75) ) {
							alt30=1;
						}
					}
					switch (alt30) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar122=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1428);  
							stream_WhiteChar.add(WhiteChar122);

							string_literal123=(Token)match(input,75,FOLLOW_75_in_constantcmp1430);  
							stream_75.add(string_literal123);

							WhiteChar124=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1432);  
							stream_WhiteChar.add(WhiteChar124);

							pushFollow(FOLLOW_constant_in_constantcmp1436);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar125=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1438);  
							stream_WhiteChar.add(WhiteChar125);

							DAYS126=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1440);  
							stream_DAYS.add(DAYS126);

							WhiteChar127=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1442);  
							stream_WhiteChar.add(WhiteChar127);

							string_literal128=(Token)match(input,60,FOLLOW_60_in_constantcmp1444);  
							stream_60.add(string_literal128);

							WhiteChar129=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1446);  
							stream_WhiteChar.add(WhiteChar129);

							pushFollow(FOLLOW_constant_in_constantcmp1450);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar130=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1452);  
							stream_WhiteChar.add(WhiteChar130);

							DAYS131=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1454);  
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
					string_literal132=(Token)match(input,58,FOLLOW_58_in_constantcmp1482);  
					stream_58.add(string_literal132);

					WhiteChar133=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1484);  
					stream_WhiteChar.add(WhiteChar133);

					pushFollow(FOLLOW_constant_in_constantcmp1488);
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
					int alt31=2;
					int LA31_0 = input.LA(1);
					if ( (LA31_0==WhiteChar) ) {
						int LA31_1 = input.LA(2);
						if ( (LA31_1==75) ) {
							alt31=1;
						}
					}
					switch (alt31) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar134=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1522);  
							stream_WhiteChar.add(WhiteChar134);

							string_literal135=(Token)match(input,75,FOLLOW_75_in_constantcmp1524);  
							stream_75.add(string_literal135);

							WhiteChar136=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1526);  
							stream_WhiteChar.add(WhiteChar136);

							pushFollow(FOLLOW_constant_in_constantcmp1530);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar137=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1532);  
							stream_WhiteChar.add(WhiteChar137);

							DAYS138=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1534);  
							stream_DAYS.add(DAYS138);

							WhiteChar139=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1536);  
							stream_WhiteChar.add(WhiteChar139);

							string_literal140=(Token)match(input,60,FOLLOW_60_in_constantcmp1538);  
							stream_60.add(string_literal140);

							WhiteChar141=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1540);  
							stream_WhiteChar.add(WhiteChar141);

							pushFollow(FOLLOW_constant_in_constantcmp1544);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar142=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1546);  
							stream_WhiteChar.add(WhiteChar142);

							DAYS143=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1548);  
							stream_DAYS.add(DAYS143);

							// AST REWRITE
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
					string_literal144=(Token)match(input,64,FOLLOW_64_in_constantcmp1577);  
					stream_64.add(string_literal144);

					WhiteChar145=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1579);  
					stream_WhiteChar.add(WhiteChar145);

					pushFollow(FOLLOW_constant_in_constantcmp1583);
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
					int alt32=2;
					int LA32_0 = input.LA(1);
					if ( (LA32_0==WhiteChar) ) {
						int LA32_1 = input.LA(2);
						if ( (LA32_1==75) ) {
							alt32=1;
						}
					}
					switch (alt32) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar146=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1617);  
							stream_WhiteChar.add(WhiteChar146);

							string_literal147=(Token)match(input,75,FOLLOW_75_in_constantcmp1619);  
							stream_75.add(string_literal147);

							WhiteChar148=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1621);  
							stream_WhiteChar.add(WhiteChar148);

							pushFollow(FOLLOW_constant_in_constantcmp1625);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar149=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1627);  
							stream_WhiteChar.add(WhiteChar149);

							DAYS150=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1629);  
							stream_DAYS.add(DAYS150);

							WhiteChar151=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1631);  
							stream_WhiteChar.add(WhiteChar151);

							string_literal152=(Token)match(input,60,FOLLOW_60_in_constantcmp1633);  
							stream_60.add(string_literal152);

							WhiteChar153=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1635);  
							stream_WhiteChar.add(WhiteChar153);

							pushFollow(FOLLOW_constant_in_constantcmp1639);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar154=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1641);  
							stream_WhiteChar.add(WhiteChar154);

							DAYS155=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1643);  
							stream_DAYS.add(DAYS155);

							// AST REWRITE
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
					string_literal156=(Token)match(input,68,FOLLOW_68_in_constantcmp1672);  
					stream_68.add(string_literal156);

					WhiteChar157=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1674);  
					stream_WhiteChar.add(WhiteChar157);

					pushFollow(FOLLOW_constant_in_constantcmp1678);
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
					int alt33=2;
					int LA33_0 = input.LA(1);
					if ( (LA33_0==WhiteChar) ) {
						int LA33_1 = input.LA(2);
						if ( (LA33_1==75) ) {
							alt33=1;
						}
					}
					switch (alt33) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar158=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1712);  
							stream_WhiteChar.add(WhiteChar158);

							string_literal159=(Token)match(input,75,FOLLOW_75_in_constantcmp1714);  
							stream_75.add(string_literal159);

							WhiteChar160=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1716);  
							stream_WhiteChar.add(WhiteChar160);

							pushFollow(FOLLOW_constant_in_constantcmp1720);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar161=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1722);  
							stream_WhiteChar.add(WhiteChar161);

							DAYS162=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1724);  
							stream_DAYS.add(DAYS162);

							WhiteChar163=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1726);  
							stream_WhiteChar.add(WhiteChar163);

							string_literal164=(Token)match(input,60,FOLLOW_60_in_constantcmp1728);  
							stream_60.add(string_literal164);

							WhiteChar165=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1730);  
							stream_WhiteChar.add(WhiteChar165);

							pushFollow(FOLLOW_constant_in_constantcmp1734);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar166=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1736);  
							stream_WhiteChar.add(WhiteChar166);

							DAYS167=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1738);  
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:1: presetcondition[CommonTree firstOp] : ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS -> ^( HigherHighCondition ) ) | ( 'makes a higher low over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS -> ^( HigherLowCondition ) ) | ( 'makes a lower high over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS -> ^( LowerHighCondition ) ) | ( 'makes a lower low over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS -> ^( LowerLowCondition ) ) );
	public final ParameterizedIndicatorsParser.presetcondition_return presetcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.presetcondition_return retval = new ParameterizedIndicatorsParser.presetcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal168=null;
		Token WhiteChar169=null;
		Token string_literal170=null;
		Token WhiteChar171=null;
		Token PERCENT172=null;
		Token WhiteChar173=null;
		Token string_literal174=null;
		Token WhiteChar175=null;
		Token WhiteChar176=null;
		Token DAYS177=null;
		Token string_literal178=null;
		Token WhiteChar179=null;
		Token string_literal180=null;
		Token WhiteChar181=null;
		Token PERCENT182=null;
		Token WhiteChar183=null;
		Token string_literal184=null;
		Token WhiteChar185=null;
		Token WhiteChar186=null;
		Token DAYS187=null;
		Token string_literal188=null;
		Token WhiteChar189=null;
		Token PERCENT190=null;
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
		Token string_literal201=null;
		Token WhiteChar202=null;
		Token PERCENT203=null;
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
		Token string_literal214=null;
		Token WhiteChar215=null;
		Token WhiteChar216=null;
		Token string_literal217=null;
		Token WhiteChar218=null;
		Token WhiteChar219=null;
		Token DAYS220=null;
		Token WhiteChar221=null;
		Token string_literal222=null;
		Token WhiteChar223=null;
		Token WhiteChar224=null;
		Token DAYS225=null;
		Token string_literal226=null;
		Token WhiteChar227=null;
		Token WhiteChar228=null;
		Token string_literal229=null;
		Token WhiteChar230=null;
		Token WhiteChar231=null;
		Token DAYS232=null;
		Token WhiteChar233=null;
		Token string_literal234=null;
		Token WhiteChar235=null;
		Token WhiteChar236=null;
		Token DAYS237=null;
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
		Token WhiteChar250=null;
		Token DAYS251=null;
		Token string_literal252=null;
		Token WhiteChar253=null;
		Token WhiteChar254=null;
		Token DAYS255=null;
		Token WhiteChar256=null;
		Token string_literal257=null;
		Token WhiteChar258=null;
		Token WhiteChar259=null;
		Token DAYS260=null;
		Token WhiteChar261=null;
		Token string_literal262=null;
		Token WhiteChar263=null;
		Token WhiteChar264=null;
		Token DAYS265=null;
		Token string_literal266=null;
		Token WhiteChar267=null;
		Token WhiteChar268=null;
		Token DAYS269=null;
		Token WhiteChar270=null;
		Token string_literal271=null;
		Token WhiteChar272=null;
		Token WhiteChar273=null;
		Token DAYS274=null;
		Token WhiteChar275=null;
		Token string_literal276=null;
		Token WhiteChar277=null;
		Token WhiteChar278=null;
		Token DAYS279=null;
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
		Token WhiteChar292=null;
		Token DAYS293=null;
		ParserRuleReturnScope percentdown =null;
		ParserRuleReturnScope spanningNbDays =null;
		ParserRuleReturnScope percentup =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope threshold =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope lookBack =null;
		ParserRuleReturnScope extremesSpan =null;
		ParserRuleReturnScope smoothP =null;

		CommonTree string_literal168_tree=null;
		CommonTree WhiteChar169_tree=null;
		CommonTree string_literal170_tree=null;
		CommonTree WhiteChar171_tree=null;
		CommonTree PERCENT172_tree=null;
		CommonTree WhiteChar173_tree=null;
		CommonTree string_literal174_tree=null;
		CommonTree WhiteChar175_tree=null;
		CommonTree WhiteChar176_tree=null;
		CommonTree DAYS177_tree=null;
		CommonTree string_literal178_tree=null;
		CommonTree WhiteChar179_tree=null;
		CommonTree string_literal180_tree=null;
		CommonTree WhiteChar181_tree=null;
		CommonTree PERCENT182_tree=null;
		CommonTree WhiteChar183_tree=null;
		CommonTree string_literal184_tree=null;
		CommonTree WhiteChar185_tree=null;
		CommonTree WhiteChar186_tree=null;
		CommonTree DAYS187_tree=null;
		CommonTree string_literal188_tree=null;
		CommonTree WhiteChar189_tree=null;
		CommonTree PERCENT190_tree=null;
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
		CommonTree string_literal201_tree=null;
		CommonTree WhiteChar202_tree=null;
		CommonTree PERCENT203_tree=null;
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
		CommonTree string_literal214_tree=null;
		CommonTree WhiteChar215_tree=null;
		CommonTree WhiteChar216_tree=null;
		CommonTree string_literal217_tree=null;
		CommonTree WhiteChar218_tree=null;
		CommonTree WhiteChar219_tree=null;
		CommonTree DAYS220_tree=null;
		CommonTree WhiteChar221_tree=null;
		CommonTree string_literal222_tree=null;
		CommonTree WhiteChar223_tree=null;
		CommonTree WhiteChar224_tree=null;
		CommonTree DAYS225_tree=null;
		CommonTree string_literal226_tree=null;
		CommonTree WhiteChar227_tree=null;
		CommonTree WhiteChar228_tree=null;
		CommonTree string_literal229_tree=null;
		CommonTree WhiteChar230_tree=null;
		CommonTree WhiteChar231_tree=null;
		CommonTree DAYS232_tree=null;
		CommonTree WhiteChar233_tree=null;
		CommonTree string_literal234_tree=null;
		CommonTree WhiteChar235_tree=null;
		CommonTree WhiteChar236_tree=null;
		CommonTree DAYS237_tree=null;
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
		CommonTree WhiteChar250_tree=null;
		CommonTree DAYS251_tree=null;
		CommonTree string_literal252_tree=null;
		CommonTree WhiteChar253_tree=null;
		CommonTree WhiteChar254_tree=null;
		CommonTree DAYS255_tree=null;
		CommonTree WhiteChar256_tree=null;
		CommonTree string_literal257_tree=null;
		CommonTree WhiteChar258_tree=null;
		CommonTree WhiteChar259_tree=null;
		CommonTree DAYS260_tree=null;
		CommonTree WhiteChar261_tree=null;
		CommonTree string_literal262_tree=null;
		CommonTree WhiteChar263_tree=null;
		CommonTree WhiteChar264_tree=null;
		CommonTree DAYS265_tree=null;
		CommonTree string_literal266_tree=null;
		CommonTree WhiteChar267_tree=null;
		CommonTree WhiteChar268_tree=null;
		CommonTree DAYS269_tree=null;
		CommonTree WhiteChar270_tree=null;
		CommonTree string_literal271_tree=null;
		CommonTree WhiteChar272_tree=null;
		CommonTree WhiteChar273_tree=null;
		CommonTree DAYS274_tree=null;
		CommonTree WhiteChar275_tree=null;
		CommonTree string_literal276_tree=null;
		CommonTree WhiteChar277_tree=null;
		CommonTree WhiteChar278_tree=null;
		CommonTree DAYS279_tree=null;
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
		CommonTree WhiteChar292_tree=null;
		CommonTree DAYS293_tree=null;
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
		RewriteRuleTokenStream stream_56=new RewriteRuleTokenStream(adaptor,"token 56");
		RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
		RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
		RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
		RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
		RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
		RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
		RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
		RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
		RewriteRuleTokenStream stream_54=new RewriteRuleTokenStream(adaptor,"token 54");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:39: ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS -> ^( HigherHighCondition ) ) | ( 'makes a higher low over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS -> ^( HigherLowCondition ) ) | ( 'makes a lower high over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS -> ^( LowerHighCondition ) ) | ( 'makes a lower low over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS -> ^( LowerLowCondition ) ) )
			int alt41=10;
			switch ( input.LA(1) ) {
			case 77:
				{
				alt41=1;
				}
				break;
			case 78:
				{
				alt41=2;
				}
				break;
			case 61:
				{
				alt41=3;
				}
				break;
			case 62:
				{
				alt41=4;
				}
				break;
			case 56:
				{
				alt41=5;
				}
				break;
			case 54:
				{
				alt41=6;
				}
				break;
			case 70:
				{
				alt41=7;
				}
				break;
			case 71:
				{
				alt41=8;
				}
				break;
			case 72:
				{
				alt41=9;
				}
				break;
			case 73:
				{
				alt41=10;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 41, 0, input);
				throw nvae;
			}
			switch (alt41) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:4: 'reverses down'
					{
					string_literal168=(Token)match(input,77,FOLLOW_77_in_presetcondition1772);  
					stream_77.add(string_literal168);

					// AST REWRITE
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:9: WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar169=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1812);  
							stream_WhiteChar.add(WhiteChar169);

							string_literal170=(Token)match(input,74,FOLLOW_74_in_presetcondition1814);  
							stream_74.add(string_literal170);

							WhiteChar171=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1816);  
							stream_WhiteChar.add(WhiteChar171);

							pushFollow(FOLLOW_constant_in_presetcondition1820);
							percentdown=constant();
							state._fsp--;

							stream_constant.add(percentdown.getTree());
							PERCENT172=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition1822);  
							stream_PERCENT.add(PERCENT172);

							WhiteChar173=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1824);  
							stream_WhiteChar.add(WhiteChar173);

							string_literal174=(Token)match(input,80,FOLLOW_80_in_presetcondition1826);  
							stream_80.add(string_literal174);

							WhiteChar175=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1828);  
							stream_WhiteChar.add(WhiteChar175);

							pushFollow(FOLLOW_constant_in_presetcondition1832);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar176=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1834);  
							stream_WhiteChar.add(WhiteChar176);

							DAYS177=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1836);  
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
					string_literal178=(Token)match(input,78,FOLLOW_78_in_presetcondition1880);  
					stream_78.add(string_literal178);

					// AST REWRITE
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:9: WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar179=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1920);  
							stream_WhiteChar.add(WhiteChar179);

							string_literal180=(Token)match(input,74,FOLLOW_74_in_presetcondition1922);  
							stream_74.add(string_literal180);

							WhiteChar181=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1924);  
							stream_WhiteChar.add(WhiteChar181);

							pushFollow(FOLLOW_constant_in_presetcondition1928);
							percentup=constant();
							state._fsp--;

							stream_constant.add(percentup.getTree());
							PERCENT182=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition1930);  
							stream_PERCENT.add(PERCENT182);

							WhiteChar183=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1932);  
							stream_WhiteChar.add(WhiteChar183);

							string_literal184=(Token)match(input,80,FOLLOW_80_in_presetcondition1934);  
							stream_80.add(string_literal184);

							WhiteChar185=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1936);  
							stream_WhiteChar.add(WhiteChar185);

							pushFollow(FOLLOW_constant_in_presetcondition1940);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar186=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1942);  
							stream_WhiteChar.add(WhiteChar186);

							DAYS187=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1944);  
							stream_DAYS.add(DAYS187);

							// AST REWRITE
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
					string_literal188=(Token)match(input,61,FOLLOW_61_in_presetcondition1987);  
					stream_61.add(string_literal188);

					WhiteChar189=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1989);  
					stream_WhiteChar.add(WhiteChar189);

					pushFollow(FOLLOW_constant_in_presetcondition1993);
					percentdown=constant();
					state._fsp--;

					stream_constant.add(percentdown.getTree());
					PERCENT190=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition1995);  
					stream_PERCENT.add(PERCENT190);

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
					int alt37=2;
					int LA37_0 = input.LA(1);
					if ( (LA37_0==WhiteChar) ) {
						int LA37_1 = input.LA(2);
						if ( (LA37_1==80) ) {
							alt37=1;
						}
					}
					switch (alt37) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar191=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2038);  
							stream_WhiteChar.add(WhiteChar191);

							string_literal192=(Token)match(input,80,FOLLOW_80_in_presetcondition2040);  
							stream_80.add(string_literal192);

							WhiteChar193=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2042);  
							stream_WhiteChar.add(WhiteChar193);

							pushFollow(FOLLOW_constant_in_presetcondition2046);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar194=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2048);  
							stream_WhiteChar.add(WhiteChar194);

							DAYS195=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2050);  
							stream_DAYS.add(DAYS195);

							WhiteChar196=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2061);  
							stream_WhiteChar.add(WhiteChar196);

							string_literal197=(Token)match(input,60,FOLLOW_60_in_presetcondition2063);  
							stream_60.add(string_literal197);

							WhiteChar198=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2065);  
							stream_WhiteChar.add(WhiteChar198);

							pushFollow(FOLLOW_constant_in_presetcondition2069);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar199=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2071);  
							stream_WhiteChar.add(WhiteChar199);

							DAYS200=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2073);  
							stream_DAYS.add(DAYS200);

							// AST REWRITE
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
					string_literal201=(Token)match(input,62,FOLLOW_62_in_presetcondition2111);  
					stream_62.add(string_literal201);

					WhiteChar202=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2113);  
					stream_WhiteChar.add(WhiteChar202);

					pushFollow(FOLLOW_constant_in_presetcondition2117);
					percentup=constant();
					state._fsp--;

					stream_constant.add(percentup.getTree());
					PERCENT203=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2119);  
					stream_PERCENT.add(PERCENT203);

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

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:122: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:151: ^( Number NumberToken[\"0.0\"] )
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
					int alt38=2;
					int LA38_0 = input.LA(1);
					if ( (LA38_0==WhiteChar) ) {
						int LA38_1 = input.LA(2);
						if ( (LA38_1==80) ) {
							alt38=1;
						}
					}
					switch (alt38) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar204=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2163);  
							stream_WhiteChar.add(WhiteChar204);

							string_literal205=(Token)match(input,80,FOLLOW_80_in_presetcondition2165);  
							stream_80.add(string_literal205);

							WhiteChar206=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2167);  
							stream_WhiteChar.add(WhiteChar206);

							pushFollow(FOLLOW_constant_in_presetcondition2171);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar207=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2173);  
							stream_WhiteChar.add(WhiteChar207);

							DAYS208=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2175);  
							stream_DAYS.add(DAYS208);

							WhiteChar209=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2186);  
							stream_WhiteChar.add(WhiteChar209);

							string_literal210=(Token)match(input,60,FOLLOW_60_in_presetcondition2188);  
							stream_60.add(string_literal210);

							WhiteChar211=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2190);  
							stream_WhiteChar.add(WhiteChar211);

							pushFollow(FOLLOW_constant_in_presetcondition2194);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar212=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2196);  
							stream_WhiteChar.add(WhiteChar212);

							DAYS213=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2198);  
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
					string_literal214=(Token)match(input,56,FOLLOW_56_in_presetcondition2243);  
					stream_56.add(string_literal214);

					WhiteChar215=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2245);  
					stream_WhiteChar.add(WhiteChar215);

					pushFollow(FOLLOW_constant_in_presetcondition2249);
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

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:125: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:154: ^( Number NumberToken[\"0.0\"] )
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
					int alt39=2;
					int LA39_0 = input.LA(1);
					if ( (LA39_0==WhiteChar) ) {
						int LA39_1 = input.LA(2);
						if ( (LA39_1==80) ) {
							alt39=1;
						}
					}
					switch (alt39) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar216=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2292);  
							stream_WhiteChar.add(WhiteChar216);

							string_literal217=(Token)match(input,80,FOLLOW_80_in_presetcondition2294);  
							stream_80.add(string_literal217);

							WhiteChar218=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2296);  
							stream_WhiteChar.add(WhiteChar218);

							pushFollow(FOLLOW_constant_in_presetcondition2300);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar219=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2302);  
							stream_WhiteChar.add(WhiteChar219);

							DAYS220=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2304);  
							stream_DAYS.add(DAYS220);

							WhiteChar221=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2315);  
							stream_WhiteChar.add(WhiteChar221);

							string_literal222=(Token)match(input,75,FOLLOW_75_in_presetcondition2317);  
							stream_75.add(string_literal222);

							WhiteChar223=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2319);  
							stream_WhiteChar.add(WhiteChar223);

							pushFollow(FOLLOW_constant_in_presetcondition2323);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar224=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2325);  
							stream_WhiteChar.add(WhiteChar224);

							DAYS225=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2327);  
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
					string_literal226=(Token)match(input,54,FOLLOW_54_in_presetcondition2373);  
					stream_54.add(string_literal226);

					WhiteChar227=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2375);  
					stream_WhiteChar.add(WhiteChar227);

					pushFollow(FOLLOW_constant_in_presetcondition2379);
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
					int alt40=2;
					int LA40_0 = input.LA(1);
					if ( (LA40_0==WhiteChar) ) {
						int LA40_1 = input.LA(2);
						if ( (LA40_1==80) ) {
							alt40=1;
						}
					}
					switch (alt40) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar228=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2421);  
							stream_WhiteChar.add(WhiteChar228);

							string_literal229=(Token)match(input,80,FOLLOW_80_in_presetcondition2423);  
							stream_80.add(string_literal229);

							WhiteChar230=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2425);  
							stream_WhiteChar.add(WhiteChar230);

							pushFollow(FOLLOW_constant_in_presetcondition2429);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar231=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2431);  
							stream_WhiteChar.add(WhiteChar231);

							DAYS232=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2433);  
							stream_DAYS.add(DAYS232);

							WhiteChar233=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2444);  
							stream_WhiteChar.add(WhiteChar233);

							string_literal234=(Token)match(input,75,FOLLOW_75_in_presetcondition2446);  
							stream_75.add(string_literal234);

							WhiteChar235=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2448);  
							stream_WhiteChar.add(WhiteChar235);

							pushFollow(FOLLOW_constant_in_presetcondition2452);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar236=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2454);  
							stream_WhiteChar.add(WhiteChar236);

							DAYS237=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2456);  
							stream_DAYS.add(DAYS237);

							// AST REWRITE
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:3: ( 'makes a higher high over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS -> ^( HigherHighCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:3: ( 'makes a higher high over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS -> ^( HigherHighCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:4: 'makes a higher high over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS
					{
					string_literal238=(Token)match(input,70,FOLLOW_70_in_presetcondition2494);  
					stream_70.add(string_literal238);

					WhiteChar239=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2496);  
					stream_WhiteChar.add(WhiteChar239);

					pushFollow(FOLLOW_constant_in_presetcondition2500);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar240=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2502);  
					stream_WhiteChar.add(WhiteChar240);

					DAYS241=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2504);  
					stream_DAYS.add(DAYS241);

					WhiteChar242=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2511);  
					stream_WhiteChar.add(WhiteChar242);

					string_literal243=(Token)match(input,80,FOLLOW_80_in_presetcondition2513);  
					stream_80.add(string_literal243);

					WhiteChar244=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2515);  
					stream_WhiteChar.add(WhiteChar244);

					pushFollow(FOLLOW_constant_in_presetcondition2519);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar245=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2521);  
					stream_WhiteChar.add(WhiteChar245);

					DAYS246=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2523);  
					stream_DAYS.add(DAYS246);

					WhiteChar247=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2530);  
					stream_WhiteChar.add(WhiteChar247);

					string_literal248=(Token)match(input,79,FOLLOW_79_in_presetcondition2532);  
					stream_79.add(string_literal248);

					WhiteChar249=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2534);  
					stream_WhiteChar.add(WhiteChar249);

					pushFollow(FOLLOW_constant_in_presetcondition2538);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar250=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2540);  
					stream_WhiteChar.add(WhiteChar250);

					DAYS251=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2542);  
					stream_DAYS.add(DAYS251);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 264:4: -> ^( HigherHighCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:7: ^( HigherHighCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(HigherHighCondition, "HigherHighCondition"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					}
					break;
				case 8 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:3: ( 'makes a higher low over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS -> ^( HigherLowCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:3: ( 'makes a higher low over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS -> ^( HigherLowCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:265:4: 'makes a higher low over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS
					{
					string_literal252=(Token)match(input,71,FOLLOW_71_in_presetcondition2569);  
					stream_71.add(string_literal252);

					WhiteChar253=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2571);  
					stream_WhiteChar.add(WhiteChar253);

					pushFollow(FOLLOW_constant_in_presetcondition2575);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar254=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2577);  
					stream_WhiteChar.add(WhiteChar254);

					DAYS255=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2579);  
					stream_DAYS.add(DAYS255);

					WhiteChar256=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2586);  
					stream_WhiteChar.add(WhiteChar256);

					string_literal257=(Token)match(input,80,FOLLOW_80_in_presetcondition2588);  
					stream_80.add(string_literal257);

					WhiteChar258=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2590);  
					stream_WhiteChar.add(WhiteChar258);

					pushFollow(FOLLOW_constant_in_presetcondition2594);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar259=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2596);  
					stream_WhiteChar.add(WhiteChar259);

					DAYS260=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2598);  
					stream_DAYS.add(DAYS260);

					WhiteChar261=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2605);  
					stream_WhiteChar.add(WhiteChar261);

					string_literal262=(Token)match(input,79,FOLLOW_79_in_presetcondition2607);  
					stream_79.add(string_literal262);

					WhiteChar263=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2609);  
					stream_WhiteChar.add(WhiteChar263);

					pushFollow(FOLLOW_constant_in_presetcondition2613);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar264=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2615);  
					stream_WhiteChar.add(WhiteChar264);

					DAYS265=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2617);  
					stream_DAYS.add(DAYS265);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 268:4: -> ^( HigherLowCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:268:7: ^( HigherLowCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(HigherLowCondition, "HigherLowCondition"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					}
					break;
				case 9 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:269:3: ( 'makes a lower high over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS -> ^( LowerHighCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:269:3: ( 'makes a lower high over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS -> ^( LowerHighCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:269:4: 'makes a lower high over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS
					{
					string_literal266=(Token)match(input,72,FOLLOW_72_in_presetcondition2644);  
					stream_72.add(string_literal266);

					WhiteChar267=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2646);  
					stream_WhiteChar.add(WhiteChar267);

					pushFollow(FOLLOW_constant_in_presetcondition2650);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar268=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2652);  
					stream_WhiteChar.add(WhiteChar268);

					DAYS269=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2654);  
					stream_DAYS.add(DAYS269);

					WhiteChar270=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2661);  
					stream_WhiteChar.add(WhiteChar270);

					string_literal271=(Token)match(input,80,FOLLOW_80_in_presetcondition2663);  
					stream_80.add(string_literal271);

					WhiteChar272=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2665);  
					stream_WhiteChar.add(WhiteChar272);

					pushFollow(FOLLOW_constant_in_presetcondition2669);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar273=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2671);  
					stream_WhiteChar.add(WhiteChar273);

					DAYS274=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2673);  
					stream_DAYS.add(DAYS274);

					WhiteChar275=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2680);  
					stream_WhiteChar.add(WhiteChar275);

					string_literal276=(Token)match(input,79,FOLLOW_79_in_presetcondition2682);  
					stream_79.add(string_literal276);

					WhiteChar277=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2684);  
					stream_WhiteChar.add(WhiteChar277);

					pushFollow(FOLLOW_constant_in_presetcondition2688);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar278=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2690);  
					stream_WhiteChar.add(WhiteChar278);

					DAYS279=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2692);  
					stream_DAYS.add(DAYS279);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 272:4: -> ^( LowerHighCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:272:7: ^( LowerHighCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LowerHighCondition, "LowerHighCondition"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					}
					break;
				case 10 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:273:3: ( 'makes a lower low over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS -> ^( LowerLowCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:273:3: ( 'makes a lower low over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS -> ^( LowerLowCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:273:4: 'makes a lower low over' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'spanning' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS
					{
					string_literal280=(Token)match(input,73,FOLLOW_73_in_presetcondition2719);  
					stream_73.add(string_literal280);

					WhiteChar281=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2721);  
					stream_WhiteChar.add(WhiteChar281);

					pushFollow(FOLLOW_constant_in_presetcondition2725);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar282=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2727);  
					stream_WhiteChar.add(WhiteChar282);

					DAYS283=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2729);  
					stream_DAYS.add(DAYS283);

					WhiteChar284=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2736);  
					stream_WhiteChar.add(WhiteChar284);

					string_literal285=(Token)match(input,80,FOLLOW_80_in_presetcondition2738);  
					stream_80.add(string_literal285);

					WhiteChar286=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2740);  
					stream_WhiteChar.add(WhiteChar286);

					pushFollow(FOLLOW_constant_in_presetcondition2744);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar287=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2746);  
					stream_WhiteChar.add(WhiteChar287);

					DAYS288=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2748);  
					stream_DAYS.add(DAYS288);

					WhiteChar289=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2755);  
					stream_WhiteChar.add(WhiteChar289);

					string_literal290=(Token)match(input,79,FOLLOW_79_in_presetcondition2757);  
					stream_79.add(string_literal290);

					WhiteChar291=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2759);  
					stream_WhiteChar.add(WhiteChar291);

					pushFollow(FOLLOW_constant_in_presetcondition2763);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar292=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2765);  
					stream_WhiteChar.add(WhiteChar292);

					DAYS293=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2767);  
					stream_DAYS.add(DAYS293);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 276:4: -> ^( LowerLowCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:7: ^( LowerLowCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LowerLowCondition, "LowerLowCondition"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
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



	public static final BitSet FOLLOW_bullish_condition_in_complete_expression341 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000006L});
	public static final BitSet FOLLOW_bearish_condition_in_complete_expression343 = new BitSet(new long[]{0x0004000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_also_display_in_complete_expression346 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_fixed_start_shift_in_complete_expression348 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_bullish_condition378 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition380 = new BitSet(new long[]{0x0000000A10200000L});
	public static final BitSet FOLLOW_primary_expression_in_bullish_condition382 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition384 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bullish_condition387 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition389 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_66_in_bearish_condition405 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition407 = new BitSet(new long[]{0x0000000A10200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_condition410 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition412 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bearish_condition415 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition417 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_bearish_not_bullish_in_bearish_condition427 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition430 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bearish_condition433 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition435 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_50_in_also_display452 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display454 = new BitSet(new long[]{0x0000000A10200000L});
	public static final BitSet FOLLOW_primary_expression_in_also_display456 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display458 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_COMMA_in_also_display461 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_76_in_fixed_start_shift496 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift498 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_fixed_start_shift502 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift504 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_fixed_start_shift506 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_COMMA_in_fixed_start_shift508 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_bearish_not_bullish538 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish547 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_bearish_not_bullish549 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish551 = new BitSet(new long[]{0x0000000A10200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish553 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish581 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_OR_in_bearish_not_bullish583 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish585 = new BitSet(new long[]{0x0000000A10200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish588 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_and_expression_in_primary_expression630 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_expression_in_and_expression645 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_lenient_in_and_expression649 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression652 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_and_expression654 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression656 = new BitSet(new long[]{0x0000000A10200000L});
	public static final BitSet FOLLOW_or_expression_in_and_expression658 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_atom_in_or_expression690 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression693 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_OR_in_or_expression695 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression697 = new BitSet(new long[]{0x0000000A10200000L});
	public static final BitSet FOLLOW_atom_in_or_expression699 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_booleanhistory_in_atom726 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom733 = new BitSet(new long[]{0x0002000A10200000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom735 = new BitSet(new long[]{0x0002000A10200000L});
	public static final BitSet FOLLOW_primary_expression_in_atom738 = new BitSet(new long[]{0x0002000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom740 = new BitSet(new long[]{0x0002000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom743 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_atom755 = new BitSet(new long[]{0x0002000200000000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom757 = new BitSet(new long[]{0x0002000200000000L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom760 = new BitSet(new long[]{0x0002000A10200000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom762 = new BitSet(new long[]{0x0002000A10200000L});
	public static final BitSet FOLLOW_primary_expression_in_atom765 = new BitSet(new long[]{0x0002000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom767 = new BitSet(new long[]{0x0002000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom770 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_booleanhistory792 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_booleanhistory794 = new BitSet(new long[]{0xEFE0000000000000L,0x00000000000063D9L});
	public static final BitSet FOLLOW_presetcondition_in_booleanhistory798 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_opcmpcondition_in_booleanhistory807 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constantcmp_in_booleanhistory815 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_operand829 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Operation_in_operand856 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NumberToken_in_constant870 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_52_in_trendconstant886 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_51_in_trendconstant899 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_lenient916 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_LENIENT_in_lenient918 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_63_in_opcmpcondition954 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition956 = new BitSet(new long[]{0x0000000800200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition960 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition986 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_opcmpcondition988 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition990 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition994 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition996 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition998 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_67_in_opcmpcondition1020 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1022 = new BitSet(new long[]{0x0000000800200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1026 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1054 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_opcmpcondition1056 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1058 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1062 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1064 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1066 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_57_in_opcmpcondition1088 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1090 = new BitSet(new long[]{0x0000000800200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1094 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1121 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_opcmpcondition1123 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1125 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1129 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1131 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1133 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_53_in_opcmpcondition1156 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1158 = new BitSet(new long[]{0x0000000800200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1160 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1197 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_opcmpcondition1199 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1201 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1205 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1207 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1209 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1222 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition1224 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1226 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1230 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1232 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1234 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_55_in_opcmpcondition1270 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1272 = new BitSet(new long[]{0x0000000800200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1274 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1311 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_opcmpcondition1313 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1315 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1319 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1321 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1323 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1334 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition1336 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1338 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1342 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1344 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1346 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_59_in_constantcmp1388 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1390 = new BitSet(new long[]{0x0018000000000000L});
	public static final BitSet FOLLOW_trendconstant_in_constantcmp1394 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_constantcmp1430 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1432 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1436 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1438 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1440 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1442 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_constantcmp1444 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1446 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1450 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1452 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1454 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_58_in_constantcmp1482 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1484 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1488 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1522 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_constantcmp1524 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1526 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1530 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1532 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1534 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1536 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_constantcmp1538 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1540 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1544 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1546 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1548 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_64_in_constantcmp1577 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1579 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1583 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1617 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_constantcmp1619 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1621 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1625 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1627 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1629 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1631 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_constantcmp1633 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1635 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1639 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1641 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1643 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_68_in_constantcmp1672 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1674 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1678 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1712 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_constantcmp1714 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1716 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1720 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1722 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1724 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1726 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_constantcmp1728 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1730 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1734 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1736 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1738 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_77_in_presetcondition1772 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1812 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition1814 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1816 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1820 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition1822 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1824 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition1826 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1828 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1832 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1834 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1836 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_78_in_presetcondition1880 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1920 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition1922 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1924 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1928 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition1930 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1932 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition1934 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1936 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1940 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1942 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1944 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_61_in_presetcondition1987 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1989 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1993 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition1995 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2038 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition2040 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2042 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2046 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2048 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2050 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2061 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition2063 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2065 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2069 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2071 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2073 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_62_in_presetcondition2111 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2113 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2117 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2119 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2163 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition2165 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2167 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2171 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2173 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2175 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2186 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition2188 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2190 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2194 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2196 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2198 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_56_in_presetcondition2243 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2245 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2249 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2292 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition2294 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2296 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2300 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2302 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2304 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2315 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_presetcondition2317 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2319 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2323 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2325 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2327 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_54_in_presetcondition2373 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2375 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2379 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2421 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition2423 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2425 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2429 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2431 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2433 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2444 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_presetcondition2446 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2448 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2452 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2454 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2456 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_70_in_presetcondition2494 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2496 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2500 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2502 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2504 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2511 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition2513 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2515 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2519 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2521 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2523 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2530 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition2532 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2534 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2538 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2540 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2542 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_71_in_presetcondition2569 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2571 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2575 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2577 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2579 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2586 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition2588 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2590 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2594 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2596 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2598 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2605 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition2607 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2609 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2613 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2615 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2617 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_72_in_presetcondition2644 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2646 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2650 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2652 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2654 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2661 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition2663 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2665 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2669 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2671 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2673 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2680 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition2682 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2684 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2688 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2690 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2692 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_73_in_presetcondition2719 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2721 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2725 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2727 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2729 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2736 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition2738 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2740 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2744 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2746 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2748 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2755 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition2757 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2759 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2763 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2765 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2767 = new BitSet(new long[]{0x0000000000000002L});
}
