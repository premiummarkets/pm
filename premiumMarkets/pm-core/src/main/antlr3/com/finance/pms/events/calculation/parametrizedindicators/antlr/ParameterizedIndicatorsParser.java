// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2017-12-05 21:51:59
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
		"EqualEventMapStringConstantCondition", "HigherHighCondition", "HigherLowCondition", 
		"HistoricalData", "InfConstantCondition", "InfDoubleMapCondition", "LENIENT", 
		"LINE_COMMENT", "LowerHighCondition", "LowerLowCondition", "NOT", "NotDoubleMapCondition", 
		"NullCondition", "Number", "NumberToken", "OPENPARENTEHSIS", "OR", "Operation", 
		"OperationOutput", "OperationsCompositioner", "OrDoubleMapCondition", 
		"PERCENT", "ReverseCondition", "StockOperation", "String", "StringOperation", 
		"StringToken", "SupConstantCondition", "SupDoubleMapCondition", "Tcheat", 
		"UpRatioCondition", "WS", "WhiteChar", "'also display'", "'bearish'", 
		"'bullish'", "'crosses down historical'", "'crosses down threshold'", 
		"'crosses up historical'", "'crosses up threshold'", "'equals historical'", 
		"'equals threshold'", "'equals trend'", "'for'", "'goes down more than'", 
		"'goes up more than'", "'is above historical'", "'is above threshold'", 
		"'is bearish if not bullish'", "'is bearish when'", "'is below historical'", 
		"'is below threshold'", "'is bullish when'", "'makes a higher high spanning'", 
		"'makes a higher low spanning'", "'makes a lower high spanning'", "'makes a lower low spanning'", 
		"'more than'", "'over'", "'override start shift with'", "'reverses down'", 
		"'reverses up'", "'spanning'"
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
	public static final int EqualEventMapStringConstantCondition=17;
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
	public static final int OperationsCompositioner=36;
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:145:1: complete_expression : bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift -> ^( OperationsCompositioner bullish_condition bearish_condition also_display fixed_start_shift StringOperation ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:145:21: (bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift -> ^( OperationsCompositioner bullish_condition bearish_condition also_display fixed_start_shift StringOperation ) )
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
			// elements: bullish_condition, also_display, bearish_condition, fixed_start_shift
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 147:90: -> ^( OperationsCompositioner bullish_condition bearish_condition also_display fixed_start_shift StringOperation )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:147:93: ^( OperationsCompositioner bullish_condition bearish_condition also_display fixed_start_shift StringOperation )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationsCompositioner, "OperationsCompositioner"), root_1);
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
					// 191:78: -> presetcondition
					{
						adaptor.addChild(root_0, stream_presetcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:100: opcmpcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_opcmpcondition_in_booleanhistory809);
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
					// 191:132: -> opcmpcondition
					{
						adaptor.addChild(root_0, stream_opcmpcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:152: constantcmp[$firstOp.tree]
					{
					pushFollow(FOLLOW_constantcmp_in_booleanhistory820);
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
					// 191:181: -> constantcmp
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:1: operand : ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ) |opName= Operation -> Operation );
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:9: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ) |opName= Operation -> Operation )
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
					HistoricalData67=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_operand837);  
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
					// 192:26: -> ^( StockOperation ^( OperationOutput HistoricalData ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:29: ^( StockOperation ^( OperationOutput HistoricalData ) )
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

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:83: opName= Operation
					{
					opName=(Token)match(input,Operation,FOLLOW_Operation_in_operand857);  
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
					// 192:137: -> Operation
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
			NumberToken68=(Token)match(input,NumberToken,FOLLOW_NumberToken_in_constant871);  
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:1: trendconstant : ( 'bullish' | 'bearish' );
	public final ParameterizedIndicatorsParser.trendconstant_return trendconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.trendconstant_return retval = new ParameterizedIndicatorsParser.trendconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set69=null;

		CommonTree set69_tree=null;

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:15: ( 'bullish' | 'bearish' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set69=input.LT(1);
			if ( (input.LA(1) >= 51 && input.LA(1) <= 52) ) {
				input.consume();
				adaptor.addChild(root_0, (CommonTree)adaptor.create(set69));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
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

		Token WhiteChar70=null;
		Token LENIENT71=null;

		CommonTree WhiteChar70_tree=null;
		CommonTree LENIENT71_tree=null;
		RewriteRuleTokenStream stream_LENIENT=new RewriteRuleTokenStream(adaptor,"token LENIENT");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:9: ( ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			int alt22=2;
			int LA22_0 = input.LA(1);
			if ( (LA22_0==WhiteChar) ) {
				int LA22_1 = input.LA(2);
				if ( (LA22_1==LENIENT) ) {
					alt22=1;
				}
				else if ( (LA22_1==AND||(LA22_1 >= CLOSEPARENTEHSIS && LA22_1 <= COMMA)||LA22_1==WhiteChar) ) {
					alt22=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 22, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( ((LA22_0 >= CLOSEPARENTEHSIS && LA22_0 <= COMMA)) ) {
				alt22=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}

			switch (alt22) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:12: WhiteChar LENIENT
					{
					WhiteChar70=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_lenient900);  
					stream_WhiteChar.add(WhiteChar70);

					LENIENT71=(Token)match(input,LENIENT,FOLLOW_LENIENT_in_lenient902);  
					stream_LENIENT.add(LENIENT71);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 195:31: -> ^( String StringToken[\"\\\"TRUE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:34: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:70: 
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
					// 195:70: -> ^( String StringToken[\"\\\"FALSE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:73: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
		Token WhiteChar88=null;
		Token string_literal89=null;
		Token WhiteChar90=null;
		Token WhiteChar91=null;
		Token DAYS92=null;
		Token string_literal93=null;
		Token WhiteChar94=null;
		Token WhiteChar96=null;
		Token string_literal97=null;
		Token WhiteChar98=null;
		Token WhiteChar99=null;
		Token DAYS100=null;
		Token WhiteChar101=null;
		Token string_literal102=null;
		Token WhiteChar103=null;
		Token WhiteChar104=null;
		Token DAYS105=null;
		Token string_literal106=null;
		Token WhiteChar107=null;
		Token WhiteChar109=null;
		Token string_literal110=null;
		Token WhiteChar111=null;
		Token WhiteChar112=null;
		Token DAYS113=null;
		Token WhiteChar114=null;
		Token string_literal115=null;
		Token WhiteChar116=null;
		Token WhiteChar117=null;
		Token DAYS118=null;
		ParserRuleReturnScope secondOp =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope spanningNbDays =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope operand95 =null;
		ParserRuleReturnScope operand108 =null;

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
		CommonTree WhiteChar88_tree=null;
		CommonTree string_literal89_tree=null;
		CommonTree WhiteChar90_tree=null;
		CommonTree WhiteChar91_tree=null;
		CommonTree DAYS92_tree=null;
		CommonTree string_literal93_tree=null;
		CommonTree WhiteChar94_tree=null;
		CommonTree WhiteChar96_tree=null;
		CommonTree string_literal97_tree=null;
		CommonTree WhiteChar98_tree=null;
		CommonTree WhiteChar99_tree=null;
		CommonTree DAYS100_tree=null;
		CommonTree WhiteChar101_tree=null;
		CommonTree string_literal102_tree=null;
		CommonTree WhiteChar103_tree=null;
		CommonTree WhiteChar104_tree=null;
		CommonTree DAYS105_tree=null;
		CommonTree string_literal106_tree=null;
		CommonTree WhiteChar107_tree=null;
		CommonTree WhiteChar109_tree=null;
		CommonTree string_literal110_tree=null;
		CommonTree WhiteChar111_tree=null;
		CommonTree WhiteChar112_tree=null;
		CommonTree DAYS113_tree=null;
		CommonTree WhiteChar114_tree=null;
		CommonTree string_literal115_tree=null;
		CommonTree WhiteChar116_tree=null;
		CommonTree WhiteChar117_tree=null;
		CommonTree DAYS118_tree=null;
		RewriteRuleTokenStream stream_55=new RewriteRuleTokenStream(adaptor,"token 55");
		RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
		RewriteRuleTokenStream stream_57=new RewriteRuleTokenStream(adaptor,"token 57");
		RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
		RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
		RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
		RewriteRuleTokenStream stream_53=new RewriteRuleTokenStream(adaptor,"token 53");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:37: ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )? )
			int alt28=5;
			switch ( input.LA(1) ) {
			case 63:
				{
				alt28=1;
				}
				break;
			case 67:
				{
				alt28=2;
				}
				break;
			case 57:
				{
				alt28=3;
				}
				break;
			case 53:
				{
				alt28=4;
				}
				break;
			case 55:
				{
				alt28=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 28, 0, input);
				throw nvae;
			}
			switch (alt28) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:4: 'is above historical' WhiteChar secondOp= operand
					{
					string_literal72=(Token)match(input,63,FOLLOW_63_in_opcmpcondition940);  
					stream_63.add(string_literal72);

					WhiteChar73=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition942);  
					stream_WhiteChar.add(WhiteChar73);

					pushFollow(FOLLOW_operand_in_opcmpcondition946);
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
					int alt23=2;
					int LA23_0 = input.LA(1);
					if ( (LA23_0==WhiteChar) ) {
						int LA23_1 = input.LA(2);
						if ( (LA23_1==60) ) {
							alt23=1;
						}
					}
					switch (alt23) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:6: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar74=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition972);  
							stream_WhiteChar.add(WhiteChar74);

							string_literal75=(Token)match(input,60,FOLLOW_60_in_opcmpcondition974);  
							stream_60.add(string_literal75);

							WhiteChar76=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition976);  
							stream_WhiteChar.add(WhiteChar76);

							pushFollow(FOLLOW_constant_in_opcmpcondition980);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar77=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition982);  
							stream_WhiteChar.add(WhiteChar77);

							DAYS78=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition984);  
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
					string_literal79=(Token)match(input,67,FOLLOW_67_in_opcmpcondition1006);  
					stream_67.add(string_literal79);

					WhiteChar80=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1008);  
					stream_WhiteChar.add(WhiteChar80);

					pushFollow(FOLLOW_operand_in_opcmpcondition1012);
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar81=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1040);  
							stream_WhiteChar.add(WhiteChar81);

							string_literal82=(Token)match(input,60,FOLLOW_60_in_opcmpcondition1042);  
							stream_60.add(string_literal82);

							WhiteChar83=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1044);  
							stream_WhiteChar.add(WhiteChar83);

							pushFollow(FOLLOW_constant_in_opcmpcondition1048);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar84=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1050);  
							stream_WhiteChar.add(WhiteChar84);

							DAYS85=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1052);  
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
					string_literal86=(Token)match(input,57,FOLLOW_57_in_opcmpcondition1074);  
					stream_57.add(string_literal86);

					WhiteChar87=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1076);  
					stream_WhiteChar.add(WhiteChar87);

					pushFollow(FOLLOW_operand_in_opcmpcondition1080);
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar88=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1107);  
							stream_WhiteChar.add(WhiteChar88);

							string_literal89=(Token)match(input,60,FOLLOW_60_in_opcmpcondition1109);  
							stream_60.add(string_literal89);

							WhiteChar90=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1111);  
							stream_WhiteChar.add(WhiteChar90);

							pushFollow(FOLLOW_constant_in_opcmpcondition1115);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar91=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1117);  
							stream_WhiteChar.add(WhiteChar91);

							DAYS92=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1119);  
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
					string_literal93=(Token)match(input,53,FOLLOW_53_in_opcmpcondition1142);  
					stream_53.add(string_literal93);

					WhiteChar94=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1144);  
					stream_WhiteChar.add(WhiteChar94);

					pushFollow(FOLLOW_operand_in_opcmpcondition1146);
					operand95=operand();
					state._fsp--;

					stream_operand.add(operand95.getTree());
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
					int alt26=2;
					int LA26_0 = input.LA(1);
					if ( (LA26_0==WhiteChar) ) {
						int LA26_1 = input.LA(2);
						if ( (LA26_1==79) ) {
							alt26=1;
						}
					}
					switch (alt26) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:11: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar96=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1183);  
							stream_WhiteChar.add(WhiteChar96);

							string_literal97=(Token)match(input,79,FOLLOW_79_in_opcmpcondition1185);  
							stream_79.add(string_literal97);

							WhiteChar98=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1187);  
							stream_WhiteChar.add(WhiteChar98);

							pushFollow(FOLLOW_constant_in_opcmpcondition1191);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar99=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1193);  
							stream_WhiteChar.add(WhiteChar99);

							DAYS100=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1195);  
							stream_DAYS.add(DAYS100);

							WhiteChar101=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1208);  
							stream_WhiteChar.add(WhiteChar101);

							string_literal102=(Token)match(input,75,FOLLOW_75_in_opcmpcondition1210);  
							stream_75.add(string_literal102);

							WhiteChar103=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1212);  
							stream_WhiteChar.add(WhiteChar103);

							pushFollow(FOLLOW_constant_in_opcmpcondition1216);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar104=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1218);  
							stream_WhiteChar.add(WhiteChar104);

							DAYS105=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1220);  
							stream_DAYS.add(DAYS105);

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
					string_literal106=(Token)match(input,55,FOLLOW_55_in_opcmpcondition1264);  
					stream_55.add(string_literal106);

					WhiteChar107=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1266);  
					stream_WhiteChar.add(WhiteChar107);

					pushFollow(FOLLOW_operand_in_opcmpcondition1268);
					operand108=operand();
					state._fsp--;

					stream_operand.add(operand108.getTree());
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
					int alt27=2;
					int LA27_0 = input.LA(1);
					if ( (LA27_0==WhiteChar) ) {
						int LA27_1 = input.LA(2);
						if ( (LA27_1==79) ) {
							alt27=1;
						}
					}
					switch (alt27) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:10: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar109=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1306);  
							stream_WhiteChar.add(WhiteChar109);

							string_literal110=(Token)match(input,79,FOLLOW_79_in_opcmpcondition1308);  
							stream_79.add(string_literal110);

							WhiteChar111=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1310);  
							stream_WhiteChar.add(WhiteChar111);

							pushFollow(FOLLOW_constant_in_opcmpcondition1314);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar112=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1316);  
							stream_WhiteChar.add(WhiteChar112);

							DAYS113=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1318);  
							stream_DAYS.add(DAYS113);

							WhiteChar114=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1329);  
							stream_WhiteChar.add(WhiteChar114);

							string_literal115=(Token)match(input,75,FOLLOW_75_in_opcmpcondition1331);  
							stream_75.add(string_literal115);

							WhiteChar116=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1333);  
							stream_WhiteChar.add(WhiteChar116);

							pushFollow(FOLLOW_constant_in_opcmpcondition1337);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar117=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1339);  
							stream_WhiteChar.add(WhiteChar117);

							DAYS118=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1341);  
							stream_DAYS.add(DAYS118);

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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:1: constantcmp[CommonTree firstOp] : ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualEventMapStringConstantCondition ^( String trendconstant ) ) ) | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )? );
	public final ParameterizedIndicatorsParser.constantcmp_return constantcmp(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.constantcmp_return retval = new ParameterizedIndicatorsParser.constantcmp_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal119=null;
		Token WhiteChar120=null;
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
		ParserRuleReturnScope trendSignal =null;
		ParserRuleReturnScope threshold =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope forNbDays =null;

		CommonTree string_literal119_tree=null;
		CommonTree WhiteChar120_tree=null;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:34: ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualEventMapStringConstantCondition ^( String trendconstant ) ) ) | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )? )
			int alt32=4;
			switch ( input.LA(1) ) {
			case 59:
				{
				alt32=1;
				}
				break;
			case 58:
				{
				alt32=2;
				}
				break;
			case 64:
				{
				alt32=3;
				}
				break;
			case 68:
				{
				alt32=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 32, 0, input);
				throw nvae;
			}
			switch (alt32) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualEventMapStringConstantCondition ^( String trendconstant ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualEventMapStringConstantCondition ^( String trendconstant ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:4: 'equals trend' WhiteChar trendSignal= trendconstant
					{
					string_literal119=(Token)match(input,59,FOLLOW_59_in_constantcmp1391);  
					stream_59.add(string_literal119);

					WhiteChar120=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1393);  
					stream_WhiteChar.add(WhiteChar120);

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
					// 219:55: -> ^( EqualEventMapStringConstantCondition ^( String trendconstant ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:58: ^( EqualEventMapStringConstantCondition ^( String trendconstant ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualEventMapStringConstantCondition, "EqualEventMapStringConstantCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:97: ^( String trendconstant )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, stream_trendconstant.nextTree());
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
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:4: 'equals threshold' WhiteChar threshold= constant
					{
					string_literal121=(Token)match(input,58,FOLLOW_58_in_constantcmp1425);  
					stream_58.add(string_literal121);

					WhiteChar122=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1427);  
					stream_WhiteChar.add(WhiteChar122);

					pushFollow(FOLLOW_constant_in_constantcmp1431);
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
					// 221:52: -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:55: ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualConstantCondition, "EqualConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:117: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )?
					int alt29=2;
					int LA29_0 = input.LA(1);
					if ( (LA29_0==WhiteChar) ) {
						int LA29_1 = input.LA(2);
						if ( (LA29_1==75) ) {
							alt29=1;
						}
					}
					switch (alt29) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar123=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1466);  
							stream_WhiteChar.add(WhiteChar123);

							string_literal124=(Token)match(input,75,FOLLOW_75_in_constantcmp1468);  
							stream_75.add(string_literal124);

							WhiteChar125=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1470);  
							stream_WhiteChar.add(WhiteChar125);

							pushFollow(FOLLOW_constant_in_constantcmp1474);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar126=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1476);  
							stream_WhiteChar.add(WhiteChar126);

							DAYS127=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1478);  
							stream_DAYS.add(DAYS127);

							WhiteChar128=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1480);  
							stream_WhiteChar.add(WhiteChar128);

							string_literal129=(Token)match(input,60,FOLLOW_60_in_constantcmp1482);  
							stream_60.add(string_literal129);

							WhiteChar130=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1484);  
							stream_WhiteChar.add(WhiteChar130);

							pushFollow(FOLLOW_constant_in_constantcmp1488);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar131=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1490);  
							stream_WhiteChar.add(WhiteChar131);

							DAYS132=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1492);  
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
							// 222:129: -> ^( EqualConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:132: ^( EqualConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:4: 'is above threshold' WhiteChar threshold= constant
					{
					string_literal133=(Token)match(input,64,FOLLOW_64_in_constantcmp1521);  
					stream_64.add(string_literal133);

					WhiteChar134=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1523);  
					stream_WhiteChar.add(WhiteChar134);

					pushFollow(FOLLOW_constant_in_constantcmp1527);
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
					// 224:54: -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:57: ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupConstantCondition, "SupConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:224:116: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar135=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1561);  
							stream_WhiteChar.add(WhiteChar135);

							string_literal136=(Token)match(input,75,FOLLOW_75_in_constantcmp1563);  
							stream_75.add(string_literal136);

							WhiteChar137=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1565);  
							stream_WhiteChar.add(WhiteChar137);

							pushFollow(FOLLOW_constant_in_constantcmp1569);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar138=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1571);  
							stream_WhiteChar.add(WhiteChar138);

							DAYS139=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1573);  
							stream_DAYS.add(DAYS139);

							WhiteChar140=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1575);  
							stream_WhiteChar.add(WhiteChar140);

							string_literal141=(Token)match(input,60,FOLLOW_60_in_constantcmp1577);  
							stream_60.add(string_literal141);

							WhiteChar142=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1579);  
							stream_WhiteChar.add(WhiteChar142);

							pushFollow(FOLLOW_constant_in_constantcmp1583);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar143=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1585);  
							stream_WhiteChar.add(WhiteChar143);

							DAYS144=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1587);  
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
							// 225:129: -> ^( SupConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:132: ^( SupConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:4: 'is below threshold' WhiteChar threshold= constant
					{
					string_literal145=(Token)match(input,68,FOLLOW_68_in_constantcmp1616);  
					stream_68.add(string_literal145);

					WhiteChar146=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1618);  
					stream_WhiteChar.add(WhiteChar146);

					pushFollow(FOLLOW_constant_in_constantcmp1622);
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
					// 227:54: -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:57: ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfConstantCondition, "InfConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:117: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar147=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1657);  
							stream_WhiteChar.add(WhiteChar147);

							string_literal148=(Token)match(input,75,FOLLOW_75_in_constantcmp1659);  
							stream_75.add(string_literal148);

							WhiteChar149=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1661);  
							stream_WhiteChar.add(WhiteChar149);

							pushFollow(FOLLOW_constant_in_constantcmp1665);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar150=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1667);  
							stream_WhiteChar.add(WhiteChar150);

							DAYS151=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1669);  
							stream_DAYS.add(DAYS151);

							WhiteChar152=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1671);  
							stream_WhiteChar.add(WhiteChar152);

							string_literal153=(Token)match(input,60,FOLLOW_60_in_constantcmp1673);  
							stream_60.add(string_literal153);

							WhiteChar154=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1675);  
							stream_WhiteChar.add(WhiteChar154);

							pushFollow(FOLLOW_constant_in_constantcmp1679);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar155=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1681);  
							stream_WhiteChar.add(WhiteChar155);

							DAYS156=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1683);  
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
							// 228:129: -> ^( InfConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:228:132: ^( InfConstantCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:1: presetcondition[CommonTree firstOp] : ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) ) );
	public final ParameterizedIndicatorsParser.presetcondition_return presetcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.presetcondition_return retval = new ParameterizedIndicatorsParser.presetcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal157=null;
		Token WhiteChar158=null;
		Token string_literal159=null;
		Token WhiteChar160=null;
		Token PERCENT161=null;
		Token WhiteChar162=null;
		Token string_literal163=null;
		Token WhiteChar164=null;
		Token WhiteChar165=null;
		Token DAYS166=null;
		Token string_literal167=null;
		Token WhiteChar168=null;
		Token string_literal169=null;
		Token WhiteChar170=null;
		Token PERCENT171=null;
		Token WhiteChar172=null;
		Token string_literal173=null;
		Token WhiteChar174=null;
		Token WhiteChar175=null;
		Token DAYS176=null;
		Token string_literal177=null;
		Token WhiteChar178=null;
		Token PERCENT179=null;
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
		Token PERCENT192=null;
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
		Token string_literal203=null;
		Token WhiteChar204=null;
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
		Token DAYS230=null;
		Token string_literal231=null;
		Token WhiteChar232=null;
		Token WhiteChar233=null;
		Token DAYS234=null;
		Token string_literal235=null;
		Token WhiteChar236=null;
		Token WhiteChar237=null;
		Token DAYS238=null;
		Token string_literal239=null;
		Token WhiteChar240=null;
		Token WhiteChar241=null;
		Token DAYS242=null;
		ParserRuleReturnScope percentdown =null;
		ParserRuleReturnScope spanningNbDays =null;
		ParserRuleReturnScope percentup =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope threshold =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope nbDays =null;

		CommonTree string_literal157_tree=null;
		CommonTree WhiteChar158_tree=null;
		CommonTree string_literal159_tree=null;
		CommonTree WhiteChar160_tree=null;
		CommonTree PERCENT161_tree=null;
		CommonTree WhiteChar162_tree=null;
		CommonTree string_literal163_tree=null;
		CommonTree WhiteChar164_tree=null;
		CommonTree WhiteChar165_tree=null;
		CommonTree DAYS166_tree=null;
		CommonTree string_literal167_tree=null;
		CommonTree WhiteChar168_tree=null;
		CommonTree string_literal169_tree=null;
		CommonTree WhiteChar170_tree=null;
		CommonTree PERCENT171_tree=null;
		CommonTree WhiteChar172_tree=null;
		CommonTree string_literal173_tree=null;
		CommonTree WhiteChar174_tree=null;
		CommonTree WhiteChar175_tree=null;
		CommonTree DAYS176_tree=null;
		CommonTree string_literal177_tree=null;
		CommonTree WhiteChar178_tree=null;
		CommonTree PERCENT179_tree=null;
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
		CommonTree PERCENT192_tree=null;
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
		CommonTree string_literal203_tree=null;
		CommonTree WhiteChar204_tree=null;
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
		CommonTree DAYS230_tree=null;
		CommonTree string_literal231_tree=null;
		CommonTree WhiteChar232_tree=null;
		CommonTree WhiteChar233_tree=null;
		CommonTree DAYS234_tree=null;
		CommonTree string_literal235_tree=null;
		CommonTree WhiteChar236_tree=null;
		CommonTree WhiteChar237_tree=null;
		CommonTree DAYS238_tree=null;
		CommonTree string_literal239_tree=null;
		CommonTree WhiteChar240_tree=null;
		CommonTree WhiteChar241_tree=null;
		CommonTree DAYS242_tree=null;
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
		RewriteRuleTokenStream stream_56=new RewriteRuleTokenStream(adaptor,"token 56");
		RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:39: ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) ) )
			int alt39=10;
			switch ( input.LA(1) ) {
			case 77:
				{
				alt39=1;
				}
				break;
			case 78:
				{
				alt39=2;
				}
				break;
			case 61:
				{
				alt39=3;
				}
				break;
			case 62:
				{
				alt39=4;
				}
				break;
			case 56:
				{
				alt39=5;
				}
				break;
			case 54:
				{
				alt39=6;
				}
				break;
			case 70:
				{
				alt39=7;
				}
				break;
			case 71:
				{
				alt39=8;
				}
				break;
			case 72:
				{
				alt39=9;
				}
				break;
			case 73:
				{
				alt39=10;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 39, 0, input);
				throw nvae;
			}
			switch (alt39) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:4: 'reverses down'
					{
					string_literal157=(Token)match(input,77,FOLLOW_77_in_presetcondition1721);  
					stream_77.add(string_literal157);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 233:20: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:23: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:42: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:70: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:99: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:7: ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					int alt33=2;
					int LA33_0 = input.LA(1);
					if ( (LA33_0==WhiteChar) ) {
						int LA33_1 = input.LA(2);
						if ( (LA33_1==74) ) {
							alt33=1;
						}
					}
					switch (alt33) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:9: WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar158=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1761);  
							stream_WhiteChar.add(WhiteChar158);

							string_literal159=(Token)match(input,74,FOLLOW_74_in_presetcondition1763);  
							stream_74.add(string_literal159);

							WhiteChar160=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1765);  
							stream_WhiteChar.add(WhiteChar160);

							pushFollow(FOLLOW_constant_in_presetcondition1769);
							percentdown=constant();
							state._fsp--;

							stream_constant.add(percentdown.getTree());
							PERCENT161=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition1771);  
							stream_PERCENT.add(PERCENT161);

							WhiteChar162=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1773);  
							stream_WhiteChar.add(WhiteChar162);

							string_literal163=(Token)match(input,79,FOLLOW_79_in_presetcondition1775);  
							stream_79.add(string_literal163);

							WhiteChar164=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1777);  
							stream_WhiteChar.add(WhiteChar164);

							pushFollow(FOLLOW_constant_in_presetcondition1781);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar165=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1783);  
							stream_WhiteChar.add(WhiteChar165);

							DAYS166=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1785);  
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
							// 235:7: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:10: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:29: ^( Number NumberToken[\"-1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:4: 'reverses up'
					{
					string_literal167=(Token)match(input,78,FOLLOW_78_in_presetcondition1829);  
					stream_78.add(string_literal167);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 237:18: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:21: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:40: ^( Number NumberToken[\"1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:67: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:96: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:7: ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:9: WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar168=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1869);  
							stream_WhiteChar.add(WhiteChar168);

							string_literal169=(Token)match(input,74,FOLLOW_74_in_presetcondition1871);  
							stream_74.add(string_literal169);

							WhiteChar170=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1873);  
							stream_WhiteChar.add(WhiteChar170);

							pushFollow(FOLLOW_constant_in_presetcondition1877);
							percentup=constant();
							state._fsp--;

							stream_constant.add(percentup.getTree());
							PERCENT171=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition1879);  
							stream_PERCENT.add(PERCENT171);

							WhiteChar172=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1881);  
							stream_WhiteChar.add(WhiteChar172);

							string_literal173=(Token)match(input,79,FOLLOW_79_in_presetcondition1883);  
							stream_79.add(string_literal173);

							WhiteChar174=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1885);  
							stream_WhiteChar.add(WhiteChar174);

							pushFollow(FOLLOW_constant_in_presetcondition1889);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar175=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1891);  
							stream_WhiteChar.add(WhiteChar175);

							DAYS176=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1893);  
							stream_DAYS.add(DAYS176);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 239:7: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:10: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:29: ^( Number NumberToken[\"1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:4: 'goes down more than' WhiteChar percentdown= constant PERCENT
					{
					string_literal177=(Token)match(input,61,FOLLOW_61_in_presetcondition1936);  
					stream_61.add(string_literal177);

					WhiteChar178=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1938);  
					stream_WhiteChar.add(WhiteChar178);

					pushFollow(FOLLOW_constant_in_presetcondition1942);
					percentdown=constant();
					state._fsp--;

					stream_constant.add(percentdown.getTree());
					PERCENT179=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition1944);  
					stream_PERCENT.add(PERCENT179);

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
					// 241:65: -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:68: ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:98: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:127: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:156: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt35=2;
					int LA35_0 = input.LA(1);
					if ( (LA35_0==WhiteChar) ) {
						int LA35_1 = input.LA(2);
						if ( (LA35_1==79) ) {
							alt35=1;
						}
					}
					switch (alt35) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:242:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar180=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1987);  
							stream_WhiteChar.add(WhiteChar180);

							string_literal181=(Token)match(input,79,FOLLOW_79_in_presetcondition1989);  
							stream_79.add(string_literal181);

							WhiteChar182=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1991);  
							stream_WhiteChar.add(WhiteChar182);

							pushFollow(FOLLOW_constant_in_presetcondition1995);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar183=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition1997);  
							stream_WhiteChar.add(WhiteChar183);

							DAYS184=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition1999);  
							stream_DAYS.add(DAYS184);

							WhiteChar185=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2010);  
							stream_WhiteChar.add(WhiteChar185);

							string_literal186=(Token)match(input,60,FOLLOW_60_in_presetcondition2012);  
							stream_60.add(string_literal186);

							WhiteChar187=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2014);  
							stream_WhiteChar.add(WhiteChar187);

							pushFollow(FOLLOW_constant_in_presetcondition2018);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar188=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2020);  
							stream_WhiteChar.add(WhiteChar188);

							DAYS189=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2022);  
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
							// 244:7: -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:10: ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentdown!=null?((CommonTree)percentdown.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:75: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:4: 'goes up more than' WhiteChar percentup= constant PERCENT
					{
					string_literal190=(Token)match(input,62,FOLLOW_62_in_presetcondition2060);  
					stream_62.add(string_literal190);

					WhiteChar191=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2062);  
					stream_WhiteChar.add(WhiteChar191);

					pushFollow(FOLLOW_constant_in_presetcondition2066);
					percentup=constant();
					state._fsp--;

					stream_constant.add(percentup.getTree());
					PERCENT192=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2068);  
					stream_PERCENT.add(PERCENT192);

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
					// 245:61: -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:64: ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:92: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:122: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:245:151: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar193=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2112);  
							stream_WhiteChar.add(WhiteChar193);

							string_literal194=(Token)match(input,79,FOLLOW_79_in_presetcondition2114);  
							stream_79.add(string_literal194);

							WhiteChar195=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2116);  
							stream_WhiteChar.add(WhiteChar195);

							pushFollow(FOLLOW_constant_in_presetcondition2120);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar196=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2122);  
							stream_WhiteChar.add(WhiteChar196);

							DAYS197=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2124);  
							stream_DAYS.add(DAYS197);

							WhiteChar198=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2135);  
							stream_WhiteChar.add(WhiteChar198);

							string_literal199=(Token)match(input,60,FOLLOW_60_in_presetcondition2137);  
							stream_60.add(string_literal199);

							WhiteChar200=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2139);  
							stream_WhiteChar.add(WhiteChar200);

							pushFollow(FOLLOW_constant_in_presetcondition2143);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar201=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2145);  
							stream_WhiteChar.add(WhiteChar201);

							DAYS202=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2147);  
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
							// 248:7: -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:10: ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentup!=null?((CommonTree)percentup.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:71: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:4: 'crosses up threshold' WhiteChar threshold= constant
					{
					string_literal203=(Token)match(input,56,FOLLOW_56_in_presetcondition2192);  
					stream_56.add(string_literal203);

					WhiteChar204=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2194);  
					stream_WhiteChar.add(WhiteChar204);

					pushFollow(FOLLOW_constant_in_presetcondition2198);
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
					// 250:56: -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:59: ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:95: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:125: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:154: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar205=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2241);  
							stream_WhiteChar.add(WhiteChar205);

							string_literal206=(Token)match(input,79,FOLLOW_79_in_presetcondition2243);  
							stream_79.add(string_literal206);

							WhiteChar207=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2245);  
							stream_WhiteChar.add(WhiteChar207);

							pushFollow(FOLLOW_constant_in_presetcondition2249);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar208=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2251);  
							stream_WhiteChar.add(WhiteChar208);

							DAYS209=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2253);  
							stream_DAYS.add(DAYS209);

							WhiteChar210=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2264);  
							stream_WhiteChar.add(WhiteChar210);

							string_literal211=(Token)match(input,75,FOLLOW_75_in_presetcondition2266);  
							stream_75.add(string_literal211);

							WhiteChar212=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2268);  
							stream_WhiteChar.add(WhiteChar212);

							pushFollow(FOLLOW_constant_in_presetcondition2272);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar213=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2274);  
							stream_WhiteChar.add(WhiteChar213);

							DAYS214=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2276);  
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
							// 253:7: -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:10: ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:97: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:4: 'crosses down threshold' WhiteChar threshold= constant
					{
					string_literal215=(Token)match(input,54,FOLLOW_54_in_presetcondition2322);  
					stream_54.add(string_literal215);

					WhiteChar216=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2324);  
					stream_WhiteChar.add(WhiteChar216);

					pushFollow(FOLLOW_constant_in_presetcondition2328);
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
					// 255:58: -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:61: ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:99: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:128: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:157: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt38=2;
					int LA38_0 = input.LA(1);
					if ( (LA38_0==WhiteChar) ) {
						int LA38_1 = input.LA(2);
						if ( (LA38_1==79) ) {
							alt38=1;
						}
					}
					switch (alt38) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar217=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2370);  
							stream_WhiteChar.add(WhiteChar217);

							string_literal218=(Token)match(input,79,FOLLOW_79_in_presetcondition2372);  
							stream_79.add(string_literal218);

							WhiteChar219=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2374);  
							stream_WhiteChar.add(WhiteChar219);

							pushFollow(FOLLOW_constant_in_presetcondition2378);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar220=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2380);  
							stream_WhiteChar.add(WhiteChar220);

							DAYS221=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2382);  
							stream_DAYS.add(DAYS221);

							WhiteChar222=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2393);  
							stream_WhiteChar.add(WhiteChar222);

							string_literal223=(Token)match(input,75,FOLLOW_75_in_presetcondition2395);  
							stream_75.add(string_literal223);

							WhiteChar224=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2397);  
							stream_WhiteChar.add(WhiteChar224);

							pushFollow(FOLLOW_constant_in_presetcondition2401);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar225=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2403);  
							stream_WhiteChar.add(WhiteChar225);

							DAYS226=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2405);  
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
							// 258:7: -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:10: ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:99: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:3: ( 'makes a higher high spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:3: ( 'makes a higher high spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:4: 'makes a higher high spanning' WhiteChar nbDays= constant WhiteChar DAYS
					{
					string_literal227=(Token)match(input,70,FOLLOW_70_in_presetcondition2449);  
					stream_70.add(string_literal227);

					WhiteChar228=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2451);  
					stream_WhiteChar.add(WhiteChar228);

					pushFollow(FOLLOW_constant_in_presetcondition2455);
					nbDays=constant();
					state._fsp--;

					stream_constant.add(nbDays.getTree());
					WhiteChar229=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2457);  
					stream_WhiteChar.add(WhiteChar229);

					DAYS230=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2459);  
					stream_DAYS.add(DAYS230);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 260:76: -> ^( HigherHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:79: ^( HigherHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(HigherHighCondition, "HigherHighCondition"), root_1);
						adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:116: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:144: ^( Number NumberToken[\"-1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:3: ( 'makes a higher low spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:3: ( 'makes a higher low spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( HigherLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:4: 'makes a higher low spanning' WhiteChar nbDays= constant WhiteChar DAYS
					{
					string_literal231=(Token)match(input,71,FOLLOW_71_in_presetcondition2491);  
					stream_71.add(string_literal231);

					WhiteChar232=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2493);  
					stream_WhiteChar.add(WhiteChar232);

					pushFollow(FOLLOW_constant_in_presetcondition2497);
					nbDays=constant();
					state._fsp--;

					stream_constant.add(nbDays.getTree());
					WhiteChar233=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2499);  
					stream_WhiteChar.add(WhiteChar233);

					DAYS234=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2501);  
					stream_DAYS.add(DAYS234);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 261:75: -> ^( HigherLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:78: ^( HigherLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(HigherLowCondition, "HigherLowCondition"), root_1);
						adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:114: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:142: ^( Number NumberToken[\"-1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:3: ( 'makes a lower high spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:3: ( 'makes a lower high spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:4: 'makes a lower high spanning' WhiteChar nbDays= constant WhiteChar DAYS
					{
					string_literal235=(Token)match(input,72,FOLLOW_72_in_presetcondition2533);  
					stream_72.add(string_literal235);

					WhiteChar236=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2535);  
					stream_WhiteChar.add(WhiteChar236);

					pushFollow(FOLLOW_constant_in_presetcondition2539);
					nbDays=constant();
					state._fsp--;

					stream_constant.add(nbDays.getTree());
					WhiteChar237=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2541);  
					stream_WhiteChar.add(WhiteChar237);

					DAYS238=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2543);  
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
					// 262:75: -> ^( LowerHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:78: ^( LowerHighCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LowerHighCondition, "LowerHighCondition"), root_1);
						adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:114: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:142: ^( Number NumberToken[\"-1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:3: ( 'makes a lower low spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:3: ( 'makes a lower low spanning' WhiteChar nbDays= constant WhiteChar DAYS -> ^( LowerLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:4: 'makes a lower low spanning' WhiteChar nbDays= constant WhiteChar DAYS
					{
					string_literal239=(Token)match(input,73,FOLLOW_73_in_presetcondition2575);  
					stream_73.add(string_literal239);

					WhiteChar240=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2577);  
					stream_WhiteChar.add(WhiteChar240);

					pushFollow(FOLLOW_constant_in_presetcondition2581);
					nbDays=constant();
					state._fsp--;

					stream_constant.add(nbDays.getTree());
					WhiteChar241=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2583);  
					stream_WhiteChar.add(WhiteChar241);

					DAYS242=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2585);  
					stream_DAYS.add(DAYS242);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 263:74: -> ^( LowerLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:77: ^( LowerLowCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"-1\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LowerLowCondition, "LowerLowCondition"), root_1);
						adaptor.addChild(root_1, (nbDays!=null?((CommonTree)nbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:112: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:140: ^( Number NumberToken[\"-1\"] )
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



	public static final BitSet FOLLOW_bullish_condition_in_complete_expression341 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000006L});
	public static final BitSet FOLLOW_bearish_condition_in_complete_expression343 = new BitSet(new long[]{0x0004000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_also_display_in_complete_expression346 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_fixed_start_shift_in_complete_expression348 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_bullish_condition378 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition380 = new BitSet(new long[]{0x0000000508100000L});
	public static final BitSet FOLLOW_primary_expression_in_bullish_condition382 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition384 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bullish_condition387 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition389 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_66_in_bearish_condition405 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition407 = new BitSet(new long[]{0x0000000508100000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_condition410 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition412 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bearish_condition415 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition417 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_bearish_not_bullish_in_bearish_condition427 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition430 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bearish_condition433 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition435 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_50_in_also_display452 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display454 = new BitSet(new long[]{0x0000000508100000L});
	public static final BitSet FOLLOW_primary_expression_in_also_display456 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display458 = new BitSet(new long[]{0x0002000000000080L});
	public static final BitSet FOLLOW_COMMA_in_also_display461 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_76_in_fixed_start_shift496 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift498 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_fixed_start_shift502 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift504 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_fixed_start_shift506 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_COMMA_in_fixed_start_shift508 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_bearish_not_bullish538 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish547 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_bearish_not_bullish549 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish551 = new BitSet(new long[]{0x0000000508100000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish553 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish581 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_OR_in_bearish_not_bullish583 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish585 = new BitSet(new long[]{0x0000000508100000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish588 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_and_expression_in_primary_expression630 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_expression_in_and_expression645 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_lenient_in_and_expression649 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression652 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_and_expression654 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression656 = new BitSet(new long[]{0x0000000508100000L});
	public static final BitSet FOLLOW_or_expression_in_and_expression658 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_atom_in_or_expression690 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression693 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_OR_in_or_expression695 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression697 = new BitSet(new long[]{0x0000000508100000L});
	public static final BitSet FOLLOW_atom_in_or_expression699 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_booleanhistory_in_atom726 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom733 = new BitSet(new long[]{0x0002000508100000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom735 = new BitSet(new long[]{0x0002000508100000L});
	public static final BitSet FOLLOW_primary_expression_in_atom738 = new BitSet(new long[]{0x0002000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom740 = new BitSet(new long[]{0x0002000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom743 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_atom755 = new BitSet(new long[]{0x0002000100000000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom757 = new BitSet(new long[]{0x0002000100000000L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom760 = new BitSet(new long[]{0x0002000508100000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom762 = new BitSet(new long[]{0x0002000508100000L});
	public static final BitSet FOLLOW_primary_expression_in_atom765 = new BitSet(new long[]{0x0002000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom767 = new BitSet(new long[]{0x0002000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom770 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_booleanhistory792 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_booleanhistory794 = new BitSet(new long[]{0xEFE0000000000000L,0x00000000000063D9L});
	public static final BitSet FOLLOW_presetcondition_in_booleanhistory798 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_opcmpcondition_in_booleanhistory809 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constantcmp_in_booleanhistory820 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_operand837 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Operation_in_operand857 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NumberToken_in_constant871 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_lenient900 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_LENIENT_in_lenient902 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_63_in_opcmpcondition940 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition942 = new BitSet(new long[]{0x0000000400100000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition946 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition972 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_opcmpcondition974 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition976 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition980 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition982 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition984 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_67_in_opcmpcondition1006 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1008 = new BitSet(new long[]{0x0000000400100000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1012 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1040 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_opcmpcondition1042 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1044 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1048 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1050 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1052 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_57_in_opcmpcondition1074 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1076 = new BitSet(new long[]{0x0000000400100000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1080 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1107 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_opcmpcondition1109 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1111 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1115 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1117 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1119 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_53_in_opcmpcondition1142 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1144 = new BitSet(new long[]{0x0000000400100000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1146 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1183 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_opcmpcondition1185 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1187 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1191 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1193 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1195 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1208 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition1210 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1212 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1216 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1218 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1220 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_55_in_opcmpcondition1264 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1266 = new BitSet(new long[]{0x0000000400100000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1268 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1306 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_opcmpcondition1308 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1310 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1314 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1316 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1318 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1329 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition1331 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1333 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1337 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1339 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1341 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_59_in_constantcmp1391 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1393 = new BitSet(new long[]{0x0018000000000000L});
	public static final BitSet FOLLOW_trendconstant_in_constantcmp1397 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_58_in_constantcmp1425 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1427 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1431 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1466 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_constantcmp1468 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1470 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1474 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1476 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1478 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1480 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_constantcmp1482 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1484 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1488 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1490 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1492 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_64_in_constantcmp1521 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1523 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1527 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1561 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_constantcmp1563 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1565 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1569 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1571 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1573 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1575 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_constantcmp1577 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1579 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1583 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1585 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1587 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_68_in_constantcmp1616 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1618 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1622 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1657 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_constantcmp1659 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1661 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1665 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1667 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1669 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1671 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_constantcmp1673 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1675 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1679 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1681 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1683 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_77_in_presetcondition1721 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1761 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition1763 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1765 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1769 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition1771 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1773 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition1775 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1777 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1781 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1783 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1785 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_78_in_presetcondition1829 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1869 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_74_in_presetcondition1871 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1873 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1877 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition1879 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1881 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition1883 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1885 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1889 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1891 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1893 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_61_in_presetcondition1936 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1938 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1942 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition1944 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1987 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition1989 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1991 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition1995 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition1997 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition1999 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2010 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition2012 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2014 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2018 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2020 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2022 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_62_in_presetcondition2060 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2062 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2066 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2068 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2112 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition2114 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2116 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2120 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2122 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2124 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2135 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_60_in_presetcondition2137 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2139 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2143 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2145 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2147 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_56_in_presetcondition2192 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2194 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2198 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2241 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition2243 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2245 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2249 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2251 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2253 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2264 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_presetcondition2266 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2268 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2272 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2274 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2276 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_54_in_presetcondition2322 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2324 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2328 = new BitSet(new long[]{0x0002000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2370 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_presetcondition2372 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2374 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2378 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2380 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2382 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2393 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_presetcondition2395 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2397 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2401 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2403 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2405 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_70_in_presetcondition2449 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2451 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2455 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2457 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2459 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_71_in_presetcondition2491 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2493 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2497 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2499 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2501 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_72_in_presetcondition2533 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2535 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2539 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2541 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2543 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_73_in_presetcondition2575 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2577 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2581 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2583 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2585 = new BitSet(new long[]{0x0000000000000002L});
}
