// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2021-02-04 11:08:57
 //lexer
    package com.finance.pms.events.calculation.parametrizedindicators.antlr;
    import com.finance.pms.events.calculation.antlr.IErrorReporter;
    import com.finance.pms.events.calculation.antlr.MyErrorReporter;
    import com.finance.pms.events.calculation.antlr.IndsLexerDelegate;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class ParameterizedIndicatorsLexer extends Lexer {
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
	public static final int T__111=111;
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

	  private MyErrorReporter errorReporter;
	  private IndsLexerDelegate lexerDelegate;

	  public void setLexerDelegate(IndsLexerDelegate lexerDelegate) {
	      this.lexerDelegate = lexerDelegate;
	  }

	  public IndsLexerDelegate getLexerDelegate() {
	     return lexerDelegate;
	  }

	  public void setMyErrorReporter(MyErrorReporter errorReporter) {
	      this.errorReporter = errorReporter;
	  }

	  @Override
	  public void reportError(RecognitionException e) {
	      if (this.errorReporter != null) {
	        this.errorReporter.report(e);
	      } else {
	        super.reportError(e);
	      }
	  }

	  private boolean runtimeHistoryOpAhead() {
	    return lexerDelegate.runtimeHistoryOpAhead();
	  }
	    private boolean runtimeOpAhead() {
	    return lexerDelegate.runtimeOpAhead();
	  }
	    private boolean runtimeOutputSelectorAhead() {
	    return lexerDelegate.runtimeOutputSelectorAhead();
	  }


	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public ParameterizedIndicatorsLexer() {} 
	public ParameterizedIndicatorsLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public ParameterizedIndicatorsLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g"; }

	// $ANTLR start "AND"
	public final void mAND() throws RecognitionException {
		try {
			int _type = AND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:49:5: ( 'and' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:49:7: 'and'
			{
			match("and"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AND"

	// $ANTLR start "CLOSEPARENTEHSIS"
	public final void mCLOSEPARENTEHSIS() throws RecognitionException {
		try {
			int _type = CLOSEPARENTEHSIS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:50:18: ( ')' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:50:20: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CLOSEPARENTEHSIS"

	// $ANTLR start "CLOSESQRT"
	public final void mCLOSESQRT() throws RecognitionException {
		try {
			int _type = CLOSESQRT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:51:11: ( ']' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:51:13: ']'
			{
			match(']'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CLOSESQRT"

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:52:7: ( ',' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:52:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMA"

	// $ANTLR start "DAYS"
	public final void mDAYS() throws RecognitionException {
		try {
			int _type = DAYS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:53:6: ( 'days' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:53:8: 'days'
			{
			match("days"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DAYS"

	// $ANTLR start "LENIENT"
	public final void mLENIENT() throws RecognitionException {
		try {
			int _type = LENIENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:54:9: ( 'lenient' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:54:11: 'lenient'
			{
			match("lenient"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LENIENT"

	// $ANTLR start "MATCHING"
	public final void mMATCHING() throws RecognitionException {
		try {
			int _type = MATCHING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:55:10: ( 'matching' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:55:12: 'matching'
			{
			match("matching"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MATCHING"

	// $ANTLR start "NOT"
	public final void mNOT() throws RecognitionException {
		try {
			int _type = NOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:56:5: ( 'not' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:56:7: 'not'
			{
			match("not"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOT"

	// $ANTLR start "OPENPARENTEHSIS"
	public final void mOPENPARENTEHSIS() throws RecognitionException {
		try {
			int _type = OPENPARENTEHSIS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:57:17: ( '(' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:57:19: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OPENPARENTEHSIS"

	// $ANTLR start "OPENSQRT"
	public final void mOPENSQRT() throws RecognitionException {
		try {
			int _type = OPENSQRT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:58:10: ( '[' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:58:12: '['
			{
			match('['); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OPENSQRT"

	// $ANTLR start "OR"
	public final void mOR() throws RecognitionException {
		try {
			int _type = OR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:59:4: ( 'or' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:59:6: 'or'
			{
			match("or"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OR"

	// $ANTLR start "PERCENT"
	public final void mPERCENT() throws RecognitionException {
		try {
			int _type = PERCENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:60:9: ( '%' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:60:11: '%'
			{
			match('%'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PERCENT"

	// $ANTLR start "SEMICOLUMN"
	public final void mSEMICOLUMN() throws RecognitionException {
		try {
			int _type = SEMICOLUMN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:61:12: ( ';' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:61:14: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SEMICOLUMN"

	// $ANTLR start "TRUTHOF"
	public final void mTRUTHOF() throws RecognitionException {
		try {
			int _type = TRUTHOF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:62:9: ( 'truth of' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:62:11: 'truth of'
			{
			match("truth of"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRUTHOF"

	// $ANTLR start "T__62"
	public final void mT__62() throws RecognitionException {
		try {
			int _type = T__62;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:63:7: ( 'NaN' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:63:9: 'NaN'
			{
			match("NaN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__62"

	// $ANTLR start "T__63"
	public final void mT__63() throws RecognitionException {
		try {
			int _type = T__63;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:64:7: ( 'adaptiveRate' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:64:9: 'adaptiveRate'
			{
			match("adaptiveRate"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__63"

	// $ANTLR start "T__64"
	public final void mT__64() throws RecognitionException {
		try {
			int _type = T__64;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:65:7: ( 'also display' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:65:9: 'also display'
			{
			match("also display"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__64"

	// $ANTLR start "T__65"
	public final void mT__65() throws RecognitionException {
		try {
			int _type = T__65;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:66:7: ( 'bearish' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:66:9: 'bearish'
			{
			match("bearish"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__65"

	// $ANTLR start "T__66"
	public final void mT__66() throws RecognitionException {
		try {
			int _type = T__66;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:67:7: ( 'bullish' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:67:9: 'bullish'
			{
			match("bullish"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__66"

	// $ANTLR start "T__67"
	public final void mT__67() throws RecognitionException {
		try {
			int _type = T__67;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:68:7: ( 'crosses down historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:68:9: 'crosses down historical'
			{
			match("crosses down historical"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__67"

	// $ANTLR start "T__68"
	public final void mT__68() throws RecognitionException {
		try {
			int _type = T__68;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:69:7: ( 'crosses down threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:69:9: 'crosses down threshold'
			{
			match("crosses down threshold"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__68"

	// $ANTLR start "T__69"
	public final void mT__69() throws RecognitionException {
		try {
			int _type = T__69;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:70:7: ( 'crosses up historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:70:9: 'crosses up historical'
			{
			match("crosses up historical"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__69"

	// $ANTLR start "T__70"
	public final void mT__70() throws RecognitionException {
		try {
			int _type = T__70;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:71:7: ( 'crosses up threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:71:9: 'crosses up threshold'
			{
			match("crosses up threshold"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__70"

	// $ANTLR start "T__71"
	public final void mT__71() throws RecognitionException {
		try {
			int _type = T__71;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:72:7: ( 'direction' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:72:9: 'direction'
			{
			match("direction"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__71"

	// $ANTLR start "T__72"
	public final void mT__72() throws RecognitionException {
		try {
			int _type = T__72;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:73:7: ( 'ending within' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:73:9: 'ending within'
			{
			match("ending within"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__72"

	// $ANTLR start "T__73"
	public final void mT__73() throws RecognitionException {
		try {
			int _type = T__73;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:74:7: ( 'epsilon' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:74:9: 'epsilon'
			{
			match("epsilon"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__73"

	// $ANTLR start "T__74"
	public final void mT__74() throws RecognitionException {
		try {
			int _type = T__74;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:75:7: ( 'equals historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:75:9: 'equals historical'
			{
			match("equals historical"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__74"

	// $ANTLR start "T__75"
	public final void mT__75() throws RecognitionException {
		try {
			int _type = T__75;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:76:7: ( 'equals threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:76:9: 'equals threshold'
			{
			match("equals threshold"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__75"

	// $ANTLR start "T__76"
	public final void mT__76() throws RecognitionException {
		try {
			int _type = T__76;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:77:7: ( 'equals trend' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:77:9: 'equals trend'
			{
			match("equals trend"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__76"

	// $ANTLR start "T__77"
	public final void mT__77() throws RecognitionException {
		try {
			int _type = T__77;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:7: ( 'for' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:9: 'for'
			{
			match("for"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__77"

	// $ANTLR start "T__78"
	public final void mT__78() throws RecognitionException {
		try {
			int _type = T__78;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:79:7: ( 'goes down more than' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:79:9: 'goes down more than'
			{
			match("goes down more than"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__78"

	// $ANTLR start "T__79"
	public final void mT__79() throws RecognitionException {
		try {
			int _type = T__79;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:80:7: ( 'goes up more than' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:80:9: 'goes up more than'
			{
			match("goes up more than"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__79"

	// $ANTLR start "T__80"
	public final void mT__80() throws RecognitionException {
		try {
			int _type = T__80;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:81:7: ( 'greed' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:81:9: 'greed'
			{
			match("greed"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__80"

	// $ANTLR start "T__81"
	public final void mT__81() throws RecognitionException {
		try {
			int _type = T__81;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:82:7: ( 'is above historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:82:9: 'is above historical'
			{
			match("is above historical"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__81"

	// $ANTLR start "T__82"
	public final void mT__82() throws RecognitionException {
		try {
			int _type = T__82;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:83:7: ( 'is above threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:83:9: 'is above threshold'
			{
			match("is above threshold"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__82"

	// $ANTLR start "T__83"
	public final void mT__83() throws RecognitionException {
		try {
			int _type = T__83;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:84:7: ( 'is bearish if not bullish' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:84:9: 'is bearish if not bullish'
			{
			match("is bearish if not bullish"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__83"

	// $ANTLR start "T__84"
	public final void mT__84() throws RecognitionException {
		try {
			int _type = T__84;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:85:7: ( 'is bearish when' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:85:9: 'is bearish when'
			{
			match("is bearish when"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__84"

	// $ANTLR start "T__85"
	public final void mT__85() throws RecognitionException {
		try {
			int _type = T__85;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:86:7: ( 'is below historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:86:9: 'is below historical'
			{
			match("is below historical"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__85"

	// $ANTLR start "T__86"
	public final void mT__86() throws RecognitionException {
		try {
			int _type = T__86;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:87:7: ( 'is below threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:87:9: 'is below threshold'
			{
			match("is below threshold"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__86"

	// $ANTLR start "T__87"
	public final void mT__87() throws RecognitionException {
		try {
			int _type = T__87;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:88:7: ( 'is bullish when' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:88:9: 'is bullish when'
			{
			match("is bullish when"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__87"

	// $ANTLR start "T__88"
	public final void mT__88() throws RecognitionException {
		try {
			int _type = T__88;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:89:7: ( 'is within' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:89:9: 'is within'
			{
			match("is within"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__88"

	// $ANTLR start "T__89"
	public final void mT__89() throws RecognitionException {
		try {
			int _type = T__89;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:90:7: ( 'makes a higher high spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:90:9: 'makes a higher high spanning'
			{
			match("makes a higher high spanning"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__89"

	// $ANTLR start "T__90"
	public final void mT__90() throws RecognitionException {
		try {
			int _type = T__90;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:91:7: ( 'makes a higher low spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:91:9: 'makes a higher low spanning'
			{
			match("makes a higher low spanning"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__90"

	// $ANTLR start "T__91"
	public final void mT__91() throws RecognitionException {
		try {
			int _type = T__91;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:92:7: ( 'makes a lower high spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:92:9: 'makes a lower high spanning'
			{
			match("makes a lower high spanning"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__91"

	// $ANTLR start "T__92"
	public final void mT__92() throws RecognitionException {
		try {
			int _type = T__92;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:93:7: ( 'makes a lower low spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:93:9: 'makes a lower low spanning'
			{
			match("makes a lower low spanning"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__92"

	// $ANTLR start "T__93"
	public final void mT__93() throws RecognitionException {
		try {
			int _type = T__93;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:94:7: ( 'makes a support break down spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:94:9: 'makes a support break down spanning'
			{
			match("makes a support break down spanning"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__93"

	// $ANTLR start "T__94"
	public final void mT__94() throws RecognitionException {
		try {
			int _type = T__94;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:95:7: ( 'makes a support break up spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:95:9: 'makes a support break up spanning'
			{
			match("makes a support break up spanning"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__94"

	// $ANTLR start "T__95"
	public final void mT__95() throws RecognitionException {
		try {
			int _type = T__95;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:96:7: ( 'more than' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:96:9: 'more than'
			{
			match("more than"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__95"

	// $ANTLR start "T__96"
	public final void mT__96() throws RecognitionException {
		try {
			int _type = T__96;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:97:7: ( 'over' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:97:9: 'over'
			{
			match("over"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__96"

	// $ANTLR start "T__97"
	public final void mT__97() throws RecognitionException {
		try {
			int _type = T__97;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:98:7: ( 'override event list name with' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:98:9: 'override event list name with'
			{
			match("override event list name with"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__97"

	// $ANTLR start "T__98"
	public final void mT__98() throws RecognitionException {
		try {
			int _type = T__98;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:99:7: ( 'override start shift with' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:99:9: 'override start shift with'
			{
			match("override start shift with"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__98"

	// $ANTLR start "T__99"
	public final void mT__99() throws RecognitionException {
		try {
			int _type = T__99;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:100:7: ( 'reverses down' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:100:9: 'reverses down'
			{
			match("reverses down"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__99"

	// $ANTLR start "T__100"
	public final void mT__100() throws RecognitionException {
		try {
			int _type = T__100;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:101:8: ( 'reverses up' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:101:10: 'reverses up'
			{
			match("reverses up"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__100"

	// $ANTLR start "T__101"
	public final void mT__101() throws RecognitionException {
		try {
			int _type = T__101;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:102:8: ( 'slope within' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:102:10: 'slope within'
			{
			match("slope within"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__101"

	// $ANTLR start "T__102"
	public final void mT__102() throws RecognitionException {
		try {
			int _type = T__102;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:103:8: ( 'smoothed' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:103:10: 'smoothed'
			{
			match("smoothed"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__102"

	// $ANTLR start "T__103"
	public final void mT__103() throws RecognitionException {
		try {
			int _type = T__103;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:104:8: ( 'spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:104:10: 'spanning'
			{
			match("spanning"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__103"

	// $ANTLR start "T__104"
	public final void mT__104() throws RecognitionException {
		try {
			int _type = T__104;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:105:8: ( 'starting within' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:105:10: 'starting within'
			{
			match("starting within"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__104"

	// $ANTLR start "T__105"
	public final void mT__105() throws RecognitionException {
		try {
			int _type = T__105;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:106:8: ( 'tolerance' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:106:10: 'tolerance'
			{
			match("tolerance"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__105"

	// $ANTLR start "T__106"
	public final void mT__106() throws RecognitionException {
		try {
			int _type = T__106;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:107:8: ( 'trends down' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:107:10: 'trends down'
			{
			match("trends down"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__106"

	// $ANTLR start "T__107"
	public final void mT__107() throws RecognitionException {
		try {
			int _type = T__107;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:108:8: ( 'trends flat' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:108:10: 'trends flat'
			{
			match("trends flat"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__107"

	// $ANTLR start "T__108"
	public final void mT__108() throws RecognitionException {
		try {
			int _type = T__108;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:109:8: ( 'trends like' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:109:10: 'trends like'
			{
			match("trends like"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__108"

	// $ANTLR start "T__109"
	public final void mT__109() throws RecognitionException {
		try {
			int _type = T__109;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:110:8: ( 'trends unlike' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:110:10: 'trends unlike'
			{
			match("trends unlike"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__109"

	// $ANTLR start "T__110"
	public final void mT__110() throws RecognitionException {
		try {
			int _type = T__110;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:111:8: ( 'trends up' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:111:10: 'trends up'
			{
			match("trends up"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__110"

	// $ANTLR start "T__111"
	public final void mT__111() throws RecognitionException {
		try {
			int _type = T__111;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:112:8: ( 'type' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:112:10: 'type'
			{
			match("type"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__111"

	// $ANTLR start "Operation"
	public final void mOperation() throws RecognitionException {
		try {
			int _type = Operation;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:402:7: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:402:9: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )+
			{
			if ( !((runtimeOpAhead())) ) {
				throw new FailedPredicateException(input, "Operation", "runtimeOpAhead()");
			}
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:402:60: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '-' && LA1_0 <= '.')||(LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
					{
					if ( (input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Operation"

	// $ANTLR start "NumberToken"
	public final void mNumberToken() throws RecognitionException {
		try {
			int _type = NumberToken;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:406:7: ( ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:406:9: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:406:9: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:406:11: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:406:11: ( '-' )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0=='-') ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:406:12: '-'
					{
					match('-'); 
					}
					break;

			}

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:406:18: ( '0' .. '9' )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:406:30: ( '.' ( '0' .. '9' )+ )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0=='.') ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:406:31: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:406:35: ( '0' .. '9' )+
					int cnt4=0;
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt4 >= 1 ) break loop4;
							EarlyExitException eee = new EarlyExitException(4, input);
							throw eee;
						}
						cnt4++;
					}

					}
					break;

			}

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NumberToken"

	// $ANTLR start "StringToken"
	public final void mStringToken() throws RecognitionException {
		try {
			int _type = StringToken;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:410:6: ( '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' | '/' | ',' | '=' | ( '0' .. '9' ) | '?' | ':' | '-' | '>' )+ '\"' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:410:8: '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' | '/' | ',' | '=' | ( '0' .. '9' ) | '?' | ':' | '-' | '>' )+ '\"'
			{
			match('\"'); 
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:410:12: ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' | '/' | ',' | '=' | ( '0' .. '9' ) | '?' | ':' | '-' | '>' )+
			int cnt6=0;
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( ((LA6_0 >= ',' && LA6_0 <= ':')||(LA6_0 >= '=' && LA6_0 <= '?')||(LA6_0 >= 'A' && LA6_0 <= 'Z')||LA6_0=='_'||(LA6_0 >= 'a' && LA6_0 <= 'z')) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
					{
					if ( (input.LA(1) >= ',' && input.LA(1) <= ':')||(input.LA(1) >= '=' && input.LA(1) <= '?')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt6 >= 1 ) break loop6;
					EarlyExitException eee = new EarlyExitException(6, input);
					throw eee;
				}
				cnt6++;
			}

			match('\"'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "StringToken"

	// $ANTLR start "HistoricalData"
	public final void mHistoricalData() throws RecognitionException {
		try {
			int _type = HistoricalData;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:414:7: ({...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:414:9: {...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' )
			{
			if ( !((runtimeHistoryOpAhead())) ) {
				throw new FailedPredicateException(input, "HistoricalData", "runtimeHistoryOpAhead()");
			}
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:414:39: ( 'open' | 'close' | 'high' | 'low' | 'volume' )
			int alt7=5;
			switch ( input.LA(1) ) {
			case 'o':
				{
				alt7=1;
				}
				break;
			case 'c':
				{
				alt7=2;
				}
				break;
			case 'h':
				{
				alt7=3;
				}
				break;
			case 'l':
				{
				alt7=4;
				}
				break;
			case 'v':
				{
				alt7=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}
			switch (alt7) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:414:41: 'open'
					{
					match("open"); 

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:414:50: 'close'
					{
					match("close"); 

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:414:60: 'high'
					{
					match("high"); 

					}
					break;
				case 4 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:414:69: 'low'
					{
					match("low"); 

					}
					break;
				case 5 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:414:78: 'volume'
					{
					match("volume"); 

					}
					break;

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HistoricalData"

	// $ANTLR start "WhiteChar"
	public final void mWhiteChar() throws RecognitionException {
		try {
			int _type = WhiteChar;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:418:7: ( ( ( ' ' )+ ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:418:9: ( ( ' ' )+ )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:418:9: ( ( ' ' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:418:10: ( ' ' )+
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:418:10: ( ' ' )+
			int cnt8=0;
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0==' ') ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:418:10: ' '
					{
					match(' '); 
					}
					break;

				default :
					if ( cnt8 >= 1 ) break loop8;
					EarlyExitException eee = new EarlyExitException(8, input);
					throw eee;
				}
				cnt8++;
			}

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WhiteChar"

	// $ANTLR start "Tcheat"
	public final void mTcheat() throws RecognitionException {
		try {
			int _type = Tcheat;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:422:6: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:422:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:422:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+
			int cnt9=0;
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( ((LA9_0 >= '0' && LA9_0 <= '9')||(LA9_0 >= 'A' && LA9_0 <= 'Z')||(LA9_0 >= 'a' && LA9_0 <= 'z')) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt9 >= 1 ) break loop9;
					EarlyExitException eee = new EarlyExitException(9, input);
					throw eee;
				}
				cnt9++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Tcheat"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:427:5: ( ( '\\r' | '\\t' | '\\u000C' | '\\n' ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:427:7: ( '\\r' | '\\t' | '\\u000C' | '\\n' )
			{
			if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||(input.LA(1) >= '\f' && input.LA(1) <= '\r') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "COMMENT"
	public final void mCOMMENT() throws RecognitionException {
		try {
			int _type = COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:430:5: ( '/*' ( . )* '*/' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:430:7: '/*' ( . )* '*/'
			{
			match("/*"); 

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:430:12: ( . )*
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( (LA10_0=='*') ) {
					int LA10_1 = input.LA(2);
					if ( (LA10_1=='/') ) {
						alt10=2;
					}
					else if ( ((LA10_1 >= '\u0000' && LA10_1 <= '.')||(LA10_1 >= '0' && LA10_1 <= '\uFFFF')) ) {
						alt10=1;
					}

				}
				else if ( ((LA10_0 >= '\u0000' && LA10_0 <= ')')||(LA10_0 >= '+' && LA10_0 <= '\uFFFF')) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:430:12: .
					{
					matchAny(); 
					}
					break;

				default :
					break loop10;
				}
			}

			match("*/"); 

			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMENT"

	// $ANTLR start "LINE_COMMENT"
	public final void mLINE_COMMENT() throws RecognitionException {
		try {
			int _type = LINE_COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:433:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:433:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match("//"); 

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:433:12: (~ ( '\\n' | '\\r' ) )*
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( ((LA11_0 >= '\u0000' && LA11_0 <= '\t')||(LA11_0 >= '\u000B' && LA11_0 <= '\f')||(LA11_0 >= '\u000E' && LA11_0 <= '\uFFFF')) ) {
					alt11=1;
				}

				switch (alt11) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop11;
				}
			}

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:433:26: ( '\\r' )?
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0=='\r') ) {
				alt12=1;
			}
			switch (alt12) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:433:26: '\\r'
					{
					match('\r'); 
					}
					break;

			}

			match('\n'); 
			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LINE_COMMENT"

	@Override
	public void mTokens() throws RecognitionException {
		// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:8: ( AND | CLOSEPARENTEHSIS | CLOSESQRT | COMMA | DAYS | LENIENT | MATCHING | NOT | OPENPARENTEHSIS | OPENSQRT | OR | PERCENT | SEMICOLUMN | TRUTHOF | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | Operation | NumberToken | StringToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT )
		int alt13=73;
		alt13 = dfa13.predict(input);
		switch (alt13) {
			case 1 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:10: AND
				{
				mAND(); 

				}
				break;
			case 2 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:14: CLOSEPARENTEHSIS
				{
				mCLOSEPARENTEHSIS(); 

				}
				break;
			case 3 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:31: CLOSESQRT
				{
				mCLOSESQRT(); 

				}
				break;
			case 4 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:41: COMMA
				{
				mCOMMA(); 

				}
				break;
			case 5 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:47: DAYS
				{
				mDAYS(); 

				}
				break;
			case 6 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:52: LENIENT
				{
				mLENIENT(); 

				}
				break;
			case 7 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:60: MATCHING
				{
				mMATCHING(); 

				}
				break;
			case 8 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:69: NOT
				{
				mNOT(); 

				}
				break;
			case 9 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:73: OPENPARENTEHSIS
				{
				mOPENPARENTEHSIS(); 

				}
				break;
			case 10 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:89: OPENSQRT
				{
				mOPENSQRT(); 

				}
				break;
			case 11 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:98: OR
				{
				mOR(); 

				}
				break;
			case 12 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:101: PERCENT
				{
				mPERCENT(); 

				}
				break;
			case 13 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:109: SEMICOLUMN
				{
				mSEMICOLUMN(); 

				}
				break;
			case 14 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:120: TRUTHOF
				{
				mTRUTHOF(); 

				}
				break;
			case 15 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:128: T__62
				{
				mT__62(); 

				}
				break;
			case 16 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:134: T__63
				{
				mT__63(); 

				}
				break;
			case 17 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:140: T__64
				{
				mT__64(); 

				}
				break;
			case 18 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:146: T__65
				{
				mT__65(); 

				}
				break;
			case 19 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:152: T__66
				{
				mT__66(); 

				}
				break;
			case 20 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:158: T__67
				{
				mT__67(); 

				}
				break;
			case 21 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:164: T__68
				{
				mT__68(); 

				}
				break;
			case 22 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:170: T__69
				{
				mT__69(); 

				}
				break;
			case 23 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:176: T__70
				{
				mT__70(); 

				}
				break;
			case 24 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:182: T__71
				{
				mT__71(); 

				}
				break;
			case 25 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:188: T__72
				{
				mT__72(); 

				}
				break;
			case 26 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:194: T__73
				{
				mT__73(); 

				}
				break;
			case 27 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:200: T__74
				{
				mT__74(); 

				}
				break;
			case 28 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:206: T__75
				{
				mT__75(); 

				}
				break;
			case 29 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:212: T__76
				{
				mT__76(); 

				}
				break;
			case 30 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:218: T__77
				{
				mT__77(); 

				}
				break;
			case 31 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:224: T__78
				{
				mT__78(); 

				}
				break;
			case 32 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:230: T__79
				{
				mT__79(); 

				}
				break;
			case 33 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:236: T__80
				{
				mT__80(); 

				}
				break;
			case 34 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:242: T__81
				{
				mT__81(); 

				}
				break;
			case 35 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:248: T__82
				{
				mT__82(); 

				}
				break;
			case 36 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:254: T__83
				{
				mT__83(); 

				}
				break;
			case 37 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:260: T__84
				{
				mT__84(); 

				}
				break;
			case 38 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:266: T__85
				{
				mT__85(); 

				}
				break;
			case 39 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:272: T__86
				{
				mT__86(); 

				}
				break;
			case 40 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:278: T__87
				{
				mT__87(); 

				}
				break;
			case 41 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:284: T__88
				{
				mT__88(); 

				}
				break;
			case 42 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:290: T__89
				{
				mT__89(); 

				}
				break;
			case 43 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:296: T__90
				{
				mT__90(); 

				}
				break;
			case 44 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:302: T__91
				{
				mT__91(); 

				}
				break;
			case 45 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:308: T__92
				{
				mT__92(); 

				}
				break;
			case 46 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:314: T__93
				{
				mT__93(); 

				}
				break;
			case 47 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:320: T__94
				{
				mT__94(); 

				}
				break;
			case 48 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:326: T__95
				{
				mT__95(); 

				}
				break;
			case 49 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:332: T__96
				{
				mT__96(); 

				}
				break;
			case 50 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:338: T__97
				{
				mT__97(); 

				}
				break;
			case 51 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:344: T__98
				{
				mT__98(); 

				}
				break;
			case 52 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:350: T__99
				{
				mT__99(); 

				}
				break;
			case 53 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:356: T__100
				{
				mT__100(); 

				}
				break;
			case 54 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:363: T__101
				{
				mT__101(); 

				}
				break;
			case 55 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:370: T__102
				{
				mT__102(); 

				}
				break;
			case 56 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:377: T__103
				{
				mT__103(); 

				}
				break;
			case 57 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:384: T__104
				{
				mT__104(); 

				}
				break;
			case 58 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:391: T__105
				{
				mT__105(); 

				}
				break;
			case 59 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:398: T__106
				{
				mT__106(); 

				}
				break;
			case 60 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:405: T__107
				{
				mT__107(); 

				}
				break;
			case 61 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:412: T__108
				{
				mT__108(); 

				}
				break;
			case 62 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:419: T__109
				{
				mT__109(); 

				}
				break;
			case 63 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:426: T__110
				{
				mT__110(); 

				}
				break;
			case 64 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:433: T__111
				{
				mT__111(); 

				}
				break;
			case 65 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:440: Operation
				{
				mOperation(); 

				}
				break;
			case 66 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:450: NumberToken
				{
				mNumberToken(); 

				}
				break;
			case 67 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:462: StringToken
				{
				mStringToken(); 

				}
				break;
			case 68 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:474: HistoricalData
				{
				mHistoricalData(); 

				}
				break;
			case 69 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:489: WhiteChar
				{
				mWhiteChar(); 

				}
				break;
			case 70 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:499: Tcheat
				{
				mTcheat(); 

				}
				break;
			case 71 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:506: WS
				{
				mWS(); 

				}
				break;
			case 72 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:509: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 73 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:517: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;

		}
	}


	protected DFA13 dfa13 = new DFA13(this);
	static final String DFA13_eotS =
		"\1\uffff\1\46\3\uffff\4\46\2\uffff\1\46\2\uffff\13\46\1\uffff\1\31\1\uffff"+
		"\2\46\4\uffff\1\112\1\114\1\116\1\117\1\uffff\1\121\1\123\1\125\1\127"+
		"\1\132\1\134\1\136\1\137\1\141\1\143\1\146\1\150\1\152\1\154\1\156\1\160"+
		"\1\162\1\164\1\166\1\170\1\172\1\174\1\176\1\u0080\1\u0082\1\u0084\1\u0086"+
		"\1\u0088\1\u008a\1\u008c\1\u008e\1\u0090\2\uffff\1\u0091\1\uffff\1\u0093"+
		"\1\uffff\1\u0095\2\uffff\1\u0097\1\uffff\1\u0099\1\uffff\1\u009b\1\uffff"+
		"\1\u009c\1\uffff\1\u009e\1\u00a0\1\uffff\1\u00a2\1\uffff\1\u00a3\2\uffff"+
		"\1\u00a5\1\uffff\1\u00a7\1\uffff\1\u00a9\1\u00ab\1\uffff\1\u00ad\1\uffff"+
		"\1\u00af\1\uffff\1\u00b0\1\uffff\1\u00b2\1\uffff\1\u00b4\1\uffff\1\u00b6"+
		"\1\uffff\1\u00b8\1\uffff\1\u00ba\1\uffff\1\u00bc\1\uffff\1\u00be\1\uffff"+
		"\1\u00bf\1\uffff\1\u00c1\1\uffff\1\u00c3\3\uffff\1\u00c8\1\uffff\1\u00ca"+
		"\1\uffff\1\u00cc\1\uffff\1\u00ce\1\uffff\1\u00d0\1\uffff\1\u00d2\1\uffff"+
		"\1\u00d4\2\uffff\1\u00d6\1\uffff\1\u00d8\1\uffff\1\u00d9\1\uffff\1\u00db"+
		"\1\uffff\1\u00dd\2\uffff\1\u00e0\1\uffff\1\u00e2\1\uffff\1\u00e4\2\uffff"+
		"\1\u00e6\1\uffff\1\u00e7\1\uffff\1\u00e9\1\uffff\1\u00eb\1\uffff\1\u00ed"+
		"\1\uffff\1\u00ee\2\uffff\1\u00f0\1\uffff\1\u00f2\1\uffff\1\u00f4\1\uffff"+
		"\1\u00f6\1\uffff\1\u00f8\1\uffff\1\u00fa\1\uffff\1\u00fc\2\uffff\1\u00fe"+
		"\1\uffff\1\u0100\4\uffff\1\u0105\1\uffff\1\u0107\1\uffff\1\u0109\1\uffff"+
		"\1\u010b\1\uffff\1\u010d\1\uffff\1\u010e\1\uffff\1\u0110\1\uffff\1\u0112"+
		"\4\uffff\1\u0114\1\uffff\1\u0116\2\uffff\1\u0118\1\uffff\1\u011a\3\uffff"+
		"\1\u011c\2\uffff\1\u011e\1\uffff\1\u0120\1\uffff\1\u0122\2\uffff\1\u0124"+
		"\1\uffff\1\u0126\1\uffff\1\u0128\1\uffff\1\u0129\1\uffff\1\u012b\1\uffff"+
		"\1\u012d\1\uffff\1\u012f\3\uffff\1\u0132\4\uffff\1\u0137\1\uffff\1\u0139"+
		"\1\uffff\1\u013b\1\uffff\1\u013d\1\uffff\1\u013f\2\uffff\1\u0141\1\uffff"+
		"\1\u0143\1\uffff\1\u0145\1\uffff\1\u0147\1\uffff\1\u0149\3\uffff\1\u014c"+
		"\3\uffff\1\u014e\1\uffff\1\u0150\1\uffff\1\u0152\1\uffff\1\u0154\1\uffff"+
		"\1\u0156\2\uffff\1\u0158\1\uffff\1\u015a\1\uffff\1\u015c\7\uffff\1\u0161"+
		"\3\uffff\1\u0163\1\uffff\1\u0165\1\uffff\1\u0167\1\uffff\1\u0168\1\uffff"+
		"\1\u016a\1\uffff\1\u016c\1\uffff\1\u016d\1\uffff\1\u016f\2\uffff\1\u0172"+
		"\3\uffff\1\u0178\1\uffff\1\u0179\1\uffff\1\u017a\1\uffff\1\u017c\3\uffff"+
		"\1\u017d\6\uffff\1\u0184\1\uffff\1\u0186\1\uffff\1\u0188\1\uffff\1\u018a"+
		"\2\uffff\1\u018c\1\uffff\1\u018e\2\uffff\1\u018f\2\uffff\1\u0194\5\uffff"+
		"\1\u0198\13\uffff\1\u01a1\1\uffff\1\u01a2\1\uffff\1\u01a3\1\uffff\1\u01a5"+
		"\1\uffff\1\u01a7\1\uffff\1\u01a8\11\uffff\1\u01ae\16\uffff\1\u01b9\21"+
		"\uffff\1\u01c1\7\uffff\1\u01ca\42\uffff";
	static final String DFA13_eofS =
		"\u01e3\uffff";
	static final String DFA13_minS =
		"\1\11\1\55\3\uffff\4\55\2\uffff\1\55\2\uffff\13\55\1\uffff\1\60\1\uffff"+
		"\2\55\3\uffff\1\52\4\55\1\uffff\30\55\1\40\7\55\2\uffff\1\55\1\0\1\55"+
		"\1\0\1\55\2\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\2\55\1\0\1\55\1\0\1"+
		"\55\1\0\1\uffff\1\55\1\0\1\55\1\0\2\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0"+
		"\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1"+
		"\0\1\55\1\0\1\55\1\0\1\141\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55"+
		"\1\0\1\55\1\0\1\55\1\0\1\uffff\1\55\1\0\1\40\1\0\1\55\1\0\1\55\1\0\1\55"+
		"\2\0\1\55\1\0\1\55\1\0\1\40\1\0\1\uffff\1\55\1\0\1\55\1\0\1\55\1\0\1\55"+
		"\1\0\1\55\1\0\1\55\1\0\1\uffff\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55"+
		"\1\0\1\55\1\0\1\55\1\0\1\uffff\1\40\1\0\1\55\1\0\1\142\1\145\1\uffff\1"+
		"\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0"+
		"\1\uffff\1\0\1\uffff\1\55\1\0\1\55\1\0\1\uffff\1\55\1\0\1\40\1\0\1\uffff"+
		"\1\0\1\55\1\uffff\1\0\1\40\1\0\1\55\1\0\1\55\1\0\1\uffff\1\55\1\0\1\55"+
		"\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\144\1\0\1\55\1\0\1"+
		"\157\1\141\1\uffff\1\55\1\0\1\40\1\0\1\55\1\0\1\55\1\0\1\55\2\0\1\55\1"+
		"\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\141\1\0\1\55\1\0\1\uffff\1\0"+
		"\1\40\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\2\0\1\40\1\0\1\55\1\0\1\40\1"+
		"\0\3\uffff\1\166\1\162\1\157\1\55\1\0\1\uffff\1\0\1\55\1\0\1\55\1\0\1"+
		"\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\40\1\55\1\0\1\144"+
		"\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\40\1\0\1\uffff\1\0\1\55\1\0\1\150\1"+
		"\0\1\145\1\151\1\167\1\55\1\0\1\55\1\0\1\55\1\0\1\55\2\0\1\55\1\0\1\55"+
		"\1\0\1\uffff\1\55\1\0\1\150\1\40\1\0\3\uffff\1\156\1\55\1\0\2\uffff\1"+
		"\144\1\0\2\uffff\1\150\1\40\1\163\2\40\1\0\1\55\1\0\1\55\1\0\1\40\1\0"+
		"\1\55\1\0\1\55\1\0\1\uffff\1\151\1\157\1\165\1\145\1\0\2\uffff\1\55\1"+
		"\0\1\157\1\160\2\uffff\3\150\1\144\1\0\3\uffff\1\0\1\55\1\0\1\uffff\1"+
		"\147\1\167\1\160\3\uffff\1\167\1\40\2\uffff\1\40\4\uffff\1\55\1\0\1\150"+
		"\1\145\1\160\1\156\1\150\1\151\1\55\1\0\1\145\1\162\1\157\1\40\5\uffff"+
		"\1\162\1\40\1\162\1\150\1\40\1\150\1\164\2\uffff\1\150\2\uffff\1\40\2"+
		"\uffff\1\142\1\162\1\145\1\141\1\153\1\40\1\144\2\uffff";
	static final String DFA13_maxS =
		"\2\172\3\uffff\4\172\2\uffff\1\172\2\uffff\13\172\1\uffff\1\172\1\uffff"+
		"\2\172\3\uffff\1\57\4\172\1\uffff\40\172\2\uffff\1\172\1\0\1\172\1\0\1"+
		"\172\2\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\2\172\1\0\1\172\1\0\1"+
		"\172\1\0\1\uffff\1\172\1\0\1\172\1\0\2\172\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\167\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\172\1"+
		"\0\1\172\1\0\1\172\1\0\1\172\2\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff"+
		"\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1"+
		"\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1"+
		"\uffff\1\172\1\0\1\172\1\0\1\142\1\165\1\uffff\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\0\1\uffff"+
		"\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\172\1\0\1\uffff\1\0\1\172\1\uffff"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\172\1\0\1\172\1"+
		"\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\165\1\0\1\172\1\0\1\157\1"+
		"\154\1\uffff\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\2\0\1\172\1"+
		"\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\141\1\0\1\172\1\0\1\uffff"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\2\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\3\uffff\1\166\1\162\1\157\1\172\1\0\1\uffff\1\0\1\172\1"+
		"\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1"+
		"\0\1\40\1\172\1\0\1\165\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1"+
		"\uffff\1\0\1\172\1\0\1\164\1\0\1\145\1\151\1\167\1\172\1\0\1\172\1\0\1"+
		"\172\1\0\1\172\2\0\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\163\1\172\1"+
		"\0\3\uffff\1\160\1\172\1\0\2\uffff\1\165\1\0\2\uffff\1\162\1\40\1\163"+
		"\1\40\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff"+
		"\1\151\1\157\1\165\1\163\1\0\2\uffff\1\172\1\0\1\157\1\160\2\uffff\1\164"+
		"\1\150\1\164\1\165\1\0\3\uffff\1\0\1\172\1\0\1\uffff\1\147\1\167\1\160"+
		"\3\uffff\1\167\1\40\2\uffff\1\40\4\uffff\1\172\1\0\1\150\1\145\1\160\1"+
		"\156\1\164\1\167\1\172\1\0\1\145\1\162\1\157\1\40\5\uffff\1\162\1\40\1"+
		"\162\1\164\1\40\1\154\1\164\2\uffff\1\154\2\uffff\1\40\2\uffff\1\142\1"+
		"\162\1\145\1\141\1\153\1\40\1\165\2\uffff";
	static final String DFA13_acceptS =
		"\2\uffff\1\2\1\3\1\4\4\uffff\1\11\1\12\1\uffff\1\14\1\15\13\uffff\1\102"+
		"\1\uffff\1\103\2\uffff\1\105\1\101\1\107\5\uffff\1\106\40\uffff\1\110"+
		"\1\111\26\uffff\1\13\61\uffff\1\1\21\uffff\1\10\14\uffff\1\17\16\uffff"+
		"\1\36\6\uffff\1\51\20\uffff\1\21\1\uffff\1\5\4\uffff\1\104\4\uffff\1\60"+
		"\2\uffff\1\61\7\uffff\1\100\24\uffff\1\50\31\uffff\1\16\22\uffff\1\37"+
		"\1\40\1\41\5\uffff\1\66\36\uffff\1\31\25\uffff\1\6\5\uffff\1\73\1\74\1"+
		"\75\3\uffff\1\22\1\23\2\uffff\1\32\1\33\20\uffff\1\7\5\uffff\1\76\1\77"+
		"\4\uffff\1\34\1\35\5\uffff\1\67\1\70\1\71\3\uffff\1\30\3\uffff\1\62\1"+
		"\63\1\72\2\uffff\1\42\1\43\1\uffff\1\46\1\47\1\64\1\65\16\uffff\1\26\1"+
		"\27\1\44\1\45\1\20\7\uffff\1\24\1\25\1\uffff\1\54\1\55\1\uffff\1\52\1"+
		"\53\7\uffff\1\56\1\57";
	static final String DFA13_specialS =
		"\1\u00cc\1\3\3\uffff\1\u0141\1\u010a\1\u0153\1\u00d5\2\uffff\1\u00c4\2"+
		"\uffff\1\u00ad\1\u00e3\1\u013d\1\u0112\1\u00c7\1\u0121\1\u012d\1\u00b0"+
		"\1\u00bd\1\u00bf\1\u00d2\3\uffff\1\u00d4\1\u00c1\4\uffff\1\u0111\1\u0133"+
		"\1\u013e\1\u00f2\1\uffff\1\u0113\1\11\1\u0117\1\u0122\1\u00c2\1\u00ce"+
		"\1\u012c\1\u00e4\1\u00be\1\u0115\1\u00bc\1\u0102\1\u010e\1\u0132\1\u0142"+
		"\1\u0149\1\u014e\1\u0118\1\u00a3\1\u00a8\1\u0155\1\u00af\1\u00e5\1\u00b1"+
		"\1\u00c5\1\u00d7\1\u00e8\1\u00ec\1\u00f4\1\u00fa\1\u011f\1\u0127\2\uffff"+
		"\1\u00c6\1\22\1\u0134\1\20\1\u013f\1\53\1\104\1\u0114\1\105\1\12\1\136"+
		"\1\u0119\1\166\1\u00b9\1\167\1\u0123\1\u00b6\1\u008e\1\u00cf\1\170\1\u00d6"+
		"\1\103\1\uffff\1\u00c0\1\54\1\u0116\1\75\1\u012e\1\5\1\55\1\u0103\1\77"+
		"\1\u010f\1\76\1\u00f3\1\u00a2\1\u0143\1\174\1\u014a\1\125\1\u014f\1\141"+
		"\1\u011a\1\45\1\u00a4\1\u009c\1\u00a9\1\106\1\u0156\1\165\1\4\1\u00a1"+
		"\1\u00e6\1\175\1\u00b2\1\102\1\uffff\1\150\1\u00d8\1\142\1\u00e9\1\u0094"+
		"\1\u00ed\1\u0090\1\u00f5\1\121\1\u00fb\1\u009d\1\u0120\1\161\1\u0129\1"+
		"\67\1\uffff\1\u0135\1\135\1\u0140\1\70\1\u00cd\1\144\1\13\1\17\1\u011b"+
		"\1\64\1\u00dc\1\u0124\1\177\1\u00b8\1\56\1\u00d0\1\162\1\uffff\1\u00c3"+
		"\1\155\1\u00b4\1\27\1\u012f\1\62\1\6\1\163\1\u0104\1\u0080\1\u0145\1\122"+
		"\1\uffff\1\u0144\1\u0086\1\u014b\1\31\1\u0150\1\u008d\1\u011c\1\156\1"+
		"\u00a5\1\63\1\u00aa\1\u00a0\1\0\1\43\1\uffff\1\u00e7\1\u0095\1\u00b3\1"+
		"\171\3\uffff\1\u00d9\1\u0096\1\u00ea\1\151\1\u00ee\1\u0097\1\u00f6\1\111"+
		"\1\u00fc\1\51\1\u00b7\1\73\1\u012a\1\u008c\1\u0136\1\176\1\uffff\1\137"+
		"\1\uffff\1\14\1\u0083\1\u011d\1\126\1\uffff\1\u0125\1\112\1\u00ba\1\52"+
		"\1\uffff\1\66\1\u00c8\1\uffff\1\u00dd\1\u0130\1\u0091\1\7\1\74\1\u0105"+
		"\1\44\1\uffff\1\u0146\1\25\1\u014c\1\36\1\u0151\1\146\1\u00b5\1\37\1\u00a6"+
		"\1\133\1\u00ab\1\124\1\1\1\46\1\uffff\1\173\1\u00ae\1\116\3\uffff\1\u00da"+
		"\1\117\1\u00eb\1\172\1\u00ef\1\113\1\u00f7\1\152\1\u00fd\1\u0085\1\u00de"+
		"\1\u012b\1\u0081\1\u0137\1\114\1\15\1\71\1\u011e\1\u0093\1\u0126\1\35"+
		"\1\uffff\1\40\1\u00c9\1\u009f\1\uffff\1\154\1\10\1\23\1\u0106\1\57\1\u0147"+
		"\1\60\1\u014d\1\u0098\1\u0152\1\100\1\u00df\1\u00a7\1\u0092\1\u00ac\1"+
		"\123\1\2\1\130\6\uffff\1\u00db\1\u009e\1\uffff\1\u0087\1\u00f0\1\145\1"+
		"\u00f8\1\41\1\u00fe\1\107\1\u00bb\1\143\1\u0138\1\72\1\16\1\47\1\u00d1"+
		"\1\u0089\1\u0128\1\115\1\uffff\1\u00ca\1\42\1\uffff\1\157\1\u0107\1\101"+
		"\1\u0109\1\140\1\u010b\1\u0088\1\u0154\1\131\1\uffff\1\132\1\u0148\1\164"+
		"\1\uffff\1\u0084\3\uffff\1\u00e1\1\61\1\u00f1\1\u008a\1\u00f9\1\160\1"+
		"\u00ff\1\32\1\u00e0\1\u0139\1\120\1\u009a\1\50\1\uffff\1\u00d3\1\26\1"+
		"\uffff\1\u00cb\1\33\4\uffff\1\u0108\1\65\3\uffff\1\34\6\uffff\1\u00e2"+
		"\1\u0099\1\u010c\1\134\1\u010d\1\147\1\u0100\1\21\1\u013a\1\u008b\1\u0131"+
		"\1\30\5\uffff\1\110\2\uffff\1\u0110\1\u009b\10\uffff\1\24\3\uffff\1\u008f"+
		"\1\u013b\1\153\20\uffff\1\u013c\1\127\6\uffff\1\u0101\1\u0082\41\uffff}>";
	static final String[] DFA13_transitionS = {
			"\2\40\1\uffff\2\40\22\uffff\1\36\1\uffff\1\33\2\uffff\1\14\2\uffff\1"+
			"\11\1\2\2\uffff\1\4\1\31\1\uffff\1\41\12\32\1\uffff\1\15\5\uffff\15\35"+
			"\1\17\14\35\1\12\1\uffff\1\3\1\uffff\1\37\1\uffff\1\1\1\20\1\21\1\5\1"+
			"\22\1\23\1\24\1\30\1\25\2\35\1\6\1\7\1\10\1\13\2\35\1\26\1\27\1\16\1"+
			"\35\1\34\4\35",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\3\45\1\43\7\45"+
			"\1\44\1\45\1\42\14\45",
			"",
			"",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\1\47\7\45\1\50"+
			"\21\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\51\11"+
			"\45\1\52\13\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\1\53\15\45\1"+
			"\54\13\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\16\45\1\55\13"+
			"\45",
			"",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\17\45\1\60\1"+
			"\45\1\56\3\45\1\57\4\45",
			"",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\16\45\1\62\2"+
			"\45\1\61\6\45\1\63\1\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\1\64\31\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\65\17"+
			"\45\1\66\5\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\13\45\1\70\5"+
			"\45\1\67\10\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\15\45\1\71\1"+
			"\45\1\72\1\73\11\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\16\45\1\74\13"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\16\45\1\75\2"+
			"\45\1\76\10\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\22\45\1\77\7"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\100\25"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\13\45\1\101\1"+
			"\102\2\45\1\103\3\45\1\104\6\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\10\45\1\105\21"+
			"\45",
			"",
			"\12\32\7\uffff\32\46\6\uffff\32\46",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\16\45\1\106\13"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"",
			"",
			"",
			"\1\107\4\uffff\1\110",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\3\45\1\111\26"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\1\113\31\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\22\45\1\115\7"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\30\45\1\120\1"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\21\45\1\122\10"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\15\45\1\124\14"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\26\45\1\126\3"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\12\45\1\131\10"+
			"\45\1\130\6\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\21\45\1\133\10"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\23\45\1\135\6"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\140\25"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\142\25"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\145\17"+
			"\45\1\144\5\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\13\45\1\147\16"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\17\45\1\151\12"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\15\45\1\153\14\45\4\uffff\1\37\1\uffff\32"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\1\155\31\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\13\45\1\157\16"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\16\45\1\161\13"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\16\45\1\163\13"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\3\45\1\165\26"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\22\45\1\167\7"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\24\45\1\171\5"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\21\45\1\173\10"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\175\25"+
			"\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\177\25"+
			"\45",
			"\1\u0081\14\uffff\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff"+
			"\32\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\25\45\1\u0083"+
			"\4\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\16\45\1\u0085"+
			"\13\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\16\45\1\u0087"+
			"\13\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\1\u0089\31\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\1\u008b\31\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\6\45\1\u008d"+
			"\23\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\13\45\1\u008f"+
			"\16\45",
			"",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\17\45\1\u0092"+
			"\12\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\16\45\1\u0094"+
			"\13\45",
			"\1\uffff",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\22\45\1\u0096"+
			"\7\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u0098"+
			"\25\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\10\45\1\u009a"+
			"\21\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\2\45\1\u009d"+
			"\27\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u009f"+
			"\25\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u00a1"+
			"\25\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\21\45\1\u00a4"+
			"\10\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\15\45\1\u00a6"+
			"\14\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\23\45\1\u00a8"+
			"\6\45",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\15\45\1\u00aa"+
			"\14\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u00ac"+
			"\25\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u00ae"+
			"\25\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\21\45\1\u00b1"+
			"\10\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\13\45\1\u00b3"+
			"\16\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\22\45\1\u00b5"+
			"\7\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\22\45\1\u00b7"+
			"\7\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\10\45\1\u00b9"+
			"\21\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\10\45\1\u00bb"+
			"\21\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\1\u00bd\31\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\22\45\1\u00c0"+
			"\7\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u00c2"+
			"\25\45",
			"\1\uffff",
			"\1\u00c4\1\u00c5\24\uffff\1\u00c6",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u00c7"+
			"\25\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\17\45\1\u00c9"+
			"\12\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\16\45\1\u00cb"+
			"\13\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\15\45\1\u00cd"+
			"\14\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\21\45\1\u00cf"+
			"\10\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\7\45\1\u00d1"+
			"\22\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\24\45\1\u00d3"+
			"\5\45",
			"\1\uffff",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\23\45\1\u00d5"+
			"\6\45",
			"\1\uffff",
			"\1\u00d7\14\uffff\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff"+
			"\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\2\45\1\u00da"+
			"\27\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u00dc"+
			"\25\45",
			"\1\uffff",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\7\45\1\u00df"+
			"\22\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\22\45\1\u00e1"+
			"\7\45",
			"\1\uffff",
			"\1\u00e3\14\uffff\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff"+
			"\32\45",
			"\1\uffff",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\21\45\1\u00e5"+
			"\10\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\7\45\1\u00e8"+
			"\22\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\3\45\1\u00ea"+
			"\26\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\21\45\1\u00ec"+
			"\10\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\10\45\1\u00ef"+
			"\21\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\10\45\1\u00f1"+
			"\21\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\22\45\1\u00f3"+
			"\7\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u00f5"+
			"\25\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\15\45\1\u00f7"+
			"\14\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\13\45\1\u00f9"+
			"\16\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\13\45\1\u00fb"+
			"\16\45",
			"\1\uffff",
			"",
			"\1\u00fd\14\uffff\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff"+
			"\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\3\45\1\u00ff"+
			"\26\45",
			"\1\uffff",
			"\1\u0101",
			"\1\u0102\17\uffff\1\u0103",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\21\45\1\u0104"+
			"\10\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u0106"+
			"\25\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\23\45\1\u0108"+
			"\6\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\15\45\1\u010a"+
			"\14\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\23\45\1\u010c"+
			"\6\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\14\45\1\u010f"+
			"\15\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\10\45\1\u0111"+
			"\21\45",
			"\1\uffff",
			"",
			"\1\uffff",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\23\45\1\u0113"+
			"\6\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\15\45\1\u0115"+
			"\14\45",
			"\1\uffff",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\10\45\1\u0117"+
			"\21\45",
			"\1\uffff",
			"\1\u0119\14\uffff\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff"+
			"\32\45",
			"\1\uffff",
			"",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\10\45\1\u011b"+
			"\21\45",
			"",
			"\1\uffff",
			"\1\u011d\14\uffff\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff"+
			"\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\22\45\1\u011f"+
			"\7\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\1\u0121\31\45",
			"\1\uffff",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\22\45\1\u0123"+
			"\7\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\22\45\1\u0125"+
			"\7\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u0127"+
			"\25\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\6\45\1\u012a"+
			"\23\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\16\45\1\u012c"+
			"\13\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\22\45\1\u012e"+
			"\7\45",
			"\1\uffff",
			"\1\u0130\20\uffff\1\u0131",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\1\u0133",
			"\1\u0134\12\uffff\1\u0135",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\22\45\1\u0136"+
			"\7\45",
			"\1\uffff",
			"\1\u0138\14\uffff\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff"+
			"\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\7\45\1\u013a"+
			"\22\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\10\45\1\u013c"+
			"\21\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\10\45\1\u013e"+
			"\21\45",
			"\1\uffff",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u0140"+
			"\25\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\25\45\1\u0142"+
			"\4\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\10\45\1\u0144"+
			"\21\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\23\45\1\u0146"+
			"\6\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\15\45\1\u0148"+
			"\14\45",
			"\1\uffff",
			"\1\u014a",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\3\45\1\u014b"+
			"\26\45",
			"\1\uffff",
			"",
			"\1\uffff",
			"\1\u014d\14\uffff\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff"+
			"\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\15\45\1\u014f"+
			"\14\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\7\45\1\u0151"+
			"\22\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\7\45\1\u0153"+
			"\22\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\22\45\1\u0155"+
			"\7\45",
			"\1\uffff",
			"\1\uffff",
			"\1\u0157\14\uffff\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff"+
			"\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\15\45\1\u0159"+
			"\14\45",
			"\1\uffff",
			"\1\u015b\14\uffff\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff"+
			"\32\45",
			"\1\uffff",
			"",
			"",
			"",
			"\1\u015d",
			"\1\u015e",
			"\1\u015f",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u0160"+
			"\25\45",
			"\1\uffff",
			"",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u0162"+
			"\25\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\15\45\1\u0164"+
			"\14\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\15\45\1\u0166"+
			"\14\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u0169"+
			"\25\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\16\45\1\u016b"+
			"\13\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\6\45\1\u016e"+
			"\23\45",
			"\1\uffff",
			"\1\u0170",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u0171"+
			"\25\45",
			"\1\uffff",
			"\1\u0173\1\uffff\1\u0174\5\uffff\1\u0175\10\uffff\1\u0176",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\2\45\1\u0177"+
			"\27\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\1\u017b\14\uffff\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff"+
			"\32\45",
			"\1\uffff",
			"",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\1\u017e\13\uffff\1\u017f",
			"\1\uffff",
			"\1\u0180",
			"\1\u0181",
			"\1\u0182",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\22\45\1\u0183"+
			"\7\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\3\45\1\u0185"+
			"\26\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\6\45\1\u0187"+
			"\23\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\6\45\1\u0189"+
			"\23\45",
			"\1\uffff",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\21\45\1\u018b\10\45\4\uffff\1\37\1\uffff"+
			"\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\15\45\1\u018d"+
			"\14\45",
			"\1\uffff",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\1\u0190\3\uffff\1\u0191\6\uffff\1\u0192",
			"\1\u0193\14\uffff\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff"+
			"\32\45",
			"\1\uffff",
			"",
			"",
			"",
			"\1\u0195\1\uffff\1\u0196",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u0197"+
			"\25\45",
			"\1\uffff",
			"",
			"",
			"\1\u0199\20\uffff\1\u019a",
			"\1\uffff",
			"",
			"",
			"\1\u019b\11\uffff\1\u019c",
			"\1\u019d",
			"\1\u019e",
			"\1\u019f",
			"\1\u01a0\14\uffff\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff"+
			"\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\1\u01a4\14\uffff\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff"+
			"\32\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\1\u01a6\31\45",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"",
			"\1\u01a9",
			"\1\u01aa",
			"\1\u01ab",
			"\1\u01ac\15\uffff\1\u01ad",
			"\1\uffff",
			"",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\1\u01af",
			"\1\u01b0",
			"",
			"",
			"\1\u01b1\13\uffff\1\u01b2",
			"\1\u01b3",
			"\1\u01b4\13\uffff\1\u01b5",
			"\1\u01b6\20\uffff\1\u01b7",
			"\1\uffff",
			"",
			"",
			"",
			"\1\uffff",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\23\45\1\u01b8"+
			"\6\45",
			"\1\uffff",
			"",
			"\1\u01ba",
			"\1\u01bb",
			"\1\u01bc",
			"",
			"",
			"",
			"\1\u01bd",
			"\1\u01be",
			"",
			"",
			"\1\u01bf",
			"",
			"",
			"",
			"",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\4\45\1\u01c0"+
			"\25\45",
			"\1\uffff",
			"\1\u01c2",
			"\1\u01c3",
			"\1\u01c4",
			"\1\u01c5",
			"\1\u01c6\13\uffff\1\u01c7",
			"\1\u01c8\15\uffff\1\u01c9",
			"\2\37\1\uffff\12\45\7\uffff\32\45\4\uffff\1\37\1\uffff\32\45",
			"\1\uffff",
			"\1\u01cb",
			"\1\u01cc",
			"\1\u01cd",
			"\1\u01ce",
			"",
			"",
			"",
			"",
			"",
			"\1\u01cf",
			"\1\u01d0",
			"\1\u01d1",
			"\1\u01d2\13\uffff\1\u01d3",
			"\1\u01d4",
			"\1\u01d5\3\uffff\1\u01d6",
			"\1\u01d7",
			"",
			"",
			"\1\u01d8\3\uffff\1\u01d9",
			"",
			"",
			"\1\u01da",
			"",
			"",
			"\1\u01db",
			"\1\u01dc",
			"\1\u01dd",
			"\1\u01de",
			"\1\u01df",
			"\1\u01e0",
			"\1\u01e1\20\uffff\1\u01e2",
			"",
			""
	};

	static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
	static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
	static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
	static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
	static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
	static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
	static final short[][] DFA13_transition;

	static {
		int numStates = DFA13_transitionS.length;
		DFA13_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
		}
	}

	protected class DFA13 extends DFA {

		public DFA13(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 13;
			this.eot = DFA13_eot;
			this.eof = DFA13_eof;
			this.min = DFA13_min;
			this.max = DFA13_max;
			this.accept = DFA13_accept;
			this.special = DFA13_special;
			this.transition = DFA13_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( AND | CLOSEPARENTEHSIS | CLOSESQRT | COMMA | DAYS | LENIENT | MATCHING | NOT | OPENPARENTEHSIS | OPENSQRT | OR | PERCENT | SEMICOLUMN | TRUTHOF | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | Operation | NumberToken | StringToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA13_189 = input.LA(1);
						 
						int index13_189 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_189=='l') ) {s = 251;}
						else if ( ((LA13_189 >= '0' && LA13_189 <= '9')||(LA13_189 >= 'A' && LA13_189 <= 'Z')||(LA13_189 >= 'a' && LA13_189 <= 'k')||(LA13_189 >= 'm' && LA13_189 <= 'z')) ) {s = 37;}
						else if ( ((LA13_189 >= '-' && LA13_189 <= '.')||LA13_189=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 252;
						 
						input.seek(index13_189);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA13_251 = input.LA(1);
						 
						int index13_251 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_251=='s') ) {s = 302;}
						else if ( ((LA13_251 >= '0' && LA13_251 <= '9')||(LA13_251 >= 'A' && LA13_251 <= 'Z')||(LA13_251 >= 'a' && LA13_251 <= 'r')||(LA13_251 >= 't' && LA13_251 <= 'z')) ) {s = 37;}
						else if ( ((LA13_251 >= '-' && LA13_251 <= '.')||LA13_251=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 303;
						 
						input.seek(index13_251);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA13_302 = input.LA(1);
						 
						int index13_302 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_302==' ') ) {s = 347;}
						else if ( ((LA13_302 >= '0' && LA13_302 <= '9')||(LA13_302 >= 'A' && LA13_302 <= 'Z')||(LA13_302 >= 'a' && LA13_302 <= 'z')) ) {s = 37;}
						else if ( ((LA13_302 >= '-' && LA13_302 <= '.')||LA13_302=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 348;
						 
						input.seek(index13_302);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA13_1 = input.LA(1);
						 
						int index13_1 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_1=='n') ) {s = 34;}
						else if ( (LA13_1=='d') ) {s = 35;}
						else if ( (LA13_1=='l') ) {s = 36;}
						else if ( ((LA13_1 >= '0' && LA13_1 <= '9')||(LA13_1 >= 'A' && LA13_1 <= 'Z')||(LA13_1 >= 'a' && LA13_1 <= 'c')||(LA13_1 >= 'e' && LA13_1 <= 'k')||LA13_1=='m'||(LA13_1 >= 'o' && LA13_1 <= 'z')) ) {s = 37;}
						else if ( ((LA13_1 >= '-' && LA13_1 <= '.')||LA13_1=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_1);
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA13_123 = input.LA(1);
						 
						int index13_123 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_123 >= '0' && LA13_123 <= '9')||(LA13_123 >= 'A' && LA13_123 <= 'Z')||(LA13_123 >= 'a' && LA13_123 <= 'z')) ) {s = 37;}
						else if ( ((LA13_123 >= '-' && LA13_123 <= '.')||LA13_123=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 191;
						 
						input.seek(index13_123);
						if ( s>=0 ) return s;
						break;

					case 5 : 
						int LA13_101 = input.LA(1);
						 
						int index13_101 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_101=='n') ) {s = 170;}
						else if ( ((LA13_101 >= '0' && LA13_101 <= '9')||(LA13_101 >= 'A' && LA13_101 <= 'Z')||(LA13_101 >= 'a' && LA13_101 <= 'm')||(LA13_101 >= 'o' && LA13_101 <= 'z')) ) {s = 37;}
						else if ( ((LA13_101 >= '-' && LA13_101 <= '.')||LA13_101=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 171;
						 
						input.seek(index13_101);
						if ( s>=0 ) return s;
						break;

					case 6 : 
						int LA13_170 = input.LA(1);
						 
						int index13_170 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_170=='d') ) {s = 234;}
						else if ( ((LA13_170 >= '0' && LA13_170 <= '9')||(LA13_170 >= 'A' && LA13_170 <= 'Z')||(LA13_170 >= 'a' && LA13_170 <= 'c')||(LA13_170 >= 'e' && LA13_170 <= 'z')) ) {s = 37;}
						else if ( ((LA13_170 >= '-' && LA13_170 <= '.')||LA13_170=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 235;
						 
						input.seek(index13_170);
						if ( s>=0 ) return s;
						break;

					case 7 : 
						int LA13_234 = input.LA(1);
						 
						int index13_234 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_234=='s') ) {s = 287;}
						else if ( ((LA13_234 >= '0' && LA13_234 <= '9')||(LA13_234 >= 'A' && LA13_234 <= 'Z')||(LA13_234 >= 'a' && LA13_234 <= 'r')||(LA13_234 >= 't' && LA13_234 <= 'z')) ) {s = 37;}
						else if ( ((LA13_234 >= '-' && LA13_234 <= '.')||LA13_234=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 288;
						 
						input.seek(index13_234);
						if ( s>=0 ) return s;
						break;

					case 8 : 
						int LA13_287 = input.LA(1);
						 
						int index13_287 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_287==' ') ) {s = 333;}
						else if ( ((LA13_287 >= '0' && LA13_287 <= '9')||(LA13_287 >= 'A' && LA13_287 <= 'Z')||(LA13_287 >= 'a' && LA13_287 <= 'z')) ) {s = 37;}
						else if ( ((LA13_287 >= '-' && LA13_287 <= '.')||LA13_287=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 334;
						 
						input.seek(index13_287);
						if ( s>=0 ) return s;
						break;

					case 9 : 
						int LA13_40 = input.LA(1);
						 
						int index13_40 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_40=='r') ) {s = 82;}
						else if ( ((LA13_40 >= '0' && LA13_40 <= '9')||(LA13_40 >= 'A' && LA13_40 <= 'Z')||(LA13_40 >= 'a' && LA13_40 <= 'q')||(LA13_40 >= 's' && LA13_40 <= 'z')) ) {s = 37;}
						else if ( ((LA13_40 >= '-' && LA13_40 <= '.')||LA13_40=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 83;
						 
						input.seek(index13_40);
						if ( s>=0 ) return s;
						break;

					case 10 : 
						int LA13_82 = input.LA(1);
						 
						int index13_82 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_82=='e') ) {s = 152;}
						else if ( ((LA13_82 >= '0' && LA13_82 <= '9')||(LA13_82 >= 'A' && LA13_82 <= 'Z')||(LA13_82 >= 'a' && LA13_82 <= 'd')||(LA13_82 >= 'f' && LA13_82 <= 'z')) ) {s = 37;}
						else if ( ((LA13_82 >= '-' && LA13_82 <= '.')||LA13_82=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 153;
						 
						input.seek(index13_82);
						if ( s>=0 ) return s;
						break;

					case 11 : 
						int LA13_152 = input.LA(1);
						 
						int index13_152 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_152=='c') ) {s = 218;}
						else if ( ((LA13_152 >= '0' && LA13_152 <= '9')||(LA13_152 >= 'A' && LA13_152 <= 'Z')||(LA13_152 >= 'a' && LA13_152 <= 'b')||(LA13_152 >= 'd' && LA13_152 <= 'z')) ) {s = 37;}
						else if ( ((LA13_152 >= '-' && LA13_152 <= '.')||LA13_152=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 219;
						 
						input.seek(index13_152);
						if ( s>=0 ) return s;
						break;

					case 12 : 
						int LA13_218 = input.LA(1);
						 
						int index13_218 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_218=='t') ) {s = 275;}
						else if ( ((LA13_218 >= '0' && LA13_218 <= '9')||(LA13_218 >= 'A' && LA13_218 <= 'Z')||(LA13_218 >= 'a' && LA13_218 <= 's')||(LA13_218 >= 'u' && LA13_218 <= 'z')) ) {s = 37;}
						else if ( ((LA13_218 >= '-' && LA13_218 <= '.')||LA13_218=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 276;
						 
						input.seek(index13_218);
						if ( s>=0 ) return s;
						break;

					case 13 : 
						int LA13_275 = input.LA(1);
						 
						int index13_275 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_275=='i') ) {s = 324;}
						else if ( ((LA13_275 >= '0' && LA13_275 <= '9')||(LA13_275 >= 'A' && LA13_275 <= 'Z')||(LA13_275 >= 'a' && LA13_275 <= 'h')||(LA13_275 >= 'j' && LA13_275 <= 'z')) ) {s = 37;}
						else if ( ((LA13_275 >= '-' && LA13_275 <= '.')||LA13_275=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 325;
						 
						input.seek(index13_275);
						if ( s>=0 ) return s;
						break;

					case 14 : 
						int LA13_324 = input.LA(1);
						 
						int index13_324 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_324=='o') ) {s = 363;}
						else if ( ((LA13_324 >= '0' && LA13_324 <= '9')||(LA13_324 >= 'A' && LA13_324 <= 'Z')||(LA13_324 >= 'a' && LA13_324 <= 'n')||(LA13_324 >= 'p' && LA13_324 <= 'z')) ) {s = 37;}
						else if ( ((LA13_324 >= '-' && LA13_324 <= '.')||LA13_324=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 364;
						 
						input.seek(index13_324);
						if ( s>=0 ) return s;
						break;

					case 15 : 
						int LA13_153 = input.LA(1);
						 
						int index13_153 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_153);
						if ( s>=0 ) return s;
						break;

					case 16 : 
						int LA13_76 = input.LA(1);
						 
						int index13_76 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_76);
						if ( s>=0 ) return s;
						break;

					case 17 : 
						int LA13_394 = input.LA(1);
						 
						int index13_394 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_394);
						if ( s>=0 ) return s;
						break;

					case 18 : 
						int LA13_74 = input.LA(1);
						 
						int index13_74 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_74);
						if ( s>=0 ) return s;
						break;

					case 19 : 
						int LA13_288 = input.LA(1);
						 
						int index13_288 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_288);
						if ( s>=0 ) return s;
						break;

					case 20 : 
						int LA13_417 = input.LA(1);
						 
						int index13_417 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_417);
						if ( s>=0 ) return s;
						break;

					case 21 : 
						int LA13_240 = input.LA(1);
						 
						int index13_240 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_240);
						if ( s>=0 ) return s;
						break;

					case 22 : 
						int LA13_367 = input.LA(1);
						 
						int index13_367 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_367);
						if ( s>=0 ) return s;
						break;

					case 23 : 
						int LA13_167 = input.LA(1);
						 
						int index13_167 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_167);
						if ( s>=0 ) return s;
						break;

					case 24 : 
						int LA13_398 = input.LA(1);
						 
						int index13_398 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_398);
						if ( s>=0 ) return s;
						break;

					case 25 : 
						int LA13_180 = input.LA(1);
						 
						int index13_180 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_180);
						if ( s>=0 ) return s;
						break;

					case 26 : 
						int LA13_359 = input.LA(1);
						 
						int index13_359 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_359);
						if ( s>=0 ) return s;
						break;

					case 27 : 
						int LA13_370 = input.LA(1);
						 
						int index13_370 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_370);
						if ( s>=0 ) return s;
						break;

					case 28 : 
						int LA13_380 = input.LA(1);
						 
						int index13_380 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_380);
						if ( s>=0 ) return s;
						break;

					case 29 : 
						int LA13_280 = input.LA(1);
						 
						int index13_280 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_280);
						if ( s>=0 ) return s;
						break;

					case 30 : 
						int LA13_242 = input.LA(1);
						 
						int index13_242 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_242);
						if ( s>=0 ) return s;
						break;

					case 31 : 
						int LA13_246 = input.LA(1);
						 
						int index13_246 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_246);
						if ( s>=0 ) return s;
						break;

					case 32 : 
						int LA13_282 = input.LA(1);
						 
						int index13_282 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_282);
						if ( s>=0 ) return s;
						break;

					case 33 : 
						int LA13_317 = input.LA(1);
						 
						int index13_317 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_317);
						if ( s>=0 ) return s;
						break;

					case 34 : 
						int LA13_332 = input.LA(1);
						 
						int index13_332 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_332);
						if ( s>=0 ) return s;
						break;

					case 35 : 
						int LA13_190 = input.LA(1);
						 
						int index13_190 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_190);
						if ( s>=0 ) return s;
						break;

					case 36 : 
						int LA13_237 = input.LA(1);
						 
						int index13_237 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_237);
						if ( s>=0 ) return s;
						break;

					case 37 : 
						int LA13_116 = input.LA(1);
						 
						int index13_116 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_116);
						if ( s>=0 ) return s;
						break;

					case 38 : 
						int LA13_252 = input.LA(1);
						 
						int index13_252 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_252);
						if ( s>=0 ) return s;
						break;

					case 39 : 
						int LA13_325 = input.LA(1);
						 
						int index13_325 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_325);
						if ( s>=0 ) return s;
						break;

					case 40 : 
						int LA13_364 = input.LA(1);
						 
						int index13_364 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_364);
						if ( s>=0 ) return s;
						break;

					case 41 : 
						int LA13_208 = input.LA(1);
						 
						int index13_208 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_208);
						if ( s>=0 ) return s;
						break;

					case 42 : 
						int LA13_226 = input.LA(1);
						 
						int index13_226 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_226);
						if ( s>=0 ) return s;
						break;

					case 43 : 
						int LA13_78 = input.LA(1);
						 
						int index13_78 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_78);
						if ( s>=0 ) return s;
						break;

					case 44 : 
						int LA13_97 = input.LA(1);
						 
						int index13_97 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_97);
						if ( s>=0 ) return s;
						break;

					case 45 : 
						int LA13_102 = input.LA(1);
						 
						int index13_102 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_102);
						if ( s>=0 ) return s;
						break;

					case 46 : 
						int LA13_160 = input.LA(1);
						 
						int index13_160 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_160);
						if ( s>=0 ) return s;
						break;

					case 47 : 
						int LA13_290 = input.LA(1);
						 
						int index13_290 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_290);
						if ( s>=0 ) return s;
						break;

					case 48 : 
						int LA13_292 = input.LA(1);
						 
						int index13_292 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_292);
						if ( s>=0 ) return s;
						break;

					case 49 : 
						int LA13_353 = input.LA(1);
						 
						int index13_353 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_353);
						if ( s>=0 ) return s;
						break;

					case 50 : 
						int LA13_169 = input.LA(1);
						 
						int index13_169 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_169);
						if ( s>=0 ) return s;
						break;

					case 51 : 
						int LA13_186 = input.LA(1);
						 
						int index13_186 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_186);
						if ( s>=0 ) return s;
						break;

					case 52 : 
						int LA13_155 = input.LA(1);
						 
						int index13_155 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_155);
						if ( s>=0 ) return s;
						break;

					case 53 : 
						int LA13_376 = input.LA(1);
						 
						int index13_376 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_376);
						if ( s>=0 ) return s;
						break;

					case 54 : 
						int LA13_228 = input.LA(1);
						 
						int index13_228 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_228);
						if ( s>=0 ) return s;
						break;

					case 55 : 
						int LA13_144 = input.LA(1);
						 
						int index13_144 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_144);
						if ( s>=0 ) return s;
						break;

					case 56 : 
						int LA13_149 = input.LA(1);
						 
						int index13_149 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_149);
						if ( s>=0 ) return s;
						break;

					case 57 : 
						int LA13_276 = input.LA(1);
						 
						int index13_276 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_276);
						if ( s>=0 ) return s;
						break;

					case 58 : 
						int LA13_323 = input.LA(1);
						 
						int index13_323 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_323);
						if ( s>=0 ) return s;
						break;

					case 59 : 
						int LA13_210 = input.LA(1);
						 
						int index13_210 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_210);
						if ( s>=0 ) return s;
						break;

					case 60 : 
						int LA13_235 = input.LA(1);
						 
						int index13_235 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_235);
						if ( s>=0 ) return s;
						break;

					case 61 : 
						int LA13_99 = input.LA(1);
						 
						int index13_99 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_99);
						if ( s>=0 ) return s;
						break;

					case 62 : 
						int LA13_106 = input.LA(1);
						 
						int index13_106 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_106);
						if ( s>=0 ) return s;
						break;

					case 63 : 
						int LA13_104 = input.LA(1);
						 
						int index13_104 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_104);
						if ( s>=0 ) return s;
						break;

					case 64 : 
						int LA13_296 = input.LA(1);
						 
						int index13_296 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_296);
						if ( s>=0 ) return s;
						break;

					case 65 : 
						int LA13_336 = input.LA(1);
						 
						int index13_336 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_336);
						if ( s>=0 ) return s;
						break;

					case 66 : 
						int LA13_128 = input.LA(1);
						 
						int index13_128 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_128);
						if ( s>=0 ) return s;
						break;

					case 67 : 
						int LA13_94 = input.LA(1);
						 
						int index13_94 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_94);
						if ( s>=0 ) return s;
						break;

					case 68 : 
						int LA13_79 = input.LA(1);
						 
						int index13_79 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_79);
						if ( s>=0 ) return s;
						break;

					case 69 : 
						int LA13_81 = input.LA(1);
						 
						int index13_81 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_81);
						if ( s>=0 ) return s;
						break;

					case 70 : 
						int LA13_120 = input.LA(1);
						 
						int index13_120 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_120);
						if ( s>=0 ) return s;
						break;

					case 71 : 
						int LA13_319 = input.LA(1);
						 
						int index13_319 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_319);
						if ( s>=0 ) return s;
						break;

					case 72 : 
						int LA13_404 = input.LA(1);
						 
						int index13_404 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_404);
						if ( s>=0 ) return s;
						break;

					case 73 : 
						int LA13_206 = input.LA(1);
						 
						int index13_206 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_206);
						if ( s>=0 ) return s;
						break;

					case 74 : 
						int LA13_224 = input.LA(1);
						 
						int index13_224 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_224);
						if ( s>=0 ) return s;
						break;

					case 75 : 
						int LA13_265 = input.LA(1);
						 
						int index13_265 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_265);
						if ( s>=0 ) return s;
						break;

					case 76 : 
						int LA13_274 = input.LA(1);
						 
						int index13_274 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_274);
						if ( s>=0 ) return s;
						break;

					case 77 : 
						int LA13_329 = input.LA(1);
						 
						int index13_329 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_329);
						if ( s>=0 ) return s;
						break;

					case 78 : 
						int LA13_256 = input.LA(1);
						 
						int index13_256 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_256);
						if ( s>=0 ) return s;
						break;

					case 79 : 
						int LA13_261 = input.LA(1);
						 
						int index13_261 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_261);
						if ( s>=0 ) return s;
						break;

					case 80 : 
						int LA13_362 = input.LA(1);
						 
						int index13_362 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_362);
						if ( s>=0 ) return s;
						break;

					case 81 : 
						int LA13_138 = input.LA(1);
						 
						int index13_138 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_138);
						if ( s>=0 ) return s;
						break;

					case 82 : 
						int LA13_175 = input.LA(1);
						 
						int index13_175 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_175);
						if ( s>=0 ) return s;
						break;

					case 83 : 
						int LA13_301 = input.LA(1);
						 
						int index13_301 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_301);
						if ( s>=0 ) return s;
						break;

					case 84 : 
						int LA13_250 = input.LA(1);
						 
						int index13_250 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_250);
						if ( s>=0 ) return s;
						break;

					case 85 : 
						int LA13_112 = input.LA(1);
						 
						int index13_112 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_112);
						if ( s>=0 ) return s;
						break;

					case 86 : 
						int LA13_221 = input.LA(1);
						 
						int index13_221 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_221);
						if ( s>=0 ) return s;
						break;

					case 87 : 
						int LA13_441 = input.LA(1);
						 
						int index13_441 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_441);
						if ( s>=0 ) return s;
						break;

					case 88 : 
						int LA13_303 = input.LA(1);
						 
						int index13_303 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_303);
						if ( s>=0 ) return s;
						break;

					case 89 : 
						int LA13_342 = input.LA(1);
						 
						int index13_342 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_342);
						if ( s>=0 ) return s;
						break;

					case 90 : 
						int LA13_344 = input.LA(1);
						 
						int index13_344 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_344);
						if ( s>=0 ) return s;
						break;

					case 91 : 
						int LA13_248 = input.LA(1);
						 
						int index13_248 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_248);
						if ( s>=0 ) return s;
						break;

					case 92 : 
						int LA13_390 = input.LA(1);
						 
						int index13_390 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_390);
						if ( s>=0 ) return s;
						break;

					case 93 : 
						int LA13_147 = input.LA(1);
						 
						int index13_147 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_147);
						if ( s>=0 ) return s;
						break;

					case 94 : 
						int LA13_83 = input.LA(1);
						 
						int index13_83 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_83);
						if ( s>=0 ) return s;
						break;

					case 95 : 
						int LA13_216 = input.LA(1);
						 
						int index13_216 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_216);
						if ( s>=0 ) return s;
						break;

					case 96 : 
						int LA13_338 = input.LA(1);
						 
						int index13_338 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_338);
						if ( s>=0 ) return s;
						break;

					case 97 : 
						int LA13_114 = input.LA(1);
						 
						int index13_114 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_114);
						if ( s>=0 ) return s;
						break;

					case 98 : 
						int LA13_132 = input.LA(1);
						 
						int index13_132 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_132);
						if ( s>=0 ) return s;
						break;

					case 99 : 
						int LA13_321 = input.LA(1);
						 
						int index13_321 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_321);
						if ( s>=0 ) return s;
						break;

					case 100 : 
						int LA13_151 = input.LA(1);
						 
						int index13_151 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_151);
						if ( s>=0 ) return s;
						break;

					case 101 : 
						int LA13_315 = input.LA(1);
						 
						int index13_315 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_315);
						if ( s>=0 ) return s;
						break;

					case 102 : 
						int LA13_244 = input.LA(1);
						 
						int index13_244 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_244);
						if ( s>=0 ) return s;
						break;

					case 103 : 
						int LA13_392 = input.LA(1);
						 
						int index13_392 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_392);
						if ( s>=0 ) return s;
						break;

					case 104 : 
						int LA13_130 = input.LA(1);
						 
						int index13_130 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_130);
						if ( s>=0 ) return s;
						break;

					case 105 : 
						int LA13_202 = input.LA(1);
						 
						int index13_202 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_202);
						if ( s>=0 ) return s;
						break;

					case 106 : 
						int LA13_267 = input.LA(1);
						 
						int index13_267 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_267);
						if ( s>=0 ) return s;
						break;

					case 107 : 
						int LA13_423 = input.LA(1);
						 
						int index13_423 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_423);
						if ( s>=0 ) return s;
						break;

					case 108 : 
						int LA13_286 = input.LA(1);
						 
						int index13_286 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_286);
						if ( s>=0 ) return s;
						break;

					case 109 : 
						int LA13_165 = input.LA(1);
						 
						int index13_165 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_165);
						if ( s>=0 ) return s;
						break;

					case 110 : 
						int LA13_184 = input.LA(1);
						 
						int index13_184 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_184);
						if ( s>=0 ) return s;
						break;

					case 111 : 
						int LA13_334 = input.LA(1);
						 
						int index13_334 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_334);
						if ( s>=0 ) return s;
						break;

					case 112 : 
						int LA13_357 = input.LA(1);
						 
						int index13_357 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_357);
						if ( s>=0 ) return s;
						break;

					case 113 : 
						int LA13_142 = input.LA(1);
						 
						int index13_142 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_142);
						if ( s>=0 ) return s;
						break;

					case 114 : 
						int LA13_162 = input.LA(1);
						 
						int index13_162 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_162);
						if ( s>=0 ) return s;
						break;

					case 115 : 
						int LA13_171 = input.LA(1);
						 
						int index13_171 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_171);
						if ( s>=0 ) return s;
						break;

					case 116 : 
						int LA13_346 = input.LA(1);
						 
						int index13_346 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_346);
						if ( s>=0 ) return s;
						break;

					case 117 : 
						int LA13_122 = input.LA(1);
						 
						int index13_122 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_122);
						if ( s>=0 ) return s;
						break;

					case 118 : 
						int LA13_85 = input.LA(1);
						 
						int index13_85 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_85);
						if ( s>=0 ) return s;
						break;

					case 119 : 
						int LA13_87 = input.LA(1);
						 
						int index13_87 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_87);
						if ( s>=0 ) return s;
						break;

					case 120 : 
						int LA13_92 = input.LA(1);
						 
						int index13_92 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_92);
						if ( s>=0 ) return s;
						break;

					case 121 : 
						int LA13_195 = input.LA(1);
						 
						int index13_195 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_195);
						if ( s>=0 ) return s;
						break;

					case 122 : 
						int LA13_263 = input.LA(1);
						 
						int index13_263 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_263);
						if ( s>=0 ) return s;
						break;

					case 123 : 
						int LA13_254 = input.LA(1);
						 
						int index13_254 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_254);
						if ( s>=0 ) return s;
						break;

					case 124 : 
						int LA13_110 = input.LA(1);
						 
						int index13_110 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_110);
						if ( s>=0 ) return s;
						break;

					case 125 : 
						int LA13_126 = input.LA(1);
						 
						int index13_126 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_126);
						if ( s>=0 ) return s;
						break;

					case 126 : 
						int LA13_214 = input.LA(1);
						 
						int index13_214 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_214);
						if ( s>=0 ) return s;
						break;

					case 127 : 
						int LA13_158 = input.LA(1);
						 
						int index13_158 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_158);
						if ( s>=0 ) return s;
						break;

					case 128 : 
						int LA13_173 = input.LA(1);
						 
						int index13_173 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_173);
						if ( s>=0 ) return s;
						break;

					case 129 : 
						int LA13_272 = input.LA(1);
						 
						int index13_272 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_272);
						if ( s>=0 ) return s;
						break;

					case 130 : 
						int LA13_449 = input.LA(1);
						 
						int index13_449 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_449);
						if ( s>=0 ) return s;
						break;

					case 131 : 
						int LA13_219 = input.LA(1);
						 
						int index13_219 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_219);
						if ( s>=0 ) return s;
						break;

					case 132 : 
						int LA13_348 = input.LA(1);
						 
						int index13_348 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_348);
						if ( s>=0 ) return s;
						break;

					case 133 : 
						int LA13_269 = input.LA(1);
						 
						int index13_269 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_269);
						if ( s>=0 ) return s;
						break;

					case 134 : 
						int LA13_178 = input.LA(1);
						 
						int index13_178 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_178);
						if ( s>=0 ) return s;
						break;

					case 135 : 
						int LA13_313 = input.LA(1);
						 
						int index13_313 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_313);
						if ( s>=0 ) return s;
						break;

					case 136 : 
						int LA13_340 = input.LA(1);
						 
						int index13_340 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_340);
						if ( s>=0 ) return s;
						break;

					case 137 : 
						int LA13_327 = input.LA(1);
						 
						int index13_327 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_327);
						if ( s>=0 ) return s;
						break;

					case 138 : 
						int LA13_355 = input.LA(1);
						 
						int index13_355 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_355);
						if ( s>=0 ) return s;
						break;

					case 139 : 
						int LA13_396 = input.LA(1);
						 
						int index13_396 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_396);
						if ( s>=0 ) return s;
						break;

					case 140 : 
						int LA13_212 = input.LA(1);
						 
						int index13_212 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_212);
						if ( s>=0 ) return s;
						break;

					case 141 : 
						int LA13_182 = input.LA(1);
						 
						int index13_182 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_182);
						if ( s>=0 ) return s;
						break;

					case 142 : 
						int LA13_90 = input.LA(1);
						 
						int index13_90 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_90);
						if ( s>=0 ) return s;
						break;

					case 143 : 
						int LA13_421 = input.LA(1);
						 
						int index13_421 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_421);
						if ( s>=0 ) return s;
						break;

					case 144 : 
						int LA13_136 = input.LA(1);
						 
						int index13_136 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_136);
						if ( s>=0 ) return s;
						break;

					case 145 : 
						int LA13_233 = input.LA(1);
						 
						int index13_233 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_233);
						if ( s>=0 ) return s;
						break;

					case 146 : 
						int LA13_299 = input.LA(1);
						 
						int index13_299 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_299);
						if ( s>=0 ) return s;
						break;

					case 147 : 
						int LA13_278 = input.LA(1);
						 
						int index13_278 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_278);
						if ( s>=0 ) return s;
						break;

					case 148 : 
						int LA13_134 = input.LA(1);
						 
						int index13_134 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_134);
						if ( s>=0 ) return s;
						break;

					case 149 : 
						int LA13_193 = input.LA(1);
						 
						int index13_193 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_193);
						if ( s>=0 ) return s;
						break;

					case 150 : 
						int LA13_200 = input.LA(1);
						 
						int index13_200 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_200);
						if ( s>=0 ) return s;
						break;

					case 151 : 
						int LA13_204 = input.LA(1);
						 
						int index13_204 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_204);
						if ( s>=0 ) return s;
						break;

					case 152 : 
						int LA13_294 = input.LA(1);
						 
						int index13_294 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_294);
						if ( s>=0 ) return s;
						break;

					case 153 : 
						int LA13_388 = input.LA(1);
						 
						int index13_388 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_388);
						if ( s>=0 ) return s;
						break;

					case 154 : 
						int LA13_363 = input.LA(1);
						 
						int index13_363 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_363=='n') ) {s = 397;}
						else if ( ((LA13_363 >= '0' && LA13_363 <= '9')||(LA13_363 >= 'A' && LA13_363 <= 'Z')||(LA13_363 >= 'a' && LA13_363 <= 'm')||(LA13_363 >= 'o' && LA13_363 <= 'z')) ) {s = 37;}
						else if ( ((LA13_363 >= '-' && LA13_363 <= '.')||LA13_363=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 398;
						 
						input.seek(index13_363);
						if ( s>=0 ) return s;
						break;

					case 155 : 
						int LA13_408 = input.LA(1);
						 
						int index13_408 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_408);
						if ( s>=0 ) return s;
						break;

					case 156 : 
						int LA13_118 = input.LA(1);
						 
						int index13_118 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_118);
						if ( s>=0 ) return s;
						break;

					case 157 : 
						int LA13_140 = input.LA(1);
						 
						int index13_140 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_140);
						if ( s>=0 ) return s;
						break;

					case 158 : 
						int LA13_311 = input.LA(1);
						 
						int index13_311 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_311);
						if ( s>=0 ) return s;
						break;

					case 159 : 
						int LA13_284 = input.LA(1);
						 
						int index13_284 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_284);
						if ( s>=0 ) return s;
						break;

					case 160 : 
						int LA13_188 = input.LA(1);
						 
						int index13_188 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_188);
						if ( s>=0 ) return s;
						break;

					case 161 : 
						int LA13_124 = input.LA(1);
						 
						int index13_124 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_124);
						if ( s>=0 ) return s;
						break;

					case 162 : 
						int LA13_108 = input.LA(1);
						 
						int index13_108 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_108);
						if ( s>=0 ) return s;
						break;

					case 163 : 
						int LA13_57 = input.LA(1);
						 
						int index13_57 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_57=='d') ) {s = 117;}
						else if ( ((LA13_57 >= '0' && LA13_57 <= '9')||(LA13_57 >= 'A' && LA13_57 <= 'Z')||(LA13_57 >= 'a' && LA13_57 <= 'c')||(LA13_57 >= 'e' && LA13_57 <= 'z')) ) {s = 37;}
						else if ( ((LA13_57 >= '-' && LA13_57 <= '.')||LA13_57=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 118;
						 
						input.seek(index13_57);
						if ( s>=0 ) return s;
						break;

					case 164 : 
						int LA13_117 = input.LA(1);
						 
						int index13_117 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_117=='i') ) {s = 185;}
						else if ( ((LA13_117 >= '0' && LA13_117 <= '9')||(LA13_117 >= 'A' && LA13_117 <= 'Z')||(LA13_117 >= 'a' && LA13_117 <= 'h')||(LA13_117 >= 'j' && LA13_117 <= 'z')) ) {s = 37;}
						else if ( ((LA13_117 >= '-' && LA13_117 <= '.')||LA13_117=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 186;
						 
						input.seek(index13_117);
						if ( s>=0 ) return s;
						break;

					case 165 : 
						int LA13_185 = input.LA(1);
						 
						int index13_185 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_185=='n') ) {s = 247;}
						else if ( ((LA13_185 >= '0' && LA13_185 <= '9')||(LA13_185 >= 'A' && LA13_185 <= 'Z')||(LA13_185 >= 'a' && LA13_185 <= 'm')||(LA13_185 >= 'o' && LA13_185 <= 'z')) ) {s = 37;}
						else if ( ((LA13_185 >= '-' && LA13_185 <= '.')||LA13_185=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 248;
						 
						input.seek(index13_185);
						if ( s>=0 ) return s;
						break;

					case 166 : 
						int LA13_247 = input.LA(1);
						 
						int index13_247 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_247=='g') ) {s = 298;}
						else if ( ((LA13_247 >= '0' && LA13_247 <= '9')||(LA13_247 >= 'A' && LA13_247 <= 'Z')||(LA13_247 >= 'a' && LA13_247 <= 'f')||(LA13_247 >= 'h' && LA13_247 <= 'z')) ) {s = 37;}
						else if ( ((LA13_247 >= '-' && LA13_247 <= '.')||LA13_247=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 299;
						 
						input.seek(index13_247);
						if ( s>=0 ) return s;
						break;

					case 167 : 
						int LA13_298 = input.LA(1);
						 
						int index13_298 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_298==' ') ) {s = 343;}
						else if ( ((LA13_298 >= '0' && LA13_298 <= '9')||(LA13_298 >= 'A' && LA13_298 <= 'Z')||(LA13_298 >= 'a' && LA13_298 <= 'z')) ) {s = 37;}
						else if ( ((LA13_298 >= '-' && LA13_298 <= '.')||LA13_298=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 344;
						 
						input.seek(index13_298);
						if ( s>=0 ) return s;
						break;

					case 168 : 
						int LA13_58 = input.LA(1);
						 
						int index13_58 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_58=='s') ) {s = 119;}
						else if ( ((LA13_58 >= '0' && LA13_58 <= '9')||(LA13_58 >= 'A' && LA13_58 <= 'Z')||(LA13_58 >= 'a' && LA13_58 <= 'r')||(LA13_58 >= 't' && LA13_58 <= 'z')) ) {s = 37;}
						else if ( ((LA13_58 >= '-' && LA13_58 <= '.')||LA13_58=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 120;
						 
						input.seek(index13_58);
						if ( s>=0 ) return s;
						break;

					case 169 : 
						int LA13_119 = input.LA(1);
						 
						int index13_119 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_119=='i') ) {s = 187;}
						else if ( ((LA13_119 >= '0' && LA13_119 <= '9')||(LA13_119 >= 'A' && LA13_119 <= 'Z')||(LA13_119 >= 'a' && LA13_119 <= 'h')||(LA13_119 >= 'j' && LA13_119 <= 'z')) ) {s = 37;}
						else if ( ((LA13_119 >= '-' && LA13_119 <= '.')||LA13_119=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 188;
						 
						input.seek(index13_119);
						if ( s>=0 ) return s;
						break;

					case 170 : 
						int LA13_187 = input.LA(1);
						 
						int index13_187 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_187=='l') ) {s = 249;}
						else if ( ((LA13_187 >= '0' && LA13_187 <= '9')||(LA13_187 >= 'A' && LA13_187 <= 'Z')||(LA13_187 >= 'a' && LA13_187 <= 'k')||(LA13_187 >= 'm' && LA13_187 <= 'z')) ) {s = 37;}
						else if ( ((LA13_187 >= '-' && LA13_187 <= '.')||LA13_187=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 250;
						 
						input.seek(index13_187);
						if ( s>=0 ) return s;
						break;

					case 171 : 
						int LA13_249 = input.LA(1);
						 
						int index13_249 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_249=='o') ) {s = 300;}
						else if ( ((LA13_249 >= '0' && LA13_249 <= '9')||(LA13_249 >= 'A' && LA13_249 <= 'Z')||(LA13_249 >= 'a' && LA13_249 <= 'n')||(LA13_249 >= 'p' && LA13_249 <= 'z')) ) {s = 37;}
						else if ( ((LA13_249 >= '-' && LA13_249 <= '.')||LA13_249=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 301;
						 
						input.seek(index13_249);
						if ( s>=0 ) return s;
						break;

					case 172 : 
						int LA13_300 = input.LA(1);
						 
						int index13_300 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_300=='n') ) {s = 345;}
						else if ( ((LA13_300 >= '0' && LA13_300 <= '9')||(LA13_300 >= 'A' && LA13_300 <= 'Z')||(LA13_300 >= 'a' && LA13_300 <= 'm')||(LA13_300 >= 'o' && LA13_300 <= 'z')) ) {s = 37;}
						else if ( ((LA13_300 >= '-' && LA13_300 <= '.')||LA13_300=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 346;
						 
						input.seek(index13_300);
						if ( s>=0 ) return s;
						break;

					case 173 : 
						int LA13_14 = input.LA(1);
						 
						int index13_14 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_14=='r') ) {s = 49;}
						else if ( (LA13_14=='o') ) {s = 50;}
						else if ( (LA13_14=='y') ) {s = 51;}
						else if ( ((LA13_14 >= '0' && LA13_14 <= '9')||(LA13_14 >= 'A' && LA13_14 <= 'Z')||(LA13_14 >= 'a' && LA13_14 <= 'n')||(LA13_14 >= 'p' && LA13_14 <= 'q')||(LA13_14 >= 's' && LA13_14 <= 'x')||LA13_14=='z') ) {s = 37;}
						else if ( ((LA13_14 >= '-' && LA13_14 <= '.')||LA13_14=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_14);
						if ( s>=0 ) return s;
						break;

					case 174 : 
						int LA13_255 = input.LA(1);
						 
						int index13_255 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_255 >= '0' && LA13_255 <= '9')||(LA13_255 >= 'A' && LA13_255 <= 'Z')||(LA13_255 >= 'a' && LA13_255 <= 'z')) ) {s = 37;}
						else if ( ((LA13_255 >= '-' && LA13_255 <= '.')||LA13_255=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 306;
						 
						input.seek(index13_255);
						if ( s>=0 ) return s;
						break;

					case 175 : 
						int LA13_60 = input.LA(1);
						 
						int index13_60 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_60=='r') ) {s = 123;}
						else if ( ((LA13_60 >= '0' && LA13_60 <= '9')||(LA13_60 >= 'A' && LA13_60 <= 'Z')||(LA13_60 >= 'a' && LA13_60 <= 'q')||(LA13_60 >= 's' && LA13_60 <= 'z')) ) {s = 37;}
						else if ( ((LA13_60 >= '-' && LA13_60 <= '.')||LA13_60=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 124;
						 
						input.seek(index13_60);
						if ( s>=0 ) return s;
						break;

					case 176 : 
						int LA13_21 = input.LA(1);
						 
						int index13_21 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_21=='s') ) {s = 63;}
						else if ( ((LA13_21 >= '0' && LA13_21 <= '9')||(LA13_21 >= 'A' && LA13_21 <= 'Z')||(LA13_21 >= 'a' && LA13_21 <= 'r')||(LA13_21 >= 't' && LA13_21 <= 'z')) ) {s = 37;}
						else if ( ((LA13_21 >= '-' && LA13_21 <= '.')||LA13_21=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_21);
						if ( s>=0 ) return s;
						break;

					case 177 : 
						int LA13_62 = input.LA(1);
						 
						int index13_62 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_62=='e') ) {s = 127;}
						else if ( ((LA13_62 >= '0' && LA13_62 <= '9')||(LA13_62 >= 'A' && LA13_62 <= 'Z')||(LA13_62 >= 'a' && LA13_62 <= 'd')||(LA13_62 >= 'f' && LA13_62 <= 'z')) ) {s = 37;}
						else if ( ((LA13_62 >= '-' && LA13_62 <= '.')||LA13_62=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 128;
						 
						input.seek(index13_62);
						if ( s>=0 ) return s;
						break;

					case 178 : 
						int LA13_127 = input.LA(1);
						 
						int index13_127 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_127=='e') ) {s = 194;}
						else if ( ((LA13_127 >= '0' && LA13_127 <= '9')||(LA13_127 >= 'A' && LA13_127 <= 'Z')||(LA13_127 >= 'a' && LA13_127 <= 'd')||(LA13_127 >= 'f' && LA13_127 <= 'z')) ) {s = 37;}
						else if ( ((LA13_127 >= '-' && LA13_127 <= '.')||LA13_127=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 195;
						 
						input.seek(index13_127);
						if ( s>=0 ) return s;
						break;

					case 179 : 
						int LA13_194 = input.LA(1);
						 
						int index13_194 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_194=='d') ) {s = 255;}
						else if ( ((LA13_194 >= '0' && LA13_194 <= '9')||(LA13_194 >= 'A' && LA13_194 <= 'Z')||(LA13_194 >= 'a' && LA13_194 <= 'c')||(LA13_194 >= 'e' && LA13_194 <= 'z')) ) {s = 37;}
						else if ( ((LA13_194 >= '-' && LA13_194 <= '.')||LA13_194=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 256;
						 
						input.seek(index13_194);
						if ( s>=0 ) return s;
						break;

					case 180 : 
						int LA13_166 = input.LA(1);
						 
						int index13_166 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_166 >= '0' && LA13_166 <= '9')||(LA13_166 >= 'A' && LA13_166 <= 'Z')||(LA13_166 >= 'a' && LA13_166 <= 'z')) ) {s = 37;}
						else if ( ((LA13_166 >= '-' && LA13_166 <= '.')||LA13_166=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 231;
						 
						input.seek(index13_166);
						if ( s>=0 ) return s;
						break;

					case 181 : 
						int LA13_245 = input.LA(1);
						 
						int index13_245 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_245 >= '0' && LA13_245 <= '9')||(LA13_245 >= 'A' && LA13_245 <= 'Z')||(LA13_245 >= 'a' && LA13_245 <= 'z')) ) {s = 37;}
						else if ( ((LA13_245 >= '-' && LA13_245 <= '.')||LA13_245=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 297;
						 
						input.seek(index13_245);
						if ( s>=0 ) return s;
						break;

					case 182 : 
						int LA13_89 = input.LA(1);
						 
						int index13_89 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_89=='e') ) {s = 159;}
						else if ( ((LA13_89 >= '0' && LA13_89 <= '9')||(LA13_89 >= 'A' && LA13_89 <= 'Z')||(LA13_89 >= 'a' && LA13_89 <= 'd')||(LA13_89 >= 'f' && LA13_89 <= 'z')) ) {s = 37;}
						else if ( ((LA13_89 >= '-' && LA13_89 <= '.')||LA13_89=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 160;
						 
						input.seek(index13_89);
						if ( s>=0 ) return s;
						break;

					case 183 : 
						int LA13_209 = input.LA(1);
						 
						int index13_209 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_209 >= '0' && LA13_209 <= '9')||(LA13_209 >= 'A' && LA13_209 <= 'Z')||(LA13_209 >= 'a' && LA13_209 <= 'z')) ) {s = 37;}
						else if ( ((LA13_209 >= '-' && LA13_209 <= '.')||LA13_209=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 270;
						 
						input.seek(index13_209);
						if ( s>=0 ) return s;
						break;

					case 184 : 
						int LA13_159 = input.LA(1);
						 
						int index13_159 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_159=='s') ) {s = 225;}
						else if ( ((LA13_159 >= '0' && LA13_159 <= '9')||(LA13_159 >= 'A' && LA13_159 <= 'Z')||(LA13_159 >= 'a' && LA13_159 <= 'r')||(LA13_159 >= 't' && LA13_159 <= 'z')) ) {s = 37;}
						else if ( ((LA13_159 >= '-' && LA13_159 <= '.')||LA13_159=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 226;
						 
						input.seek(index13_159);
						if ( s>=0 ) return s;
						break;

					case 185 : 
						int LA13_86 = input.LA(1);
						 
						int index13_86 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_86 >= '0' && LA13_86 <= '9')||(LA13_86 >= 'A' && LA13_86 <= 'Z')||(LA13_86 >= 'a' && LA13_86 <= 'z')) ) {s = 37;}
						else if ( ((LA13_86 >= '-' && LA13_86 <= '.')||LA13_86=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 156;
						 
						input.seek(index13_86);
						if ( s>=0 ) return s;
						break;

					case 186 : 
						int LA13_225 = input.LA(1);
						 
						int index13_225 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_225==' ') ) {s = 281;}
						else if ( ((LA13_225 >= '0' && LA13_225 <= '9')||(LA13_225 >= 'A' && LA13_225 <= 'Z')||(LA13_225 >= 'a' && LA13_225 <= 'z')) ) {s = 37;}
						else if ( ((LA13_225 >= '-' && LA13_225 <= '.')||LA13_225=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 282;
						 
						input.seek(index13_225);
						if ( s>=0 ) return s;
						break;

					case 187 : 
						int LA13_320 = input.LA(1);
						 
						int index13_320 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_320 >= '0' && LA13_320 <= '9')||(LA13_320 >= 'A' && LA13_320 <= 'Z')||(LA13_320 >= 'a' && LA13_320 <= 'z')) ) {s = 37;}
						else if ( ((LA13_320 >= '-' && LA13_320 <= '.')||LA13_320=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 360;
						 
						input.seek(index13_320);
						if ( s>=0 ) return s;
						break;

					case 188 : 
						int LA13_49 = input.LA(1);
						 
						int index13_49 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_49=='u') ) {s = 100;}
						else if ( (LA13_49=='e') ) {s = 101;}
						else if ( ((LA13_49 >= '0' && LA13_49 <= '9')||(LA13_49 >= 'A' && LA13_49 <= 'Z')||(LA13_49 >= 'a' && LA13_49 <= 'd')||(LA13_49 >= 'f' && LA13_49 <= 't')||(LA13_49 >= 'v' && LA13_49 <= 'z')) ) {s = 37;}
						else if ( ((LA13_49 >= '-' && LA13_49 <= '.')||LA13_49=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 102;
						 
						input.seek(index13_49);
						if ( s>=0 ) return s;
						break;

					case 189 : 
						int LA13_22 = input.LA(1);
						 
						int index13_22 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_22=='e') ) {s = 64;}
						else if ( ((LA13_22 >= '0' && LA13_22 <= '9')||(LA13_22 >= 'A' && LA13_22 <= 'Z')||(LA13_22 >= 'a' && LA13_22 <= 'd')||(LA13_22 >= 'f' && LA13_22 <= 'z')) ) {s = 37;}
						else if ( ((LA13_22 >= '-' && LA13_22 <= '.')||LA13_22=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_22);
						if ( s>=0 ) return s;
						break;

					case 190 : 
						int LA13_47 = input.LA(1);
						 
						int index13_47 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_47=='e') ) {s = 96;}
						else if ( ((LA13_47 >= '0' && LA13_47 <= '9')||(LA13_47 >= 'A' && LA13_47 <= 'Z')||(LA13_47 >= 'a' && LA13_47 <= 'd')||(LA13_47 >= 'f' && LA13_47 <= 'z')) ) {s = 37;}
						else if ( ((LA13_47 >= '-' && LA13_47 <= '.')||LA13_47=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 97;
						 
						input.seek(index13_47);
						if ( s>=0 ) return s;
						break;

					case 191 : 
						int LA13_23 = input.LA(1);
						 
						int index13_23 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_23=='l') ) {s = 65;}
						else if ( (LA13_23=='m') ) {s = 66;}
						else if ( (LA13_23=='p') ) {s = 67;}
						else if ( (LA13_23=='t') ) {s = 68;}
						else if ( ((LA13_23 >= '0' && LA13_23 <= '9')||(LA13_23 >= 'A' && LA13_23 <= 'Z')||(LA13_23 >= 'a' && LA13_23 <= 'k')||(LA13_23 >= 'n' && LA13_23 <= 'o')||(LA13_23 >= 'q' && LA13_23 <= 's')||(LA13_23 >= 'u' && LA13_23 <= 'z')) ) {s = 37;}
						else if ( ((LA13_23 >= '-' && LA13_23 <= '.')||LA13_23=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_23);
						if ( s>=0 ) return s;
						break;

					case 192 : 
						int LA13_96 = input.LA(1);
						 
						int index13_96 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_96=='r') ) {s = 164;}
						else if ( ((LA13_96 >= '0' && LA13_96 <= '9')||(LA13_96 >= 'A' && LA13_96 <= 'Z')||(LA13_96 >= 'a' && LA13_96 <= 'q')||(LA13_96 >= 's' && LA13_96 <= 'z')) ) {s = 37;}
						else if ( ((LA13_96 >= '-' && LA13_96 <= '.')||LA13_96=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 165;
						 
						input.seek(index13_96);
						if ( s>=0 ) return s;
						break;

					case 193 : 
						int LA13_29 = input.LA(1);
						 
						int index13_29 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_29 >= '0' && LA13_29 <= '9')||(LA13_29 >= 'A' && LA13_29 <= 'Z')||(LA13_29 >= 'a' && LA13_29 <= 'z')) ) {s = 37;}
						else if ( ((LA13_29 >= '-' && LA13_29 <= '.')||LA13_29=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_29);
						if ( s>=0 ) return s;
						break;

					case 194 : 
						int LA13_43 = input.LA(1);
						 
						int index13_43 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_43=='t') ) {s = 88;}
						else if ( (LA13_43=='k') ) {s = 89;}
						else if ( ((LA13_43 >= '0' && LA13_43 <= '9')||(LA13_43 >= 'A' && LA13_43 <= 'Z')||(LA13_43 >= 'a' && LA13_43 <= 'j')||(LA13_43 >= 'l' && LA13_43 <= 's')||(LA13_43 >= 'u' && LA13_43 <= 'z')) ) {s = 37;}
						else if ( ((LA13_43 >= '-' && LA13_43 <= '.')||LA13_43=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 90;
						 
						input.seek(index13_43);
						if ( s>=0 ) return s;
						break;

					case 195 : 
						int LA13_164 = input.LA(1);
						 
						int index13_164 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_164=='r') ) {s = 229;}
						else if ( ((LA13_164 >= '0' && LA13_164 <= '9')||(LA13_164 >= 'A' && LA13_164 <= 'Z')||(LA13_164 >= 'a' && LA13_164 <= 'q')||(LA13_164 >= 's' && LA13_164 <= 'z')) ) {s = 37;}
						else if ( ((LA13_164 >= '-' && LA13_164 <= '.')||LA13_164=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 230;
						 
						input.seek(index13_164);
						if ( s>=0 ) return s;
						break;

					case 196 : 
						int LA13_11 = input.LA(1);
						 
						int index13_11 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_11=='r') ) {s = 46;}
						else if ( (LA13_11=='v') ) {s = 47;}
						else if ( (LA13_11=='p') ) {s = 48;}
						else if ( ((LA13_11 >= '0' && LA13_11 <= '9')||(LA13_11 >= 'A' && LA13_11 <= 'Z')||(LA13_11 >= 'a' && LA13_11 <= 'o')||LA13_11=='q'||(LA13_11 >= 's' && LA13_11 <= 'u')||(LA13_11 >= 'w' && LA13_11 <= 'z')) ) {s = 37;}
						else if ( ((LA13_11 >= '-' && LA13_11 <= '.')||LA13_11=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_11);
						if ( s>=0 ) return s;
						break;

					case 197 : 
						int LA13_63 = input.LA(1);
						 
						int index13_63 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_63==' ') ) {s = 129;}
						else if ( ((LA13_63 >= '0' && LA13_63 <= '9')||(LA13_63 >= 'A' && LA13_63 <= 'Z')||(LA13_63 >= 'a' && LA13_63 <= 'z')) ) {s = 37;}
						else if ( ((LA13_63 >= '-' && LA13_63 <= '.')||LA13_63=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 130;
						 
						input.seek(index13_63);
						if ( s>=0 ) return s;
						break;

					case 198 : 
						int LA13_73 = input.LA(1);
						 
						int index13_73 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_73 >= '0' && LA13_73 <= '9')||(LA13_73 >= 'A' && LA13_73 <= 'Z')||(LA13_73 >= 'a' && LA13_73 <= 'z')) ) {s = 37;}
						else if ( ((LA13_73 >= '-' && LA13_73 <= '.')||LA13_73=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 145;
						 
						input.seek(index13_73);
						if ( s>=0 ) return s;
						break;

					case 199 : 
						int LA13_18 = input.LA(1);
						 
						int index13_18 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_18=='n') ) {s = 57;}
						else if ( (LA13_18=='p') ) {s = 58;}
						else if ( (LA13_18=='q') ) {s = 59;}
						else if ( ((LA13_18 >= '0' && LA13_18 <= '9')||(LA13_18 >= 'A' && LA13_18 <= 'Z')||(LA13_18 >= 'a' && LA13_18 <= 'm')||LA13_18=='o'||(LA13_18 >= 'r' && LA13_18 <= 'z')) ) {s = 37;}
						else if ( ((LA13_18 >= '-' && LA13_18 <= '.')||LA13_18=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_18);
						if ( s>=0 ) return s;
						break;

					case 200 : 
						int LA13_229 = input.LA(1);
						 
						int index13_229 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_229=='i') ) {s = 283;}
						else if ( ((LA13_229 >= '0' && LA13_229 <= '9')||(LA13_229 >= 'A' && LA13_229 <= 'Z')||(LA13_229 >= 'a' && LA13_229 <= 'h')||(LA13_229 >= 'j' && LA13_229 <= 'z')) ) {s = 37;}
						else if ( ((LA13_229 >= '-' && LA13_229 <= '.')||LA13_229=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 284;
						 
						input.seek(index13_229);
						if ( s>=0 ) return s;
						break;

					case 201 : 
						int LA13_283 = input.LA(1);
						 
						int index13_283 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_283=='d') ) {s = 331;}
						else if ( ((LA13_283 >= '0' && LA13_283 <= '9')||(LA13_283 >= 'A' && LA13_283 <= 'Z')||(LA13_283 >= 'a' && LA13_283 <= 'c')||(LA13_283 >= 'e' && LA13_283 <= 'z')) ) {s = 37;}
						else if ( ((LA13_283 >= '-' && LA13_283 <= '.')||LA13_283=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 332;
						 
						input.seek(index13_283);
						if ( s>=0 ) return s;
						break;

					case 202 : 
						int LA13_331 = input.LA(1);
						 
						int index13_331 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_331=='e') ) {s = 369;}
						else if ( ((LA13_331 >= '0' && LA13_331 <= '9')||(LA13_331 >= 'A' && LA13_331 <= 'Z')||(LA13_331 >= 'a' && LA13_331 <= 'd')||(LA13_331 >= 'f' && LA13_331 <= 'z')) ) {s = 37;}
						else if ( ((LA13_331 >= '-' && LA13_331 <= '.')||LA13_331=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 370;
						 
						input.seek(index13_331);
						if ( s>=0 ) return s;
						break;

					case 203 : 
						int LA13_369 = input.LA(1);
						 
						int index13_369 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_369==' ') ) {s = 403;}
						else if ( ((LA13_369 >= '0' && LA13_369 <= '9')||(LA13_369 >= 'A' && LA13_369 <= 'Z')||(LA13_369 >= 'a' && LA13_369 <= 'z')) ) {s = 37;}
						else if ( ((LA13_369 >= '-' && LA13_369 <= '.')||LA13_369=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 404;
						 
						input.seek(index13_369);
						if ( s>=0 ) return s;
						break;

					case 204 : 
						int LA13_0 = input.LA(1);
						 
						int index13_0 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_0=='a') ) {s = 1;}
						else if ( (LA13_0==')') ) {s = 2;}
						else if ( (LA13_0==']') ) {s = 3;}
						else if ( (LA13_0==',') ) {s = 4;}
						else if ( (LA13_0=='d') ) {s = 5;}
						else if ( (LA13_0=='l') ) {s = 6;}
						else if ( (LA13_0=='m') ) {s = 7;}
						else if ( (LA13_0=='n') ) {s = 8;}
						else if ( (LA13_0=='(') ) {s = 9;}
						else if ( (LA13_0=='[') ) {s = 10;}
						else if ( (LA13_0=='o') ) {s = 11;}
						else if ( (LA13_0=='%') ) {s = 12;}
						else if ( (LA13_0==';') ) {s = 13;}
						else if ( (LA13_0=='t') ) {s = 14;}
						else if ( (LA13_0=='N') ) {s = 15;}
						else if ( (LA13_0=='b') ) {s = 16;}
						else if ( (LA13_0=='c') ) {s = 17;}
						else if ( (LA13_0=='e') ) {s = 18;}
						else if ( (LA13_0=='f') ) {s = 19;}
						else if ( (LA13_0=='g') ) {s = 20;}
						else if ( (LA13_0=='i') ) {s = 21;}
						else if ( (LA13_0=='r') ) {s = 22;}
						else if ( (LA13_0=='s') ) {s = 23;}
						else if ( (LA13_0=='h') ) {s = 24;}
						else if ( (LA13_0=='-') ) {s = 25;}
						else if ( ((LA13_0 >= '0' && LA13_0 <= '9')) ) {s = 26;}
						else if ( (LA13_0=='\"') ) {s = 27;}
						else if ( (LA13_0=='v') ) {s = 28;}
						else if ( ((LA13_0 >= 'A' && LA13_0 <= 'M')||(LA13_0 >= 'O' && LA13_0 <= 'Z')||(LA13_0 >= 'j' && LA13_0 <= 'k')||(LA13_0 >= 'p' && LA13_0 <= 'q')||LA13_0=='u'||(LA13_0 >= 'w' && LA13_0 <= 'z')) ) {s = 29;}
						else if ( (LA13_0==' ') ) {s = 30;}
						else if ( (LA13_0=='_') && ((runtimeOpAhead()))) {s = 31;}
						else if ( ((LA13_0 >= '\t' && LA13_0 <= '\n')||(LA13_0 >= '\f' && LA13_0 <= '\r')) ) {s = 32;}
						else if ( (LA13_0=='/') ) {s = 33;}
						 
						input.seek(index13_0);
						if ( s>=0 ) return s;
						break;

					case 205 : 
						int LA13_150 = input.LA(1);
						 
						int index13_150 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_150 >= '0' && LA13_150 <= '9')||(LA13_150 >= 'A' && LA13_150 <= 'Z')||(LA13_150 >= 'a' && LA13_150 <= 'z')) ) {s = 37;}
						else if ( ((LA13_150 >= '-' && LA13_150 <= '.')||LA13_150=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 217;
						 
						input.seek(index13_150);
						if ( s>=0 ) return s;
						break;

					case 206 : 
						int LA13_44 = input.LA(1);
						 
						int index13_44 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_44=='r') ) {s = 91;}
						else if ( ((LA13_44 >= '0' && LA13_44 <= '9')||(LA13_44 >= 'A' && LA13_44 <= 'Z')||(LA13_44 >= 'a' && LA13_44 <= 'q')||(LA13_44 >= 's' && LA13_44 <= 'z')) ) {s = 37;}
						else if ( ((LA13_44 >= '-' && LA13_44 <= '.')||LA13_44=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 92;
						 
						input.seek(index13_44);
						if ( s>=0 ) return s;
						break;

					case 207 : 
						int LA13_91 = input.LA(1);
						 
						int index13_91 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_91=='e') ) {s = 161;}
						else if ( ((LA13_91 >= '0' && LA13_91 <= '9')||(LA13_91 >= 'A' && LA13_91 <= 'Z')||(LA13_91 >= 'a' && LA13_91 <= 'd')||(LA13_91 >= 'f' && LA13_91 <= 'z')) ) {s = 37;}
						else if ( ((LA13_91 >= '-' && LA13_91 <= '.')||LA13_91=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 162;
						 
						input.seek(index13_91);
						if ( s>=0 ) return s;
						break;

					case 208 : 
						int LA13_161 = input.LA(1);
						 
						int index13_161 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_161==' ') ) {s = 227;}
						else if ( ((LA13_161 >= '0' && LA13_161 <= '9')||(LA13_161 >= 'A' && LA13_161 <= 'Z')||(LA13_161 >= 'a' && LA13_161 <= 'z')) ) {s = 37;}
						else if ( ((LA13_161 >= '-' && LA13_161 <= '.')||LA13_161=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 228;
						 
						input.seek(index13_161);
						if ( s>=0 ) return s;
						break;

					case 209 : 
						int LA13_326 = input.LA(1);
						 
						int index13_326 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_326 >= '0' && LA13_326 <= '9')||(LA13_326 >= 'A' && LA13_326 <= 'Z')||(LA13_326 >= 'a' && LA13_326 <= 'z')) ) {s = 37;}
						else if ( ((LA13_326 >= '-' && LA13_326 <= '.')||LA13_326=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 365;
						 
						input.seek(index13_326);
						if ( s>=0 ) return s;
						break;

					case 210 : 
						int LA13_24 = input.LA(1);
						 
						int index13_24 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_24=='i') ) {s = 69;}
						else if ( ((LA13_24 >= '0' && LA13_24 <= '9')||(LA13_24 >= 'A' && LA13_24 <= 'Z')||(LA13_24 >= 'a' && LA13_24 <= 'h')||(LA13_24 >= 'j' && LA13_24 <= 'z')) ) {s = 37;}
						else if ( ((LA13_24 >= '-' && LA13_24 <= '.')||LA13_24=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_24);
						if ( s>=0 ) return s;
						break;

					case 211 : 
						int LA13_366 = input.LA(1);
						 
						int index13_366 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_366 >= '0' && LA13_366 <= '9')||(LA13_366 >= 'A' && LA13_366 <= 'Z')||(LA13_366 >= 'a' && LA13_366 <= 'z')) ) {s = 37;}
						else if ( ((LA13_366 >= '-' && LA13_366 <= '.')||LA13_366=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 399;
						 
						input.seek(index13_366);
						if ( s>=0 ) return s;
						break;

					case 212 : 
						int LA13_28 = input.LA(1);
						 
						int index13_28 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_28=='o') ) {s = 70;}
						else if ( ((LA13_28 >= '0' && LA13_28 <= '9')||(LA13_28 >= 'A' && LA13_28 <= 'Z')||(LA13_28 >= 'a' && LA13_28 <= 'n')||(LA13_28 >= 'p' && LA13_28 <= 'z')) ) {s = 37;}
						else if ( ((LA13_28 >= '-' && LA13_28 <= '.')||LA13_28=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_28);
						if ( s>=0 ) return s;
						break;

					case 213 : 
						int LA13_8 = input.LA(1);
						 
						int index13_8 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_8=='o') ) {s = 45;}
						else if ( ((LA13_8 >= '0' && LA13_8 <= '9')||(LA13_8 >= 'A' && LA13_8 <= 'Z')||(LA13_8 >= 'a' && LA13_8 <= 'n')||(LA13_8 >= 'p' && LA13_8 <= 'z')) ) {s = 37;}
						else if ( ((LA13_8 >= '-' && LA13_8 <= '.')||LA13_8=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_8);
						if ( s>=0 ) return s;
						break;

					case 214 : 
						int LA13_93 = input.LA(1);
						 
						int index13_93 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_93 >= '0' && LA13_93 <= '9')||(LA13_93 >= 'A' && LA13_93 <= 'Z')||(LA13_93 >= 'a' && LA13_93 <= 'z')) ) {s = 37;}
						else if ( ((LA13_93 >= '-' && LA13_93 <= '.')||LA13_93=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 163;
						 
						input.seek(index13_93);
						if ( s>=0 ) return s;
						break;

					case 215 : 
						int LA13_64 = input.LA(1);
						 
						int index13_64 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_64=='v') ) {s = 131;}
						else if ( ((LA13_64 >= '0' && LA13_64 <= '9')||(LA13_64 >= 'A' && LA13_64 <= 'Z')||(LA13_64 >= 'a' && LA13_64 <= 'u')||(LA13_64 >= 'w' && LA13_64 <= 'z')) ) {s = 37;}
						else if ( ((LA13_64 >= '-' && LA13_64 <= '.')||LA13_64=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 132;
						 
						input.seek(index13_64);
						if ( s>=0 ) return s;
						break;

					case 216 : 
						int LA13_131 = input.LA(1);
						 
						int index13_131 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_131=='e') ) {s = 199;}
						else if ( ((LA13_131 >= '0' && LA13_131 <= '9')||(LA13_131 >= 'A' && LA13_131 <= 'Z')||(LA13_131 >= 'a' && LA13_131 <= 'd')||(LA13_131 >= 'f' && LA13_131 <= 'z')) ) {s = 37;}
						else if ( ((LA13_131 >= '-' && LA13_131 <= '.')||LA13_131=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 200;
						 
						input.seek(index13_131);
						if ( s>=0 ) return s;
						break;

					case 217 : 
						int LA13_199 = input.LA(1);
						 
						int index13_199 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_199=='r') ) {s = 260;}
						else if ( ((LA13_199 >= '0' && LA13_199 <= '9')||(LA13_199 >= 'A' && LA13_199 <= 'Z')||(LA13_199 >= 'a' && LA13_199 <= 'q')||(LA13_199 >= 's' && LA13_199 <= 'z')) ) {s = 37;}
						else if ( ((LA13_199 >= '-' && LA13_199 <= '.')||LA13_199=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 261;
						 
						input.seek(index13_199);
						if ( s>=0 ) return s;
						break;

					case 218 : 
						int LA13_260 = input.LA(1);
						 
						int index13_260 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_260=='s') ) {s = 310;}
						else if ( ((LA13_260 >= '0' && LA13_260 <= '9')||(LA13_260 >= 'A' && LA13_260 <= 'Z')||(LA13_260 >= 'a' && LA13_260 <= 'r')||(LA13_260 >= 't' && LA13_260 <= 'z')) ) {s = 37;}
						else if ( ((LA13_260 >= '-' && LA13_260 <= '.')||LA13_260=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 311;
						 
						input.seek(index13_260);
						if ( s>=0 ) return s;
						break;

					case 219 : 
						int LA13_310 = input.LA(1);
						 
						int index13_310 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_310=='e') ) {s = 352;}
						else if ( ((LA13_310 >= '0' && LA13_310 <= '9')||(LA13_310 >= 'A' && LA13_310 <= 'Z')||(LA13_310 >= 'a' && LA13_310 <= 'd')||(LA13_310 >= 'f' && LA13_310 <= 'z')) ) {s = 37;}
						else if ( ((LA13_310 >= '-' && LA13_310 <= '.')||LA13_310=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 353;
						 
						input.seek(index13_310);
						if ( s>=0 ) return s;
						break;

					case 220 : 
						int LA13_156 = input.LA(1);
						 
						int index13_156 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 222;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_156);
						if ( s>=0 ) return s;
						break;

					case 221 : 
						int LA13_231 = input.LA(1);
						 
						int index13_231 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 222;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_231);
						if ( s>=0 ) return s;
						break;

					case 222 : 
						int LA13_270 = input.LA(1);
						 
						int index13_270 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 222;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_270);
						if ( s>=0 ) return s;
						break;

					case 223 : 
						int LA13_297 = input.LA(1);
						 
						int index13_297 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 222;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_297);
						if ( s>=0 ) return s;
						break;

					case 224 : 
						int LA13_360 = input.LA(1);
						 
						int index13_360 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 31;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 222;}
						else if ( (true) ) {s = 38;}
						 
						input.seek(index13_360);
						if ( s>=0 ) return s;
						break;

					case 225 : 
						int LA13_352 = input.LA(1);
						 
						int index13_352 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_352=='s') ) {s = 387;}
						else if ( ((LA13_352 >= '0' && LA13_352 <= '9')||(LA13_352 >= 'A' && LA13_352 <= 'Z')||(LA13_352 >= 'a' && LA13_352 <= 'r')||(LA13_352 >= 't' && LA13_352 <= 'z')) ) {s = 37;}
						else if ( ((LA13_352 >= '-' && LA13_352 <= '.')||LA13_352=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 388;
						 
						input.seek(index13_352);
						if ( s>=0 ) return s;
						break;

					case 226 : 
						int LA13_387 = input.LA(1);
						 
						int index13_387 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_387==' ') ) {s = 416;}
						else if ( ((LA13_387 >= '0' && LA13_387 <= '9')||(LA13_387 >= 'A' && LA13_387 <= 'Z')||(LA13_387 >= 'a' && LA13_387 <= 'z')) ) {s = 37;}
						else if ( ((LA13_387 >= '-' && LA13_387 <= '.')||LA13_387=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 417;
						 
						input.seek(index13_387);
						if ( s>=0 ) return s;
						break;

					case 227 : 
						int LA13_15 = input.LA(1);
						 
						int index13_15 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_15=='a') ) {s = 52;}
						else if ( ((LA13_15 >= '0' && LA13_15 <= '9')||(LA13_15 >= 'A' && LA13_15 <= 'Z')||(LA13_15 >= 'b' && LA13_15 <= 'z')) ) {s = 37;}
						else if ( ((LA13_15 >= '-' && LA13_15 <= '.')||LA13_15=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_15);
						if ( s>=0 ) return s;
						break;

					case 228 : 
						int LA13_46 = input.LA(1);
						 
						int index13_46 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_46 >= '0' && LA13_46 <= '9')||(LA13_46 >= 'A' && LA13_46 <= 'Z')||(LA13_46 >= 'a' && LA13_46 <= 'z')) ) {s = 37;}
						else if ( ((LA13_46 >= '-' && LA13_46 <= '.')||LA13_46=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 95;
						 
						input.seek(index13_46);
						if ( s>=0 ) return s;
						break;

					case 229 : 
						int LA13_61 = input.LA(1);
						 
						int index13_61 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_61=='e') ) {s = 125;}
						else if ( ((LA13_61 >= '0' && LA13_61 <= '9')||(LA13_61 >= 'A' && LA13_61 <= 'Z')||(LA13_61 >= 'a' && LA13_61 <= 'd')||(LA13_61 >= 'f' && LA13_61 <= 'z')) ) {s = 37;}
						else if ( ((LA13_61 >= '-' && LA13_61 <= '.')||LA13_61=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 126;
						 
						input.seek(index13_61);
						if ( s>=0 ) return s;
						break;

					case 230 : 
						int LA13_125 = input.LA(1);
						 
						int index13_125 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_125=='s') ) {s = 192;}
						else if ( ((LA13_125 >= '0' && LA13_125 <= '9')||(LA13_125 >= 'A' && LA13_125 <= 'Z')||(LA13_125 >= 'a' && LA13_125 <= 'r')||(LA13_125 >= 't' && LA13_125 <= 'z')) ) {s = 37;}
						else if ( ((LA13_125 >= '-' && LA13_125 <= '.')||LA13_125=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 193;
						 
						input.seek(index13_125);
						if ( s>=0 ) return s;
						break;

					case 231 : 
						int LA13_192 = input.LA(1);
						 
						int index13_192 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_192==' ') ) {s = 253;}
						else if ( ((LA13_192 >= '0' && LA13_192 <= '9')||(LA13_192 >= 'A' && LA13_192 <= 'Z')||(LA13_192 >= 'a' && LA13_192 <= 'z')) ) {s = 37;}
						else if ( ((LA13_192 >= '-' && LA13_192 <= '.')||LA13_192=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 254;
						 
						input.seek(index13_192);
						if ( s>=0 ) return s;
						break;

					case 232 : 
						int LA13_65 = input.LA(1);
						 
						int index13_65 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_65=='o') ) {s = 133;}
						else if ( ((LA13_65 >= '0' && LA13_65 <= '9')||(LA13_65 >= 'A' && LA13_65 <= 'Z')||(LA13_65 >= 'a' && LA13_65 <= 'n')||(LA13_65 >= 'p' && LA13_65 <= 'z')) ) {s = 37;}
						else if ( ((LA13_65 >= '-' && LA13_65 <= '.')||LA13_65=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 134;
						 
						input.seek(index13_65);
						if ( s>=0 ) return s;
						break;

					case 233 : 
						int LA13_133 = input.LA(1);
						 
						int index13_133 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_133=='p') ) {s = 201;}
						else if ( ((LA13_133 >= '0' && LA13_133 <= '9')||(LA13_133 >= 'A' && LA13_133 <= 'Z')||(LA13_133 >= 'a' && LA13_133 <= 'o')||(LA13_133 >= 'q' && LA13_133 <= 'z')) ) {s = 37;}
						else if ( ((LA13_133 >= '-' && LA13_133 <= '.')||LA13_133=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 202;
						 
						input.seek(index13_133);
						if ( s>=0 ) return s;
						break;

					case 234 : 
						int LA13_201 = input.LA(1);
						 
						int index13_201 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_201=='e') ) {s = 262;}
						else if ( ((LA13_201 >= '0' && LA13_201 <= '9')||(LA13_201 >= 'A' && LA13_201 <= 'Z')||(LA13_201 >= 'a' && LA13_201 <= 'd')||(LA13_201 >= 'f' && LA13_201 <= 'z')) ) {s = 37;}
						else if ( ((LA13_201 >= '-' && LA13_201 <= '.')||LA13_201=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 263;
						 
						input.seek(index13_201);
						if ( s>=0 ) return s;
						break;

					case 235 : 
						int LA13_262 = input.LA(1);
						 
						int index13_262 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_262==' ') ) {s = 312;}
						else if ( ((LA13_262 >= '0' && LA13_262 <= '9')||(LA13_262 >= 'A' && LA13_262 <= 'Z')||(LA13_262 >= 'a' && LA13_262 <= 'z')) ) {s = 37;}
						else if ( ((LA13_262 >= '-' && LA13_262 <= '.')||LA13_262=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 313;
						 
						input.seek(index13_262);
						if ( s>=0 ) return s;
						break;

					case 236 : 
						int LA13_66 = input.LA(1);
						 
						int index13_66 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_66=='o') ) {s = 135;}
						else if ( ((LA13_66 >= '0' && LA13_66 <= '9')||(LA13_66 >= 'A' && LA13_66 <= 'Z')||(LA13_66 >= 'a' && LA13_66 <= 'n')||(LA13_66 >= 'p' && LA13_66 <= 'z')) ) {s = 37;}
						else if ( ((LA13_66 >= '-' && LA13_66 <= '.')||LA13_66=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 136;
						 
						input.seek(index13_66);
						if ( s>=0 ) return s;
						break;

					case 237 : 
						int LA13_135 = input.LA(1);
						 
						int index13_135 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_135=='o') ) {s = 203;}
						else if ( ((LA13_135 >= '0' && LA13_135 <= '9')||(LA13_135 >= 'A' && LA13_135 <= 'Z')||(LA13_135 >= 'a' && LA13_135 <= 'n')||(LA13_135 >= 'p' && LA13_135 <= 'z')) ) {s = 37;}
						else if ( ((LA13_135 >= '-' && LA13_135 <= '.')||LA13_135=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 204;
						 
						input.seek(index13_135);
						if ( s>=0 ) return s;
						break;

					case 238 : 
						int LA13_203 = input.LA(1);
						 
						int index13_203 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_203=='t') ) {s = 264;}
						else if ( ((LA13_203 >= '0' && LA13_203 <= '9')||(LA13_203 >= 'A' && LA13_203 <= 'Z')||(LA13_203 >= 'a' && LA13_203 <= 's')||(LA13_203 >= 'u' && LA13_203 <= 'z')) ) {s = 37;}
						else if ( ((LA13_203 >= '-' && LA13_203 <= '.')||LA13_203=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 265;
						 
						input.seek(index13_203);
						if ( s>=0 ) return s;
						break;

					case 239 : 
						int LA13_264 = input.LA(1);
						 
						int index13_264 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_264=='h') ) {s = 314;}
						else if ( ((LA13_264 >= '0' && LA13_264 <= '9')||(LA13_264 >= 'A' && LA13_264 <= 'Z')||(LA13_264 >= 'a' && LA13_264 <= 'g')||(LA13_264 >= 'i' && LA13_264 <= 'z')) ) {s = 37;}
						else if ( ((LA13_264 >= '-' && LA13_264 <= '.')||LA13_264=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 315;
						 
						input.seek(index13_264);
						if ( s>=0 ) return s;
						break;

					case 240 : 
						int LA13_314 = input.LA(1);
						 
						int index13_314 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_314=='e') ) {s = 354;}
						else if ( ((LA13_314 >= '0' && LA13_314 <= '9')||(LA13_314 >= 'A' && LA13_314 <= 'Z')||(LA13_314 >= 'a' && LA13_314 <= 'd')||(LA13_314 >= 'f' && LA13_314 <= 'z')) ) {s = 37;}
						else if ( ((LA13_314 >= '-' && LA13_314 <= '.')||LA13_314=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 355;
						 
						input.seek(index13_314);
						if ( s>=0 ) return s;
						break;

					case 241 : 
						int LA13_354 = input.LA(1);
						 
						int index13_354 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_354=='d') ) {s = 389;}
						else if ( ((LA13_354 >= '0' && LA13_354 <= '9')||(LA13_354 >= 'A' && LA13_354 <= 'Z')||(LA13_354 >= 'a' && LA13_354 <= 'c')||(LA13_354 >= 'e' && LA13_354 <= 'z')) ) {s = 37;}
						else if ( ((LA13_354 >= '-' && LA13_354 <= '.')||LA13_354=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 390;
						 
						input.seek(index13_354);
						if ( s>=0 ) return s;
						break;

					case 242 : 
						int LA13_37 = input.LA(1);
						 
						int index13_37 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_37 >= '0' && LA13_37 <= '9')||(LA13_37 >= 'A' && LA13_37 <= 'Z')||(LA13_37 >= 'a' && LA13_37 <= 'z')) ) {s = 37;}
						else if ( ((LA13_37 >= '-' && LA13_37 <= '.')||LA13_37=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 79;
						 
						input.seek(index13_37);
						if ( s>=0 ) return s;
						break;

					case 243 : 
						int LA13_107 = input.LA(1);
						 
						int index13_107 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_107 >= '0' && LA13_107 <= '9')||(LA13_107 >= 'A' && LA13_107 <= 'Z')||(LA13_107 >= 'a' && LA13_107 <= 'z')) ) {s = 37;}
						else if ( ((LA13_107 >= '-' && LA13_107 <= '.')||LA13_107=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 176;
						 
						input.seek(index13_107);
						if ( s>=0 ) return s;
						break;

					case 244 : 
						int LA13_67 = input.LA(1);
						 
						int index13_67 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_67=='a') ) {s = 137;}
						else if ( ((LA13_67 >= '0' && LA13_67 <= '9')||(LA13_67 >= 'A' && LA13_67 <= 'Z')||(LA13_67 >= 'b' && LA13_67 <= 'z')) ) {s = 37;}
						else if ( ((LA13_67 >= '-' && LA13_67 <= '.')||LA13_67=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 138;
						 
						input.seek(index13_67);
						if ( s>=0 ) return s;
						break;

					case 245 : 
						int LA13_137 = input.LA(1);
						 
						int index13_137 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_137=='n') ) {s = 205;}
						else if ( ((LA13_137 >= '0' && LA13_137 <= '9')||(LA13_137 >= 'A' && LA13_137 <= 'Z')||(LA13_137 >= 'a' && LA13_137 <= 'm')||(LA13_137 >= 'o' && LA13_137 <= 'z')) ) {s = 37;}
						else if ( ((LA13_137 >= '-' && LA13_137 <= '.')||LA13_137=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 206;
						 
						input.seek(index13_137);
						if ( s>=0 ) return s;
						break;

					case 246 : 
						int LA13_205 = input.LA(1);
						 
						int index13_205 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_205=='n') ) {s = 266;}
						else if ( ((LA13_205 >= '0' && LA13_205 <= '9')||(LA13_205 >= 'A' && LA13_205 <= 'Z')||(LA13_205 >= 'a' && LA13_205 <= 'm')||(LA13_205 >= 'o' && LA13_205 <= 'z')) ) {s = 37;}
						else if ( ((LA13_205 >= '-' && LA13_205 <= '.')||LA13_205=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 267;
						 
						input.seek(index13_205);
						if ( s>=0 ) return s;
						break;

					case 247 : 
						int LA13_266 = input.LA(1);
						 
						int index13_266 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_266=='i') ) {s = 316;}
						else if ( ((LA13_266 >= '0' && LA13_266 <= '9')||(LA13_266 >= 'A' && LA13_266 <= 'Z')||(LA13_266 >= 'a' && LA13_266 <= 'h')||(LA13_266 >= 'j' && LA13_266 <= 'z')) ) {s = 37;}
						else if ( ((LA13_266 >= '-' && LA13_266 <= '.')||LA13_266=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 317;
						 
						input.seek(index13_266);
						if ( s>=0 ) return s;
						break;

					case 248 : 
						int LA13_316 = input.LA(1);
						 
						int index13_316 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_316=='n') ) {s = 356;}
						else if ( ((LA13_316 >= '0' && LA13_316 <= '9')||(LA13_316 >= 'A' && LA13_316 <= 'Z')||(LA13_316 >= 'a' && LA13_316 <= 'm')||(LA13_316 >= 'o' && LA13_316 <= 'z')) ) {s = 37;}
						else if ( ((LA13_316 >= '-' && LA13_316 <= '.')||LA13_316=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 357;
						 
						input.seek(index13_316);
						if ( s>=0 ) return s;
						break;

					case 249 : 
						int LA13_356 = input.LA(1);
						 
						int index13_356 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_356=='g') ) {s = 391;}
						else if ( ((LA13_356 >= '0' && LA13_356 <= '9')||(LA13_356 >= 'A' && LA13_356 <= 'Z')||(LA13_356 >= 'a' && LA13_356 <= 'f')||(LA13_356 >= 'h' && LA13_356 <= 'z')) ) {s = 37;}
						else if ( ((LA13_356 >= '-' && LA13_356 <= '.')||LA13_356=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 392;
						 
						input.seek(index13_356);
						if ( s>=0 ) return s;
						break;

					case 250 : 
						int LA13_68 = input.LA(1);
						 
						int index13_68 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_68=='a') ) {s = 139;}
						else if ( ((LA13_68 >= '0' && LA13_68 <= '9')||(LA13_68 >= 'A' && LA13_68 <= 'Z')||(LA13_68 >= 'b' && LA13_68 <= 'z')) ) {s = 37;}
						else if ( ((LA13_68 >= '-' && LA13_68 <= '.')||LA13_68=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 140;
						 
						input.seek(index13_68);
						if ( s>=0 ) return s;
						break;

					case 251 : 
						int LA13_139 = input.LA(1);
						 
						int index13_139 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_139=='r') ) {s = 207;}
						else if ( ((LA13_139 >= '0' && LA13_139 <= '9')||(LA13_139 >= 'A' && LA13_139 <= 'Z')||(LA13_139 >= 'a' && LA13_139 <= 'q')||(LA13_139 >= 's' && LA13_139 <= 'z')) ) {s = 37;}
						else if ( ((LA13_139 >= '-' && LA13_139 <= '.')||LA13_139=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 208;
						 
						input.seek(index13_139);
						if ( s>=0 ) return s;
						break;

					case 252 : 
						int LA13_207 = input.LA(1);
						 
						int index13_207 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_207=='t') ) {s = 268;}
						else if ( ((LA13_207 >= '0' && LA13_207 <= '9')||(LA13_207 >= 'A' && LA13_207 <= 'Z')||(LA13_207 >= 'a' && LA13_207 <= 's')||(LA13_207 >= 'u' && LA13_207 <= 'z')) ) {s = 37;}
						else if ( ((LA13_207 >= '-' && LA13_207 <= '.')||LA13_207=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 269;
						 
						input.seek(index13_207);
						if ( s>=0 ) return s;
						break;

					case 253 : 
						int LA13_268 = input.LA(1);
						 
						int index13_268 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_268=='i') ) {s = 318;}
						else if ( ((LA13_268 >= '0' && LA13_268 <= '9')||(LA13_268 >= 'A' && LA13_268 <= 'Z')||(LA13_268 >= 'a' && LA13_268 <= 'h')||(LA13_268 >= 'j' && LA13_268 <= 'z')) ) {s = 37;}
						else if ( ((LA13_268 >= '-' && LA13_268 <= '.')||LA13_268=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 319;
						 
						input.seek(index13_268);
						if ( s>=0 ) return s;
						break;

					case 254 : 
						int LA13_318 = input.LA(1);
						 
						int index13_318 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_318=='n') ) {s = 358;}
						else if ( ((LA13_318 >= '0' && LA13_318 <= '9')||(LA13_318 >= 'A' && LA13_318 <= 'Z')||(LA13_318 >= 'a' && LA13_318 <= 'm')||(LA13_318 >= 'o' && LA13_318 <= 'z')) ) {s = 37;}
						else if ( ((LA13_318 >= '-' && LA13_318 <= '.')||LA13_318=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 359;
						 
						input.seek(index13_318);
						if ( s>=0 ) return s;
						break;

					case 255 : 
						int LA13_358 = input.LA(1);
						 
						int index13_358 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_358=='g') ) {s = 393;}
						else if ( ((LA13_358 >= '0' && LA13_358 <= '9')||(LA13_358 >= 'A' && LA13_358 <= 'Z')||(LA13_358 >= 'a' && LA13_358 <= 'f')||(LA13_358 >= 'h' && LA13_358 <= 'z')) ) {s = 37;}
						else if ( ((LA13_358 >= '-' && LA13_358 <= '.')||LA13_358=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 394;
						 
						input.seek(index13_358);
						if ( s>=0 ) return s;
						break;

					case 256 : 
						int LA13_393 = input.LA(1);
						 
						int index13_393 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_393==' ') ) {s = 420;}
						else if ( ((LA13_393 >= '0' && LA13_393 <= '9')||(LA13_393 >= 'A' && LA13_393 <= 'Z')||(LA13_393 >= 'a' && LA13_393 <= 'z')) ) {s = 37;}
						else if ( ((LA13_393 >= '-' && LA13_393 <= '.')||LA13_393=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 421;
						 
						input.seek(index13_393);
						if ( s>=0 ) return s;
						break;

					case 257 : 
						int LA13_448 = input.LA(1);
						 
						int index13_448 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_448 >= '0' && LA13_448 <= '9')||(LA13_448 >= 'A' && LA13_448 <= 'Z')||(LA13_448 >= 'a' && LA13_448 <= 'z')) ) {s = 37;}
						else if ( ((LA13_448 >= '-' && LA13_448 <= '.')||LA13_448=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 458;
						 
						input.seek(index13_448);
						if ( s>=0 ) return s;
						break;

					case 258 : 
						int LA13_50 = input.LA(1);
						 
						int index13_50 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_50=='l') ) {s = 103;}
						else if ( ((LA13_50 >= '0' && LA13_50 <= '9')||(LA13_50 >= 'A' && LA13_50 <= 'Z')||(LA13_50 >= 'a' && LA13_50 <= 'k')||(LA13_50 >= 'm' && LA13_50 <= 'z')) ) {s = 37;}
						else if ( ((LA13_50 >= '-' && LA13_50 <= '.')||LA13_50=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 104;
						 
						input.seek(index13_50);
						if ( s>=0 ) return s;
						break;

					case 259 : 
						int LA13_103 = input.LA(1);
						 
						int index13_103 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_103=='e') ) {s = 172;}
						else if ( ((LA13_103 >= '0' && LA13_103 <= '9')||(LA13_103 >= 'A' && LA13_103 <= 'Z')||(LA13_103 >= 'a' && LA13_103 <= 'd')||(LA13_103 >= 'f' && LA13_103 <= 'z')) ) {s = 37;}
						else if ( ((LA13_103 >= '-' && LA13_103 <= '.')||LA13_103=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 173;
						 
						input.seek(index13_103);
						if ( s>=0 ) return s;
						break;

					case 260 : 
						int LA13_172 = input.LA(1);
						 
						int index13_172 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_172=='r') ) {s = 236;}
						else if ( ((LA13_172 >= '0' && LA13_172 <= '9')||(LA13_172 >= 'A' && LA13_172 <= 'Z')||(LA13_172 >= 'a' && LA13_172 <= 'q')||(LA13_172 >= 's' && LA13_172 <= 'z')) ) {s = 37;}
						else if ( ((LA13_172 >= '-' && LA13_172 <= '.')||LA13_172=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 237;
						 
						input.seek(index13_172);
						if ( s>=0 ) return s;
						break;

					case 261 : 
						int LA13_236 = input.LA(1);
						 
						int index13_236 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_236=='a') ) {s = 289;}
						else if ( ((LA13_236 >= '0' && LA13_236 <= '9')||(LA13_236 >= 'A' && LA13_236 <= 'Z')||(LA13_236 >= 'b' && LA13_236 <= 'z')) ) {s = 37;}
						else if ( ((LA13_236 >= '-' && LA13_236 <= '.')||LA13_236=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 290;
						 
						input.seek(index13_236);
						if ( s>=0 ) return s;
						break;

					case 262 : 
						int LA13_289 = input.LA(1);
						 
						int index13_289 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_289=='n') ) {s = 335;}
						else if ( ((LA13_289 >= '0' && LA13_289 <= '9')||(LA13_289 >= 'A' && LA13_289 <= 'Z')||(LA13_289 >= 'a' && LA13_289 <= 'm')||(LA13_289 >= 'o' && LA13_289 <= 'z')) ) {s = 37;}
						else if ( ((LA13_289 >= '-' && LA13_289 <= '.')||LA13_289=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 336;
						 
						input.seek(index13_289);
						if ( s>=0 ) return s;
						break;

					case 263 : 
						int LA13_335 = input.LA(1);
						 
						int index13_335 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_335=='c') ) {s = 375;}
						else if ( ((LA13_335 >= '0' && LA13_335 <= '9')||(LA13_335 >= 'A' && LA13_335 <= 'Z')||(LA13_335 >= 'a' && LA13_335 <= 'b')||(LA13_335 >= 'd' && LA13_335 <= 'z')) ) {s = 37;}
						else if ( ((LA13_335 >= '-' && LA13_335 <= '.')||LA13_335=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 376;
						 
						input.seek(index13_335);
						if ( s>=0 ) return s;
						break;

					case 264 : 
						int LA13_375 = input.LA(1);
						 
						int index13_375 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_375=='e') ) {s = 407;}
						else if ( ((LA13_375 >= '0' && LA13_375 <= '9')||(LA13_375 >= 'A' && LA13_375 <= 'Z')||(LA13_375 >= 'a' && LA13_375 <= 'd')||(LA13_375 >= 'f' && LA13_375 <= 'z')) ) {s = 37;}
						else if ( ((LA13_375 >= '-' && LA13_375 <= '.')||LA13_375=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 408;
						 
						input.seek(index13_375);
						if ( s>=0 ) return s;
						break;

					case 265 : 
						int LA13_337 = input.LA(1);
						 
						int index13_337 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_337 >= '0' && LA13_337 <= '9')||(LA13_337 >= 'A' && LA13_337 <= 'Z')||(LA13_337 >= 'a' && LA13_337 <= 'z')) ) {s = 37;}
						else if ( ((LA13_337 >= '-' && LA13_337 <= '.')||LA13_337=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 377;
						 
						input.seek(index13_337);
						if ( s>=0 ) return s;
						break;

					case 266 : 
						int LA13_6 = input.LA(1);
						 
						int index13_6 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_6=='e') ) {s = 41;}
						else if ( (LA13_6=='o') ) {s = 42;}
						else if ( ((LA13_6 >= '0' && LA13_6 <= '9')||(LA13_6 >= 'A' && LA13_6 <= 'Z')||(LA13_6 >= 'a' && LA13_6 <= 'd')||(LA13_6 >= 'f' && LA13_6 <= 'n')||(LA13_6 >= 'p' && LA13_6 <= 'z')) ) {s = 37;}
						else if ( ((LA13_6 >= '-' && LA13_6 <= '.')||LA13_6=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_6);
						if ( s>=0 ) return s;
						break;

					case 267 : 
						int LA13_339 = input.LA(1);
						 
						int index13_339 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_339 >= '0' && LA13_339 <= '9')||(LA13_339 >= 'A' && LA13_339 <= 'Z')||(LA13_339 >= 'a' && LA13_339 <= 'z')) ) {s = 37;}
						else if ( ((LA13_339 >= '-' && LA13_339 <= '.')||LA13_339=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 378;
						 
						input.seek(index13_339);
						if ( s>=0 ) return s;
						break;

					case 268 : 
						int LA13_389 = input.LA(1);
						 
						int index13_389 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_389 >= '0' && LA13_389 <= '9')||(LA13_389 >= 'A' && LA13_389 <= 'Z')||(LA13_389 >= 'a' && LA13_389 <= 'z')) ) {s = 37;}
						else if ( ((LA13_389 >= '-' && LA13_389 <= '.')||LA13_389=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 418;
						 
						input.seek(index13_389);
						if ( s>=0 ) return s;
						break;

					case 269 : 
						int LA13_391 = input.LA(1);
						 
						int index13_391 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_391 >= '0' && LA13_391 <= '9')||(LA13_391 >= 'A' && LA13_391 <= 'Z')||(LA13_391 >= 'a' && LA13_391 <= 'z')) ) {s = 37;}
						else if ( ((LA13_391 >= '-' && LA13_391 <= '.')||LA13_391=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 419;
						 
						input.seek(index13_391);
						if ( s>=0 ) return s;
						break;

					case 270 : 
						int LA13_51 = input.LA(1);
						 
						int index13_51 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_51=='p') ) {s = 105;}
						else if ( ((LA13_51 >= '0' && LA13_51 <= '9')||(LA13_51 >= 'A' && LA13_51 <= 'Z')||(LA13_51 >= 'a' && LA13_51 <= 'o')||(LA13_51 >= 'q' && LA13_51 <= 'z')) ) {s = 37;}
						else if ( ((LA13_51 >= '-' && LA13_51 <= '.')||LA13_51=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 106;
						 
						input.seek(index13_51);
						if ( s>=0 ) return s;
						break;

					case 271 : 
						int LA13_105 = input.LA(1);
						 
						int index13_105 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_105=='e') ) {s = 174;}
						else if ( ((LA13_105 >= '0' && LA13_105 <= '9')||(LA13_105 >= 'A' && LA13_105 <= 'Z')||(LA13_105 >= 'a' && LA13_105 <= 'd')||(LA13_105 >= 'f' && LA13_105 <= 'z')) ) {s = 37;}
						else if ( ((LA13_105 >= '-' && LA13_105 <= '.')||LA13_105=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 175;
						 
						input.seek(index13_105);
						if ( s>=0 ) return s;
						break;

					case 272 : 
						int LA13_407 = input.LA(1);
						 
						int index13_407 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_407 >= '0' && LA13_407 <= '9')||(LA13_407 >= 'A' && LA13_407 <= 'Z')||(LA13_407 >= 'a' && LA13_407 <= 'z')) ) {s = 37;}
						else if ( ((LA13_407 >= '-' && LA13_407 <= '.')||LA13_407=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 430;
						 
						input.seek(index13_407);
						if ( s>=0 ) return s;
						break;

					case 273 : 
						int LA13_34 = input.LA(1);
						 
						int index13_34 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_34=='d') ) {s = 73;}
						else if ( ((LA13_34 >= '0' && LA13_34 <= '9')||(LA13_34 >= 'A' && LA13_34 <= 'Z')||(LA13_34 >= 'a' && LA13_34 <= 'c')||(LA13_34 >= 'e' && LA13_34 <= 'z')) ) {s = 37;}
						else if ( ((LA13_34 >= '-' && LA13_34 <= '.')||LA13_34=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 74;
						 
						input.seek(index13_34);
						if ( s>=0 ) return s;
						break;

					case 274 : 
						int LA13_17 = input.LA(1);
						 
						int index13_17 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_17=='r') ) {s = 55;}
						else if ( (LA13_17=='l') ) {s = 56;}
						else if ( ((LA13_17 >= '0' && LA13_17 <= '9')||(LA13_17 >= 'A' && LA13_17 <= 'Z')||(LA13_17 >= 'a' && LA13_17 <= 'k')||(LA13_17 >= 'm' && LA13_17 <= 'q')||(LA13_17 >= 's' && LA13_17 <= 'z')) ) {s = 37;}
						else if ( ((LA13_17 >= '-' && LA13_17 <= '.')||LA13_17=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_17);
						if ( s>=0 ) return s;
						break;

					case 275 : 
						int LA13_39 = input.LA(1);
						 
						int index13_39 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_39=='y') ) {s = 80;}
						else if ( ((LA13_39 >= '0' && LA13_39 <= '9')||(LA13_39 >= 'A' && LA13_39 <= 'Z')||(LA13_39 >= 'a' && LA13_39 <= 'x')||LA13_39=='z') ) {s = 37;}
						else if ( ((LA13_39 >= '-' && LA13_39 <= '.')||LA13_39=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 81;
						 
						input.seek(index13_39);
						if ( s>=0 ) return s;
						break;

					case 276 : 
						int LA13_80 = input.LA(1);
						 
						int index13_80 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_80=='s') ) {s = 150;}
						else if ( ((LA13_80 >= '0' && LA13_80 <= '9')||(LA13_80 >= 'A' && LA13_80 <= 'Z')||(LA13_80 >= 'a' && LA13_80 <= 'r')||(LA13_80 >= 't' && LA13_80 <= 'z')) ) {s = 37;}
						else if ( ((LA13_80 >= '-' && LA13_80 <= '.')||LA13_80=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 151;
						 
						input.seek(index13_80);
						if ( s>=0 ) return s;
						break;

					case 277 : 
						int LA13_48 = input.LA(1);
						 
						int index13_48 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_48=='e') ) {s = 98;}
						else if ( ((LA13_48 >= '0' && LA13_48 <= '9')||(LA13_48 >= 'A' && LA13_48 <= 'Z')||(LA13_48 >= 'a' && LA13_48 <= 'd')||(LA13_48 >= 'f' && LA13_48 <= 'z')) ) {s = 37;}
						else if ( ((LA13_48 >= '-' && LA13_48 <= '.')||LA13_48=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 99;
						 
						input.seek(index13_48);
						if ( s>=0 ) return s;
						break;

					case 278 : 
						int LA13_98 = input.LA(1);
						 
						int index13_98 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_98=='n') ) {s = 166;}
						else if ( ((LA13_98 >= '0' && LA13_98 <= '9')||(LA13_98 >= 'A' && LA13_98 <= 'Z')||(LA13_98 >= 'a' && LA13_98 <= 'm')||(LA13_98 >= 'o' && LA13_98 <= 'z')) ) {s = 37;}
						else if ( ((LA13_98 >= '-' && LA13_98 <= '.')||LA13_98=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 167;
						 
						input.seek(index13_98);
						if ( s>=0 ) return s;
						break;

					case 279 : 
						int LA13_41 = input.LA(1);
						 
						int index13_41 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_41=='n') ) {s = 84;}
						else if ( ((LA13_41 >= '0' && LA13_41 <= '9')||(LA13_41 >= 'A' && LA13_41 <= 'Z')||(LA13_41 >= 'a' && LA13_41 <= 'm')||(LA13_41 >= 'o' && LA13_41 <= 'z')) ) {s = 37;}
						else if ( ((LA13_41 >= '-' && LA13_41 <= '.')||LA13_41=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 85;
						 
						input.seek(index13_41);
						if ( s>=0 ) return s;
						break;

					case 280 : 
						int LA13_56 = input.LA(1);
						 
						int index13_56 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_56=='o') ) {s = 115;}
						else if ( ((LA13_56 >= '0' && LA13_56 <= '9')||(LA13_56 >= 'A' && LA13_56 <= 'Z')||(LA13_56 >= 'a' && LA13_56 <= 'n')||(LA13_56 >= 'p' && LA13_56 <= 'z')) ) {s = 37;}
						else if ( ((LA13_56 >= '-' && LA13_56 <= '.')||LA13_56=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 116;
						 
						input.seek(index13_56);
						if ( s>=0 ) return s;
						break;

					case 281 : 
						int LA13_84 = input.LA(1);
						 
						int index13_84 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_84=='i') ) {s = 154;}
						else if ( ((LA13_84 >= '0' && LA13_84 <= '9')||(LA13_84 >= 'A' && LA13_84 <= 'Z')||(LA13_84 >= 'a' && LA13_84 <= 'h')||(LA13_84 >= 'j' && LA13_84 <= 'z')) ) {s = 37;}
						else if ( ((LA13_84 >= '-' && LA13_84 <= '.')||LA13_84=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 155;
						 
						input.seek(index13_84);
						if ( s>=0 ) return s;
						break;

					case 282 : 
						int LA13_115 = input.LA(1);
						 
						int index13_115 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_115=='s') ) {s = 183;}
						else if ( ((LA13_115 >= '0' && LA13_115 <= '9')||(LA13_115 >= 'A' && LA13_115 <= 'Z')||(LA13_115 >= 'a' && LA13_115 <= 'r')||(LA13_115 >= 't' && LA13_115 <= 'z')) ) {s = 37;}
						else if ( ((LA13_115 >= '-' && LA13_115 <= '.')||LA13_115=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 184;
						 
						input.seek(index13_115);
						if ( s>=0 ) return s;
						break;

					case 283 : 
						int LA13_154 = input.LA(1);
						 
						int index13_154 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_154=='e') ) {s = 220;}
						else if ( ((LA13_154 >= '0' && LA13_154 <= '9')||(LA13_154 >= 'A' && LA13_154 <= 'Z')||(LA13_154 >= 'a' && LA13_154 <= 'd')||(LA13_154 >= 'f' && LA13_154 <= 'z')) ) {s = 37;}
						else if ( ((LA13_154 >= '-' && LA13_154 <= '.')||LA13_154=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 221;
						 
						input.seek(index13_154);
						if ( s>=0 ) return s;
						break;

					case 284 : 
						int LA13_183 = input.LA(1);
						 
						int index13_183 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_183=='e') ) {s = 245;}
						else if ( ((LA13_183 >= '0' && LA13_183 <= '9')||(LA13_183 >= 'A' && LA13_183 <= 'Z')||(LA13_183 >= 'a' && LA13_183 <= 'd')||(LA13_183 >= 'f' && LA13_183 <= 'z')) ) {s = 37;}
						else if ( ((LA13_183 >= '-' && LA13_183 <= '.')||LA13_183=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 246;
						 
						input.seek(index13_183);
						if ( s>=0 ) return s;
						break;

					case 285 : 
						int LA13_220 = input.LA(1);
						 
						int index13_220 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_220=='n') ) {s = 277;}
						else if ( ((LA13_220 >= '0' && LA13_220 <= '9')||(LA13_220 >= 'A' && LA13_220 <= 'Z')||(LA13_220 >= 'a' && LA13_220 <= 'm')||(LA13_220 >= 'o' && LA13_220 <= 'z')) ) {s = 37;}
						else if ( ((LA13_220 >= '-' && LA13_220 <= '.')||LA13_220=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 278;
						 
						input.seek(index13_220);
						if ( s>=0 ) return s;
						break;

					case 286 : 
						int LA13_277 = input.LA(1);
						 
						int index13_277 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_277=='t') ) {s = 326;}
						else if ( ((LA13_277 >= '0' && LA13_277 <= '9')||(LA13_277 >= 'A' && LA13_277 <= 'Z')||(LA13_277 >= 'a' && LA13_277 <= 's')||(LA13_277 >= 'u' && LA13_277 <= 'z')) ) {s = 37;}
						else if ( ((LA13_277 >= '-' && LA13_277 <= '.')||LA13_277=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 327;
						 
						input.seek(index13_277);
						if ( s>=0 ) return s;
						break;

					case 287 : 
						int LA13_69 = input.LA(1);
						 
						int index13_69 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_69=='g') ) {s = 141;}
						else if ( ((LA13_69 >= '0' && LA13_69 <= '9')||(LA13_69 >= 'A' && LA13_69 <= 'Z')||(LA13_69 >= 'a' && LA13_69 <= 'f')||(LA13_69 >= 'h' && LA13_69 <= 'z')) ) {s = 37;}
						else if ( ((LA13_69 >= '-' && LA13_69 <= '.')||LA13_69=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 142;
						 
						input.seek(index13_69);
						if ( s>=0 ) return s;
						break;

					case 288 : 
						int LA13_141 = input.LA(1);
						 
						int index13_141 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_141=='h') ) {s = 209;}
						else if ( ((LA13_141 >= '0' && LA13_141 <= '9')||(LA13_141 >= 'A' && LA13_141 <= 'Z')||(LA13_141 >= 'a' && LA13_141 <= 'g')||(LA13_141 >= 'i' && LA13_141 <= 'z')) ) {s = 37;}
						else if ( ((LA13_141 >= '-' && LA13_141 <= '.')||LA13_141=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 210;
						 
						input.seek(index13_141);
						if ( s>=0 ) return s;
						break;

					case 289 : 
						int LA13_19 = input.LA(1);
						 
						int index13_19 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_19=='o') ) {s = 60;}
						else if ( ((LA13_19 >= '0' && LA13_19 <= '9')||(LA13_19 >= 'A' && LA13_19 <= 'Z')||(LA13_19 >= 'a' && LA13_19 <= 'n')||(LA13_19 >= 'p' && LA13_19 <= 'z')) ) {s = 37;}
						else if ( ((LA13_19 >= '-' && LA13_19 <= '.')||LA13_19=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_19);
						if ( s>=0 ) return s;
						break;

					case 290 : 
						int LA13_42 = input.LA(1);
						 
						int index13_42 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_42=='w') ) {s = 86;}
						else if ( ((LA13_42 >= '0' && LA13_42 <= '9')||(LA13_42 >= 'A' && LA13_42 <= 'Z')||(LA13_42 >= 'a' && LA13_42 <= 'v')||(LA13_42 >= 'x' && LA13_42 <= 'z')) ) {s = 37;}
						else if ( ((LA13_42 >= '-' && LA13_42 <= '.')||LA13_42=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 87;
						 
						input.seek(index13_42);
						if ( s>=0 ) return s;
						break;

					case 291 : 
						int LA13_88 = input.LA(1);
						 
						int index13_88 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_88=='c') ) {s = 157;}
						else if ( ((LA13_88 >= '0' && LA13_88 <= '9')||(LA13_88 >= 'A' && LA13_88 <= 'Z')||(LA13_88 >= 'a' && LA13_88 <= 'b')||(LA13_88 >= 'd' && LA13_88 <= 'z')) ) {s = 37;}
						else if ( ((LA13_88 >= '-' && LA13_88 <= '.')||LA13_88=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 158;
						 
						input.seek(index13_88);
						if ( s>=0 ) return s;
						break;

					case 292 : 
						int LA13_157 = input.LA(1);
						 
						int index13_157 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_157=='h') ) {s = 223;}
						else if ( ((LA13_157 >= '0' && LA13_157 <= '9')||(LA13_157 >= 'A' && LA13_157 <= 'Z')||(LA13_157 >= 'a' && LA13_157 <= 'g')||(LA13_157 >= 'i' && LA13_157 <= 'z')) ) {s = 37;}
						else if ( ((LA13_157 >= '-' && LA13_157 <= '.')||LA13_157=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 224;
						 
						input.seek(index13_157);
						if ( s>=0 ) return s;
						break;

					case 293 : 
						int LA13_223 = input.LA(1);
						 
						int index13_223 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_223=='i') ) {s = 279;}
						else if ( ((LA13_223 >= '0' && LA13_223 <= '9')||(LA13_223 >= 'A' && LA13_223 <= 'Z')||(LA13_223 >= 'a' && LA13_223 <= 'h')||(LA13_223 >= 'j' && LA13_223 <= 'z')) ) {s = 37;}
						else if ( ((LA13_223 >= '-' && LA13_223 <= '.')||LA13_223=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 280;
						 
						input.seek(index13_223);
						if ( s>=0 ) return s;
						break;

					case 294 : 
						int LA13_279 = input.LA(1);
						 
						int index13_279 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_279=='n') ) {s = 328;}
						else if ( ((LA13_279 >= '0' && LA13_279 <= '9')||(LA13_279 >= 'A' && LA13_279 <= 'Z')||(LA13_279 >= 'a' && LA13_279 <= 'm')||(LA13_279 >= 'o' && LA13_279 <= 'z')) ) {s = 37;}
						else if ( ((LA13_279 >= '-' && LA13_279 <= '.')||LA13_279=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 329;
						 
						input.seek(index13_279);
						if ( s>=0 ) return s;
						break;

					case 295 : 
						int LA13_70 = input.LA(1);
						 
						int index13_70 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_70=='l') ) {s = 143;}
						else if ( ((LA13_70 >= '0' && LA13_70 <= '9')||(LA13_70 >= 'A' && LA13_70 <= 'Z')||(LA13_70 >= 'a' && LA13_70 <= 'k')||(LA13_70 >= 'm' && LA13_70 <= 'z')) ) {s = 37;}
						else if ( ((LA13_70 >= '-' && LA13_70 <= '.')||LA13_70=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 144;
						 
						input.seek(index13_70);
						if ( s>=0 ) return s;
						break;

					case 296 : 
						int LA13_328 = input.LA(1);
						 
						int index13_328 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_328=='g') ) {s = 366;}
						else if ( ((LA13_328 >= '0' && LA13_328 <= '9')||(LA13_328 >= 'A' && LA13_328 <= 'Z')||(LA13_328 >= 'a' && LA13_328 <= 'f')||(LA13_328 >= 'h' && LA13_328 <= 'z')) ) {s = 37;}
						else if ( ((LA13_328 >= '-' && LA13_328 <= '.')||LA13_328=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 367;
						 
						input.seek(index13_328);
						if ( s>=0 ) return s;
						break;

					case 297 : 
						int LA13_143 = input.LA(1);
						 
						int index13_143 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_143=='u') ) {s = 211;}
						else if ( ((LA13_143 >= '0' && LA13_143 <= '9')||(LA13_143 >= 'A' && LA13_143 <= 'Z')||(LA13_143 >= 'a' && LA13_143 <= 't')||(LA13_143 >= 'v' && LA13_143 <= 'z')) ) {s = 37;}
						else if ( ((LA13_143 >= '-' && LA13_143 <= '.')||LA13_143=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 212;
						 
						input.seek(index13_143);
						if ( s>=0 ) return s;
						break;

					case 298 : 
						int LA13_211 = input.LA(1);
						 
						int index13_211 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_211=='m') ) {s = 271;}
						else if ( ((LA13_211 >= '0' && LA13_211 <= '9')||(LA13_211 >= 'A' && LA13_211 <= 'Z')||(LA13_211 >= 'a' && LA13_211 <= 'l')||(LA13_211 >= 'n' && LA13_211 <= 'z')) ) {s = 37;}
						else if ( ((LA13_211 >= '-' && LA13_211 <= '.')||LA13_211=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 272;
						 
						input.seek(index13_211);
						if ( s>=0 ) return s;
						break;

					case 299 : 
						int LA13_271 = input.LA(1);
						 
						int index13_271 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_271=='e') ) {s = 320;}
						else if ( ((LA13_271 >= '0' && LA13_271 <= '9')||(LA13_271 >= 'A' && LA13_271 <= 'Z')||(LA13_271 >= 'a' && LA13_271 <= 'd')||(LA13_271 >= 'f' && LA13_271 <= 'z')) ) {s = 37;}
						else if ( ((LA13_271 >= '-' && LA13_271 <= '.')||LA13_271=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 321;
						 
						input.seek(index13_271);
						if ( s>=0 ) return s;
						break;

					case 300 : 
						int LA13_45 = input.LA(1);
						 
						int index13_45 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_45=='t') ) {s = 93;}
						else if ( ((LA13_45 >= '0' && LA13_45 <= '9')||(LA13_45 >= 'A' && LA13_45 <= 'Z')||(LA13_45 >= 'a' && LA13_45 <= 's')||(LA13_45 >= 'u' && LA13_45 <= 'z')) ) {s = 37;}
						else if ( ((LA13_45 >= '-' && LA13_45 <= '.')||LA13_45=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 94;
						 
						input.seek(index13_45);
						if ( s>=0 ) return s;
						break;

					case 301 : 
						int LA13_20 = input.LA(1);
						 
						int index13_20 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_20=='o') ) {s = 61;}
						else if ( (LA13_20=='r') ) {s = 62;}
						else if ( ((LA13_20 >= '0' && LA13_20 <= '9')||(LA13_20 >= 'A' && LA13_20 <= 'Z')||(LA13_20 >= 'a' && LA13_20 <= 'n')||(LA13_20 >= 'p' && LA13_20 <= 'q')||(LA13_20 >= 's' && LA13_20 <= 'z')) ) {s = 37;}
						else if ( ((LA13_20 >= '-' && LA13_20 <= '.')||LA13_20=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_20);
						if ( s>=0 ) return s;
						break;

					case 302 : 
						int LA13_100 = input.LA(1);
						 
						int index13_100 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_100=='t') ) {s = 168;}
						else if ( ((LA13_100 >= '0' && LA13_100 <= '9')||(LA13_100 >= 'A' && LA13_100 <= 'Z')||(LA13_100 >= 'a' && LA13_100 <= 's')||(LA13_100 >= 'u' && LA13_100 <= 'z')) ) {s = 37;}
						else if ( ((LA13_100 >= '-' && LA13_100 <= '.')||LA13_100=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 169;
						 
						input.seek(index13_100);
						if ( s>=0 ) return s;
						break;

					case 303 : 
						int LA13_168 = input.LA(1);
						 
						int index13_168 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_168=='h') ) {s = 232;}
						else if ( ((LA13_168 >= '0' && LA13_168 <= '9')||(LA13_168 >= 'A' && LA13_168 <= 'Z')||(LA13_168 >= 'a' && LA13_168 <= 'g')||(LA13_168 >= 'i' && LA13_168 <= 'z')) ) {s = 37;}
						else if ( ((LA13_168 >= '-' && LA13_168 <= '.')||LA13_168=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 233;
						 
						input.seek(index13_168);
						if ( s>=0 ) return s;
						break;

					case 304 : 
						int LA13_232 = input.LA(1);
						 
						int index13_232 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_232==' ') ) {s = 285;}
						else if ( ((LA13_232 >= '0' && LA13_232 <= '9')||(LA13_232 >= 'A' && LA13_232 <= 'Z')||(LA13_232 >= 'a' && LA13_232 <= 'z')) ) {s = 37;}
						else if ( ((LA13_232 >= '-' && LA13_232 <= '.')||LA13_232=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 286;
						 
						input.seek(index13_232);
						if ( s>=0 ) return s;
						break;

					case 305 : 
						int LA13_397 = input.LA(1);
						 
						int index13_397 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_397 >= '0' && LA13_397 <= '9')||(LA13_397 >= 'A' && LA13_397 <= 'Z')||(LA13_397 >= 'a' && LA13_397 <= 'z')) ) {s = 37;}
						else if ( ((LA13_397 >= '-' && LA13_397 <= '.')||LA13_397=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 424;
						 
						input.seek(index13_397);
						if ( s>=0 ) return s;
						break;

					case 306 : 
						int LA13_52 = input.LA(1);
						 
						int index13_52 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_52=='N') ) {s = 107;}
						else if ( ((LA13_52 >= '0' && LA13_52 <= '9')||(LA13_52 >= 'A' && LA13_52 <= 'M')||(LA13_52 >= 'O' && LA13_52 <= 'Z')||(LA13_52 >= 'a' && LA13_52 <= 'z')) ) {s = 37;}
						else if ( ((LA13_52 >= '-' && LA13_52 <= '.')||LA13_52=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 108;
						 
						input.seek(index13_52);
						if ( s>=0 ) return s;
						break;

					case 307 : 
						int LA13_35 = input.LA(1);
						 
						int index13_35 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_35=='a') ) {s = 75;}
						else if ( ((LA13_35 >= '0' && LA13_35 <= '9')||(LA13_35 >= 'A' && LA13_35 <= 'Z')||(LA13_35 >= 'b' && LA13_35 <= 'z')) ) {s = 37;}
						else if ( ((LA13_35 >= '-' && LA13_35 <= '.')||LA13_35=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 76;
						 
						input.seek(index13_35);
						if ( s>=0 ) return s;
						break;

					case 308 : 
						int LA13_75 = input.LA(1);
						 
						int index13_75 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_75=='p') ) {s = 146;}
						else if ( ((LA13_75 >= '0' && LA13_75 <= '9')||(LA13_75 >= 'A' && LA13_75 <= 'Z')||(LA13_75 >= 'a' && LA13_75 <= 'o')||(LA13_75 >= 'q' && LA13_75 <= 'z')) ) {s = 37;}
						else if ( ((LA13_75 >= '-' && LA13_75 <= '.')||LA13_75=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 147;
						 
						input.seek(index13_75);
						if ( s>=0 ) return s;
						break;

					case 309 : 
						int LA13_146 = input.LA(1);
						 
						int index13_146 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_146=='t') ) {s = 213;}
						else if ( ((LA13_146 >= '0' && LA13_146 <= '9')||(LA13_146 >= 'A' && LA13_146 <= 'Z')||(LA13_146 >= 'a' && LA13_146 <= 's')||(LA13_146 >= 'u' && LA13_146 <= 'z')) ) {s = 37;}
						else if ( ((LA13_146 >= '-' && LA13_146 <= '.')||LA13_146=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 214;
						 
						input.seek(index13_146);
						if ( s>=0 ) return s;
						break;

					case 310 : 
						int LA13_213 = input.LA(1);
						 
						int index13_213 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_213=='i') ) {s = 273;}
						else if ( ((LA13_213 >= '0' && LA13_213 <= '9')||(LA13_213 >= 'A' && LA13_213 <= 'Z')||(LA13_213 >= 'a' && LA13_213 <= 'h')||(LA13_213 >= 'j' && LA13_213 <= 'z')) ) {s = 37;}
						else if ( ((LA13_213 >= '-' && LA13_213 <= '.')||LA13_213=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 274;
						 
						input.seek(index13_213);
						if ( s>=0 ) return s;
						break;

					case 311 : 
						int LA13_273 = input.LA(1);
						 
						int index13_273 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_273=='v') ) {s = 322;}
						else if ( ((LA13_273 >= '0' && LA13_273 <= '9')||(LA13_273 >= 'A' && LA13_273 <= 'Z')||(LA13_273 >= 'a' && LA13_273 <= 'u')||(LA13_273 >= 'w' && LA13_273 <= 'z')) ) {s = 37;}
						else if ( ((LA13_273 >= '-' && LA13_273 <= '.')||LA13_273=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 323;
						 
						input.seek(index13_273);
						if ( s>=0 ) return s;
						break;

					case 312 : 
						int LA13_322 = input.LA(1);
						 
						int index13_322 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_322=='e') ) {s = 361;}
						else if ( ((LA13_322 >= '0' && LA13_322 <= '9')||(LA13_322 >= 'A' && LA13_322 <= 'Z')||(LA13_322 >= 'a' && LA13_322 <= 'd')||(LA13_322 >= 'f' && LA13_322 <= 'z')) ) {s = 37;}
						else if ( ((LA13_322 >= '-' && LA13_322 <= '.')||LA13_322=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 362;
						 
						input.seek(index13_322);
						if ( s>=0 ) return s;
						break;

					case 313 : 
						int LA13_361 = input.LA(1);
						 
						int index13_361 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_361=='R') ) {s = 395;}
						else if ( ((LA13_361 >= '0' && LA13_361 <= '9')||(LA13_361 >= 'A' && LA13_361 <= 'Q')||(LA13_361 >= 'S' && LA13_361 <= 'Z')||(LA13_361 >= 'a' && LA13_361 <= 'z')) ) {s = 37;}
						else if ( ((LA13_361 >= '-' && LA13_361 <= '.')||LA13_361=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 396;
						 
						input.seek(index13_361);
						if ( s>=0 ) return s;
						break;

					case 314 : 
						int LA13_395 = input.LA(1);
						 
						int index13_395 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_395=='a') ) {s = 422;}
						else if ( ((LA13_395 >= '0' && LA13_395 <= '9')||(LA13_395 >= 'A' && LA13_395 <= 'Z')||(LA13_395 >= 'b' && LA13_395 <= 'z')) ) {s = 37;}
						else if ( ((LA13_395 >= '-' && LA13_395 <= '.')||LA13_395=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 423;
						 
						input.seek(index13_395);
						if ( s>=0 ) return s;
						break;

					case 315 : 
						int LA13_422 = input.LA(1);
						 
						int index13_422 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_422=='t') ) {s = 440;}
						else if ( ((LA13_422 >= '0' && LA13_422 <= '9')||(LA13_422 >= 'A' && LA13_422 <= 'Z')||(LA13_422 >= 'a' && LA13_422 <= 's')||(LA13_422 >= 'u' && LA13_422 <= 'z')) ) {s = 37;}
						else if ( ((LA13_422 >= '-' && LA13_422 <= '.')||LA13_422=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 441;
						 
						input.seek(index13_422);
						if ( s>=0 ) return s;
						break;

					case 316 : 
						int LA13_440 = input.LA(1);
						 
						int index13_440 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_440=='e') ) {s = 448;}
						else if ( ((LA13_440 >= '0' && LA13_440 <= '9')||(LA13_440 >= 'A' && LA13_440 <= 'Z')||(LA13_440 >= 'a' && LA13_440 <= 'd')||(LA13_440 >= 'f' && LA13_440 <= 'z')) ) {s = 37;}
						else if ( ((LA13_440 >= '-' && LA13_440 <= '.')||LA13_440=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 449;
						 
						input.seek(index13_440);
						if ( s>=0 ) return s;
						break;

					case 317 : 
						int LA13_16 = input.LA(1);
						 
						int index13_16 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_16=='e') ) {s = 53;}
						else if ( (LA13_16=='u') ) {s = 54;}
						else if ( ((LA13_16 >= '0' && LA13_16 <= '9')||(LA13_16 >= 'A' && LA13_16 <= 'Z')||(LA13_16 >= 'a' && LA13_16 <= 'd')||(LA13_16 >= 'f' && LA13_16 <= 't')||(LA13_16 >= 'v' && LA13_16 <= 'z')) ) {s = 37;}
						else if ( ((LA13_16 >= '-' && LA13_16 <= '.')||LA13_16=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_16);
						if ( s>=0 ) return s;
						break;

					case 318 : 
						int LA13_36 = input.LA(1);
						 
						int index13_36 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_36=='s') ) {s = 77;}
						else if ( ((LA13_36 >= '0' && LA13_36 <= '9')||(LA13_36 >= 'A' && LA13_36 <= 'Z')||(LA13_36 >= 'a' && LA13_36 <= 'r')||(LA13_36 >= 't' && LA13_36 <= 'z')) ) {s = 37;}
						else if ( ((LA13_36 >= '-' && LA13_36 <= '.')||LA13_36=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 78;
						 
						input.seek(index13_36);
						if ( s>=0 ) return s;
						break;

					case 319 : 
						int LA13_77 = input.LA(1);
						 
						int index13_77 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_77=='o') ) {s = 148;}
						else if ( ((LA13_77 >= '0' && LA13_77 <= '9')||(LA13_77 >= 'A' && LA13_77 <= 'Z')||(LA13_77 >= 'a' && LA13_77 <= 'n')||(LA13_77 >= 'p' && LA13_77 <= 'z')) ) {s = 37;}
						else if ( ((LA13_77 >= '-' && LA13_77 <= '.')||LA13_77=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 149;
						 
						input.seek(index13_77);
						if ( s>=0 ) return s;
						break;

					case 320 : 
						int LA13_148 = input.LA(1);
						 
						int index13_148 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_148==' ') ) {s = 215;}
						else if ( ((LA13_148 >= '0' && LA13_148 <= '9')||(LA13_148 >= 'A' && LA13_148 <= 'Z')||(LA13_148 >= 'a' && LA13_148 <= 'z')) ) {s = 37;}
						else if ( ((LA13_148 >= '-' && LA13_148 <= '.')||LA13_148=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 216;
						 
						input.seek(index13_148);
						if ( s>=0 ) return s;
						break;

					case 321 : 
						int LA13_5 = input.LA(1);
						 
						int index13_5 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_5=='a') ) {s = 39;}
						else if ( (LA13_5=='i') ) {s = 40;}
						else if ( ((LA13_5 >= '0' && LA13_5 <= '9')||(LA13_5 >= 'A' && LA13_5 <= 'Z')||(LA13_5 >= 'b' && LA13_5 <= 'h')||(LA13_5 >= 'j' && LA13_5 <= 'z')) ) {s = 37;}
						else if ( ((LA13_5 >= '-' && LA13_5 <= '.')||LA13_5=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_5);
						if ( s>=0 ) return s;
						break;

					case 322 : 
						int LA13_53 = input.LA(1);
						 
						int index13_53 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_53=='a') ) {s = 109;}
						else if ( ((LA13_53 >= '0' && LA13_53 <= '9')||(LA13_53 >= 'A' && LA13_53 <= 'Z')||(LA13_53 >= 'b' && LA13_53 <= 'z')) ) {s = 37;}
						else if ( ((LA13_53 >= '-' && LA13_53 <= '.')||LA13_53=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 110;
						 
						input.seek(index13_53);
						if ( s>=0 ) return s;
						break;

					case 323 : 
						int LA13_109 = input.LA(1);
						 
						int index13_109 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_109=='r') ) {s = 177;}
						else if ( ((LA13_109 >= '0' && LA13_109 <= '9')||(LA13_109 >= 'A' && LA13_109 <= 'Z')||(LA13_109 >= 'a' && LA13_109 <= 'q')||(LA13_109 >= 's' && LA13_109 <= 'z')) ) {s = 37;}
						else if ( ((LA13_109 >= '-' && LA13_109 <= '.')||LA13_109=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 178;
						 
						input.seek(index13_109);
						if ( s>=0 ) return s;
						break;

					case 324 : 
						int LA13_177 = input.LA(1);
						 
						int index13_177 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_177=='i') ) {s = 239;}
						else if ( ((LA13_177 >= '0' && LA13_177 <= '9')||(LA13_177 >= 'A' && LA13_177 <= 'Z')||(LA13_177 >= 'a' && LA13_177 <= 'h')||(LA13_177 >= 'j' && LA13_177 <= 'z')) ) {s = 37;}
						else if ( ((LA13_177 >= '-' && LA13_177 <= '.')||LA13_177=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 240;
						 
						input.seek(index13_177);
						if ( s>=0 ) return s;
						break;

					case 325 : 
						int LA13_174 = input.LA(1);
						 
						int index13_174 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_174 >= '0' && LA13_174 <= '9')||(LA13_174 >= 'A' && LA13_174 <= 'Z')||(LA13_174 >= 'a' && LA13_174 <= 'z')) ) {s = 37;}
						else if ( ((LA13_174 >= '-' && LA13_174 <= '.')||LA13_174=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 238;
						 
						input.seek(index13_174);
						if ( s>=0 ) return s;
						break;

					case 326 : 
						int LA13_239 = input.LA(1);
						 
						int index13_239 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_239=='s') ) {s = 291;}
						else if ( ((LA13_239 >= '0' && LA13_239 <= '9')||(LA13_239 >= 'A' && LA13_239 <= 'Z')||(LA13_239 >= 'a' && LA13_239 <= 'r')||(LA13_239 >= 't' && LA13_239 <= 'z')) ) {s = 37;}
						else if ( ((LA13_239 >= '-' && LA13_239 <= '.')||LA13_239=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 292;
						 
						input.seek(index13_239);
						if ( s>=0 ) return s;
						break;

					case 327 : 
						int LA13_291 = input.LA(1);
						 
						int index13_291 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_291=='h') ) {s = 337;}
						else if ( ((LA13_291 >= '0' && LA13_291 <= '9')||(LA13_291 >= 'A' && LA13_291 <= 'Z')||(LA13_291 >= 'a' && LA13_291 <= 'g')||(LA13_291 >= 'i' && LA13_291 <= 'z')) ) {s = 37;}
						else if ( ((LA13_291 >= '-' && LA13_291 <= '.')||LA13_291=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 338;
						 
						input.seek(index13_291);
						if ( s>=0 ) return s;
						break;

					case 328 : 
						int LA13_345 = input.LA(1);
						 
						int index13_345 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_345 >= '0' && LA13_345 <= '9')||(LA13_345 >= 'A' && LA13_345 <= 'Z')||(LA13_345 >= 'a' && LA13_345 <= 'z')) ) {s = 37;}
						else if ( ((LA13_345 >= '-' && LA13_345 <= '.')||LA13_345=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 381;
						 
						input.seek(index13_345);
						if ( s>=0 ) return s;
						break;

					case 329 : 
						int LA13_54 = input.LA(1);
						 
						int index13_54 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_54=='l') ) {s = 111;}
						else if ( ((LA13_54 >= '0' && LA13_54 <= '9')||(LA13_54 >= 'A' && LA13_54 <= 'Z')||(LA13_54 >= 'a' && LA13_54 <= 'k')||(LA13_54 >= 'm' && LA13_54 <= 'z')) ) {s = 37;}
						else if ( ((LA13_54 >= '-' && LA13_54 <= '.')||LA13_54=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 112;
						 
						input.seek(index13_54);
						if ( s>=0 ) return s;
						break;

					case 330 : 
						int LA13_111 = input.LA(1);
						 
						int index13_111 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_111=='l') ) {s = 179;}
						else if ( ((LA13_111 >= '0' && LA13_111 <= '9')||(LA13_111 >= 'A' && LA13_111 <= 'Z')||(LA13_111 >= 'a' && LA13_111 <= 'k')||(LA13_111 >= 'm' && LA13_111 <= 'z')) ) {s = 37;}
						else if ( ((LA13_111 >= '-' && LA13_111 <= '.')||LA13_111=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 180;
						 
						input.seek(index13_111);
						if ( s>=0 ) return s;
						break;

					case 331 : 
						int LA13_179 = input.LA(1);
						 
						int index13_179 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_179=='i') ) {s = 241;}
						else if ( ((LA13_179 >= '0' && LA13_179 <= '9')||(LA13_179 >= 'A' && LA13_179 <= 'Z')||(LA13_179 >= 'a' && LA13_179 <= 'h')||(LA13_179 >= 'j' && LA13_179 <= 'z')) ) {s = 37;}
						else if ( ((LA13_179 >= '-' && LA13_179 <= '.')||LA13_179=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 242;
						 
						input.seek(index13_179);
						if ( s>=0 ) return s;
						break;

					case 332 : 
						int LA13_241 = input.LA(1);
						 
						int index13_241 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_241=='s') ) {s = 293;}
						else if ( ((LA13_241 >= '0' && LA13_241 <= '9')||(LA13_241 >= 'A' && LA13_241 <= 'Z')||(LA13_241 >= 'a' && LA13_241 <= 'r')||(LA13_241 >= 't' && LA13_241 <= 'z')) ) {s = 37;}
						else if ( ((LA13_241 >= '-' && LA13_241 <= '.')||LA13_241=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 294;
						 
						input.seek(index13_241);
						if ( s>=0 ) return s;
						break;

					case 333 : 
						int LA13_293 = input.LA(1);
						 
						int index13_293 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_293=='h') ) {s = 339;}
						else if ( ((LA13_293 >= '0' && LA13_293 <= '9')||(LA13_293 >= 'A' && LA13_293 <= 'Z')||(LA13_293 >= 'a' && LA13_293 <= 'g')||(LA13_293 >= 'i' && LA13_293 <= 'z')) ) {s = 37;}
						else if ( ((LA13_293 >= '-' && LA13_293 <= '.')||LA13_293=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 340;
						 
						input.seek(index13_293);
						if ( s>=0 ) return s;
						break;

					case 334 : 
						int LA13_55 = input.LA(1);
						 
						int index13_55 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_55=='o') ) {s = 113;}
						else if ( ((LA13_55 >= '0' && LA13_55 <= '9')||(LA13_55 >= 'A' && LA13_55 <= 'Z')||(LA13_55 >= 'a' && LA13_55 <= 'n')||(LA13_55 >= 'p' && LA13_55 <= 'z')) ) {s = 37;}
						else if ( ((LA13_55 >= '-' && LA13_55 <= '.')||LA13_55=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 114;
						 
						input.seek(index13_55);
						if ( s>=0 ) return s;
						break;

					case 335 : 
						int LA13_113 = input.LA(1);
						 
						int index13_113 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_113=='s') ) {s = 181;}
						else if ( ((LA13_113 >= '0' && LA13_113 <= '9')||(LA13_113 >= 'A' && LA13_113 <= 'Z')||(LA13_113 >= 'a' && LA13_113 <= 'r')||(LA13_113 >= 't' && LA13_113 <= 'z')) ) {s = 37;}
						else if ( ((LA13_113 >= '-' && LA13_113 <= '.')||LA13_113=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 182;
						 
						input.seek(index13_113);
						if ( s>=0 ) return s;
						break;

					case 336 : 
						int LA13_181 = input.LA(1);
						 
						int index13_181 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_181=='s') ) {s = 243;}
						else if ( ((LA13_181 >= '0' && LA13_181 <= '9')||(LA13_181 >= 'A' && LA13_181 <= 'Z')||(LA13_181 >= 'a' && LA13_181 <= 'r')||(LA13_181 >= 't' && LA13_181 <= 'z')) ) {s = 37;}
						else if ( ((LA13_181 >= '-' && LA13_181 <= '.')||LA13_181=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 244;
						 
						input.seek(index13_181);
						if ( s>=0 ) return s;
						break;

					case 337 : 
						int LA13_243 = input.LA(1);
						 
						int index13_243 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_243=='e') ) {s = 295;}
						else if ( ((LA13_243 >= '0' && LA13_243 <= '9')||(LA13_243 >= 'A' && LA13_243 <= 'Z')||(LA13_243 >= 'a' && LA13_243 <= 'd')||(LA13_243 >= 'f' && LA13_243 <= 'z')) ) {s = 37;}
						else if ( ((LA13_243 >= '-' && LA13_243 <= '.')||LA13_243=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 296;
						 
						input.seek(index13_243);
						if ( s>=0 ) return s;
						break;

					case 338 : 
						int LA13_295 = input.LA(1);
						 
						int index13_295 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_295=='s') ) {s = 341;}
						else if ( ((LA13_295 >= '0' && LA13_295 <= '9')||(LA13_295 >= 'A' && LA13_295 <= 'Z')||(LA13_295 >= 'a' && LA13_295 <= 'r')||(LA13_295 >= 't' && LA13_295 <= 'z')) ) {s = 37;}
						else if ( ((LA13_295 >= '-' && LA13_295 <= '.')||LA13_295=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 342;
						 
						input.seek(index13_295);
						if ( s>=0 ) return s;
						break;

					case 339 : 
						int LA13_7 = input.LA(1);
						 
						int index13_7 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_7=='a') ) {s = 43;}
						else if ( (LA13_7=='o') ) {s = 44;}
						else if ( ((LA13_7 >= '0' && LA13_7 <= '9')||(LA13_7 >= 'A' && LA13_7 <= 'Z')||(LA13_7 >= 'b' && LA13_7 <= 'n')||(LA13_7 >= 'p' && LA13_7 <= 'z')) ) {s = 37;}
						else if ( ((LA13_7 >= '-' && LA13_7 <= '.')||LA13_7=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 38;
						 
						input.seek(index13_7);
						if ( s>=0 ) return s;
						break;

					case 340 : 
						int LA13_341 = input.LA(1);
						 
						int index13_341 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_341==' ') ) {s = 379;}
						else if ( ((LA13_341 >= '0' && LA13_341 <= '9')||(LA13_341 >= 'A' && LA13_341 <= 'Z')||(LA13_341 >= 'a' && LA13_341 <= 'z')) ) {s = 37;}
						else if ( ((LA13_341 >= '-' && LA13_341 <= '.')||LA13_341=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 380;
						 
						input.seek(index13_341);
						if ( s>=0 ) return s;
						break;

					case 341 : 
						int LA13_59 = input.LA(1);
						 
						int index13_59 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_59=='u') ) {s = 121;}
						else if ( ((LA13_59 >= '0' && LA13_59 <= '9')||(LA13_59 >= 'A' && LA13_59 <= 'Z')||(LA13_59 >= 'a' && LA13_59 <= 't')||(LA13_59 >= 'v' && LA13_59 <= 'z')) ) {s = 37;}
						else if ( ((LA13_59 >= '-' && LA13_59 <= '.')||LA13_59=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 122;
						 
						input.seek(index13_59);
						if ( s>=0 ) return s;
						break;

					case 342 : 
						int LA13_121 = input.LA(1);
						 
						int index13_121 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_121=='a') ) {s = 189;}
						else if ( ((LA13_121 >= '0' && LA13_121 <= '9')||(LA13_121 >= 'A' && LA13_121 <= 'Z')||(LA13_121 >= 'b' && LA13_121 <= 'z')) ) {s = 37;}
						else if ( ((LA13_121 >= '-' && LA13_121 <= '.')||LA13_121=='_') && ((runtimeOpAhead()))) {s = 31;}
						else s = 190;
						 
						input.seek(index13_121);
						if ( s>=0 ) return s;
						break;
			}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 13, _s, input);
			error(nvae);
			throw nvae;
		}
	}

}
