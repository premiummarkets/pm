// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2021-05-29 20:10:43
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
	public static final int T__112=112;
	public static final int T__113=113;
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
	public static final int PreAndSignalCondition=46;
	public static final int ReverseCondition=47;
	public static final int SEMICOLUMN=48;
	public static final int StockOperation=49;
	public static final int String=50;
	public static final int StringOperation=51;
	public static final int StringToken=52;
	public static final int SupConstantCondition=53;
	public static final int SupDoubleMapCondition=54;
	public static final int SupportBreakDown=55;
	public static final int SupportBreakUp=56;
	public static final int TRUTHOF=57;
	public static final int Tcheat=58;
	public static final int TruthOfCondition=59;
	public static final int UpRatioCondition=60;
	public static final int WITH=61;
	public static final int WS=62;
	public static final int WhiteChar=63;

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

	// $ANTLR start "T__64"
	public final void mT__64() throws RecognitionException {
		try {
			int _type = T__64;
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
	// $ANTLR end "T__64"

	// $ANTLR start "T__65"
	public final void mT__65() throws RecognitionException {
		try {
			int _type = T__65;
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
	// $ANTLR end "T__65"

	// $ANTLR start "T__66"
	public final void mT__66() throws RecognitionException {
		try {
			int _type = T__66;
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
	// $ANTLR end "T__66"

	// $ANTLR start "T__67"
	public final void mT__67() throws RecognitionException {
		try {
			int _type = T__67;
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
	// $ANTLR end "T__67"

	// $ANTLR start "T__68"
	public final void mT__68() throws RecognitionException {
		try {
			int _type = T__68;
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
	// $ANTLR end "T__68"

	// $ANTLR start "T__69"
	public final void mT__69() throws RecognitionException {
		try {
			int _type = T__69;
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
	// $ANTLR end "T__69"

	// $ANTLR start "T__70"
	public final void mT__70() throws RecognitionException {
		try {
			int _type = T__70;
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
	// $ANTLR end "T__70"

	// $ANTLR start "T__71"
	public final void mT__71() throws RecognitionException {
		try {
			int _type = T__71;
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
	// $ANTLR end "T__71"

	// $ANTLR start "T__72"
	public final void mT__72() throws RecognitionException {
		try {
			int _type = T__72;
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
	// $ANTLR end "T__72"

	// $ANTLR start "T__73"
	public final void mT__73() throws RecognitionException {
		try {
			int _type = T__73;
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
	// $ANTLR end "T__73"

	// $ANTLR start "T__74"
	public final void mT__74() throws RecognitionException {
		try {
			int _type = T__74;
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
	// $ANTLR end "T__74"

	// $ANTLR start "T__75"
	public final void mT__75() throws RecognitionException {
		try {
			int _type = T__75;
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
	// $ANTLR end "T__75"

	// $ANTLR start "T__76"
	public final void mT__76() throws RecognitionException {
		try {
			int _type = T__76;
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
	// $ANTLR end "T__76"

	// $ANTLR start "T__77"
	public final void mT__77() throws RecognitionException {
		try {
			int _type = T__77;
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
	// $ANTLR end "T__77"

	// $ANTLR start "T__78"
	public final void mT__78() throws RecognitionException {
		try {
			int _type = T__78;
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
	// $ANTLR end "T__78"

	// $ANTLR start "T__79"
	public final void mT__79() throws RecognitionException {
		try {
			int _type = T__79;
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
	// $ANTLR end "T__79"

	// $ANTLR start "T__80"
	public final void mT__80() throws RecognitionException {
		try {
			int _type = T__80;
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
	// $ANTLR end "T__80"

	// $ANTLR start "T__81"
	public final void mT__81() throws RecognitionException {
		try {
			int _type = T__81;
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
	// $ANTLR end "T__81"

	// $ANTLR start "T__82"
	public final void mT__82() throws RecognitionException {
		try {
			int _type = T__82;
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
	// $ANTLR end "T__82"

	// $ANTLR start "T__83"
	public final void mT__83() throws RecognitionException {
		try {
			int _type = T__83;
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
	// $ANTLR end "T__83"

	// $ANTLR start "T__84"
	public final void mT__84() throws RecognitionException {
		try {
			int _type = T__84;
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
	// $ANTLR end "T__84"

	// $ANTLR start "T__85"
	public final void mT__85() throws RecognitionException {
		try {
			int _type = T__85;
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
	// $ANTLR end "T__85"

	// $ANTLR start "T__86"
	public final void mT__86() throws RecognitionException {
		try {
			int _type = T__86;
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
	// $ANTLR end "T__86"

	// $ANTLR start "T__87"
	public final void mT__87() throws RecognitionException {
		try {
			int _type = T__87;
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
	// $ANTLR end "T__87"

	// $ANTLR start "T__88"
	public final void mT__88() throws RecognitionException {
		try {
			int _type = T__88;
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
	// $ANTLR end "T__88"

	// $ANTLR start "T__89"
	public final void mT__89() throws RecognitionException {
		try {
			int _type = T__89;
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
	// $ANTLR end "T__89"

	// $ANTLR start "T__90"
	public final void mT__90() throws RecognitionException {
		try {
			int _type = T__90;
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
	// $ANTLR end "T__90"

	// $ANTLR start "T__91"
	public final void mT__91() throws RecognitionException {
		try {
			int _type = T__91;
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
	// $ANTLR end "T__91"

	// $ANTLR start "T__92"
	public final void mT__92() throws RecognitionException {
		try {
			int _type = T__92;
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
	// $ANTLR end "T__92"

	// $ANTLR start "T__93"
	public final void mT__93() throws RecognitionException {
		try {
			int _type = T__93;
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
	// $ANTLR end "T__93"

	// $ANTLR start "T__94"
	public final void mT__94() throws RecognitionException {
		try {
			int _type = T__94;
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
	// $ANTLR end "T__94"

	// $ANTLR start "T__95"
	public final void mT__95() throws RecognitionException {
		try {
			int _type = T__95;
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
	// $ANTLR end "T__95"

	// $ANTLR start "T__96"
	public final void mT__96() throws RecognitionException {
		try {
			int _type = T__96;
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
	// $ANTLR end "T__96"

	// $ANTLR start "T__97"
	public final void mT__97() throws RecognitionException {
		try {
			int _type = T__97;
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
	// $ANTLR end "T__97"

	// $ANTLR start "T__98"
	public final void mT__98() throws RecognitionException {
		try {
			int _type = T__98;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:98:7: ( 'over' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:98:9: 'over'
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
	// $ANTLR end "T__98"

	// $ANTLR start "T__99"
	public final void mT__99() throws RecognitionException {
		try {
			int _type = T__99;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:99:7: ( 'override event list name with' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:99:9: 'override event list name with'
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
	// $ANTLR end "T__99"

	// $ANTLR start "T__100"
	public final void mT__100() throws RecognitionException {
		try {
			int _type = T__100;
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
	// $ANTLR end "T__100"

	// $ANTLR start "T__101"
	public final void mT__101() throws RecognitionException {
		try {
			int _type = T__101;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:101:8: ( 'reverses down' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:101:10: 'reverses down'
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
	// $ANTLR end "T__101"

	// $ANTLR start "T__102"
	public final void mT__102() throws RecognitionException {
		try {
			int _type = T__102;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:102:8: ( 'reverses up' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:102:10: 'reverses up'
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
	// $ANTLR end "T__102"

	// $ANTLR start "T__103"
	public final void mT__103() throws RecognitionException {
		try {
			int _type = T__103;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:103:8: ( 'slope within' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:103:10: 'slope within'
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
	// $ANTLR end "T__103"

	// $ANTLR start "T__104"
	public final void mT__104() throws RecognitionException {
		try {
			int _type = T__104;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:104:8: ( 'smoothed' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:104:10: 'smoothed'
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
	// $ANTLR end "T__104"

	// $ANTLR start "T__105"
	public final void mT__105() throws RecognitionException {
		try {
			int _type = T__105;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:105:8: ( 'spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:105:10: 'spanning'
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
	// $ANTLR end "T__105"

	// $ANTLR start "T__106"
	public final void mT__106() throws RecognitionException {
		try {
			int _type = T__106;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:106:8: ( 'starting within' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:106:10: 'starting within'
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
	// $ANTLR end "T__106"

	// $ANTLR start "T__107"
	public final void mT__107() throws RecognitionException {
		try {
			int _type = T__107;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:107:8: ( 'tolerance' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:107:10: 'tolerance'
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
	// $ANTLR end "T__107"

	// $ANTLR start "T__108"
	public final void mT__108() throws RecognitionException {
		try {
			int _type = T__108;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:108:8: ( 'trends down' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:108:10: 'trends down'
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
	// $ANTLR end "T__108"

	// $ANTLR start "T__109"
	public final void mT__109() throws RecognitionException {
		try {
			int _type = T__109;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:109:8: ( 'trends flat' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:109:10: 'trends flat'
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
	// $ANTLR end "T__109"

	// $ANTLR start "T__110"
	public final void mT__110() throws RecognitionException {
		try {
			int _type = T__110;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:110:8: ( 'trends like' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:110:10: 'trends like'
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
	// $ANTLR end "T__110"

	// $ANTLR start "T__111"
	public final void mT__111() throws RecognitionException {
		try {
			int _type = T__111;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:111:8: ( 'trends unlike' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:111:10: 'trends unlike'
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
	// $ANTLR end "T__111"

	// $ANTLR start "T__112"
	public final void mT__112() throws RecognitionException {
		try {
			int _type = T__112;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:112:8: ( 'trends up' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:112:10: 'trends up'
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
	// $ANTLR end "T__112"

	// $ANTLR start "T__113"
	public final void mT__113() throws RecognitionException {
		try {
			int _type = T__113;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:113:8: ( 'type' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:113:10: 'type'
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
	// $ANTLR end "T__113"

	// $ANTLR start "Operation"
	public final void mOperation() throws RecognitionException {
		try {
			int _type = Operation;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:407:7: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:407:9: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:407:60: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:411:7: ( ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:411:9: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:411:9: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:411:11: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:411:11: ( '-' )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0=='-') ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:411:12: '-'
					{
					match('-'); 
					}
					break;

			}

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:411:18: ( '0' .. '9' )+
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

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:411:30: ( '.' ( '0' .. '9' )+ )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0=='.') ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:411:31: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:411:35: ( '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:415:6: ( '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' | '/' | ',' | '=' | ( '0' .. '9' ) | '?' | ':' | '-' | '>' )+ '\"' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:415:8: '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' | '/' | ',' | '=' | ( '0' .. '9' ) | '?' | ':' | '-' | '>' )+ '\"'
			{
			match('\"'); 
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:415:12: ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' | '/' | ',' | '=' | ( '0' .. '9' ) | '?' | ':' | '-' | '>' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:419:7: ({...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:419:9: {...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' )
			{
			if ( !((runtimeHistoryOpAhead())) ) {
				throw new FailedPredicateException(input, "HistoricalData", "runtimeHistoryOpAhead()");
			}
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:419:39: ( 'open' | 'close' | 'high' | 'low' | 'volume' )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:419:41: 'open'
					{
					match("open"); 

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:419:50: 'close'
					{
					match("close"); 

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:419:60: 'high'
					{
					match("high"); 

					}
					break;
				case 4 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:419:69: 'low'
					{
					match("low"); 

					}
					break;
				case 5 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:419:78: 'volume'
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:423:7: ( ( ( ' ' )+ ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:423:9: ( ( ' ' )+ )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:423:9: ( ( ' ' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:423:10: ( ' ' )+
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:423:10: ( ' ' )+
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:423:10: ' '
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:427:6: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:427:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:427:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:432:5: ( ( '\\r' | '\\t' | '\\u000C' | '\\n' ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:432:7: ( '\\r' | '\\t' | '\\u000C' | '\\n' )
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:435:5: ( '/*' ( . )* '*/' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:435:7: '/*' ( . )* '*/'
			{
			match("/*"); 

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:435:12: ( . )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:435:12: .
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:438:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:438:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match("//"); 

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:438:12: (~ ( '\\n' | '\\r' ) )*
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

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:438:26: ( '\\r' )?
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0=='\r') ) {
				alt12=1;
			}
			switch (alt12) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:438:26: '\\r'
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
		// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:8: ( AND | CLOSEPARENTEHSIS | CLOSESQRT | COMMA | DAYS | LENIENT | MATCHING | NOT | OPENPARENTEHSIS | OPENSQRT | OR | PERCENT | SEMICOLUMN | TRUTHOF | WITH | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | Operation | NumberToken | StringToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT )
		int alt13=74;
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
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:128: WITH
				{
				mWITH(); 

				}
				break;
			case 16 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:133: T__64
				{
				mT__64(); 

				}
				break;
			case 17 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:139: T__65
				{
				mT__65(); 

				}
				break;
			case 18 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:145: T__66
				{
				mT__66(); 

				}
				break;
			case 19 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:151: T__67
				{
				mT__67(); 

				}
				break;
			case 20 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:157: T__68
				{
				mT__68(); 

				}
				break;
			case 21 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:163: T__69
				{
				mT__69(); 

				}
				break;
			case 22 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:169: T__70
				{
				mT__70(); 

				}
				break;
			case 23 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:175: T__71
				{
				mT__71(); 

				}
				break;
			case 24 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:181: T__72
				{
				mT__72(); 

				}
				break;
			case 25 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:187: T__73
				{
				mT__73(); 

				}
				break;
			case 26 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:193: T__74
				{
				mT__74(); 

				}
				break;
			case 27 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:199: T__75
				{
				mT__75(); 

				}
				break;
			case 28 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:205: T__76
				{
				mT__76(); 

				}
				break;
			case 29 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:211: T__77
				{
				mT__77(); 

				}
				break;
			case 30 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:217: T__78
				{
				mT__78(); 

				}
				break;
			case 31 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:223: T__79
				{
				mT__79(); 

				}
				break;
			case 32 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:229: T__80
				{
				mT__80(); 

				}
				break;
			case 33 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:235: T__81
				{
				mT__81(); 

				}
				break;
			case 34 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:241: T__82
				{
				mT__82(); 

				}
				break;
			case 35 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:247: T__83
				{
				mT__83(); 

				}
				break;
			case 36 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:253: T__84
				{
				mT__84(); 

				}
				break;
			case 37 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:259: T__85
				{
				mT__85(); 

				}
				break;
			case 38 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:265: T__86
				{
				mT__86(); 

				}
				break;
			case 39 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:271: T__87
				{
				mT__87(); 

				}
				break;
			case 40 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:277: T__88
				{
				mT__88(); 

				}
				break;
			case 41 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:283: T__89
				{
				mT__89(); 

				}
				break;
			case 42 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:289: T__90
				{
				mT__90(); 

				}
				break;
			case 43 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:295: T__91
				{
				mT__91(); 

				}
				break;
			case 44 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:301: T__92
				{
				mT__92(); 

				}
				break;
			case 45 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:307: T__93
				{
				mT__93(); 

				}
				break;
			case 46 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:313: T__94
				{
				mT__94(); 

				}
				break;
			case 47 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:319: T__95
				{
				mT__95(); 

				}
				break;
			case 48 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:325: T__96
				{
				mT__96(); 

				}
				break;
			case 49 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:331: T__97
				{
				mT__97(); 

				}
				break;
			case 50 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:337: T__98
				{
				mT__98(); 

				}
				break;
			case 51 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:343: T__99
				{
				mT__99(); 

				}
				break;
			case 52 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:349: T__100
				{
				mT__100(); 

				}
				break;
			case 53 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:356: T__101
				{
				mT__101(); 

				}
				break;
			case 54 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:363: T__102
				{
				mT__102(); 

				}
				break;
			case 55 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:370: T__103
				{
				mT__103(); 

				}
				break;
			case 56 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:377: T__104
				{
				mT__104(); 

				}
				break;
			case 57 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:384: T__105
				{
				mT__105(); 

				}
				break;
			case 58 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:391: T__106
				{
				mT__106(); 

				}
				break;
			case 59 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:398: T__107
				{
				mT__107(); 

				}
				break;
			case 60 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:405: T__108
				{
				mT__108(); 

				}
				break;
			case 61 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:412: T__109
				{
				mT__109(); 

				}
				break;
			case 62 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:419: T__110
				{
				mT__110(); 

				}
				break;
			case 63 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:426: T__111
				{
				mT__111(); 

				}
				break;
			case 64 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:433: T__112
				{
				mT__112(); 

				}
				break;
			case 65 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:440: T__113
				{
				mT__113(); 

				}
				break;
			case 66 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:447: Operation
				{
				mOperation(); 

				}
				break;
			case 67 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:457: NumberToken
				{
				mNumberToken(); 

				}
				break;
			case 68 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:469: StringToken
				{
				mStringToken(); 

				}
				break;
			case 69 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:481: HistoricalData
				{
				mHistoricalData(); 

				}
				break;
			case 70 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:496: WhiteChar
				{
				mWhiteChar(); 

				}
				break;
			case 71 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:506: Tcheat
				{
				mTcheat(); 

				}
				break;
			case 72 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:513: WS
				{
				mWS(); 

				}
				break;
			case 73 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:516: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 74 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:524: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;

		}
	}


	protected DFA13 dfa13 = new DFA13(this);
	static final String DFA13_eotS =
		"\1\uffff\1\47\3\uffff\4\47\2\uffff\1\47\2\uffff\14\47\1\uffff\1\32\1\uffff"+
		"\2\47\4\uffff\1\114\1\116\1\120\1\121\1\uffff\1\123\1\125\1\127\1\131"+
		"\1\134\1\136\1\140\1\141\1\143\1\145\1\150\1\152\1\154\1\156\1\160\1\162"+
		"\1\164\1\166\1\170\1\172\1\174\1\176\1\u0080\1\u0082\1\u0084\1\u0086\1"+
		"\u0088\1\u008a\1\u008c\1\u008e\1\u0090\1\u0092\1\u0094\2\uffff\1\u0095"+
		"\1\uffff\1\u0097\1\uffff\1\u0099\2\uffff\1\u009b\1\uffff\1\u009d\1\uffff"+
		"\1\u009f\1\uffff\1\u00a0\1\uffff\1\u00a2\1\u00a4\1\uffff\1\u00a6\1\uffff"+
		"\1\u00a7\2\uffff\1\u00a9\1\uffff\1\u00ab\1\uffff\1\u00ad\1\u00af\1\uffff"+
		"\1\u00b1\1\uffff\1\u00b3\1\uffff\1\u00b5\1\uffff\1\u00b6\1\uffff\1\u00b8"+
		"\1\uffff\1\u00ba\1\uffff\1\u00bc\1\uffff\1\u00be\1\uffff\1\u00c0\1\uffff"+
		"\1\u00c2\1\uffff\1\u00c4\1\uffff\1\u00c5\1\uffff\1\u00c7\1\uffff\1\u00c9"+
		"\3\uffff\1\u00ce\1\uffff\1\u00d0\1\uffff\1\u00d2\1\uffff\1\u00d4\1\uffff"+
		"\1\u00d6\1\uffff\1\u00d8\1\uffff\1\u00da\2\uffff\1\u00dc\1\uffff\1\u00de"+
		"\1\uffff\1\u00df\1\uffff\1\u00e1\1\uffff\1\u00e3\2\uffff\1\u00e6\1\uffff"+
		"\1\u00e8\1\uffff\1\u00ea\2\uffff\1\u00ec\1\uffff\1\u00ed\1\uffff\1\u00ef"+
		"\1\uffff\1\u00f1\1\uffff\1\u00f3\1\uffff\1\u00f4\1\uffff\1\u00f5\2\uffff"+
		"\1\u00f7\1\uffff\1\u00f9\1\uffff\1\u00fb\1\uffff\1\u00fd\1\uffff\1\u00ff"+
		"\1\uffff\1\u0101\1\uffff\1\u0103\2\uffff\1\u0105\1\uffff\1\u0107\4\uffff"+
		"\1\u010c\1\uffff\1\u010e\1\uffff\1\u0110\1\uffff\1\u0112\1\uffff\1\u0114"+
		"\1\uffff\1\u0115\1\uffff\1\u0117\1\uffff\1\u0119\4\uffff\1\u011b\1\uffff"+
		"\1\u011d\2\uffff\1\u011f\1\uffff\1\u0121\3\uffff\1\u0123\2\uffff\1\u0125"+
		"\1\uffff\1\u0127\1\uffff\1\u0129\3\uffff\1\u012b\1\uffff\1\u012d\1\uffff"+
		"\1\u012f\1\uffff\1\u0130\1\uffff\1\u0132\1\uffff\1\u0134\1\uffff\1\u0136"+
		"\3\uffff\1\u0139\4\uffff\1\u013e\1\uffff\1\u0140\1\uffff\1\u0142\1\uffff"+
		"\1\u0144\1\uffff\1\u0146\2\uffff\1\u0148\1\uffff\1\u014a\1\uffff\1\u014c"+
		"\1\uffff\1\u014e\1\uffff\1\u0150\3\uffff\1\u0153\3\uffff\1\u0155\1\uffff"+
		"\1\u0157\1\uffff\1\u0159\1\uffff\1\u015b\1\uffff\1\u015d\2\uffff\1\u015f"+
		"\1\uffff\1\u0161\1\uffff\1\u0163\7\uffff\1\u0168\3\uffff\1\u016a\1\uffff"+
		"\1\u016c\1\uffff\1\u016e\1\uffff\1\u016f\1\uffff\1\u0171\1\uffff\1\u0173"+
		"\1\uffff\1\u0174\1\uffff\1\u0176\2\uffff\1\u0179\3\uffff\1\u017f\1\uffff"+
		"\1\u0180\1\uffff\1\u0181\1\uffff\1\u0183\3\uffff\1\u0184\6\uffff\1\u018b"+
		"\1\uffff\1\u018d\1\uffff\1\u018f\1\uffff\1\u0191\2\uffff\1\u0193\1\uffff"+
		"\1\u0195\2\uffff\1\u0196\2\uffff\1\u019b\5\uffff\1\u019f\13\uffff\1\u01a8"+
		"\1\uffff\1\u01a9\1\uffff\1\u01aa\1\uffff\1\u01ac\1\uffff\1\u01ae\1\uffff"+
		"\1\u01af\11\uffff\1\u01b5\16\uffff\1\u01c0\21\uffff\1\u01c8\7\uffff\1"+
		"\u01d1\42\uffff";
	static final String DFA13_eofS =
		"\u01ea\uffff";
	static final String DFA13_minS =
		"\1\11\1\55\3\uffff\4\55\2\uffff\1\55\2\uffff\14\55\1\uffff\1\60\1\uffff"+
		"\2\55\3\uffff\1\52\4\55\1\uffff\31\55\1\40\7\55\2\uffff\1\55\1\0\1\55"+
		"\1\0\1\55\2\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\2\55\1\0\1\55\1\0\1"+
		"\55\1\0\1\uffff\1\55\1\0\1\55\1\0\2\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0"+
		"\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1"+
		"\0\1\55\1\0\1\55\1\0\1\55\1\0\1\141\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55"+
		"\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\uffff\1\55\1\0\1\40\1\0\1\55\1\0\1\55"+
		"\1\0\1\55\2\0\1\55\1\0\1\55\1\0\1\40\1\0\1\uffff\1\55\1\0\1\55\1\0\1\55"+
		"\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\uffff\1\55\1\0\1\55\1\0\1\55"+
		"\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\uffff\1\40\1\0\1\55\1\0\1\142"+
		"\1\145\1\uffff\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1"+
		"\55\1\0\1\55\1\0\1\uffff\1\0\1\uffff\1\55\1\0\1\55\1\0\1\uffff\1\55\1"+
		"\0\1\40\1\0\1\uffff\1\0\1\55\1\uffff\1\0\1\40\1\0\1\55\1\0\1\55\1\0\2"+
		"\uffff\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0"+
		"\1\144\1\0\1\55\1\0\1\157\1\141\1\uffff\1\55\1\0\1\40\1\0\1\55\1\0\1\55"+
		"\1\0\1\55\2\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\141\1\0\1"+
		"\55\1\0\1\uffff\1\0\1\40\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\2\0\1\40"+
		"\1\0\1\55\1\0\1\40\1\0\3\uffff\1\166\1\162\1\157\1\55\1\0\1\uffff\1\0"+
		"\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\55\1"+
		"\0\1\40\1\55\1\0\1\144\1\0\1\55\1\0\1\55\1\0\1\55\1\0\1\40\1\0\1\uffff"+
		"\1\0\1\55\1\0\1\150\1\0\1\145\1\151\1\167\1\55\1\0\1\55\1\0\1\55\1\0\1"+
		"\55\2\0\1\55\1\0\1\55\1\0\1\uffff\1\55\1\0\1\150\1\40\1\0\3\uffff\1\156"+
		"\1\55\1\0\2\uffff\1\144\1\0\2\uffff\1\150\1\40\1\163\2\40\1\0\1\55\1\0"+
		"\1\55\1\0\1\40\1\0\1\55\1\0\1\55\1\0\1\uffff\1\151\1\157\1\165\1\145\1"+
		"\0\2\uffff\1\55\1\0\1\157\1\160\2\uffff\3\150\1\144\1\0\3\uffff\1\0\1"+
		"\55\1\0\1\uffff\1\147\1\167\1\160\3\uffff\1\167\1\40\2\uffff\1\40\4\uffff"+
		"\1\55\1\0\1\150\1\145\1\160\1\156\1\150\1\151\1\55\1\0\1\145\1\162\1\157"+
		"\1\40\5\uffff\1\162\1\40\1\162\1\150\1\40\1\150\1\164\2\uffff\1\150\2"+
		"\uffff\1\40\2\uffff\1\142\1\162\1\145\1\141\1\153\1\40\1\144\2\uffff";
	static final String DFA13_maxS =
		"\2\172\3\uffff\4\172\2\uffff\1\172\2\uffff\14\172\1\uffff\1\172\1\uffff"+
		"\2\172\3\uffff\1\57\4\172\1\uffff\41\172\2\uffff\1\172\1\0\1\172\1\0\1"+
		"\172\2\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\2\172\1\0\1\172\1\0\1"+
		"\172\1\0\1\uffff\1\172\1\0\1\172\1\0\2\172\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\167\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\172\1"+
		"\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\2\0\1\172\1\0\1\172\1\0\1\172\1"+
		"\0\1\uffff\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0"+
		"\1\172\1\0\1\uffff\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1"+
		"\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\172\1\0\1\142\1\165\1\uffff\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\uffff\1\0\1\uffff\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\172\1"+
		"\0\1\uffff\1\0\1\172\1\uffff\1\0\1\172\1\0\1\172\1\0\1\172\1\0\2\uffff"+
		"\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0"+
		"\1\165\1\0\1\172\1\0\1\157\1\154\1\uffff\1\172\1\0\1\172\1\0\1\172\1\0"+
		"\1\172\1\0\1\172\2\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0"+
		"\1\141\1\0\1\172\1\0\1\uffff\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1"+
		"\0\1\172\2\0\1\172\1\0\1\172\1\0\1\172\1\0\3\uffff\1\166\1\162\1\157\1"+
		"\172\1\0\1\uffff\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0"+
		"\1\172\1\0\1\172\1\0\1\172\1\0\1\40\1\172\1\0\1\165\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\uffff\1\0\1\172\1\0\1\164\1\0\1\145\1\151\1"+
		"\167\1\172\1\0\1\172\1\0\1\172\1\0\1\172\2\0\1\172\1\0\1\172\1\0\1\uffff"+
		"\1\172\1\0\1\163\1\172\1\0\3\uffff\1\160\1\172\1\0\2\uffff\1\165\1\0\2"+
		"\uffff\1\162\1\40\1\163\1\40\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1"+
		"\172\1\0\1\172\1\0\1\uffff\1\151\1\157\1\165\1\163\1\0\2\uffff\1\172\1"+
		"\0\1\157\1\160\2\uffff\1\164\1\150\1\164\1\165\1\0\3\uffff\1\0\1\172\1"+
		"\0\1\uffff\1\147\1\167\1\160\3\uffff\1\167\1\40\2\uffff\1\40\4\uffff\1"+
		"\172\1\0\1\150\1\145\1\160\1\156\1\164\1\167\1\172\1\0\1\145\1\162\1\157"+
		"\1\40\5\uffff\1\162\1\40\1\162\1\164\1\40\1\154\1\164\2\uffff\1\154\2"+
		"\uffff\1\40\2\uffff\1\142\1\162\1\145\1\141\1\153\1\40\1\165\2\uffff";
	static final String DFA13_acceptS =
		"\2\uffff\1\2\1\3\1\4\4\uffff\1\11\1\12\1\uffff\1\14\1\15\14\uffff\1\103"+
		"\1\uffff\1\104\2\uffff\1\106\1\102\1\110\5\uffff\1\107\41\uffff\1\111"+
		"\1\112\26\uffff\1\13\63\uffff\1\1\21\uffff\1\10\16\uffff\1\20\16\uffff"+
		"\1\37\6\uffff\1\52\20\uffff\1\22\1\uffff\1\5\4\uffff\1\105\4\uffff\1\61"+
		"\2\uffff\1\62\7\uffff\1\101\1\17\24\uffff\1\51\31\uffff\1\16\22\uffff"+
		"\1\40\1\41\1\42\5\uffff\1\67\36\uffff\1\32\25\uffff\1\6\5\uffff\1\74\1"+
		"\75\1\76\3\uffff\1\23\1\24\2\uffff\1\33\1\34\20\uffff\1\7\5\uffff\1\77"+
		"\1\100\4\uffff\1\35\1\36\5\uffff\1\70\1\71\1\72\3\uffff\1\31\3\uffff\1"+
		"\63\1\64\1\73\2\uffff\1\43\1\44\1\uffff\1\47\1\50\1\65\1\66\16\uffff\1"+
		"\27\1\30\1\45\1\46\1\21\7\uffff\1\25\1\26\1\uffff\1\55\1\56\1\uffff\1"+
		"\53\1\54\7\uffff\1\57\1\60";
	static final String DFA13_specialS =
		"\1\u00f5\1\u00c3\3\uffff\1\15\1\u013a\1\u00c5\1\u0109\2\uffff\1\u00fa"+
		"\2\uffff\1\u00e4\1\u0111\1\u0112\1\16\1\u014d\1\u00fd\1\u014a\1\2\1\u00ea"+
		"\1\u00f0\1\u00f7\1\u0108\3\uffff\1\u010b\1\u00f6\4\uffff\1\u0149\1\22"+
		"\1\34\1\u0124\1\uffff\1\u014b\1\u00ce\1\u014e\1\4\1\u00fb\1\u010c\1\5"+
		"\1\u011c\1\u00f8\1\u0150\1\u00f4\1\u013b\1\u0146\1\17\1\21\1\40\1\45\1"+
		"\u00c1\1\u0155\1\u00d6\1\u00dd\1\u00c7\1\u00e6\1\u0120\1\u00e7\1\u010a"+
		"\1\u0115\1\u011d\1\u0125\1\u012b\1\u0131\1\u015b\1\6\2\uffff\1\u00fe\1"+
		"\106\1\23\1\123\1\35\1\124\1\u008c\1\u014c\1\51\1\u00cf\1\u009d\1\u014f"+
		"\1\50\1\u00ee\1\u00b9\1\u0159\1\u00ef\1\u008b\1\u010e\1\u009e\1\u0114"+
		"\1\u00bd\1\uffff\1\u00f9\1\u0093\1\u0152\1\164\1\12\1\u00db\1\75\1\u013c"+
		"\1\125\1\u0147\1\52\1\20\1\u00a0\1\u0139\1\u008d\1\41\1\u0094\1\46\1\141"+
		"\1\u00c2\1\163\1\u0156\1\u00a1\1\u00d7\1\u0095\1\u00de\1\107\1\u00c9\1"+
		"\60\1\u00d4\1\61\1\u0122\1\170\1\u00e8\1\122\1\uffff\1\152\1\u0116\1\171"+
		"\1\u011e\1\u00a8\1\u0126\1\134\1\u012c\1\135\1\u0133\1\u0098\1\0\1\66"+
		"\1\7\1\u0080\1\uffff\1\24\1\55\1\36\1\57\1\u010d\1\62\1\u00d0\1\166\1"+
		"\u0151\1\u00b8\1\u00ff\1\u015a\1\131\1\u00f1\1\70\1\u010f\1\113\1\uffff"+
		"\1\u00fc\1\u00af\1\u00eb\1\114\1\13\1\132\1\u00dc\1\76\1\u013d\1\u009c"+
		"\1\u00bf\1\u00a9\1\u0132\1\136\1\uffff\1\42\1\u00ac\1\47\1\105\1\u00c4"+
		"\1\u008a\1\u0158\1\153\1\u00d8\1\u0086\1\u00e0\1\116\1\u00cb\1\103\1\uffff"+
		"\1\u0123\1\137\1\u00e9\1\u00bb\3\uffff\1\u0117\1\63\1\u011f\1\146\1\u0127"+
		"\1\u0091\1\u012d\1\147\1\u0134\1\104\1\u00ed\1\160\1\10\1\154\1\25\1\u0097"+
		"\1\uffff\1\u00aa\1\uffff\1\u00d1\1\u0081\1\u0153\1\u009b\1\uffff\1\u015c"+
		"\1\u00b4\1\u00f3\1\101\1\uffff\1\121\1\u0100\1\uffff\1\u0101\1\14\1\u00b7"+
		"\1\u00df\1\u00a6\1\u013e\1\54\2\uffff\1\43\1\u0087\1\112\1\150\1\u00c6"+
		"\1\u00b5\1\u00ec\1\56\1\u00d9\1\53\1\u00e1\1\u008e\1\u00cc\1\162\1\uffff"+
		"\1\u00b3\1\u00e5\1\144\3\uffff\1\u0118\1\120\1\u0121\1\145\1\u0128\1\u00ae"+
		"\1\u012e\1\167\1\u0135\1\174\1\u0102\1\11\1\71\1\26\1\172\1\u00d2\1\102"+
		"\1\u0154\1\140\1\1\1\u0083\1\uffff\1\u0082\1\u0105\1\117\1\uffff\1\73"+
		"\1\u00e3\1\u0084\1\u013f\1\176\1\44\1\100\1\u00be\1\115\1\u00c8\1\u0085"+
		"\1\u0103\1\u00da\1\165\1\u00e2\1\u0099\1\u00cd\1\133\6\uffff\1\u0119\1"+
		"\142\1\uffff\1\127\1\u0129\1\u008f\1\u012f\1\110\1\u0136\1\126\1\u00f2"+
		"\1\64\1\27\1\u00a2\1\u00d3\1\u0089\1\u0110\1\151\1\3\1\u00b0\1\uffff\1"+
		"\u0106\1\143\1\uffff\1\177\1\u0140\1\u00a4\1\u0143\1\u00a3\1\u0144\1\u00b1"+
		"\1\u00ca\1\67\1\uffff\1\175\1\u00c0\1\u00b2\1\uffff\1\u00ba\3\uffff\1"+
		"\u011a\1\u00bc\1\u012a\1\77\1\u0130\1\u00a5\1\u0137\1\65\1\u0104\1\30"+
		"\1\156\1\u00d5\1\u0090\1\uffff\1\u0113\1\74\1\uffff\1\u0107\1\111\4\uffff"+
		"\1\u0141\1\u0088\3\uffff\1\u0092\6\uffff\1\u011b\1\72\1\u0145\1\u00ab"+
		"\1\u0148\1\173\1\u0138\1\u00a7\1\31\1\u00ad\1\37\1\u0096\5\uffff\1\157"+
		"\2\uffff\1\u0157\1\u00b6\10\uffff\1\161\3\uffff\1\u009a\1\32\1\130\20"+
		"\uffff\1\33\1\u009f\6\uffff\1\u0142\1\155\41\uffff}>";
	static final String[] DFA13_transitionS = {
			"\2\41\1\uffff\2\41\22\uffff\1\37\1\uffff\1\34\2\uffff\1\14\2\uffff\1"+
			"\11\1\2\2\uffff\1\4\1\32\1\uffff\1\42\12\33\1\uffff\1\15\5\uffff\15\36"+
			"\1\20\14\36\1\12\1\uffff\1\3\1\uffff\1\40\1\uffff\1\1\1\21\1\22\1\5\1"+
			"\23\1\24\1\25\1\31\1\26\2\36\1\6\1\7\1\10\1\13\2\36\1\27\1\30\1\16\1"+
			"\36\1\35\1\17\3\36",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\3\46\1\44\7\46"+
			"\1\45\1\46\1\43\14\46",
			"",
			"",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\1\50\7\46\1\51"+
			"\21\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\52\11"+
			"\46\1\53\13\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\1\54\15\46\1"+
			"\55\13\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\16\46\1\56\13"+
			"\46",
			"",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\17\46\1\61\1"+
			"\46\1\57\3\46\1\60\4\46",
			"",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\16\46\1\63\2"+
			"\46\1\62\6\46\1\64\1\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\10\46\1\65\21"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\1\66\31\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\67\17"+
			"\46\1\70\5\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\13\46\1\72\5"+
			"\46\1\71\10\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\15\46\1\73\1"+
			"\46\1\74\1\75\11\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\16\46\1\76\13"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\16\46\1\77\2"+
			"\46\1\100\10\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\22\46\1\101\7"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\102\25"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\13\46\1\103\1"+
			"\104\2\46\1\105\3\46\1\106\6\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\10\46\1\107\21"+
			"\46",
			"",
			"\12\33\7\uffff\32\47\6\uffff\32\47",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\16\46\1\110\13"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"",
			"",
			"",
			"\1\111\4\uffff\1\112",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\3\46\1\113\26"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\1\115\31\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\22\46\1\117\7"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\30\46\1\122\1"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\21\46\1\124\10"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\15\46\1\126\14"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\26\46\1\130\3"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\12\46\1\133\10"+
			"\46\1\132\6\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\21\46\1\135\10"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\23\46\1\137\6"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\142\25"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\144\25"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\147\17"+
			"\46\1\146\5\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\13\46\1\151\16"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\17\46\1\153\12"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\23\46\1\155\6"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\15\46\1\157\14\46\4\uffff\1\40\1\uffff\32"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\1\161\31\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\13\46\1\163\16"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\16\46\1\165\13"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\16\46\1\167\13"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\3\46\1\171\26"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\22\46\1\173\7"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\24\46\1\175\5"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\21\46\1\177\10"+
			"\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u0081"+
			"\25\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u0083"+
			"\25\46",
			"\1\u0085\14\uffff\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff"+
			"\32\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\25\46\1\u0087"+
			"\4\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\16\46\1\u0089"+
			"\13\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\16\46\1\u008b"+
			"\13\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\1\u008d\31\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\1\u008f\31\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\6\46\1\u0091"+
			"\23\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\13\46\1\u0093"+
			"\16\46",
			"",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\17\46\1\u0096"+
			"\12\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\16\46\1\u0098"+
			"\13\46",
			"\1\uffff",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\22\46\1\u009a"+
			"\7\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u009c"+
			"\25\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\10\46\1\u009e"+
			"\21\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\2\46\1\u00a1"+
			"\27\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u00a3"+
			"\25\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u00a5"+
			"\25\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\21\46\1\u00a8"+
			"\10\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\15\46\1\u00aa"+
			"\14\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\23\46\1\u00ac"+
			"\6\46",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\15\46\1\u00ae"+
			"\14\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u00b0"+
			"\25\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u00b2"+
			"\25\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\7\46\1\u00b4"+
			"\22\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\21\46\1\u00b7"+
			"\10\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\13\46\1\u00b9"+
			"\16\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\22\46\1\u00bb"+
			"\7\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\22\46\1\u00bd"+
			"\7\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\10\46\1\u00bf"+
			"\21\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\10\46\1\u00c1"+
			"\21\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\1\u00c3\31\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\22\46\1\u00c6"+
			"\7\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u00c8"+
			"\25\46",
			"\1\uffff",
			"\1\u00ca\1\u00cb\24\uffff\1\u00cc",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u00cd"+
			"\25\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\17\46\1\u00cf"+
			"\12\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\16\46\1\u00d1"+
			"\13\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\15\46\1\u00d3"+
			"\14\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\21\46\1\u00d5"+
			"\10\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\7\46\1\u00d7"+
			"\22\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\24\46\1\u00d9"+
			"\5\46",
			"\1\uffff",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\23\46\1\u00db"+
			"\6\46",
			"\1\uffff",
			"\1\u00dd\14\uffff\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff"+
			"\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\2\46\1\u00e0"+
			"\27\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u00e2"+
			"\25\46",
			"\1\uffff",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\7\46\1\u00e5"+
			"\22\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\22\46\1\u00e7"+
			"\7\46",
			"\1\uffff",
			"\1\u00e9\14\uffff\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff"+
			"\32\46",
			"\1\uffff",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\21\46\1\u00eb"+
			"\10\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\7\46\1\u00ee"+
			"\22\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\3\46\1\u00f0"+
			"\26\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\21\46\1\u00f2"+
			"\10\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\10\46\1\u00f6"+
			"\21\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\10\46\1\u00f8"+
			"\21\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\22\46\1\u00fa"+
			"\7\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u00fc"+
			"\25\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\15\46\1\u00fe"+
			"\14\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\13\46\1\u0100"+
			"\16\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\13\46\1\u0102"+
			"\16\46",
			"\1\uffff",
			"",
			"\1\u0104\14\uffff\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff"+
			"\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\3\46\1\u0106"+
			"\26\46",
			"\1\uffff",
			"\1\u0108",
			"\1\u0109\17\uffff\1\u010a",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\21\46\1\u010b"+
			"\10\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u010d"+
			"\25\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\23\46\1\u010f"+
			"\6\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\15\46\1\u0111"+
			"\14\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\23\46\1\u0113"+
			"\6\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\14\46\1\u0116"+
			"\15\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\10\46\1\u0118"+
			"\21\46",
			"\1\uffff",
			"",
			"\1\uffff",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\23\46\1\u011a"+
			"\6\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\15\46\1\u011c"+
			"\14\46",
			"\1\uffff",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\10\46\1\u011e"+
			"\21\46",
			"\1\uffff",
			"\1\u0120\14\uffff\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff"+
			"\32\46",
			"\1\uffff",
			"",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\10\46\1\u0122"+
			"\21\46",
			"",
			"\1\uffff",
			"\1\u0124\14\uffff\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff"+
			"\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\22\46\1\u0126"+
			"\7\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\1\u0128\31\46",
			"\1\uffff",
			"",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\22\46\1\u012a"+
			"\7\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\22\46\1\u012c"+
			"\7\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u012e"+
			"\25\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\6\46\1\u0131"+
			"\23\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\16\46\1\u0133"+
			"\13\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\22\46\1\u0135"+
			"\7\46",
			"\1\uffff",
			"\1\u0137\20\uffff\1\u0138",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\1\u013a",
			"\1\u013b\12\uffff\1\u013c",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\22\46\1\u013d"+
			"\7\46",
			"\1\uffff",
			"\1\u013f\14\uffff\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff"+
			"\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\7\46\1\u0141"+
			"\22\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\10\46\1\u0143"+
			"\21\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\10\46\1\u0145"+
			"\21\46",
			"\1\uffff",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u0147"+
			"\25\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\25\46\1\u0149"+
			"\4\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\10\46\1\u014b"+
			"\21\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\23\46\1\u014d"+
			"\6\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\15\46\1\u014f"+
			"\14\46",
			"\1\uffff",
			"\1\u0151",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\3\46\1\u0152"+
			"\26\46",
			"\1\uffff",
			"",
			"\1\uffff",
			"\1\u0154\14\uffff\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff"+
			"\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\15\46\1\u0156"+
			"\14\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\7\46\1\u0158"+
			"\22\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\7\46\1\u015a"+
			"\22\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\22\46\1\u015c"+
			"\7\46",
			"\1\uffff",
			"\1\uffff",
			"\1\u015e\14\uffff\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff"+
			"\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\15\46\1\u0160"+
			"\14\46",
			"\1\uffff",
			"\1\u0162\14\uffff\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff"+
			"\32\46",
			"\1\uffff",
			"",
			"",
			"",
			"\1\u0164",
			"\1\u0165",
			"\1\u0166",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u0167"+
			"\25\46",
			"\1\uffff",
			"",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u0169"+
			"\25\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\15\46\1\u016b"+
			"\14\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\15\46\1\u016d"+
			"\14\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u0170"+
			"\25\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\16\46\1\u0172"+
			"\13\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\6\46\1\u0175"+
			"\23\46",
			"\1\uffff",
			"\1\u0177",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u0178"+
			"\25\46",
			"\1\uffff",
			"\1\u017a\1\uffff\1\u017b\5\uffff\1\u017c\10\uffff\1\u017d",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\2\46\1\u017e"+
			"\27\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\1\u0182\14\uffff\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff"+
			"\32\46",
			"\1\uffff",
			"",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\1\u0185\13\uffff\1\u0186",
			"\1\uffff",
			"\1\u0187",
			"\1\u0188",
			"\1\u0189",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\22\46\1\u018a"+
			"\7\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\3\46\1\u018c"+
			"\26\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\6\46\1\u018e"+
			"\23\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\6\46\1\u0190"+
			"\23\46",
			"\1\uffff",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\21\46\1\u0192\10\46\4\uffff\1\40\1\uffff"+
			"\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\15\46\1\u0194"+
			"\14\46",
			"\1\uffff",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\1\u0197\3\uffff\1\u0198\6\uffff\1\u0199",
			"\1\u019a\14\uffff\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff"+
			"\32\46",
			"\1\uffff",
			"",
			"",
			"",
			"\1\u019c\1\uffff\1\u019d",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u019e"+
			"\25\46",
			"\1\uffff",
			"",
			"",
			"\1\u01a0\20\uffff\1\u01a1",
			"\1\uffff",
			"",
			"",
			"\1\u01a2\11\uffff\1\u01a3",
			"\1\u01a4",
			"\1\u01a5",
			"\1\u01a6",
			"\1\u01a7\14\uffff\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff"+
			"\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\1\u01ab\14\uffff\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff"+
			"\32\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\1\u01ad\31\46",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"",
			"\1\u01b0",
			"\1\u01b1",
			"\1\u01b2",
			"\1\u01b3\15\uffff\1\u01b4",
			"\1\uffff",
			"",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\1\u01b6",
			"\1\u01b7",
			"",
			"",
			"\1\u01b8\13\uffff\1\u01b9",
			"\1\u01ba",
			"\1\u01bb\13\uffff\1\u01bc",
			"\1\u01bd\20\uffff\1\u01be",
			"\1\uffff",
			"",
			"",
			"",
			"\1\uffff",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\23\46\1\u01bf"+
			"\6\46",
			"\1\uffff",
			"",
			"\1\u01c1",
			"\1\u01c2",
			"\1\u01c3",
			"",
			"",
			"",
			"\1\u01c4",
			"\1\u01c5",
			"",
			"",
			"\1\u01c6",
			"",
			"",
			"",
			"",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\4\46\1\u01c7"+
			"\25\46",
			"\1\uffff",
			"\1\u01c9",
			"\1\u01ca",
			"\1\u01cb",
			"\1\u01cc",
			"\1\u01cd\13\uffff\1\u01ce",
			"\1\u01cf\15\uffff\1\u01d0",
			"\2\40\1\uffff\12\46\7\uffff\32\46\4\uffff\1\40\1\uffff\32\46",
			"\1\uffff",
			"\1\u01d2",
			"\1\u01d3",
			"\1\u01d4",
			"\1\u01d5",
			"",
			"",
			"",
			"",
			"",
			"\1\u01d6",
			"\1\u01d7",
			"\1\u01d8",
			"\1\u01d9\13\uffff\1\u01da",
			"\1\u01db",
			"\1\u01dc\3\uffff\1\u01dd",
			"\1\u01de",
			"",
			"",
			"\1\u01df\3\uffff\1\u01e0",
			"",
			"",
			"\1\u01e1",
			"",
			"",
			"\1\u01e2",
			"\1\u01e3",
			"\1\u01e4",
			"\1\u01e5",
			"\1\u01e6",
			"\1\u01e7",
			"\1\u01e8\20\uffff\1\u01e9",
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
			return "1:1: Tokens : ( AND | CLOSEPARENTEHSIS | CLOSESQRT | COMMA | DAYS | LENIENT | MATCHING | NOT | OPENPARENTEHSIS | OPENSQRT | OR | PERCENT | SEMICOLUMN | TRUTHOF | WITH | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | Operation | NumberToken | StringToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA13_145 = input.LA(1);
						 
						int index13_145 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_145=='h') ) {s = 215;}
						else if ( ((LA13_145 >= '0' && LA13_145 <= '9')||(LA13_145 >= 'A' && LA13_145 <= 'Z')||(LA13_145 >= 'a' && LA13_145 <= 'g')||(LA13_145 >= 'i' && LA13_145 <= 'z')) ) {s = 38;}
						else if ( ((LA13_145 >= '-' && LA13_145 <= '.')||LA13_145=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 216;
						 
						input.seek(index13_145);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA13_286 = input.LA(1);
						 
						int index13_286 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_286=='n') ) {s = 335;}
						else if ( ((LA13_286 >= '0' && LA13_286 <= '9')||(LA13_286 >= 'A' && LA13_286 <= 'Z')||(LA13_286 >= 'a' && LA13_286 <= 'm')||(LA13_286 >= 'o' && LA13_286 <= 'z')) ) {s = 38;}
						else if ( ((LA13_286 >= '-' && LA13_286 <= '.')||LA13_286=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 336;
						 
						input.seek(index13_286);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA13_21 = input.LA(1);
						 
						int index13_21 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_21=='o') ) {s = 63;}
						else if ( (LA13_21=='r') ) {s = 64;}
						else if ( ((LA13_21 >= '0' && LA13_21 <= '9')||(LA13_21 >= 'A' && LA13_21 <= 'Z')||(LA13_21 >= 'a' && LA13_21 <= 'n')||(LA13_21 >= 'p' && LA13_21 <= 'q')||(LA13_21 >= 's' && LA13_21 <= 'z')) ) {s = 38;}
						else if ( ((LA13_21 >= '-' && LA13_21 <= '.')||LA13_21=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_21);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA13_335 = input.LA(1);
						 
						int index13_335 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_335=='g') ) {s = 373;}
						else if ( ((LA13_335 >= '0' && LA13_335 <= '9')||(LA13_335 >= 'A' && LA13_335 <= 'Z')||(LA13_335 >= 'a' && LA13_335 <= 'f')||(LA13_335 >= 'h' && LA13_335 <= 'z')) ) {s = 38;}
						else if ( ((LA13_335 >= '-' && LA13_335 <= '.')||LA13_335=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 374;
						 
						input.seek(index13_335);
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA13_43 = input.LA(1);
						 
						int index13_43 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_43=='w') ) {s = 88;}
						else if ( ((LA13_43 >= '0' && LA13_43 <= '9')||(LA13_43 >= 'A' && LA13_43 <= 'Z')||(LA13_43 >= 'a' && LA13_43 <= 'v')||(LA13_43 >= 'x' && LA13_43 <= 'z')) ) {s = 38;}
						else if ( ((LA13_43 >= '-' && LA13_43 <= '.')||LA13_43=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 89;
						 
						input.seek(index13_43);
						if ( s>=0 ) return s;
						break;

					case 5 : 
						int LA13_46 = input.LA(1);
						 
						int index13_46 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_46=='t') ) {s = 95;}
						else if ( ((LA13_46 >= '0' && LA13_46 <= '9')||(LA13_46 >= 'A' && LA13_46 <= 'Z')||(LA13_46 >= 'a' && LA13_46 <= 's')||(LA13_46 >= 'u' && LA13_46 <= 'z')) ) {s = 38;}
						else if ( ((LA13_46 >= '-' && LA13_46 <= '.')||LA13_46=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 96;
						 
						input.seek(index13_46);
						if ( s>=0 ) return s;
						break;

					case 6 : 
						int LA13_72 = input.LA(1);
						 
						int index13_72 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_72=='l') ) {s = 147;}
						else if ( ((LA13_72 >= '0' && LA13_72 <= '9')||(LA13_72 >= 'A' && LA13_72 <= 'Z')||(LA13_72 >= 'a' && LA13_72 <= 'k')||(LA13_72 >= 'm' && LA13_72 <= 'z')) ) {s = 38;}
						else if ( ((LA13_72 >= '-' && LA13_72 <= '.')||LA13_72=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 148;
						 
						input.seek(index13_72);
						if ( s>=0 ) return s;
						break;

					case 7 : 
						int LA13_147 = input.LA(1);
						 
						int index13_147 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_147=='u') ) {s = 217;}
						else if ( ((LA13_147 >= '0' && LA13_147 <= '9')||(LA13_147 >= 'A' && LA13_147 <= 'Z')||(LA13_147 >= 'a' && LA13_147 <= 't')||(LA13_147 >= 'v' && LA13_147 <= 'z')) ) {s = 38;}
						else if ( ((LA13_147 >= '-' && LA13_147 <= '.')||LA13_147=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 218;
						 
						input.seek(index13_147);
						if ( s>=0 ) return s;
						break;

					case 8 : 
						int LA13_217 = input.LA(1);
						 
						int index13_217 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_217=='m') ) {s = 278;}
						else if ( ((LA13_217 >= '0' && LA13_217 <= '9')||(LA13_217 >= 'A' && LA13_217 <= 'Z')||(LA13_217 >= 'a' && LA13_217 <= 'l')||(LA13_217 >= 'n' && LA13_217 <= 'z')) ) {s = 38;}
						else if ( ((LA13_217 >= '-' && LA13_217 <= '.')||LA13_217=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 279;
						 
						input.seek(index13_217);
						if ( s>=0 ) return s;
						break;

					case 9 : 
						int LA13_278 = input.LA(1);
						 
						int index13_278 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_278=='e') ) {s = 327;}
						else if ( ((LA13_278 >= '0' && LA13_278 <= '9')||(LA13_278 >= 'A' && LA13_278 <= 'Z')||(LA13_278 >= 'a' && LA13_278 <= 'd')||(LA13_278 >= 'f' && LA13_278 <= 'z')) ) {s = 38;}
						else if ( ((LA13_278 >= '-' && LA13_278 <= '.')||LA13_278=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 328;
						 
						input.seek(index13_278);
						if ( s>=0 ) return s;
						break;

					case 10 : 
						int LA13_102 = input.LA(1);
						 
						int index13_102 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_102=='t') ) {s = 172;}
						else if ( ((LA13_102 >= '0' && LA13_102 <= '9')||(LA13_102 >= 'A' && LA13_102 <= 'Z')||(LA13_102 >= 'a' && LA13_102 <= 's')||(LA13_102 >= 'u' && LA13_102 <= 'z')) ) {s = 38;}
						else if ( ((LA13_102 >= '-' && LA13_102 <= '.')||LA13_102=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 173;
						 
						input.seek(index13_102);
						if ( s>=0 ) return s;
						break;

					case 11 : 
						int LA13_172 = input.LA(1);
						 
						int index13_172 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_172=='h') ) {s = 238;}
						else if ( ((LA13_172 >= '0' && LA13_172 <= '9')||(LA13_172 >= 'A' && LA13_172 <= 'Z')||(LA13_172 >= 'a' && LA13_172 <= 'g')||(LA13_172 >= 'i' && LA13_172 <= 'z')) ) {s = 38;}
						else if ( ((LA13_172 >= '-' && LA13_172 <= '.')||LA13_172=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 239;
						 
						input.seek(index13_172);
						if ( s>=0 ) return s;
						break;

					case 12 : 
						int LA13_238 = input.LA(1);
						 
						int index13_238 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_238==' ') ) {s = 292;}
						else if ( ((LA13_238 >= '0' && LA13_238 <= '9')||(LA13_238 >= 'A' && LA13_238 <= 'Z')||(LA13_238 >= 'a' && LA13_238 <= 'z')) ) {s = 38;}
						else if ( ((LA13_238 >= '-' && LA13_238 <= '.')||LA13_238=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 293;
						 
						input.seek(index13_238);
						if ( s>=0 ) return s;
						break;

					case 13 : 
						int LA13_5 = input.LA(1);
						 
						int index13_5 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_5=='a') ) {s = 40;}
						else if ( (LA13_5=='i') ) {s = 41;}
						else if ( ((LA13_5 >= '0' && LA13_5 <= '9')||(LA13_5 >= 'A' && LA13_5 <= 'Z')||(LA13_5 >= 'b' && LA13_5 <= 'h')||(LA13_5 >= 'j' && LA13_5 <= 'z')) ) {s = 38;}
						else if ( ((LA13_5 >= '-' && LA13_5 <= '.')||LA13_5=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_5);
						if ( s>=0 ) return s;
						break;

					case 14 : 
						int LA13_17 = input.LA(1);
						 
						int index13_17 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_17=='e') ) {s = 55;}
						else if ( (LA13_17=='u') ) {s = 56;}
						else if ( ((LA13_17 >= '0' && LA13_17 <= '9')||(LA13_17 >= 'A' && LA13_17 <= 'Z')||(LA13_17 >= 'a' && LA13_17 <= 'd')||(LA13_17 >= 'f' && LA13_17 <= 't')||(LA13_17 >= 'v' && LA13_17 <= 'z')) ) {s = 38;}
						else if ( ((LA13_17 >= '-' && LA13_17 <= '.')||LA13_17=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_17);
						if ( s>=0 ) return s;
						break;

					case 15 : 
						int LA13_53 = input.LA(1);
						 
						int index13_53 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_53=='t') ) {s = 109;}
						else if ( ((LA13_53 >= '0' && LA13_53 <= '9')||(LA13_53 >= 'A' && LA13_53 <= 'Z')||(LA13_53 >= 'a' && LA13_53 <= 's')||(LA13_53 >= 'u' && LA13_53 <= 'z')) ) {s = 38;}
						else if ( ((LA13_53 >= '-' && LA13_53 <= '.')||LA13_53=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 110;
						 
						input.seek(index13_53);
						if ( s>=0 ) return s;
						break;

					case 16 : 
						int LA13_109 = input.LA(1);
						 
						int index13_109 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_109=='h') ) {s = 180;}
						else if ( ((LA13_109 >= '0' && LA13_109 <= '9')||(LA13_109 >= 'A' && LA13_109 <= 'Z')||(LA13_109 >= 'a' && LA13_109 <= 'g')||(LA13_109 >= 'i' && LA13_109 <= 'z')) ) {s = 38;}
						else if ( ((LA13_109 >= '-' && LA13_109 <= '.')||LA13_109=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 181;
						 
						input.seek(index13_109);
						if ( s>=0 ) return s;
						break;

					case 17 : 
						int LA13_54 = input.LA(1);
						 
						int index13_54 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_54=='N') ) {s = 111;}
						else if ( ((LA13_54 >= '0' && LA13_54 <= '9')||(LA13_54 >= 'A' && LA13_54 <= 'M')||(LA13_54 >= 'O' && LA13_54 <= 'Z')||(LA13_54 >= 'a' && LA13_54 <= 'z')) ) {s = 38;}
						else if ( ((LA13_54 >= '-' && LA13_54 <= '.')||LA13_54=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 112;
						 
						input.seek(index13_54);
						if ( s>=0 ) return s;
						break;

					case 18 : 
						int LA13_36 = input.LA(1);
						 
						int index13_36 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_36=='a') ) {s = 77;}
						else if ( ((LA13_36 >= '0' && LA13_36 <= '9')||(LA13_36 >= 'A' && LA13_36 <= 'Z')||(LA13_36 >= 'b' && LA13_36 <= 'z')) ) {s = 38;}
						else if ( ((LA13_36 >= '-' && LA13_36 <= '.')||LA13_36=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 78;
						 
						input.seek(index13_36);
						if ( s>=0 ) return s;
						break;

					case 19 : 
						int LA13_77 = input.LA(1);
						 
						int index13_77 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_77=='p') ) {s = 150;}
						else if ( ((LA13_77 >= '0' && LA13_77 <= '9')||(LA13_77 >= 'A' && LA13_77 <= 'Z')||(LA13_77 >= 'a' && LA13_77 <= 'o')||(LA13_77 >= 'q' && LA13_77 <= 'z')) ) {s = 38;}
						else if ( ((LA13_77 >= '-' && LA13_77 <= '.')||LA13_77=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 151;
						 
						input.seek(index13_77);
						if ( s>=0 ) return s;
						break;

					case 20 : 
						int LA13_150 = input.LA(1);
						 
						int index13_150 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_150=='t') ) {s = 219;}
						else if ( ((LA13_150 >= '0' && LA13_150 <= '9')||(LA13_150 >= 'A' && LA13_150 <= 'Z')||(LA13_150 >= 'a' && LA13_150 <= 's')||(LA13_150 >= 'u' && LA13_150 <= 'z')) ) {s = 38;}
						else if ( ((LA13_150 >= '-' && LA13_150 <= '.')||LA13_150=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 220;
						 
						input.seek(index13_150);
						if ( s>=0 ) return s;
						break;

					case 21 : 
						int LA13_219 = input.LA(1);
						 
						int index13_219 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_219=='i') ) {s = 280;}
						else if ( ((LA13_219 >= '0' && LA13_219 <= '9')||(LA13_219 >= 'A' && LA13_219 <= 'Z')||(LA13_219 >= 'a' && LA13_219 <= 'h')||(LA13_219 >= 'j' && LA13_219 <= 'z')) ) {s = 38;}
						else if ( ((LA13_219 >= '-' && LA13_219 <= '.')||LA13_219=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 281;
						 
						input.seek(index13_219);
						if ( s>=0 ) return s;
						break;

					case 22 : 
						int LA13_280 = input.LA(1);
						 
						int index13_280 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_280=='v') ) {s = 329;}
						else if ( ((LA13_280 >= '0' && LA13_280 <= '9')||(LA13_280 >= 'A' && LA13_280 <= 'Z')||(LA13_280 >= 'a' && LA13_280 <= 'u')||(LA13_280 >= 'w' && LA13_280 <= 'z')) ) {s = 38;}
						else if ( ((LA13_280 >= '-' && LA13_280 <= '.')||LA13_280=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 330;
						 
						input.seek(index13_280);
						if ( s>=0 ) return s;
						break;

					case 23 : 
						int LA13_329 = input.LA(1);
						 
						int index13_329 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_329=='e') ) {s = 368;}
						else if ( ((LA13_329 >= '0' && LA13_329 <= '9')||(LA13_329 >= 'A' && LA13_329 <= 'Z')||(LA13_329 >= 'a' && LA13_329 <= 'd')||(LA13_329 >= 'f' && LA13_329 <= 'z')) ) {s = 38;}
						else if ( ((LA13_329 >= '-' && LA13_329 <= '.')||LA13_329=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 369;
						 
						input.seek(index13_329);
						if ( s>=0 ) return s;
						break;

					case 24 : 
						int LA13_368 = input.LA(1);
						 
						int index13_368 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_368=='R') ) {s = 402;}
						else if ( ((LA13_368 >= '0' && LA13_368 <= '9')||(LA13_368 >= 'A' && LA13_368 <= 'Q')||(LA13_368 >= 'S' && LA13_368 <= 'Z')||(LA13_368 >= 'a' && LA13_368 <= 'z')) ) {s = 38;}
						else if ( ((LA13_368 >= '-' && LA13_368 <= '.')||LA13_368=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 403;
						 
						input.seek(index13_368);
						if ( s>=0 ) return s;
						break;

					case 25 : 
						int LA13_402 = input.LA(1);
						 
						int index13_402 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_402=='a') ) {s = 429;}
						else if ( ((LA13_402 >= '0' && LA13_402 <= '9')||(LA13_402 >= 'A' && LA13_402 <= 'Z')||(LA13_402 >= 'b' && LA13_402 <= 'z')) ) {s = 38;}
						else if ( ((LA13_402 >= '-' && LA13_402 <= '.')||LA13_402=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 430;
						 
						input.seek(index13_402);
						if ( s>=0 ) return s;
						break;

					case 26 : 
						int LA13_429 = input.LA(1);
						 
						int index13_429 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_429=='t') ) {s = 447;}
						else if ( ((LA13_429 >= '0' && LA13_429 <= '9')||(LA13_429 >= 'A' && LA13_429 <= 'Z')||(LA13_429 >= 'a' && LA13_429 <= 's')||(LA13_429 >= 'u' && LA13_429 <= 'z')) ) {s = 38;}
						else if ( ((LA13_429 >= '-' && LA13_429 <= '.')||LA13_429=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 448;
						 
						input.seek(index13_429);
						if ( s>=0 ) return s;
						break;

					case 27 : 
						int LA13_447 = input.LA(1);
						 
						int index13_447 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_447=='e') ) {s = 455;}
						else if ( ((LA13_447 >= '0' && LA13_447 <= '9')||(LA13_447 >= 'A' && LA13_447 <= 'Z')||(LA13_447 >= 'a' && LA13_447 <= 'd')||(LA13_447 >= 'f' && LA13_447 <= 'z')) ) {s = 38;}
						else if ( ((LA13_447 >= '-' && LA13_447 <= '.')||LA13_447=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 456;
						 
						input.seek(index13_447);
						if ( s>=0 ) return s;
						break;

					case 28 : 
						int LA13_37 = input.LA(1);
						 
						int index13_37 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_37=='s') ) {s = 79;}
						else if ( ((LA13_37 >= '0' && LA13_37 <= '9')||(LA13_37 >= 'A' && LA13_37 <= 'Z')||(LA13_37 >= 'a' && LA13_37 <= 'r')||(LA13_37 >= 't' && LA13_37 <= 'z')) ) {s = 38;}
						else if ( ((LA13_37 >= '-' && LA13_37 <= '.')||LA13_37=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 80;
						 
						input.seek(index13_37);
						if ( s>=0 ) return s;
						break;

					case 29 : 
						int LA13_79 = input.LA(1);
						 
						int index13_79 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_79=='o') ) {s = 152;}
						else if ( ((LA13_79 >= '0' && LA13_79 <= '9')||(LA13_79 >= 'A' && LA13_79 <= 'Z')||(LA13_79 >= 'a' && LA13_79 <= 'n')||(LA13_79 >= 'p' && LA13_79 <= 'z')) ) {s = 38;}
						else if ( ((LA13_79 >= '-' && LA13_79 <= '.')||LA13_79=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 153;
						 
						input.seek(index13_79);
						if ( s>=0 ) return s;
						break;

					case 30 : 
						int LA13_152 = input.LA(1);
						 
						int index13_152 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_152==' ') ) {s = 221;}
						else if ( ((LA13_152 >= '0' && LA13_152 <= '9')||(LA13_152 >= 'A' && LA13_152 <= 'Z')||(LA13_152 >= 'a' && LA13_152 <= 'z')) ) {s = 38;}
						else if ( ((LA13_152 >= '-' && LA13_152 <= '.')||LA13_152=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 222;
						 
						input.seek(index13_152);
						if ( s>=0 ) return s;
						break;

					case 31 : 
						int LA13_404 = input.LA(1);
						 
						int index13_404 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_404 >= '0' && LA13_404 <= '9')||(LA13_404 >= 'A' && LA13_404 <= 'Z')||(LA13_404 >= 'a' && LA13_404 <= 'z')) ) {s = 38;}
						else if ( ((LA13_404 >= '-' && LA13_404 <= '.')||LA13_404=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 431;
						 
						input.seek(index13_404);
						if ( s>=0 ) return s;
						break;

					case 32 : 
						int LA13_55 = input.LA(1);
						 
						int index13_55 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_55=='a') ) {s = 113;}
						else if ( ((LA13_55 >= '0' && LA13_55 <= '9')||(LA13_55 >= 'A' && LA13_55 <= 'Z')||(LA13_55 >= 'b' && LA13_55 <= 'z')) ) {s = 38;}
						else if ( ((LA13_55 >= '-' && LA13_55 <= '.')||LA13_55=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 114;
						 
						input.seek(index13_55);
						if ( s>=0 ) return s;
						break;

					case 33 : 
						int LA13_113 = input.LA(1);
						 
						int index13_113 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_113=='r') ) {s = 183;}
						else if ( ((LA13_113 >= '0' && LA13_113 <= '9')||(LA13_113 >= 'A' && LA13_113 <= 'Z')||(LA13_113 >= 'a' && LA13_113 <= 'q')||(LA13_113 >= 's' && LA13_113 <= 'z')) ) {s = 38;}
						else if ( ((LA13_113 >= '-' && LA13_113 <= '.')||LA13_113=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 184;
						 
						input.seek(index13_113);
						if ( s>=0 ) return s;
						break;

					case 34 : 
						int LA13_183 = input.LA(1);
						 
						int index13_183 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_183=='i') ) {s = 246;}
						else if ( ((LA13_183 >= '0' && LA13_183 <= '9')||(LA13_183 >= 'A' && LA13_183 <= 'Z')||(LA13_183 >= 'a' && LA13_183 <= 'h')||(LA13_183 >= 'j' && LA13_183 <= 'z')) ) {s = 38;}
						else if ( ((LA13_183 >= '-' && LA13_183 <= '.')||LA13_183=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 247;
						 
						input.seek(index13_183);
						if ( s>=0 ) return s;
						break;

					case 35 : 
						int LA13_246 = input.LA(1);
						 
						int index13_246 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_246=='s') ) {s = 298;}
						else if ( ((LA13_246 >= '0' && LA13_246 <= '9')||(LA13_246 >= 'A' && LA13_246 <= 'Z')||(LA13_246 >= 'a' && LA13_246 <= 'r')||(LA13_246 >= 't' && LA13_246 <= 'z')) ) {s = 38;}
						else if ( ((LA13_246 >= '-' && LA13_246 <= '.')||LA13_246=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 299;
						 
						input.seek(index13_246);
						if ( s>=0 ) return s;
						break;

					case 36 : 
						int LA13_298 = input.LA(1);
						 
						int index13_298 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_298=='h') ) {s = 344;}
						else if ( ((LA13_298 >= '0' && LA13_298 <= '9')||(LA13_298 >= 'A' && LA13_298 <= 'Z')||(LA13_298 >= 'a' && LA13_298 <= 'g')||(LA13_298 >= 'i' && LA13_298 <= 'z')) ) {s = 38;}
						else if ( ((LA13_298 >= '-' && LA13_298 <= '.')||LA13_298=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 345;
						 
						input.seek(index13_298);
						if ( s>=0 ) return s;
						break;

					case 37 : 
						int LA13_56 = input.LA(1);
						 
						int index13_56 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_56=='l') ) {s = 115;}
						else if ( ((LA13_56 >= '0' && LA13_56 <= '9')||(LA13_56 >= 'A' && LA13_56 <= 'Z')||(LA13_56 >= 'a' && LA13_56 <= 'k')||(LA13_56 >= 'm' && LA13_56 <= 'z')) ) {s = 38;}
						else if ( ((LA13_56 >= '-' && LA13_56 <= '.')||LA13_56=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 116;
						 
						input.seek(index13_56);
						if ( s>=0 ) return s;
						break;

					case 38 : 
						int LA13_115 = input.LA(1);
						 
						int index13_115 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_115=='l') ) {s = 185;}
						else if ( ((LA13_115 >= '0' && LA13_115 <= '9')||(LA13_115 >= 'A' && LA13_115 <= 'Z')||(LA13_115 >= 'a' && LA13_115 <= 'k')||(LA13_115 >= 'm' && LA13_115 <= 'z')) ) {s = 38;}
						else if ( ((LA13_115 >= '-' && LA13_115 <= '.')||LA13_115=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 186;
						 
						input.seek(index13_115);
						if ( s>=0 ) return s;
						break;

					case 39 : 
						int LA13_185 = input.LA(1);
						 
						int index13_185 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_185=='i') ) {s = 248;}
						else if ( ((LA13_185 >= '0' && LA13_185 <= '9')||(LA13_185 >= 'A' && LA13_185 <= 'Z')||(LA13_185 >= 'a' && LA13_185 <= 'h')||(LA13_185 >= 'j' && LA13_185 <= 'z')) ) {s = 38;}
						else if ( ((LA13_185 >= '-' && LA13_185 <= '.')||LA13_185=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 249;
						 
						input.seek(index13_185);
						if ( s>=0 ) return s;
						break;

					case 40 : 
						int LA13_87 = input.LA(1);
						 
						int index13_87 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_87);
						if ( s>=0 ) return s;
						break;

					case 41 : 
						int LA13_83 = input.LA(1);
						 
						int index13_83 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_83);
						if ( s>=0 ) return s;
						break;

					case 42 : 
						int LA13_108 = input.LA(1);
						 
						int index13_108 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_108);
						if ( s>=0 ) return s;
						break;

					case 43 : 
						int LA13_255 = input.LA(1);
						 
						int index13_255 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_255);
						if ( s>=0 ) return s;
						break;

					case 44 : 
						int LA13_243 = input.LA(1);
						 
						int index13_243 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_243);
						if ( s>=0 ) return s;
						break;

					case 45 : 
						int LA13_151 = input.LA(1);
						 
						int index13_151 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_151);
						if ( s>=0 ) return s;
						break;

					case 46 : 
						int LA13_253 = input.LA(1);
						 
						int index13_253 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_253);
						if ( s>=0 ) return s;
						break;

					case 47 : 
						int LA13_153 = input.LA(1);
						 
						int index13_153 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_153);
						if ( s>=0 ) return s;
						break;

					case 48 : 
						int LA13_126 = input.LA(1);
						 
						int index13_126 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_126);
						if ( s>=0 ) return s;
						break;

					case 49 : 
						int LA13_128 = input.LA(1);
						 
						int index13_128 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_128);
						if ( s>=0 ) return s;
						break;

					case 50 : 
						int LA13_155 = input.LA(1);
						 
						int index13_155 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_155);
						if ( s>=0 ) return s;
						break;

					case 51 : 
						int LA13_206 = input.LA(1);
						 
						int index13_206 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_206);
						if ( s>=0 ) return s;
						break;

					case 52 : 
						int LA13_328 = input.LA(1);
						 
						int index13_328 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_328);
						if ( s>=0 ) return s;
						break;

					case 53 : 
						int LA13_366 = input.LA(1);
						 
						int index13_366 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_366);
						if ( s>=0 ) return s;
						break;

					case 54 : 
						int LA13_146 = input.LA(1);
						 
						int index13_146 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_146);
						if ( s>=0 ) return s;
						break;

					case 55 : 
						int LA13_349 = input.LA(1);
						 
						int index13_349 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_349);
						if ( s>=0 ) return s;
						break;

					case 56 : 
						int LA13_164 = input.LA(1);
						 
						int index13_164 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_164);
						if ( s>=0 ) return s;
						break;

					case 57 : 
						int LA13_279 = input.LA(1);
						 
						int index13_279 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_279);
						if ( s>=0 ) return s;
						break;

					case 58 : 
						int LA13_395 = input.LA(1);
						 
						int index13_395 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_395);
						if ( s>=0 ) return s;
						break;

					case 59 : 
						int LA13_293 = input.LA(1);
						 
						int index13_293 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_293);
						if ( s>=0 ) return s;
						break;

					case 60 : 
						int LA13_374 = input.LA(1);
						 
						int index13_374 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_374);
						if ( s>=0 ) return s;
						break;

					case 61 : 
						int LA13_104 = input.LA(1);
						 
						int index13_104 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_104);
						if ( s>=0 ) return s;
						break;

					case 62 : 
						int LA13_175 = input.LA(1);
						 
						int index13_175 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_175);
						if ( s>=0 ) return s;
						break;

					case 63 : 
						int LA13_362 = input.LA(1);
						 
						int index13_362 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_362);
						if ( s>=0 ) return s;
						break;

					case 64 : 
						int LA13_299 = input.LA(1);
						 
						int index13_299 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_299);
						if ( s>=0 ) return s;
						break;

					case 65 : 
						int LA13_232 = input.LA(1);
						 
						int index13_232 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_232);
						if ( s>=0 ) return s;
						break;

					case 66 : 
						int LA13_283 = input.LA(1);
						 
						int index13_283 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_283);
						if ( s>=0 ) return s;
						break;

					case 67 : 
						int LA13_196 = input.LA(1);
						 
						int index13_196 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_196);
						if ( s>=0 ) return s;
						break;

					case 68 : 
						int LA13_214 = input.LA(1);
						 
						int index13_214 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_214);
						if ( s>=0 ) return s;
						break;

					case 69 : 
						int LA13_186 = input.LA(1);
						 
						int index13_186 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_186);
						if ( s>=0 ) return s;
						break;

					case 70 : 
						int LA13_76 = input.LA(1);
						 
						int index13_76 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_76);
						if ( s>=0 ) return s;
						break;

					case 71 : 
						int LA13_124 = input.LA(1);
						 
						int index13_124 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_124);
						if ( s>=0 ) return s;
						break;

					case 72 : 
						int LA13_324 = input.LA(1);
						 
						int index13_324 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_324);
						if ( s>=0 ) return s;
						break;

					case 73 : 
						int LA13_377 = input.LA(1);
						 
						int index13_377 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_377);
						if ( s>=0 ) return s;
						break;

					case 74 : 
						int LA13_248 = input.LA(1);
						 
						int index13_248 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_248=='s') ) {s = 300;}
						else if ( ((LA13_248 >= '0' && LA13_248 <= '9')||(LA13_248 >= 'A' && LA13_248 <= 'Z')||(LA13_248 >= 'a' && LA13_248 <= 'r')||(LA13_248 >= 't' && LA13_248 <= 'z')) ) {s = 38;}
						else if ( ((LA13_248 >= '-' && LA13_248 <= '.')||LA13_248=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 301;
						 
						input.seek(index13_248);
						if ( s>=0 ) return s;
						break;

					case 75 : 
						int LA13_166 = input.LA(1);
						 
						int index13_166 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_166);
						if ( s>=0 ) return s;
						break;

					case 76 : 
						int LA13_171 = input.LA(1);
						 
						int index13_171 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_171);
						if ( s>=0 ) return s;
						break;

					case 77 : 
						int LA13_301 = input.LA(1);
						 
						int index13_301 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_301);
						if ( s>=0 ) return s;
						break;

					case 78 : 
						int LA13_194 = input.LA(1);
						 
						int index13_194 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_194);
						if ( s>=0 ) return s;
						break;

					case 79 : 
						int LA13_291 = input.LA(1);
						 
						int index13_291 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_291);
						if ( s>=0 ) return s;
						break;

					case 80 : 
						int LA13_268 = input.LA(1);
						 
						int index13_268 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_268);
						if ( s>=0 ) return s;
						break;

					case 81 : 
						int LA13_234 = input.LA(1);
						 
						int index13_234 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_234);
						if ( s>=0 ) return s;
						break;

					case 82 : 
						int LA13_132 = input.LA(1);
						 
						int index13_132 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_132);
						if ( s>=0 ) return s;
						break;

					case 83 : 
						int LA13_78 = input.LA(1);
						 
						int index13_78 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_78);
						if ( s>=0 ) return s;
						break;

					case 84 : 
						int LA13_80 = input.LA(1);
						 
						int index13_80 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_80);
						if ( s>=0 ) return s;
						break;

					case 85 : 
						int LA13_106 = input.LA(1);
						 
						int index13_106 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_106);
						if ( s>=0 ) return s;
						break;

					case 86 : 
						int LA13_326 = input.LA(1);
						 
						int index13_326 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_326);
						if ( s>=0 ) return s;
						break;

					case 87 : 
						int LA13_320 = input.LA(1);
						 
						int index13_320 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_320);
						if ( s>=0 ) return s;
						break;

					case 88 : 
						int LA13_430 = input.LA(1);
						 
						int index13_430 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_430);
						if ( s>=0 ) return s;
						break;

					case 89 : 
						int LA13_162 = input.LA(1);
						 
						int index13_162 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_162);
						if ( s>=0 ) return s;
						break;

					case 90 : 
						int LA13_173 = input.LA(1);
						 
						int index13_173 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_173);
						if ( s>=0 ) return s;
						break;

					case 91 : 
						int LA13_310 = input.LA(1);
						 
						int index13_310 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_310);
						if ( s>=0 ) return s;
						break;

					case 92 : 
						int LA13_140 = input.LA(1);
						 
						int index13_140 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_140);
						if ( s>=0 ) return s;
						break;

					case 93 : 
						int LA13_142 = input.LA(1);
						 
						int index13_142 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_142);
						if ( s>=0 ) return s;
						break;

					case 94 : 
						int LA13_181 = input.LA(1);
						 
						int index13_181 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_181);
						if ( s>=0 ) return s;
						break;

					case 95 : 
						int LA13_199 = input.LA(1);
						 
						int index13_199 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_199);
						if ( s>=0 ) return s;
						break;

					case 96 : 
						int LA13_285 = input.LA(1);
						 
						int index13_285 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_285);
						if ( s>=0 ) return s;
						break;

					case 97 : 
						int LA13_116 = input.LA(1);
						 
						int index13_116 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_116);
						if ( s>=0 ) return s;
						break;

					case 98 : 
						int LA13_318 = input.LA(1);
						 
						int index13_318 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_318);
						if ( s>=0 ) return s;
						break;

					case 99 : 
						int LA13_339 = input.LA(1);
						 
						int index13_339 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_339);
						if ( s>=0 ) return s;
						break;

					case 100 : 
						int LA13_263 = input.LA(1);
						 
						int index13_263 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_263);
						if ( s>=0 ) return s;
						break;

					case 101 : 
						int LA13_270 = input.LA(1);
						 
						int index13_270 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_270);
						if ( s>=0 ) return s;
						break;

					case 102 : 
						int LA13_208 = input.LA(1);
						 
						int index13_208 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_208);
						if ( s>=0 ) return s;
						break;

					case 103 : 
						int LA13_212 = input.LA(1);
						 
						int index13_212 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_212);
						if ( s>=0 ) return s;
						break;

					case 104 : 
						int LA13_249 = input.LA(1);
						 
						int index13_249 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_249);
						if ( s>=0 ) return s;
						break;

					case 105 : 
						int LA13_334 = input.LA(1);
						 
						int index13_334 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_334);
						if ( s>=0 ) return s;
						break;

					case 106 : 
						int LA13_134 = input.LA(1);
						 
						int index13_134 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_134);
						if ( s>=0 ) return s;
						break;

					case 107 : 
						int LA13_190 = input.LA(1);
						 
						int index13_190 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_190);
						if ( s>=0 ) return s;
						break;

					case 108 : 
						int LA13_218 = input.LA(1);
						 
						int index13_218 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_218);
						if ( s>=0 ) return s;
						break;

					case 109 : 
						int LA13_456 = input.LA(1);
						 
						int index13_456 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_456);
						if ( s>=0 ) return s;
						break;

					case 110 : 
						int LA13_369 = input.LA(1);
						 
						int index13_369 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_369);
						if ( s>=0 ) return s;
						break;

					case 111 : 
						int LA13_411 = input.LA(1);
						 
						int index13_411 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_411);
						if ( s>=0 ) return s;
						break;

					case 112 : 
						int LA13_216 = input.LA(1);
						 
						int index13_216 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_216);
						if ( s>=0 ) return s;
						break;

					case 113 : 
						int LA13_424 = input.LA(1);
						 
						int index13_424 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_424);
						if ( s>=0 ) return s;
						break;

					case 114 : 
						int LA13_259 = input.LA(1);
						 
						int index13_259 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_259);
						if ( s>=0 ) return s;
						break;

					case 115 : 
						int LA13_118 = input.LA(1);
						 
						int index13_118 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_118);
						if ( s>=0 ) return s;
						break;

					case 116 : 
						int LA13_101 = input.LA(1);
						 
						int index13_101 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_101);
						if ( s>=0 ) return s;
						break;

					case 117 : 
						int LA13_306 = input.LA(1);
						 
						int index13_306 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_306);
						if ( s>=0 ) return s;
						break;

					case 118 : 
						int LA13_157 = input.LA(1);
						 
						int index13_157 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_157);
						if ( s>=0 ) return s;
						break;

					case 119 : 
						int LA13_274 = input.LA(1);
						 
						int index13_274 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_274);
						if ( s>=0 ) return s;
						break;

					case 120 : 
						int LA13_130 = input.LA(1);
						 
						int index13_130 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_130);
						if ( s>=0 ) return s;
						break;

					case 121 : 
						int LA13_136 = input.LA(1);
						 
						int index13_136 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_136);
						if ( s>=0 ) return s;
						break;

					case 122 : 
						int LA13_281 = input.LA(1);
						 
						int index13_281 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_281);
						if ( s>=0 ) return s;
						break;

					case 123 : 
						int LA13_399 = input.LA(1);
						 
						int index13_399 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_399);
						if ( s>=0 ) return s;
						break;

					case 124 : 
						int LA13_276 = input.LA(1);
						 
						int index13_276 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_276);
						if ( s>=0 ) return s;
						break;

					case 125 : 
						int LA13_351 = input.LA(1);
						 
						int index13_351 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_351);
						if ( s>=0 ) return s;
						break;

					case 126 : 
						int LA13_297 = input.LA(1);
						 
						int index13_297 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_297);
						if ( s>=0 ) return s;
						break;

					case 127 : 
						int LA13_341 = input.LA(1);
						 
						int index13_341 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_341);
						if ( s>=0 ) return s;
						break;

					case 128 : 
						int LA13_148 = input.LA(1);
						 
						int index13_148 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_148);
						if ( s>=0 ) return s;
						break;

					case 129 : 
						int LA13_225 = input.LA(1);
						 
						int index13_225 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_225);
						if ( s>=0 ) return s;
						break;

					case 130 : 
						int LA13_289 = input.LA(1);
						 
						int index13_289 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_289);
						if ( s>=0 ) return s;
						break;

					case 131 : 
						int LA13_287 = input.LA(1);
						 
						int index13_287 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_287);
						if ( s>=0 ) return s;
						break;

					case 132 : 
						int LA13_295 = input.LA(1);
						 
						int index13_295 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_295);
						if ( s>=0 ) return s;
						break;

					case 133 : 
						int LA13_303 = input.LA(1);
						 
						int index13_303 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_303);
						if ( s>=0 ) return s;
						break;

					case 134 : 
						int LA13_192 = input.LA(1);
						 
						int index13_192 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_192);
						if ( s>=0 ) return s;
						break;

					case 135 : 
						int LA13_247 = input.LA(1);
						 
						int index13_247 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_247);
						if ( s>=0 ) return s;
						break;

					case 136 : 
						int LA13_383 = input.LA(1);
						 
						int index13_383 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_383);
						if ( s>=0 ) return s;
						break;

					case 137 : 
						int LA13_332 = input.LA(1);
						 
						int index13_332 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_332);
						if ( s>=0 ) return s;
						break;

					case 138 : 
						int LA13_188 = input.LA(1);
						 
						int index13_188 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_188);
						if ( s>=0 ) return s;
						break;

					case 139 : 
						int LA13_92 = input.LA(1);
						 
						int index13_92 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_92);
						if ( s>=0 ) return s;
						break;

					case 140 : 
						int LA13_81 = input.LA(1);
						 
						int index13_81 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_81);
						if ( s>=0 ) return s;
						break;

					case 141 : 
						int LA13_112 = input.LA(1);
						 
						int index13_112 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_112);
						if ( s>=0 ) return s;
						break;

					case 142 : 
						int LA13_257 = input.LA(1);
						 
						int index13_257 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_257);
						if ( s>=0 ) return s;
						break;

					case 143 : 
						int LA13_322 = input.LA(1);
						 
						int index13_322 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_322);
						if ( s>=0 ) return s;
						break;

					case 144 : 
						int LA13_371 = input.LA(1);
						 
						int index13_371 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_371);
						if ( s>=0 ) return s;
						break;

					case 145 : 
						int LA13_210 = input.LA(1);
						 
						int index13_210 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_210);
						if ( s>=0 ) return s;
						break;

					case 146 : 
						int LA13_387 = input.LA(1);
						 
						int index13_387 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_387);
						if ( s>=0 ) return s;
						break;

					case 147 : 
						int LA13_99 = input.LA(1);
						 
						int index13_99 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_99);
						if ( s>=0 ) return s;
						break;

					case 148 : 
						int LA13_114 = input.LA(1);
						 
						int index13_114 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_114);
						if ( s>=0 ) return s;
						break;

					case 149 : 
						int LA13_122 = input.LA(1);
						 
						int index13_122 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_122);
						if ( s>=0 ) return s;
						break;

					case 150 : 
						int LA13_405 = input.LA(1);
						 
						int index13_405 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_405);
						if ( s>=0 ) return s;
						break;

					case 151 : 
						int LA13_220 = input.LA(1);
						 
						int index13_220 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_220);
						if ( s>=0 ) return s;
						break;

					case 152 : 
						int LA13_144 = input.LA(1);
						 
						int index13_144 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_144);
						if ( s>=0 ) return s;
						break;

					case 153 : 
						int LA13_308 = input.LA(1);
						 
						int index13_308 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_308);
						if ( s>=0 ) return s;
						break;

					case 154 : 
						int LA13_428 = input.LA(1);
						 
						int index13_428 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_428);
						if ( s>=0 ) return s;
						break;

					case 155 : 
						int LA13_227 = input.LA(1);
						 
						int index13_227 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_227);
						if ( s>=0 ) return s;
						break;

					case 156 : 
						int LA13_177 = input.LA(1);
						 
						int index13_177 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_177);
						if ( s>=0 ) return s;
						break;

					case 157 : 
						int LA13_85 = input.LA(1);
						 
						int index13_85 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_85);
						if ( s>=0 ) return s;
						break;

					case 158 : 
						int LA13_94 = input.LA(1);
						 
						int index13_94 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_94);
						if ( s>=0 ) return s;
						break;

					case 159 : 
						int LA13_448 = input.LA(1);
						 
						int index13_448 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_448);
						if ( s>=0 ) return s;
						break;

					case 160 : 
						int LA13_110 = input.LA(1);
						 
						int index13_110 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_110);
						if ( s>=0 ) return s;
						break;

					case 161 : 
						int LA13_120 = input.LA(1);
						 
						int index13_120 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_120);
						if ( s>=0 ) return s;
						break;

					case 162 : 
						int LA13_330 = input.LA(1);
						 
						int index13_330 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_330);
						if ( s>=0 ) return s;
						break;

					case 163 : 
						int LA13_345 = input.LA(1);
						 
						int index13_345 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_345);
						if ( s>=0 ) return s;
						break;

					case 164 : 
						int LA13_343 = input.LA(1);
						 
						int index13_343 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_343);
						if ( s>=0 ) return s;
						break;

					case 165 : 
						int LA13_364 = input.LA(1);
						 
						int index13_364 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_364);
						if ( s>=0 ) return s;
						break;

					case 166 : 
						int LA13_241 = input.LA(1);
						 
						int index13_241 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_241);
						if ( s>=0 ) return s;
						break;

					case 167 : 
						int LA13_401 = input.LA(1);
						 
						int index13_401 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_401);
						if ( s>=0 ) return s;
						break;

					case 168 : 
						int LA13_138 = input.LA(1);
						 
						int index13_138 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_138);
						if ( s>=0 ) return s;
						break;

					case 169 : 
						int LA13_179 = input.LA(1);
						 
						int index13_179 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_179);
						if ( s>=0 ) return s;
						break;

					case 170 : 
						int LA13_222 = input.LA(1);
						 
						int index13_222 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_222);
						if ( s>=0 ) return s;
						break;

					case 171 : 
						int LA13_397 = input.LA(1);
						 
						int index13_397 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_397);
						if ( s>=0 ) return s;
						break;

					case 172 : 
						int LA13_184 = input.LA(1);
						 
						int index13_184 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_184);
						if ( s>=0 ) return s;
						break;

					case 173 : 
						int LA13_403 = input.LA(1);
						 
						int index13_403 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_403);
						if ( s>=0 ) return s;
						break;

					case 174 : 
						int LA13_272 = input.LA(1);
						 
						int index13_272 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_272);
						if ( s>=0 ) return s;
						break;

					case 175 : 
						int LA13_169 = input.LA(1);
						 
						int index13_169 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_169);
						if ( s>=0 ) return s;
						break;

					case 176 : 
						int LA13_336 = input.LA(1);
						 
						int index13_336 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_336);
						if ( s>=0 ) return s;
						break;

					case 177 : 
						int LA13_347 = input.LA(1);
						 
						int index13_347 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_347);
						if ( s>=0 ) return s;
						break;

					case 178 : 
						int LA13_353 = input.LA(1);
						 
						int index13_353 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_353);
						if ( s>=0 ) return s;
						break;

					case 179 : 
						int LA13_261 = input.LA(1);
						 
						int index13_261 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_261);
						if ( s>=0 ) return s;
						break;

					case 180 : 
						int LA13_230 = input.LA(1);
						 
						int index13_230 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_230);
						if ( s>=0 ) return s;
						break;

					case 181 : 
						int LA13_251 = input.LA(1);
						 
						int index13_251 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_251);
						if ( s>=0 ) return s;
						break;

					case 182 : 
						int LA13_415 = input.LA(1);
						 
						int index13_415 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_415);
						if ( s>=0 ) return s;
						break;

					case 183 : 
						int LA13_239 = input.LA(1);
						 
						int index13_239 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_239);
						if ( s>=0 ) return s;
						break;

					case 184 : 
						int LA13_159 = input.LA(1);
						 
						int index13_159 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_159);
						if ( s>=0 ) return s;
						break;

					case 185 : 
						int LA13_89 = input.LA(1);
						 
						int index13_89 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_89);
						if ( s>=0 ) return s;
						break;

					case 186 : 
						int LA13_355 = input.LA(1);
						 
						int index13_355 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_355);
						if ( s>=0 ) return s;
						break;

					case 187 : 
						int LA13_201 = input.LA(1);
						 
						int index13_201 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_201);
						if ( s>=0 ) return s;
						break;

					case 188 : 
						int LA13_360 = input.LA(1);
						 
						int index13_360 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_360);
						if ( s>=0 ) return s;
						break;

					case 189 : 
						int LA13_96 = input.LA(1);
						 
						int index13_96 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_96);
						if ( s>=0 ) return s;
						break;

					case 190 : 
						int LA13_300 = input.LA(1);
						 
						int index13_300 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_300=='h') ) {s = 346;}
						else if ( ((LA13_300 >= '0' && LA13_300 <= '9')||(LA13_300 >= 'A' && LA13_300 <= 'Z')||(LA13_300 >= 'a' && LA13_300 <= 'g')||(LA13_300 >= 'i' && LA13_300 <= 'z')) ) {s = 38;}
						else if ( ((LA13_300 >= '-' && LA13_300 <= '.')||LA13_300=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 347;
						 
						input.seek(index13_300);
						if ( s>=0 ) return s;
						break;

					case 191 : 
						int LA13_178 = input.LA(1);
						 
						int index13_178 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_178 >= '0' && LA13_178 <= '9')||(LA13_178 >= 'A' && LA13_178 <= 'Z')||(LA13_178 >= 'a' && LA13_178 <= 'z')) ) {s = 38;}
						else if ( ((LA13_178 >= '-' && LA13_178 <= '.')||LA13_178=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 244;
						 
						input.seek(index13_178);
						if ( s>=0 ) return s;
						break;

					case 192 : 
						int LA13_352 = input.LA(1);
						 
						int index13_352 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_352 >= '0' && LA13_352 <= '9')||(LA13_352 >= 'A' && LA13_352 <= 'Z')||(LA13_352 >= 'a' && LA13_352 <= 'z')) ) {s = 38;}
						else if ( ((LA13_352 >= '-' && LA13_352 <= '.')||LA13_352=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 388;
						 
						input.seek(index13_352);
						if ( s>=0 ) return s;
						break;

					case 193 : 
						int LA13_57 = input.LA(1);
						 
						int index13_57 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_57=='o') ) {s = 117;}
						else if ( ((LA13_57 >= '0' && LA13_57 <= '9')||(LA13_57 >= 'A' && LA13_57 <= 'Z')||(LA13_57 >= 'a' && LA13_57 <= 'n')||(LA13_57 >= 'p' && LA13_57 <= 'z')) ) {s = 38;}
						else if ( ((LA13_57 >= '-' && LA13_57 <= '.')||LA13_57=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 118;
						 
						input.seek(index13_57);
						if ( s>=0 ) return s;
						break;

					case 194 : 
						int LA13_117 = input.LA(1);
						 
						int index13_117 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_117=='s') ) {s = 187;}
						else if ( ((LA13_117 >= '0' && LA13_117 <= '9')||(LA13_117 >= 'A' && LA13_117 <= 'Z')||(LA13_117 >= 'a' && LA13_117 <= 'r')||(LA13_117 >= 't' && LA13_117 <= 'z')) ) {s = 38;}
						else if ( ((LA13_117 >= '-' && LA13_117 <= '.')||LA13_117=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 188;
						 
						input.seek(index13_117);
						if ( s>=0 ) return s;
						break;

					case 195 : 
						int LA13_1 = input.LA(1);
						 
						int index13_1 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_1=='n') ) {s = 35;}
						else if ( (LA13_1=='d') ) {s = 36;}
						else if ( (LA13_1=='l') ) {s = 37;}
						else if ( ((LA13_1 >= '0' && LA13_1 <= '9')||(LA13_1 >= 'A' && LA13_1 <= 'Z')||(LA13_1 >= 'a' && LA13_1 <= 'c')||(LA13_1 >= 'e' && LA13_1 <= 'k')||LA13_1=='m'||(LA13_1 >= 'o' && LA13_1 <= 'z')) ) {s = 38;}
						else if ( ((LA13_1 >= '-' && LA13_1 <= '.')||LA13_1=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_1);
						if ( s>=0 ) return s;
						break;

					case 196 : 
						int LA13_187 = input.LA(1);
						 
						int index13_187 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_187=='s') ) {s = 250;}
						else if ( ((LA13_187 >= '0' && LA13_187 <= '9')||(LA13_187 >= 'A' && LA13_187 <= 'Z')||(LA13_187 >= 'a' && LA13_187 <= 'r')||(LA13_187 >= 't' && LA13_187 <= 'z')) ) {s = 38;}
						else if ( ((LA13_187 >= '-' && LA13_187 <= '.')||LA13_187=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 251;
						 
						input.seek(index13_187);
						if ( s>=0 ) return s;
						break;

					case 197 : 
						int LA13_7 = input.LA(1);
						 
						int index13_7 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_7=='a') ) {s = 44;}
						else if ( (LA13_7=='o') ) {s = 45;}
						else if ( ((LA13_7 >= '0' && LA13_7 <= '9')||(LA13_7 >= 'A' && LA13_7 <= 'Z')||(LA13_7 >= 'b' && LA13_7 <= 'n')||(LA13_7 >= 'p' && LA13_7 <= 'z')) ) {s = 38;}
						else if ( ((LA13_7 >= '-' && LA13_7 <= '.')||LA13_7=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_7);
						if ( s>=0 ) return s;
						break;

					case 198 : 
						int LA13_250 = input.LA(1);
						 
						int index13_250 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_250=='e') ) {s = 302;}
						else if ( ((LA13_250 >= '0' && LA13_250 <= '9')||(LA13_250 >= 'A' && LA13_250 <= 'Z')||(LA13_250 >= 'a' && LA13_250 <= 'd')||(LA13_250 >= 'f' && LA13_250 <= 'z')) ) {s = 38;}
						else if ( ((LA13_250 >= '-' && LA13_250 <= '.')||LA13_250=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 303;
						 
						input.seek(index13_250);
						if ( s>=0 ) return s;
						break;

					case 199 : 
						int LA13_61 = input.LA(1);
						 
						int index13_61 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_61=='u') ) {s = 125;}
						else if ( ((LA13_61 >= '0' && LA13_61 <= '9')||(LA13_61 >= 'A' && LA13_61 <= 'Z')||(LA13_61 >= 'a' && LA13_61 <= 't')||(LA13_61 >= 'v' && LA13_61 <= 'z')) ) {s = 38;}
						else if ( ((LA13_61 >= '-' && LA13_61 <= '.')||LA13_61=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 126;
						 
						input.seek(index13_61);
						if ( s>=0 ) return s;
						break;

					case 200 : 
						int LA13_302 = input.LA(1);
						 
						int index13_302 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_302=='s') ) {s = 348;}
						else if ( ((LA13_302 >= '0' && LA13_302 <= '9')||(LA13_302 >= 'A' && LA13_302 <= 'Z')||(LA13_302 >= 'a' && LA13_302 <= 'r')||(LA13_302 >= 't' && LA13_302 <= 'z')) ) {s = 38;}
						else if ( ((LA13_302 >= '-' && LA13_302 <= '.')||LA13_302=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 349;
						 
						input.seek(index13_302);
						if ( s>=0 ) return s;
						break;

					case 201 : 
						int LA13_125 = input.LA(1);
						 
						int index13_125 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_125=='a') ) {s = 195;}
						else if ( ((LA13_125 >= '0' && LA13_125 <= '9')||(LA13_125 >= 'A' && LA13_125 <= 'Z')||(LA13_125 >= 'b' && LA13_125 <= 'z')) ) {s = 38;}
						else if ( ((LA13_125 >= '-' && LA13_125 <= '.')||LA13_125=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 196;
						 
						input.seek(index13_125);
						if ( s>=0 ) return s;
						break;

					case 202 : 
						int LA13_348 = input.LA(1);
						 
						int index13_348 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_348==' ') ) {s = 386;}
						else if ( ((LA13_348 >= '0' && LA13_348 <= '9')||(LA13_348 >= 'A' && LA13_348 <= 'Z')||(LA13_348 >= 'a' && LA13_348 <= 'z')) ) {s = 38;}
						else if ( ((LA13_348 >= '-' && LA13_348 <= '.')||LA13_348=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 387;
						 
						input.seek(index13_348);
						if ( s>=0 ) return s;
						break;

					case 203 : 
						int LA13_195 = input.LA(1);
						 
						int index13_195 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_195=='l') ) {s = 258;}
						else if ( ((LA13_195 >= '0' && LA13_195 <= '9')||(LA13_195 >= 'A' && LA13_195 <= 'Z')||(LA13_195 >= 'a' && LA13_195 <= 'k')||(LA13_195 >= 'm' && LA13_195 <= 'z')) ) {s = 38;}
						else if ( ((LA13_195 >= '-' && LA13_195 <= '.')||LA13_195=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 259;
						 
						input.seek(index13_195);
						if ( s>=0 ) return s;
						break;

					case 204 : 
						int LA13_258 = input.LA(1);
						 
						int index13_258 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_258=='s') ) {s = 309;}
						else if ( ((LA13_258 >= '0' && LA13_258 <= '9')||(LA13_258 >= 'A' && LA13_258 <= 'Z')||(LA13_258 >= 'a' && LA13_258 <= 'r')||(LA13_258 >= 't' && LA13_258 <= 'z')) ) {s = 38;}
						else if ( ((LA13_258 >= '-' && LA13_258 <= '.')||LA13_258=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 310;
						 
						input.seek(index13_258);
						if ( s>=0 ) return s;
						break;

					case 205 : 
						int LA13_309 = input.LA(1);
						 
						int index13_309 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_309==' ') ) {s = 354;}
						else if ( ((LA13_309 >= '0' && LA13_309 <= '9')||(LA13_309 >= 'A' && LA13_309 <= 'Z')||(LA13_309 >= 'a' && LA13_309 <= 'z')) ) {s = 38;}
						else if ( ((LA13_309 >= '-' && LA13_309 <= '.')||LA13_309=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 355;
						 
						input.seek(index13_309);
						if ( s>=0 ) return s;
						break;

					case 206 : 
						int LA13_41 = input.LA(1);
						 
						int index13_41 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_41=='r') ) {s = 84;}
						else if ( ((LA13_41 >= '0' && LA13_41 <= '9')||(LA13_41 >= 'A' && LA13_41 <= 'Z')||(LA13_41 >= 'a' && LA13_41 <= 'q')||(LA13_41 >= 's' && LA13_41 <= 'z')) ) {s = 38;}
						else if ( ((LA13_41 >= '-' && LA13_41 <= '.')||LA13_41=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 85;
						 
						input.seek(index13_41);
						if ( s>=0 ) return s;
						break;

					case 207 : 
						int LA13_84 = input.LA(1);
						 
						int index13_84 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_84=='e') ) {s = 156;}
						else if ( ((LA13_84 >= '0' && LA13_84 <= '9')||(LA13_84 >= 'A' && LA13_84 <= 'Z')||(LA13_84 >= 'a' && LA13_84 <= 'd')||(LA13_84 >= 'f' && LA13_84 <= 'z')) ) {s = 38;}
						else if ( ((LA13_84 >= '-' && LA13_84 <= '.')||LA13_84=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 157;
						 
						input.seek(index13_84);
						if ( s>=0 ) return s;
						break;

					case 208 : 
						int LA13_156 = input.LA(1);
						 
						int index13_156 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_156=='c') ) {s = 224;}
						else if ( ((LA13_156 >= '0' && LA13_156 <= '9')||(LA13_156 >= 'A' && LA13_156 <= 'Z')||(LA13_156 >= 'a' && LA13_156 <= 'b')||(LA13_156 >= 'd' && LA13_156 <= 'z')) ) {s = 38;}
						else if ( ((LA13_156 >= '-' && LA13_156 <= '.')||LA13_156=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 225;
						 
						input.seek(index13_156);
						if ( s>=0 ) return s;
						break;

					case 209 : 
						int LA13_224 = input.LA(1);
						 
						int index13_224 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_224=='t') ) {s = 282;}
						else if ( ((LA13_224 >= '0' && LA13_224 <= '9')||(LA13_224 >= 'A' && LA13_224 <= 'Z')||(LA13_224 >= 'a' && LA13_224 <= 's')||(LA13_224 >= 'u' && LA13_224 <= 'z')) ) {s = 38;}
						else if ( ((LA13_224 >= '-' && LA13_224 <= '.')||LA13_224=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 283;
						 
						input.seek(index13_224);
						if ( s>=0 ) return s;
						break;

					case 210 : 
						int LA13_282 = input.LA(1);
						 
						int index13_282 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_282=='i') ) {s = 331;}
						else if ( ((LA13_282 >= '0' && LA13_282 <= '9')||(LA13_282 >= 'A' && LA13_282 <= 'Z')||(LA13_282 >= 'a' && LA13_282 <= 'h')||(LA13_282 >= 'j' && LA13_282 <= 'z')) ) {s = 38;}
						else if ( ((LA13_282 >= '-' && LA13_282 <= '.')||LA13_282=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 332;
						 
						input.seek(index13_282);
						if ( s>=0 ) return s;
						break;

					case 211 : 
						int LA13_331 = input.LA(1);
						 
						int index13_331 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_331=='o') ) {s = 370;}
						else if ( ((LA13_331 >= '0' && LA13_331 <= '9')||(LA13_331 >= 'A' && LA13_331 <= 'Z')||(LA13_331 >= 'a' && LA13_331 <= 'n')||(LA13_331 >= 'p' && LA13_331 <= 'z')) ) {s = 38;}
						else if ( ((LA13_331 >= '-' && LA13_331 <= '.')||LA13_331=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 371;
						 
						input.seek(index13_331);
						if ( s>=0 ) return s;
						break;

					case 212 : 
						int LA13_127 = input.LA(1);
						 
						int index13_127 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_127 >= '0' && LA13_127 <= '9')||(LA13_127 >= 'A' && LA13_127 <= 'Z')||(LA13_127 >= 'a' && LA13_127 <= 'z')) ) {s = 38;}
						else if ( ((LA13_127 >= '-' && LA13_127 <= '.')||LA13_127=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 197;
						 
						input.seek(index13_127);
						if ( s>=0 ) return s;
						break;

					case 213 : 
						int LA13_370 = input.LA(1);
						 
						int index13_370 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_370=='n') ) {s = 404;}
						else if ( ((LA13_370 >= '0' && LA13_370 <= '9')||(LA13_370 >= 'A' && LA13_370 <= 'Z')||(LA13_370 >= 'a' && LA13_370 <= 'm')||(LA13_370 >= 'o' && LA13_370 <= 'z')) ) {s = 38;}
						else if ( ((LA13_370 >= '-' && LA13_370 <= '.')||LA13_370=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 405;
						 
						input.seek(index13_370);
						if ( s>=0 ) return s;
						break;

					case 214 : 
						int LA13_59 = input.LA(1);
						 
						int index13_59 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_59=='d') ) {s = 121;}
						else if ( ((LA13_59 >= '0' && LA13_59 <= '9')||(LA13_59 >= 'A' && LA13_59 <= 'Z')||(LA13_59 >= 'a' && LA13_59 <= 'c')||(LA13_59 >= 'e' && LA13_59 <= 'z')) ) {s = 38;}
						else if ( ((LA13_59 >= '-' && LA13_59 <= '.')||LA13_59=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 122;
						 
						input.seek(index13_59);
						if ( s>=0 ) return s;
						break;

					case 215 : 
						int LA13_121 = input.LA(1);
						 
						int index13_121 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_121=='i') ) {s = 191;}
						else if ( ((LA13_121 >= '0' && LA13_121 <= '9')||(LA13_121 >= 'A' && LA13_121 <= 'Z')||(LA13_121 >= 'a' && LA13_121 <= 'h')||(LA13_121 >= 'j' && LA13_121 <= 'z')) ) {s = 38;}
						else if ( ((LA13_121 >= '-' && LA13_121 <= '.')||LA13_121=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 192;
						 
						input.seek(index13_121);
						if ( s>=0 ) return s;
						break;

					case 216 : 
						int LA13_191 = input.LA(1);
						 
						int index13_191 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_191=='n') ) {s = 254;}
						else if ( ((LA13_191 >= '0' && LA13_191 <= '9')||(LA13_191 >= 'A' && LA13_191 <= 'Z')||(LA13_191 >= 'a' && LA13_191 <= 'm')||(LA13_191 >= 'o' && LA13_191 <= 'z')) ) {s = 38;}
						else if ( ((LA13_191 >= '-' && LA13_191 <= '.')||LA13_191=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 255;
						 
						input.seek(index13_191);
						if ( s>=0 ) return s;
						break;

					case 217 : 
						int LA13_254 = input.LA(1);
						 
						int index13_254 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_254=='g') ) {s = 305;}
						else if ( ((LA13_254 >= '0' && LA13_254 <= '9')||(LA13_254 >= 'A' && LA13_254 <= 'Z')||(LA13_254 >= 'a' && LA13_254 <= 'f')||(LA13_254 >= 'h' && LA13_254 <= 'z')) ) {s = 38;}
						else if ( ((LA13_254 >= '-' && LA13_254 <= '.')||LA13_254=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 306;
						 
						input.seek(index13_254);
						if ( s>=0 ) return s;
						break;

					case 218 : 
						int LA13_305 = input.LA(1);
						 
						int index13_305 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_305==' ') ) {s = 350;}
						else if ( ((LA13_305 >= '0' && LA13_305 <= '9')||(LA13_305 >= 'A' && LA13_305 <= 'Z')||(LA13_305 >= 'a' && LA13_305 <= 'z')) ) {s = 38;}
						else if ( ((LA13_305 >= '-' && LA13_305 <= '.')||LA13_305=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 351;
						 
						input.seek(index13_305);
						if ( s>=0 ) return s;
						break;

					case 219 : 
						int LA13_103 = input.LA(1);
						 
						int index13_103 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_103=='n') ) {s = 174;}
						else if ( ((LA13_103 >= '0' && LA13_103 <= '9')||(LA13_103 >= 'A' && LA13_103 <= 'Z')||(LA13_103 >= 'a' && LA13_103 <= 'm')||(LA13_103 >= 'o' && LA13_103 <= 'z')) ) {s = 38;}
						else if ( ((LA13_103 >= '-' && LA13_103 <= '.')||LA13_103=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 175;
						 
						input.seek(index13_103);
						if ( s>=0 ) return s;
						break;

					case 220 : 
						int LA13_174 = input.LA(1);
						 
						int index13_174 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_174=='d') ) {s = 240;}
						else if ( ((LA13_174 >= '0' && LA13_174 <= '9')||(LA13_174 >= 'A' && LA13_174 <= 'Z')||(LA13_174 >= 'a' && LA13_174 <= 'c')||(LA13_174 >= 'e' && LA13_174 <= 'z')) ) {s = 38;}
						else if ( ((LA13_174 >= '-' && LA13_174 <= '.')||LA13_174=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 241;
						 
						input.seek(index13_174);
						if ( s>=0 ) return s;
						break;

					case 221 : 
						int LA13_60 = input.LA(1);
						 
						int index13_60 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_60=='s') ) {s = 123;}
						else if ( ((LA13_60 >= '0' && LA13_60 <= '9')||(LA13_60 >= 'A' && LA13_60 <= 'Z')||(LA13_60 >= 'a' && LA13_60 <= 'r')||(LA13_60 >= 't' && LA13_60 <= 'z')) ) {s = 38;}
						else if ( ((LA13_60 >= '-' && LA13_60 <= '.')||LA13_60=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 124;
						 
						input.seek(index13_60);
						if ( s>=0 ) return s;
						break;

					case 222 : 
						int LA13_123 = input.LA(1);
						 
						int index13_123 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_123=='i') ) {s = 193;}
						else if ( ((LA13_123 >= '0' && LA13_123 <= '9')||(LA13_123 >= 'A' && LA13_123 <= 'Z')||(LA13_123 >= 'a' && LA13_123 <= 'h')||(LA13_123 >= 'j' && LA13_123 <= 'z')) ) {s = 38;}
						else if ( ((LA13_123 >= '-' && LA13_123 <= '.')||LA13_123=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 194;
						 
						input.seek(index13_123);
						if ( s>=0 ) return s;
						break;

					case 223 : 
						int LA13_240 = input.LA(1);
						 
						int index13_240 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_240=='s') ) {s = 294;}
						else if ( ((LA13_240 >= '0' && LA13_240 <= '9')||(LA13_240 >= 'A' && LA13_240 <= 'Z')||(LA13_240 >= 'a' && LA13_240 <= 'r')||(LA13_240 >= 't' && LA13_240 <= 'z')) ) {s = 38;}
						else if ( ((LA13_240 >= '-' && LA13_240 <= '.')||LA13_240=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 295;
						 
						input.seek(index13_240);
						if ( s>=0 ) return s;
						break;

					case 224 : 
						int LA13_193 = input.LA(1);
						 
						int index13_193 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_193=='l') ) {s = 256;}
						else if ( ((LA13_193 >= '0' && LA13_193 <= '9')||(LA13_193 >= 'A' && LA13_193 <= 'Z')||(LA13_193 >= 'a' && LA13_193 <= 'k')||(LA13_193 >= 'm' && LA13_193 <= 'z')) ) {s = 38;}
						else if ( ((LA13_193 >= '-' && LA13_193 <= '.')||LA13_193=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 257;
						 
						input.seek(index13_193);
						if ( s>=0 ) return s;
						break;

					case 225 : 
						int LA13_256 = input.LA(1);
						 
						int index13_256 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_256=='o') ) {s = 307;}
						else if ( ((LA13_256 >= '0' && LA13_256 <= '9')||(LA13_256 >= 'A' && LA13_256 <= 'Z')||(LA13_256 >= 'a' && LA13_256 <= 'n')||(LA13_256 >= 'p' && LA13_256 <= 'z')) ) {s = 38;}
						else if ( ((LA13_256 >= '-' && LA13_256 <= '.')||LA13_256=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 308;
						 
						input.seek(index13_256);
						if ( s>=0 ) return s;
						break;

					case 226 : 
						int LA13_307 = input.LA(1);
						 
						int index13_307 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_307=='n') ) {s = 352;}
						else if ( ((LA13_307 >= '0' && LA13_307 <= '9')||(LA13_307 >= 'A' && LA13_307 <= 'Z')||(LA13_307 >= 'a' && LA13_307 <= 'm')||(LA13_307 >= 'o' && LA13_307 <= 'z')) ) {s = 38;}
						else if ( ((LA13_307 >= '-' && LA13_307 <= '.')||LA13_307=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 353;
						 
						input.seek(index13_307);
						if ( s>=0 ) return s;
						break;

					case 227 : 
						int LA13_294 = input.LA(1);
						 
						int index13_294 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_294==' ') ) {s = 340;}
						else if ( ((LA13_294 >= '0' && LA13_294 <= '9')||(LA13_294 >= 'A' && LA13_294 <= 'Z')||(LA13_294 >= 'a' && LA13_294 <= 'z')) ) {s = 38;}
						else if ( ((LA13_294 >= '-' && LA13_294 <= '.')||LA13_294=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 341;
						 
						input.seek(index13_294);
						if ( s>=0 ) return s;
						break;

					case 228 : 
						int LA13_14 = input.LA(1);
						 
						int index13_14 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_14=='r') ) {s = 50;}
						else if ( (LA13_14=='o') ) {s = 51;}
						else if ( (LA13_14=='y') ) {s = 52;}
						else if ( ((LA13_14 >= '0' && LA13_14 <= '9')||(LA13_14 >= 'A' && LA13_14 <= 'Z')||(LA13_14 >= 'a' && LA13_14 <= 'n')||(LA13_14 >= 'p' && LA13_14 <= 'q')||(LA13_14 >= 's' && LA13_14 <= 'x')||LA13_14=='z') ) {s = 38;}
						else if ( ((LA13_14 >= '-' && LA13_14 <= '.')||LA13_14=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_14);
						if ( s>=0 ) return s;
						break;

					case 229 : 
						int LA13_262 = input.LA(1);
						 
						int index13_262 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_262 >= '0' && LA13_262 <= '9')||(LA13_262 >= 'A' && LA13_262 <= 'Z')||(LA13_262 >= 'a' && LA13_262 <= 'z')) ) {s = 38;}
						else if ( ((LA13_262 >= '-' && LA13_262 <= '.')||LA13_262=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 313;
						 
						input.seek(index13_262);
						if ( s>=0 ) return s;
						break;

					case 230 : 
						int LA13_62 = input.LA(1);
						 
						int index13_62 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_62=='r') ) {s = 127;}
						else if ( ((LA13_62 >= '0' && LA13_62 <= '9')||(LA13_62 >= 'A' && LA13_62 <= 'Z')||(LA13_62 >= 'a' && LA13_62 <= 'q')||(LA13_62 >= 's' && LA13_62 <= 'z')) ) {s = 38;}
						else if ( ((LA13_62 >= '-' && LA13_62 <= '.')||LA13_62=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 128;
						 
						input.seek(index13_62);
						if ( s>=0 ) return s;
						break;

					case 231 : 
						int LA13_64 = input.LA(1);
						 
						int index13_64 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_64=='e') ) {s = 131;}
						else if ( ((LA13_64 >= '0' && LA13_64 <= '9')||(LA13_64 >= 'A' && LA13_64 <= 'Z')||(LA13_64 >= 'a' && LA13_64 <= 'd')||(LA13_64 >= 'f' && LA13_64 <= 'z')) ) {s = 38;}
						else if ( ((LA13_64 >= '-' && LA13_64 <= '.')||LA13_64=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 132;
						 
						input.seek(index13_64);
						if ( s>=0 ) return s;
						break;

					case 232 : 
						int LA13_131 = input.LA(1);
						 
						int index13_131 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_131=='e') ) {s = 200;}
						else if ( ((LA13_131 >= '0' && LA13_131 <= '9')||(LA13_131 >= 'A' && LA13_131 <= 'Z')||(LA13_131 >= 'a' && LA13_131 <= 'd')||(LA13_131 >= 'f' && LA13_131 <= 'z')) ) {s = 38;}
						else if ( ((LA13_131 >= '-' && LA13_131 <= '.')||LA13_131=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 201;
						 
						input.seek(index13_131);
						if ( s>=0 ) return s;
						break;

					case 233 : 
						int LA13_200 = input.LA(1);
						 
						int index13_200 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_200=='d') ) {s = 262;}
						else if ( ((LA13_200 >= '0' && LA13_200 <= '9')||(LA13_200 >= 'A' && LA13_200 <= 'Z')||(LA13_200 >= 'a' && LA13_200 <= 'c')||(LA13_200 >= 'e' && LA13_200 <= 'z')) ) {s = 38;}
						else if ( ((LA13_200 >= '-' && LA13_200 <= '.')||LA13_200=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 263;
						 
						input.seek(index13_200);
						if ( s>=0 ) return s;
						break;

					case 234 : 
						int LA13_22 = input.LA(1);
						 
						int index13_22 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_22=='s') ) {s = 65;}
						else if ( ((LA13_22 >= '0' && LA13_22 <= '9')||(LA13_22 >= 'A' && LA13_22 <= 'Z')||(LA13_22 >= 'a' && LA13_22 <= 'r')||(LA13_22 >= 't' && LA13_22 <= 'z')) ) {s = 38;}
						else if ( ((LA13_22 >= '-' && LA13_22 <= '.')||LA13_22=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_22);
						if ( s>=0 ) return s;
						break;

					case 235 : 
						int LA13_170 = input.LA(1);
						 
						int index13_170 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_170 >= '0' && LA13_170 <= '9')||(LA13_170 >= 'A' && LA13_170 <= 'Z')||(LA13_170 >= 'a' && LA13_170 <= 'z')) ) {s = 38;}
						else if ( ((LA13_170 >= '-' && LA13_170 <= '.')||LA13_170=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 237;
						 
						input.seek(index13_170);
						if ( s>=0 ) return s;
						break;

					case 236 : 
						int LA13_252 = input.LA(1);
						 
						int index13_252 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_252 >= '0' && LA13_252 <= '9')||(LA13_252 >= 'A' && LA13_252 <= 'Z')||(LA13_252 >= 'a' && LA13_252 <= 'z')) ) {s = 38;}
						else if ( ((LA13_252 >= '-' && LA13_252 <= '.')||LA13_252=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 304;
						 
						input.seek(index13_252);
						if ( s>=0 ) return s;
						break;

					case 237 : 
						int LA13_215 = input.LA(1);
						 
						int index13_215 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_215 >= '0' && LA13_215 <= '9')||(LA13_215 >= 'A' && LA13_215 <= 'Z')||(LA13_215 >= 'a' && LA13_215 <= 'z')) ) {s = 38;}
						else if ( ((LA13_215 >= '-' && LA13_215 <= '.')||LA13_215=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 277;
						 
						input.seek(index13_215);
						if ( s>=0 ) return s;
						break;

					case 238 : 
						int LA13_88 = input.LA(1);
						 
						int index13_88 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_88 >= '0' && LA13_88 <= '9')||(LA13_88 >= 'A' && LA13_88 <= 'Z')||(LA13_88 >= 'a' && LA13_88 <= 'z')) ) {s = 38;}
						else if ( ((LA13_88 >= '-' && LA13_88 <= '.')||LA13_88=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 160;
						 
						input.seek(index13_88);
						if ( s>=0 ) return s;
						break;

					case 239 : 
						int LA13_91 = input.LA(1);
						 
						int index13_91 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_91=='e') ) {s = 163;}
						else if ( ((LA13_91 >= '0' && LA13_91 <= '9')||(LA13_91 >= 'A' && LA13_91 <= 'Z')||(LA13_91 >= 'a' && LA13_91 <= 'd')||(LA13_91 >= 'f' && LA13_91 <= 'z')) ) {s = 38;}
						else if ( ((LA13_91 >= '-' && LA13_91 <= '.')||LA13_91=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 164;
						 
						input.seek(index13_91);
						if ( s>=0 ) return s;
						break;

					case 240 : 
						int LA13_23 = input.LA(1);
						 
						int index13_23 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_23=='e') ) {s = 66;}
						else if ( ((LA13_23 >= '0' && LA13_23 <= '9')||(LA13_23 >= 'A' && LA13_23 <= 'Z')||(LA13_23 >= 'a' && LA13_23 <= 'd')||(LA13_23 >= 'f' && LA13_23 <= 'z')) ) {s = 38;}
						else if ( ((LA13_23 >= '-' && LA13_23 <= '.')||LA13_23=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_23);
						if ( s>=0 ) return s;
						break;

					case 241 : 
						int LA13_163 = input.LA(1);
						 
						int index13_163 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_163=='s') ) {s = 231;}
						else if ( ((LA13_163 >= '0' && LA13_163 <= '9')||(LA13_163 >= 'A' && LA13_163 <= 'Z')||(LA13_163 >= 'a' && LA13_163 <= 'r')||(LA13_163 >= 't' && LA13_163 <= 'z')) ) {s = 38;}
						else if ( ((LA13_163 >= '-' && LA13_163 <= '.')||LA13_163=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 232;
						 
						input.seek(index13_163);
						if ( s>=0 ) return s;
						break;

					case 242 : 
						int LA13_327 = input.LA(1);
						 
						int index13_327 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_327 >= '0' && LA13_327 <= '9')||(LA13_327 >= 'A' && LA13_327 <= 'Z')||(LA13_327 >= 'a' && LA13_327 <= 'z')) ) {s = 38;}
						else if ( ((LA13_327 >= '-' && LA13_327 <= '.')||LA13_327=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 367;
						 
						input.seek(index13_327);
						if ( s>=0 ) return s;
						break;

					case 243 : 
						int LA13_231 = input.LA(1);
						 
						int index13_231 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_231==' ') ) {s = 288;}
						else if ( ((LA13_231 >= '0' && LA13_231 <= '9')||(LA13_231 >= 'A' && LA13_231 <= 'Z')||(LA13_231 >= 'a' && LA13_231 <= 'z')) ) {s = 38;}
						else if ( ((LA13_231 >= '-' && LA13_231 <= '.')||LA13_231=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 289;
						 
						input.seek(index13_231);
						if ( s>=0 ) return s;
						break;

					case 244 : 
						int LA13_50 = input.LA(1);
						 
						int index13_50 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_50=='u') ) {s = 102;}
						else if ( (LA13_50=='e') ) {s = 103;}
						else if ( ((LA13_50 >= '0' && LA13_50 <= '9')||(LA13_50 >= 'A' && LA13_50 <= 'Z')||(LA13_50 >= 'a' && LA13_50 <= 'd')||(LA13_50 >= 'f' && LA13_50 <= 't')||(LA13_50 >= 'v' && LA13_50 <= 'z')) ) {s = 38;}
						else if ( ((LA13_50 >= '-' && LA13_50 <= '.')||LA13_50=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 104;
						 
						input.seek(index13_50);
						if ( s>=0 ) return s;
						break;

					case 245 : 
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
						else if ( (LA13_0=='w') ) {s = 15;}
						else if ( (LA13_0=='N') ) {s = 16;}
						else if ( (LA13_0=='b') ) {s = 17;}
						else if ( (LA13_0=='c') ) {s = 18;}
						else if ( (LA13_0=='e') ) {s = 19;}
						else if ( (LA13_0=='f') ) {s = 20;}
						else if ( (LA13_0=='g') ) {s = 21;}
						else if ( (LA13_0=='i') ) {s = 22;}
						else if ( (LA13_0=='r') ) {s = 23;}
						else if ( (LA13_0=='s') ) {s = 24;}
						else if ( (LA13_0=='h') ) {s = 25;}
						else if ( (LA13_0=='-') ) {s = 26;}
						else if ( ((LA13_0 >= '0' && LA13_0 <= '9')) ) {s = 27;}
						else if ( (LA13_0=='\"') ) {s = 28;}
						else if ( (LA13_0=='v') ) {s = 29;}
						else if ( ((LA13_0 >= 'A' && LA13_0 <= 'M')||(LA13_0 >= 'O' && LA13_0 <= 'Z')||(LA13_0 >= 'j' && LA13_0 <= 'k')||(LA13_0 >= 'p' && LA13_0 <= 'q')||LA13_0=='u'||(LA13_0 >= 'x' && LA13_0 <= 'z')) ) {s = 30;}
						else if ( (LA13_0==' ') ) {s = 31;}
						else if ( (LA13_0=='_') && ((runtimeOpAhead()))) {s = 32;}
						else if ( ((LA13_0 >= '\t' && LA13_0 <= '\n')||(LA13_0 >= '\f' && LA13_0 <= '\r')) ) {s = 33;}
						else if ( (LA13_0=='/') ) {s = 34;}
						 
						input.seek(index13_0);
						if ( s>=0 ) return s;
						break;

					case 246 : 
						int LA13_30 = input.LA(1);
						 
						int index13_30 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_30 >= '0' && LA13_30 <= '9')||(LA13_30 >= 'A' && LA13_30 <= 'Z')||(LA13_30 >= 'a' && LA13_30 <= 'z')) ) {s = 38;}
						else if ( ((LA13_30 >= '-' && LA13_30 <= '.')||LA13_30=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_30);
						if ( s>=0 ) return s;
						break;

					case 247 : 
						int LA13_24 = input.LA(1);
						 
						int index13_24 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_24=='l') ) {s = 67;}
						else if ( (LA13_24=='m') ) {s = 68;}
						else if ( (LA13_24=='p') ) {s = 69;}
						else if ( (LA13_24=='t') ) {s = 70;}
						else if ( ((LA13_24 >= '0' && LA13_24 <= '9')||(LA13_24 >= 'A' && LA13_24 <= 'Z')||(LA13_24 >= 'a' && LA13_24 <= 'k')||(LA13_24 >= 'n' && LA13_24 <= 'o')||(LA13_24 >= 'q' && LA13_24 <= 's')||(LA13_24 >= 'u' && LA13_24 <= 'z')) ) {s = 38;}
						else if ( ((LA13_24 >= '-' && LA13_24 <= '.')||LA13_24=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_24);
						if ( s>=0 ) return s;
						break;

					case 248 : 
						int LA13_48 = input.LA(1);
						 
						int index13_48 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_48=='e') ) {s = 98;}
						else if ( ((LA13_48 >= '0' && LA13_48 <= '9')||(LA13_48 >= 'A' && LA13_48 <= 'Z')||(LA13_48 >= 'a' && LA13_48 <= 'd')||(LA13_48 >= 'f' && LA13_48 <= 'z')) ) {s = 38;}
						else if ( ((LA13_48 >= '-' && LA13_48 <= '.')||LA13_48=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 99;
						 
						input.seek(index13_48);
						if ( s>=0 ) return s;
						break;

					case 249 : 
						int LA13_98 = input.LA(1);
						 
						int index13_98 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_98=='r') ) {s = 168;}
						else if ( ((LA13_98 >= '0' && LA13_98 <= '9')||(LA13_98 >= 'A' && LA13_98 <= 'Z')||(LA13_98 >= 'a' && LA13_98 <= 'q')||(LA13_98 >= 's' && LA13_98 <= 'z')) ) {s = 38;}
						else if ( ((LA13_98 >= '-' && LA13_98 <= '.')||LA13_98=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 169;
						 
						input.seek(index13_98);
						if ( s>=0 ) return s;
						break;

					case 250 : 
						int LA13_11 = input.LA(1);
						 
						int index13_11 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_11=='r') ) {s = 47;}
						else if ( (LA13_11=='v') ) {s = 48;}
						else if ( (LA13_11=='p') ) {s = 49;}
						else if ( ((LA13_11 >= '0' && LA13_11 <= '9')||(LA13_11 >= 'A' && LA13_11 <= 'Z')||(LA13_11 >= 'a' && LA13_11 <= 'o')||LA13_11=='q'||(LA13_11 >= 's' && LA13_11 <= 'u')||(LA13_11 >= 'w' && LA13_11 <= 'z')) ) {s = 38;}
						else if ( ((LA13_11 >= '-' && LA13_11 <= '.')||LA13_11=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_11);
						if ( s>=0 ) return s;
						break;

					case 251 : 
						int LA13_44 = input.LA(1);
						 
						int index13_44 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_44=='t') ) {s = 90;}
						else if ( (LA13_44=='k') ) {s = 91;}
						else if ( ((LA13_44 >= '0' && LA13_44 <= '9')||(LA13_44 >= 'A' && LA13_44 <= 'Z')||(LA13_44 >= 'a' && LA13_44 <= 'j')||(LA13_44 >= 'l' && LA13_44 <= 's')||(LA13_44 >= 'u' && LA13_44 <= 'z')) ) {s = 38;}
						else if ( ((LA13_44 >= '-' && LA13_44 <= '.')||LA13_44=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 92;
						 
						input.seek(index13_44);
						if ( s>=0 ) return s;
						break;

					case 252 : 
						int LA13_168 = input.LA(1);
						 
						int index13_168 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_168=='r') ) {s = 235;}
						else if ( ((LA13_168 >= '0' && LA13_168 <= '9')||(LA13_168 >= 'A' && LA13_168 <= 'Z')||(LA13_168 >= 'a' && LA13_168 <= 'q')||(LA13_168 >= 's' && LA13_168 <= 'z')) ) {s = 38;}
						else if ( ((LA13_168 >= '-' && LA13_168 <= '.')||LA13_168=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 236;
						 
						input.seek(index13_168);
						if ( s>=0 ) return s;
						break;

					case 253 : 
						int LA13_19 = input.LA(1);
						 
						int index13_19 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_19=='n') ) {s = 59;}
						else if ( (LA13_19=='p') ) {s = 60;}
						else if ( (LA13_19=='q') ) {s = 61;}
						else if ( ((LA13_19 >= '0' && LA13_19 <= '9')||(LA13_19 >= 'A' && LA13_19 <= 'Z')||(LA13_19 >= 'a' && LA13_19 <= 'm')||LA13_19=='o'||(LA13_19 >= 'r' && LA13_19 <= 'z')) ) {s = 38;}
						else if ( ((LA13_19 >= '-' && LA13_19 <= '.')||LA13_19=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_19);
						if ( s>=0 ) return s;
						break;

					case 254 : 
						int LA13_75 = input.LA(1);
						 
						int index13_75 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_75 >= '0' && LA13_75 <= '9')||(LA13_75 >= 'A' && LA13_75 <= 'Z')||(LA13_75 >= 'a' && LA13_75 <= 'z')) ) {s = 38;}
						else if ( ((LA13_75 >= '-' && LA13_75 <= '.')||LA13_75=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 149;
						 
						input.seek(index13_75);
						if ( s>=0 ) return s;
						break;

					case 255 : 
						int LA13_160 = input.LA(1);
						 
						int index13_160 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 228;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_160);
						if ( s>=0 ) return s;
						break;

					case 256 : 
						int LA13_235 = input.LA(1);
						 
						int index13_235 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_235=='i') ) {s = 290;}
						else if ( ((LA13_235 >= '0' && LA13_235 <= '9')||(LA13_235 >= 'A' && LA13_235 <= 'Z')||(LA13_235 >= 'a' && LA13_235 <= 'h')||(LA13_235 >= 'j' && LA13_235 <= 'z')) ) {s = 38;}
						else if ( ((LA13_235 >= '-' && LA13_235 <= '.')||LA13_235=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 291;
						 
						input.seek(index13_235);
						if ( s>=0 ) return s;
						break;

					case 257 : 
						int LA13_237 = input.LA(1);
						 
						int index13_237 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 228;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_237);
						if ( s>=0 ) return s;
						break;

					case 258 : 
						int LA13_277 = input.LA(1);
						 
						int index13_277 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 228;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_277);
						if ( s>=0 ) return s;
						break;

					case 259 : 
						int LA13_304 = input.LA(1);
						 
						int index13_304 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 228;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_304);
						if ( s>=0 ) return s;
						break;

					case 260 : 
						int LA13_367 = input.LA(1);
						 
						int index13_367 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 32;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 228;}
						else if ( (true) ) {s = 39;}
						 
						input.seek(index13_367);
						if ( s>=0 ) return s;
						break;

					case 261 : 
						int LA13_290 = input.LA(1);
						 
						int index13_290 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_290=='d') ) {s = 338;}
						else if ( ((LA13_290 >= '0' && LA13_290 <= '9')||(LA13_290 >= 'A' && LA13_290 <= 'Z')||(LA13_290 >= 'a' && LA13_290 <= 'c')||(LA13_290 >= 'e' && LA13_290 <= 'z')) ) {s = 38;}
						else if ( ((LA13_290 >= '-' && LA13_290 <= '.')||LA13_290=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 339;
						 
						input.seek(index13_290);
						if ( s>=0 ) return s;
						break;

					case 262 : 
						int LA13_338 = input.LA(1);
						 
						int index13_338 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_338=='e') ) {s = 376;}
						else if ( ((LA13_338 >= '0' && LA13_338 <= '9')||(LA13_338 >= 'A' && LA13_338 <= 'Z')||(LA13_338 >= 'a' && LA13_338 <= 'd')||(LA13_338 >= 'f' && LA13_338 <= 'z')) ) {s = 38;}
						else if ( ((LA13_338 >= '-' && LA13_338 <= '.')||LA13_338=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 377;
						 
						input.seek(index13_338);
						if ( s>=0 ) return s;
						break;

					case 263 : 
						int LA13_376 = input.LA(1);
						 
						int index13_376 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_376==' ') ) {s = 410;}
						else if ( ((LA13_376 >= '0' && LA13_376 <= '9')||(LA13_376 >= 'A' && LA13_376 <= 'Z')||(LA13_376 >= 'a' && LA13_376 <= 'z')) ) {s = 38;}
						else if ( ((LA13_376 >= '-' && LA13_376 <= '.')||LA13_376=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 411;
						 
						input.seek(index13_376);
						if ( s>=0 ) return s;
						break;

					case 264 : 
						int LA13_25 = input.LA(1);
						 
						int index13_25 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_25=='i') ) {s = 71;}
						else if ( ((LA13_25 >= '0' && LA13_25 <= '9')||(LA13_25 >= 'A' && LA13_25 <= 'Z')||(LA13_25 >= 'a' && LA13_25 <= 'h')||(LA13_25 >= 'j' && LA13_25 <= 'z')) ) {s = 38;}
						else if ( ((LA13_25 >= '-' && LA13_25 <= '.')||LA13_25=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_25);
						if ( s>=0 ) return s;
						break;

					case 265 : 
						int LA13_8 = input.LA(1);
						 
						int index13_8 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_8=='o') ) {s = 46;}
						else if ( ((LA13_8 >= '0' && LA13_8 <= '9')||(LA13_8 >= 'A' && LA13_8 <= 'Z')||(LA13_8 >= 'a' && LA13_8 <= 'n')||(LA13_8 >= 'p' && LA13_8 <= 'z')) ) {s = 38;}
						else if ( ((LA13_8 >= '-' && LA13_8 <= '.')||LA13_8=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_8);
						if ( s>=0 ) return s;
						break;

					case 266 : 
						int LA13_65 = input.LA(1);
						 
						int index13_65 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_65==' ') ) {s = 133;}
						else if ( ((LA13_65 >= '0' && LA13_65 <= '9')||(LA13_65 >= 'A' && LA13_65 <= 'Z')||(LA13_65 >= 'a' && LA13_65 <= 'z')) ) {s = 38;}
						else if ( ((LA13_65 >= '-' && LA13_65 <= '.')||LA13_65=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 134;
						 
						input.seek(index13_65);
						if ( s>=0 ) return s;
						break;

					case 267 : 
						int LA13_29 = input.LA(1);
						 
						int index13_29 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_29=='o') ) {s = 72;}
						else if ( ((LA13_29 >= '0' && LA13_29 <= '9')||(LA13_29 >= 'A' && LA13_29 <= 'Z')||(LA13_29 >= 'a' && LA13_29 <= 'n')||(LA13_29 >= 'p' && LA13_29 <= 'z')) ) {s = 38;}
						else if ( ((LA13_29 >= '-' && LA13_29 <= '.')||LA13_29=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_29);
						if ( s>=0 ) return s;
						break;

					case 268 : 
						int LA13_45 = input.LA(1);
						 
						int index13_45 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_45=='r') ) {s = 93;}
						else if ( ((LA13_45 >= '0' && LA13_45 <= '9')||(LA13_45 >= 'A' && LA13_45 <= 'Z')||(LA13_45 >= 'a' && LA13_45 <= 'q')||(LA13_45 >= 's' && LA13_45 <= 'z')) ) {s = 38;}
						else if ( ((LA13_45 >= '-' && LA13_45 <= '.')||LA13_45=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 94;
						 
						input.seek(index13_45);
						if ( s>=0 ) return s;
						break;

					case 269 : 
						int LA13_154 = input.LA(1);
						 
						int index13_154 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_154 >= '0' && LA13_154 <= '9')||(LA13_154 >= 'A' && LA13_154 <= 'Z')||(LA13_154 >= 'a' && LA13_154 <= 'z')) ) {s = 38;}
						else if ( ((LA13_154 >= '-' && LA13_154 <= '.')||LA13_154=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 223;
						 
						input.seek(index13_154);
						if ( s>=0 ) return s;
						break;

					case 270 : 
						int LA13_93 = input.LA(1);
						 
						int index13_93 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_93=='e') ) {s = 165;}
						else if ( ((LA13_93 >= '0' && LA13_93 <= '9')||(LA13_93 >= 'A' && LA13_93 <= 'Z')||(LA13_93 >= 'a' && LA13_93 <= 'd')||(LA13_93 >= 'f' && LA13_93 <= 'z')) ) {s = 38;}
						else if ( ((LA13_93 >= '-' && LA13_93 <= '.')||LA13_93=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 166;
						 
						input.seek(index13_93);
						if ( s>=0 ) return s;
						break;

					case 271 : 
						int LA13_165 = input.LA(1);
						 
						int index13_165 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_165==' ') ) {s = 233;}
						else if ( ((LA13_165 >= '0' && LA13_165 <= '9')||(LA13_165 >= 'A' && LA13_165 <= 'Z')||(LA13_165 >= 'a' && LA13_165 <= 'z')) ) {s = 38;}
						else if ( ((LA13_165 >= '-' && LA13_165 <= '.')||LA13_165=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 234;
						 
						input.seek(index13_165);
						if ( s>=0 ) return s;
						break;

					case 272 : 
						int LA13_333 = input.LA(1);
						 
						int index13_333 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_333 >= '0' && LA13_333 <= '9')||(LA13_333 >= 'A' && LA13_333 <= 'Z')||(LA13_333 >= 'a' && LA13_333 <= 'z')) ) {s = 38;}
						else if ( ((LA13_333 >= '-' && LA13_333 <= '.')||LA13_333=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 372;
						 
						input.seek(index13_333);
						if ( s>=0 ) return s;
						break;

					case 273 : 
						int LA13_15 = input.LA(1);
						 
						int index13_15 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_15=='i') ) {s = 53;}
						else if ( ((LA13_15 >= '0' && LA13_15 <= '9')||(LA13_15 >= 'A' && LA13_15 <= 'Z')||(LA13_15 >= 'a' && LA13_15 <= 'h')||(LA13_15 >= 'j' && LA13_15 <= 'z')) ) {s = 38;}
						else if ( ((LA13_15 >= '-' && LA13_15 <= '.')||LA13_15=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_15);
						if ( s>=0 ) return s;
						break;

					case 274 : 
						int LA13_16 = input.LA(1);
						 
						int index13_16 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_16=='a') ) {s = 54;}
						else if ( ((LA13_16 >= '0' && LA13_16 <= '9')||(LA13_16 >= 'A' && LA13_16 <= 'Z')||(LA13_16 >= 'b' && LA13_16 <= 'z')) ) {s = 38;}
						else if ( ((LA13_16 >= '-' && LA13_16 <= '.')||LA13_16=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_16);
						if ( s>=0 ) return s;
						break;

					case 275 : 
						int LA13_373 = input.LA(1);
						 
						int index13_373 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_373 >= '0' && LA13_373 <= '9')||(LA13_373 >= 'A' && LA13_373 <= 'Z')||(LA13_373 >= 'a' && LA13_373 <= 'z')) ) {s = 38;}
						else if ( ((LA13_373 >= '-' && LA13_373 <= '.')||LA13_373=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 406;
						 
						input.seek(index13_373);
						if ( s>=0 ) return s;
						break;

					case 276 : 
						int LA13_95 = input.LA(1);
						 
						int index13_95 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_95 >= '0' && LA13_95 <= '9')||(LA13_95 >= 'A' && LA13_95 <= 'Z')||(LA13_95 >= 'a' && LA13_95 <= 'z')) ) {s = 38;}
						else if ( ((LA13_95 >= '-' && LA13_95 <= '.')||LA13_95=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 167;
						 
						input.seek(index13_95);
						if ( s>=0 ) return s;
						break;

					case 277 : 
						int LA13_66 = input.LA(1);
						 
						int index13_66 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_66=='v') ) {s = 135;}
						else if ( ((LA13_66 >= '0' && LA13_66 <= '9')||(LA13_66 >= 'A' && LA13_66 <= 'Z')||(LA13_66 >= 'a' && LA13_66 <= 'u')||(LA13_66 >= 'w' && LA13_66 <= 'z')) ) {s = 38;}
						else if ( ((LA13_66 >= '-' && LA13_66 <= '.')||LA13_66=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 136;
						 
						input.seek(index13_66);
						if ( s>=0 ) return s;
						break;

					case 278 : 
						int LA13_135 = input.LA(1);
						 
						int index13_135 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_135=='e') ) {s = 205;}
						else if ( ((LA13_135 >= '0' && LA13_135 <= '9')||(LA13_135 >= 'A' && LA13_135 <= 'Z')||(LA13_135 >= 'a' && LA13_135 <= 'd')||(LA13_135 >= 'f' && LA13_135 <= 'z')) ) {s = 38;}
						else if ( ((LA13_135 >= '-' && LA13_135 <= '.')||LA13_135=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 206;
						 
						input.seek(index13_135);
						if ( s>=0 ) return s;
						break;

					case 279 : 
						int LA13_205 = input.LA(1);
						 
						int index13_205 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_205=='r') ) {s = 267;}
						else if ( ((LA13_205 >= '0' && LA13_205 <= '9')||(LA13_205 >= 'A' && LA13_205 <= 'Z')||(LA13_205 >= 'a' && LA13_205 <= 'q')||(LA13_205 >= 's' && LA13_205 <= 'z')) ) {s = 38;}
						else if ( ((LA13_205 >= '-' && LA13_205 <= '.')||LA13_205=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 268;
						 
						input.seek(index13_205);
						if ( s>=0 ) return s;
						break;

					case 280 : 
						int LA13_267 = input.LA(1);
						 
						int index13_267 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_267=='s') ) {s = 317;}
						else if ( ((LA13_267 >= '0' && LA13_267 <= '9')||(LA13_267 >= 'A' && LA13_267 <= 'Z')||(LA13_267 >= 'a' && LA13_267 <= 'r')||(LA13_267 >= 't' && LA13_267 <= 'z')) ) {s = 38;}
						else if ( ((LA13_267 >= '-' && LA13_267 <= '.')||LA13_267=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 318;
						 
						input.seek(index13_267);
						if ( s>=0 ) return s;
						break;

					case 281 : 
						int LA13_317 = input.LA(1);
						 
						int index13_317 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_317=='e') ) {s = 359;}
						else if ( ((LA13_317 >= '0' && LA13_317 <= '9')||(LA13_317 >= 'A' && LA13_317 <= 'Z')||(LA13_317 >= 'a' && LA13_317 <= 'd')||(LA13_317 >= 'f' && LA13_317 <= 'z')) ) {s = 38;}
						else if ( ((LA13_317 >= '-' && LA13_317 <= '.')||LA13_317=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 360;
						 
						input.seek(index13_317);
						if ( s>=0 ) return s;
						break;

					case 282 : 
						int LA13_359 = input.LA(1);
						 
						int index13_359 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_359=='s') ) {s = 394;}
						else if ( ((LA13_359 >= '0' && LA13_359 <= '9')||(LA13_359 >= 'A' && LA13_359 <= 'Z')||(LA13_359 >= 'a' && LA13_359 <= 'r')||(LA13_359 >= 't' && LA13_359 <= 'z')) ) {s = 38;}
						else if ( ((LA13_359 >= '-' && LA13_359 <= '.')||LA13_359=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 395;
						 
						input.seek(index13_359);
						if ( s>=0 ) return s;
						break;

					case 283 : 
						int LA13_394 = input.LA(1);
						 
						int index13_394 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_394==' ') ) {s = 423;}
						else if ( ((LA13_394 >= '0' && LA13_394 <= '9')||(LA13_394 >= 'A' && LA13_394 <= 'Z')||(LA13_394 >= 'a' && LA13_394 <= 'z')) ) {s = 38;}
						else if ( ((LA13_394 >= '-' && LA13_394 <= '.')||LA13_394=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 424;
						 
						input.seek(index13_394);
						if ( s>=0 ) return s;
						break;

					case 284 : 
						int LA13_47 = input.LA(1);
						 
						int index13_47 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_47 >= '0' && LA13_47 <= '9')||(LA13_47 >= 'A' && LA13_47 <= 'Z')||(LA13_47 >= 'a' && LA13_47 <= 'z')) ) {s = 38;}
						else if ( ((LA13_47 >= '-' && LA13_47 <= '.')||LA13_47=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 97;
						 
						input.seek(index13_47);
						if ( s>=0 ) return s;
						break;

					case 285 : 
						int LA13_67 = input.LA(1);
						 
						int index13_67 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_67=='o') ) {s = 137;}
						else if ( ((LA13_67 >= '0' && LA13_67 <= '9')||(LA13_67 >= 'A' && LA13_67 <= 'Z')||(LA13_67 >= 'a' && LA13_67 <= 'n')||(LA13_67 >= 'p' && LA13_67 <= 'z')) ) {s = 38;}
						else if ( ((LA13_67 >= '-' && LA13_67 <= '.')||LA13_67=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 138;
						 
						input.seek(index13_67);
						if ( s>=0 ) return s;
						break;

					case 286 : 
						int LA13_137 = input.LA(1);
						 
						int index13_137 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_137=='p') ) {s = 207;}
						else if ( ((LA13_137 >= '0' && LA13_137 <= '9')||(LA13_137 >= 'A' && LA13_137 <= 'Z')||(LA13_137 >= 'a' && LA13_137 <= 'o')||(LA13_137 >= 'q' && LA13_137 <= 'z')) ) {s = 38;}
						else if ( ((LA13_137 >= '-' && LA13_137 <= '.')||LA13_137=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 208;
						 
						input.seek(index13_137);
						if ( s>=0 ) return s;
						break;

					case 287 : 
						int LA13_207 = input.LA(1);
						 
						int index13_207 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_207=='e') ) {s = 269;}
						else if ( ((LA13_207 >= '0' && LA13_207 <= '9')||(LA13_207 >= 'A' && LA13_207 <= 'Z')||(LA13_207 >= 'a' && LA13_207 <= 'd')||(LA13_207 >= 'f' && LA13_207 <= 'z')) ) {s = 38;}
						else if ( ((LA13_207 >= '-' && LA13_207 <= '.')||LA13_207=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 270;
						 
						input.seek(index13_207);
						if ( s>=0 ) return s;
						break;

					case 288 : 
						int LA13_63 = input.LA(1);
						 
						int index13_63 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_63=='e') ) {s = 129;}
						else if ( ((LA13_63 >= '0' && LA13_63 <= '9')||(LA13_63 >= 'A' && LA13_63 <= 'Z')||(LA13_63 >= 'a' && LA13_63 <= 'd')||(LA13_63 >= 'f' && LA13_63 <= 'z')) ) {s = 38;}
						else if ( ((LA13_63 >= '-' && LA13_63 <= '.')||LA13_63=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 130;
						 
						input.seek(index13_63);
						if ( s>=0 ) return s;
						break;

					case 289 : 
						int LA13_269 = input.LA(1);
						 
						int index13_269 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_269==' ') ) {s = 319;}
						else if ( ((LA13_269 >= '0' && LA13_269 <= '9')||(LA13_269 >= 'A' && LA13_269 <= 'Z')||(LA13_269 >= 'a' && LA13_269 <= 'z')) ) {s = 38;}
						else if ( ((LA13_269 >= '-' && LA13_269 <= '.')||LA13_269=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 320;
						 
						input.seek(index13_269);
						if ( s>=0 ) return s;
						break;

					case 290 : 
						int LA13_129 = input.LA(1);
						 
						int index13_129 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_129=='s') ) {s = 198;}
						else if ( ((LA13_129 >= '0' && LA13_129 <= '9')||(LA13_129 >= 'A' && LA13_129 <= 'Z')||(LA13_129 >= 'a' && LA13_129 <= 'r')||(LA13_129 >= 't' && LA13_129 <= 'z')) ) {s = 38;}
						else if ( ((LA13_129 >= '-' && LA13_129 <= '.')||LA13_129=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 199;
						 
						input.seek(index13_129);
						if ( s>=0 ) return s;
						break;

					case 291 : 
						int LA13_198 = input.LA(1);
						 
						int index13_198 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_198==' ') ) {s = 260;}
						else if ( ((LA13_198 >= '0' && LA13_198 <= '9')||(LA13_198 >= 'A' && LA13_198 <= 'Z')||(LA13_198 >= 'a' && LA13_198 <= 'z')) ) {s = 38;}
						else if ( ((LA13_198 >= '-' && LA13_198 <= '.')||LA13_198=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 261;
						 
						input.seek(index13_198);
						if ( s>=0 ) return s;
						break;

					case 292 : 
						int LA13_38 = input.LA(1);
						 
						int index13_38 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_38 >= '0' && LA13_38 <= '9')||(LA13_38 >= 'A' && LA13_38 <= 'Z')||(LA13_38 >= 'a' && LA13_38 <= 'z')) ) {s = 38;}
						else if ( ((LA13_38 >= '-' && LA13_38 <= '.')||LA13_38=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 81;
						 
						input.seek(index13_38);
						if ( s>=0 ) return s;
						break;

					case 293 : 
						int LA13_68 = input.LA(1);
						 
						int index13_68 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_68=='o') ) {s = 139;}
						else if ( ((LA13_68 >= '0' && LA13_68 <= '9')||(LA13_68 >= 'A' && LA13_68 <= 'Z')||(LA13_68 >= 'a' && LA13_68 <= 'n')||(LA13_68 >= 'p' && LA13_68 <= 'z')) ) {s = 38;}
						else if ( ((LA13_68 >= '-' && LA13_68 <= '.')||LA13_68=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 140;
						 
						input.seek(index13_68);
						if ( s>=0 ) return s;
						break;

					case 294 : 
						int LA13_139 = input.LA(1);
						 
						int index13_139 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_139=='o') ) {s = 209;}
						else if ( ((LA13_139 >= '0' && LA13_139 <= '9')||(LA13_139 >= 'A' && LA13_139 <= 'Z')||(LA13_139 >= 'a' && LA13_139 <= 'n')||(LA13_139 >= 'p' && LA13_139 <= 'z')) ) {s = 38;}
						else if ( ((LA13_139 >= '-' && LA13_139 <= '.')||LA13_139=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 210;
						 
						input.seek(index13_139);
						if ( s>=0 ) return s;
						break;

					case 295 : 
						int LA13_209 = input.LA(1);
						 
						int index13_209 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_209=='t') ) {s = 271;}
						else if ( ((LA13_209 >= '0' && LA13_209 <= '9')||(LA13_209 >= 'A' && LA13_209 <= 'Z')||(LA13_209 >= 'a' && LA13_209 <= 's')||(LA13_209 >= 'u' && LA13_209 <= 'z')) ) {s = 38;}
						else if ( ((LA13_209 >= '-' && LA13_209 <= '.')||LA13_209=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 272;
						 
						input.seek(index13_209);
						if ( s>=0 ) return s;
						break;

					case 296 : 
						int LA13_271 = input.LA(1);
						 
						int index13_271 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_271=='h') ) {s = 321;}
						else if ( ((LA13_271 >= '0' && LA13_271 <= '9')||(LA13_271 >= 'A' && LA13_271 <= 'Z')||(LA13_271 >= 'a' && LA13_271 <= 'g')||(LA13_271 >= 'i' && LA13_271 <= 'z')) ) {s = 38;}
						else if ( ((LA13_271 >= '-' && LA13_271 <= '.')||LA13_271=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 322;
						 
						input.seek(index13_271);
						if ( s>=0 ) return s;
						break;

					case 297 : 
						int LA13_321 = input.LA(1);
						 
						int index13_321 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_321=='e') ) {s = 361;}
						else if ( ((LA13_321 >= '0' && LA13_321 <= '9')||(LA13_321 >= 'A' && LA13_321 <= 'Z')||(LA13_321 >= 'a' && LA13_321 <= 'd')||(LA13_321 >= 'f' && LA13_321 <= 'z')) ) {s = 38;}
						else if ( ((LA13_321 >= '-' && LA13_321 <= '.')||LA13_321=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 362;
						 
						input.seek(index13_321);
						if ( s>=0 ) return s;
						break;

					case 298 : 
						int LA13_361 = input.LA(1);
						 
						int index13_361 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_361=='d') ) {s = 396;}
						else if ( ((LA13_361 >= '0' && LA13_361 <= '9')||(LA13_361 >= 'A' && LA13_361 <= 'Z')||(LA13_361 >= 'a' && LA13_361 <= 'c')||(LA13_361 >= 'e' && LA13_361 <= 'z')) ) {s = 38;}
						else if ( ((LA13_361 >= '-' && LA13_361 <= '.')||LA13_361=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 397;
						 
						input.seek(index13_361);
						if ( s>=0 ) return s;
						break;

					case 299 : 
						int LA13_69 = input.LA(1);
						 
						int index13_69 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_69=='a') ) {s = 141;}
						else if ( ((LA13_69 >= '0' && LA13_69 <= '9')||(LA13_69 >= 'A' && LA13_69 <= 'Z')||(LA13_69 >= 'b' && LA13_69 <= 'z')) ) {s = 38;}
						else if ( ((LA13_69 >= '-' && LA13_69 <= '.')||LA13_69=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 142;
						 
						input.seek(index13_69);
						if ( s>=0 ) return s;
						break;

					case 300 : 
						int LA13_141 = input.LA(1);
						 
						int index13_141 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_141=='n') ) {s = 211;}
						else if ( ((LA13_141 >= '0' && LA13_141 <= '9')||(LA13_141 >= 'A' && LA13_141 <= 'Z')||(LA13_141 >= 'a' && LA13_141 <= 'm')||(LA13_141 >= 'o' && LA13_141 <= 'z')) ) {s = 38;}
						else if ( ((LA13_141 >= '-' && LA13_141 <= '.')||LA13_141=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 212;
						 
						input.seek(index13_141);
						if ( s>=0 ) return s;
						break;

					case 301 : 
						int LA13_211 = input.LA(1);
						 
						int index13_211 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_211=='n') ) {s = 273;}
						else if ( ((LA13_211 >= '0' && LA13_211 <= '9')||(LA13_211 >= 'A' && LA13_211 <= 'Z')||(LA13_211 >= 'a' && LA13_211 <= 'm')||(LA13_211 >= 'o' && LA13_211 <= 'z')) ) {s = 38;}
						else if ( ((LA13_211 >= '-' && LA13_211 <= '.')||LA13_211=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 274;
						 
						input.seek(index13_211);
						if ( s>=0 ) return s;
						break;

					case 302 : 
						int LA13_273 = input.LA(1);
						 
						int index13_273 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_273=='i') ) {s = 323;}
						else if ( ((LA13_273 >= '0' && LA13_273 <= '9')||(LA13_273 >= 'A' && LA13_273 <= 'Z')||(LA13_273 >= 'a' && LA13_273 <= 'h')||(LA13_273 >= 'j' && LA13_273 <= 'z')) ) {s = 38;}
						else if ( ((LA13_273 >= '-' && LA13_273 <= '.')||LA13_273=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 324;
						 
						input.seek(index13_273);
						if ( s>=0 ) return s;
						break;

					case 303 : 
						int LA13_323 = input.LA(1);
						 
						int index13_323 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_323=='n') ) {s = 363;}
						else if ( ((LA13_323 >= '0' && LA13_323 <= '9')||(LA13_323 >= 'A' && LA13_323 <= 'Z')||(LA13_323 >= 'a' && LA13_323 <= 'm')||(LA13_323 >= 'o' && LA13_323 <= 'z')) ) {s = 38;}
						else if ( ((LA13_323 >= '-' && LA13_323 <= '.')||LA13_323=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 364;
						 
						input.seek(index13_323);
						if ( s>=0 ) return s;
						break;

					case 304 : 
						int LA13_363 = input.LA(1);
						 
						int index13_363 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_363=='g') ) {s = 398;}
						else if ( ((LA13_363 >= '0' && LA13_363 <= '9')||(LA13_363 >= 'A' && LA13_363 <= 'Z')||(LA13_363 >= 'a' && LA13_363 <= 'f')||(LA13_363 >= 'h' && LA13_363 <= 'z')) ) {s = 38;}
						else if ( ((LA13_363 >= '-' && LA13_363 <= '.')||LA13_363=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 399;
						 
						input.seek(index13_363);
						if ( s>=0 ) return s;
						break;

					case 305 : 
						int LA13_70 = input.LA(1);
						 
						int index13_70 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_70=='a') ) {s = 143;}
						else if ( ((LA13_70 >= '0' && LA13_70 <= '9')||(LA13_70 >= 'A' && LA13_70 <= 'Z')||(LA13_70 >= 'b' && LA13_70 <= 'z')) ) {s = 38;}
						else if ( ((LA13_70 >= '-' && LA13_70 <= '.')||LA13_70=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 144;
						 
						input.seek(index13_70);
						if ( s>=0 ) return s;
						break;

					case 306 : 
						int LA13_180 = input.LA(1);
						 
						int index13_180 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_180 >= '0' && LA13_180 <= '9')||(LA13_180 >= 'A' && LA13_180 <= 'Z')||(LA13_180 >= 'a' && LA13_180 <= 'z')) ) {s = 38;}
						else if ( ((LA13_180 >= '-' && LA13_180 <= '.')||LA13_180=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 245;
						 
						input.seek(index13_180);
						if ( s>=0 ) return s;
						break;

					case 307 : 
						int LA13_143 = input.LA(1);
						 
						int index13_143 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_143=='r') ) {s = 213;}
						else if ( ((LA13_143 >= '0' && LA13_143 <= '9')||(LA13_143 >= 'A' && LA13_143 <= 'Z')||(LA13_143 >= 'a' && LA13_143 <= 'q')||(LA13_143 >= 's' && LA13_143 <= 'z')) ) {s = 38;}
						else if ( ((LA13_143 >= '-' && LA13_143 <= '.')||LA13_143=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 214;
						 
						input.seek(index13_143);
						if ( s>=0 ) return s;
						break;

					case 308 : 
						int LA13_213 = input.LA(1);
						 
						int index13_213 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_213=='t') ) {s = 275;}
						else if ( ((LA13_213 >= '0' && LA13_213 <= '9')||(LA13_213 >= 'A' && LA13_213 <= 'Z')||(LA13_213 >= 'a' && LA13_213 <= 's')||(LA13_213 >= 'u' && LA13_213 <= 'z')) ) {s = 38;}
						else if ( ((LA13_213 >= '-' && LA13_213 <= '.')||LA13_213=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 276;
						 
						input.seek(index13_213);
						if ( s>=0 ) return s;
						break;

					case 309 : 
						int LA13_275 = input.LA(1);
						 
						int index13_275 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_275=='i') ) {s = 325;}
						else if ( ((LA13_275 >= '0' && LA13_275 <= '9')||(LA13_275 >= 'A' && LA13_275 <= 'Z')||(LA13_275 >= 'a' && LA13_275 <= 'h')||(LA13_275 >= 'j' && LA13_275 <= 'z')) ) {s = 38;}
						else if ( ((LA13_275 >= '-' && LA13_275 <= '.')||LA13_275=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 326;
						 
						input.seek(index13_275);
						if ( s>=0 ) return s;
						break;

					case 310 : 
						int LA13_325 = input.LA(1);
						 
						int index13_325 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_325=='n') ) {s = 365;}
						else if ( ((LA13_325 >= '0' && LA13_325 <= '9')||(LA13_325 >= 'A' && LA13_325 <= 'Z')||(LA13_325 >= 'a' && LA13_325 <= 'm')||(LA13_325 >= 'o' && LA13_325 <= 'z')) ) {s = 38;}
						else if ( ((LA13_325 >= '-' && LA13_325 <= '.')||LA13_325=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 366;
						 
						input.seek(index13_325);
						if ( s>=0 ) return s;
						break;

					case 311 : 
						int LA13_365 = input.LA(1);
						 
						int index13_365 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_365=='g') ) {s = 400;}
						else if ( ((LA13_365 >= '0' && LA13_365 <= '9')||(LA13_365 >= 'A' && LA13_365 <= 'Z')||(LA13_365 >= 'a' && LA13_365 <= 'f')||(LA13_365 >= 'h' && LA13_365 <= 'z')) ) {s = 38;}
						else if ( ((LA13_365 >= '-' && LA13_365 <= '.')||LA13_365=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 401;
						 
						input.seek(index13_365);
						if ( s>=0 ) return s;
						break;

					case 312 : 
						int LA13_400 = input.LA(1);
						 
						int index13_400 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_400==' ') ) {s = 427;}
						else if ( ((LA13_400 >= '0' && LA13_400 <= '9')||(LA13_400 >= 'A' && LA13_400 <= 'Z')||(LA13_400 >= 'a' && LA13_400 <= 'z')) ) {s = 38;}
						else if ( ((LA13_400 >= '-' && LA13_400 <= '.')||LA13_400=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 428;
						 
						input.seek(index13_400);
						if ( s>=0 ) return s;
						break;

					case 313 : 
						int LA13_111 = input.LA(1);
						 
						int index13_111 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_111 >= '0' && LA13_111 <= '9')||(LA13_111 >= 'A' && LA13_111 <= 'Z')||(LA13_111 >= 'a' && LA13_111 <= 'z')) ) {s = 38;}
						else if ( ((LA13_111 >= '-' && LA13_111 <= '.')||LA13_111=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 182;
						 
						input.seek(index13_111);
						if ( s>=0 ) return s;
						break;

					case 314 : 
						int LA13_6 = input.LA(1);
						 
						int index13_6 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_6=='e') ) {s = 42;}
						else if ( (LA13_6=='o') ) {s = 43;}
						else if ( ((LA13_6 >= '0' && LA13_6 <= '9')||(LA13_6 >= 'A' && LA13_6 <= 'Z')||(LA13_6 >= 'a' && LA13_6 <= 'd')||(LA13_6 >= 'f' && LA13_6 <= 'n')||(LA13_6 >= 'p' && LA13_6 <= 'z')) ) {s = 38;}
						else if ( ((LA13_6 >= '-' && LA13_6 <= '.')||LA13_6=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_6);
						if ( s>=0 ) return s;
						break;

					case 315 : 
						int LA13_51 = input.LA(1);
						 
						int index13_51 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_51=='l') ) {s = 105;}
						else if ( ((LA13_51 >= '0' && LA13_51 <= '9')||(LA13_51 >= 'A' && LA13_51 <= 'Z')||(LA13_51 >= 'a' && LA13_51 <= 'k')||(LA13_51 >= 'm' && LA13_51 <= 'z')) ) {s = 38;}
						else if ( ((LA13_51 >= '-' && LA13_51 <= '.')||LA13_51=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 106;
						 
						input.seek(index13_51);
						if ( s>=0 ) return s;
						break;

					case 316 : 
						int LA13_105 = input.LA(1);
						 
						int index13_105 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_105=='e') ) {s = 176;}
						else if ( ((LA13_105 >= '0' && LA13_105 <= '9')||(LA13_105 >= 'A' && LA13_105 <= 'Z')||(LA13_105 >= 'a' && LA13_105 <= 'd')||(LA13_105 >= 'f' && LA13_105 <= 'z')) ) {s = 38;}
						else if ( ((LA13_105 >= '-' && LA13_105 <= '.')||LA13_105=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 177;
						 
						input.seek(index13_105);
						if ( s>=0 ) return s;
						break;

					case 317 : 
						int LA13_176 = input.LA(1);
						 
						int index13_176 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_176=='r') ) {s = 242;}
						else if ( ((LA13_176 >= '0' && LA13_176 <= '9')||(LA13_176 >= 'A' && LA13_176 <= 'Z')||(LA13_176 >= 'a' && LA13_176 <= 'q')||(LA13_176 >= 's' && LA13_176 <= 'z')) ) {s = 38;}
						else if ( ((LA13_176 >= '-' && LA13_176 <= '.')||LA13_176=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 243;
						 
						input.seek(index13_176);
						if ( s>=0 ) return s;
						break;

					case 318 : 
						int LA13_242 = input.LA(1);
						 
						int index13_242 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_242=='a') ) {s = 296;}
						else if ( ((LA13_242 >= '0' && LA13_242 <= '9')||(LA13_242 >= 'A' && LA13_242 <= 'Z')||(LA13_242 >= 'b' && LA13_242 <= 'z')) ) {s = 38;}
						else if ( ((LA13_242 >= '-' && LA13_242 <= '.')||LA13_242=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 297;
						 
						input.seek(index13_242);
						if ( s>=0 ) return s;
						break;

					case 319 : 
						int LA13_296 = input.LA(1);
						 
						int index13_296 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_296=='n') ) {s = 342;}
						else if ( ((LA13_296 >= '0' && LA13_296 <= '9')||(LA13_296 >= 'A' && LA13_296 <= 'Z')||(LA13_296 >= 'a' && LA13_296 <= 'm')||(LA13_296 >= 'o' && LA13_296 <= 'z')) ) {s = 38;}
						else if ( ((LA13_296 >= '-' && LA13_296 <= '.')||LA13_296=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 343;
						 
						input.seek(index13_296);
						if ( s>=0 ) return s;
						break;

					case 320 : 
						int LA13_342 = input.LA(1);
						 
						int index13_342 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_342=='c') ) {s = 382;}
						else if ( ((LA13_342 >= '0' && LA13_342 <= '9')||(LA13_342 >= 'A' && LA13_342 <= 'Z')||(LA13_342 >= 'a' && LA13_342 <= 'b')||(LA13_342 >= 'd' && LA13_342 <= 'z')) ) {s = 38;}
						else if ( ((LA13_342 >= '-' && LA13_342 <= '.')||LA13_342=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 383;
						 
						input.seek(index13_342);
						if ( s>=0 ) return s;
						break;

					case 321 : 
						int LA13_382 = input.LA(1);
						 
						int index13_382 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_382=='e') ) {s = 414;}
						else if ( ((LA13_382 >= '0' && LA13_382 <= '9')||(LA13_382 >= 'A' && LA13_382 <= 'Z')||(LA13_382 >= 'a' && LA13_382 <= 'd')||(LA13_382 >= 'f' && LA13_382 <= 'z')) ) {s = 38;}
						else if ( ((LA13_382 >= '-' && LA13_382 <= '.')||LA13_382=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 415;
						 
						input.seek(index13_382);
						if ( s>=0 ) return s;
						break;

					case 322 : 
						int LA13_455 = input.LA(1);
						 
						int index13_455 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_455 >= '0' && LA13_455 <= '9')||(LA13_455 >= 'A' && LA13_455 <= 'Z')||(LA13_455 >= 'a' && LA13_455 <= 'z')) ) {s = 38;}
						else if ( ((LA13_455 >= '-' && LA13_455 <= '.')||LA13_455=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 465;
						 
						input.seek(index13_455);
						if ( s>=0 ) return s;
						break;

					case 323 : 
						int LA13_344 = input.LA(1);
						 
						int index13_344 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_344 >= '0' && LA13_344 <= '9')||(LA13_344 >= 'A' && LA13_344 <= 'Z')||(LA13_344 >= 'a' && LA13_344 <= 'z')) ) {s = 38;}
						else if ( ((LA13_344 >= '-' && LA13_344 <= '.')||LA13_344=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 384;
						 
						input.seek(index13_344);
						if ( s>=0 ) return s;
						break;

					case 324 : 
						int LA13_346 = input.LA(1);
						 
						int index13_346 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_346 >= '0' && LA13_346 <= '9')||(LA13_346 >= 'A' && LA13_346 <= 'Z')||(LA13_346 >= 'a' && LA13_346 <= 'z')) ) {s = 38;}
						else if ( ((LA13_346 >= '-' && LA13_346 <= '.')||LA13_346=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 385;
						 
						input.seek(index13_346);
						if ( s>=0 ) return s;
						break;

					case 325 : 
						int LA13_396 = input.LA(1);
						 
						int index13_396 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_396 >= '0' && LA13_396 <= '9')||(LA13_396 >= 'A' && LA13_396 <= 'Z')||(LA13_396 >= 'a' && LA13_396 <= 'z')) ) {s = 38;}
						else if ( ((LA13_396 >= '-' && LA13_396 <= '.')||LA13_396=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 425;
						 
						input.seek(index13_396);
						if ( s>=0 ) return s;
						break;

					case 326 : 
						int LA13_52 = input.LA(1);
						 
						int index13_52 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_52=='p') ) {s = 107;}
						else if ( ((LA13_52 >= '0' && LA13_52 <= '9')||(LA13_52 >= 'A' && LA13_52 <= 'Z')||(LA13_52 >= 'a' && LA13_52 <= 'o')||(LA13_52 >= 'q' && LA13_52 <= 'z')) ) {s = 38;}
						else if ( ((LA13_52 >= '-' && LA13_52 <= '.')||LA13_52=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 108;
						 
						input.seek(index13_52);
						if ( s>=0 ) return s;
						break;

					case 327 : 
						int LA13_107 = input.LA(1);
						 
						int index13_107 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_107=='e') ) {s = 178;}
						else if ( ((LA13_107 >= '0' && LA13_107 <= '9')||(LA13_107 >= 'A' && LA13_107 <= 'Z')||(LA13_107 >= 'a' && LA13_107 <= 'd')||(LA13_107 >= 'f' && LA13_107 <= 'z')) ) {s = 38;}
						else if ( ((LA13_107 >= '-' && LA13_107 <= '.')||LA13_107=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 179;
						 
						input.seek(index13_107);
						if ( s>=0 ) return s;
						break;

					case 328 : 
						int LA13_398 = input.LA(1);
						 
						int index13_398 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_398 >= '0' && LA13_398 <= '9')||(LA13_398 >= 'A' && LA13_398 <= 'Z')||(LA13_398 >= 'a' && LA13_398 <= 'z')) ) {s = 38;}
						else if ( ((LA13_398 >= '-' && LA13_398 <= '.')||LA13_398=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 426;
						 
						input.seek(index13_398);
						if ( s>=0 ) return s;
						break;

					case 329 : 
						int LA13_35 = input.LA(1);
						 
						int index13_35 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_35=='d') ) {s = 75;}
						else if ( ((LA13_35 >= '0' && LA13_35 <= '9')||(LA13_35 >= 'A' && LA13_35 <= 'Z')||(LA13_35 >= 'a' && LA13_35 <= 'c')||(LA13_35 >= 'e' && LA13_35 <= 'z')) ) {s = 38;}
						else if ( ((LA13_35 >= '-' && LA13_35 <= '.')||LA13_35=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 76;
						 
						input.seek(index13_35);
						if ( s>=0 ) return s;
						break;

					case 330 : 
						int LA13_20 = input.LA(1);
						 
						int index13_20 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_20=='o') ) {s = 62;}
						else if ( ((LA13_20 >= '0' && LA13_20 <= '9')||(LA13_20 >= 'A' && LA13_20 <= 'Z')||(LA13_20 >= 'a' && LA13_20 <= 'n')||(LA13_20 >= 'p' && LA13_20 <= 'z')) ) {s = 38;}
						else if ( ((LA13_20 >= '-' && LA13_20 <= '.')||LA13_20=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_20);
						if ( s>=0 ) return s;
						break;

					case 331 : 
						int LA13_40 = input.LA(1);
						 
						int index13_40 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_40=='y') ) {s = 82;}
						else if ( ((LA13_40 >= '0' && LA13_40 <= '9')||(LA13_40 >= 'A' && LA13_40 <= 'Z')||(LA13_40 >= 'a' && LA13_40 <= 'x')||LA13_40=='z') ) {s = 38;}
						else if ( ((LA13_40 >= '-' && LA13_40 <= '.')||LA13_40=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 83;
						 
						input.seek(index13_40);
						if ( s>=0 ) return s;
						break;

					case 332 : 
						int LA13_82 = input.LA(1);
						 
						int index13_82 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_82=='s') ) {s = 154;}
						else if ( ((LA13_82 >= '0' && LA13_82 <= '9')||(LA13_82 >= 'A' && LA13_82 <= 'Z')||(LA13_82 >= 'a' && LA13_82 <= 'r')||(LA13_82 >= 't' && LA13_82 <= 'z')) ) {s = 38;}
						else if ( ((LA13_82 >= '-' && LA13_82 <= '.')||LA13_82=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 155;
						 
						input.seek(index13_82);
						if ( s>=0 ) return s;
						break;

					case 333 : 
						int LA13_18 = input.LA(1);
						 
						int index13_18 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_18=='r') ) {s = 57;}
						else if ( (LA13_18=='l') ) {s = 58;}
						else if ( ((LA13_18 >= '0' && LA13_18 <= '9')||(LA13_18 >= 'A' && LA13_18 <= 'Z')||(LA13_18 >= 'a' && LA13_18 <= 'k')||(LA13_18 >= 'm' && LA13_18 <= 'q')||(LA13_18 >= 's' && LA13_18 <= 'z')) ) {s = 38;}
						else if ( ((LA13_18 >= '-' && LA13_18 <= '.')||LA13_18=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 39;
						 
						input.seek(index13_18);
						if ( s>=0 ) return s;
						break;

					case 334 : 
						int LA13_42 = input.LA(1);
						 
						int index13_42 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_42=='n') ) {s = 86;}
						else if ( ((LA13_42 >= '0' && LA13_42 <= '9')||(LA13_42 >= 'A' && LA13_42 <= 'Z')||(LA13_42 >= 'a' && LA13_42 <= 'm')||(LA13_42 >= 'o' && LA13_42 <= 'z')) ) {s = 38;}
						else if ( ((LA13_42 >= '-' && LA13_42 <= '.')||LA13_42=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 87;
						 
						input.seek(index13_42);
						if ( s>=0 ) return s;
						break;

					case 335 : 
						int LA13_86 = input.LA(1);
						 
						int index13_86 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_86=='i') ) {s = 158;}
						else if ( ((LA13_86 >= '0' && LA13_86 <= '9')||(LA13_86 >= 'A' && LA13_86 <= 'Z')||(LA13_86 >= 'a' && LA13_86 <= 'h')||(LA13_86 >= 'j' && LA13_86 <= 'z')) ) {s = 38;}
						else if ( ((LA13_86 >= '-' && LA13_86 <= '.')||LA13_86=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 159;
						 
						input.seek(index13_86);
						if ( s>=0 ) return s;
						break;

					case 336 : 
						int LA13_49 = input.LA(1);
						 
						int index13_49 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_49=='e') ) {s = 100;}
						else if ( ((LA13_49 >= '0' && LA13_49 <= '9')||(LA13_49 >= 'A' && LA13_49 <= 'Z')||(LA13_49 >= 'a' && LA13_49 <= 'd')||(LA13_49 >= 'f' && LA13_49 <= 'z')) ) {s = 38;}
						else if ( ((LA13_49 >= '-' && LA13_49 <= '.')||LA13_49=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 101;
						 
						input.seek(index13_49);
						if ( s>=0 ) return s;
						break;

					case 337 : 
						int LA13_158 = input.LA(1);
						 
						int index13_158 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_158=='e') ) {s = 226;}
						else if ( ((LA13_158 >= '0' && LA13_158 <= '9')||(LA13_158 >= 'A' && LA13_158 <= 'Z')||(LA13_158 >= 'a' && LA13_158 <= 'd')||(LA13_158 >= 'f' && LA13_158 <= 'z')) ) {s = 38;}
						else if ( ((LA13_158 >= '-' && LA13_158 <= '.')||LA13_158=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 227;
						 
						input.seek(index13_158);
						if ( s>=0 ) return s;
						break;

					case 338 : 
						int LA13_100 = input.LA(1);
						 
						int index13_100 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_100=='n') ) {s = 170;}
						else if ( ((LA13_100 >= '0' && LA13_100 <= '9')||(LA13_100 >= 'A' && LA13_100 <= 'Z')||(LA13_100 >= 'a' && LA13_100 <= 'm')||(LA13_100 >= 'o' && LA13_100 <= 'z')) ) {s = 38;}
						else if ( ((LA13_100 >= '-' && LA13_100 <= '.')||LA13_100=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 171;
						 
						input.seek(index13_100);
						if ( s>=0 ) return s;
						break;

					case 339 : 
						int LA13_226 = input.LA(1);
						 
						int index13_226 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_226=='n') ) {s = 284;}
						else if ( ((LA13_226 >= '0' && LA13_226 <= '9')||(LA13_226 >= 'A' && LA13_226 <= 'Z')||(LA13_226 >= 'a' && LA13_226 <= 'm')||(LA13_226 >= 'o' && LA13_226 <= 'z')) ) {s = 38;}
						else if ( ((LA13_226 >= '-' && LA13_226 <= '.')||LA13_226=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 285;
						 
						input.seek(index13_226);
						if ( s>=0 ) return s;
						break;

					case 340 : 
						int LA13_284 = input.LA(1);
						 
						int index13_284 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_284=='t') ) {s = 333;}
						else if ( ((LA13_284 >= '0' && LA13_284 <= '9')||(LA13_284 >= 'A' && LA13_284 <= 'Z')||(LA13_284 >= 'a' && LA13_284 <= 's')||(LA13_284 >= 'u' && LA13_284 <= 'z')) ) {s = 38;}
						else if ( ((LA13_284 >= '-' && LA13_284 <= '.')||LA13_284=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 334;
						 
						input.seek(index13_284);
						if ( s>=0 ) return s;
						break;

					case 341 : 
						int LA13_58 = input.LA(1);
						 
						int index13_58 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_58=='o') ) {s = 119;}
						else if ( ((LA13_58 >= '0' && LA13_58 <= '9')||(LA13_58 >= 'A' && LA13_58 <= 'Z')||(LA13_58 >= 'a' && LA13_58 <= 'n')||(LA13_58 >= 'p' && LA13_58 <= 'z')) ) {s = 38;}
						else if ( ((LA13_58 >= '-' && LA13_58 <= '.')||LA13_58=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 120;
						 
						input.seek(index13_58);
						if ( s>=0 ) return s;
						break;

					case 342 : 
						int LA13_119 = input.LA(1);
						 
						int index13_119 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_119=='s') ) {s = 189;}
						else if ( ((LA13_119 >= '0' && LA13_119 <= '9')||(LA13_119 >= 'A' && LA13_119 <= 'Z')||(LA13_119 >= 'a' && LA13_119 <= 'r')||(LA13_119 >= 't' && LA13_119 <= 'z')) ) {s = 38;}
						else if ( ((LA13_119 >= '-' && LA13_119 <= '.')||LA13_119=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 190;
						 
						input.seek(index13_119);
						if ( s>=0 ) return s;
						break;

					case 343 : 
						int LA13_414 = input.LA(1);
						 
						int index13_414 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_414 >= '0' && LA13_414 <= '9')||(LA13_414 >= 'A' && LA13_414 <= 'Z')||(LA13_414 >= 'a' && LA13_414 <= 'z')) ) {s = 38;}
						else if ( ((LA13_414 >= '-' && LA13_414 <= '.')||LA13_414=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 437;
						 
						input.seek(index13_414);
						if ( s>=0 ) return s;
						break;

					case 344 : 
						int LA13_189 = input.LA(1);
						 
						int index13_189 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_189=='e') ) {s = 252;}
						else if ( ((LA13_189 >= '0' && LA13_189 <= '9')||(LA13_189 >= 'A' && LA13_189 <= 'Z')||(LA13_189 >= 'a' && LA13_189 <= 'd')||(LA13_189 >= 'f' && LA13_189 <= 'z')) ) {s = 38;}
						else if ( ((LA13_189 >= '-' && LA13_189 <= '.')||LA13_189=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 253;
						 
						input.seek(index13_189);
						if ( s>=0 ) return s;
						break;

					case 345 : 
						int LA13_90 = input.LA(1);
						 
						int index13_90 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_90=='c') ) {s = 161;}
						else if ( ((LA13_90 >= '0' && LA13_90 <= '9')||(LA13_90 >= 'A' && LA13_90 <= 'Z')||(LA13_90 >= 'a' && LA13_90 <= 'b')||(LA13_90 >= 'd' && LA13_90 <= 'z')) ) {s = 38;}
						else if ( ((LA13_90 >= '-' && LA13_90 <= '.')||LA13_90=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 162;
						 
						input.seek(index13_90);
						if ( s>=0 ) return s;
						break;

					case 346 : 
						int LA13_161 = input.LA(1);
						 
						int index13_161 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_161=='h') ) {s = 229;}
						else if ( ((LA13_161 >= '0' && LA13_161 <= '9')||(LA13_161 >= 'A' && LA13_161 <= 'Z')||(LA13_161 >= 'a' && LA13_161 <= 'g')||(LA13_161 >= 'i' && LA13_161 <= 'z')) ) {s = 38;}
						else if ( ((LA13_161 >= '-' && LA13_161 <= '.')||LA13_161=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 230;
						 
						input.seek(index13_161);
						if ( s>=0 ) return s;
						break;

					case 347 : 
						int LA13_71 = input.LA(1);
						 
						int index13_71 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_71=='g') ) {s = 145;}
						else if ( ((LA13_71 >= '0' && LA13_71 <= '9')||(LA13_71 >= 'A' && LA13_71 <= 'Z')||(LA13_71 >= 'a' && LA13_71 <= 'f')||(LA13_71 >= 'h' && LA13_71 <= 'z')) ) {s = 38;}
						else if ( ((LA13_71 >= '-' && LA13_71 <= '.')||LA13_71=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 146;
						 
						input.seek(index13_71);
						if ( s>=0 ) return s;
						break;

					case 348 : 
						int LA13_229 = input.LA(1);
						 
						int index13_229 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_229=='i') ) {s = 286;}
						else if ( ((LA13_229 >= '0' && LA13_229 <= '9')||(LA13_229 >= 'A' && LA13_229 <= 'Z')||(LA13_229 >= 'a' && LA13_229 <= 'h')||(LA13_229 >= 'j' && LA13_229 <= 'z')) ) {s = 38;}
						else if ( ((LA13_229 >= '-' && LA13_229 <= '.')||LA13_229=='_') && ((runtimeOpAhead()))) {s = 32;}
						else s = 287;
						 
						input.seek(index13_229);
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
