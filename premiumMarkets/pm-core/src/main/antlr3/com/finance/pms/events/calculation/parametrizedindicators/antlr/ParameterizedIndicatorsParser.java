// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2018-09-11 23:02:26
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
		"LENIENT", "LINE_COMMENT", "LinearTrendsCondition", "LowerHighCondition", 
		"LowerLowCondition", "NOT", "NotDoubleMapCondition", "NullCondition", 
		"Number", "NumberToken", "OPENPARENTEHSIS", "OR", "Operation", "OperationOutput", 
		"OrDoubleMapCondition", "PERCENT", "ReverseCondition", "StockOperation", 
		"String", "StringOperation", "StringToken", "SupConstantCondition", "SupDoubleMapCondition", 
		"Tcheat", "UpRatioCondition", "WS", "WhiteChar", "','", "'NaN'", "'['", 
		"']'", "'also display'", "'bearish'", "'bullish'", "'crosses down historical'", 
		"'crosses down threshold'", "'crosses up historical'", "'crosses up threshold'", 
		"'ending within'", "'equals historical'", "'equals threshold'", "'equals trend'", 
		"'for'", "'goes down more than'", "'goes up more than'", "'is above historical'", 
		"'is above threshold'", "'is bearish if not bullish'", "'is bearish when'", 
		"'is below historical'", "'is below threshold'", "'is bullish when'", 
		"'makes a higher high spanning'", "'makes a higher low spanning'", "'makes a lower high spanning'", 
		"'makes a lower low spanning'", "'more than'", "'over'", "'override start shift with'", 
		"'reverses down'", "'reverses up'", "'smoothed'", "'spanning'", "'starting within'", 
		"'trends down like'", "'trends like'", "'trends up like'"
	};
	public static final int EOF=-1;
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
	public static final int T__87=87;
	public static final int T__88=88;
	public static final int T__89=89;
	public static final int T__90=90;
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
	public static final int LinearTrendsCondition=26;
	public static final int LowerHighCondition=27;
	public static final int LowerLowCondition=28;
	public static final int NOT=29;
	public static final int NotDoubleMapCondition=30;
	public static final int NullCondition=31;
	public static final int Number=32;
	public static final int NumberToken=33;
	public static final int OPENPARENTEHSIS=34;
	public static final int OR=35;
	public static final int Operation=36;
	public static final int OperationOutput=37;
	public static final int OrDoubleMapCondition=38;
	public static final int PERCENT=39;
	public static final int ReverseCondition=40;
	public static final int StockOperation=41;
	public static final int String=42;
	public static final int StringOperation=43;
	public static final int StringToken=44;
	public static final int SupConstantCondition=45;
	public static final int SupDoubleMapCondition=46;
	public static final int Tcheat=47;
	public static final int UpRatioCondition=48;
	public static final int WS=49;
	public static final int WhiteChar=50;

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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:146:1: complete_expression : bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:146:21: (bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:148:4: bcond= bullish_condition bearish_condition[$bcond.tree] also_display fixed_start_shift
			{
			pushFollow(FOLLOW_bullish_condition_in_complete_expression350);
			bcond=bullish_condition();
			state._fsp--;

			stream_bullish_condition.add(bcond.getTree());
			pushFollow(FOLLOW_bearish_condition_in_complete_expression352);
			bearish_condition1=bearish_condition((bcond!=null?((CommonTree)bcond.getTree()):null));
			state._fsp--;

			stream_bearish_condition.add(bearish_condition1.getTree());
			pushFollow(FOLLOW_also_display_in_complete_expression355);
			also_display2=also_display();
			state._fsp--;

			stream_also_display.add(also_display2.getTree());
			pushFollow(FOLLOW_fixed_start_shift_in_complete_expression357);
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
			// 148:90: -> ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:148:93: ^( EventInfoOpsCompoOperation bullish_condition bearish_condition also_display fixed_start_shift StringOperation )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:151:1: bullish_condition : 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression ;
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
		RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:151:19: ( 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:152:2: 'is bullish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )*
			{
			string_literal4=(Token)match(input,75,FOLLOW_75_in_bullish_condition387);  
			stream_75.add(string_literal4);

			WhiteChar5=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition389);  
			stream_WhiteChar.add(WhiteChar5);

			pushFollow(FOLLOW_primary_expression_in_bullish_condition391);
			primary_expression6=primary_expression();
			state._fsp--;

			stream_primary_expression.add(primary_expression6.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:152:49: ( WhiteChar )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==WhiteChar) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:152:49: WhiteChar
					{
					WhiteChar7=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition393);  
					stream_WhiteChar.add(WhiteChar7);

					}
					break;

				default :
					break loop1;
				}
			}

			COMMA8=(Token)match(input,COMMA,FOLLOW_COMMA_in_bullish_condition396);  
			stream_COMMA.add(COMMA8);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:152:66: ( WhiteChar )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==WhiteChar) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:152:66: WhiteChar
					{
					WhiteChar9=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition398);  
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
			// 152:77: -> primary_expression
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:154:1: bearish_condition[CommonTree bcond] : ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* COMMA ( WhiteChar )* -> bearish_not_bullish );
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
		RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
		RewriteRuleSubtreeStream stream_bearish_not_bullish=new RewriteRuleSubtreeStream(adaptor,"rule bearish_not_bullish");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:154:37: ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bcond] ( WhiteChar )* COMMA ( WhiteChar )* -> bearish_not_bullish )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==72) ) {
				alt7=1;
			}
			else if ( (LA7_0==71) ) {
				alt7=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:2: 'is bearish when' WhiteChar primary_expression ( WhiteChar )* COMMA ( WhiteChar )*
					{
					string_literal10=(Token)match(input,72,FOLLOW_72_in_bearish_condition414);  
					stream_72.add(string_literal10);

					WhiteChar11=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition416);  
					stream_WhiteChar.add(WhiteChar11);

					pushFollow(FOLLOW_primary_expression_in_bearish_condition419);
					primary_expression12=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression12.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:50: ( WhiteChar )*
					loop3:
					while (true) {
						int alt3=2;
						int LA3_0 = input.LA(1);
						if ( (LA3_0==WhiteChar) ) {
							alt3=1;
						}

						switch (alt3) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:50: WhiteChar
							{
							WhiteChar13=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition421);  
							stream_WhiteChar.add(WhiteChar13);

							}
							break;

						default :
							break loop3;
						}
					}

					COMMA14=(Token)match(input,COMMA,FOLLOW_COMMA_in_bearish_condition424);  
					stream_COMMA.add(COMMA14);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:67: ( WhiteChar )*
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( (LA4_0==WhiteChar) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:155:67: WhiteChar
							{
							WhiteChar15=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition426);  
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
					// 155:78: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:156:2: bearish_not_bullish[$bcond] ( WhiteChar )* COMMA ( WhiteChar )*
					{
					pushFollow(FOLLOW_bearish_not_bullish_in_bearish_condition436);
					bearish_not_bullish16=bearish_not_bullish(bcond);
					state._fsp--;

					stream_bearish_not_bullish.add(bearish_not_bullish16.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:156:30: ( WhiteChar )*
					loop5:
					while (true) {
						int alt5=2;
						int LA5_0 = input.LA(1);
						if ( (LA5_0==WhiteChar) ) {
							alt5=1;
						}

						switch (alt5) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:156:30: WhiteChar
							{
							WhiteChar17=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition439);  
							stream_WhiteChar.add(WhiteChar17);

							}
							break;

						default :
							break loop5;
						}
					}

					COMMA18=(Token)match(input,COMMA,FOLLOW_COMMA_in_bearish_condition442);  
					stream_COMMA.add(COMMA18);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:156:47: ( WhiteChar )*
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( (LA6_0==WhiteChar) ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:156:47: WhiteChar
							{
							WhiteChar19=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition444);  
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
					// 156:58: -> bearish_not_bullish
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:158:2: also_display : ( 'also display' WhiteChar primary_expression ( WhiteChar )* COMMA -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition );
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
		RewriteRuleTokenStream stream_55=new RewriteRuleTokenStream(adaptor,"token 55");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:158:15: ( 'also display' WhiteChar primary_expression ( WhiteChar )* COMMA -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==55) ) {
				alt9=1;
			}
			else if ( (LA9_0==EOF||LA9_0==82) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:3: 'also display' WhiteChar primary_expression ( WhiteChar )* COMMA
					{
					string_literal20=(Token)match(input,55,FOLLOW_55_in_also_display461);  
					stream_55.add(string_literal20);

					WhiteChar21=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display463);  
					stream_WhiteChar.add(WhiteChar21);

					pushFollow(FOLLOW_primary_expression_in_also_display465);
					primary_expression22=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression22.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:47: ( WhiteChar )*
					loop8:
					while (true) {
						int alt8=2;
						int LA8_0 = input.LA(1);
						if ( (LA8_0==WhiteChar) ) {
							alt8=1;
						}

						switch (alt8) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:47: WhiteChar
							{
							WhiteChar23=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display467);  
							stream_WhiteChar.add(WhiteChar23);

							}
							break;

						default :
							break loop8;
						}
					}

					COMMA24=(Token)match(input,COMMA,FOLLOW_COMMA_in_also_display470);  
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
					// 159:64: -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:67: ^( AndDoubleMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:159:91: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:160:3: 
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
					// 160:3: -> NullCondition
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:2: fixed_start_shift : ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS COMMA ->| -> ^( Number NumberToken[\"-1\"] ) );
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
		RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:20: ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS COMMA ->| -> ^( Number NumberToken[\"-1\"] ) )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==82) ) {
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:163:3: 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS COMMA
					{
					string_literal25=(Token)match(input,82,FOLLOW_82_in_fixed_start_shift505);  
					stream_82.add(string_literal25);

					WhiteChar26=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift507);  
					stream_WhiteChar.add(WhiteChar26);

					pushFollow(FOLLOW_constant_in_fixed_start_shift511);
					fixedStartShift=constant();
					state._fsp--;

					stream_constant.add(fixedStartShift.getTree());
					WhiteChar27=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift513);  
					stream_WhiteChar.add(WhiteChar27);

					DAYS28=(Token)match(input,DAYS,FOLLOW_DAYS_in_fixed_start_shift515);  
					stream_DAYS.add(DAYS28);

					COMMA29=(Token)match(input,COMMA,FOLLOW_COMMA_in_fixed_start_shift517);  
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
					// 163:87: ->
					{
						adaptor.addChild(root_0, (fixedStartShift!=null?((CommonTree)fixedStartShift.getTree()):null));
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:164:3: 
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
					// 164:3: -> ^( Number NumberToken[\"-1\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:164:6: ^( Number NumberToken[\"-1\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:1: bearish_not_bullish[CommonTree bcond] : 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) ) ;
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
		RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:39: ( 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:168:2: 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) )
			{
			string_literal30=(Token)match(input,71,FOLLOW_71_in_bearish_not_bullish547);  
			stream_71.add(string_literal30);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:3: ( WhiteChar AND WhiteChar primary_expression -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression ) | -> ^( NotDoubleMapCondition ) )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:3: WhiteChar AND WhiteChar primary_expression
					{
					WhiteChar31=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish556);  
					stream_WhiteChar.add(WhiteChar31);

					AND32=(Token)match(input,AND,FOLLOW_AND_in_bearish_not_bullish558);  
					stream_AND.add(AND32);

					WhiteChar33=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish560);  
					stream_WhiteChar.add(WhiteChar33);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish562);
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
					// 170:46: -> ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:49: ^( AndDoubleMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotDoubleMapCondition ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:73: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:108: ^( NotDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:171:3: WhiteChar OR WhiteChar primary_expression
					{
					WhiteChar35=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish589);  
					stream_WhiteChar.add(WhiteChar35);

					OR36=(Token)match(input,OR,FOLLOW_OR_in_bearish_not_bullish591);  
					stream_OR.add(OR36);

					WhiteChar37=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish593);  
					stream_WhiteChar.add(WhiteChar37);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish596);
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
					// 171:46: -> ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:171:49: ^( OrDoubleMapCondition ^( NotDoubleMapCondition ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrDoubleMapCondition, "OrDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:171:72: ^( NotDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:172:3: 
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
					// 172:3: -> ^( NotDoubleMapCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:172:6: ^( NotDoubleMapCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:1: primary_expression : and_expression ;
	public final ParameterizedIndicatorsParser.primary_expression_return primary_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.primary_expression_return retval = new ParameterizedIndicatorsParser.primary_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope and_expression39 =null;


		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:20: ( and_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:177:2: and_expression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_and_expression_in_primary_expression636);
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:1: and_expression : or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndDoubleMapCondition or_expression ( or_expression )* ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:16: ( or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndDoubleMapCondition or_expression ( or_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:3: or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )*
			{
			pushFollow(FOLLOW_or_expression_in_and_expression649);
			or_expression40=or_expression();
			state._fsp--;

			stream_or_expression.add(or_expression40.getTree());
			pushFollow(FOLLOW_lenient_in_and_expression653);
			lenientParam=lenient();
			state._fsp--;

			stream_lenient.add(lenientParam.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:38: ( WhiteChar AND WhiteChar or_expression )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:39: WhiteChar AND WhiteChar or_expression
					{
					WhiteChar41=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression656);  
					stream_WhiteChar.add(WhiteChar41);

					AND42=(Token)match(input,AND,FOLLOW_AND_in_and_expression658);  
					stream_AND.add(AND42);

					WhiteChar43=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression660);  
					stream_WhiteChar.add(WhiteChar43);

					pushFollow(FOLLOW_or_expression_in_and_expression662);
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
			// 181:79: -> ^( AndDoubleMapCondition or_expression ( or_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:82: ^( AndDoubleMapCondition or_expression ( or_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndDoubleMapCondition, "AndDoubleMapCondition"), root_1);
				adaptor.addChild(root_1, (lenientParam!=null?((CommonTree)lenientParam.getTree()):null));
				adaptor.addChild(root_1, stream_or_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:181:142: ( or_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:1: or_expression : atom ( WhiteChar OR WhiteChar atom )* -> ^( OrDoubleMapCondition atom ( atom )* ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:15: ( atom ( WhiteChar OR WhiteChar atom )* -> ^( OrDoubleMapCondition atom ( atom )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:3: atom ( WhiteChar OR WhiteChar atom )*
			{
			pushFollow(FOLLOW_atom_in_or_expression690);
			atom45=atom();
			state._fsp--;

			stream_atom.add(atom45.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:8: ( WhiteChar OR WhiteChar atom )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:9: WhiteChar OR WhiteChar atom
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
			// 184:39: -> ^( OrDoubleMapCondition atom ( atom )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:42: ^( OrDoubleMapCondition atom ( atom )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrDoubleMapCondition, "OrDoubleMapCondition"), root_1);
				adaptor.addChild(root_1, stream_atom.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:70: ( atom )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:1: atom : ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotDoubleMapCondition primary_expression ) );
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:6: ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotDoubleMapCondition primary_expression ) )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:3: booleanhistory
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_booleanhistory_in_atom725);
					booleanhistory50=booleanhistory();
					state._fsp--;

					adaptor.addChild(root_0, booleanhistory50.getTree());

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:3: '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					char_literal51=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom732);  
					stream_OPENPARENTEHSIS.add(char_literal51);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:7: ( WhiteChar )*
					loop14:
					while (true) {
						int alt14=2;
						int LA14_0 = input.LA(1);
						if ( (LA14_0==WhiteChar) ) {
							alt14=1;
						}

						switch (alt14) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:7: WhiteChar
							{
							WhiteChar52=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom734);  
							stream_WhiteChar.add(WhiteChar52);

							}
							break;

						default :
							break loop14;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom737);
					primary_expression53=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression53.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:37: ( WhiteChar )*
					loop15:
					while (true) {
						int alt15=2;
						int LA15_0 = input.LA(1);
						if ( (LA15_0==WhiteChar) ) {
							alt15=1;
						}

						switch (alt15) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:37: WhiteChar
							{
							WhiteChar54=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom739);  
							stream_WhiteChar.add(WhiteChar54);

							}
							break;

						default :
							break loop15;
						}
					}

					char_literal55=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom742);  
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
					// 188:52: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:3: 'not' ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					string_literal56=(Token)match(input,NOT,FOLLOW_NOT_in_atom753);  
					stream_NOT.add(string_literal56);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:9: ( WhiteChar )*
					loop16:
					while (true) {
						int alt16=2;
						int LA16_0 = input.LA(1);
						if ( (LA16_0==WhiteChar) ) {
							alt16=1;
						}

						switch (alt16) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:9: WhiteChar
							{
							WhiteChar57=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom755);  
							stream_WhiteChar.add(WhiteChar57);

							}
							break;

						default :
							break loop16;
						}
					}

					char_literal58=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom758);  
					stream_OPENPARENTEHSIS.add(char_literal58);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:24: ( WhiteChar )*
					loop17:
					while (true) {
						int alt17=2;
						int LA17_0 = input.LA(1);
						if ( (LA17_0==WhiteChar) ) {
							alt17=1;
						}

						switch (alt17) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:24: WhiteChar
							{
							WhiteChar59=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom760);  
							stream_WhiteChar.add(WhiteChar59);

							}
							break;

						default :
							break loop17;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom763);
					primary_expression60=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression60.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:54: ( WhiteChar )*
					loop18:
					while (true) {
						int alt18=2;
						int LA18_0 = input.LA(1);
						if ( (LA18_0==WhiteChar) ) {
							alt18=1;
						}

						switch (alt18) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:54: WhiteChar
							{
							WhiteChar61=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom765);  
							stream_WhiteChar.add(WhiteChar61);

							}
							break;

						default :
							break loop18;
						}
					}

					char_literal62=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom768);  
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
					// 189:69: -> ^( NotDoubleMapCondition primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:189:72: ^( NotDoubleMapCondition primary_expression )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:1: booleanhistory : firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:16: (firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:18: firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			{
			pushFollow(FOLLOW_operand_in_booleanhistory790);
			firstOp=operand();
			state._fsp--;

			stream_operand.add(firstOp.getTree());
			WhiteChar63=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_booleanhistory792);  
			stream_WhiteChar.add(WhiteChar63);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:44: ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			int alt20=3;
			switch ( input.LA(1) ) {
			case 59:
			case 61:
			case 67:
			case 68:
			case 76:
			case 77:
			case 78:
			case 79:
			case 83:
			case 84:
				{
				alt20=1;
				}
				break;
			case 58:
			case 60:
			case 63:
			case 69:
			case 73:
			case 88:
			case 89:
			case 90:
				{
				alt20=2;
				}
				break;
			case 64:
			case 65:
			case 70:
			case 74:
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:46: presetcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_presetcondition_in_booleanhistory796);
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
					// 192:77: -> presetcondition
					{
						adaptor.addChild(root_0, stream_presetcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:98: opcmpcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_opcmpcondition_in_booleanhistory805);
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
					// 192:128: -> opcmpcondition
					{
						adaptor.addChild(root_0, stream_opcmpcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:147: constantcmp[$firstOp.tree]
					{
					pushFollow(FOLLOW_constantcmp_in_booleanhistory813);
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
					// 192:174: -> constantcmp
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:1: operand : ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation );
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:9: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:11: HistoricalData
					{
					HistoricalData67=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_operand827);  
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
					// 193:26: -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:29: ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:46: ^( OperationOutput HistoricalData )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
						adaptor.addChild(root_2, stream_HistoricalData.nextNode());
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:80: ^( String StringToken[\"\\\"THIS\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:193:117: opName= Operation
					{
					opName=(Token)match(input,Operation,FOLLOW_Operation_in_operand854);  
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
					// 193:171: -> Operation
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:1: constant : ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) );
	public final ParameterizedIndicatorsParser.constant_return constant() throws RecognitionException {
		ParameterizedIndicatorsParser.constant_return retval = new ParameterizedIndicatorsParser.constant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NumberToken68=null;
		Token string_literal69=null;

		CommonTree NumberToken68_tree=null;
		CommonTree string_literal69_tree=null;
		RewriteRuleTokenStream stream_NumberToken=new RewriteRuleTokenStream(adaptor,"token NumberToken");
		RewriteRuleTokenStream stream_52=new RewriteRuleTokenStream(adaptor,"token 52");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:10: ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) )
			int alt22=2;
			int LA22_0 = input.LA(1);
			if ( (LA22_0==NumberToken) ) {
				alt22=1;
			}
			else if ( (LA22_0==52) ) {
				alt22=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}

			switch (alt22) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:13: NumberToken
					{
					NumberToken68=(Token)match(input,NumberToken,FOLLOW_NumberToken_in_constant868);  
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
					// 194:25: -> ^( Number NumberToken )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:28: ^( Number NumberToken )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:52: 'NaN'
					{
					string_literal69=(Token)match(input,52,FOLLOW_52_in_constant880);  
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
					// 194:58: -> ^( Number NumberToken[\"NaN\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:194:61: ^( Number NumberToken[\"NaN\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:1: trendconstant : ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) );
	public final ParameterizedIndicatorsParser.trendconstant_return trendconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.trendconstant_return retval = new ParameterizedIndicatorsParser.trendconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal70=null;
		Token string_literal71=null;

		CommonTree string_literal70_tree=null;
		CommonTree string_literal71_tree=null;
		RewriteRuleTokenStream stream_56=new RewriteRuleTokenStream(adaptor,"token 56");
		RewriteRuleTokenStream stream_57=new RewriteRuleTokenStream(adaptor,"token 57");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:15: ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) )
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( (LA23_0==57) ) {
				alt23=1;
			}
			else if ( (LA23_0==56) ) {
				alt23=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}

			switch (alt23) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:17: 'bullish'
					{
					string_literal70=(Token)match(input,57,FOLLOW_57_in_trendconstant896);  
					stream_57.add(string_literal70);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 195:27: -> ^( String StringToken[\"\\\"bullish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:30: ^( String StringToken[\"\\\"bullish\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:69: 'bearish'
					{
					string_literal71=(Token)match(input,56,FOLLOW_56_in_trendconstant909);  
					stream_56.add(string_literal71);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 195:79: -> ^( String StringToken[\"\\\"bearish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:82: ^( String StringToken[\"\\\"bearish\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:1: lenient : ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:9: ( ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:12: WhiteChar LENIENT
					{
					WhiteChar72=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_lenient926);  
					stream_WhiteChar.add(WhiteChar72);

					LENIENT73=(Token)match(input,LENIENT,FOLLOW_LENIENT_in_lenient928);  
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
					// 196:30: -> ^( String StringToken[\"\\\"TRUE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:33: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:69: 
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
					// 196:69: -> ^( String StringToken[\"\\\"FALSE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:72: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:1: opcmpcondition[CommonTree firstOp] : ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( LinearTrendsCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( String StringToken[\"\\\"TRUE\\\"\"] ) ) ) | ( 'trends up like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( LinearTrendsCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ) | ( 'trends down like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( LinearTrendsCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( String StringToken[\"\\\"TRUE\\\"\"] ) ) ) );
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
		RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
		RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");
		RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_58=new RewriteRuleTokenStream(adaptor,"token 58");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
		RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
		RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
		RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
		RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
		RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:37: ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )? | ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( LinearTrendsCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( String StringToken[\"\\\"TRUE\\\"\"] ) ) ) | ( 'trends up like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( LinearTrendsCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ) | ( 'trends down like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( LinearTrendsCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( String StringToken[\"\\\"TRUE\\\"\"] ) ) ) )
			int alt30=8;
			switch ( input.LA(1) ) {
			case 69:
				{
				alt30=1;
				}
				break;
			case 73:
				{
				alt30=2;
				}
				break;
			case 63:
				{
				alt30=3;
				}
				break;
			case 58:
				{
				alt30=4;
				}
				break;
			case 60:
				{
				alt30=5;
				}
				break;
			case 89:
				{
				alt30=6;
				}
				break;
			case 90:
				{
				alt30=7;
				}
				break;
			case 88:
				{
				alt30=8;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 30, 0, input);
				throw nvae;
			}
			switch (alt30) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:4: 'is above historical' WhiteChar secondOp= operand
					{
					string_literal74=(Token)match(input,69,FOLLOW_69_in_opcmpcondition964);  
					stream_69.add(string_literal74);

					WhiteChar75=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition966);  
					stream_WhiteChar.add(WhiteChar75);

					pushFollow(FOLLOW_operand_in_opcmpcondition970);
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
					// 200:53: -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:56: ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupDoubleMapCondition, "SupDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:80: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupDoubleMapCondition ) )?
					int alt25=2;
					int LA25_0 = input.LA(1);
					if ( (LA25_0==WhiteChar) ) {
						int LA25_1 = input.LA(2);
						if ( (LA25_1==66) ) {
							alt25=1;
						}
					}
					switch (alt25) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:6: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar76=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition996);  
							stream_WhiteChar.add(WhiteChar76);

							string_literal77=(Token)match(input,66,FOLLOW_66_in_opcmpcondition998);  
							stream_66.add(string_literal77);

							WhiteChar78=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1000);  
							stream_WhiteChar.add(WhiteChar78);

							pushFollow(FOLLOW_constant_in_opcmpcondition1004);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar79=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1006);  
							stream_WhiteChar.add(WhiteChar79);

							DAYS80=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1008);  
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
							// 201:66: -> ^( SupDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:201:69: ^( SupDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:4: 'is below historical' WhiteChar secondOp= operand
					{
					string_literal81=(Token)match(input,73,FOLLOW_73_in_opcmpcondition1030);  
					stream_73.add(string_literal81);

					WhiteChar82=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1032);  
					stream_WhiteChar.add(WhiteChar82);

					pushFollow(FOLLOW_operand_in_opcmpcondition1036);
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
					// 202:53: -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:56: ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfDoubleMapCondition, "InfDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:202:80: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:5: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfDoubleMapCondition ) )?
					int alt26=2;
					int LA26_0 = input.LA(1);
					if ( (LA26_0==WhiteChar) ) {
						int LA26_1 = input.LA(2);
						if ( (LA26_1==66) ) {
							alt26=1;
						}
					}
					switch (alt26) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar83=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1064);  
							stream_WhiteChar.add(WhiteChar83);

							string_literal84=(Token)match(input,66,FOLLOW_66_in_opcmpcondition1066);  
							stream_66.add(string_literal84);

							WhiteChar85=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1068);  
							stream_WhiteChar.add(WhiteChar85);

							pushFollow(FOLLOW_constant_in_opcmpcondition1072);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar86=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1074);  
							stream_WhiteChar.add(WhiteChar86);

							DAYS87=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1076);  
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
							// 203:67: -> ^( InfDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:203:70: ^( InfDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:4: 'equals historical' WhiteChar secondOp= operand
					{
					string_literal88=(Token)match(input,63,FOLLOW_63_in_opcmpcondition1098);  
					stream_63.add(string_literal88);

					WhiteChar89=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1100);  
					stream_WhiteChar.add(WhiteChar89);

					pushFollow(FOLLOW_operand_in_opcmpcondition1104);
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
					// 204:51: -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:54: ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualDoubleMapCondition, "EqualDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:80: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:5: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualDoubleMapCondition ) )?
					int alt27=2;
					int LA27_0 = input.LA(1);
					if ( (LA27_0==WhiteChar) ) {
						int LA27_1 = input.LA(2);
						if ( (LA27_1==66) ) {
							alt27=1;
						}
					}
					switch (alt27) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:7: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar90=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1131);  
							stream_WhiteChar.add(WhiteChar90);

							string_literal91=(Token)match(input,66,FOLLOW_66_in_opcmpcondition1133);  
							stream_66.add(string_literal91);

							WhiteChar92=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1135);  
							stream_WhiteChar.add(WhiteChar92);

							pushFollow(FOLLOW_constant_in_opcmpcondition1139);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar93=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1141);  
							stream_WhiteChar.add(WhiteChar93);

							DAYS94=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1143);  
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
							// 205:67: -> ^( EqualDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:70: ^( EqualDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:3: ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:3: ( 'crosses down historical' WhiteChar operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:4: 'crosses down historical' WhiteChar operand
					{
					string_literal95=(Token)match(input,58,FOLLOW_58_in_opcmpcondition1166);  
					stream_58.add(string_literal95);

					WhiteChar96=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1168);  
					stream_WhiteChar.add(WhiteChar96);

					pushFollow(FOLLOW_operand_in_opcmpcondition1170);
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
					// 207:48: -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:51: ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:81: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:110: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:9: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownDoubleMapCondition operand ) )?
					int alt28=2;
					int LA28_0 = input.LA(1);
					if ( (LA28_0==WhiteChar) ) {
						int LA28_1 = input.LA(2);
						if ( (LA28_1==86) ) {
							alt28=1;
						}
					}
					switch (alt28) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:11: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar98=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1207);  
							stream_WhiteChar.add(WhiteChar98);

							string_literal99=(Token)match(input,86,FOLLOW_86_in_opcmpcondition1209);  
							stream_86.add(string_literal99);

							WhiteChar100=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1211);  
							stream_WhiteChar.add(WhiteChar100);

							pushFollow(FOLLOW_constant_in_opcmpcondition1215);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar101=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1217);  
							stream_WhiteChar.add(WhiteChar101);

							DAYS102=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1219);  
							stream_DAYS.add(DAYS102);

							WhiteChar103=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1232);  
							stream_WhiteChar.add(WhiteChar103);

							string_literal104=(Token)match(input,81,FOLLOW_81_in_opcmpcondition1234);  
							stream_81.add(string_literal104);

							WhiteChar105=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1236);  
							stream_WhiteChar.add(WhiteChar105);

							pushFollow(FOLLOW_constant_in_opcmpcondition1240);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar106=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1242);  
							stream_WhiteChar.add(WhiteChar106);

							DAYS107=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1244);  
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
							// 210:11: -> ^( CrossDownDoubleMapCondition operand )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:14: ^( CrossDownDoubleMapCondition operand )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:3: ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:3: ( 'crosses up historical' WhiteChar operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:4: 'crosses up historical' WhiteChar operand
					{
					string_literal108=(Token)match(input,60,FOLLOW_60_in_opcmpcondition1280);  
					stream_60.add(string_literal108);

					WhiteChar109=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1282);  
					stream_WhiteChar.add(WhiteChar109);

					pushFollow(FOLLOW_operand_in_opcmpcondition1284);
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
					// 212:46: -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:49: ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:77: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:212:106: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:8: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpDoubleMapCondition operand ) )?
					int alt29=2;
					int LA29_0 = input.LA(1);
					if ( (LA29_0==WhiteChar) ) {
						int LA29_1 = input.LA(2);
						if ( (LA29_1==86) ) {
							alt29=1;
						}
					}
					switch (alt29) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:10: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar111=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1320);  
							stream_WhiteChar.add(WhiteChar111);

							string_literal112=(Token)match(input,86,FOLLOW_86_in_opcmpcondition1322);  
							stream_86.add(string_literal112);

							WhiteChar113=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1324);  
							stream_WhiteChar.add(WhiteChar113);

							pushFollow(FOLLOW_constant_in_opcmpcondition1328);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar114=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1330);  
							stream_WhiteChar.add(WhiteChar114);

							DAYS115=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1332);  
							stream_DAYS.add(DAYS115);

							WhiteChar116=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1343);  
							stream_WhiteChar.add(WhiteChar116);

							string_literal117=(Token)match(input,81,FOLLOW_81_in_opcmpcondition1345);  
							stream_81.add(string_literal117);

							WhiteChar118=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1347);  
							stream_WhiteChar.add(WhiteChar118);

							pushFollow(FOLLOW_constant_in_opcmpcondition1351);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar119=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1353);  
							stream_WhiteChar.add(WhiteChar119);

							DAYS120=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1355);  
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
							// 215:10: -> ^( CrossUpDoubleMapCondition operand )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:215:13: ^( CrossUpDoubleMapCondition operand )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( LinearTrendsCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( String StringToken[\"\\\"TRUE\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( LinearTrendsCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( String StringToken[\"\\\"TRUE\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:4: 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
					{
					string_literal121=(Token)match(input,89,FOLLOW_89_in_opcmpcondition1390);  
					stream_89.add(string_literal121);

					WhiteChar122=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1392);  
					stream_WhiteChar.add(WhiteChar122);

					pushFollow(FOLLOW_operand_in_opcmpcondition1396);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar123=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1404);  
					stream_WhiteChar.add(WhiteChar123);

					string_literal124=(Token)match(input,81,FOLLOW_81_in_opcmpcondition1406);  
					stream_81.add(string_literal124);

					WhiteChar125=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1408);  
					stream_WhiteChar.add(WhiteChar125);

					pushFollow(FOLLOW_constant_in_opcmpcondition1412);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar126=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1414);  
					stream_WhiteChar.add(WhiteChar126);

					DAYS127=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1416);  
					stream_DAYS.add(DAYS127);

					WhiteChar128=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1424);  
					stream_WhiteChar.add(WhiteChar128);

					string_literal129=(Token)match(input,66,FOLLOW_66_in_opcmpcondition1426);  
					stream_66.add(string_literal129);

					WhiteChar130=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1428);  
					stream_WhiteChar.add(WhiteChar130);

					pushFollow(FOLLOW_constant_in_opcmpcondition1432);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar131=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1434);  
					stream_WhiteChar.add(WhiteChar131);

					DAYS132=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1436);  
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
					// 220:7: -> ^( LinearTrendsCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( String StringToken[\"\\\"TRUE\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:10: ^( LinearTrendsCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( String StringToken[\"\\\"TRUE\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearTrendsCondition, "LinearTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:71: ^( String StringToken[\"\\\"TRUE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:105: ^( String StringToken[\"\\\"TRUE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
						adaptor.addChild(root_1, root_2);
						}

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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:3: ( 'trends up like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( LinearTrendsCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:3: ( 'trends up like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( LinearTrendsCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:4: 'trends up like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
					{
					string_literal133=(Token)match(input,90,FOLLOW_90_in_opcmpcondition1485);  
					stream_90.add(string_literal133);

					WhiteChar134=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1487);  
					stream_WhiteChar.add(WhiteChar134);

					pushFollow(FOLLOW_operand_in_opcmpcondition1491);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar135=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1499);  
					stream_WhiteChar.add(WhiteChar135);

					string_literal136=(Token)match(input,81,FOLLOW_81_in_opcmpcondition1501);  
					stream_81.add(string_literal136);

					WhiteChar137=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1503);  
					stream_WhiteChar.add(WhiteChar137);

					pushFollow(FOLLOW_constant_in_opcmpcondition1507);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar138=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1509);  
					stream_WhiteChar.add(WhiteChar138);

					DAYS139=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1511);  
					stream_DAYS.add(DAYS139);

					WhiteChar140=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1519);  
					stream_WhiteChar.add(WhiteChar140);

					string_literal141=(Token)match(input,66,FOLLOW_66_in_opcmpcondition1521);  
					stream_66.add(string_literal141);

					WhiteChar142=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1523);  
					stream_WhiteChar.add(WhiteChar142);

					pushFollow(FOLLOW_constant_in_opcmpcondition1527);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar143=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1529);  
					stream_WhiteChar.add(WhiteChar143);

					DAYS144=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1531);  
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
					// 225:7: -> ^( LinearTrendsCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:10: ^( LinearTrendsCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearTrendsCondition, "LinearTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:71: ^( String StringToken[\"\\\"TRUE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:105: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, firstOp);
						adaptor.addChild(root_1, (secondOp!=null?((CommonTree)secondOp.getTree()):null));
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}

					}
					break;
				case 8 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:3: ( 'trends down like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( LinearTrendsCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( String StringToken[\"\\\"TRUE\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:3: ( 'trends down like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( LinearTrendsCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( String StringToken[\"\\\"TRUE\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:4: 'trends down like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
					{
					string_literal145=(Token)match(input,88,FOLLOW_88_in_opcmpcondition1580);  
					stream_88.add(string_literal145);

					WhiteChar146=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1582);  
					stream_WhiteChar.add(WhiteChar146);

					pushFollow(FOLLOW_operand_in_opcmpcondition1586);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar147=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1594);  
					stream_WhiteChar.add(WhiteChar147);

					string_literal148=(Token)match(input,81,FOLLOW_81_in_opcmpcondition1596);  
					stream_81.add(string_literal148);

					WhiteChar149=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1598);  
					stream_WhiteChar.add(WhiteChar149);

					pushFollow(FOLLOW_constant_in_opcmpcondition1602);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar150=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1604);  
					stream_WhiteChar.add(WhiteChar150);

					DAYS151=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1606);  
					stream_DAYS.add(DAYS151);

					WhiteChar152=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1614);  
					stream_WhiteChar.add(WhiteChar152);

					string_literal153=(Token)match(input,66,FOLLOW_66_in_opcmpcondition1616);  
					stream_66.add(string_literal153);

					WhiteChar154=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1618);  
					stream_WhiteChar.add(WhiteChar154);

					pushFollow(FOLLOW_constant_in_opcmpcondition1622);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar155=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1624);  
					stream_WhiteChar.add(WhiteChar155);

					DAYS156=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1626);  
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
					// 230:7: -> ^( LinearTrendsCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( String StringToken[\"\\\"TRUE\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:10: ^( LinearTrendsCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( String StringToken[\"\\\"TRUE\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearTrendsCondition, "LinearTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:71: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:106: ^( String StringToken[\"\\\"TRUE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
						adaptor.addChild(root_1, root_2);
						}

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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:1: constantcmp[CommonTree firstOp] : ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )? );
	public final ParameterizedIndicatorsParser.constantcmp_return constantcmp(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.constantcmp_return retval = new ParameterizedIndicatorsParser.constantcmp_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

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
		Token string_literal169=null;
		Token WhiteChar170=null;
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
		Token WhiteChar183=null;
		Token string_literal184=null;
		Token WhiteChar185=null;
		Token WhiteChar186=null;
		Token DAYS187=null;
		Token WhiteChar188=null;
		Token string_literal189=null;
		Token WhiteChar190=null;
		Token WhiteChar191=null;
		Token DAYS192=null;
		Token string_literal193=null;
		Token WhiteChar194=null;
		Token WhiteChar195=null;
		Token string_literal196=null;
		Token WhiteChar197=null;
		Token WhiteChar198=null;
		Token DAYS199=null;
		Token WhiteChar200=null;
		Token string_literal201=null;
		Token WhiteChar202=null;
		Token WhiteChar203=null;
		Token DAYS204=null;
		ParserRuleReturnScope trendSignal =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope threshold =null;

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
		CommonTree string_literal169_tree=null;
		CommonTree WhiteChar170_tree=null;
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
		CommonTree WhiteChar183_tree=null;
		CommonTree string_literal184_tree=null;
		CommonTree WhiteChar185_tree=null;
		CommonTree WhiteChar186_tree=null;
		CommonTree DAYS187_tree=null;
		CommonTree WhiteChar188_tree=null;
		CommonTree string_literal189_tree=null;
		CommonTree WhiteChar190_tree=null;
		CommonTree WhiteChar191_tree=null;
		CommonTree DAYS192_tree=null;
		CommonTree string_literal193_tree=null;
		CommonTree WhiteChar194_tree=null;
		CommonTree WhiteChar195_tree=null;
		CommonTree string_literal196_tree=null;
		CommonTree WhiteChar197_tree=null;
		CommonTree WhiteChar198_tree=null;
		CommonTree DAYS199_tree=null;
		CommonTree WhiteChar200_tree=null;
		CommonTree string_literal201_tree=null;
		CommonTree WhiteChar202_tree=null;
		CommonTree WhiteChar203_tree=null;
		CommonTree DAYS204_tree=null;
		RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
		RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
		RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_trendconstant=new RewriteRuleSubtreeStream(adaptor,"rule trendconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:34: ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )? )
			int alt35=4;
			switch ( input.LA(1) ) {
			case 65:
				{
				alt35=1;
				}
				break;
			case 64:
				{
				alt35=2;
				}
				break;
			case 70:
				{
				alt35=3;
				}
				break;
			case 74:
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:4: 'equals trend' WhiteChar trendSignal= trendconstant
					{
					string_literal157=(Token)match(input,65,FOLLOW_65_in_constantcmp1675);  
					stream_65.add(string_literal157);

					WhiteChar158=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1677);  
					stream_WhiteChar.add(WhiteChar158);

					pushFollow(FOLLOW_trendconstant_in_constantcmp1681);
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
					// 234:55: -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:58: ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualStringConstantCondition, "EqualStringConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_trendconstant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:103: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:234:130: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					int alt31=2;
					int LA31_0 = input.LA(1);
					if ( (LA31_0==WhiteChar) ) {
						int LA31_1 = input.LA(2);
						if ( (LA31_1==81) ) {
							alt31=1;
						}
					}
					switch (alt31) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar159=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1715);  
							stream_WhiteChar.add(WhiteChar159);

							string_literal160=(Token)match(input,81,FOLLOW_81_in_constantcmp1717);  
							stream_81.add(string_literal160);

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

							string_literal165=(Token)match(input,66,FOLLOW_66_in_constantcmp1731);  
							stream_66.add(string_literal165);

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
							// 235:129: -> ^( EqualStringConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:235:132: ^( EqualStringConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:4: 'equals threshold' WhiteChar threshold= constant
					{
					string_literal169=(Token)match(input,64,FOLLOW_64_in_constantcmp1769);  
					stream_64.add(string_literal169);

					WhiteChar170=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1771);  
					stream_WhiteChar.add(WhiteChar170);

					pushFollow(FOLLOW_constant_in_constantcmp1775);
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
					// 237:52: -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:55: ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualConstantCondition, "EqualConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:237:116: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualConstantCondition ) )?
					int alt32=2;
					int LA32_0 = input.LA(1);
					if ( (LA32_0==WhiteChar) ) {
						int LA32_1 = input.LA(2);
						if ( (LA32_1==81) ) {
							alt32=1;
						}
					}
					switch (alt32) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar171=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1809);  
							stream_WhiteChar.add(WhiteChar171);

							string_literal172=(Token)match(input,81,FOLLOW_81_in_constantcmp1811);  
							stream_81.add(string_literal172);

							WhiteChar173=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1813);  
							stream_WhiteChar.add(WhiteChar173);

							pushFollow(FOLLOW_constant_in_constantcmp1817);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar174=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1819);  
							stream_WhiteChar.add(WhiteChar174);

							DAYS175=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1821);  
							stream_DAYS.add(DAYS175);

							WhiteChar176=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1823);  
							stream_WhiteChar.add(WhiteChar176);

							string_literal177=(Token)match(input,66,FOLLOW_66_in_constantcmp1825);  
							stream_66.add(string_literal177);

							WhiteChar178=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1827);  
							stream_WhiteChar.add(WhiteChar178);

							pushFollow(FOLLOW_constant_in_constantcmp1831);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar179=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1833);  
							stream_WhiteChar.add(WhiteChar179);

							DAYS180=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1835);  
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
							// 238:129: -> ^( EqualConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:132: ^( EqualConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:4: 'is above threshold' WhiteChar threshold= constant
					{
					string_literal181=(Token)match(input,70,FOLLOW_70_in_constantcmp1864);  
					stream_70.add(string_literal181);

					WhiteChar182=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1866);  
					stream_WhiteChar.add(WhiteChar182);

					pushFollow(FOLLOW_constant_in_constantcmp1870);
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
					// 240:54: -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:57: ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupConstantCondition, "SupConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:116: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( SupConstantCondition ) )?
					int alt33=2;
					int LA33_0 = input.LA(1);
					if ( (LA33_0==WhiteChar) ) {
						int LA33_1 = input.LA(2);
						if ( (LA33_1==81) ) {
							alt33=1;
						}
					}
					switch (alt33) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar183=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1904);  
							stream_WhiteChar.add(WhiteChar183);

							string_literal184=(Token)match(input,81,FOLLOW_81_in_constantcmp1906);  
							stream_81.add(string_literal184);

							WhiteChar185=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1908);  
							stream_WhiteChar.add(WhiteChar185);

							pushFollow(FOLLOW_constant_in_constantcmp1912);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar186=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1914);  
							stream_WhiteChar.add(WhiteChar186);

							DAYS187=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1916);  
							stream_DAYS.add(DAYS187);

							WhiteChar188=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1918);  
							stream_WhiteChar.add(WhiteChar188);

							string_literal189=(Token)match(input,66,FOLLOW_66_in_constantcmp1920);  
							stream_66.add(string_literal189);

							WhiteChar190=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1922);  
							stream_WhiteChar.add(WhiteChar190);

							pushFollow(FOLLOW_constant_in_constantcmp1926);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar191=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1928);  
							stream_WhiteChar.add(WhiteChar191);

							DAYS192=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp1930);  
							stream_DAYS.add(DAYS192);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 241:129: -> ^( SupConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:132: ^( SupConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:4: 'is below threshold' WhiteChar threshold= constant
					{
					string_literal193=(Token)match(input,74,FOLLOW_74_in_constantcmp1959);  
					stream_74.add(string_literal193);

					WhiteChar194=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1961);  
					stream_WhiteChar.add(WhiteChar194);

					pushFollow(FOLLOW_constant_in_constantcmp1965);
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
					// 243:54: -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:57: ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfConstantCondition, "InfConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:116: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( InfConstantCondition ) )?
					int alt34=2;
					int LA34_0 = input.LA(1);
					if ( (LA34_0==WhiteChar) ) {
						int LA34_1 = input.LA(2);
						if ( (LA34_1==81) ) {
							alt34=1;
						}
					}
					switch (alt34) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar195=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp1999);  
							stream_WhiteChar.add(WhiteChar195);

							string_literal196=(Token)match(input,81,FOLLOW_81_in_constantcmp2001);  
							stream_81.add(string_literal196);

							WhiteChar197=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2003);  
							stream_WhiteChar.add(WhiteChar197);

							pushFollow(FOLLOW_constant_in_constantcmp2007);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar198=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2009);  
							stream_WhiteChar.add(WhiteChar198);

							DAYS199=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2011);  
							stream_DAYS.add(DAYS199);

							WhiteChar200=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2013);  
							stream_WhiteChar.add(WhiteChar200);

							string_literal201=(Token)match(input,66,FOLLOW_66_in_constantcmp2015);  
							stream_66.add(string_literal201);

							WhiteChar202=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2017);  
							stream_WhiteChar.add(WhiteChar202);

							pushFollow(FOLLOW_constant_in_constantcmp2021);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar203=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2023);  
							stream_WhiteChar.add(WhiteChar203);

							DAYS204=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2025);  
							stream_DAYS.add(DAYS204);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 244:129: -> ^( InfConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:244:132: ^( InfConstantCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:1: presetcondition[CommonTree firstOp] : ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherHighCondition ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherLowCondition ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerHighCondition ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerLowCondition ) ) );
	public final ParameterizedIndicatorsParser.presetcondition_return presetcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.presetcondition_return retval = new ParameterizedIndicatorsParser.presetcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal205=null;
		Token WhiteChar206=null;
		Token string_literal207=null;
		Token WhiteChar208=null;
		Token PERCENT209=null;
		Token WhiteChar210=null;
		Token string_literal211=null;
		Token WhiteChar212=null;
		Token WhiteChar213=null;
		Token DAYS214=null;
		Token string_literal215=null;
		Token WhiteChar216=null;
		Token string_literal217=null;
		Token WhiteChar218=null;
		Token PERCENT219=null;
		Token WhiteChar220=null;
		Token string_literal221=null;
		Token WhiteChar222=null;
		Token WhiteChar223=null;
		Token DAYS224=null;
		Token string_literal225=null;
		Token WhiteChar226=null;
		Token PERCENT227=null;
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
		Token PERCENT240=null;
		Token WhiteChar241=null;
		Token string_literal242=null;
		Token WhiteChar243=null;
		Token WhiteChar244=null;
		Token DAYS245=null;
		Token WhiteChar246=null;
		Token string_literal247=null;
		Token WhiteChar248=null;
		Token WhiteChar249=null;
		Token DAYS250=null;
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
		Token string_literal263=null;
		Token WhiteChar264=null;
		Token WhiteChar265=null;
		Token string_literal266=null;
		Token WhiteChar267=null;
		Token WhiteChar268=null;
		Token DAYS269=null;
		Token WhiteChar270=null;
		Token string_literal271=null;
		Token WhiteChar272=null;
		Token WhiteChar273=null;
		Token DAYS274=null;
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
		Token WhiteChar292=null;
		Token DAYS293=null;
		Token WhiteChar294=null;
		Token string_literal295=null;
		Token WhiteChar296=null;
		Token char_literal297=null;
		Token char_literal298=null;
		Token char_literal299=null;
		Token WhiteChar300=null;
		Token string_literal301=null;
		Token WhiteChar302=null;
		Token char_literal303=null;
		Token char_literal304=null;
		Token char_literal305=null;
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
		Token WhiteChar323=null;
		Token DAYS324=null;
		Token WhiteChar325=null;
		Token string_literal326=null;
		Token WhiteChar327=null;
		Token char_literal328=null;
		Token char_literal329=null;
		Token char_literal330=null;
		Token WhiteChar331=null;
		Token string_literal332=null;
		Token WhiteChar333=null;
		Token char_literal334=null;
		Token char_literal335=null;
		Token char_literal336=null;
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
		Token WhiteChar354=null;
		Token DAYS355=null;
		Token WhiteChar356=null;
		Token string_literal357=null;
		Token WhiteChar358=null;
		Token char_literal359=null;
		Token char_literal360=null;
		Token char_literal361=null;
		Token WhiteChar362=null;
		Token string_literal363=null;
		Token WhiteChar364=null;
		Token char_literal365=null;
		Token char_literal366=null;
		Token char_literal367=null;
		Token string_literal368=null;
		Token WhiteChar369=null;
		Token WhiteChar370=null;
		Token DAYS371=null;
		Token WhiteChar372=null;
		Token string_literal373=null;
		Token WhiteChar374=null;
		Token WhiteChar375=null;
		Token DAYS376=null;
		Token WhiteChar377=null;
		Token string_literal378=null;
		Token WhiteChar379=null;
		Token WhiteChar380=null;
		Token DAYS381=null;
		Token WhiteChar382=null;
		Token string_literal383=null;
		Token WhiteChar384=null;
		Token WhiteChar385=null;
		Token DAYS386=null;
		Token WhiteChar387=null;
		Token string_literal388=null;
		Token WhiteChar389=null;
		Token char_literal390=null;
		Token char_literal391=null;
		Token char_literal392=null;
		Token WhiteChar393=null;
		Token string_literal394=null;
		Token WhiteChar395=null;
		Token char_literal396=null;
		Token char_literal397=null;
		Token char_literal398=null;
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

		CommonTree string_literal205_tree=null;
		CommonTree WhiteChar206_tree=null;
		CommonTree string_literal207_tree=null;
		CommonTree WhiteChar208_tree=null;
		CommonTree PERCENT209_tree=null;
		CommonTree WhiteChar210_tree=null;
		CommonTree string_literal211_tree=null;
		CommonTree WhiteChar212_tree=null;
		CommonTree WhiteChar213_tree=null;
		CommonTree DAYS214_tree=null;
		CommonTree string_literal215_tree=null;
		CommonTree WhiteChar216_tree=null;
		CommonTree string_literal217_tree=null;
		CommonTree WhiteChar218_tree=null;
		CommonTree PERCENT219_tree=null;
		CommonTree WhiteChar220_tree=null;
		CommonTree string_literal221_tree=null;
		CommonTree WhiteChar222_tree=null;
		CommonTree WhiteChar223_tree=null;
		CommonTree DAYS224_tree=null;
		CommonTree string_literal225_tree=null;
		CommonTree WhiteChar226_tree=null;
		CommonTree PERCENT227_tree=null;
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
		CommonTree PERCENT240_tree=null;
		CommonTree WhiteChar241_tree=null;
		CommonTree string_literal242_tree=null;
		CommonTree WhiteChar243_tree=null;
		CommonTree WhiteChar244_tree=null;
		CommonTree DAYS245_tree=null;
		CommonTree WhiteChar246_tree=null;
		CommonTree string_literal247_tree=null;
		CommonTree WhiteChar248_tree=null;
		CommonTree WhiteChar249_tree=null;
		CommonTree DAYS250_tree=null;
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
		CommonTree string_literal263_tree=null;
		CommonTree WhiteChar264_tree=null;
		CommonTree WhiteChar265_tree=null;
		CommonTree string_literal266_tree=null;
		CommonTree WhiteChar267_tree=null;
		CommonTree WhiteChar268_tree=null;
		CommonTree DAYS269_tree=null;
		CommonTree WhiteChar270_tree=null;
		CommonTree string_literal271_tree=null;
		CommonTree WhiteChar272_tree=null;
		CommonTree WhiteChar273_tree=null;
		CommonTree DAYS274_tree=null;
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
		CommonTree WhiteChar292_tree=null;
		CommonTree DAYS293_tree=null;
		CommonTree WhiteChar294_tree=null;
		CommonTree string_literal295_tree=null;
		CommonTree WhiteChar296_tree=null;
		CommonTree char_literal297_tree=null;
		CommonTree char_literal298_tree=null;
		CommonTree char_literal299_tree=null;
		CommonTree WhiteChar300_tree=null;
		CommonTree string_literal301_tree=null;
		CommonTree WhiteChar302_tree=null;
		CommonTree char_literal303_tree=null;
		CommonTree char_literal304_tree=null;
		CommonTree char_literal305_tree=null;
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
		CommonTree WhiteChar323_tree=null;
		CommonTree DAYS324_tree=null;
		CommonTree WhiteChar325_tree=null;
		CommonTree string_literal326_tree=null;
		CommonTree WhiteChar327_tree=null;
		CommonTree char_literal328_tree=null;
		CommonTree char_literal329_tree=null;
		CommonTree char_literal330_tree=null;
		CommonTree WhiteChar331_tree=null;
		CommonTree string_literal332_tree=null;
		CommonTree WhiteChar333_tree=null;
		CommonTree char_literal334_tree=null;
		CommonTree char_literal335_tree=null;
		CommonTree char_literal336_tree=null;
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
		CommonTree WhiteChar354_tree=null;
		CommonTree DAYS355_tree=null;
		CommonTree WhiteChar356_tree=null;
		CommonTree string_literal357_tree=null;
		CommonTree WhiteChar358_tree=null;
		CommonTree char_literal359_tree=null;
		CommonTree char_literal360_tree=null;
		CommonTree char_literal361_tree=null;
		CommonTree WhiteChar362_tree=null;
		CommonTree string_literal363_tree=null;
		CommonTree WhiteChar364_tree=null;
		CommonTree char_literal365_tree=null;
		CommonTree char_literal366_tree=null;
		CommonTree char_literal367_tree=null;
		CommonTree string_literal368_tree=null;
		CommonTree WhiteChar369_tree=null;
		CommonTree WhiteChar370_tree=null;
		CommonTree DAYS371_tree=null;
		CommonTree WhiteChar372_tree=null;
		CommonTree string_literal373_tree=null;
		CommonTree WhiteChar374_tree=null;
		CommonTree WhiteChar375_tree=null;
		CommonTree DAYS376_tree=null;
		CommonTree WhiteChar377_tree=null;
		CommonTree string_literal378_tree=null;
		CommonTree WhiteChar379_tree=null;
		CommonTree WhiteChar380_tree=null;
		CommonTree DAYS381_tree=null;
		CommonTree WhiteChar382_tree=null;
		CommonTree string_literal383_tree=null;
		CommonTree WhiteChar384_tree=null;
		CommonTree WhiteChar385_tree=null;
		CommonTree DAYS386_tree=null;
		CommonTree WhiteChar387_tree=null;
		CommonTree string_literal388_tree=null;
		CommonTree WhiteChar389_tree=null;
		CommonTree char_literal390_tree=null;
		CommonTree char_literal391_tree=null;
		CommonTree char_literal392_tree=null;
		CommonTree WhiteChar393_tree=null;
		CommonTree string_literal394_tree=null;
		CommonTree WhiteChar395_tree=null;
		CommonTree char_literal396_tree=null;
		CommonTree char_literal397_tree=null;
		CommonTree char_literal398_tree=null;
		RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
		RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
		RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
		RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
		RewriteRuleTokenStream stream_59=new RewriteRuleTokenStream(adaptor,"token 59");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
		RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
		RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
		RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
		RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
		RewriteRuleTokenStream stream_51=new RewriteRuleTokenStream(adaptor,"token 51");
		RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
		RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");
		RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
		RewriteRuleTokenStream stream_53=new RewriteRuleTokenStream(adaptor,"token 53");
		RewriteRuleTokenStream stream_54=new RewriteRuleTokenStream(adaptor,"token 54");
		RewriteRuleTokenStream stream_87=new RewriteRuleTokenStream(adaptor,"token 87");
		RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:38: ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherHighCondition ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherLowCondition ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerHighCondition ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerLowCondition ) ) )
			int alt42=10;
			switch ( input.LA(1) ) {
			case 83:
				{
				alt42=1;
				}
				break;
			case 84:
				{
				alt42=2;
				}
				break;
			case 67:
				{
				alt42=3;
				}
				break;
			case 68:
				{
				alt42=4;
				}
				break;
			case 61:
				{
				alt42=5;
				}
				break;
			case 59:
				{
				alt42=6;
				}
				break;
			case 76:
				{
				alt42=7;
				}
				break;
			case 77:
				{
				alt42=8;
				}
				break;
			case 78:
				{
				alt42=9;
				}
				break;
			case 79:
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:4: 'reverses down'
					{
					string_literal205=(Token)match(input,83,FOLLOW_83_in_presetcondition2057);  
					stream_83.add(string_literal205);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 249:20: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:23: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:42: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:70: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:249:99: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:7: ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					int alt36=2;
					int LA36_0 = input.LA(1);
					if ( (LA36_0==WhiteChar) ) {
						int LA36_1 = input.LA(2);
						if ( (LA36_1==80) ) {
							alt36=1;
						}
					}
					switch (alt36) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:9: WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar206=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2097);  
							stream_WhiteChar.add(WhiteChar206);

							string_literal207=(Token)match(input,80,FOLLOW_80_in_presetcondition2099);  
							stream_80.add(string_literal207);

							WhiteChar208=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2101);  
							stream_WhiteChar.add(WhiteChar208);

							pushFollow(FOLLOW_constant_in_presetcondition2105);
							percentdown=constant();
							state._fsp--;

							stream_constant.add(percentdown.getTree());
							PERCENT209=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2107);  
							stream_PERCENT.add(PERCENT209);

							WhiteChar210=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2109);  
							stream_WhiteChar.add(WhiteChar210);

							string_literal211=(Token)match(input,86,FOLLOW_86_in_presetcondition2111);  
							stream_86.add(string_literal211);

							WhiteChar212=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2113);  
							stream_WhiteChar.add(WhiteChar212);

							pushFollow(FOLLOW_constant_in_presetcondition2117);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar213=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2119);  
							stream_WhiteChar.add(WhiteChar213);

							DAYS214=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2121);  
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
							// 251:7: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:10: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:29: ^( Number NumberToken[\"-1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:4: 'reverses up'
					{
					string_literal215=(Token)match(input,84,FOLLOW_84_in_presetcondition2165);  
					stream_84.add(string_literal215);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 253:18: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:21: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:40: ^( Number NumberToken[\"1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:67: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:253:96: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:7: ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:9: WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar216=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2205);  
							stream_WhiteChar.add(WhiteChar216);

							string_literal217=(Token)match(input,80,FOLLOW_80_in_presetcondition2207);  
							stream_80.add(string_literal217);

							WhiteChar218=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2209);  
							stream_WhiteChar.add(WhiteChar218);

							pushFollow(FOLLOW_constant_in_presetcondition2213);
							percentup=constant();
							state._fsp--;

							stream_constant.add(percentup.getTree());
							PERCENT219=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2215);  
							stream_PERCENT.add(PERCENT219);

							WhiteChar220=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2217);  
							stream_WhiteChar.add(WhiteChar220);

							string_literal221=(Token)match(input,86,FOLLOW_86_in_presetcondition2219);  
							stream_86.add(string_literal221);

							WhiteChar222=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2221);  
							stream_WhiteChar.add(WhiteChar222);

							pushFollow(FOLLOW_constant_in_presetcondition2225);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar223=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2227);  
							stream_WhiteChar.add(WhiteChar223);

							DAYS224=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2229);  
							stream_DAYS.add(DAYS224);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 255:7: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:10: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:29: ^( Number NumberToken[\"1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:4: 'goes down more than' WhiteChar percentdown= constant PERCENT
					{
					string_literal225=(Token)match(input,67,FOLLOW_67_in_presetcondition2272);  
					stream_67.add(string_literal225);

					WhiteChar226=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2274);  
					stream_WhiteChar.add(WhiteChar226);

					pushFollow(FOLLOW_constant_in_presetcondition2278);
					percentdown=constant();
					state._fsp--;

					stream_constant.add(percentdown.getTree());
					PERCENT227=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2280);  
					stream_PERCENT.add(PERCENT227);

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
					// 257:65: -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:68: ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:98: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:127: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:257:156: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt38=2;
					int LA38_0 = input.LA(1);
					if ( (LA38_0==WhiteChar) ) {
						int LA38_1 = input.LA(2);
						if ( (LA38_1==86) ) {
							alt38=1;
						}
					}
					switch (alt38) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:258:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar228=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2322);  
							stream_WhiteChar.add(WhiteChar228);

							string_literal229=(Token)match(input,86,FOLLOW_86_in_presetcondition2324);  
							stream_86.add(string_literal229);

							WhiteChar230=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2326);  
							stream_WhiteChar.add(WhiteChar230);

							pushFollow(FOLLOW_constant_in_presetcondition2330);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar231=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2332);  
							stream_WhiteChar.add(WhiteChar231);

							DAYS232=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2334);  
							stream_DAYS.add(DAYS232);

							WhiteChar233=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2345);  
							stream_WhiteChar.add(WhiteChar233);

							string_literal234=(Token)match(input,66,FOLLOW_66_in_presetcondition2347);  
							stream_66.add(string_literal234);

							WhiteChar235=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2349);  
							stream_WhiteChar.add(WhiteChar235);

							pushFollow(FOLLOW_constant_in_presetcondition2353);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar236=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2355);  
							stream_WhiteChar.add(WhiteChar236);

							DAYS237=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2357);  
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
							// 260:7: -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:10: ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentdown!=null?((CommonTree)percentdown.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:75: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:4: 'goes up more than' WhiteChar percentup= constant PERCENT
					{
					string_literal238=(Token)match(input,68,FOLLOW_68_in_presetcondition2395);  
					stream_68.add(string_literal238);

					WhiteChar239=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2397);  
					stream_WhiteChar.add(WhiteChar239);

					pushFollow(FOLLOW_constant_in_presetcondition2401);
					percentup=constant();
					state._fsp--;

					stream_constant.add(percentup.getTree());
					PERCENT240=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2403);  
					stream_PERCENT.add(PERCENT240);

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
					// 261:61: -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:64: ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:92: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:121: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:150: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt39=2;
					int LA39_0 = input.LA(1);
					if ( (LA39_0==WhiteChar) ) {
						int LA39_1 = input.LA(2);
						if ( (LA39_1==86) ) {
							alt39=1;
						}
					}
					switch (alt39) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:262:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar241=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2445);  
							stream_WhiteChar.add(WhiteChar241);

							string_literal242=(Token)match(input,86,FOLLOW_86_in_presetcondition2447);  
							stream_86.add(string_literal242);

							WhiteChar243=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2449);  
							stream_WhiteChar.add(WhiteChar243);

							pushFollow(FOLLOW_constant_in_presetcondition2453);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar244=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2455);  
							stream_WhiteChar.add(WhiteChar244);

							DAYS245=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2457);  
							stream_DAYS.add(DAYS245);

							WhiteChar246=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2468);  
							stream_WhiteChar.add(WhiteChar246);

							string_literal247=(Token)match(input,66,FOLLOW_66_in_presetcondition2470);  
							stream_66.add(string_literal247);

							WhiteChar248=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2472);  
							stream_WhiteChar.add(WhiteChar248);

							pushFollow(FOLLOW_constant_in_presetcondition2476);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar249=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2478);  
							stream_WhiteChar.add(WhiteChar249);

							DAYS250=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2480);  
							stream_DAYS.add(DAYS250);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 264:7: -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:10: ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentup!=null?((CommonTree)percentup.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:71: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:4: 'crosses up threshold' WhiteChar threshold= constant
					{
					string_literal251=(Token)match(input,61,FOLLOW_61_in_presetcondition2525);  
					stream_61.add(string_literal251);

					WhiteChar252=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2527);  
					stream_WhiteChar.add(WhiteChar252);

					pushFollow(FOLLOW_constant_in_presetcondition2531);
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
					// 266:56: -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:59: ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:95: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:124: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:153: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt40=2;
					int LA40_0 = input.LA(1);
					if ( (LA40_0==WhiteChar) ) {
						int LA40_1 = input.LA(2);
						if ( (LA40_1==86) ) {
							alt40=1;
						}
					}
					switch (alt40) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar253=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2573);  
							stream_WhiteChar.add(WhiteChar253);

							string_literal254=(Token)match(input,86,FOLLOW_86_in_presetcondition2575);  
							stream_86.add(string_literal254);

							WhiteChar255=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2577);  
							stream_WhiteChar.add(WhiteChar255);

							pushFollow(FOLLOW_constant_in_presetcondition2581);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar256=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2583);  
							stream_WhiteChar.add(WhiteChar256);

							DAYS257=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2585);  
							stream_DAYS.add(DAYS257);

							WhiteChar258=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2596);  
							stream_WhiteChar.add(WhiteChar258);

							string_literal259=(Token)match(input,81,FOLLOW_81_in_presetcondition2598);  
							stream_81.add(string_literal259);

							WhiteChar260=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2600);  
							stream_WhiteChar.add(WhiteChar260);

							pushFollow(FOLLOW_constant_in_presetcondition2604);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar261=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2606);  
							stream_WhiteChar.add(WhiteChar261);

							DAYS262=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2608);  
							stream_DAYS.add(DAYS262);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 269:7: -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:269:10: ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:269:97: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:4: 'crosses down threshold' WhiteChar threshold= constant
					{
					string_literal263=(Token)match(input,59,FOLLOW_59_in_presetcondition2654);  
					stream_59.add(string_literal263);

					WhiteChar264=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2656);  
					stream_WhiteChar.add(WhiteChar264);

					pushFollow(FOLLOW_constant_in_presetcondition2660);
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
					// 271:58: -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:61: ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:99: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:128: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:157: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:272:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt41=2;
					int LA41_0 = input.LA(1);
					if ( (LA41_0==WhiteChar) ) {
						int LA41_1 = input.LA(2);
						if ( (LA41_1==86) ) {
							alt41=1;
						}
					}
					switch (alt41) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:272:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS
							{
							WhiteChar265=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2702);  
							stream_WhiteChar.add(WhiteChar265);

							string_literal266=(Token)match(input,86,FOLLOW_86_in_presetcondition2704);  
							stream_86.add(string_literal266);

							WhiteChar267=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2706);  
							stream_WhiteChar.add(WhiteChar267);

							pushFollow(FOLLOW_constant_in_presetcondition2710);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar268=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2712);  
							stream_WhiteChar.add(WhiteChar268);

							DAYS269=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2714);  
							stream_DAYS.add(DAYS269);

							WhiteChar270=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2725);  
							stream_WhiteChar.add(WhiteChar270);

							string_literal271=(Token)match(input,81,FOLLOW_81_in_presetcondition2727);  
							stream_81.add(string_literal271);

							WhiteChar272=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2729);  
							stream_WhiteChar.add(WhiteChar272);

							pushFollow(FOLLOW_constant_in_presetcondition2733);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar273=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2735);  
							stream_WhiteChar.add(WhiteChar273);

							DAYS274=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2737);  
							stream_DAYS.add(DAYS274);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 274:7: -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:10: ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:99: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherHighCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherHighCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:4: 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']'
					{
					string_literal275=(Token)match(input,76,FOLLOW_76_in_presetcondition2775);  
					stream_76.add(string_literal275);

					WhiteChar276=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2777);  
					stream_WhiteChar.add(WhiteChar276);

					pushFollow(FOLLOW_constant_in_presetcondition2781);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar277=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2783);  
					stream_WhiteChar.add(WhiteChar277);

					DAYS278=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2785);  
					stream_DAYS.add(DAYS278);

					WhiteChar279=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2792);  
					stream_WhiteChar.add(WhiteChar279);

					string_literal280=(Token)match(input,81,FOLLOW_81_in_presetcondition2794);  
					stream_81.add(string_literal280);

					WhiteChar281=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2796);  
					stream_WhiteChar.add(WhiteChar281);

					pushFollow(FOLLOW_constant_in_presetcondition2800);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar282=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2802);  
					stream_WhiteChar.add(WhiteChar282);

					DAYS283=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2804);  
					stream_DAYS.add(DAYS283);

					WhiteChar284=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2811);  
					stream_WhiteChar.add(WhiteChar284);

					string_literal285=(Token)match(input,66,FOLLOW_66_in_presetcondition2813);  
					stream_66.add(string_literal285);

					WhiteChar286=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2815);  
					stream_WhiteChar.add(WhiteChar286);

					pushFollow(FOLLOW_constant_in_presetcondition2819);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar287=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2821);  
					stream_WhiteChar.add(WhiteChar287);

					DAYS288=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2823);  
					stream_DAYS.add(DAYS288);

					WhiteChar289=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2830);  
					stream_WhiteChar.add(WhiteChar289);

					string_literal290=(Token)match(input,85,FOLLOW_85_in_presetcondition2832);  
					stream_85.add(string_literal290);

					WhiteChar291=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2834);  
					stream_WhiteChar.add(WhiteChar291);

					pushFollow(FOLLOW_constant_in_presetcondition2838);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar292=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2840);  
					stream_WhiteChar.add(WhiteChar292);

					DAYS293=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2842);  
					stream_DAYS.add(DAYS293);

					WhiteChar294=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2849);  
					stream_WhiteChar.add(WhiteChar294);

					string_literal295=(Token)match(input,87,FOLLOW_87_in_presetcondition2851);  
					stream_87.add(string_literal295);

					WhiteChar296=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2853);  
					stream_WhiteChar.add(WhiteChar296);

					char_literal297=(Token)match(input,53,FOLLOW_53_in_presetcondition2855);  
					stream_53.add(char_literal297);

					pushFollow(FOLLOW_constant_in_presetcondition2859);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal298=(Token)match(input,51,FOLLOW_51_in_presetcondition2861);  
					stream_51.add(char_literal298);

					pushFollow(FOLLOW_constant_in_presetcondition2865);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal299=(Token)match(input,54,FOLLOW_54_in_presetcondition2867);  
					stream_54.add(char_literal299);

					WhiteChar300=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2869);  
					stream_WhiteChar.add(WhiteChar300);

					string_literal301=(Token)match(input,62,FOLLOW_62_in_presetcondition2871);  
					stream_62.add(string_literal301);

					WhiteChar302=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2873);  
					stream_WhiteChar.add(WhiteChar302);

					char_literal303=(Token)match(input,53,FOLLOW_53_in_presetcondition2875);  
					stream_53.add(char_literal303);

					pushFollow(FOLLOW_constant_in_presetcondition2879);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal304=(Token)match(input,51,FOLLOW_51_in_presetcondition2881);  
					stream_51.add(char_literal304);

					pushFollow(FOLLOW_constant_in_presetcondition2885);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal305=(Token)match(input,54,FOLLOW_54_in_presetcondition2887);  
					stream_54.add(char_literal305);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 281:4: -> ^( HigherHighCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:281:7: ^( HigherHighCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherLowCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( HigherLowCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:4: 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']'
					{
					string_literal306=(Token)match(input,77,FOLLOW_77_in_presetcondition2923);  
					stream_77.add(string_literal306);

					WhiteChar307=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2925);  
					stream_WhiteChar.add(WhiteChar307);

					pushFollow(FOLLOW_constant_in_presetcondition2929);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar308=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2931);  
					stream_WhiteChar.add(WhiteChar308);

					DAYS309=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2933);  
					stream_DAYS.add(DAYS309);

					WhiteChar310=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2940);  
					stream_WhiteChar.add(WhiteChar310);

					string_literal311=(Token)match(input,81,FOLLOW_81_in_presetcondition2942);  
					stream_81.add(string_literal311);

					WhiteChar312=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2944);  
					stream_WhiteChar.add(WhiteChar312);

					pushFollow(FOLLOW_constant_in_presetcondition2948);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar313=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2950);  
					stream_WhiteChar.add(WhiteChar313);

					DAYS314=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2952);  
					stream_DAYS.add(DAYS314);

					WhiteChar315=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2959);  
					stream_WhiteChar.add(WhiteChar315);

					string_literal316=(Token)match(input,66,FOLLOW_66_in_presetcondition2961);  
					stream_66.add(string_literal316);

					WhiteChar317=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2963);  
					stream_WhiteChar.add(WhiteChar317);

					pushFollow(FOLLOW_constant_in_presetcondition2967);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar318=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2969);  
					stream_WhiteChar.add(WhiteChar318);

					DAYS319=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2971);  
					stream_DAYS.add(DAYS319);

					WhiteChar320=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2978);  
					stream_WhiteChar.add(WhiteChar320);

					string_literal321=(Token)match(input,85,FOLLOW_85_in_presetcondition2980);  
					stream_85.add(string_literal321);

					WhiteChar322=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2982);  
					stream_WhiteChar.add(WhiteChar322);

					pushFollow(FOLLOW_constant_in_presetcondition2986);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar323=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2988);  
					stream_WhiteChar.add(WhiteChar323);

					DAYS324=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2990);  
					stream_DAYS.add(DAYS324);

					WhiteChar325=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2997);  
					stream_WhiteChar.add(WhiteChar325);

					string_literal326=(Token)match(input,87,FOLLOW_87_in_presetcondition2999);  
					stream_87.add(string_literal326);

					WhiteChar327=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3001);  
					stream_WhiteChar.add(WhiteChar327);

					char_literal328=(Token)match(input,53,FOLLOW_53_in_presetcondition3003);  
					stream_53.add(char_literal328);

					pushFollow(FOLLOW_constant_in_presetcondition3007);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal329=(Token)match(input,51,FOLLOW_51_in_presetcondition3009);  
					stream_51.add(char_literal329);

					pushFollow(FOLLOW_constant_in_presetcondition3013);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal330=(Token)match(input,54,FOLLOW_54_in_presetcondition3015);  
					stream_54.add(char_literal330);

					WhiteChar331=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3017);  
					stream_WhiteChar.add(WhiteChar331);

					string_literal332=(Token)match(input,62,FOLLOW_62_in_presetcondition3019);  
					stream_62.add(string_literal332);

					WhiteChar333=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3021);  
					stream_WhiteChar.add(WhiteChar333);

					char_literal334=(Token)match(input,53,FOLLOW_53_in_presetcondition3023);  
					stream_53.add(char_literal334);

					pushFollow(FOLLOW_constant_in_presetcondition3027);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal335=(Token)match(input,51,FOLLOW_51_in_presetcondition3029);  
					stream_51.add(char_literal335);

					pushFollow(FOLLOW_constant_in_presetcondition3033);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal336=(Token)match(input,54,FOLLOW_54_in_presetcondition3035);  
					stream_54.add(char_literal336);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 287:4: -> ^( HigherLowCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:7: ^( HigherLowCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerHighCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerHighCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:4: 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']'
					{
					string_literal337=(Token)match(input,78,FOLLOW_78_in_presetcondition3071);  
					stream_78.add(string_literal337);

					WhiteChar338=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3073);  
					stream_WhiteChar.add(WhiteChar338);

					pushFollow(FOLLOW_constant_in_presetcondition3077);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar339=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3079);  
					stream_WhiteChar.add(WhiteChar339);

					DAYS340=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3081);  
					stream_DAYS.add(DAYS340);

					WhiteChar341=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3088);  
					stream_WhiteChar.add(WhiteChar341);

					string_literal342=(Token)match(input,81,FOLLOW_81_in_presetcondition3090);  
					stream_81.add(string_literal342);

					WhiteChar343=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3092);  
					stream_WhiteChar.add(WhiteChar343);

					pushFollow(FOLLOW_constant_in_presetcondition3096);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar344=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3098);  
					stream_WhiteChar.add(WhiteChar344);

					DAYS345=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3100);  
					stream_DAYS.add(DAYS345);

					WhiteChar346=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3107);  
					stream_WhiteChar.add(WhiteChar346);

					string_literal347=(Token)match(input,66,FOLLOW_66_in_presetcondition3109);  
					stream_66.add(string_literal347);

					WhiteChar348=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3111);  
					stream_WhiteChar.add(WhiteChar348);

					pushFollow(FOLLOW_constant_in_presetcondition3115);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar349=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3117);  
					stream_WhiteChar.add(WhiteChar349);

					DAYS350=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3119);  
					stream_DAYS.add(DAYS350);

					WhiteChar351=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3126);  
					stream_WhiteChar.add(WhiteChar351);

					string_literal352=(Token)match(input,85,FOLLOW_85_in_presetcondition3128);  
					stream_85.add(string_literal352);

					WhiteChar353=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3130);  
					stream_WhiteChar.add(WhiteChar353);

					pushFollow(FOLLOW_constant_in_presetcondition3134);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar354=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3136);  
					stream_WhiteChar.add(WhiteChar354);

					DAYS355=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3138);  
					stream_DAYS.add(DAYS355);

					WhiteChar356=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3145);  
					stream_WhiteChar.add(WhiteChar356);

					string_literal357=(Token)match(input,87,FOLLOW_87_in_presetcondition3147);  
					stream_87.add(string_literal357);

					WhiteChar358=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3149);  
					stream_WhiteChar.add(WhiteChar358);

					char_literal359=(Token)match(input,53,FOLLOW_53_in_presetcondition3151);  
					stream_53.add(char_literal359);

					pushFollow(FOLLOW_constant_in_presetcondition3155);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal360=(Token)match(input,51,FOLLOW_51_in_presetcondition3157);  
					stream_51.add(char_literal360);

					pushFollow(FOLLOW_constant_in_presetcondition3161);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal361=(Token)match(input,54,FOLLOW_54_in_presetcondition3163);  
					stream_54.add(char_literal361);

					WhiteChar362=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3165);  
					stream_WhiteChar.add(WhiteChar362);

					string_literal363=(Token)match(input,62,FOLLOW_62_in_presetcondition3167);  
					stream_62.add(string_literal363);

					WhiteChar364=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3169);  
					stream_WhiteChar.add(WhiteChar364);

					char_literal365=(Token)match(input,53,FOLLOW_53_in_presetcondition3171);  
					stream_53.add(char_literal365);

					pushFollow(FOLLOW_constant_in_presetcondition3175);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal366=(Token)match(input,51,FOLLOW_51_in_presetcondition3177);  
					stream_51.add(char_literal366);

					pushFollow(FOLLOW_constant_in_presetcondition3181);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal367=(Token)match(input,54,FOLLOW_54_in_presetcondition3183);  
					stream_54.add(char_literal367);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 293:4: -> ^( LowerHighCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:293:7: ^( LowerHighCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerLowCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' -> ^( LowerLowCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:4: 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']'
					{
					string_literal368=(Token)match(input,79,FOLLOW_79_in_presetcondition3219);  
					stream_79.add(string_literal368);

					WhiteChar369=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3221);  
					stream_WhiteChar.add(WhiteChar369);

					pushFollow(FOLLOW_constant_in_presetcondition3225);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar370=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3227);  
					stream_WhiteChar.add(WhiteChar370);

					DAYS371=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3229);  
					stream_DAYS.add(DAYS371);

					WhiteChar372=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3236);  
					stream_WhiteChar.add(WhiteChar372);

					string_literal373=(Token)match(input,81,FOLLOW_81_in_presetcondition3238);  
					stream_81.add(string_literal373);

					WhiteChar374=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3240);  
					stream_WhiteChar.add(WhiteChar374);

					pushFollow(FOLLOW_constant_in_presetcondition3244);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar375=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3246);  
					stream_WhiteChar.add(WhiteChar375);

					DAYS376=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3248);  
					stream_DAYS.add(DAYS376);

					WhiteChar377=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3255);  
					stream_WhiteChar.add(WhiteChar377);

					string_literal378=(Token)match(input,66,FOLLOW_66_in_presetcondition3257);  
					stream_66.add(string_literal378);

					WhiteChar379=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3259);  
					stream_WhiteChar.add(WhiteChar379);

					pushFollow(FOLLOW_constant_in_presetcondition3263);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar380=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3265);  
					stream_WhiteChar.add(WhiteChar380);

					DAYS381=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3267);  
					stream_DAYS.add(DAYS381);

					WhiteChar382=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3274);  
					stream_WhiteChar.add(WhiteChar382);

					string_literal383=(Token)match(input,85,FOLLOW_85_in_presetcondition3276);  
					stream_85.add(string_literal383);

					WhiteChar384=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3278);  
					stream_WhiteChar.add(WhiteChar384);

					pushFollow(FOLLOW_constant_in_presetcondition3282);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar385=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3284);  
					stream_WhiteChar.add(WhiteChar385);

					DAYS386=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3286);  
					stream_DAYS.add(DAYS386);

					WhiteChar387=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3293);  
					stream_WhiteChar.add(WhiteChar387);

					string_literal388=(Token)match(input,87,FOLLOW_87_in_presetcondition3295);  
					stream_87.add(string_literal388);

					WhiteChar389=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3297);  
					stream_WhiteChar.add(WhiteChar389);

					char_literal390=(Token)match(input,53,FOLLOW_53_in_presetcondition3299);  
					stream_53.add(char_literal390);

					pushFollow(FOLLOW_constant_in_presetcondition3303);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal391=(Token)match(input,51,FOLLOW_51_in_presetcondition3305);  
					stream_51.add(char_literal391);

					pushFollow(FOLLOW_constant_in_presetcondition3309);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal392=(Token)match(input,54,FOLLOW_54_in_presetcondition3311);  
					stream_54.add(char_literal392);

					WhiteChar393=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3313);  
					stream_WhiteChar.add(WhiteChar393);

					string_literal394=(Token)match(input,62,FOLLOW_62_in_presetcondition3315);  
					stream_62.add(string_literal394);

					WhiteChar395=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3317);  
					stream_WhiteChar.add(WhiteChar395);

					char_literal396=(Token)match(input,53,FOLLOW_53_in_presetcondition3319);  
					stream_53.add(char_literal396);

					pushFollow(FOLLOW_constant_in_presetcondition3323);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal397=(Token)match(input,51,FOLLOW_51_in_presetcondition3325);  
					stream_51.add(char_literal397);

					pushFollow(FOLLOW_constant_in_presetcondition3329);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal398=(Token)match(input,54,FOLLOW_54_in_presetcondition3331);  
					stream_54.add(char_literal398);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 299:4: -> ^( LowerLowCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:7: ^( LowerLowCondition )
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



	public static final BitSet FOLLOW_bullish_condition_in_complete_expression350 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000180L});
	public static final BitSet FOLLOW_bearish_condition_in_complete_expression352 = new BitSet(new long[]{0x0080000000000000L,0x0000000000040000L});
	public static final BitSet FOLLOW_also_display_in_complete_expression355 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
	public static final BitSet FOLLOW_fixed_start_shift_in_complete_expression357 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_75_in_bullish_condition387 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition389 = new BitSet(new long[]{0x0000001420200000L});
	public static final BitSet FOLLOW_primary_expression_in_bullish_condition391 = new BitSet(new long[]{0x0004000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition393 = new BitSet(new long[]{0x0004000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bullish_condition396 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition398 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_72_in_bearish_condition414 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition416 = new BitSet(new long[]{0x0000001420200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_condition419 = new BitSet(new long[]{0x0004000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition421 = new BitSet(new long[]{0x0004000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bearish_condition424 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition426 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_bearish_not_bullish_in_bearish_condition436 = new BitSet(new long[]{0x0004000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition439 = new BitSet(new long[]{0x0004000000000080L});
	public static final BitSet FOLLOW_COMMA_in_bearish_condition442 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition444 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_55_in_also_display461 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display463 = new BitSet(new long[]{0x0000001420200000L});
	public static final BitSet FOLLOW_primary_expression_in_also_display465 = new BitSet(new long[]{0x0004000000000080L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display467 = new BitSet(new long[]{0x0004000000000080L});
	public static final BitSet FOLLOW_COMMA_in_also_display470 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_82_in_fixed_start_shift505 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift507 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_fixed_start_shift511 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift513 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_fixed_start_shift515 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_COMMA_in_fixed_start_shift517 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_71_in_bearish_not_bullish547 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish556 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_bearish_not_bullish558 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish560 = new BitSet(new long[]{0x0000001420200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish562 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish589 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_OR_in_bearish_not_bullish591 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish593 = new BitSet(new long[]{0x0000001420200000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish596 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_and_expression_in_primary_expression636 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_expression_in_and_expression649 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_lenient_in_and_expression653 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression656 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_and_expression658 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression660 = new BitSet(new long[]{0x0000001420200000L});
	public static final BitSet FOLLOW_or_expression_in_and_expression662 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_atom_in_or_expression690 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression693 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_OR_in_or_expression695 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression697 = new BitSet(new long[]{0x0000001420200000L});
	public static final BitSet FOLLOW_atom_in_or_expression699 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_booleanhistory_in_atom725 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom732 = new BitSet(new long[]{0x0004001420200000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom734 = new BitSet(new long[]{0x0004001420200000L});
	public static final BitSet FOLLOW_primary_expression_in_atom737 = new BitSet(new long[]{0x0004000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom739 = new BitSet(new long[]{0x0004000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom742 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_atom753 = new BitSet(new long[]{0x0004000400000000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom755 = new BitSet(new long[]{0x0004000400000000L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom758 = new BitSet(new long[]{0x0004001420200000L});
	public static final BitSet FOLLOW_WhiteChar_in_atom760 = new BitSet(new long[]{0x0004001420200000L});
	public static final BitSet FOLLOW_primary_expression_in_atom763 = new BitSet(new long[]{0x0004000000000040L});
	public static final BitSet FOLLOW_WhiteChar_in_atom765 = new BitSet(new long[]{0x0004000000000040L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom768 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_booleanhistory790 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_booleanhistory792 = new BitSet(new long[]{0xBC00000000000000L,0x000000000718F67BL});
	public static final BitSet FOLLOW_presetcondition_in_booleanhistory796 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_opcmpcondition_in_booleanhistory805 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constantcmp_in_booleanhistory813 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_operand827 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Operation_in_operand854 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NumberToken_in_constant868 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_52_in_constant880 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_57_in_trendconstant896 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_56_in_trendconstant909 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_lenient926 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_LENIENT_in_lenient928 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_opcmpcondition964 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition966 = new BitSet(new long[]{0x0000001000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition970 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition996 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_opcmpcondition998 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1000 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1004 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1006 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1008 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_73_in_opcmpcondition1030 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1032 = new BitSet(new long[]{0x0000001000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1036 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1064 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_opcmpcondition1066 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1068 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1072 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1074 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1076 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_63_in_opcmpcondition1098 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1100 = new BitSet(new long[]{0x0000001000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1104 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1131 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_opcmpcondition1133 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1135 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1139 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1141 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1143 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_58_in_opcmpcondition1166 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1168 = new BitSet(new long[]{0x0000001000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1170 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1207 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_86_in_opcmpcondition1209 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1211 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1215 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1217 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1219 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1232 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_opcmpcondition1234 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1236 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1240 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1242 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1244 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_60_in_opcmpcondition1280 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1282 = new BitSet(new long[]{0x0000001000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1284 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1320 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_86_in_opcmpcondition1322 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1324 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1328 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1330 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1332 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1343 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_opcmpcondition1345 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1347 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1351 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1353 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1355 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_89_in_opcmpcondition1390 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1392 = new BitSet(new long[]{0x0000001000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1396 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1404 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_opcmpcondition1406 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1408 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1412 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1414 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1416 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1424 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_opcmpcondition1426 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1428 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1432 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1434 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1436 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_90_in_opcmpcondition1485 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1487 = new BitSet(new long[]{0x0000001000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1491 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1499 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_opcmpcondition1501 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1503 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1507 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1509 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1511 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1519 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_opcmpcondition1521 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1523 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1527 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1529 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1531 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_88_in_opcmpcondition1580 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1582 = new BitSet(new long[]{0x0000001000200000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1586 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1594 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_opcmpcondition1596 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1598 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1602 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1604 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1606 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1614 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_opcmpcondition1616 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1618 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1622 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1624 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1626 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_constantcmp1675 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1677 = new BitSet(new long[]{0x0300000000000000L});
	public static final BitSet FOLLOW_trendconstant_in_constantcmp1681 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1715 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_constantcmp1717 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1719 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1723 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1725 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1727 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1729 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_constantcmp1731 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1733 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1737 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1739 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1741 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_64_in_constantcmp1769 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1771 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1775 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1809 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_constantcmp1811 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1813 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1817 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1819 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1821 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1823 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_constantcmp1825 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1827 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1831 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1833 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1835 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_70_in_constantcmp1864 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1866 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1870 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1904 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_constantcmp1906 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1908 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1912 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1914 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1916 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1918 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_constantcmp1920 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1922 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1926 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1928 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp1930 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_74_in_constantcmp1959 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1961 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp1965 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp1999 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_constantcmp2001 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2003 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2007 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2009 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2011 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2013 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_constantcmp2015 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2017 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_constantcmp2021 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2023 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2025 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_83_in_presetcondition2057 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2097 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition2099 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2101 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2105 = new BitSet(new long[]{0x0000008000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2107 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2109 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_86_in_presetcondition2111 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2113 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2117 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2119 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2121 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_84_in_presetcondition2165 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2205 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_presetcondition2207 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2209 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2213 = new BitSet(new long[]{0x0000008000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2215 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2217 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_86_in_presetcondition2219 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2221 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2225 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2227 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2229 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_67_in_presetcondition2272 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2274 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2278 = new BitSet(new long[]{0x0000008000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2280 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2322 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_86_in_presetcondition2324 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2326 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2330 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2332 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2334 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2345 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_presetcondition2347 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2349 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2353 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2355 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2357 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_68_in_presetcondition2395 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2397 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2401 = new BitSet(new long[]{0x0000008000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2403 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2445 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_86_in_presetcondition2447 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2449 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2453 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2455 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2457 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2468 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_presetcondition2470 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2472 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2476 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2478 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2480 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_61_in_presetcondition2525 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2527 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2531 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2573 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_86_in_presetcondition2575 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2577 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2581 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2583 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2585 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2596 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_presetcondition2598 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2600 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2604 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2606 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2608 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_59_in_presetcondition2654 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2656 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2660 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2702 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_86_in_presetcondition2704 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2706 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2710 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2712 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2714 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2725 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_presetcondition2727 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2729 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2733 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2735 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2737 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_76_in_presetcondition2775 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2777 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2781 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2783 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2785 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2792 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_presetcondition2794 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2796 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2800 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2802 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2804 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2811 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_presetcondition2813 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2815 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2819 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2821 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2823 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2830 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_presetcondition2832 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2834 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2838 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2840 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2842 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2849 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_87_in_presetcondition2851 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2853 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition2855 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2859 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_presetcondition2861 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2865 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_54_in_presetcondition2867 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2869 = new BitSet(new long[]{0x4000000000000000L});
	public static final BitSet FOLLOW_62_in_presetcondition2871 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2873 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition2875 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2879 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_presetcondition2881 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2885 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_54_in_presetcondition2887 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_77_in_presetcondition2923 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2925 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2929 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2931 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2933 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2940 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_presetcondition2942 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2944 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2948 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2950 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2952 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2959 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_presetcondition2961 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2963 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2967 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2969 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2971 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2978 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_presetcondition2980 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2982 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition2986 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2988 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2990 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2997 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_87_in_presetcondition2999 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3001 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition3003 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3007 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_presetcondition3009 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3013 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_54_in_presetcondition3015 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3017 = new BitSet(new long[]{0x4000000000000000L});
	public static final BitSet FOLLOW_62_in_presetcondition3019 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3021 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition3023 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3027 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_presetcondition3029 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3033 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_54_in_presetcondition3035 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_78_in_presetcondition3071 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3073 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3077 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3079 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3081 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3088 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_presetcondition3090 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3092 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3096 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3098 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3100 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3107 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_presetcondition3109 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3111 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3115 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3117 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3119 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3126 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_presetcondition3128 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3130 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3134 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3136 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3138 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3145 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_87_in_presetcondition3147 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3149 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition3151 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3155 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_presetcondition3157 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3161 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_54_in_presetcondition3163 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3165 = new BitSet(new long[]{0x4000000000000000L});
	public static final BitSet FOLLOW_62_in_presetcondition3167 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3169 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition3171 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3175 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_presetcondition3177 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3181 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_54_in_presetcondition3183 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_79_in_presetcondition3219 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3221 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3225 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3227 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3229 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3236 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_presetcondition3238 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3240 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3244 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3246 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3248 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3255 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_66_in_presetcondition3257 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3259 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3263 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3265 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3267 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3274 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_presetcondition3276 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3278 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3282 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3284 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3286 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3293 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_87_in_presetcondition3295 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3297 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition3299 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3303 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_presetcondition3305 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3309 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_54_in_presetcondition3311 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3313 = new BitSet(new long[]{0x4000000000000000L});
	public static final BitSet FOLLOW_62_in_presetcondition3315 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3317 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_presetcondition3319 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3323 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_presetcondition3325 = new BitSet(new long[]{0x0010000200000000L});
	public static final BitSet FOLLOW_constant_in_presetcondition3329 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_54_in_presetcondition3331 = new BitSet(new long[]{0x0000000000000002L});
}
