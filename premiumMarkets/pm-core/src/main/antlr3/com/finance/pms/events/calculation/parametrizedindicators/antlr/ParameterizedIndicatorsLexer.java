// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2025-03-02 20:38:41
 //lexer
    package com.finance.pms.events.calculation.parametrizedindicators.antlr;
    import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.FailedPredicateException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

import com.finance.pms.events.calculation.antlr.IndsLexerDelegate;
import com.finance.pms.events.calculation.antlr.MyErrorReporter;

@SuppressWarnings("all")
public class ParameterizedIndicatorsLexer extends Lexer {
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

	// $ANTLR start "WITH"
	public final void mWITH() throws RecognitionException {
		try {
			int _type = WITH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:63:6: ( 'with' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:63:8: 'with'
			{
			match("with"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WITH"

	// $ANTLR start "T__66"
	public final void mT__66() throws RecognitionException {
		try {
			int _type = T__66;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:64:7: ( 'NaN' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:64:9: 'NaN'
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
	// $ANTLR end "T__66"

	// $ANTLR start "T__67"
	public final void mT__67() throws RecognitionException {
		try {
			int _type = T__67;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:65:7: ( 'adaptiveRate' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:65:9: 'adaptiveRate'
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
	// $ANTLR end "T__67"

	// $ANTLR start "T__68"
	public final void mT__68() throws RecognitionException {
		try {
			int _type = T__68;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:66:7: ( 'also display' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:66:9: 'also display'
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
	// $ANTLR end "T__68"

	// $ANTLR start "T__69"
	public final void mT__69() throws RecognitionException {
		try {
			int _type = T__69;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:67:7: ( 'bearish' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:67:9: 'bearish'
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
	// $ANTLR end "T__69"

	// $ANTLR start "T__70"
	public final void mT__70() throws RecognitionException {
		try {
			int _type = T__70;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:68:7: ( 'bullish' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:68:9: 'bullish'
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
	// $ANTLR end "T__70"

	// $ANTLR start "T__71"
	public final void mT__71() throws RecognitionException {
		try {
			int _type = T__71;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:69:7: ( 'crosses down historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:69:9: 'crosses down historical'
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
	// $ANTLR end "T__71"

	// $ANTLR start "T__72"
	public final void mT__72() throws RecognitionException {
		try {
			int _type = T__72;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:70:7: ( 'crosses down threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:70:9: 'crosses down threshold'
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
	// $ANTLR end "T__72"

	// $ANTLR start "T__73"
	public final void mT__73() throws RecognitionException {
		try {
			int _type = T__73;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:71:7: ( 'crosses up historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:71:9: 'crosses up historical'
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
	// $ANTLR end "T__73"

	// $ANTLR start "T__74"
	public final void mT__74() throws RecognitionException {
		try {
			int _type = T__74;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:72:7: ( 'crosses up threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:72:9: 'crosses up threshold'
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
	// $ANTLR end "T__74"

	// $ANTLR start "T__75"
	public final void mT__75() throws RecognitionException {
		try {
			int _type = T__75;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:73:7: ( 'direction' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:73:9: 'direction'
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
	// $ANTLR end "T__75"

	// $ANTLR start "T__76"
	public final void mT__76() throws RecognitionException {
		try {
			int _type = T__76;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:74:7: ( 'ending within' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:74:9: 'ending within'
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
	// $ANTLR end "T__76"

	// $ANTLR start "T__77"
	public final void mT__77() throws RecognitionException {
		try {
			int _type = T__77;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:75:7: ( 'epsilon' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:75:9: 'epsilon'
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
	// $ANTLR end "T__77"

	// $ANTLR start "T__78"
	public final void mT__78() throws RecognitionException {
		try {
			int _type = T__78;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:76:7: ( 'equals historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:76:9: 'equals historical'
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
	// $ANTLR end "T__78"

	// $ANTLR start "T__79"
	public final void mT__79() throws RecognitionException {
		try {
			int _type = T__79;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:77:7: ( 'equals threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:77:9: 'equals threshold'
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
	// $ANTLR end "T__79"

	// $ANTLR start "T__80"
	public final void mT__80() throws RecognitionException {
		try {
			int _type = T__80;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:7: ( 'equals trend' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:9: 'equals trend'
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
	// $ANTLR end "T__80"

	// $ANTLR start "T__81"
	public final void mT__81() throws RecognitionException {
		try {
			int _type = T__81;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:79:7: ( 'for' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:79:9: 'for'
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
	// $ANTLR end "T__81"

	// $ANTLR start "T__82"
	public final void mT__82() throws RecognitionException {
		try {
			int _type = T__82;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:80:7: ( 'goes down more than' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:80:9: 'goes down more than'
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
	// $ANTLR end "T__82"

	// $ANTLR start "T__83"
	public final void mT__83() throws RecognitionException {
		try {
			int _type = T__83;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:81:7: ( 'goes up more than' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:81:9: 'goes up more than'
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
	// $ANTLR end "T__83"

	// $ANTLR start "T__84"
	public final void mT__84() throws RecognitionException {
		try {
			int _type = T__84;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:82:7: ( 'greed' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:82:9: 'greed'
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
	// $ANTLR end "T__84"

	// $ANTLR start "T__85"
	public final void mT__85() throws RecognitionException {
		try {
			int _type = T__85;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:83:7: ( 'is above historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:83:9: 'is above historical'
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
	// $ANTLR end "T__85"

	// $ANTLR start "T__86"
	public final void mT__86() throws RecognitionException {
		try {
			int _type = T__86;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:84:7: ( 'is above threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:84:9: 'is above threshold'
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
	// $ANTLR end "T__86"

	// $ANTLR start "T__87"
	public final void mT__87() throws RecognitionException {
		try {
			int _type = T__87;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:85:7: ( 'is bearish if not bullish' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:85:9: 'is bearish if not bullish'
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
	// $ANTLR end "T__87"

	// $ANTLR start "T__88"
	public final void mT__88() throws RecognitionException {
		try {
			int _type = T__88;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:86:7: ( 'is bearish when' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:86:9: 'is bearish when'
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
	// $ANTLR end "T__88"

	// $ANTLR start "T__89"
	public final void mT__89() throws RecognitionException {
		try {
			int _type = T__89;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:87:7: ( 'is below historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:87:9: 'is below historical'
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
	// $ANTLR end "T__89"

	// $ANTLR start "T__90"
	public final void mT__90() throws RecognitionException {
		try {
			int _type = T__90;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:88:7: ( 'is below threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:88:9: 'is below threshold'
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
	// $ANTLR end "T__90"

	// $ANTLR start "T__91"
	public final void mT__91() throws RecognitionException {
		try {
			int _type = T__91;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:89:7: ( 'is bullish when' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:89:9: 'is bullish when'
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
	// $ANTLR end "T__91"

	// $ANTLR start "T__92"
	public final void mT__92() throws RecognitionException {
		try {
			int _type = T__92;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:90:7: ( 'is within' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:90:9: 'is within'
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
	// $ANTLR end "T__92"

	// $ANTLR start "T__93"
	public final void mT__93() throws RecognitionException {
		try {
			int _type = T__93;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:91:7: ( 'makes a higher high spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:91:9: 'makes a higher high spanning'
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
	// $ANTLR end "T__93"

	// $ANTLR start "T__94"
	public final void mT__94() throws RecognitionException {
		try {
			int _type = T__94;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:92:7: ( 'makes a higher low spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:92:9: 'makes a higher low spanning'
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
	// $ANTLR end "T__94"

	// $ANTLR start "T__95"
	public final void mT__95() throws RecognitionException {
		try {
			int _type = T__95;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:93:7: ( 'makes a lower high spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:93:9: 'makes a lower high spanning'
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
	// $ANTLR end "T__95"

	// $ANTLR start "T__96"
	public final void mT__96() throws RecognitionException {
		try {
			int _type = T__96;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:94:7: ( 'makes a lower low spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:94:9: 'makes a lower low spanning'
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
	// $ANTLR end "T__96"

	// $ANTLR start "T__97"
	public final void mT__97() throws RecognitionException {
		try {
			int _type = T__97;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:95:7: ( 'makes a support break down spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:95:9: 'makes a support break down spanning'
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
	// $ANTLR end "T__97"

	// $ANTLR start "T__98"
	public final void mT__98() throws RecognitionException {
		try {
			int _type = T__98;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:96:7: ( 'makes a support break up spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:96:9: 'makes a support break up spanning'
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
	// $ANTLR end "T__98"

	// $ANTLR start "T__99"
	public final void mT__99() throws RecognitionException {
		try {
			int _type = T__99;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:97:7: ( 'more than' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:97:9: 'more than'
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
	// $ANTLR end "T__99"

	// $ANTLR start "T__100"
	public final void mT__100() throws RecognitionException {
		try {
			int _type = T__100;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:98:8: ( 'over' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:98:10: 'over'
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
	// $ANTLR end "T__100"

	// $ANTLR start "T__101"
	public final void mT__101() throws RecognitionException {
		try {
			int _type = T__101;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:99:8: ( 'override event list name with' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:99:10: 'override event list name with'
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
	// $ANTLR end "T__101"

	// $ANTLR start "T__102"
	public final void mT__102() throws RecognitionException {
		try {
			int _type = T__102;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:100:8: ( 'override start shift with' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:100:10: 'override start shift with'
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
	// $ANTLR end "T__102"

	// $ANTLR start "T__103"
	public final void mT__103() throws RecognitionException {
		try {
			int _type = T__103;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:101:8: ( 'precondition' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:101:10: 'precondition'
			{
			match("precondition"); 

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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:102:8: ( 'reverses down' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:102:10: 'reverses down'
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
	// $ANTLR end "T__104"

	// $ANTLR start "T__105"
	public final void mT__105() throws RecognitionException {
		try {
			int _type = T__105;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:103:8: ( 'reverses up' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:103:10: 'reverses up'
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
	// $ANTLR end "T__105"

	// $ANTLR start "T__106"
	public final void mT__106() throws RecognitionException {
		try {
			int _type = T__106;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:104:8: ( 'slope within' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:104:10: 'slope within'
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
	// $ANTLR end "T__106"

	// $ANTLR start "T__107"
	public final void mT__107() throws RecognitionException {
		try {
			int _type = T__107;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:105:8: ( 'smoothed' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:105:10: 'smoothed'
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
	// $ANTLR end "T__107"

	// $ANTLR start "T__108"
	public final void mT__108() throws RecognitionException {
		try {
			int _type = T__108;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:106:8: ( 'spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:106:10: 'spanning'
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
	// $ANTLR end "T__108"

	// $ANTLR start "T__109"
	public final void mT__109() throws RecognitionException {
		try {
			int _type = T__109;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:107:8: ( 'starting within' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:107:10: 'starting within'
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
	// $ANTLR end "T__109"

	// $ANTLR start "T__110"
	public final void mT__110() throws RecognitionException {
		try {
			int _type = T__110;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:108:8: ( 'tolerance' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:108:10: 'tolerance'
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
	// $ANTLR end "T__110"

	// $ANTLR start "T__111"
	public final void mT__111() throws RecognitionException {
		try {
			int _type = T__111;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:109:8: ( 'trends down' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:109:10: 'trends down'
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
	// $ANTLR end "T__111"

	// $ANTLR start "T__112"
	public final void mT__112() throws RecognitionException {
		try {
			int _type = T__112;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:110:8: ( 'trends flat' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:110:10: 'trends flat'
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
	// $ANTLR end "T__112"

	// $ANTLR start "T__113"
	public final void mT__113() throws RecognitionException {
		try {
			int _type = T__113;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:111:8: ( 'trends like' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:111:10: 'trends like'
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
	// $ANTLR end "T__113"

	// $ANTLR start "T__114"
	public final void mT__114() throws RecognitionException {
		try {
			int _type = T__114;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:112:8: ( 'trends unlike' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:112:10: 'trends unlike'
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
	// $ANTLR end "T__114"

	// $ANTLR start "T__115"
	public final void mT__115() throws RecognitionException {
		try {
			int _type = T__115;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:113:8: ( 'trends up' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:113:10: 'trends up'
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
	// $ANTLR end "T__115"

	// $ANTLR start "T__116"
	public final void mT__116() throws RecognitionException {
		try {
			int _type = T__116;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:114:8: ( 'type' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:114:10: 'type'
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
	// $ANTLR end "T__116"

	// $ANTLR start "Operation"
	public final void mOperation() throws RecognitionException {
		try {
			int _type = Operation;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:413:7: ({...}? => Tcheat )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:413:9: {...}? => Tcheat
			{
			if ( !((runtimeOpAhead())) ) {
				throw new FailedPredicateException(input, "Operation", "runtimeOpAhead()");
			}
			mTcheat(); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Operation"

	// $ANTLR start "AlphaCase"
	public final void mAlphaCase() throws RecognitionException {
		try {
			int _type = AlphaCase;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:417:7: ( 'a' .. 'z' | 'A' .. 'Z' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AlphaCase"

	// $ANTLR start "SepCase"
	public final void mSepCase() throws RecognitionException {
		try {
			int _type = SepCase;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:420:8: ( '_' | '.' | '-' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
			{
			if ( (input.LA(1) >= '-' && input.LA(1) <= '.')||input.LA(1)=='_' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SepCase"

	// $ANTLR start "NumberToken"
	public final void mNumberToken() throws RecognitionException {
		try {
			int _type = NumberToken;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:424:7: ( ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:424:9: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:424:9: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:424:11: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:424:11: ( '-' )?
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0=='-') ) {
				alt1=1;
			}
			switch (alt1) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:424:12: '-'
					{
					match('-'); 
					}
					break;

			}

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:424:18: ( '0' .. '9' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
					alt2=1;
				}

				switch (alt2) {
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
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:424:30: ( '.' ( '0' .. '9' )+ )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0=='.') ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:424:31: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:424:35: ( '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:428:6: ( '\"' ( AlphaCase | SepCase | '0' .. '9' | '/' | ',' | '=' | '?' | ':' | '>' )+ '\"' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:428:8: '\"' ( AlphaCase | SepCase | '0' .. '9' | '/' | ',' | '=' | '?' | ':' | '>' )+ '\"'
			{
			match('\"'); 
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:428:12: ( AlphaCase | SepCase | '0' .. '9' | '/' | ',' | '=' | '?' | ':' | '>' )+
			int cnt5=0;
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= ',' && LA5_0 <= ':')||(LA5_0 >= '=' && LA5_0 <= '?')||(LA5_0 >= 'A' && LA5_0 <= 'Z')||LA5_0=='_'||(LA5_0 >= 'a' && LA5_0 <= 'z')) ) {
					alt5=1;
				}

				switch (alt5) {
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
					if ( cnt5 >= 1 ) break loop5;
					EarlyExitException eee = new EarlyExitException(5, input);
					throw eee;
				}
				cnt5++;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:432:7: ({...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:432:9: {...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' )
			{
			if ( !((runtimeHistoryOpAhead())) ) {
				throw new FailedPredicateException(input, "HistoricalData", "runtimeHistoryOpAhead()");
			}
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:432:39: ( 'open' | 'close' | 'high' | 'low' | 'volume' )
			int alt6=5;
			switch ( input.LA(1) ) {
			case 'o':
				{
				alt6=1;
				}
				break;
			case 'c':
				{
				alt6=2;
				}
				break;
			case 'h':
				{
				alt6=3;
				}
				break;
			case 'l':
				{
				alt6=4;
				}
				break;
			case 'v':
				{
				alt6=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}
			switch (alt6) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:432:41: 'open'
					{
					match("open"); 

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:432:50: 'close'
					{
					match("close"); 

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:432:60: 'high'
					{
					match("high"); 

					}
					break;
				case 4 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:432:69: 'low'
					{
					match("low"); 

					}
					break;
				case 5 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:432:78: 'volume'
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:436:7: ( ( ( ' ' )+ ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:436:9: ( ( ' ' )+ )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:436:9: ( ( ' ' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:436:10: ( ' ' )+
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:436:10: ( ' ' )+
			int cnt7=0;
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==' ') ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:436:10: ' '
					{
					match(' '); 
					}
					break;

				default :
					if ( cnt7 >= 1 ) break loop7;
					EarlyExitException eee = new EarlyExitException(7, input);
					throw eee;
				}
				cnt7++;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:440:5: ( ( AlphaCase | '_' ) ( AlphaCase | SepCase | '0' .. '9' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:440:7: ( AlphaCase | '_' ) ( AlphaCase | SepCase | '0' .. '9' )+
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:440:25: ( AlphaCase | SepCase | '0' .. '9' )+
			int cnt8=0;
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( ((LA8_0 >= '-' && LA8_0 <= '.')||(LA8_0 >= '0' && LA8_0 <= '9')||(LA8_0 >= 'A' && LA8_0 <= 'Z')||LA8_0=='_'||(LA8_0 >= 'a' && LA8_0 <= 'z')) ) {
					alt8=1;
				}

				switch (alt8) {
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
					if ( cnt8 >= 1 ) break loop8;
					EarlyExitException eee = new EarlyExitException(8, input);
					throw eee;
				}
				cnt8++;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:446:5: ( ( '\\r' | '\\t' | '\\u000C' | '\\n' ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:446:7: ( '\\r' | '\\t' | '\\u000C' | '\\n' )
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:449:5: ( '/*' ( . )* '*/' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:449:7: '/*' ( . )* '*/'
			{
			match("/*"); 

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:449:12: ( . )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0=='*') ) {
					int LA9_1 = input.LA(2);
					if ( (LA9_1=='/') ) {
						alt9=2;
					}
					else if ( ((LA9_1 >= '\u0000' && LA9_1 <= '.')||(LA9_1 >= '0' && LA9_1 <= '\uFFFF')) ) {
						alt9=1;
					}

				}
				else if ( ((LA9_0 >= '\u0000' && LA9_0 <= ')')||(LA9_0 >= '+' && LA9_0 <= '\uFFFF')) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:449:12: .
					{
					matchAny(); 
					}
					break;

				default :
					break loop9;
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:452:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:452:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match("//"); 

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:452:12: (~ ( '\\n' | '\\r' ) )*
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( ((LA10_0 >= '\u0000' && LA10_0 <= '\t')||(LA10_0 >= '\u000B' && LA10_0 <= '\f')||(LA10_0 >= '\u000E' && LA10_0 <= '\uFFFF')) ) {
					alt10=1;
				}

				switch (alt10) {
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
					break loop10;
				}
			}

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:452:26: ( '\\r' )?
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0=='\r') ) {
				alt11=1;
			}
			switch (alt11) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:452:26: '\\r'
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
		// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:8: ( AND | CLOSEPARENTEHSIS | CLOSESQRT | COMMA | DAYS | LENIENT | MATCHING | NOT | OPENPARENTEHSIS | OPENSQRT | OR | PERCENT | SEMICOLUMN | TRUTHOF | WITH | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | Operation | AlphaCase | SepCase | NumberToken | StringToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT )
		int alt12=77;
		alt12 = dfa12.predict(input);
		switch (alt12) {
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
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:128: WITH
				{
				mWITH(); 

				}
				break;
			case 16 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:133: T__66
				{
				mT__66(); 

				}
				break;
			case 17 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:139: T__67
				{
				mT__67(); 

				}
				break;
			case 18 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:145: T__68
				{
				mT__68(); 

				}
				break;
			case 19 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:151: T__69
				{
				mT__69(); 

				}
				break;
			case 20 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:157: T__70
				{
				mT__70(); 

				}
				break;
			case 21 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:163: T__71
				{
				mT__71(); 

				}
				break;
			case 22 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:169: T__72
				{
				mT__72(); 

				}
				break;
			case 23 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:175: T__73
				{
				mT__73(); 

				}
				break;
			case 24 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:181: T__74
				{
				mT__74(); 

				}
				break;
			case 25 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:187: T__75
				{
				mT__75(); 

				}
				break;
			case 26 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:193: T__76
				{
				mT__76(); 

				}
				break;
			case 27 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:199: T__77
				{
				mT__77(); 

				}
				break;
			case 28 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:205: T__78
				{
				mT__78(); 

				}
				break;
			case 29 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:211: T__79
				{
				mT__79(); 

				}
				break;
			case 30 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:217: T__80
				{
				mT__80(); 

				}
				break;
			case 31 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:223: T__81
				{
				mT__81(); 

				}
				break;
			case 32 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:229: T__82
				{
				mT__82(); 

				}
				break;
			case 33 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:235: T__83
				{
				mT__83(); 

				}
				break;
			case 34 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:241: T__84
				{
				mT__84(); 

				}
				break;
			case 35 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:247: T__85
				{
				mT__85(); 

				}
				break;
			case 36 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:253: T__86
				{
				mT__86(); 

				}
				break;
			case 37 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:259: T__87
				{
				mT__87(); 

				}
				break;
			case 38 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:265: T__88
				{
				mT__88(); 

				}
				break;
			case 39 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:271: T__89
				{
				mT__89(); 

				}
				break;
			case 40 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:277: T__90
				{
				mT__90(); 

				}
				break;
			case 41 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:283: T__91
				{
				mT__91(); 

				}
				break;
			case 42 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:289: T__92
				{
				mT__92(); 

				}
				break;
			case 43 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:295: T__93
				{
				mT__93(); 

				}
				break;
			case 44 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:301: T__94
				{
				mT__94(); 

				}
				break;
			case 45 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:307: T__95
				{
				mT__95(); 

				}
				break;
			case 46 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:313: T__96
				{
				mT__96(); 

				}
				break;
			case 47 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:319: T__97
				{
				mT__97(); 

				}
				break;
			case 48 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:325: T__98
				{
				mT__98(); 

				}
				break;
			case 49 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:331: T__99
				{
				mT__99(); 

				}
				break;
			case 50 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:337: T__100
				{
				mT__100(); 

				}
				break;
			case 51 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:344: T__101
				{
				mT__101(); 

				}
				break;
			case 52 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:351: T__102
				{
				mT__102(); 

				}
				break;
			case 53 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:358: T__103
				{
				mT__103(); 

				}
				break;
			case 54 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:365: T__104
				{
				mT__104(); 

				}
				break;
			case 55 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:372: T__105
				{
				mT__105(); 

				}
				break;
			case 56 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:379: T__106
				{
				mT__106(); 

				}
				break;
			case 57 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:386: T__107
				{
				mT__107(); 

				}
				break;
			case 58 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:393: T__108
				{
				mT__108(); 

				}
				break;
			case 59 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:400: T__109
				{
				mT__109(); 

				}
				break;
			case 60 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:407: T__110
				{
				mT__110(); 

				}
				break;
			case 61 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:414: T__111
				{
				mT__111(); 

				}
				break;
			case 62 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:421: T__112
				{
				mT__112(); 

				}
				break;
			case 63 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:428: T__113
				{
				mT__113(); 

				}
				break;
			case 64 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:435: T__114
				{
				mT__114(); 

				}
				break;
			case 65 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:442: T__115
				{
				mT__115(); 

				}
				break;
			case 66 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:449: T__116
				{
				mT__116(); 

				}
				break;
			case 67 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:456: Operation
				{
				mOperation(); 

				}
				break;
			case 68 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:466: AlphaCase
				{
				mAlphaCase(); 

				}
				break;
			case 69 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:476: SepCase
				{
				mSepCase(); 

				}
				break;
			case 70 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:484: NumberToken
				{
				mNumberToken(); 

				}
				break;
			case 71 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:496: StringToken
				{
				mStringToken(); 

				}
				break;
			case 72 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:508: HistoricalData
				{
				mHistoricalData(); 

				}
				break;
			case 73 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:523: WhiteChar
				{
				mWhiteChar(); 

				}
				break;
			case 74 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:533: Tcheat
				{
				mTcheat(); 

				}
				break;
			case 75 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:540: WS
				{
				mWS(); 

				}
				break;
			case 76 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:543: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 77 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:551: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;

		}
	}


	protected DFA12 dfa12 = new DFA12(this);
	static final String DFA12_eotS =
		"\1\uffff\1\51\3\uffff\4\51\2\uffff\1\51\2\uffff\15\51\2\35\3\uffff\2\51"+
		"\3\uffff\1\117\1\121\1\123\1\124\1\uffff\1\126\1\130\1\132\1\134\1\137"+
		"\1\141\1\143\1\144\1\146\1\150\1\153\1\155\1\157\1\161\1\163\1\165\1\167"+
		"\1\171\1\173\1\175\1\177\1\u0081\1\u0083\1\u0085\1\u0087\1\u0089\1\u008b"+
		"\1\u008d\1\u008f\1\u0091\1\u0093\1\u0095\1\u0097\1\u0099\2\uffff\1\u009a"+
		"\1\uffff\1\u009e\1\uffff\1\u00a0\2\uffff\1\u00a2\1\uffff\1\u00a4\1\uffff"+
		"\1\u00a6\1\uffff\1\u00a7\1\uffff\1\u00a9\1\u00ab\1\uffff\1\u00ad\1\uffff"+
		"\1\u00ae\2\uffff\1\u00b0\1\uffff\1\u00b2\1\uffff\1\u00b4\1\u00b6\1\uffff"+
		"\1\u00b8\1\uffff\1\u00ba\1\uffff\1\u00bc\1\uffff\1\u00bd\1\uffff\1\u00bf"+
		"\1\uffff\1\u00c1\1\uffff\1\u00c3\1\uffff\1\u00c5\1\uffff\1\u00c7\1\uffff"+
		"\1\u00c9\1\uffff\1\u00cb\1\uffff\1\u00cc\1\uffff\1\u00ce\1\uffff\1\u00d0"+
		"\3\uffff\1\u00d5\1\uffff\1\u00d7\1\uffff\1\u00d9\1\uffff\1\u00db\1\uffff"+
		"\1\u00dd\1\uffff\1\u00df\1\uffff\1\u00e1\1\uffff\1\u00e3\4\uffff\1\u00e5"+
		"\1\uffff\1\u00e7\1\uffff\1\u00e8\1\uffff\1\u00ea\1\uffff\1\u00ec\2\uffff"+
		"\1\u00ef\1\uffff\1\u00f1\1\uffff\1\u00f3\2\uffff\1\u00f5\1\uffff\1\u00f6"+
		"\1\uffff\1\u00f8\1\uffff\1\u00fa\1\uffff\1\u00fc\1\uffff\1\u00fd\1\uffff"+
		"\1\u00fe\2\uffff\1\u0100\1\uffff\1\u0102\1\uffff\1\u0104\1\uffff\1\u0106"+
		"\1\uffff\1\u0108\1\uffff\1\u010a\1\uffff\1\u010c\2\uffff\1\u010e\1\uffff"+
		"\1\u0110\4\uffff\1\u0115\1\uffff\1\u0117\1\uffff\1\u0119\1\uffff\1\u011b"+
		"\1\uffff\1\u011d\1\uffff\1\u011f\1\uffff\1\u0120\1\uffff\1\u0122\1\uffff"+
		"\1\u0124\4\uffff\1\u0126\1\uffff\1\u0128\2\uffff\1\u012a\1\uffff\1\u012c"+
		"\3\uffff\1\u012e\2\uffff\1\u0130\1\uffff\1\u0132\1\uffff\1\u0134\3\uffff"+
		"\1\u0136\1\uffff\1\u0138\1\uffff\1\u013a\1\uffff\1\u013b\1\uffff\1\u013d"+
		"\1\uffff\1\u013f\1\uffff\1\u0141\3\uffff\1\u0144\4\uffff\1\u0149\1\uffff"+
		"\1\u014b\1\uffff\1\u014d\1\uffff\1\u014f\1\uffff\1\u0151\1\uffff\1\u0153"+
		"\2\uffff\1\u0155\1\uffff\1\u0157\1\uffff\1\u0159\1\uffff\1\u015b\1\uffff"+
		"\1\u015d\3\uffff\1\u0160\3\uffff\1\u0162\1\uffff\1\u0164\1\uffff\1\u0166"+
		"\1\uffff\1\u0168\1\uffff\1\u016a\2\uffff\1\u016c\1\uffff\1\u016e\1\uffff"+
		"\1\u0170\7\uffff\1\u0175\1\uffff\1\u0177\3\uffff\1\u0179\1\uffff\1\u017b"+
		"\1\uffff\1\u017d\1\uffff\1\u017e\1\uffff\1\u0180\1\uffff\1\u0182\1\uffff"+
		"\1\u0183\1\uffff\1\u0185\2\uffff\1\u0188\3\uffff\1\u018e\1\uffff\1\u018f"+
		"\1\uffff\1\u0190\1\uffff\1\u0192\3\uffff\1\u0193\6\uffff\1\u019a\1\uffff"+
		"\1\u019c\1\uffff\1\u019e\1\uffff\1\u01a0\1\uffff\1\u01a2\2\uffff\1\u01a4"+
		"\1\uffff\1\u01a6\2\uffff\1\u01a7\2\uffff\1\u01ac\5\uffff\1\u01b0\13\uffff"+
		"\1\u01b9\1\uffff\1\u01bb\1\uffff\1\u01bc\1\uffff\1\u01bd\1\uffff\1\u01bf"+
		"\1\uffff\1\u01c1\1\uffff\1\u01c2\11\uffff\1\u01c8\10\uffff\1\u01d1\7\uffff"+
		"\1\u01d5\17\uffff\1\u01dd\3\uffff\1\u01df\7\uffff\1\u01e9\1\uffff\1\u01ea"+
		"\11\uffff\1\u01ef\33\uffff";
	static final String DFA12_eofS =
		"\u0204\uffff";
	static final String DFA12_minS =
		"\1\11\1\55\3\uffff\4\55\2\uffff\1\55\2\uffff\16\55\1\60\3\uffff\2\55\2"+
		"\uffff\1\52\4\55\1\uffff\31\55\1\40\10\55\2\uffff\1\55\1\0\1\55\1\0\1"+
		"\55\2\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\2\55\1\0\1\55\1\0\1\55\1\0"+
		"\1\uffff\1\55\1\0\1\55\1\0\2\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1"+
		"\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55"+
		"\1\0\1\55\1\0\1\55\1\0\1\141\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1"+
		"\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\3\uffff\1\55\1\0\1\40\1\0\1\55\1\0"+
		"\1\55\1\0\1\55\2\0\1\55\1\0\1\55\1\0\1\40\1\0\1\uffff\1\55\1\0\1\55\1"+
		"\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\uffff\1\55\1\0\1\55"+
		"\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\uffff\1\40\1\0\1\55"+
		"\1\0\1\142\1\145\1\uffff\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0"+
		"\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\uffff\1\0\1\uffff\1\55\1\0\1\55"+
		"\1\0\1\uffff\1\55\1\0\1\40\1\0\1\uffff\1\0\1\55\1\uffff\1\0\1\40\1\0\1"+
		"\55\1\0\1\55\1\0\2\uffff\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0"+
		"\1\55\1\0\1\55\1\0\1\144\1\0\1\55\1\0\1\157\1\141\1\uffff\1\55\1\0\1\55"+
		"\1\0\1\40\1\0\1\55\1\0\1\55\1\0\1\55\2\0\1\55\1\0\1\55\1\0\1\55\1\0\1"+
		"\55\1\0\1\55\1\0\1\141\1\0\1\55\1\0\1\uffff\1\0\1\40\1\0\1\55\1\0\1\55"+
		"\1\0\1\55\1\0\1\55\2\0\1\40\1\0\1\55\1\0\1\40\1\0\3\uffff\1\166\1\162"+
		"\1\157\1\55\1\0\1\55\1\0\1\uffff\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1"+
		"\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\40\1\55\1\0\1\144\1\0\1\55\1"+
		"\0\1\55\1\0\1\55\1\0\1\40\1\0\1\uffff\1\0\1\55\1\0\1\150\1\0\1\145\1\151"+
		"\1\167\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\2\0\1\55\1\0\1\55\1\0"+
		"\1\uffff\1\55\1\0\1\150\1\40\1\0\3\uffff\1\156\1\55\1\0\2\uffff\1\144"+
		"\1\0\2\uffff\1\150\1\40\1\163\1\40\1\55\1\0\1\40\1\0\1\55\1\0\1\55\1\0"+
		"\1\40\1\0\1\55\1\0\1\55\1\0\1\uffff\1\151\1\157\1\165\1\145\1\0\2\uffff"+
		"\1\55\1\0\1\157\1\160\2\uffff\3\150\1\55\1\0\1\144\1\0\3\uffff\1\0\1\55"+
		"\1\0\1\uffff\1\147\1\167\1\160\3\uffff\1\167\1\40\2\uffff\1\40\2\uffff"+
		"\1\55\1\0\2\uffff\1\55\1\0\1\150\1\145\1\160\1\156\1\150\1\151\1\55\1"+
		"\0\1\55\1\0\1\145\1\162\1\157\1\40\4\uffff\1\55\1\0\1\uffff\1\162\1\40"+
		"\1\162\1\150\1\uffff\1\40\1\150\1\164\2\uffff\1\150\2\uffff\1\40\2\uffff"+
		"\1\142\1\162\1\145\1\141\1\153\1\40\1\144\2\uffff";
	static final String DFA12_maxS =
		"\2\172\3\uffff\4\172\2\uffff\1\172\2\uffff\16\172\1\71\3\uffff\2\172\2"+
		"\uffff\1\57\4\172\1\uffff\42\172\2\uffff\1\172\1\0\1\172\1\0\1\172\2\0"+
		"\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\2\172\1\0\1\172\1\0\1\172\1\0"+
		"\1\uffff\1\172\1\0\1\172\1\0\2\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1"+
		"\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1"+
		"\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\167\1\0\1\172\1\0\1\172\1\0\1"+
		"\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\3\uffff\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\2\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\uffff\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1"+
		"\0\1\172\1\0\1\uffff\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0"+
		"\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\172\1\0\1\142\1\165\1\uffff\1"+
		"\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1"+
		"\172\1\0\1\172\1\0\1\uffff\1\0\1\uffff\1\172\1\0\1\172\1\0\1\uffff\1\172"+
		"\1\0\1\172\1\0\1\uffff\1\0\1\172\1\uffff\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\2\uffff\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1"+
		"\0\1\172\1\0\1\165\1\0\1\172\1\0\1\157\1\154\1\uffff\1\172\1\0\1\172\1"+
		"\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\2\0\1\172\1\0\1\172\1\0\1\172\1"+
		"\0\1\172\1\0\1\172\1\0\1\141\1\0\1\172\1\0\1\uffff\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\2\0\1\172\1\0\1\172\1\0\1\172\1\0\3\uffff"+
		"\1\166\1\162\1\157\1\172\1\0\1\172\1\0\1\uffff\1\0\1\172\1\0\1\172\1\0"+
		"\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\40\1\172"+
		"\1\0\1\165\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\0\1\172"+
		"\1\0\1\164\1\0\1\145\1\151\1\167\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1"+
		"\0\1\172\2\0\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\163\1\172\1\0\3\uffff"+
		"\1\160\1\172\1\0\2\uffff\1\165\1\0\2\uffff\1\162\1\40\1\163\1\40\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff"+
		"\1\151\1\157\1\165\1\163\1\0\2\uffff\1\172\1\0\1\157\1\160\2\uffff\1\164"+
		"\1\150\1\164\1\172\1\0\1\165\1\0\3\uffff\1\0\1\172\1\0\1\uffff\1\147\1"+
		"\167\1\160\3\uffff\1\167\1\40\2\uffff\1\40\2\uffff\1\172\1\0\2\uffff\1"+
		"\172\1\0\1\150\1\145\1\160\1\156\1\164\1\167\1\172\1\0\1\172\1\0\1\145"+
		"\1\162\1\157\1\40\4\uffff\1\172\1\0\1\uffff\1\162\1\40\1\162\1\164\1\uffff"+
		"\1\40\1\154\1\164\2\uffff\1\154\2\uffff\1\40\2\uffff\1\142\1\162\1\145"+
		"\1\141\1\153\1\40\1\165\2\uffff";
	static final String DFA12_acceptS =
		"\2\uffff\1\2\1\3\1\4\4\uffff\1\11\1\12\1\uffff\1\14\1\15\17\uffff\1\105"+
		"\1\106\1\107\2\uffff\1\111\1\113\5\uffff\1\104\42\uffff\1\114\1\115\26"+
		"\uffff\1\13\65\uffff\1\1\1\103\1\112\21\uffff\1\10\16\uffff\1\20\16\uffff"+
		"\1\37\6\uffff\1\52\22\uffff\1\22\1\uffff\1\5\4\uffff\1\110\4\uffff\1\61"+
		"\2\uffff\1\62\7\uffff\1\102\1\17\24\uffff\1\51\33\uffff\1\16\22\uffff"+
		"\1\40\1\41\1\42\7\uffff\1\70\36\uffff\1\32\27\uffff\1\6\5\uffff\1\75\1"+
		"\76\1\77\3\uffff\1\23\1\24\2\uffff\1\33\1\34\22\uffff\1\7\5\uffff\1\100"+
		"\1\101\4\uffff\1\35\1\36\7\uffff\1\71\1\72\1\73\3\uffff\1\31\3\uffff\1"+
		"\63\1\64\1\74\2\uffff\1\43\1\44\1\uffff\1\47\1\50\2\uffff\1\66\1\67\20"+
		"\uffff\1\27\1\30\1\45\1\46\2\uffff\1\21\4\uffff\1\65\3\uffff\1\25\1\26"+
		"\1\uffff\1\55\1\56\1\uffff\1\53\1\54\7\uffff\1\57\1\60";
	static final String DFA12_specialS =
		"\117\uffff\1\2\1\uffff\1\31\1\uffff\1\1\1\32\1\uffff\1\44\1\uffff\1\45"+
		"\1\uffff\1\112\1\uffff\1\130\2\uffff\1\175\1\uffff\1\134\1\uffff\1\46"+
		"\2\uffff\1\36\1\uffff\1\u008f\2\uffff\1\u009e\1\uffff\1\63\1\uffff\1\135"+
		"\1\uffff\1\47\1\uffff\1\u0086\1\uffff\1\u0087\1\uffff\1\3\1\uffff\1\153"+
		"\1\uffff\1\174\1\uffff\1\76\1\uffff\1\142\1\uffff\1\140\1\uffff\1\22\1"+
		"\uffff\1\0\1\uffff\1\115\1\uffff\1\141\1\uffff\1\33\1\uffff\1\163\1\uffff"+
		"\1\u0099\1\uffff\1\42\1\uffff\1\67\1\uffff\1\133\1\uffff\1\u009a\1\uffff"+
		"\1\150\4\uffff\1\131\1\uffff\1\u008b\1\uffff\1\164\1\uffff\1\4\1\uffff"+
		"\1\27\1\u009f\1\uffff\1\161\1\uffff\1\30\1\uffff\1\104\2\uffff\1\u0088"+
		"\1\uffff\1\17\1\uffff\1\75\1\uffff\1\u008a\1\uffff\1\65\1\uffff\1\u0094"+
		"\1\uffff\1\5\2\uffff\1\37\1\uffff\1\25\1\uffff\1\56\1\uffff\1\21\1\uffff"+
		"\1\23\1\uffff\1\u0080\1\uffff\1\15\2\uffff\1\162\1\uffff\1\132\4\uffff"+
		"\1\40\1\uffff\1\127\1\uffff\1\156\1\uffff\1\62\1\uffff\1\123\1\uffff\1"+
		"\124\1\uffff\1\24\1\uffff\1\u0090\1\uffff\1\53\1\uffff\1\u0085\2\uffff"+
		"\1\u009d\1\uffff\1\u009b\2\uffff\1\170\1\uffff\1\171\1\uffff\1\u008d\2"+
		"\uffff\1\u00a0\1\uffff\1\111\1\uffff\1\54\1\uffff\1\116\3\uffff\1\35\1"+
		"\uffff\1\101\1\uffff\1\34\1\uffff\1\66\1\uffff\1\16\1\uffff\1\122\1\uffff"+
		"\1\u0082\1\uffff\1\73\1\uffff\1\11\4\uffff\1\u0092\1\uffff\1\113\1\uffff"+
		"\1\u0081\1\uffff\1\172\1\uffff\1\143\1\uffff\1\55\1\u00a1\1\uffff\1\114"+
		"\1\uffff\1\166\1\uffff\1\144\1\uffff\1\41\1\uffff\1\u008e\1\uffff\1\u009c"+
		"\1\uffff\1\u0093\1\uffff\1\151\1\uffff\1\u0091\1\uffff\1\125\1\uffff\1"+
		"\u0089\1\uffff\1\152\1\uffff\1\136\1\u00a2\1\uffff\1\u0095\1\uffff\1\u0084"+
		"\1\uffff\1\106\7\uffff\1\14\1\uffff\1\107\1\uffff\1\70\1\uffff\1\145\1"+
		"\uffff\1\u0096\1\uffff\1\157\1\uffff\1\50\1\uffff\1\126\1\uffff\1\26\1"+
		"\uffff\1\u0097\1\uffff\1\u008c\2\uffff\1\57\1\uffff\1\13\1\uffff\1\173"+
		"\1\uffff\1\43\1\uffff\1\110\1\uffff\1\103\1\uffff\1\137\1\uffff\1\146"+
		"\1\uffff\1\117\4\uffff\1\72\1\uffff\1\u0083\1\uffff\1\71\1\uffff\1\52"+
		"\1\uffff\1\102\1\u00a3\1\uffff\1\20\1\uffff\1\74\2\uffff\1\51\2\uffff"+
		"\1\77\5\uffff\1\154\3\uffff\1\160\7\uffff\1\167\1\uffff\1\12\1\uffff\1"+
		"\155\1\uffff\1\121\1\uffff\1\u0098\1\uffff\1\105\1\uffff\1\120\5\uffff"+
		"\1\176\3\uffff\1\10\10\uffff\1\177\1\uffff\1\64\3\uffff\1\6\1\uffff\1"+
		"\100\17\uffff\1\147\3\uffff\1\61\7\uffff\1\165\1\uffff\1\7\11\uffff\1"+
		"\60\32\uffff}>";
	static final String[] DFA12_transitionS = {
			"\2\43\1\uffff\2\43\22\uffff\1\42\1\uffff\1\37\2\uffff\1\14\2\uffff\1"+
			"\11\1\2\2\uffff\1\4\1\34\1\35\1\44\12\36\1\uffff\1\15\5\uffff\15\41\1"+
			"\20\14\41\1\12\1\uffff\1\3\1\uffff\1\33\1\uffff\1\1\1\21\1\22\1\5\1\23"+
			"\1\24\1\25\1\32\1\26\2\41\1\6\1\7\1\10\1\13\1\27\1\41\1\30\1\31\1\16"+
			"\1\41\1\40\1\17\3\41",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\3\50\1\46\7\50"+
			"\1\47\1\50\1\45\14\50",
			"",
			"",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\1\52\7\50\1\53"+
			"\21\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\54\11"+
			"\50\1\55\13\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\1\56\15\50\1"+
			"\57\13\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\60\13"+
			"\50",
			"",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\17\50\1\63\1"+
			"\50\1\61\3\50\1\62\4\50",
			"",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\65\2"+
			"\50\1\64\6\50\1\66\1\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10\50\1\67\21"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\1\70\31\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\71\17"+
			"\50\1\72\5\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\13\50\1\74\5"+
			"\50\1\73\10\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\75\1"+
			"\50\1\76\1\77\11\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\100\13"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\101\2"+
			"\50\1\102\10\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\103\7"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\21\50\1\104\10"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\105\25"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\13\50\1\106\1"+
			"\107\2\50\1\110\3\50\1\111\6\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10\50\1\112\21"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\12\36",
			"",
			"",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\113\13"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"",
			"",
			"\1\114\4\uffff\1\115",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\3\50\1\116\26"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\1\120\31\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\122\7"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\30\50\1\125\1"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\21\50\1\127\10"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\131\14"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\26\50\1\133\3"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\12\50\1\136\10"+
			"\50\1\135\6\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\21\50\1\140\10"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\23\50\1\142\6"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\145\25"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\147\25"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\152\17"+
			"\50\1\151\5\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\13\50\1\154\16"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\17\50\1\156\12"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\23\50\1\160\6"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\15\50\1\162\14\50\4\uffff\1\50\1\uffff\32"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\1\164\31\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\13\50\1\166\16"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\170\13"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\172\13"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\3\50\1\174\26"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\176\7"+
			"\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\24\50\1\u0080"+
			"\5\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\21\50\1\u0082"+
			"\10\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u0084"+
			"\25\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u0086"+
			"\25\50",
			"\1\u0088\14\uffff\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff"+
			"\32\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u008a"+
			"\25\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\25\50\1\u008c"+
			"\4\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\u008e"+
			"\13\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\u0090"+
			"\13\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\1\u0092\31\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\1\u0094\31\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\6\50\1\u0096"+
			"\23\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\13\50\1\u0098"+
			"\16\50",
			"",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\17\50\1\u009d"+
			"\12\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\u009f"+
			"\13\50",
			"\1\uffff",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\u00a1"+
			"\7\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u00a3"+
			"\25\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10\50\1\u00a5"+
			"\21\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\2\50\1\u00a8"+
			"\27\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u00aa"+
			"\25\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u00ac"+
			"\25\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\21\50\1\u00af"+
			"\10\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\u00b1"+
			"\14\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\23\50\1\u00b3"+
			"\6\50",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\u00b5"+
			"\14\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u00b7"+
			"\25\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u00b9"+
			"\25\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\7\50\1\u00bb"+
			"\22\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\21\50\1\u00be"+
			"\10\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\13\50\1\u00c0"+
			"\16\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\u00c2"+
			"\7\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\u00c4"+
			"\7\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10\50\1\u00c6"+
			"\21\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10\50\1\u00c8"+
			"\21\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\1\u00ca\31\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\u00cd"+
			"\7\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u00cf"+
			"\25\50",
			"\1\uffff",
			"\1\u00d1\1\u00d2\24\uffff\1\u00d3",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\2\50\1\u00d4"+
			"\27\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u00d6"+
			"\25\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\17\50\1\u00d8"+
			"\12\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\u00da"+
			"\13\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\u00dc"+
			"\14\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\21\50\1\u00de"+
			"\10\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\7\50\1\u00e0"+
			"\22\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\24\50\1\u00e2"+
			"\5\50",
			"\1\uffff",
			"",
			"",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\23\50\1\u00e4"+
			"\6\50",
			"\1\uffff",
			"\1\u00e6\14\uffff\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff"+
			"\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\2\50\1\u00e9"+
			"\27\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u00eb"+
			"\25\50",
			"\1\uffff",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\7\50\1\u00ee"+
			"\22\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\u00f0"+
			"\7\50",
			"\1\uffff",
			"\1\u00f2\14\uffff\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff"+
			"\32\50",
			"\1\uffff",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\21\50\1\u00f4"+
			"\10\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\7\50\1\u00f7"+
			"\22\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\3\50\1\u00f9"+
			"\26\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\21\50\1\u00fb"+
			"\10\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10\50\1\u00ff"+
			"\21\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10\50\1\u0101"+
			"\21\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\u0103"+
			"\7\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u0105"+
			"\25\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\u0107"+
			"\14\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\13\50\1\u0109"+
			"\16\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\13\50\1\u010b"+
			"\16\50",
			"\1\uffff",
			"",
			"\1\u010d\14\uffff\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff"+
			"\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\3\50\1\u010f"+
			"\26\50",
			"\1\uffff",
			"\1\u0111",
			"\1\u0112\17\uffff\1\u0113",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\u0114"+
			"\13\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\21\50\1\u0116"+
			"\10\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u0118"+
			"\25\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\23\50\1\u011a"+
			"\6\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\u011c"+
			"\14\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\23\50\1\u011e"+
			"\6\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\14\50\1\u0121"+
			"\15\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10\50\1\u0123"+
			"\21\50",
			"\1\uffff",
			"",
			"\1\uffff",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\23\50\1\u0125"+
			"\6\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\u0127"+
			"\14\50",
			"\1\uffff",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10\50\1\u0129"+
			"\21\50",
			"\1\uffff",
			"\1\u012b\14\uffff\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff"+
			"\32\50",
			"\1\uffff",
			"",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10\50\1\u012d"+
			"\21\50",
			"",
			"\1\uffff",
			"\1\u012f\14\uffff\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff"+
			"\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\u0131"+
			"\7\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\1\u0133\31\50",
			"\1\uffff",
			"",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\u0135"+
			"\7\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\u0137"+
			"\7\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u0139"+
			"\25\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\6\50\1\u013c"+
			"\23\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\u013e"+
			"\13\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\u0140"+
			"\7\50",
			"\1\uffff",
			"\1\u0142\20\uffff\1\u0143",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\1\u0145",
			"\1\u0146\12\uffff\1\u0147",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\u0148"+
			"\14\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\u014a"+
			"\7\50",
			"\1\uffff",
			"\1\u014c\14\uffff\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff"+
			"\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\7\50\1\u014e"+
			"\22\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10\50\1\u0150"+
			"\21\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10\50\1\u0152"+
			"\21\50",
			"\1\uffff",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u0154"+
			"\25\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\25\50\1\u0156"+
			"\4\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10\50\1\u0158"+
			"\21\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\23\50\1\u015a"+
			"\6\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\u015c"+
			"\14\50",
			"\1\uffff",
			"\1\u015e",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\3\50\1\u015f"+
			"\26\50",
			"\1\uffff",
			"",
			"\1\uffff",
			"\1\u0161\14\uffff\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff"+
			"\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\u0163"+
			"\14\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\7\50\1\u0165"+
			"\22\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\7\50\1\u0167"+
			"\22\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\u0169"+
			"\7\50",
			"\1\uffff",
			"\1\uffff",
			"\1\u016b\14\uffff\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff"+
			"\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\u016d"+
			"\14\50",
			"\1\uffff",
			"\1\u016f\14\uffff\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff"+
			"\32\50",
			"\1\uffff",
			"",
			"",
			"",
			"\1\u0171",
			"\1\u0172",
			"\1\u0173",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\3\50\1\u0174"+
			"\26\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u0176"+
			"\25\50",
			"\1\uffff",
			"",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u0178"+
			"\25\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\u017a"+
			"\14\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\u017c"+
			"\14\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u017f"+
			"\25\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\u0181"+
			"\13\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\6\50\1\u0184"+
			"\23\50",
			"\1\uffff",
			"\1\u0186",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u0187"+
			"\25\50",
			"\1\uffff",
			"\1\u0189\1\uffff\1\u018a\5\uffff\1\u018b\10\uffff\1\u018c",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\2\50\1\u018d"+
			"\27\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\1\u0191\14\uffff\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff"+
			"\32\50",
			"\1\uffff",
			"",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\1\u0194\13\uffff\1\u0195",
			"\1\uffff",
			"\1\u0196",
			"\1\u0197",
			"\1\u0198",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10\50\1\u0199"+
			"\21\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\u019b"+
			"\7\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\3\50\1\u019d"+
			"\26\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\6\50\1\u019f"+
			"\23\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\6\50\1\u01a1"+
			"\23\50",
			"\1\uffff",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\21\50\1\u01a3\10\50\4\uffff\1\50\1\uffff"+
			"\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\u01a5"+
			"\14\50",
			"\1\uffff",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\1\u01a8\3\uffff\1\u01a9\6\uffff\1\u01aa",
			"\1\u01ab\14\uffff\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff"+
			"\32\50",
			"\1\uffff",
			"",
			"",
			"",
			"\1\u01ad\1\uffff\1\u01ae",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u01af"+
			"\25\50",
			"\1\uffff",
			"",
			"",
			"\1\u01b1\20\uffff\1\u01b2",
			"\1\uffff",
			"",
			"",
			"\1\u01b3\11\uffff\1\u01b4",
			"\1\u01b5",
			"\1\u01b6",
			"\1\u01b7",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\23\50\1\u01b8"+
			"\6\50",
			"\1\uffff",
			"\1\u01ba\14\uffff\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff"+
			"\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\1\u01be\14\uffff\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff"+
			"\32\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\1\u01c0\31\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"",
			"\1\u01c3",
			"\1\u01c4",
			"\1\u01c5",
			"\1\u01c6\15\uffff\1\u01c7",
			"\1\uffff",
			"",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\1\u01c9",
			"\1\u01ca",
			"",
			"",
			"\1\u01cb\13\uffff\1\u01cc",
			"\1\u01cd",
			"\1\u01ce\13\uffff\1\u01cf",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10\50\1\u01d0"+
			"\21\50",
			"\1\uffff",
			"\1\u01d2\20\uffff\1\u01d3",
			"\1\uffff",
			"",
			"",
			"",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\23\50\1\u01d4"+
			"\6\50",
			"\1\uffff",
			"",
			"\1\u01d6",
			"\1\u01d7",
			"\1\u01d8",
			"",
			"",
			"",
			"\1\u01d9",
			"\1\u01da",
			"",
			"",
			"\1\u01db",
			"",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\u01dc"+
			"\13\50",
			"\1\uffff",
			"",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u01de"+
			"\25\50",
			"\1\uffff",
			"\1\u01e0",
			"\1\u01e1",
			"\1\u01e2",
			"\1\u01e3",
			"\1\u01e4\13\uffff\1\u01e5",
			"\1\u01e6\15\uffff\1\u01e7",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\u01e8"+
			"\14\50",
			"\1\uffff",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"\1\u01eb",
			"\1\u01ec",
			"\1\u01ed",
			"\1\u01ee",
			"",
			"",
			"",
			"",
			"\2\50\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
			"\1\uffff",
			"",
			"\1\u01f0",
			"\1\u01f1",
			"\1\u01f2",
			"\1\u01f3\13\uffff\1\u01f4",
			"",
			"\1\u01f5",
			"\1\u01f6\3\uffff\1\u01f7",
			"\1\u01f8",
			"",
			"",
			"\1\u01f9\3\uffff\1\u01fa",
			"",
			"",
			"\1\u01fb",
			"",
			"",
			"\1\u01fc",
			"\1\u01fd",
			"\1\u01fe",
			"\1\u01ff",
			"\1\u0200",
			"\1\u0201",
			"\1\u0202\20\uffff\1\u0203",
			"",
			""
	};

	static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
	static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
	static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
	static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
	static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
	static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
	static final short[][] DFA12_transition;

	static {
		int numStates = DFA12_transitionS.length;
		DFA12_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
		}
	}

	protected class DFA12 extends DFA {

		public DFA12(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 12;
			this.eot = DFA12_eot;
			this.eof = DFA12_eof;
			this.min = DFA12_min;
			this.max = DFA12_max;
			this.accept = DFA12_accept;
			this.special = DFA12_special;
			this.transition = DFA12_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( AND | CLOSEPARENTEHSIS | CLOSESQRT | COMMA | DAYS | LENIENT | MATCHING | NOT | OPENPARENTEHSIS | OPENSQRT | OR | PERCENT | SEMICOLUMN | TRUTHOF | WITH | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | Operation | AlphaCase | SepCase | NumberToken | StringToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA12_133 = input.LA(1);
						 
						int index12_133 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_133);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA12_83 = input.LA(1);
						 
						int index12_83 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_83);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA12_79 = input.LA(1);
						 
						int index12_79 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_79);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA12_119 = input.LA(1);
						 
						int index12_119 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_119);
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA12_164 = input.LA(1);
						 
						int index12_164 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_164);
						if ( s>=0 ) return s;
						break;

					case 5 : 
						int LA12_188 = input.LA(1);
						 
						int index12_188 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_188);
						if ( s>=0 ) return s;
						break;

					case 6 : 
						int LA12_447 = input.LA(1);
						 
						int index12_447 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_447);
						if ( s>=0 ) return s;
						break;

					case 7 : 
						int LA12_479 = input.LA(1);
						 
						int index12_479 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_479);
						if ( s>=0 ) return s;
						break;

					case 8 : 
						int LA12_432 = input.LA(1);
						 
						int index12_432 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_432);
						if ( s>=0 ) return s;
						break;

					case 9 : 
						int LA12_272 = input.LA(1);
						 
						int index12_272 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_272);
						if ( s>=0 ) return s;
						break;

					case 10 : 
						int LA12_412 = input.LA(1);
						 
						int index12_412 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_412);
						if ( s>=0 ) return s;
						break;

					case 11 : 
						int LA12_354 = input.LA(1);
						 
						int index12_354 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_354);
						if ( s>=0 ) return s;
						break;

					case 12 : 
						int LA12_329 = input.LA(1);
						 
						int index12_329 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_329);
						if ( s>=0 ) return s;
						break;

					case 13 : 
						int LA12_203 = input.LA(1);
						 
						int index12_203 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_203);
						if ( s>=0 ) return s;
						break;

					case 14 : 
						int LA12_264 = input.LA(1);
						 
						int index12_264 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_264);
						if ( s>=0 ) return s;
						break;

					case 15 : 
						int LA12_178 = input.LA(1);
						 
						int index12_178 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_178);
						if ( s>=0 ) return s;
						break;

					case 16 : 
						int LA12_384 = input.LA(1);
						 
						int index12_384 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_384);
						if ( s>=0 ) return s;
						break;

					case 17 : 
						int LA12_197 = input.LA(1);
						 
						int index12_197 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_197);
						if ( s>=0 ) return s;
						break;

					case 18 : 
						int LA12_131 = input.LA(1);
						 
						int index12_131 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_131);
						if ( s>=0 ) return s;
						break;

					case 19 : 
						int LA12_199 = input.LA(1);
						 
						int index12_199 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_199);
						if ( s>=0 ) return s;
						break;

					case 20 : 
						int LA12_225 = input.LA(1);
						 
						int index12_225 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_225);
						if ( s>=0 ) return s;
						break;

					case 21 : 
						int LA12_193 = input.LA(1);
						 
						int index12_193 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_193);
						if ( s>=0 ) return s;
						break;

					case 22 : 
						int LA12_345 = input.LA(1);
						 
						int index12_345 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_345);
						if ( s>=0 ) return s;
						break;

					case 23 : 
						int LA12_166 = input.LA(1);
						 
						int index12_166 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_166);
						if ( s>=0 ) return s;
						break;

					case 24 : 
						int LA12_171 = input.LA(1);
						 
						int index12_171 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_171);
						if ( s>=0 ) return s;
						break;

					case 25 : 
						int LA12_81 = input.LA(1);
						 
						int index12_81 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_81);
						if ( s>=0 ) return s;
						break;

					case 26 : 
						int LA12_84 = input.LA(1);
						 
						int index12_84 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_84);
						if ( s>=0 ) return s;
						break;

					case 27 : 
						int LA12_139 = input.LA(1);
						 
						int index12_139 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_139);
						if ( s>=0 ) return s;
						break;

					case 28 : 
						int LA12_260 = input.LA(1);
						 
						int index12_260 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_260);
						if ( s>=0 ) return s;
						break;

					case 29 : 
						int LA12_256 = input.LA(1);
						 
						int index12_256 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_256);
						if ( s>=0 ) return s;
						break;

					case 30 : 
						int LA12_102 = input.LA(1);
						 
						int index12_102 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_102);
						if ( s>=0 ) return s;
						break;

					case 31 : 
						int LA12_191 = input.LA(1);
						 
						int index12_191 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_191);
						if ( s>=0 ) return s;
						break;

					case 32 : 
						int LA12_213 = input.LA(1);
						 
						int index12_213 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_213);
						if ( s>=0 ) return s;
						break;

					case 33 : 
						int LA12_296 = input.LA(1);
						 
						int index12_296 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_296);
						if ( s>=0 ) return s;
						break;

					case 34 : 
						int LA12_145 = input.LA(1);
						 
						int index12_145 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_145);
						if ( s>=0 ) return s;
						break;

					case 35 : 
						int LA12_358 = input.LA(1);
						 
						int index12_358 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_358);
						if ( s>=0 ) return s;
						break;

					case 36 : 
						int LA12_86 = input.LA(1);
						 
						int index12_86 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_86);
						if ( s>=0 ) return s;
						break;

					case 37 : 
						int LA12_88 = input.LA(1);
						 
						int index12_88 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_88);
						if ( s>=0 ) return s;
						break;

					case 38 : 
						int LA12_99 = input.LA(1);
						 
						int index12_99 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_99);
						if ( s>=0 ) return s;
						break;

					case 39 : 
						int LA12_113 = input.LA(1);
						 
						int index12_113 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_113);
						if ( s>=0 ) return s;
						break;

					case 40 : 
						int LA12_341 = input.LA(1);
						 
						int index12_341 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_341);
						if ( s>=0 ) return s;
						break;

					case 41 : 
						int LA12_389 = input.LA(1);
						 
						int index12_389 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_389);
						if ( s>=0 ) return s;
						break;

					case 42 : 
						int LA12_379 = input.LA(1);
						 
						int index12_379 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_379);
						if ( s>=0 ) return s;
						break;

					case 43 : 
						int LA12_229 = input.LA(1);
						 
						int index12_229 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_229);
						if ( s>=0 ) return s;
						break;

					case 44 : 
						int LA12_250 = input.LA(1);
						 
						int index12_250 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_250);
						if ( s>=0 ) return s;
						break;

					case 45 : 
						int LA12_287 = input.LA(1);
						 
						int index12_287 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_287);
						if ( s>=0 ) return s;
						break;

					case 46 : 
						int LA12_195 = input.LA(1);
						 
						int index12_195 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_195);
						if ( s>=0 ) return s;
						break;

					case 47 : 
						int LA12_352 = input.LA(1);
						 
						int index12_352 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_352);
						if ( s>=0 ) return s;
						break;

					case 48 : 
						int LA12_489 = input.LA(1);
						 
						int index12_489 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_489);
						if ( s>=0 ) return s;
						break;

					case 49 : 
						int LA12_469 = input.LA(1);
						 
						int index12_469 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_469);
						if ( s>=0 ) return s;
						break;

					case 50 : 
						int LA12_219 = input.LA(1);
						 
						int index12_219 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_219);
						if ( s>=0 ) return s;
						break;

					case 51 : 
						int LA12_109 = input.LA(1);
						 
						int index12_109 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_109);
						if ( s>=0 ) return s;
						break;

					case 52 : 
						int LA12_443 = input.LA(1);
						 
						int index12_443 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_443);
						if ( s>=0 ) return s;
						break;

					case 53 : 
						int LA12_184 = input.LA(1);
						 
						int index12_184 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_184);
						if ( s>=0 ) return s;
						break;

					case 54 : 
						int LA12_262 = input.LA(1);
						 
						int index12_262 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_262);
						if ( s>=0 ) return s;
						break;

					case 55 : 
						int LA12_147 = input.LA(1);
						 
						int index12_147 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_147);
						if ( s>=0 ) return s;
						break;

					case 56 : 
						int LA12_333 = input.LA(1);
						 
						int index12_333 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_333);
						if ( s>=0 ) return s;
						break;

					case 57 : 
						int LA12_377 = input.LA(1);
						 
						int index12_377 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_377);
						if ( s>=0 ) return s;
						break;

					case 58 : 
						int LA12_373 = input.LA(1);
						 
						int index12_373 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_373);
						if ( s>=0 ) return s;
						break;

					case 59 : 
						int LA12_270 = input.LA(1);
						 
						int index12_270 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_270);
						if ( s>=0 ) return s;
						break;

					case 60 : 
						int LA12_386 = input.LA(1);
						 
						int index12_386 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_386);
						if ( s>=0 ) return s;
						break;

					case 61 : 
						int LA12_180 = input.LA(1);
						 
						int index12_180 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_180);
						if ( s>=0 ) return s;
						break;

					case 62 : 
						int LA12_125 = input.LA(1);
						 
						int index12_125 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_125);
						if ( s>=0 ) return s;
						break;

					case 63 : 
						int LA12_392 = input.LA(1);
						 
						int index12_392 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_392);
						if ( s>=0 ) return s;
						break;

					case 64 : 
						int LA12_449 = input.LA(1);
						 
						int index12_449 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_449);
						if ( s>=0 ) return s;
						break;

					case 65 : 
						int LA12_258 = input.LA(1);
						 
						int index12_258 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_258);
						if ( s>=0 ) return s;
						break;

					case 66 : 
						int LA12_381 = input.LA(1);
						 
						int index12_381 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_381);
						if ( s>=0 ) return s;
						break;

					case 67 : 
						int LA12_362 = input.LA(1);
						 
						int index12_362 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_362);
						if ( s>=0 ) return s;
						break;

					case 68 : 
						int LA12_173 = input.LA(1);
						 
						int index12_173 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_173);
						if ( s>=0 ) return s;
						break;

					case 69 : 
						int LA12_420 = input.LA(1);
						 
						int index12_420 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_420);
						if ( s>=0 ) return s;
						break;

					case 70 : 
						int LA12_321 = input.LA(1);
						 
						int index12_321 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_321);
						if ( s>=0 ) return s;
						break;

					case 71 : 
						int LA12_331 = input.LA(1);
						 
						int index12_331 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_331);
						if ( s>=0 ) return s;
						break;

					case 72 : 
						int LA12_360 = input.LA(1);
						 
						int index12_360 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_360);
						if ( s>=0 ) return s;
						break;

					case 73 : 
						int LA12_248 = input.LA(1);
						 
						int index12_248 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_248);
						if ( s>=0 ) return s;
						break;

					case 74 : 
						int LA12_90 = input.LA(1);
						 
						int index12_90 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_90);
						if ( s>=0 ) return s;
						break;

					case 75 : 
						int LA12_279 = input.LA(1);
						 
						int index12_279 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_279);
						if ( s>=0 ) return s;
						break;

					case 76 : 
						int LA12_290 = input.LA(1);
						 
						int index12_290 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_290);
						if ( s>=0 ) return s;
						break;

					case 77 : 
						int LA12_135 = input.LA(1);
						 
						int index12_135 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_135);
						if ( s>=0 ) return s;
						break;

					case 78 : 
						int LA12_252 = input.LA(1);
						 
						int index12_252 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_252);
						if ( s>=0 ) return s;
						break;

					case 79 : 
						int LA12_368 = input.LA(1);
						 
						int index12_368 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_368);
						if ( s>=0 ) return s;
						break;

					case 80 : 
						int LA12_422 = input.LA(1);
						 
						int index12_422 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_422);
						if ( s>=0 ) return s;
						break;

					case 81 : 
						int LA12_416 = input.LA(1);
						 
						int index12_416 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_416);
						if ( s>=0 ) return s;
						break;

					case 82 : 
						int LA12_266 = input.LA(1);
						 
						int index12_266 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_266);
						if ( s>=0 ) return s;
						break;

					case 83 : 
						int LA12_221 = input.LA(1);
						 
						int index12_221 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_221);
						if ( s>=0 ) return s;
						break;

					case 84 : 
						int LA12_223 = input.LA(1);
						 
						int index12_223 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_223);
						if ( s>=0 ) return s;
						break;

					case 85 : 
						int LA12_308 = input.LA(1);
						 
						int index12_308 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_308);
						if ( s>=0 ) return s;
						break;

					case 86 : 
						int LA12_343 = input.LA(1);
						 
						int index12_343 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_343);
						if ( s>=0 ) return s;
						break;

					case 87 : 
						int LA12_215 = input.LA(1);
						 
						int index12_215 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_215);
						if ( s>=0 ) return s;
						break;

					case 88 : 
						int LA12_92 = input.LA(1);
						 
						int index12_92 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_92);
						if ( s>=0 ) return s;
						break;

					case 89 : 
						int LA12_158 = input.LA(1);
						 
						int index12_158 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_158);
						if ( s>=0 ) return s;
						break;

					case 90 : 
						int LA12_208 = input.LA(1);
						 
						int index12_208 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_208);
						if ( s>=0 ) return s;
						break;

					case 91 : 
						int LA12_149 = input.LA(1);
						 
						int index12_149 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_149);
						if ( s>=0 ) return s;
						break;

					case 92 : 
						int LA12_97 = input.LA(1);
						 
						int index12_97 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_97);
						if ( s>=0 ) return s;
						break;

					case 93 : 
						int LA12_111 = input.LA(1);
						 
						int index12_111 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_111);
						if ( s>=0 ) return s;
						break;

					case 94 : 
						int LA12_314 = input.LA(1);
						 
						int index12_314 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_314);
						if ( s>=0 ) return s;
						break;

					case 95 : 
						int LA12_364 = input.LA(1);
						 
						int index12_364 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_364);
						if ( s>=0 ) return s;
						break;

					case 96 : 
						int LA12_129 = input.LA(1);
						 
						int index12_129 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_129);
						if ( s>=0 ) return s;
						break;

					case 97 : 
						int LA12_137 = input.LA(1);
						 
						int index12_137 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_137);
						if ( s>=0 ) return s;
						break;

					case 98 : 
						int LA12_127 = input.LA(1);
						 
						int index12_127 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_127);
						if ( s>=0 ) return s;
						break;

					case 99 : 
						int LA12_285 = input.LA(1);
						 
						int index12_285 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_285);
						if ( s>=0 ) return s;
						break;

					case 100 : 
						int LA12_294 = input.LA(1);
						 
						int index12_294 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_294);
						if ( s>=0 ) return s;
						break;

					case 101 : 
						int LA12_335 = input.LA(1);
						 
						int index12_335 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_335);
						if ( s>=0 ) return s;
						break;

					case 102 : 
						int LA12_366 = input.LA(1);
						 
						int index12_366 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_366);
						if ( s>=0 ) return s;
						break;

					case 103 : 
						int LA12_465 = input.LA(1);
						 
						int index12_465 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_465);
						if ( s>=0 ) return s;
						break;

					case 104 : 
						int LA12_153 = input.LA(1);
						 
						int index12_153 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_153);
						if ( s>=0 ) return s;
						break;

					case 105 : 
						int LA12_304 = input.LA(1);
						 
						int index12_304 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_304);
						if ( s>=0 ) return s;
						break;

					case 106 : 
						int LA12_312 = input.LA(1);
						 
						int index12_312 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_312);
						if ( s>=0 ) return s;
						break;

					case 107 : 
						int LA12_121 = input.LA(1);
						 
						int index12_121 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_121);
						if ( s>=0 ) return s;
						break;

					case 108 : 
						int LA12_398 = input.LA(1);
						 
						int index12_398 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_398);
						if ( s>=0 ) return s;
						break;

					case 109 : 
						int LA12_414 = input.LA(1);
						 
						int index12_414 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_414);
						if ( s>=0 ) return s;
						break;

					case 110 : 
						int LA12_217 = input.LA(1);
						 
						int index12_217 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_217);
						if ( s>=0 ) return s;
						break;

					case 111 : 
						int LA12_339 = input.LA(1);
						 
						int index12_339 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_339);
						if ( s>=0 ) return s;
						break;

					case 112 : 
						int LA12_402 = input.LA(1);
						 
						int index12_402 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_402);
						if ( s>=0 ) return s;
						break;

					case 113 : 
						int LA12_169 = input.LA(1);
						 
						int index12_169 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_169);
						if ( s>=0 ) return s;
						break;

					case 114 : 
						int LA12_206 = input.LA(1);
						 
						int index12_206 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_206);
						if ( s>=0 ) return s;
						break;

					case 115 : 
						int LA12_141 = input.LA(1);
						 
						int index12_141 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_141);
						if ( s>=0 ) return s;
						break;

					case 116 : 
						int LA12_162 = input.LA(1);
						 
						int index12_162 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_162);
						if ( s>=0 ) return s;
						break;

					case 117 : 
						int LA12_477 = input.LA(1);
						 
						int index12_477 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_477);
						if ( s>=0 ) return s;
						break;

					case 118 : 
						int LA12_292 = input.LA(1);
						 
						int index12_292 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_292);
						if ( s>=0 ) return s;
						break;

					case 119 : 
						int LA12_410 = input.LA(1);
						 
						int index12_410 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_410);
						if ( s>=0 ) return s;
						break;

					case 120 : 
						int LA12_239 = input.LA(1);
						 
						int index12_239 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_239);
						if ( s>=0 ) return s;
						break;

					case 121 : 
						int LA12_241 = input.LA(1);
						 
						int index12_241 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_241);
						if ( s>=0 ) return s;
						break;

					case 122 : 
						int LA12_283 = input.LA(1);
						 
						int index12_283 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_283);
						if ( s>=0 ) return s;
						break;

					case 123 : 
						int LA12_356 = input.LA(1);
						 
						int index12_356 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_356);
						if ( s>=0 ) return s;
						break;

					case 124 : 
						int LA12_123 = input.LA(1);
						 
						int index12_123 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_123);
						if ( s>=0 ) return s;
						break;

					case 125 : 
						int LA12_95 = input.LA(1);
						 
						int index12_95 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_95);
						if ( s>=0 ) return s;
						break;

					case 126 : 
						int LA12_428 = input.LA(1);
						 
						int index12_428 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_428);
						if ( s>=0 ) return s;
						break;

					case 127 : 
						int LA12_441 = input.LA(1);
						 
						int index12_441 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_441);
						if ( s>=0 ) return s;
						break;

					case 128 : 
						int LA12_201 = input.LA(1);
						 
						int index12_201 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_201);
						if ( s>=0 ) return s;
						break;

					case 129 : 
						int LA12_281 = input.LA(1);
						 
						int index12_281 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_281);
						if ( s>=0 ) return s;
						break;

					case 130 : 
						int LA12_268 = input.LA(1);
						 
						int index12_268 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_268);
						if ( s>=0 ) return s;
						break;

					case 131 : 
						int LA12_375 = input.LA(1);
						 
						int index12_375 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_375);
						if ( s>=0 ) return s;
						break;

					case 132 : 
						int LA12_319 = input.LA(1);
						 
						int index12_319 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_319);
						if ( s>=0 ) return s;
						break;

					case 133 : 
						int LA12_231 = input.LA(1);
						 
						int index12_231 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_231);
						if ( s>=0 ) return s;
						break;

					case 134 : 
						int LA12_115 = input.LA(1);
						 
						int index12_115 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_115);
						if ( s>=0 ) return s;
						break;

					case 135 : 
						int LA12_117 = input.LA(1);
						 
						int index12_117 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_117);
						if ( s>=0 ) return s;
						break;

					case 136 : 
						int LA12_176 = input.LA(1);
						 
						int index12_176 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_176);
						if ( s>=0 ) return s;
						break;

					case 137 : 
						int LA12_310 = input.LA(1);
						 
						int index12_310 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_310);
						if ( s>=0 ) return s;
						break;

					case 138 : 
						int LA12_182 = input.LA(1);
						 
						int index12_182 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_182);
						if ( s>=0 ) return s;
						break;

					case 139 : 
						int LA12_160 = input.LA(1);
						 
						int index12_160 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_160);
						if ( s>=0 ) return s;
						break;

					case 140 : 
						int LA12_349 = input.LA(1);
						 
						int index12_349 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_349);
						if ( s>=0 ) return s;
						break;

					case 141 : 
						int LA12_243 = input.LA(1);
						 
						int index12_243 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_243);
						if ( s>=0 ) return s;
						break;

					case 142 : 
						int LA12_298 = input.LA(1);
						 
						int index12_298 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_298);
						if ( s>=0 ) return s;
						break;

					case 143 : 
						int LA12_104 = input.LA(1);
						 
						int index12_104 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_104);
						if ( s>=0 ) return s;
						break;

					case 144 : 
						int LA12_227 = input.LA(1);
						 
						int index12_227 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_227);
						if ( s>=0 ) return s;
						break;

					case 145 : 
						int LA12_306 = input.LA(1);
						 
						int index12_306 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_306);
						if ( s>=0 ) return s;
						break;

					case 146 : 
						int LA12_277 = input.LA(1);
						 
						int index12_277 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_277);
						if ( s>=0 ) return s;
						break;

					case 147 : 
						int LA12_302 = input.LA(1);
						 
						int index12_302 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_302);
						if ( s>=0 ) return s;
						break;

					case 148 : 
						int LA12_186 = input.LA(1);
						 
						int index12_186 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_186);
						if ( s>=0 ) return s;
						break;

					case 149 : 
						int LA12_317 = input.LA(1);
						 
						int index12_317 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_317);
						if ( s>=0 ) return s;
						break;

					case 150 : 
						int LA12_337 = input.LA(1);
						 
						int index12_337 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_337);
						if ( s>=0 ) return s;
						break;

					case 151 : 
						int LA12_347 = input.LA(1);
						 
						int index12_347 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_347);
						if ( s>=0 ) return s;
						break;

					case 152 : 
						int LA12_418 = input.LA(1);
						 
						int index12_418 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_418);
						if ( s>=0 ) return s;
						break;

					case 153 : 
						int LA12_143 = input.LA(1);
						 
						int index12_143 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_143);
						if ( s>=0 ) return s;
						break;

					case 154 : 
						int LA12_151 = input.LA(1);
						 
						int index12_151 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_151);
						if ( s>=0 ) return s;
						break;

					case 155 : 
						int LA12_236 = input.LA(1);
						 
						int index12_236 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_236);
						if ( s>=0 ) return s;
						break;

					case 156 : 
						int LA12_300 = input.LA(1);
						 
						int index12_300 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_300);
						if ( s>=0 ) return s;
						break;

					case 157 : 
						int LA12_234 = input.LA(1);
						 
						int index12_234 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_234);
						if ( s>=0 ) return s;
						break;

					case 158 : 
						int LA12_107 = input.LA(1);
						 
						int index12_107 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_107);
						if ( s>=0 ) return s;
						break;

					case 159 : 
						int LA12_167 = input.LA(1);
						 
						int index12_167 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 237;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_167);
						if ( s>=0 ) return s;
						break;

					case 160 : 
						int LA12_246 = input.LA(1);
						 
						int index12_246 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 237;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_246);
						if ( s>=0 ) return s;
						break;

					case 161 : 
						int LA12_288 = input.LA(1);
						 
						int index12_288 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 237;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_288);
						if ( s>=0 ) return s;
						break;

					case 162 : 
						int LA12_315 = input.LA(1);
						 
						int index12_315 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 237;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_315);
						if ( s>=0 ) return s;
						break;

					case 163 : 
						int LA12_382 = input.LA(1);
						 
						int index12_382 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 155;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 237;}
						else if ( (true) ) {s = 156;}
						 
						input.seek(index12_382);
						if ( s>=0 ) return s;
						break;
			}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 12, _s, input);
			error(nvae);
			throw nvae;
		}
	}

}
