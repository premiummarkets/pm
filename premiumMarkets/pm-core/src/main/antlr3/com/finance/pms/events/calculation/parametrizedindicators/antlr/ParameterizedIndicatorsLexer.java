// $ANTLR 3.5.2 com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g 2018-05-28 18:51:09
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

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:51:7: ( ';' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:51:9: ';'
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
	// $ANTLR end "COMMA"

	// $ANTLR start "DAYS"
	public final void mDAYS() throws RecognitionException {
		try {
			int _type = DAYS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:52:6: ( 'days' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:52:8: 'days'
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:53:9: ( 'lenient' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:53:11: 'lenient'
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

	// $ANTLR start "NOT"
	public final void mNOT() throws RecognitionException {
		try {
			int _type = NOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:54:5: ( 'not' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:54:7: 'not'
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:55:17: ( '(' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:55:19: '('
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

	// $ANTLR start "OR"
	public final void mOR() throws RecognitionException {
		try {
			int _type = OR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:56:4: ( 'or' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:56:6: 'or'
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:57:9: ( '%' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:57:11: '%'
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

	// $ANTLR start "T__50"
	public final void mT__50() throws RecognitionException {
		try {
			int _type = T__50;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:58:7: ( 'also display' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:58:9: 'also display'
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
	// $ANTLR end "T__50"

	// $ANTLR start "T__51"
	public final void mT__51() throws RecognitionException {
		try {
			int _type = T__51;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:59:7: ( 'bearish' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:59:9: 'bearish'
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
	// $ANTLR end "T__51"

	// $ANTLR start "T__52"
	public final void mT__52() throws RecognitionException {
		try {
			int _type = T__52;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:60:7: ( 'bullish' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:60:9: 'bullish'
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
	// $ANTLR end "T__52"

	// $ANTLR start "T__53"
	public final void mT__53() throws RecognitionException {
		try {
			int _type = T__53;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:61:7: ( 'crosses down historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:61:9: 'crosses down historical'
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
	// $ANTLR end "T__53"

	// $ANTLR start "T__54"
	public final void mT__54() throws RecognitionException {
		try {
			int _type = T__54;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:62:7: ( 'crosses down threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:62:9: 'crosses down threshold'
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
	// $ANTLR end "T__54"

	// $ANTLR start "T__55"
	public final void mT__55() throws RecognitionException {
		try {
			int _type = T__55;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:63:7: ( 'crosses up historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:63:9: 'crosses up historical'
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
	// $ANTLR end "T__55"

	// $ANTLR start "T__56"
	public final void mT__56() throws RecognitionException {
		try {
			int _type = T__56;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:64:7: ( 'crosses up threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:64:9: 'crosses up threshold'
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
	// $ANTLR end "T__56"

	// $ANTLR start "T__57"
	public final void mT__57() throws RecognitionException {
		try {
			int _type = T__57;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:65:7: ( 'equals historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:65:9: 'equals historical'
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
	// $ANTLR end "T__57"

	// $ANTLR start "T__58"
	public final void mT__58() throws RecognitionException {
		try {
			int _type = T__58;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:66:7: ( 'equals threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:66:9: 'equals threshold'
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
	// $ANTLR end "T__58"

	// $ANTLR start "T__59"
	public final void mT__59() throws RecognitionException {
		try {
			int _type = T__59;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:67:7: ( 'equals trend' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:67:9: 'equals trend'
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
	// $ANTLR end "T__59"

	// $ANTLR start "T__60"
	public final void mT__60() throws RecognitionException {
		try {
			int _type = T__60;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:68:7: ( 'for' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:68:9: 'for'
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
	// $ANTLR end "T__60"

	// $ANTLR start "T__61"
	public final void mT__61() throws RecognitionException {
		try {
			int _type = T__61;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:69:7: ( 'goes down more than' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:69:9: 'goes down more than'
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
	// $ANTLR end "T__61"

	// $ANTLR start "T__62"
	public final void mT__62() throws RecognitionException {
		try {
			int _type = T__62;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:70:7: ( 'goes up more than' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:70:9: 'goes up more than'
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
	// $ANTLR end "T__62"

	// $ANTLR start "T__63"
	public final void mT__63() throws RecognitionException {
		try {
			int _type = T__63;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:71:7: ( 'is above historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:71:9: 'is above historical'
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
	// $ANTLR end "T__63"

	// $ANTLR start "T__64"
	public final void mT__64() throws RecognitionException {
		try {
			int _type = T__64;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:72:7: ( 'is above threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:72:9: 'is above threshold'
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
	// $ANTLR end "T__64"

	// $ANTLR start "T__65"
	public final void mT__65() throws RecognitionException {
		try {
			int _type = T__65;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:73:7: ( 'is bearish if not bullish' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:73:9: 'is bearish if not bullish'
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
	// $ANTLR end "T__65"

	// $ANTLR start "T__66"
	public final void mT__66() throws RecognitionException {
		try {
			int _type = T__66;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:74:7: ( 'is bearish when' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:74:9: 'is bearish when'
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
	// $ANTLR end "T__66"

	// $ANTLR start "T__67"
	public final void mT__67() throws RecognitionException {
		try {
			int _type = T__67;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:75:7: ( 'is below historical' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:75:9: 'is below historical'
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
	// $ANTLR end "T__67"

	// $ANTLR start "T__68"
	public final void mT__68() throws RecognitionException {
		try {
			int _type = T__68;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:76:7: ( 'is below threshold' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:76:9: 'is below threshold'
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
	// $ANTLR end "T__68"

	// $ANTLR start "T__69"
	public final void mT__69() throws RecognitionException {
		try {
			int _type = T__69;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:77:7: ( 'is bullish when' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:77:9: 'is bullish when'
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
	// $ANTLR end "T__69"

	// $ANTLR start "T__70"
	public final void mT__70() throws RecognitionException {
		try {
			int _type = T__70;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:7: ( 'makes a higher high spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:78:9: 'makes a higher high spanning'
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
	// $ANTLR end "T__70"

	// $ANTLR start "T__71"
	public final void mT__71() throws RecognitionException {
		try {
			int _type = T__71;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:79:7: ( 'makes a higher low spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:79:9: 'makes a higher low spanning'
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
	// $ANTLR end "T__71"

	// $ANTLR start "T__72"
	public final void mT__72() throws RecognitionException {
		try {
			int _type = T__72;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:80:7: ( 'makes a lower high spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:80:9: 'makes a lower high spanning'
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
	// $ANTLR end "T__72"

	// $ANTLR start "T__73"
	public final void mT__73() throws RecognitionException {
		try {
			int _type = T__73;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:81:7: ( 'makes a lower low spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:81:9: 'makes a lower low spanning'
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
	// $ANTLR end "T__73"

	// $ANTLR start "T__74"
	public final void mT__74() throws RecognitionException {
		try {
			int _type = T__74;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:82:7: ( 'more than' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:82:9: 'more than'
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
	// $ANTLR end "T__74"

	// $ANTLR start "T__75"
	public final void mT__75() throws RecognitionException {
		try {
			int _type = T__75;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:83:7: ( 'over' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:83:9: 'over'
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
	// $ANTLR end "T__75"

	// $ANTLR start "T__76"
	public final void mT__76() throws RecognitionException {
		try {
			int _type = T__76;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:84:7: ( 'override start shift with' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:84:9: 'override start shift with'
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
	// $ANTLR end "T__76"

	// $ANTLR start "T__77"
	public final void mT__77() throws RecognitionException {
		try {
			int _type = T__77;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:85:7: ( 'reverses down' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:85:9: 'reverses down'
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
	// $ANTLR end "T__77"

	// $ANTLR start "T__78"
	public final void mT__78() throws RecognitionException {
		try {
			int _type = T__78;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:86:7: ( 'reverses up' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:86:9: 'reverses up'
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
	// $ANTLR end "T__78"

	// $ANTLR start "T__79"
	public final void mT__79() throws RecognitionException {
		try {
			int _type = T__79;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:87:7: ( 'spanning' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:87:9: 'spanning'
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
	// $ANTLR end "T__79"

	// $ANTLR start "Operation"
	public final void mOperation() throws RecognitionException {
		try {
			int _type = Operation;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:7: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:9: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:266:60: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:7: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:10: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:10: ( '-' )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0=='-') ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:11: '-'
					{
					match('-'); 
					}
					break;

			}

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:17: ( '0' .. '9' )+
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

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:29: ( '.' ( '0' .. '9' )+ )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0=='.') ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:30: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:270:34: ( '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:6: ( '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )* '\"' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:8: '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )* '\"'
			{
			match('\"'); 
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:274:12: ( 'a' .. 'z' | 'A' .. 'Z' | '.' | '_' )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0=='.'||(LA6_0 >= 'A' && LA6_0 <= 'Z')||LA6_0=='_'||(LA6_0 >= 'a' && LA6_0 <= 'z')) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:
					{
					if ( input.LA(1)=='.'||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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
					break loop6;
				}
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:4: ({...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:6: {...}? => ( 'open' | 'close' | 'high' | 'low' | 'volume' )
			{
			if ( !((runtimeHistoryOpAhead())) ) {
				throw new FailedPredicateException(input, "HistoricalData", "runtimeHistoryOpAhead()");
			}
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:36: ( 'open' | 'close' | 'high' | 'low' | 'volume' )
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:38: 'open'
					{
					match("open"); 

					}
					break;
				case 2 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:47: 'close'
					{
					match("close"); 

					}
					break;
				case 3 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:57: 'high'
					{
					match("high"); 

					}
					break;
				case 4 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:66: 'low'
					{
					match("low"); 

					}
					break;
				case 5 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:278:75: 'volume'
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:7: ( ( ( ' ' )+ ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:9: ( ( ' ' )+ )
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:9: ( ( ' ' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:10: ( ' ' )+
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:10: ( ' ' )+
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:282:10: ' '
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:6: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+ )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+
			{
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:286:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )+
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:5: ( ( '\\r' | '\\t' | '\\u000C' | '\\n' ) )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:291:7: ( '\\r' | '\\t' | '\\u000C' | '\\n' )
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:5: ( '/*' ( . )* '*/' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:7: '/*' ( . )* '*/'
			{
			match("/*"); 

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:12: ( . )*
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
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:294:12: .
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
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match("//"); 

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:12: (~ ( '\\n' | '\\r' ) )*
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

			// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:26: ( '\\r' )?
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0=='\r') ) {
				alt12=1;
			}
			switch (alt12) {
				case 1 :
					// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:297:26: '\\r'
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
		// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:8: ( AND | CLOSEPARENTEHSIS | COMMA | DAYS | LENIENT | NOT | OPENPARENTEHSIS | OR | PERCENT | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | Operation | NumberToken | StringToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT )
		int alt13=48;
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
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:31: COMMA
				{
				mCOMMA(); 

				}
				break;
			case 4 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:37: DAYS
				{
				mDAYS(); 

				}
				break;
			case 5 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:42: LENIENT
				{
				mLENIENT(); 

				}
				break;
			case 6 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:50: NOT
				{
				mNOT(); 

				}
				break;
			case 7 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:54: OPENPARENTEHSIS
				{
				mOPENPARENTEHSIS(); 

				}
				break;
			case 8 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:70: OR
				{
				mOR(); 

				}
				break;
			case 9 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:73: PERCENT
				{
				mPERCENT(); 

				}
				break;
			case 10 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:81: T__50
				{
				mT__50(); 

				}
				break;
			case 11 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:87: T__51
				{
				mT__51(); 

				}
				break;
			case 12 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:93: T__52
				{
				mT__52(); 

				}
				break;
			case 13 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:99: T__53
				{
				mT__53(); 

				}
				break;
			case 14 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:105: T__54
				{
				mT__54(); 

				}
				break;
			case 15 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:111: T__55
				{
				mT__55(); 

				}
				break;
			case 16 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:117: T__56
				{
				mT__56(); 

				}
				break;
			case 17 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:123: T__57
				{
				mT__57(); 

				}
				break;
			case 18 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:129: T__58
				{
				mT__58(); 

				}
				break;
			case 19 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:135: T__59
				{
				mT__59(); 

				}
				break;
			case 20 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:141: T__60
				{
				mT__60(); 

				}
				break;
			case 21 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:147: T__61
				{
				mT__61(); 

				}
				break;
			case 22 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:153: T__62
				{
				mT__62(); 

				}
				break;
			case 23 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:159: T__63
				{
				mT__63(); 

				}
				break;
			case 24 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:165: T__64
				{
				mT__64(); 

				}
				break;
			case 25 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:171: T__65
				{
				mT__65(); 

				}
				break;
			case 26 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:177: T__66
				{
				mT__66(); 

				}
				break;
			case 27 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:183: T__67
				{
				mT__67(); 

				}
				break;
			case 28 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:189: T__68
				{
				mT__68(); 

				}
				break;
			case 29 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:195: T__69
				{
				mT__69(); 

				}
				break;
			case 30 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:201: T__70
				{
				mT__70(); 

				}
				break;
			case 31 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:207: T__71
				{
				mT__71(); 

				}
				break;
			case 32 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:213: T__72
				{
				mT__72(); 

				}
				break;
			case 33 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:219: T__73
				{
				mT__73(); 

				}
				break;
			case 34 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:225: T__74
				{
				mT__74(); 

				}
				break;
			case 35 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:231: T__75
				{
				mT__75(); 

				}
				break;
			case 36 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:237: T__76
				{
				mT__76(); 

				}
				break;
			case 37 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:243: T__77
				{
				mT__77(); 

				}
				break;
			case 38 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:249: T__78
				{
				mT__78(); 

				}
				break;
			case 39 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:255: T__79
				{
				mT__79(); 

				}
				break;
			case 40 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:261: Operation
				{
				mOperation(); 

				}
				break;
			case 41 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:271: NumberToken
				{
				mNumberToken(); 

				}
				break;
			case 42 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:283: StringToken
				{
				mStringToken(); 

				}
				break;
			case 43 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:295: HistoricalData
				{
				mHistoricalData(); 

				}
				break;
			case 44 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:310: WhiteChar
				{
				mWhiteChar(); 

				}
				break;
			case 45 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:320: Tcheat
				{
				mTcheat(); 

				}
				break;
			case 46 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:327: WS
				{
				mWS(); 

				}
				break;
			case 47 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:330: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 48 :
				// com/finance/pms/events/calculation/parametrizedindicators/antlr/ParameterizedIndicators.g:1:338: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;

		}
	}


	protected DFA13 dfa13 = new DFA13(this);
	static final String DFA13_eotS =
		"\1\uffff\1\40\2\uffff\3\40\1\uffff\1\40\1\uffff\12\40\1\uffff\1\24\1\uffff"+
		"\2\40\4\uffff\1\71\1\73\1\74\1\uffff\1\76\1\100\1\102\1\104\1\105\1\107"+
		"\1\111\1\113\1\115\1\117\1\121\1\123\1\125\1\127\1\131\1\133\1\135\1\137"+
		"\1\141\1\143\1\145\2\uffff\1\146\1\uffff\1\150\2\uffff\1\152\1\uffff\1"+
		"\154\1\uffff\1\155\1\uffff\1\156\2\uffff\1\160\1\uffff\1\162\1\uffff\1"+
		"\164\1\uffff\1\166\1\uffff\1\170\1\uffff\1\172\1\uffff\1\174\1\uffff\1"+
		"\175\1\uffff\1\177\3\uffff\1\u0083\1\uffff\1\u0085\1\uffff\1\u0087\1\uffff"+
		"\1\u0089\1\uffff\1\u008b\1\uffff\1\u008d\2\uffff\1\u008f\1\uffff\1\u0090"+
		"\1\uffff\1\u0092\3\uffff\1\u0095\1\uffff\1\u0096\1\uffff\1\u0098\1\uffff"+
		"\1\u009a\1\uffff\1\u009c\1\uffff\1\u009e\1\uffff\1\u00a0\2\uffff\1\u00a2"+
		"\3\uffff\1\u00a7\1\uffff\1\u00a9\1\uffff\1\u00ab\1\uffff\1\u00ad\1\uffff"+
		"\1\u00ae\1\uffff\1\u00b0\4\uffff\1\u00b2\2\uffff\1\u00b4\2\uffff\1\u00b6"+
		"\1\uffff\1\u00b8\1\uffff\1\u00ba\1\uffff\1\u00bb\1\uffff\1\u00bd\6\uffff"+
		"\1\u00c4\3\uffff\1\u00c6\1\uffff\1\u00c8\2\uffff\1\u00ca\1\uffff\1\u00cc"+
		"\1\uffff\1\u00ce\1\uffff\1\u00d0\1\uffff\1\u00d2\1\uffff\1\u00d4\2\uffff"+
		"\1\u00d6\10\uffff\1\u00dc\1\uffff\1\u00de\1\uffff\1\u00df\1\uffff\1\u00e0"+
		"\1\uffff\1\u00e2\1\uffff\1\u00e3\1\uffff\1\u00e4\1\uffff\1\u00e6\7\uffff"+
		"\1\u00ee\1\uffff\1\u00f0\3\uffff\1\u00f2\13\uffff\1\u00fd\1\uffff\1\u00fe"+
		"\62\uffff";
	static final String DFA13_eofS =
		"\u0122\uffff";
	static final String DFA13_minS =
		"\1\11\1\60\2\uffff\3\60\1\uffff\1\60\1\uffff\12\60\1\uffff\1\60\1\uffff"+
		"\2\60\3\uffff\1\52\3\60\1\uffff\16\60\1\40\6\60\2\uffff\1\60\1\0\1\60"+
		"\2\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\uffff\1\60\1\0\1\60\1\0\1\60"+
		"\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\141\1\0\1"+
		"\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\uffff\1\40\1\0"+
		"\1\60\1\0\1\60\2\0\1\uffff\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1"+
		"\0\1\60\1\0\1\60\1\0\1\uffff\1\40\1\0\1\142\1\145\1\60\1\0\1\40\1\0\1"+
		"\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\uffff\1\0\1\uffff\1\60\1\0\1\uffff"+
		"\1\60\1\uffff\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\144\1"+
		"\0\1\157\1\141\1\uffff\1\40\1\0\1\uffff\1\0\1\60\1\0\1\60\2\0\1\60\1\0"+
		"\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\2\0\1\40\1\0\2\uffff\1\166\1"+
		"\162\1\157\1\141\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60"+
		"\1\0\1\60\1\0\1\40\1\0\1\150\1\0\1\145\1\151\1\167\1\40\1\60\1\0\1\60"+
		"\2\0\1\uffff\1\40\1\0\2\uffff\1\144\1\0\1\uffff\1\150\1\40\1\163\1\40"+
		"\1\150\1\40\1\0\1\60\1\0\1\uffff\1\0\1\157\1\160\2\uffff\3\150\1\151\1"+
		"\157\1\144\1\0\1\uffff\1\167\1\40\2\uffff\1\40\2\uffff\1\147\1\167\2\uffff"+
		"\1\156\1\150\1\151\1\150\1\145\1\40\4\uffff\1\145\1\162\1\150\1\162\1"+
		"\40\2\uffff\1\40\2\150\4\uffff";
	static final String DFA13_maxS =
		"\2\172\2\uffff\3\172\1\uffff\1\172\1\uffff\12\172\1\uffff\1\172\1\uffff"+
		"\2\172\3\uffff\1\57\3\172\1\uffff\25\172\2\uffff\1\172\1\0\1\172\2\0\1"+
		"\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\142"+
		"\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff"+
		"\1\172\1\0\1\172\1\0\1\172\2\0\1\uffff\1\172\1\0\1\172\1\0\1\172\1\0\1"+
		"\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\172\1\0\1\142\1\165\1"+
		"\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\uffff\1\0"+
		"\1\uffff\1\172\1\0\1\uffff\1\172\1\uffff\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\1\0\1\172\1\0\1\165\1\0\1\157\1\154\1\uffff\1\172\1\0\1\uffff"+
		"\1\0\1\172\1\0\1\172\2\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172"+
		"\1\0\1\172\2\0\1\172\1\0\2\uffff\1\166\1\162\1\157\1\141\1\0\1\172\1\0"+
		"\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0\1\172\1\0"+
		"\1\164\1\0\1\145\1\151\1\167\1\40\1\172\1\0\1\172\2\0\1\uffff\1\172\1"+
		"\0\2\uffff\1\165\1\0\1\uffff\1\162\1\40\1\163\1\40\1\154\1\172\1\0\1\172"+
		"\1\0\1\uffff\1\0\1\157\1\160\2\uffff\1\164\1\150\1\164\1\151\1\157\1\165"+
		"\1\0\1\uffff\1\167\1\40\2\uffff\1\40\2\uffff\1\147\1\167\2\uffff\1\156"+
		"\1\164\1\167\1\150\1\145\1\40\4\uffff\1\145\1\162\1\164\1\162\1\40\2\uffff"+
		"\1\40\2\154\4\uffff";
	static final String DFA13_acceptS =
		"\2\uffff\1\2\1\3\3\uffff\1\7\1\uffff\1\11\12\uffff\1\51\1\uffff\1\52\2"+
		"\uffff\1\54\1\50\1\56\4\uffff\1\55\25\uffff\1\57\1\60\15\uffff\1\10\40"+
		"\uffff\1\1\7\uffff\1\6\16\uffff\1\24\20\uffff\1\12\1\uffff\1\4\2\uffff"+
		"\1\53\1\uffff\1\43\17\uffff\1\35\2\uffff\1\42\25\uffff\1\25\1\26\40\uffff"+
		"\1\5\2\uffff\1\13\1\14\2\uffff\1\21\11\uffff\1\44\3\uffff\1\22\1\23\7"+
		"\uffff\1\47\2\uffff\1\27\1\30\1\uffff\1\33\1\34\2\uffff\1\45\1\46\6\uffff"+
		"\1\17\1\20\1\31\1\32\5\uffff\1\15\1\16\3\uffff\1\40\1\41\1\36\1\37";
	static final String DFA13_specialS =
		"\1\u00b5\1\u00a1\2\uffff\1\u0084\1\0\1\u0085\1\uffff\1\u009e\1\uffff\1"+
		"\u00b9\1\u0091\1\u00be\1\u00b8\1\u009a\1\177\1\1\1\u00bf\1\u009d\1\u00b3"+
		"\3\uffff\1\u00b6\1\133\4\uffff\1\5\1\137\1\u00bc\1\uffff\1\6\1\125\1\u0092"+
		"\1\132\1\u00bd\1\u0098\1\u0086\1\147\1\160\1\u009f\1\u008a\1\u00a5\1\u009b"+
		"\1\165\1\4\1\u0087\1\134\1\u00a4\1\170\1\u008e\1\u0093\2\uffff\1\u00b4"+
		"\1\12\1\140\1\21\1\11\1\7\1\31\1\126\1\56\1\u0082\1\57\1\u00bb\1\66\1"+
		"\uffff\1\u0099\1\74\1\u0088\1\75\1\151\1\15\1\161\1\45\1\u00a0\1\124\1"+
		"\u008b\1\20\1\u00a8\1\22\1\u0097\1\73\1\166\1\113\1\uffff\1\123\1\u0089"+
		"\1\107\1\135\1\60\1\u00a6\1\76\1\171\1\10\1\u008f\1\70\1\u0094\1\115\1"+
		"\uffff\1\141\1\112\1\u00b7\1\61\1\127\1\46\1\142\1\uffff\1\u00b2\1\102"+
		"\1\176\1\110\1\153\1\55\1\162\1\114\1\u00a2\1\47\1\u008c\1\72\1\u00ac"+
		"\1\44\1\uffff\1\167\1\77\2\uffff\1\u008d\1\52\1\136\1\54\1\u00a9\1\100"+
		"\1\172\1\120\1\u0081\1\30\1\u0095\1\105\1\uffff\1\25\1\uffff\1\130\1\32"+
		"\1\uffff\1\150\1\uffff\1\143\1\155\1\62\1\163\1\14\1\u00a3\1\106\1\u0080"+
		"\1\67\1\u00ae\1\122\1\uffff\1\104\3\uffff\1\u0090\1\17\1\uffff\1\121\1"+
		"\u00aa\1\23\1\173\1\36\1\144\1\u0096\1\33\1\131\1\34\1\152\1\63\1\157"+
		"\1\101\1\164\1\41\1\u00a7\1\24\1\145\1\u00b1\1\51\6\uffff\1\27\1\u00ad"+
		"\1\64\1\174\1\37\1\u0083\1\26\1\u00ba\1\71\1\154\1\50\1\2\1\42\1\3\1\43"+
		"\1\u00ab\1\35\1\uffff\1\13\4\uffff\1\u00af\1\111\1\175\1\103\1\146\1\uffff"+
		"\1\156\1\65\3\uffff\1\16\6\uffff\1\u00b0\1\40\1\u009c\1\53\1\uffff\1\117"+
		"\12\uffff\1\116\44\uffff}>";
	static final String[] DFA13_transitionS = {
			"\2\33\1\uffff\2\33\22\uffff\1\31\1\uffff\1\26\2\uffff\1\11\2\uffff\1"+
			"\7\1\2\3\uffff\1\24\1\uffff\1\34\12\25\1\uffff\1\3\5\uffff\32\30\4\uffff"+
			"\1\32\1\uffff\1\1\1\12\1\13\1\4\1\14\1\15\1\16\1\23\1\17\2\30\1\5\1\20"+
			"\1\6\1\10\2\30\1\21\1\22\2\30\1\27\4\30",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\13\37\1\36\1\37\1\35\14\37",
			"",
			"",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\1\41\31\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\42\11\37\1\43\13\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\16\37\1\44\13\37",
			"",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\17\37\1\47\1\37\1\45\3\37"+
			"\1\46\4\37",
			"",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\50\17\37\1\51\5\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\13\37\1\53\5\37\1\52\10\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\20\37\1\54\11\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\16\37\1\55\13\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\16\37\1\56\13\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\57\7\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\1\60\15\37\1\61\13\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\62\25\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\17\37\1\63\12\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\10\37\1\64\21\37",
			"",
			"\12\25\7\uffff\32\40\6\uffff\32\40",
			"",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\16\37\1\65\13\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"",
			"",
			"",
			"\1\66\4\uffff\1\67",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\3\37\1\70\26\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\72\7\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\30\37\1\75\1\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\15\37\1\77\14\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\26\37\1\101\3\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\23\37\1\103\6\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\106\25\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\110\25\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\1\112\31\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\13\37\1\114\16\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\16\37\1\116\13\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\16\37\1\120\13\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\24\37\1\122\5\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\21\37\1\124\10\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\126\25\37",
			"\1\130\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\12\37\1\132\17\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\21\37\1\134\10\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\25\37\1\136\4\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\1\140\31\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\6\37\1\142\23\37",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\13\37\1\144\16\37",
			"",
			"",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\16\37\1\147\13\37",
			"\1\uffff",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\151\7\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\10\37\1\153\21\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\21\37\1\157\10\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\15\37\1\161\14\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\21\37\1\163\10\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\13\37\1\165\16\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\167\7\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\171\7\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\1\173\31\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\176\7\37",
			"\1\uffff",
			"\1\u0080\1\u0081",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u0082\25\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u0084\25\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u0086\25\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\15\37\1\u0088\14\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\7\37\1\u008a\22\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\24\37\1\u008c\5\37",
			"\1\uffff",
			"",
			"\1\u008e\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u0091\25\37",
			"\1\uffff",
			"\1\uffff",
			"",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\21\37\1\u0094\10\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\10\37\1\u0097\21\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\10\37\1\u0099\21\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\u009b\7\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u009d\25\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\13\37\1\u009f\16\37",
			"\1\uffff",
			"",
			"\1\u00a1\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\1\u00a3",
			"\1\u00a4\17\uffff\1\u00a5",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\u00a6\7\37",
			"\1\uffff",
			"\1\u00a8\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\21\37\1\u00aa\10\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\15\37\1\u00ac\14\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\14\37\1\u00af\15\37",
			"\1\uffff",
			"",
			"\1\uffff",
			"",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\15\37\1\u00b1\14\37",
			"\1\uffff",
			"",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\10\37\1\u00b3\21\37",
			"",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\u00b5\7\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\u00b7\7\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u00b9\25\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\u00bc\7\37",
			"\1\uffff",
			"\1\u00be\20\uffff\1\u00bf",
			"\1\uffff",
			"\1\u00c0",
			"\1\u00c1\12\uffff\1\u00c2",
			"",
			"\1\u00c3\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\u00c5\7\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\10\37\1\u00c7\21\37",
			"\1\uffff",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u00c9\25\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\23\37\1\u00cb\6\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\3\37\1\u00cd\26\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\7\37\1\u00cf\22\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\7\37\1\u00d1\22\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\u00d3\7\37",
			"\1\uffff",
			"\1\uffff",
			"\1\u00d5\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"",
			"",
			"\1\u00d7",
			"\1\u00d8",
			"\1\u00d9",
			"\1\u00da",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u00db\25\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\15\37\1\u00dd\14\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\4\37\1\u00e1\25\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\1\u00e5\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\1\u00e7\13\uffff\1\u00e8",
			"\1\uffff",
			"\1\u00e9",
			"\1\u00ea",
			"\1\u00eb",
			"\1\u00ec",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\22\37\1\u00ed\7\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\6\37\1\u00ef\23\37",
			"\1\uffff",
			"\1\uffff",
			"",
			"\1\u00f1\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"",
			"",
			"\1\u00f3\20\uffff\1\u00f4",
			"\1\uffff",
			"",
			"\1\u00f5\11\uffff\1\u00f6",
			"\1\u00f7",
			"\1\u00f8",
			"\1\u00f9",
			"\1\u00fa\3\uffff\1\u00fb",
			"\1\u00fc\17\uffff\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"\12\37\7\uffff\32\37\4\uffff\1\32\1\uffff\32\37",
			"\1\uffff",
			"",
			"\1\uffff",
			"\1\u00ff",
			"\1\u0100",
			"",
			"",
			"\1\u0101\13\uffff\1\u0102",
			"\1\u0103",
			"\1\u0104\13\uffff\1\u0105",
			"\1\u0106",
			"\1\u0107",
			"\1\u0108\20\uffff\1\u0109",
			"\1\uffff",
			"",
			"\1\u010a",
			"\1\u010b",
			"",
			"",
			"\1\u010c",
			"",
			"",
			"\1\u010d",
			"\1\u010e",
			"",
			"",
			"\1\u010f",
			"\1\u0110\13\uffff\1\u0111",
			"\1\u0112\15\uffff\1\u0113",
			"\1\u0114",
			"\1\u0115",
			"\1\u0116",
			"",
			"",
			"",
			"",
			"\1\u0117",
			"\1\u0118",
			"\1\u0119\13\uffff\1\u011a",
			"\1\u011b",
			"\1\u011c",
			"",
			"",
			"\1\u011d",
			"\1\u011e\3\uffff\1\u011f",
			"\1\u0120\3\uffff\1\u0121",
			"",
			"",
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
			return "1:1: Tokens : ( AND | CLOSEPARENTEHSIS | COMMA | DAYS | LENIENT | NOT | OPENPARENTEHSIS | OR | PERCENT | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | Operation | NumberToken | StringToken | HistoricalData | WhiteChar | Tcheat | WS | COMMENT | LINE_COMMENT );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA13_5 = input.LA(1);
						 
						int index13_5 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_5=='e') ) {s = 34;}
						else if ( (LA13_5=='o') ) {s = 35;}
						else if ( ((LA13_5 >= '0' && LA13_5 <= '9')||(LA13_5 >= 'A' && LA13_5 <= 'Z')||(LA13_5 >= 'a' && LA13_5 <= 'd')||(LA13_5 >= 'f' && LA13_5 <= 'n')||(LA13_5 >= 'p' && LA13_5 <= 'z')) ) {s = 31;}
						else if ( (LA13_5=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_5);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA13_16 = input.LA(1);
						 
						int index13_16 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_16=='a') ) {s = 48;}
						else if ( (LA13_16=='o') ) {s = 49;}
						else if ( ((LA13_16 >= '0' && LA13_16 <= '9')||(LA13_16 >= 'A' && LA13_16 <= 'Z')||(LA13_16 >= 'b' && LA13_16 <= 'n')||(LA13_16 >= 'p' && LA13_16 <= 'z')) ) {s = 31;}
						else if ( (LA13_16=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_16);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA13_207 = input.LA(1);
						 
						int index13_207 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_207 >= '0' && LA13_207 <= '9')||(LA13_207 >= 'A' && LA13_207 <= 'Z')||(LA13_207 >= 'a' && LA13_207 <= 'z')) ) {s = 31;}
						else if ( (LA13_207=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 227;
						 
						input.seek(index13_207);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA13_209 = input.LA(1);
						 
						int index13_209 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_209 >= '0' && LA13_209 <= '9')||(LA13_209 >= 'A' && LA13_209 <= 'Z')||(LA13_209 >= 'a' && LA13_209 <= 'z')) ) {s = 31;}
						else if ( (LA13_209=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 228;
						 
						input.seek(index13_209);
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA13_47 = input.LA(1);
						 
						int index13_47 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_47==' ') ) {s = 88;}
						else if ( ((LA13_47 >= '0' && LA13_47 <= '9')||(LA13_47 >= 'A' && LA13_47 <= 'Z')||(LA13_47 >= 'a' && LA13_47 <= 'z')) ) {s = 31;}
						else if ( (LA13_47=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 89;
						 
						input.seek(index13_47);
						if ( s>=0 ) return s;
						break;

					case 5 : 
						int LA13_29 = input.LA(1);
						 
						int index13_29 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_29=='d') ) {s = 56;}
						else if ( ((LA13_29 >= '0' && LA13_29 <= '9')||(LA13_29 >= 'A' && LA13_29 <= 'Z')||(LA13_29 >= 'a' && LA13_29 <= 'c')||(LA13_29 >= 'e' && LA13_29 <= 'z')) ) {s = 31;}
						else if ( (LA13_29=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 57;
						 
						input.seek(index13_29);
						if ( s>=0 ) return s;
						break;

					case 6 : 
						int LA13_33 = input.LA(1);
						 
						int index13_33 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_33=='y') ) {s = 61;}
						else if ( ((LA13_33 >= '0' && LA13_33 <= '9')||(LA13_33 >= 'A' && LA13_33 <= 'Z')||(LA13_33 >= 'a' && LA13_33 <= 'x')||LA13_33=='z') ) {s = 31;}
						else if ( (LA13_33=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 62;
						 
						input.seek(index13_33);
						if ( s>=0 ) return s;
						break;

					case 7 : 
						int LA13_61 = input.LA(1);
						 
						int index13_61 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_61=='s') ) {s = 105;}
						else if ( ((LA13_61 >= '0' && LA13_61 <= '9')||(LA13_61 >= 'A' && LA13_61 <= 'Z')||(LA13_61 >= 'a' && LA13_61 <= 'r')||(LA13_61 >= 't' && LA13_61 <= 'z')) ) {s = 31;}
						else if ( (LA13_61=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 106;
						 
						input.seek(index13_61);
						if ( s>=0 ) return s;
						break;

					case 8 : 
						int LA13_97 = input.LA(1);
						 
						int index13_97 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_97);
						if ( s>=0 ) return s;
						break;

					case 9 : 
						int LA13_60 = input.LA(1);
						 
						int index13_60 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_60);
						if ( s>=0 ) return s;
						break;

					case 10 : 
						int LA13_57 = input.LA(1);
						 
						int index13_57 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_57);
						if ( s>=0 ) return s;
						break;

					case 11 : 
						int LA13_214 = input.LA(1);
						 
						int index13_214 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_214);
						if ( s>=0 ) return s;
						break;

					case 12 : 
						int LA13_154 = input.LA(1);
						 
						int index13_154 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_154);
						if ( s>=0 ) return s;
						break;

					case 13 : 
						int LA13_75 = input.LA(1);
						 
						int index13_75 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_75);
						if ( s>=0 ) return s;
						break;

					case 14 : 
						int LA13_230 = input.LA(1);
						 
						int index13_230 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_230);
						if ( s>=0 ) return s;
						break;

					case 15 : 
						int LA13_167 = input.LA(1);
						 
						int index13_167 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_167);
						if ( s>=0 ) return s;
						break;

					case 16 : 
						int LA13_81 = input.LA(1);
						 
						int index13_81 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_81);
						if ( s>=0 ) return s;
						break;

					case 17 : 
						int LA13_59 = input.LA(1);
						 
						int index13_59 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_59);
						if ( s>=0 ) return s;
						break;

					case 18 : 
						int LA13_83 = input.LA(1);
						 
						int index13_83 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_83);
						if ( s>=0 ) return s;
						break;

					case 19 : 
						int LA13_171 = input.LA(1);
						 
						int index13_171 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_171);
						if ( s>=0 ) return s;
						break;

					case 20 : 
						int LA13_186 = input.LA(1);
						 
						int index13_186 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_186);
						if ( s>=0 ) return s;
						break;

					case 21 : 
						int LA13_143 = input.LA(1);
						 
						int index13_143 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_143);
						if ( s>=0 ) return s;
						break;

					case 22 : 
						int LA13_202 = input.LA(1);
						 
						int index13_202 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_202);
						if ( s>=0 ) return s;
						break;

					case 23 : 
						int LA13_196 = input.LA(1);
						 
						int index13_196 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_196);
						if ( s>=0 ) return s;
						break;

					case 24 : 
						int LA13_139 = input.LA(1);
						 
						int index13_139 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_139);
						if ( s>=0 ) return s;
						break;

					case 25 : 
						int LA13_62 = input.LA(1);
						 
						int index13_62 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_62);
						if ( s>=0 ) return s;
						break;

					case 26 : 
						int LA13_146 = input.LA(1);
						 
						int index13_146 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_146);
						if ( s>=0 ) return s;
						break;

					case 27 : 
						int LA13_176 = input.LA(1);
						 
						int index13_176 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_176);
						if ( s>=0 ) return s;
						break;

					case 28 : 
						int LA13_178 = input.LA(1);
						 
						int index13_178 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_178);
						if ( s>=0 ) return s;
						break;

					case 29 : 
						int LA13_212 = input.LA(1);
						 
						int index13_212 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_212);
						if ( s>=0 ) return s;
						break;

					case 30 : 
						int LA13_173 = input.LA(1);
						 
						int index13_173 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_173);
						if ( s>=0 ) return s;
						break;

					case 31 : 
						int LA13_200 = input.LA(1);
						 
						int index13_200 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_200);
						if ( s>=0 ) return s;
						break;

					case 32 : 
						int LA13_238 = input.LA(1);
						 
						int index13_238 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_238);
						if ( s>=0 ) return s;
						break;

					case 33 : 
						int LA13_184 = input.LA(1);
						 
						int index13_184 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_184);
						if ( s>=0 ) return s;
						break;

					case 34 : 
						int LA13_208 = input.LA(1);
						 
						int index13_208 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_208);
						if ( s>=0 ) return s;
						break;

					case 35 : 
						int LA13_210 = input.LA(1);
						 
						int index13_210 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_210);
						if ( s>=0 ) return s;
						break;

					case 36 : 
						int LA13_124 = input.LA(1);
						 
						int index13_124 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_124);
						if ( s>=0 ) return s;
						break;

					case 37 : 
						int LA13_77 = input.LA(1);
						 
						int index13_77 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_77);
						if ( s>=0 ) return s;
						break;

					case 38 : 
						int LA13_108 = input.LA(1);
						 
						int index13_108 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_108);
						if ( s>=0 ) return s;
						break;

					case 39 : 
						int LA13_120 = input.LA(1);
						 
						int index13_120 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_120);
						if ( s>=0 ) return s;
						break;

					case 40 : 
						int LA13_206 = input.LA(1);
						 
						int index13_206 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_206);
						if ( s>=0 ) return s;
						break;

					case 41 : 
						int LA13_189 = input.LA(1);
						 
						int index13_189 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_189);
						if ( s>=0 ) return s;
						break;

					case 42 : 
						int LA13_131 = input.LA(1);
						 
						int index13_131 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_131);
						if ( s>=0 ) return s;
						break;

					case 43 : 
						int LA13_240 = input.LA(1);
						 
						int index13_240 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_240);
						if ( s>=0 ) return s;
						break;

					case 44 : 
						int LA13_133 = input.LA(1);
						 
						int index13_133 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_133);
						if ( s>=0 ) return s;
						break;

					case 45 : 
						int LA13_116 = input.LA(1);
						 
						int index13_116 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_116);
						if ( s>=0 ) return s;
						break;

					case 46 : 
						int LA13_64 = input.LA(1);
						 
						int index13_64 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_64);
						if ( s>=0 ) return s;
						break;

					case 47 : 
						int LA13_66 = input.LA(1);
						 
						int index13_66 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_66);
						if ( s>=0 ) return s;
						break;

					case 48 : 
						int LA13_93 = input.LA(1);
						 
						int index13_93 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_93);
						if ( s>=0 ) return s;
						break;

					case 49 : 
						int LA13_106 = input.LA(1);
						 
						int index13_106 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_106);
						if ( s>=0 ) return s;
						break;

					case 50 : 
						int LA13_152 = input.LA(1);
						 
						int index13_152 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_152);
						if ( s>=0 ) return s;
						break;

					case 51 : 
						int LA13_180 = input.LA(1);
						 
						int index13_180 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_180);
						if ( s>=0 ) return s;
						break;

					case 52 : 
						int LA13_198 = input.LA(1);
						 
						int index13_198 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_198);
						if ( s>=0 ) return s;
						break;

					case 53 : 
						int LA13_226 = input.LA(1);
						 
						int index13_226 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_226);
						if ( s>=0 ) return s;
						break;

					case 54 : 
						int LA13_68 = input.LA(1);
						 
						int index13_68 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_68);
						if ( s>=0 ) return s;
						break;

					case 55 : 
						int LA13_158 = input.LA(1);
						 
						int index13_158 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_158);
						if ( s>=0 ) return s;
						break;

					case 56 : 
						int LA13_99 = input.LA(1);
						 
						int index13_99 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_99);
						if ( s>=0 ) return s;
						break;

					case 57 : 
						int LA13_204 = input.LA(1);
						 
						int index13_204 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_204);
						if ( s>=0 ) return s;
						break;

					case 58 : 
						int LA13_122 = input.LA(1);
						 
						int index13_122 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_122);
						if ( s>=0 ) return s;
						break;

					case 59 : 
						int LA13_85 = input.LA(1);
						 
						int index13_85 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_85);
						if ( s>=0 ) return s;
						break;

					case 60 : 
						int LA13_71 = input.LA(1);
						 
						int index13_71 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_71);
						if ( s>=0 ) return s;
						break;

					case 61 : 
						int LA13_73 = input.LA(1);
						 
						int index13_73 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_73);
						if ( s>=0 ) return s;
						break;

					case 62 : 
						int LA13_95 = input.LA(1);
						 
						int index13_95 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_95);
						if ( s>=0 ) return s;
						break;

					case 63 : 
						int LA13_127 = input.LA(1);
						 
						int index13_127 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_127);
						if ( s>=0 ) return s;
						break;

					case 64 : 
						int LA13_135 = input.LA(1);
						 
						int index13_135 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_135);
						if ( s>=0 ) return s;
						break;

					case 65 : 
						int LA13_182 = input.LA(1);
						 
						int index13_182 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_182);
						if ( s>=0 ) return s;
						break;

					case 66 : 
						int LA13_112 = input.LA(1);
						 
						int index13_112 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_112);
						if ( s>=0 ) return s;
						break;

					case 67 : 
						int LA13_222 = input.LA(1);
						 
						int index13_222 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_222);
						if ( s>=0 ) return s;
						break;

					case 68 : 
						int LA13_162 = input.LA(1);
						 
						int index13_162 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_162);
						if ( s>=0 ) return s;
						break;

					case 69 : 
						int LA13_141 = input.LA(1);
						 
						int index13_141 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_141);
						if ( s>=0 ) return s;
						break;

					case 70 : 
						int LA13_156 = input.LA(1);
						 
						int index13_156 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_156);
						if ( s>=0 ) return s;
						break;

					case 71 : 
						int LA13_91 = input.LA(1);
						 
						int index13_91 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_91);
						if ( s>=0 ) return s;
						break;

					case 72 : 
						int LA13_114 = input.LA(1);
						 
						int index13_114 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_114);
						if ( s>=0 ) return s;
						break;

					case 73 : 
						int LA13_220 = input.LA(1);
						 
						int index13_220 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_220);
						if ( s>=0 ) return s;
						break;

					case 74 : 
						int LA13_104 = input.LA(1);
						 
						int index13_104 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_104);
						if ( s>=0 ) return s;
						break;

					case 75 : 
						int LA13_87 = input.LA(1);
						 
						int index13_87 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_87);
						if ( s>=0 ) return s;
						break;

					case 76 : 
						int LA13_118 = input.LA(1);
						 
						int index13_118 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_118);
						if ( s>=0 ) return s;
						break;

					case 77 : 
						int LA13_101 = input.LA(1);
						 
						int index13_101 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_101);
						if ( s>=0 ) return s;
						break;

					case 78 : 
						int LA13_253 = input.LA(1);
						 
						int index13_253 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_253);
						if ( s>=0 ) return s;
						break;

					case 79 : 
						int LA13_242 = input.LA(1);
						 
						int index13_242 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_242);
						if ( s>=0 ) return s;
						break;

					case 80 : 
						int LA13_137 = input.LA(1);
						 
						int index13_137 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_137);
						if ( s>=0 ) return s;
						break;

					case 81 : 
						int LA13_169 = input.LA(1);
						 
						int index13_169 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_169);
						if ( s>=0 ) return s;
						break;

					case 82 : 
						int LA13_160 = input.LA(1);
						 
						int index13_160 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_160);
						if ( s>=0 ) return s;
						break;

					case 83 : 
						int LA13_89 = input.LA(1);
						 
						int index13_89 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_89);
						if ( s>=0 ) return s;
						break;

					case 84 : 
						int LA13_79 = input.LA(1);
						 
						int index13_79 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_79);
						if ( s>=0 ) return s;
						break;

					case 85 : 
						int LA13_34 = input.LA(1);
						 
						int index13_34 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_34=='n') ) {s = 63;}
						else if ( ((LA13_34 >= '0' && LA13_34 <= '9')||(LA13_34 >= 'A' && LA13_34 <= 'Z')||(LA13_34 >= 'a' && LA13_34 <= 'm')||(LA13_34 >= 'o' && LA13_34 <= 'z')) ) {s = 31;}
						else if ( (LA13_34=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 64;
						 
						input.seek(index13_34);
						if ( s>=0 ) return s;
						break;

					case 86 : 
						int LA13_63 = input.LA(1);
						 
						int index13_63 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_63=='i') ) {s = 107;}
						else if ( ((LA13_63 >= '0' && LA13_63 <= '9')||(LA13_63 >= 'A' && LA13_63 <= 'Z')||(LA13_63 >= 'a' && LA13_63 <= 'h')||(LA13_63 >= 'j' && LA13_63 <= 'z')) ) {s = 31;}
						else if ( (LA13_63=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 108;
						 
						input.seek(index13_63);
						if ( s>=0 ) return s;
						break;

					case 87 : 
						int LA13_107 = input.LA(1);
						 
						int index13_107 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_107=='e') ) {s = 145;}
						else if ( ((LA13_107 >= '0' && LA13_107 <= '9')||(LA13_107 >= 'A' && LA13_107 <= 'Z')||(LA13_107 >= 'a' && LA13_107 <= 'd')||(LA13_107 >= 'f' && LA13_107 <= 'z')) ) {s = 31;}
						else if ( (LA13_107=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 146;
						 
						input.seek(index13_107);
						if ( s>=0 ) return s;
						break;

					case 88 : 
						int LA13_145 = input.LA(1);
						 
						int index13_145 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_145=='n') ) {s = 177;}
						else if ( ((LA13_145 >= '0' && LA13_145 <= '9')||(LA13_145 >= 'A' && LA13_145 <= 'Z')||(LA13_145 >= 'a' && LA13_145 <= 'm')||(LA13_145 >= 'o' && LA13_145 <= 'z')) ) {s = 31;}
						else if ( (LA13_145=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 178;
						 
						input.seek(index13_145);
						if ( s>=0 ) return s;
						break;

					case 89 : 
						int LA13_177 = input.LA(1);
						 
						int index13_177 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_177=='t') ) {s = 203;}
						else if ( ((LA13_177 >= '0' && LA13_177 <= '9')||(LA13_177 >= 'A' && LA13_177 <= 'Z')||(LA13_177 >= 'a' && LA13_177 <= 's')||(LA13_177 >= 'u' && LA13_177 <= 'z')) ) {s = 31;}
						else if ( (LA13_177=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 204;
						 
						input.seek(index13_177);
						if ( s>=0 ) return s;
						break;

					case 90 : 
						int LA13_36 = input.LA(1);
						 
						int index13_36 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_36=='t') ) {s = 67;}
						else if ( ((LA13_36 >= '0' && LA13_36 <= '9')||(LA13_36 >= 'A' && LA13_36 <= 'Z')||(LA13_36 >= 'a' && LA13_36 <= 's')||(LA13_36 >= 'u' && LA13_36 <= 'z')) ) {s = 31;}
						else if ( (LA13_36=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 68;
						 
						input.seek(index13_36);
						if ( s>=0 ) return s;
						break;

					case 91 : 
						int LA13_24 = input.LA(1);
						 
						int index13_24 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_24 >= '0' && LA13_24 <= '9')||(LA13_24 >= 'A' && LA13_24 <= 'Z')||(LA13_24 >= 'a' && LA13_24 <= 'z')) ) {s = 31;}
						else if ( (LA13_24=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_24);
						if ( s>=0 ) return s;
						break;

					case 92 : 
						int LA13_49 = input.LA(1);
						 
						int index13_49 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_49=='r') ) {s = 92;}
						else if ( ((LA13_49 >= '0' && LA13_49 <= '9')||(LA13_49 >= 'A' && LA13_49 <= 'Z')||(LA13_49 >= 'a' && LA13_49 <= 'q')||(LA13_49 >= 's' && LA13_49 <= 'z')) ) {s = 31;}
						else if ( (LA13_49=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 93;
						 
						input.seek(index13_49);
						if ( s>=0 ) return s;
						break;

					case 93 : 
						int LA13_92 = input.LA(1);
						 
						int index13_92 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_92=='e') ) {s = 132;}
						else if ( ((LA13_92 >= '0' && LA13_92 <= '9')||(LA13_92 >= 'A' && LA13_92 <= 'Z')||(LA13_92 >= 'a' && LA13_92 <= 'd')||(LA13_92 >= 'f' && LA13_92 <= 'z')) ) {s = 31;}
						else if ( (LA13_92=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 133;
						 
						input.seek(index13_92);
						if ( s>=0 ) return s;
						break;

					case 94 : 
						int LA13_132 = input.LA(1);
						 
						int index13_132 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_132==' ') ) {s = 168;}
						else if ( ((LA13_132 >= '0' && LA13_132 <= '9')||(LA13_132 >= 'A' && LA13_132 <= 'Z')||(LA13_132 >= 'a' && LA13_132 <= 'z')) ) {s = 31;}
						else if ( (LA13_132=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 169;
						 
						input.seek(index13_132);
						if ( s>=0 ) return s;
						break;

					case 95 : 
						int LA13_30 = input.LA(1);
						 
						int index13_30 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_30=='s') ) {s = 58;}
						else if ( ((LA13_30 >= '0' && LA13_30 <= '9')||(LA13_30 >= 'A' && LA13_30 <= 'Z')||(LA13_30 >= 'a' && LA13_30 <= 'r')||(LA13_30 >= 't' && LA13_30 <= 'z')) ) {s = 31;}
						else if ( (LA13_30=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 59;
						 
						input.seek(index13_30);
						if ( s>=0 ) return s;
						break;

					case 96 : 
						int LA13_58 = input.LA(1);
						 
						int index13_58 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_58=='o') ) {s = 103;}
						else if ( ((LA13_58 >= '0' && LA13_58 <= '9')||(LA13_58 >= 'A' && LA13_58 <= 'Z')||(LA13_58 >= 'a' && LA13_58 <= 'n')||(LA13_58 >= 'p' && LA13_58 <= 'z')) ) {s = 31;}
						else if ( (LA13_58=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 104;
						 
						input.seek(index13_58);
						if ( s>=0 ) return s;
						break;

					case 97 : 
						int LA13_103 = input.LA(1);
						 
						int index13_103 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_103==' ') ) {s = 142;}
						else if ( ((LA13_103 >= '0' && LA13_103 <= '9')||(LA13_103 >= 'A' && LA13_103 <= 'Z')||(LA13_103 >= 'a' && LA13_103 <= 'z')) ) {s = 31;}
						else if ( (LA13_103=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 143;
						 
						input.seek(index13_103);
						if ( s>=0 ) return s;
						break;

					case 98 : 
						int LA13_109 = input.LA(1);
						 
						int index13_109 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 147;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_109);
						if ( s>=0 ) return s;
						break;

					case 99 : 
						int LA13_150 = input.LA(1);
						 
						int index13_150 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 147;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_150);
						if ( s>=0 ) return s;
						break;

					case 100 : 
						int LA13_174 = input.LA(1);
						 
						int index13_174 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 147;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_174);
						if ( s>=0 ) return s;
						break;

					case 101 : 
						int LA13_187 = input.LA(1);
						 
						int index13_187 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 147;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_187);
						if ( s>=0 ) return s;
						break;

					case 102 : 
						int LA13_223 = input.LA(1);
						 
						int index13_223 = input.index();
						input.rewind();
						s = -1;
						if ( ((runtimeOpAhead())) ) {s = 26;}
						else if ( ((runtimeHistoryOpAhead())) ) {s = 147;}
						else if ( (true) ) {s = 32;}
						 
						input.seek(index13_223);
						if ( s>=0 ) return s;
						break;

					case 103 : 
						int LA13_40 = input.LA(1);
						 
						int index13_40 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_40=='a') ) {s = 74;}
						else if ( ((LA13_40 >= '0' && LA13_40 <= '9')||(LA13_40 >= 'A' && LA13_40 <= 'Z')||(LA13_40 >= 'b' && LA13_40 <= 'z')) ) {s = 31;}
						else if ( (LA13_40=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 75;
						 
						input.seek(index13_40);
						if ( s>=0 ) return s;
						break;

					case 104 : 
						int LA13_148 = input.LA(1);
						 
						int index13_148 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_148=='i') ) {s = 179;}
						else if ( ((LA13_148 >= '0' && LA13_148 <= '9')||(LA13_148 >= 'A' && LA13_148 <= 'Z')||(LA13_148 >= 'a' && LA13_148 <= 'h')||(LA13_148 >= 'j' && LA13_148 <= 'z')) ) {s = 31;}
						else if ( (LA13_148=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 180;
						 
						input.seek(index13_148);
						if ( s>=0 ) return s;
						break;

					case 105 : 
						int LA13_74 = input.LA(1);
						 
						int index13_74 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_74=='r') ) {s = 115;}
						else if ( ((LA13_74 >= '0' && LA13_74 <= '9')||(LA13_74 >= 'A' && LA13_74 <= 'Z')||(LA13_74 >= 'a' && LA13_74 <= 'q')||(LA13_74 >= 's' && LA13_74 <= 'z')) ) {s = 31;}
						else if ( (LA13_74=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 116;
						 
						input.seek(index13_74);
						if ( s>=0 ) return s;
						break;

					case 106 : 
						int LA13_179 = input.LA(1);
						 
						int index13_179 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_179=='d') ) {s = 205;}
						else if ( ((LA13_179 >= '0' && LA13_179 <= '9')||(LA13_179 >= 'A' && LA13_179 <= 'Z')||(LA13_179 >= 'a' && LA13_179 <= 'c')||(LA13_179 >= 'e' && LA13_179 <= 'z')) ) {s = 31;}
						else if ( (LA13_179=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 206;
						 
						input.seek(index13_179);
						if ( s>=0 ) return s;
						break;

					case 107 : 
						int LA13_115 = input.LA(1);
						 
						int index13_115 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_115=='i') ) {s = 151;}
						else if ( ((LA13_115 >= '0' && LA13_115 <= '9')||(LA13_115 >= 'A' && LA13_115 <= 'Z')||(LA13_115 >= 'a' && LA13_115 <= 'h')||(LA13_115 >= 'j' && LA13_115 <= 'z')) ) {s = 31;}
						else if ( (LA13_115=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 152;
						 
						input.seek(index13_115);
						if ( s>=0 ) return s;
						break;

					case 108 : 
						int LA13_205 = input.LA(1);
						 
						int index13_205 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_205=='e') ) {s = 225;}
						else if ( ((LA13_205 >= '0' && LA13_205 <= '9')||(LA13_205 >= 'A' && LA13_205 <= 'Z')||(LA13_205 >= 'a' && LA13_205 <= 'd')||(LA13_205 >= 'f' && LA13_205 <= 'z')) ) {s = 31;}
						else if ( (LA13_205=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 226;
						 
						input.seek(index13_205);
						if ( s>=0 ) return s;
						break;

					case 109 : 
						int LA13_151 = input.LA(1);
						 
						int index13_151 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_151=='s') ) {s = 181;}
						else if ( ((LA13_151 >= '0' && LA13_151 <= '9')||(LA13_151 >= 'A' && LA13_151 <= 'Z')||(LA13_151 >= 'a' && LA13_151 <= 'r')||(LA13_151 >= 't' && LA13_151 <= 'z')) ) {s = 31;}
						else if ( (LA13_151=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 182;
						 
						input.seek(index13_151);
						if ( s>=0 ) return s;
						break;

					case 110 : 
						int LA13_225 = input.LA(1);
						 
						int index13_225 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_225==' ') ) {s = 241;}
						else if ( ((LA13_225 >= '0' && LA13_225 <= '9')||(LA13_225 >= 'A' && LA13_225 <= 'Z')||(LA13_225 >= 'a' && LA13_225 <= 'z')) ) {s = 31;}
						else if ( (LA13_225=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 242;
						 
						input.seek(index13_225);
						if ( s>=0 ) return s;
						break;

					case 111 : 
						int LA13_181 = input.LA(1);
						 
						int index13_181 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_181=='h') ) {s = 207;}
						else if ( ((LA13_181 >= '0' && LA13_181 <= '9')||(LA13_181 >= 'A' && LA13_181 <= 'Z')||(LA13_181 >= 'a' && LA13_181 <= 'g')||(LA13_181 >= 'i' && LA13_181 <= 'z')) ) {s = 31;}
						else if ( (LA13_181=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 208;
						 
						input.seek(index13_181);
						if ( s>=0 ) return s;
						break;

					case 112 : 
						int LA13_41 = input.LA(1);
						 
						int index13_41 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_41=='l') ) {s = 76;}
						else if ( ((LA13_41 >= '0' && LA13_41 <= '9')||(LA13_41 >= 'A' && LA13_41 <= 'Z')||(LA13_41 >= 'a' && LA13_41 <= 'k')||(LA13_41 >= 'm' && LA13_41 <= 'z')) ) {s = 31;}
						else if ( (LA13_41=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 77;
						 
						input.seek(index13_41);
						if ( s>=0 ) return s;
						break;

					case 113 : 
						int LA13_76 = input.LA(1);
						 
						int index13_76 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_76=='l') ) {s = 117;}
						else if ( ((LA13_76 >= '0' && LA13_76 <= '9')||(LA13_76 >= 'A' && LA13_76 <= 'Z')||(LA13_76 >= 'a' && LA13_76 <= 'k')||(LA13_76 >= 'm' && LA13_76 <= 'z')) ) {s = 31;}
						else if ( (LA13_76=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 118;
						 
						input.seek(index13_76);
						if ( s>=0 ) return s;
						break;

					case 114 : 
						int LA13_117 = input.LA(1);
						 
						int index13_117 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_117=='i') ) {s = 153;}
						else if ( ((LA13_117 >= '0' && LA13_117 <= '9')||(LA13_117 >= 'A' && LA13_117 <= 'Z')||(LA13_117 >= 'a' && LA13_117 <= 'h')||(LA13_117 >= 'j' && LA13_117 <= 'z')) ) {s = 31;}
						else if ( (LA13_117=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 154;
						 
						input.seek(index13_117);
						if ( s>=0 ) return s;
						break;

					case 115 : 
						int LA13_153 = input.LA(1);
						 
						int index13_153 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_153=='s') ) {s = 183;}
						else if ( ((LA13_153 >= '0' && LA13_153 <= '9')||(LA13_153 >= 'A' && LA13_153 <= 'Z')||(LA13_153 >= 'a' && LA13_153 <= 'r')||(LA13_153 >= 't' && LA13_153 <= 'z')) ) {s = 31;}
						else if ( (LA13_153=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 184;
						 
						input.seek(index13_153);
						if ( s>=0 ) return s;
						break;

					case 116 : 
						int LA13_183 = input.LA(1);
						 
						int index13_183 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_183=='h') ) {s = 209;}
						else if ( ((LA13_183 >= '0' && LA13_183 <= '9')||(LA13_183 >= 'A' && LA13_183 <= 'Z')||(LA13_183 >= 'a' && LA13_183 <= 'g')||(LA13_183 >= 'i' && LA13_183 <= 'z')) ) {s = 31;}
						else if ( (LA13_183=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 210;
						 
						input.seek(index13_183);
						if ( s>=0 ) return s;
						break;

					case 117 : 
						int LA13_46 = input.LA(1);
						 
						int index13_46 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_46=='e') ) {s = 86;}
						else if ( ((LA13_46 >= '0' && LA13_46 <= '9')||(LA13_46 >= 'A' && LA13_46 <= 'Z')||(LA13_46 >= 'a' && LA13_46 <= 'd')||(LA13_46 >= 'f' && LA13_46 <= 'z')) ) {s = 31;}
						else if ( (LA13_46=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 87;
						 
						input.seek(index13_46);
						if ( s>=0 ) return s;
						break;

					case 118 : 
						int LA13_86 = input.LA(1);
						 
						int index13_86 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_86=='s') ) {s = 126;}
						else if ( ((LA13_86 >= '0' && LA13_86 <= '9')||(LA13_86 >= 'A' && LA13_86 <= 'Z')||(LA13_86 >= 'a' && LA13_86 <= 'r')||(LA13_86 >= 't' && LA13_86 <= 'z')) ) {s = 31;}
						else if ( (LA13_86=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 127;
						 
						input.seek(index13_86);
						if ( s>=0 ) return s;
						break;

					case 119 : 
						int LA13_126 = input.LA(1);
						 
						int index13_126 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_126==' ') ) {s = 161;}
						else if ( ((LA13_126 >= '0' && LA13_126 <= '9')||(LA13_126 >= 'A' && LA13_126 <= 'Z')||(LA13_126 >= 'a' && LA13_126 <= 'z')) ) {s = 31;}
						else if ( (LA13_126=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 162;
						 
						input.seek(index13_126);
						if ( s>=0 ) return s;
						break;

					case 120 : 
						int LA13_51 = input.LA(1);
						 
						int index13_51 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_51=='a') ) {s = 96;}
						else if ( ((LA13_51 >= '0' && LA13_51 <= '9')||(LA13_51 >= 'A' && LA13_51 <= 'Z')||(LA13_51 >= 'b' && LA13_51 <= 'z')) ) {s = 31;}
						else if ( (LA13_51=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 97;
						 
						input.seek(index13_51);
						if ( s>=0 ) return s;
						break;

					case 121 : 
						int LA13_96 = input.LA(1);
						 
						int index13_96 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_96=='n') ) {s = 136;}
						else if ( ((LA13_96 >= '0' && LA13_96 <= '9')||(LA13_96 >= 'A' && LA13_96 <= 'Z')||(LA13_96 >= 'a' && LA13_96 <= 'm')||(LA13_96 >= 'o' && LA13_96 <= 'z')) ) {s = 31;}
						else if ( (LA13_96=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 137;
						 
						input.seek(index13_96);
						if ( s>=0 ) return s;
						break;

					case 122 : 
						int LA13_136 = input.LA(1);
						 
						int index13_136 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_136=='n') ) {s = 172;}
						else if ( ((LA13_136 >= '0' && LA13_136 <= '9')||(LA13_136 >= 'A' && LA13_136 <= 'Z')||(LA13_136 >= 'a' && LA13_136 <= 'm')||(LA13_136 >= 'o' && LA13_136 <= 'z')) ) {s = 31;}
						else if ( (LA13_136=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 173;
						 
						input.seek(index13_136);
						if ( s>=0 ) return s;
						break;

					case 123 : 
						int LA13_172 = input.LA(1);
						 
						int index13_172 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_172=='i') ) {s = 199;}
						else if ( ((LA13_172 >= '0' && LA13_172 <= '9')||(LA13_172 >= 'A' && LA13_172 <= 'Z')||(LA13_172 >= 'a' && LA13_172 <= 'h')||(LA13_172 >= 'j' && LA13_172 <= 'z')) ) {s = 31;}
						else if ( (LA13_172=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 200;
						 
						input.seek(index13_172);
						if ( s>=0 ) return s;
						break;

					case 124 : 
						int LA13_199 = input.LA(1);
						 
						int index13_199 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_199=='n') ) {s = 221;}
						else if ( ((LA13_199 >= '0' && LA13_199 <= '9')||(LA13_199 >= 'A' && LA13_199 <= 'Z')||(LA13_199 >= 'a' && LA13_199 <= 'm')||(LA13_199 >= 'o' && LA13_199 <= 'z')) ) {s = 31;}
						else if ( (LA13_199=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 222;
						 
						input.seek(index13_199);
						if ( s>=0 ) return s;
						break;

					case 125 : 
						int LA13_221 = input.LA(1);
						 
						int index13_221 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_221=='g') ) {s = 239;}
						else if ( ((LA13_221 >= '0' && LA13_221 <= '9')||(LA13_221 >= 'A' && LA13_221 <= 'Z')||(LA13_221 >= 'a' && LA13_221 <= 'f')||(LA13_221 >= 'h' && LA13_221 <= 'z')) ) {s = 31;}
						else if ( (LA13_221=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 240;
						 
						input.seek(index13_221);
						if ( s>=0 ) return s;
						break;

					case 126 : 
						int LA13_113 = input.LA(1);
						 
						int index13_113 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_113 >= '0' && LA13_113 <= '9')||(LA13_113 >= 'A' && LA13_113 <= 'Z')||(LA13_113 >= 'a' && LA13_113 <= 'z')) ) {s = 31;}
						else if ( (LA13_113=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 150;
						 
						input.seek(index13_113);
						if ( s>=0 ) return s;
						break;

					case 127 : 
						int LA13_15 = input.LA(1);
						 
						int index13_15 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_15=='s') ) {s = 47;}
						else if ( ((LA13_15 >= '0' && LA13_15 <= '9')||(LA13_15 >= 'A' && LA13_15 <= 'Z')||(LA13_15 >= 'a' && LA13_15 <= 'r')||(LA13_15 >= 't' && LA13_15 <= 'z')) ) {s = 31;}
						else if ( (LA13_15=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_15);
						if ( s>=0 ) return s;
						break;

					case 128 : 
						int LA13_157 = input.LA(1);
						 
						int index13_157 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_157 >= '0' && LA13_157 <= '9')||(LA13_157 >= 'A' && LA13_157 <= 'Z')||(LA13_157 >= 'a' && LA13_157 <= 'z')) ) {s = 31;}
						else if ( (LA13_157=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 187;
						 
						input.seek(index13_157);
						if ( s>=0 ) return s;
						break;

					case 129 : 
						int LA13_138 = input.LA(1);
						 
						int index13_138 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_138 >= '0' && LA13_138 <= '9')||(LA13_138 >= 'A' && LA13_138 <= 'Z')||(LA13_138 >= 'a' && LA13_138 <= 'z')) ) {s = 31;}
						else if ( (LA13_138=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 174;
						 
						input.seek(index13_138);
						if ( s>=0 ) return s;
						break;

					case 130 : 
						int LA13_65 = input.LA(1);
						 
						int index13_65 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_65 >= '0' && LA13_65 <= '9')||(LA13_65 >= 'A' && LA13_65 <= 'Z')||(LA13_65 >= 'a' && LA13_65 <= 'z')) ) {s = 31;}
						else if ( (LA13_65=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 109;
						 
						input.seek(index13_65);
						if ( s>=0 ) return s;
						break;

					case 131 : 
						int LA13_201 = input.LA(1);
						 
						int index13_201 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_201 >= '0' && LA13_201 <= '9')||(LA13_201 >= 'A' && LA13_201 <= 'Z')||(LA13_201 >= 'a' && LA13_201 <= 'z')) ) {s = 31;}
						else if ( (LA13_201=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 223;
						 
						input.seek(index13_201);
						if ( s>=0 ) return s;
						break;

					case 132 : 
						int LA13_4 = input.LA(1);
						 
						int index13_4 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_4=='a') ) {s = 33;}
						else if ( ((LA13_4 >= '0' && LA13_4 <= '9')||(LA13_4 >= 'A' && LA13_4 <= 'Z')||(LA13_4 >= 'b' && LA13_4 <= 'z')) ) {s = 31;}
						else if ( (LA13_4=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_4);
						if ( s>=0 ) return s;
						break;

					case 133 : 
						int LA13_6 = input.LA(1);
						 
						int index13_6 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_6=='o') ) {s = 36;}
						else if ( ((LA13_6 >= '0' && LA13_6 <= '9')||(LA13_6 >= 'A' && LA13_6 <= 'Z')||(LA13_6 >= 'a' && LA13_6 <= 'n')||(LA13_6 >= 'p' && LA13_6 <= 'z')) ) {s = 31;}
						else if ( (LA13_6=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_6);
						if ( s>=0 ) return s;
						break;

					case 134 : 
						int LA13_39 = input.LA(1);
						 
						int index13_39 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_39=='e') ) {s = 72;}
						else if ( ((LA13_39 >= '0' && LA13_39 <= '9')||(LA13_39 >= 'A' && LA13_39 <= 'Z')||(LA13_39 >= 'a' && LA13_39 <= 'd')||(LA13_39 >= 'f' && LA13_39 <= 'z')) ) {s = 31;}
						else if ( (LA13_39=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 73;
						 
						input.seek(index13_39);
						if ( s>=0 ) return s;
						break;

					case 135 : 
						int LA13_48 = input.LA(1);
						 
						int index13_48 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_48=='k') ) {s = 90;}
						else if ( ((LA13_48 >= '0' && LA13_48 <= '9')||(LA13_48 >= 'A' && LA13_48 <= 'Z')||(LA13_48 >= 'a' && LA13_48 <= 'j')||(LA13_48 >= 'l' && LA13_48 <= 'z')) ) {s = 31;}
						else if ( (LA13_48=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 91;
						 
						input.seek(index13_48);
						if ( s>=0 ) return s;
						break;

					case 136 : 
						int LA13_72 = input.LA(1);
						 
						int index13_72 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_72=='n') ) {s = 113;}
						else if ( ((LA13_72 >= '0' && LA13_72 <= '9')||(LA13_72 >= 'A' && LA13_72 <= 'Z')||(LA13_72 >= 'a' && LA13_72 <= 'm')||(LA13_72 >= 'o' && LA13_72 <= 'z')) ) {s = 31;}
						else if ( (LA13_72=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 114;
						 
						input.seek(index13_72);
						if ( s>=0 ) return s;
						break;

					case 137 : 
						int LA13_90 = input.LA(1);
						 
						int index13_90 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_90=='e') ) {s = 130;}
						else if ( ((LA13_90 >= '0' && LA13_90 <= '9')||(LA13_90 >= 'A' && LA13_90 <= 'Z')||(LA13_90 >= 'a' && LA13_90 <= 'd')||(LA13_90 >= 'f' && LA13_90 <= 'z')) ) {s = 31;}
						else if ( (LA13_90=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 131;
						 
						input.seek(index13_90);
						if ( s>=0 ) return s;
						break;

					case 138 : 
						int LA13_43 = input.LA(1);
						 
						int index13_43 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_43=='o') ) {s = 80;}
						else if ( ((LA13_43 >= '0' && LA13_43 <= '9')||(LA13_43 >= 'A' && LA13_43 <= 'Z')||(LA13_43 >= 'a' && LA13_43 <= 'n')||(LA13_43 >= 'p' && LA13_43 <= 'z')) ) {s = 31;}
						else if ( (LA13_43=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 81;
						 
						input.seek(index13_43);
						if ( s>=0 ) return s;
						break;

					case 139 : 
						int LA13_80 = input.LA(1);
						 
						int index13_80 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_80=='s') ) {s = 121;}
						else if ( ((LA13_80 >= '0' && LA13_80 <= '9')||(LA13_80 >= 'A' && LA13_80 <= 'Z')||(LA13_80 >= 'a' && LA13_80 <= 'r')||(LA13_80 >= 't' && LA13_80 <= 'z')) ) {s = 31;}
						else if ( (LA13_80=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 122;
						 
						input.seek(index13_80);
						if ( s>=0 ) return s;
						break;

					case 140 : 
						int LA13_121 = input.LA(1);
						 
						int index13_121 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_121=='e') ) {s = 157;}
						else if ( ((LA13_121 >= '0' && LA13_121 <= '9')||(LA13_121 >= 'A' && LA13_121 <= 'Z')||(LA13_121 >= 'a' && LA13_121 <= 'd')||(LA13_121 >= 'f' && LA13_121 <= 'z')) ) {s = 31;}
						else if ( (LA13_121=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 158;
						 
						input.seek(index13_121);
						if ( s>=0 ) return s;
						break;

					case 141 : 
						int LA13_130 = input.LA(1);
						 
						int index13_130 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_130=='s') ) {s = 166;}
						else if ( ((LA13_130 >= '0' && LA13_130 <= '9')||(LA13_130 >= 'A' && LA13_130 <= 'Z')||(LA13_130 >= 'a' && LA13_130 <= 'r')||(LA13_130 >= 't' && LA13_130 <= 'z')) ) {s = 31;}
						else if ( (LA13_130=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 167;
						 
						input.seek(index13_130);
						if ( s>=0 ) return s;
						break;

					case 142 : 
						int LA13_52 = input.LA(1);
						 
						int index13_52 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_52=='g') ) {s = 98;}
						else if ( ((LA13_52 >= '0' && LA13_52 <= '9')||(LA13_52 >= 'A' && LA13_52 <= 'Z')||(LA13_52 >= 'a' && LA13_52 <= 'f')||(LA13_52 >= 'h' && LA13_52 <= 'z')) ) {s = 31;}
						else if ( (LA13_52=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 99;
						 
						input.seek(index13_52);
						if ( s>=0 ) return s;
						break;

					case 143 : 
						int LA13_98 = input.LA(1);
						 
						int index13_98 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_98=='h') ) {s = 138;}
						else if ( ((LA13_98 >= '0' && LA13_98 <= '9')||(LA13_98 >= 'A' && LA13_98 <= 'Z')||(LA13_98 >= 'a' && LA13_98 <= 'g')||(LA13_98 >= 'i' && LA13_98 <= 'z')) ) {s = 31;}
						else if ( (LA13_98=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 139;
						 
						input.seek(index13_98);
						if ( s>=0 ) return s;
						break;

					case 144 : 
						int LA13_166 = input.LA(1);
						 
						int index13_166 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_166==' ') ) {s = 195;}
						else if ( ((LA13_166 >= '0' && LA13_166 <= '9')||(LA13_166 >= 'A' && LA13_166 <= 'Z')||(LA13_166 >= 'a' && LA13_166 <= 'z')) ) {s = 31;}
						else if ( (LA13_166=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 196;
						 
						input.seek(index13_166);
						if ( s>=0 ) return s;
						break;

					case 145 : 
						int LA13_11 = input.LA(1);
						 
						int index13_11 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_11=='r') ) {s = 42;}
						else if ( (LA13_11=='l') ) {s = 43;}
						else if ( ((LA13_11 >= '0' && LA13_11 <= '9')||(LA13_11 >= 'A' && LA13_11 <= 'Z')||(LA13_11 >= 'a' && LA13_11 <= 'k')||(LA13_11 >= 'm' && LA13_11 <= 'q')||(LA13_11 >= 's' && LA13_11 <= 'z')) ) {s = 31;}
						else if ( (LA13_11=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_11);
						if ( s>=0 ) return s;
						break;

					case 146 : 
						int LA13_35 = input.LA(1);
						 
						int index13_35 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_35=='w') ) {s = 65;}
						else if ( ((LA13_35 >= '0' && LA13_35 <= '9')||(LA13_35 >= 'A' && LA13_35 <= 'Z')||(LA13_35 >= 'a' && LA13_35 <= 'v')||(LA13_35 >= 'x' && LA13_35 <= 'z')) ) {s = 31;}
						else if ( (LA13_35=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 66;
						 
						input.seek(index13_35);
						if ( s>=0 ) return s;
						break;

					case 147 : 
						int LA13_53 = input.LA(1);
						 
						int index13_53 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_53=='l') ) {s = 100;}
						else if ( ((LA13_53 >= '0' && LA13_53 <= '9')||(LA13_53 >= 'A' && LA13_53 <= 'Z')||(LA13_53 >= 'a' && LA13_53 <= 'k')||(LA13_53 >= 'm' && LA13_53 <= 'z')) ) {s = 31;}
						else if ( (LA13_53=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 101;
						 
						input.seek(index13_53);
						if ( s>=0 ) return s;
						break;

					case 148 : 
						int LA13_100 = input.LA(1);
						 
						int index13_100 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_100=='u') ) {s = 140;}
						else if ( ((LA13_100 >= '0' && LA13_100 <= '9')||(LA13_100 >= 'A' && LA13_100 <= 'Z')||(LA13_100 >= 'a' && LA13_100 <= 't')||(LA13_100 >= 'v' && LA13_100 <= 'z')) ) {s = 31;}
						else if ( (LA13_100=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 141;
						 
						input.seek(index13_100);
						if ( s>=0 ) return s;
						break;

					case 149 : 
						int LA13_140 = input.LA(1);
						 
						int index13_140 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_140=='m') ) {s = 175;}
						else if ( ((LA13_140 >= '0' && LA13_140 <= '9')||(LA13_140 >= 'A' && LA13_140 <= 'Z')||(LA13_140 >= 'a' && LA13_140 <= 'l')||(LA13_140 >= 'n' && LA13_140 <= 'z')) ) {s = 31;}
						else if ( (LA13_140=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 176;
						 
						input.seek(index13_140);
						if ( s>=0 ) return s;
						break;

					case 150 : 
						int LA13_175 = input.LA(1);
						 
						int index13_175 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_175=='e') ) {s = 201;}
						else if ( ((LA13_175 >= '0' && LA13_175 <= '9')||(LA13_175 >= 'A' && LA13_175 <= 'Z')||(LA13_175 >= 'a' && LA13_175 <= 'd')||(LA13_175 >= 'f' && LA13_175 <= 'z')) ) {s = 31;}
						else if ( (LA13_175=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 202;
						 
						input.seek(index13_175);
						if ( s>=0 ) return s;
						break;

					case 151 : 
						int LA13_84 = input.LA(1);
						 
						int index13_84 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_84 >= '0' && LA13_84 <= '9')||(LA13_84 >= 'A' && LA13_84 <= 'Z')||(LA13_84 >= 'a' && LA13_84 <= 'z')) ) {s = 31;}
						else if ( (LA13_84=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 125;
						 
						input.seek(index13_84);
						if ( s>=0 ) return s;
						break;

					case 152 : 
						int LA13_38 = input.LA(1);
						 
						int index13_38 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_38=='e') ) {s = 70;}
						else if ( ((LA13_38 >= '0' && LA13_38 <= '9')||(LA13_38 >= 'A' && LA13_38 <= 'Z')||(LA13_38 >= 'a' && LA13_38 <= 'd')||(LA13_38 >= 'f' && LA13_38 <= 'z')) ) {s = 31;}
						else if ( (LA13_38=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 71;
						 
						input.seek(index13_38);
						if ( s>=0 ) return s;
						break;

					case 153 : 
						int LA13_70 = input.LA(1);
						 
						int index13_70 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_70=='r') ) {s = 111;}
						else if ( ((LA13_70 >= '0' && LA13_70 <= '9')||(LA13_70 >= 'A' && LA13_70 <= 'Z')||(LA13_70 >= 'a' && LA13_70 <= 'q')||(LA13_70 >= 's' && LA13_70 <= 'z')) ) {s = 31;}
						else if ( (LA13_70=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 112;
						 
						input.seek(index13_70);
						if ( s>=0 ) return s;
						break;

					case 154 : 
						int LA13_14 = input.LA(1);
						 
						int index13_14 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_14=='o') ) {s = 46;}
						else if ( ((LA13_14 >= '0' && LA13_14 <= '9')||(LA13_14 >= 'A' && LA13_14 <= 'Z')||(LA13_14 >= 'a' && LA13_14 <= 'n')||(LA13_14 >= 'p' && LA13_14 <= 'z')) ) {s = 31;}
						else if ( (LA13_14=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_14);
						if ( s>=0 ) return s;
						break;

					case 155 : 
						int LA13_45 = input.LA(1);
						 
						int index13_45 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_45=='r') ) {s = 84;}
						else if ( ((LA13_45 >= '0' && LA13_45 <= '9')||(LA13_45 >= 'A' && LA13_45 <= 'Z')||(LA13_45 >= 'a' && LA13_45 <= 'q')||(LA13_45 >= 's' && LA13_45 <= 'z')) ) {s = 31;}
						else if ( (LA13_45=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 85;
						 
						input.seek(index13_45);
						if ( s>=0 ) return s;
						break;

					case 156 : 
						int LA13_239 = input.LA(1);
						 
						int index13_239 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_239 >= '0' && LA13_239 <= '9')||(LA13_239 >= 'A' && LA13_239 <= 'Z')||(LA13_239 >= 'a' && LA13_239 <= 'z')) ) {s = 31;}
						else if ( (LA13_239=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 254;
						 
						input.seek(index13_239);
						if ( s>=0 ) return s;
						break;

					case 157 : 
						int LA13_18 = input.LA(1);
						 
						int index13_18 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_18=='p') ) {s = 51;}
						else if ( ((LA13_18 >= '0' && LA13_18 <= '9')||(LA13_18 >= 'A' && LA13_18 <= 'Z')||(LA13_18 >= 'a' && LA13_18 <= 'o')||(LA13_18 >= 'q' && LA13_18 <= 'z')) ) {s = 31;}
						else if ( (LA13_18=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_18);
						if ( s>=0 ) return s;
						break;

					case 158 : 
						int LA13_8 = input.LA(1);
						 
						int index13_8 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_8=='r') ) {s = 37;}
						else if ( (LA13_8=='v') ) {s = 38;}
						else if ( (LA13_8=='p') ) {s = 39;}
						else if ( ((LA13_8 >= '0' && LA13_8 <= '9')||(LA13_8 >= 'A' && LA13_8 <= 'Z')||(LA13_8 >= 'a' && LA13_8 <= 'o')||LA13_8=='q'||(LA13_8 >= 's' && LA13_8 <= 'u')||(LA13_8 >= 'w' && LA13_8 <= 'z')) ) {s = 31;}
						else if ( (LA13_8=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_8);
						if ( s>=0 ) return s;
						break;

					case 159 : 
						int LA13_42 = input.LA(1);
						 
						int index13_42 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_42=='o') ) {s = 78;}
						else if ( ((LA13_42 >= '0' && LA13_42 <= '9')||(LA13_42 >= 'A' && LA13_42 <= 'Z')||(LA13_42 >= 'a' && LA13_42 <= 'n')||(LA13_42 >= 'p' && LA13_42 <= 'z')) ) {s = 31;}
						else if ( (LA13_42=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 79;
						 
						input.seek(index13_42);
						if ( s>=0 ) return s;
						break;

					case 160 : 
						int LA13_78 = input.LA(1);
						 
						int index13_78 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_78=='s') ) {s = 119;}
						else if ( ((LA13_78 >= '0' && LA13_78 <= '9')||(LA13_78 >= 'A' && LA13_78 <= 'Z')||(LA13_78 >= 'a' && LA13_78 <= 'r')||(LA13_78 >= 't' && LA13_78 <= 'z')) ) {s = 31;}
						else if ( (LA13_78=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 120;
						 
						input.seek(index13_78);
						if ( s>=0 ) return s;
						break;

					case 161 : 
						int LA13_1 = input.LA(1);
						 
						int index13_1 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_1=='n') ) {s = 29;}
						else if ( (LA13_1=='l') ) {s = 30;}
						else if ( ((LA13_1 >= '0' && LA13_1 <= '9')||(LA13_1 >= 'A' && LA13_1 <= 'Z')||(LA13_1 >= 'a' && LA13_1 <= 'k')||LA13_1=='m'||(LA13_1 >= 'o' && LA13_1 <= 'z')) ) {s = 31;}
						else if ( (LA13_1=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_1);
						if ( s>=0 ) return s;
						break;

					case 162 : 
						int LA13_119 = input.LA(1);
						 
						int index13_119 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_119=='s') ) {s = 155;}
						else if ( ((LA13_119 >= '0' && LA13_119 <= '9')||(LA13_119 >= 'A' && LA13_119 <= 'Z')||(LA13_119 >= 'a' && LA13_119 <= 'r')||(LA13_119 >= 't' && LA13_119 <= 'z')) ) {s = 31;}
						else if ( (LA13_119=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 156;
						 
						input.seek(index13_119);
						if ( s>=0 ) return s;
						break;

					case 163 : 
						int LA13_155 = input.LA(1);
						 
						int index13_155 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_155=='e') ) {s = 185;}
						else if ( ((LA13_155 >= '0' && LA13_155 <= '9')||(LA13_155 >= 'A' && LA13_155 <= 'Z')||(LA13_155 >= 'a' && LA13_155 <= 'd')||(LA13_155 >= 'f' && LA13_155 <= 'z')) ) {s = 31;}
						else if ( (LA13_155=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 186;
						 
						input.seek(index13_155);
						if ( s>=0 ) return s;
						break;

					case 164 : 
						int LA13_50 = input.LA(1);
						 
						int index13_50 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_50=='v') ) {s = 94;}
						else if ( ((LA13_50 >= '0' && LA13_50 <= '9')||(LA13_50 >= 'A' && LA13_50 <= 'Z')||(LA13_50 >= 'a' && LA13_50 <= 'u')||(LA13_50 >= 'w' && LA13_50 <= 'z')) ) {s = 31;}
						else if ( (LA13_50=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 95;
						 
						input.seek(index13_50);
						if ( s>=0 ) return s;
						break;

					case 165 : 
						int LA13_44 = input.LA(1);
						 
						int index13_44 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_44=='u') ) {s = 82;}
						else if ( ((LA13_44 >= '0' && LA13_44 <= '9')||(LA13_44 >= 'A' && LA13_44 <= 'Z')||(LA13_44 >= 'a' && LA13_44 <= 't')||(LA13_44 >= 'v' && LA13_44 <= 'z')) ) {s = 31;}
						else if ( (LA13_44=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 83;
						 
						input.seek(index13_44);
						if ( s>=0 ) return s;
						break;

					case 166 : 
						int LA13_94 = input.LA(1);
						 
						int index13_94 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_94=='e') ) {s = 134;}
						else if ( ((LA13_94 >= '0' && LA13_94 <= '9')||(LA13_94 >= 'A' && LA13_94 <= 'Z')||(LA13_94 >= 'a' && LA13_94 <= 'd')||(LA13_94 >= 'f' && LA13_94 <= 'z')) ) {s = 31;}
						else if ( (LA13_94=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 135;
						 
						input.seek(index13_94);
						if ( s>=0 ) return s;
						break;

					case 167 : 
						int LA13_185 = input.LA(1);
						 
						int index13_185 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_185=='s') ) {s = 211;}
						else if ( ((LA13_185 >= '0' && LA13_185 <= '9')||(LA13_185 >= 'A' && LA13_185 <= 'Z')||(LA13_185 >= 'a' && LA13_185 <= 'r')||(LA13_185 >= 't' && LA13_185 <= 'z')) ) {s = 31;}
						else if ( (LA13_185=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 212;
						 
						input.seek(index13_185);
						if ( s>=0 ) return s;
						break;

					case 168 : 
						int LA13_82 = input.LA(1);
						 
						int index13_82 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_82=='a') ) {s = 123;}
						else if ( ((LA13_82 >= '0' && LA13_82 <= '9')||(LA13_82 >= 'A' && LA13_82 <= 'Z')||(LA13_82 >= 'b' && LA13_82 <= 'z')) ) {s = 31;}
						else if ( (LA13_82=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 124;
						 
						input.seek(index13_82);
						if ( s>=0 ) return s;
						break;

					case 169 : 
						int LA13_134 = input.LA(1);
						 
						int index13_134 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_134=='r') ) {s = 170;}
						else if ( ((LA13_134 >= '0' && LA13_134 <= '9')||(LA13_134 >= 'A' && LA13_134 <= 'Z')||(LA13_134 >= 'a' && LA13_134 <= 'q')||(LA13_134 >= 's' && LA13_134 <= 'z')) ) {s = 31;}
						else if ( (LA13_134=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 171;
						 
						input.seek(index13_134);
						if ( s>=0 ) return s;
						break;

					case 170 : 
						int LA13_170 = input.LA(1);
						 
						int index13_170 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_170=='s') ) {s = 197;}
						else if ( ((LA13_170 >= '0' && LA13_170 <= '9')||(LA13_170 >= 'A' && LA13_170 <= 'Z')||(LA13_170 >= 'a' && LA13_170 <= 'r')||(LA13_170 >= 't' && LA13_170 <= 'z')) ) {s = 31;}
						else if ( (LA13_170=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 198;
						 
						input.seek(index13_170);
						if ( s>=0 ) return s;
						break;

					case 171 : 
						int LA13_211 = input.LA(1);
						 
						int index13_211 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_211==' ') ) {s = 229;}
						else if ( ((LA13_211 >= '0' && LA13_211 <= '9')||(LA13_211 >= 'A' && LA13_211 <= 'Z')||(LA13_211 >= 'a' && LA13_211 <= 'z')) ) {s = 31;}
						else if ( (LA13_211=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 230;
						 
						input.seek(index13_211);
						if ( s>=0 ) return s;
						break;

					case 172 : 
						int LA13_123 = input.LA(1);
						 
						int index13_123 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_123=='l') ) {s = 159;}
						else if ( ((LA13_123 >= '0' && LA13_123 <= '9')||(LA13_123 >= 'A' && LA13_123 <= 'Z')||(LA13_123 >= 'a' && LA13_123 <= 'k')||(LA13_123 >= 'm' && LA13_123 <= 'z')) ) {s = 31;}
						else if ( (LA13_123=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 160;
						 
						input.seek(index13_123);
						if ( s>=0 ) return s;
						break;

					case 173 : 
						int LA13_197 = input.LA(1);
						 
						int index13_197 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_197=='e') ) {s = 219;}
						else if ( ((LA13_197 >= '0' && LA13_197 <= '9')||(LA13_197 >= 'A' && LA13_197 <= 'Z')||(LA13_197 >= 'a' && LA13_197 <= 'd')||(LA13_197 >= 'f' && LA13_197 <= 'z')) ) {s = 31;}
						else if ( (LA13_197=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 220;
						 
						input.seek(index13_197);
						if ( s>=0 ) return s;
						break;

					case 174 : 
						int LA13_159 = input.LA(1);
						 
						int index13_159 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_159=='s') ) {s = 188;}
						else if ( ((LA13_159 >= '0' && LA13_159 <= '9')||(LA13_159 >= 'A' && LA13_159 <= 'Z')||(LA13_159 >= 'a' && LA13_159 <= 'r')||(LA13_159 >= 't' && LA13_159 <= 'z')) ) {s = 31;}
						else if ( (LA13_159=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 189;
						 
						input.seek(index13_159);
						if ( s>=0 ) return s;
						break;

					case 175 : 
						int LA13_219 = input.LA(1);
						 
						int index13_219 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_219=='s') ) {s = 237;}
						else if ( ((LA13_219 >= '0' && LA13_219 <= '9')||(LA13_219 >= 'A' && LA13_219 <= 'Z')||(LA13_219 >= 'a' && LA13_219 <= 'r')||(LA13_219 >= 't' && LA13_219 <= 'z')) ) {s = 31;}
						else if ( (LA13_219=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 238;
						 
						input.seek(index13_219);
						if ( s>=0 ) return s;
						break;

					case 176 : 
						int LA13_237 = input.LA(1);
						 
						int index13_237 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_237==' ') ) {s = 252;}
						else if ( ((LA13_237 >= '0' && LA13_237 <= '9')||(LA13_237 >= 'A' && LA13_237 <= 'Z')||(LA13_237 >= 'a' && LA13_237 <= 'z')) ) {s = 31;}
						else if ( (LA13_237=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 253;
						 
						input.seek(index13_237);
						if ( s>=0 ) return s;
						break;

					case 177 : 
						int LA13_188 = input.LA(1);
						 
						int index13_188 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_188==' ') ) {s = 213;}
						else if ( ((LA13_188 >= '0' && LA13_188 <= '9')||(LA13_188 >= 'A' && LA13_188 <= 'Z')||(LA13_188 >= 'a' && LA13_188 <= 'z')) ) {s = 31;}
						else if ( (LA13_188=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 214;
						 
						input.seek(index13_188);
						if ( s>=0 ) return s;
						break;

					case 178 : 
						int LA13_111 = input.LA(1);
						 
						int index13_111 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_111=='r') ) {s = 148;}
						else if ( ((LA13_111 >= '0' && LA13_111 <= '9')||(LA13_111 >= 'A' && LA13_111 <= 'Z')||(LA13_111 >= 'a' && LA13_111 <= 'q')||(LA13_111 >= 's' && LA13_111 <= 'z')) ) {s = 31;}
						else if ( (LA13_111=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 149;
						 
						input.seek(index13_111);
						if ( s>=0 ) return s;
						break;

					case 179 : 
						int LA13_19 = input.LA(1);
						 
						int index13_19 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_19=='i') ) {s = 52;}
						else if ( ((LA13_19 >= '0' && LA13_19 <= '9')||(LA13_19 >= 'A' && LA13_19 <= 'Z')||(LA13_19 >= 'a' && LA13_19 <= 'h')||(LA13_19 >= 'j' && LA13_19 <= 'z')) ) {s = 31;}
						else if ( (LA13_19=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_19);
						if ( s>=0 ) return s;
						break;

					case 180 : 
						int LA13_56 = input.LA(1);
						 
						int index13_56 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_56 >= '0' && LA13_56 <= '9')||(LA13_56 >= 'A' && LA13_56 <= 'Z')||(LA13_56 >= 'a' && LA13_56 <= 'z')) ) {s = 31;}
						else if ( (LA13_56=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 102;
						 
						input.seek(index13_56);
						if ( s>=0 ) return s;
						break;

					case 181 : 
						int LA13_0 = input.LA(1);
						 
						int index13_0 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_0=='a') ) {s = 1;}
						else if ( (LA13_0==')') ) {s = 2;}
						else if ( (LA13_0==';') ) {s = 3;}
						else if ( (LA13_0=='d') ) {s = 4;}
						else if ( (LA13_0=='l') ) {s = 5;}
						else if ( (LA13_0=='n') ) {s = 6;}
						else if ( (LA13_0=='(') ) {s = 7;}
						else if ( (LA13_0=='o') ) {s = 8;}
						else if ( (LA13_0=='%') ) {s = 9;}
						else if ( (LA13_0=='b') ) {s = 10;}
						else if ( (LA13_0=='c') ) {s = 11;}
						else if ( (LA13_0=='e') ) {s = 12;}
						else if ( (LA13_0=='f') ) {s = 13;}
						else if ( (LA13_0=='g') ) {s = 14;}
						else if ( (LA13_0=='i') ) {s = 15;}
						else if ( (LA13_0=='m') ) {s = 16;}
						else if ( (LA13_0=='r') ) {s = 17;}
						else if ( (LA13_0=='s') ) {s = 18;}
						else if ( (LA13_0=='h') ) {s = 19;}
						else if ( (LA13_0=='-') ) {s = 20;}
						else if ( ((LA13_0 >= '0' && LA13_0 <= '9')) ) {s = 21;}
						else if ( (LA13_0=='\"') ) {s = 22;}
						else if ( (LA13_0=='v') ) {s = 23;}
						else if ( ((LA13_0 >= 'A' && LA13_0 <= 'Z')||(LA13_0 >= 'j' && LA13_0 <= 'k')||(LA13_0 >= 'p' && LA13_0 <= 'q')||(LA13_0 >= 't' && LA13_0 <= 'u')||(LA13_0 >= 'w' && LA13_0 <= 'z')) ) {s = 24;}
						else if ( (LA13_0==' ') ) {s = 25;}
						else if ( (LA13_0=='_') && ((runtimeOpAhead()))) {s = 26;}
						else if ( ((LA13_0 >= '\t' && LA13_0 <= '\n')||(LA13_0 >= '\f' && LA13_0 <= '\r')) ) {s = 27;}
						else if ( (LA13_0=='/') ) {s = 28;}
						 
						input.seek(index13_0);
						if ( s>=0 ) return s;
						break;

					case 182 : 
						int LA13_23 = input.LA(1);
						 
						int index13_23 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_23=='o') ) {s = 53;}
						else if ( ((LA13_23 >= '0' && LA13_23 <= '9')||(LA13_23 >= 'A' && LA13_23 <= 'Z')||(LA13_23 >= 'a' && LA13_23 <= 'n')||(LA13_23 >= 'p' && LA13_23 <= 'z')) ) {s = 31;}
						else if ( (LA13_23=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_23);
						if ( s>=0 ) return s;
						break;

					case 183 : 
						int LA13_105 = input.LA(1);
						 
						int index13_105 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_105 >= '0' && LA13_105 <= '9')||(LA13_105 >= 'A' && LA13_105 <= 'Z')||(LA13_105 >= 'a' && LA13_105 <= 'z')) ) {s = 31;}
						else if ( (LA13_105=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 144;
						 
						input.seek(index13_105);
						if ( s>=0 ) return s;
						break;

					case 184 : 
						int LA13_13 = input.LA(1);
						 
						int index13_13 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_13=='o') ) {s = 45;}
						else if ( ((LA13_13 >= '0' && LA13_13 <= '9')||(LA13_13 >= 'A' && LA13_13 <= 'Z')||(LA13_13 >= 'a' && LA13_13 <= 'n')||(LA13_13 >= 'p' && LA13_13 <= 'z')) ) {s = 31;}
						else if ( (LA13_13=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_13);
						if ( s>=0 ) return s;
						break;

					case 185 : 
						int LA13_10 = input.LA(1);
						 
						int index13_10 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_10=='e') ) {s = 40;}
						else if ( (LA13_10=='u') ) {s = 41;}
						else if ( ((LA13_10 >= '0' && LA13_10 <= '9')||(LA13_10 >= 'A' && LA13_10 <= 'Z')||(LA13_10 >= 'a' && LA13_10 <= 'd')||(LA13_10 >= 'f' && LA13_10 <= 't')||(LA13_10 >= 'v' && LA13_10 <= 'z')) ) {s = 31;}
						else if ( (LA13_10=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_10);
						if ( s>=0 ) return s;
						break;

					case 186 : 
						int LA13_203 = input.LA(1);
						 
						int index13_203 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_203 >= '0' && LA13_203 <= '9')||(LA13_203 >= 'A' && LA13_203 <= 'Z')||(LA13_203 >= 'a' && LA13_203 <= 'z')) ) {s = 31;}
						else if ( (LA13_203=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 224;
						 
						input.seek(index13_203);
						if ( s>=0 ) return s;
						break;

					case 187 : 
						int LA13_67 = input.LA(1);
						 
						int index13_67 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_67 >= '0' && LA13_67 <= '9')||(LA13_67 >= 'A' && LA13_67 <= 'Z')||(LA13_67 >= 'a' && LA13_67 <= 'z')) ) {s = 31;}
						else if ( (LA13_67=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 110;
						 
						input.seek(index13_67);
						if ( s>=0 ) return s;
						break;

					case 188 : 
						int LA13_31 = input.LA(1);
						 
						int index13_31 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_31 >= '0' && LA13_31 <= '9')||(LA13_31 >= 'A' && LA13_31 <= 'Z')||(LA13_31 >= 'a' && LA13_31 <= 'z')) ) {s = 31;}
						else if ( (LA13_31=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 60;
						 
						input.seek(index13_31);
						if ( s>=0 ) return s;
						break;

					case 189 : 
						int LA13_37 = input.LA(1);
						 
						int index13_37 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA13_37 >= '0' && LA13_37 <= '9')||(LA13_37 >= 'A' && LA13_37 <= 'Z')||(LA13_37 >= 'a' && LA13_37 <= 'z')) ) {s = 31;}
						else if ( (LA13_37=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 69;
						 
						input.seek(index13_37);
						if ( s>=0 ) return s;
						break;

					case 190 : 
						int LA13_12 = input.LA(1);
						 
						int index13_12 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_12=='q') ) {s = 44;}
						else if ( ((LA13_12 >= '0' && LA13_12 <= '9')||(LA13_12 >= 'A' && LA13_12 <= 'Z')||(LA13_12 >= 'a' && LA13_12 <= 'p')||(LA13_12 >= 'r' && LA13_12 <= 'z')) ) {s = 31;}
						else if ( (LA13_12=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_12);
						if ( s>=0 ) return s;
						break;

					case 191 : 
						int LA13_17 = input.LA(1);
						 
						int index13_17 = input.index();
						input.rewind();
						s = -1;
						if ( (LA13_17=='e') ) {s = 50;}
						else if ( ((LA13_17 >= '0' && LA13_17 <= '9')||(LA13_17 >= 'A' && LA13_17 <= 'Z')||(LA13_17 >= 'a' && LA13_17 <= 'd')||(LA13_17 >= 'f' && LA13_17 <= 'z')) ) {s = 31;}
						else if ( (LA13_17=='_') && ((runtimeOpAhead()))) {s = 26;}
						else s = 32;
						 
						input.seek(index13_17);
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
