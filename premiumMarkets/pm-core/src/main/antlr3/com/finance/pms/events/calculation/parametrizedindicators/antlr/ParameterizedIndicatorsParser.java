// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2025-03-02 20:38:40
 //parser
    package com.finance.pms.events.calculation.parametrizedindicators.antlr;
    import org.antlr.runtime.BitSet;
import org.antlr.runtime.EarlyExitException;
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
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "AND", "AlphaCase", "AndBooleanMapCondition", 
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
		"PreAndSignalCondition", "ReverseCondition", "SEMICOLUMN", "SepCase", 
		"StockOperation", "String", "StringOperation", "StringToken", "SupConstantCondition", 
		"SupDoubleMapCondition", "SupportBreakDown", "SupportBreakUp", "TRUTHOF", 
		"Tcheat", "TruthOfCondition", "UpRatioCondition", "WITH", "WS", "WhiteChar", 
		"'NaN'", "'adaptiveRate'", "'also display'", "'bearish'", "'bullish'", 
		"'crosses down historical'", "'crosses down threshold'", "'crosses up historical'", 
		"'crosses up threshold'", "'direction'", "'ending within'", "'epsilon'", 
		"'equals historical'", "'equals threshold'", "'equals trend'", "'for'", 
		"'goes down more than'", "'goes up more than'", "'greed'", "'is above historical'", 
		"'is above threshold'", "'is bearish if not bullish'", "'is bearish when'", 
		"'is below historical'", "'is below threshold'", "'is bullish when'", 
		"'is within'", "'makes a higher high spanning'", "'makes a higher low spanning'", 
		"'makes a lower high spanning'", "'makes a lower low spanning'", "'makes a support break down spanning'", 
		"'makes a support break up spanning'", "'more than'", "'over'", "'override event list name with'", 
		"'override start shift with'", "'precondition'", "'reverses down'", "'reverses up'", 
		"'slope within'", "'smoothed'", "'spanning'", "'starting within'", "'tolerance'", 
		"'trends down'", "'trends flat'", "'trends like'", "'trends unlike'", 
		"'trends up'", "'type'"
	};
	public static final int EOF=-1;
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
	public static final int T__111=111;
	public static final int T__112=112;
	public static final int T__113=113;
	public static final int T__114=114;
	public static final int T__115=115;
	public static final int T__116=116;
	public static final int AND=4;
	public static final int AlphaCase=5;
	public static final int AndBooleanMapCondition=6;
	public static final int CLOSEPARENTEHSIS=7;
	public static final int CLOSESQRT=8;
	public static final int COMMA=9;
	public static final int COMMENT=10;
	public static final int CrossDownConstantCondition=11;
	public static final int CrossDownDoubleMapCondition=12;
	public static final int CrossUpConstantCondition=13;
	public static final int CrossUpDoubleMapCondition=14;
	public static final int DAYS=15;
	public static final int DownRatioCondition=16;
	public static final int EqualConstantCondition=17;
	public static final int EqualDoubleMapCondition=18;
	public static final int EqualStringConstantCondition=19;
	public static final int EventInfoOpsCompoOperation=20;
	public static final int HigherHighCondition=21;
	public static final int HigherLowCondition=22;
	public static final int HistoricalData=23;
	public static final int InfConstantCondition=24;
	public static final int InfDoubleMapCondition=25;
	public static final int LENIENT=26;
	public static final int LINE_COMMENT=27;
	public static final int LinearDirectedTrendsCondition=28;
	public static final int LinearOppositeTrendsCondition=29;
	public static final int LinearSimilarTrendsCondition=30;
	public static final int LowerHighCondition=31;
	public static final int LowerLowCondition=32;
	public static final int MATCHING=33;
	public static final int MatchingBooleanMapCondition=34;
	public static final int NOT=35;
	public static final int NotBooleanMapCondition=36;
	public static final int NullCondition=37;
	public static final int Number=38;
	public static final int NumberToken=39;
	public static final int OPENPARENTEHSIS=40;
	public static final int OPENSQRT=41;
	public static final int OR=42;
	public static final int Operation=43;
	public static final int OperationOutput=44;
	public static final int OrBooleanMapCondition=45;
	public static final int PERCENT=46;
	public static final int PreAndSignalCondition=47;
	public static final int ReverseCondition=48;
	public static final int SEMICOLUMN=49;
	public static final int SepCase=50;
	public static final int StockOperation=51;
	public static final int String=52;
	public static final int StringOperation=53;
	public static final int StringToken=54;
	public static final int SupConstantCondition=55;
	public static final int SupDoubleMapCondition=56;
	public static final int SupportBreakDown=57;
	public static final int SupportBreakUp=58;
	public static final int TRUTHOF=59;
	public static final int Tcheat=60;
	public static final int TruthOfCondition=61;
	public static final int UpRatioCondition=62;
	public static final int WITH=63;
	public static final int WS=64;
	public static final int WhiteChar=65;

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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:1: complete_expression : with_precondition also_display fixed_start_shift na_event_list_name -> ^( EventInfoOpsCompoOperation with_precondition also_display fixed_start_shift na_event_list_name ) ;
	public final ParameterizedIndicatorsParser.complete_expression_return complete_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.complete_expression_return retval = new ParameterizedIndicatorsParser.complete_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope with_precondition1 =null;
		ParserRuleReturnScope also_display2 =null;
		ParserRuleReturnScope fixed_start_shift3 =null;
		ParserRuleReturnScope na_event_list_name4 =null;

		RewriteRuleSubtreeStream stream_na_event_list_name=new RewriteRuleSubtreeStream(adaptor,"rule na_event_list_name");
		RewriteRuleSubtreeStream stream_with_precondition=new RewriteRuleSubtreeStream(adaptor,"rule with_precondition");
		RewriteRuleSubtreeStream stream_also_display=new RewriteRuleSubtreeStream(adaptor,"rule also_display");
		RewriteRuleSubtreeStream stream_fixed_start_shift=new RewriteRuleSubtreeStream(adaptor,"rule fixed_start_shift");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:162:21: ( with_precondition also_display fixed_start_shift na_event_list_name -> ^( EventInfoOpsCompoOperation with_precondition also_display fixed_start_shift na_event_list_name ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:164:5: with_precondition also_display fixed_start_shift na_event_list_name
			{
			pushFollow(FOLLOW_with_precondition_in_complete_expression446);
			with_precondition1=with_precondition();
			state._fsp--;

			stream_with_precondition.add(with_precondition1.getTree());
			pushFollow(FOLLOW_also_display_in_complete_expression448);
			also_display2=also_display();
			state._fsp--;

			stream_also_display.add(also_display2.getTree());
			pushFollow(FOLLOW_fixed_start_shift_in_complete_expression450);
			fixed_start_shift3=fixed_start_shift();
			state._fsp--;

			stream_fixed_start_shift.add(fixed_start_shift3.getTree());
			pushFollow(FOLLOW_na_event_list_name_in_complete_expression452);
			na_event_list_name4=na_event_list_name();
			state._fsp--;

			stream_na_event_list_name.add(na_event_list_name4.getTree());
			// AST REWRITE
			// elements: with_precondition, na_event_list_name, fixed_start_shift, also_display
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 164:73: -> ^( EventInfoOpsCompoOperation with_precondition also_display fixed_start_shift na_event_list_name )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:164:76: ^( EventInfoOpsCompoOperation with_precondition also_display fixed_start_shift na_event_list_name )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EventInfoOpsCompoOperation, "EventInfoOpsCompoOperation"), root_1);
				adaptor.addChild(root_1, stream_with_precondition.nextTree());
				adaptor.addChild(root_1, stream_also_display.nextTree());
				adaptor.addChild(root_1, stream_fixed_start_shift.nextTree());
				adaptor.addChild(root_1, stream_na_event_list_name.nextTree());
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


	public static class with_precondition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "with_precondition"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:1: with_precondition : bullCond= bullish_condition bearCond= bearish_condition[$bullCond.tree] ( ( 'precondition' WhiteChar primary_expression )+ ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> ^( PreAndSignalCondition ^( Number NumberToken[\"2\"] ) bullish_condition bearish_condition primary_expression ( primary_expression )* ) | -> ^( PreAndSignalCondition ^( Number NumberToken[\"2\"] ) bullish_condition bearish_condition ) ) ;
	public final ParameterizedIndicatorsParser.with_precondition_return with_precondition() throws RecognitionException {
		ParameterizedIndicatorsParser.with_precondition_return retval = new ParameterizedIndicatorsParser.with_precondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal5=null;
		Token WhiteChar6=null;
		Token WhiteChar8=null;
		Token SEMICOLUMN9=null;
		Token WhiteChar10=null;
		ParserRuleReturnScope bullCond =null;
		ParserRuleReturnScope bearCond =null;
		ParserRuleReturnScope primary_expression7 =null;

		CommonTree string_literal5_tree=null;
		CommonTree WhiteChar6_tree=null;
		CommonTree WhiteChar8_tree=null;
		CommonTree SEMICOLUMN9_tree=null;
		CommonTree WhiteChar10_tree=null;
		RewriteRuleTokenStream stream_SEMICOLUMN=new RewriteRuleTokenStream(adaptor,"token SEMICOLUMN");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_103=new RewriteRuleTokenStream(adaptor,"token 103");
		RewriteRuleSubtreeStream stream_bullish_condition=new RewriteRuleSubtreeStream(adaptor,"rule bullish_condition");
		RewriteRuleSubtreeStream stream_bearish_condition=new RewriteRuleSubtreeStream(adaptor,"rule bearish_condition");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:167:18: (bullCond= bullish_condition bearCond= bearish_condition[$bullCond.tree] ( ( 'precondition' WhiteChar primary_expression )+ ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> ^( PreAndSignalCondition ^( Number NumberToken[\"2\"] ) bullish_condition bearish_condition primary_expression ( primary_expression )* ) | -> ^( PreAndSignalCondition ^( Number NumberToken[\"2\"] ) bullish_condition bearish_condition ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:168:2: bullCond= bullish_condition bearCond= bearish_condition[$bullCond.tree] ( ( 'precondition' WhiteChar primary_expression )+ ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> ^( PreAndSignalCondition ^( Number NumberToken[\"2\"] ) bullish_condition bearish_condition primary_expression ( primary_expression )* ) | -> ^( PreAndSignalCondition ^( Number NumberToken[\"2\"] ) bullish_condition bearish_condition ) )
			{
			pushFollow(FOLLOW_bullish_condition_in_with_precondition480);
			bullCond=bullish_condition();
			state._fsp--;

			stream_bullish_condition.add(bullCond.getTree());
			pushFollow(FOLLOW_bearish_condition_in_with_precondition484);
			bearCond=bearish_condition((bullCond!=null?((CommonTree)bullCond.getTree()):null));
			state._fsp--;

			stream_bearish_condition.add(bearCond.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:169:2: ( ( 'precondition' WhiteChar primary_expression )+ ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> ^( PreAndSignalCondition ^( Number NumberToken[\"2\"] ) bullish_condition bearish_condition primary_expression ( primary_expression )* ) | -> ^( PreAndSignalCondition ^( Number NumberToken[\"2\"] ) bullish_condition bearish_condition ) )
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==103) ) {
				alt4=1;
			}
			else if ( (LA4_0==EOF||LA4_0==68||(LA4_0 >= 101 && LA4_0 <= 102)) ) {
				alt4=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}

			switch (alt4) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:2: ( 'precondition' WhiteChar primary_expression )+ ( WhiteChar )* SEMICOLUMN ( WhiteChar )*
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:2: ( 'precondition' WhiteChar primary_expression )+
					int cnt1=0;
					loop1:
					while (true) {
						int alt1=2;
						int LA1_0 = input.LA(1);
						if ( (LA1_0==103) ) {
							alt1=1;
						}

						switch (alt1) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:3: 'precondition' WhiteChar primary_expression
							{
							string_literal5=(Token)match(input,103,FOLLOW_103_in_with_precondition492);  
							stream_103.add(string_literal5);

							WhiteChar6=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_with_precondition494);  
							stream_WhiteChar.add(WhiteChar6);

							pushFollow(FOLLOW_primary_expression_in_with_precondition496);
							primary_expression7=primary_expression();
							state._fsp--;

							stream_primary_expression.add(primary_expression7.getTree());
							}
							break;

						default :
							if ( cnt1 >= 1 ) break loop1;
							EarlyExitException eee = new EarlyExitException(1, input);
							throw eee;
						}
						cnt1++;
					}

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:49: ( WhiteChar )*
					loop2:
					while (true) {
						int alt2=2;
						int LA2_0 = input.LA(1);
						if ( (LA2_0==WhiteChar) ) {
							alt2=1;
						}

						switch (alt2) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:49: WhiteChar
							{
							WhiteChar8=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_with_precondition500);  
							stream_WhiteChar.add(WhiteChar8);

							}
							break;

						default :
							break loop2;
						}
					}

					SEMICOLUMN9=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_with_precondition503);  
					stream_SEMICOLUMN.add(SEMICOLUMN9);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:71: ( WhiteChar )*
					loop3:
					while (true) {
						int alt3=2;
						int LA3_0 = input.LA(1);
						if ( (LA3_0==WhiteChar) ) {
							alt3=1;
						}

						switch (alt3) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:71: WhiteChar
							{
							WhiteChar10=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_with_precondition505);  
							stream_WhiteChar.add(WhiteChar10);

							}
							break;

						default :
							break loop3;
						}
					}

					// AST REWRITE
					// elements: bearish_condition, primary_expression, primary_expression, bullish_condition
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 170:82: -> ^( PreAndSignalCondition ^( Number NumberToken[\"2\"] ) bullish_condition bearish_condition primary_expression ( primary_expression )* )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:85: ^( PreAndSignalCondition ^( Number NumberToken[\"2\"] ) bullish_condition bearish_condition primary_expression ( primary_expression )* )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(PreAndSignalCondition, "PreAndSignalCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:109: ^( Number NumberToken[\"2\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "2"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, stream_bullish_condition.nextTree());
						adaptor.addChild(root_1, stream_bearish_condition.nextTree());
						adaptor.addChild(root_1, stream_primary_expression.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:170:191: ( primary_expression )*
						while ( stream_primary_expression.hasNext() ) {
							adaptor.addChild(root_1, stream_primary_expression.nextTree());
						}
						stream_primary_expression.reset();

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
					// elements: bullish_condition, bearish_condition
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 171:3: -> ^( PreAndSignalCondition ^( Number NumberToken[\"2\"] ) bullish_condition bearish_condition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:171:6: ^( PreAndSignalCondition ^( Number NumberToken[\"2\"] ) bullish_condition bearish_condition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(PreAndSignalCondition, "PreAndSignalCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:171:30: ^( Number NumberToken[\"2\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "2"));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, stream_bullish_condition.nextTree());
						adaptor.addChild(root_1, stream_bearish_condition.nextTree());
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
	// $ANTLR end "with_precondition"


	public static class bullish_condition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "bullish_condition"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:175:1: bullish_condition : 'is bullish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression ;
	public final ParameterizedIndicatorsParser.bullish_condition_return bullish_condition() throws RecognitionException {
		ParameterizedIndicatorsParser.bullish_condition_return retval = new ParameterizedIndicatorsParser.bullish_condition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal11=null;
		Token WhiteChar12=null;
		Token WhiteChar14=null;
		Token SEMICOLUMN15=null;
		Token WhiteChar16=null;
		ParserRuleReturnScope primary_expression13 =null;

		CommonTree string_literal11_tree=null;
		CommonTree WhiteChar12_tree=null;
		CommonTree WhiteChar14_tree=null;
		CommonTree SEMICOLUMN15_tree=null;
		CommonTree WhiteChar16_tree=null;
		RewriteRuleTokenStream stream_SEMICOLUMN=new RewriteRuleTokenStream(adaptor,"token SEMICOLUMN");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_91=new RewriteRuleTokenStream(adaptor,"token 91");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:175:19: ( 'is bullish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:2: 'is bullish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )*
			{
			string_literal11=(Token)match(input,91,FOLLOW_91_in_bullish_condition563);  
			stream_91.add(string_literal11);

			WhiteChar12=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition565);  
			stream_WhiteChar.add(WhiteChar12);

			pushFollow(FOLLOW_primary_expression_in_bullish_condition567);
			primary_expression13=primary_expression();
			state._fsp--;

			stream_primary_expression.add(primary_expression13.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:49: ( WhiteChar )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==WhiteChar) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:49: WhiteChar
					{
					WhiteChar14=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition569);  
					stream_WhiteChar.add(WhiteChar14);

					}
					break;

				default :
					break loop5;
				}
			}

			SEMICOLUMN15=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_bullish_condition572);  
			stream_SEMICOLUMN.add(SEMICOLUMN15);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:71: ( WhiteChar )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==WhiteChar) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:176:71: WhiteChar
					{
					WhiteChar16=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bullish_condition574);  
					stream_WhiteChar.add(WhiteChar16);

					}
					break;

				default :
					break loop6;
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
			// 176:82: -> primary_expression
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:178:1: bearish_condition[CommonTree bullCond] : ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bullCond] ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> bearish_not_bullish );
	public final ParameterizedIndicatorsParser.bearish_condition_return bearish_condition(CommonTree bullCond) throws RecognitionException {
		ParameterizedIndicatorsParser.bearish_condition_return retval = new ParameterizedIndicatorsParser.bearish_condition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal17=null;
		Token WhiteChar18=null;
		Token WhiteChar20=null;
		Token SEMICOLUMN21=null;
		Token WhiteChar22=null;
		Token WhiteChar24=null;
		Token SEMICOLUMN25=null;
		Token WhiteChar26=null;
		ParserRuleReturnScope primary_expression19 =null;
		ParserRuleReturnScope bearish_not_bullish23 =null;

		CommonTree string_literal17_tree=null;
		CommonTree WhiteChar18_tree=null;
		CommonTree WhiteChar20_tree=null;
		CommonTree SEMICOLUMN21_tree=null;
		CommonTree WhiteChar22_tree=null;
		CommonTree WhiteChar24_tree=null;
		CommonTree SEMICOLUMN25_tree=null;
		CommonTree WhiteChar26_tree=null;
		RewriteRuleTokenStream stream_SEMICOLUMN=new RewriteRuleTokenStream(adaptor,"token SEMICOLUMN");
		RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_bearish_not_bullish=new RewriteRuleSubtreeStream(adaptor,"rule bearish_not_bullish");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:178:40: ( 'is bearish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> primary_expression | bearish_not_bullish[$bullCond] ( WhiteChar )* SEMICOLUMN ( WhiteChar )* -> bearish_not_bullish )
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==88) ) {
				alt11=1;
			}
			else if ( (LA11_0==87) ) {
				alt11=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}

			switch (alt11) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:2: 'is bearish when' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN ( WhiteChar )*
					{
					string_literal17=(Token)match(input,88,FOLLOW_88_in_bearish_condition590);  
					stream_88.add(string_literal17);

					WhiteChar18=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition592);  
					stream_WhiteChar.add(WhiteChar18);

					pushFollow(FOLLOW_primary_expression_in_bearish_condition594);
					primary_expression19=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression19.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:49: ( WhiteChar )*
					loop7:
					while (true) {
						int alt7=2;
						int LA7_0 = input.LA(1);
						if ( (LA7_0==WhiteChar) ) {
							alt7=1;
						}

						switch (alt7) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:49: WhiteChar
							{
							WhiteChar20=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition596);  
							stream_WhiteChar.add(WhiteChar20);

							}
							break;

						default :
							break loop7;
						}
					}

					SEMICOLUMN21=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_bearish_condition599);  
					stream_SEMICOLUMN.add(SEMICOLUMN21);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:71: ( WhiteChar )*
					loop8:
					while (true) {
						int alt8=2;
						int LA8_0 = input.LA(1);
						if ( (LA8_0==WhiteChar) ) {
							alt8=1;
						}

						switch (alt8) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:179:71: WhiteChar
							{
							WhiteChar22=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition601);  
							stream_WhiteChar.add(WhiteChar22);

							}
							break;

						default :
							break loop8;
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
					// 179:82: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:2: bearish_not_bullish[$bullCond] ( WhiteChar )* SEMICOLUMN ( WhiteChar )*
					{
					pushFollow(FOLLOW_bearish_not_bullish_in_bearish_condition611);
					bearish_not_bullish23=bearish_not_bullish(bullCond);
					state._fsp--;

					stream_bearish_not_bullish.add(bearish_not_bullish23.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:33: ( WhiteChar )*
					loop9:
					while (true) {
						int alt9=2;
						int LA9_0 = input.LA(1);
						if ( (LA9_0==WhiteChar) ) {
							alt9=1;
						}

						switch (alt9) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:33: WhiteChar
							{
							WhiteChar24=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition614);  
							stream_WhiteChar.add(WhiteChar24);

							}
							break;

						default :
							break loop9;
						}
					}

					SEMICOLUMN25=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_bearish_condition617);  
					stream_SEMICOLUMN.add(SEMICOLUMN25);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:55: ( WhiteChar )*
					loop10:
					while (true) {
						int alt10=2;
						int LA10_0 = input.LA(1);
						if ( (LA10_0==WhiteChar) ) {
							alt10=1;
						}

						switch (alt10) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:180:55: WhiteChar
							{
							WhiteChar26=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_condition619);  
							stream_WhiteChar.add(WhiteChar26);

							}
							break;

						default :
							break loop10;
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
					// 180:66: -> bearish_not_bullish
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:182:2: also_display : ( 'also display' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition );
	public final ParameterizedIndicatorsParser.also_display_return also_display() throws RecognitionException {
		ParameterizedIndicatorsParser.also_display_return retval = new ParameterizedIndicatorsParser.also_display_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal27=null;
		Token WhiteChar28=null;
		Token WhiteChar30=null;
		Token SEMICOLUMN31=null;
		ParserRuleReturnScope primary_expression29 =null;

		CommonTree string_literal27_tree=null;
		CommonTree WhiteChar28_tree=null;
		CommonTree WhiteChar30_tree=null;
		CommonTree SEMICOLUMN31_tree=null;
		RewriteRuleTokenStream stream_SEMICOLUMN=new RewriteRuleTokenStream(adaptor,"token SEMICOLUMN");
		RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:182:15: ( 'also display' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ) | -> NullCondition )
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==68) ) {
				alt13=1;
			}
			else if ( (LA13_0==EOF||(LA13_0 >= 101 && LA13_0 <= 102)) ) {
				alt13=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}

			switch (alt13) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:3: 'also display' WhiteChar primary_expression ( WhiteChar )* SEMICOLUMN
					{
					string_literal27=(Token)match(input,68,FOLLOW_68_in_also_display636);  
					stream_68.add(string_literal27);

					WhiteChar28=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display638);  
					stream_WhiteChar.add(WhiteChar28);

					pushFollow(FOLLOW_primary_expression_in_also_display640);
					primary_expression29=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression29.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:47: ( WhiteChar )*
					loop12:
					while (true) {
						int alt12=2;
						int LA12_0 = input.LA(1);
						if ( (LA12_0==WhiteChar) ) {
							alt12=1;
						}

						switch (alt12) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:47: WhiteChar
							{
							WhiteChar30=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_also_display642);  
							stream_WhiteChar.add(WhiteChar30);

							}
							break;

						default :
							break loop12;
						}
					}

					SEMICOLUMN31=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_also_display645);  
					stream_SEMICOLUMN.add(SEMICOLUMN31);

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
					// 183:69: -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:72: ^( AndBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndBooleanMapCondition, "AndBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:183:97: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:184:3: 
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
					// 184:3: -> NullCondition
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:2: fixed_start_shift : ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS SEMICOLUMN ->| -> ^( Number NumberToken[\"-1\"] ) );
	public final ParameterizedIndicatorsParser.fixed_start_shift_return fixed_start_shift() throws RecognitionException {
		ParameterizedIndicatorsParser.fixed_start_shift_return retval = new ParameterizedIndicatorsParser.fixed_start_shift_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal32=null;
		Token WhiteChar33=null;
		Token WhiteChar34=null;
		Token DAYS35=null;
		Token SEMICOLUMN36=null;
		ParserRuleReturnScope fixedStartShift =null;

		CommonTree string_literal32_tree=null;
		CommonTree WhiteChar33_tree=null;
		CommonTree WhiteChar34_tree=null;
		CommonTree DAYS35_tree=null;
		CommonTree SEMICOLUMN36_tree=null;
		RewriteRuleTokenStream stream_SEMICOLUMN=new RewriteRuleTokenStream(adaptor,"token SEMICOLUMN");
		RewriteRuleTokenStream stream_102=new RewriteRuleTokenStream(adaptor,"token 102");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:186:20: ( 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS SEMICOLUMN ->| -> ^( Number NumberToken[\"-1\"] ) )
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==102) ) {
				alt14=1;
			}
			else if ( (LA14_0==EOF||LA14_0==101) ) {
				alt14=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}

			switch (alt14) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:187:3: 'override start shift with' WhiteChar fixedStartShift= constant WhiteChar DAYS SEMICOLUMN
					{
					string_literal32=(Token)match(input,102,FOLLOW_102_in_fixed_start_shift680);  
					stream_102.add(string_literal32);

					WhiteChar33=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift682);  
					stream_WhiteChar.add(WhiteChar33);

					pushFollow(FOLLOW_constant_in_fixed_start_shift686);
					fixedStartShift=constant();
					state._fsp--;

					stream_constant.add(fixedStartShift.getTree());
					WhiteChar34=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_fixed_start_shift688);  
					stream_WhiteChar.add(WhiteChar34);

					DAYS35=(Token)match(input,DAYS,FOLLOW_DAYS_in_fixed_start_shift690);  
					stream_DAYS.add(DAYS35);

					SEMICOLUMN36=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_fixed_start_shift692);  
					stream_SEMICOLUMN.add(SEMICOLUMN36);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 187:92: ->
					{
						adaptor.addChild(root_0, (fixedStartShift!=null?((CommonTree)fixedStartShift.getTree()):null));
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:3: 
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
					// 188:3: -> ^( Number NumberToken[\"-1\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:188:6: ^( Number NumberToken[\"-1\"] )
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


	public static class na_event_list_name_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "na_event_list_name"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:2: na_event_list_name : ( 'override event list name with' WhiteChar eventListName= stringconstant SEMICOLUMN ->| -> ^( String StringToken[\"FROM PARENT\"] ) );
	public final ParameterizedIndicatorsParser.na_event_list_name_return na_event_list_name() throws RecognitionException {
		ParameterizedIndicatorsParser.na_event_list_name_return retval = new ParameterizedIndicatorsParser.na_event_list_name_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal37=null;
		Token WhiteChar38=null;
		Token SEMICOLUMN39=null;
		ParserRuleReturnScope eventListName =null;

		CommonTree string_literal37_tree=null;
		CommonTree WhiteChar38_tree=null;
		CommonTree SEMICOLUMN39_tree=null;
		RewriteRuleTokenStream stream_SEMICOLUMN=new RewriteRuleTokenStream(adaptor,"token SEMICOLUMN");
		RewriteRuleTokenStream stream_101=new RewriteRuleTokenStream(adaptor,"token 101");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_stringconstant=new RewriteRuleSubtreeStream(adaptor,"rule stringconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:190:21: ( 'override event list name with' WhiteChar eventListName= stringconstant SEMICOLUMN ->| -> ^( String StringToken[\"FROM PARENT\"] ) )
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0==101) ) {
				alt15=1;
			}
			else if ( (LA15_0==EOF) ) {
				alt15=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}

			switch (alt15) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:191:3: 'override event list name with' WhiteChar eventListName= stringconstant SEMICOLUMN
					{
					string_literal37=(Token)match(input,101,FOLLOW_101_in_na_event_list_name721);  
					stream_101.add(string_literal37);

					WhiteChar38=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_na_event_list_name723);  
					stream_WhiteChar.add(WhiteChar38);

					pushFollow(FOLLOW_stringconstant_in_na_event_list_name727);
					eventListName=stringconstant();
					state._fsp--;

					stream_stringconstant.add(eventListName.getTree());
					SEMICOLUMN39=(Token)match(input,SEMICOLUMN,FOLLOW_SEMICOLUMN_in_na_event_list_name729);  
					stream_SEMICOLUMN.add(SEMICOLUMN39);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 191:85: ->
					{
						adaptor.addChild(root_0, (eventListName!=null?((CommonTree)eventListName.getTree()):null));
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:3: 
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
					// 192:3: -> ^( String StringToken[\"FROM PARENT\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:192:6: ^( String StringToken[\"FROM PARENT\"] )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_1);
						adaptor.addChild(root_1, (CommonTree)adaptor.create(StringToken, "FROM PARENT"));
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
	// $ANTLR end "na_event_list_name"


	public static class bearish_not_bullish_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "bearish_not_bullish"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:1: bearish_not_bullish[CommonTree bullCond] : 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ) ;
	public final ParameterizedIndicatorsParser.bearish_not_bullish_return bearish_not_bullish(CommonTree bullCond) throws RecognitionException {
		ParameterizedIndicatorsParser.bearish_not_bullish_return retval = new ParameterizedIndicatorsParser.bearish_not_bullish_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal40=null;
		Token WhiteChar41=null;
		Token AND42=null;
		Token WhiteChar43=null;
		Token WhiteChar45=null;
		Token OR46=null;
		Token WhiteChar47=null;
		ParserRuleReturnScope primary_expression44 =null;
		ParserRuleReturnScope primary_expression48 =null;

		CommonTree string_literal40_tree=null;
		CommonTree WhiteChar41_tree=null;
		CommonTree AND42_tree=null;
		CommonTree WhiteChar43_tree=null;
		CommonTree WhiteChar45_tree=null;
		CommonTree OR46_tree=null;
		CommonTree WhiteChar47_tree=null;
		RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
		RewriteRuleTokenStream stream_87=new RewriteRuleTokenStream(adaptor,"token 87");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:195:42: ( 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:196:2: 'is bearish if not bullish' ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			{
			string_literal40=(Token)match(input,87,FOLLOW_87_in_bearish_not_bullish758);  
			stream_87.add(string_literal40);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:197:3: ( WhiteChar AND WhiteChar primary_expression -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | WhiteChar OR WhiteChar primary_expression -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression ) | -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			int alt16=3;
			int LA16_0 = input.LA(1);
			if ( (LA16_0==WhiteChar) ) {
				switch ( input.LA(2) ) {
				case AND:
					{
					alt16=1;
					}
					break;
				case OR:
					{
					alt16=2;
					}
					break;
				case SEMICOLUMN:
				case WhiteChar:
					{
					alt16=3;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 16, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}
			else if ( (LA16_0==SEMICOLUMN) ) {
				alt16=3;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 16, 0, input);
				throw nvae;
			}

			switch (alt16) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:3: WhiteChar AND WhiteChar primary_expression
					{
					WhiteChar41=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish766);  
					stream_WhiteChar.add(WhiteChar41);

					AND42=(Token)match(input,AND,FOLLOW_AND_in_bearish_not_bullish768);  
					stream_AND.add(AND42);

					WhiteChar43=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish770);  
					stream_WhiteChar.add(WhiteChar43);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish772);
					primary_expression44=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression44.getTree());
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
					// 198:46: -> ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:49: ^( AndBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndBooleanMapCondition, "AndBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:74: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:109: ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_2);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:198:134: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_3 = (CommonTree)adaptor.nil();
						root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_3);
						adaptor.addChild(root_3, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_2, root_3);
						}

						adaptor.addChild(root_2, bullCond);
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:3: WhiteChar OR WhiteChar primary_expression
					{
					WhiteChar45=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish805);  
					stream_WhiteChar.add(WhiteChar45);

					OR46=(Token)match(input,OR,FOLLOW_OR_in_bearish_not_bullish807);  
					stream_OR.add(OR46);

					WhiteChar47=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_bearish_not_bullish809);  
					stream_WhiteChar.add(WhiteChar47);

					pushFollow(FOLLOW_primary_expression_in_bearish_not_bullish811);
					primary_expression48=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression48.getTree());
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
					// 199:45: -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:48: ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrBooleanMapCondition, "OrBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:72: ^( String StringToken[\"\\\"TRUE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:106: ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_2);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:199:131: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_3 = (CommonTree)adaptor.nil();
						root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_3);
						adaptor.addChild(root_3, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_2, root_3);
						}

						adaptor.addChild(root_2, bullCond);
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:3: 
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
					// 200:3: -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:6: ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:200:31: ^( String StringToken[\"\\\"FALSE\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"FALSE\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, bullCond);
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:1: primary_expression : and_expression ;
	public final ParameterizedIndicatorsParser.primary_expression_return primary_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.primary_expression_return retval = new ParameterizedIndicatorsParser.primary_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope and_expression49 =null;


		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:204:20: ( and_expression )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:205:3: and_expression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_and_expression_in_primary_expression873);
			and_expression49=and_expression();
			state._fsp--;

			adaptor.addChild(root_0, and_expression49.getTree());

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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:1: and_expression : or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndBooleanMapCondition or_expression ( or_expression )* ) ;
	public final ParameterizedIndicatorsParser.and_expression_return and_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.and_expression_return retval = new ParameterizedIndicatorsParser.and_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar51=null;
		Token AND52=null;
		Token WhiteChar53=null;
		ParserRuleReturnScope lenientParam =null;
		ParserRuleReturnScope or_expression50 =null;
		ParserRuleReturnScope or_expression54 =null;

		CommonTree WhiteChar51_tree=null;
		CommonTree AND52_tree=null;
		CommonTree WhiteChar53_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
		RewriteRuleSubtreeStream stream_or_expression=new RewriteRuleSubtreeStream(adaptor,"rule or_expression");
		RewriteRuleSubtreeStream stream_lenient=new RewriteRuleSubtreeStream(adaptor,"rule lenient");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:207:16: ( or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )* -> ^( AndBooleanMapCondition or_expression ( or_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:3: or_expression lenientParam= lenient ( WhiteChar AND WhiteChar or_expression )*
			{
			pushFollow(FOLLOW_or_expression_in_and_expression885);
			or_expression50=or_expression();
			state._fsp--;

			stream_or_expression.add(or_expression50.getTree());
			pushFollow(FOLLOW_lenient_in_and_expression889);
			lenientParam=lenient();
			state._fsp--;

			stream_lenient.add(lenientParam.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:38: ( WhiteChar AND WhiteChar or_expression )*
			loop17:
			while (true) {
				int alt17=2;
				int LA17_0 = input.LA(1);
				if ( (LA17_0==WhiteChar) ) {
					int LA17_1 = input.LA(2);
					if ( (LA17_1==AND) ) {
						alt17=1;
					}

				}

				switch (alt17) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:39: WhiteChar AND WhiteChar or_expression
					{
					WhiteChar51=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression892);  
					stream_WhiteChar.add(WhiteChar51);

					AND52=(Token)match(input,AND,FOLLOW_AND_in_and_expression894);  
					stream_AND.add(AND52);

					WhiteChar53=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_and_expression896);  
					stream_WhiteChar.add(WhiteChar53);

					pushFollow(FOLLOW_or_expression_in_and_expression898);
					or_expression54=or_expression();
					state._fsp--;

					stream_or_expression.add(or_expression54.getTree());
					}
					break;

				default :
					break loop17;
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
			// 208:79: -> ^( AndBooleanMapCondition or_expression ( or_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:82: ^( AndBooleanMapCondition or_expression ( or_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AndBooleanMapCondition, "AndBooleanMapCondition"), root_1);
				adaptor.addChild(root_1, (lenientParam!=null?((CommonTree)lenientParam.getTree()):null));
				adaptor.addChild(root_1, stream_or_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:208:142: ( or_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:1: or_expression : precondition_expression ( WhiteChar OR WhiteChar precondition_expression )* -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) precondition_expression ( precondition_expression )* ) ;
	public final ParameterizedIndicatorsParser.or_expression_return or_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.or_expression_return retval = new ParameterizedIndicatorsParser.or_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar56=null;
		Token OR57=null;
		Token WhiteChar58=null;
		ParserRuleReturnScope precondition_expression55 =null;
		ParserRuleReturnScope precondition_expression59 =null;

		CommonTree WhiteChar56_tree=null;
		CommonTree OR57_tree=null;
		CommonTree WhiteChar58_tree=null;
		RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_precondition_expression=new RewriteRuleSubtreeStream(adaptor,"rule precondition_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:210:15: ( precondition_expression ( WhiteChar OR WhiteChar precondition_expression )* -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) precondition_expression ( precondition_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:3: precondition_expression ( WhiteChar OR WhiteChar precondition_expression )*
			{
			pushFollow(FOLLOW_precondition_expression_in_or_expression925);
			precondition_expression55=precondition_expression();
			state._fsp--;

			stream_precondition_expression.add(precondition_expression55.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:27: ( WhiteChar OR WhiteChar precondition_expression )*
			loop18:
			while (true) {
				int alt18=2;
				int LA18_0 = input.LA(1);
				if ( (LA18_0==WhiteChar) ) {
					int LA18_1 = input.LA(2);
					if ( (LA18_1==OR) ) {
						alt18=1;
					}

				}

				switch (alt18) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:28: WhiteChar OR WhiteChar precondition_expression
					{
					WhiteChar56=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression928);  
					stream_WhiteChar.add(WhiteChar56);

					OR57=(Token)match(input,OR,FOLLOW_OR_in_or_expression930);  
					stream_OR.add(OR57);

					WhiteChar58=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_or_expression932);  
					stream_WhiteChar.add(WhiteChar58);

					pushFollow(FOLLOW_precondition_expression_in_or_expression934);
					precondition_expression59=precondition_expression();
					state._fsp--;

					stream_precondition_expression.add(precondition_expression59.getTree());
					}
					break;

				default :
					break loop18;
				}
			}

			// AST REWRITE
			// elements: precondition_expression, precondition_expression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 211:77: -> ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) precondition_expression ( precondition_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:80: ^( OrBooleanMapCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) precondition_expression ( precondition_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OrBooleanMapCondition, "OrBooleanMapCondition"), root_1);
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:104: ^( String StringToken[\"\\\"TRUE\\\"\"] )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
				adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, stream_precondition_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:211:162: ( precondition_expression )*
				while ( stream_precondition_expression.hasNext() ) {
					adaptor.addChild(root_1, stream_precondition_expression.nextTree());
				}
				stream_precondition_expression.reset();

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


	public static class precondition_expression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precondition_expression"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:1: precondition_expression : matches_expression ( WhiteChar WITH WhiteChar matches_expression )* -> ^( PreAndSignalCondition ^( Number NumberToken[\"1\"] ) matches_expression ( matches_expression )* ) ;
	public final ParameterizedIndicatorsParser.precondition_expression_return precondition_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.precondition_expression_return retval = new ParameterizedIndicatorsParser.precondition_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar61=null;
		Token WITH62=null;
		Token WhiteChar63=null;
		ParserRuleReturnScope matches_expression60 =null;
		ParserRuleReturnScope matches_expression64 =null;

		CommonTree WhiteChar61_tree=null;
		CommonTree WITH62_tree=null;
		CommonTree WhiteChar63_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_WITH=new RewriteRuleTokenStream(adaptor,"token WITH");
		RewriteRuleSubtreeStream stream_matches_expression=new RewriteRuleSubtreeStream(adaptor,"rule matches_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:213:25: ( matches_expression ( WhiteChar WITH WhiteChar matches_expression )* -> ^( PreAndSignalCondition ^( Number NumberToken[\"1\"] ) matches_expression ( matches_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:3: matches_expression ( WhiteChar WITH WhiteChar matches_expression )*
			{
			pushFollow(FOLLOW_matches_expression_in_precondition_expression966);
			matches_expression60=matches_expression();
			state._fsp--;

			stream_matches_expression.add(matches_expression60.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:22: ( WhiteChar WITH WhiteChar matches_expression )*
			loop19:
			while (true) {
				int alt19=2;
				int LA19_0 = input.LA(1);
				if ( (LA19_0==WhiteChar) ) {
					int LA19_1 = input.LA(2);
					if ( (LA19_1==WITH) ) {
						alt19=1;
					}

				}

				switch (alt19) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:23: WhiteChar WITH WhiteChar matches_expression
					{
					WhiteChar61=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_precondition_expression969);  
					stream_WhiteChar.add(WhiteChar61);

					WITH62=(Token)match(input,WITH,FOLLOW_WITH_in_precondition_expression971);  
					stream_WITH.add(WITH62);

					WhiteChar63=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_precondition_expression973);  
					stream_WhiteChar.add(WhiteChar63);

					pushFollow(FOLLOW_matches_expression_in_precondition_expression975);
					matches_expression64=matches_expression();
					state._fsp--;

					stream_matches_expression.add(matches_expression64.getTree());
					}
					break;

				default :
					break loop19;
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
			// 214:69: -> ^( PreAndSignalCondition ^( Number NumberToken[\"1\"] ) matches_expression ( matches_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:72: ^( PreAndSignalCondition ^( Number NumberToken[\"1\"] ) matches_expression ( matches_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(PreAndSignalCondition, "PreAndSignalCondition"), root_1);
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:96: ^( Number NumberToken[\"1\"] )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
				adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1"));
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, stream_matches_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:214:142: ( matches_expression )*
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
	// $ANTLR end "precondition_expression"


	public static class matches_expression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "matches_expression"
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:1: matches_expression : atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )? -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? ) ;
	public final ParameterizedIndicatorsParser.matches_expression_return matches_expression() throws RecognitionException {
		ParameterizedIndicatorsParser.matches_expression_return retval = new ParameterizedIndicatorsParser.matches_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar66=null;
		Token MATCHING67=null;
		Token WhiteChar68=null;
		Token char_literal69=null;
		Token char_literal71=null;
		Token char_literal73=null;
		Token WhiteChar74=null;
		ParserRuleReturnScope atom65 =null;
		ParserRuleReturnScope constant70 =null;
		ParserRuleReturnScope constant72 =null;
		ParserRuleReturnScope atom75 =null;

		CommonTree WhiteChar66_tree=null;
		CommonTree MATCHING67_tree=null;
		CommonTree WhiteChar68_tree=null;
		CommonTree char_literal69_tree=null;
		CommonTree char_literal71_tree=null;
		CommonTree char_literal73_tree=null;
		CommonTree WhiteChar74_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_OPENSQRT=new RewriteRuleTokenStream(adaptor,"token OPENSQRT");
		RewriteRuleTokenStream stream_MATCHING=new RewriteRuleTokenStream(adaptor,"token MATCHING");
		RewriteRuleTokenStream stream_CLOSESQRT=new RewriteRuleTokenStream(adaptor,"token CLOSESQRT");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:216:20: ( atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )? -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:3: atom ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )?
			{
			pushFollow(FOLLOW_atom_in_matches_expression1007);
			atom65=atom();
			state._fsp--;

			stream_atom.add(atom65.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:8: ( WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom )?
			int alt21=2;
			int LA21_0 = input.LA(1);
			if ( (LA21_0==WhiteChar) ) {
				int LA21_1 = input.LA(2);
				if ( (LA21_1==MATCHING) ) {
					alt21=1;
				}
			}
			switch (alt21) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:9: WhiteChar MATCHING WhiteChar '[' constant ( ',' constant )* ']' WhiteChar atom
					{
					WhiteChar66=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression1010);  
					stream_WhiteChar.add(WhiteChar66);

					MATCHING67=(Token)match(input,MATCHING,FOLLOW_MATCHING_in_matches_expression1012);  
					stream_MATCHING.add(MATCHING67);

					WhiteChar68=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression1014);  
					stream_WhiteChar.add(WhiteChar68);

					char_literal69=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_matches_expression1016);  
					stream_OPENSQRT.add(char_literal69);

					pushFollow(FOLLOW_constant_in_matches_expression1018);
					constant70=constant();
					state._fsp--;

					stream_constant.add(constant70.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:51: ( ',' constant )*
					loop20:
					while (true) {
						int alt20=2;
						int LA20_0 = input.LA(1);
						if ( (LA20_0==COMMA) ) {
							alt20=1;
						}

						switch (alt20) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:52: ',' constant
							{
							char_literal71=(Token)match(input,COMMA,FOLLOW_COMMA_in_matches_expression1021);  
							stream_COMMA.add(char_literal71);

							pushFollow(FOLLOW_constant_in_matches_expression1023);
							constant72=constant();
							state._fsp--;

							stream_constant.add(constant72.getTree());
							}
							break;

						default :
							break loop20;
						}
					}

					char_literal73=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_matches_expression1027);  
					stream_CLOSESQRT.add(char_literal73);

					WhiteChar74=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_matches_expression1029);  
					stream_WhiteChar.add(WhiteChar74);

					pushFollow(FOLLOW_atom_in_matches_expression1031);
					atom75=atom();
					state._fsp--;

					stream_atom.add(atom75.getTree());
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
			// 217:88: -> ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:91: ^( MatchingBooleanMapCondition ( constant )* atom ( atom )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(MatchingBooleanMapCondition, "MatchingBooleanMapCondition"), root_1);
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:121: ( constant )*
				while ( stream_constant.hasNext() ) {
					adaptor.addChild(root_1, stream_constant.nextTree());
				}
				stream_constant.reset();

				adaptor.addChild(root_1, stream_atom.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:217:136: ( atom )?
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:1: atom : ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | NOT ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression ) | conjunctiontruthof );
	public final ParameterizedIndicatorsParser.atom_return atom() throws RecognitionException {
		ParameterizedIndicatorsParser.atom_return retval = new ParameterizedIndicatorsParser.atom_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal77=null;
		Token WhiteChar78=null;
		Token WhiteChar80=null;
		Token char_literal81=null;
		Token NOT82=null;
		Token WhiteChar83=null;
		Token char_literal84=null;
		Token WhiteChar85=null;
		Token WhiteChar87=null;
		Token char_literal88=null;
		ParserRuleReturnScope booleanhistory76 =null;
		ParserRuleReturnScope primary_expression79 =null;
		ParserRuleReturnScope primary_expression86 =null;
		ParserRuleReturnScope conjunctiontruthof89 =null;

		CommonTree char_literal77_tree=null;
		CommonTree WhiteChar78_tree=null;
		CommonTree WhiteChar80_tree=null;
		CommonTree char_literal81_tree=null;
		CommonTree NOT82_tree=null;
		CommonTree WhiteChar83_tree=null;
		CommonTree char_literal84_tree=null;
		CommonTree WhiteChar85_tree=null;
		CommonTree WhiteChar87_tree=null;
		CommonTree char_literal88_tree=null;
		RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_CLOSEPARENTEHSIS=new RewriteRuleTokenStream(adaptor,"token CLOSEPARENTEHSIS");
		RewriteRuleTokenStream stream_OPENPARENTEHSIS=new RewriteRuleTokenStream(adaptor,"token OPENPARENTEHSIS");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:219:6: ( booleanhistory | '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> primary_expression | NOT ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')' -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression ) | conjunctiontruthof )
			int alt27=4;
			switch ( input.LA(1) ) {
			case HistoricalData:
			case Operation:
				{
				alt27=1;
				}
				break;
			case OPENPARENTEHSIS:
				{
				alt27=2;
				}
				break;
			case NOT:
				{
				alt27=3;
				}
				break;
			case TRUTHOF:
				{
				alt27=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 27, 0, input);
				throw nvae;
			}
			switch (alt27) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:220:3: booleanhistory
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_booleanhistory_in_atom1059);
					booleanhistory76=booleanhistory();
					state._fsp--;

					adaptor.addChild(root_0, booleanhistory76.getTree());

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:3: '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					char_literal77=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom1065);  
					stream_OPENPARENTEHSIS.add(char_literal77);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:7: ( WhiteChar )*
					loop22:
					while (true) {
						int alt22=2;
						int LA22_0 = input.LA(1);
						if ( (LA22_0==WhiteChar) ) {
							alt22=1;
						}

						switch (alt22) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:7: WhiteChar
							{
							WhiteChar78=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom1067);  
							stream_WhiteChar.add(WhiteChar78);

							}
							break;

						default :
							break loop22;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom1070);
					primary_expression79=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression79.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:37: ( WhiteChar )*
					loop23:
					while (true) {
						int alt23=2;
						int LA23_0 = input.LA(1);
						if ( (LA23_0==WhiteChar) ) {
							alt23=1;
						}

						switch (alt23) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:221:37: WhiteChar
							{
							WhiteChar80=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom1072);  
							stream_WhiteChar.add(WhiteChar80);

							}
							break;

						default :
							break loop23;
						}
					}

					char_literal81=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom1075);  
					stream_CLOSEPARENTEHSIS.add(char_literal81);

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
					// 221:52: -> primary_expression
					{
						adaptor.addChild(root_0, stream_primary_expression.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:3: NOT ( WhiteChar )* '(' ( WhiteChar )* primary_expression ( WhiteChar )* ')'
					{
					NOT82=(Token)match(input,NOT,FOLLOW_NOT_in_atom1085);  
					stream_NOT.add(NOT82);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:7: ( WhiteChar )*
					loop24:
					while (true) {
						int alt24=2;
						int LA24_0 = input.LA(1);
						if ( (LA24_0==WhiteChar) ) {
							alt24=1;
						}

						switch (alt24) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:7: WhiteChar
							{
							WhiteChar83=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom1087);  
							stream_WhiteChar.add(WhiteChar83);

							}
							break;

						default :
							break loop24;
						}
					}

					char_literal84=(Token)match(input,OPENPARENTEHSIS,FOLLOW_OPENPARENTEHSIS_in_atom1090);  
					stream_OPENPARENTEHSIS.add(char_literal84);

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:22: ( WhiteChar )*
					loop25:
					while (true) {
						int alt25=2;
						int LA25_0 = input.LA(1);
						if ( (LA25_0==WhiteChar) ) {
							alt25=1;
						}

						switch (alt25) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:22: WhiteChar
							{
							WhiteChar85=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom1092);  
							stream_WhiteChar.add(WhiteChar85);

							}
							break;

						default :
							break loop25;
						}
					}

					pushFollow(FOLLOW_primary_expression_in_atom1095);
					primary_expression86=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression86.getTree());
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:52: ( WhiteChar )*
					loop26:
					while (true) {
						int alt26=2;
						int LA26_0 = input.LA(1);
						if ( (LA26_0==WhiteChar) ) {
							alt26=1;
						}

						switch (alt26) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:52: WhiteChar
							{
							WhiteChar87=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_atom1097);  
							stream_WhiteChar.add(WhiteChar87);

							}
							break;

						default :
							break loop26;
						}
					}

					char_literal88=(Token)match(input,CLOSEPARENTEHSIS,FOLLOW_CLOSEPARENTEHSIS_in_atom1100);  
					stream_CLOSEPARENTEHSIS.add(char_literal88);

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
					// 222:67: -> ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:70: ^( NotBooleanMapCondition ^( String StringToken[\"\\\"FALSE\\\"\"] ) primary_expression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NotBooleanMapCondition, "NotBooleanMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:222:95: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:223:3: conjunctiontruthof
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_conjunctiontruthof_in_atom1121);
					conjunctiontruthof89=conjunctiontruthof();
					state._fsp--;

					adaptor.addChild(root_0, conjunctiontruthof89.getTree());

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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:1: conjunctiontruthof : TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']' -> ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* ) ;
	public final ParameterizedIndicatorsParser.conjunctiontruthof_return conjunctiontruthof() throws RecognitionException {
		ParameterizedIndicatorsParser.conjunctiontruthof_return retval = new ParameterizedIndicatorsParser.conjunctiontruthof_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token TRUTHOF90=null;
		Token WhiteChar91=null;
		Token COMMA93=null;
		Token WhiteChar94=null;
		Token WhiteChar96=null;
		Token string_literal97=null;
		Token WhiteChar98=null;
		Token char_literal99=null;
		Token char_literal100=null;
		Token char_literal101=null;
		ParserRuleReturnScope min =null;
		ParserRuleReturnScope max =null;
		ParserRuleReturnScope primary_expression92 =null;
		ParserRuleReturnScope primary_expression95 =null;

		CommonTree TRUTHOF90_tree=null;
		CommonTree WhiteChar91_tree=null;
		CommonTree COMMA93_tree=null;
		CommonTree WhiteChar94_tree=null;
		CommonTree WhiteChar96_tree=null;
		CommonTree string_literal97_tree=null;
		CommonTree WhiteChar98_tree=null;
		CommonTree char_literal99_tree=null;
		CommonTree char_literal100_tree=null;
		CommonTree char_literal101_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_OPENSQRT=new RewriteRuleTokenStream(adaptor,"token OPENSQRT");
		RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
		RewriteRuleTokenStream stream_CLOSESQRT=new RewriteRuleTokenStream(adaptor,"token CLOSESQRT");
		RewriteRuleTokenStream stream_TRUTHOF=new RewriteRuleTokenStream(adaptor,"token TRUTHOF");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_primary_expression=new RewriteRuleSubtreeStream(adaptor,"rule primary_expression");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:225:20: ( TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']' -> ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:3: TRUTHOF WhiteChar primary_expression ( COMMA WhiteChar primary_expression )* WhiteChar 'is within' WhiteChar '[' min= constant ',' max= constant ']'
			{
			TRUTHOF90=(Token)match(input,TRUTHOF,FOLLOW_TRUTHOF_in_conjunctiontruthof1133);  
			stream_TRUTHOF.add(TRUTHOF90);

			WhiteChar91=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof1135);  
			stream_WhiteChar.add(WhiteChar91);

			pushFollow(FOLLOW_primary_expression_in_conjunctiontruthof1137);
			primary_expression92=primary_expression();
			state._fsp--;

			stream_primary_expression.add(primary_expression92.getTree());
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:40: ( COMMA WhiteChar primary_expression )*
			loop28:
			while (true) {
				int alt28=2;
				int LA28_0 = input.LA(1);
				if ( (LA28_0==COMMA) ) {
					alt28=1;
				}

				switch (alt28) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:226:41: COMMA WhiteChar primary_expression
					{
					COMMA93=(Token)match(input,COMMA,FOLLOW_COMMA_in_conjunctiontruthof1140);  
					stream_COMMA.add(COMMA93);

					WhiteChar94=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof1142);  
					stream_WhiteChar.add(WhiteChar94);

					pushFollow(FOLLOW_primary_expression_in_conjunctiontruthof1144);
					primary_expression95=primary_expression();
					state._fsp--;

					stream_primary_expression.add(primary_expression95.getTree());
					}
					break;

				default :
					break loop28;
				}
			}

			WhiteChar96=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof1148);  
			stream_WhiteChar.add(WhiteChar96);

			string_literal97=(Token)match(input,92,FOLLOW_92_in_conjunctiontruthof1150);  
			stream_92.add(string_literal97);

			WhiteChar98=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_conjunctiontruthof1152);  
			stream_WhiteChar.add(WhiteChar98);

			char_literal99=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_conjunctiontruthof1154);  
			stream_OPENSQRT.add(char_literal99);

			pushFollow(FOLLOW_constant_in_conjunctiontruthof1158);
			min=constant();
			state._fsp--;

			stream_constant.add(min.getTree());
			char_literal100=(Token)match(input,COMMA,FOLLOW_COMMA_in_conjunctiontruthof1160);  
			stream_COMMA.add(char_literal100);

			pushFollow(FOLLOW_constant_in_conjunctiontruthof1164);
			max=constant();
			state._fsp--;

			stream_constant.add(max.getTree());
			char_literal101=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_conjunctiontruthof1166);  
			stream_CLOSESQRT.add(char_literal101);

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
			// 227:4: -> ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:7: ^( TruthOfCondition ^( String StringToken[\"\\\"TRUE\\\"\"] ) primary_expression ( primary_expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TruthOfCondition, "TruthOfCondition"), root_1);
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:26: ^( String StringToken[\"\\\"TRUE\\\"\"] )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
				adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"TRUE\""));
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, (min!=null?((CommonTree)min.getTree()):null));
				adaptor.addChild(root_1, (max!=null?((CommonTree)max.getTree()):null));
				adaptor.addChild(root_1, stream_primary_expression.nextTree());
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:227:103: ( primary_expression )*
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:1: booleanhistory : firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) ;
	public final ParameterizedIndicatorsParser.booleanhistory_return booleanhistory() throws RecognitionException {
		ParameterizedIndicatorsParser.booleanhistory_return retval = new ParameterizedIndicatorsParser.booleanhistory_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar102=null;
		ParserRuleReturnScope firstOp =null;
		ParserRuleReturnScope presetcondition103 =null;
		ParserRuleReturnScope opcmpcondition104 =null;
		ParserRuleReturnScope constantcmp105 =null;

		CommonTree WhiteChar102_tree=null;
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleSubtreeStream stream_presetcondition=new RewriteRuleSubtreeStream(adaptor,"rule presetcondition");
		RewriteRuleSubtreeStream stream_constantcmp=new RewriteRuleSubtreeStream(adaptor,"rule constantcmp");
		RewriteRuleSubtreeStream stream_opcmpcondition=new RewriteRuleSubtreeStream(adaptor,"rule opcmpcondition");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:229:16: (firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:2: firstOp= operand WhiteChar ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			{
			pushFollow(FOLLOW_operand_in_booleanhistory1205);
			firstOp=operand();
			state._fsp--;

			stream_operand.add(firstOp.getTree());
			WhiteChar102=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_booleanhistory1207);  
			stream_WhiteChar.add(WhiteChar102);

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:230:28: ( presetcondition[$firstOp.tree] -> presetcondition | opcmpcondition[$firstOp.tree] -> opcmpcondition | constantcmp[$firstOp.tree] -> constantcmp )
			int alt29=3;
			switch ( input.LA(1) ) {
			case 72:
			case 74:
			case 82:
			case 83:
			case 93:
			case 94:
			case 95:
			case 96:
			case 97:
			case 98:
			case 104:
			case 105:
			case 111:
			case 112:
			case 115:
				{
				alt29=1;
				}
				break;
			case 71:
			case 73:
			case 78:
			case 85:
			case 89:
			case 113:
			case 114:
				{
				alt29=2;
				}
				break;
			case 79:
			case 80:
			case 86:
			case 90:
				{
				alt29=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 29, 0, input);
				throw nvae;
			}
			switch (alt29) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:231:3: presetcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_presetcondition_in_booleanhistory1213);
					presetcondition103=presetcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_presetcondition.add(presetcondition103.getTree());
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
					// 231:34: -> presetcondition
					{
						adaptor.addChild(root_0, stream_presetcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:232:3: opcmpcondition[$firstOp.tree]
					{
					pushFollow(FOLLOW_opcmpcondition_in_booleanhistory1224);
					opcmpcondition104=opcmpcondition((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_opcmpcondition.add(opcmpcondition104.getTree());
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
					// 232:33: -> opcmpcondition
					{
						adaptor.addChild(root_0, stream_opcmpcondition.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:233:3: constantcmp[$firstOp.tree]
					{
					pushFollow(FOLLOW_constantcmp_in_booleanhistory1235);
					constantcmp105=constantcmp((firstOp!=null?((CommonTree)firstOp.getTree()):null));
					state._fsp--;

					stream_constantcmp.add(constantcmp105.getTree());
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
					// 233:30: -> constantcmp
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:1: operand : ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation );
	public final ParameterizedIndicatorsParser.operand_return operand() throws RecognitionException {
		ParameterizedIndicatorsParser.operand_return retval = new ParameterizedIndicatorsParser.operand_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token opName=null;
		Token HistoricalData106=null;

		CommonTree opName_tree=null;
		CommonTree HistoricalData106_tree=null;
		RewriteRuleTokenStream stream_Operation=new RewriteRuleTokenStream(adaptor,"token Operation");
		RewriteRuleTokenStream stream_HistoricalData=new RewriteRuleTokenStream(adaptor,"token HistoricalData");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:9: ( HistoricalData -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) ) |opName= Operation -> Operation )
			int alt30=2;
			int LA30_0 = input.LA(1);
			if ( (LA30_0==HistoricalData) ) {
				alt30=1;
			}
			else if ( (LA30_0==Operation) ) {
				alt30=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 30, 0, input);
				throw nvae;
			}

			switch (alt30) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:11: HistoricalData
					{
					HistoricalData106=(Token)match(input,HistoricalData,FOLLOW_HistoricalData_in_operand1251);  
					stream_HistoricalData.add(HistoricalData106);

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
					// 236:26: -> ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:29: ^( StockOperation ^( OperationOutput HistoricalData ) ^( String StringToken[\"\\\"THIS\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(StockOperation, "StockOperation"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:46: ^( OperationOutput HistoricalData )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OperationOutput, "OperationOutput"), root_2);
						adaptor.addChild(root_2, stream_HistoricalData.nextNode());
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:80: ^( String StringToken[\"\\\"THIS\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:236:117: opName= Operation
					{
					opName=(Token)match(input,Operation,FOLLOW_Operation_in_operand1278);  
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
					// 236:171: -> Operation
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:1: constant : ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) );
	public final ParameterizedIndicatorsParser.constant_return constant() throws RecognitionException {
		ParameterizedIndicatorsParser.constant_return retval = new ParameterizedIndicatorsParser.constant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NumberToken107=null;
		Token string_literal108=null;

		CommonTree NumberToken107_tree=null;
		CommonTree string_literal108_tree=null;
		RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
		RewriteRuleTokenStream stream_NumberToken=new RewriteRuleTokenStream(adaptor,"token NumberToken");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:10: ( NumberToken -> ^( Number NumberToken ) | 'NaN' -> ^( Number NumberToken[\"NaN\"] ) )
			int alt31=2;
			int LA31_0 = input.LA(1);
			if ( (LA31_0==NumberToken) ) {
				alt31=1;
			}
			else if ( (LA31_0==66) ) {
				alt31=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 31, 0, input);
				throw nvae;
			}

			switch (alt31) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:13: NumberToken
					{
					NumberToken107=(Token)match(input,NumberToken,FOLLOW_NumberToken_in_constant1293);  
					stream_NumberToken.add(NumberToken107);

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
					// 238:25: -> ^( Number NumberToken )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:28: ^( Number NumberToken )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:52: 'NaN'
					{
					string_literal108=(Token)match(input,66,FOLLOW_66_in_constant1305);  
					stream_66.add(string_literal108);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 238:58: -> ^( Number NumberToken[\"NaN\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:238:61: ^( Number NumberToken[\"NaN\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:1: stringconstant : StringToken -> ^( String StringToken ) ;
	public final ParameterizedIndicatorsParser.stringconstant_return stringconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.stringconstant_return retval = new ParameterizedIndicatorsParser.stringconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token StringToken109=null;

		CommonTree StringToken109_tree=null;
		RewriteRuleTokenStream stream_StringToken=new RewriteRuleTokenStream(adaptor,"token StringToken");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:16: ( StringToken -> ^( String StringToken ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:18: StringToken
			{
			StringToken109=(Token)match(input,StringToken,FOLLOW_StringToken_in_stringconstant1321);  
			stream_StringToken.add(StringToken109);

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
			// 239:30: -> ^( String StringToken )
			{
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:239:33: ^( String StringToken )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:1: trendconstant : ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) );
	public final ParameterizedIndicatorsParser.trendconstant_return trendconstant() throws RecognitionException {
		ParameterizedIndicatorsParser.trendconstant_return retval = new ParameterizedIndicatorsParser.trendconstant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal110=null;
		Token string_literal111=null;

		CommonTree string_literal110_tree=null;
		CommonTree string_literal111_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:15: ( 'bullish' -> ^( String StringToken[\"\\\"bullish\\\"\"] ) | 'bearish' -> ^( String StringToken[\"\\\"bearish\\\"\"] ) )
			int alt32=2;
			int LA32_0 = input.LA(1);
			if ( (LA32_0==70) ) {
				alt32=1;
			}
			else if ( (LA32_0==69) ) {
				alt32=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 32, 0, input);
				throw nvae;
			}

			switch (alt32) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:17: 'bullish'
					{
					string_literal110=(Token)match(input,70,FOLLOW_70_in_trendconstant1336);  
					stream_70.add(string_literal110);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 240:27: -> ^( String StringToken[\"\\\"bullish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:30: ^( String StringToken[\"\\\"bullish\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:69: 'bearish'
					{
					string_literal111=(Token)match(input,69,FOLLOW_69_in_trendconstant1349);  
					stream_69.add(string_literal111);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 240:79: -> ^( String StringToken[\"\\\"bearish\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:240:82: ^( String StringToken[\"\\\"bearish\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:1: lenient : ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) ;
	public final ParameterizedIndicatorsParser.lenient_return lenient() throws RecognitionException {
		ParameterizedIndicatorsParser.lenient_return retval = new ParameterizedIndicatorsParser.lenient_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WhiteChar112=null;
		Token LENIENT113=null;

		CommonTree WhiteChar112_tree=null;
		CommonTree LENIENT113_tree=null;
		RewriteRuleTokenStream stream_LENIENT=new RewriteRuleTokenStream(adaptor,"token LENIENT");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:9: ( ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:11: ( WhiteChar LENIENT -> ^( String StringToken[\"\\\"TRUE\\\"\"] ) | -> ^( String StringToken[\"\\\"FALSE\\\"\"] ) )
			int alt33=2;
			int LA33_0 = input.LA(1);
			if ( (LA33_0==WhiteChar) ) {
				int LA33_1 = input.LA(2);
				if ( (LA33_1==LENIENT) ) {
					alt33=1;
				}
				else if ( (LA33_1==AND||LA33_1==CLOSEPARENTEHSIS||LA33_1==SEMICOLUMN||LA33_1==WhiteChar||LA33_1==92) ) {
					alt33=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 33, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA33_0==CLOSEPARENTEHSIS||LA33_0==COMMA||LA33_0==SEMICOLUMN||LA33_0==103) ) {
				alt33=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 33, 0, input);
				throw nvae;
			}

			switch (alt33) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:12: WhiteChar LENIENT
					{
					WhiteChar112=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_lenient1366);  
					stream_WhiteChar.add(WhiteChar112);

					LENIENT113=(Token)match(input,LENIENT,FOLLOW_LENIENT_in_lenient1368);  
					stream_LENIENT.add(LENIENT113);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 241:30: -> ^( String StringToken[\"\\\"TRUE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:33: ^( String StringToken[\"\\\"TRUE\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:69: 
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
					// 241:69: -> ^( String StringToken[\"\\\"FALSE\\\"\"] )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:241:72: ^( String StringToken[\"\\\"FALSE\\\"\"] )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:1: opcmpcondition[CommonTree firstOp] : ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )? | ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) );
	public final ParameterizedIndicatorsParser.opcmpcondition_return opcmpcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.opcmpcondition_return retval = new ParameterizedIndicatorsParser.opcmpcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal114=null;
		Token WhiteChar115=null;
		Token WhiteChar116=null;
		Token string_literal117=null;
		Token WhiteChar118=null;
		Token WhiteChar119=null;
		Token DAYS120=null;
		Token WhiteChar121=null;
		Token string_literal122=null;
		Token WhiteChar123=null;
		Token PERCENT124=null;
		Token string_literal125=null;
		Token WhiteChar126=null;
		Token WhiteChar127=null;
		Token string_literal128=null;
		Token WhiteChar129=null;
		Token WhiteChar130=null;
		Token DAYS131=null;
		Token WhiteChar132=null;
		Token string_literal133=null;
		Token WhiteChar134=null;
		Token PERCENT135=null;
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
		Token PERCENT146=null;
		Token string_literal147=null;
		Token WhiteChar148=null;
		Token WhiteChar149=null;
		Token string_literal150=null;
		Token WhiteChar151=null;
		Token WhiteChar152=null;
		Token DAYS153=null;
		Token WhiteChar154=null;
		Token string_literal155=null;
		Token WhiteChar156=null;
		Token WhiteChar157=null;
		Token DAYS158=null;
		Token WhiteChar159=null;
		Token string_literal160=null;
		Token WhiteChar161=null;
		Token PERCENT162=null;
		Token WhiteChar163=null;
		Token string_literal164=null;
		Token WhiteChar165=null;
		Token PERCENT166=null;
		Token WhiteChar167=null;
		Token string_literal168=null;
		Token WhiteChar169=null;
		Token WhiteChar170=null;
		Token string_literal171=null;
		Token WhiteChar172=null;
		Token WhiteChar173=null;
		Token DAYS174=null;
		Token WhiteChar175=null;
		Token string_literal176=null;
		Token WhiteChar177=null;
		Token WhiteChar178=null;
		Token DAYS179=null;
		Token WhiteChar180=null;
		Token string_literal181=null;
		Token WhiteChar182=null;
		Token PERCENT183=null;
		Token WhiteChar184=null;
		Token string_literal185=null;
		Token WhiteChar186=null;
		Token PERCENT187=null;
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
		Token WhiteChar204=null;
		Token string_literal205=null;
		Token WhiteChar206=null;
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
		Token WhiteChar219=null;
		Token string_literal220=null;
		Token WhiteChar221=null;
		ParserRuleReturnScope secondOp =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope epsilon =null;
		ParserRuleReturnScope spanningNbDays =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope adaptiveRate =null;
		ParserRuleReturnScope adaptive =null;
		ParserRuleReturnScope direction =null;

		CommonTree string_literal114_tree=null;
		CommonTree WhiteChar115_tree=null;
		CommonTree WhiteChar116_tree=null;
		CommonTree string_literal117_tree=null;
		CommonTree WhiteChar118_tree=null;
		CommonTree WhiteChar119_tree=null;
		CommonTree DAYS120_tree=null;
		CommonTree WhiteChar121_tree=null;
		CommonTree string_literal122_tree=null;
		CommonTree WhiteChar123_tree=null;
		CommonTree PERCENT124_tree=null;
		CommonTree string_literal125_tree=null;
		CommonTree WhiteChar126_tree=null;
		CommonTree WhiteChar127_tree=null;
		CommonTree string_literal128_tree=null;
		CommonTree WhiteChar129_tree=null;
		CommonTree WhiteChar130_tree=null;
		CommonTree DAYS131_tree=null;
		CommonTree WhiteChar132_tree=null;
		CommonTree string_literal133_tree=null;
		CommonTree WhiteChar134_tree=null;
		CommonTree PERCENT135_tree=null;
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
		CommonTree PERCENT146_tree=null;
		CommonTree string_literal147_tree=null;
		CommonTree WhiteChar148_tree=null;
		CommonTree WhiteChar149_tree=null;
		CommonTree string_literal150_tree=null;
		CommonTree WhiteChar151_tree=null;
		CommonTree WhiteChar152_tree=null;
		CommonTree DAYS153_tree=null;
		CommonTree WhiteChar154_tree=null;
		CommonTree string_literal155_tree=null;
		CommonTree WhiteChar156_tree=null;
		CommonTree WhiteChar157_tree=null;
		CommonTree DAYS158_tree=null;
		CommonTree WhiteChar159_tree=null;
		CommonTree string_literal160_tree=null;
		CommonTree WhiteChar161_tree=null;
		CommonTree PERCENT162_tree=null;
		CommonTree WhiteChar163_tree=null;
		CommonTree string_literal164_tree=null;
		CommonTree WhiteChar165_tree=null;
		CommonTree PERCENT166_tree=null;
		CommonTree WhiteChar167_tree=null;
		CommonTree string_literal168_tree=null;
		CommonTree WhiteChar169_tree=null;
		CommonTree WhiteChar170_tree=null;
		CommonTree string_literal171_tree=null;
		CommonTree WhiteChar172_tree=null;
		CommonTree WhiteChar173_tree=null;
		CommonTree DAYS174_tree=null;
		CommonTree WhiteChar175_tree=null;
		CommonTree string_literal176_tree=null;
		CommonTree WhiteChar177_tree=null;
		CommonTree WhiteChar178_tree=null;
		CommonTree DAYS179_tree=null;
		CommonTree WhiteChar180_tree=null;
		CommonTree string_literal181_tree=null;
		CommonTree WhiteChar182_tree=null;
		CommonTree PERCENT183_tree=null;
		CommonTree WhiteChar184_tree=null;
		CommonTree string_literal185_tree=null;
		CommonTree WhiteChar186_tree=null;
		CommonTree PERCENT187_tree=null;
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
		CommonTree WhiteChar204_tree=null;
		CommonTree string_literal205_tree=null;
		CommonTree WhiteChar206_tree=null;
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
		CommonTree WhiteChar219_tree=null;
		CommonTree string_literal220_tree=null;
		CommonTree WhiteChar221_tree=null;
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");
		RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
		RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_100=new RewriteRuleTokenStream(adaptor,"token 100");
		RewriteRuleTokenStream stream_113=new RewriteRuleTokenStream(adaptor,"token 113");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_114=new RewriteRuleTokenStream(adaptor,"token 114");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
		RewriteRuleTokenStream stream_108=new RewriteRuleTokenStream(adaptor,"token 108");
		RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
		RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
		RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");
		RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
		RewriteRuleSubtreeStream stream_stringconstant=new RewriteRuleSubtreeStream(adaptor,"rule stringconstant");
		RewriteRuleSubtreeStream stream_operand=new RewriteRuleSubtreeStream(adaptor,"rule operand");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:243:37: ( ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )? | ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )? | ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )? | ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )? | ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )? | ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) ) | ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) ) )
			int alt41=7;
			switch ( input.LA(1) ) {
			case 85:
				{
				alt41=1;
				}
				break;
			case 89:
				{
				alt41=2;
				}
				break;
			case 78:
				{
				alt41=3;
				}
				break;
			case 71:
				{
				alt41=4;
				}
				break;
			case 73:
				{
				alt41=5;
				}
				break;
			case 113:
				{
				alt41=6;
				}
				break;
			case 114:
				{
				alt41=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 41, 0, input);
				throw nvae;
			}
			switch (alt41) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:3: ( 'is above historical' WhiteChar secondOp= operand -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:4: 'is above historical' WhiteChar secondOp= operand
					{
					string_literal114=(Token)match(input,85,FOLLOW_85_in_opcmpcondition1405);  
					stream_85.add(string_literal114);

					WhiteChar115=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1407);  
					stream_WhiteChar.add(WhiteChar115);

					pushFollow(FOLLOW_operand_in_opcmpcondition1411);
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
					// 246:53: -> ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:56: ^( SupDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupDoubleMapCondition, "SupDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:80: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:246:107: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupDoubleMapCondition ) )?
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
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:247:5: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar116=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1442);  
							stream_WhiteChar.add(WhiteChar116);

							string_literal117=(Token)match(input,81,FOLLOW_81_in_opcmpcondition1444);  
							stream_81.add(string_literal117);

							WhiteChar118=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1446);  
							stream_WhiteChar.add(WhiteChar118);

							pushFollow(FOLLOW_constant_in_opcmpcondition1450);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar119=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1452);  
							stream_WhiteChar.add(WhiteChar119);

							DAYS120=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1454);  
							stream_DAYS.add(DAYS120);

							WhiteChar121=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1456);  
							stream_WhiteChar.add(WhiteChar121);

							string_literal122=(Token)match(input,77,FOLLOW_77_in_opcmpcondition1458);  
							stream_77.add(string_literal122);

							WhiteChar123=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1460);  
							stream_WhiteChar.add(WhiteChar123);

							pushFollow(FOLLOW_constant_in_opcmpcondition1464);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT124=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1466);  
							stream_PERCENT.add(PERCENT124);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 248:5: -> ^( SupDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:248:8: ^( SupDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:3: ( 'is below historical' WhiteChar secondOp= operand -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:4: 'is below historical' WhiteChar secondOp= operand
					{
					string_literal125=(Token)match(input,89,FOLLOW_89_in_opcmpcondition1498);  
					stream_89.add(string_literal125);

					WhiteChar126=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1500);  
					stream_WhiteChar.add(WhiteChar126);

					pushFollow(FOLLOW_operand_in_opcmpcondition1504);
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
					// 250:53: -> ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:56: ^( InfDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfDoubleMapCondition, "InfDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:80: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:250:107: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfDoubleMapCondition ) )?
					int alt35=2;
					int LA35_0 = input.LA(1);
					if ( (LA35_0==WhiteChar) ) {
						int LA35_1 = input.LA(2);
						if ( (LA35_1==81) ) {
							alt35=1;
						}
					}
					switch (alt35) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:251:5: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar127=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1535);  
							stream_WhiteChar.add(WhiteChar127);

							string_literal128=(Token)match(input,81,FOLLOW_81_in_opcmpcondition1537);  
							stream_81.add(string_literal128);

							WhiteChar129=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1539);  
							stream_WhiteChar.add(WhiteChar129);

							pushFollow(FOLLOW_constant_in_opcmpcondition1543);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar130=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1545);  
							stream_WhiteChar.add(WhiteChar130);

							DAYS131=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1547);  
							stream_DAYS.add(DAYS131);

							WhiteChar132=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1549);  
							stream_WhiteChar.add(WhiteChar132);

							string_literal133=(Token)match(input,77,FOLLOW_77_in_opcmpcondition1551);  
							stream_77.add(string_literal133);

							WhiteChar134=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1553);  
							stream_WhiteChar.add(WhiteChar134);

							pushFollow(FOLLOW_constant_in_opcmpcondition1557);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT135=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1559);  
							stream_PERCENT.add(PERCENT135);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 252:5: -> ^( InfDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:252:8: ^( InfDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) ) ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:3: ( 'equals historical' WhiteChar secondOp= operand -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:4: 'equals historical' WhiteChar secondOp= operand
					{
					string_literal136=(Token)match(input,78,FOLLOW_78_in_opcmpcondition1590);  
					stream_78.add(string_literal136);

					WhiteChar137=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1592);  
					stream_WhiteChar.add(WhiteChar137);

					pushFollow(FOLLOW_operand_in_opcmpcondition1596);
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
					// 254:51: -> ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:54: ^( EqualDoubleMapCondition ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0.0\"] ) operand )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualDoubleMapCondition, "EqualDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:80: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:254:107: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:4: ( WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualDoubleMapCondition ) )?
					int alt36=2;
					int LA36_0 = input.LA(1);
					if ( (LA36_0==WhiteChar) ) {
						int LA36_1 = input.LA(2);
						if ( (LA36_1==81) ) {
							alt36=1;
						}
					}
					switch (alt36) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:255:5: WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar138=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1627);  
							stream_WhiteChar.add(WhiteChar138);

							string_literal139=(Token)match(input,81,FOLLOW_81_in_opcmpcondition1629);  
							stream_81.add(string_literal139);

							WhiteChar140=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1631);  
							stream_WhiteChar.add(WhiteChar140);

							pushFollow(FOLLOW_constant_in_opcmpcondition1635);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar141=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1637);  
							stream_WhiteChar.add(WhiteChar141);

							DAYS142=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1639);  
							stream_DAYS.add(DAYS142);

							WhiteChar143=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1641);  
							stream_WhiteChar.add(WhiteChar143);

							string_literal144=(Token)match(input,77,FOLLOW_77_in_opcmpcondition1643);  
							stream_77.add(string_literal144);

							WhiteChar145=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1645);  
							stream_WhiteChar.add(WhiteChar145);

							pushFollow(FOLLOW_constant_in_opcmpcondition1649);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT146=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1651);  
							stream_PERCENT.add(PERCENT146);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 256:5: -> ^( EqualDoubleMapCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:256:8: ^( EqualDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:3: ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:3: ( 'crosses down historical' WhiteChar secondOp= operand -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:260:4: 'crosses down historical' WhiteChar secondOp= operand
					{
					string_literal147=(Token)match(input,71,FOLLOW_71_in_opcmpcondition1684);  
					stream_71.add(string_literal147);

					WhiteChar148=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1686);  
					stream_WhiteChar.add(WhiteChar148);

					pushFollow(FOLLOW_operand_in_opcmpcondition1690);
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
					// 261:4: -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:7: ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:37: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:66: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:95: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:261:124: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:3: ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )? )?
					int alt38=2;
					int LA38_0 = input.LA(1);
					if ( (LA38_0==WhiteChar) ) {
						int LA38_1 = input.LA(2);
						if ( (LA38_1==108) ) {
							alt38=1;
						}
					}
					switch (alt38) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )?
							{
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:263:5: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar149=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1744);  
							stream_WhiteChar.add(WhiteChar149);

							string_literal150=(Token)match(input,108,FOLLOW_108_in_opcmpcondition1746);  
							stream_108.add(string_literal150);

							WhiteChar151=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1748);  
							stream_WhiteChar.add(WhiteChar151);

							pushFollow(FOLLOW_constant_in_opcmpcondition1752);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar152=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1754);  
							stream_WhiteChar.add(WhiteChar152);

							DAYS153=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1756);  
							stream_DAYS.add(DAYS153);

							WhiteChar154=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1758);  
							stream_WhiteChar.add(WhiteChar154);

							string_literal155=(Token)match(input,100,FOLLOW_100_in_opcmpcondition1760);  
							stream_100.add(string_literal155);

							WhiteChar156=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1762);  
							stream_WhiteChar.add(WhiteChar156);

							pushFollow(FOLLOW_constant_in_opcmpcondition1766);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar157=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1768);  
							stream_WhiteChar.add(WhiteChar157);

							DAYS158=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1770);  
							stream_DAYS.add(DAYS158);

							WhiteChar159=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1772);  
							stream_WhiteChar.add(WhiteChar159);

							string_literal160=(Token)match(input,77,FOLLOW_77_in_opcmpcondition1774);  
							stream_77.add(string_literal160);

							WhiteChar161=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1776);  
							stream_WhiteChar.add(WhiteChar161);

							pushFollow(FOLLOW_constant_in_opcmpcondition1780);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT162=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1782);  
							stream_PERCENT.add(PERCENT162);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 264:5: -> ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:8: ^( CrossDownDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownDoubleMapCondition, "CrossDownDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:264:96: ^( Number NumberToken[\"0.0\"] )
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

							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:4: ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossDownDoubleMapCondition ) )?
							int alt37=2;
							int LA37_0 = input.LA(1);
							if ( (LA37_0==WhiteChar) ) {
								int LA37_1 = input.LA(2);
								if ( (LA37_1==67) ) {
									alt37=1;
								}
							}
							switch (alt37) {
								case 1 :
									// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:5: WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom
									{
									WhiteChar163=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1822);  
									stream_WhiteChar.add(WhiteChar163);

									string_literal164=(Token)match(input,67,FOLLOW_67_in_opcmpcondition1824);  
									stream_67.add(string_literal164);

									WhiteChar165=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1826);  
									stream_WhiteChar.add(WhiteChar165);

									pushFollow(FOLLOW_constant_in_opcmpcondition1830);
									adaptiveRate=constant();
									state._fsp--;

									stream_constant.add(adaptiveRate.getTree());
									PERCENT166=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1832);  
									stream_PERCENT.add(PERCENT166);

									WhiteChar167=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1834);  
									stream_WhiteChar.add(WhiteChar167);

									pushFollow(FOLLOW_atom_in_opcmpcondition1838);
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
									// 267:5: -> ^( CrossDownDoubleMapCondition )
									{
										// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:267:8: ^( CrossDownDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:3: ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:3: ( 'crosses up historical' WhiteChar secondOp= operand -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:4: 'crosses up historical' WhiteChar secondOp= operand
					{
					string_literal168=(Token)match(input,73,FOLLOW_73_in_opcmpcondition1881);  
					stream_73.add(string_literal168);

					WhiteChar169=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1883);  
					stream_WhiteChar.add(WhiteChar169);

					pushFollow(FOLLOW_operand_in_opcmpcondition1887);
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
					// 271:4: -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:7: ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) NullCondition )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:35: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:64: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:93: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:271:122: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:273:3: ( ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )? )?
					int alt40=2;
					int LA40_0 = input.LA(1);
					if ( (LA40_0==WhiteChar) ) {
						int LA40_1 = input.LA(2);
						if ( (LA40_1==108) ) {
							alt40=1;
						}
					}
					switch (alt40) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:273:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) ) ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )?
							{
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:273:4: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition ) )
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:273:5: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar170=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1941);  
							stream_WhiteChar.add(WhiteChar170);

							string_literal171=(Token)match(input,108,FOLLOW_108_in_opcmpcondition1943);  
							stream_108.add(string_literal171);

							WhiteChar172=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1945);  
							stream_WhiteChar.add(WhiteChar172);

							pushFollow(FOLLOW_constant_in_opcmpcondition1949);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar173=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1951);  
							stream_WhiteChar.add(WhiteChar173);

							DAYS174=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1953);  
							stream_DAYS.add(DAYS174);

							WhiteChar175=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1955);  
							stream_WhiteChar.add(WhiteChar175);

							string_literal176=(Token)match(input,100,FOLLOW_100_in_opcmpcondition1957);  
							stream_100.add(string_literal176);

							WhiteChar177=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1959);  
							stream_WhiteChar.add(WhiteChar177);

							pushFollow(FOLLOW_constant_in_opcmpcondition1963);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar178=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1965);  
							stream_WhiteChar.add(WhiteChar178);

							DAYS179=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition1967);  
							stream_DAYS.add(DAYS179);

							WhiteChar180=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1969);  
							stream_WhiteChar.add(WhiteChar180);

							string_literal181=(Token)match(input,77,FOLLOW_77_in_opcmpcondition1971);  
							stream_77.add(string_literal181);

							WhiteChar182=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition1973);  
							stream_WhiteChar.add(WhiteChar182);

							pushFollow(FOLLOW_constant_in_opcmpcondition1977);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT183=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition1979);  
							stream_PERCENT.add(PERCENT183);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 274:5: -> ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:8: ^( CrossUpDoubleMapCondition ^( Number NumberToken[\"0.0\"] ) NullCondition )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpDoubleMapCondition, "CrossUpDoubleMapCondition"), root_1);
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								adaptor.addChild(root_1, (epsilon!=null?((CommonTree)epsilon.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:94: ^( Number NumberToken[\"0.0\"] )
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

							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:4: ( WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom -> ^( CrossUpDoubleMapCondition ) )?
							int alt39=2;
							int LA39_0 = input.LA(1);
							if ( (LA39_0==WhiteChar) ) {
								int LA39_1 = input.LA(2);
								if ( (LA39_1==67) ) {
									alt39=1;
								}
							}
							switch (alt39) {
								case 1 :
									// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:276:5: WhiteChar 'adaptiveRate' WhiteChar adaptiveRate= constant PERCENT WhiteChar adaptive= atom
									{
									WhiteChar184=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2019);  
									stream_WhiteChar.add(WhiteChar184);

									string_literal185=(Token)match(input,67,FOLLOW_67_in_opcmpcondition2021);  
									stream_67.add(string_literal185);

									WhiteChar186=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2023);  
									stream_WhiteChar.add(WhiteChar186);

									pushFollow(FOLLOW_constant_in_opcmpcondition2027);
									adaptiveRate=constant();
									state._fsp--;

									stream_constant.add(adaptiveRate.getTree());
									PERCENT187=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_opcmpcondition2029);  
									stream_PERCENT.add(PERCENT187);

									WhiteChar188=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2031);  
									stream_WhiteChar.add(WhiteChar188);

									pushFollow(FOLLOW_atom_in_opcmpcondition2035);
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
									// 277:5: -> ^( CrossUpDoubleMapCondition )
									{
										// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:277:8: ^( CrossUpDoubleMapCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:3: ( 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearSimilarTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:4: 'trends like' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal189=(Token)match(input,113,FOLLOW_113_in_opcmpcondition2080);  
					stream_113.add(string_literal189);

					WhiteChar190=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2082);  
					stream_WhiteChar.add(WhiteChar190);

					pushFollow(FOLLOW_operand_in_opcmpcondition2086);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar191=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2094);  
					stream_WhiteChar.add(WhiteChar191);

					string_literal192=(Token)match(input,100,FOLLOW_100_in_opcmpcondition2096);  
					stream_100.add(string_literal192);

					WhiteChar193=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2098);  
					stream_WhiteChar.add(WhiteChar193);

					pushFollow(FOLLOW_constant_in_opcmpcondition2102);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar194=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2104);  
					stream_WhiteChar.add(WhiteChar194);

					DAYS195=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition2106);  
					stream_DAYS.add(DAYS195);

					WhiteChar196=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2114);  
					stream_WhiteChar.add(WhiteChar196);

					string_literal197=(Token)match(input,81,FOLLOW_81_in_opcmpcondition2116);  
					stream_81.add(string_literal197);

					WhiteChar198=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2118);  
					stream_WhiteChar.add(WhiteChar198);

					pushFollow(FOLLOW_constant_in_opcmpcondition2122);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar199=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2124);  
					stream_WhiteChar.add(WhiteChar199);

					DAYS200=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition2126);  
					stream_DAYS.add(DAYS200);

					WhiteChar201=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2134);  
					stream_WhiteChar.add(WhiteChar201);

					string_literal202=(Token)match(input,75,FOLLOW_75_in_opcmpcondition2136);  
					stream_75.add(string_literal202);

					WhiteChar203=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2138);  
					stream_WhiteChar.add(WhiteChar203);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition2142);
					direction=stringconstant();
					state._fsp--;

					stream_stringconstant.add(direction.getTree());
					WhiteChar204=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2150);  
					stream_WhiteChar.add(WhiteChar204);

					string_literal205=(Token)match(input,77,FOLLOW_77_in_opcmpcondition2152);  
					stream_77.add(string_literal205);

					WhiteChar206=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2154);  
					stream_WhiteChar.add(WhiteChar206);

					pushFollow(FOLLOW_constant_in_opcmpcondition2158);
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
					// 287:7: -> ^( LinearSimilarTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:287:10: ^( LinearSimilarTrendsCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:3: ( 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant -> ^( LinearOppositeTrendsCondition ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:288:4: 'trends unlike' WhiteChar secondOp= operand WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'direction' WhiteChar direction= stringconstant
					{
					string_literal207=(Token)match(input,114,FOLLOW_114_in_opcmpcondition2190);  
					stream_114.add(string_literal207);

					WhiteChar208=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2192);  
					stream_WhiteChar.add(WhiteChar208);

					pushFollow(FOLLOW_operand_in_opcmpcondition2196);
					secondOp=operand();
					state._fsp--;

					stream_operand.add(secondOp.getTree());
					WhiteChar209=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2204);  
					stream_WhiteChar.add(WhiteChar209);

					string_literal210=(Token)match(input,100,FOLLOW_100_in_opcmpcondition2206);  
					stream_100.add(string_literal210);

					WhiteChar211=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2208);  
					stream_WhiteChar.add(WhiteChar211);

					pushFollow(FOLLOW_constant_in_opcmpcondition2212);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar212=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2214);  
					stream_WhiteChar.add(WhiteChar212);

					DAYS213=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition2216);  
					stream_DAYS.add(DAYS213);

					WhiteChar214=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2224);  
					stream_WhiteChar.add(WhiteChar214);

					string_literal215=(Token)match(input,81,FOLLOW_81_in_opcmpcondition2226);  
					stream_81.add(string_literal215);

					WhiteChar216=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2228);  
					stream_WhiteChar.add(WhiteChar216);

					pushFollow(FOLLOW_constant_in_opcmpcondition2232);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar217=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2234);  
					stream_WhiteChar.add(WhiteChar217);

					DAYS218=(Token)match(input,DAYS,FOLLOW_DAYS_in_opcmpcondition2236);  
					stream_DAYS.add(DAYS218);

					WhiteChar219=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2244);  
					stream_WhiteChar.add(WhiteChar219);

					string_literal220=(Token)match(input,75,FOLLOW_75_in_opcmpcondition2246);  
					stream_75.add(string_literal220);

					WhiteChar221=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_opcmpcondition2248);  
					stream_WhiteChar.add(WhiteChar221);

					pushFollow(FOLLOW_stringconstant_in_opcmpcondition2252);
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
					// 292:7: -> ^( LinearOppositeTrendsCondition )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:292:10: ^( LinearOppositeTrendsCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:1: constantcmp[CommonTree firstOp] : ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )? );
	public final ParameterizedIndicatorsParser.constantcmp_return constantcmp(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.constantcmp_return retval = new ParameterizedIndicatorsParser.constantcmp_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal222=null;
		Token WhiteChar223=null;
		Token WhiteChar224=null;
		Token string_literal225=null;
		Token WhiteChar226=null;
		Token WhiteChar227=null;
		Token DAYS228=null;
		Token WhiteChar229=null;
		Token string_literal230=null;
		Token WhiteChar231=null;
		Token WhiteChar232=null;
		Token DAYS233=null;
		Token string_literal234=null;
		Token WhiteChar235=null;
		Token WhiteChar236=null;
		Token string_literal237=null;
		Token WhiteChar238=null;
		Token WhiteChar239=null;
		Token DAYS240=null;
		Token WhiteChar241=null;
		Token string_literal242=null;
		Token WhiteChar243=null;
		Token WhiteChar244=null;
		Token DAYS245=null;
		Token WhiteChar246=null;
		Token string_literal247=null;
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
		Token PERCENT265=null;
		Token string_literal266=null;
		Token WhiteChar267=null;
		Token WhiteChar268=null;
		Token string_literal269=null;
		Token WhiteChar270=null;
		Token WhiteChar271=null;
		Token DAYS272=null;
		Token WhiteChar273=null;
		Token string_literal274=null;
		Token WhiteChar275=null;
		Token WhiteChar276=null;
		Token DAYS277=null;
		Token WhiteChar278=null;
		Token string_literal279=null;
		Token WhiteChar280=null;
		Token PERCENT281=null;
		ParserRuleReturnScope trendSignal =null;
		ParserRuleReturnScope overNbDays =null;
		ParserRuleReturnScope forNbDays =null;
		ParserRuleReturnScope threshold =null;
		ParserRuleReturnScope epsilon =null;

		CommonTree string_literal222_tree=null;
		CommonTree WhiteChar223_tree=null;
		CommonTree WhiteChar224_tree=null;
		CommonTree string_literal225_tree=null;
		CommonTree WhiteChar226_tree=null;
		CommonTree WhiteChar227_tree=null;
		CommonTree DAYS228_tree=null;
		CommonTree WhiteChar229_tree=null;
		CommonTree string_literal230_tree=null;
		CommonTree WhiteChar231_tree=null;
		CommonTree WhiteChar232_tree=null;
		CommonTree DAYS233_tree=null;
		CommonTree string_literal234_tree=null;
		CommonTree WhiteChar235_tree=null;
		CommonTree WhiteChar236_tree=null;
		CommonTree string_literal237_tree=null;
		CommonTree WhiteChar238_tree=null;
		CommonTree WhiteChar239_tree=null;
		CommonTree DAYS240_tree=null;
		CommonTree WhiteChar241_tree=null;
		CommonTree string_literal242_tree=null;
		CommonTree WhiteChar243_tree=null;
		CommonTree WhiteChar244_tree=null;
		CommonTree DAYS245_tree=null;
		CommonTree WhiteChar246_tree=null;
		CommonTree string_literal247_tree=null;
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
		CommonTree PERCENT265_tree=null;
		CommonTree string_literal266_tree=null;
		CommonTree WhiteChar267_tree=null;
		CommonTree WhiteChar268_tree=null;
		CommonTree string_literal269_tree=null;
		CommonTree WhiteChar270_tree=null;
		CommonTree WhiteChar271_tree=null;
		CommonTree DAYS272_tree=null;
		CommonTree WhiteChar273_tree=null;
		CommonTree string_literal274_tree=null;
		CommonTree WhiteChar275_tree=null;
		CommonTree WhiteChar276_tree=null;
		CommonTree DAYS277_tree=null;
		CommonTree WhiteChar278_tree=null;
		CommonTree string_literal279_tree=null;
		CommonTree WhiteChar280_tree=null;
		CommonTree PERCENT281_tree=null;
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_100=new RewriteRuleTokenStream(adaptor,"token 100");
		RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
		RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
		RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_trendconstant=new RewriteRuleSubtreeStream(adaptor,"rule trendconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:34: ( ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )? | ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )? | ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )? | ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )? )
			int alt46=4;
			switch ( input.LA(1) ) {
			case 80:
				{
				alt46=1;
				}
				break;
			case 79:
				{
				alt46=2;
				}
				break;
			case 86:
				{
				alt46=3;
				}
				break;
			case 90:
				{
				alt46=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 46, 0, input);
				throw nvae;
			}
			switch (alt46) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:296:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:296:3: ( 'equals trend' WhiteChar trendSignal= trendconstant -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:296:4: 'equals trend' WhiteChar trendSignal= trendconstant
					{
					string_literal222=(Token)match(input,80,FOLLOW_80_in_constantcmp2289);  
					stream_80.add(string_literal222);

					WhiteChar223=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2291);  
					stream_WhiteChar.add(WhiteChar223);

					pushFollow(FOLLOW_trendconstant_in_constantcmp2295);
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
					// 296:55: -> ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:296:58: ^( EqualStringConstantCondition trendconstant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualStringConstantCondition, "EqualStringConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_trendconstant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:296:103: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:296:130: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( EqualStringConstantCondition ) )?
					int alt42=2;
					int LA42_0 = input.LA(1);
					if ( (LA42_0==WhiteChar) ) {
						int LA42_1 = input.LA(2);
						if ( (LA42_1==100) ) {
							alt42=1;
						}
					}
					switch (alt42) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar224=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2329);  
							stream_WhiteChar.add(WhiteChar224);

							string_literal225=(Token)match(input,100,FOLLOW_100_in_constantcmp2331);  
							stream_100.add(string_literal225);

							WhiteChar226=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2333);  
							stream_WhiteChar.add(WhiteChar226);

							pushFollow(FOLLOW_constant_in_constantcmp2337);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar227=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2339);  
							stream_WhiteChar.add(WhiteChar227);

							DAYS228=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2341);  
							stream_DAYS.add(DAYS228);

							WhiteChar229=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2343);  
							stream_WhiteChar.add(WhiteChar229);

							string_literal230=(Token)match(input,81,FOLLOW_81_in_constantcmp2345);  
							stream_81.add(string_literal230);

							WhiteChar231=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2347);  
							stream_WhiteChar.add(WhiteChar231);

							pushFollow(FOLLOW_constant_in_constantcmp2351);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar232=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2353);  
							stream_WhiteChar.add(WhiteChar232);

							DAYS233=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2355);  
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
							// 297:129: -> ^( EqualStringConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:132: ^( EqualStringConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:3: ( 'equals threshold' WhiteChar threshold= constant -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:4: 'equals threshold' WhiteChar threshold= constant
					{
					string_literal234=(Token)match(input,79,FOLLOW_79_in_constantcmp2379);  
					stream_79.add(string_literal234);

					WhiteChar235=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2381);  
					stream_WhiteChar.add(WhiteChar235);

					pushFollow(FOLLOW_constant_in_constantcmp2385);
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
					// 299:52: -> ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:55: ^( EqualConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EqualConstantCondition, "EqualConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:299:143: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:300:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( EqualConstantCondition ) )?
					int alt43=2;
					int LA43_0 = input.LA(1);
					if ( (LA43_0==WhiteChar) ) {
						int LA43_1 = input.LA(2);
						if ( (LA43_1==100) ) {
							alt43=1;
						}
					}
					switch (alt43) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:300:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar236=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2426);  
							stream_WhiteChar.add(WhiteChar236);

							string_literal237=(Token)match(input,100,FOLLOW_100_in_constantcmp2428);  
							stream_100.add(string_literal237);

							WhiteChar238=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2430);  
							stream_WhiteChar.add(WhiteChar238);

							pushFollow(FOLLOW_constant_in_constantcmp2434);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar239=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2436);  
							stream_WhiteChar.add(WhiteChar239);

							DAYS240=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2438);  
							stream_DAYS.add(DAYS240);

							WhiteChar241=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2444);  
							stream_WhiteChar.add(WhiteChar241);

							string_literal242=(Token)match(input,81,FOLLOW_81_in_constantcmp2446);  
							stream_81.add(string_literal242);

							WhiteChar243=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2448);  
							stream_WhiteChar.add(WhiteChar243);

							pushFollow(FOLLOW_constant_in_constantcmp2452);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar244=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2454);  
							stream_WhiteChar.add(WhiteChar244);

							DAYS245=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2456);  
							stream_DAYS.add(DAYS245);

							WhiteChar246=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2462);  
							stream_WhiteChar.add(WhiteChar246);

							string_literal247=(Token)match(input,77,FOLLOW_77_in_constantcmp2464);  
							stream_77.add(string_literal247);

							WhiteChar248=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2466);  
							stream_WhiteChar.add(WhiteChar248);

							pushFollow(FOLLOW_constant_in_constantcmp2470);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT249=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2472);  
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
							// 303:5: -> ^( EqualConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:303:8: ^( EqualConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:3: ( 'is above threshold' WhiteChar threshold= constant -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:4: 'is above threshold' WhiteChar threshold= constant
					{
					string_literal250=(Token)match(input,86,FOLLOW_86_in_constantcmp2502);  
					stream_86.add(string_literal250);

					WhiteChar251=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2504);  
					stream_WhiteChar.add(WhiteChar251);

					pushFollow(FOLLOW_constant_in_constantcmp2508);
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
					// 304:54: -> ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:57: ^( SupConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupConstantCondition, "SupConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:304:143: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:305:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( SupConstantCondition ) )?
					int alt44=2;
					int LA44_0 = input.LA(1);
					if ( (LA44_0==WhiteChar) ) {
						int LA44_1 = input.LA(2);
						if ( (LA44_1==100) ) {
							alt44=1;
						}
					}
					switch (alt44) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:305:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar252=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2549);  
							stream_WhiteChar.add(WhiteChar252);

							string_literal253=(Token)match(input,100,FOLLOW_100_in_constantcmp2551);  
							stream_100.add(string_literal253);

							WhiteChar254=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2553);  
							stream_WhiteChar.add(WhiteChar254);

							pushFollow(FOLLOW_constant_in_constantcmp2557);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar255=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2559);  
							stream_WhiteChar.add(WhiteChar255);

							DAYS256=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2561);  
							stream_DAYS.add(DAYS256);

							WhiteChar257=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2567);  
							stream_WhiteChar.add(WhiteChar257);

							string_literal258=(Token)match(input,81,FOLLOW_81_in_constantcmp2569);  
							stream_81.add(string_literal258);

							WhiteChar259=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2571);  
							stream_WhiteChar.add(WhiteChar259);

							pushFollow(FOLLOW_constant_in_constantcmp2575);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar260=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2577);  
							stream_WhiteChar.add(WhiteChar260);

							DAYS261=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2579);  
							stream_DAYS.add(DAYS261);

							WhiteChar262=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2585);  
							stream_WhiteChar.add(WhiteChar262);

							string_literal263=(Token)match(input,77,FOLLOW_77_in_constantcmp2587);  
							stream_77.add(string_literal263);

							WhiteChar264=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2589);  
							stream_WhiteChar.add(WhiteChar264);

							pushFollow(FOLLOW_constant_in_constantcmp2593);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT265=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2595);  
							stream_PERCENT.add(PERCENT265);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 308:5: -> ^( SupConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:308:8: ^( SupConstantCondition )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:3: ( 'is below threshold' WhiteChar threshold= constant -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:4: 'is below threshold' WhiteChar threshold= constant
					{
					string_literal266=(Token)match(input,90,FOLLOW_90_in_constantcmp2625);  
					stream_90.add(string_literal266);

					WhiteChar267=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2627);  
					stream_WhiteChar.add(WhiteChar267);

					pushFollow(FOLLOW_constant_in_constantcmp2631);
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
					// 309:54: -> ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:57: ^( InfConstantCondition constant ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(InfConstantCondition, "InfConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:89: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:116: ^( Number NumberToken[\"0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:309:143: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:310:5: ( WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( InfConstantCondition ) )?
					int alt45=2;
					int LA45_0 = input.LA(1);
					if ( (LA45_0==WhiteChar) ) {
						int LA45_1 = input.LA(2);
						if ( (LA45_1==100) ) {
							alt45=1;
						}
					}
					switch (alt45) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:310:7: WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar268=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2672);  
							stream_WhiteChar.add(WhiteChar268);

							string_literal269=(Token)match(input,100,FOLLOW_100_in_constantcmp2674);  
							stream_100.add(string_literal269);

							WhiteChar270=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2676);  
							stream_WhiteChar.add(WhiteChar270);

							pushFollow(FOLLOW_constant_in_constantcmp2680);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar271=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2682);  
							stream_WhiteChar.add(WhiteChar271);

							DAYS272=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2684);  
							stream_DAYS.add(DAYS272);

							WhiteChar273=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2690);  
							stream_WhiteChar.add(WhiteChar273);

							string_literal274=(Token)match(input,81,FOLLOW_81_in_constantcmp2692);  
							stream_81.add(string_literal274);

							WhiteChar275=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2694);  
							stream_WhiteChar.add(WhiteChar275);

							pushFollow(FOLLOW_constant_in_constantcmp2698);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar276=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2700);  
							stream_WhiteChar.add(WhiteChar276);

							DAYS277=(Token)match(input,DAYS,FOLLOW_DAYS_in_constantcmp2702);  
							stream_DAYS.add(DAYS277);

							WhiteChar278=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2708);  
							stream_WhiteChar.add(WhiteChar278);

							string_literal279=(Token)match(input,77,FOLLOW_77_in_constantcmp2710);  
							stream_77.add(string_literal279);

							WhiteChar280=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_constantcmp2712);  
							stream_WhiteChar.add(WhiteChar280);

							pushFollow(FOLLOW_constant_in_constantcmp2716);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT281=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_constantcmp2718);  
							stream_PERCENT.add(PERCENT281);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 313:5: -> ^( InfConstantCondition )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:313:8: ^( InfConstantCondition )
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
	// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:315:1: presetcondition[CommonTree firstOp] : ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) ) | ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) ) | ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) ) );
	public final ParameterizedIndicatorsParser.presetcondition_return presetcondition(CommonTree firstOp) throws RecognitionException {
		ParameterizedIndicatorsParser.presetcondition_return retval = new ParameterizedIndicatorsParser.presetcondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal282=null;
		Token WhiteChar283=null;
		Token string_literal284=null;
		Token WhiteChar285=null;
		Token PERCENT286=null;
		Token WhiteChar287=null;
		Token string_literal288=null;
		Token WhiteChar289=null;
		Token WhiteChar290=null;
		Token DAYS291=null;
		Token string_literal292=null;
		Token WhiteChar293=null;
		Token string_literal294=null;
		Token WhiteChar295=null;
		Token PERCENT296=null;
		Token WhiteChar297=null;
		Token string_literal298=null;
		Token WhiteChar299=null;
		Token WhiteChar300=null;
		Token DAYS301=null;
		Token string_literal302=null;
		Token WhiteChar303=null;
		Token PERCENT304=null;
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
		Token string_literal315=null;
		Token WhiteChar316=null;
		Token PERCENT317=null;
		Token WhiteChar318=null;
		Token string_literal319=null;
		Token WhiteChar320=null;
		Token WhiteChar321=null;
		Token DAYS322=null;
		Token WhiteChar323=null;
		Token string_literal324=null;
		Token WhiteChar325=null;
		Token WhiteChar326=null;
		Token DAYS327=null;
		Token string_literal328=null;
		Token WhiteChar329=null;
		Token WhiteChar330=null;
		Token string_literal331=null;
		Token WhiteChar332=null;
		Token WhiteChar333=null;
		Token DAYS334=null;
		Token WhiteChar335=null;
		Token string_literal336=null;
		Token WhiteChar337=null;
		Token WhiteChar338=null;
		Token DAYS339=null;
		Token WhiteChar340=null;
		Token string_literal341=null;
		Token WhiteChar342=null;
		Token PERCENT343=null;
		Token string_literal344=null;
		Token WhiteChar345=null;
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
		Token PERCENT359=null;
		Token string_literal360=null;
		Token WhiteChar361=null;
		Token WhiteChar362=null;
		Token DAYS363=null;
		Token WhiteChar364=null;
		Token string_literal365=null;
		Token WhiteChar366=null;
		Token WhiteChar367=null;
		Token DAYS368=null;
		Token WhiteChar369=null;
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
		Token string_literal383=null;
		Token WhiteChar384=null;
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
		Token WhiteChar397=null;
		Token string_literal398=null;
		Token WhiteChar399=null;
		Token char_literal400=null;
		Token char_literal401=null;
		Token char_literal402=null;
		Token string_literal403=null;
		Token WhiteChar404=null;
		Token WhiteChar405=null;
		Token DAYS406=null;
		Token WhiteChar407=null;
		Token string_literal408=null;
		Token WhiteChar409=null;
		Token WhiteChar410=null;
		Token DAYS411=null;
		Token WhiteChar412=null;
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
		Token string_literal426=null;
		Token WhiteChar427=null;
		Token WhiteChar428=null;
		Token string_literal429=null;
		Token WhiteChar430=null;
		Token char_literal431=null;
		Token char_literal432=null;
		Token char_literal433=null;
		Token WhiteChar434=null;
		Token string_literal435=null;
		Token WhiteChar436=null;
		Token char_literal437=null;
		Token char_literal438=null;
		Token char_literal439=null;
		Token WhiteChar440=null;
		Token string_literal441=null;
		Token WhiteChar442=null;
		Token char_literal443=null;
		Token char_literal444=null;
		Token char_literal445=null;
		Token string_literal446=null;
		Token WhiteChar447=null;
		Token WhiteChar448=null;
		Token DAYS449=null;
		Token WhiteChar450=null;
		Token string_literal451=null;
		Token WhiteChar452=null;
		Token WhiteChar453=null;
		Token DAYS454=null;
		Token WhiteChar455=null;
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
		Token string_literal469=null;
		Token WhiteChar470=null;
		Token WhiteChar471=null;
		Token string_literal472=null;
		Token WhiteChar473=null;
		Token char_literal474=null;
		Token char_literal475=null;
		Token char_literal476=null;
		Token WhiteChar477=null;
		Token string_literal478=null;
		Token WhiteChar479=null;
		Token char_literal480=null;
		Token char_literal481=null;
		Token char_literal482=null;
		Token WhiteChar483=null;
		Token string_literal484=null;
		Token WhiteChar485=null;
		Token char_literal486=null;
		Token char_literal487=null;
		Token char_literal488=null;
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
		Token WhiteChar506=null;
		Token DAYS507=null;
		Token WhiteChar508=null;
		Token string_literal509=null;
		Token WhiteChar510=null;
		Token WhiteChar511=null;
		Token string_literal512=null;
		Token WhiteChar513=null;
		Token WhiteChar514=null;
		Token string_literal515=null;
		Token WhiteChar516=null;
		Token char_literal517=null;
		Token char_literal518=null;
		Token char_literal519=null;
		Token WhiteChar520=null;
		Token string_literal521=null;
		Token WhiteChar522=null;
		Token char_literal523=null;
		Token char_literal524=null;
		Token char_literal525=null;
		Token WhiteChar526=null;
		Token string_literal527=null;
		Token WhiteChar528=null;
		Token char_literal529=null;
		Token char_literal530=null;
		Token char_literal531=null;
		Token string_literal532=null;
		Token WhiteChar533=null;
		Token WhiteChar534=null;
		Token DAYS535=null;
		Token WhiteChar536=null;
		Token string_literal537=null;
		Token WhiteChar538=null;
		Token WhiteChar539=null;
		Token DAYS540=null;
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
		Token char_literal554=null;
		Token char_literal555=null;
		Token char_literal556=null;
		Token WhiteChar557=null;
		Token string_literal558=null;
		Token WhiteChar559=null;
		Token string_literal560=null;
		Token WhiteChar561=null;
		Token WhiteChar562=null;
		Token DAYS563=null;
		Token WhiteChar564=null;
		Token string_literal565=null;
		Token WhiteChar566=null;
		Token WhiteChar567=null;
		Token DAYS568=null;
		Token WhiteChar569=null;
		Token string_literal570=null;
		Token WhiteChar571=null;
		Token WhiteChar572=null;
		Token DAYS573=null;
		Token WhiteChar574=null;
		Token string_literal575=null;
		Token WhiteChar576=null;
		Token WhiteChar577=null;
		Token DAYS578=null;
		Token WhiteChar579=null;
		Token string_literal580=null;
		Token WhiteChar581=null;
		Token char_literal582=null;
		Token char_literal583=null;
		Token char_literal584=null;
		Token WhiteChar585=null;
		Token string_literal586=null;
		Token WhiteChar587=null;
		Token string_literal588=null;
		Token WhiteChar589=null;
		Token string_literal590=null;
		Token WhiteChar591=null;
		Token WhiteChar592=null;
		Token DAYS593=null;
		Token WhiteChar594=null;
		Token string_literal595=null;
		Token WhiteChar596=null;
		Token WhiteChar597=null;
		Token DAYS598=null;
		Token WhiteChar599=null;
		Token string_literal600=null;
		Token WhiteChar601=null;
		Token string_literal602=null;
		Token WhiteChar603=null;
		Token string_literal604=null;
		Token WhiteChar605=null;
		Token WhiteChar606=null;
		Token DAYS607=null;
		Token WhiteChar608=null;
		Token string_literal609=null;
		Token WhiteChar610=null;
		Token WhiteChar611=null;
		Token DAYS612=null;
		Token WhiteChar613=null;
		Token string_literal614=null;
		Token WhiteChar615=null;
		Token string_literal616=null;
		Token WhiteChar617=null;
		Token string_literal618=null;
		Token WhiteChar619=null;
		Token WhiteChar620=null;
		Token DAYS621=null;
		Token WhiteChar622=null;
		Token string_literal623=null;
		Token WhiteChar624=null;
		Token WhiteChar625=null;
		Token DAYS626=null;
		Token WhiteChar627=null;
		Token string_literal628=null;
		Token WhiteChar629=null;
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

		CommonTree string_literal282_tree=null;
		CommonTree WhiteChar283_tree=null;
		CommonTree string_literal284_tree=null;
		CommonTree WhiteChar285_tree=null;
		CommonTree PERCENT286_tree=null;
		CommonTree WhiteChar287_tree=null;
		CommonTree string_literal288_tree=null;
		CommonTree WhiteChar289_tree=null;
		CommonTree WhiteChar290_tree=null;
		CommonTree DAYS291_tree=null;
		CommonTree string_literal292_tree=null;
		CommonTree WhiteChar293_tree=null;
		CommonTree string_literal294_tree=null;
		CommonTree WhiteChar295_tree=null;
		CommonTree PERCENT296_tree=null;
		CommonTree WhiteChar297_tree=null;
		CommonTree string_literal298_tree=null;
		CommonTree WhiteChar299_tree=null;
		CommonTree WhiteChar300_tree=null;
		CommonTree DAYS301_tree=null;
		CommonTree string_literal302_tree=null;
		CommonTree WhiteChar303_tree=null;
		CommonTree PERCENT304_tree=null;
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
		CommonTree string_literal315_tree=null;
		CommonTree WhiteChar316_tree=null;
		CommonTree PERCENT317_tree=null;
		CommonTree WhiteChar318_tree=null;
		CommonTree string_literal319_tree=null;
		CommonTree WhiteChar320_tree=null;
		CommonTree WhiteChar321_tree=null;
		CommonTree DAYS322_tree=null;
		CommonTree WhiteChar323_tree=null;
		CommonTree string_literal324_tree=null;
		CommonTree WhiteChar325_tree=null;
		CommonTree WhiteChar326_tree=null;
		CommonTree DAYS327_tree=null;
		CommonTree string_literal328_tree=null;
		CommonTree WhiteChar329_tree=null;
		CommonTree WhiteChar330_tree=null;
		CommonTree string_literal331_tree=null;
		CommonTree WhiteChar332_tree=null;
		CommonTree WhiteChar333_tree=null;
		CommonTree DAYS334_tree=null;
		CommonTree WhiteChar335_tree=null;
		CommonTree string_literal336_tree=null;
		CommonTree WhiteChar337_tree=null;
		CommonTree WhiteChar338_tree=null;
		CommonTree DAYS339_tree=null;
		CommonTree WhiteChar340_tree=null;
		CommonTree string_literal341_tree=null;
		CommonTree WhiteChar342_tree=null;
		CommonTree PERCENT343_tree=null;
		CommonTree string_literal344_tree=null;
		CommonTree WhiteChar345_tree=null;
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
		CommonTree PERCENT359_tree=null;
		CommonTree string_literal360_tree=null;
		CommonTree WhiteChar361_tree=null;
		CommonTree WhiteChar362_tree=null;
		CommonTree DAYS363_tree=null;
		CommonTree WhiteChar364_tree=null;
		CommonTree string_literal365_tree=null;
		CommonTree WhiteChar366_tree=null;
		CommonTree WhiteChar367_tree=null;
		CommonTree DAYS368_tree=null;
		CommonTree WhiteChar369_tree=null;
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
		CommonTree string_literal383_tree=null;
		CommonTree WhiteChar384_tree=null;
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
		CommonTree WhiteChar397_tree=null;
		CommonTree string_literal398_tree=null;
		CommonTree WhiteChar399_tree=null;
		CommonTree char_literal400_tree=null;
		CommonTree char_literal401_tree=null;
		CommonTree char_literal402_tree=null;
		CommonTree string_literal403_tree=null;
		CommonTree WhiteChar404_tree=null;
		CommonTree WhiteChar405_tree=null;
		CommonTree DAYS406_tree=null;
		CommonTree WhiteChar407_tree=null;
		CommonTree string_literal408_tree=null;
		CommonTree WhiteChar409_tree=null;
		CommonTree WhiteChar410_tree=null;
		CommonTree DAYS411_tree=null;
		CommonTree WhiteChar412_tree=null;
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
		CommonTree string_literal426_tree=null;
		CommonTree WhiteChar427_tree=null;
		CommonTree WhiteChar428_tree=null;
		CommonTree string_literal429_tree=null;
		CommonTree WhiteChar430_tree=null;
		CommonTree char_literal431_tree=null;
		CommonTree char_literal432_tree=null;
		CommonTree char_literal433_tree=null;
		CommonTree WhiteChar434_tree=null;
		CommonTree string_literal435_tree=null;
		CommonTree WhiteChar436_tree=null;
		CommonTree char_literal437_tree=null;
		CommonTree char_literal438_tree=null;
		CommonTree char_literal439_tree=null;
		CommonTree WhiteChar440_tree=null;
		CommonTree string_literal441_tree=null;
		CommonTree WhiteChar442_tree=null;
		CommonTree char_literal443_tree=null;
		CommonTree char_literal444_tree=null;
		CommonTree char_literal445_tree=null;
		CommonTree string_literal446_tree=null;
		CommonTree WhiteChar447_tree=null;
		CommonTree WhiteChar448_tree=null;
		CommonTree DAYS449_tree=null;
		CommonTree WhiteChar450_tree=null;
		CommonTree string_literal451_tree=null;
		CommonTree WhiteChar452_tree=null;
		CommonTree WhiteChar453_tree=null;
		CommonTree DAYS454_tree=null;
		CommonTree WhiteChar455_tree=null;
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
		CommonTree string_literal469_tree=null;
		CommonTree WhiteChar470_tree=null;
		CommonTree WhiteChar471_tree=null;
		CommonTree string_literal472_tree=null;
		CommonTree WhiteChar473_tree=null;
		CommonTree char_literal474_tree=null;
		CommonTree char_literal475_tree=null;
		CommonTree char_literal476_tree=null;
		CommonTree WhiteChar477_tree=null;
		CommonTree string_literal478_tree=null;
		CommonTree WhiteChar479_tree=null;
		CommonTree char_literal480_tree=null;
		CommonTree char_literal481_tree=null;
		CommonTree char_literal482_tree=null;
		CommonTree WhiteChar483_tree=null;
		CommonTree string_literal484_tree=null;
		CommonTree WhiteChar485_tree=null;
		CommonTree char_literal486_tree=null;
		CommonTree char_literal487_tree=null;
		CommonTree char_literal488_tree=null;
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
		CommonTree WhiteChar506_tree=null;
		CommonTree DAYS507_tree=null;
		CommonTree WhiteChar508_tree=null;
		CommonTree string_literal509_tree=null;
		CommonTree WhiteChar510_tree=null;
		CommonTree WhiteChar511_tree=null;
		CommonTree string_literal512_tree=null;
		CommonTree WhiteChar513_tree=null;
		CommonTree WhiteChar514_tree=null;
		CommonTree string_literal515_tree=null;
		CommonTree WhiteChar516_tree=null;
		CommonTree char_literal517_tree=null;
		CommonTree char_literal518_tree=null;
		CommonTree char_literal519_tree=null;
		CommonTree WhiteChar520_tree=null;
		CommonTree string_literal521_tree=null;
		CommonTree WhiteChar522_tree=null;
		CommonTree char_literal523_tree=null;
		CommonTree char_literal524_tree=null;
		CommonTree char_literal525_tree=null;
		CommonTree WhiteChar526_tree=null;
		CommonTree string_literal527_tree=null;
		CommonTree WhiteChar528_tree=null;
		CommonTree char_literal529_tree=null;
		CommonTree char_literal530_tree=null;
		CommonTree char_literal531_tree=null;
		CommonTree string_literal532_tree=null;
		CommonTree WhiteChar533_tree=null;
		CommonTree WhiteChar534_tree=null;
		CommonTree DAYS535_tree=null;
		CommonTree WhiteChar536_tree=null;
		CommonTree string_literal537_tree=null;
		CommonTree WhiteChar538_tree=null;
		CommonTree WhiteChar539_tree=null;
		CommonTree DAYS540_tree=null;
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
		CommonTree char_literal554_tree=null;
		CommonTree char_literal555_tree=null;
		CommonTree char_literal556_tree=null;
		CommonTree WhiteChar557_tree=null;
		CommonTree string_literal558_tree=null;
		CommonTree WhiteChar559_tree=null;
		CommonTree string_literal560_tree=null;
		CommonTree WhiteChar561_tree=null;
		CommonTree WhiteChar562_tree=null;
		CommonTree DAYS563_tree=null;
		CommonTree WhiteChar564_tree=null;
		CommonTree string_literal565_tree=null;
		CommonTree WhiteChar566_tree=null;
		CommonTree WhiteChar567_tree=null;
		CommonTree DAYS568_tree=null;
		CommonTree WhiteChar569_tree=null;
		CommonTree string_literal570_tree=null;
		CommonTree WhiteChar571_tree=null;
		CommonTree WhiteChar572_tree=null;
		CommonTree DAYS573_tree=null;
		CommonTree WhiteChar574_tree=null;
		CommonTree string_literal575_tree=null;
		CommonTree WhiteChar576_tree=null;
		CommonTree WhiteChar577_tree=null;
		CommonTree DAYS578_tree=null;
		CommonTree WhiteChar579_tree=null;
		CommonTree string_literal580_tree=null;
		CommonTree WhiteChar581_tree=null;
		CommonTree char_literal582_tree=null;
		CommonTree char_literal583_tree=null;
		CommonTree char_literal584_tree=null;
		CommonTree WhiteChar585_tree=null;
		CommonTree string_literal586_tree=null;
		CommonTree WhiteChar587_tree=null;
		CommonTree string_literal588_tree=null;
		CommonTree WhiteChar589_tree=null;
		CommonTree string_literal590_tree=null;
		CommonTree WhiteChar591_tree=null;
		CommonTree WhiteChar592_tree=null;
		CommonTree DAYS593_tree=null;
		CommonTree WhiteChar594_tree=null;
		CommonTree string_literal595_tree=null;
		CommonTree WhiteChar596_tree=null;
		CommonTree WhiteChar597_tree=null;
		CommonTree DAYS598_tree=null;
		CommonTree WhiteChar599_tree=null;
		CommonTree string_literal600_tree=null;
		CommonTree WhiteChar601_tree=null;
		CommonTree string_literal602_tree=null;
		CommonTree WhiteChar603_tree=null;
		CommonTree string_literal604_tree=null;
		CommonTree WhiteChar605_tree=null;
		CommonTree WhiteChar606_tree=null;
		CommonTree DAYS607_tree=null;
		CommonTree WhiteChar608_tree=null;
		CommonTree string_literal609_tree=null;
		CommonTree WhiteChar610_tree=null;
		CommonTree WhiteChar611_tree=null;
		CommonTree DAYS612_tree=null;
		CommonTree WhiteChar613_tree=null;
		CommonTree string_literal614_tree=null;
		CommonTree WhiteChar615_tree=null;
		CommonTree string_literal616_tree=null;
		CommonTree WhiteChar617_tree=null;
		CommonTree string_literal618_tree=null;
		CommonTree WhiteChar619_tree=null;
		CommonTree WhiteChar620_tree=null;
		CommonTree DAYS621_tree=null;
		CommonTree WhiteChar622_tree=null;
		CommonTree string_literal623_tree=null;
		CommonTree WhiteChar624_tree=null;
		CommonTree WhiteChar625_tree=null;
		CommonTree DAYS626_tree=null;
		CommonTree WhiteChar627_tree=null;
		CommonTree string_literal628_tree=null;
		CommonTree WhiteChar629_tree=null;
		RewriteRuleTokenStream stream_DAYS=new RewriteRuleTokenStream(adaptor,"token DAYS");
		RewriteRuleTokenStream stream_110=new RewriteRuleTokenStream(adaptor,"token 110");
		RewriteRuleTokenStream stream_111=new RewriteRuleTokenStream(adaptor,"token 111");
		RewriteRuleTokenStream stream_112=new RewriteRuleTokenStream(adaptor,"token 112");
		RewriteRuleTokenStream stream_115=new RewriteRuleTokenStream(adaptor,"token 115");
		RewriteRuleTokenStream stream_116=new RewriteRuleTokenStream(adaptor,"token 116");
		RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
		RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
		RewriteRuleTokenStream stream_94=new RewriteRuleTokenStream(adaptor,"token 94");
		RewriteRuleTokenStream stream_CLOSESQRT=new RewriteRuleTokenStream(adaptor,"token CLOSESQRT");
		RewriteRuleTokenStream stream_95=new RewriteRuleTokenStream(adaptor,"token 95");
		RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
		RewriteRuleTokenStream stream_96=new RewriteRuleTokenStream(adaptor,"token 96");
		RewriteRuleTokenStream stream_97=new RewriteRuleTokenStream(adaptor,"token 97");
		RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
		RewriteRuleTokenStream stream_98=new RewriteRuleTokenStream(adaptor,"token 98");
		RewriteRuleTokenStream stream_99=new RewriteRuleTokenStream(adaptor,"token 99");
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_100=new RewriteRuleTokenStream(adaptor,"token 100");
		RewriteRuleTokenStream stream_WhiteChar=new RewriteRuleTokenStream(adaptor,"token WhiteChar");
		RewriteRuleTokenStream stream_104=new RewriteRuleTokenStream(adaptor,"token 104");
		RewriteRuleTokenStream stream_OPENSQRT=new RewriteRuleTokenStream(adaptor,"token OPENSQRT");
		RewriteRuleTokenStream stream_PERCENT=new RewriteRuleTokenStream(adaptor,"token PERCENT");
		RewriteRuleTokenStream stream_105=new RewriteRuleTokenStream(adaptor,"token 105");
		RewriteRuleTokenStream stream_106=new RewriteRuleTokenStream(adaptor,"token 106");
		RewriteRuleTokenStream stream_107=new RewriteRuleTokenStream(adaptor,"token 107");
		RewriteRuleTokenStream stream_108=new RewriteRuleTokenStream(adaptor,"token 108");
		RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
		RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
		RewriteRuleTokenStream stream_109=new RewriteRuleTokenStream(adaptor,"token 109");
		RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
		RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
		RewriteRuleSubtreeStream stream_constant=new RewriteRuleSubtreeStream(adaptor,"rule constant");
		RewriteRuleSubtreeStream stream_stringconstant=new RewriteRuleSubtreeStream(adaptor,"rule stringconstant");

		try {
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:315:38: ( ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )? | ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )? | ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )? | ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) ) | ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) ) | ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) ) | ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) ) )
			int alt53=15;
			switch ( input.LA(1) ) {
			case 104:
				{
				alt53=1;
				}
				break;
			case 105:
				{
				alt53=2;
				}
				break;
			case 82:
				{
				alt53=3;
				}
				break;
			case 83:
				{
				alt53=4;
				}
				break;
			case 74:
				{
				alt53=5;
				}
				break;
			case 72:
				{
				alt53=6;
				}
				break;
			case 93:
				{
				alt53=7;
				}
				break;
			case 94:
				{
				alt53=8;
				}
				break;
			case 95:
				{
				alt53=9;
				}
				break;
			case 96:
				{
				alt53=10;
				}
				break;
			case 97:
				{
				alt53=11;
				}
				break;
			case 98:
				{
				alt53=12;
				}
				break;
			case 112:
				{
				alt53=13;
				}
				break;
			case 115:
				{
				alt53=14;
				}
				break;
			case 111:
				{
				alt53=15;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 53, 0, input);
				throw nvae;
			}
			switch (alt53) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:3: ( 'reverses down' -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:4: 'reverses down'
					{
					string_literal282=(Token)match(input,104,FOLLOW_104_in_presetcondition2755);  
					stream_104.add(string_literal282);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 317:20: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:23: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:42: ^( Number NumberToken[\"-1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "-1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:70: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:317:99: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:7: ( WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) ) )?
					int alt47=2;
					int LA47_0 = input.LA(1);
					if ( (LA47_0==WhiteChar) ) {
						int LA47_1 = input.LA(2);
						if ( (LA47_1==99) ) {
							alt47=1;
						}
					}
					switch (alt47) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:318:9: WhiteChar 'more than' WhiteChar percentdown= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar283=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2795);  
							stream_WhiteChar.add(WhiteChar283);

							string_literal284=(Token)match(input,99,FOLLOW_99_in_presetcondition2797);  
							stream_99.add(string_literal284);

							WhiteChar285=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2799);  
							stream_WhiteChar.add(WhiteChar285);

							pushFollow(FOLLOW_constant_in_presetcondition2803);
							percentdown=constant();
							state._fsp--;

							stream_constant.add(percentdown.getTree());
							PERCENT286=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2805);  
							stream_PERCENT.add(PERCENT286);

							WhiteChar287=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2807);  
							stream_WhiteChar.add(WhiteChar287);

							string_literal288=(Token)match(input,108,FOLLOW_108_in_presetcondition2809);  
							stream_108.add(string_literal288);

							WhiteChar289=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2811);  
							stream_WhiteChar.add(WhiteChar289);

							pushFollow(FOLLOW_constant_in_presetcondition2815);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar290=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2817);  
							stream_WhiteChar.add(WhiteChar290);

							DAYS291=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2819);  
							stream_DAYS.add(DAYS291);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 319:7: -> ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:319:10: ^( ReverseCondition ^( Number NumberToken[\"-1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:319:29: ^( Number NumberToken[\"-1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:320:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) ) ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:320:3: ( 'reverses up' -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:320:4: 'reverses up'
					{
					string_literal292=(Token)match(input,105,FOLLOW_105_in_presetcondition2855);  
					stream_105.add(string_literal292);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 320:18: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:320:21: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"1.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:320:40: ^( Number NumberToken[\"1\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:320:67: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:320:96: ^( Number NumberToken[\"1.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:321:7: ( WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) ) )?
					int alt48=2;
					int LA48_0 = input.LA(1);
					if ( (LA48_0==WhiteChar) ) {
						int LA48_1 = input.LA(2);
						if ( (LA48_1==99) ) {
							alt48=1;
						}
					}
					switch (alt48) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:321:9: WhiteChar 'more than' WhiteChar percentup= constant PERCENT WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS
							{
							WhiteChar293=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2895);  
							stream_WhiteChar.add(WhiteChar293);

							string_literal294=(Token)match(input,99,FOLLOW_99_in_presetcondition2897);  
							stream_99.add(string_literal294);

							WhiteChar295=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2899);  
							stream_WhiteChar.add(WhiteChar295);

							pushFollow(FOLLOW_constant_in_presetcondition2903);
							percentup=constant();
							state._fsp--;

							stream_constant.add(percentup.getTree());
							PERCENT296=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2905);  
							stream_PERCENT.add(PERCENT296);

							WhiteChar297=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2907);  
							stream_WhiteChar.add(WhiteChar297);

							string_literal298=(Token)match(input,108,FOLLOW_108_in_presetcondition2909);  
							stream_108.add(string_literal298);

							WhiteChar299=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2911);  
							stream_WhiteChar.add(WhiteChar299);

							pushFollow(FOLLOW_constant_in_presetcondition2915);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar300=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2917);  
							stream_WhiteChar.add(WhiteChar300);

							DAYS301=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition2919);  
							stream_DAYS.add(DAYS301);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 322:7: -> ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:10: ^( ReverseCondition ^( Number NumberToken[\"1\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ReverseCondition, "ReverseCondition"), root_1);
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:322:29: ^( Number NumberToken[\"1\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:3: ( 'goes down more than' WhiteChar percentdown= constant PERCENT -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:4: 'goes down more than' WhiteChar percentdown= constant PERCENT
					{
					string_literal302=(Token)match(input,82,FOLLOW_82_in_presetcondition2956);  
					stream_82.add(string_literal302);

					WhiteChar303=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition2958);  
					stream_WhiteChar.add(WhiteChar303);

					pushFollow(FOLLOW_constant_in_presetcondition2962);
					percentdown=constant();
					state._fsp--;

					stream_constant.add(percentdown.getTree());
					PERCENT304=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition2964);  
					stream_PERCENT.add(PERCENT304);

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
					// 324:65: -> ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:68: ^( DownRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:98: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:127: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:156: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:324:185: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:325:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt49=2;
					int LA49_0 = input.LA(1);
					if ( (LA49_0==WhiteChar) ) {
						int LA49_1 = input.LA(2);
						if ( (LA49_1==108) ) {
							alt49=1;
						}
					}
					switch (alt49) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:325:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar305=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3013);  
							stream_WhiteChar.add(WhiteChar305);

							string_literal306=(Token)match(input,108,FOLLOW_108_in_presetcondition3015);  
							stream_108.add(string_literal306);

							WhiteChar307=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3017);  
							stream_WhiteChar.add(WhiteChar307);

							pushFollow(FOLLOW_constant_in_presetcondition3021);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar308=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3023);  
							stream_WhiteChar.add(WhiteChar308);

							DAYS309=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3025);  
							stream_DAYS.add(DAYS309);

							WhiteChar310=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3036);  
							stream_WhiteChar.add(WhiteChar310);

							string_literal311=(Token)match(input,81,FOLLOW_81_in_presetcondition3038);  
							stream_81.add(string_literal311);

							WhiteChar312=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3040);  
							stream_WhiteChar.add(WhiteChar312);

							pushFollow(FOLLOW_constant_in_presetcondition3044);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar313=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3046);  
							stream_WhiteChar.add(WhiteChar313);

							DAYS314=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3048);  
							stream_DAYS.add(DAYS314);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 327:7: -> ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:10: ^( DownRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DownRatioCondition, "DownRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentdown!=null?((CommonTree)percentdown.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:74: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:327:121: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:328:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:328:3: ( 'goes up more than' WhiteChar percentup= constant PERCENT -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:328:4: 'goes up more than' WhiteChar percentup= constant PERCENT
					{
					string_literal315=(Token)match(input,83,FOLLOW_83_in_presetcondition3092);  
					stream_83.add(string_literal315);

					WhiteChar316=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3094);  
					stream_WhiteChar.add(WhiteChar316);

					pushFollow(FOLLOW_constant_in_presetcondition3098);
					percentup=constant();
					state._fsp--;

					stream_constant.add(percentup.getTree());
					PERCENT317=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition3100);  
					stream_PERCENT.add(PERCENT317);

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
					// 328:61: -> ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:328:64: ^( UpRatioCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:328:92: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:328:121: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:328:150: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:328:179: ^( Number NumberToken[\"0.0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:329:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt50=2;
					int LA50_0 = input.LA(1);
					if ( (LA50_0==WhiteChar) ) {
						int LA50_1 = input.LA(2);
						if ( (LA50_1==108) ) {
							alt50=1;
						}
					}
					switch (alt50) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:329:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS
							{
							WhiteChar318=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3149);  
							stream_WhiteChar.add(WhiteChar318);

							string_literal319=(Token)match(input,108,FOLLOW_108_in_presetcondition3151);  
							stream_108.add(string_literal319);

							WhiteChar320=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3153);  
							stream_WhiteChar.add(WhiteChar320);

							pushFollow(FOLLOW_constant_in_presetcondition3157);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar321=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3159);  
							stream_WhiteChar.add(WhiteChar321);

							DAYS322=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3161);  
							stream_DAYS.add(DAYS322);

							WhiteChar323=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3172);  
							stream_WhiteChar.add(WhiteChar323);

							string_literal324=(Token)match(input,81,FOLLOW_81_in_presetcondition3174);  
							stream_81.add(string_literal324);

							WhiteChar325=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3176);  
							stream_WhiteChar.add(WhiteChar325);

							pushFollow(FOLLOW_constant_in_presetcondition3180);
							forNbDays=constant();
							state._fsp--;

							stream_constant.add(forNbDays.getTree());
							WhiteChar326=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3182);  
							stream_WhiteChar.add(WhiteChar326);

							DAYS327=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3184);  
							stream_DAYS.add(DAYS327);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 331:7: -> ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:331:10: ^( UpRatioCondition ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UpRatioCondition, "UpRatioCondition"), root_1);
								adaptor.addChild(root_1, (percentup!=null?((CommonTree)percentup.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:331:70: ^( Number NumberToken[\"0.0\"] )
								{
								CommonTree root_2 = (CommonTree)adaptor.nil();
								root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
								adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
								adaptor.addChild(root_1, root_2);
								}

								adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:331:117: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:333:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:333:3: ( 'crosses up threshold' WhiteChar threshold= constant -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:333:4: 'crosses up threshold' WhiteChar threshold= constant
					{
					string_literal328=(Token)match(input,74,FOLLOW_74_in_presetcondition3229);  
					stream_74.add(string_literal328);

					WhiteChar329=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3231);  
					stream_WhiteChar.add(WhiteChar329);

					pushFollow(FOLLOW_constant_in_presetcondition3235);
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
					// 333:56: -> ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:333:59: ^( CrossUpConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:333:95: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:333:124: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:333:153: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:333:182: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:334:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt51=2;
					int LA51_0 = input.LA(1);
					if ( (LA51_0==WhiteChar) ) {
						int LA51_1 = input.LA(2);
						if ( (LA51_1==108) ) {
							alt51=1;
						}
					}
					switch (alt51) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:334:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar330=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3284);  
							stream_WhiteChar.add(WhiteChar330);

							string_literal331=(Token)match(input,108,FOLLOW_108_in_presetcondition3286);  
							stream_108.add(string_literal331);

							WhiteChar332=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3288);  
							stream_WhiteChar.add(WhiteChar332);

							pushFollow(FOLLOW_constant_in_presetcondition3292);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar333=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3294);  
							stream_WhiteChar.add(WhiteChar333);

							DAYS334=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3296);  
							stream_DAYS.add(DAYS334);

							WhiteChar335=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3307);  
							stream_WhiteChar.add(WhiteChar335);

							string_literal336=(Token)match(input,100,FOLLOW_100_in_presetcondition3309);  
							stream_100.add(string_literal336);

							WhiteChar337=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3311);  
							stream_WhiteChar.add(WhiteChar337);

							pushFollow(FOLLOW_constant_in_presetcondition3315);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar338=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3317);  
							stream_WhiteChar.add(WhiteChar338);

							DAYS339=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3319);  
							stream_DAYS.add(DAYS339);

							WhiteChar340=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3329);  
							stream_WhiteChar.add(WhiteChar340);

							string_literal341=(Token)match(input,77,FOLLOW_77_in_presetcondition3331);  
							stream_77.add(string_literal341);

							WhiteChar342=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3333);  
							stream_WhiteChar.add(WhiteChar342);

							pushFollow(FOLLOW_constant_in_presetcondition3337);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT343=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition3339);  
							stream_PERCENT.add(PERCENT343);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 337:7: -> ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:337:10: ^( CrossUpConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossUpConstantCondition, "CrossUpConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:337:97: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:338:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) ) ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:338:3: ( 'crosses down threshold' WhiteChar threshold= constant -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:338:4: 'crosses down threshold' WhiteChar threshold= constant
					{
					string_literal344=(Token)match(input,72,FOLLOW_72_in_presetcondition3378);  
					stream_72.add(string_literal344);

					WhiteChar345=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3380);  
					stream_WhiteChar.add(WhiteChar345);

					pushFollow(FOLLOW_constant_in_presetcondition3384);
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
					// 338:58: -> ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:338:61: ^( CrossDownConstantCondition constant ^( Number NumberToken[\"1.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0.0\"] ) ^( Number NumberToken[\"0\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
						adaptor.addChild(root_1, stream_constant.nextTree());
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:338:99: ^( Number NumberToken[\"1.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "1.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:338:128: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:338:157: ^( Number NumberToken[\"0.0\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "0.0"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:338:186: ^( Number NumberToken[\"0\"] )
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

					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:339:7: ( WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) ) )?
					int alt52=2;
					int LA52_0 = input.LA(1);
					if ( (LA52_0==WhiteChar) ) {
						int LA52_1 = input.LA(2);
						if ( (LA52_1==108) ) {
							alt52=1;
						}
					}
					switch (alt52) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:339:9: WhiteChar 'spanning' WhiteChar spanningNbDays= constant WhiteChar DAYS WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant PERCENT
							{
							WhiteChar346=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3433);  
							stream_WhiteChar.add(WhiteChar346);

							string_literal347=(Token)match(input,108,FOLLOW_108_in_presetcondition3435);  
							stream_108.add(string_literal347);

							WhiteChar348=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3437);  
							stream_WhiteChar.add(WhiteChar348);

							pushFollow(FOLLOW_constant_in_presetcondition3441);
							spanningNbDays=constant();
							state._fsp--;

							stream_constant.add(spanningNbDays.getTree());
							WhiteChar349=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3443);  
							stream_WhiteChar.add(WhiteChar349);

							DAYS350=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3445);  
							stream_DAYS.add(DAYS350);

							WhiteChar351=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3456);  
							stream_WhiteChar.add(WhiteChar351);

							string_literal352=(Token)match(input,100,FOLLOW_100_in_presetcondition3458);  
							stream_100.add(string_literal352);

							WhiteChar353=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3460);  
							stream_WhiteChar.add(WhiteChar353);

							pushFollow(FOLLOW_constant_in_presetcondition3464);
							overNbDays=constant();
							state._fsp--;

							stream_constant.add(overNbDays.getTree());
							WhiteChar354=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3466);  
							stream_WhiteChar.add(WhiteChar354);

							DAYS355=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3468);  
							stream_DAYS.add(DAYS355);

							WhiteChar356=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3478);  
							stream_WhiteChar.add(WhiteChar356);

							string_literal357=(Token)match(input,77,FOLLOW_77_in_presetcondition3480);  
							stream_77.add(string_literal357);

							WhiteChar358=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3482);  
							stream_WhiteChar.add(WhiteChar358);

							pushFollow(FOLLOW_constant_in_presetcondition3486);
							epsilon=constant();
							state._fsp--;

							stream_constant.add(epsilon.getTree());
							PERCENT359=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_presetcondition3488);  
							stream_PERCENT.add(PERCENT359);

							// AST REWRITE
							// elements: 
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 342:7: -> ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
							{
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:342:10: ^( CrossDownConstantCondition ^( Number NumberToken[\"0.0\"] ) )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CrossDownConstantCondition, "CrossDownConstantCondition"), root_1);
								adaptor.addChild(root_1, (threshold!=null?((CommonTree)threshold.getTree()):null));
								adaptor.addChild(root_1, (spanningNbDays!=null?((CommonTree)spanningNbDays.getTree()):null));
								adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
								// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:342:99: ^( Number NumberToken[\"0.0\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:344:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:344:3: ( 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:344:4: 'makes a higher high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal360=(Token)match(input,93,FOLLOW_93_in_presetcondition3528);  
					stream_93.add(string_literal360);

					WhiteChar361=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3530);  
					stream_WhiteChar.add(WhiteChar361);

					pushFollow(FOLLOW_constant_in_presetcondition3534);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar362=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3536);  
					stream_WhiteChar.add(WhiteChar362);

					DAYS363=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3538);  
					stream_DAYS.add(DAYS363);

					WhiteChar364=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3545);  
					stream_WhiteChar.add(WhiteChar364);

					string_literal365=(Token)match(input,100,FOLLOW_100_in_presetcondition3547);  
					stream_100.add(string_literal365);

					WhiteChar366=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3549);  
					stream_WhiteChar.add(WhiteChar366);

					pushFollow(FOLLOW_constant_in_presetcondition3553);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar367=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3555);  
					stream_WhiteChar.add(WhiteChar367);

					DAYS368=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3557);  
					stream_DAYS.add(DAYS368);

					WhiteChar369=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3564);  
					stream_WhiteChar.add(WhiteChar369);

					string_literal370=(Token)match(input,81,FOLLOW_81_in_presetcondition3566);  
					stream_81.add(string_literal370);

					WhiteChar371=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3568);  
					stream_WhiteChar.add(WhiteChar371);

					pushFollow(FOLLOW_constant_in_presetcondition3572);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar372=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3574);  
					stream_WhiteChar.add(WhiteChar372);

					DAYS373=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3576);  
					stream_DAYS.add(DAYS373);

					WhiteChar374=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3583);  
					stream_WhiteChar.add(WhiteChar374);

					string_literal375=(Token)match(input,107,FOLLOW_107_in_presetcondition3585);  
					stream_107.add(string_literal375);

					WhiteChar376=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3587);  
					stream_WhiteChar.add(WhiteChar376);

					pushFollow(FOLLOW_constant_in_presetcondition3591);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar377=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3593);  
					stream_WhiteChar.add(WhiteChar377);

					DAYS378=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3595);  
					stream_DAYS.add(DAYS378);

					WhiteChar379=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3602);  
					stream_WhiteChar.add(WhiteChar379);

					string_literal380=(Token)match(input,84,FOLLOW_84_in_presetcondition3604);  
					stream_84.add(string_literal380);

					WhiteChar381=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3606);  
					stream_WhiteChar.add(WhiteChar381);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3610);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar382=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3616);  
					stream_WhiteChar.add(WhiteChar382);

					string_literal383=(Token)match(input,116,FOLLOW_116_in_presetcondition3618);  
					stream_116.add(string_literal383);

					WhiteChar384=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3620);  
					stream_WhiteChar.add(WhiteChar384);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3624);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar385=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3630);  
					stream_WhiteChar.add(WhiteChar385);

					string_literal386=(Token)match(input,109,FOLLOW_109_in_presetcondition3632);  
					stream_109.add(string_literal386);

					WhiteChar387=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3634);  
					stream_WhiteChar.add(WhiteChar387);

					char_literal388=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3636);  
					stream_OPENSQRT.add(char_literal388);

					pushFollow(FOLLOW_constant_in_presetcondition3640);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal389=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3642);  
					stream_COMMA.add(char_literal389);

					pushFollow(FOLLOW_constant_in_presetcondition3646);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal390=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3648);  
					stream_CLOSESQRT.add(char_literal390);

					WhiteChar391=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3650);  
					stream_WhiteChar.add(WhiteChar391);

					string_literal392=(Token)match(input,76,FOLLOW_76_in_presetcondition3652);  
					stream_76.add(string_literal392);

					WhiteChar393=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3654);  
					stream_WhiteChar.add(WhiteChar393);

					char_literal394=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3656);  
					stream_OPENSQRT.add(char_literal394);

					pushFollow(FOLLOW_constant_in_presetcondition3660);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal395=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3662);  
					stream_COMMA.add(char_literal395);

					pushFollow(FOLLOW_constant_in_presetcondition3666);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal396=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3668);  
					stream_CLOSESQRT.add(char_literal396);

					WhiteChar397=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3674);  
					stream_WhiteChar.add(WhiteChar397);

					string_literal398=(Token)match(input,106,FOLLOW_106_in_presetcondition3676);  
					stream_106.add(string_literal398);

					WhiteChar399=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3678);  
					stream_WhiteChar.add(WhiteChar399);

					char_literal400=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3680);  
					stream_OPENSQRT.add(char_literal400);

					pushFollow(FOLLOW_constant_in_presetcondition3684);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal401=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3686);  
					stream_COMMA.add(char_literal401);

					pushFollow(FOLLOW_constant_in_presetcondition3690);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal402=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3692);  
					stream_CLOSESQRT.add(char_literal402);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 352:4: -> ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:352:7: ^( HigherHighCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:352:246: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:353:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:353:3: ( 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:353:4: 'makes a higher low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal403=(Token)match(input,94,FOLLOW_94_in_presetcondition3743);  
					stream_94.add(string_literal403);

					WhiteChar404=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3745);  
					stream_WhiteChar.add(WhiteChar404);

					pushFollow(FOLLOW_constant_in_presetcondition3749);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar405=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3751);  
					stream_WhiteChar.add(WhiteChar405);

					DAYS406=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3753);  
					stream_DAYS.add(DAYS406);

					WhiteChar407=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3760);  
					stream_WhiteChar.add(WhiteChar407);

					string_literal408=(Token)match(input,100,FOLLOW_100_in_presetcondition3762);  
					stream_100.add(string_literal408);

					WhiteChar409=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3764);  
					stream_WhiteChar.add(WhiteChar409);

					pushFollow(FOLLOW_constant_in_presetcondition3768);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar410=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3770);  
					stream_WhiteChar.add(WhiteChar410);

					DAYS411=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3772);  
					stream_DAYS.add(DAYS411);

					WhiteChar412=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3779);  
					stream_WhiteChar.add(WhiteChar412);

					string_literal413=(Token)match(input,81,FOLLOW_81_in_presetcondition3781);  
					stream_81.add(string_literal413);

					WhiteChar414=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3783);  
					stream_WhiteChar.add(WhiteChar414);

					pushFollow(FOLLOW_constant_in_presetcondition3787);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar415=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3789);  
					stream_WhiteChar.add(WhiteChar415);

					DAYS416=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3791);  
					stream_DAYS.add(DAYS416);

					WhiteChar417=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3798);  
					stream_WhiteChar.add(WhiteChar417);

					string_literal418=(Token)match(input,107,FOLLOW_107_in_presetcondition3800);  
					stream_107.add(string_literal418);

					WhiteChar419=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3802);  
					stream_WhiteChar.add(WhiteChar419);

					pushFollow(FOLLOW_constant_in_presetcondition3806);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar420=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3808);  
					stream_WhiteChar.add(WhiteChar420);

					DAYS421=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3810);  
					stream_DAYS.add(DAYS421);

					WhiteChar422=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3817);  
					stream_WhiteChar.add(WhiteChar422);

					string_literal423=(Token)match(input,84,FOLLOW_84_in_presetcondition3819);  
					stream_84.add(string_literal423);

					WhiteChar424=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3821);  
					stream_WhiteChar.add(WhiteChar424);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3825);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar425=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3831);  
					stream_WhiteChar.add(WhiteChar425);

					string_literal426=(Token)match(input,116,FOLLOW_116_in_presetcondition3833);  
					stream_116.add(string_literal426);

					WhiteChar427=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3835);  
					stream_WhiteChar.add(WhiteChar427);

					pushFollow(FOLLOW_stringconstant_in_presetcondition3839);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar428=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3845);  
					stream_WhiteChar.add(WhiteChar428);

					string_literal429=(Token)match(input,109,FOLLOW_109_in_presetcondition3847);  
					stream_109.add(string_literal429);

					WhiteChar430=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3849);  
					stream_WhiteChar.add(WhiteChar430);

					char_literal431=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3851);  
					stream_OPENSQRT.add(char_literal431);

					pushFollow(FOLLOW_constant_in_presetcondition3855);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal432=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3857);  
					stream_COMMA.add(char_literal432);

					pushFollow(FOLLOW_constant_in_presetcondition3861);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal433=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3863);  
					stream_CLOSESQRT.add(char_literal433);

					WhiteChar434=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3865);  
					stream_WhiteChar.add(WhiteChar434);

					string_literal435=(Token)match(input,76,FOLLOW_76_in_presetcondition3867);  
					stream_76.add(string_literal435);

					WhiteChar436=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3869);  
					stream_WhiteChar.add(WhiteChar436);

					char_literal437=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3871);  
					stream_OPENSQRT.add(char_literal437);

					pushFollow(FOLLOW_constant_in_presetcondition3875);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal438=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3877);  
					stream_COMMA.add(char_literal438);

					pushFollow(FOLLOW_constant_in_presetcondition3881);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal439=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3883);  
					stream_CLOSESQRT.add(char_literal439);

					WhiteChar440=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3889);  
					stream_WhiteChar.add(WhiteChar440);

					string_literal441=(Token)match(input,106,FOLLOW_106_in_presetcondition3891);  
					stream_106.add(string_literal441);

					WhiteChar442=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3893);  
					stream_WhiteChar.add(WhiteChar442);

					char_literal443=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition3895);  
					stream_OPENSQRT.add(char_literal443);

					pushFollow(FOLLOW_constant_in_presetcondition3899);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal444=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition3901);  
					stream_COMMA.add(char_literal444);

					pushFollow(FOLLOW_constant_in_presetcondition3905);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal445=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition3907);  
					stream_CLOSESQRT.add(char_literal445);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 361:4: -> ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:361:7: ^( HigherLowCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:361:245: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:362:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:362:3: ( 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:362:4: 'makes a lower high spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal446=(Token)match(input,95,FOLLOW_95_in_presetcondition3958);  
					stream_95.add(string_literal446);

					WhiteChar447=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3960);  
					stream_WhiteChar.add(WhiteChar447);

					pushFollow(FOLLOW_constant_in_presetcondition3964);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar448=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3966);  
					stream_WhiteChar.add(WhiteChar448);

					DAYS449=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3968);  
					stream_DAYS.add(DAYS449);

					WhiteChar450=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3975);  
					stream_WhiteChar.add(WhiteChar450);

					string_literal451=(Token)match(input,100,FOLLOW_100_in_presetcondition3977);  
					stream_100.add(string_literal451);

					WhiteChar452=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3979);  
					stream_WhiteChar.add(WhiteChar452);

					pushFollow(FOLLOW_constant_in_presetcondition3983);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar453=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3985);  
					stream_WhiteChar.add(WhiteChar453);

					DAYS454=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition3987);  
					stream_DAYS.add(DAYS454);

					WhiteChar455=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3994);  
					stream_WhiteChar.add(WhiteChar455);

					string_literal456=(Token)match(input,81,FOLLOW_81_in_presetcondition3996);  
					stream_81.add(string_literal456);

					WhiteChar457=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition3998);  
					stream_WhiteChar.add(WhiteChar457);

					pushFollow(FOLLOW_constant_in_presetcondition4002);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar458=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4004);  
					stream_WhiteChar.add(WhiteChar458);

					DAYS459=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4006);  
					stream_DAYS.add(DAYS459);

					WhiteChar460=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4013);  
					stream_WhiteChar.add(WhiteChar460);

					string_literal461=(Token)match(input,107,FOLLOW_107_in_presetcondition4015);  
					stream_107.add(string_literal461);

					WhiteChar462=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4017);  
					stream_WhiteChar.add(WhiteChar462);

					pushFollow(FOLLOW_constant_in_presetcondition4021);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar463=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4023);  
					stream_WhiteChar.add(WhiteChar463);

					DAYS464=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4025);  
					stream_DAYS.add(DAYS464);

					WhiteChar465=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4032);  
					stream_WhiteChar.add(WhiteChar465);

					string_literal466=(Token)match(input,84,FOLLOW_84_in_presetcondition4034);  
					stream_84.add(string_literal466);

					WhiteChar467=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4036);  
					stream_WhiteChar.add(WhiteChar467);

					pushFollow(FOLLOW_stringconstant_in_presetcondition4040);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar468=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4046);  
					stream_WhiteChar.add(WhiteChar468);

					string_literal469=(Token)match(input,116,FOLLOW_116_in_presetcondition4048);  
					stream_116.add(string_literal469);

					WhiteChar470=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4050);  
					stream_WhiteChar.add(WhiteChar470);

					pushFollow(FOLLOW_stringconstant_in_presetcondition4054);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar471=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4060);  
					stream_WhiteChar.add(WhiteChar471);

					string_literal472=(Token)match(input,109,FOLLOW_109_in_presetcondition4062);  
					stream_109.add(string_literal472);

					WhiteChar473=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4064);  
					stream_WhiteChar.add(WhiteChar473);

					char_literal474=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4066);  
					stream_OPENSQRT.add(char_literal474);

					pushFollow(FOLLOW_constant_in_presetcondition4070);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal475=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4072);  
					stream_COMMA.add(char_literal475);

					pushFollow(FOLLOW_constant_in_presetcondition4076);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal476=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4078);  
					stream_CLOSESQRT.add(char_literal476);

					WhiteChar477=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4080);  
					stream_WhiteChar.add(WhiteChar477);

					string_literal478=(Token)match(input,76,FOLLOW_76_in_presetcondition4082);  
					stream_76.add(string_literal478);

					WhiteChar479=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4084);  
					stream_WhiteChar.add(WhiteChar479);

					char_literal480=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4086);  
					stream_OPENSQRT.add(char_literal480);

					pushFollow(FOLLOW_constant_in_presetcondition4090);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal481=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4092);  
					stream_COMMA.add(char_literal481);

					pushFollow(FOLLOW_constant_in_presetcondition4096);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal482=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4098);  
					stream_CLOSESQRT.add(char_literal482);

					WhiteChar483=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4104);  
					stream_WhiteChar.add(WhiteChar483);

					string_literal484=(Token)match(input,106,FOLLOW_106_in_presetcondition4106);  
					stream_106.add(string_literal484);

					WhiteChar485=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4108);  
					stream_WhiteChar.add(WhiteChar485);

					char_literal486=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4110);  
					stream_OPENSQRT.add(char_literal486);

					pushFollow(FOLLOW_constant_in_presetcondition4114);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal487=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4116);  
					stream_COMMA.add(char_literal487);

					pushFollow(FOLLOW_constant_in_presetcondition4120);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal488=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4122);  
					stream_CLOSESQRT.add(char_literal488);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 370:4: -> ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:370:7: ^( LowerHighCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:370:245: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:371:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:371:3: ( 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']' -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:371:4: 'makes a lower low spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'greed' WhiteChar greed= stringconstant WhiteChar 'type' WhiteChar type= stringconstant WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'ending within' WhiteChar '[' lowestEnd= constant ',' highestEnd= constant ']' WhiteChar 'slope within' WhiteChar '[' minSlope= constant ',' maxSlope= constant ']'
					{
					string_literal489=(Token)match(input,96,FOLLOW_96_in_presetcondition4173);  
					stream_96.add(string_literal489);

					WhiteChar490=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4175);  
					stream_WhiteChar.add(WhiteChar490);

					pushFollow(FOLLOW_constant_in_presetcondition4179);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar491=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4181);  
					stream_WhiteChar.add(WhiteChar491);

					DAYS492=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4183);  
					stream_DAYS.add(DAYS492);

					WhiteChar493=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4190);  
					stream_WhiteChar.add(WhiteChar493);

					string_literal494=(Token)match(input,100,FOLLOW_100_in_presetcondition4192);  
					stream_100.add(string_literal494);

					WhiteChar495=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4194);  
					stream_WhiteChar.add(WhiteChar495);

					pushFollow(FOLLOW_constant_in_presetcondition4198);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar496=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4200);  
					stream_WhiteChar.add(WhiteChar496);

					DAYS497=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4202);  
					stream_DAYS.add(DAYS497);

					WhiteChar498=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4209);  
					stream_WhiteChar.add(WhiteChar498);

					string_literal499=(Token)match(input,81,FOLLOW_81_in_presetcondition4211);  
					stream_81.add(string_literal499);

					WhiteChar500=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4213);  
					stream_WhiteChar.add(WhiteChar500);

					pushFollow(FOLLOW_constant_in_presetcondition4217);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar501=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4219);  
					stream_WhiteChar.add(WhiteChar501);

					DAYS502=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4221);  
					stream_DAYS.add(DAYS502);

					WhiteChar503=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4228);  
					stream_WhiteChar.add(WhiteChar503);

					string_literal504=(Token)match(input,107,FOLLOW_107_in_presetcondition4230);  
					stream_107.add(string_literal504);

					WhiteChar505=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4232);  
					stream_WhiteChar.add(WhiteChar505);

					pushFollow(FOLLOW_constant_in_presetcondition4236);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar506=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4238);  
					stream_WhiteChar.add(WhiteChar506);

					DAYS507=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4240);  
					stream_DAYS.add(DAYS507);

					WhiteChar508=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4247);  
					stream_WhiteChar.add(WhiteChar508);

					string_literal509=(Token)match(input,84,FOLLOW_84_in_presetcondition4249);  
					stream_84.add(string_literal509);

					WhiteChar510=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4251);  
					stream_WhiteChar.add(WhiteChar510);

					pushFollow(FOLLOW_stringconstant_in_presetcondition4255);
					greed=stringconstant();
					state._fsp--;

					stream_stringconstant.add(greed.getTree());
					WhiteChar511=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4261);  
					stream_WhiteChar.add(WhiteChar511);

					string_literal512=(Token)match(input,116,FOLLOW_116_in_presetcondition4263);  
					stream_116.add(string_literal512);

					WhiteChar513=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4265);  
					stream_WhiteChar.add(WhiteChar513);

					pushFollow(FOLLOW_stringconstant_in_presetcondition4269);
					type=stringconstant();
					state._fsp--;

					stream_stringconstant.add(type.getTree());
					WhiteChar514=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4275);  
					stream_WhiteChar.add(WhiteChar514);

					string_literal515=(Token)match(input,109,FOLLOW_109_in_presetcondition4277);  
					stream_109.add(string_literal515);

					WhiteChar516=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4279);  
					stream_WhiteChar.add(WhiteChar516);

					char_literal517=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4281);  
					stream_OPENSQRT.add(char_literal517);

					pushFollow(FOLLOW_constant_in_presetcondition4285);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal518=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4287);  
					stream_COMMA.add(char_literal518);

					pushFollow(FOLLOW_constant_in_presetcondition4291);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal519=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4293);  
					stream_CLOSESQRT.add(char_literal519);

					WhiteChar520=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4295);  
					stream_WhiteChar.add(WhiteChar520);

					string_literal521=(Token)match(input,76,FOLLOW_76_in_presetcondition4297);  
					stream_76.add(string_literal521);

					WhiteChar522=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4299);  
					stream_WhiteChar.add(WhiteChar522);

					char_literal523=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4301);  
					stream_OPENSQRT.add(char_literal523);

					pushFollow(FOLLOW_constant_in_presetcondition4305);
					lowestEnd=constant();
					state._fsp--;

					stream_constant.add(lowestEnd.getTree());
					char_literal524=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4307);  
					stream_COMMA.add(char_literal524);

					pushFollow(FOLLOW_constant_in_presetcondition4311);
					highestEnd=constant();
					state._fsp--;

					stream_constant.add(highestEnd.getTree());
					char_literal525=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4313);  
					stream_CLOSESQRT.add(char_literal525);

					WhiteChar526=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4319);  
					stream_WhiteChar.add(WhiteChar526);

					string_literal527=(Token)match(input,106,FOLLOW_106_in_presetcondition4321);  
					stream_106.add(string_literal527);

					WhiteChar528=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4323);  
					stream_WhiteChar.add(WhiteChar528);

					char_literal529=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4325);  
					stream_OPENSQRT.add(char_literal529);

					pushFollow(FOLLOW_constant_in_presetcondition4329);
					minSlope=constant();
					state._fsp--;

					stream_constant.add(minSlope.getTree());
					char_literal530=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4331);  
					stream_COMMA.add(char_literal530);

					pushFollow(FOLLOW_constant_in_presetcondition4335);
					maxSlope=constant();
					state._fsp--;

					stream_constant.add(maxSlope.getTree());
					char_literal531=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4337);  
					stream_CLOSESQRT.add(char_literal531);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 379:4: -> ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:379:7: ^( LowerLowCondition ^( Number NumberToken[\"NaN\"] ) )
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
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:379:244: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:4: ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:4: ( 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:381:5: 'makes a support break down spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant
					{
					string_literal532=(Token)match(input,97,FOLLOW_97_in_presetcondition4390);  
					stream_97.add(string_literal532);

					WhiteChar533=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4392);  
					stream_WhiteChar.add(WhiteChar533);

					pushFollow(FOLLOW_constant_in_presetcondition4396);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar534=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4398);  
					stream_WhiteChar.add(WhiteChar534);

					DAYS535=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4400);  
					stream_DAYS.add(DAYS535);

					WhiteChar536=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4407);  
					stream_WhiteChar.add(WhiteChar536);

					string_literal537=(Token)match(input,100,FOLLOW_100_in_presetcondition4409);  
					stream_100.add(string_literal537);

					WhiteChar538=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4411);  
					stream_WhiteChar.add(WhiteChar538);

					pushFollow(FOLLOW_constant_in_presetcondition4415);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar539=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4417);  
					stream_WhiteChar.add(WhiteChar539);

					DAYS540=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4419);  
					stream_DAYS.add(DAYS540);

					WhiteChar541=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4426);  
					stream_WhiteChar.add(WhiteChar541);

					string_literal542=(Token)match(input,81,FOLLOW_81_in_presetcondition4428);  
					stream_81.add(string_literal542);

					WhiteChar543=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4430);  
					stream_WhiteChar.add(WhiteChar543);

					pushFollow(FOLLOW_constant_in_presetcondition4434);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar544=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4436);  
					stream_WhiteChar.add(WhiteChar544);

					DAYS545=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4438);  
					stream_DAYS.add(DAYS545);

					WhiteChar546=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4445);  
					stream_WhiteChar.add(WhiteChar546);

					string_literal547=(Token)match(input,107,FOLLOW_107_in_presetcondition4447);  
					stream_107.add(string_literal547);

					WhiteChar548=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4449);  
					stream_WhiteChar.add(WhiteChar548);

					pushFollow(FOLLOW_constant_in_presetcondition4453);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar549=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4455);  
					stream_WhiteChar.add(WhiteChar549);

					DAYS550=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4457);  
					stream_DAYS.add(DAYS550);

					WhiteChar551=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4464);  
					stream_WhiteChar.add(WhiteChar551);

					string_literal552=(Token)match(input,109,FOLLOW_109_in_presetcondition4466);  
					stream_109.add(string_literal552);

					WhiteChar553=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4468);  
					stream_WhiteChar.add(WhiteChar553);

					char_literal554=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4470);  
					stream_OPENSQRT.add(char_literal554);

					pushFollow(FOLLOW_constant_in_presetcondition4474);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal555=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4476);  
					stream_COMMA.add(char_literal555);

					pushFollow(FOLLOW_constant_in_presetcondition4480);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal556=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4482);  
					stream_CLOSESQRT.add(char_literal556);

					WhiteChar557=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4488);  
					stream_WhiteChar.add(WhiteChar557);

					string_literal558=(Token)match(input,110,FOLLOW_110_in_presetcondition4490);  
					stream_110.add(string_literal558);

					WhiteChar559=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4492);  
					stream_WhiteChar.add(WhiteChar559);

					pushFollow(FOLLOW_constant_in_presetcondition4496);
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
					// 387:4: -> ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:387:7: ^( SupportBreakDown ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupportBreakDown, "SupportBreakDown"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:387:104: ^( String StringToken[\"\\\"greedy\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"greedy\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:387:140: ^( String StringToken[\"\\\"smooth\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"smooth\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:387:217: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:387:246: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:387:275: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:387:304: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:388:4: ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:388:4: ( 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:388:5: 'makes a support break up spanning' WhiteChar lookBack= constant WhiteChar DAYS WhiteChar 'over' WhiteChar remanencePeriod= constant WhiteChar DAYS WhiteChar 'for' WhiteChar extremesSpan= constant WhiteChar DAYS WhiteChar 'smoothed' WhiteChar smoothP= constant WhiteChar DAYS WhiteChar 'starting within' WhiteChar '[' lowestStart= constant ',' highestStart= constant ']' WhiteChar 'tolerance' WhiteChar tolerance= constant
					{
					string_literal560=(Token)match(input,98,FOLLOW_98_in_presetcondition4573);  
					stream_98.add(string_literal560);

					WhiteChar561=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4575);  
					stream_WhiteChar.add(WhiteChar561);

					pushFollow(FOLLOW_constant_in_presetcondition4579);
					lookBack=constant();
					state._fsp--;

					stream_constant.add(lookBack.getTree());
					WhiteChar562=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4581);  
					stream_WhiteChar.add(WhiteChar562);

					DAYS563=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4583);  
					stream_DAYS.add(DAYS563);

					WhiteChar564=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4593);  
					stream_WhiteChar.add(WhiteChar564);

					string_literal565=(Token)match(input,100,FOLLOW_100_in_presetcondition4595);  
					stream_100.add(string_literal565);

					WhiteChar566=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4597);  
					stream_WhiteChar.add(WhiteChar566);

					pushFollow(FOLLOW_constant_in_presetcondition4601);
					remanencePeriod=constant();
					state._fsp--;

					stream_constant.add(remanencePeriod.getTree());
					WhiteChar567=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4603);  
					stream_WhiteChar.add(WhiteChar567);

					DAYS568=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4605);  
					stream_DAYS.add(DAYS568);

					WhiteChar569=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4615);  
					stream_WhiteChar.add(WhiteChar569);

					string_literal570=(Token)match(input,81,FOLLOW_81_in_presetcondition4617);  
					stream_81.add(string_literal570);

					WhiteChar571=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4619);  
					stream_WhiteChar.add(WhiteChar571);

					pushFollow(FOLLOW_constant_in_presetcondition4623);
					extremesSpan=constant();
					state._fsp--;

					stream_constant.add(extremesSpan.getTree());
					WhiteChar572=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4625);  
					stream_WhiteChar.add(WhiteChar572);

					DAYS573=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4627);  
					stream_DAYS.add(DAYS573);

					WhiteChar574=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4637);  
					stream_WhiteChar.add(WhiteChar574);

					string_literal575=(Token)match(input,107,FOLLOW_107_in_presetcondition4639);  
					stream_107.add(string_literal575);

					WhiteChar576=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4641);  
					stream_WhiteChar.add(WhiteChar576);

					pushFollow(FOLLOW_constant_in_presetcondition4645);
					smoothP=constant();
					state._fsp--;

					stream_constant.add(smoothP.getTree());
					WhiteChar577=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4647);  
					stream_WhiteChar.add(WhiteChar577);

					DAYS578=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4649);  
					stream_DAYS.add(DAYS578);

					WhiteChar579=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4659);  
					stream_WhiteChar.add(WhiteChar579);

					string_literal580=(Token)match(input,109,FOLLOW_109_in_presetcondition4661);  
					stream_109.add(string_literal580);

					WhiteChar581=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4663);  
					stream_WhiteChar.add(WhiteChar581);

					char_literal582=(Token)match(input,OPENSQRT,FOLLOW_OPENSQRT_in_presetcondition4665);  
					stream_OPENSQRT.add(char_literal582);

					pushFollow(FOLLOW_constant_in_presetcondition4669);
					lowestStart=constant();
					state._fsp--;

					stream_constant.add(lowestStart.getTree());
					char_literal583=(Token)match(input,COMMA,FOLLOW_COMMA_in_presetcondition4671);  
					stream_COMMA.add(char_literal583);

					pushFollow(FOLLOW_constant_in_presetcondition4675);
					highestStart=constant();
					state._fsp--;

					stream_constant.add(highestStart.getTree());
					char_literal584=(Token)match(input,CLOSESQRT,FOLLOW_CLOSESQRT_in_presetcondition4677);  
					stream_CLOSESQRT.add(char_literal584);

					WhiteChar585=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4687);  
					stream_WhiteChar.add(WhiteChar585);

					string_literal586=(Token)match(input,110,FOLLOW_110_in_presetcondition4689);  
					stream_110.add(string_literal586);

					WhiteChar587=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4691);  
					stream_WhiteChar.add(WhiteChar587);

					pushFollow(FOLLOW_constant_in_presetcondition4695);
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
					// 394:6: -> ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:394:9: ^( SupportBreakUp ^( String StringToken[\"\\\"greedy\\\"\"] ) ^( String StringToken[\"\\\"smooth\\\"\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) ^( Number NumberToken[\"NaN\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SupportBreakUp, "SupportBreakUp"), root_1);
						adaptor.addChild(root_1, (lookBack!=null?((CommonTree)lookBack.getTree()):null));
						adaptor.addChild(root_1, (remanencePeriod!=null?((CommonTree)remanencePeriod.getTree()):null));
						adaptor.addChild(root_1, (extremesSpan!=null?((CommonTree)extremesSpan.getTree()):null));
						adaptor.addChild(root_1, (smoothP!=null?((CommonTree)smoothP.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:394:104: ^( String StringToken[\"\\\"greedy\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"greedy\""));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:394:140: ^( String StringToken[\"\\\"smooth\\\"\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(String, "String"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(StringToken, "\"smooth\""));
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, (lowestStart!=null?((CommonTree)lowestStart.getTree()):null));
						adaptor.addChild(root_1, (highestStart!=null?((CommonTree)highestStart.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:394:217: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:394:246: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:394:275: ^( Number NumberToken[\"NaN\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(Number, "Number"), root_2);
						adaptor.addChild(root_2, (CommonTree)adaptor.create(NumberToken, "NaN"));
						adaptor.addChild(root_1, root_2);
						}

						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:394:304: ^( Number NumberToken[\"NaN\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:396:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:396:3: ( 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:396:4: 'trends flat' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal588=(Token)match(input,112,FOLLOW_112_in_presetcondition4774);  
					stream_112.add(string_literal588);

					WhiteChar589=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4782);  
					stream_WhiteChar.add(WhiteChar589);

					string_literal590=(Token)match(input,100,FOLLOW_100_in_presetcondition4784);  
					stream_100.add(string_literal590);

					WhiteChar591=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4786);  
					stream_WhiteChar.add(WhiteChar591);

					pushFollow(FOLLOW_constant_in_presetcondition4790);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar592=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4792);  
					stream_WhiteChar.add(WhiteChar592);

					DAYS593=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4794);  
					stream_DAYS.add(DAYS593);

					WhiteChar594=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4802);  
					stream_WhiteChar.add(WhiteChar594);

					string_literal595=(Token)match(input,81,FOLLOW_81_in_presetcondition4804);  
					stream_81.add(string_literal595);

					WhiteChar596=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4806);  
					stream_WhiteChar.add(WhiteChar596);

					pushFollow(FOLLOW_constant_in_presetcondition4810);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar597=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4812);  
					stream_WhiteChar.add(WhiteChar597);

					DAYS598=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4814);  
					stream_DAYS.add(DAYS598);

					WhiteChar599=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4822);  
					stream_WhiteChar.add(WhiteChar599);

					string_literal600=(Token)match(input,77,FOLLOW_77_in_presetcondition4824);  
					stream_77.add(string_literal600);

					WhiteChar601=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4826);  
					stream_WhiteChar.add(WhiteChar601);

					pushFollow(FOLLOW_constant_in_presetcondition4830);
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
					// 400:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:400:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"flat\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:400:79: ^( String StringToken[\"\\\"flat\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:401:3: ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:401:3: ( 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:401:4: 'trends up' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal602=(Token)match(input,115,FOLLOW_115_in_presetcondition4865);  
					stream_115.add(string_literal602);

					WhiteChar603=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4873);  
					stream_WhiteChar.add(WhiteChar603);

					string_literal604=(Token)match(input,100,FOLLOW_100_in_presetcondition4875);  
					stream_100.add(string_literal604);

					WhiteChar605=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4877);  
					stream_WhiteChar.add(WhiteChar605);

					pushFollow(FOLLOW_constant_in_presetcondition4881);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar606=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4883);  
					stream_WhiteChar.add(WhiteChar606);

					DAYS607=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4885);  
					stream_DAYS.add(DAYS607);

					WhiteChar608=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4893);  
					stream_WhiteChar.add(WhiteChar608);

					string_literal609=(Token)match(input,81,FOLLOW_81_in_presetcondition4895);  
					stream_81.add(string_literal609);

					WhiteChar610=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4897);  
					stream_WhiteChar.add(WhiteChar610);

					pushFollow(FOLLOW_constant_in_presetcondition4901);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar611=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4903);  
					stream_WhiteChar.add(WhiteChar611);

					DAYS612=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4905);  
					stream_DAYS.add(DAYS612);

					WhiteChar613=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4913);  
					stream_WhiteChar.add(WhiteChar613);

					string_literal614=(Token)match(input,77,FOLLOW_77_in_presetcondition4915);  
					stream_77.add(string_literal614);

					WhiteChar615=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4917);  
					stream_WhiteChar.add(WhiteChar615);

					pushFollow(FOLLOW_constant_in_presetcondition4921);
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
					// 405:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:405:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"up\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:405:79: ^( String StringToken[\"\\\"up\\\"\"] )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:406:3: ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) )
					{
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:406:3: ( 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) ) )
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:406:4: 'trends down' WhiteChar 'over' WhiteChar overNbDays= constant WhiteChar DAYS WhiteChar 'for' WhiteChar forNbDays= constant WhiteChar DAYS WhiteChar 'epsilon' WhiteChar epsilon= constant
					{
					string_literal616=(Token)match(input,111,FOLLOW_111_in_presetcondition4956);  
					stream_111.add(string_literal616);

					WhiteChar617=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4964);  
					stream_WhiteChar.add(WhiteChar617);

					string_literal618=(Token)match(input,100,FOLLOW_100_in_presetcondition4966);  
					stream_100.add(string_literal618);

					WhiteChar619=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4968);  
					stream_WhiteChar.add(WhiteChar619);

					pushFollow(FOLLOW_constant_in_presetcondition4972);
					overNbDays=constant();
					state._fsp--;

					stream_constant.add(overNbDays.getTree());
					WhiteChar620=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4974);  
					stream_WhiteChar.add(WhiteChar620);

					DAYS621=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4976);  
					stream_DAYS.add(DAYS621);

					WhiteChar622=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4984);  
					stream_WhiteChar.add(WhiteChar622);

					string_literal623=(Token)match(input,81,FOLLOW_81_in_presetcondition4986);  
					stream_81.add(string_literal623);

					WhiteChar624=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4988);  
					stream_WhiteChar.add(WhiteChar624);

					pushFollow(FOLLOW_constant_in_presetcondition4992);
					forNbDays=constant();
					state._fsp--;

					stream_constant.add(forNbDays.getTree());
					WhiteChar625=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition4994);  
					stream_WhiteChar.add(WhiteChar625);

					DAYS626=(Token)match(input,DAYS,FOLLOW_DAYS_in_presetcondition4996);  
					stream_DAYS.add(DAYS626);

					WhiteChar627=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition5004);  
					stream_WhiteChar.add(WhiteChar627);

					string_literal628=(Token)match(input,77,FOLLOW_77_in_presetcondition5006);  
					stream_77.add(string_literal628);

					WhiteChar629=(Token)match(input,WhiteChar,FOLLOW_WhiteChar_in_presetcondition5008);  
					stream_WhiteChar.add(WhiteChar629);

					pushFollow(FOLLOW_constant_in_presetcondition5012);
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
					// 410:7: -> ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) )
					{
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:410:10: ^( LinearDirectedTrendsCondition ^( String StringToken[\"\\\"down\\\"\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LinearDirectedTrendsCondition, "LinearDirectedTrendsCondition"), root_1);
						adaptor.addChild(root_1, (overNbDays!=null?((CommonTree)overNbDays.getTree()):null));
						adaptor.addChild(root_1, (forNbDays!=null?((CommonTree)forNbDays.getTree()):null));
						// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:410:79: ^( String StringToken[\"\\\"down\\\"\"] )
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



	public static final BitSet FOLLOW_with_precondition_in_complete_expression446 = new BitSet(new long[]{0x0000000000000000L,0x0000006000000010L});
	public static final BitSet FOLLOW_also_display_in_complete_expression448 = new BitSet(new long[]{0x0000000000000000L,0x0000006000000000L});
	public static final BitSet FOLLOW_fixed_start_shift_in_complete_expression450 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_na_event_list_name_in_complete_expression452 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_bullish_condition_in_with_precondition480 = new BitSet(new long[]{0x0000000000000000L,0x0000000001800000L});
	public static final BitSet FOLLOW_bearish_condition_in_with_precondition484 = new BitSet(new long[]{0x0000000000000002L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_with_precondition492 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_with_precondition494 = new BitSet(new long[]{0x0800090800800000L});
	public static final BitSet FOLLOW_primary_expression_in_with_precondition496 = new BitSet(new long[]{0x0002000000000000L,0x0000008000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_with_precondition500 = new BitSet(new long[]{0x0002000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_with_precondition503 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_with_precondition505 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_91_in_bullish_condition563 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition565 = new BitSet(new long[]{0x0800090800800000L});
	public static final BitSet FOLLOW_primary_expression_in_bullish_condition567 = new BitSet(new long[]{0x0002000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition569 = new BitSet(new long[]{0x0002000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bullish_condition572 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bullish_condition574 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_88_in_bearish_condition590 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition592 = new BitSet(new long[]{0x0800090800800000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_condition594 = new BitSet(new long[]{0x0002000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition596 = new BitSet(new long[]{0x0002000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bearish_condition599 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition601 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_bearish_not_bullish_in_bearish_condition611 = new BitSet(new long[]{0x0002000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition614 = new BitSet(new long[]{0x0002000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_bearish_condition617 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_condition619 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_68_in_also_display636 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display638 = new BitSet(new long[]{0x0800090800800000L});
	public static final BitSet FOLLOW_primary_expression_in_also_display640 = new BitSet(new long[]{0x0002000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_also_display642 = new BitSet(new long[]{0x0002000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_also_display645 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_102_in_fixed_start_shift680 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift682 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_fixed_start_shift686 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_fixed_start_shift688 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_fixed_start_shift690 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_fixed_start_shift692 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_101_in_na_event_list_name721 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_na_event_list_name723 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_na_event_list_name727 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_SEMICOLUMN_in_na_event_list_name729 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_87_in_bearish_not_bullish758 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish766 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_bearish_not_bullish768 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish770 = new BitSet(new long[]{0x0800090800800000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish772 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish805 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_OR_in_bearish_not_bullish807 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_bearish_not_bullish809 = new BitSet(new long[]{0x0800090800800000L});
	public static final BitSet FOLLOW_primary_expression_in_bearish_not_bullish811 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_and_expression_in_primary_expression873 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_expression_in_and_expression885 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_lenient_in_and_expression889 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression892 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_AND_in_and_expression894 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_and_expression896 = new BitSet(new long[]{0x0800090800800000L});
	public static final BitSet FOLLOW_or_expression_in_and_expression898 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_precondition_expression_in_or_expression925 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression928 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_OR_in_or_expression930 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_or_expression932 = new BitSet(new long[]{0x0800090800800000L});
	public static final BitSet FOLLOW_precondition_expression_in_or_expression934 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_matches_expression_in_precondition_expression966 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_precondition_expression969 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_WITH_in_precondition_expression971 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_precondition_expression973 = new BitSet(new long[]{0x0800090800800000L});
	public static final BitSet FOLLOW_matches_expression_in_precondition_expression975 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_atom_in_matches_expression1007 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression1010 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_MATCHING_in_matches_expression1012 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression1014 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_matches_expression1016 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_matches_expression1018 = new BitSet(new long[]{0x0000000000000300L});
	public static final BitSet FOLLOW_COMMA_in_matches_expression1021 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_matches_expression1023 = new BitSet(new long[]{0x0000000000000300L});
	public static final BitSet FOLLOW_CLOSESQRT_in_matches_expression1027 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_matches_expression1029 = new BitSet(new long[]{0x0800090800800000L});
	public static final BitSet FOLLOW_atom_in_matches_expression1031 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_booleanhistory_in_atom1059 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom1065 = new BitSet(new long[]{0x0800090800800000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_atom1067 = new BitSet(new long[]{0x0800090800800000L,0x0000000000000002L});
	public static final BitSet FOLLOW_primary_expression_in_atom1070 = new BitSet(new long[]{0x0000000000000080L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_atom1072 = new BitSet(new long[]{0x0000000000000080L,0x0000000000000002L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom1075 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_atom1085 = new BitSet(new long[]{0x0000010000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_atom1087 = new BitSet(new long[]{0x0000010000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPARENTEHSIS_in_atom1090 = new BitSet(new long[]{0x0800090800800000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_atom1092 = new BitSet(new long[]{0x0800090800800000L,0x0000000000000002L});
	public static final BitSet FOLLOW_primary_expression_in_atom1095 = new BitSet(new long[]{0x0000000000000080L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_atom1097 = new BitSet(new long[]{0x0000000000000080L,0x0000000000000002L});
	public static final BitSet FOLLOW_CLOSEPARENTEHSIS_in_atom1100 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_conjunctiontruthof_in_atom1121 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUTHOF_in_conjunctiontruthof1133 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof1135 = new BitSet(new long[]{0x0800090800800000L});
	public static final BitSet FOLLOW_primary_expression_in_conjunctiontruthof1137 = new BitSet(new long[]{0x0000000000000200L,0x0000000000000002L});
	public static final BitSet FOLLOW_COMMA_in_conjunctiontruthof1140 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof1142 = new BitSet(new long[]{0x0800090800800000L});
	public static final BitSet FOLLOW_primary_expression_in_conjunctiontruthof1144 = new BitSet(new long[]{0x0000000000000200L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof1148 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_conjunctiontruthof1150 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_conjunctiontruthof1152 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_conjunctiontruthof1154 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_conjunctiontruthof1158 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COMMA_in_conjunctiontruthof1160 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_conjunctiontruthof1164 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLOSESQRT_in_conjunctiontruthof1166 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_booleanhistory1205 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_booleanhistory1207 = new BitSet(new long[]{0x0000000000000000L,0x000F8307E66DC780L});
	public static final BitSet FOLLOW_presetcondition_in_booleanhistory1213 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_opcmpcondition_in_booleanhistory1224 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constantcmp_in_booleanhistory1235 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HistoricalData_in_operand1251 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Operation_in_operand1278 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NumberToken_in_constant1293 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_66_in_constant1305 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_StringToken_in_stringconstant1321 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_70_in_trendconstant1336 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_trendconstant1349 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_lenient1366 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_LENIENT_in_lenient1368 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_85_in_opcmpcondition1405 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1407 = new BitSet(new long[]{0x0000080000800000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1411 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1442 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_opcmpcondition1444 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1446 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1450 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1452 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1454 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1456 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_opcmpcondition1458 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1460 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1464 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1466 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_89_in_opcmpcondition1498 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1500 = new BitSet(new long[]{0x0000080000800000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1504 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1535 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_opcmpcondition1537 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1539 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1543 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1545 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1547 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1549 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_opcmpcondition1551 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1553 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1557 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1559 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_78_in_opcmpcondition1590 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1592 = new BitSet(new long[]{0x0000080000800000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1596 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1627 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_opcmpcondition1629 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1631 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1635 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1637 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1639 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1641 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_opcmpcondition1643 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1645 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1649 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1651 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_71_in_opcmpcondition1684 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1686 = new BitSet(new long[]{0x0000080000800000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1690 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1744 = new BitSet(new long[]{0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_108_in_opcmpcondition1746 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1748 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1752 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1754 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1756 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1758 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_opcmpcondition1760 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1762 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1766 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1768 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1770 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1772 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_opcmpcondition1774 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1776 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1780 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1782 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1822 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_67_in_opcmpcondition1824 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1826 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1830 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1832 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1834 = new BitSet(new long[]{0x0800090800800000L});
	public static final BitSet FOLLOW_atom_in_opcmpcondition1838 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_73_in_opcmpcondition1881 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1883 = new BitSet(new long[]{0x0000080000800000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition1887 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1941 = new BitSet(new long[]{0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_108_in_opcmpcondition1943 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1945 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1949 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1951 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1953 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1955 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_opcmpcondition1957 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1959 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1963 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1965 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition1967 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1969 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_opcmpcondition1971 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition1973 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition1977 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition1979 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2019 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_67_in_opcmpcondition2021 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2023 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2027 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_PERCENT_in_opcmpcondition2029 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2031 = new BitSet(new long[]{0x0800090800800000L});
	public static final BitSet FOLLOW_atom_in_opcmpcondition2035 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_113_in_opcmpcondition2080 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2082 = new BitSet(new long[]{0x0000080000800000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition2086 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2094 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_opcmpcondition2096 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2098 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2102 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2104 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition2106 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2114 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_opcmpcondition2116 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2118 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2122 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2124 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition2126 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2134 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition2136 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2138 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition2142 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2150 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_opcmpcondition2152 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2154 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2158 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_114_in_opcmpcondition2190 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2192 = new BitSet(new long[]{0x0000080000800000L});
	public static final BitSet FOLLOW_operand_in_opcmpcondition2196 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2204 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_opcmpcondition2206 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2208 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2212 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2214 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition2216 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2224 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_opcmpcondition2226 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2228 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_opcmpcondition2232 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2234 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_opcmpcondition2236 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2244 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_opcmpcondition2246 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_opcmpcondition2248 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_opcmpcondition2252 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_80_in_constantcmp2289 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2291 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_trendconstant_in_constantcmp2295 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2329 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_constantcmp2331 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2333 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_constantcmp2337 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2339 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2341 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2343 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_constantcmp2345 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2347 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_constantcmp2351 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2353 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2355 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_79_in_constantcmp2379 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2381 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_constantcmp2385 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2426 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_constantcmp2428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2430 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_constantcmp2434 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2436 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2438 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2444 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_constantcmp2446 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2448 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_constantcmp2452 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2454 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2456 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2462 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_constantcmp2464 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2466 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_constantcmp2470 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2472 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_86_in_constantcmp2502 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2504 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_constantcmp2508 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2549 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_constantcmp2551 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2553 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_constantcmp2557 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2559 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2561 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2567 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_constantcmp2569 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2571 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_constantcmp2575 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2577 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2579 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2585 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_constantcmp2587 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2589 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_constantcmp2593 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2595 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_90_in_constantcmp2625 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2627 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_constantcmp2631 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2672 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_constantcmp2674 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2676 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_constantcmp2680 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2682 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2684 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2690 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_constantcmp2692 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2694 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_constantcmp2698 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2700 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_constantcmp2702 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2708 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_constantcmp2710 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_constantcmp2712 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_constantcmp2716 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_PERCENT_in_constantcmp2718 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_104_in_presetcondition2755 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2795 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_99_in_presetcondition2797 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2799 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition2803 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2805 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2807 = new BitSet(new long[]{0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_108_in_presetcondition2809 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2811 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition2815 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2817 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2819 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_105_in_presetcondition2855 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2895 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_99_in_presetcondition2897 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2899 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition2903 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2905 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2907 = new BitSet(new long[]{0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_108_in_presetcondition2909 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2911 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition2915 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2917 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition2919 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_82_in_presetcondition2956 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition2958 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition2962 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition2964 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3013 = new BitSet(new long[]{0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_108_in_presetcondition3015 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3017 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3021 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3023 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3025 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3036 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_presetcondition3038 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3040 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3044 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3046 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3048 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_83_in_presetcondition3092 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3094 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3098 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition3100 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3149 = new BitSet(new long[]{0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_108_in_presetcondition3151 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3153 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3157 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3159 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3161 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3172 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_presetcondition3174 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3176 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3180 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3182 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3184 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_74_in_presetcondition3229 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3231 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3235 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3284 = new BitSet(new long[]{0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_108_in_presetcondition3286 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3288 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3292 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3294 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3296 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3307 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition3309 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3311 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3315 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3317 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3319 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3329 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition3331 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3333 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3337 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition3339 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_72_in_presetcondition3378 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3380 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3384 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3433 = new BitSet(new long[]{0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_108_in_presetcondition3435 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3437 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3441 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3443 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3445 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3456 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition3458 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3460 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3464 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3466 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3468 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3478 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition3480 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3482 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3486 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_PERCENT_in_presetcondition3488 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_93_in_presetcondition3528 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3530 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3534 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3536 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3538 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3545 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition3547 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3549 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3553 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3555 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3557 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3564 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_presetcondition3566 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3568 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3572 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3574 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3576 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3583 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_presetcondition3585 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3587 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3591 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3593 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3595 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3602 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_84_in_presetcondition3604 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3606 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3610 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3616 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_116_in_presetcondition3618 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3620 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3624 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3630 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_presetcondition3632 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3634 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3636 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3640 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3642 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3646 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3648 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3650 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_presetcondition3652 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3654 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3656 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3660 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3662 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3666 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3668 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3674 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition3676 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3678 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3680 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3684 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3686 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3690 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3692 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_94_in_presetcondition3743 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3745 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3749 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3751 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3753 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3760 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition3762 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3764 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3768 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3770 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3772 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3779 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_presetcondition3781 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3783 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3787 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3789 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3791 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3798 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_presetcondition3800 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3802 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3806 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3808 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3810 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3817 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_84_in_presetcondition3819 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3821 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3825 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3831 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_116_in_presetcondition3833 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3835 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition3839 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3845 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_presetcondition3847 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3849 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3851 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3855 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3857 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3861 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3863 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3865 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_presetcondition3867 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3869 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3871 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3875 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3877 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3881 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3883 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3889 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition3891 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3893 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition3895 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3899 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition3901 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3905 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition3907 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_95_in_presetcondition3958 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3960 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3964 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3966 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3968 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3975 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition3977 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3979 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition3983 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3985 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition3987 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3994 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_presetcondition3996 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition3998 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4002 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4004 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4006 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4013 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_presetcondition4015 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4017 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4021 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4023 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4025 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4032 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_84_in_presetcondition4034 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4036 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition4040 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4046 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_116_in_presetcondition4048 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4050 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition4054 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4060 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_presetcondition4062 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4064 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4066 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4070 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4072 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4076 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4078 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4080 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_presetcondition4082 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4084 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4086 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4090 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4092 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4096 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4098 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4104 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition4106 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4108 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4110 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4114 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4116 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4120 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4122 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_96_in_presetcondition4173 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4175 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4179 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4181 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4183 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4190 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition4192 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4194 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4198 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4200 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4202 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4209 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_presetcondition4211 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4213 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4217 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4219 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4221 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4228 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_presetcondition4230 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4232 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4236 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4238 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4240 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4247 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_84_in_presetcondition4249 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4251 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition4255 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4261 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_116_in_presetcondition4263 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4265 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_stringconstant_in_presetcondition4269 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4275 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_presetcondition4277 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4279 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4281 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4285 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4287 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4291 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4293 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4295 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_76_in_presetcondition4297 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4299 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4301 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4305 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4307 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4311 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4313 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4319 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_presetcondition4321 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4323 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4325 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4329 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4331 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4335 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4337 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_97_in_presetcondition4390 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4392 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4396 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4398 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4400 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4407 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition4409 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4411 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4415 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4417 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4419 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4426 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_presetcondition4428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4430 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4434 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4436 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4438 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4445 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_presetcondition4447 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4449 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4453 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4455 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4457 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4464 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_presetcondition4466 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4468 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4470 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4474 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4476 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4480 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4482 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4488 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_110_in_presetcondition4490 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4492 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4496 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_98_in_presetcondition4573 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4575 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4579 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4581 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4583 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4593 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition4595 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4597 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4601 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4603 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4605 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4615 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_presetcondition4617 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4619 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4623 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4625 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4627 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4637 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_presetcondition4639 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4641 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4645 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4647 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4649 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4659 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_presetcondition4661 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4663 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_OPENSQRT_in_presetcondition4665 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4669 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COMMA_in_presetcondition4671 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4675 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLOSESQRT_in_presetcondition4677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4687 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_110_in_presetcondition4689 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4691 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4695 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_112_in_presetcondition4774 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4782 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition4784 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4786 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4790 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4792 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4794 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4802 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_presetcondition4804 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4806 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4810 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4812 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4814 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4822 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition4824 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4826 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4830 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_115_in_presetcondition4865 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4873 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition4875 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4877 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4881 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4883 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4885 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4893 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_presetcondition4895 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4897 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4901 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4903 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4905 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4913 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition4915 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4917 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4921 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_111_in_presetcondition4956 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4964 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_presetcondition4966 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4968 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4972 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4974 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4976 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4984 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_presetcondition4986 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4988 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition4992 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition4994 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_DAYS_in_presetcondition4996 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition5004 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_presetcondition5006 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_WhiteChar_in_presetcondition5008 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_constant_in_presetcondition5012 = new BitSet(new long[]{0x0000000000000002L});
}
